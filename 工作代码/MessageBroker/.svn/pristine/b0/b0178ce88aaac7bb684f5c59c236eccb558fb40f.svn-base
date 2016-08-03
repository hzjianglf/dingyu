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
/* Licensed Materials - Property of IBM 
 * 
 * 5724-N72 5655-R41 
 * 
 * (c) Copyright IBM Corp. 2008 All Rights Reserved 
 * 
 * US Government Users Restricted Rights - Use, duplication or 
 * disclosure restricted by GSA ADP Schedule Contract with 
 * IBM Corp. 
 */ 

/* load required packages */
dojo.registerModulePath("com.ibm.sr.widgets", "../widgets");
dojo.require("dojo.date.locale");
dojo.require("dojo.fx");
dojo.require("dojo.parser");
dojo.require("dojo.rpc.JsonService");
dojo.require("dijit.form.DateTextBox");
dojo.require("dijit.form.NumberTextBox");
dojo.require("dijit.form.ValidationTextBox");
dojo.require("com.ibm.sr.widgets.PaddedSpinner");
dojo.require("com.ibm.sr.widgets.TimeSelector");
dojo.require("com.ibm.sr.widgets.DateSelector");
dojo.require("com.ibm.sr.widgets.DateTimePopup");
dojo.require("com.ibm.sr.widgets.DateTimeTextBox");
dojo.require("com.ibm.sr.widgets.AddRelationship");
dojo.require("com.ibm.sr.widgets.AddLoadOrCreateRelationshipTarget");
dojo.require("com.ibm.sr.widgets.DetailView");
dojo.require("com.ibm.sr.widgets.LongInteger");
dojo.require("com.ibm.sr.widgets.ProgressPanel");
dojo.require("com.ibm.sr.widgets.DigitTextBox");
dojo.require("com.ibm.sr.widgets.ValidationDigitBox");

// open the JSON service
var goService=new dojo.rpc.JsonService(contextRoot + "/RPCAdapter/jsonrpc/CreateGOService");
var baseObjectService=new dojo.rpc.JsonService(contextRoot + "/RPCAdapter/jsonrpc/BaseObjectService");

// globals
var mandatoryComplete=false;
var relationshipsComplete=false;
var validationComplete=false;
var lastStatusMode=0;
var currentView=1;
var previousView=1;
var currentWidget=null;
var selectedRow="row0";
var processingAjax=false;
var wipeDuration=400;
var rowCounter=0;
// id of the div to show progress in the ProgressPanel widget - no idea how to refer to the static in the widget itself
var PROGRESS_DIV_ID = "progressPanelSection";
// title to show for the progress panel when/if it appears
var progressTabTitle="";
var lastClientSize={width: 0, height: 0};

var previousScrollPos = -1;

// height of the scrollable table in pixels. Used to position the new element in
// the middle of the table.
var tableHeight = 170;

var lastTargetViewed = null;

// create an empty generic object template
var sourceObject={
  properties: [
  {
    "name":"__typeDisplayName",
    "value":displayTypeName
  }],
  relationships: [
  {
    "targets":[],
    "name":"predecessors"
  },
  {
    "targets":[],
    "name":"template"
  }],
  classifications: []
};
var targetCache={};
var rowTargetLookup={};
var tabDefinitionCache={};
var businessModelInfoCache={};
var errorBsrUrisMap={};
var bsrUrisMap={};
var errorBsrUriFinalObj=null;


/**
 * Initialise all the required stuff on first page load.
 */
function initPage()
{
  adjustTableSize();
  // sort out convenience data
  if (errorBsrUris && errorBsrUris.data)
  {
    // convenience reference to get a hashmap of the error bsruris
	for(x in errorBsrUris.data) {
		errorBsrUrisMap[errorBsrUris.data[x]] = true;
		errorBsrUriFinalObj = errorBsrUris.data[x];		
	}
  }
  // sort out convenience data
  if (bsrUris && bsrUris.data)
  {
    // convenience reference to get a hashmap of the error bsruris
	for(x in bsrUris.data) {
		bsrUrisMap[bsrUris.data[x]] = true;
	}
  }
  if (optionalRels && optionalRels.data)
  {
    // convenience reference to get the list of optional rels
    optionalRelList=optionalRels.data;
  }
  if (requiredRels && requiredRels.data)
  {
    // convenience reference to get the list of required rels
    requiredRelList=requiredRels.data;
    // add required relationships to the object template
    for (var i=0;i<requiredRelList.length;i++)
    {
      var rel=requiredRelList[i];
      var ncName=rel.NCName;
      var rowId="row"+(i+1);
      var newRel={"targets":[],"name":ncName,"rowId":rowId,"targetRows":[]};
      sourceObject.relationships[sourceObject.relationships.length]=newRel;
    } // end for
  }
  if (allProps && allProps.data)
  {
    // convenience reference to get the list of all properties
    allPropsList=allProps.data;
    // add the property list into the object template
    for (var i=0;i<allPropsList.length;i++)
    {
      var propName=allPropsList[i];
      var newProp={"name":propName,"value":""};
      sourceObject.properties[sourceObject.properties.length]=newProp;
    } // end for
  }
  else
  {
    // standard props not available, must add at least name
    var newProp={"name":"name","value":""};
    sourceObject.properties[sourceObject.properties.length]=newProp;
  }
  if (targetModelInfosRaw && targetModelInfosRaw.data)
  {
    // convenience reference to get the map of target info
    targetModelInfos=targetModelInfosRaw.data;
  }
  if (addedRels && addedRels.data)
  {
    // convenience reference to get the list of added rels
    addedRelList=addedRels.data;
  }
  if (requiredRelTargetsRaw && requiredRelTargetsRaw.data)
  {
    // convenience reference to get the map of required relationship targets
    requiredRelTargets=requiredRelTargetsRaw.data;
  }
  // get the table row index
  var table=document.getElementById("relationshiptable");
  if (table)
  {
    var rows=table.tBodies[0].rows;
    rowCounter=rows.length-1;
  }
  // show any existing added rels and targets
  displayRelationshipData();
  // hook up any mandatory fields
  setupMandatoryProperties();
  // make sure all screen information is up-to-date
  checkRequiredInput();
  if (dojo.isIE==6)
  {
    if (typeof window != "undefined")
      dojo.connect(window, 'onresize', 'handleWindowResize');  // window resize
  }
  nudgeTable();
  
  if (viewMe != null) {
	viewTargetDetails(viewMe);  
  } else {
	  // place focus on the name field
	  var nameField=document.getElementById("help1");
	  if (nameField)
	    nameField.focus();
  }
}

/**
 * Adjusts the main container table when column contents are over-wide.
 */
function nudgeTable()
{
  var table=document.getElementById("detailcontenttable");
  if (table && (currentView==1 || currentView==4))
  {
    var rows=table.tBodies[0].rows;
    if (rows.length>1)
    {
      var row=rows[1];
      var leftColumn=row.cells[0];
      var rightColumn=row.cells[1];
      var left=dojo.coords(leftColumn);
      var right=dojo.coords(rightColumn);
      var propContainer=document.getElementById("editpropertiessection");
      if (propContainer)
      {
        var propCoords=dojo.coords(propContainer);
        var inputFields=propContainer.getElementsByTagName("input");
        if (inputFields)
        {
          var containerWidth=propCoords.w;
          var overwide=false;
          var wideness=0;
          for (var i=0;i<inputFields.length;i++)
          {
            var input=inputFields[i];
            var inputCoords=dojo.coords(input);
            if (isRTL)
            {
              if (inputCoords.l<0)
              {
                overwide=true;
                var amount=inputCoords.l*-1;
                if (amount>wideness)
                  wideness=amount;
              }
            }
            else
            {
              var inputWidth=inputCoords.w+inputCoords.l;
              if (inputWidth>containerWidth)
              {
                overwide=true;
                var amount=inputWidth-containerWidth;
                if (amount>wideness)
                  wideness=amount;
              }
            }
          } // end for
          if (overwide)
          {
            var totalWidth=left.w+right.w;
            var columnWidth=totalWidth/20;
            var numColumns=Math.ceil(wideness/columnWidth);
            leftColumn.colSpan=10+numColumns;
            rightColumn.colSpan=10-numColumns;
          }
          else if (browserType=="chrome" || browserType=="safari")
          {
            var colWidth=left.w+right.w;
            var extra=left.w-colWidth/2;
            var columnWidth=left.w/10;
            var numColumns=Math.floor(extra/columnWidth);
            leftColumn.colSpan=10-numColumns;
            rightColumn.colSpan=10+numColumns;
          }
        }
      }
    }
  }
}

/**
 * Sets up all the handling for mandatory properties.
 */
function setupMandatoryProperties()
{
  var anyBlank=false;
  // merge all the inputs into one list for processing
  var inputs=getPropertyInputs();
  if (inputs)
  {
    for (var i=0;i<inputs.length;i++)
    {
      var input=inputs[i];
      // name attribute will be the var NCName
      var name=input.getAttribute("name");
      if (name)
      {
        var property=propertyList[name];
        if (property)
        {
          // attach input handler for mandatory fields
          if (property.mandatoryValue && property.displayAs!="BOOLEAN")
          {
            var value=input.value;
            if (value || (typeof value=='number' && value==0))
              property.hasValue=true;
            if (input.id)
            {
              // only pickup fields with name and ID - rest are Dojo inputs
              // which are dealt with separately
              dojo.connect(input,"onkeypress",null,"handleKeyInput");
              dojo.connect(input,"onblur",null,"handleKeyInput");
            }
          }
        }
      }
    } // end for
  }
  // now do the Dojo inputs (if any)
  for (var key in propertyList)
  {
    var property=propertyList[key];
    // add the handlers to all Dojo inputs, even non-mandatory
    if (property.displayAs!="BOOLEAN")
    {
      var globalName="djif"+key;
      if (window[globalName])
      {
        var dojoInput=window[globalName];
        dojoInput.onChange=handleInputOnChange;
        dojoInput.onkeyup=handleKeyInputDojo;
      }
    }
  } // end for
}

/**
 * Returns an array of all the property inputs on the page.
 */
function getPropertyInputs()
{
  var inputs=null;
  var propContainer=document.getElementById("editpropertiessection");
  if (propContainer)
  {
    inputs=[];
    // go through all the inputs on the page
    var inputFields=propContainer.getElementsByTagName("input");
    var textareaFields=propContainer.getElementsByTagName("textarea");
    var selectFields=propContainer.getElementsByTagName("select");
    // merge all the inputs into one list for processing
    if (inputFields)
    {
      for (var i=0;i<inputFields.length;i++)
      {
        inputs[inputs.length]=inputFields[i];
      } // end for
    }
    if (textareaFields)
    {
      for (var i=0;i<textareaFields.length;i++)
      {
        inputs[inputs.length]=textareaFields[i];
      } // end for
    }
    if (selectFields)
    {
      for (var i=0;i<selectFields.length;i++)
      {
        inputs[inputs.length]=selectFields[i];
      } // end for
    }
  }
  return(inputs);
}

/**
 * Called whenever a key is pressed on a mandatory field.
 */
function handleKeyInput(/*DOMevent*/e)
{
  var input=null;
  if (e.srcElement)
    input=e.srcElement;
  else
    input=e.target;
  window.setTimeout(function(){handleKeyInputCallback(input)},0);
}

/**
 * Called whenever a key is pressed on a mandatory dojo field.
 */
function handleKeyInputDojo(/*DOMevent*/e)
{
  var input=null;
  if (e.srcElement)
    input=e.srcElement;
  else
    input=e.target;
  var propertyName=this.name;
  window.setTimeout(function(){handleKeyInputCallback(input,propertyName)},0);
}

function filterHTML(message) {
		
		var sb = message;
		var entities = [ "&amp;", "&lt;", "&gt;",  "&quot;", "&#39;" ];

	    var index = 0;
	    while (index < sb.length) {
	        var ch = sb.charAt(index);
	        var c = 0;
	        	       
	        // the values set here are the index into the entities
	        switch (ch) {
	            case '&' : c = 0; break;
	            case '<' : c = 1; break;
	            case '>' : c = 2; break;
	            case '\"' : c = 3; break;
	            case '\'' : c = 4; break;
	            default : c = -1;
	        }
	        
	        if (c != -1) {
					var newS = "";
					
					if (index > 0)
						newS = sb.substring(0,index);
						
					newS = newS + entities[c];
					
					if (index + 1 < sb.length)
						newS = newS + sb.substring(index + 1);
						
					sb = newS;
			}				
			
			index++;
		}
		
		return sb;
	}
	
/**
 * Delayed callback function so we can correctly read the value of the input
 * field given.
 */
function handleKeyInputCallback(/*DOMnode*/input,/*string*/propertyName)
{
  var value=input.value;
  var inputName="";
  if (propertyName)
    inputName=propertyName;
  else
    inputName=input.getAttribute("name");
  var property=propertyList[inputName];
  if (value || (typeof value=='number' && value==0))
    property.hasValue=true;
  else
    property.hasValue=false;
  if (inputName=="name")
  {
    // special case for name - update table entry for name
    var objectName=document.getElementById("objectname");
    if (value)
    {
      objectName.innerHTML=filterHTML(value);
      var property=getProperty("name",sourceObject);
      property.value=value;
    }
    else
    {
      objectName.innerHTML=initialName;
      var property=getProperty("name",sourceObject);
      property.value="";
    }
  }
  checkRequiredInput();
}

/**
 * Called by Dojo input fields when the value is changed.
 */
function handleInputOnChange(/*multi*/newValue)
{
  var propertyName=this.name;
  var property=propertyList[propertyName];
  if (newValue || (typeof newValue=='number' && newValue==0))
    property.hasValue=true;
  else
    property.hasValue=false;
  checkRequiredInput();
}

/**
 * Handles click events for checkboxes.
 */
function handleCheckbox(/*DOMevent*/e)
{
  var input=null;
  if (e.srcElement)
    input=e.srcElement;
  else
    input=e.target;
  var hiddenId="check"+input.id;
  var realInput=document.getElementById(hiddenId);
  if (realInput)
  {
    if (input.checked)
      realInput.value="true";
    else
      realInput.value="false";
  }
}

/**
 * Checks whether all required input is complete and enables/disables the Finish
 * button as required.
 */
function checkRequiredInput()
{
  checkMandatoryProperties();
  checkValidProperties();
  checkRelationships();
  var finishButton=document.getElementById("finishbutton");
  if (mandatoryComplete && validationComplete && errorBsrUriFinalObj != currentBsrUri)
  {
    if (lastStatusMode!=3)
      showStatus("row0col2",3);    
    lastStatusMode=3;
  } else if (errorBsrUriFinalObj == currentBsrUri) {
    if (lastStatusMode!=1)
	  	showStatus("row0col2", 1);
  	lastStatusMode=1;
  } else if (lastStatusMode!=2) {
  	showStatus("row0col2", 2);
  	lastStatusMode=2;
  }
  if (mandatoryComplete && validationComplete && relationshipsComplete)
    finishButton.disabled=false;
  else
    finishButton.disabled=true;
}

/**
 * Checks whether the mandatory properties are correctly filled in and adjusts the
 * screen feedback accordingly.
 */
function checkMandatoryProperties()
{
  var anyBlank=false;
  var nameBlank=false;
  for (var key in propertyList)
  {
    var property=propertyList[key];
    if (property.mandatoryValue && property.displayAs!="BOOLEAN")
    {
      if (!property.hasValue)
      {
        anyBlank=true;
        if (key=="name")
          nameBlank=true;
      }
    }
  } // end for
  if (anyBlank)
  {
    // only switch status when it's changed
    if (nameBlank && errorBsrUriFinalObj != currentBsrUri)
    {
      if (lastStatusMode!=2)
         showStatus("row0col2",2);  // show not defined if name not yet given
      lastStatusMode=2;
    }
    else
    {
      if (lastStatusMode!=1)
         showStatus("row0col2",1);  // error for all other cases
      lastStatusMode=1;
    }
    mandatoryComplete=false;
  }
  else
    mandatoryComplete=true;
}

/**
 * Checks whether the Dojo properties are valid and adjusts the
 * screen feedback accordingly.
 */
function checkValidProperties()
{
  var anyNotValid=false;
  for (var key in propertyList)
  {
    var property=propertyList[key];
    var globalName="djif"+key;
    if (window[globalName])
    {
      var dojoInput=window[globalName];
      if (!dojoInput.isValid())
        anyNotValid=true;
    }
  } // end for
  if (anyNotValid)
  {
    if (lastStatusMode!=1)
       showStatus("row0col2",1);  // error for all other cases
    lastStatusMode=1;
    validationComplete=false;
  }
  else
    validationComplete=true;
}

/**
 * Checks whether required relationships have been correctly specified.
 */
function checkRelationships()
{
  var anyNotValid=false;
  // go through all the required rels
  for (var i=0;i<requiredRelList.length;i++)
  {
    var requiredRel=requiredRelList[i];
    var relationshipName=requiredRel.NCName;
    var relationship=getRelationship(relationshipName, sourceObject);
    if (relationship)
    {
      // only need to be checking BM rels
      var relationshipInfo=getRelationshipInfo(relationshipName);
      if (relationshipInfo)
      {
        // business model relationship - use the constraints to determine what to do
        var constraints=relationshipInfo.constraints;
        var targetCount=relationship.targets.length;
        var minSatisfied=false;
        var maxSatisfied=false;
        if (targetCount>=constraints.minCardinality)
          minSatisfied=true;
        if (targetCount<=constraints.maxCardinality || constraints.maxCardinality==-1)
          maxSatisfied=true;
        if (!minSatisfied || !maxSatisfied)
        {
          // not yet complying with constraints
          anyNotValid=true;
        }
        var rowId=relationship.rowId;
        if (rowId)
        {
          var row=document.getElementById(rowId);
          if (row)
          {
            // update the target status
            var statusCell=row.cells[2];
            if (statusCell.id)
            {
              if (minSatisfied && maxSatisfied)
              {
                if (constraints.maxCardinality==-1 || targetCount<constraints.maxCardinality)
                  showStatus(statusCell.id,4);
                else if (constraints.minCardinality>0) {
                  if (rowTargetLookup[rowId].bsrUri.charAt(0) == "_")
	                showStatus(statusCell.id,3);
	              else
	                showStatus(statusCell.id,3,"existing");
                }
              }
              else
                showStatus(statusCell.id,2); // not defined
            }
          }
        }
      }
    }
  } // end for
  if (anyNotValid)
    relationshipsComplete=false;
  else
    relationshipsComplete=true;
}

/**
 * Takes any pre-existing added relationships and targets (of either required
 * or added rels) and places them on screen.
 */
function displayRelationshipData()
{
  // go through each required relationship and add its targets (if any)
  for (var i=0;i<requiredRelList.length;i++)
  {
    var rel=requiredRelList[i];
    var ncName=rel.NCName;
    var targets=requiredRelTargets[ncName];
    if (targets)
    {
      // show each target
      for (var j=0;j<targets.length;j++)
      {
        var targetObject=targets[j];
        var targetBsrUri=getBsrUri(targetObject);
        // show the target object on screen
        displayTarget(ncName,targetObject,targetBsrUri);
      } // end for
    }
  } // end for
  // go through each added relationship and add it and its targets (if any)
  for (var i=0;i<addedRelList.length;i++)
  {
    var rel=addedRelList[i];
    var ncName=rel.name;
    // show the relationship on screen
    displayRelationship(ncName);
    var targets=rel.targets;
    if (targets)
    {
      // show each target
      for (var j=0;j<targets.length;j++)
      {
        var targetObject=targets[j];
        var targetBsrUri=getBsrUri(targetObject);
        // show the target object on screen
        displayTarget(ncName,targetObject,targetBsrUri);
      } // end for
    }
  } // end for
}

/**
 * Shows icon and text status in the given table cell with the required settings.
 */
function showStatus(/*string*/cellId,/*int*/mode,/*string*/variant)
{
  var cell=document.getElementById(cellId);
  if (cell)
  {
    switch (mode)
    {
      case 1: /*error*/
        var html="<img src=\"theme/"+currentTheme+"/images/sm_error.gif\" alt=\""+TRANS_STATUS_IMAGE_ALT_ERROR+"\"/>&nbsp;"+TRANS_STATUS_ERROR;
        cell.innerHTML=html;
        break;
      case 2: /*warning*/
        var html="<img src=\"theme/"+currentTheme+"/images/sm_warn.gif\" alt=\""+TRANS_STATUS_IMAGE_ALT_WARNING+"\"/>&nbsp;"+TRANS_STATUS_NOT_DEFINED;
        cell.innerHTML=html;
        break;
      case 3: /*ok*/
        var value=TRANS_STATUS_NEW;
        if (variant=="existing")
          value=TRANS_STATUS_EXISTING;
        var html="<img src=\"theme/"+currentTheme+"/images/sm_greencheck.gif\" alt=\"\"/>&nbsp;"+value;
        cell.innerHTML=html;
        break;
      case 4: /*blank*/
        cell.innerHTML="&nbsp;";
        break;
    } // end switch
  }
}

/**
 * Makes sure the top table scrolls correctly
 */
function adjustTableSize()
{
  var table=document.getElementById('relationshiptable');
  if (table)
  {
    var tbody=table.getElementsByTagName("tbody");
    if (tbody)
    {
      if (document.defaultView)
      {
        var style=document.defaultView.getComputedStyle(tbody[0], null).getPropertyValue("height");
        style=style.substring(0,style.length-2);
        if (style>170)
          tbody[0].style.height='170px';
      }
    }
    adjustScrollbox();
  }
  adjustTargetCells();
}

/**
 * Saves what the user has typed into the object properties.
 */
function saveObjectProperties(/*function*/funcToRun)
{
  if (currentView==1)
  {
    // only fetch data if the main view is showing
    var callbackFunction=function(response) {
      processingAjax=false;
      stopProgress();
      if (response.error)
      {
        displayError(response.error.message,response.error.error);
      }
      else
      {
        // do something after AJAX call complete
        if (funcToRun)
          funcToRun.call(null);
      }
    }
    if (!processingAjax)
    {
      // build up the transmission dta
      var data=fetchUserInput();
      // make the primary RPC call to save the data
      processingAjax=true;
      var deferred=goService.saveGenericObjectData(getGOBeanUri(),getUserCorrelator(),data);
      deferred.addCallback(callbackFunction);
      deferred.addErrback(processRPCException);
      // progress
      progressTabTitle = null;
      startProgressFunction(PROGRESS_DIV_ID, TRANS_PROCESSING, displayProgressPanel);
    }
  }
  else
  {
    // still need to chain to the follow-on function if given
    if (funcToRun)
      funcToRun.call(null);
  }
}

/**
 * Reads all the input fields.
 */
function fetchUserInput()
{
  var data={"properties": new Array()};
  // get the list of fields first (can be input, textarea and select)
  var inputs=getPropertyInputs();
  if (inputs)
  {
    for (var i=0;i<inputs.length;i++)
    {
      var input=inputs[i];
      // name attribute will be the var NCName
      var name=input.getAttribute("name");
      if (name)
      {
        var value="";
        var type=input.type.toLowerCase();
        type=type.substr(0,6);
        if (type=="select")
        {
          var index=input.selectedIndex;
          if (index!=-1)
            value=input.options[index].value;
        }
        else
          value=input.value;
        data.properties[data.properties.length]={"name": name, "value": value};
      }
    } // end for
  }
  return(data);
}

/**
 * Add a new relationship to the current object.
 */
function addRelationship()
{
  if (!processingAjax)
  {
    // must save user input first
    saveObjectProperties(addRelationshipInit);
  }
}

/**
 * Rebuilds the page ready for adding a relationship.
 */
function addRelationshipInit()
{
  selectRow("row0");
  switchToView(2);
}

/**
 * Add a target to the given relationship to the current object.
 */
function addTarget(/*string*/relationshipName)
{
  if (!processingAjax)
  {
    // must save user input first
    saveObjectProperties(function(){addTargetInit(relationshipName);});
  }
}

/**
 * Rebuilds the page ready for adding a relationship target.
 */
function addTargetInit(/*string*/relationshipName)
{
  switchToView(3,relationshipName);
}

/**
 * Edit the target on the given relationship.
 */
function editTarget(/*string*/relationshipName,/*string*/bsrUri)
{
  if (!processingAjax)
  {
	  var hidden=document.getElementById("submitactionhidden");
	  if (hidden)
	    hidden.value="edit";

	  hidden=document.getElementById("relnamehidden");
	  if (hidden)
	    hidden.value=relationshipName;

	  hidden=document.getElementById("resumeToInternalUri");
	  if (hidden)
	    hidden.value=bsrUri;
	    
	  saveObjectProperties(submitForm);
	  
  }
}

/**
 * Change a target on the given relationship.
 */
function changeTarget(/*string*/relationshipName,/*string*/bsrUri)
{
  if (!processingAjax)
  {
    // must save user input first
    saveObjectProperties(function(){changeTargetInit(relationshipName,bsrUri);});
  }
}

/**
 * Rebuilds the page ready for changing a relationship target.
 */
function changeTargetInit(/*string*/relationshipName,/*string*/bsrUri)
{
  switchToView(5,relationshipName,bsrUri);
}

/**
 * Show the progress panel
 */
function displayProgressPanel() {
	switchToView(6, progressTabTitle, null);
}

/**
 * Switches the page to a different detail section view.
 */
function switchToView(/*int*/view,/*string*/parmOne,/*string*/parmTwo)
{
  hideMessages();
  var contentTable=document.getElementById("detailcontenttable");
  var linksRow=document.getElementById("editlinks");
  var contentTableCell=document.getElementById("contenttablecell2");
  var propertiesSection=document.getElementById("editpropertiessection");
  var widgetDiv=document.getElementById("widgetdiv");
  var propertiesStore=document.getElementById("propertiesstore");
  var relationshipSection=document.getElementById("relationshipsection");
  var tabName=document.getElementById("tabnametext");
  if (currentView!=view || view==3 || view==4 || view==5 || view==6)
  {
    // clear out any old widget
    if (currentWidget)
    {
      currentWidget.destroyRecursive();
      currentWidget=null;
    }
    switch (view)
    {
      case 1:  // edit main object
        // clear widget div, re-instate table
        tabName.innerHTML=TRANS_DETAILS;
        widgetDiv.innerHtml="";
        clearDomNode(contentTableCell);
        propertiesStore.removeChild(propertiesSection);
        propertiesSection.style.display="block";
        contentTableCell.appendChild(propertiesSection);
        if (relationshipSection)
          relationshipSection.style.display="block";
        linksRow.style.display="block";
        contentTable.style.display="block";
        previousView=currentView;
        currentView=1;
        break;
      case 2: // add relationship
        // clear widget div, store properties section, show widget inside table cell
        tabName.innerHTML=TRANS_ADD_RELATIONSHIP;
        widgetDiv.innerHtml="";
        if (currentView==1)
        {
          contentTableCell.removeChild(propertiesSection);
          propertiesSection.style.display="none";
          propertiesStore.appendChild(propertiesSection);
        }
        linksRow.style.display="none";
        if (relationshipSection)
          relationshipSection.style.display="none";
        contentTable.style.display="block";
        // create an anchor point for the widget
        var widgetAnchor=document.createElement("div");
        contentTableCell.appendChild(widgetAnchor);
        // create the widget, setup args first
        var args = {
          contextRoot: contextRoot,
          sourceObjectType: "GenericObject",
          sourceObject: sourceObject,
          optionalRelationships: optionalRelList
        };
        var addRelationshipWidget=new com.ibm.sr.widgets.AddRelationship(args, widgetAnchor);
        addRelationshipWidget.startup();
        // hook up handlers
        addRelationshipWidget.onAddButtonClicked=commitAddRelationship;
        addRelationshipWidget.onCancelButtonClicked=cancelAddRelationship;
        currentWidget=addRelationshipWidget;
        previousView=currentView;
        currentView=2;
        break;
      case 3: // add target
        // clear widget div, store properties section, show widget inside table cell
        var relationshipName=parmOne;
        tabName.innerHTML=TRANS_ADD_TARGET;
        widgetDiv.innerHtml="";
        if (currentView==1)
        {
          contentTableCell.removeChild(propertiesSection);
          propertiesSection.style.display="none";
          propertiesStore.appendChild(propertiesSection);
        }
        linksRow.style.display="none";
        if (relationshipSection)
          relationshipSection.style.display="none";
        contentTable.style.display="block";
        clearDomNode(contentTableCell);
        // create an anchor point for the widget
        var widgetAnchor=document.createElement("div");
        contentTableCell.appendChild(widgetAnchor);
        // create the widget
        if (relationshipName)
        {
          // select the row where the link is
          var relationship=getRelationship(relationshipName,sourceObject);
          var rowId=relationship.rowId;
          selectRow(rowId);
          // build the args first
          var args=resolveRelTargetArgs(relationshipName);
          // now ready to create the widget
          var addOrLoadRelationshipTargetWidget=new com.ibm.sr.widgets.AddLoadOrCreateRelationshipTarget(args, widgetAnchor);
          addOrLoadRelationshipTargetWidget.startup();
          // hook up handlers
          addOrLoadRelationshipTargetWidget.onSearchButtonClicked=searchAddRelationshipTarget;
          addOrLoadRelationshipTargetWidget.onAddButtonClicked=commitAddRelationshipTarget;
          addOrLoadRelationshipTargetWidget.onLoadDocumentButtonClicked=loadDocument;
          addOrLoadRelationshipTargetWidget.onCreateEntityButtonClicked=createEntity;          
          addOrLoadRelationshipTargetWidget.onCancelButtonClicked=cancelAddRelationshipTarget;
          currentWidget=addOrLoadRelationshipTargetWidget;
        }
        previousView=currentView;
        currentView=3;
        break;
      case 4: // show target details
        // clear widget div, store properties section, show widget inside widget div
        var bsrUri=parmOne;
        tabName.innerHTML=TRANS_DETAILS;
        widgetDiv.innerHtml="";
        if (currentView==1)
        {
          // only move the properties section if not already done
          if (contentTableCell.hasChildNodes())
          {
            contentTableCell.removeChild(propertiesSection);
            propertiesSection.style.display="none";
            propertiesStore.appendChild(propertiesSection);
          }
        }
        linksRow.style.display="none";
        if (relationshipSection)
          relationshipSection.style.display="none";
        contentTable.style.display="none";
        clearDomNode(contentTableCell);
        // fetch all the information we need for the detail view
        var targetObject=targetCache[bsrUri];
        // tab definition will be in the cache
        var tabDefinition=tabDefinitionCache[targetObject.sdoType];
        var businessModelInfo=null;
        if (targetObject.sdoType=="GenericObject")
        {
          var primaryType=getPrimaryType(targetObject);
          // The busines model info object will be in the cache
          if (primaryType)
            businessModelInfo = businessModelInfoCache[primaryType];
        }
        // fetch the general properties info
        var targetTypeGeneralProperties = null;
        if (tabDefinition.generalProperties && tabDefinition.generalProperties.length > 0)
          targetTypeGeneralProperties = tabDefinition.generalProperties[0].properties;
        // fetch the business model info
        var modelledPropertyInfos = null;
        var optionalPropertyInfos = null;
        var modelledRelationshipInfos = null;
        var optionalRelationshipInfos = null;
        if (businessModelInfo)
        {
          modelledPropertyInfos = businessModelInfo.modelPropertyInfos,
          optionalPropertyInfos = businessModelInfo.optionalPropertyInfos,
          modelledRelationshipInfos = businessModelInfo.modelRelationshipInfos;
          optionalRelationshipInfos = businessModelInfo.optionalRelationshipInfos;
        }
        // create an anchor point for the widget
        var widgetAnchor=document.createElement("div");
        widgetDiv.appendChild(widgetAnchor);
        // now generate the args ready for the widget
        var args = {
          wsrrObject: targetObject,
          generalProperties: targetTypeGeneralProperties,
          modelledPropertyInfos: modelledPropertyInfos, 
          optionalPropertyInfos: optionalPropertyInfos,
          modelledRelationshipInfos: modelledRelationshipInfos,
          optionalRelationshipInfos: optionalRelationshipInfos,
          themeName: currentTheme
        };
        // show the widget
        var detailViewWidget=new com.ibm.sr.widgets.DetailView(args,widgetAnchor);
        detailViewWidget.startup();
        currentWidget=detailViewWidget;
        previousView=currentView;
        currentView=4;
        break;
      case 5: // change target
        // clear widget div, store properties section, show widget inside table cell
        var relationshipName=parmOne;
        var targetBsrUri=parmTwo;
        tabName.innerHTML=TRANS_CHANGE_TARGET;
        widgetDiv.innerHtml="";
        if (currentView==1)
        {
          contentTableCell.removeChild(propertiesSection);
          propertiesSection.style.display="none";
          propertiesStore.appendChild(propertiesSection);
        }
        linksRow.style.display="none";
        if (relationshipSection)
          relationshipSection.style.display="none";
        contentTable.style.display="block";
        clearDomNode(contentTableCell);
        // create an anchor point for the widget
        var widgetAnchor=document.createElement("div");
        contentTableCell.appendChild(widgetAnchor);
        // create the widget
        if (relationshipName)
        {
          var lookup=rowTargetLookup[relationshipName + "#" + targetBsrUri];
          if (lookup)
          {
            // select the row where the link is
            var rowId=lookup.rowId;
            selectRow(rowId);
          }
          // build the args first
          var args=resolveRelTargetArgs(relationshipName);
          args["targetBsrUri"]=targetBsrUri;
          // now ready to create the widget
          var addOrLoadRelationshipTargetWidget=new com.ibm.sr.widgets.AddLoadOrCreateRelationshipTarget(args, widgetAnchor);
          addOrLoadRelationshipTargetWidget.startup();
          // hook up handlers
          addOrLoadRelationshipTargetWidget.onSearchButtonClicked=searchAddRelationshipTarget;
          addOrLoadRelationshipTargetWidget.onChangeButtonClicked=commitChangeRelationshipTarget;
          addOrLoadRelationshipTargetWidget.onLoadDocumentButtonClicked=loadDocument;
          addOrLoadRelationshipTargetWidget.onCreateEntityButtonClicked=createEntity;
          addOrLoadRelationshipTargetWidget.onCancelButtonClicked=cancelAddRelationshipTarget;
          currentWidget=addOrLoadRelationshipTargetWidget;
        }
        previousView=currentView;
        currentView=5;
        break;
        
      case 6: // progress panel
        // clear widget div, store properties section, show widget inside table cell
        var tabText=parmOne;
        if(tabText != null) {
	        tabName.innerHTML=tabText;
	    }
        widgetDiv.innerHtml="";
        if (currentView==1)
        {
          contentTableCell.removeChild(propertiesSection);
          propertiesSection.style.display="none";
          propertiesStore.appendChild(propertiesSection);
        }
        linksRow.style.display="none";
        if (relationshipSection)
          relationshipSection.style.display="none";
        contentTable.style.display="block";
        clearDomNode(contentTableCell);
        // create an anchor point for the widget
        var widgetAnchor=document.createElement("div");
        contentTableCell.appendChild(widgetAnchor);
    	var args = {};
	    var progressPanelWidget = new com.ibm.sr.widgets.ProgressPanel(args, widgetAnchor);
	    progressPanelWidget.startup();
	    currentWidget = progressPanelWidget;
        previousView=currentView;
        currentView=6;
        break;
    } // end switch
  }
}

/**
 * Generates the arguments object for the add relationship target widget.
 */
function resolveRelTargetArgs(/*string*/relationshipName)
{
  var displaySavedSearches = false;
  var relationshipDisplayName=relationshipName;
  var actualSearchTypes=searchTypes;
  // check if we have a match against a modelled rel (required or optional)
  var relationshipInfo=getRelationshipInfo(relationshipName);
  if (relationshipInfo)
  {
    // found a business model rel
    // can now pick off the real display name
    relationshipDisplayName=relationshipInfo.name;
    // Retrieve the OWL uris of target types for this relationship
    var targetTypeUris=relationshipInfo.urisForTargets;
    if (targetTypeUris && targetTypeUris.length>0)
    {
      /*
       * Iterate over the list of targets for the relationship and build
       * up a JSON representation of an AutoSuggestSearchType for each.
       * For each target type uri, we need to retrieve the display name to
       * render in the entity type drop down and the actual type that the
       * auto-suggest widget will search by.  If the acutal type is a
       * business model representation of an internal SDO type, we need to
       * retrieve the actual SDO type name.
       */
      actualSearchTypes=new Array();
      for (var i=0;i<targetTypeUris.length;i++)
      {
        var targetTypeUri=targetTypeUris[i];
        var displayName=getTargetTypeName(targetTypeUri);
        var actualType=getSDOTypeFromTargetUri(targetTypeUri);
        if (!actualType)
          actualType=targetTypeUri;
        /*
         * Now we need to fix up the label for the BaseObject type
         * because the label in the system OWL file is not correct.
         */
        if (actualType=="BaseObject")
        {
          displayName=getSearchTypeDisplayName(actualType);
          displaySavedSearches=true;
        }
        // Now create the AutoSuggestSearchType object
        var searchType=new Object();
        searchType.displayName=displayName;
        searchType.id=actualType;
        actualSearchTypes.push(searchType);
      } // end for
    }
    else
      displaySavedSearches=true;
  }
  else
    displaySavedSearches=true;
  // now build the final args list
  var args = {
    relationshipName: relationshipName,
    displayName: relationshipDisplayName,
    contextRoot: contextRoot,
    searchTypes: actualSearchTypes,
    displaySavedSearches: displaySavedSearches,
    savedSearches: savedSearches,
    allowCreate: true
  };
  return(args);
}

/**
 * Find the relationship info matching the given relationship name.
 */
function getRelationshipInfo(/*string*/relationshipName)
{
  var relationshipInfo=null;
  // check the required rels first
  for (var i=0;i<requiredRelList.length;i++)
  {
    if (requiredRelList[i].NCName==relationshipName)
    {
      relationshipInfo=requiredRelList[i];
      break;
    }
  } // end for
  if (!relationshipInfo)
  {
    // didnt find a required rel, check optional rels
    for (var i=0;i<optionalRelList.length;i++)
    {
      if (optionalRelList[i].NCName==relationshipName)
      {
        relationshipInfo=optionalRelList[i];
        break;
      }
    } // end for
  }
  return(relationshipInfo);
}

/**
 * Get the display name for the target type URI.
 */
function getTargetTypeName(/*String*/targetTypeUri)
{
  var targetTypeName=null;
  // Make sure that the OWL uri passed is valid  
  if (targetTypeUri)
  {
    var targetModel=targetModelInfos[targetTypeUri];
    if (targetModel)
      targetTypeName=targetModel.name;
  }
  return(targetTypeName);
}

/**
 * The getSearchTypeDisplayName function returns the display name for the
 * specified search type.  This function should be used to determine the
 * display name for the BaseObject type, since the label contained
 * in the relevant OWL file in WSRR is not correct.
 */
function getSearchTypeDisplayName(/*String*/sdoType)
{
  var displayName=null;
  // Make sure that the SDO type passed is valid  
  if (sdoType)
  {
    // Find the search type with a matching id
    if (searchTypes && searchTypes.length>0)
    {
      for (var i=0;i<searchTypes.length;i++)
      {
        if (searchTypes[i].id==sdoType)
        {
          displayName=searchTypes[i].displayName;
          break;
        }
      } // end for
    }
  }
  return(displayName);
}

/**
 * Resets the view to show the source object being created.
 */
function viewSourceObject()
{
  selectRow("row0");
  lastTargetViewed = "row0";
  fixScrollbarPosition("row0");
  switchToView(1);
}

/**
 * Selects the row in the top table.
 */
function selectRow(/*string*/rowId)
{
  var oldRow=document.getElementById(selectedRow);
  if (oldRow)
    oldRow.className="notselected";
  var row=document.getElementById(rowId);
  if (row) {
    row.className="selected";
    
    /*
     * We also want to make sure that the selected row is visible in the
     * viewport for the table (if the vertical scrollbar is displayed).
     * The styles for IE and FF are subtly different, causing the DOM
     * element that contains the scrollable viewport to be different on
     * IE than on FF.  This also results in the header row being included
     * in the scrollable section on IE and not on FF.
     */
    var scrollableSection = dojo.byId("scrollreltable"); // DIV on IE
    var headerRowHeight = 0;
    if (dojo.isFF) {
      var tableBodies = scrollableSection.getElementsByTagName("tbody");
      scrollableSection = tableBodies[0]; // TBODY on FF
    } else {
      headerRowHeight = row.offsetHeight;
    }

    // Calculate the y position of the top of the selected row
    var rowTop = row.offsetTop - row.offsetHeight + headerRowHeight;

    // Retrieve the current location of the top of the scrollbar
    var scrollTop = scrollableSection.scrollTop;

    /*
     * Now workout if the newly selected row lies outside the visible area
     * of the viewport and, if it does, move the scrollbar so that the row
     * is visible.
     */
    if (rowTop < scrollTop || rowTop >= (scrollTop + 170 - row.offsetHeight)) {
      scrollableSection.scrollTop = rowTop;
    }
  }
  selectedRow=rowId;
}

/**
 * Handles user pressed Add from the add relationship panel.
 */
function commitAddRelationship(/*DomEvent*/event)
{
  if (!processingAjax)
  {
    // Retrieve the relationship name that has been specified
    var relationshipName=this.getRelationshipName();
  	// Note: the callback function isn't executed yet
    var callbackFunction=function(response) {
      processingAjax=false;
      stopProgress();
    	if (response.error)
    	{
  	    displayError(response.error.message,response.error.error);
    	}
    	else
    	{
        hideMessages();
        // display the relationship on screen
        displayRelationship(relationshipName)
        // TODO: add rel to source object view (1)
        // switch back to the main view
        viewSourceObject();
    	}
  	}
    // make the RPC call to log the relationship add
    processingAjax=true;
    var deferred=goService.addGenericObjectRelationship(getGOBeanUri(),getUserCorrelator(),relationshipName);
    deferred.addCallback(callbackFunction);
    deferred.addErrback(processRPCException);
    // progress
    progressTabTitle = null;
    startProgressFunction(PROGRESS_DIV_ID, TRANS_PROCESSING, displayProgressPanel);
  }
}

/**
 * Displays the new relationship and updates internal state.
 */
function displayRelationship(/*string*/relationshipName)
{
  // add the new relationship to the dummy source object
  var newRelationship=new Object();
  newRelationship.name=relationshipName;
  newRelationship.targets=new Array();
  newRelationship.modelled=false;
  sourceObject.relationships.push(newRelationship);
  // see if we chose an optional relationship
  var optionalRel=matchOptionalRelationship(relationshipName);
  // update the table
  var row=addTableRow();
  if (row)
  {
    // store row x-ref
    newRelationship.rowId=row.id;
    newRelationship.targetRows=[];
    // create the relationship cell
    var nameCell=row.insertCell(0);
    nameCell.className="bottomleft";
    var img=document.createElement("img");
    img.setAttribute("src","theme/"+currentTheme+"/images/"+rtlMode+"arrow_down_right.gif");
    img.setAttribute("alt","");
    nameCell.appendChild(img);
    // work out the correct display name
    var displayName=relationshipName;
    if (optionalRel)
      displayName=optionalRel.name;
    var divider="\u202a|\u202c";
    if (isRTL)
      divider="\u202b|\u202c";
    var textNode=document.createTextNode(displayName+"\u00a0"+divider+"\u00a0");
    nameCell.appendChild(textNode);
    var removeSpan = document.createElement("span");
    removeSpan.setAttribute("id", relationshipName + "#Remove");
    nameCell.appendChild(removeSpan);
    var removeAnchor=document.createElement("a");
    removeAnchor.setAttribute("href","javascript:removeRelationship('"+relationshipName+"')");
    removeAnchor.setAttribute("title",TRANS_REMOVE_REL_TITLE);
    removeAnchor.appendChild(document.createTextNode(TRANS_REMOVE));
    removeSpan.appendChild(removeAnchor);
    // create the targets cell
    var targetsCell=row.insertCell(1);
    targetsCell.className="bottomleft";
    // we add the target type name if available
    var targetText=TRANS_ADD_TARGET;
    if (optionalRel)
    {
      var targetType=optionalRel.targetTypeName;
      if (targetType)
        targetText=resolveMessageInserts(TRANS_ADD,targetType);
    }
    var addTargetAnchor=document.createElement("a");
    addTargetAnchor.setAttribute("href","javascript:addTarget('"+relationshipName+"')");
    addTargetAnchor.setAttribute("title",TRANS_ADD_TARGET_TITLE);
    addTargetAnchor.appendChild(document.createTextNode(targetText));
    targetsCell.appendChild(addTargetAnchor);
    // create the status cell
    var statusCell=row.insertCell(2);
    statusCell.className="nowrap";
    statusCell.appendChild(document.createTextNode("\u00a0"));
    // reflow the table
    adjustTableSize();
  }
}

/**
 * Handles user pressed Cancel from the add relationship panel.
 */
function cancelAddRelationship(/*DomEvent*/event)
{
  // switch back to the previous view
  if (previousView==1) {
    viewSourceObject()
    fixScrollbarPosition("row0");
 } else {
    if (previousView == 4) {
	    fixScrollbarPosition(lastTargetViewed);
	    var lookup=rowTargetLookup[lastTargetViewed];
          if (lookup)
          {
            // select the row where the link is
            var rowId=lookup.rowId;
            selectRow(rowId);
          }
    }
    switchToView(previousView, lastTargetViewed);
    
  }}

/**
 * Removes the given relationship from the object.
 */
function removeRelationship(/*string*/relationshipName)
{
  if (!processingAjax)
  {
    var relationship=getRelationship(relationshipName,sourceObject);
    var rowId=relationship.rowId;
    // Note: the callback function isn't executed yet
    var callbackFunction=function(response) {
      processingAjax=false;
      stopProgress();
      if (response.error)
      {
        displayError(response.error.message,response.error.error);
      }
      else
      {
        // remove the row and any dependent target rows
        removeTableRow(rowId);
        // TODO: remove rel from source object view (1)
        // also need to remove the relationship from our dummy source object
        for (var i=0;i<sourceObject.relationships.length;i++)
        {
          if (sourceObject.relationships[i].name==relationshipName)
          {
            // Remove the element from the array of user defined relationships    
            var modifiedArray=removeElementAtIndex(sourceObject.relationships, i);
            // Overwrite the user defined relationships array 
            if (modifiedArray)
              sourceObject.relationships=modifiedArray;
            break;
          }
        } // end for
        // reflow the table
        adjustTableSize();
        // switch back to the main view
        viewSourceObject();
      }
    }
    // make the RPC call to log the relationship remove
    processingAjax=true;
    var deferred=goService.removeGenericObjectRelationship(getGOBeanUri(),getUserCorrelator(),relationshipName);
    deferred.addCallback(callbackFunction);
    deferred.addErrback(processRPCException);
    // progress
    startProgress(relationshipName + "#Remove", TRANS_REMOVING);
  }
}

/**
 * Handles user pressed Add from the add relationship target panel.
 */
function commitAddRelationshipTarget(/*DomEvent*/event)
{
  if (!processingAjax)
  {
    // Retrieve the input the user gave us
    var relationshipName=this.getRelationshipName();
    var targetName=this.getTargetEntityName();
    var targetType=this.getTargetEntityType();
    var targetBsrUri=this.getSuggestTargetBsrUri();
    // Note: the callback function isn't executed yet
    var callbackFunction=function(response) {
      processingAjax=false;
      stopProgress();
      if (response.error)
      {
        displayError(response.error.message,response.error.error);
      }
      else
      {
        hideMessages();
        // fetch the response object
        var targetObject=response.data[0];
        // show the target object on screen
        displayTarget(relationshipName,targetObject,targetBsrUri);
        checkRequiredInput();
        // Now view the details of the new target
        viewTargetDetails(relationshipName + "#" + targetBsrUri);
      }
    }
    // make the RPC call to log the relationship target add
    processingAjax=true;
    var deferred=goService.addGenericObjectRelationshipTarget(getGOBeanUri(),getUserCorrelator(),relationshipName,targetBsrUri);
    deferred.addCallback(callbackFunction);
    deferred.addErrback(processRPCException);
    // progress
    progressTabTitle = null;
    startProgressFunction(PROGRESS_DIV_ID, TRANS_PROCESSING, displayProgressPanel);
  }
}

/**
 * Displays the new relationship target and updates internal state.
 */
function displayTarget(/*string*/relationshipName,/*WSRRObject*/targetObject,/*string*/targetBsrUri)
{
  // add into dummy source object
  var relationship=getRelationship(relationshipName, sourceObject);
  if (relationship)
  {
    relationship.targets.push(targetObject);
    var rowId=relationship.rowId;
    if (rowId)
    {
      var row=document.getElementById(rowId);
      if (row)
      {
        // Need to work out where to place the target object on screen
        // and which links should be shown.
        var addTargetAllowed=false;
        var removeTargetAllowed=false;
        var relationshipInfo=getRelationshipInfo(relationshipName);
        if (relationshipInfo)
        {
          // business model relationship - use the constraints to determine what to do
          var constraints=relationshipInfo.constraints;
          if (constraints.maxCardinality==-1 || relationship.targets.length<constraints.maxCardinality)
            addTargetAllowed=true;
          if (constraints.minCardinality==0 || relationship.targets.length>constraints.minCardinality)
            removeTargetAllowed=true;
          if (!addTargetAllowed)
          {
            var targetsCell=row.cells[1];
            // keep track of where things are
            rowTargetLookup[relationshipName + "#" + targetBsrUri]={"relationship":relationship,"rowId":rowId};
            rowTargetLookup[rowId]={"relationship":relationship,"bsrUri":targetBsrUri};
            // Clear any contents in the cell
            targetsCell.innerHTML="";
            // Construct the new contents for the cell

            var divider="\u202a|\u202c";
            if (isRTL)
              divider="\u202b|\u202c";

		    var isGO = targetBsrUri.charAt(0) == "_" && targetBsrUri.charAt(1) != "_";
		    
		    if (isGO && currentBsrUri == targetBsrUri) {
			    var targetNameSpan = document.createElement("span");
		    	targetNameSpan.appendChild(document.createTextNode(getObjectName(targetObject)));
		    	targetsCell.appendChild(targetNameSpan);
		    } else {                        
	            var targetNameAnchor = document.createElement("a");
	            targetNameAnchor.setAttribute("href", "javascript:viewTargetDetails('"+relationshipName + "#" + targetBsrUri+"')");
	            targetNameAnchor.setAttribute("title", TRANS_SHOW_TARGET_TITLE);
	            var targetNameSpan = document.createElement("span");
	            targetNameSpan.className = "bold";
	            targetNameSpan.appendChild(document.createTextNode(getObjectName(targetObject)));
	            targetNameAnchor.appendChild(targetNameSpan);
	            targetsCell.appendChild(targetNameAnchor);                            
	            targetsCell.appendChild(document.createTextNode("\u00a0"+divider+"\u00a0"));
            }
            //if the bsrUri starts with a single _ then we can edit this child
            if (isGO && bsrUrisMap[targetBsrUri] != true) {
	            var editTargetAnchor = document.createElement("a");
	            editTargetAnchor.setAttribute("href", "javascript:editTarget('"+relationshipName+"','"+targetBsrUri+"')");
	            editTargetAnchor.setAttribute("title", TRANS_EDIT_TARGET_TITLE);
	            editTargetAnchor.appendChild(document.createTextNode(TRANS_EDIT));
	            targetsCell.appendChild(editTargetAnchor);
	            targetsCell.appendChild(document.createTextNode("\u00a0"+divider+"\u00a0"));	            
            }
            
            var changeTargetAnchor = document.createElement("a");
            changeTargetAnchor.setAttribute("href", "javascript:changeTarget('"+relationshipName+"','"+targetBsrUri+"')");
            changeTargetAnchor.setAttribute("title", TRANS_CHANGE_TARGET_TITLE);
            changeTargetAnchor.appendChild(document.createTextNode(TRANS_CHANGE));
            targetsCell.appendChild(changeTargetAnchor);
                        
            if (removeTargetAllowed)
            {
              targetsCell.appendChild(document.createTextNode("\u00a0"+divider+"\u00a0"));
              var removeSpan = document.createElement("span");
              removeSpan.setAttribute("id", relationshipName + "#" + targetBsrUri + "#Remove");
                targetsCell.appendChild(removeSpan);
              var removeTargetAnchor = document.createElement("a");
              removeTargetAnchor.setAttribute("href", "javascript:removeTarget('"+relationshipName+"','"+targetBsrUri+"')");
              removeTargetAnchor.setAttribute("title", TRANS_REMOVE_TARGET_TITLE);
              removeTargetAnchor.appendChild(document.createTextNode(TRANS_REMOVE));
              removeSpan.appendChild(removeTargetAnchor);
            }
            // update the target status
            var statusCell=row.cells[2];
            if (statusCell.id) {
            	if (errorBsrUrisMap[targetBsrUri] == true)
            		showStatus(statusCell.id,1);
            	else if (targetBsrUri.charAt(0) == "_")
	            	showStatus(statusCell.id,3);
	            else
   	          		showStatus(statusCell.id,3,"existing");
            }			    	          	
          }
        }
        else
        {
          // regular user-defined relationship - permit all
          addTargetAllowed = true;
          removeTargetAllowed = true;
        }
        // add a row to the table if we can still add more targets
        if (addTargetAllowed)
        {
          var targetRows=relationship.targetRows;
          if (targetRows)
          {
            var lastRow=null;
            if (targetRows.length>0)
            {
              var lastRowId=targetRows[targetRows.length-1];
              lastRow=document.getElementById(lastRowId);
            }
            else
              lastRow=row;
            // Create a row for the target object
            var row=createTargetRow(lastRow,relationshipName,targetObject,removeTargetAllowed);
            // keep track of where things are
            rowTargetLookup[relationshipName + "#" + targetBsrUri]={"relationship":relationship,"rowId":row.id};
            rowTargetLookup[row.id]={"relationship":relationship,"bsrUri":targetBsrUri};
            targetRows.push(row.id);
            // update the target status
            var statusCell=row.cells[2];
            if (statusCell.id) {
				if (errorBsrUrisMap[targetBsrUri] == true)
            		showStatus(statusCell.id,1);
            	else if (targetBsrUri.charAt(0) == "_")
	            	showStatus(statusCell.id,3);
	            else
    	          	showStatus(statusCell.id,3,"existing");
    	    }
          }
        }
        // Now apply contraints to any remaining targets
        applyConstraints(relationship);
      }
    }
  }
}

/**
 * Handles user pressed Search from the add relationship target panel.
 */
function searchAddRelationshipTarget(/*DomEvent*/event)
{
  // fetch the user input
  var relationshipName=this.getRelationshipName();
  var targetBsrUri=this.getTargetBsrUri();
  var targetName=this.getTargetEntityName();
  var targetType=this.getTargetEntityType();
  var savedSearch=this.getSavedSearchBsrUri();
  if (targetBsrUri==null)
    targetBsrUri="";
  // now place in the form ready to submit the request
  var relName=document.getElementById("relnamehidden");
  if (relName)
    relName.value=relationshipName;
  var oldTarget=document.getElementById("oldtargethidden");
  if (oldTarget)
    oldTarget.value=targetBsrUri;
  var name=document.getElementById("namehidden");
  if (name)
    name.value=targetName;
  var type=document.getElementById("entitytypehidden");
  if (type)
    type.value=targetType;
  var saved=document.getElementById("savedsearchhidden");
  if (saved)
    saved.value=savedSearch;
  // work out constraints for business models
  var relationship=getRelationship(relationshipName, sourceObject);
  var relationshipInfo=getRelationshipInfo(relationshipName);
  if (relationship && relationshipInfo)
  {
    // Use the constraints to determine what to do
    var constraints=relationshipInfo.constraints;
    if (constraints.maxCardinality!=-1)
    {
      var maxAllowedSelections=constraints.maxCardinality-relationship.targets.length;
      
      //If we are replacing an entry then must up the max allowed by 1
      if (targetBsrUri)
        maxAllowedSelections++;
      
      var maxSelections=document.getElementById("maxselectionshidden");
      if (maxSelections)
        maxSelections.value=maxAllowedSelections;
    }
  }
  // tell teh server what action to perform
  var hidden=document.getElementById("submitactionhidden");
  if (hidden)
    hidden.value="search";
  // send the request
  submitForm();
}

/**
 * Handles user pressed Cancel from the add relationship target panel.
 */
function cancelAddRelationshipTarget(/*DomEvent*/event)
{
  // switch back to the previous view
  if (previousView==1) {
    viewSourceObject()
    fixScrollbarPosition("row0");
 } else {
    if (previousView == 4) {
	    fixScrollbarPosition(lastTargetViewed);
	    var lookup=rowTargetLookup[lastTargetViewed];
          if (lookup)
          {
            // select the row where the link is
            var rowId=lookup.rowId;
            selectRow(rowId);
          }
    }
    switchToView(previousView, lastTargetViewed);
    
  }
}

/**
 * Handles user pressed load document from the add relationship target panel.
 */
function loadDocument(/*DomEvent*/event)
{
  var relationshipName=this.getRelationshipName();
  var targetBsrUri=this.getTargetBsrUri();
  if (targetBsrUri==null)
    targetBsrUri="";

  // now place in the form ready to submit the request
  var relName=document.getElementById("relnamehidden");
  if (relName)
    relName.value=relationshipName;
  var oldTarget=document.getElementById("oldtargethidden");
  if (oldTarget)
    oldTarget.value=targetBsrUri;

 // work out constraints for business models
  var relationship=getRelationship(relationshipName, sourceObject);
  var relationshipInfo=getRelationshipInfo(relationshipName);
  if (relationship && relationshipInfo)
  {
    // Use the constraints to determine what to do
    var constraints=relationshipInfo.constraints;
    if (constraints.maxCardinality!=-1)
    {
      var maxAllowedSelections=constraints.maxCardinality-relationship.targets.length;
      
      //If we are replacing an entry then must up the max allowed by 1
      if (targetBsrUri)
        maxAllowedSelections++;
      
      var maxSelections=document.getElementById("maxselectionshidden");
      if (maxSelections)
        maxSelections.value=maxAllowedSelections;
    }
  }
  
  // Modify the action to perform the load  
  if (document.GenericObjectForm) {
	var docType = this.getDocumentType();	
    var docTypeInput = document.createElement("input");
    docTypeInput.type = "hidden";
    docTypeInput.name = "documentType";
    docTypeInput.value = docType;
	document.GenericObjectForm.appendChild(docTypeInput);

	document.GenericObjectForm.action = contextRoot + "/CreateGenericObjectLoadPrepare.do";    
  }
	
  submitForm();
}

/**
 * Handles user pressed create from the add relationship target panel.
 */
function createEntity(/*DomEvent*/event)
{
  var relationshipName=this.getRelationshipName();
  var targetBsrUri=this.getTargetBsrUri();
  if (targetBsrUri==null)
    targetBsrUri="";

  // now place in the form ready to submit the request
  var relName=document.getElementById("relnamehidden");
  if (relName)
    relName.value=relationshipName;
  var oldTarget=document.getElementById("oldtargethidden");
  if (oldTarget)
    oldTarget.value=targetBsrUri;
  
  // Modify the action to perform the load  
  if (document.GenericObjectForm) {
	var entityType = this.getEntityType();	
    var entityTypeInput = document.createElement("input");
    entityTypeInput.type = "hidden";
    entityTypeInput.name = "entityType";
    entityTypeInput.value = entityType;
	document.GenericObjectForm.appendChild(entityTypeInput);

	document.GenericObjectForm.action = contextRoot + "/CreateGenericObjectCreatePrepare.do";    
  }
	
  submitForm();
}

/**
 * Handles user pressed Change from the add relationship target panel.
 */
function commitChangeRelationshipTarget(/*DomEvent*/event)
{
  if (!processingAjax)
  {
    // Retrieve the input the user gave us
    var relationshipName=this.getRelationshipName();
    var targetName=this.getTargetEntityName();
    var targetType=this.getTargetEntityType();
    var targetBsrUri=this.getSuggestTargetBsrUri();
    var originalBsrUri=this.getTargetBsrUri();
    // Note: the callback function isn't executed yet
    var callbackFunction=function(response) {
      processingAjax=false;
      stopProgress();
      if (response.error)
      {
        displayError(response.error.message,response.error.error);
      }
      else
      {
        hideMessages();
        // fetch the response object
        var targetObject=response.data[0];
        var lookup=rowTargetLookup[relationshipName + "#" + originalBsrUri];
        if (lookup)
        {
          var relationship=lookup.relationship;
          var rowId=lookup.rowId;
          // fixup lookup table
          rowTargetLookup[relationshipName + "#" + targetBsrUri]=lookup;
          var rowRef=rowTargetLookup[rowId];
          if (rowRef)
            rowRef.bsrUri=targetBsrUri;
          else
            rowTargetLookup[rowId]={"relationship":relationship,"bsrUri":targetBsrUri};
          // Find the current target object and replace it with the new target
          for (var i=0;i<relationship.targets.length;i++)
          {
            if (relationship.targets[i].bsrUri==originalBsrUri)
            {
              relationship.targets[i]=targetObject;
              break;
            }
          } // end for
          var relationshipRow=document.getElementById(rowId);
          if (relationshipRow)
          {
          
	        var relationshipInfo=getRelationshipInfo(relationshipName);
	        var removeTargetAllowed=false;
	        if (relationshipInfo)
	        {
	          // business model relationship - use the constraints to determine what to do
	          var constraints=relationshipInfo.constraints;
	          if (constraints.minCardinality==0 || relationship.targets.length>constraints.minCardinality)
	            removeTargetAllowed=true;	          
			} else
			  removeTargetAllowed=true;
			
            var targetsCell=relationshipRow.cells[1];
            // Clear any contents in the cell
            targetsCell.innerHTML="";
            // Construct the new contents for the cell

            var divider="\u202a|\u202c";
            if (isRTL)
              divider="\u202b|\u202c";

		    var isGO = targetBsrUri.charAt(0) == "_" && targetBsrUri.charAt(1) != "_";
			    
		    if (isGO && currentBsrUri == targetBsrUri) {
			    var targetNameSpan = document.createElement("span");
		    	targetNameSpan.appendChild(document.createTextNode(getObjectName(targetObject)));
		    	targetsCell.appendChild(targetNameSpan);
		    } else {			                        
	            var targetNameAnchor = document.createElement("a");
	            targetNameAnchor.setAttribute("href", "javascript:viewTargetDetails('"+relationshipName + "#" + targetBsrUri+"')");
	            targetNameAnchor.setAttribute("title", TRANS_SHOW_TARGET_TITLE);
	            var targetNameSpan = document.createElement("span");
	            targetNameSpan.className = "bold";
	            targetNameSpan.appendChild(document.createTextNode(getObjectName(targetObject)));
	            targetNameAnchor.appendChild(targetNameSpan);
	            targetsCell.appendChild(targetNameAnchor);                            
	            targetsCell.appendChild(document.createTextNode("\u00a0"+divider+"\u00a0"));
			}            
			
            //if the bsrUri starts with a single _ then we can edit this child
            if (isGO && bsrUrisMap[targetBsrUri] != true) {
	            var editTargetAnchor = document.createElement("a");
	            editTargetAnchor.setAttribute("href", "javascript:editTarget('"+relationshipName+"','"+targetBsrUri+"')");
	            editTargetAnchor.setAttribute("title", TRANS_EDIT_TARGET_TITLE);
	            editTargetAnchor.appendChild(document.createTextNode(TRANS_EDIT));
	            targetsCell.appendChild(editTargetAnchor);
	            targetsCell.appendChild(document.createTextNode("\u00a0"+divider+"\u00a0"));	            
            }
            
            var changeTargetAnchor = document.createElement("a");
            changeTargetAnchor.setAttribute("href", "javascript:changeTarget('"+relationshipName+"','"+targetBsrUri+"')");
            changeTargetAnchor.setAttribute("title", TRANS_CHANGE_TARGET_TITLE);
            changeTargetAnchor.appendChild(document.createTextNode(TRANS_CHANGE));
            targetsCell.appendChild(changeTargetAnchor);
                        
            if (removeTargetAllowed)
            {
              targetsCell.appendChild(document.createTextNode("\u00a0"+divider+"\u00a0"));
              var removeSpan = document.createElement("span");
              removeSpan.setAttribute("id", relationshipName + "#" + targetBsrUri + "#Remove");
                targetsCell.appendChild(removeSpan);
              var removeTargetAnchor = document.createElement("a");
              removeTargetAnchor.setAttribute("href", "javascript:removeTarget('"+relationshipName+"','"+targetBsrUri+"')");
              removeTargetAnchor.setAttribute("title", TRANS_REMOVE_TARGET_TITLE);
              removeTargetAnchor.appendChild(document.createTextNode(TRANS_REMOVE));
              removeSpan.appendChild(removeTargetAnchor);
            }
            // update the target status
            var statusCell=relationshipRow.cells[2];
            if (statusCell.id) {
            	if (errorBsrUrisMap[targetBsrUri] == true)
            		showStatus(statusCell.id,1);
            	else if (targetBsrUri.charAt(0) == "_")
	            	showStatus(statusCell.id,3);
	            else
   	          	showStatus(statusCell.id,3,"existing");
            }			    	          	
          }
        }
        checkRequiredInput();
        // Now view the details of the new target
        viewTargetDetails(relationshipName + "#" + targetBsrUri);
      }
    }
    // make the RPC call to log the relationship target add
    processingAjax=true;
    var deferred=goService.changeGenericObjectRelationshipTarget(getGOBeanUri(),getUserCorrelator(),relationshipName,targetBsrUri,originalBsrUri);
    deferred.addCallback(callbackFunction);
    deferred.addErrback(processRPCException);
    // progress
    progressTabTitle = null;
    startProgressFunction(PROGRESS_DIV_ID, TRANS_PROCESSING, displayProgressPanel);
  }
}

/**
 * Removes the given target bsrUri from the given relationship from the object.
 */
function removeTarget(/*string*/relationshipName,/*string*/bsrUri)
{
  if (!processingAjax)
  {
    var lookup=rowTargetLookup[relationshipName + "#" + bsrUri];
    var relationship=lookup.relationship;
    var relationshipRowId=relationship.rowId;
    var rowId=lookup.rowId;
    var switchView=false;
    // Note: the callback function isn't executed yet
    var callbackFunction=function(response) {
      processingAjax=false;
      stopProgress();
      if (response.error)
      {
        displayError(response.error.message,response.error.error);
      }
      else
      {
        // adjust the local relationship data
        var targets=relationship.targets;
        for (var i=0;i<targets.length;i++)
        {
          if (targets[i].bsrUri==bsrUri)
          {
            // Remove the element from the array of targets
            var modifiedArray=removeElementAtIndex(targets,i);
            // and update with the returned array
            if (modifiedArray)
              relationship.targets=modifiedArray;
            break;
          }
        } // end for
        // TODO: remove rel from source object view (1)
        // do a straight remove on the row if its not the main relationship row
        if (relationshipRowId!=rowId)
        {
          // check if row was sleected before we junk it
          var row=document.getElementById(rowId);
          if (row.className=="selected")
            switchView=true;
          // update the target rows
          var targetRows=relationship.targetRows;
          for (var i=0;i<targetRows.length;i++)
          {
            if (targetRows[i]==rowId)
            {
              // Remove the element from the array of targetRows
              var modifiedArray=removeElementAtIndex(targetRows,i);
              // and update with the returned array
              if (modifiedArray)
                relationship.targetRows=modifiedArray;
              break;
            }
          } // end for
          removeRow(rowId);
        }
        // for business models we may need to reconstruct the add target cell
        var relationshipInfo=getRelationshipInfo(relationshipName);
        if (relationship && relationshipInfo)
        {
          /*
           * Retrieve the constraints for the relationship and see if are
           * allowed to add targets and, if so, whether the 'Add Target'
           * link is currently being displayed.
           */
          var relationshipRow=document.getElementById(relationshipRowId);
          if (relationshipRow)
          {
            var constraints=relationshipInfo.constraints;
            if (constraints.maxCardinality==-1 || relationship.targets.length<constraints.maxCardinality)
            {
              // Make sure that the add target link is not already displayed
              var targetsCell=relationshipRow.cells[1];
              if (targetsCell.getElementsByTagName("A").length > 1)
              {
                // applyConstraints will fixup the display text for the link
                // Create a new row directly below the relationship row
                // since we enabled add targets, but didnt remove the link on the
                // main relationship row
                if (relationshipRowId!=rowId)
                {
                  var leftClass = "centerleft";
                  var rightClass = "right";
                  var rowIndex = relationshipRow.sectionRowIndex + 1;
                  var table=document.getElementById("relationshiptable");
                  if (rowIndex == table.tBodies[0].rows.length)
                  {
                    /*
                     * The new row will be the last row in the table.  We
                     * need to modify the styles of the current last row to
                     * make sure that the cells render correctly.
                     */
                    rowIndex = -1;
                    var rows = table.tBodies[0].rows;
                    var lastRow = rows[rows.length - 1];
                    var cells = lastRow.cells;
                    for (var i = 0; i < cells.length; i++)
                    {
                      if (i != cells.length - 1) {
                        cells[i].className = leftClass;
                      } else {
                        cells[i].className = rightClass;
                      }
                    } // end for
                    // set the class names
                    leftClass = "bottomleft";
                    rightClass = "nowrap";
                  }
                  // create the new row
                  var newTargetRow = table.tBodies[0].insertRow(rowIndex);
                  // preserve the original row ID so the lookup still works
                  newTargetRow.id = relationshipRow.id;
                  newTargetRow.className="notselected";
                  // give the relationship row a new ID
                  rowCounter=rowCounter+1;
                  relationshipRow.id="row"+rowCounter;
                  relationship.rowId=relationshipRow.id;
                  relationship.targetRows.push(newTargetRow.id);
                  // update status cell ID as well
                  var oldStatusCell=relationshipRow.cells[2];
                  oldStatusCell.id=relationshipRow.id+"col2";
                  // add the cells into the row
                  var nameCell = newTargetRow.insertCell(0);
                  nameCell.className = leftClass;
                  nameCell.innerHTML = "&nbsp;";
                  // Targets cell
                  var tempAddTargetCell=document.createElement("td");
                  var oldTargetsCell = relationshipRow.replaceChild(tempAddTargetCell, targetsCell);
                  newTargetRow.appendChild(oldTargetsCell);
                  newTargetRow.cells[1].id = "";
                  newTargetRow.cells[1].className = leftClass;
                  // create the status cell
                  var statusCell=document.createElement("td");
                  statusCell.id=newTargetRow.id+"col2";
                  statusCell.className=rightClass;
                  statusCell.appendChild(document.createTextNode("\u00a0"));
                  newTargetRow.appendChild(statusCell);
                  showStatus(statusCell.id,3,"existing");
                  // move selection to new row if original was selected
                  if (relationshipRow.className=="selected")
                  {
                    // need to switch view back to source
                    switchView=true;
                    relationshipRow.className="notselected";
                    selectRow(newTargetRow.id);
                  }
                }
              }
            }
          }
        }
        // Now apply contraints to any remaining targets
        if (relationship)
          applyConstraints(relationship);
        // reflow the table
        adjustTableSize();
        checkRequiredInput();
        // switch back to the main view
        if (switchView)
          viewSourceObject();
      }
    }
    // make the RPC call to log the relationship remove
    processingAjax=true;
    var deferred=goService.removeGenericObjectRelationshipTarget(getGOBeanUri(),getUserCorrelator(),relationshipName,bsrUri);
    deferred.addCallback(callbackFunction);
    deferred.addErrback(processRPCException);
    // progress
    startProgress(relationshipName + "#" + bsrUri + "#Remove", TRANS_REMOVING);
  }
}

/**
 * Adjust the relationship section to make sure the constraints are being
 * honored with the user choices available.
 */
function applyConstraints(/*Relationship*/relationship)
{
  // Ensure that we have a valid relationship
  if (relationship)
  {
    /*
     * Check to see if this is a business model relationship and, if so,
     * retrieve the RelationshipInfo object for it.
     */ 
    var relationshipInfo=getRelationshipInfo(relationship.name);
    if (relationshipInfo)
    {
      // Use the constraints to determine what to do
      /*
       * We need to iterate over all of the targets for the relationship and
       * add or remove the 'remove' link as appropriate.  In order to
       * determine where the first target is rendered, we need to use the
       * constraints to check whether the 'Add Target' link in displayed.
       * We also need to use the constraints to determine whether targets
       * can be removed.
       */
      var addTargetAllowed=false;
      var removeTargetAllowed=false;
      var targetCount=relationship.targets.length;
      var constraints=relationshipInfo.constraints;
      if (constraints.maxCardinality==-1 || targetCount<constraints.maxCardinality)
        addTargetAllowed=true;
      if (constraints.minCardinality==0 || targetCount>constraints.minCardinality)
        removeTargetAllowed=true;
      // build the list of rows to manage
      var targetRows=[];
      // add the primary row if it contains a target
      if (!addTargetAllowed)
        targetRows.push(relationship.rowId);
      // now add any other targets
      var relTargetRows=relationship.targetRows;
      for (var i=0;i<relTargetRows.length;i++)
      {
        targetRows.push(relTargetRows[i]);        
      } // end for
      // now go through the list of rows and patch them up
      for (var i=0;i<targetRows.length;i++)
      {
        var row=document.getElementById(targetRows[i]);
        if (row)
        {
          // fetch the cell and adjust its contents if needed
          var targetsCell=row.cells[1];
          var target=rowTargetLookup[row.id];
          var targetBsrUri=" ";
          if (target)
            targetBsrUri=target.bsrUri;
          
          var elements = targetsCell.getElementsByTagName("A");
         
          if (removeTargetAllowed && elements[elements.length-1].getAttribute("href").substr(0,23) != "javascript:removeTarget")
          {
            /* 
             * Targets can be removed but the remove link is not displayed
             * on the current row.  Add the remove link.
             */
            var divider="\u202a|\u202c";
            if (isRTL)
              divider="\u202b|\u202c";
            targetsCell.appendChild(document.createTextNode("\u00a0"+divider+"\u00a0"));
            var removeSpan = document.createElement("span");
            removeSpan.setAttribute("id", relationship.name + "#" + targetBsrUri + "#Remove");
            targetsCell.appendChild(removeSpan);
            var removeTargetAnchor = document.createElement("a");
            removeTargetAnchor.setAttribute("href", "javascript:removeTarget('"+relationship.name+"','"+targetBsrUri+"')");
            removeTargetAnchor.setAttribute("title", TRANS_REMOVE_TARGET_TITLE);
            removeTargetAnchor.appendChild(document.createTextNode(TRANS_REMOVE));
            removeSpan.appendChild(removeTargetAnchor);
          }
          else if (!removeTargetAllowed && elements[elements.length-1].getAttribute("href").substr(0,23) == "javascript:removeTarget")
          {
            /* 
             * Targets cannot be removed but the remove link is displayed on
             * the current row.  Remove the remove link.
             */
            targetsCell.removeChild(targetsCell.lastChild);
            targetsCell.removeChild(targetsCell.lastChild);
          }
        }
      } // end for
      // now make sure the add target link is displayed correctly
      if (addTargetAllowed)
      {
        var row=document.getElementById(relationship.rowId);
        if (row)
        {
          var targetsCell=row.cells[1];
          var relationshipName=relationship.name;
          // work out the display text
          var remainingTargets=0;
          if (constraints.maxCardinality!=-1)
            remainingTargets=constraints.minCardinality-targetCount;
          var targetText="";
          var targetNode=null;
          var targetType=relationshipInfo.targetTypeName;
          // adjust the text based on whether we still need to provide required rels
          if (remainingTargets==1)
          {
            targetText=resolveMessageInserts(TRANS_ADD_REQUIRED,targetType);
            targetNode=document.createElement("span");
            targetNode.className="required";
            targetNode.appendChild(document.createTextNode(targetText));
          }
          else if (remainingTargets>1)
          {
            targetText=resolveMessageInserts(TRANS_ADD_N_REQUIRED,targetType,remainingTargets);
            targetNode=document.createElement("span");
            targetNode.className="required";
            targetNode.appendChild(document.createTextNode(targetText));
          }
          else
          {
            targetText=resolveMessageInserts(TRANS_ADD,targetType);
            targetNode=document.createTextNode(targetText);
          }
          // work out whether to edit or replace the cell
          var targetAnchors=targetsCell.getElementsByTagName("A");
          if (targetAnchors.length > 1 || targetAnchors.length==0)
          {
            // Create a new cell to replace the current one
            var addTargetCell=document.createElement("td");
            addTargetCell.className="centerleft";
            var addTargetAnchor=document.createElement("a");
            addTargetAnchor.setAttribute("href","javascript:addTarget('"+relationshipName+"')");
            addTargetAnchor.setAttribute("title",TRANS_ADD_TARGET_TITLE);
            addTargetAnchor.appendChild(targetNode);
            addTargetCell.appendChild(addTargetAnchor);
            // Now swap the contents over
            var oldTargetsCell=row.replaceChild(addTargetCell, targetsCell);
          }
          else if (targetAnchors.length==1)
          {
            // just change the link text
            var addTargetAnchor=targetAnchors[0];
            addTargetAnchor.innerHTML="";
            addTargetAnchor.appendChild(targetNode);
          }
        }
      }
    }
  }
}

/**
 * Adjusts the styling on target cells to give a visual clue for association.
 */
function adjustTargetCells()
{
  var table=document.getElementById("relationshiptable");
  if (table) 
  {
    var length=table.tBodies[0].rows.length;
    if (length>1)
    {
      var previousRow=table.tBodies[0].rows[0];
      for (var i=1;i<length;i++)
      {
        var row=table.tBodies[0].rows[i];
        var leftCell=row.cells[0];
        if (leftCell.getElementsByTagName("img").length==0)
        {
          // target row - adjust previous row
          previousRow.cells[0].className="multileft";
          previousRow.cells[1].className="multileft";
          previousRow.cells[2].className="multiright";
        }
        else
        {
          // non-target row - adjust previous row
          previousRow.cells[0].className="centerleft";
          previousRow.cells[1].className="centerleft";
          previousRow.cells[2].className="right";
        }
        previousRow=row;
      } // end for
    }
  }
}

/**
 * Shows the details for the given relName + bsrUri.
 */
function viewTargetDetails(/*string*/relNameBsrUri)
{
  if (relNameBsrUri)
  {
    lastTargetViewed = relNameBsrUri;
	    
    // select the row its on first
    var lookup=rowTargetLookup[relNameBsrUri];
    if (lookup)
    {
      selectRow(lookup.rowId);
      // now show the view
      var relationshipName=lookup.relationship.name;
      var bsrUri = rowTargetLookup[lookup.rowId].bsrUri;
      fetchAndShowTarget(relationshipName,bsrUri);
      fixScrollbarPosition(relNameBsrUri);
    }	
  }
}

/**
 * Fetches a full detail view for the given bsrUri from the server. Previous
 * fetches are checked first against the local cache.
 */
function fetchAndShowTarget(/*string*/relationshipName,/*string*/bsrUri)
{

  var previousObject=targetCache[bsrUri];
  if (previousObject)
  {
    // already have the object cached, just show it
    switchToView(4,bsrUri);
  }
  else
  {
    if (!processingAjax)
    {
      // clear the content table so we dont leave the last input on screen
      var contentTableCell=document.getElementById("contenttablecell2");
      var propertiesSection=document.getElementById("editpropertiessection");
      var propertiesStore=document.getElementById("propertiesstore");
      var relationshipSection=document.getElementById("relationshipsection");
      if (currentView==1)
      {
      try {
        contentTableCell.removeChild(propertiesSection);
        } catch (e) {
          //no need to anything. The child isn't there.
        }
        propertiesSection.style.display="none";
        propertiesStore.appendChild(propertiesSection);
      }
      contentTableCell.innerHTML="";
      if (relationshipSection)
        relationshipSection.style.display="none";
      // Note: the callback function isn't executed yet
      var callbackFunction=function(response) {
        processingAjax=false;
        stopProgress();
        
		  // First clear any error message that might be displayed
		  //clearMessages();
		  
        if (response.error)
        {
          displayError(response.error.message,response.error.error);
        }
        else
        {
          // Retrieve the details of the target object from the response
          var targetObject=response.data[0];
          if (targetObject)
          {
            // cache the object for future use
            addTargetCache(targetObject);
            /*
             * Now check to see if there is a tab definition and/or a business model
             * info object include in the response
             */
            var tabDefinition = null;
            var businessModelInfo = null;
            if (response.data.length > 1)
            {
              for (var i = 1; i < response.data.length; i++)
              {
                // Retrieve the next object from the response and inspect it
                var object = response.data[i];
                if ("generalProperties" in object
                  && "additionalProperties" in object
                  && "action" in object)
                {
                  // The object is a tab definition
                  tabDefinition = object;
                  // Place the tab definition in the cache
                  tabDefinitionCache[targetObject.sdoType] = tabDefinition;
                }
                else if ("modelPropertyInfos" in object
                  && "optionalPropertyInfos" in object
                  && "modelRelationshipInfos" in object
                  && "optionalRelationshipInfos" in object)
                {
                  // The object is a business model info
                  businessModelInfo = object;
                  // Place the tab definition in the cache
                  var primaryType = getPrimaryType(targetObject);
                  if (primaryType)
                    businessModelInfoCache[primaryType] = businessModelInfo;
                }
              } // end for
            }
            // now show on screen as we have enough info
            switchToView(4,bsrUri);
          }
        }
      }
      // Attempt to retrieve the target type
      var targetType=getTargetType(relationshipName,bsrUri);
      var fetchTabDefinition=true;
      if (tabDefinitionCache[targetType])
        fetchTabDefinition=false;
      // Attempt to retrieve business model info
      var fetchBusinessModelInfo=true;
      if (targetType=="GenericObject")
      {
        var primaryType=getTargetObjectPrimaryType(relationshipName,bsrUri);
        if (primaryType)
        {
          if (businessModelInfoCache[primaryType])
            fetchBusinessModelInfo=false;
        }
        else
          fetchBusinessModelInfo= false;
      }
      // make the RPC call to fetch the object details
      processingAjax=true;
      
      //Add in the genericObjectbeanUri
      var genericObjectBeanUri = dojo.byId("genericObjectBeanUri");
      var userStorageCorrelator = dojo.byId("userStorageCorrelator");
      var deferred=baseObjectService.retrieveByInternalBsrUri(bsrUri,genericObjectBeanUri.value, null, parseInt(userStorageCorrelator.value),fetchTabDefinition,fetchBusinessModelInfo);
      deferred.addCallback(callbackFunction);
      deferred.addErrback(processRPCException);
      // progress
      progressTabTitle = TRANS_DETAILS;
      startProgressFunction(PROGRESS_DIV_ID, TRANS_LOADING, displayProgressPanel);
    }
  }
}

/**
 * Adds the given WSRRObject to the target cache list.
 */
function addTargetCache(/*WSRRObject*/obj)
{
  if (obj && obj.properties)
  {
    // find its bsrURI first
    var bsrUri=null;
    var properties=obj.properties;
    for (var i=0;i<properties.length;i++)
    {
      var property=properties[i];
      if (property.name=="bsrURI")
      {
        bsrUri=property.value;

        //check for startsWith "_" and remove the property from the targetObject if so
        if (bsrUri.charAt(0) == "_")
          properties[i].value = "";

        break;
      }
    } // end for
    if (bsrUri)
    {
      // now place ref into the cache (overwrites previous if exists)
      targetCache[bsrUri]=obj;
    }
  }
}

/**
 * Adds a row to the end of the table.
 */
function addTableRow()
{
  var row=null;
  // get handle to table
  var table=document.getElementById("relationshiptable");
  if (table)
  {
    // switch styles on the current bottom row to those of a normal row
    var rows=table.tBodies[0].rows;
    var lastRow=rows[rows.length-1];
    rowCounter=rowCounter+1;
    var cells=lastRow.cells;
    for (var i=0;i<cells.length;i++)
    {
      if (i!=cells.length-1)
        cells[i].className="centerleft";
      else
        cells[i].className="right";
    } // end for
    // Create a new row in the table
    row=table.tBodies[0].insertRow(-1);
    row.className="notselected";
    row.id="row"+rowCounter;
  }
  return(row);
}

/**
 * Removes the table row with the given ID. Also removes any related rows
 * for the target objects.
 */
function removeTableRow(/*string*/rowId)
{
  if (rowId)
  {
    // get handle to table
    var table=document.getElementById("relationshiptable");
    if (table)
    {
      // fetch the row
      var row=document.getElementById(rowId);
      if (row)
      {
        // track which rows we need to punt
        var rowsToRemove=new Array();
        rowsToRemove.push(row);
        // We've already logged the rows of the targets, just need to find
        // the matching relationship entry for the row to get the list
        var relationships=sourceObject.relationships;
        for (var i=0;i<relationships.length;i++)
        {
          if (relationships[i].rowId==rowId)
          {
            // got any potential target rows, now add to master list
            var targetRows=relationships[i].targetRows;
            for (var j=0;j<targetRows.length;j++)
            {
              var targetRowId=targetRows[j];
              var targetRow=document.getElementById(targetRowId);
              if (targetRow)
                rowsToRemove.push(targetRow);
            } // end for
            break;
          }
        } // end for
        // set the table height to auto so that the resize code can do its work
        table.tBodies[0].style.height="auto";
        // remove all the rows we found
        for (var i=0;i<rowsToRemove.length;i++)
        {
          table.deleteRow(rowsToRemove[i].rowIndex);
        } // end for
        // Set the correct classes for the cells in the last row
        rows=table.tBodies[0].rows;
        if (rows.length>0)
        {
          var lastRow=rows[rows.length-1];
          var cells=lastRow.cells;
          for (var i=0;i<cells.length;i++)
          {
            if (i!=cells.length-1)
              cells[i].className="bottomleft";
            else
              cells[i].className="nowrap";
          } // end for
        }
      }
    }
  }
}

/**
 * Removes the table row with the given ID.
 */
function removeRow(/*string*/rowId)
{
  if (rowId)
  {
    // get handle to table
    var table=document.getElementById("relationshiptable");
    if (table)
    {
      // fetch the row
      var row=document.getElementById(rowId);
      if (row)
      {
        // set the table height to auto so that the resize code can do its work
        table.tBodies[0].style.height="auto";
        // junk the row
        table.deleteRow(row.rowIndex);
        // Set the correct classes for the cells in the last row
        rows=table.tBodies[0].rows;
        if (rows.length>0)
        {
          var lastRow=rows[rows.length-1];
          var cells=lastRow.cells;
          for (var i=0;i<cells.length;i++)
          {
            if (i!=cells.length-1)
              cells[i].className="bottomleft";
            else
              cells[i].className="nowrap";
          } // end for
        }
      }
    }
  }
}

/**
 * Adds a table row for a target object after the given parent row.
 */
function createTargetRow(/*DOM Node*/parentRow,/*String*/relationshipName,/*WSRRObject*/targetObject,/*Boolean*/removeTargetAllowed)
{
  var targetRow=null;
  // get handle to table
  var table=document.getElementById("relationshiptable");
  if (table) 
  {
    /*
     * Create a new row in the table.  The insertRow method creates the new
     * row before the row with the specified index.  Therefore, we need to
     * add one to the relationship row index and then make sure that this
     * does not take us beyond the end of the table.
     */
    var leftClass="centerleft";
    var rightClass="right";
    var rowIndex=parentRow.sectionRowIndex+1;
    if (rowIndex==table.tBodies[0].rows.length)
    {
      /*
       * The new row will be the last row in the table.  We need to modify
       * the styles of the current last row to make sure that the cells
       * render correctly.
       */
      rowIndex=-1;
      var rows=table.tBodies[0].rows;
      var lastRow=rows[rows.length-1];
      var cells=lastRow.cells;
      for (var i=0;i<cells.length;i++)
      {
        if (i!=cells.length-1)
          cells[i].className=leftClass;
        else
          cells[i].className=rightClass;
      } // end for
      // Set the left and right class names for the row to be inserted
      leftClass="bottomleft";
      rightClass="nowrap";
    }
    /*
     * Now create the new row in the table.  Don't bother selecting it at
     * this point since this will happen when we display the details of
     * the target object.
     */
    rowCounter=rowCounter+1;
    targetRow=table.tBodies[0].insertRow(rowIndex);
    targetRow.id="row"+rowCounter;
    targetRow.className="notselected";
    /*
     * Now create all of the relevant cells within the new row.  We start
     * with the name cell.
     */
    var nameCell=targetRow.insertCell(0);
    nameCell.className=leftClass;
    nameCell.innerHTML="&nbsp;";
    // Targets cell
    var targetBsrUri=getBsrUri(targetObject);
    var targetsCell = targetRow.insertCell(1);
    targetsCell.className = leftClass;
    var isGO = targetBsrUri.charAt(0) == "_" && targetBsrUri.charAt(1) != "_";
    
    if (isGO && currentBsrUri == targetBsrUri) {
	    var targetNameSpan = document.createElement("span");
    	targetNameSpan.appendChild(document.createTextNode(getObjectName(targetObject)));
    	targetsCell.appendChild(targetNameSpan);
    } else {
	    var targetNameAnchor = document.createElement("a");
	    targetNameAnchor.setAttribute("href", "javascript:viewTargetDetails('"+relationshipName + "#" + targetBsrUri+"')");
	    targetNameAnchor.setAttribute("title", TRANS_SHOW_TARGET_TITLE);
	    var targetNameSpan = document.createElement("span");
	    targetNameSpan.className = "bold";
	    targetNameSpan.appendChild(document.createTextNode(getObjectName(targetObject)));
	    targetNameAnchor.appendChild(targetNameSpan);
	    targetsCell.appendChild(targetNameAnchor);
    }
    
    var divider="\u202a|\u202c";
    if (isRTL)
      divider="\u202b|\u202c";
      
    //if the bsrUri starts with a single _ then we can edit this child
    if (isGO && bsrUrisMap[targetBsrUri] != true) {
     targetsCell.appendChild(document.createTextNode("\u00a0"+divider+"\u00a0"));
     var editTargetAnchor = document.createElement("a");
     editTargetAnchor.setAttribute("href", "javascript:editTarget('"+relationshipName+"','"+targetBsrUri+"')");
     editTargetAnchor.setAttribute("title", TRANS_EDIT_TARGET_TITLE);
     editTargetAnchor.appendChild(document.createTextNode(TRANS_EDIT));
     targetsCell.appendChild(editTargetAnchor);
    }
      
    targetsCell.appendChild(document.createTextNode("\u00a0"+divider+"\u00a0"));
    var changeTargetAnchor = document.createElement("a");
    changeTargetAnchor.setAttribute("href", "javascript:changeTarget('"+relationshipName+"','"+targetBsrUri+"')");
    changeTargetAnchor.setAttribute("title", TRANS_CHANGE_TARGET_TITLE);
    changeTargetAnchor.appendChild(document.createTextNode(TRANS_CHANGE));
    targetsCell.appendChild(changeTargetAnchor);
        
    if (removeTargetAllowed)
    {
      targetsCell.appendChild(document.createTextNode("\u00a0"+divider+"\u00a0"));
      var removeSpan = document.createElement("span");
      removeSpan.setAttribute("id", relationshipName + "#" + targetBsrUri + "#Remove");
      targetsCell.appendChild(removeSpan);
      var removeTargetAnchor = document.createElement("a");
      removeTargetAnchor.setAttribute("href", "javascript:removeTarget('"+relationshipName+"','"+targetBsrUri+"')");
      removeTargetAnchor.setAttribute("title", TRANS_REMOVE_TARGET_TITLE);
      removeTargetAnchor.appendChild(document.createTextNode(TRANS_REMOVE));
      removeSpan.appendChild(removeTargetAnchor);
    } // IF - removeTargetAllowed
    // create the status cell
    var statusCell=targetRow.insertCell(2);
    statusCell.className=rightClass;
    statusCell.id="row"+rowCounter+"col2";
    statusCell.appendChild(document.createTextNode("\u00a0"));
    // Adjust the size of the table as required
    adjustTableSize(table);
  } // IF - table
  return(targetRow);
}

/**
 * Matches the given relationship name against the list of optional relationships.
 * Returns null if no match, or the RelationshipInfo if found.
 */
function matchOptionalRelationship(/*string*/relationshipName)
{
  var ret=null;
  for (var i=0;i<optionalRelList.length;i++)
  {
    var rel=optionalRelList[i];
    if (rel.NCName==relationshipName)
    {
      ret=rel;
      break;
    }
  } // end for
  return(ret);
}

/**
 * Find the type of the target for the bsrUri target of the given relationship.
 */
function getTargetType(/*String*/relationshipName,/*String*/bsrUri)
{
  var targetType = "BaseObject";
  if (relationshipName && bsrUri)
  {
    // Iterate over the user defined relationships
    var targetFound = false;
    for (var i = 0; i < sourceObject.relationships.length && targetFound == false; i++)
    {
      if (sourceObject.relationships[i].name == relationshipName)
      {
        // Iterate over the targets of this relationship
        var targets = sourceObject.relationships[i].targets;
        for (var j = 0; j < targets.length; j++)
        {
          if (targets[j].bsrUri == bsrUri)
          {
            targetType = targets[j].sdoType;
            targetFound = true;
            break;
          }
        } // end for
      }
    } // end for
  }
  return targetType;
}

/**
 * Find the business model type of the target for the bsrUri target of the
 * given relationship.
 */
function getTargetObjectPrimaryType(/*String*/relationshipName,/*String*/bsrUri)
{
  var primaryType = null;
  if (relationshipName && bsrUri)
  {
    // Iterate over the relationships
    var targetFound = false;
    for (var i = 0; i < sourceObject.relationships.length && targetFound == false; i++)
    {
      if (sourceObject.relationships[i].name == relationshipName)
      {
        // Iterate over the targets of this relationship
        var targets = sourceObject.relationships[i].targets;
        for (var j = 0; j < targets.length && targetFound == false; j++)
        {
          if (targets[j].bsrUri == bsrUri)
          {
            primaryType = getPrimaryType(targets[j]);
            targetFound = true;
            break;
          }
        } // end for
      }
    } // end for
  }
  return primaryType;
}

/**
 * Handle the button press for Finish.
 */
function submitFinish()
{
  var hidden=document.getElementById("actionhidden");
  if (hidden)
    hidden.value="finish";
  var finishButton=document.getElementById("finishbutton");
  if (finishButton)
    finishButton.disabled=true;
  var cancelButton=document.getElementById("cancelbutton");
  if (cancelButton)
    cancelButton.disabled=true;
  saveObjectProperties(submitForm);
}

/**
 * Handle the button press for Cancel.
 */
function submitCancel()
{
  var hidden=document.getElementById("actionhidden");
  if (hidden)
    hidden.value="cancel";
  var finishButton=document.getElementById("finishbutton");
  if (finishButton)
    finishButton.disabled=true;
  var cancelButton=document.getElementById("cancelbutton");
  if (cancelButton)
    cancelButton.disabled=true;
  submitForm();
}

/**
 * Handle the link for edit classifications
 */
function editClassifications()
{
  var hidden=document.getElementById("submitactionhidden");
  if (hidden)
    hidden.value="classify";
  saveObjectProperties(submitForm);
}

/**
 * Submits the button form.
 */
function submitForm()
{
  if (document.GenericObjectForm)
    document.GenericObjectForm.submit();
}

/** 
 * Deals with exceptions thrown from the JSON RPC stuff.
 */
function processRPCException(/*object*/e)
{
  hideMessages();
  processingAjax=false;
  var msg=TRANS_ERROR_EXCEPTION+" "+e.message;
  if (e.fileName)
    msg=msg+"; "+TRANS_SOURCE_FILE+" "+e.fileName;
  if (e.lineNumber)
    msg=msg+"; "+TRANS_FAILING_LINE+" "+e.lineNumber;
  displayError(msg,null);
}

/**
 * Shows the error message dynamically in the message box at the top of the page.
 */
function displayError(/*string*/errorMsg,/*string*/errorExtra)
{
  //always set the currentView to zero so we cancarry on
  currentView = 0;
  if (errorMsg)
  {
    var messageBox=document.getElementById("msgannounce");
    if (messageBox!=null)
    {
      messageBox.style.display="block";         // make the box visible
      var errorMsgContent=document.getElementById("errorMsgContent");
      errorMsgContent.innerHTML=errorMsg;
      var errorExtraTwist=document.getElementById("errorExtraTwist");
      var errorExtraContent=document.getElementById("errorExtraContent");
      if (errorExtra)
      {
        errorExtraContent.innerHTML=errorExtra;
        errorExtraTwist.style.display="inline";
      }
      else
      {
        errorExtraContent.innerHTML="";
        errorExtraTwist.style.display="none";
      }
    }
  }
}

/**
 * Hides the error message panel from view.
 */
function hideMessages()
{
  var messageBox=document.getElementById("msgannounce");
  if (messageBox!=null)
  {
    messageBox.style.display="none";          // hide the message box
  }
}

/*
 * Utility function to remove the item at the specified index from the
 * specified array.
 */
function removeElementAtIndex(/*array*/array,/*int*/index)
{
  // Create the variable to return
  var modifiedArray = null;
  // Make sure that the array and index are valid
  if (array && array.length > 0 && index >= 0 && array.length > index)
  {
    // Copy the elements before and after the the index into two new arrays.
    var elementsBefore = array.slice(0, index);
    var elementsAfter = null;
    if (index != array.length - 1) {
      elementsAfter = array.slice(index + 1);
    } else {
      elementsAfter = new Array();
    }
    // Join the new arrays together
    modifiedArray = elementsBefore.concat(elementsAfter);
  }
  return modifiedArray;
}

/**
 * Handles the width calculation of the scrollable setion for IE.
 */
function adjustScrollbox()
{
  if (dojo.isIE==6)
  {
    var table=document.getElementById("scrollreltable");
    var refTable=document.getElementById("buttontable");
    if (refTable)
    {
      var size=getWindowClientSize();
      var parent=table.parentNode;
      var refCoords=dojo.coords(refTable);
      var tableCoords=dojo.coords(table);
      var parentCoords=dojo.coords(parent);
      var diff=parentCoords.w-refCoords.w;
      if (diff!=0)
        table.style.width=refCoords.w-diff-2+"px";
      else
      {
        diff=tableCoords.w-refCoords.w;
        if (diff!=0)
          table.style.width=refCoords.w-2+"px";
      }
      var confirmFunction=function() {
        var rCoords=dojo.coords(refTable);
        var tCoords=dojo.coords(table);
        var pCoords=dojo.coords(parent);
        var dif=pCoords.w-rCoords.w;
        if (dif!=0)
          table.style.width=rCoords.w-dif-2+"px";
        else
        {
          dif=tCoords.w-rCoords.w;
          if (dif!=0)
            table.style.width=rCoords.w-2+"px";
        }
      }
      if (size.width!=lastClientSize.width && lastClientSize.width!=0)
        window.setTimeout(confirmFunction,0);
      // force the tab to repaint
      var tabtable=document.getElementById("tabtable");
      tabtable.style.display="none";
      tabtable.style.display="block";
      lastClientSize=size;
    }
  }
}

/**
 * Deal with window resize events
 */
function handleWindowResize(/*DOMevent*/event)
{
  window.setTimeout(adjustScrollbox,0); // on timer so as not to holdup event
}

/**
 * Clears all the child DOM nodes from the given DOM node.
 */
function clearDomNode(/*DomObject*/node)
{
  if (node.hasChildNodes())
  {
    var children=node.childNodes;
    for (var i=0;i<children.length;i++)
    {
      node.removeChild(children[i]);
    } // end for
  }
}

function printObject(/*object*/obj)
{
  var text="";
  for (var key in obj)
  {
    var value=obj[key];
    text=text+" "+key+": "+value;
  } // end for
  alert(text);
}

function getUserCorrelator() {
	var uc = dojo.byId("userStorageCorrelator");
	
	if (uc)
		return parseInt(uc.value);
	else
		return 0;
}

function getGOBeanUri() {
	var uri = dojo.byId("genericObjectBeanUri");
	
	if (uri)
		return uri.value;
	else
		return null;
}

function fixScrollbarPosition(bsrUri) {

	var scrollBar = document.getElementById("scrollreltable");
	
	//If there is a scrollbar then move it to the correct place
	if (scrollBar != null) {
		previousScrollPos = scrollBar.scrollTop;
		var tmp = scrollBar.getElementsByTagName("tbody");
				
		if (tmp[0].scrollTop > previousScrollPos) {
			scrollBar = tmp[0];
			previousScrollPos = scrollBar.scrollTop;
		}						
	}
	
	//Put the scrollbar back where it was, or move it to the position of the new element
	if (scrollBar != null) {			
		var relTable = document.getElementById("relationshiptable");
		var numRows = relTable.rows.length;
		if (bsrUri != null) {
			//Position the new element in the middle of the nav section
			var index = indexOfSelectedID(bsrUri);
			placeScrollBar(previousScrollPos,index,numRows);
		} else {
			//Scroll it back to where it was
			var index = 0;
			placeScrollBar(previousScrollPos,index,numRows);						
		}
	}
}

function indexOfSelectedID(id) {
	var relTable = document.getElementById("relationshiptable");
	var lookup=rowTargetLookup[id];
	
	var ret = 0;
	
	if (relTable != null && lookup != null && id != "row0") {
		var rowId = lookup.rowId;
		
		var count = 0;
		while (ret ==0 && count < relTable.rows.length) {
			var row = relTable.rows[count];
			if (row.id == rowId) {
				ret=count;
				break;
			} else
				count++;
		}
	}
	
	return ret;
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
	
	//Always use 0 here
	addOne = 0;
	
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

dojo.addOnLoad(initPage);
