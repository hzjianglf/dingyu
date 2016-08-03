/* begin_generated_IBM_copyright_prolog                            */
/* 
 * Licensed Materials - Property of IBM 
 * 
 * 5724-N72 5655-W17
 * 
 * (c) Copyright IBM Corp. 2008, 2009 All Rights Reserved 
 * 
 * US Government Users Restricted Rights - Use, duplication or 
 * disclosure restricted by GSA ADP Schedule Contract with 
 * IBM Corp. 
 */
/* end_generated_IBM_copyright_prolog                              */


//Enums for Navigator Actions
var NavActionSelect = new UIEnum(0);
var NavActionAddPolicy = new UIEnum(1);
var NavActionAddAsertion = new UIEnum(2);
var NavActionAddAttribute = new UIEnum(3);
var NavActionDelete = new UIEnum(4);
var NavActionDisplay = new UIEnum(5);
var NavActionInitialDisplay = new UIEnum(6);
var NavActionCancel = new UIEnum(7);
var NavActionPublish = new UIEnum(8);
var NavActionAddAsertion = new UIEnum(9);
var NavActionChoosePolicyRef = new UIEnum(10);
var NavActionAddFrameworkAsertion = new UIEnum(11);

//Enums for Details modes (what is shown in the bottom half of the screen)
var ModeShowAttributes = new UIEnum(0);
var ModeChooseAssertion = new UIEnum(1);
var ModeChoosePolicy = new UIEnum(2);
var ModeChoosePolicyRef = new UIEnum(3);
var ModeChooseAssertionReplace = new UIEnum(4);
var ModeChoosePolicyDomain = new UIEnum(5);
var ModeChooseFrameworkAssertion = new UIEnum(6);

//Maps the action to the mode for the detailsCache. The detailsCache only works for the attriubute panels
//not the choose panels, due to complications whene deleting assertions.
var actionToMode = { 5: 0};

//Enums for Element types. Used to display the icon and alt text for each navigator row
var ElementTypePolicyDocument = new UIEnum(0);
var ElementTypePolicyReference = new UIEnum(1);
var ElementTypePolicyClass = new UIEnum(2);
var ElementTypeAssertion = new UIEnum(3);
var ElementTypeAssertionInvalid = new UIEnum(4);
var ElementTypeAssertionUnknown = new UIEnum(5);
var ElementTypePolicyInvalid = new UIEnum(6);
var ElementTypePolicyUnknown = new UIEnum(7);
var ElementTypeAllOf = new UIEnum(8);
var ElementTypeOneOf = new UIEnum(9);
var ElementTypePolicyAttachment = new UIEnum(10);
var ElementTypeAssertionSelector = new UIEnum(11);
var ElementTypeAssertionSelectorInvalid = new UIEnum(12);
var ElementTypeAssertionSelectorUnknown = new UIEnum(13);

//Enum to select what kind of policy reference to add
var RefOrClassReferenceInternal = new UIEnum(0);
var RefOrClassReferenceExternal = new UIEnum(1);
var RefOrClassClass = new UIEnum(2);
var RefOrClassSingleNameMatchExternal = new UIEnum(3);

//
var TypeText = "TEXT";
var TypeTextArea = "TEXTAREA";

//Global variables
var globalOptions = null;

//Records of the last operation performed
var thisOp = NavActionDisplay.value;

//Cache of the last PolicyAuthBean we received from the server that had ShowAttributes set as it's details mode
var lastDisplayBean = null;

//Are we in Display mode or Choose mode.
var currentMode = null;

//Cache to order the navigator rows by ID instead of index
var structureCache = null;

//Cache to order the attributes for the details panel by ID instead of index
var attributesCache = null;

//Cache to store all details client side
var detailsCache = [];

//ID of the row we were on before the user clicked a link that resulted in a Choose action
var cancelToID = null;

//ID of the row that was most recently selected
var lastSelectedID = null;

//ID of the row that is currently selected
var currentlySelectedID = null;

//Used to kick off some initial processing when teh first response comes back from the server.
var firstTime = true;

//ID of the last added navigator row. We need to scroll to it, and highlight it once it has been added
var lastAddedID = null;

//ID of the last added attribute. We need to set focus to it once it has been added
var lastAddedAttributeID = null;

//Used to show the description for policies and assertions
var globalID = 0;

//Holds a reference to the suggest widget.
var suggest = null;

// size of td for text measuring
var sizeOfScrollArea = null;

//Hashmap of attribute.ID -> PolicyStructureBean, used to update the navigator attributes
//when the user edits them in the details panel.
var strucForAttr = [];

//used to forward to a policy expression if the user reuquests it.
var jumpToPolicy = null;

//Keeps track of whether the publish button is active or disabled.
var publishOK = false;

// height of the scrollable table in pixels. Used to position the new element in
// the middle of the table.
var tableHeight = 170;

// id of span showing progress in empty details
var DETAILS_SHOW_PROGRESS = "detailsShowProgress";
// id of span showing progress in empty navigator
var NAVIGATOR_SHOW_PROGRESS = "navigatorShowProgress";

dojo.registerModulePath("com.ibm.sr.widgets", "../widgets");
dojo.require("com.ibm.sr.widgets.AutoSuggest");
dojo.require( "dijit._base" );
dojo.require("dijit.Tooltip");

/**
 * displayAfterLoad is called as soon as the page has loaded. It starts the timer animation and then
 * sends a request to the server for all of the page data. If we have just returned from an action
 * to add a policy reference then we send a request to add the policy reference instead
 */
function displayAfterLoad() {	

	//Create the policy service !!
	policyService = new dojo.rpc.JsonService(contextRoot + "/RPCAdapter/jsonrpc/PolicyService");
	
	//Bug #5182. On a secure system after recovery from a timeout the policyService may not
	//be initialized properly. Check if the ServiceUrl is defined and if not then send
	//the request again and all shall be well.
	if (policyService == null || policyService.serviceUrl == null)
		policyService = new dojo.rpc.JsonService(contextRoot + "/RPCAdapter/jsonrpc/PolicyService");

	// show loading message
	startProgressImmediate("renderingMessage", renderingMessage);
		
	if (inProgress == false) {
		if (policyRefUri != null && policyRefUri != "null")
			addCallErrBack(policyService.policyAction(-1,RefOrClassReferenceExternal,policyRefUri),displayPolicyAuthBean,errorBack);
		else		
			addCallErrBack(policyService.navigatorAction(NavActionInitialDisplay,-1,null),displayPolicyAuthBean,errorBack);
	}
}

/**
 * Start whatever progress indicator is needed for the nav action.
 */
function setupProgressIndicator(nodeID, opID) {
	// for delete actions we should show progress on the delete link itself
	if(opID == NavActionDelete.value) {
		// work out span id
		var spanId = "NavOpsID_" + nodeID + "_" + opID;
		// replace Delete link with progress if it comes up
		startProgress(spanId, deletingMessage);	
	} else {
		// call function to show detail panel if it takes too long
		startProgressFunction(DETAILS_SHOW_PROGRESS, renderingMessage, displayEmptyDetailsPanelProgress);
	}
}

/*
 * Show an empty details panel with an element we can put the progress in.
 *
 * We cannot display a title here in the tab because literally anything
 * can come back from the server, depending on the navigator action
 * being taken.
 *
 * This is called once the progress has timed out and is showing the
 * spinner.
 */
function displayEmptyDetailsPanelProgress() {
   	var detailsDivHtml = new dojox.string.Builder();

	var detailsDiv = document.getElementById("propertiesSection");
	
	//Set title to blank with spaces so the tab renders correctly
	setTabTitle(" &nbsp; &nbsp; &nbsp; &nbsp; ");

	// add element to show progress in
	detailsDivHtml.append("<span id=\"" + DETAILS_SHOW_PROGRESS + "\"></span>");
						
	// Set the HTML for the details div
	detailsDiv.innerHTML = detailsDivHtml.toString();
}

/**
 * Display an empty navigator with the progress element for navigators in it.
 *
 * Also blanks the details panel. Save the original in case of error.
 *
 */
function displayEmptyNavigatorProgress() {
	var navigatorDiv = document.getElementById("navigator");

	// save old html
	progressOldHtml = navigatorDiv.innerHTML;			
			
	// Create a variable to hold the HTML for navigator
  	var navDivHtml = new dojox.string.Builder();

	navDivHtml.append("<div class=\"scrollablesection\" id=\"scrollreltable\">");
	navDivHtml.append("<table class=\"relationshiptable\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\"structuretable\">");
	navDivHtml.append("<thead><tr><th class=\"right\"><span>");
    navDivHtml.append(textContents);
    navDivHtml.append("</span></th></tr></thead><tbody>");

	navDivHtml.append("<tr id=\"NavTRID_progress");
	navDivHtml.append("\" class=\"");
	navDivHtml.append("notselected\">");
		
	navDivHtml.append("<td");
	navDivHtml.append(" class=\"right\">");

	navDivHtml.append("<span id=\"");
	navDivHtml.append(NAVIGATOR_SHOW_PROGRESS);
	navDivHtml.append("\">");
	
	navDivHtml.append("</td>");
	navDivHtml.append("</tr>");		
	
   	navDivHtml.append("</tbody></table></div>");

	// Set the HTML for the navigator div
	navigatorDiv.innerHTML = navDivHtml.toString();
	
	// set the details panel to blank - we cannot hide it
	displayEmptyDetailsPanelProgress();
}

/**
 * displayPolicyAuthBean renders the entire page by callign other sub functions
 */
function displayPolicyAuthBean(bean, exception) {

	var error = endTransaction(bean,exception);
	stopProgress();
	
	if (firstTime) {
	
		// measure text
		measureTextWidthTD();
		
		// measure size of scrollable area
		sizeOfScrollArea = measureNode(dojo.byId("mainButtons"));
		
		//Show tab and details section
		var tabtable = document.getElementById("childtabtable");
		var detailtable = document.getElementById("detailcontenttable");
		
		tabtable.style.display = "block";
		detailtable.style.display = "block";		
		
		//Set the portlet title
		if (readOnly == false) {
			var titleDiv = document.getElementById("editpolicyviewheading");
			
			if (bean.bsrURI != null && bean.bsrURI != "")		
				titleDiv.innerHTML = textEditPolicy;
			else
				titleDiv.innerHTML = textNewPolicy;
		}
		
		doMainButtonsHtml();							
	}
	
	if (error == false && bean != null) {
			
		var structureThisTime = null;
		
		//Save the bean into teh global var for Cancel purposes	
		if (bean.details.mode.value == ModeShowAttributes.value) {				
		
			//Check to see if there is any structure returned.
			if (bean.oneRow == true && bean.structure != null && bean.structure.length == 1) {			
				//This is a one row job, so do something similar to the no structure case
				lastDisplayBean.details = bean.details;
				lastSelectedID = lastDisplayBean.selectedID;
				lastDisplayBean.selectedID = bean.selectedID;				
				
				//copy the new structure bean in
				var index = structureCache[bean.structure[0].ID];
				bean.structure[0].level = lastDisplayBean.structure[index].level
				lastDisplayBean.structure[index] = new cloneObject(bean.structure[0]);
			} else if (bean.structure != null && bean.structure.length > 0) {
				//save the bean in the global var
				lastDisplayBean = bean;
				lastSelectedID = lastDisplayBean.selectedID;				
			} else {
				// Copy the details to the global var and then point bean to the global var
				lastDisplayBean.details = bean.details;
				lastSelectedID = lastDisplayBean.selectedID;
				lastDisplayBean.selectedID = bean.selectedID;				
				structureThisTime = bean.structure;
				bean = new cloneObject(lastDisplayBean);			
				bean.structure = structureThisTime;
			}

			//Need to overwrite the lastSelectedID with the cancelToID if it's set
			//to simulate the effect of pressing the cancel button and then selecting a new row
			if (cancelToID != null) {
				lastSelectedID = cancelToID;
				cancelToID = null;
			}
			
		} else {
			//If not in show attrs mode then save the currently selected ID and the last selected ID
			if (cancelToID != null)
				lastSelectedID = cancelToID;
			else
				lastSelectedID = lastDisplayBean.selectedID;
			
			cancelToID = bean.selectedID;
		}
		
		currentlySelectedID = bean.selectedID;

		if (bean.oneRow == true && bean.structure != null && bean.structure.length == 1) {
			//Just update the single row
			var navDivHtml = new dojox.string.Builder();
			var row = document.getElementById("NavTDID_" + bean.structure[0].ID);

			addStructureTDHtml(lastSelectedID, bean.structure[0], navDivHtml);
			
			row.innerHTML = navDivHtml.toString();
		} else  {
			//Display the structure section
			displayNavigatorSection(bean);
		}		
		
		//If this a special Cancel Choose policy display	
		if (bean.choosePolicyDetails != null) {
			displayPolicyDetailsBeanInt(bean.choosePolicyDetails);
			lastDisplayBean.choosePolicyDetails = null;
		} else {
			displayPolicyDetailsBeanInt(bean.details);		
		}
	}	
	
	if (firstTime) {
		// resize to redo ellipsis
		dojo.connect(window, 'onresize', 'ellipsiseRows');
				
		firstTime = false;
		stopProgress();
	}		
}

function displayNavigatorSection(bean) {

	var start = new Date().getTime();
	
	var previousScrollPos = -1;
	
	var scrollBar = document.getElementById("scrollreltable");
	
	//If there is a scrollbar then move it to the correct place
	if (scrollBar != null && firstTime == false) {
		previousScrollPos = scrollBar.scrollTop;
		var tmp = scrollBar.getElementsByTagName("tbody");
				
		if (tmp[0].scrollTop > previousScrollPos) {
			scrollBar = tmp[0];
			previousScrollPos = scrollBar.scrollTop;
		}						
	}
	
	// Retrieve the divs for the navigator and the details
	var navigatorDiv = document.getElementById("navigator");
			
	if (bean.structure != null && bean.structure.length > 0) {	
		//Create a new structure cache and op cache
		structureCache = [];
		opCache = [];

		// Create a variable to hold the HTML for navigator
	   	var navDivHtml = new dojox.string.Builder();

		navDivHtml.append("<div class=\"scrollablesection\" id=\"scrollreltable\">");
		navDivHtml.append("<table class=\"relationshiptable\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\"structuretable\">");
		navDivHtml.append("<thead><tr><th class=\"right\"><span>");
        navDivHtml.append(textContents);
        navDivHtml.append("</span></th></tr></thead><tbody>");
                	
		for (var i = 0; i < bean.structure.length; i++) {
			//Add this line to the cache
			structureCache[bean.structure[i].ID] = i;
		
			// Append a row for each navigatable row
			if (i == bean.structure.length-1)
				addStructureHtml(bean.selectedID, bean.structure[i], navDivHtml, true);
			else
				addStructureHtml(bean.selectedID, bean.structure[i], navDivHtml, false);							
		}

        //Display measuring div
		navDivHtml.append("<tr><td class=\"right\">");
		navDivHtml.append("<div class=\"textmeasureellipsis\" id=\"textmeasure\"></div></td></tr>");        

       	navDivHtml.append("</tbody></table></div>");

		if (firstTime) {
			stopProgress();
		}
					
		// Set the HTML for the navigator div
		navigatorDiv.innerHTML = navDivHtml.toString();
		
		//fix for firefox to make it scrollable
		makeScrollable('scrollreltable');
		
		//Put the scrollbar back where it was, or move it to the position of the new element
		if (scrollBar != null) {			
			if (lastAddedID != null) {
				//Position the new element in the middle of the nav section
				var index = indexOfSelectedID(lastAddedID);
				placeScrollBar(previousScrollPos,index,bean.structure.length);
				lastAddedID = null;
			} else {
				//Scroll it back to where it was
				var index = indexOfSelectedID(bean.selectedID);
				placeScrollBar(previousScrollPos,index,lastDisplayBean.structure.length);						
			}
		} else {
			//Need this to scroll to the last selected one when we come back to the page
			//from policy select
			var index = indexOfSelectedID(bean.selectedID);
			placeScrollBar(previousScrollPos,index,bean.structure.length);

		}				
	} else {
		swapActiveTR(lastSelectedID, bean.selectedID);	

		//Scroll to the right position		
		var index = indexOfSelectedID(bean.selectedID);
		placeScrollBar(previousScrollPos,index,lastDisplayBean.structure.length);			
	}
	
	var end = new Date().getTime();
	//debugIt("addStructureHtml took " + (end - start));
	
}

//Position the scrollbar either back where it was. Or if the selected row is now
//out of the visible area then chase to the selected row. By chase I mean if the row
//is before the current scrollbar position then then position that row at the top of the 
//visible area, and if the row is after the scrollbar then position that row at the bottom
//of the scrollbar. This way the user knows which way the scrollbar has automatically scrolled.
function placeScrollBar(previousScrollPos,index,listLength) {
	var scrollBar = document.getElementById("scrollreltable");			
	var tbodyScrollBar = scrollBar.getElementsByTagName("tbody");

	if (tbodyScrollBar[0].scrollHeight > scrollBar.scrollHeight) {
		var scrollHeight = tbodyScrollBar[0].scrollHeight;
		var addOne  = 0;
	} else {
		var scrollHeight = scrollBar.scrollHeight;		
		var addOne  = 1;
	}
	
	var oneRowInPixels = scrollHeight / (listLength + addOne);		
	//alert(oneRowInPixels+ " " + scrollHeight+ " " +listLength);		
	
	var newPositionTop = parseInt((index + addOne)*oneRowInPixels) ;
		
	//alert(index + " " + tableHeight + " ");
	
	var ieScrollBar = 0;
	if (dojo.isIE)
		ieScrollBar = 8;
		
	var maxPreviousScrollPos = parseInt(previousScrollPos + tableHeight - oneRowInPixels);
	var minPreviousScrollPos = parseInt(previousScrollPos);
	
	if (newPositionTop < minPreviousScrollPos || minPreviousScrollPos == -1) {
		var newPosition = newPositionTop;	
	} else if (newPositionTop + ieScrollBar > maxPreviousScrollPos) {
		var newPosition = parseInt(newPositionTop - tableHeight + oneRowInPixels);		
		
		//Account for the horizontal scrollbar in IE	
		if (dojo.isIE) 
			newPosition += 16;
		
		if (newPosition < 0)
			newPosition = 0;
	} else {
		var newPosition = previousScrollPos;
	}
	
	//alert("newPosition: " + newPosition + "newPositionTop: " + newPositionTop + " low: " + minPreviousScrollPos + " high: " + maxPreviousScrollPos);
	
	scrollBar.scrollTop = newPosition;
	tbodyScrollBar[0].scrollTop = newPosition;
}

function displayPolicyDetailsBean(details,exception) {

	var error = endTransaction(details,exception);

	if (error == false && details != null)
		displayPolicyDetailsBeanInt(details);
}

function displayPolicyDetailsBeanInt(details) {

   	var detailsDivHtml = new dojox.string.Builder();

	var detailsDiv = document.getElementById("propertiesSection");
	
	
	detailsCache[currentlySelectedID + "_" + details.mode.value] = cloneObject(details);
	//alert("add key: " + currentlySelectedID + "_" + details.mode.value);	
	
	if (details.mode.value == ModeShowAttributes.value) {
	
		//save the bean in the global var
		lastDisplayBean.details = details;			
		
		//Set title
		setTabTitle(textDetails);
		
		currentMode = "Display";
	
		firstID = addAttributesHtml(lastDisplayBean.selectedID, details, detailsDivHtml);
						
		// Set the HTML for the details div
		detailsDiv.innerHTML = detailsDivHtml.toString();			
					
		//If we just added an attribute then focus on it
		if (lastAddedAttributeID != null)
			setFocus("inputID_" + lastAddedAttributeID);				
		else
			setFocusDetails(firstID);
			
	} else if (details.mode.value == ModeChoosePolicy.value) {
		//Save the old selectedID and then put in the new one
		savedID = lastDisplayBean.selectedID;
		lastDisplayBean.selectedID = details.parentID;
		
		//Set the title the server told us to.
		setTabTitle(details.title);
		
		currentMode = "Choose";
		
		//Reinstate the last selectedID
		lastDisplayBean.selectedID = savedID;
	
		addChoosePolicyHtml(details,detailsDivHtml);
		
		setFocus("inputID_choosePolicy");
		
		// Set the HTML for the details div
		detailsDiv.innerHTML = detailsDivHtml.toString();					
			
		//Set the description for the currently selected policy
		if (details.policies.length > 0)
			showPolicyAssDesc("inputID_choosePolicy");
		
	} else if (details.mode.value == ModeChoosePolicyRef.value) {
		//Save the old selectedID and then put in the new one
		savedID = lastDisplayBean.selectedID;
		lastDisplayBean.selectedID = details.parentID;
		
		//Set title
		setTabTitle(referenceExisting);
		
		currentMode = "Choose";
		
		//Reinstate the last selectedID
		lastDisplayBean.selectedID = savedID;
	
		addChoosePolicyRefHtml(details,detailsDivHtml);
		
		// Set the HTML for the details div
		detailsDiv.innerHTML = detailsDivHtml.toString();					
				
		//Dettach the widget if we'd already used it
		if (suggest) {
			suggest.destroy();
			//Have to call dijit.registry.remove directly or otherwise
			//we get an error when we create the widget again.
			dijit.registry.remove("policySearch");
		}
			
		//Attach the auto-suggest widjit
		var srcDiv= dojo.byId("policySearch");
		suggest = new com.ibm.sr.widgets.AutoSuggest();
		suggest.create({id: "policySearch"}, srcDiv); 
		suggest.contextRoot = contextRoot;
		suggest.type= "text";
		suggest.name = "query";
		suggest.size = 20;
		suggest.title = "";
		suggest.setType("PolicyExpression");
		suggest.onSuggestionSelected = searchPolicy;
		
		setFocus("policySearch");
		
		//Set the description for the currently selected policy
		if (details.policies.length > 0)
			showPolicyAssDesc("inputID_choosePolicy");
		
	} else if (details.mode.value == ModeChooseAssertion.value || details.mode.value == ModeChooseAssertionReplace.value || details.mode.value == ModeChooseFrameworkAssertion.value) {
		//Save the old selectedID and then put in the new one
		savedID = lastDisplayBean.selectedID;
		lastDisplayBean.selectedID = details.parentID;
		
		//Set title
		
		if (details.mode.value == ModeChooseAssertion.value)
			setTabTitle(textAddAssertion);
		else if (details.mode.value == ModeChooseAssertionReplace.value)
			setTabTitle(textSelectAssertion);
		else
			setTabTitle(textSelectFrameworkAssertion);
			
		currentMode = "Choose";

		//Reinstate the last selectedID
		lastDisplayBean.selectedID = savedID;

		if (details.mode.value == ModeChooseAssertionReplace.value)			
			addChooseAssertionHtml(details,detailsDivHtml, true);
		else
			addChooseAssertionHtml(details,detailsDivHtml, false);
			
		// Set the HTML for the details div
		detailsDiv.innerHTML = detailsDivHtml.toString();					

		setFocus("inputID_chooseAssertion");

		//Set the description for the currently selected assertion
		if (details.assertions.length > 0)
			showPolicyAssDesc("inputID_chooseAssertion");
		
	}  else if (details.mode.value == ModeChoosePolicyDomain.value) {
		//Save the old selectedID and then put in the new one
		savedID = lastDisplayBean.selectedID;
		lastDisplayBean.selectedID = details.parentID;
		
		//Set title
		setTabTitle(textSelectPolicyDomain);
		
		currentMode = "Choose";
		
		//Reinstate the last selectedID
		lastDisplayBean.selectedID = savedID;
	
		addChoosePolicyDomainHtml(details,detailsDivHtml);
		
		// Set the HTML for the details div
		detailsDiv.innerHTML = detailsDivHtml.toString();					

		setFocus("inputID_choosePolicyDomain");
					
		//Set the description for the currently selected policy
		if (details.domains.length > 0)
			showPolicyAssDesc("inputID_choosePolicyDomain");
		
	}
}

function addAttributesHtml(selectedID, details, detailsDivHtml) {

	var firstID = null;
	addAttributesDescriptionHtml(details, detailsDivHtml);
	addAttributesTitleHtml(details,detailsDivHtml);
	
	detailsDivHtml.append("<div class=\"expandablesection expanded\" id=\"child_propSectionDetails\">");
		
	if (details.attributes != null && details.attributes.length > 0) {
		//Create new attribute cache
		attributesCache = [];
	
		firstID = "inputID_" + details.attributes[0].ID;
	
		for (var i = 0; i < details.attributes.length; i++) {
			//Add to teh cache
			attributesCache[details.attributes[i].ID] = i;
			
			// Append a row for each navigatable row			
			if (selectedID == lastDisplayBean.structure[0].ID)
				addAttributeHtml(selectedID,details.attributes[i], detailsDivHtml, true);
			else
				addAttributeHtml(selectedID,details.attributes[i], detailsDivHtml, false);				
		}
	} else {
		detailsDivHtml.append("<br/>");
		detailsDivHtml.append(textNoAttributes);
		detailsDivHtml.append("<br/>");
	}
	
	// If we can add any attributes then display the Add Attribute link
	if (readOnly == false && details.potentialAttributes != null && details.potentialAttributes.length > 0) {
		detailsDivHtml.append("<br/><div id=\"addAttributeDivID\"><a tabindex=\"1\" class=\"AJAXControl\" href=\"javascript:showAddAttributePanel(");
		detailsDivHtml.append(selectedID);
		detailsDivHtml.append(")\">");
		detailsDivHtml.append(textAddAttribute);
		detailsDivHtml.append("</a></div>");
	}
	
	detailsDivHtml.append("</div>");
	
	return firstID;
}

/**
 * Add a row to the navDivHtml to show this structure row
 */
function addStructureHtml(selectedID, policyStructure, navDivHtml, last) {
	
	navDivHtml.append("<tr id=\"NavTRID_");
	navDivHtml.append(policyStructure.ID);	
	navDivHtml.append("\" class=\"");
	
	if (selectedID == policyStructure.ID)
		navDivHtml.append("selected\">");
	else
		navDivHtml.append("notselected\">");		

	navDivHtml.append("<td nowrap id=\"NavTDID_");
	navDivHtml.append(policyStructure.ID);	
	navDivHtml.append("\"");
		
	if (last == false)
		navDivHtml.append(" class=\"right\">");
	else
		navDivHtml.append(">");
		
	addStructureTDHtml(selectedID, policyStructure, navDivHtml);
	
	navDivHtml.append("</td>");
	
	navDivHtml.append("</tr>");		
}

function swapActiveTR(previousID, currentID) {

	var prevTrElem = document.getElementById("NavTRID_" + previousID);	
	var curTrElem = document.getElementById("NavTRID_" + currentID);	
	
	//swap the selected classes
	prevTrElem.className = "notselected";
	curTrElem.className = "selected";
		
	var prevTDElem = document.getElementById("NavOpsID_" + previousID);	
	var curTDElem = document.getElementById("NavOpsID_" + currentID);	
		
	//change the active/deactive links
	renderNavLink(curTDElem, currentID, currentID);
	
	//only render this one if it's a different line
	if (previousID != currentID)
		renderNavLink(prevTDElem, currentID, previousID );
}

function renderNavLink(elem, selectedID, itemID ) {
	var navDivHtml = new dojox.string.Builder();
	
	//Find the item in lastDisplayBean.structure
	var index = findIDInStructure(itemID);

	if (index != -1 ) {
		if (selectedID != itemID || thisOp != NavActionDisplay.value) {
			navDivHtml.append("<b><a tabindex=\"1\" class=\"AJAXControl\" href=\"javascript:doNavigatorOp(");
			navDivHtml.append(itemID);
			navDivHtml.append(",");
			navDivHtml.append(NavActionDisplay.value);
			navDivHtml.append(")\">");
			navDivHtml.append(lastDisplayBean.structure[index].localName);
			navDivHtml.append("</a></b>");
		} else {
			navDivHtml.append("<b><span>");
			navDivHtml.append(lastDisplayBean.structure[index].localName);
			navDivHtml.append("</span></b>");
		}
		
		elem.innerHTML = navDivHtml.toString();
		
		//Rerender the operations
		var navDivHtml = new dojox.string.Builder();
		var opElem = document.getElementById("ops_" + itemID);
		renderOps(selectedID, lastDisplayBean.structure[index],navDivHtml);
		
		opElem.innerHTML = navDivHtml.toString();		
	}
}

function addStructureTDHtml(selectedID, policyStructure, navDivHtml) {
  if (isRTL && !dojo.isIE)
  	navDivHtml.append("<span style=\"margin-right:");
  else
    navDivHtml.append("<span style=\"margin-left:");
	navDivHtml.append(policyStructure.level);
	navDivHtml.append("em\">");
	
	var elemName = textUnknownType;
	var elemImg = "theme/"+currentTheme+"/images/HomeHelp2.gif";

	//If this is the Document row then set the publishOK flag	
	if (policyStructure.attributes != null && policyStructure.attributes.length > 0 && policyStructure.attributes[0].ID == -11) {
		setPublishState(policyStructure.attributes[0].value);
	}
			
 	if (policyStructure.elementType.value == ElementTypePolicyDocument.value) { 	
 		if (publishOK) {
			elemName = textPolicyDocument;
			elemImg = "theme/"+currentTheme+"/images/types/policyDocument.gif";
		} else {
			elemName = textPolicyDocumentError;
			elemImg = "theme/"+currentTheme+"/images/policy/policyDocument_error.gif";
		}
	} else	if (policyStructure.elementType.value == ElementTypePolicyReference.value) {
		elemName = textPolicyReference;
		elemImg = "theme/"+currentTheme+"/images/policy/Policy_Reference.gif";
	} else	if (policyStructure.elementType.value == ElementTypePolicyClass.value) {
		elemName = textPolicyClass;
		elemImg = "theme/"+currentTheme+"/images/policy/policy.gif";
	} else	if (policyStructure.elementType.value == ElementTypeAssertion.value) {
		elemName = textAssertion;
		elemImg = "theme/"+currentTheme+"/images/policy/Policy_Assertion.gif";
	} else	if (policyStructure.elementType.value == ElementTypeAllOf.value) {
		elemName = policyStructure.localName;
		elemImg = "theme/"+currentTheme+"/images/policy/Policy_Allof.gif";		
	} else	if (policyStructure.elementType.value == ElementTypeOneOf.value) {
		elemName = policyStructure.localName;
		elemImg = "theme/"+currentTheme+"/images/policy/Policy_Oneof.gif";		
	} else	if (policyStructure.elementType.value == ElementTypeAssertionInvalid.value) {
		elemName = textAssertionInvalid;
		elemImg = "theme/"+currentTheme+"/images/policy/Policy_AssertionInvalid.gif";		
	} else	if (policyStructure.elementType.value == ElementTypeAssertionUnknown.value) {
		elemName = textAssertionUnknown;
		elemImg = "theme/"+currentTheme+"/images/policy/Policy_AssertionUnknown.gif";		
	} else	if (policyStructure.elementType.value == ElementTypePolicyInvalid.value) {
		elemName = textPolicyInvalid;
		elemImg = "theme/"+currentTheme+"/images/policy/Policy_Invalid.gif";		
	} else	if (policyStructure.elementType.value == ElementTypePolicyUnknown.value) {
		elemName = textPolicyUnknown;
		elemImg = "theme/"+currentTheme+"/images/policy/Policy_UnknownType.gif";		
	} else	if (policyStructure.elementType.value == ElementTypePolicyAttachment.value) {
		elemName = textPolicyAttachment;
		elemImg = "theme/"+currentTheme+"/images/types/policyAttachment.gif";		
	} else	if (policyStructure.elementType.value == ElementTypeAssertionSelector.value) {
		elemName = textAssertionSelector;
		elemImg = "theme/"+currentTheme+"/images/policy/Policy_Ass_Selector.gif";		
	} else	if (policyStructure.elementType.value == ElementTypeAssertionSelectorInvalid.value) {
		elemName = textAssertionSelectorInvalid;
		elemImg = "theme/"+currentTheme+"/images/policy/Policy_Ass_Sel_Invalid.gif";		
	} else	if (policyStructure.elementType.value == ElementTypeAssertionSelectorUnknown.value) {
		elemName = textAssertionSelectorUnknown;
		elemImg = "theme/"+currentTheme+"/images/policy/Policy_Ass_Sel_UnKnown.gif";		
	}
	
	if (policyStructure.elementType.value == ElementTypePolicyDocument.value)
		navDivHtml.append("<img id=\"documentImageID\" src=\"");
	else
		navDivHtml.append("<img src=\"");
		
	navDivHtml.append(elemImg);
	navDivHtml.append("\" alt=\"");
	navDivHtml.append(elemName);
	navDivHtml.append("\" title=\"");
	navDivHtml.append(elemName);
	navDivHtml.append("\"/> ");

	navDivHtml.append("<span id=\"NavOpsID_");
	navDivHtml.append(policyStructure.ID);
	navDivHtml.append("\">");
	
	if (selectedID != policyStructure.ID || thisOp != NavActionDisplay.value) {
		navDivHtml.append("<b><a tabindex=\"1\" class=\"AJAXControl\" href=\"javascript:doNavigatorOp(");
		navDivHtml.append(policyStructure.ID);
		navDivHtml.append(",");
		navDivHtml.append(NavActionDisplay.value);
		navDivHtml.append(")\">");
		navDivHtml.append(policyStructure.localName);
		navDivHtml.append("</a></b>");
	} else {
		navDivHtml.append("<b><span>");
		navDivHtml.append(policyStructure.localName);
		navDivHtml.append("</span></b>");
	}
	
	navDivHtml.append("</span>");
	
	
	// calculate width of a single attribute value
	var attributeWidth = calculateAttributeWidth(policyStructure);
  // Need to use text direction indicators and markers to ensure the
  // text is correctly displayed in LTR and RTL modes.
  var dirLeft="\u202a";
  var dirRight="\u202c";
  var dirMarker="\u200e";
  if (isRTL)
  {
    dirLeft="\u202b";
    dirMarker="\u200f";
  }
	
	// Add in the attributes for display in the navigator
	for (var i = 0; i < policyStructure.attributes.length; i++) {
		strucForAttr[policyStructure.attributes[i].ID] = policyStructure;
		navDivHtml.append(" "+dirLeft+"<span class=\"ns\" title=\"");
		navDivHtml.append(policyStructure.attributes[i].localDescription);
		navDivHtml.append("\">");
		navDivHtml.append(policyStructure.attributes[i].localName);
		navDivHtml.append(" = </span> ");
		navDivHtml.append("\""+dirMarker);
		
		//If the type is a URI then this must be a policy reference so add an href
		if (policyStructure.attributes[i].type == "URI") {
			navDivHtml.append("<a href=\"javascript:showPolicyExpression(");
			navDivHtml.append(policyStructure.ID);
			navDivHtml.append(",'");
			navDivHtml.append(policyStructure.attributes[i].value);
			navDivHtml.append("')\">");		
		}
		// append a shortened attribute value
		appendShortenedText(navDivHtml, policyStructure.attributes[i].value, attributeWidth, policyStructure.attributes[i].ID);
		//navDivHtml.append(filterHTML(policyStructure.attributes[i].value));
	
		if (policyStructure.attributes[i].type == "URI")
			navDivHtml.append("</a>");		
			
		navDivHtml.append("\""+dirMarker+dirRight);
	}
				
	navDivHtml.append("<span id=\"ops_");
	navDivHtml.append(policyStructure.ID);
	navDivHtml.append("\">");
	
	renderOps(selectedID,policyStructure, navDivHtml);
				
	navDivHtml.append("</span></span>");
}

function renderOps(selectedID, policyStructure, navDivHtml) {

	if (readOnly == false && policyStructure.ops != null && policyStructure.ops.length > 0) {
		for (var i = 0; i < policyStructure.ops.length; i++) {
			var id = policyStructure.ID + "_" + policyStructure.ops[i].operation.value;
			
			//update the opCache
			opCache[id] = policyStructure.ops[i];
			
      var divider=" \u202a|\u202c ";
      if (isRTL)
        divider=" \u202b|\u202c ";
			navDivHtml.append(divider);
			navDivHtml.append("<span id=\"NavOpsID_");
			navDivHtml.append(id);
			navDivHtml.append("\">");
			
			if (selectedID == policyStructure.ID && thisOp == policyStructure.ops[i].operation.value) {
				navDivHtml.append("<b>");
				navDivHtml.append(policyStructure.ops[i].localName);
				navDivHtml.append("</b>");
			} else {
				navDivHtml.append("<a tabindex=\"1\" class=\"AJAXControl\" href=\"javascript:doNavigatorOp(");
				navDivHtml.append(policyStructure.ID);
				navDivHtml.append(",");
				navDivHtml.append(policyStructure.ops[i].operation.value);
				navDivHtml.append(")\">");
				navDivHtml.append(policyStructure.ops[i].localName);
				navDivHtml.append("</a>");
			}
			
			navDivHtml.append("</span>");
		}
	}	
}

/**
 * Add the title for this attributes section
 */
function addAttributesTitleHtml(details, detailsTitleDivHtml) {

	if (details.title != null) {
		detailsTitleDivHtml.append("<table class=\"sectiontitletable\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");
		detailsTitleDivHtml.append("<tr><td width=\"1%\">");
		detailsTitleDivHtml.append("<a id=\"toggleSectionID\" href=\"javascript:toggleSection('propSectionDetails')\" tabindex=\"1\">");
		detailsTitleDivHtml.append("<img id=\"img_propSectionDetails\" src=\"theme/"+currentTheme+"/images/expanded.gif\" alt=\"");
		detailsTitleDivHtml.append(TRANS_GENERAL_MSG_COLLAPSE);
		detailsTitleDivHtml.append("\" title=\"");
		detailsTitleDivHtml.append(TRANS_GENERAL_MSG_COLLAPSE);
		detailsTitleDivHtml.append("\"/>");
		detailsTitleDivHtml.append("</a></td><td width=\"99%\"><div id=\"navNodeTitle\" class=\"sectiontitle\"  alt=\"");
		detailsTitleDivHtml.append(details.title);
		detailsTitleDivHtml.append("\" title=\"");
		detailsTitleDivHtml.append(details.title);
		detailsTitleDivHtml.append("\">");
		
		detailsTitleDivHtml.append(details.title);
		
		detailsTitleDivHtml.append("</div></td></tr></table>");
	}
}

/**
 * Add the title for this attributes section
 */
function addAttributesDescriptionHtml(details, detailsTitleDivHtml) {

	if (details.description != null) {
		detailsTitleDivHtml.append("<div id=\"navNodeDescription\" class=\"instruction-text\">");			
		detailsTitleDivHtml.append(details.description);		
		detailsTitleDivHtml.append("</div><br/>");
	}
}

function addAttributeHtml(parentID, policyAttribute, detailsDivHtml, isPolicyDoc) {

	if (policyAttribute.readOnly == true || readOnly == true)
		detailsDivHtml.append("<div class=\"readonlyproperty");
	else
		detailsDivHtml.append("<div class=\"property");
		
	if (policyAttribute.mandatory)
		detailsDivHtml.append(" mandatory\">");
	else
		detailsDivHtml.append(" notmandatory\">");
	
	//Label and title
	detailsDivHtml.append("<label for=\"inputID_");
	detailsDivHtml.append(policyAttribute.ID);
	detailsDivHtml.append("\" title=\"");
	detailsDivHtml.append(filterHTML(policyAttribute.localDescription));
	detailsDivHtml.append("\">");
	detailsDivHtml.append(policyAttribute.localName);
	detailsDivHtml.append("</label>");
	
	if (policyAttribute.removable == true && isPolicyDoc == false && readOnly == false) {
		detailsDivHtml.append(" <a tabindex=\"1\" href=\"javascript:deleteAttribute(");
		detailsDivHtml.append(parentID);
		detailsDivHtml.append(",");
		detailsDivHtml.append(policyAttribute.ID); 
		detailsDivHtml.append(")\"><img class=\"AJAXControl\" src=\"theme/"+currentTheme+"/images/closebox.gif\" alt=\"");
		detailsDivHtml.append(resolveMessageInserts(altTextDelete, policyAttribute.localName));
		detailsDivHtml.append("\" title=\"");
		detailsDivHtml.append(resolveMessageInserts(altTextDelete, policyAttribute.localName));
		detailsDivHtml.append("\"/></a>");
	}
	
	detailsDivHtml.append("<br/>");
	
	if (policyAttribute.choices == null) {		

		// don't show the text box if this is an enumerated value.		
		if ((policyAttribute.enumeratedValue==undefined) || (policyAttribute.enumeratedValue==false)) {
		
			//Is this a text or a textarea
			if (policyAttribute.type == TypeTextArea) {
				//Textarea
				detailsDivHtml.append("<textarea name=\"inputID_");
				detailsDivHtml.append(policyAttribute.ID);
				detailsDivHtml.append("\" id=\"inputID_");
				detailsDivHtml.append(policyAttribute.ID);
				detailsDivHtml.append("\"");
		
				// Mark the attribute readonly if the attribute or global flags are set
				if (policyAttribute.readOnly == true || readOnly == true)
					detailsDivHtml.append(" readonly=\"readonly\"");
				
				detailsDivHtml.append(" tabindex=\"1\">");
				detailsDivHtml.append(filterHTML(policyAttribute.value));			
				detailsDivHtml.append("</textarea>");
			} else {
				//Default to text
				detailsDivHtml.append("<input type=\"text\"");
				
				detailsDivHtml.append(" name=\"inputID_");
				detailsDivHtml.append(policyAttribute.ID);
				detailsDivHtml.append("\" id=\"inputID_");
				detailsDivHtml.append(policyAttribute.ID);
				detailsDivHtml.append("\" value=\"");
				detailsDivHtml.append(filterHTML(policyAttribute.value));
				detailsDivHtml.append("\"");
	
				// Mark the attribute readonly if the attribute or global flags are set
				if (policyAttribute.readOnly == true || readOnly == true)
					detailsDivHtml.append(" readonly=\"readonly\"");
				
				detailsDivHtml.append(" tabindex=\"1\"");
				
				//Code for the name attribute of the policy document
				if (policyAttribute.ID == -11) {
					//Add the code to Enable/disable the publish button based on whether the name of the doc is 
					//non-null and NCName compliant		
					detailsDivHtml.append(" onKeyUp=\"javascript:delaySetPublishState()\" onBlur=\"javascript:delaySetPublishState()\"/>");
				} else {
					//Add the code to update the fields in the navigator section
					detailsDivHtml.append(" onKeyUp=\"javascript:delayUpdateNavParam(");
					detailsDivHtml.append(policyAttribute.ID);
					detailsDivHtml.append(")\"/>");
				}
			}		
		} else {
			detailsDivHtml.append("<br/>");
		}
	} else {
		//Selectbox
		detailsDivHtml.append("<select name=\"inputID_");
		detailsDivHtml.append(policyAttribute.ID);
		detailsDivHtml.append("\" id=\"inputID_");
		detailsDivHtml.append(policyAttribute.ID);
		detailsDivHtml.append("\" tabindex=\"1\"");
		detailsDivHtml.append(" onChange=\"javascript:delayUpdateNavParam(");
		detailsDivHtml.append(policyAttribute.ID);
		detailsDivHtml.append(")\"/>");									

		if (readOnly == true)
			detailsDivHtml.append(" readonly=\"readonly\"/>");
		else
			detailsDivHtml.append("/>");
			
		addSelectOptionsTextOnly(policyAttribute.choices, detailsDivHtml);
		
		detailsDivHtml.append("</select>");
	}
	
	detailsDivHtml.append("</div>");		
}

function addSelectOptionsTextOnly(optionsArray, detailsDivHtml) {
	for (var i = 0; i < optionsArray.length; i++) {
		detailsDivHtml.append("<option value=\"");
		detailsDivHtml.append(optionsArray[i].ID);

		// Set the current selection
		if (optionsArray[i].selected==true)
			detailsDivHtml.append("\" selected=\"selected\"");
		else
			detailsDivHtml.append("\"");

		detailsDivHtml.append(" desc=\"");
		detailsDivHtml.append(optionsArray[i].localDescription);
		detailsDivHtml.append("\">");
			
		detailsDivHtml.append(optionsArray[i].localName);
		detailsDivHtml.append("</option>");			
	}

}

function addSelectOptionsIDNameDesc(optionsArray, detailsDivHtml) {
	
	// Add the options
	for (var i = 0; i < optionsArray.length; i++) {
	
		//Set a global var so we can lookup the descriptions when the item is selected
		globalOptions[optionsArray[i].ID] = optionsArray[i].localDescription;
	
		detailsDivHtml.append("<option value=\"");
		detailsDivHtml.append(optionsArray[i].ID);		
    detailsDivHtml.append("\" title=\"");
    detailsDivHtml.append(optionsArray[i].localName);
		
		//Select the first one in the list by default
		if (i == 0)
			detailsDivHtml.append("\" selected=\"selected\">");
		else
			detailsDivHtml.append("\">");
			
		detailsDivHtml.append(optionsArray[i].localName);
		detailsDivHtml.append("</option>");			
	}

}

function showPolicyAssDesc(elementID) {		

	if (globalOptions != null) {

		var element = document.getElementById(elementID);	
		var descBox = document.getElementById("elementDescription");		
		descBox.value = globalOptions[element.value];	
	}
}

function delayShowPolicyAssDesc(elementID) {
	globalID = elementID;
	window.setTimeout("showPolicyAssDesc(globalID)",0);
}

function setTabTitle(title){
	var tabDiv = document.getElementById("tabName");	
	tabDiv.innerHTML = title;
}

/**
 * Show the choose policy details stuff
 */
function addChoosePolicyHtml(details, detailsDivHtml) {
		
	detailsDivHtml.append("<h4>");	
	detailsDivHtml.append(selectPolicyDescText);
	detailsDivHtml.append("</h4>")
	
	// New Policy
	detailsDivHtml.append("<div class=\"property notmandatory\"><label for=\"inputID_choosePolicy\" title=\"");	
	detailsDivHtml.append(textSelectPolicyFH);
	detailsDivHtml.append("\">");
	detailsDivHtml.append(policyOptions);
	
	//Selectbox
	detailsDivHtml.append("</label><br/><select name=\" name=\"inputID_choosePolicy\" id=\"inputID_choosePolicy\" tabindex=\"1\" onChange=\"javascript:showPolicyAssDesc('inputID_choosePolicy')\" onKeypress=\"javascript:delayShowPolicyAssDesc('inputID_choosePolicy')\"/>");
	
	globalOptions = new Array();	
	addSelectOptionsIDNameDesc(details.policies, detailsDivHtml);	
	
	detailsDivHtml.append("</select>");
	detailsDivHtml.append("</div>");
	
	detailsDivHtml.append("<div class=\"readonlyproperty notmandatory\">");
	detailsDivHtml.append("<label for=\"description\" title=\"");
	detailsDivHtml.append(textDescriptionFH);
	detailsDivHtml.append("\">");
	detailsDivHtml.append(textDescription);
	detailsDivHtml.append("</label><br/>");	
	detailsDivHtml.append("<textarea name=\"description\" tabindex=\"1\" id=\"elementDescription\" readonly=\"readonly\" rows=\"5\" cols=\"20\">");
	detailsDivHtml.append("</textarea>"); 	
	detailsDivHtml.append("</div><br/>");

	//Add button
	addButtonHtml(detailsDivHtml,"addPolicy",textSelect,"addPolicy( "+ details.parentID + ")",true);

	//Cancel button
	addButtonHtml(detailsDivHtml,"cancelPolicy",textCancel,"cancelChoose()",false);		
}

/**
 * Show the choose policy domain details stuff
 */
function addChoosePolicyDomainHtml(details, detailsDivHtml) {
		
	detailsDivHtml.append("<h4>");	
	detailsDivHtml.append(selectPolicyDomainDescText);
	detailsDivHtml.append("</h4>")
	
	// New Policy Domain
	detailsDivHtml.append("<div class=\"property notmandatory\"><label for=\"inputID_choosePolicyDomain\" title=\"");	
	detailsDivHtml.append(textSelectPolicyDomainFH);
	detailsDivHtml.append("\">");			
	detailsDivHtml.append(textPolicyDomains);
	
	//Selectbox
	detailsDivHtml.append("</label><br/><select name=\" name=\"inputID_choosePolicyDomain\" id=\"inputID_choosePolicyDomain\" tabindex=\"1\" onChange=\"javascript:showPolicyAssDesc('inputID_choosePolicyDomain')\" onKeypress=\"javascript:delayShowPolicyAssDesc('inputID_choosePolicyDomain')\"/>");
	
	globalOptions = new Array();	
	addSelectOptionsIDNameDesc(details.domains, detailsDivHtml);	
	
	detailsDivHtml.append("</select>");
	detailsDivHtml.append("</div>");
	
	detailsDivHtml.append("<div class=\"readonlyproperty notmandatory\">");
	detailsDivHtml.append("<label for=\"description\" title=\"");
	detailsDivHtml.append(textDescriptionFH);
	detailsDivHtml.append("\">");
	detailsDivHtml.append(textDescription);
	detailsDivHtml.append("</label><br/>");	
	detailsDivHtml.append("<textarea name=\"description\" tabindex=\"1\" id=\"elementDescription\" readonly=\"readonly\" rows=\"5\" cols=\"20\">");
	detailsDivHtml.append("</textarea>"); 	
	detailsDivHtml.append("</div><br/>");

	//Add button
	addButtonHtml(detailsDivHtml,"addDomain",textApply,"addPolicyDomain( "+ details.parentID + ")",true);

	//Cancel button
	addButtonHtml(detailsDivHtml,"cancelDomain",textCancel,"cancelChoose()",false);		
}

/**
 * Show the choose policy details stuff
 */
function addChoosePolicyRefHtml(details, detailsDivHtml) {
		
	detailsDivHtml.append("<h4>");	
	detailsDivHtml.append(searchPolicyRefDescText);
	detailsDivHtml.append("</h4>")
				
	//Reference an existing
	detailsDivHtml.append("<div class=\"property notmandatory\"><label for=\"policySearch\" title=\"");
	detailsDivHtml.append(textSearchFH);
	detailsDivHtml.append("\">");				
	detailsDivHtml.append(textSearch);
	detailsDivHtml.append("</label><br/>");
	detailsDivHtml.append("<div id=\"policySearch\"></div>");
	detailsDivHtml.append("</div></br>");

	//Search button
	addButtonHtml(detailsDivHtml,"searchPolicy",textSearch,"searchPolicy()",true);
	
	//Cancel button
	addButtonHtml(detailsDivHtml,"cancelPolicy",textCancel,"cancelChoose()",false);		
}

function searchPolicy(suggestion) {

	var newEdit = "";
	
	if (lastDisplayBean.bsrUri != null && lastDisplayBean.bsrUri != "")
		newEdit = "documentEdit";
	else
		newEdit = "documentNew";
		
	if (suggestion) {
		if (suggestion.count == 1) {
			if (inProgress == false) {
				lastAddedAttributeID = null;
				lastAddedID = null;
				thisOp = NavActionDisplay.value;
				startProgressFunction(DETAILS_SHOW_PROGRESS, processingMessage, displayEmptyDetailsPanelProgress);
				addCallErrBack(policyService.policyAction(-1,RefOrClassSingleNameMatchExternal,suggestion.name),displayPolicyAuthBean,errorBack);
			}
		} else
			window.location = contextRoot + "/PolicySelection.do?queryString=" + escape(suggestion.name) + "&authoringType=" + newEdit ;
	} else {
		var searchInput = document.getElementById("policySearch");	
		var searchString = searchInput.value;	
		window.location = contextRoot + "/PolicySelection.do?queryString=" + escape(searchString) + "&authoringType=" + newEdit
	}
}
/**
 * Show the choose policy details stuff
 */
function addChooseAssertionHtml(details, detailsDivHtml, isSelect) {
		
	// New Policy	
	detailsDivHtml.append("<div class=\"property notmandatory\"><label for=\"inputID_chooseAssertion\" title=\"");	
	detailsDivHtml.append(textSelectAssertionFH);
	detailsDivHtml.append("\">");	
	detailsDivHtml.append(assertionOptions);
	
	//Selectbox
	detailsDivHtml.append("</label><br/><select name=\" name=\"inputID_chooseAssertion\" id=\"inputID_chooseAssertion\" tabindex=\"1\" onChange=\"javascript:showPolicyAssDesc('inputID_chooseAssertion')\" onKeypress=\"javascript:delayShowPolicyAssDesc('inputID_chooseAssertion')\"/>");
	
	globalOptions = new Array();	
	addSelectOptionsIDNameDesc(details.assertions, detailsDivHtml);	
	
	detailsDivHtml.append("</select>");
	detailsDivHtml.append("</div>");
	
	detailsDivHtml.append("<div class=\"readonlyproperty notmandatory\">");
	detailsDivHtml.append("<label for=\"description\" title=\"");
	detailsDivHtml.append(textDescriptionFH);
	detailsDivHtml.append("\">");
	detailsDivHtml.append(textDescription);
	detailsDivHtml.append("</label><br/>");
	detailsDivHtml.append("<textarea name=\"description\" tabindex=\"1\" id=\"elementDescription\" readonly=\"readonly\" rows=\"5\" cols=\"20\">");
	detailsDivHtml.append("</textarea>"); 	
	
	detailsDivHtml.append("</div><br/>");

	if (isSelect) {
		//Select button 
		addButtonHtml(detailsDivHtml,"addAssertion",textSelect,"addAssertion(" + details.parentID + ")",true);
	} else {	
		//Add button 
		addButtonHtml(detailsDivHtml,"addAssertion",textAdd,"addAssertion(" + details.parentID + ")",true);
	}
		
	//Cancel button
	addButtonHtml(detailsDivHtml,"cancelAssertion",textCancel,"cancelChoose()",false);	
}

function cancelChoose() {

	//Go back to the right place.
	lastSelectedID = cancelToID;
	
	//We always go back to the last displayed thing
	thisOp = NavActionDisplay.value;
	
	//Copy the old bean and then blat the structure so we do the swap thang
	var oldStruc = lastDisplayBean.structure;
	lastDisplayBean.structure = null;
	bean = new cloneObject(lastDisplayBean);			
	lastDisplayBean.structure = oldStruc;
	
	displayPolicyAuthBean(bean);
}

function doNavigatorOp(nodeID, opID) {

	if (inProgress == false) {
		lastAddedID = null;
		lastAddedAttributeID = null;
		
		//Set the last op so we highlight the correct action in the navigator section.
		//Don't do it for delete as it does not have a second click.
		if (opID != NavActionDelete.value)
			thisOp = opID;	
		else
			thisOp = NavActionDisplay.value;
			
		//Check if it's in the cache
		var details = null;
		if (actionToMode[opID] != null) {	
			//alert("look for key: " + nodeID + "_" + actionToMode[opID]);
			details = detailsCache[nodeID + "_" + actionToMode[opID]];
		}
	
		if (details != null) {
			var bean = buildPolicyDetailsBean(lastDisplayBean.selectedID);
			
			//Still need to send the results to the server if any details have changed
			if (bean != null) {
				addCallErrBack(policyService.navigatorAction(NavActionPublish,nodeID,bean),doneUpdate,errorBack);			
			}
	
			//Copy the old bean and then blat the structure so we do the swap thang
			var oldStruc = lastDisplayBean.structure;
			lastDisplayBean.structure = null;
			bean = new cloneObject(lastDisplayBean);
			bean.selectedID = nodeID;
			bean.mode = actionToMode[opID];
			bean.details = details;
			lastDisplayBean.structure = oldStruc;		
			displayPolicyAuthBean(bean);		
		} else {
			// retrieving details from the server - start progress
			setupProgressIndicator(nodeID, opID);
			
			addCallErrBack(policyService.navigatorAction(new UIEnum(opID),nodeID,buildPolicyDetailsBean(lastDisplayBean.selectedID)),displayPolicyAuthBean,errorBack);	
		}
	}
}

function doPublish() {
	
	if (inProgress == false) {	
		lastAddedID = null;
		lastAddedAttributeID = null;
		var nodeID = lastDisplayBean.selectedID;	
		thisOp = NavActionDisplay.value;
		startProgressFunction(NAVIGATOR_SHOW_PROGRESS, publishingMessage, displayEmptyNavigatorProgress);
		addCallErrBack(policyService.navigatorAction(NavActionPublish,nodeID,buildPolicyDetailsBean(nodeID)),donePublish,errorBack);	
	}
}

function donePublish(authBean, exception) {

	// stop the progress - browsers stop the spinny animation so it looks bad to leave it there
	stopProgress();

	var error = endTransaction(authBean,exception);
	//Forward to the struts action if all ok.
	if (error == false)
		window.location = contextRoot + "/PublishPolicyDocument.do";
}

function doneUpdate(authBean, exception) {
									
	var error = endTransaction(authBean,exception);
}

function deleteAttribute(parentID, ID) {
	
	if (inProgress == false) {
		lastAddedID = null;
		lastAddedAttributeID = null;
		thisOp = NavActionDisplay.value;
		startProgressFunction(DETAILS_SHOW_PROGRESS, processingMessage, displayEmptyDetailsPanelProgress);		
		addCallErrBack(policyService.deleteAttributeAction(parentID, ID, buildPolicyDetailsBean(lastDisplayBean.selectedID)),displayPolicyAuthBean,errorBack);	
	}
}

function showAddAttributePanel(parentID) {

	if (inProgress == false) {
		var addAttributeDiv = document.getElementById("addAttributeDivID");	
		var addAttributeHtml = new dojox.string.Builder();
		var details = lastDisplayBean.details;
	
		addAttributeHtml.append("<fieldset title=\"\">");
		addAttributeHtml.append("<legend class=\"messageTitle\">");
		addAttributeHtml.append(textAddAttribute);
		addAttributeHtml.append("</legend>");
		
		addAttributeHtml.append("<div class=\"property notmandatory\"><label for=\"inputID_chooseAttribute\" title=\"");	
		addAttributeHtml.append(textSelectAttributeFH);
		addAttributeHtml.append("\">");				
		addAttributeHtml.append(textOptionalAttributes);
		
		//Selectbox
		addAttributeHtml.append("</label><br/><select name=\" name=\"inputID_chooseAttribute\" id=\"inputID_chooseAttribute\" tabindex=\"1\" onChange=\"javascript:showPolicyAssDesc('inputID_chooseAttribute')\" onKeypress=\"javascript:delayShowPolicyAssDesc('inputID_chooseAttribute')\"/>");

		globalOptions = new Array();	
		addSelectOptionsIDNameDesc(details.potentialAttributes, addAttributeHtml);	
		
		addAttributeHtml.append("</select>");
		addAttributeHtml.append("</div>");
	
		addAttributeHtml.append("<div class=\"readonlyproperty notmandatory\">");
		addAttributeHtml.append("<label for=\"description\" title=\"");
		addAttributeHtml.append(textDescriptionFH);
		addAttributeHtml.append("\">");
		addAttributeHtml.append(textDescription);
		addAttributeHtml.append("</label><br/>");
		addAttributeHtml.append("<textarea name=\"description\" tabindex=\"1\" id=\"elementDescription\" readonly=\"readonly\" rows=\"5\" cols=\"20\">");
		addAttributeHtml.append("</textarea>"); 	
		addAttributeHtml.append("</div>");
	
		//Add button
		addButtonHtml(addAttributeHtml,"addAttribute",textAdd,"addAttribute(" + parentID + ")",true);	
	
		//Cancel button
		addButtonHtml(addAttributeHtml,"cancelAttribute",textCancel,"cancelChooseAttribute()",false);	
		
		addAttributeHtml.append("</fieldset>");
	
		addAttributeDiv.innerHTML = addAttributeHtml.toString();

		setFocus("inputID_chooseAttribute");
		
		//Set the description for the currently selected policy
		if (details.potentialAttributes.length > 0)
			showPolicyAssDesc("inputID_chooseAttribute");
		
	}
}

function cancelChooseAttribute() {

	var addAttributeDiv = document.getElementById("addAttributeDivID");	
	var addAttributeHtml = new dojox.string.Builder();

	addAttributeHtml.append("<a tabindex=\"1\" href=\"javascript:showAddAttributePanel()\">");
	addAttributeHtml.append(textAddAttribute);
	addAttributeHtml.append("</a>");

	addAttributeDiv.innerHTML = addAttributeHtml.toString();
}

function addAttribute(parentID) {
	
	if (inProgress == false) {
		//Find the ID of the attribute
		var attributeSelect = document.getElementById("inputID_chooseAttribute");	
		var attributeID = parseInt(attributeSelect.value);	
		lastAddedID = null;
		lastAddedAttributeID = attributeID;
		thisOp = NavActionDisplay.value;
		startProgressFunction(DETAILS_SHOW_PROGRESS, processingMessage, displayEmptyDetailsPanelProgress);		
		addCallErrBack(policyService.addAttributeAction(parentID, attributeID,buildPolicyDetailsBean(lastDisplayBean.selectedID)),displayPolicyAuthBean,errorBack);		
	}
}

function addAssertion(parentID) {
	
	if (inProgress == false) {
		//Find the ID of the assertion	
		var assertionSelect = document.getElementById("inputID_chooseAssertion");	
		var assertionID = parseInt(assertionSelect.value);	
		lastAddedID = assertionID;
		lastAddedAttributeID = null;
		thisOp = NavActionDisplay.value;
		startProgressFunction(DETAILS_SHOW_PROGRESS, processingMessage, displayEmptyDetailsPanelProgress);
		addCallErrBack(policyService.assertionAction(parentID, assertionID),displayPolicyAuthBean,errorBack);	
	}

}

function addPolicy(parentID) {
	
	if (inProgress == false) {
		//Find the ID of the policy
		var policySelect = document.getElementById("inputID_choosePolicy");	
		var policyID = parseInt(policySelect.value);	
		lastAddedID = policyID;
		lastAddedAttributeID = null;
		thisOp = NavActionDisplay.value;
		addCallErrBack(policyService.policyAction(parentID, RefOrClassClass, policyID + ''),displayPolicyAuthBean,errorBack);	
	}

}

function addPolicyDomain(parentID) {
	
	if (inProgress == false) {
		//Find the ID of the policy
		var policyDomainSelect = document.getElementById("inputID_choosePolicyDomain");	
		var policyDomainID = parseInt(policyDomainSelect.value);	
		lastAddedID = policyDomainID;
		thisOp = NavActionDisplay.value;
		addCallErrBack(policyService.policyDomainAction(parentID, policyDomainID),displayPolicyAuthBean,errorBack);	
	}

}

function buildPolicyStructureBeanForEllipsis(structure) {
	var struc = { "ID": structure.ID, "level": structure.level, "localName": structure.localName, "ops": structure.ops, "attributes": [] };

	for (var i = 0; i < structure.attributes.length; i++) {
	
		var attr = structure.attributes[i];
		var elem=document.getElementById("inputID_" + attr.ID);				
		
		if((elem!=null) && (elem!=undefined)) {
			struc.attributes.push({ "value": elem.value, "ID": attr.ID, "localName":  structure.attributes[i].localName});		
		}
	}
	
	return struc;
}

function buildPolicyDetailsBean(subjectID) {

	//If in Choose mode then there are no details to send to the server
	if (currentMode == "Choose")
		return null;
			
	var details = { "parentID": subjectID, "attributes": [], "myfunc": function() { blah } };
	
	for (var i = 0; i < lastDisplayBean.details.attributes.length; i++) {
	
		var attr = lastDisplayBean.details.attributes[i];
	
		//Only process the ones that have changed.
		//WSRR-6211, can't rely on the state in attrsChanged. Check them all
		var elem=document.getElementById("inputID_" + attr.ID);		

		if (elem.value != attr.value) {
		
			details.attributes.push({ "value": elem.value, "ID": attr.ID });		
		
			//Also set our last display bean so that cancel goes back to the new values
			lastDisplayBean.details.attributes[i].value = elem.value;		
			
			//Update the cache
			if (detailsCache[subjectID + "_0"] != null)
				detailsCache[subjectID + "_0"].attributes[i].value = elem.value;
		}
	}
	
	//Return null if no attributes had changed
	if (details.attributes.length == 0)
		return null;
	else {
		return details;
	}
}

function returnFromCancel() {
	// stop the progress - browsers stop the spinny animation so it looks bad to leave it there
	stopProgress();
	
	if (lastDisplayBean != null && lastDisplayBean.logicalBsrURI != null && lastDisplayBean.logicalBsrURI != "") {
		//Go back to the policy tab of this Policy Expression
		window.location = contextRoot + "/ViewDetail.do?tabName=policy&uri=" + lastDisplayBean.logicalBsrURI;
	} else if (lastDisplayBean != null && lastDisplayBean.bsrURI != null && lastDisplayBean.bsrURI != "") {
		//Go back to the policy tab of this Policy Doc
		window.location = contextRoot + "/ViewDetail.do?tabName=policy&uri=" + lastDisplayBean.bsrURI;		
	} else  {
		//Go back to the previous breadcrumb entry if there is one, or the home page if not
		var i = 0;
		var lastLink = null;
		var thisLink = ".dummy.";
		
		while(thisLink != null) {
			if (thisLink != ".dummy.")
				lastLink = thisLink;
				
			var id = "breadcrumb_" + i;
			var thisLink = document.getElementById(id);	
			i++;
		}
		
		if (lastLink != null) {
			window.location = lastLink.href;
		} else {
			window.location = contextRoot + "/HomePage.do";
		}
	}
}

function doMainCancel() {

	//Do not check inProgress here, as maybe something went wrong and the user just wants to quit
	try {
		startProgressFunction(NAVIGATOR_SHOW_PROGRESS, cancellingMessage, displayEmptyNavigatorProgress);
	} catch (e) {
		//Do nothing. It's only a progress message
	}
	
	try {
		addCallErrBack(policyService.navigatorAction(NavActionCancel,-1,null),returnFromCancel,returnFromCancel);	
	} catch (e) {
		//Must do this to make sure we can always quit this page no matter what has gone wrong.
		//Don't care if we get an exception.
		returnFromCancel();
	}
		
}

function doMainButtonsHtml() {

	if (readOnly == false) {
		var buttonsDiv = document.getElementById("mainButtons");	
		var buttonsHtml = new dojox.string.Builder();
	
		buttonsHtml.append("<table class=\"buttontable\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\"buttontable\">");
		buttonsHtml.append("<tr><td><input type=\"button\" onClick=\"javascript:doPublish()\" name=\"publish\" value='");
		buttonsHtml.append(textPublish);
		buttonsHtml.append("' class=\"standardbutton AJAXControl\" disabled id=\"publishButton\" tabindex=\"1\"/>");
	    buttonsHtml.append("<input type=\"button\" onClick=\"javascript:doMainCancel()\" name=\"cancel\" value='");
	    buttonsHtml.append(textCancel);
	    buttonsHtml.append("' class=\"standardbutton\" id=\"mainCancelButton\" tabindex=\"1\"/>");
	    buttonsHtml.append("</td></tr></table>");
	    
	    buttonsDiv.innerHTML = buttonsHtml.toString();
   }
}

function addButtonHtml(detailsDivHtml,name,translation, functionName, isAJAX) {

	detailsDivHtml.append("<input tabindex=\"1\" class=\"standardbutton");
	
	if (isAJAX)
		detailsDivHtml.append(" AJAXControl");	
	
	detailsDivHtml.append("\" type=\"button\" name=\"");
	detailsDivHtml.append(name);
	detailsDivHtml.append("\" value=\"");
	detailsDivHtml.append(translation);
	detailsDivHtml.append("\" id=\"");
	detailsDivHtml.append(name);	
	detailsDivHtml.append("ID\" onclick=\"javascript:");
	detailsDivHtml.append(functionName);
	detailsDivHtml.append("\"");
	
	if (readOnly == true)
		detailsDivHtml.append(" disabled/>");
	else
		detailsDivHtml.append("/>");
}

function indexOfSelectedID(selectedID) {

	for (var i = 0; i < lastDisplayBean.structure.length; i++) {
		var attr = lastDisplayBean.structure[i];
		
		if (attr.ID == selectedID)
			return i;
	}
	
	return 0;
}

function setPublishState(docName) {

	var name = null;
	var nameElem = document.getElementById("inputID_-11");	

	if (docName == null) {
		//Find the value of name		
		name = nameElem.value;		
	} else	
		name = docName;
		
	if (nameElem != null)		
		dijit.hideTooltip(nameElem);
				
	var pb = document.getElementById("publishButton");			
	var pImg = document.getElementById("documentImageID");			 	
	
	//check if it's non empty and valid
	if (name != "" /*&& isValidNCName(name)*/) {
		pb.disabled = false;
		if (pImg != null) {
			pImg.src = "theme/"+currentTheme+"/images/types/policyDocument.gif"; 
			pImg.alt = textPolicyDocument;
		}
		publishOK = true;
	} else {
		pb.disabled = true;
		if (pImg != null) {	
			pImg.src = "theme/"+currentTheme+"/images/policy/policyDocument_error.gif"; 
			pImg.alt = textPolicyDocumentError;
		}
		publishOK = false;
		if (nameElem != null) {				
			dijit.showTooltip(invalidDocNameText,nameElem);
		}
	}	
	
	//Also update the value of the Name field shown on the policy document navigator node
	updateNavParam(-11);
}

function updateNavParam(param_ID) {
		
	var elem=document.getElementById("inputID_" + param_ID);
	var navElem = document.getElementById("navAttrParent_" + param_ID);	
		
	if (elem != null) {		

		//Update in the navigator if need be
		if (navElem != null) {
			var navDivHtml = new dojox.string.Builder();

			var bean = buildPolicyStructureBeanForEllipsis(strucForAttr[param_ID]);
			ellipsiseStructureBean(bean);
		}
	}
}

function updateNavParams() {
	
	//Find all inputs named inputID_* and copy their values to the samed numbered
	//fields in the navigator
	var elems=document.getElementsByTagName("input");
	for (i=0; i<elems.length;i++) {	

		if (elems[i].id.indexOf("inputID_") == 0) { 
			//We found one
			var ID = elems[i].id.substring(8);
			
			var navElem = document.getElementById("navAttr_" + ID);	
			
			if (navElem != null)
				navElem.innerHTML = elems[i].value;
		}
	}
}

function delayUpdateNavParams() {	
	window.setTimeout("updateNavParams()",0);
}

function delayUpdateNavParam(paramID) {	
	window.setTimeout("updateNavParam(" + paramID + ")",0);
}

function delaySetPublishState() {
	window.setTimeout("setPublishState()",0);
}

function setFocusDetails(elemID) {

	//Always just focus to an empty anchor before the description of this object
	setFocus("detailsSectionID");
		
	/*if (setFocus(elemID) == false)
		setFocus("detailsSectionID");*/
}

function findIDInStructure(ID) {

	if (structureCache) {
		var item = structureCache[ID];	
		if (item != null)
			return item;
	}
	
	for (var i = 0; i < lastDisplayBean.structure.length; i++) {
		if (lastDisplayBean.structure[i].ID == ID)
			return i;
	}
	
	return -1;
}

function findIDInAttributes(ID) {

	if (attributesCache) {
		var item = attributesCache[ID];	
		if (item != null)
			return item;
	}

	for (var i = 0; i < lastDisplayBean.details.attributes.length; i++) {
		if (lastDisplayBean.details.attributes[i].ID == ID) {
			return i;
		}
	}
	return -1;
}

function cloneObject(obj){

    if(obj == null || typeof(obj) != 'object')
        return obj;

    var tmp = obj.constructor();

    for(var key in obj)
        tmp[key] = cloneObject(obj[key]);

    return tmp;
}

/**
 * Ellipsise the rows again because the browser has been resized.
 */
function ellipsiseRowsInt() 
{
	timerRunning = false;
	clearTimeout(timer);

	//var start = new Date().getTime();

	//var oldTimer = timer;
	
	// re-measure text incase the font size changed (by user interaction)
	measureTextWidthTD();
	// re-measure size of scrollable area
	newSizeOfScrollArea = measureNode(dojo.byId("mainButtons"));
	//debugIt("width: " + newSizeOfScrollArea.width);
		
	if(sizeOfScrollArea == null || newSizeOfScrollArea.width != sizeOfScrollArea.width) {
		// changes
		sizeOfScrollArea = newSizeOfScrollArea;
				
		for (var i = 0; i < lastDisplayBean.structure.length; i++) {		
			ellipsiseStructureBean(lastDisplayBean.structure[i]);
		}
	}
	
	//if (timer != oldTimer)
		//debugIt("oldTimer = " + oldTimer + "timer = " + timer); 
		
	//var end = new Date().getTime();
	//debugIt("ellipsiseRowsInt count: " + count + " width: " + newSizeOfScrollArea.width + " oldTimer = " + oldTimer + " timer = " + timer + " func took " + (end - start));
	
}

/*
 * Re ellipsise the table cell for the bean.
 */
function ellipsiseStructureBean(bean) {
	//var start = new Date().getTime();

	// calculate width for a single attribute value
	var attributeWidth = calculateAttributeWidth(bean);

	// attributes
	if (bean.attributes != null && bean.attributes.length > 0) {
		for (var i = 0; i < bean.attributes.length; i++) {
			// get span for this attribute
			var theSpanId = "navAttr_" + bean.attributes[i].ID;
			var theSpan = document.getElementById(theSpanId);
			if(theSpan != null) {
				// make new text
				
				//Check to see if there is an updated text field in the details section 
				//and use that instead of the saved text
				var elem=document.getElementById("inputID_" + bean.attributes[i].ID);

				if (elem != null)
				 	var txt = elem.value;
				else
					var txt = bean.attributes[i].value;
									
				var shortText = bestFitText(txt, attributeWidth, false);
				
				//debugIt(shortText + " bestFit(" + txt + "," + attributeWidth +")");
					
				// set it
				theSpan.innerHTML = shortText;
			}
		}
	}
	
	//var end = new Date().getTime();
	//debugIt("ellipsiseStructureBean took " + (end - start));
	
}

/**
 * Calcuate the attribute width for the provided bean
 */
function calculateAttributeWidth(bean) {
	var attributeWidth = 0;
	
	var fixedChars = new dojox.string.Builder();
	var fixedCharsBold = new dojox.string.Builder();
	
	// calculate values for ellipsis
	fixedCharsBold.append(bean.localName);
	fixedCharsBold.append(" ");	
	
	// consider attributes
	var attributeValues = new Array();
	var emptyAttrs = 0;
	if (bean.attributes != null && bean.attributes.length > 0) {
		for (var i = 0; i < bean.attributes.length; i++) {
			fixedChars.append(" " + bean.attributes[i].localName);
			fixedChars.append(" = \"\"");
			
			//Check to see if there is an updated text field in the details section 
			//and use that instead of the saved text
			var elem=document.getElementById("inputID_" + bean.attributes[i].ID);
			
			if (elem != null)
			 	var txt = elem.value;
			else
				var txt = bean.attributes[i].value;
				
			attributeValues.push(txt);
		}
	}
	
	// consider operations
	if (bean.ops != null && bean.ops.length > 0) {
    var divider=" \u202a|\u202c ";
    if (isRTL)
      divider=" \u202b|\u202c ";
		for (var i = 0; i < bean.ops.length; i++) {
			fixedChars.append(divider);
			fixedChars.append(bean.ops[i].localName);
		}
	}

	// work out width available for each attribute value
	attributeWidth = calculatePolicyAttachmentAuthoringAttributeWidth(bean.level, fixedCharsBold.toString(), fixedChars.toString(), attributeValues, sizeOfScrollArea.width, 1);

	return attributeWidth;
}

/**
 * Measure the width of 1 letter in our display table cell.
 * Actually measure 2 (to include a space) then half.
 */
function measureTextWidthTD() {
	// measure text for 2 ms which includes a space
	var sizeOfTD = measureText("textmeasure","mm");
	// make width half this but do not round
	globalSizeOfTDWidth = sizeOfTD.width / 2;
	//alert(globalSizeOfTDWidth);
}

/**
 * Shorten the text and append it unshortened or shortened in a span
 * Also write a span around the text with the id of
 * "item<beanId>attribute<attributeNumber>"
 */
function appendShortenedText(navDivHtml, text, width, beanId) {
	var idString = beanId;
	
	navDivHtml.append("<span id=\"navAttrParent_");
	navDivHtml.append(idString);
	navDivHtml.append("\">");	
	appendShortenedTextInnerOnly(navDivHtml, text, width, beanId);
	navDivHtml.append("</span>");
}

/**
 * Shorten the text and append it unshortened or shortened in a span
 * Also write a span around the text with the id of
 * "item<beanId>attribute<attributeNumber>"
 */
function appendShortenedTextInnerOnly(navDivHtml, text, width, beanId) {
	// shorten the attribute value
	var shortText = bestFitText(text, width, false);
	var escapedText = filterHTML(text);
	var idString = beanId;
	
	navDivHtml.append("<span id=\"navAttr_");
	navDivHtml.append(idString);
	navDivHtml.append("\"");
	if (shortText == escapedText) {
		navDivHtml.append(">");
		navDivHtml.append(shortText);
	} else {
		navDivHtml.append(" title=\"");
		navDivHtml.append(escapedText);
		navDivHtml.append("\">");
		navDivHtml.append(shortText);
	}
	navDivHtml.append("</span>");
}

function showPolicyExpression(nodeID,policyUri) {

	if (inProgress == false) {			
		//Set a global for the return
		jumpToPolicy = policyUri;
		
		//Send any changes to the server if we need to and then redirect 
		bean = buildPolicyDetailsBean(lastDisplayBean.selectedID);
	
		if (bean != null) {	
			lastAddedID = null;
			lastAddedAttributeID = null;
			addCallErrBack(policyService.navigatorAction(NavActionPublish,nodeID,bean),callBackShowPolicyExpression,errorBack);	
		} else
			callBackShowPolicyExpression();	
	}
}

function callBackShowPolicyExpression() {
	
	if (jumpToPolicy) {
		var tmp = jumpToPolicy;		
		jumpToPolicy = null;
		window.location = contextRoot + "/PolicyCutDownDetail.do?policyUri=" + escape(tmp);		
	}
}
