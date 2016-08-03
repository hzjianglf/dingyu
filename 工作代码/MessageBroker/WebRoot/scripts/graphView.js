/* begin_generated_IBM_copyright_prolog                            */
/* 
 * Licensed Materials - Property of IBM 
 * 
 * 5724-N72 5655-W17
 * 
 * (c) Copyright IBM Corp. 2007, 2009 All Rights Reserved 
 * 
 * US Government Users Restricted Rights - Use, duplication or 
 * disclosure restricted by GSA ADP Schedule Contract with 
 * IBM Corp. 
 */
/* end_generated_IBM_copyright_prolog                              */

/* load required packages */
dojo.require("dojo.parser");
dojo.require("dijit.layout.AccordionContainer");
dojo.require("dijit.layout.ContentPane");
dojo.require("dojo.rpc.JsonService");

/* dimension settings */
var rightColumnSize=180;
var surfaceWidth=1000;
var surfaceHeight=1000;
var viewportWidth=rightColumnSize-14;
var viewportHeight=140;
var entityWidth=150;
var entityHeight=38;
var xScale=100;
var yScale=25;
var orientScale=[{xScale: 100, yScale: 25}, {xScale: 80, yScale: 45}];
var orientation=1;
var canvasBorder=10;
var moreWidgetWidth=40;
var scrollWidth=16;

/* standard colours */
var normalBoxColour=[51,102,153,1];
var normalLineColour=[51,102,153,1];
var highlightBoxColour=[234,178,58,1];
var highlightLineColour=[234,178,58,1];
//var selectGradeTop=[192,152,89,1];
//var selectGradeBottom=[160,102,65,1];
var selectGradeTop=[133,90,161,1];
var selectGradeBottom=[94,64,115,1];

/* drawing vars */
var container=null;
var containerPosition=null;
var surface=null;
var surfaceSize=null;
var gShapes={};
var gShapeCounter=0;
var viewport=null;
var viewportPosition=null;
var viewportSurface=null;
var viewportSurfaceSize=null;
var viewportShape=null;
var viewportLastPosition=null;
var viewportCurrentShape=null;
var viewportCurrentShapeWindow=null;
var draggingCanvas=false;
var dragStart=null;
var gConnections={};
var gConnectionCounter=0;
var gWidgets={};
var gWidgetCounter=0;
var lastClientSize={width: 0, height: 0};
var beforeLastClientSize={width: 0, height: 0};

/* wait timer settings */
var timerFrame=0;
var maxTimerFrames=12;
var timerFrameWidth=40;
var timerActive=0;
var timerDelay=50;

/* tooltip vars */
var tooltipDelay=500;
var tooltipMaxShowTime=5000;
var tooltipShowing=false;
var tooltipShape=null;
var tooltipTimeout=null;
var tooltipHideTimeout=null;
var tooltipLastX=0;
var tooltipLastY=0;

/* primary data from RPC */
var objectGraph={};
var entityList={};
var graphSettings={};

/* misc */
var actionHTML={};
var navigationHistory=[];
var suppressHistoryAdd=false;

var graphService=new dojo.rpc.JsonService(contextRoot + "/RPCAdapter/jsonrpc/GraphService");

// Mapping of object type to icon
var typeIcons=[
  "", // dummy node
  "wsdlDocument",
  "xsdDocument",
  "xmlDocument",
  "binaryDocument",
  "policyDocument",
  "scaIntegrationModule",
  "scaModuleDocument",
  "scaExportDocument",
  "scaImportDocument",
  "genericObject",
  "wsdlService",
  "port",
  "binding",
  "wsdlPortType",
  "operation",
  "message",
  "part",
  "soapAddress",
  "soapBinding",
  "element",
  "attribute",
  "simpleType",
  "complexType",
  "attribute", // local attribute
  "policyExpression",
  "policyAttachment",
  "scaModule",
  "scaImport", // sca import
  "scaExport", // sca export
  "scaPortType", // sca wsdl port type
  "scaImportBinding", // web service import binding
  "scaImportBinding", // slsb import binding
  "scaImportBinding", // sca import binding
  "scaImportBinding", // jms import binding
  "scaExportBinding", // web service export binding
  "scaExportBinding", // sca export binding
  "scaExportBinding", // jms export binding
  "subscription",
  "query", // property query
  "graphQuery",
  "governanceRecord",
  "extensionLogicalObject",
  "wsdlInput",
  "wsdlOutput",
  "wsdlFault"
];

// Mapping of object type to whther it is a logical object
var logicalMap=[
  false, // dummy node
  false, // WSDL document
  false, // XSD document
  false, // XML document
  false, // binary document
  false, // policy document
  false, // sca integration module
  false, // sca module document
  false, // sca export document
  false, // sca import document
  false, // generic object
  true, // service
  true, // port
  true, // binding
  true, // wsdl port type
  true, // operation
  true, // message
  true, // part
  true, // soap address
  true, // soap binding
  true, // element
  true, // attribute
  true, // simple type
  true, // complex type
  true, // local attribute
  true, // policy expression
  true, // policy attachment
  true, // sca module
  true, // sca import
  true, // sca export
  true, // sca wsdl port type
  true, // web service import binding
  true, // slsb import binding
  true, // sca import binding
  true, // jms import binding
  true, // web service export binding
  true, // sca export binding
  true, // jms export binding
  false, // subscription
  false, // property query
  false, // graph query
  false, // governance record
  true,  // extension logical object
  true, // wsdl input
  true, // wsdl output
  true // wsdl fault
];

var actionIdList=[
  "actionrecenter",
  "actiondetails",
  "actionaddprops",
  "actionaddrel",
  "actionaddclass",
  "actionaddfavorites",
  "actionexport",
  "actionsubscribe"
];

// Bugfix local copy of dash arrays
var localDasharray = {
	solid:				"none",
	shortdash:			[4, 1],
	shortdot:			[1, 1],
	shortdashdot:		[4, 1, 1, 1],
	shortdashdotdot:	[4, 1, 1, 1, 1, 1],
	dot:				[1, 3],
	dash:				[4, 3],
	longdash:			[8, 3],
	dashdot:			[4, 3, 1, 3],
	longdashdot:		[8, 3, 1, 3],
	longdashdotdot:		[8, 3, 1, 3, 1, 3]
};

/**
 * Draw a box on screen that displays an entity.
 */
EntityDrawer=function()
{
	this.draw=function(/*integer*/inlocx, /*integer*/inlocy, /*string*/objid)
	{
	  var mainWidth=entityWidth;
	  var mainHeight=entityHeight;
	  var normalColour=normalBoxColour;
	  var entity=entityList[objid];
	  var entityType=graphSettings.typeTranslations[entity.typeId];
	  var contentColour=normalColour;
    var locx=inlocx;
    var locy=inlocy;
    if (isRTL && orientation==1)
      locx=surfaceWidth-inlocx-mainWidth;
	  // group all entity objects together
	  var group=surface.createGroup();
	  this.id="shape_"+gShapeCounter;
	  // hover text gets added to all objects
	  var hoverText=entity.properties.name+" ("+entityType+")";
	  // create primary outline rectangle
	  this.mainRect=group.createRect({x: locx, y: locy, width: mainWidth, height: mainHeight});
	  var lineWidth=1;
	  if (entity.isAnchor)
	    lineWidth=2;                            // anchor object gets bold outline
	  var lineStyle="Solid";
	  if (entity.isLogical)
	    lineStyle="Dash";
	  setStroke(this.mainRect,{color: normalColour, width: lineWidth, style: lineStyle});
	  if (entity.isSelected)
	  {
	    this.mainRect.setFill({type: "linear", x1: locx, y1: locy, x2: locx, y2: locy+mainHeight, colors: [{offset: 0, color: selectGradeTop}, {offset: 1, color: selectGradeBottom}]});
	    contentColour=[255,255,255,1];
	  }
	  else
	    this.mainRect.setFill([255,255,255,1]);
    if (dojox.gfx.renderer!="silverlight")
    {
      group.connect("onmouseover",this,"handleOver");
      group.connect("onmouseout",this,"handleOut");
    }
    else
    {
      group.connect("onmouseenter",this,"handleOver");
      group.connect("onmouseleave",this,"handleOut");
    }
    group.connect("onmousedown",this,"handleDown");
	  gShapes[this.id]=this.mainRect;
	  gShapeCounter+=1;
	  // add center line
	  var line=group.createLine({x1: locx+1, y1: locy+mainHeight/2, x2: locx+mainWidth-1, y2: locy+mainHeight/2});
	  setStroke(line,{color: contentColour, width: 1, style: "Dot"});
	  // add entity name
	  var entityName=bestFitText(entity.properties.name,mainWidth-5,true);
    var textLocx=locx+5;
    if (isRTL)
    {
      textLocx=locx+mainWidth-5-entityName.size.width;
      if (textLocx<(locx+5))
        textLocx=locx+5;
    }
	  var text=group.createText({x: textLocx, y: locy+14, text: entityName.text});
	  text.setFont({size: "8pt", family: "sans-serif", weight: "bold"});
	  text.setFill(contentColour);
	// Do not enable images for IE VML - there is a massive memory usage problem in IE
	// when you display images in IE VML.
	  var adjustText=0;
	  if (graphSettings.displayIcons)
	  {
	    if (dojox.gfx.renderer!="vml")
	    {
	      var iconName=typeIcons[entity.typeId];
	      if (!iconName)
	      	iconName=typeIcons[10];	// custom types use generic object icon
        var imgLocx=locx+5;
        if (isRTL)
          imgLocx=locx+mainWidth-21;
	      var img=group.createImage({x: imgLocx, y: locy+21, width: 16, height: 16, src: "theme/"+currentTheme+"/images/types/"+iconName+".png"});
	      adjustText=20;
	    }
	//    else
	//    {
	//      var rawImg=img.getEventSource();
	//      var parentName=rawImg.parentNode.tagName;
	//      if (parentName=="DIV")                    // special adjustment just for IE VML
	//      {
	//        var parent=rawImg.parentNode;
	//        rawImg.style.position="relative";
	//        rawImg.style.top="22px";
	//        rawImg.style.left="5px";
	//        parent.style.width=mainWidth+"px";
	//        parent.style.height=mainHeight+"px";
	//        parent.style.filter="";
	//      }
	//    }
	  }
	  // add entity type
	  entityType=bestFitText(entityType,mainWidth-5-adjustText,false);
    var typeLocx=locx+5+adjustText;
    if (isRTL)
    {
      typeLocx=locx+mainWidth-5-adjustText-entityType.size.width;
      if (typeLocx<(locx+5))
        typeLocx=locx+5;
    }
	  var text2=group.createText({x: typeLocx, y: locy+33, text: entityType.text});
	  text2.setFont({size: "8pt",family: "sans-serif"});
	  text2.setFill(contentColour);
	  // store useful info for later
	  this.mainRect.shapeId=this.id;
	  this.mainRect.uid=objid;
	  this.mainRect.centerLine=line;
	  this.mainRect.nameText=text;
	  this.mainRect.typeText=text2;
	  entity.rect=this.mainRect;
	  return(this.mainRect);
	}

  this.handleOver=function(/*DOMEvent*/event)
  {
    highlightEntity(this.mainRect);
    // suppress tooltip reshow if mouse hasnt moved
    if (tooltipLastX!=event.clientX || tooltipLastY!=event.clientY)
      showTooltip(event,this.mainRect);
    tooltipLastX=event.clientX;
    tooltipLastY=event.clientY;
    dojo.stopEvent(event);
  }
  
  this.handleOut=function(/*DOMEvent*/event)
  {
    unHighlightEntity(this.mainRect);
    // suppress tooltip hide when it's cornered by the screen and the mouse hasn't moved
    if (tooltipLastX!=event.clientX || tooltipLastY!=event.clientY)
      hideTooltip();
    dojo.stopEvent(event);
  }

  this.handleUp=function(/*DOMEvent*/event)
  {
    dojo.stopEvent(event);
  }

  this.handleDown=function(/*DOMEvent*/event)
  {
    focusOnShape(event,this.mainRect);
    dojo.stopEvent(event);
  }
}

/**
 * Create the information needed for a dummy node.
 */
function createDummyNode(/*integer*/inlocx, /*integer*/inlocy, /*string*/objid)
{
  var entity=entityList[objid];
  var locx=inlocx;
  var locy=inlocy;
  if (isRTL && orientation==1)
    locx=surfaceWidth-inlocx-entityWidth;
  var fakeRect=new dojox.gfx.Shape();
  var box={};
  if (orientation==1)                       // left to right
    box={x: locx, y: locy+entityHeight/2, width: entityWidth, height: 0};
  else                                      // top to bottom
    box={x: locx+entityWidth/2, y: locy, width: 0, height: entityHeight};
  fakeRect.bbox=box;
  fakeRect.uid=objid;
  entity.rect=fakeRect;
  return(fakeRect);
}

/**
 * Draws the "more" widget in the appropriate place for the current orientation.
 * The given coordinates are the top left of the entity's main rectangle.
 */
WidgetDrawer=function()
{
	this.draw=function(/*ObjectData*/entity,/*integer*/locx, /*integer*/locy)
	{
    var group=surface.createGroup();
    this.id="widget_"+gWidgetCounter;
    var x=locx;
    var y=locy;
    var lineLength=25;
    var connectLine={x1: 0, y1: 0, x2: 0, y2: 0};
    var mainWidth=15;
    var mainHeight=12;
    if (orientation==1)
    {
      x=x+entityWidth+lineLength;
      y=y+(entityHeight-mainHeight)/2;
      connectLine.x1=locx+entityWidth;
      connectLine.x2=connectLine.x1+lineLength;
      connectLine.y1=locy+entityHeight/2;
      connectLine.y2=connectLine.y1;
      if (isRTL)
      {
        x=surfaceWidth-locx-mainWidth-entityWidth-lineLength;
        connectLine.x1=surfaceWidth-locx-entityWidth;
        connectLine.x2=connectLine.x1-lineLength;
      }
    }
    else
    {
      x=x+(entityWidth-mainWidth)/2;
      y=y+entityHeight+lineLength;
      connectLine.x1=locx+entityWidth/2;
      connectLine.x2=connectLine.x1;
      connectLine.y1=locy+entityHeight;
      connectLine.y2=connectLine.y1+lineLength;
    }
    // create primary outline rectangle
    this.mainRect=group.createRect({x: x, y: y, width: mainWidth, height: mainHeight});
    var lineWidth=1;
    var lineStyle="Solid";
    setStroke(this.mainRect,{color: normalBoxColour, width: lineWidth, style: lineStyle});
    this.mainRect.setFill([255,255,255,1]);
    gWidgets[this.id]=this.mainRect;
    gWidgetCounter+=1;
    if (dojox.gfx.renderer!="silverlight")
    {
      group.connect("onmouseover",this,"handleOver");
      group.connect("onmouseout",this,"handleOut");
    }
    else
    {
      group.connect("onmouseenter",this,"handleOver");
      group.connect("onmouseleave",this,"handleOut");
    }
    group.connect("onmousedown",this,"handleDown");
    // add the ellipsis text
    var text=group.createText({x: x+3, y: y+7, text: "..."});
    text.setFont({size: "8pt", family: "sans-serif", weight: "bold"});
    text.setFill(normalBoxColour);
    // draw the connecting line
    var line=group.createLine(connectLine);
    setStroke(line,{color: normalLineColour, width: 1, style: "dash", alias: false});
    // store useful info for later
    this.mainRect.widgetId=this.id;
    this.mainRect.ellipsisText=text;
    this.mainRect.connectLine=line;
    this.mainRect.parentEntity=entity;
  }

  this.handleOver=function(/*DOMEvent*/event)
  {
    highlightWidget(this.mainRect);
    // suppress tooltip reshow if mouse hasnt moved
    if (tooltipLastX!=event.clientX || tooltipLastY!=event.clientY)
      showWidgetTooltip(event,this.mainRect);
    tooltipLastX=event.clientX;
    tooltipLastY=event.clientY;
    dojo.stopEvent(event);
  }
  
  this.handleOut=function(/*DOMEvent*/event)
  {
    unHighlightWidget(this.mainRect);
    // suppress tooltip hide when it's cornered by the screen and the mouse hasn't moved
    if (tooltipLastX!=event.clientX || tooltipLastY!=event.clientY)
      hideTooltip();
    dojo.stopEvent(event);
  }

  this.handleUp=function(/*DOMEvent*/event)
  {
    dojo.stopEvent(event);
  }

  this.handleDown=function(/*DOMEvent*/event)
  {
    showMore(this.mainRect);
    dojo.stopEvent(event);
  }
}

/**
 * Fetch the graph data from the server.
 */
function fetchGraph(/*string*/bsrURI)
{
  var callbackFunction=function(oGraph,e) {
    processGraph(oGraph,e);
  }
  // make the primary RPC call to walk the graph - this will take time, use callback
  var deferred=graphService.fetchGraphFromObject(bsrURI);
  deferred.addCallback(callbackFunction);
  deferred.addErrback(processRPCException);
}

/**
 * Callback function that processes the return from the graph fetch.
 */
function processGraph(/*ObjectGraph*/oGraph,/*object*/exception)
{
  objectGraph=oGraph;
  if (exception)
  {
    var text="";
    for (var key in exception)
    {
      text=text+" "+key+": "+exception[key]+"\n";
    } // end for
    displayError(TRANS_ERROR_EXCEPTION+" "+text,null);
    unblockAllLinks();
    hideTimer();                            // clear the wait timer
  }
  else if (objectGraph.errorCondition)
  {																					// JSON action threw an exception
    displayError(objectGraph.errorMessage,objectGraph.errorExtra);
    unblockAllLinks();
    hideTimer();                            // clear the wait timer
  }
  else
  {
    entityList={};                          // reset any old lists
    gShapes={};
    gShapeCounter=0;
    gConnections={};
    gConnectionCounter=0;
    gWidgets={};
    gWidgetCounter=0;
    var graphObjects=objectGraph.graphObjects;
    var len=graphObjects.length;
    for (var i=0;i<len;i++)
    {
      // unpack a linear list of ObjectData objects
      var uid=graphObjects[i].id;
      var entity=graphObjects[i];
      entity.isAnchor=false;
      entity.isSelected=false;
      entity.isLogical=isLogicalType(entity.typeId);
      entityList[uid]=entity;
    } // end for
    populateDisplay2();
  }
}

/**
 * Replaces and resizes the drawing canvas based on the bounding box of the graph.
 */
function replaceCanvas()
{
  var bbox=objectGraph.boundingBox;
  var dx=bbox.maxX-bbox.minX;
  var dy=bbox.maxY-bbox.minY;
  var width=dx*xScale+entityWidth+canvasBorder*2;
  var height=dy*yScale+entityHeight+canvasBorder*2;
  // find out if the last level has any entities showing the "more" widget
  var hasWidget=checkLastLevelForMore();
  // compensate the canvas width
  if (hasWidget)
    width+=moreWidgetWidth;
  surfaceWidth=width;
  surfaceHeight=height;
  clearSurface(surface);                    // clear any old drawing
  if (dojox.gfx.renderer=="svg")
    container.removeChild(surface.rawNode);   // dump the old surface first
  container.style.width=width+"px";
  container.style.height=height+"px";
  surface=dojox.gfx.createSurface(container, surfaceWidth, surfaceHeight);
  surfaceSize=surface.getDimensions();
  surfaceSize.width=parseInt(surfaceSize.width);
  surfaceSize.height=parseInt(surfaceSize.height);
  var area=dojo.byId("drawingareasurround");  // reset scroll position
  if (isRTL)
  {
    area.scrollTop=0;
    if (dojo.isIE || dojo.isSafari)
      area.scrollLeft=surfaceWidth;
    else
      area.scrollLeft=0;
  }
  else
  {
    area.scrollTop=0;
    area.scrollLeft=0;
  }
  resizeDrawArea(); // also resizes viewport
}

/**
 * Find out if the last level has any entities showing the "more" widget.
 */
function checkLastLevelForMore()
{
  var hasWidget=false;
  var levels=objectGraph.levels;
  var len=levels.length;
  if (len>0)
  {
    var levelList=levels[len-1];
    var listLen=levelList.length;
    for (var i=0;i<listLen;i++)
    {
      // process each node on the last level
      var uid=levelList[i];
      var entity=entityList[uid];
      if (entity.moreOutbound)
      {
        hasWidget=true;
        break;
      }
    }
  }
  return(hasWidget);
}

/**
 * Resizes the canvas, preserving the existing DOM objects given the dimensions of
 * the main viewport.
 */
function resizeCanvas(/*integer*/viewWidth,/*integer*/viewHeight)
{
  // calculate the minimum dimensions first based on the bounding box
  var bbox=objectGraph.boundingBox;
  if (bbox)
  {
    // only do this if we've already got a graph displaying
    var dx=bbox.maxX-bbox.minX;
    var dy=bbox.maxY-bbox.minY;
    var width=dx*xScale+entityWidth+canvasBorder*2;
    var height=dy*yScale+entityHeight+canvasBorder*2;
    // find out if the last level has any entities showing the "more" widget
    var hasWidget=checkLastLevelForMore();
    // compensate the canvas width
    if (hasWidget)
      width+=moreWidgetWidth;
    // fill out the canvas width to fit the viewport, if smaller
    if (width<(viewWidth-scrollWidth-2))
      width=viewWidth-scrollWidth-2;
    if (height<(viewHeight-scrollWidth-2))
      height=viewHeight-scrollWidth-2;
    surfaceWidth=width;
    surfaceHeight=height;
    container.style.width=width+"px";
    container.style.height=height+"px";
    surface.setDimensions(width,height);
  }
}

/**
 * Return whether the typeID represents a logical object.
 */
function isLogicalType(/*integer*/typeId)
{
	var logic=logicalMap[typeId];
	if (!logic)
		logic=false;
	return(logic);
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
  unblockAllLinks();
  hideTimer();                            	// clear the wait timer
}

/**
 * Draw all entities on screen.
 */
function drawEntities()
{
  var anchor=null;
  clearSurface(surface);
  var boundingBox=objectGraph.boundingBox;
  var offsetX=canvasBorder;                 // minimum border
  var offsetY=canvasBorder;
  if (boundingBox.minX<0)
    offsetX=offsetX+(-1*boundingBox.minX*xScale); // normalise X coord
  if (boundingBox.minY<0)
    offsetY=offsetY+(-1*boundingBox.minY*yScale); // normalise Y coord
  var foundAnchor=false;
  var x=0;
  var y=0;
  for (var uid in entityList)
  {
    var entity=entityList[uid];
    if (!foundAnchor && entity.properties.bsrURI==startBsrUri)
    {                                       // found anchor object
      entity.isAnchor=true;
      entity.isSelected=true;
      // change the main portlet title to match the anchor object
      var name=entity.properties.name;
      var portletHeading=dojo.byId("graphviewheading");
      if (graphSettings.impactMode)
	      portletHeading.innerHTML=TRANS_IMPACT_VIEW_FOR+" "+name;
	    else
	      portletHeading.innerHTML=TRANS_GRAPH_VIEW_FOR+" "+name;
      foundAnchor=true;
      anchor=entity;
    }
    x=offsetX+entity.xpos*xScale;
    y=offsetY+entity.ypos*yScale;
    if (entity.typeId!=0)
    {
    	var drawer=new EntityDrawer();
      var rect=drawer.draw(x,y,uid);        // draw real nodes
  	  // see if we need to draw a widget
	    if (entity.moreOutbound)
      {
        var wDrawer=new WidgetDrawer();
        wDrawer.draw(entity,x,y);
      }
    }
    else
      var rect=createDummyNode(x,y,uid);    // do some calc for dummies
  } // end for
  drawConnections();
}

/**
 * Center the graph on the anchor object
 */
function centerAnchor()
{
  var anchor=null;
  for (var uid in entityList)
  {
    var entity=entityList[uid];
    if (entity.isAnchor)
    {
      anchor=entity;
      break;
    }
  } // end for
  if (anchor)
    centerObject(anchor);
}

/**
 * Center the graph on the given object
 */
function centerObject(/*DataObject*/entity)
{
  if (entity)
  {
    var boundingBox=objectGraph.boundingBox;
    var offsetX=canvasBorder;                 // minimum border
    var offsetY=canvasBorder;
    if (boundingBox.minX<0)
      offsetX=offsetX+(-1*boundingBox.minX*xScale); // normalise X coord
    if (boundingBox.minY<0)
      offsetY=offsetY+(-1*boundingBox.minY*yScale); // normalise Y coord
    var x=offsetX+entity.xpos*xScale;
    var y=offsetY+entity.ypos*yScale;
    var area=dojo.byId("drawingareasurround");
    var viewWidth=parseInt(area.style.width);
    var viewHeight=parseInt(area.style.height);
    // max amount to move to center the entity in the view
    var centerDeflectionX=(viewWidth-entityWidth)/2;
    var centerDeflectionY=(viewHeight-entityHeight)/2;
    var xWidth=x+entityWidth+scrollWidth;     // calc bottom right corner of entity +scrollbars
    var yHeight=y+entityHeight+scrollWidth;
    if (isRTL && (dojo.isSafari || dojo.isIE))
    {
      var apparentScroll=surfaceWidth-area.scrollLeft-viewWidth;
      if (xWidth>viewWidth || xWidth<apparentScroll)
      {
        // entity obscured in x-axis
        var dx=xWidth-viewWidth;                // delta to bring it back in view
        var maxX=surfaceWidth-viewWidth;        // add in re-center amount
        var delta=Math.min(maxX-dx,centerDeflectionX);
        area.scrollLeft=surfaceWidth-(delta+dx);
      }
    }
    else
    {
      if (xWidth>viewWidth || xWidth<area.scrollLeft)
      {
        // entity obscured in x-axis
        var dx=xWidth-viewWidth;                // delta to bring it back in view
        var maxX=surfaceWidth-viewWidth;        // add in re-center amount
        var delta=Math.min(maxX-dx,centerDeflectionX);
        area.scrollLeft=delta+dx;
      }
    }
    if (yHeight>viewHeight || yHeight<area.scrollTop)
    {
      // entity obscured in y-axis
      var dy=yHeight-viewHeight;              // delta to bring it back in view
      var maxY=surfaceHeight-viewHeight;
      var delta=Math.min(maxY-dy,centerDeflectionY);
      area.scrollTop=delta+dy;
    }
  }
}

/**
 * Draw the connections between objects
 */
function drawConnections()
{
  // This method uses the level list and a repackaged edge list to walk
  // through the edges from left to right (logically, even though they may be
  // drawn top to bottom). The level lists are in sorted order which means
  // we can use this information to apply connection spreading to the connection
  // edges of each entity to make the layout more readable.
  // First we start by repackaging the raw edge list into a useful form for
  // quick lookups with the sourceID as the key.
  var edgeLookup={};
  var graphEdges=objectGraph.graphEdges;
  var len=graphEdges.length;
  for (var i=0;i<len;i++)
  {
    var edge=graphEdges[i];
    var sourceId=edge.sourceId;
    var targetMap=edgeLookup[sourceId];
    if (!targetMap)
    {
      // not seen this source object before, create a new target map
      targetMap={};
      targetMap[edge.targetId]=edge;
      edgeLookup[sourceId]=targetMap;
    }
    else
    {
      // add to the existing target list
      targetMap[edge.targetId]=edge;
    }
  } // end for
  // now run through the levels in order
  var levels=objectGraph.levels;
  len=levels.length;
  for (var i=0;i<len;i++)
  {
    var levelList=levels[i];
    var listLen=levelList.length;
    for (var j=0;j<listLen;j++)
    {
      // process each node on the level
      var sourceId=levelList[j];
      var targetList=objectGraph.edgeMap[sourceId];
      if (targetList)
      {
        // current node connects with some targets
        var targetLen=targetList.length;
        var targetLevelList=levels[i+1];
        var targetLevelLen=targetLevelList.length;
        var count=0;
        // Go through the target level as it is in order, storing all the connection
        // spreading calculations. We don't actually draw the connections yet since
        // we must perform all the calculations first
        for (var k=0;k<targetLevelLen;k++)
        {
          var targetId=targetLevelList[k];
          var idx=targetList.indexOf(targetId);
          if (idx!=-1)
          {
            // use the target object to track inbound connection spreading
            var target=entityList[targetId];
            if (target.targetTotal)
            {
              target.targetTotal+=1;
              target.targetIndex+=1;
            }
            else
            {
              target.targetTotal=1;
              target.targetIndex=0;
            }
            // store positional info in the edge for use during connection
            var edge=edgeLookup[sourceId][targetId];
            edge.edgeIndex=count;
            edge.edgeTotal=targetLen;
            edge.targetIndex=target.targetIndex;  // don't store the total yet, just the index
            count+=1;
          }
        } // end for
      }
    } // end for
    // Repeat the loop on this level just so we can draw the connections.
    // This is required since the connection spreading for the whole level must
    // be calculated first before we can draw the connection correctly.
    for (var j=0;j<listLen;j++)
    {
      // process each node on the level
      var sourceId=levelList[j];
      var targetList=objectGraph.edgeMap[sourceId];
      if (targetList)
      {
        // current node connects with some targets
        var targetLevelList=levels[i+1];
        var targetLevelLen=targetLevelList.length;
        // Now just draw the connections
        for (var k=0;k<targetLevelLen;k++)
        {
          var targetId=targetLevelList[k];
          var idx=targetList.indexOf(targetId);
          if (idx!=-1)
          {
            var target=entityList[targetId];
            // store final info in the edge for use during connection
            var edge=edgeLookup[sourceId][targetId];
            // now we store the total since it's been calculated in the previous loop
            edge.targetTotal=target.targetTotal;
            var drawer=new ConnectionDrawer();
            drawer.draw(edge);
          }
        } // end for
      }
    } // end for
  } // end for
}
/*
 * Draw an arrowed line between two entities.
 */
ConnectionDrawer=function()
{
	this.draw=function(/*GraphEdge*/edge)
	{
    var vectors=edge.edgeVectors;
    var vector1=null;
    var vector2=null;
    var source=entityList[edge.sourceId];
    var target=entityList[edge.targetId];
    var len=vectors.length;
    for (var i=0;i<len;i++)
    {
      var vector=vectors[i];
      var name=graphSettings.relationshipNames[vector.id];
      if (i==0)                               // first time through just take initial values
        vector1={reversed: vector.reversed, multi: false, names: [name]};
      else
      {                                       // potential multi or 2-way vector
        if (vector.reversed==vector1.reversed)
        {                                     // multi on vector 1
          vector1.multi=true;
          vector1.names[vector1.names.length]=name;
        }
        else
        {                                     // definite 2-way
          if (!vector2)                       // initial values for vector 2
            vector2={reversed: vector.reversed, multi: false, names: [name]};
          else
          {                                   // multi vector2
            vector2.multi=true;
            vector2.names[vector2.names.length]=name;
          }
        }
      }
    } // end for
    var rect1=source.rect;
    var rect2=target.rect;
    var minGap=15;
    var dualAdjust=8;                         // separation of dual relationships
    var connectLine={x1: 0, y1: 0, x2: 0, y2: 0};
    var dualLine=null;
    // Work out the correct spreading factor for the edge so that the connections
    // get spaced evenly along the rectangle
    var spread=(edge.edgeIndex+1)/(edge.edgeTotal+1);
    var targetSpread=(edge.targetIndex+1)/(edge.targetTotal+1);
    // must get the current absolute positions of the two rectangles
    // for that we must take account of the current transforms
    var bbox1raw=rect1.getBoundingBox();
    var bbox2raw=rect2.getBoundingBox();
    var bbox1={x: bbox1raw.x, y: bbox1raw.y, width: bbox1raw.width, height: bbox1raw.height};
    var bbox2={x: bbox2raw.x, y: bbox2raw.y, width: bbox2raw.width, height: bbox2raw.height};
    var transform1=null;
    var transform2=null;
    var parent1=rect1.getParent();
    var parent2=rect2.getParent();
    if (parent1)
      transform1=parent1.getTransform();
    if (parent2)
      transform2=parent2.getTransform();
    var direction=0;
    if (transform1)
    {
      bbox1.x=bbox1raw.x+transform1.dx;
      bbox1.y=bbox1raw.y+transform1.dy;
    }
    if (transform2)
    {
      bbox2.x=bbox2raw.x+transform2.dx;
      bbox2.y=bbox2raw.y+transform2.dy;
    }
    if (bbox1.x==bbox2.x)
    {                                         // aligned vertically
      connectLine.x1=bbox1.x+(bbox1.width*spread);
      connectLine.x2=bbox2.x+(bbox2.width*targetSpread);
      if (bbox2.y>bbox1.y)
      {                                       // 2 is south of 1
        direction=5;
        if (bbox2.y>(bbox1.y+bbox1.height))
          connectLine.y1=bbox1.y+bbox1.height;
        else
          connectLine.y1=bbox1.y;
        connectLine.y2=bbox2.y;
      }
      else if (bbox1.y>bbox2.y)
      {                                       // 2 is north of 1
        direction=1;
        if (bbox1.y>(bbox2.y+bbox2.height))
          connectLine.y1=bbox1.y;
        else
          connectLine.y1=bbox1.y+bbox1.height;
        connectLine.y2=bbox2.y+bbox2.height;
      }
    }
    else if (bbox2.x>bbox1.x)
    {                                         // 2 is east
      if (bbox1.y==bbox2.y)
      {                                       // 2 is due east
        direction=3;
        if (bbox2.x>(bbox1.x+bbox1.width))
          connectLine.x1=bbox1.x+bbox1.width;
        else
          connectLine.x1=bbox1.x;
        connectLine.x2=bbox2.x;
        connectLine.y1=bbox1.y+(bbox1.height*spread);
        connectLine.y2=bbox2.y+(bbox2.height*targetSpread);
      }
      else if (bbox2.y>bbox1.y)
      {                                       // 2 is south east
        direction=4;
        if (bbox2.y>(bbox1.y+bbox1.height+minGap) && orientation==2)
        {
          connectLine.x1=bbox1.x+(bbox1.width*spread);
          connectLine.x2=bbox2.x+(bbox2.width*targetSpread);
          connectLine.y1=bbox1.y+bbox1.height;
          connectLine.y2=bbox2.y;
        }
        else if (bbox2.x>(bbox1.x+bbox1.width+minGap) && orientation==1)
        {
          connectLine.y1=bbox1.y+(bbox1.height*spread);
          connectLine.y2=bbox2.y+(bbox2.height*targetSpread);
          connectLine.x1=bbox1.x+bbox1.width;
          connectLine.x2=bbox2.x;
        }
        else
        {
          connectLine.y1=bbox1.y+(bbox1.height*spread);
          connectLine.y2=bbox2.y+(bbox2.height*targetSpread);
          connectLine.x1=bbox1.x;
          connectLine.x2=bbox2.x;
        }
      }
      else if (bbox1.y>bbox2.y)
      {                                       // 2 is north east
        direction=2;
        if (bbox1.y>(bbox2.y+bbox2.height+minGap) && orientation==2)
        {
          connectLine.x1=bbox1.x+(bbox1.width*spread);
          connectLine.x2=bbox2.x+(bbox2.width*targetSpread);
          connectLine.y1=bbox1.y;
          connectLine.y2=bbox2.y+bbox2.height;
        }
        else if (bbox2.x>(bbox1.x+bbox1.width+minGap) && orientation==1)
        {
          connectLine.y1=bbox1.y+(bbox1.height*spread);
          connectLine.y2=bbox2.y+(bbox2.height*targetSpread);
          connectLine.x1=bbox1.x+bbox1.width;
          connectLine.x2=bbox2.x;
        }
        else
        {
          connectLine.y1=bbox1.y+(bbox1.height*spread);
          connectLine.y2=bbox2.y+(bbox2.height*targetSpread);
          connectLine.x1=bbox1.x;
          connectLine.x2=bbox2.x;
        }
      }
    }
    else if (bbox1.x>bbox2.x)
    {                                         // 2 is west
      if (bbox1.y==bbox2.y)
      {                                       // 2 is due west
        direction=7;
        if (bbox1.x>(bbox2.x+bbox2.width))
          connectLine.x1=bbox1.x;
        else
          connectLine.x1=bbox1.x+bbox1.width;
        connectLine.x2=bbox2.x+bbox2.width;
        connectLine.y1=bbox1.y+(bbox1.height*spread);
        connectLine.y2=bbox2.y+(bbox2.height*targetSpread);
      }
      else if (bbox2.y>bbox1.y)
      {                                       // 2 is south west
        direction=6;
        if (bbox2.y>(bbox1.y+bbox1.height+minGap) && orientation==2)
        {
          connectLine.x1=bbox1.x+(bbox1.width*spread);
          connectLine.x2=bbox2.x+(bbox2.width*targetSpread);
          connectLine.y1=bbox1.y+bbox1.height;
          connectLine.y2=bbox2.y;
        }
        else if (bbox1.x>(bbox2.x+bbox2.width+minGap) && orientation==1)
        {
          connectLine.y1=bbox1.y+(bbox1.height*spread);
          connectLine.y2=bbox2.y+(bbox2.height*targetSpread);
          connectLine.x1=bbox1.x;
          connectLine.x2=bbox2.x+bbox2.width;
        }
        else
        {
          connectLine.y1=bbox1.y+(bbox1.height*spread);
          connectLine.y2=bbox2.y+(bbox2.height*targetSpread);
          connectLine.x1=bbox1.x+bbox1.width;
          connectLine.x2=bbox2.x+bbox2.width;
        }
      }
      else if (bbox1.y>bbox2.y)
      {                                       // 2 is north west
        direction=8;
        if (bbox1.y>(bbox2.y+bbox2.height+minGap) && orientation==2)
        {
          connectLine.x1=bbox1.x+(bbox1.width*spread);
          connectLine.x2=bbox2.x+(bbox2.width*targetSpread);
          connectLine.y1=bbox1.y;
          connectLine.y2=bbox2.y+bbox2.height;
        }
        else if (bbox1.x>(bbox2.x+bbox2.width+minGap) && orientation==1)
        {
          connectLine.y1=bbox1.y+(bbox1.height*spread);
          connectLine.y2=bbox2.y+(bbox2.height*targetSpread);
          connectLine.x1=bbox1.x;
          connectLine.x2=bbox2.x+bbox2.width;
        }
        else
        {
          connectLine.y1=bbox1.y+(bbox1.height*spread);
          connectLine.y2=bbox2.y+(bbox2.height*targetSpread);
          connectLine.x1=bbox1.x+bbox1.width;
          connectLine.x2=bbox2.x+bbox2.width;
        }
      }
    }
    if (vector2!=null)
    {                                         // got a 2-way relationship
      dualLine={x1: connectLine.x1, y1: connectLine.y1, x2: connectLine.x2, y2: connectLine.y2};
      var dir=direction;
      if (source.directionHint)
        dir=source.directionHint;
      switch (dir)
      {                                       // work out line adjustments to split lines
        case 1: // due north
          connectLine.x1-=dualAdjust/2;
          connectLine.x2-=dualAdjust/2;
          dualLine.x1+=dualAdjust/2;
          dualLine.x2+=dualAdjust/2;
          break;
        case 2: // north east
          if (orientation==1)
          {
            connectLine.y1-=dualAdjust;
            connectLine.y2-=dualAdjust;
          }
          else
          {
            connectLine.x1+=dualAdjust*2;
            connectLine.x2+=dualAdjust*2;
          }
          break;
        case 3: // due east
          connectLine.y1+=dualAdjust/2;
          connectLine.y2+=dualAdjust/2;
          dualLine.y1-=dualAdjust/2;
          dualLine.y2-=dualAdjust/2;
          break;
        case 4: // south east
          if (orientation==1)
          {
            connectLine.y1+=dualAdjust;
            connectLine.y2+=dualAdjust;
          }
          else
          {
            connectLine.x1+=dualAdjust*2;
            connectLine.x2+=dualAdjust*2;
          }
          break;
        case 5: // due south
          connectLine.x1-=dualAdjust/2;
          connectLine.x2-=dualAdjust/2;
          dualLine.x1+=dualAdjust/2;
          dualLine.x2+=dualAdjust/2;
          break;
        case 6: // south west
          if (orientation==1)
          {
            connectLine.y1+=dualAdjust;
            connectLine.y2+=dualAdjust;
          }
          else
          {
            connectLine.x1-=dualAdjust*2;
            connectLine.x2-=dualAdjust*2;
          }
          break;
        case 7: // due west
          connectLine.y1+=dualAdjust/2;
          connectLine.y2+=dualAdjust/2;
          dualLine.y1-=dualAdjust/2;
          dualLine.y2-=dualAdjust/2;
          break;
        case 8: // north west
          if (orientation==1)
          {
            connectLine.y1-=dualAdjust;
            connectLine.y2-=dualAdjust;
          }
          else
          {
            connectLine.x1-=dualAdjust*2;
            connectLine.x2-=dualAdjust*2;
          }
          break;
      } // end switch
    }
    // we've worked out where the line should be, so draw it
    this.id="conn_"+gConnectionCounter;
    var lineColor=normalLineColour;
    if (source.typeId!=0 && target.typeId==0)
    {
      // start of polyline - start building array of points
      source.polyLine=[{x: connectLine.x1, y: connectLine.y1}, {x: connectLine.x2, y: connectLine.y2}];
      target.masterPolyLine=source.polyLine;  // keep ref to line so we can add later
      source.directionHint=direction;
      target.directionHint=direction;
//    console.debug("start line: source: "+source.id+" target: "+target.id);
      if (vector2!=null)
      {                                       // handle return part of line
        source.polyLine2=[{x: dualLine.x1, y: dualLine.y1}, {x: dualLine.x2, y: dualLine.y2}];
        target.masterPolyLine2=source.polyLine2;  // keep ref to line so we can add later
        // keep line start within box edge
        if (orientation==1)
        {
          var ymin=bbox1.y;
          var ymax=bbox1.y+bbox1.height;
          if (dualLine.y1<ymin && (direction==3 || direction==7))
          {
            var diff=ymin-dualLine.y1;
            source.polyLine2[0].y+=diff;
            source.polyLine[0].y+=diff;
          }
          else if (connectLine.y1<ymin && (direction==2 || direction==8))
          {
            var diff=ymin-connectLine.y1;
            source.polyLine2[0].y+=diff;
            source.polyLine[0].y+=diff;
          }
          else if (connectLine.y1>ymax && (direction==3 || direction==4 || direction==6 || direction==7))
          {
            var diff=connectLine.y1-ymax;
            source.polyLine2[0].y-=diff;
            source.polyLine[0].y-=diff;
          }
        }
        else
        {
          var xmin=bbox1.x;
          var xmax=bbox1.x+bbox1.width;
          if (dualLine.x1>xmax && (direction==1 || direction==5))
          {
            var diff=dualLine.x1-xmax;
            source.polyLine2[0].x-=diff;
            source.polyLine[0].x-=diff;
          }
          else if (connectLine.x1>xmax && (direction==2 || direction==4))
          {
            var diff=connectLine.x1-xmax;
            source.polyLine2[0].x-=diff;
            source.polyLine[0].x-=diff;
          }
          else if (connectLine.x1<xmin && (direction==1 || direction==5 || direction==6 || direction==8))
          {
            var diff=xmin-connectLine.x1;
            source.polyLine2[0].x+=diff;
            source.polyLine[0].x+=diff;
          }
        }
      }
    }
    else if (source.typeId==0 && target.typeId==0)
    {
      // middle of polyline - just add to master
      if (source.masterPolyLine)
      {
        // Add both start and end of line to node list since we need to create the
        // link that goes "through" the dummy node as well.
        polyLine=source.masterPolyLine;
        var len=polyLine.length;
        polyLine[len]={x: connectLine.x1, y: connectLine.y1};
        polyLine[len+1]={x: connectLine.x2, y: connectLine.y2};
        target.masterPolyLine=polyLine;       // keep ref to line so we can add later
        target.directionHint=source.directionHint;
      }
      if (vector2!=null)
      {                                       // handle return part of line
        if (source.masterPolyLine2)
        {
          // Add both start and end of line to node list since we need to create the
          // link that goes "through" the dummy node as well.
          polyLine=source.masterPolyLine2;
          var len=polyLine.length;
          polyLine[len]={x: dualLine.x1, y: dualLine.y1};
          len=len+1;
          polyLine[len]={x: dualLine.x2, y: dualLine.y2};
          target.masterPolyLine2=polyLine;    // keep ref to line so we can add later
        }
      }
    }
    else if (source.typeId==0 && target.typeId!=0)
    {
      // end of polyline - add final nodes and draw
      // construct poly-line first
      if (source.masterPolyLine)
      {
        // Add both start and end of line to node list since we need to create the
        // link that goes "through" the dummy node as well.
        polyLine=source.masterPolyLine;
        var len=polyLine.length;
        polyLine[len]={x: connectLine.x1, y: connectLine.y1};
        polyLine[len+1]={x: connectLine.x2, y: connectLine.y2};
        target.masterPolyLine=polyLine;       // keep ref to line so we can add later
        target.directionHint=source.directionHint;
      }
      if (vector2!=null)
      {                                       // handle return part of line
        if (source.masterPolyLine2)
        {
          // Add both start and end of line to node list since we need to create the
          // link that goes "through" the dummy node as well.
          polyLine=source.masterPolyLine2;
          var len=polyLine.length;
          polyLine[len]={x: dualLine.x1, y: dualLine.y1};
          polyLine[len+1]={x: dualLine.x2, y: dualLine.y2};
          target.masterPolyLine2=polyLine;       // keep ref to line so we can add later
          // keep line end within box edge
          if (orientation==1)
          {
            var ymin=bbox2.y;
            var ymax=bbox2.y+bbox2.height;
            if (dualLine.y2<ymin && (direction==3 || direction==7))
            {
              var diff=ymin-dualLine.y2;
              source.masterPolyLine[len].y+=diff;
              polyLine[len].y+=diff;
            }
            else if (connectLine.y2<ymin && (direction==2 || direction==8))
            {
              var diff=ymin-connectLine.y2;
              source.masterPolyLine[len].y+=diff;
              polyLine[len].y+=diff;
            }
            else if (connectLine.y2>ymax && (direction==3 || direction==4 || direction==6 || direction==7))
            {
              var diff=connectLine.y2-ymax;
              source.masterPolyLine[len].y-=diff;
              polyLine[len].y-=diff;
            }
          }
          else
          {
            var xmin=bbox2.x;
            var xmax=bbox2.x+bbox2.width;
            if (dualLine.x2>xmax && (direction==1 || direction==5))
            {
              var diff=dualLine.x2-xmax;
              source.masterPolyLine[len].x-=diff;
              polyLine[len].x-=diff;
            }
            else if (connectLine.x2>xmax && (direction==2 || direction==4))
            {
              var diff=connectLine.x2-xmax;
              source.masterPolyLine[len].x-=diff;
              polyLine[len].x-=diff;
            }
            else if (connectLine.x2<xmin && (direction==1 || direction==5 || direction==6 || direction==8))
            {
              var diff=xmin-connectLine.x2;
              source.masterPolyLine[len].x+=diff;
              polyLine[len].x+=diff;
            }
          }
        }
      }
      if (source.masterPolyLine)
      {
        polyLine=source.masterPolyLine;
        this.hitboxLine=surface.createPolyline({points: polyLine});
        setStroke(this.hitboxLine,{color: [255,255,255,0], width: 3, alias: true});
        this.hitboxLine.moveToBack();
        if (dojox.gfx.renderer!="silverlight")
        {
          this.hitboxLine.connect("onmouseover",this,"handleOver");
          this.hitboxLine.connect("onmouseout",this,"handleOut");
        }
        else
        {
          this.hitboxLine.connect("onmouseenter",this,"handleOver");
          this.hitboxLine.connect("onmouseleave",this,"handleOut");
        }
        this.line=surface.createPolyline({points: polyLine});
        setStroke(this.line,{color: lineColor, width: 1, alias: true});
        if (dojox.gfx.renderer!="silverlight")
        {
          this.line.connect("onmouseover",this,"handleOver");
          this.line.connect("onmouseout",this,"handleOut");
        }
        else
        {
          this.line.connect("onmouseenter",this,"handleOver");
          this.line.connect("onmouseleave",this,"handleOut");
        }
        if (vector1.reversed)
        {
          // build line segment from start of poly line to attach arrowhead to
          var revLine={x1: polyLine[1].x, y1: polyLine[1].y, x2: polyLine[0].x, y2: polyLine[0].y};
          var arrow=buildArrow(revLine,lineColor,vector1.multi);
          this.line.arrowHead=arrow;
        }
        else
        {
          // just use final line segment
          var arrow=buildArrow(connectLine,lineColor,vector1.multi);
          this.line.arrowHead=arrow;
        }
        // store useful info for later
        this.line.direction=direction;
        this.line.sourceShape=rect1;
        this.line.targetShape=rect2;
        this.line.graphEdge=edge;
        this.line.hitbox=this.hitboxLine;
        this.line.vector=vector1;
        gConnections[this.id]=this.line;
        gConnectionCounter+=1;
      }
      if (vector2!=null)
      {                                       // handle return part of line
        if (source.masterPolyLine2)
        {
          this.id2="conn_"+gConnectionCounter;      // need fresh ID for new line
          polyLine=source.masterPolyLine2;
          this.hitboxLine2=surface.createPolyline({points: polyLine});
          setStroke(this.hitboxLine2,{color: [255,255,255,0], width: 3, alias: true});
          this.hitboxLine2.moveToBack();
          if (dojox.gfx.renderer!="silverlight")
          {
            this.hitboxLine2.connect("onmouseover",this,"handleOver2");
            this.hitboxLine2.connect("onmouseout",this,"handleOut2");
          }
          else
          {
            this.hitboxLine2.connect("onmouseenter",this,"handleOver2");
            this.hitboxLine2.connect("onmouseleave",this,"handleOut2");
          }
          this.line2=surface.createPolyline({points: polyLine});
          setStroke(this.line2,{color: lineColor, width: 1, alias: true});
          if (dojox.gfx.renderer!="silverlight")
          {
            this.line2.connect("onmouseover",this,"handleOver2");
            this.line2.connect("onmouseout",this,"handleOut2");
          }
          else
          {
            this.line2.connect("onmouseenter",this,"handleOver2");
            this.line2.connect("onmouseleave",this,"handleOut2");
          }
          if (vector2.reversed)
          {
            // build line segment from start of poly line to attach arrowhead to
            var revLine={x1: polyLine[1].x, y1: polyLine[1].y, x2: polyLine[0].x, y2: polyLine[0].y};
            var arrow=buildArrow(revLine,lineColor,vector2.multi);
            this.line2.arrowHead=arrow;
          }
          else
          {
            // just use final line segment
            var arrow=buildArrow(dualLine,lineColor,vector2.multi);
            this.line2.arrowHead=arrow;
          }
          // store useful info for later
          this.line2.direction=direction;
          this.line2.sourceShape=rect1;
          this.line2.targetShape=rect2;
          this.line2.graphEdge=edge;
          this.line2.hitbox=this.hitboxLine2;
          this.line2.vector=vector2;
          gConnections[this.id2]=this.line2;
          gConnectionCounter+=1;
        }
      }
    }
    else
    {
      // regular line, no dummies
      this.hitboxLine=surface.createLine(connectLine);
      setStroke(this.hitboxLine,{color: [255,255,255,0], width: 3, alias: true});
      this.hitboxLine.moveToBack();
      if (dojox.gfx.renderer!="silverlight")
      {
        this.hitboxLine.connect("onmouseover",this,"handleOver");
        this.hitboxLine.connect("onmouseout",this,"handleOut");
      }
      else
      {
        this.hitboxLine.connect("onmouseenter",this,"handleOver");
        this.hitboxLine.connect("onmouseleave",this,"handleOut");
      }
      this.line=surface.createLine(connectLine);
      setStroke(this.line,{color: lineColor, width: 1, alias: true});
      if (dojox.gfx.renderer!="silverlight")
      {
        this.line.connect("onmouseover",this,"handleOver");
        this.line.connect("onmouseout",this,"handleOut");
      }
      else
      {
        this.line.connect("onmouseenter",this,"handleOver");
        this.line.connect("onmouseleave",this,"handleOut");
      }
      if (vector1.reversed)
      {
        var revLine={x1: connectLine.x2, y1: connectLine.y2, x2: connectLine.x1, y2: connectLine.y1};
        var arrow=buildArrow(revLine,lineColor,vector1.multi);
        this.line.arrowHead=arrow;
      }
      else
      {
        var arrow=buildArrow(connectLine,lineColor,vector1.multi);
        this.line.arrowHead=arrow;
      }
      // store useful info for later
      this.line.direction=direction;
      this.line.sourceShape=rect1;
      this.line.targetShape=rect2;
      this.line.graphEdge=edge;
      this.line.hitbox=this.hitboxLine;
      this.line.vector=vector1;
      gConnections[this.id]=this.line;
      gConnectionCounter+=1;
      if (vector2!=null)
      {                                       // handle return part of line
        this.id2="conn_"+gConnectionCounter;        // need fresh ID for new line
        this.hitboxLine2=surface.createLine(dualLine);
        setStroke(this.hitboxLine2,{color: [255,255,255,0], width: 3, alias: true});
        this.hitboxLine2.moveToBack();
        if (dojox.gfx.renderer!="silverlight")
        {
          this.hitboxLine2.connect("onmouseover",this,"handleOver2");
          this.hitboxLine2.connect("onmouseout",this,"handleOut2");
        }
        else
        {
          this.hitboxLine2.connect("onmouseenter",this,"handleOver2");
          this.hitboxLine2.connect("onmouseleave",this,"handleOut2");
        }
        this.line2=surface.createLine(dualLine);
        setStroke(this.line2,{color: lineColor, width: 1, alias: true});
        if (dojox.gfx.renderer!="silverlight")
        {
          this.line2.connect("onmouseover",this,"handleOver2");
          this.line2.connect("onmouseout",this,"handleOut2");
        }
        else
        {
          this.line2.connect("onmouseenter",this,"handleOver2");
          this.line2.connect("onmouseleave",this,"handleOut2");
        }
        if (vector2.reversed)
        {
          var revLine={x1: dualLine.x2, y1: dualLine.y2, x2: dualLine.x1, y2: dualLine.y1};
          var arrow=buildArrow(revLine,lineColor,vector2.multi);
          this.line2.arrowHead=arrow;
        }
        else
        {
          var arrow=buildArrow(dualLine,lineColor,vector2.multi);
          this.line2.arrowHead=arrow;
        }
        // store useful info for later
        this.line2.direction=direction;
        this.line2.sourceShape=rect1;
        this.line2.targetShape=rect2;
        this.line2.graphEdge=edge;
        this.line2.hitbox=this.hitboxLine2;
        this.line2.vector=vector2;
        gConnections[this.id2]=this.line2;
        gConnectionCounter+=1;
      }
    }
  }

  this.handleOver=function(/*DOMEvent*/event)
  {
    highlightLine(this.line);
    // suppress tooltip reshow if mouse hasnt moved
    if (tooltipLastX!=event.clientX || tooltipLastY!=event.clientY)
      showLineTooltip(event,this.line);
    tooltipLastX=event.clientX;
    tooltipLastY=event.clientY;
    dojo.stopEvent(event);
  }
  
  this.handleOut=function(/*DOMEvent*/event)
  {
    unHighlightLine(this.line);
    // suppress tooltip hide when it's cornered by the screen and the mouse hasn't moved
    if (tooltipLastX!=event.clientX || tooltipLastY!=event.clientY)
      hideTooltip();
    dojo.stopEvent(event);
  }

  this.handleOver2=function(/*DOMEvent*/event)
  {
    highlightLine(this.line2);
    // suppress tooltip reshow if mouse hasnt moved
    if (tooltipLastX!=event.clientX || tooltipLastY!=event.clientY)
      showLineTooltip(event,this.line2);
    tooltipLastX=event.clientX;
    tooltipLastY=event.clientY;
    dojo.stopEvent(event);
  }
  
  this.handleOut2=function(/*DOMEvent*/event)
  {
    unHighlightLine(this.line2);
    // suppress tooltip hide when it's cornered by the screen and the mouse hasn't moved
    if (tooltipLastX!=event.clientX || tooltipLastY!=event.clientY)
      hideTooltip();
    dojo.stopEvent(event);
  }
}

/**
 * Draw an arrowhead on the end of the line with correct orientation.
 */
function buildArrow(/*object*/line, /*array*/lineColor, /*boolean*/multi)
{
  var arrowHead=null;
  var arrowAngle=30*Math.PI/180;  // define the number of degrees above the line
  var arrowLength=6;              // define the arrowhead length
  var dx=line.x1-line.x2;
  var dy=line.y2-line.y1;
  var xlength=Math.abs(dx);
  var ylength=Math.abs(dy);
  var lineLength=Math.sqrt(xlength*xlength + ylength*ylength);
  if (lineLength>arrowLength)
  {                                         // big enough to add arrow
    // The maths here constructs an arrowhead normalised around the x-axis to make the
    // calculations easy. It then applies a rotational transform and an x,y shift to
    // position the arrowHead at any arbitrary angle and location on the screen.
    var lineAngle=Math.atan(ylength/xlength);
    // prevent very small angles from disturbing VML
    var absAngle=Math.abs(lineAngle);
    if (absAngle<0.0001)
    	lineAngle=0.0;
    var arrowx=arrowLength;
    var arrowy=Math.tan(arrowAngle)*arrowx;
    // work out the translation angle based on which quadrant the line is in
    var translateAngle=lineAngle;
    if (dx<0 && dy>0)
      translateAngle=((Math.PI/2)-lineAngle)+Math.PI/2;
    else if (dx<0 && dy<=0)
      translateAngle=lineAngle+Math.PI;
    else if (dx>=0 && dy<0)
      translateAngle=((Math.PI/2)-lineAngle)+Math.PI*3/2;
    // polyline automatically fills in the third side of the triangle for us when we fill it
    arrowHead=surface.createPolyline({points: [{x: arrowx, y: arrowy}, {x: 0, y: 0}, {x: arrowx, y: -arrowy}]});
    setStroke(arrowHead,{color: lineColor, alias: true});
    arrowHead.applyTransform({xx: Math.cos(translateAngle), xy: Math.sin(translateAngle), yx: -Math.sin(translateAngle), yy: Math.cos(translateAngle),dx: line.x2, dy: line.y2});
    arrowHead.setFill(lineColor);
    if (multi)
    {
      var arrowHead2=surface.createPolyline({points: [{x: arrowx+arrowLength+2, y: arrowy}, {x: arrowLength+2, y: 0}, {x: arrowx+arrowLength+2, y: -arrowy}]});
      setStroke(arrowHead2,{color: lineColor, alias: true});
      arrowHead2.applyTransform({xx: Math.cos(translateAngle), xy: Math.sin(translateAngle), yx: -Math.sin(translateAngle), yy: Math.cos(translateAngle),dx: line.x2, dy: line.y2});
      arrowHead2.setFill(lineColor);
      arrowHead.subArrow=arrowHead2;
    }
  }
  return(arrowHead);
}

/**
 * Populates the initial view.
 */
function populateDisplay()
{
  showTimer();                              // tell the user we're doing something
  blockAllLinks();
  fetchGraph(startBsrUri);                  // do the real work
  // callback now calls populateDisplay2
}

/**
 * Callback function from fetching graph settings
 */
function settingsCallback(settings)
{
	graphSettings=settings;
  try
  {
    processSettings();
    replaceCanvas();                        // resize must be after settings are processed to get scaling factors
    drawEntities();                         // show em
    drawMiniMap();                          // create the mini-map
    buildObjectList();
    addToHistory();
    centerAnchor();
  }
  catch (anyError)
  {
  	var msg=TRANS_ERROR_EXCEPTION+" "+anyError.name+": "+anyError.message;
  	if (anyError.fileName)
  		msg=msg+"; "+TRANS_SOURCE_FILE+" "+anyError.fileName;
  	if (anyError.lineNumber)
  		msg=msg+"; "+TRANS_FAILING_LINE+" "+anyError.lineNumber;
    displayError(msg,null);
  }
  finally
  {
    // must guarantee to clean up the page and the timer at end
    unblockAllLinks();
    hideTimer();                              // done
  }
}

/**
 * Called from graph fetch callback to complete the populate.
 */
function populateDisplay2()
{
  var deferred=graphService.getGraphSettings();
  deferred.addCallback(settingsCallback);
  deferred.addErrback(processRPCException);
	// continues in settingsCallback()
}

/**
 * Build the object list in the right column.
 */
function buildObjectList()
{
  var containerDiv=null;
  var listContents=dojo.byId("listcontents");
  // must clear out any existing divs that we created before
  var children=listContents.childNodes;
  var removeList=[];
  var removeCount=0;
  for (var i=0;i<children.length;i++)
  {                                         // build list of matching children
    var child=children[i];
    if (child.id && child.id.substring(0,8)=="listcon_")
    {
      removeList[removeCount]=child;
      removeCount+=1;
    }
  } // end for
  for (var i=0;i<removeCount;i++)
  {                                         // clear out old divs
    listContents.removeChild(removeList[i]);
  } // end for
  // build list of new items
  for (var uid in entityList)
  {
    var entity=entityList[uid];
    if (entity.typeId!=0)
    {
      containerDiv=document.createElement("div");
      containerDiv.setAttribute("class","objectdivider");
      containerDiv.setAttribute("className","objectdivider");
      containerDiv.setAttribute("id","listcon_"+uid);
      var outerHighlight="outerhighlightoff";
      if (entity.isAnchor)
        outerHighlight="outerhighlightanchor";
      var outerDiv=document.createElement("div");
      outerDiv.setAttribute("class",outerHighlight);
      outerDiv.setAttribute("className",outerHighlight);
      outerDiv.setAttribute("id",uid+"_outer");
      containerDiv.appendChild(outerDiv);
      var innerHighlight="innerhighlightoff";
      if (entity.isSelected)
        innerHighlight="innerhighlighton";
      var innerDiv=document.createElement("div");
      innerDiv.setAttribute("class",innerHighlight);
      innerDiv.setAttribute("className",innerHighlight);
      innerDiv.setAttribute("id",uid+"_inner");
      innerDiv.setAttribute("hoverid",uid);
      outerDiv.appendChild(innerDiv);
      var link=document.createElement("a");
      link.setAttribute("href","javascript:toggleEntitySelection('"+uid+"');");
      link.setAttribute("title",entity.properties.name);
      link.setAttribute("hoverid",uid);
      innerDiv.appendChild(link);
      var image=document.createElement("img");
      var iconName=typeIcons[entity.typeId];
      if (!iconName)
      	iconName=typeIcons[10];	// custom types use generic object icon
      image.setAttribute("src","theme/"+currentTheme+"/images/types/"+iconName+".gif");
      var entityType=graphSettings.typeTranslations[entity.typeId];
      image.setAttribute("alt",entityType);
      image.setAttribute("title",entityType);
      image.setAttribute("hoverid",uid);
      var text=document.createTextNode(entity.properties.name);
      link.appendChild(image);
      link.appendChild(text);
      listContents.appendChild(containerDiv);
      dojo.connect(innerDiv,'onmouseover','handleListMouseOver');
      dojo.connect(innerDiv,'onmouseout','handleListMouseOut');
    }
  } // end for
  if (containerDiv)
  {
    // set the final container div to not have the divider line
    containerDiv.setAttribute("class","");
    containerDiv.setAttribute("className","");
  }
}

/**
 * Adds the current starting bsrURI to the history list.
 */
function addToHistory()
{
  if (suppressHistoryAdd)
    suppressHistoryAdd=false;
  else
  {
    var containerDiv=null;
    var name="";
    var type="";
    for (var uid in entityList)
    {
      var entity=entityList[uid];
      if (entity.isAnchor)
      {
        name=entity.properties.name;
        type=graphSettings.typeTranslations[entity.typeId];
      }
    }
    var len=navigationHistory.length;
    navigationHistory[len]={name: name, bsrURI: startBsrUri};
    // add a link to the history list
    var listContents=dojo.byId("listcontents2");
    containerDiv=document.createElement("div");
    containerDiv.setAttribute("class","historydivider");
    containerDiv.setAttribute("className","historydivider");
    containerDiv.setAttribute("id","history_"+len);
    var link=document.createElement("a");
    link.setAttribute("href","javascript:gotoHistory('"+len+"');");
    link.setAttribute("title",type+" - "+name);
    var text=document.createTextNode(name);
    link.appendChild(text);
    containerDiv.appendChild(link);
    listContents.appendChild(containerDiv);
  }
}

/**
 * Change the view to go back to the history index given.
 */
function gotoHistory(/*integer*/index)
{
  hideMessages();
  if (timerActive==0)
  {                                         // only allow if timer is not showing
    var history=navigationHistory[index];
    if (history)
    {
      startBsrUri=history["bsrURI"];        // change the starting bsrURI
      suppressHistoryAdd=true;              // dont add this view back to the history
      populateDisplay();                    // redraw the graph
    }
  }
}

/**
 * Draw the mini-map version of the entities. Only draws the rectangles, nothing else.
 */
function drawMiniMap()
{
  clearSurface(viewportSurface);
  var xFactor=xScale*viewportWidth/surfaceWidth;
  var yFactor=yScale*viewportHeight/surfaceHeight;
  var boundingBox=objectGraph.boundingBox;
  var offsetX=canvasBorder*viewportWidth/surfaceWidth;  // minimum border
  var offsetY=canvasBorder*viewportHeight/surfaceHeight;
  if (boundingBox.minX<0)
    offsetX=offsetX+(-1*boundingBox.minX*xFactor); // normalise X coord
  if (boundingBox.minY<0)
    offsetY=offsetY+(-1*boundingBox.minY*yFactor); // normalise Y coord
  var rectWidth=entityWidth*viewportWidth/surfaceWidth;
  var rectHeight=entityHeight*viewportHeight/surfaceHeight;
  var x=0;
  var y=0;
  // prevent VML from being given bad objects
  if (rectWidth<1.0)
    rectWidth=1.0;
  if (rectHeight<1.0)
    rectHeight=1.0;
  for (var uid in entityList)
  {
    var entity=entityList[uid];
    if (entity.typeId!=0)
    {
      x=offsetX+entity.xpos*xFactor;
      y=offsetY+entity.ypos*yFactor;
      if (isRTL && orientation==1)
        x=viewportWidth-x-rectWidth;
      var mainRect=viewportSurface.createRect({x: x, y: y, width: rectWidth, height: rectHeight});
      setStroke(mainRect,{color: normalBoxColour, width: 1, style: "Solid"});
    }
  } // end for
  drawViewport();                           // put the drag rect back on
}

/**
 * Obtains the current browser locale setting.
 */
function getBrowserLocale()
{
  // have to check both vars for locale since IE and FF do if differently
  var locale="en";
  if (navigator.language)
    locale=navigator.language;
  else if (navigator.userLanguage)
    locale=navigator.userLanguage;
  return(locale);
}

/**
 * Re-center the graph on the selected entity.
 */
function reCenterGraph()
{
  hideMessages();
  var entity=null;
  for (var uid in entityList)
  {                                         // find out which one is selected
    entity=entityList[uid];
    if (entity.isSelected)
      break;
  } // end for
  if (entity)
  {
    var properties=entity.properties;
    startBsrUri=properties["bsrURI"];       // change the starting bsrURI
    populateDisplay();                      // redraw the graph
  }
}

/**
 * Changes the page URL to display the details view for the selected object.
 */
function showDetails()
{
  hideMessages();
  var entity=null;
  for (var uid in entityList)
  {                                         // find out which one is selected
    entity=entityList[uid];
    if (entity.isSelected)
      break;
  } // end for
  if (entity)
  {
    var properties=entity.properties;
    var bsrUri=properties["bsrURI"];       	// get the entity bsrURI
    window.location.href="ViewDetail.do?uri="+bsrUri;	// redirect
  }
}

/**
 * Calls to the collection view add properties page.
 */
function addProperties()
{
  submitButtonAction("addProperty");
}

/**
 * Calls to the collection view add relationships page.
 */
function addRelationships()
{
  submitButtonAction("addRelationship");
}

/**
 * Calls to the collection view add classifications page.
 */
function addClassifications()
{
  submitButtonAction("addClassifications");
}

/**
 * Calls to the collection view add favourites page.
 */
function addFavourites()
{
  submitButtonAction("addFavorites");
}

/**
 * Calls to the collection view export entities page.
 */
function exportEntities()
{
  submitButtonAction("exportDocuments");
}

/**
 * Calls to the collection view subscribe entities page.
 */
function subscribeEntities()
{
  submitButtonAction("subscribe");
}

/**
 * Common routine to submit the collection form to simulate a button press
 * as if it had come from a conventional collection view.
 */
function submitButtonAction(/*string*/actionName)
{
  hideMessages();
  var entities=[];
  var count=0;
  for (var uid in entityList)
  {                                         // get the list of selected entities
    var entity=entityList[uid];
    if (entity.isSelected)
    {
      var properties=entity.properties;
      var bsrUri=properties["bsrURI"];      // get the entity bsrURI
      entities[count]=bsrUri;
      count+=1;
    }
  } // end for
  // build the hidden fields that we need to submit
  var form=dojo.byId("collectionform");
  var content="<input type=\"hidden\" name="+actionName+" value=\"set\" />";
  for (var i=0;i<count;i++)
  {
    var line="<input type=\"hidden\" name=\"selectedItemIds\" value="+entities[i]+" />";
    content=content+line;
  } // end for
  form.innerHTML=content;
  document.CollectionForm.submit();         // submit the form
}

/**
 * Shows more relationships from the given widget shape by doing the
 * equivalent of re-focussing the graph on the parent entity.
 */
function showMore(/*shape*/shape)
{
  hideMessages();
  var entity=shape.parentEntity;
  if (entity)
  {
    var properties=entity.properties;
    startBsrUri=properties["bsrURI"];       // change the starting bsrURI
    populateDisplay();                      // redraw the graph
  }
}

/**
 * Process the settings to make sure all screen setup is correct.
 */
function processSettings()
{
  var depth=graphSettings.depth;
  var displayMode=graphSettings.objectDisplayMode;
  var orient=graphSettings.orientation;
  var impactMode=graphSettings.impactMode;
  if (!impactMode)
  {
    if (depth=="auto")
      dojo.byId("depthauto").checked=true;
    else if (depth=="unlimited")
      dojo.byId("depthunlimited").checked=true;
    else
    {
      var depthItem=dojo.byId("depth"+depth);
      if (depthItem)
        depthItem.checked=true;
    }
    if (displayMode=="all")
      dojo.byId("displayallobjects").checked=true;
    else if (displayMode=="contents")
      dojo.byId("displaycontents").checked=true;
    else if (displayMode=="external")
      dojo.byId("displayexternal").checked=true;
  }
  if (orient=="leftRight")
  {
    dojo.byId("orientleftright").checked=true;
    xScale=orientScale[0].xScale;
    yScale=orientScale[0].yScale;
    orientation=1;
  }
  else if (orient=="topBottom")
  {
    dojo.byId("orienttopbottom").checked=true;
    xScale=orientScale[1].xScale;
    yScale=orientScale[1].yScale;
    orientation=2;
  }
}

/**
 * Switch the tabs of the options/list column.
 */
function switchToTab(/*integer*/tabId)
{
  if (timerActive==0)
  {                                         // only allow if timer is not showing
    if (tabId==1)
    {                                       // options tab
      var tab=dojo.byId("optionstab1");
      tab.className="visibletab";
      var tab=dojo.byId("optionstab2");
      tab.className="invisibletab";
      var tab=dojo.byId("optionstab1title");
      tab.className="tabon";
      var tab=dojo.byId("optionstab2title");
      tab.className="taboff";
    }
    else
    {                                       // list tab
      var tab=dojo.byId("optionstab1");
      tab.className="invisibletab";
      var tab=dojo.byId("optionstab2");
      tab.className="visibletab";
      var tab=dojo.byId("optionstab1title");
      tab.className="taboff";
      var tab=dojo.byId("optionstab2title");
      tab.className="tabon";
    }
  }
}

/**
 * Get the shape that was targetted from a mouse event.
 */
function getShape(/*DOMevent*/event)
{
  var id=event.target.getAttribute('shapeid');
  var s=id ? gShapes[id] : null;
  return s;
}

/**
 * Get the line that was targetted from a mouse event.
 */
function getLineShape(/*DOMevent*/event)
{
  var id=event.target.getAttribute('connid');
  var s=id ? gConnections[id] : null;
  return s;
}

/**
 * Get the widget that was targetted from a mouse event.
 */
function getWidgetShape(/*DOMevent*/event)
{
  var id=event.target.getAttribute('widgetid');
  var s=id ? gWidgets[id] : null;
  return s;
}

/**
 * Highlight the shape due to a mouse over.
 */
function highlightEntity(/*shape*/shape)
{
  var entity=entityList[shape.uid];
  var lineStyle="Solid";
  if (entity.isLogical)
    lineStyle="Dash";
  setStroke(shape,{color: highlightBoxColour, width: 2, style: lineStyle});
  var listOuter=dojo.byId(shape.uid+"_outer");
  listOuter.className="outerhighlighton";
}

/**
 * Un-highlight the shape due to a mouse over.
 */
function unHighlightEntity(/*shape*/shape)
{
  var entity=entityList[shape.uid];
  var lineWidth=1;
  if (entity.isAnchor)
    lineWidth=2;                            // anchor object gets bold outline
  var lineStyle="Solid";
  if (entity.isLogical)
    lineStyle="Dash";
  setStroke(shape,{color: normalBoxColour, width: lineWidth, style: lineStyle});
  var listOuter=dojo.byId(shape.uid+"_outer");
  if (entity.isAnchor)
    listOuter.className="outerhighlightanchor";
  else
    listOuter.className="outerhighlightoff";
}

/**
 * Highlight the line due to a mouse over.
 */
function highlightLine(/*shape*/line)
{
  setStroke(line,{color: highlightLineColour, width: 1, alias: true});
  var arrowHead=line.arrowHead;
  if (arrowHead)
  {
    setStroke(arrowHead,{color: highlightLineColour, alias: true});
    arrowHead.setFill(highlightLineColour);
    if (arrowHead.subArrow)
    {
      setStroke(arrowHead.subArrow,{color: highlightLineColour, alias: true});
      arrowHead.subArrow.setFill(highlightLineColour);
    }
  }
}

/**
 * Un-highlight the line due to a mouse over.
 */
function unHighlightLine(/*shape*/line)
{
  setStroke(line,{color: normalLineColour, width: 1, alias: true});
  var arrowHead=line.arrowHead;
  if (arrowHead)
  {
    setStroke(arrowHead,{color: normalLineColour, alias: true});
    arrowHead.setFill(normalLineColour);
    if (arrowHead.subArrow)
    {
      setStroke(arrowHead.subArrow,{color: normalLineColour, alias: true});
      arrowHead.subArrow.setFill(normalLineColour);
    }
  }
}

/**
 * Highlight the list entry due to a mouse over.
 */
function highlightListEntity(/*string*/uid)
{
  var listOuter=dojo.byId(uid+"_outer");
  listOuter.className="outerhighlighton";
  var entity=entityList[uid];
  var lineStyle="Solid";
  if (entity.isLogical)
    lineStyle="Dash";
  setStroke(entity.rect,{color: highlightBoxColour, width: 2, style: lineStyle});
}

/**
 * Un-highlight the list entry due to a mouse out.
 */
function unHighlightListEntity(/*string*/uid)
{
  var entity=entityList[uid];
  var listOuter=dojo.byId(uid+"_outer");
  if (entity.isAnchor)
    listOuter.className="outerhighlightanchor";
  else
    listOuter.className="outerhighlightoff";
  var lineWidth=1;
  if (entity.isAnchor)
    lineWidth=2;                            // anchor object gets bold outline
  var lineStyle="Solid";
  if (entity.isLogical)
    lineStyle="Dash";
  setStroke(entity.rect,{color: normalBoxColour, width: lineWidth, style: lineStyle});
}

/**
 * Highlight the widget due to a mouse over.
 */
function highlightWidget(/*shape*/shape)
{
  setStroke(shape,{color: highlightBoxColour, width: 1, style: "Solid"});
  var line=shape.connectLine;
  setStroke(line,{color: highlightLineColour, width: 1, style: "dash", alias: false});
  var entity=shape.parentEntity;
  highlightEntity(entity.rect);
}

/**
 * Un-highlight the widget due to a mouse out.
 */
function unHighlightWidget(/*shape*/shape)
{
  setStroke(shape,{color: normalBoxColour, width: 1, style: "Solid"});
  var line=shape.connectLine;
  setStroke(line,{color: normalLineColour, width: 1, style: "dash", alias: false});
  var entity=shape.parentEntity;
  unHighlightEntity(entity.rect);
}

/**
 * Apply focus to the given object.
 */
function focusOnShape(/*DOMevent*/event, /*shape*/focusShape)
{
  if (event && !event.ctrlKey)
  {
    for (var key in gShapes)
    {                                       // remove focus from all
      var shape=gShapes[key];
      var entity=entityList[shape.uid];
      if (entity.isSelected)
      {
        entity.isSelected=false;
        shape.setFill([255,255,255,1]);
        setStroke(shape.centerLine,{color: normalBoxColour, width: 1, style: "Dot"});
        shape.nameText.setFill(normalBoxColour);
        shape.typeText.setFill(normalBoxColour);
        if (dojox.gfx.renderer=="vml")
          shape.rawNode.fill.type="solid";
        var listInner=dojo.byId(shape.uid+"_inner");
        listInner.className="innerhighlightoff";
      }
    } // end for
  }
  var focusEntity=entityList[focusShape.uid];
  if (focusEntity.isSelected && event.ctrlKey)
  {
    focusEntity.isSelected=false;
    focusShape.setFill([255,255,255,1]);
    setStroke(focusShape.centerLine,{color: normalBoxColour, width: 1, style: "Dot"});
    focusShape.nameText.setFill(normalBoxColour);
    focusShape.typeText.setFill(normalBoxColour);
    if (dojox.gfx.renderer=="vml")
      focusShape.rawNode.fill.type="solid";
    var listInner=dojo.byId(focusShape.uid+"_inner");
    listInner.className="innerhighlightoff";
  }
  else
  {
    focusEntity.isSelected=true;
    var rawShape=focusShape.getShape();
    var x1=rawShape.x;
    var y1=rawShape.y;
    var x2=rawShape.x;
    var y2=rawShape.y+rawShape.height;
    focusShape.setFill({type: "linear", x1: x1, y1: y1, x2: x2, y2: y2, colors: [{offset: 0, color: selectGradeTop}, {offset: 1, color: selectGradeBottom}]});
    setStroke(focusShape.centerLine,{color: [255,255,255,1], width: 1, style: "Dot"});
    focusShape.nameText.setFill([255,255,255,1]);
    focusShape.typeText.setFill([255,255,255,1]);
    var listInner=dojo.byId(focusShape.uid+"_inner");
    listInner.className="innerhighlighton";
  }
  adjustActions(true);
}

/**
 * Adjust the dynamic actions based on the number of things selected.
 */
function adjustActions(/*boolean*/enable)
{
  var count=0;
  var containsLogical=false;
  // defaults to 0 if not enabled - i.e. turn all off
  if (enable)
  {
    for (var uid in entityList)
    {                                       // find out how many are selected
      var entity=entityList[uid];
      if (entity.isSelected)
      {
        count+=1;
        if (entity.isLogical)
          containsLogical=true;
      }
    } // end for
  }
  if (count==0)
  {
    setActionState("actionrecenter",false);
    setActionState("actiondetails",false);
    setActionState("actionaddprops",false);
    setActionState("actionaddrel",false);
    setActionState("actionaddclass",false);
    setActionState("actionaddfavorites",false);
    setActionState("actionexport",false);
    setActionState("actionsubscribe",false);
  }
  else
  {
    setActionState("actionaddprops",true);
    setActionState("actionaddrel",true);
    setActionState("actionaddclass",true);
    setActionState("actionaddfavorites",true);
    setActionState("actionsubscribe",true);
    if (containsLogical)
      setActionState("actionexport",false); // cant export logical objects
    else
      setActionState("actionexport",true);
    if (count==1)
    {
      setActionState("actionrecenter",true);
      setActionState("actiondetails",true);
    }
    else
    {
      setActionState("actionrecenter",false);
      setActionState("actiondetails",false);
    }
  }
}

/**
 * Set the state of an individual action on screen.
 */
function setActionState(/*string*/id,/*boolean*/enabled)
{
  var action=actionHTML[id];
  if (enabled && !action.enabled)
  {                                         // toggle to enabled
    var item=dojo.byId(id);
    item.innerHTML=action.rawHTML;          // restore original HTML
    action.enabled=true;
  }
  else if (!enabled && action.enabled)
  {                                         // toggle to disabled
    var item=dojo.byId(id);
    item.innerHTML=action.text;             // remove link
    action.enabled=false;
  }
}

/**
 * Blocks all active links in the right column - used while we're
 * fetching a new graph view.
 */
function blockAllLinks()
{
  adjustActions(false);                     // turn off all actions
  var displayAll=dojo.byId("displayallobjects");
  var displayContents=dojo.byId("displaycontents");
  var displayExternal=dojo.byId("displayexternal");
  var depthAuto=dojo.byId("depthauto");
  var depth1=dojo.byId("depth1");
  var depth2=dojo.byId("depth2");
  var depth3=dojo.byId("depth3");
  var depth4=dojo.byId("depth4");
  var depthUnlimited=dojo.byId("depthunlimited");
  var orientLeftRight=dojo.byId("orientleftright");
  var orientTopBottom=dojo.byId("orienttopbottom");
  if (displayAll)
    displayAll.disabled=true;
  if (displayContents)
    displayContents.disabled=true;
  if (displayExternal)
    displayExternal.disabled=true;
  if (depthAuto)
   	depthAuto.disabled=true;
  if (depth1)
    depth1.disabled=true;
 	if (depth2)
   	depth2.disabled=true;
  if (depth3)
    depth3.disabled=true;
  if (depth4)
   	depth4.disabled=true;
  if (depthUnlimited)
    depthUnlimited.disabled=true;
  orientLeftRight.disabled=true;
  orientTopBottom.disabled=true;
}

/**
 * Unblocks the links in the right column.
 */
function unblockAllLinks()
{
  adjustActions(true);
  var displayAll=dojo.byId("displayallobjects");
  var displayContents=dojo.byId("displaycontents");
  var displayExternal=dojo.byId("displayexternal");
  var depthAuto=dojo.byId("depthauto");
  var depth1=dojo.byId("depth1");
  var depth2=dojo.byId("depth2");
  var depth3=dojo.byId("depth3");
  var depth4=dojo.byId("depth4");
  var depthUnlimited=dojo.byId("depthunlimited");
  var orientLeftRight=dojo.byId("orientleftright");
  var orientTopBottom=dojo.byId("orienttopbottom");
  if (displayAll)
    displayAll.disabled=false;
  if (displayContents)
   	displayContents.disabled=false;
  if (displayExternal)
    displayExternal.disabled=false;
  if (depthAuto)
   	depthAuto.disabled=false;
  if (depth1)
    depth1.disabled=false;
  if (depth2)
   	depth2.disabled=false;
  if (depth3)
    depth3.disabled=false;
  if (depth4)
   	depth4.disabled=false;
  if (depthUnlimited)
    depthUnlimited.disabled=false;
  orientLeftRight.disabled=false;
  orientTopBottom.disabled=false;
}

/**
 * Harvest the action HTML from the page so we can manipulate it.
 */
function harvestActionHTML()
{
  var length=actionIdList.length;
  for (var i=0;i<length;i++)
  {
    var actionId=actionIdList[i];
    var item=dojo.byId(actionId);
    actionHTML[actionId]={rawHTML: item.innerHTML, enabled: true};  // store the raw HTML so we can restore easily
    var linkNode=item.childNodes[0];
    if (linkNode)
    {
      var value=linkNode.childNodes[0];
      actionHTML[actionId].text=value.nodeValue;  // store the display text
    }
  } // end for
}

/**
 * Show the entity tooltip dynamically.
 */
function showTooltip(/*DOMevent*/event,/*shape*/shape)
{
  if (!tooltipShowing)
  {
    var entity=entityList[shape.uid];
    var entityType=graphSettings.typeTranslations[entity.typeId];
    var propertyList=graphSettings.standardProperties;
    var objName="";
    if (entity.properties.name)
    	objName=entity.properties.name;
    var caption="<table border=0 cellpadding=0 cellspacing=0><tr><td><span style='font-weight: bold;'>"+TRANS_TOOLTIP_NAME+"</span></td><td><span style='font-weight: bold;'>"+objName+"</span></td></tr><tr><td>"+TRANS_TOOLTIP_TYPE+"</td><td>"+entityType+"</td></tr>";
    var properties=entity.properties;
    // note: propertyList is an ordered list so is used to determine the display order
    var len=propertyList.length;
    for (var i=0;i<len;i++)
    {
      var key=propertyList[i];
      var value=properties[key];
      if (key!="name")
      {                                     // all properties other than name
      	var transKey=graphSettings.propertyTranslations[key];
      	if (!transKey)
      		transKey=key;											// backoff to raw key if no translation
        var line="<tr><td>"+transKey+TRANS_COLON+"</td><td>"+value+"</td></tr>";
        caption=caption+line;
      }
    } // end for
    caption=caption+"</table>";
    var tooltipDiv=dojo.byId("graphtooltip");
    tooltipDiv.innerHTML=caption;
    var corners={"BL":"TL","BR":"TR","TL":"BL","TR":"BR"};
    if (isRTL)
      corners={"BR":"TR","BL":"TL","TR":"BR","TL":"BL"};
    placeAroundRect(tooltipDiv,{x:event.clientX-2, y:event.clientY-2, width:25, height:25},corners);
    tooltipShape=shape;
    tooltipTimeout=window.setTimeout(displayTooltip,tooltipDelay);
  }
}

/**
 * Show the line tooltip dynamically.
 */
function showLineTooltip(/*DOMevent*/event,/*shape*/shape)
{
  if (!tooltipShowing)
  {
    var edge=shape.graphEdge;
    var caption="<table border=0 cellpadding=0 cellspacing=0>";
    var vectorNames=shape.vector.names;
    var len=vectorNames.length;
    for (var i=0;i<len;i++)
    {
      var name=vectorNames[i];
      var line="";
      if (i<(len-1))
        line="<tr><td class=\"tooltipdivide\">"+name+"</td></tr>";
      else
        line="<tr><td>"+name+"</td></tr>";
      caption=caption+line;
    } // end for
    caption=caption+"</table>";
    var tooltipDiv=dojo.byId("graphtooltip");
    tooltipDiv.innerHTML=caption;
    var corners={"BL":"TL","BR":"TR","TL":"BL","TR":"BR"};
    if (isRTL)
      corners={"BR":"TR","BL":"TL","TR":"BR","TL":"BL"};
    placeAroundRect(tooltipDiv,{x:event.clientX-2, y:event.clientY-2, width:25, height:25},corners);
    tooltipShape=shape;
    tooltipTimeout=window.setTimeout(displayTooltip,tooltipDelay);
  }
}

/**
 * Show the widget tooltip dynamically.
 */
function showWidgetTooltip(/*DOMevent*/event,/*shape*/shape)
{
  if (!tooltipShowing)
  {
    var caption="<table border=0 cellpadding=0 cellspacing=0 style='width: 250px;'>";
    caption=caption+"<tr><td><span style='font-weight: bold;'>"+TRANS_ADDITIONAL_RELS+"</span></td></tr>";
    caption=caption+"<tr><td>"+TRANS_SHOW_ADDITIONAL+"</td></tr>";
    caption=caption+"</table>";
    var tooltipDiv=dojo.byId("graphtooltip");
    tooltipDiv.innerHTML=caption;
    var corners={"BL":"TL","BR":"TR","TL":"BL","TR":"BR"};
    if (isRTL)
      corners={"BR":"TR","BL":"TL","TR":"BR","TL":"BL"};
    placeAroundRect(tooltipDiv,{x:event.clientX-2, y:event.clientY-2, width:25, height:25},corners);
    tooltipShape=shape;
    tooltipTimeout=window.setTimeout(displayTooltip,tooltipDelay);
  }
}

/**
 * Display the tooltip on screen
 */
function displayTooltip(/*DOMElement*/tooltipDiv)
{
  if (tooltipShape)
  {
    var tooltipDiv=dojo.byId("graphtooltip");
    tooltipDiv.style.display="block";
    tooltipShowing=true;
    if (tooltipHideTimeout)
      clearTimeout(tooltipHideTimeout);     // clear any old hide timer
    tooltipHideTimeout=window.setTimeout(hideTooltipTimer,tooltipMaxShowTime);
  }
}

/**
 * Hides the tooltip
 */
function hideTooltip()
{
  var tooltipDiv=dojo.byId("graphtooltip");
  tooltipDiv.style.display="none";
  tooltipShowing=false;
  tooltipShape=null;
  if (tooltipHideTimeout)
    clearTimeout(tooltipHideTimeout);       // clear any old hide timer
  tooltipHideTimeout=null;
}

/**
 * Hides the tooltip from timer callback
 */
function hideTooltipTimer()
{
  var tooltipDiv=dojo.byId("graphtooltip");
  tooltipDiv.style.display="none";
  tooltipShowing=false;
  tooltipShape=null;
  tooltipHideTimeout=null;
}

var textDebug=null;

/**
 * Deal with mouse move events in the drawing area.
 */
function handleMouseMove(/*DOMevent*/event)
{
//  if (tooltipShape)
//  {                                         // handle tooltip status
//    var shape=getShape(event);
//    if (!shape)
//      shape=getLineShape(event);
//    if (!shape)
//      shape=getWidgetShape(event);
//    if (!shape)
//    {                                       // no longer over anything interesting
//      if (!tooltipShowing)
//      {                                     // timeout still outstanding
//        clearTimeout(tooltipTimeout);       // clear it
//        tooltipTimeout=null;
//        tooltipShape=null;
//      }
//      else
//        hideTooltip();
//    }
//    else
//    {                                       // over something interesting
//      if (shape!=tooltipShape)
//      {                                     // not the same object as last event
//        if (!tooltipShowing)                // reset the tooltip
//        {
//          clearTimeout(tooltipTimeout);
//          tooltipTimeout=null;
//          tooltipShape=null;
//        }
//        else
//          hideTooltip();
//      }
//    }
//  }
  if (draggingCanvas)
  {                                         // handle canvas drags
    var delta={dx: 0, dy: 0};
    delta.dx=event.clientX-dragStart.x;
    delta.dy=event.clientY-dragStart.y;
    var area=dojo.byId("drawingareasurround");
    var top=dragStart.scrollTop-delta.dy;
    var left=dragStart.scrollLeft-delta.dx;
    if (top<0)
      top=0;
    else if (top>(surfaceHeight-dragStart.height+20))
      top=surfaceHeight-dragStart.height+20;
    if (left<0)
      left=0;
    else if (left>(surfaceWidth-dragStart.width+20))
      left=surfaceWidth-dragStart.width+20;
    area.scrollTop=top;
    area.scrollLeft=left;
  }
//  dojo.stopEvent(event);
}

/**
 * Deal with mouse down events in the drawing area.
 */
function handleMouseDown(/*DOMevent*/event)
{
  document.body.style.cursor="move";
  draggingCanvas=true;
  var area=dojo.byId("drawingareasurround");
  var contentSize=dojo.contentBox(area);
  dragStart={x: event.clientX, y: event.clientY, scrollTop: area.scrollTop, scrollLeft: area.scrollLeft, width: contentSize.width, height: contentSize.height};
  dojo.stopEvent(event);
}

/**
 * Deal with mouse up events in the drawing area.
 */
function handleMouseUp(/*DOMevent*/event)
{
  document.body.style.cursor="default";
  if (draggingCanvas)
  {
    draggingCanvas=false;
    dragStart=null;
  }
  dojo.stopEvent(event);
}

/**
 * Deal with mouse over events in the entity list.
 */
function handleListMouseOver(/*DOMevent*/event)
{
  var uid=event.target.getAttribute('hoverid');
  if (uid && uid.length>0)
  {
    highlightListEntity(uid);
    var entity=entityList[uid];
    var shape=entity.rect;
    // suppress tooltip reshow if mouse hasnt moved
    if (tooltipLastX!=event.clientX || tooltipLastY!=event.clientY)
      showTooltip(event,shape);
    tooltipLastX=event.clientX;
    tooltipLastY=event.clientY;
  }
  dojo.stopEvent(event);
}

/**
 * Deal with mouse out events in the entity list.
 */
function handleListMouseOut(/*DOMevent*/event)
{
  var uid=event.target.getAttribute('hoverid');
  if (uid && uid.length>0)
  {
    unHighlightListEntity(uid);
    // suppress tooltip hide when it's cornered by the screen and the mouse hasn't moved
    if (tooltipLastX!=event.clientX || tooltipLastY!=event.clientY)
      hideTooltip();
  }
  dojo.stopEvent(event);
}

/**
 * Deal with mouse down events in the viewport.
 */
function handleViewportMouseDown(/*DOMevent*/event)
{
  var clickOutside=false;
  var shape=viewportShape;
  if (shape)
  {
    var id=event.target.getAttribute('shapeid');
    if (id && id=="viewportRect")
      clickOutside=false;
    else
      clickOutside=true;
    // remember what we are dragging
    viewportCurrentShape=shape;
    var bbox = shape.getBoundingBox();
    var loc = dojox.gfx.matrix.multiplyPoint(shape.getTransform(), bbox.x, bbox.y);
    if (clickOutside)
    {
      // calculate important location information used during the drag
      if (dojo.isIE)
      {
        viewportLastPosition = {
          x: loc.x+5,
          y: loc.y+bbox.height/2
        };
      }
      else
      {
        viewportLastPosition = {
          x: loc.x+bbox.width/2,
          y: loc.y+bbox.height/2
        };
      }
      var dx = viewportLastPosition.x - loc.x;
      var dy = viewportLastPosition.y - loc.y;
      viewportCurrentShapeWindow = {
        x1: dx,
        y1: dy,
        x2: viewportSurfaceSize.width+dx-bbox.width,
        y2: viewportSurfaceSize.height+dy-bbox.height
      };
      handleViewportMouseMove(event);
    }
    else
    {
      // calculate important location information used during the drag
      viewportLastPosition = {
        x: event.clientX - viewportPosition.x,
        y: event.clientY - viewportPosition.y
      };
      var dx = viewportLastPosition.x - loc.x;
      var dy = viewportLastPosition.y - loc.y;
      viewportCurrentShapeWindow = {
        x1: dx,
        y1: dy,
        x2: viewportSurfaceSize.width+dx-bbox.width,
        y2: viewportSurfaceSize.height+dy-bbox.height
      };
    }
  }
  if (!clickOutside)
    dojo.stopEvent(event);
}

/**
 * Deal with mouse drag events in the viewport.
 */
function handleViewportMouseMove(/*DOMevent*/event)
{
  if (viewportCurrentShape)
  {
    var offset=0;
    if (dojo.isIE || dojo.isSafari)
      offset=-1;
    // calculate the normalised mouse move
    var x = Math.min(Math.max(event.clientX - viewportPosition.x, viewportCurrentShapeWindow.x1)+1, viewportCurrentShapeWindow.x2)-1;
    var y = Math.min(Math.max(event.clientY - viewportPosition.y, viewportCurrentShapeWindow.y1)+1+offset, viewportCurrentShapeWindow.y2)+offset;
    dojo.stopEvent(event);
    // apply the delta transform to the viewport
    viewportShape.applyTransform({dx: x - viewportLastPosition.x, dy: y - viewportLastPosition.y});
    // calculate the new absolute position of the scroll bars on the drawing area clip
    var transform=viewportShape.getTransform();
    if (!transform)
      transform={dx: 0, dy: 0};
//    var area=dojo.byId("drawingareasurround");
//    area.scrollTop=(transform.dy)*surfaceHeight/(viewportHeight-10);
//    area.scrollLeft=(transform.dx)*surfaceWidth/(viewportWidth-10);
    // remember where we last were
    viewportLastPosition = {x: x, y: y};
  }
}

/**
 * Deal with mouse up events in the viewport.
 */
function handleViewportMouseUp(/*DOMevent*/event)
{
  if (viewportCurrentShape)
  {
    var offset=0;
    if (dojo.isIE)
      offset=1;
    // work out the final delta movement based on the last position
    var bbox=viewportCurrentShape.getBoundingBox();
    var transform=viewportCurrentShape.getTransform();
    if (!transform)
      transform={dx: 0, dy: 0};
    var dx=viewportLastPosition.x-bbox.x-transform.dx-viewportCurrentShapeWindow.x1+offset;
    var dy=viewportLastPosition.y-bbox.y-transform.dy-viewportCurrentShapeWindow.y1;
    // move the real viewport box
    viewportCurrentShape.applyTransform({dx: dx, dy: dy});
    // update the scroll positions
    var transform=viewportShape.getTransform();
    var area=dojo.byId("drawingareasurround");
    area.scrollTop=transform.dy*surfaceHeight/(viewportHeight);
    area.scrollLeft=transform.dx*surfaceWidth/(viewportWidth);
  }
  viewportCurrentShape = null;
  dojo.stopEvent(event);
}

/**
 * Deal with mouse out events on the viewport.
 */
function handleViewportMouseOut(/*DOMevent*/event)
{
  if (viewportCurrentShape)
  {
    if (event.relatedTarget)
    {
      if (event.relatedTarget.id!="")
        handleViewportMouseUp(event);   // treat as end of drag
    }
  }
}

/**
 * Deal with window resize events
 */
function handleWindowResize(/*DOMevent*/event)
{
  // must reset scroll on drawing area
  var area=dojo.byId("drawingareasurround");
  if (isRTL)
  {
    area.scrollTop=0;
    if (dojo.isIE || dojo.isSafari)
      area.scrollLeft=surfaceWidth;
    else
      area.scrollLeft=0;
  }
  else
  {
    area.scrollTop=0;
    area.scrollLeft=0;
  }
  window.setTimeout(resizeDrawArea,0); // on timer so as not to holdup event
}

/**
 * Deal with scroll events from drawing area.
 */
function handleScroll(/*DOMevent*/event)
{
  if (!viewportCurrentShape)
  {                                         // only if we're not already dragging
    if (viewportShape)
    {
      // work out how far to move the viewport based on the current scroll positions
      var area=dojo.byId("drawingareasurround");
      var viewWidth=parseInt(area.style.width);
      var delta={dx: 0, dy: 0};
      var apparentScroll=area.scrollLeft;
      if (isRTL && (dojo.isSafari || dojo.isIE))
        apparentScroll=surfaceWidth-area.scrollLeft-viewWidth+scrollWidth;
      delta.dy=area.scrollTop*(viewportHeight)/surfaceHeight;
      delta.dx=apparentScroll*(viewportWidth)/surfaceWidth;
      var transform=viewportShape.getTransform();
      if (!transform)
        transform={dx: 0, dy: 0};
      delta.dy=delta.dy-transform.dy;
      if (isRTL && (dojo.isSafari || dojo.isIE))
        delta.dx=-delta.dx-transform.dx;
      else
        delta.dx=delta.dx-transform.dx;
      viewportShape.applyTransform(delta);
    }
  }
}

/**
 * Handler for clicks on the display mode radio buttons
 */
function handleDisplayModeChange(/*DOMevent*/event)
{
  hideMessages();
  var value=event.target.getAttribute('value');
  var checked=event.target.checked;
  if (!checked)
  {
    // some browsers send wrong event on keyboard selection (Firefox)
    // so we need to query all the buttons to find the right one
    var displayAll=dojo.byId("displayallobjects");
    var displayContents=dojo.byId("displaycontents");
    var displayExternal=dojo.byId("displayexternal");
    if (displayAll.checked)
      value=displayAll.value;
    else if (displayContents.checked)
      value=displayContents.value;
    else
      value=displayExternal.value;
  }
  var callbackFunction=function() {
    suppressHistoryAdd=true;
	  window.setTimeout(populateDisplay,0); // on timer so as not to holdup event
  }
  var deferred=graphService.setGraphSettingValue("displayMode",value);
  deferred.addCallback(callbackFunction);
  deferred.addErrback(processRPCException);
}

/**
 * Handler for clicks on the depth radio buttons
 */
function handleDepthChange(/*DOMevent*/event)
{
  hideMessages();
  var value=event.target.getAttribute('value');
  var checked=event.target.checked;
  if (!checked)
  {
    // some browsers send wrong event on keyboard selection (Firefox)
    // so we need to query all the buttons to find the right one
    var depthAuto=dojo.byId("depthauto");
    var depth1=dojo.byId("depth1");
    var depth2=dojo.byId("depth2");
    var depth3=dojo.byId("depth3");
    var depth4=dojo.byId("depth4");
    var depthUnlimited=dojo.byId("depthunlimited");
    if (depthAuto.checked)
      value=depthAuto.value;
    else if (depth1.checked)
      value=depth1.value;
    else if (depth2.checked)
      value=depth2.value;
    else if (depth3.checked)
      value=depth3.value;
    else if (depth4.checked)
      value=depth4.value;
    else if (depthUnlimited.checked)
      value=depthUnlimited.value;
  }
  var callbackFunction=function() {
    suppressHistoryAdd=true;
	  window.setTimeout(populateDisplay,0); // on timer so as not to holdup event
  }
  var deferred=graphService.setGraphSettingValue("depth",value);
  deferred.addCallback(callbackFunction);
  deferred.addErrback(processRPCException);
}

/**
 * Handler for clicks on the orientation radio buttons
 */
function handleOrientationChange(/*DOMevent*/event)
{
  hideMessages();
  var value=event.target.getAttribute('value');
  var checked=event.target.checked;
  if (!checked)
  {
    // some browsers send wrong event on keyboard selection (Firefox)
    // so we need to query all the buttons to find the right one
	  var orientLeftRight=dojo.byId("orientleftright");
  	var orientTopBottom=dojo.byId("orienttopbottom");
    if (orientLeftRight.checked)
      value=orientLeftRight.value;
    else
      value=orientTopBottom.value;
  }
  var callbackFunction=function() {
    suppressHistoryAdd=true;
	  window.setTimeout(populateDisplay,0); // on timer so as not to holdup event
  }
  var deferred=graphService.setGraphSettingValue("orientation",value);
  deferred.addCallback(callbackFunction);
  deferred.addErrback(processRPCException);
}

/**
 * Toggles the selection state of the list item in sync with graph.
 */
function toggleEntitySelection(/*string*/uid)
{
  if (timerActive==0)
  {                                         // only allow if timer is not showing
    var entity=entityList[uid];
    if (entity.isSelected)
    {
      entity.isSelected=false;
      var shape=entity.rect;
      shape.setFill([255,255,255,1]);
      setStroke(shape.centerLine,{color: normalBoxColour, width: 1, style: "Dot"});
      shape.nameText.setFill(normalBoxColour);
      shape.typeText.setFill(normalBoxColour);
      if (dojox.gfx.renderer=="vml")
        shape.rawNode.fill.type="solid";
      var listInner=dojo.byId(uid+"_inner");
      listInner.className="innerhighlightoff";
    }
    else
    {
      entity.isSelected=true;
      var shape=entity.rect;
      var rawShape=shape.getShape();
      var x1=rawShape.x;
      var y1=rawShape.y;
      var x2=rawShape.x;
      var y2=rawShape.y+rawShape.height;
      shape.setFill({type: "linear", x1: x1, y1: y1, x2: x2, y2: y2, colors: [{offset: 0, color: selectGradeTop}, {offset: 1, color: selectGradeBottom}]});
      setStroke(shape.centerLine,{color: [255,255,255,1], width: 1, style: "Dot"});
      shape.nameText.setFill([255,255,255,1]);
      shape.typeText.setFill([255,255,255,1]);
      var listInner=dojo.byId(uid+"_inner");
      listInner.className="innerhighlighton";
      centerObject(entity);
    }
    adjustActions(true);
  }
}

/**
 * Show the animation timer on screen.
 */
function showTimer()
{
  // centre the timer div on the screen
  if (timerActive==0)
  {
    timerActive=1;
    var timer=dojo.byId("timeranimation");
    var size=getWindowClientSize();
    var x=(size.width/2-80);
    if (x<0)
      x=0;
    var y=(size.height/2-40);
    if (y<0)
      y=0;
    timer.style.left=x+"px";
    timer.style.top=y+"px";
    // move it visible
    timer.style.display="block";
    // kick off the timer to do the animation
    window.setTimeout(animateTimer,timerDelay);
  }
}

/**
 * Hide the animation timer.
 */
function hideTimer()
{
  // turn off the timer and hide the div
  timerActive=0;
  var timer=dojo.byId("timeranimation");
  timer.style.display="none";
}

/**
 * Move to the next frame of the timer animation.
 */
function animateTimer()
{
  // Animation is achieved by moving the div left past the viewport by a fixed
  // increment of pixels equal to the animation frame width. The image is as wide
  // as the number of frames * frameWidth.
  var viewport=dojo.byId("timerviewport");
  var x=timerFrame*timerFrameWidth;
  if (isRTL)
    viewport.style.left=440-x+"px";
  else
    viewport.style.left="-"+x+"px";
  timerFrame=timerFrame+1;
  if (timerFrame>=maxTimerFrames)
    timerFrame=0;
  if (timerActive)
    window.setTimeout(animateTimer,timerDelay);
}

/**
 * Resize the drawing area to fill the remaining space. Also resizes the list scroll.
 */
function resizeDrawArea()
{
  var goAhead=true;
  var size=getWindowClientSize();
  if (dojo.isIE)
  {
    if (beforeLastClientSize.width!=0 && beforeLastClientSize.width==lastClientSize.width && beforeLastClientSize.height==lastClientSize.height)
      goAhead=false;
    if (size.width!=lastClientSize.width || size.height!=lastClientSize.height)
      goAhead=true;
  }
  if (goAhead)
  {
    // main drawing area fills all available free space in the window
    var area=dojo.byId("drawingareasurround");
    var column1=dojo.byId("column1");
    var column1Coords=dojo.coords(column1);
    var areaWidth=column1Coords.w-1;
    if (dojo.isSafari)
      areaWidth=areaWidth-1;
    var areaHeight=(size.height-containerPosition.y-10);
    if (dojo.isIE)
    {
      if (size.width<lastClientSize.width)
      {
        var diff=lastClientSize.width-size.width;
        areaWidth=areaWidth-diff-10;
      }
    }
    area.style.width=areaWidth+"px";
    area.style.height=areaHeight+"px";
    // now resize the canvas, if required
    resizeCanvas(areaWidth,areaHeight);

    var optionsList=dojo.byId("optionslistbody"); // use parent (visible) div for position
    var listPosition=dojo.coords(optionsList);
    var listHeight=size.height-listPosition.y-10;
    if (listHeight<150)
      listHeight=150;                         // enforce sensible minimum
    optionsList.style.height=listHeight+"px";

    var accordion=dijit.byId("optionslistbody");
//    var objectList=dojo.byId("listsurround");
//    var objectList2=dojo.byId("listsurround2");
//    var titleHeight=0;
//    var children=accordion.getChildren();
//    var len=children.length;
//    for (var i=0;i<len;i++)
//    {
//      titleHeight+=children[i].getTitleHeight();
//    } // end for
//    objectList.style.height=(listHeight-titleHeight-1)+"px";
//    objectList2.style.height=(listHeight-titleHeight-1)+"px";
//    objectList.style.width=(listPosition.w-2)+"px";
//    objectList2.style.width=(listPosition.w-2)+"px";

    accordion.resize({h: listHeight});                    // force re-size
    // must recalculate the viewport display
    redrawViewport();
    if (!dojo.isIE)
    {
      // double check width later
      window.setTimeout(resizeDrawAreaConfirm,0);
    }
  }
  beforeLastClientSize.width=lastClientSize.width;
  beforeLastClientSize.height=lastClientSize.height;
  lastClientSize.width=size.width;
  lastClientSize.height=size.height;
}


/**
 * Adjusts the final positions if necessary.
 */
function resizeDrawAreaConfirm()
{
  var area=dojo.byId("drawingareasurround");
  var column1=dojo.byId("column1");
  var column1Coords=dojo.coords(column1);
  var areaWidth=column1Coords.w-1;
  if (dojo.isSafari)
    areaWidth=areaWidth-1;
  area.style.width=areaWidth+"px";
}

/**
 * Redraws the viewport shape at the appropriate scale.
 */
function redrawViewport()
{
  viewportSurface.remove(viewportShape);
  drawViewport();
}

/**
 * Draws the viewport shape at the appropriate scale.
 */
function drawViewport()
{
  // work out the proportionate size of the inner rectangle based on the size of the
  // drawing area viewport relative to its actual drawing surface size.
  var size=getWindowClientSize();
  var column1=dojo.byId("drawingareasurround");
  var column1Coords=dojo.coords(column1);
  var areaWidth=column1Coords.w-scrollWidth;
  var areaHeight=(size.height-containerPosition.y-10-scrollWidth);
  var width=areaWidth*viewportWidth/surfaceWidth;
  var height=areaHeight*viewportHeight/surfaceHeight;
  if (width>viewportWidth)
    width=viewportWidth;
  if (height>viewportHeight)
    height=viewportHeight;
  width-=1;
  height-=1;
  var x=0;
  var y=0;
  if (dojo.isMoz)
    y=1;
  if (dojo.isIE && dojox.gfx.renderer!="silverlight")
  {
    width-=1;
    height-=1;
    x=0;
    y=0;
  }
  if (isRTL)
    x=viewportWidth-x-width;
  viewportShape=viewportSurface.createRect({x: x, y: y, width: width, height: height});
  setStroke(viewportShape,{color: [0,0,0,1], width: 1});
  viewportShape.setFill([192,192,192,0.2]);
  if (dojox.gfx.renderer!="silverlight")
    viewportShape.getEventSource().setAttribute('shapeid',"viewportRect");
}

/**
 * Special function to set the stroke parms on a shape.
 */
function setStroke(/*shape*/shape, /*object*/attributes)
{
  var alias=false;
  shape.setStroke(attributes);
  if (attributes.alias)
    alias=attributes.alias;
  var style="Solid";
  if (attributes.style)
    style=attributes.style;
  if (dojox.gfx.renderer=="svg") // special for SVG
  {
    // Added new feature for SVG to turn off anti-alias to make lines crisp
    if (!alias)
      shape.rawNode.setAttribute("shape-rendering", "crispEdges");  // set to non-anti-alias
    // Fix bug in SVG where dashed lines wider than 1px get progressively less dashed
		var width=shape.rawNode.getAttribute("stroke-width");
		var cap=shape.rawNode.getAttribute("stroke-linecap");
    var da=style.toLowerCase();
		if (da in localDasharray)
      da=localDasharray[da];
    var daOut=[];
		if (da instanceof Array)
    {
			for (var i = 0; i < da.length; ++i)
      {
				daOut[i]=da[i]*width;
			} // end for
			if (cap != "butt")
      {
				for (var i = 0; i < daOut.length; i += 2)
        {
					daOut[i] -= width;
					if (daOut[i] < 1)
            daOut[i]=1;
				} // end for
				for (var i = 1; i < daOut.length; i += 2)
        {
					daOut[i] += width;
				}
			}
			daOut=daOut.join(",");
		}
		shape.rawNode.setAttribute("stroke-dasharray", daOut);
  }
}

/**
 * Calculate the size of a piece of rendered text.
 */
function measureText(/*string*/text,/*boolean*/bold)
{
  var measurer=dojo.byId("textmeasure");
  if (bold)
    measurer.style.fontWeight="bold";
  else
    measurer.style.fontWeight="normal";
  var dimensions=measureFragment(measurer,text);
  return(dimensions);
}

function measureFragment(/* HTMLElement */node, /* string */html, /* string? */boxType)
{
	//	summary
	//	get the dimensions of passed node if it were populated with passed html.
	var clone = node.cloneNode(true);
	clone.innerHTML = html;
	node.parentNode.appendChild(clone);
	var ret = dojo.contentBox(clone, boxType);
	node.parentNode.removeChild(clone);
	clone=null;
  ret.width=ret.w;
  ret.height=ret.h;
	return ret; // object
}

/**
 * Calculate the best fit text within the given width
 */
function bestFitText(/*string*/text,/*integer*/width,/*boolean*/bold)
{
  var bestFit="";
  var rawSize=0;
  if (text)
  {
  	bestFit=text;
	  var len=text.length;
	  rawSize=measureText(text,bold);
	  if (rawSize.width>width)
	  {                                         // doesn't fit - need to chomp the text
	    var newLen=Math.round(len*width/rawSize.width); // do a simple linear chop
	    newLen=newLen-2;                        // add space for ellipsis
	    bestFit=text.substring(0,newLen)+"...";
	    rawSize=measureText(bestFit,bold);
	    if (rawSize.width>width)
	    {                                       // still bigger
	      while (rawSize.width>width)
	      {                                     // chomp more
	        newLen=newLen-1;
	        bestFit=text.substring(0,newLen)+"...";
	        rawSize=measureText(bestFit,bold);
	      } // end while
	    }
	    else if (rawSize.width<width)
	    {                                       // see if we chomped too much
	      var newestFit=bestFit;
	      while (rawSize.width<width)
	      {                                     // add more
	        bestFit=newestFit;                  // previous fit was still good
	        newLen=newLen+1;
	        newestFit=text.substring(0,newLen)+"...";
	        rawSize=measureText(newestFit,bold);
	      } // end while
	    }
	  }
  }
  return({text: bestFit, size: rawSize});
}

/**
 * Work out the dimensions of the browser frame client area for the given frame ID.
 */
function getWindowFrameClientSize(/*integer*/frameId)
{
  // must check each of 3 different ways that browsers use to declare client dimensions
  // special case for frames - use the frameId to be completely sure we've got the 
  // correct numbers.
  var windowHeight=0;
  var windowWidth=0;
  if (typeof(parent.window.frames[frameId].innerHeight)=='number')
    windowHeight=parent.window.frames[frameId].innerHeight;
  else
  {
    if (top.frames[frameId].document.documentElement && top.frames[frameId].document.documentElement.clientHeight)
      windowHeight=top.frames[frameId].document.documentElement.clientHeight;
    else
    {
      if (top.frames[frameId].document.body && top.frames[frameId].document.body.clientHeight)
        windowHeight=top.frames[frameId].document.body.clientHeight;
    }
  }
  if (typeof(parent.window.frames[frameId].innerWidth)=='number')
    windowWidth=parent.window.frames[frameId].innerWidth;
  else
  {
    if (top.frames[frameId].document.documentElement && top.frames[frameId].document.documentElement.clientWidth)
      windowWidth=top.frames[frameId].document.documentElement.clientWidth;
    else
    {
      if (top.frames[frameId].document.body && top.frames[frameId].document.body.clientWidth)
        windowWidth=top.frames[frameId].document.body.clientWidth;
    }
  }
  windowSize={width: windowWidth, height: windowHeight};
  return(windowSize);
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

/**
 * Removes all children from the given surface. Special case on SVG is
 * to not remove the first child since this is the <refs> element.
 */
function clearSurface(/*surface*/surf)
{
	if (dojo.isIE || dojox.gfx.renderer=="silverlight")
	{
		// IE uses VML so just call regular method
		surf.clear();
	}
	else
	{
	  // all other common browsers use SVG, so use this modded version
	  var raw=surf.rawNode;
	  while(raw.lastChild && raw.firstChild != raw.lastChild)
	  {
	  	raw.removeChild(raw.lastChild);
	  } // end while
	}
}

/**
 * Registers extensions to the language.
 */
function registerExtensions()
{
  if (!Array.prototype.indexOf)
  {
    Array.prototype.indexOf=function(obj)
    {
      var ret=-1;
      var len=this.length;
      for (var i=0;i<len;i++)
      {
        if (this[i]===obj)
        {
          ret=i;
          break;
        }
      } // end for
      return(ret);
    };
  }
}

function getScrollBar()
{
	//	summary
	//	returns the width of a scrollbar.
	
	//	set up the test nodes.
	var scroll = document.createElement("div");
	scroll.style.width="100px";
	scroll.style.height="100px";
	scroll.style.overflow="scroll";
	scroll.style.position="absolute";
	scroll.style.top="-300px";
	scroll.style.left="0px"
	
	var test = document.createElement("div");
	test.style.width="400px";
	test.style.height="400px";
	scroll.appendChild(test);
	document.body.appendChild(scroll);

	var width=scroll.offsetWidth - scroll.clientWidth;

	document.body.removeChild(scroll);
	scroll.removeChild(test);
	scroll=test=null;

	//	we return an object because we may add additional info in the future.
	return { width: width };	//	object
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

/**
 * Initialisation function. Called after page load.
 */
function initGfx()
{
	// this line must be inside a method called from onload().
	dojo.require("dojox.gfx");
	
  registerExtensions();

  // calculate scrollbar size
  var scrollBar=getScrollBar();
  scrollWidth=scrollBar.width;

  // build the main drawing area graphics surface
  container=dojo.byId("drawingarea");
  containerPosition=dojo.coords(container);
  surface=dojox.gfx.createSurface(container, surfaceWidth, surfaceHeight);
  surfaceSize=surface.getDimensions();
  surfaceSize.width=parseInt(surfaceSize.width);
  surfaceSize.height=parseInt(surfaceSize.height);

  // build the viewport graphics surface
  viewport=dojo.byId("drawingarea2");
  viewportPosition=dojo.coords(viewport);
  viewportSurface=dojox.gfx.createSurface(viewport, viewportWidth, viewportHeight);
  viewportSurfaceSize=viewportSurface.getDimensions();
  viewportSurfaceSize.width=parseInt(viewportSurfaceSize.width);
  viewportSurfaceSize.height=parseInt(viewportSurfaceSize.height);
  drawViewport();
  resizeDrawArea();

  // radio button groups
  var displayAll=dojo.byId("displayallobjects");
  var displayContents=dojo.byId("displaycontents");
  var displayExternal=dojo.byId("displayexternal");
  var depthAuto=dojo.byId("depthauto");
  var depth1=dojo.byId("depth1");
  var depth2=dojo.byId("depth2");
  var depth3=dojo.byId("depth3");
  var depth4=dojo.byId("depth4");
  var depthUnlimited=dojo.byId("depthunlimited");
  var orientLeftRight=dojo.byId("orientleftright");
  var orientTopBottom=dojo.byId("orienttopbottom");

  // hook up all the event handlers we need
  var containerSurround=dojo.byId("drawingareasurround");
  var viewportSurround=dojo.byId("viewportbody");
  dojo.connect(containerSurround,'onscroll','handleScroll');
//  dojo.connect(container,'onmouseover','handleMouseOver');
//  dojo.connect(container,'onmouseout','handleMouseOut');
  dojo.connect(container,'onmousedown','handleMouseDown');
  dojo.connect(container,'onmousemove','handleMouseMove');
  dojo.connect(container,'onmouseup','handleMouseUp');
  dojo.connect(viewport,'onmousedown','handleViewportMouseDown');
  dojo.connect(viewport,'onmousemove','handleViewportMouseMove');
  dojo.connect(viewport,'onmouseup','handleViewportMouseUp');
  dojo.connect(viewportSurround,'onmouseout','handleViewportMouseOut');
  if (displayAll)
    dojo.connect(displayAll,'onclick','handleDisplayModeChange');
  if (displayContents)
   	dojo.connect(displayContents,'onclick','handleDisplayModeChange');
 	if (displayExternal)
    dojo.connect(displayExternal,'onclick','handleDisplayModeChange');
 	if (depthAuto)
   	dojo.connect(depthAuto,'onclick','handleDepthChange');
 	if (depth1)
    dojo.connect(depth1,'onclick','handleDepthChange');
 	if (depth2)
   	dojo.connect(depth2,'onclick','handleDepthChange');
 	if (depth3)
    dojo.connect(depth3,'onclick','handleDepthChange');
 	if (depth4)
   	dojo.connect(depth4,'onclick','handleDepthChange');
 	if (depthUnlimited)
    dojo.connect(depthUnlimited,'onclick','handleDepthChange');
  dojo.connect(orientLeftRight,'onclick','handleOrientationChange');
  dojo.connect(orientTopBottom,'onclick','handleOrientationChange');
  if (typeof window != "undefined")
    dojo.connect(window, 'onresize', 'handleWindowResize');  // window resize

  // capture action list HTML
  harvestActionHTML();

  // bare page is laid out, go do the real work
  window.setTimeout(populateDisplay,0);     // separate thread
}

dojo.addOnLoad(initGfx);

