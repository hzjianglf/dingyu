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

var serviceregistry = {};

serviceregistry.MESSAGE_TYPE_INFORMATIONAL = "INFORMATION";
serviceregistry.MESSAGE_TYPE_WARNING = "WARNING";
serviceregistry.MESSAGE_TYPE_ERROR = "ERROR";

serviceregistry.SOURCE_HOME_PAGE = "HOME_PAGE";
serviceregistry.SOURCE_NAVIGATION_TREE = "NAVIGATION_TREE";
serviceregistry.SOURCE_MENU_BAR = "MENU_BAR";
serviceregistry.SOURCE_DETAIL_VIEW = "DETAIL_VIEW";
serviceregistry.SOURCE_COLLECTION_VIEW = "COLLECTION_VIEW";

serviceregistry.TAB_DETAILS = "details";
serviceregistry.TAB_CONTENT = "content";
serviceregistry.TAB_IMPACT_ANALYSIS = "impactanalysis";
serviceregistry.TAB_GOVERNANCE = "governance";
serviceregistry.TAB_POLICY = "policy";
serviceregistry.TAB_ACTIVITY = "activity";

serviceregistry.displayMessage = function(/* String */ message, /* type enumeration */ type) {
	// call functions in utils.js
	if (type == "INFORMATION") {
		displayInformationalMessage(message);
	} else if (type == "WARNING") {
		displayWarningMessage(message, null);
	} else {
		displayErrorMessage(message, null);
	}
}

serviceregistry.clearMessages = function() {
	// call function in utils.js
	clearMessages();
}

serviceregistry.deselectAll = function () {
	// get checkboxes array for collection view
	var form = dojo.byId("com.ibm.sr.ui.forms.CollectionForm");
    if (form) {
      // uncheck the items
      for (i = 0; i < form.length; i++) {
        if (form[i].type == "checkbox" && form[i].checked == true) {
          form[i].checked = false;
          // set style correctly
          checkChecks(form[i]);
        }
      } // FOR
    }
    
    //TODO: change style so the line is not selected!
}

serviceregistry.doJavaCustomAction = function(/* String */ fullClassName, /* Array */ bsrUris, /* map */ parameters, /* function */ successFunction) {
	// delegate
	doJavaCustomAction(fullClassName, bsrUris, parameters, successFunction);
}

serviceregistry.formatMessage = function() {
	// grab params as we have any number of inserts
	// string messageKey, string bundleName, string insert 1, string insert 2, etc
	
  var a = arguments;
  var messageKey = a[0];
  var bundleName = a[1];
  
  // get the message string
  var message = null;
  // get the map using the bundle name
  var messagesMap = serviceRegistryMessages[bundleName];
  if(messagesMap != null) {
  	// get the message using the key
  	message = messagesMap[messageKey];
  } else {
  	// throw a message
  	var errorMsg = resolveCBMessageInserts(textCustomActionMissingBundle, bundleName);
  	throw errorMsg;
  }

  if(message != null) {
  	// replace &quot; with " and others
  	  message = unescapeHTML(message);
	  if (a.length > 2)
	  {
	    for (var i=2; i<a.length; i++)
	    {
	      var insert=a[i];
	      var search="{"+(i-2)+"}";
	      var index=message.indexOf(search);
	      if (index!=-1)
	      {
	        var left=message.substring(0,index);
	        var right=message.substr(index+3);
	        message=left+insert+right;
	      }
	    } // end for
	  }
	} else {
		// throw an error
	  	var errorMsg = resolveCBMessageInserts(textCustomActionMissingKey, messageKey);
  		throw errorMsg;
	}
	
	return message;	
}

serviceregistry.startBusy = function(/* custom action object */ customActionObject) {

	if(customActionObject) {
		// html id of various things
		var htmlId = customActionObject.serviceregistryJavaScriptElementName;
		// context 
		var context = customActionObject.serviceregistryJavascriptContext;
		
		if(htmlId == null || context == null) {
			var msg = resolveCBMessageInserts(textCustomActionStartBusy, customActionObject);
			msg = unescapeHTML(msg);
			displayErrorMessage(msg, null);
			return;
		}

		// check we haven't already started busy				
		if(customActionObject.serviceregistryJavascriptOldHtml == null) {				
			var htmlElement = dojo.byId(htmlId);
			if(htmlElement) {
				if(htmlElement.type == "button") {
					// disable
					htmlElement.disabled = true;
					customActionObject.serviceregistryJavascriptOldHtml = "p";
				} else {
					// a html link - need to replace elements to disable the link
					if(context == serviceregistry.SOURCE_DETAIL_VIEW ||
						context == serviceregistry.SOURCE_HOME_PAGE ||
						context == serviceregistry.SOURCE_NAVIGATION_TREE) {
						// element is the LI
						var oldHtml = htmlElement.innerHTML;
						// set on the CA object
						customActionObject.serviceregistryJavascriptOldHtml = oldHtml;
						
						for(var i = 0; i < htmlElement.childNodes.length; i++) {
							if(htmlElement.childNodes[i].tagName == "A" 
							|| htmlElement.childNodes[i].tagName == "a") {
								// link tag
								// grab the text
								var message = null;
								if(htmlElement.childNodes[i].textContent) {
									message = htmlElement.childNodes[i].textContent;
								} else {
									message = htmlElement.childNodes[i].innerText;
								}
								
								// now re-write the contents of the <LI>
								if(context == serviceregistry.SOURCE_DETAIL_VIEW) {
									writeInactiveLink(htmlElement, message, true);
								} else {
									writeInactiveLink(htmlElement, message, false);
								}
							}
						}
					} else if(context == serviceregistry.SOURCE_MENU_BAR) {
						// element is "A"
						var liElement = htmlElement.parentNode;
						var message = null;
						if(htmlElement.textContent) {
							message = htmlElement.textContent;
						} else {
							message = htmlElement.innerText;
						}
						var oldHtml = liElement.innerHTML;

						// store old href attribute						
						var oldHref = htmlElement.getAttribute("href");
						customActionObject.serviceregistryJavascriptOldHref = oldHref;
						
						// set on the CA object
						customActionObject.serviceregistryJavascriptOldHtml = oldHtml;
						// now re-write the contents of the <LI>
						writeInactiveMenubarLink(liElement, message, htmlId);
					}
				}
			} else {
				var msg = resolveCBMessageInserts(textCustomActionHtmlId, htmlId);
				msg = unescapeHTML(msg);
				displayErrorMessage(msg, null);
			}
		} // end if old html == null
		
		// set cursor to arrow hourglass hybrid (works on FF 3 and IE 6)
		document.body.style.cursor = "progress";
	}

}

serviceregistry.stopBusy = function(/* custom action object */ customActionObject) {

	if(customActionObject) {
		// html id of various things
		var htmlId = customActionObject.serviceregistryJavaScriptElementName;
		// context 
		var context = customActionObject.serviceregistryJavascriptContext;

		if(htmlId == null || context == null) {
			var msg = resolveCBMessageInserts(textCustomActionStopBusy, customActionObject);
			msg = unescapeHTML(msg);
			displayErrorMessage(msg, null);
			return;
		}
		
		// check we haven't already stopped busy
		if(customActionObject.serviceregistryJavascriptOldHtml != null) {
			var htmlElement = dojo.byId(htmlId);
			if(htmlElement) {
				if(htmlElement.type == "button") {
					// enable
					htmlElement.disabled = false;
					customActionObject.serviceregistryJavascriptOldHtml = null;
				} else {
					// a html link - need to replace elements to enable the link
					if(context == serviceregistry.SOURCE_DETAIL_VIEW ||
						context == serviceregistry.SOURCE_HOME_PAGE ||
						context == serviceregistry.SOURCE_NAVIGATION_TREE) {
						// element is the LI
						var oldHtml = customActionObject.serviceregistryJavascriptOldHtml;
						customActionObject.serviceregistryJavascriptOldHtml = null;
						
						// set on the element
						htmlElement.innerHTML = oldHtml;
					} else if(context == serviceregistry.SOURCE_MENU_BAR) {
						// element is the "span" we wrote in startBusy()
						var liElement = htmlElement.parentNode;
						var oldHtml = customActionObject.serviceregistryJavascriptOldHtml;
						customActionObject.serviceregistryJavascriptOldHtml = null;
						var href = customActionObject.serviceregistryJavascriptOldHref;
						customActionObject.serviceregistryJavascriptOldHref = null;

						// get message from span
						var message = null;
						if(htmlElement.textContent) {
							message = htmlElement.textContent;
						} else {
							message = htmlElement.innerText;
						}
	
						// use DOM to write the link out for IE bug
						// remove old nodes
						for(var i = 0; i < liElement.childNodes.length; i++) {
							liElement.removeChild(liElement.childNodes[i]);
						}
						// make a A node
						var linkElement = document.createElement("a");
						linkElement.setAttribute("href", href);
						linkElement.setAttribute("id", htmlId);
						messageNode = document.createTextNode(message);
						linkElement.appendChild(messageNode);
						
						liElement.appendChild(linkElement);
					}
				}
			} else {
				var msg = resolveCBMessageInserts(textCustomActionHtmlId, htmlId);
				msg = unescapeHTML(msg);
				displayErrorMessage(msg, null);
			}
		} // end if old html != null
			
		// set cursor to normal
		document.body.style.cursor = "auto";
	}
}

serviceregistry.navigation = {};
serviceregistry.navigation.reloadCurrentPage = function() {
	// reload the current page, using the crumb URL - replace the current page in the browser history
	window.location.replace(customActionPageRefresh); 
}

// go to the detail view, optionally on the specified tab
serviceregistry.navigation.showDetail = function(bsrUri, tabName) {
	var detailUri = contextRoot + "/ViewDetail.do?uri=" + bsrUri;
	if(typeof tabName != "undefined") {
		detailUri += "&tabName=" + tabName;
	}
	window.location = detailUri;
}

// go to the previous page
serviceregistry.navigation.previousPage = function() {
	// navigate using the global variable
	window.location = customActionPagePrevious;
}

// run the specified view query - viewQueryName does not need special characters to be URL encoded
serviceregistry.navigation.viewQuery = function(viewQueryName) {
	var escapedVQName = escape(viewQueryName);
	var vqUrl = contextRoot + "/ShowViewQuery.do?viewQueryId=" + escapedVQName;
	window.location = vqUrl;
}

// go to the home page
serviceregistry.navigation.homePage = function() {
	// set directly
	window.location = contextRoot + "/HomePage.do";
}

// turn the provided URL into one which goes via the proxy
serviceregistry.resolveExternalUrl = function(url) {
	//TODO: this
}

serviceregistry.getUserName = function() {
	// return already set variable (from the header)
	return user_name;
}

function CustomActionContext() {
	this.items = new Array();
	this.source = "";
}

