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

dojo.require("dijit.dijit");

// progress variables
var progressElementId = null;
var progressTimer = null;
var progressMessage = null;
var progressFunction = null;
var progressOldHtml = null;
// number of ms before we show a progress spinner
var SHOW_PROGRESS_TIMER = 2000;

var enableDisableArray=new Array();
var messageCount=0;

function toggleSection(/*string*/item)
{
  var block=document.getElementById("child_"+item);
  var img=document.getElementById("img_"+item);
  var style="";
  if (document.defaultView)
    style=document.defaultView.getComputedStyle(block, null).getPropertyValue("display");
  else if (block.currentStyle)
    style=block.currentStyle.display;
  if (style=="none")
  {
    var srcURL=img.src;
    if (srcURL.indexOf("title_maximize.gif")!=-1)
    {
      block.style.display="";
      img.src="theme/"+currentTheme+"/images/title_minimize.gif";
    }
    else
    {
      block.style.display="block";
      img.src="theme/"+currentTheme+"/images/expanded.gif";
    }
    img.alt=TRANS_GENERAL_MSG_COLLAPSE;
    img.title=TRANS_GENERAL_MSG_COLLAPSE;
  }
  else
  {
    block.style.display="none";
    var srcURL=img.src;
    if (srcURL.indexOf("title_minimize.gif")!=-1)
      img.src="theme/"+currentTheme+"/images/title_maximize.gif";
    else
      img.src="theme/"+currentTheme+"/images/collapsed.gif";
    img.alt=TRANS_GENERAL_MSG_EXPAND;
    img.title=TRANS_GENERAL_MSG_EXPAND;
  }
}

function forceExpandSection(/*string*/item)
{
  var block=document.getElementById("child_"+item);
  var img=document.getElementById("img_"+item);
  var style="";
  if (document.defaultView)
    style=document.defaultView.getComputedStyle(block, null).getPropertyValue("display");
  else if (block.currentStyle)
    style=block.currentStyle.display;
  if (style!="block")
  {
    block.style.display="block";
    img.src="theme/"+currentTheme+"/images/expanded.gif";
    img.alt=TRANS_GENERAL_MSG_COLLAPSE;
    img.title=TRANS_GENERAL_MSG_COLLAPSE;
  }
}

function forceCollapseSection(/*string*/item)
{
  var block=document.getElementById("child_"+item);
  var img=document.getElementById("img_"+item);
  var style="";
  if (document.defaultView)
    style=document.defaultView.getComputedStyle(block, null).getPropertyValue("display");
  else if (block.currentStyle)
    style=block.currentStyle.display;
  if (style=="block")
  {
    block.style.display="none";
    img.src="theme/"+currentTheme+"/images/collapsed.gif";
    img.alt=TRANS_GENERAL_MSG_EXPAND;
    img.title=TRANS_GENERAL_MSG_EXPAND;
  }
}

function showHideDetails(value)
{
  // Turn off row0 when an item is selected (it shows only
  // when nothing is selected).
  if (document.getElementById("row0") != null) {
    document.getElementById("row0").style.display = "none";
  }
  var index=value.indexOf(",");     // value is supplied as "rownum,totalrows"
  var objectNum=parseInt(value.substring(0,index));
  var total=parseInt(value.substring(index+1));
  for (i=1;i<=total;i++)
  {                                 // iterate all rows
    var objectId="row"+i;
    var element=document.getElementById(objectId);
    if (i==objectNum)
    {                               // this is the one to show
      if (element != null) {
        element.style.display = "";
      }
    }
    else
    {                               // hide all the others
      if (element != null) {
        element.style.display = "none";
      }
    }
  } // end for
}

function enableDisable(controlSet,origin)
{
  if (origin != "reset")
    enableDisableArray[enableDisableArray.length] = controlSet;
  controlArray = controlSet.split(",");
  // set up variable default values
  disStatus = false;
  textStyle = "textEntry";
  textColor = "#000000";
  textAreaStyle = "textAreaEntry";
  // loop through controls to assign enablement/disablement to children
  for (i=0;i<controlArray.length;i++)
  {
    tmp = controlArray[i];
    cArray = tmp.split(":");
    // if this control is a master of 1 or more controls 
    if (cArray.length > 1)
    {
      // master control is at 0
      masterControlId = cArray[0];
      masterControlStatus = document.getElementById(masterControlId).checked;
      // determine variable values for children based on master attribute
      // CHECKED attribute applies to both checkbox and radio buttons
      // For radio buttons, make sure ID attribute is assigned to the value of that
      // specific radio button
      if (masterControlStatus)
      {
        disStatus = false;
        textColor = "#000000";
      }
      else
      {
        disStatus = true;
        textColor = "#CCCCCC";
      }
      for (j=1;j<cArray.length;j++)
      {
        // If child control is required, then "+Required" follows child control id
        // If child control is long, then "+Long" follows
        rcArray = cArray[j].split("+");
        if (rcArray.length > 1)
        {
          textStyle = textStyle+rcArray[1];
          cArray[j] = rcArray[0];
        }
        // assign child control styles according to type
        var element=document.getElementById(cArray[j]);
        element.parentNode.style.color = textColor;
        element.disabled = disStatus;                
      }
    }
  }
}

function togglevis (o)
{
  if (document.getElementById(o).style.display == "none")
  {
    document.getElementById(o).style.display = "block";
    return;
  }
  if (document.getElementById(o).style.display == "block")
  {
    document.getElementById(o).style.display = "none";
    return;
  } 
  if (document.getElementById(o).style.display == "")
  {
    document.getElementById(o).style.display = "none";
    return;
  }
}

/**
 * Takes a raw message and fills in any inserts
 */
function resolveMessageInserts()
{
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

function showAtCursor(e,id,tooltipsize)
{
  var obj = document.getElementById(id);
  obj.style.display = "block";
  var corners={"BL":"TL","BR":"TR","TL":"BL","TR":"BR"};
  if (isRTL)
    corners={"BR":"TR","BL":"TL","TR":"BR","TL":"BL"};
  placeAroundRect(obj,{x:e.clientX-2, y:e.clientY-2, width:25, height:25},corners);
}

/**
 * Displays the specified error message dynamically in the messages portlet at
 * the top of the page.
 */
function displayErrorMessage(/*string*/errorMsg, /*string*/errorExtra) {
	displayWarningOrErrorMessage(/*string*/errorMsg, /*string*/errorExtra, true);
}

/**
 * Displays the specified warning message dynamically in the messages portlet at
 * the top of the page.
 */
function displayWarningMessage(/*string*/warnMsg, /*string*/warnExtra) {
	displayWarningOrErrorMessage(/*string*/warnMsg, /*string*/warnExtra, false);
}

/**
 * Displays the specified error message dynamically in the messages portlet at
 * the top of the page.
 */
function displayWarningOrErrorMessage(/*string*/errorMsg, /*string*/errorExtra, isError) {

  // Make sure that we have a valid error message
  if (errorMsg) {
  
    // Retrieve the messages portlet div and make sure that it is not hidden
    var messagesPortletDiv = document.getElementById("messagePortletDiv");
    if (messagesPortletDiv) {
      messagesPortletDiv.className = "messagePortlet visible";
      /*
       * Now retrieve the inner block that contains the actual messages and
       * insert a row at the bottom of the table for the new message.
       * Note that it's not actually a table any more.
       */ 
      var messagesTable = document.getElementById("messagesTable");
      if (messagesTable) {
        var id = "inlineMessages" + messageCount;
        var titleDiv = document.createElement("div");
        titleDiv.className="sectiondivider";
        titleDiv.setAttribute("id",id);
        
        // Now create all of the relevant content for the message
        if (errorExtra && errorExtra.length > 0) {
          /*
           * If there is an inline message the first cell contains a twisty
           * that enables the user to expand and collapse the section that
           * contains the additional information.
           */
          var titleTable = document.createElement("table");
          titleTable.className="messagetitletable";
          titleTable.setAttribute("border","0");
          titleTable.setAttribute("cellpadding","0");
          titleTable.setAttribute("cellspacing","0");
          titleDiv.appendChild(titleTable);
          var row=titleTable.insertRow(-1);
          var cell1=row.insertCell(0);
          cell1.setAttribute("width","1%");
          
          var errorMsgTwiddleAnchor = document.createElement("a");
          errorMsgTwiddleAnchor.setAttribute("href", "javascript:toggleSection('" + id + "')");
          errorMsgTwiddleAnchor.setAttribute("tabindex", "1");
          
          var errorMsgTwiddleImg = document.createElement("img");
          errorMsgTwiddleImg.setAttribute("src", "theme/"+currentTheme+"/images/collapsed.gif");
          errorMsgTwiddleImg.setAttribute("id", "img_"+id);
          errorMsgTwiddleImg.setAttribute("alt", TRANS_GENERAL_MSG_EXPAND);
          
          errorMsgTwiddleAnchor.appendChild(errorMsgTwiddleImg);
          
          cell1.appendChild(errorMsgTwiddleAnchor);

          var cell2 = row.insertCell(1);
          cell2.className = "nopad";
          cell2.setAttribute("width","99%");
        }
        
        var errorMsgImg = document.createElement("img");
        var errorMsgSpan = document.createElement("span");
        
        if (isError) {
	        errorMsgImg.setAttribute("src", "theme/"+currentTheme+"/images/Error.gif");
    	    errorMsgImg.setAttribute("alt", TRANS_GENERAL_ERROR_ALT);
	        errorMsgSpan.className = "validation-error";
    	} else {
	        errorMsgImg.setAttribute("src", "theme/"+currentTheme+"/images/Warning.gif");
    	    errorMsgImg.setAttribute("alt", TRANS_GENERAL_WARNING_ALT);    	
            errorMsgSpan.className = "validation-warn-info";
    	}
    	
        errorMsgSpan.appendChild(errorMsgImg);
        errorMsgSpan.appendChild(document.createTextNode(errorMsg));
        
        /*
         * If there is an inline message, the text of the message goes in the
         * cell of the title message
         */
        if (errorExtra && errorExtra.length > 0) {
          // Add the message to the cell
          cell2.appendChild(errorMsgSpan);
        } else {
          // Add the span to the title
          titleDiv.appendChild(errorMsgSpan);
        }
        messagesTable.appendChild(titleDiv);
        
        /*
         * Finally, if there is an inline message, we need to create an
         * additional table body section that will be shown/hidden when the
         * twisty or the parent message is clicked.
         */
        if (errorExtra && errorExtra.length > 0) {
          var inlineMsgDiv = document.createElement("div");
          inlineMsgDiv.id = "child_" + id;
          inlineMsgDiv.className="expandablesection collapsed";
          var inlineMsgDivText = document.createElement("div");
          inlineMsgDivText.className="messages-submessages";
          inlineMsgDivText.appendChild(document.createTextNode(errorExtra));
          inlineMsgDiv.appendChild(inlineMsgDivText);
          
          messagesTable.appendChild(inlineMsgDiv);
        }
        messageCount+=1;
      } // IF - messagesTables
    } // IF - messagesPortletDiv
  } // IF - errorMsg
}

/**
 * Displays the specified informational message dynamically in the messages
 * portlet at the top of the page.
 */
function displayInformationalMessage(/*string*/infoMsg) {

  // Make sure that we have a valid informational message
  if (infoMsg) {
  
    // Retrieve the messages portlet div and make sure that it is not hidden
    var messagesPortletDiv = document.getElementById("messagePortletDiv");
    if (messagesPortletDiv) {
      messagesPortletDiv.className = "messagePortlet visible";
      
      /*
       * Now retrieve the inner table that contains the actual messages and
       * insert a row at the bottom of the table for the new message.
       */ 
      var messagesTable = document.getElementById("messagesTable");
      if (messagesTable) {
        var id = "inlineMessages" + messageCount;
        var titleDiv = document.createElement("div");
        titleDiv.className="sectiondivider";
        titleDiv.setAttribute("id",id);
        
        var infoMsgImg = document.createElement("img");
        infoMsgImg.setAttribute("src", "theme/"+currentTheme+"/images/Information.gif");
        infoMsgImg.setAttribute("alt", TRANS_GENERAL_INFO_ALT);

        var infoMsgSpan = document.createElement("span");
        infoMsgSpan.className = "validation-warn-info";
        infoMsgSpan.appendChild(infoMsgImg);
        infoMsgSpan.appendChild(document.createTextNode(infoMsg));
        
        // Add the span to the div
        titleDiv.appendChild(infoMsgSpan);
        messagesTable.appendChild(titleDiv);
        
        messageCount+=1;
      } // IF - messagesTables
    } // IF - messagesPortletDiv
  } // IF - infoMsg
}

/**
 * Hides the error message in the message box at the top of the page
 */
function clearMessages() {

  // Retrieve the messages portlet div and make sure that it is not hidden
  var messagesPortletDiv = document.getElementById("messagePortletDiv");
  if (messagesPortletDiv) {
    messagesPortletDiv.className = "messagePortlet invisible";

    var messagesTable = document.getElementById("messagesTable");
    if (messagesTable) {
      messagesTable.innerHTML="";
    }
  }
  messageCount=0;
}

/*
 * Start the progress timer to show the progress indicator,
 * in the provided Div with the provided message.
 *
 * The div is looked up when the progress message is shown, 
 * not before.
 */
function startProgress(/*element*/ elementId, /*string*/ message) {
  // get element id to show progress in
  progressElementId = elementId;

  // cancel previous timer if any
  if (progressTimer) {
    clearTimeout(progressTimer);
    progressTimer = null;
  }

  progressMessage = message;
  
  // set timeout to start spinner
  progressTimer = setTimeout(showProgress, SHOW_PROGRESS_TIMER);
}

/*
 * Start the progress timer to show the progress indicator,
 * in the provided Div with the provided message, calling the 
 * provided function before showing the indicator.
 *
 * The div is looked up when the progress message is shown, 
 * not before. Therefore the provided function can alter
 * the HTML before the progress is shown.
 *
 */
function startProgressFunction(/*element*/ elementId, /*string*/ message, /* function */ theProgressFunction) {
  // get element to show progress in
  progressElementId = elementId;

  // save function
  progressFunction = theProgressFunction;
  
  // cancel previous timer if any
  if (progressTimer) {
    clearTimeout(progressTimer);
    progressTimer = null;
  }

  progressMessage = message;
  
  // set timeout to start spinner
  progressTimer = setTimeout(showProgressFunction, SHOW_PROGRESS_TIMER);
}

/*
 * Same as above but start immediately.
 */
function startProgressImmediate(/*element*/ elementId, /*string*/ message) {
  // get element to show progress in
  progressElementId = elementId;
  
  // cancel previous timer if any
  if (progressTimer) {
    clearTimeout(progressTimer);
    progressTimer = null;
  }

  progressMessage = message;

  // show
  showProgress(); 
}

/*
 * Stop the progress timer and/or hide the div
 * Can be called when startProgress() has not been
 * called.
 */
function stopProgress() {
  // cancel previous timer if any
  if (progressTimer) {
    clearTimeout(progressTimer);
    progressTimer = null;
  }

  // hide the message
  if(progressElementId != null) {
    var progressElement = document.getElementById(progressElementId);
    if(progressElement != null) {
      // restore old html if we have some
      if(progressOldHtml != null) {
        progressElement.innerHTML = progressOldHtml;
      } else {
        progressElement.innerHTML = "";
      }
    }
  }
    
  // clear globals
  progressElementId = null;
  progressMessage = null;
  progressFunction = null;
  progressOldHtml = null;
}

// show the spinner and message
function showProgress() {
  // get element
  var progressElement = document.getElementById(progressElementId);

  // save old html
  if(progressElement != null) {
    progressOldHtml = progressElement.innerHTML;
  }

  if(progressElement != null) {
    progressElement.innerHTML = "<img src='theme/"+currentTheme+"/images/working.gif' border='0' width='13' height='13'> " + progressMessage;
  }
}

// show the spinner and message, call the function first
function showProgressFunction() {
  if(progressFunction != null) {
    progressFunction();
  }

  showProgress(); 
}

function findParentRow(el,st) {
  par = el.parentNode;
  if (par.tagName == "TR" || par.tagName == "tr") {
    par.className = st;
  } else {
    findParentRow(par,st);
  }
}

function iscSelectAll(tmpForm,tmpBox) {
  theForm = document.getElementById(tmpForm)
  if (!tmpBox) {
    for (i=0;i<theForm.length;i++) {
      if (theForm[i].type == "checkbox") {
        if(theForm[i].disabled == false){
          theForm[i].checked = true;
          findParentRow(theForm.elements[i],"selected");
        }
      }
    }
  } else {
    for (i=0;i<theForm.length;i++) {
      if ((theForm[i].type == "checkbox") && (theForm[i].id == tmpBox)) {
        if(theForm[i].disabled == false){
          theForm[i].checked = true;
          findParentRow(theForm.elements[i],"selected");
        }
      }
    }
  }
}

function iscDeselectAll(tmpForm,tmpBox) {
  theForm = document.getElementById(tmpForm)
  if (!tmpBox) {
    for (i=0;i<theForm.length;i++) {
      if (theForm[i].type == "checkbox") {
        theForm[i].checked = false;
        findParentRow(theForm.elements[i],"notselected");
      }
    }
  } else {
    for (i=0;i<theForm.length;i++) {
      if ((theForm[i].type == "checkbox") && (theForm[i].id == tmpBox)) {
        theForm[i].checked = false;
        findParentRow(theForm.elements[i],"notselected");
      }
    }
  }
}

function checkChecks(chk) {
  if (chk) {
    if (chk.type == "checkbox") {
      if (chk.checked == true) {
        findParentRow(chk,"selected");
      } else {
        findParentRow(chk,"notselected");
      }
    } else if (chk.type == "radio") {
      var table = document.getElementById("collection-table");
      if (table) {
        var radioButtons = table.getElementsByTagName("input");
        for (var i = 0; i < radioButtons.length; i++) {
          if (radioButtons[i].type == "radio") {
            if (radioButtons[i].checked == true) {
              findParentRow(radioButtons[i], "selected");
            } else {
              findParentRow(radioButtons[i], "notselected");
            }
          }
        } // FOR
      }
    }
  } // IF - chk
}

/*
 * Place node around the given rectangle.
 */
function placeAroundRect(/*DOMNode*/node,/*object*/rect,/*object*/corners)
{
	// Generate list of possible positions for node
	var choices = [];
	for(var nodeCorner in corners){
		choices.push( {
			aroundCorner: nodeCorner,
			corner: corners[nodeCorner],
			pos: {
				x: rect.x + (nodeCorner.charAt(1) == 'L' ? 0 : rect.width),
				y: rect.y + (nodeCorner.charAt(0) == 'T' ? 0 : rect.height)
			}
		});
	}
	return placeMe(node, choices);
}

function placeMe(/*DomNode*/ node, /* Array */ choices, /* Function */ layoutNode){
	// summary:
	//		Given a list of spots to put node, put it at the first spot where it fits,
	//		of if it doesn't fit anywhere then the place with the least overflow
	// choices: Array
	//		Array of elements like: {corner: 'TL', pos: {x: 10, y: 20} }
	//		Above example says to put the top-left corner of the node at (10,20)
	//	layoutNode: Function(node, aroundNodeCorner, nodeCorner)
	//		for things like tooltip, they are displayed differently (and have different dimensions)
	//		based on their orientation relative to the parent.   This adjusts the popup based on orientation.

	// get {x: 10, y: 10, w: 100, h:100} type obj representing position of
	// viewport over document
	var view = dijit.getViewport();

	// This won't work if the node is inside a <div style="position: relative">,
	// so reattach it to dojo.doc.body.   (Otherwise, the positioning will be wrong
	// and also it might get cutoff)
	if(!node.parentNode || String(node.parentNode.tagName).toLowerCase() != "body"){
		dojo.body().appendChild(node);
	}

	var best = null;
	dojo.some(choices, function(choice){
		var corner = choice.corner;
		var pos = choice.pos;

		// configure node to be displayed in given position relative to button
		// (need to do this in order to get an accurate size for the node, because
		// a tooltips size changes based on position, due to triangle)
		if(layoutNode){
			layoutNode(node, choice.aroundCorner, corner);
		}

		// get node's size
		var style = node.style;
		var oldDisplay = style.display;
		var oldVis = style.visibility;
		style.visibility = "hidden";
		style.display = "block";
		var mb = dojo.marginBox(node);
		style.display = oldDisplay;
		style.visibility = oldVis;

		// coordinates and size of node with specified corner placed at pos,
		// and clipped by viewport
		var startX = (corner.charAt(1) == 'L' ? pos.x : Math.max(view.l, pos.x - mb.w)),
			startY = (corner.charAt(0) == 'T' ? pos.y : Math.max(view.t, pos.y -  mb.h)),
			endX = (corner.charAt(1) == 'L' ? Math.min(view.l + view.w, startX + mb.w) : pos.x),
			endY = (corner.charAt(0) == 'T' ? Math.min(view.t + view.h, startY + mb.h) : pos.y),
			width = endX - startX,
			height = endY - startY,
			overflow = (mb.w - width) + (mb.h - height);

		if(best == null || overflow < best.overflow){
			best = {
				corner: corner,
				aroundCorner: choice.aroundCorner,
				x: startX,
				y: startY,
				w: width,
				h: height,
				overflow: overflow
			};
		}
		return !overflow;
	});

	node.style.left = best.x + "px";
	node.style.top = best.y + "px";
	if(best.overflow && layoutNode){
		layoutNode(node, best.aroundCorner, best.corner);
	}
	return best;
}

/**
 * Work out the dimensions of the browser client area.
 */
function getWindowClientSize()
{
  // must check each of 3 different ways that browsers use to declare client dimensions
  var windowHeight=0;
  var windowWidth=0;
  if (typeof(window.innerHeight)=='number')
    windowHeight=window.innerHeight;
  else
  {
    if (document.documentElement && document.documentElement.clientHeight)
      windowHeight=document.documentElement.clientHeight;
    else
    {
      if (document.body && document.body.clientHeight)
        windowHeight=document.body.clientHeight;
    }
  }
  if (typeof(window.innerWidth)=='number')
    windowWidth=window.innerWidth;
  else
  {
    if (document.documentElement && document.documentElement.clientWidth)
      windowWidth=document.documentElement.clientWidth;
    else
    {
      if (document.body && document.body.clientWidth)
        windowWidth=document.body.clientWidth;
    }
  }
  windowSize={width: windowWidth, height: windowHeight};
  return(windowSize);
}

/**
 * Fixes the src link on generated iframes.
 */
function pacifyIframes()
{
  if (dijit._frames)
  {
    var oldPop=dijit._frames.pop;
    dijit._frames.pop = function(){
      var iframe=oldPop();
      if (dojo.isIE){
        iframe.src=contextRoot+"/scripts/dojo/resources/blank.html";
      }
      return iframe;
    };
    if (dojo.isIE)
    {
      var currentFrames=document.getElementsByTagName("iframe");
      for (var i=0;i<currentFrames.length;i++)
      {
        var f=currentFrames[i];
        var src=f.src.substr(0,10);
        if (src=="javascript")
          f.src=contextRoot+"/scripts/dojo/resources/blank.html";
      } // end for
    }
  }
}

// force an immediate change to the function
pacifyIframes();

/**
 * Handles cursor control for elements with field help
 */
function fieldHelpMouseOver(/*DOMevent*/event)
{
  var obj=null;
  if (event.srcElement)
    obj=event.srcElement;
  else
    obj=event.target;
  if (obj!=null)
  {
    var helpPortlet=document.getElementById("fieldHelpPortlet");
    if (helpPortlet)
    {
      if ((obj.tagName=="LI") && (obj.getAttribute("title")))
      {
        obj.style.cursor="help";
      }
      else if (obj.parentNode.parentNode && (obj.parentNode.parentNode.tagName=="LI") && (obj.tagName == "A"))
      {
        obj.parentNode.style.cursor="help";
      } 
      else
      {
        if ((obj.tagName=="LABEL") || (obj.tagName=="LEGEND"))
        {
          if ((obj.getAttribute("title")) || (obj.getAttribute("desc")))
          {
            var titleText=obj.getAttribute("title");
            if (titleText && titleText.indexOf(selectText+":") < 0)
              obj.style.cursor="help";
          }
        }
      }
    }
  } 
}

/**
 * Displays the field help based on input event.
 */
function showFieldHelp(/*DOMevent*/event)
{
  var obj=null;
  if (event.srcElement)
    obj=event.srcElement;
  else
    obj=event.target;
  if (obj && obj.tagName!="LABEL")
  {
    // see if there's an associated label
    var match=obj.id;
    if (!match)
      match=obj.parentNode.id;
    if (match)
    {
      var labelList=document.getElementsByTagName("label");
      for (var i=0;i<labelList.length;i++)
      {
        var pointsAt=labelList[i].getAttribute("htmlFor");
        if (pointsAt && pointsAt==match)
        {
          obj=labelList[i];
          break;
        }
      } // end for
    }
  }
  if (obj!=null)
  {
    if ((obj.tagName!="IMG") && (obj.name!="selectedObjectIds"))
    {
      try
      {
        if ((obj.getAttribute("title")) || (obj.getAttribute("desc")))
        {
          if (obj.getAttribute("title").indexOf(selectText+":") < 0)
            writeOutHelpPortlet(obj);
        } 
        else
        {
          obj=findtheLabel(obj);
          writeOutHelpPortlet(obj);
        }
      }
      catch(err) {}
    }
  }
}

/**
 * Find the parent label for the given object
 */
function findtheLabel(/*DOMElement*/anObj)
{
  if ((anObj.parentNode.getAttribute("title")) || (anObj.parentNode.getAttribute("desc")))
    anObj=anObj.parentNode;
  else
  {
    var children=anObj.parentNode.childNodes;
    if (children && children.length>1)
    {
      for (var i=0;i<children.length;i++ )
      {
        if (children[i].tagName=="LABEL")
        {
          anObj=children[i];
          break;
        } 
      } // end for
    }
  }
  return anObj;
}

/**
 * Put the help into the help portlet
 */
function writeOutHelpPortlet(/*DOMElement*/obj)
{
  try
  {
    if (!(obj.id == "" && obj.tagName == "A"))
    {
      if ((obj.getAttribute("title")) || (obj.getAttribute("desc")))
      {
        var titleText=obj.getAttribute("title");
        if (titleText=="")
          titleText = obj.getAttribute("desc");
        var scriptLabel=document.createTextNode(titleText);
        var screenObj=document.getElementById("fieldHelpPortlet");
        if (screenObj)
        {
          screenObj.innerHTML="";
          screenObj.appendChild(scriptLabel);
        }
      }
      else
      {
        // Added this check to let users copy and paste text from inside the fieldHelpPortlet
        secondParId = obj.parentNode.parentNode.id;
        thirdParId = obj.parentNode.parentNode.parentNode.id;
        if ((obj.id != "fieldHelpPortlet") && (secondParId != "wasHelpPortlet") && (thirdParId != "wasHelpPortlet"))
        {
          var titleText=noInfoAvailable;
          if (titleText)
          {
            // Note use of innerHTML=text here is deliberate since we must force
            // the XML escaped chars in the text to be processed.
            var screenObj=document.getElementById("fieldHelpPortlet");
            if (screenObj)
              screenObj.innerHTML=titleText;
          }
        }
      }
    }
  }
  catch(err) {}
}

/**
 * Initialises the focus handler for all focusable elements.
 */
function initFieldHelpFocus()
{
  var focusList=[];
  // go through all the inputs and anchors on the page
  var inputFields=document.getElementsByTagName("input");
  var textareaFields=document.getElementsByTagName("textarea");
  var selectFields=document.getElementsByTagName("select");
  var anchors=document.getElementsByTagName("a");
  // merge all the elements into one list for processing
  if (inputFields)
  {
    for (var i=0;i<inputFields.length;i++)
    {
      focusList[focusList.length]=inputFields[i];
    } // end for
  }
  if (textareaFields)
  {
    for (var i=0;i<textareaFields.length;i++)
    {
      focusList[focusList.length]=textareaFields[i];
    } // end for
  }
  if (selectFields)
  {
    for (var i=0;i<selectFields.length;i++)
    {
      focusList[focusList.length]=selectFields[i];
    } // end for
  }
  if (anchors)
  {
    for (var i=0;i<anchors.length;i++)
    {
      focusList[focusList.length]=anchors[i];
    } // end for
  }
  // now add focus handler
  for (var i=0;i<focusList.length;i++)
  {
    var element=focusList[i];
    dojo.connect(element,'onfocus','showFieldHelp');
  } // end for
}

/**
 * Initialises the field help system.
 */
function initFieldHelp()
{
  dojo.connect(document,'onmouseover','fieldHelpMouseOver');
  dojo.connect(window,'onclick','showFieldHelp');
  dojo.connect(window,'onkeypress','showFieldHelp');
  dojo.connect(window,'onmouseup','showFieldHelp');
  initFieldHelpFocus();
}

dojo.addOnLoad(initFieldHelp);

function printObject(/*object*/obj)
{
  var text="";
  for (var key in obj)
  {
    try
    {
      var value=obj[key];
      text=text+" "+key+": "+value;
    }
    catch (e) {}
  } // end for
  alert(text);
}

function logObject(/*object*/obj)
{
  var text="";
  for (var key in obj)
  {
    try
    {
      var value=obj[key];
      text=text+" "+key+": "+value;
    }
    catch (e) {}
  } // end for
  console.log(text);
}

function printObjectNoFunctions(/*object*/obj)
{
  var text="";
  for (var key in obj)
  {
    try
    {
      var value=obj[key];
      if (typeof(value)!='function')
        text=text+" "+key+": "+value;
    }
    catch (e) {}
  } // end for
  alert(text);
}

function logObjectNoFunctions(/*object*/obj)
{
  var text="";
  for (var key in obj)
  {
    try
    {
      var value=obj[key];
      if (typeof(value)!='function')
        text=text+" "+key+": "+value;
    }
    catch (e) {}
  } // end for
  console.log(text);
}
