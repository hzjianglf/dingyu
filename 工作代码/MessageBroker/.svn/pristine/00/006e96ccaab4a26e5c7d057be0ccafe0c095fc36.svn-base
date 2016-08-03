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

var count = 0;
var lastWidth = 0;

//This code optimizes the dojox.string.Builder class so that rendering a large policy file
//in IE is done in <1 sec rather than 20/30+. Should imrpove firefox and others a little too.
//Note now that the append function cannot take multiple arguments like it could in the
//original version.
if (dojo.isIE) {
	dojo.extend(dojox.string.Builder, {
		append: function(/*String*/sr){ 
			// summary: Append just one argument to the end of the buffer 
				this.b[this.b.length] = sr;
			}	
	});
} else {
	dojo.extend(dojox.string.Builder, {
		append: function(/*String*/sr){ 
			// summary: Append just one argument to the end of the buffer 
				this.b += sr;		
			}
	});
}

// This must be created in the onLoad functions of policy.js and policyAttachment.js
// as creating it here seems to randomly fail on firefox 3.
var policyService = null;

UIEnum = function(value){	
	this.value = value;
}

var inProgress = false;
var scrollDivId=null;

function makeScrollable(divID) {
	var table=document.getElementById(divID);
	if (table)
	{
    scrollDivId=divID;
	  var tbody=table.getElementsByTagName("tbody");
	  if (tbody)
	  {
	    if (document.defaultView)
	    {
	      var style=document.defaultView.getComputedStyle(tbody[0], null).getPropertyValue("height");
	      style=style.substring(0,style.length-2);
	      if (style>170)
	        tbody[0].style.height='170px';
	      else
	      	tbody[0].style.height='';
	    }
	  }
    adjustScrollbox();
	}
}

function makeBusyDefault(elemName, waitDefault, trueFalse) {

	var classMatch = "AJAXControl";
	
	// Make all buttons and links busy.	
	var elems=document.getElementsByTagName(elemName);
	//var debug=document.getElementById("debug");
	for (i=0; i<elems.length;i++) {	

		if (classMatch.match(elems[i].className) == classMatch && elems[i].name != "cancel") { 
			elems[i].style.cursor = waitDefault;
			
			//if (elemName == "a")
				//debug.innerHTML = debug.innerHTML + elems[i].href + " == " + classMatch.match(elems[i].className) + "<br/>"; 
	
			if (trueFalse == "true")
				elems[i].disabled = false;
			else
				elems[i].disabled = true;
		}
	}
}

function startTransaction() {
	inProgress = true;
	
	//This tooltip hiding is only for Policy Authoring
	var nameElem = document.getElementById("inputID_-11");	
	if (nameElem != null)		
		dijit.hideTooltip(nameElem);		
	
	// Make all AJAX buttons and links busy.	
	makeBusyDefault("input","wait","false");
	//makeBusyDefault("a","wait","false");		
	//makeBusyDefault("img","wait","false");		
}

function endTransaction(details, exception) {

	//This tooltip hiding is only for Policy Authoring
	var nameElem = document.getElementById("inputID_-11");	
	if (nameElem != null)		
		dijit.hideTooltip(nameElem);		

	//Always clear the error div here
	var messageBox=document.getElementById("msgannounce");	
	messageBox.style.display="none";         // make the box invisible

	inProgress = false;
	
	// Make all AJAX buttons and links default.	
	makeBusyDefault("input","","true");
	//makeBusyDefault("a","","true");		
	//makeBusyDefault("img","","true");		
	
	//If this is the main auth bean then drill down to the details
	if (details != null && details.details != null)
		details = details.details;
		
	if (exception)
	{
	  var text="";
	  for (var key in exception)
	  {
	    text=text+" "+key+": "+exception[key]+"\n";
	  } // end for
	  displayError(textAJAXError+" "+text,null);
	  return true;
	}
	else if (details != null && details.errorCondition)
	{ // JSON action threw an exception
	  displayError(details.errorMessage,details.errorExtra);
	  return true;
	} else if (details == null) {
	  //This means that the session has expired.
	  displayError(textPolicyExpired,"");
	}
	
	return false;	
}

function addCallErrBack(obj,callBack,errBack) {
	startTransaction();
	obj.addCallback(callBack);
	obj.addErrback(errBack);

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
 * Deals with exceptions thrown from the JSON RPC stuff.
 */
function processRPCException(/*object*/e)
{
  var msg=textAJAXError+" "+e.message;
  if (e.fileName)
    msg=msg+"; "+TRANS_SOURCE_FILE+" "+e.fileName;
  if (e.lineNumber)
    msg=msg+"; "+TRANS_FAILING_LINE+" "+e.lineNumber;
  displayError(msg,null);
}

function errorBack(err) {

	//For some reason IE sometimes calls the proper callback and then also this one
	//which sounds like a lot of nonsense to me. So if we check inProgress then
	//we don;t process the error if the normal func got called already.
	if (inProgress == true) {
		stopMessageTimer();
		endTransaction(null);	
		stopProgress();
		processRPCException(err);
	}
}

// message timer
var messageTimerInterval = 200;
var messageTimer;
var dots = ["", ".", "..", "...", "....", "....."];
var dotsIndex = 0;

/**
 * The initializeMessageTimer simply initializes the messageTimer!!!
 */
function initializeMessageTimer() {

	messageTimer = setTimeout(updateMessage, messageTimerInterval);
}

function stopMessageTimer() {
	// stop message timer
	if (messageTimer) {
		clearTimeout(messageTimer);
		messageTimer = null;
	}
}

/**
 * The updateMessage function animates the ... on the "Fetching filters..."
 * message in order to provide some feedback to the user.  This function is
 * invoked when the messageTimer expires.
 */
function updateMessage() {

	// Retrieve the div for the "rendering" message
	var messageNode = document.getElementById("renderingMessage");
	
	// Set the message new value for the message
	if (++dotsIndex == dots.length) {
		dotsIndex = 0;
	}
	messageNode.firstChild.nodeValue = renderingMessage + dots[dotsIndex];
	
	// Reschedule the timer
	messageTimer = setTimeout(updateMessage, messageTimerInterval);
}

var timerRunning = false;
var timer = null;

/**
 * calculate the padding size in Ems for a given row level
 */
function calculatePaddingEmSize(/*int*/level) {
	return level + 1.5;
}

/**
 * Ellipsise the rows again because the browser has been resized.
 * Seem to have to set a time out on IE to do this.
 */
function ellipsiseRows()  {

 
	if ( dojo.isIE) {		
		if (lastWidth == 0 || lastWidth != document.body.clientWidth)
		{
			lastWidth = document.body.clientWidth;
		
			count++;
			if (timerRunning) 
				clearTimeout(timer);
			
			timer = setTimeout("ellipsiseRowsInt()",500);		
			timerRunning = true;
		}			
	} else {
		ellipsiseRowsInt();
	}
}

function setFocus(elemID) {

	var element = document.getElementById(elemID);		
	
	if (element != null) {
		element.focus();	
		return true;
	}
	
	return false;
}

function initPageEvents()
{
  if (dojo.isIE==6)
  {
    if (typeof window != "undefined")
      dojo.connect(window, 'onresize', 'handleWindowResize');  // window resize
  }
}

var lastClientSize={width: 0, height: 0};

function adjustScrollbox()
{
  if (dojo.isIE==6)
  {
    if (scrollDivId!=null)
    {
    	var table=document.getElementById(scrollDivId);
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
        lastClientSize=size;
      }
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

function filterHTML(message) {

		var sb = message;
		var entities = [ "&amp;", "&lt;", "&gt;",  "&quot;", "&#39;" ];

	    var index = 0;
	    while (index < sb.length) {
	        var ch = sb.charAt(index);
	        var c = 0;
	        	      
	        // the values set here are the index into specials and entities
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

dojo.addOnLoad(initPageEvents);

