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

var globalEllipsisRC = null;
var firstTime = true;
var globalFacets = null;
var globalSizeOfPageWidth = 0;
var globalSizeOfTDWidth = 0;
var processing = false;
var timer = null;
var timerRunning = false;
var forClasses = false;
var classesExtra = 0;
var globalSetIndex = 0;
var previousWidth = 0;
var lastWidth = 0;
var sizeOfHiddenArea = 0;
var sizeOf2EM = 0;

/* Define the EllipsisRC JavaScript object.  Instances of this object are
 * created when a collection view is rendered.
 */
 
var EllipsisRC = function(name, owlUri, level){

	this.name = name;
	this.uri = owlUri;
	this.level = level;
}

/* Define the EllipsisRC JavaScript object.  Instances of this object are
 * created when a collection view is rendered.
 */
var EllipsisRCSet = function(name, uri, systemName, systemUri, size, depth){

	this.name = name;
	this.uri = uri;
	
	if (systemName == "")
		this.systemName = systemUri;
	else
		this.systemName = systemName;
		
	this.systemUri = systemUri;
	this.items = size;
	this.level = depth;
	this.index = globalSetIndex++;
	this.set = true;
}

/**
 * Calculate the size of a piece of rendered text.
 */
function measureM(/*string*/id)
{
  var measurer=dojo.byId(id);
  measurer.style.fontWeight="normal";
  var dimensions=measureFragment(measurer,"m");
  return(dimensions);
}

function measureText(/*string*/id, /*string*/text)
{
  var measurer=dojo.byId(id);
  measurer.style.fontWeight="normal";
  var dimensions=measureFragment(measurer,text);
  return(dimensions);
}

function measureTextT(/*string*/text, bold)
{	
	var measurer=dojo.byId("textmeasure");
	if (bold)
	 measurer.style.fontWeight="bold";
	else
	    measurer.style.fontWeight="normal";

	var textMeasureTR = dojo.byId("textMeasureTR");

	var dimensions = null;
	
	if (textMeasureTR) {
		textMeasureTR.style.display = "block";	
		textMeasureTR.style.visibility = "";	
		dimensions=measureFragment(measurer,text);		
		textMeasureTR.style.display = "none";
		textMeasureTR.style.visibility = "hidden";	
	} else      
		dimensions=measureFragment(measurer,text);

	//debugIt(text + " has size " + dimensions.width + " x " + dimensions.height + " bold: " + bold);
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

function measureNode(/* HTMLElement */node, /* string? */boxType)
{
	//	summary
	//	get the dimensions of passed node as it is
	var clone = node.cloneNode(true);
	node.parentNode.appendChild(clone);
	var ret = dojo.contentBox(clone, boxType);
	node.parentNode.removeChild(clone);
	clone=null;
    ret.width=ret.w;
    ret.height=ret.h;
	return ret; // object
}

function measureNodeNoClone(/* HTMLElement */node, /* string? */boxType)
{
	//	summary
	//	get the dimensions of passed node as it is
	var ret = dojo.contentBox(node, boxType);
    ret.width=ret.w;
    ret.height=ret.h;
	return ret; // object
}

var biggestButton = 0;

function calculateClassificationCollectionWidth()  {

	if (dojo.isIE) {		
		if (lastWidth != document.body.clientWidth)
		{
			lastWidth = document.body.clientWidth;
	
			if (timerRunning)
				clearTimeout(timer);
				
			timer = setTimeout("calculateClassificationCollectionWidthInt()",500);		
			timerRunning = true;
		}
	} else {
		calculateClassificationCollectionWidthInt();
	}
}

function calculateClassificationCollectionWidthInt() 
{
	timerRunning = false;
	clearTimeout(timer);

	newSizeOfScrollArea = new Object();
	var measureTable = dojo.byId("measureTable");
	
	if (measureTable) {
		measureTable.style.display = "block";	
		measureTable.style.visibility = "";	
		newSizeOfScrollArea.width = dojo.coords(dojo.byId("mainButtons")).w;
		measureTable.style.display = "none";
		measureTable.style.visibility = "hidden";	
	} else {
		newSizeOfScrollArea.width = dojo.coords(dojo.byId("mainButtons")).w;
	}
	
	sizeOf2EM = measureTextT("mm", false);
	
	if (newSizeOfScrollArea.width != previousWidth) {
		previousWidth =	newSizeOfScrollArea.width;
		// deduct 20 px for the expand/collapse graphic and also deduct
		// 2ems for the padding in the table
		globalSizeOfPageWidth = newSizeOfScrollArea.width - 20 - sizeOf2EM.width;
		
		//debugIt("globalSizeOfPageWidth: " + globalSizeOfPageWidth + " sizeOf2EM: " + sizeOf2EM);
		displayClassificationCollection(globalEllipsisRC);			
	}
}

function displayClassificationCollection(ellipsisRC) {

		if (ellipsisRC && ellipsisRC.length > 0) {

				for (var i = 0; i < ellipsisRC.length; i++) {
	
					var i1 = i + 1;
					// Retrieve the div for the element
					var id = "item" + i1;
					var itemDiv = document.getElementById(id);	
				
					var realTD = globalSizeOfPageWidth - (ellipsisRC[i].level * 10);
					
//					if (forClasses)
	//					realTD = realTD + 10;
						
					if (realTD < 120)
						realTD = 120;
					
					//Set these into a global var so we don't have to submit on each resize
					globalEllipsisRC = ellipsisRC;					
											
					if (ellipsisRC[i].name == ellipsisRC[i].uri)
						var shortText = bestFitTextFront(ellipsisRC[i].name,realTD);
					else
						var shortText = bestFitText(ellipsisRC[i].name,realTD);
					
					if (shortText == ellipsisRC[i].name)
						itemDiv.innerHTML = shortText;
					else
						itemDiv.innerHTML = "<span title=\"" + filterHTML(ellipsisRC[i].name) + "\">" + shortText+ "</span>";
				}				
		}		
		
		processing = false;
}

function calculateClassificationCollectionWidthForDetail()  {

	if (dojo.isIE) {	
		if (lastWidth != document.body.clientWidth)
		{
			lastWidth = document.body.clientWidth;
		
			if (timerRunning)
				clearTimeout(timer);
				
			timer = setTimeout("calculateClassificationCollectionWidthForDetailInt()",500);		
			timerRunning = true;
		}
	} else {
		calculateClassificationCollectionWidthForDetailInt();
	}
}

function calculateClassificationCollectionWidthForDetailInt() 
{
	timerRunning = false;
	clearTimeout(timer);

	sizeOfHiddenArea = new Object();
	sizeOfHiddenArea.width = dojo.coords(dojo.byId("hiddenArea")).w;
	
	if (sizeOfHiddenArea.width != previousWidth) {
		previousWidth =	sizeOfHiddenArea.width;
		globalSizeOfPageWidth = sizeOfHiddenArea.width;
		
		//debugIt("globalSizeOfPageWidth: " + globalSizeOfPageWidth);
		displayClassificationCollectionForDetail(globalEllipsisRC);			
	}
}

function displayClassificationCollectionForDetail(ellipsisRC) {

		var again = 0;
		var firstSet = -1;
		
				
		if (ellipsisRC != null && ellipsisRC.length > 0) {
								
				for (var i = 0; i < ellipsisRC.length; i++) {
	
					var i1 = i + 1;
					// Retrieve the div for the element
					var id = "item" + i1;
					var itemDiv = document.getElementById(id);

					var realTD = parseInt(globalSizeOfPageWidth * 0.35)
					var realTD2 = parseInt(globalSizeOfPageWidth * 0.3)
					
					//Set table widths :-) then it all flows nicely,
					//even in IE 6 !!!
					cl = document.getElementById('classificationList');
					ot = document.getElementById('outerTable');
          pt = document.getElementById('pagingtable');
					cl.style.width=(realTD2 + 16) + "px";
					ot.style.width=(realTD + 16) + "px";
          if (pt)
            pt.style.width=(realTD + 16) + "px";

					if (ellipsisRC[i].set && firstSet == -1)
						firstSet = i;
						
					if (ellipsisRC[i].name == ellipsisRC[i].uri) {
						if (ellipsisRC[i].set == true)
							var shortText = bestFitTextFront(ellipsisRC[i].name,realTD2);
						else
							var shortText = bestFitTextFront(ellipsisRC[i].name,realTD);
					} else {
						if (ellipsisRC[i].set == true)
							var shortText = bestFitText(ellipsisRC[i].name,realTD2);
						else
							var shortText = bestFitText(ellipsisRC[i].name,realTD);
					}
					
					if (ellipsisRC[i].set == true) {
						itemDiv.innerHTML =  shortText;
					} else {
						if (shortText == ellipsisRC[i].name)
							itemDiv.innerHTML = shortText ;
						else
							itemDiv.innerHTML = "<span title=\"" + filterHTML(ellipsisRC[i].name) + "\">" + shortText+ "</span>";
					}
				}
				
			//Shorten the hidden bits
			if (firstSet != -1) {
				var index = 1;
				var maxTD = parseInt(sizeOfHiddenArea.width * 0.9 / 3);
				
				for (var i = firstSet; i < ellipsisRC.length; i++) {

					//Is the uri too long?									
					var sizeUri = measureTextT(ellipsisRC[i].uri,false);
					if (sizeUri.width > maxTD) {
						realTD = (sizeOfHiddenArea.width * 0.9 - sizeUri.width) / 2;
						if (realTD < 60)
							realTD = 60;
					} else
						realTD = maxTD;
					
					//fill in the name
					// Retrieve the div for the name
					var id = "nameItem" + index;
					var itemDiv = document.getElementById(id);

					//fill in the name
					if (ellipsisRC[i].name == ellipsisRC[i].uri)
						var shortText = bestFitTextFront(ellipsisRC[i].name,realTD);
					else
						var shortText = bestFitText(ellipsisRC[i].name,realTD);
												
					if (shortText == ellipsisRC[i].name)
						itemDiv.innerHTML = "<span>" + shortText + "</span>";
					else
						itemDiv.innerHTML = "<span title=\"" + filterHTML(ellipsisRC[i].name) + "\">" + shortText+ "</span>";
											
					//fill in the system name
					// Retrieve the div for the system name
					var id = "systemNameItem" + index;
					var itemDiv = document.getElementById(id);

					//fill in the name
					if (ellipsisRC[i].systemName == ellipsisRC[i].systemUri)
						var shortText = bestFitTextFront(ellipsisRC[i].systemName,realTD);
					else
						var shortText = bestFitText(ellipsisRC[i].systemName,realTD);
												
					if (shortText == ellipsisRC[i].systemName)
						itemDiv.innerHTML = "<span>" + shortText + "</span>";
					else
						itemDiv.innerHTML = "<span title=\"" + filterHTML(ellipsisRC[i].systemName) + "\">" + shortText+ "</span>";
						
					index++;
				}
			}
		}		
}

function getHomePageFacetSize() {
	sizeOfPage = getWindowClientSize();
	
	// 35% minus 45 for borders etc
	return (parseInt(sizeOfPage.width * 0.35) - 45);
}

function getHomePageFacetSize() {

	sizeOfPage = measureNodeNoClone(dojo.byId("facetMeasure"));	
	size = sizeOfPage.width;
	
	if (dojo.isIE) {
		sizeOf2EM = measureTextT("mm", false);	
		size = size - sizeOf2EM.width;
	}
	
	return size;
}

function getSearchBoxFacetSize() {

	sizeOfPage = measureNodeNoClone(dojo.byId("facetMeasure"));	
	size = sizeOfPage.width;

	if (dojo.isIE) {	
		sizeOf2EM = measureTextT("m", false);		
		size = size - sizeOf2EM.width;
	}
	
	return size;
}

function getFacetSize(titleSize, count) {
	
	var sizeOf = measureTextT("( " + count +" )", false);
	//alert("sizeOfPage.width: " + sizeOfPage.width + " sizeOfEM: " + sizeOf2EM.width);
	size = titleSize - sizeOf.width;

	return size;
}

function getTitleFacetSize() {
	
	sizeOfPage = measureNodeNoClone(dojo.byId("facetMeasure"));	
	size = sizeOfPage.width;	

	if (dojo.isIE) {	
		sizeOf2EM = measureTextT("mmmm", false);		
	} else {
		sizeOf2EM = measureTextT("mm", false);		
	}
	
	size = size - sizeOf2EM.width;
	
	return size;
}

function shortenFacet(facet, size) {
	if (facet.name == facet.uri) 
		return (bestFitTextFront(facet.name,size));
	else
		return (bestFitText(facet.name,size));
}

function shortenTitle(title, uri, size) {
	if (title == uri) 
		return (bestFitTextFront(title,size, true));
	else
		return title;
}

/** replace all item${n} divs wiht a short name */
function displayHomePageFacetsInt() {

	var size = getHomePageFacetSize();
	var titleSize = getTitleFacetSize();
	
	size = parseInt(size / 3);
	titleSize = parseInt(titleSize / 3);
	
	var facets = globalFacets;
	
	var lastTitle = null;
	
	if (facets != null)
		for (var i = 0; i < facets.length; i++) {
			var i1 = i + 1;		
			var id = "item" + i1;
			
			var shortName = shortenFacet(facets[i],size);
			
			if (facets[i].title != lastTitle) {
				lastTitle = facets[i].title;
				
				var shortTitle = shortenTitle(facets[i].title,facets[i].titleUri,titleSize,true);
				var itemDiv = document.getElementById(id + "t");
				
				if (shortTitle == facets[i].title)
					itemDiv.innerHTML = shortTitle;
				else
					itemDiv.innerHTML = "<span title=\"" + filterHTML(facets[i].title) + "\">" + shortTitle + "</span>";			
			}
			
			var itemDiv = document.getElementById(id);
					
			if (shortName == facets[i].name)
				itemDiv.innerHTML = shortName;
			else
				itemDiv.innerHTML = "<span title=\"" + filterHTML(facets[i].name) + "\">" + shortName + "</span>";
		}

}

/** Set timer for IE, for others just go straight to the Int version */
function displayHomePageFacets()  {

	if (dojo.isIE) {		
		if (lastWidth != document.body.clientWidth)
		{	
			if (timerRunning)
				clearTimeout(timer);
			
			timer = setTimeout("displayHomePageFacetsInt()",500);		
			timerRunning = true;
		}
	} else {
		displayHomePageFacetsInt();
	}
}

/** recreate the html for the facets with potentially short names */
function displayResizeFacetsInt() {

	timerRunning = false;
	clearTimeout(timer);

	var size = getTitleFacetSize();
	if (previousWidth != size) {
		previousWidth = size;

	var facetProviders = globalFacetProviders;
	
	if (facetProviders != null)
			for (var i = 0; i < facetProviders.length; i++) {
			
				var facets = globalFacets[facetProviders[i]];
		
				displayFacets(facetProviders[i], facets, true, 0);
			}
	}
}

/** Set timer for IE, for others just go straight to resizeInt */
function displayResizeFacets()  {

	if (dojo.isIE) {		
		if (timerRunning)
			clearTimeout(timer);
			
		timer = setTimeout("displayResizeFacetsInt()",500);		
		timerRunning = true;
	} else {
		displayResizeFacetsInt();
	}
}


function findLower(/* array */ a, /*integer*/ num) {
	remember = 0;
	for (index in a) {	
		var iindex = parseInt(index);	
		if (iindex < num && iindex > remember)
			remember = iindex;
	}
	
	return remember;
}

function findHigher(/* array */ a, /*integer*/ num) {
	remember = 0;
	for (index in a) {
		var iindex = parseInt(index);	
			if (iindex  > num && (iindex  < remember || remember == 0))
			remember = iindex ;
	}
	
	return remember;
}

function bestFitTextInt(/*string*/text,/*integer*/width, bold,  atFront)
{
  var bestFit="";

  if (text)
  {
      bestFit=text;
	  var len=text.length;
	  var rawSize=measureTextT(text, bold);	  	  
 	 //debugIt("rawSize: " + rawSize.width + " of " + text + " in bold?=" + bold + " to fit into: " + width);
 	  if (rawSize.width > width) {
 	  
 	  		//start = new Date().getTime();
 	  
	 	  var oldWidth = width;
		  var charsToSize = new Array();	  
		  var closest = 0;
		  charsToSize[len]=rawSize.width;
		 //debugIt("1) charsToSize[" + len + "] = " + rawSize.width);
		  var firstTime = true;
		  var count = 0;
 	   	  		
		  while (rawSize.width != width && closest == 0 && count < 10) {		  
		    count++;
		    if (firstTime) {
		    	firstTime = false;
			    //debugIt("charsToSize.length == 1");
		    	//Try a linear chop first
			    //Must fit into the space needed minus the ellipsis. Make them bold for a margin of error
			    var rawSizeEllipsis=measureTextT("...", bold);
			    width=width - rawSizeEllipsis.width;
			   //debugIt("rawSizeEllipsis: " + rawSizeEllipsis.width + " new width: " + width);
			    var newLen=Math.round(len*width/rawSize.width); // do a simple linear chop	    	
			    if (atFront)
					bestFit=text.substring(len-newLen);
				else
					bestFit=text.substring(0,newLen);
				rawSize=measureTextT(bestFit,bold);		
			    charsToSize[newLen] = rawSize.width;
			    
			   //debugIt("2) charsToSize[" + newLen + "] = " + rawSize.width);
		    } else {
			   //debugIt("rs.width: " + rawSize.width + " w: " + width);
	  			if (rawSize.width < width) {
	  				index = findHigher(charsToSize,newLen);
	  			//debugIt("findHigher(" + newLen + "): " + index);
	  				if (index > newLen) {
	  					//Found the first index bigger than newLen
		    			difference = Math.round((width - rawSize.width) / ((charsToSize[index] - charsToSize[newLen]) / (index - newLen)));
		    		//debugIt("3) diff = " + difference + " charsToSize[" + index + "] = " + charsToSize[index] + " charsToSize[" + newLen + "] = " + charsToSize[newLen]);		
		    									
		    			if (difference != 0) {		    				
		    				newLen=newLen+difference;
		    				if (atFront)
								bestFit=text.substring(len-newLen);
							else		    				
			    				bestFit=text.substring(0,newLen);
	    					rawSize=measureTextT(bestFit,bold);
		    				charsToSize[newLen] = rawSize.width;	
		    				//debugIt("3) charsToSize[" + newLen + "] = " + rawSize.width);		
		    			} else
		    				closest = newLen;
	  				} else {
	  					//This should never happen as we have the total length and size as the biggest element in the array
	  					closest = newLen;
	  				}
	  			} else if (rawSize.width == width) {
	  				closest = newLen;
	  			} else {
		  			index = findLower(charsToSize,newLen);
				//debugIt("findLower(" + newLen + "): " + index);
	  				if (index != 0 && index < newLen) {
	  					//Found the first index smaller than newLen
	  					//If the index is only one below then we must just use this index as it's the longest
	  					//length that fits in the space
	  					if (index == newLen - 1) {
	  					//debugIt("index == newLen - 1 so setting closest to index");
	  						closest = index;
	  					} else {
			    			difference = Math.round((rawSize.width - width) / ((charsToSize[newLen] - charsToSize[index]) / (newLen - index)));
			    		//debugIt("3.5) diff = " + difference + " charsToSize[" + index + "] = " + charsToSize[index] + " charsToSize[" + newLen + "] = " + charsToSize[newLen]);				    									
			    			if (difference == 0)
			    				difference = 1;
			    			else {		    		
			    				difference = Math.round(difference);
			    			}
			    			
		    				newLen=newLen-difference;
		    				if (atFront)
								bestFit=text.substring(len-newLen);
							else		    				
				    			bestFit=text.substring(0,newLen);
	   						rawSize=measureTextT(bestFit,bold);
	    					charsToSize[newLen] = rawSize.width;				    				
		    				//debugIt("4) charsToSize[" + newLen + "] = " + rawSize.width);	    				
	    				}
	  				} else {
	  					//Do a linear chop of twice the size
					    var newLen=Math.round(newLen * width/(rawSize.width*2));
					    newLen--;
					   //debugIt("index: " + index + " newLen: "+ newLen);
	 	  				if (atFront)
							bestFit=text.substring(len-newLen);
						else		    				
							bestFit=text.substring(0,newLen);
						rawSize=measureTextT(bestFit,bold);		
					    charsToSize[newLen] = rawSize.width;
					   //debugIt("5) charsToSize[" + newLen + "] = " + rawSize.width);
	  				}	  			
	  			} // if (rawSize.width < width) 
		  } // if (charsToSize.length == 1)
		} // while (rawSize.width != width && closest == 0)
				
		if (rawSize.width == width)
			closest = newLen;
		else if (closest == 0) {
			//Something has gone badly wrong, so to guard against re-entering the function
			//if we return the biggest match that is below the size
			for (index in charsToSize) {		
				var iindex = parseInt(index);
				if (charsToSize[index] < width && iindex > closest)
					closest = iindex;
			}
			
			//If we still didn't find one then do not truncate	
			if (closest == 0)	
				closest = len;
		}
			
		//Set bestFit
		if (closest != len) {
			if (atFront)
				bestFit = "..." + text.substring(len-closest);
			else
				bestFit = text.substring(0,closest) + "...";	
		} else
			bestFit = text;
		
		/* Debug code */
/*		var f = "";		
		for(i in charsToSize)
			f +=  "[" + i + "] = " + charsToSize[i] + ", ";
		rawSize=measureTextT(bestFit,bold);		
	debugIt("C[" + closest + "] = " + rawSize.width + "  " + f + count + ", " + width + "," + oldWidth + " bestFit: " + bestFit);*/
//			end = new Date().getTime();
	//	debugIt("bestFit took " + (end - start));
		/* End debug code */
		
	  }	  //if (rawSize.width > width)
  } // if(text)
  
  return(filterHTML(bestFit));
}

function bestFitText(/*string*/text,/*integer*/width, bold) {
	return bestFitTextInt(text,width, bold,  false);
}

function bestFitTextFront(/*string*/text,/*integer*/width, bold) {
	return bestFitTextInt(text,width, bold,  true);
}

/** the view of the classes inside a system calls this as we need to do a couple of things differently */
function setForClasses() {
	forClasses = true;
	classesExtra = 20;
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

/**
 * Calculate the size of each attribute for display in the policy
 * attachment authoring.
 *
 * We have to consider the case where some of the attribute values are small and less
 * long than the final size we would calculate using merely (available size / number of attributes).
 * 
 * Due to odd browser behaviour we also need to stop the text going right to
 * the edge of the table so we take off another icon width.
 *
 * Due to IE moving the scroll bar inward we have to also take off a width to compensate.
 * 
 * level - level of the row
 * fixedChars - The fixed characters on the line which must be there
 * attributes - array of string attribute values which must fit on the line
 * lineWidth - width in pixels of the whole line we are fitting on
 */	
function calculatePolicyAttachmentAuthoringAttributeWidth(/*int*/level, /*string*/fixedCharsBold, /*string*/fixedChars, /*Array of strings*/attributes, /*int*/lineWidth, /*int*/percent) {
	// minimum size of attribute, trust me. 4.8 is a good number :-)
	var MINIMUM_SIZE = Math.round(4.8 * globalSizeOfTDWidth);

	var numberOfAttributes = attributes.length;

	// width in pixels of the icon
	var iconSize = 16;

	// width of a scrollbar
	var scrollbarSize = 20;
	
	// work out the space taken by the padding for the level
	var levelEmSize = calculatePaddingEmSize(level);
	
	// we think the pixel size is the width of a char times the Em size
	var levelSize = Math.round(levelEmSize * globalSizeOfTDWidth);

	//measure the fixed chars	
	var fixedCharSize = measureTextT(fixedChars).width + measureTextT(fixedCharsBold, true).width
		
	// work out whats left
	var availableSize = lineWidth - fixedCharSize - levelSize - iconSize - iconSize;		
	var OavailableSize = availableSize;
	var availableSize = Math.round(availableSize*percent);
	
	//debugIt("fixedChars: '" + fixedChars + "' levelEmSize: " + levelEmSize + " globalSizeOfTDWidth: " + globalSizeOfTDWidth + " availableSize: " + availableSize + " OavailableSize: " + OavailableSize+ " lineWidth: " + lineWidth + " fixedCharSize: " + fixedCharSize + " levelSize: " + levelSize);
	if(! dojo.isIE) {
		availableSize -= scrollbarSize;
	}
	
	// return value
	var attributeSize = availableSize;
	
	// if its negative we have a problem and must resort to specifying a compromise width
	// which will force scrolling...
	if(availableSize < 0) {
		// make it the minimum
		attributeSize = MINIMUM_SIZE;
		availableSize = MINIMUM_SIZE * numberOfAttributes;
	} else {		
		if (numberOfAttributes > 1) {
			attributeSize = Math.round(availableSize / numberOfAttributes);
		
			//alert(attributeSize + " " + availableSize + " " + numberOfAttributes);
		
			//alert(">1");
			// now check if any of the values would be smaller than this size, if so
			// we can use the extra space to make it larger		
			var totalLeft = Math.round(availableSize);
			var divideBy = 0;
			
			//We need to create 2 numbers, the total free space available minus the space not needed by the attributes
			//smaller then attributeSize and the number of attrs bigger than attributeSize to divide it amongst
			for (attr in attributes) {
				var sizeAttr = measureTextT(attributes[attr]).width;
				//debugIt("sizeAttr: " + sizeAttr + " attributeSize: " + attributeSize);
				
				if (sizeAttr < attributeSize) {
					totalLeft -= sizeAttr;
				} else {				
					divideBy++;
				}			
			}
	
			//If we found any attributes over attributeSize then redistribute the free
			//space just between them 		
			if (divideBy > 0) {							
				attributeSize = Math.round(totalLeft / divideBy);
				//debugIt("totalLeft: " + totalLeft + " divideBy: " + divideBy + " attributeSize: " + attributeSize);
			}
		}		
	}
	
	// check the attribute size isn't too small want 10 min
	if(attributeSize < MINIMUM_SIZE) {
		attributeSize = MINIMUM_SIZE;
		//debugIt("attributeSize was too small, setting to: " + attributeSize);
	}
	
	//debugIt(attributeSize);
	
	return attributeSize;
}

function debugIt(txt) {
	var debug=document.getElementById("debug");	
	debug.innerHTML = debug.innerHTML + txt + "<br/><br/>";
}

/**
 * Define the HomePageFacet JavaScript object.  Instances of this object are
 * created when a home page is rendered.  They are used to render
 * browse by classification with dynamic ellipsizing
 */
HomePageFacet = function(title, titleUri, name, uri){

	// Facet provider name
	this.title = title;
	this.titleUri = titleUri;
	this.name = name;	
	this.uri = uri;
}
