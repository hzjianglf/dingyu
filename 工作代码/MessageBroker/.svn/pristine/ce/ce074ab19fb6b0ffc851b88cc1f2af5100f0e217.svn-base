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

// This must be created in the onLoad functions
// as creating it here seems to randomly fail on firefox 3.
var customActionService = null;
var javaActionCustomActionObject = null;

var inProgress = false;
var globalSuccessFunction = null;

/**
 * displayAfterLoad is called as soon as the page has loaded. 
 */
function displayAfterLoad() {	
	//Only create the service if we have any async Java Actions
	if (customActionService == null && typeof(customActionFlags) != "undefined" && customActionFlags.haveAsyncJavaAction == "true") {	
		customActionService = new dojo.rpc.JsonService(contextRoot + "/RPCAdapter/jsonrpc/CustomActionService");	
	}
}

function addCallErrBack(obj,callBack,errBack) {
	obj.addCallback(callBack);
	obj.addErrback(errBack);

}

function errorBack(err) {

	//For some reason IE sometimes calls the proper callback and then also this one
	//which sounds like a lot of nonsense to me. So if we check inProgress then
	//we don;t process the error if the normal func got called already.
	if (inProgress == true) {
		processRPCException(err);
		inProgress = false;
	}
}

/** 
 * Deals with exceptions thrown from the JSON RPC stuff.
 */
function processRPCException(/*object*/e)
{
  var msg=textAJAXError+" "+e.message;
  if (e.fileName)
    msg=msg+"; "+TRANS_SOURCE_FILE+" "+e.fileName;
  if (e.lineNumber)
    msg=msg+"; "+TRANS_FAILING_LINE+" "+e.lineNumber;
  displayErrorMessage(msg,null);
}

function getCurrentBsrUris() {
	var	bsrUris = new Array();
	
    var form = dojo.byId("com.ibm.sr.ui.forms.CollectionForm");
    var detailUri = dojo.byId("detailUri");
    if (form) {
      for (i = 0; i < form.length; i++) {
        if (form[i].type == "checkbox" && form[i].checked == true) {
          bsrUris.push(form[i].value);
        }
      } // FOR
    } else if (detailUri) {
    	bsrUris.push(detailUri.value);
    }
	
	return bsrUris;
}

function clearCheckboxes() {
    var form = dojo.byId("com.ibm.sr.ui.forms.CollectionForm");
    if (form) {
      for (i = 0; i < form.length; i++) {
        if (form[i].type == "checkbox" && form[i].checked == true) {
          form[i].checked = false;
          findParentRow(form.elements[i],"table-row")
        }
      } // FOR
    }
}

function doJavaCustomAction(fullClassName, bsrUris, parameters, successFunction) {
	
	if (inProgress == false) {
		inProgress = true;		
		if (parameters == undefined)
			parameters = null;

		javaActionCustomActionObject = null;
		
		globalSuccessFunction = successFunction;
		addCallErrBack(customActionService.doJavaCustomAction(fullClassName, bsrUris, parameters),doneJavaCustomAction,errorBack);	
	}
}

function doJavaCustomActionWithBusy(fullClassName, bsrUris, htmlID, source, parameters) {
	
	if (inProgress == false) {
		inProgress = true;		
		if (parameters == undefined)
			parameters = null;

		//create the busy indicator context object
		javaActionCustomActionObject = Array();
		javaActionCustomActionObject.serviceregistryJavaScriptElementName = htmlID;
		javaActionCustomActionObject.serviceregistryJavascriptContext = source;
		
		//start the busy indicator	
		serviceregistry.startBusy(javaActionCustomActionObject);
			
		globalSuccessFunction = null;
		addCallErrBack(customActionService.doJavaCustomAction(fullClassName, bsrUris, parameters),doneJavaCustomAction,errorBack);	
	}
}

function doJavaCustomActionSubmit(actionIndex) {

	var hidden=document.getElementById("submitactionhidden");
	
	if (! hidden)		
		hidden=document.getElementById("submitactionhiddenfoot");
		
	if (hidden) {
		hidden.value="CustomAction_" + actionIndex;
  		hidden.name=hidden.value;
  	
		// send the request
		if (document.DetailForm)
			document.DetailForm.submit();						
		else if (document.CustomActionForm)
			document.CustomActionForm.submit();
	}
}

function displayMessage(/* { text ,type} */ msg) {
	if (msg) {
		if (msg.type == "INFORMATION")
			displayInformationalMessage(msg.text);
		else if (msg.type == "WARNING")
			displayWarningMessage(msg.text, null);
		else 
			displayErrorMessage(msg.text, null);
	}
}

function displayMessages(/* array of {text, type} */ messages) {
	if (messages)
		for(msg in messages)
			displayMessage(messages[msg]);
}

function doneJavaCustomAction(customActionRC) {
	inProgress = false;

	//stop the busy indicator	
	if (javaActionCustomActionObject != null) {
		serviceregistry.stopBusy(javaActionCustomActionObject);
		javaActionCustomActionObject = null;
	}
	
	if (customActionRC)
	{
		if (customActionRC.errorCondition)
		{
			clearMessages();
			displayErrorMessage(customActionRC.errorMessage,customActionRC.errorExtra);
		} else {
			if (globalSuccessFunction)
				globalSuccessFunction(customActionRC.rc);
			else {
			  	clearMessages();
				displayMessages(customActionRC.rc.messages);  				
			}
		}
	}
	
	clearCheckboxes();
}

// do the JS custom action
function doJSCustomAction(/* action code object */ actionObject) {

	// check in progress - if so, do nothing
	if (inProgress == false) {
		// get the context from the action object
		var customJSActionContext = actionObject.serviceregistryJavascriptContext;
		// get page data - diff depending on detail vs collection
		var pageData = customJSActionGetDataFromPage(false, customJSActionContext);
		
		// wrap in try catch
		try {
			actionObject.execute(pageData);
		} catch(err) {
			// error in execute code - show error in messages portlet
			var msg = resolveCBMessageInserts(textCustomActionExecuteError, err);
			msg = unescapeHTML(msg);		
			displayErrorMessage(msg, null);
		}
	}
}

// call init and show the custom button if it returns true
function initCustomJSAction(/* String id of action object on page */ customActionHtmlId, /* action code object */ actionObject, /* collection or detail etc */ context, /* string or null */ initParams) {
	// grab the HTML object on the page
	var customActionHtml = dojo.byId(customActionHtmlId);

	// get data for page
	var pageData = customJSActionGetDataFromPage(true, context);

	// call constructor here - wrap in try catch
	var toShow = false;
	try {
		toShow = actionObject.init(initParams, pageData);
	}catch(err) {
		// error in init code - show error in messages portlet
		var msg = resolveCBMessageInserts(textCustomActionInitError, customActionHtmlId, err);
		msg = unescapeHTML(msg);		
		displayErrorMessage(msg, null);
	}
	
	if(toShow == true) {
		// show custom action html once it is ready - set to empty to inherit container styles
		customActionHtml.style.display = "";
	} else {
		if(context == serviceregistry.SOURCE_MENU_BAR) {
			// menu bar links are shown by default so must be disabled
			// if in a menu bar the html object is the <a> tag
			
			// set the href to # so the link does nothing
			customActionHtml.href = "#";
			
			// set the class of the parent <li> to inactive so the appearance changes
			var liElement = customActionHtml.parentNode;
			// use dojo method 
			dojo.addClass(liElement, "inactive");
		} else if(context == serviceregistry.SOURCE_NAVIGATION_TREE) {
			// nav tree links should be shown but disabled
			// element is the LI
			customActionHtml.style.display = "";
			
			// get the text and rewrite the A as a span
			for(var i = 0; i < customActionHtml.childNodes.length; i++) {
				if(customActionHtml.childNodes[i].tagName == "A" 
				|| customActionHtml.childNodes[i].tagName == "a") {
					// link tag
					// grab the text
					var message = null;
					if(customActionHtml.childNodes[i].textContent) {
						message = customActionHtml.childNodes[i].textContent;
					} else {
						message = customActionHtml.childNodes[i].innerText;
					}
					
					// now re-write the contents of the <LI>
					writeInactiveNavTreeLink(customActionHtml, message);
				}
			}
		}
		// else don't hide ca html - its hidden anyway
	}
	
	// store the id of the html element onto the object for busy
	actionObject.serviceregistryJavaScriptElementName = customActionHtmlId;
	
}

// Get data for a button call from the page
function customJSActionGetDataFromPage(/* boolean */ isInit, /* string */ context) {
	// need to know type of page first
	
	var data = new CustomActionContext();
	if(context == serviceregistry.SOURCE_COLLECTION_VIEW) {
		// collection view - data is list of bsr uris of selected items, or if isInit = true, 
		// all bsruris shown on the page.
		var form = dojo.byId("com.ibm.sr.ui.forms.CollectionForm");
	    if (form) {
      		// go through all the checkboxes
      		var numSelectedItems = 0;
      		for (i = 0; i < form.length; i++) {
        		if (form[i].type == "checkbox") {
        			if(isInit) {
        				// if init we include all items
						data.items.push(form[i].id);
        			} else {
        				// otherwise only if the item is checked
        				if(form[i].checked == true) {
							data.items.push(form[i].id);
        				}
		        	}
        		}
	      	} // FOR
		}
		
	} else if (context == serviceregistry.SOURCE_DETAIL_VIEW) {
		// detail view
		var detailUriInput = dojo.byId("detailUri");
		if(detailUriInput) {
			data.items.push(detailUriInput.value);
		}
	}
	// for other types there are no items given
	
	// set the source
	data.source = context;

	return data;
}

/**
 * Takes a raw message and fills in any inserts.
 * Param 0 = message
 * Params 1+ = inserts {0}, {1}, etc
 */
function resolveCBMessageInserts() {
//TODO: resolveMessageInserts() exists in policy.js which is the same code! Needs consolidating

  var a=arguments;
  var newMsg=a[0];
  if (a.length>1)
  {
    for (var i=1;i<a.length;i++)
    {
      var insert=a[i];
      var search="{"+(i-1)+"}";
      var index=newMsg.indexOf(search);
      if (index!=-1)
      {
        var left=newMsg.substring(0,index);
        var right=newMsg.substr(index+3);
        newMsg=left+insert+right;
      }
    } // end for
  }
  return(newMsg);
}

function unescapeHTML(/* string */ html) {
	var node = document.createElement("div");
	node.innerHTML = html;
	var string = null;
	if(node.innerText) {
		string = node.innerText;
	} else {
		string = node.textContent;
	}
	return string;
}

// write an inactive link as the innerHTML of the element
function writeInactiveLink(htmlElement, message, isDetailView) {
	var html = "";
	if(isDetailView) {
		html = "\n";
	}
	html += "<span class=\"busyCustomAction\">" + message + "</span>";
	htmlElement.innerHTML = html;
}

// write an inactive link as the innerHTML of the element for the menu bar
function writeInactiveMenubarLink(htmlElement, message, htmlId) {
	// need to write the id into the span here
	htmlElement.innerHTML = "<span class=\"busyCustomAction\" id=\"" + htmlId + "\">" + message + "</span>";
}

// write an inactive nav tree link as the innerHTML of the element
function writeInactiveNavTreeLink(htmlElement, message) {
	var html = "";
	html += "<span class=\"inactiveCustomAction\">" + message + "</span>";
	htmlElement.innerHTML = html;
}

dojo.addOnLoad(displayAfterLoad);

