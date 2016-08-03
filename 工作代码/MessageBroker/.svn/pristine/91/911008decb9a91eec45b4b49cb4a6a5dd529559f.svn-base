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

/* load required packages */
dojo.registerModulePath("com.ibm.sr.widgets", "../widgets");
dojo.require("dijit.form.DateTextBox");
dojo.require("dijit.form.NumberTextBox");
dojo.require("dijit.form.ValidationTextBox");
dojo.require("dojo.date.locale");
dojo.require("dojo.parser");
dojo.require("dojo.rpc.JsonService");
dojo.require("dijit.Tooltip");
dojo.require("com.ibm.sr.widgets.DateTimeTextBox");
dojo.require("dijit.form.NumberSpinner");
dojo.require("com.ibm.sr.widgets.PaddedSpinner");
dojo.require("com.ibm.sr.widgets.TimeSelector");
dojo.require("com.ibm.sr.widgets.DateSelector");
dojo.require("com.ibm.sr.widgets.DateTimePopup");
dojo.require("com.ibm.sr.widgets.LongInteger");
dojo.require("com.ibm.sr.widgets.DigitTextBox");
dojo.require("com.ibm.sr.widgets.ValidationDigitBox");

/* globals */
var addedPropIndex=1000000;
var mandatoryComplete=false;
var validationComplete=false;
var validatingInputs={};
var addDialogMode=false;

// open the JSON service
var detailService=new dojo.rpc.JsonService(contextRoot + "/RPCAdapter/jsonrpc/BaseObjectService");

/**
 * Initialise all the required stuff on first page load.
 */
function initPage()
{
  if (typeof(optionalVars) != "undefined" && optionalVars.data)
  {
    // convenience reference to get the list of optional vars
    optionalVarList=optionalVars.data;
    // build the convenience lookups for display name and description
    for (var i=0;i<optionalVarList.length;i++)
    {
      optionalVar=optionalVarList[i];
      var varName=optionalVar.NCName;
      optionalVarIndex[varName]=i;
    } // end for
  }
  if (typeof(flattenedProperties) != "undefined" && flattenedProperties.data)
  {
    // convenience reference to the property array
    flattenedList=flattenedProperties.data;
  }
  nudgeTable();
}

/**
 * Adjusts the main container table when column contents are over-wide.
 */
function nudgeTable()
{
  var table=document.getElementById("detailcontenttable");
  if (table)
  {
    var rows=table.tBodies[0].rows;
    if (rows.length>1)
    {
      var row=rows[1];
      var leftColumn=row.cells[0];
      var rightColumn=row.cells[1];
      var left=dojo.coords(leftColumn);
      var right=dojo.coords(rightColumn);
      var propContainer=document.getElementById("propertiessection");
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
 * Switches the type of the variable to create.
 */
function switchVarType(/*integer*/type)
{
  if (type==1)
  {
    var optionaltypediv=document.getElementById("vartypeoptional");
    var customtypediv=document.getElementById("vartypecustom");
    var optionalname=document.getElementById("optionalpropertyname");
    var customname=document.getElementById("custompropertyname");
    var addbutton=document.getElementById("propertyaddbutton");
    optionaltypediv.className="radiochoice";
    customtypediv.className="disabledradiochoice";
    customname.disabled=true;
    optionalname.disabled=false;
    addbutton.disabled=false;
  }
  else
  {
    var optionaltypediv=document.getElementById("vartypeoptional");
    var customtypediv=document.getElementById("vartypecustom");
    var optionalname=document.getElementById("optionalpropertyname");
    var customname=document.getElementById("custompropertyname");
    var addbutton=document.getElementById("propertyaddbutton");
    optionaltypediv.className="disabledradiochoice";
    customtypediv.className="radiochoice";
    customname.disabled=false;
    optionalname.disabled=true;
    // validate the custom property name - may have a previous value
    handlePropertyInputCallback(customname);
  }
}

/**
 * Called on change from the optional var list to show any description for the
 * optional var.
 */
function showOptionalDescription()
{
  var optionalname=document.getElementById("optionalpropertyname");
  var value=optionalname.value;
  var index=optionalVarIndex[value];
  var desc=optionalVarList[index].description;
  var descValue=document.getElementById("optionalpropertydesc");
  clearDomNode(descValue);
  if (!desc)
    desc="";
  var textNode=document.createTextNode(desc);
  descValue.appendChild(textNode);
}

/**
 * Triggered by keypress on the optional var list.
 */
function showNameChange()
{
  // delay the real function
  window.setTimeout(showOptionalDescription,0);
}

/**
 * Shows an inline box prompting the user to define the new property to add.
 */
function addProperty()
{
  var container=document.getElementById("addpropertystart");
  var focusObject="";
  if (optionalVarList.length>0)
  {
    var newHTML="\n \
<fieldset>\n \
  <legend>"+TRANS_ADD_PROPERTY+"</legend>\n \
  <div class='radiochoice' id='vartypeoptional'>\n \
    <input type='radio' name='varchoice' id='optionalvar' tabindex='1' value='optional' title='"+TRANS_OPTIONAL_NAME_TITLE+"' class='noborder autowidth' checked='checked' onclick='switchVarType(1)'/>\n \
    <label for='optionalpropertyname' title='"+TRANS_CHOOSE_OPTIONAL_NAME+"'>\n \
      "+TRANS_OPTIONAL_NAME+"\n \
    </label>\n \
    <br/>\n \
    <select name='optionalproperty' id='optionalpropertyname' title='"+TRANS_CHOOSE_OPTIONAL_NAME+"' tabindex='1' onchange='showOptionalDescription()' onkeypress='showNameChange()'>\n";

    // TODO: reduce the list according to what has already been added
    // add in the choices
    var choices="";
    for (var i=0;i<optionalVarList.length;i++)
    {
      var optionalVar=optionalVarList[i];
      var varName=optionalVar.NCName;
      var description=optionalVar.description;
      if (!description)
        description="";
      var choice="<option value='"+varName+"' title='"+description+"'>"+optionalVar.name+"</option>\n";
      choices=choices+choice;
    } // end for
    // get description of first var
    var desc=optionalVarList[0].description;
    if (!desc)
      desc="";
    newHTML=newHTML+choices+" \
    </select>\n \
    <p id='optionalpropertydesc'>"+desc+"</p>\n \
  </div>\n \
  <div class='disabledradiochoice' id='vartypecustom'>\n \
    <input type='radio' name='varchoice' id='customvar' tabindex='1' value='custom' title='"+TRANS_CUSTOM_NAME_TITLE+"' class='noborder autowidth' onclick='switchVarType(2)'/>\n \
    <label for='custompropertyname' title='"+TRANS_CHOOSE_NEW_NAME+"'>\n \
      "+TRANS_CUSTOM_NAME+"\n \
    </label>\n \
    <br/>\n \
    <input type='text' name='customproperty' value='' autocomplete='off' id='custompropertyname' title='"+TRANS_CHOOSE_NEW_NAME+"' tabindex='1' disabled='disabled' onkeypress='handlePropertyInput(event)' onblur='handlePropertyInput(event)'/>\n \
    <br/>\n \
  </div>\n \
  <div class='buttongroup'>\n \
    <button type='button' class='standardbutton' id='propertyaddbutton' tabindex='1' onclick='commitAddProperty()'>"+TRANS_BUTTON_ADD+"</button>\n \
    <button type='button' class='standardbutton' id='propertycancelbutton' tabindex='1' onclick='cancelAddProperty()'>"+TRANS_BUTTON_CANCEL+"</button>\n \
  </div>\n \
</fieldset>\n";
    // set the generated HTML
    container.innerHTML=newHTML;
    // switch focus to list
    focusObject="optionalpropertyname";
  }
  else
  {
    container.innerHTML="\n \
<fieldset>\n \
  <legend>"+TRANS_ADD_PROPERTY+"</legend>\n \
  <label for='custompropertyname' title='"+TRANS_CHOOSE_NEW_NAME+"'>\n \
    "+TRANS_CUSTOM_NAME+"\n \
  </label>\n \
  <br/>\n \
  <input type='text' name='customproperty' value='' autocomplete='off' id='custompropertyname' title='"+TRANS_CHOOSE_NEW_NAME+"' tabindex='1' onkeypress='handlePropertyInput(event)' onblur='handlePropertyInput(event)'/>\n \
  <br/>\n \
  <div class='buttongroup'>\n \
    <button type='button' class='standardbutton' id='propertyaddbutton' tabindex='1' onclick='commitAddProperty()' disabled='disabled'>"+TRANS_BUTTON_ADD+"</button>\n \
    <button type='button' class='standardbutton' id='propertycancelbutton' tabindex='1' onclick='cancelAddProperty()'>"+TRANS_BUTTON_CANCEL+"</button>\n \
  </div>\n \
</fieldset>\n";
    // switch focus to entry field
    focusObject="custompropertyname";
  }
  // turn off the main buttons
  disableMainButtons();
  // switch input field to focus
  window.setTimeout(function(){focusOnObject(focusObject);},0); // separate thread
}

/**
 * Changes input focus to the object with the given ID.
 */
function focusOnObject(/*string*/id)
{
  var obj=dojo.byId(id);
  if (obj)
  {
    obj.focus();
    try {
      obj.select();
    } catch (e) {}
    var coords=dojo.coords(obj,true);
    window.scrollTo(0,coords.y);
  }
}

/**
 * Called whenever a key is pressed in the property name field.
 */
function handlePropertyInput(/*DOMevent*/e)
{
  var input=null;
  if (e.srcElement)
    input=e.srcElement;
  else
    input=e.target;
  window.setTimeout(function(){handlePropertyInputCallback(input)},0);
}

/**
 * Delayed callback function so we can correctly read the value of the input
 * field given.
 */
function handlePropertyInputCallback(/*DOMnode*/input)
{
  var value=input.value;
  var addButton=document.getElementById("propertyaddbutton");
  var propertyInput=document.getElementById("custompropertyname");
  dijit.hideTooltip(propertyInput);
  var optionalInput=document.getElementById("optionalpropertyname");
  dijit.hideTooltip(optionalInput);
  // check if the user has input a valid value
  if (value)
  {
    var firstChar=value.charAt(0);
    if (firstChar=='_' || value=="sdoType")
    {
      // reserved value
      addButton.disabled=true;
      dijit.showTooltip(TRANS_ERROR_RESERVED_NAME,propertyInput);
    }
    else
    {
      if (isValidNCName(value))
        addButton.disabled=false;
      else
      {
        // invalid value
        addButton.disabled=true;
        dijit.showTooltip(TRANS_ERROR_INVALID_NAME,propertyInput);
      }
    }
  }
  else
    addButton.disabled=true;
}

/**
 * Cancels the property add and restores the original link.
 */
function cancelAddProperty()
{
  // hide any messages or tooltips first
  hideMessages();
  var propertyInput=document.getElementById("custompropertyname");
  dijit.hideTooltip(propertyInput);
  var optionalInput=document.getElementById("optionalpropertyname");
  dijit.hideTooltip(optionalInput);
  // delay the next block
  var callbackFunction=function() {
    var container=document.getElementById("addpropertystart");
    container.innerHTML="\n \
<a href='javascript:addProperty()' tabindex='1' title='"+TRANS_ADD_PROPERTY_TITLE+"'>\n \
  "+TRANS_ADD_PROPERTY+"\n \
</a>\n";
    // enable the main buttons
    enableMainButtons();
  }
  window.setTimeout(callbackFunction,0);
}

/**
 * Commits the add new property - inserts a new property field of the correct type
 * and places a new add property link on screen.
 */
function commitAddProperty()
{
  hideMessages();
  var propName="";
  var displayName="";
  var defaultValue="";
  var fieldHelp="";
  var constraints={"displayAs": "TEXT","enumeration": false};
  var vdbProperty={};
  var settingOptional=false;
  if (optionalVarList.length>0)
  {
    var radio=document.getElementById("optionalvar");
    if (radio.checked)
    {                                       // chosen optional var
      settingOptional=true;
      var optionalname=document.getElementById("optionalpropertyname");
      propName=optionalname.value;
      var index=optionalVarIndex[propName];
      displayName=optionalVarList[index].name;
      defaultValue=optionalVarList[index].defaultValue;
      fieldHelp=optionalVarList[index].description;
      if (fieldHelp.length==0)
        fieldHelp=displayName;
      constraints=optionalVarList[index].constraints;
      // fill in property bean
      vdbProperty.mandatoryValue=false;
      vdbProperty.value=defaultValue;
      if (defaultValue || (typeof defaultValue=='number' && defaultValue==0))
        vdbProperty.hasValue=true;
      else
        vdbProperty.hasValue=false;
      vdbProperty.readOnly=false;
      vdbProperty.constraints=constraints;
      vdbProperty.propertyName=propName;
    }
    else
    {
      var propField=document.getElementById("custompropertyname");
      propName=propField.value;
      displayName=propName;
      fieldHelp=displayName;
      // fill in property bean
      vdbProperty.mandatoryValue=false;
      vdbProperty.hasValue=false;
      vdbProperty.readOnly=false;
      vdbProperty.constraints=constraints;
      vdbProperty.propertyName=propName;
    }
  }
  else
  {
    var propField=document.getElementById("custompropertyname");
    propName=propField.value;
    displayName=propName;
    fieldHelp=displayName;
    // fill in property bean
    vdbProperty.mandatoryValue=false;
    vdbProperty.hasValue=false;
    vdbProperty.readOnly=false;
    vdbProperty.constraints=constraints;
    vdbProperty.propertyName=propName;
  }
  var container=document.getElementById("addpropertystart");
  var propertyContainer=container.parentNode;
  var propertySection=propertyContainer.parentNode;
  addedPropIndex+=1;

  // Note: the callback function isn't executed yet
  var callbackFunction=function(response) {
    if (response.error)
    {
      var propertyInput=null;
      if (settingOptional)
        propertyInput=document.getElementById("optionalpropertyname");
      else
        propertyInput=document.getElementById("custompropertyname");
      var msg=response.error.name;
      if (response.error.message)
        msg=msg+": "+response.error.message;
      dijit.showTooltip(msg,propertyInput);
      // enable the main buttons
      if (response.error.code!=5002)
        enableMainButtons();
    }
    else
    {
      // fetch the result
      var propertyIndex=response.data[0].propertyIndex;
      var propId="input_"+propertyIndex;
      var inputName="flattenedProperties["+propertyIndex+"].value";
      var newClass="property notmandatory";
      // build the new property field
      propertyContainer.setAttribute("id","prop_"+propertyIndex);
      // junk the old contents first
      propertyContainer.innerHTML="";
      // build the common label
      var propertyLabel=document.createElement("label");
      propertyLabel.setAttribute("id","label_"+propertyIndex);
      propertyLabel.setAttribute("for",propId);
      propertyLabel.setAttribute("title",fieldHelp);
      propertyLabel.innerHTML=displayName;
      var deleteLink=null;
      if (editableProperties)
      {
        var linkUrl="javascript:deleteProperty('"+propName+"','"+propId+"')";
        deleteLink=document.createElement("a");
        deleteLink.href=linkUrl;
        deleteLink.setAttribute("tabIndex","1");
        var deleteText=resolveMessageInserts(TRANS_DELETE_PROPERTY,displayName);
        var linkValue="<img src='theme/"+currentTheme+"/images/closebox.gif' alt='"+deleteText+"' title='"+deleteText+"'/>";
        deleteLink.innerHTML=linkValue;
      }
      var input=null;
      // build the basic input first
      switch (constraints.displayAs)
      {
        case "TEXTAREA":
          propertyContainer.appendChild(propertyLabel);
          if (deleteLink)
          {
            propertyContainer.appendChild(document.createTextNode("\u00a0"));
            propertyContainer.appendChild(deleteLink);
          }
          var propertyBreak=document.createElement("br");
          propertyContainer.appendChild(propertyBreak);
          input=document.createElement("textarea");
          input.setAttribute("id",propId);
          input.setAttribute("rows","3");
          input.setAttribute("name",inputName);
          input.setAttribute("title",fieldHelp);
          input.setAttribute("tabIndex","1");
          input.appendChild(document.createTextNode(defaultValue));
          propertyContainer.appendChild(input);
          break;
        case "BOOLEAN":
          var hiddenInput=document.createElement("input");
          hiddenInput.setAttribute("id","check"+propId);
          hiddenInput.setAttribute("type","hidden");
          hiddenInput.setAttribute("name",inputName);
          if (defaultValue)
            hiddenInput.value=defaultValue;
          else
            hiddenInput.value="false";
          propertyContainer.appendChild(hiddenInput);
          input=document.createElement("input");
          input.setAttribute("id",propId);
          input.setAttribute("type","checkbox");
          input.setAttribute("title",fieldHelp);
          input.setAttribute("tabIndex","1");
          if (defaultValue)
            input.setAttribute("checked","checked");
          input.onclick=handleCheckbox;
          // now build the content
          propertyContainer.appendChild(input);
          propertyContainer.appendChild(propertyLabel);
          if (deleteLink)
          {
            propertyContainer.appendChild(document.createTextNode("\u00a0"));
            propertyContainer.appendChild(deleteLink);
          }
          break;
        case "URI":
          propertyContainer.appendChild(propertyLabel);
          if (deleteLink)
          {
            propertyContainer.appendChild(document.createTextNode("\u00a0"));
            propertyContainer.appendChild(deleteLink);
          }
          var propertyBreak=document.createElement("br");
          propertyContainer.appendChild(propertyBreak);
          input=document.createElement("div");
          input.className="urivalue";
          input.setAttribute("id",propId);
          input.setAttribute("dir","ltr");
          input.appendChild(document.createTextNode(defaultValue));
          propertyContainer.appendChild(input);
          break;
        default:
          propertyContainer.appendChild(propertyLabel);
          if (deleteLink)
          {
            propertyContainer.appendChild(document.createTextNode("\u00a0"));
            propertyContainer.appendChild(deleteLink);
          }
          var propertyBreak=document.createElement("br");
          propertyContainer.appendChild(propertyBreak);
          input=document.createElement("input");
          input.setAttribute("id",propId);
          input.setAttribute("type","text");
          input.setAttribute("name",inputName);
          input.setAttribute("value",defaultValue);
          input.setAttribute("title",fieldHelp);
          input.setAttribute("tabIndex","1");
          propertyContainer.appendChild(input);
          break;
      } // end switch
      // now perform the final transform to a fully validating field
      replaceReadOnlyInput(input,vdbProperty,propertyContainer,constraints,inputName,newClass,propertyIndex);
      // now create a new add property div below the new one
      var addPropertyDiv=document.createElement("div");
      addPropertyDiv.className="property notmandatory";
      propertySection.appendChild(addPropertyDiv);
      addPropertyDiv.innerHTML="\n \
<div class='newproperty' id='addpropertystart'>\n \
  <a href='javascript:addProperty()' tabindex='1' title='"+TRANS_ADD_PROPERTY_TITLE+"'>\n \
    "+TRANS_ADD_PROPERTY+"\n \
  </a>\n \
</div>\n";
      // enable the main buttons
      enableMainButtons();
      // switch input field to focus
      window.setTimeout(function(){focusOnObject(propId);},0);     // separate thread
    }
  }
  // make the RPC call to log the new property name
  var deferred=detailService.addNewPropertyName(propName);
  deferred.addCallback(callbackFunction);
  deferred.addErrback(processRPCException);
}

/**
 * Delete the given property from the object.
 */
function deleteProperty(/*string*/propName,/*string*/elementId)
{
  // Note: the callback function isn't executed yet
  var callbackFunction=function(response) {
    if (response.error)
    {
      displayError(response.error.name,response.error.message);
    }
    else
    {
      // find and remove the property from the page
      var element=document.getElementById(elementId);
      var propertyContainer=element.parentNode;
      if (dojo.hasClass(propertyContainer,"dijitInputField"))
      {
        element=document.getElementById("widget_"+elementId);
        propertyContainer=element.parentNode;
      }
      var propertySection=propertyContainer.parentNode;
      // need to destroy the Dojo widget first
      var dojoWidget=dijit.byId(elementId);
      if (dojoWidget)
        dojoWidget.destroyRecursive();
      propertySection.removeChild(propertyContainer);
    }
  }
  // make the RPC call to log the property delete
  var deferred=detailService.deletePropertyName(propName);
  deferred.addCallback(callbackFunction);
  deferred.addErrback(processRPCException);
}

/**
 * Converts the page from read-only mode to editable mode. Only those properties
 * that can be edited are converted.
 */
function editProperties()
{
  var someEditable=false;
  var firstEdit=null;
  if (flattenedList.length>0)
  {
    for (var i=0;i<flattenedList.length;i++)
    {
      // adjust all the non-read-only properties
      var vdbProperty=flattenedList[i];
      if (!vdbProperty.readOnly)
      {
        var constraints=vdbProperty.constraints;
        if (constraints)
        {
          someEditable=true;
          // grab common objects and settings
          var containerDiv=document.getElementById("prop_"+i);
          var input=document.getElementById("input_"+i);
          var inputName="flattenedProperties["+i+"].value";
          var newClass="property ";
          if (constraints.mandatoryValue)
            newClass=newClass+"mandatory";
          else
            newClass=newClass+"notmandatory";
          // remember the first editable field
          if (!firstEdit)
            firstEdit="input_"+i;
          // need to adjust deletable properties to add the delete icon
          if (vdbProperty.deleteable)
          {
            var label=document.getElementById("label_"+i);
            var propId="input_"+i;
            var propName=vdbProperty.propertyName;
            var displayName=propName;
            if (propName in optionalVarIndex)
            {
              var index=optionalVarIndex[propName];
              displayName=optionalVarList[index].name;
            }
            var linkUrl="javascript:deleteProperty('"+propName+"','"+propId+"')";
            deleteLink=document.createElement("a");
            deleteLink.href=linkUrl;
            deleteLink.setAttribute("tabIndex","1");
            var deleteText=resolveMessageInserts(TRANS_DELETE_PROPERTY,displayName);
            var linkValue="<img src='theme/"+currentTheme+"/images/closebox.gif' alt='"+deleteText+"' title='"+deleteText+"'/>";
            deleteLink.innerHTML=linkValue;
            var parent=label.parentNode;
            var nextNode=label.nextSibling;
            parent.insertBefore(document.createTextNode("\u00a0"),nextNode);
            parent.insertBefore(deleteLink,nextNode);
          }
          // make it fully editable
          replaceReadOnlyInput(input,vdbProperty,containerDiv,constraints,inputName,newClass,i);
        }
      }
    } // end for
    // now show the four standard buttons and the add property link
    if (someEditable)
    {
    //Disable any custom buttoms
    var customButtonsSpan = document.getElementById("customButtons");    
    if (customButtonsSpan) {
	    var customButtons = customButtonsSpan.childNodes;	    
	    if (customButtons)
		    for (var i=0;i<customButtons.length;i++)
		      {
		        var node=customButtons[i];
		        if (node.nodeType==1)
		        {
		          var tagName=node.tagName.toLowerCase();
		          if (tagName=="input")
		          {
		            node.disabled = true;            
		          }
		        }
		      } // end for
    }
// <input type='reset' name='reset' value='"+TRANS_BUTTON_RESET+"' class='standardbutton' id='navigationreset' tabindex='1'/>\n \
// <input type='submit' name='save' value='"+TRANS_BUTTON_OK+"' class='standardbutton' id='navigationok' tabindex='1'/>\n \
      var buttonDiv=document.getElementById("primarybuttons");
      var content="\n \
<input type='submit' name='apply' value='"+TRANS_BUTTON_OK+"' class='standardbutton' id='navigationapply' tabindex='1'/>\n \
<input type='submit' name='canceledit' value='"+TRANS_BUTTON_CANCEL+"' class='standardbutton' id='navigationcancel' tabindex='1'/>\n";
      buttonDiv.innerHTML=content;
      // insert the add property link
      var propertiesSection=document.getElementById("propertiessection");
      // will always be in the last div
      var lastDiv=null;
      var childNodes=propertiesSection.childNodes;
      for (var i=childNodes.length-1;i>=0;i--)
      {
        var node=childNodes[i];
        if (node.nodeType==1)
        {
          var tagName=node.tagName.toLowerCase();
          if (tagName=="div")
          {
            lastDiv=node;
            break;
          }
        }
      } // end for
      if (lastDiv)
      {
        var addPropertyDiv=document.createElement("div");
        addPropertyDiv.className="property notmandatory";
        lastDiv.appendChild(addPropertyDiv);
        addPropertyDiv.innerHTML="\n \
<div class='newproperty' id='addpropertystart'>\n \
  <a href='javascript:addProperty()' tabindex='1' title='"+TRANS_ADD_PROPERTY_TITLE+"'>\n \
    "+TRANS_ADD_PROPERTY+"\n \
  </a>\n \
</div>\n";
        // make sure all properties sections are visible on edit
        var id=lastDiv.id;
        var start=id.substring(0,6);
        if (start=="child_")
        {
          var lastChar=id.substr(id.length-1);
          var lastIndex=parseInt(lastChar);
          for (var i=0;i<=lastIndex;i++)
          {
            var sectionId="propSection"+i;
            forceExpandSection(sectionId);
          } // end for
        }
      }
    }
  }
  // remove the edit properties link from the page
  var editLinks=document.getElementById("editlinks");
  var editLink=document.getElementById("editlink1");
  var connector=document.getElementById("connector1");
  editLinks.removeChild(editLink);
  // also remove the vertical bar connector
  if (connector)
    editLinks.removeChild(connector);
  else
  {
    // dont have relationship section, remove the next connector
    connector=document.getElementById("connector2");
    if (connector)
      editLinks.removeChild(connector);
  }
  // grey out any remaining links
  var editLink2=document.getElementById("editlink2");
  if (editLink2)
  {
    var originalValue=editLink2.innerHTML;
    var span=document.createElement("span");
    span.className="disablededitlink";
    var textNode=document.createTextNode(originalValue);
    span.appendChild(textNode);
    editLinks.replaceChild(span,editLink2);
  }
  var editLink3=document.getElementById("editlink3");
  if (editLink3)
  {
    var originalValue=editLink3.innerHTML;
    var span=document.createElement("span");
    span.className="disablededitlink";
    var textNode=document.createTextNode(originalValue);
    span.appendChild(textNode);
    editLinks.replaceChild(span,editLink3);
  }
  // switch input field to focus on first editable field
  if (firstEdit)
    window.setTimeout(function(){focusOnObject(firstEdit);},0);     // separate thread
}

/**
 * Changes an existing read-only input into a fully validating field.
 */
function replaceReadOnlyInput(/*DomNode*/input,/*ViewDetailBeanProperty*/vdbProperty,
  /*DomNode*/containerDiv,/*constraints*/constraints,/*string*/inputName,/*string*/newClass,/*int*/index)
{
    if (constraints.enumeration)
    {
    var label=document.getElementById("label_"+index);
    var title=label.getAttribute("title");
    var value=input.value;

    var selectElement=document.createElement("select");
    selectElement.name=inputName;
    selectElement.setAttribute("id","input_"+index);
    selectElement.setAttribute("title",title);
    selectElement.setAttribute("tabIndex","1");
    selectElement.setAttribute("tabindex","1");
    // now add all the options in
    var values=constraints.allowedValues;
    var selectedIndex=-1;
    if (values)
    {
      for (var j=0;j<values.length;j++)
      {
        var optionValue=values[j];

        var choice=new Option(optionValue,optionValue);

        if (optionValue==value)
          selectedIndex=j;
        selectElement.options[j]=choice;

      } // end for
      if (selectedIndex!=-1)
        selectElement.selectedIndex = selectedIndex;
    }
    // now replace the input with the select
    containerDiv.replaceChild(selectElement,input);
  }
  else
  {
    // replace the presentation value with the original (stored) value
    if (vdbProperty.originalValue)
      input.value=vdbProperty.originalValue;
    switch (constraints.displayAs)
    {
      case "TEXT":
        input.readOnly=false;
        input.name=inputName;
        containerDiv.className=newClass;
        if (vdbProperty.mandatoryValue)
        {
          dojo.connect(input,"onkeypress",null,"handleKeyInput");
          dojo.connect(input,"onblur",null,"handleKeyInput");
          registerValidatingInput(null,input,vdbProperty);
        }
        break;
      case "TEXTAREA":
        input.readOnly=false;
        input.name=inputName;
        containerDiv.className=newClass;
        if (vdbProperty.mandatoryValue)
        {
          dojo.connect(input,"onkeypress",null,"handleKeyInput");
          dojo.connect(input,"onblur",null,"handleKeyInput");
          registerValidatingInput(null,input,vdbProperty);
        }
        break;
      case "URI":
        // replace div with input field
        var label=document.getElementById("label_"+index);
        var title=label.getAttribute("title");
        var value="";
        if (vdbProperty.originalValue)
          value=vdbProperty.originalValue;
        else
          value=vdbProperty.value;
        var inputElement=document.createElement("input");
        inputElement.name=inputName;
        inputElement.setAttribute("id","input_"+index);
        inputElement.setAttribute("dir","ltr");
        inputElement.setAttribute("type","text");
        inputElement.setAttribute("title",title);
        inputElement.setAttribute("tabIndex","1");
        inputElement.setAttribute("tabindex","1");
        inputElement.value=value;
        // now replace the div with the input
        containerDiv.replaceChild(inputElement,input);
        containerDiv.className=newClass;
        if (vdbProperty.mandatoryValue)
        {
          dojo.connect(inputElement,"onkeypress",null,"handleKeyInput");
          dojo.connect(inputElement,"onblur",null,"handleKeyInput");
          if (inputElement.value)
            vdbProperty.hasValue=true;
          else
            vdbProperty.hasValue=false;
          registerValidatingInput(null,inputElement,vdbProperty);
        }
        break;
      case "BOOLEAN":
        input.readOnly=false;
        input.disabled=false;
        input.name=inputName;
        containerDiv.className=newClass+" checkboxproperty";
        break;
      case "INTEGER":
        input.readOnly=false;
        input.name=inputName;
        containerDiv.className=newClass;
        var props = {
          name: inputName,
          value: input.value,
          regExp: "[+-]?(0|[1-9]\\d*)",
          promptMessage: TRANS_INTEGER_PROMPT,
          invalidMessage: TRANS_INTEGER_INVALID
        };
        var w = new com.ibm.sr.widgets.ValidationDigitBox(props, input);
        w.setAttribute("tabindex","1");
        w.setAttribute("tabIndex","1");
        registerValidatingInput(w,input,vdbProperty);
        break;
      case "INT":
        input.readOnly=false;
        input.name=inputName;
        containerDiv.className=newClass;
        var props = {
          name: inputName,
          value: input.value,
          constraints: {min: -2147483648, max: 2147483647, places:0},
          promptMessage: TRANS_INT_PROMPT,
          invalidMessage: TRANS_INT_INVALID
        };
        var w = new com.ibm.sr.widgets.DigitTextBox(props, input);
        w.setAttribute("tabindex","1");
        w.setAttribute("tabIndex","1");
        registerValidatingInput(w,input,vdbProperty);
        break;
      case "SHORT":
        input.readOnly=false;
        input.name=inputName;
        containerDiv.className=newClass;
        var props = {
          name: inputName,
          value: input.value,
          constraints: {min: -32768, max: 32767, places:0},
          promptMessage: TRANS_SHORT_PROMPT,
          invalidMessage: TRANS_SHORT_INVALID
        };
        var w = new com.ibm.sr.widgets.DigitTextBox(props, input);
        w.setAttribute("tabindex","1");
        w.setAttribute("tabIndex","1");
        registerValidatingInput(w,input,vdbProperty);
        break;
      case "LONG":
        input.readOnly=false;
        input.name=inputName;
        containerDiv.className=newClass;
        var props = {
          name: inputName,
          value: input.value,
          promptMessage: TRANS_LONG_PROMPT,
          invalidMessage: TRANS_LONG_INVALID
        };
        var w = new com.ibm.sr.widgets.LongInteger(props, input);
        w.setAttribute("tabindex","1");
        w.setAttribute("tabIndex","1");
        registerValidatingInput(w,input,vdbProperty);
        break;
      case "FLOAT":
        input.readOnly=false;
        input.name=inputName;
        containerDiv.className=newClass;
        var props = {
          name: inputName,
          value: input.value,
          regExp: "[-+]?(0|([1-9]+\\d*))(\\.\\d+)?([eE][-+]?(0|([1-9]+\\d*)))?",
          promptMessage: TRANS_FLOAT_PROMPT,
          invalidMessage: TRANS_FLOAT_INVALID
        };
        var w = new com.ibm.sr.widgets.ValidationDigitBox(props, input);
        w.setAttribute("tabindex","1");
        w.setAttribute("tabIndex","1");
        registerValidatingInput(w,input,vdbProperty);
        break;
      case "DOUBLE":
        input.readOnly=false;
        input.name=inputName;
        containerDiv.className=newClass;
        var props = {
          name: inputName,
          value: input.value,
          regExp: "[-+]?(0|([1-9]+\\d*))(\\.\\d+)?([eE][-+]?(0|([1-9]+\\d*)))?",
          promptMessage: TRANS_DOUBLE_PROMPT,
          invalidMessage: TRANS_DOUBLE_INVALID
        };
        var w = new com.ibm.sr.widgets.ValidationDigitBox(props, input);
        w.setAttribute("tabindex","1");
        w.setAttribute("tabIndex","1");
        registerValidatingInput(w,input,vdbProperty);
        break;
      case "DATETIME":
        input.readOnly=false;
        input.name=inputName;
        containerDiv.className=newClass;
        var dateValue = null;
        if (input.value) {
          dateValue = dojo.date.stamp.fromISOString(input.value);
        }
        var props = {
          name: inputName,
          value: dateValue,
          constraints: {formatLength:'full',zulu:true},
          promptMessage: TRANS_DATETIME_PROMPT,
          invalidMessage: TRANS_DATETIME_INVALID,
          selector: "datetime",
          iconTitle: TRANS_DATETIME_ICON
        };
        var w = new com.ibm.sr.widgets.DateTimeTextBox(props, input);
        w.setAttribute("tabindex","1");
        w.setAttribute("tabIndex","1");
        registerValidatingInput(w,input,vdbProperty);
        break;
      case "DATE":
        input.readOnly=false;
        input.name=inputName;
        containerDiv.className=newClass;
        var dateValue = null;
        if (input.value) {
          dateValue = dojo.date.stamp.fromISOString(input.value);
        }
        var props = {
          name: inputName,
          value: dateValue,
          constraints: {formatLength:'full'},
          promptMessage: TRANS_DATE_PROMPT,
          invalidMessage: TRANS_DATE_INVALID,
          selector: "date",
          iconTitle: TRANS_DATE_ICON
        };
        var w = new com.ibm.sr.widgets.DateTimeTextBox(props, input);
        w.setAttribute("tabindex","1");
        w.setAttribute("tabIndex","1");
        registerValidatingInput(w,input,vdbProperty);
        break;
      case "TIME":
        input.readOnly=false;
        input.name=inputName;
        containerDiv.className=newClass;
        var dateValue = null;
        if (input.value) {
          var rawValue=input.value;
          var firstChar=rawValue.charAt(0);
          if (firstChar!="T")
            rawValue="T"+rawValue;
          var timeValue = dojo.date.stamp.fromISOString(rawValue);
          var originalOffset=timeValue.getTimezoneOffset();
          dateValue=new Date();
          var timeZoneOffsetMinutes=dateValue.getTimezoneOffset();
          timeZoneOffsetMinutes=timeZoneOffsetMinutes*-1 + originalOffset;
          dateValue.setHours(timeValue.getHours(),timeValue.getMinutes(),timeValue.getSeconds());
          dateValue=dojo.date.add(dateValue, "minute", timeZoneOffsetMinutes);
        }
        var props = {
          name: inputName,
          value: dateValue,
          constraints: {formatLength:'full',zulu:true},
          promptMessage: TRANS_TIME_PROMPT,
          invalidMessage: TRANS_TIME_INVALID,
          selector: "time",
          iconTitle: TRANS_TIME_ICON
        };
        var w = new com.ibm.sr.widgets.DateTimeTextBox(props, input);
        w.setAttribute("tabindex","1");
        w.setAttribute("tabIndex","1");
        registerValidatingInput(w,input,vdbProperty);
        break;
    } // end switch
  }
}

/**
 * Stores a reference to the Dojo validating input.
 */
function registerValidatingInput(/*widget*/widget,/*DomNode*/input,/*ViewDetailBeanProperty*/vdbProperty)
{
  var inputName=input.name;
  validatingInputs[inputName]={"widget": widget, "property": vdbProperty};
  var value=input.value;
  if (value || (typeof value=='number' && value==0))
    vdbProperty.hasValue=true;
  else
    vdbProperty.hasValue=false;
  // register extra handlers
  if (widget)
  {
    widget.onChange=handleInputOnChange;
    widget.onkeyup=handleKeyInputDojo;
  }
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
  var validatingInput=validatingInputs[inputName];
  if (validatingInput)
  {
    var property=validatingInput.property;
    if (value || (typeof value=='number' && value==0))
      property.hasValue=true;
    else
      property.hasValue=false;
  }
  checkRequiredInput();
}

/**
 * Called by Dojo input fields when the value is changed.
 */
function handleInputOnChange(/*multi*/newValue)
{
  var propertyName=this.name;
  var validatingInput=validatingInputs[propertyName];
  if (validatingInput)
  {
    var property=validatingInput.property;
    if (newValue || (typeof newValue=='number' && newValue==0))
      property.hasValue=true;
    else
      property.hasValue=false;
  }
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
  if (!addDialogMode)
  {
    checkMandatoryProperties();
    checkValidProperties();
    if (mandatoryComplete && validationComplete)
      enableSave();
    else
      disableSave();
  }
}

/**
 * Checks whether the mandatory properties are correctly filled in and adjusts the
 * screen feedback accordingly.
 */
function checkMandatoryProperties()
{
  var anyBlank=false;
  if (flattenedList.length>0)
  {
    for (var i=0;i<flattenedList.length;i++)
    {
      var vdbProperty=flattenedList[i];
      var constraints=vdbProperty.constraints;
      if (constraints)
      {
        if (!vdbProperty.readOnly && vdbProperty.mandatoryValue && constraints.displayAs!="BOOLEAN" && !constraints.enumeration)
        {
          if (!vdbProperty.hasValue)
            anyBlank=true;
        }
      }
    } // end for
  }
  if (anyBlank)
    mandatoryComplete=false;
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
  for (var key in validatingInputs)
  {
    var validatingInput=validatingInputs[key];
    var widget=validatingInput.widget;
    if (widget)
    {
      if (!widget.isValid())
        anyNotValid=true;
    }
  } // end for
  if (anyNotValid)
    validationComplete=false;
  else
    validationComplete=true;
}

/**
 * Disables the main buttons on the form.
 */
function disableMainButtons()
{
  addDialogMode=true;
  var apply=document.getElementById("navigationapply");
  if (apply)
    apply.disabled=true;
  var ok=document.getElementById("navigationok");
  if (ok)
    ok.disabled=true;
  var cancel=document.getElementById("navigationcancel");
  if (cancel)
    cancel.disabled=true;
}

/**
 * Enables the main buttons on the form.
 */
function enableMainButtons()
{
  addDialogMode=false;
//  var apply=document.getElementById("navigationapply");
//  if (apply)
//    apply.disabled=false;
//  var ok=document.getElementById("navigationok");
//  if (ok)
//    ok.disabled=false;
  var cancel=document.getElementById("navigationcancel");
  if (cancel)
    cancel.disabled=false;
  checkRequiredInput();
}

/**
 * Disables the save buttons on the form.
 */
function disableSave()
{
  var apply=document.getElementById("navigationapply");
  if (apply)
    apply.disabled=true;
  var ok=document.getElementById("navigationok");
  if (ok)
    ok.disabled=true;
}

/**
 * Enables the save buttons on the form.
 */
function enableSave()
{
  var apply=document.getElementById("navigationapply");
  if (apply)
    apply.disabled=false;
  var ok=document.getElementById("navigationok");
  if (ok)
    ok.disabled=false;
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

/** 
 * Deals with exceptions thrown from the JSON RPC stuff.
 */
function processRPCException(/*object*/e)
{
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

dojo.addOnLoad(initPage);
