/* begin_generated_IBM_copyright_prolog                            */
/* 
 * Licensed Materials - Property of IBM 
 * 
 * 5724-N72 5655-W17
 * 
 * (c) Copyright IBM Corp. 2008 All Rights Reserved 
 * 
 * US Government Users Restricted Rights - Use, duplication or 
 * disclosure restricted by GSA ADP Schedule Contract with 
 * IBM Corp. 
 */
/* end_generated_IBM_copyright_prolog                              */

// types of node
var ElementTypeAttachment = new UIEnum(0);
var ElementTypeAttachmentPoint = new UIEnum(1);
var ElementTypeDisplay = new UIEnum(2);

//Global vars
var globalOptions = null;
var lastDisplayBean = null;

var firstTime = true;

var globalID = 0;

// which element is selected?
var selectedID = -1;
// old HTML for the selected element action span, containing the html link to activate the action
var selectedActionHTML = null;

var CLASS_MAND = "property mandatory";
var CLASS_READONLY_NOMAND = "readonlyproperty notmandatory";
var CLASS_NOMAND = "property notmandatory";

// size of td for text measuring
var sizeOfScrollArea = null;

// id of the div in the details panel to show progress in
var DETAILS_SHOW_PROGRESS = "detailsShowProgress";

// Common functions stolen from policy.js:

function makeBusyDefault(elemName, waitDefault, trueFalse) {

	var classMatch = "AJAXControl";
	
	// Make all buttons and links busy.	
	var elems=document.getElementsByTagName(elemName);
	var debug=document.getElementById("debug");
	for (i=0; i<elems.length;i++) {	

		if (classMatch.match(elems[i].className) != "" && elems[i].name != "finishButton" && elems[i].name != "cancelButton") { 
			elems[i].style.cursor = waitDefault;
	
			if (trueFalse == "true") {
				elems[i].disabled = false;
				if(elemName == "a") {
					// enable the href
					if(elems[i].hrefBak != null) {
						elems[i].href = elems[i].hrefBak;
					}
				}
			} else {
				elems[i].disabled = true;
				if(elemName == "a") {
					// disable the href
					if(elems[i].href != null) {
						elems[i].hrefBak = elems[i].href;
						elems[i].removeAttribute("href");
					}
				}
			}
		}
	}
}



/*
 * Enable or disable the finish button
 */
function enableDisableFinishButton() {
	var finishButton = document.getElementById("functionsFinish");
	// disable button if no changes
	if(lastDisplayBean.changesMade == true) {
		finishButton.disabled = false;
	} else {
		finishButton.disabled = true;
	}
}

/**
 * Explicitly disable the finish button as a result of an input field
 * validation error.
 */
function disableFinishButton() {
	var finishButton = document.getElementById("functionsFinish");
	// disable button 
	finishButton.disabled = true;
}

// Display initial tree functions:

/**
 * displayAttachmentsAfterLoad
 */
function displayAttachmentsAfterLoad() {	

	// keypress handlers to the form fields
	dojo.connect(dojo.byId("attachmentName"), "onkeypress", null, "handleDetailsKeyInputMandatory");
	dojo.connect(dojo.byId("attachmentNamespace"), "onkeypress", null, "handleDetailsKeyInput");
	dojo.connect(dojo.byId("attachmentVersion"), "onkeypress", null, "handleDetailsKeyInput");

	// get initial tree for attachment authoring

	// get bsrUri of the item to show
	var bsrUriInput = document.getElementById("subjectBsrUri");
	var bsrUri = bsrUriInput.value;

	// get sdo type of the item to show
	var sdoTypeInput = document.getElementById("subjectSdoType");
	var sdoType = sdoTypeInput.value;
	
	// get if first time
	var firstTimeInput = document.getElementById("firstTime");
	if(firstTimeInput.value.toLowerCase() == "true") {
		firstTime = true;
	} else {
		firstTime = false;
	}
	if(firstTime == true) {
		// set to false so when form is submitted it gets set to false
		firstTimeInput.value = "false";
	}
	
	//Create the policy service !!
	policyService = new dojo.rpc.JsonService(contextRoot + "/RPCAdapter/jsonrpc/PolicyService");
	
	//Bug #5182. On a secure system after recovery from a timeout the policyService may not
	//be initialized properly. Check if the ServiceUrl is defined and if not then send
	//the request again and all shall be well.
	if (policyService == null || policyService.serviceUrl == null)
		policyService = new dojo.rpc.JsonService(contextRoot + "/RPCAdapter/jsonrpc/PolicyService");

	if (inProgress == false) {
		if(firstTime == true) {
			// generate model and render beans
			addCallErrBack(policyService.attachFirstLoad(bsrUri, sdoType),displayPolicyAttachAuthBeanCallback,errorBack);
		} else {
			// rerender beans from existing model
			addCallErrBack(policyService.attachBuildBean(),displayPolicyAttachAuthBeanCallback,errorBack);
		}

		// show loading message
		startProgressImmediate("renderingMessage", renderingMessage);
	}
}

// show the tree, called from JSP loading
function displayPolicyAttachAuthBeanCallback(bean, exception) {
			
	endTransaction(bean, exception);

	// stop message timer
	stopProgress();
	
	// measure text
	measureTextWidthTD();
	

	// measure size of scrollable area
	sizeOfScrollArea = measureNode(dojo.byId("mainButtons"));

	// show the tree
	displayPolicyAttachAuthBean(bean, exception, false);

	// measure size of scrollable area
	var newSizeOfScrollArea = measureNode(dojo.byId("mainButtons"));

	if (newSizeOfScrollArea.width != sizeOfScrollArea.width) {
		sizeOfScrollArea = newSizeOfScrollArea;
		// show the tree
		displayPolicyAttachAuthBean(bean, exception, false);
	}				
	
	// resize to redo ellipsis
	dojo.connect(window, 'onresize', 'ellipsiseRows');
}

// show the tree for attachment authoring
function displayPolicyAttachAuthBean(bean, exception, /*boolean*/fade) {

	// store the bean
	lastDisplayBean = bean;
	
	var previousScrollPos = -1;
	var scrollBar = document.getElementById("scrollreltable");

	if (scrollBar != null) {
		previousScrollPos = scrollBar.scrollTop;
		var tmp = scrollBar.getElementsByTagName("tbody");
				
		if (tmp[0].scrollTop != 0) {
			scrollBar = tmp[0];
			previousScrollPos = scrollBar.scrollTop;
		}		
	}
	
	if (bean != null) {

		// Retrieve the divs for the navigator and the details
		var navigatorDiv = document.getElementById("navigator");
				
		if (bean.rootNode != null) {
			// Create a variable to hold the HTML for navigator
		   	var navDivHtml = new dojox.string.Builder();
		   	
			navDivHtml.append("<div class=\"scrollablesection\" id=\"scrollreltable\">");
			navDivHtml.append("<table class=\"relationshiptable\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\"structuretable\">");

			// get heading title - write out table header
			var tableTitleField = document.getElementById("tableTitle");
			navDivHtml.append("<thead><tr><th>");
			navDivHtml.append(tableTitleField.value);
			navDivHtml.append("</th></tr></thead>");
			navDivHtml.append("<tbody>");

			// recurse through the beans - write out the table body
			// the level of indentation
			var level = 0;
			addAttachmentNodeBean(bean.rootNode, navDivHtml, level);

			// add the measurer
			navDivHtml.append("<tr><td class=\"right\"><div class=\"textmeasureellipsis\" id=\"textmeasure\"></td></tr>");
	        	
	       	navDivHtml.append("</tbody></table></div>");

			// fade in - hide the navigator
			if(fade == true) {
				dojo.style(navigatorDiv, "opacity", 0);
			}
						
			// Set the HTML for the navigator div
			navigatorDiv.innerHTML = navDivHtml.toString();

			// firefox bug fix
			makeScrollable("scrollreltable");
			
			// put the scrollbar back where it was
			if (scrollBar != null) {
				var scrollBar = document.getElementById("scrollreltable");
				
				var tbodyScrollBar = scrollBar.getElementsByTagName("tbody");

				scrollBar.scrollTop = previousScrollPos;
				tbodyScrollBar[0].scrollTop = previousScrollPos;
			}

			// fade in - start the fade in			
			if(fade == true) {
				dojo.fadeIn({node : 'navigator'}).play();
			}
		}
	}	
	
	// check whether to enable finish
	enableDisableFinishButton();
	
}

/*
 * Add a bean node to the table, add children if any
 */
function addAttachmentNodeBean(bean, navDivHtml, level) {

	navDivHtml.append("<tr class=\"");
	
	if (selectedID == bean.ID)
		navDivHtml.append("selected\"");
	else
		navDivHtml.append("notselected\"");
		
	navDivHtml.append(" id=\"row");
	navDivHtml.append(bean.ID);
	navDivHtml.append("\">");
	
	addStructureTDHtml(bean, navDivHtml, level, false);
	
	navDivHtml.append("</tr>");		
	
	// check for children
	if(bean.children != null) {
		// render children
		// bump the level
		level = level + 1;
		for (var i = 0; i < bean.children.length; i++) {
			// Append the childs
			if (i == bean.children.length-1)
				addAttachmentNodeBean(bean.children[i], navDivHtml, level);
			else
				addAttachmentNodeBean(bean.children[i], navDivHtml, level);
		}		
	}
	
}

/*
 * Write out the table cell for the bean.
 */
function addStructureTDHtml(bean, navDivHtml, level, last) {
	navDivHtml.append("<td");
	
	if (last == false)
		navDivHtml.append(" class=\"right\">");
	else
		navDivHtml.append(">");

  if (isRTL && !dojo.isIE)
  	navDivHtml.append("<span style=\"margin-right:");
  else
    navDivHtml.append("<span style=\"margin-left:");
	navDivHtml.append(calculatePaddingEmSize(level));
	navDivHtml.append("em\">");

	// images and name come from bean	
	var elemName = bean.displayName;
	var elemImg = bean.iconPath;
	
	navDivHtml.append("<img src=\"theme/"+currentTheme+"/");
	navDivHtml.append(elemImg);
	navDivHtml.append("\" alt=\"");
	navDivHtml.append(elemName);
	navDivHtml.append("\" title=\"");
	navDivHtml.append(elemName);
	navDivHtml.append("\"/></span> ");

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
  var divider=" \u202a|\u202c ";
  if (isRTL)
    divider=" \u202b|\u202c ";

	// calculate width of a single attribute value
	var attributeWidth = calculateAttributeWidth(bean, level);
	
	if (bean.elementType.value == ElementTypeAttachmentPoint.value || bean.elementType.value == ElementTypeDisplay.value) {
		// attachment point OR display

		// element name
		navDivHtml.append("<span class=\"t\">");
		navDivHtml.append(bean.displayName);
		navDivHtml.append("</span>");
		
		// attributes
		if (bean.attributes != null && bean.attributes.length > 0) {
			for (var i = 0; i < bean.attributes.length; i++) {
				navDivHtml.append(" "+dirLeft+"<span class=\"ns\">");
				navDivHtml.append(bean.attributes[i].name);
				navDivHtml.append(" = </span>\""+dirMarker);
				// append a shortened attribute value
				appendShortenedText(navDivHtml, bean.attributes[i].value, attributeWidth, bean.ID, i);
				navDivHtml.append("\""+dirMarker+dirRight);
			}
		}

		// action
		if(bean.elementType.value == ElementTypeAttachmentPoint.value) {
			navDivHtml.append(divider);
			navDivHtml.append("<span id=\"itemAction" + bean.ID + "\">");
//			navDivHtml.append("<a tabindex=\"1\" class=\"AJAXControl\" href=\"javascript:attachPolicy(");
			navDivHtml.append("<a tabindex=\"1\" class=\"AJAXControl\" href=\"javascript:showSearch(");
			navDivHtml.append(bean.ID);
			navDivHtml.append(")\">");
			navDivHtml.append(textAttachPolicy);
			navDivHtml.append("</a>");
			navDivHtml.append("</span>");
		}
	} else {
		// attachment

		// element name
//		if (selectedID != bean.ID) {		
			navDivHtml.append("<span id=\"itemAction" + bean.ID + "\">");
			navDivHtml.append("<a tabindex=\"1\" class=\"AJAXControl\" href=\"javascript:showAttachmentDetails(");
			navDivHtml.append(bean.ID);
			navDivHtml.append(")\">");
			navDivHtml.append(bean.displayName);
			navDivHtml.append("</a>");
			navDivHtml.append("</span>");
//		} else {
			// render as not selectable
//			navDivHtml.append(bean.displayName);
//		}

		// uri
		navDivHtml.append(" "+dirLeft+"<span class=\"ns\">");
		navDivHtml.append(textUri);
		navDivHtml.append(" = </span>");
		navDivHtml.append("<a tabindex=\"1\" class=\"AJAXControl\" href=\"javascript:showPolicy(");
		navDivHtml.append(bean.ID);
		navDivHtml.append(")\">");
		navDivHtml.append("\""+dirMarker);
		
		// append a shortened policy uri
		appendShortenedText(navDivHtml, bean.policyUri, attributeWidth, bean.ID, 0);
		
		navDivHtml.append("\""+dirMarker+dirRight);
		navDivHtml.append("</a>");

		// action - only removable if not read only
		if(bean.readOnly == false) {
			navDivHtml.append(divider);
			navDivHtml.append("<span id=\"item");
			navDivHtml.append(bean.ID);
			navDivHtml.append("remove\">");
			navDivHtml.append("<a tabindex=\"1\" class=\"AJAXControl\" href=\"javascript:removeAttachment(");
			navDivHtml.append(bean.ID);
			navDivHtml.append(")\">");
			navDivHtml.append(textRemove);
			navDivHtml.append("</a>");
			navDivHtml.append("</span>");
		}				
	}

	navDivHtml.append("</td>");
}

/**
 * Shorten the text and append it unshortened or shortened in a span
 * Also write a span around the text with the id of
 * "item<beanId>attribute<attributeNumber>"
 */
function appendShortenedText(navDivHtml, text, width, beanId, attributeNumber) {
	// shorten the attribute value
	var shortText = bestFitText(text, width, false);
	var escapedText = filterHTML(text);
	navDivHtml.append("<span id=\"");
	var idString = getSpanIdForAttribute(beanId, attributeNumber);
	navDivHtml.append(idString);
	navDivHtml.append("\" ");
	if (shortText == escapedText) {
		navDivHtml.append(">");
		navDivHtml.append(shortText);
	} else {
		navDivHtml.append("title=\"");
		navDivHtml.append(escapedText);
		navDivHtml.append("\">");
		navDivHtml.append(shortText);
	}
	navDivHtml.append("</span>");
}

/**
 * The ID of the span containing the attribute
 */
function getSpanIdForAttribute(beanId, attributeNumber) {
	return "item" + beanId + "attribute" + attributeNumber;
}

// Button or Action Link Clicked functions:

/*
 * Attach Policy clicked.
 */
function attachPolicy(beanId) {
	if(inProgress == true) {
		return;
	}

	// set values in form
	setFormAction("attachPolicy", beanId);
	
	// check for detail changes, save if needed, then submit the form
	checkForPolicyDetailsChangesWithFunction(submitForm);
}

/*
 * Policy Uri clicked
 */
function showPolicy(beanId) {
	if(inProgress == true) {
		return;
	}

	// set values in form
	setFormAction("showPolicy", beanId);
	
	// check for detail changes, save if needed, then submit form
	checkForPolicyDetailsChangesWithFunction(submitForm);
}

/*
 * Policy Attachment details link clicked - if read only the details should be read only
 *
 * Show details panel, select the attachment in the view
 *
 * If changes are made in the currently shown panel, save them first then show the 
 * new attachment details.
 */
function showAttachmentDetails(beanId) {
	if(inProgress == true) {
		return;
	}

	// hide the messages, if any, first before any subsequent actions cause errors
	hideMessageBox();
	
	// store the bean Id for the new details to be shown in a global
	globalID = beanId;
	
	// if the user reclicks the same details this will save them
	// else save any visible details panel content. Call the
	// function to show the clicked-on details afterward.
	checkForPolicyDetailsChangesWithFunction(showAttachmentDetailsCallback);
}

/**
 * Called immediately when show attachment details is clicked,
 * or, if we need to save currently-displayed details, after
 * the save completes.
 */
function showAttachmentDetailsCallback(resultsBean, exception) {
	// end transaction
	var error = endTransaction(resultsBean, exception);

	// stop the spinner if one was shown
	stopProgress();

	// if this did not work, return
	if(error == true) {		
		return;
	}
	
	// if we have a results bean and error is false then a save has happened
	if(resultsBean != null && resultsBean.isErrorCondition == false) {
		// signal changes made
		lastDisplayBean.changesMade = true;
		
		// check finish button
		enableDisableFinishButton();
	}
		
	// recover the bean to show now
	var beanId = globalID;
	globalID = null;
	
	// get the details to show
	var nodeBean = findNodeBeanById(beanId);
	
	if(nodeBean != null && nodeBean.detailsBean != null) {
	
		var detailsBean = nodeBean.detailsBean;
	
		// set the details fields to the correct values
		var nameField = document.getElementById("attachmentName");
		var namespaceField = document.getElementById("attachmentNamespace");
		var versionField = document.getElementById("attachmentVersion");
		var scopeXPathField = document.getElementById("attachmentScopeXPath");
		var wsdlIdentifierField = document.getElementById("attachmentWsdlIdentifier");		
		var idField = document.getElementById("attachmentId");

		nameField.value = detailsBean.name;
		namespaceField.value = detailsBean.namespace;
		versionField.value = detailsBean.version;
		scopeXPathField.value = detailsBean.scopeXPath;
		wsdlIdentifierField.value = detailsBean.wsdlIdentifier;
		idField.value = beanId;

		var nameDiv = document.getElementById("attachmentNameDiv");
		var namespaceDiv = document.getElementById("attachmentNamespaceDiv");
		var versionDiv = document.getElementById("attachmentVersionDiv");
//		var applyButton = document.getElementById("functionsApply");
		var resetButton = document.getElementById("functionsReset");

		// make read only if the node is read only
		if(nodeBean.readOnly) {
			// make fields read only
			nameField.readOnly = true;
			namespaceField.readOnly = true;
			versionField.readOnly = true;
			
			// make the divs read only style
			nameDiv.className = CLASS_READONLY_NOMAND;
			namespaceDiv.className = CLASS_READONLY_NOMAND;
			versionDiv.className = CLASS_READONLY_NOMAND;
			
			// disable apply button
//			applyButton.disabled = true;
			// disable reset button
			resetButton.disabled = true;
		} else {
			// make read write
			nameField.readOnly = false;			
			namespaceField.readOnly = false;			
			versionField.readOnly = false;		
			
			// make the divs write style
			nameDiv.className = CLASS_MAND;
			namespaceDiv.className = CLASS_NOMAND;
			versionDiv.className = CLASS_NOMAND;
			
			// enable apply button
//			applyButton.disabled = false;
			// enable reset button
			resetButton.disabled = false;
		}

		// hide search panel
		showHideSearchPanel(false);

		// show details panel
		showHideDetailsPanel(true);	
		
		// clear previous selected row
		if(selectedID != -1) {
			var selectedElement = document.getElementById("row" + selectedID);
			selectedElement.className = "notselected";
		}

		// enable the action on the previous row
		enableRowAction(selectedID);

		// select new row
		var newElement = document.getElementById("row" + beanId);
		newElement.className = "selected";

		// disable and bold the action on this row
		disableRowAction(beanId);
		
		// set selected ID to new row
		selectedID = beanId;
		
		// set the focus to the start of the details panel
		setFocus("detailsSectionID");
	}
	
}

/* 
 * Remove clicked.
 *
 * Remove only on policies we can actually remove so no need to check
 * for read only ness.
 */
function removeAttachment(beanId) {
	var beanIdString = "" + beanId;
	
	// do remove on the server first, then clear up the UI once we know it worked
	addCallErrBack(policyService.attachRemoveAttachment(beanIdString),removeAttachmentCallback,errorBack);
	
	// show progress changing the Remove link selected
	startProgress("item" + beanId + "remove", removingMessage);
}

/*
 * Removing attachment on the server has finished.
 *
 * Results are a results bean, contains error if something went wrong on the server.
 */ 
function removeAttachmentCallback(resultsBean, exception) {	
	var error = endTransaction(resultsBean, exception);

	// stop progress
	stopProgress();

	// if this didn't work, return
	if(error == true) {		
		return;
	}

	// get id of attachment
	var attachmentId = resultsBean.returnValue;
	
	// if we are looking at the details for this item, hide the details
	if(isDetailsPanelShown() == true) {
		var idField = document.getElementById("attachmentId");
		var id = idField.value;

		if(id != null && id != "" && id == attachmentId) {
			// details is for the policy we are removing
			showHideDetailsPanel(false);
		}
	}

	// if the item we are removing is selected, reset selection
	if(selectedID == attachmentId) {
		selectedID = -1;
	}

	// remove attachment from nodes
	// find parent to remove attachment from
	var parent = findNodeBeanParentById(attachmentId);
	if(parent != null) {
		// find where in the children this thing is
		var childrenPos = -1;
		for(var i = 0; i < parent.children.length; i++) {
			if(parent.children[i].ID == attachmentId) {
				childrenPos = i;
				break;
			}
		}
		if(childrenPos != -1) {
			// remove child from children array
			parent.children.splice(childrenPos, 1);
		}
	}
	
	// rerender table - easier than trying to remove the necessary HTML elements using DOM
	displayPolicyAttachAuthBean(lastDisplayBean, null, false);
	
	// signal changes made
	lastDisplayBean.changesMade = true;
	
	// check finish button
	enableDisableFinishButton();
}

/*
 * Finish button clicked
 */
function finishClicked() {
	if(inProgress == true) {
		return;
	}

	// set values in form
	setFormAction("finish", null);
	
	// check for detail changes, save if needed, then submit the form
	checkForPolicyDetailsChangesWithFunction(submitForm);
	
	// then the call back function is either run directly or after the AJAX request returns
 	// and will submit the form.
}

/*
 * Cancel button clicked
 */
function cancelClicked() {
	if(inProgress == true) {
		return;
	}

	// dont save any details changes cos we are cancelling
	// set action on form and submit the form
	setFormAction("cancel", null);
	submitForm();
}

/*
 * Reset button clicked on details form
 */
function resetClicked() {
	if(inProgress == true) {
		return;
	}

	// get details bean Id
	var idField = document.getElementById("attachmentId");
	var id = idField.value;

	if(id != null && id != "") {
		// set values from bean
		
		var nodeBean = findNodeBeanById(id);
		if(nodeBean != null && nodeBean.detailsBean != null) {
			var detailsBean = nodeBean.detailsBean;
			
			// set the details fields to the correct values
			var nameField = document.getElementById("attachmentName");
			var namespaceField = document.getElementById("attachmentNamespace");
			var versionField = document.getElementById("attachmentVersion");
			var scopeXPathField = document.getElementById("attachmentScopeXPath");
			var wsdlIdentifierField = document.getElementById("attachmentWsdlIdentifier");		
	
			nameField.value = detailsBean.name;
			namespaceField.value = detailsBean.namespace;
			versionField.value = detailsBean.version;
			scopeXPathField.value = detailsBean.scopeXPath;
			wsdlIdentifierField.value = detailsBean.wsdlIdentifier;
		}
	}
}

/*
 * Apply button clicked on details form
 */
function applyClicked(){
	if(inProgress == true) {
		return;
	}
	
	// check for changes but don't hide the details panel afterward
	checkForPolicyDetailsChanges(false);
}

/*
 * Search button clicked
 */
function searchClicked(suggestion){
	if(inProgress == true) {
		return;
	}

	// set values in form
	var searchIdField = document.getElementById("attachmentSearchId");
	setFormAction("attachPolicy", searchIdField.value);
	
	if (suggestion) {
		if (suggestion.count == 1) {
			// indicate we should just get the item and attach it without going to the collection
			setFormAction("attachPolicyDirect", searchIdField.value);
		}
	}
	
	// submit the form
	submitForm();	
}

/*
 * Search cancel button clicked
 */
function searchCancelClicked(){
	// just hide the search panel
	showHideSearchPanel(false);
	
	// reenable action
	var searchIdField = document.getElementById("attachmentSearchId");
	var beanId = searchIdField.value;
	enableRowAction(beanId);
	
	// deselect row
	if(selectedID != -1) {
		var selectedElement = document.getElementById("row" + selectedID);
		selectedElement.className = "notselected";
	}
}

function showSearch(beanId){
	// hide the messages, if any, first before any subsequent actions cause errors
	hideMessageBox();
		
	// save any visible details panel content
	checkForPolicyDetailsChanges(true);
	
	// set search fields
	var searchField = document.getElementById("attachmentSearch");
	searchField.value = "";
	var searchIdField = document.getElementById("attachmentSearchId");
	// save id of bean to attach to
	searchIdField.value = beanId;

	// set autosuggest type
	suggestionWidget.setType("PolicyExpression");

	// clear previous selected row
	if(selectedID != -1) {
		var selectedElement = document.getElementById("row" + selectedID);
		selectedElement.className = "notselected";
	}

	// enable the action on the previous row
	enableRowAction(selectedID);

	// select new row
	var newElement = document.getElementById("row" + beanId);
	newElement.className = "selected";
	
	// disable and bold the action on this row
	disableRowAction(beanId);
	
	// set selected ID to new row
	selectedID = beanId;

	// show search panel
	showHideSearchPanel(true);
	
	// set the focus
	setFocus("attachmentSearch");
}

/*
 * Enable the action on the given row, by restoring the
 * previously-saved link HTML to the span, if it exists.
 *
 * Then blank the previously-saved HTML variable.
 */
function enableRowAction(beanId) {
	// check we had an action before
	if(selectedActionHTML != null) {
		// get the span for the action
		var actionSpan = document.getElementById("itemAction" + beanId);
		if(actionSpan != null) {
			// found a span - restore
			actionSpan.innerHTML = selectedActionHTML;
			
			// blank out the old html
			selectedActionHTML = null;
		}
	}
}

/*
 * Disable the action on a given row, by removing the
 * html link and rendering the link text in bold, if
 * the bean has an action.
 *
 * Save the old HTML link text for later.
 */
function disableRowAction(beanId) {
	// get the span for the action
	var actionSpan = document.getElementById("itemAction" + beanId);
	if(actionSpan != null) {
		// found span - save old html
		selectedActionHTML = actionSpan.innerHTML;
		
		// make a bolded link text
		// get the bean
		var bean = findNodeBeanById(beanId);
		if(bean != null) {
			// work out the type of bean we have
			if(bean.elementType.value == ElementTypeAttachmentPoint.value) {
				// attachment point
				actionSpan.innerHTML = "<b>" + textAttachPolicy + "</b>";
			} else {
				// attachment
				actionSpan.innerHTML = "<b>" + bean.displayName + "</b>";
			}
		} else {
			// erm this should not happen
			actionSpan.innerHTML = "";
		}
	}
}

// Utility functions:

/*
 * Submit the form.
 */
function submitForm() {
	// stop progress
	stopProgress();
	
	var theForm = document.getElementById('com.ibm.sr.ui.forms.PolicyAttachmentEditForm');
	
	// submit
	theForm.submit();
}

/*
 * Set the form action field to the specified value.
 * Set the form parameter field to the specified value.
 * Submit it to the button action.
 */
function setFormAction(action, parameter) {
	var theForm = document.getElementById('com.ibm.sr.ui.forms.PolicyAttachmentEditForm');
	// set the action field
	theForm.action.value = action;
	// set the paremeter field
	if(parameter == null) {
		parameter = "";
	}
	theForm.parameter.value = parameter;
}

/*
 * Check that there are details entered in the details panel
 * and there are changes. If so we need to save these.
 * Then blank the details in the panel and hide the details panel, if hidePanel true.
 
 * Used when user navigates away from the detail page, but not for a button
 * press cos the action will deal.
 */
function checkForPolicyDetailsChanges(hidePanel) {

	// need to know if the panel was shown or not! If not shown dont bother OR attach Id null
	if(isDetailsPanelShown() == true) {
		// check the attachId is not null/empty
		var idField = document.getElementById("attachmentId");
		var id = idField.value;
		if(id != null && id != "") {
			var detailsBean = updateWithPolicyDetailsChanges(id);
			
			// now save on the server if changes
			if(detailsBean != null) {
				saveDetails(id, detailsBean);
			}
		}
		
		// hide details panel
		if(hidePanel == true) {
			showHideDetailsPanel(false);
			
			var idField = document.getElementById("attachmentId");
			var nameField = document.getElementById("attachmentName");
			var namespaceField = document.getElementById("attachmentNamespace");
			var versionField = document.getElementById("attachmentVersion");
			var scopeXPathField = document.getElementById("attachmentScopeXPath");
			var wsdlIdentifierField = document.getElementById("attachmentWsdlIdentifier");		
			
			// blank details
			idField.value = "";
			nameField.value = "";
			namespaceField.value = "";
			versionField.value = "";
			scopeXPathField.value = "";
			wsdlIdentifierField.value = "";
		}
	}
}

/*
 * If the details panel contents have changed, save them
 * to the correct details bean and return the bean. Else
 * return null.
 */
function updateWithPolicyDetailsChanges(id) {
	// get the details bean for this
	var nodeBean = findNodeBeanById(id);
	// if not null and is writeable
	if(nodeBean != null && nodeBean.readOnly == false) {
		var detailsBean = nodeBean.detailsBean;

		var nameField = document.getElementById("attachmentName");
		var namespaceField = document.getElementById("attachmentNamespace");
		var versionField = document.getElementById("attachmentVersion");
		
		var name = nameField.value;
		var namespace = namespaceField.value;
		var version = versionField.value;
		
		// check the values have changed
		if(detailsBean.name != name || detailsBean.namespace != namespace || detailsBean.version != version) {
		
			// check the mandatory name field has a value - else don't save
			if(name != "") {
				// set values
				detailsBean.name = name;
				detailsBean.namespace = namespace;
				detailsBean.version = version;
				
				// return the bean
				return detailsBean;
			}
		} // otherwise do nothing
	}
	
	// no changes
	return null;
}

/*
 * If the details panel contents have changed, return true. Else
 * return false. Do not save the changes to the bean.
 */
function arePolicyDetailsChanges(id) {
	// get the details bean for this
	var nodeBean = findNodeBeanById(id);
	// if not null and is writeable
	if(nodeBean != null && nodeBean.readOnly == false) {
		var detailsBean = nodeBean.detailsBean;

		var nameField = document.getElementById("attachmentName");
		var namespaceField = document.getElementById("attachmentNamespace");
		var versionField = document.getElementById("attachmentVersion");
		
		var name = nameField.value;
		var namespace = namespaceField.value;
		var version = versionField.value;
		
		// check the values have changed
		if(detailsBean.name != name || detailsBean.namespace != namespace || detailsBean.version != version) {
			return true;
		}
	}
	return false;
}

function checkForPolicyDetailsChangesWithFunction(endFunction) {

	// the bean to save on the server
	var detailsBean = null;
	
	// need to know if the panel was shown or not! If not shown dont bother OR attach Id null
	if(isDetailsPanelShown() == true) {
		// check the attachId is not null/empty
		var idField = document.getElementById("attachmentId");
		var id = idField.value;
		if(id != null && id != "") {
			detailsBean = updateWithPolicyDetailsChanges(id);
			
			// now save on the server if changes
			if(detailsBean != null) {
				// show progress
				startProgress(DETAILS_SHOW_PROGRESS, processingMessage);
				// save
				saveDetailsWithFunction(id, detailsBean, endFunction);
			}
		}
	}
	
	// if bean is still null, we did not save it and so must call the endFunction function
	// but with a valid variable in the resultsBean and null for the exception.
	if(detailsBean == null) {
		var resultsBean = new Array();
		endFunction(resultsBean, null);
	}
}

/*
 * Handle a key pressed in the details panel fields
 */
function handleDetailsKeyInput(/*DOMevent*/e) {
	var input=e.target;
	window.setTimeout(function(){handleDetailsKeyInputCallback(input)},0);
}

/*
 * Handle a key pressed in the details panel field which is mandatory
 */
function handleDetailsKeyInputMandatory(/*DOMevent*/e) {
	var input=e.target;
	window.setTimeout(function(){handleDetailsKeyInputMandatoryCallback(input)},0);
}

/*
 * Key pressed in details panel fields.
 * Check if changes made, if so enable Finish button.
 */
function handleDetailsKeyInputCallback(input) {
	// need to know if the panel was shown or not! If not shown dont bother OR attach Id null
	if(isDetailsPanelShown() == true) {
		// check the attachId is not null/empty
		var idField = document.getElementById("attachmentId");
		var id = idField.value;
		if(id != null && id != "") {
			// check for changes but don't set in beans

			var areChanges = arePolicyDetailsChanges(id);
			
			// now enable finish if changes
			if(areChanges == true) {
				lastDisplayBean.changesMade = true;
				enableDisableFinishButton();
			}
			
			// we cannot set changes made to false, because there
			// may have been previous changes.
		}
	}
}

/*
 * Key pressed in details panel field which is mandatory.
 * Check if changes made, if so enable Finish button and clear any errors.
 * Check that field contains a value, if not disable Finish and show an error.
 */
function handleDetailsKeyInputMandatoryCallback(input) {
	// need to know if the panel was shown or not! If not shown dont bother OR attach Id null
	if(isDetailsPanelShown() == true) {
		// check the attachId is not null/empty
		var idField = document.getElementById("attachmentId");
		var id = idField.value;
		if(id != null && id != "") {
			// check the field has a value
			if(input.value != "") {
				// check for changes but don't set in beans
				var areChanges = arePolicyDetailsChanges(id);
				
				// now enable finish if changes
				if(areChanges == true) {
					lastDisplayBean.changesMade = true;
					enableDisableFinishButton();
				}
				
				// clear any previous errors
				hideMessageBox();
				
				// we cannot set changes made to false, because there
				// may have been previous changes.
			} else {
				// input field is empty so this is an error
				// disable Finish button
				disableFinishButton();
				
				// show error
				displayError(errorNameEmpty, null);
			}
		}
	}
}

/*
 * Find the node bean with the given Id
 */
function findNodeBeanById(beanId) {
	var rootNode = lastDisplayBean.rootNode;
	
	var bean = findNodeBeanByIdNode(beanId, rootNode);
	
	return bean;
}

function findNodeBeanByIdNode(beanId, node) {
	if(node.ID == beanId) {
		return node;
	} else {
		// check children if any
		if(node.children != null) {
			for (var i = 0; i < node.children.length; i++) {
				var match = findNodeBeanByIdNode(beanId, node.children[i]);
				if(match != null) {
					// found match
					return match;
				}
			}
		}
	}
	
	return null;
}

/*
 * Find the parent of the node bean with the given Id
 */
function findNodeBeanParentById(beanId) {
	var rootNode = lastDisplayBean.rootNode;
	
	var bean = findNodeBeanParentByIdNode(beanId, rootNode, null);
	
	return bean;
}

function findNodeBeanParentByIdNode(beanId, node, parent) {
	if(node.ID == beanId) {
		return parent;
	} else {
		// check children if any
		if(node.children != null) {
			for (var i = 0; i < node.children.length; i++) {
				var match = findNodeBeanParentByIdNode(beanId, node.children[i], node);
				if(match != null) {
					// found match
					return match;
				}
			}
		}
	}
	
	return null;
}

/*
 * Show (true) or hide (false) the 
 * details panel
 */
function showHideDetailsPanel(show) {
	var detailsPanel = document.getElementById("detailsection");
	if(show) {
		detailsPanel.style.display = "block";
	} else {
		detailsPanel.style.display = "none";
	}
}

/*
 * Is the details panel currently shown?
 */
function isDetailsPanelShown() {
	var detailsPanel = document.getElementById("detailsection");
	if(detailsPanel.style.display == "block") {
		return true;
	} else {
		return false;
	}
}

/*
 * Show (true) or hide (false) the 
 * search panel
 */
function showHideSearchPanel(show) {
	var searchPanel = document.getElementById("searchsection");
	if(show) {
		searchPanel.style.display = "block";
	} else {
		searchPanel.style.display = "none";
	}
}

/*
 * Save the details bean on the server.
 */
function saveDetails(id, details) {
// TODO: some sort of loading message?
	addCallErrBack(policyService.attachSaveAttachmentDetails(id, details),saveDetailsCallback,errorBack);
}

/*
 * Save the details bean on the server then run the function.
 */
function saveDetailsWithFunction(id, details, endFunction) {
// TODO: some sort of loading message?
	addCallErrBack(policyService.attachSaveAttachmentDetails(id, details),endFunction,errorBack);
}

/*
 * Saving details on the server has finished.
 *
 * Results are a results bean, contains error if something went wrong on the server.
 */
function saveDetailsCallback(resultsBean, exception){
	var error = endTransaction(resultsBean, exception);

	// if this worked:
	if(error == false) {		
		// signal changes made
		lastDisplayBean.changesMade = true;
		
		// check finish button
		enableDisableFinishButton();
	}	
}

function hideMessageBox() {
	// clear any previous errors
	var messageBox=document.getElementById("msgannounce");	
	messageBox.style.display="none";         // make the box invisible
}


// resizing ellipsis stuff

/**
 * Ellipsise the rows again because the browser has been resized.
 */
function ellipsiseRowsInt() 
{
	clearTimeout(timer);
	
	// re-measure text incase the font size changed (by user interaction)
	measureTextWidthTD();
	
	// re-measure size of scrollable area
	newSizeOfScrollArea = measureNode(dojo.byId("mainButtons"));
	if(newSizeOfScrollArea.width != sizeOfScrollArea.width) {
		// changes
		sizeOfScrollArea = newSizeOfScrollArea;
		
		var bean = lastDisplayBean;
		if (bean.rootNode != null) {
			// recurse through the beans - changing the span contents
			// the level of indentation
			var level = 0;
			ellipsiseAttachmentNodeBean(bean.rootNode, level);
		}
	}
}

/*
 * Re ellipsise a bean node in the table, and children if any
 */
function ellipsiseAttachmentNodeBean(bean, level) {

	ellipsiseStructureTDHtml(bean, level);
	
	// check for children
	if(bean.children != null) {
		// render children
		// bump the level
		level = level + 1;
		for (var i = 0; i < bean.children.length; i++) {
			// Append the childs
			if (i == bean.children.length-1)
				ellipsiseAttachmentNodeBean(bean.children[i], level);
			else
				ellipsiseAttachmentNodeBean(bean.children[i], level);
		}		
	}
}

/*
 * Re ellipsise the table cell for the bean.
 */
function ellipsiseStructureTDHtml(bean, level) {

	// calculate width for a single attribute value
	var attributeWidth = calculateAttributeWidth(bean, level);

	if (bean.elementType.value == ElementTypeAttachmentPoint.value || bean.elementType.value == ElementTypeDisplay.value) {
		// attachment point OR display

		// attributes
		if (bean.attributes != null && bean.attributes.length > 0) {
			for (var i = 0; i < bean.attributes.length; i++) {
				// get span for this attribute
				var theSpanId = getSpanIdForAttribute(bean.ID, i);
				var theSpan = document.getElementById(theSpanId);
				if(theSpan != null) {
					// make new text
					var shortText = bestFitText(bean.attributes[i].value, attributeWidth, false);
					// set it
					theSpan.innerHTML = shortText;
				}
			}
		}
	} else {
		// attachment

		// uri
		var theSpanId = getSpanIdForAttribute(bean.ID, 0);
		var theSpan = document.getElementById(theSpanId);
		if(theSpan != null) {
			// make new text
			var shortText = bestFitText(bean.policyUri, attributeWidth, false);
			// set it
			theSpan.innerHTML = shortText;
		}
	}
}

/**
 * Calcuate the attribute width for the provided bean
 */
function calculateAttributeWidth(bean, level) {
	var attributeWidth = 0;
	var fixedChars = new dojox.string.Builder();
  var divider=" \u202a|\u202c ";
  if (isRTL)
    divider=" \u202b|\u202c ";
	
	if (bean.elementType.value == ElementTypeAttachmentPoint.value || bean.elementType.value == ElementTypeDisplay.value) {
		// attachment point OR display

		// calculate values for ellipsis
		fixedChars.append(bean.displayName);
		fixedChars.append(" ");

		// consider attributes
		var attributeValues = new Array();
		if (bean.attributes != null && bean.attributes.length > 0) {
			for (var i = 0; i < bean.attributes.length; i++) {
				fixedChars.append(" " + bean.attributes[i].name);
				fixedChars.append(" = \"\"");
				
				// add value to attributes
				attributeValues.push(bean.attributes[i].value);
			}
		}
		// consider an action 
		if(bean.elementType.value == ElementTypeAttachmentPoint.value) {
			// will append textAttachPolicy and spaces and the pipe
			fixedChars.append(divider+""+textAttachPolicy);
		}		
		
		// work out width available for each attribute value
		attributeWidth = calculatePolicyAttachmentAuthoringAttributeWidth(level, "", fixedChars.toString(), attributeValues, sizeOfScrollArea.width, 1);

	} else {
		// attachment

		// calculate values for ellipsis
		fixedChars.append(bean.displayName);
		fixedChars.append(" ");
		
		// attributes 
		var attributeValues = new Array();
		attributeValues[0] = bean.policyUri;

		fixedChars.append(textUri);
		fixedChars.append(" = \"\"");
		
		// consider the action
		if(bean.readOnly == false) {
			// will append textRemove and spaces and the pipe
			fixedChars.append(divider+""+textRemove);
		}		
		
		//add an m to the end for good luck
		fixedChars.append("m");
		
		// work out width available for each attribute value
		attributeWidth = calculatePolicyAttachmentAuthoringAttributeWidth(level, "", fixedChars.toString(), attributeValues, sizeOfScrollArea.width, 1);
	}
	
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
}
