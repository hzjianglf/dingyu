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

var facetsSMD = {
					"serviceType":"JSON-RPC",
					"SMDVersion":"0.1",
					"serviceURL":"/ServiceRegistry/RPCAdapter/jsonrpc/FacetService",
					"objectName":"FacetService",
					"methods":[
						{
							"name":"retrieveFacets",
							"parameters":[
								{"name":"facetProviderName"}
							]
						}
					]
				};

// Global variables
var numberOfResponses = 0;
var messageTimerInterval = 200;
var messageTimer;
var dots = ["", ".", "..", "...", "....", "....."];
var dotsIndex = 0;

var globalFacets = new Array();
var globalFacetProviders = new Array();

var facetService = new dojo.rpc.JsonService(contextRoot + "/RPCAdapter/jsonrpc/FacetService");

/**
 * The retrieveFacets method is invoked by each facet provider when the page
 * is loaded in order to retrieve its facets. It uses the JSONRpcClient to
 * retrieve of the facets.
 */
function retrieveFacets(facetProviderName) {

	/*
	 * Retrieve the the list of facet asynchronously.  Use closure to ensure
	 * that we have access to the facetProviderName from within the callback
	 * function.
	 */
	var callbackFunction = function(facets) {
		
		// Display the retrieved facets
		displayFacetsFirstTime(facetProviderName, facets);
	}
	facetService.retrieveFacets(facetProviderName).addCallback(callbackFunction);
}

/**
 * The initializeMessageTimer simply initializes the messageTimer!!!
 */
function initializeMessageTimer() {

	messageTimer = setTimeout(updateMessage, messageTimerInterval);
}

/**
 * The updateMessage function animates the ... on the "Fetching filters..."
 * message in order to provide some feedback to the user.  This function is
 * invoked when the messageTimer expires.
 */
function updateMessage() {

	// Retrieve the div for the "Fetching filters..." message
	var messageNode = document.getElementById("fetchingFiltersMessage");
	
	// Set the message new value for the message
	if (++dotsIndex == dots.length) {
		dotsIndex = 0;
	}
	messageNode.firstChild.nodeValue = fetchingFiltersMessage + dots[dotsIndex];
	
	// Reschedule the timer
	messageTimer = setTimeout(updateMessage, messageTimerInterval);
}

function displayFacetsFirstTime(facetProvidername, facets) {
	displayFacets(facetProvidername, facets);
	displayResizeFacetsInt();
}

/**
 * The facetsCallback function is invoked when the submitTimer expires.
 * It uses the JSONRpcClient to retrieve all of the facets which can be
 * applied to the current collection.
 */
function displayFacets(facetProviderName, facets, resizing, newTD) {

	//Save them away so we can resize with the browser
	if (resizing != true) {
		globalFacetProviders[numberOfResponses] = facetProviderName;
		globalFacets[facetProviderName] = facets;
	}
	
	// First, increment the number of responses
	if (resizing != true)
		numberOfResponses++;

	// Check to see if any facets were returned
	if (facets.length > 0) {
	
		var titleSize = getSearchBoxFacetSize();

		// Retrieve the divs for the facet provider and the facet provider title
		var facetsDiv = document.getElementById(facetProviderName);
		
		// Create a variable to hold the HTML for the facet provider div
   	var facetsDivHtml = new dojox.string.Builder();

		// Render the title for the facet provider
		var facetProvider = facetProviders[facetProviderName];
					
		if(facetProvider.useTitle == true) {
			// use the provider title

			addTitleHtml(facetProvider.title, facetProvider.name, facetsDivHtml, titleSize);
			
	   	// Create a div to hold the facets
	    facetsDivHtml.append("<div class=\"facetGroup\">");
			// Iterate over the facets
			for (var i = 0; i < facets.length; i++) {
				// Append a row for each facet
				addFacetHtml(facets[i], facetsDivHtml, titleSize);
			}
			// Close the div
			facetsDivHtml.append("</div>");
		} else {
			// use the titles in the facets
			var lastTitle = "";
			var isFirstTitle = true;
			for (var i = 0; i < facets.length; i++) {
				if(facets[i].title != lastTitle) {
					// new title, need to show it
					lastTitle = facets[i].title;
					if(isFirstTitle == true) {
						// use the title
						addTitleHtml(facets[i].title, facets[i].titleUri, facetsDivHtml, titleSize);
						// first title, need to create a div
				    facetsDivHtml.append("<div class=\"facetGroup\">");
						isFirstTitle = false;	
					} else {
						// need to close previous div
						facetsDivHtml.append("</div>");
						// use the title
						addTitleHtml(facets[i].title, facets[i].titleUri, facetsDivHtml, titleSize);
						// create a table
            facetsDivHtml.append("<div class=\"facetGroup\">");
					}
				} // end new title

				// output the facet
				addFacetHtml(facets[i], facetsDivHtml, titleSize);				
			} // end for over facets
			
			// need to close the div if we have started one
			if(isFirstTitle == false) {
				facetsDivHtml.append("</div>");
			}
		}
		
		// Set the HTML for the facet provider div
		facetsDiv.innerHTML = facetsDivHtml.toString();
	}
	
	// Check to see if we have received all of the expected responses
	if (resizing != true && numberOfResponses == facetProviders.length) {

		// Clear the current message timer
		if (messageTimer) {
			clearTimeout(messageTimer);
			messageTimer = null;
		}


		// Retrieve the div for the "Fetching filters..." table
		var fetchingFacetsTableDiv = document.getElementById("fetchingFiltersMessage");
		fetchingFacetsTableDiv.innerHTML = "";
		fetchingFacetsTableDiv.style.visibility = "hidden";
		fetchingFacetsTableDiv.style.position = "absolute";

		// if there are no freeform facet providers, we can hide the portlet if all done
		if(isFreeformFacetProvider == false) {
			// Check to see if any facets were returned
			var someFacets = false;
			var facetsPortletTd = document.getElementById("wsrrFacetSearchPortletArea");
			var providerDivs = facetsPortletTd.childNodes;
			for(var i = 0; i < providerDivs.length; i++) {
				var providerDiv = providerDivs[i];
				// check the child is a DIV before testing for content
				if(providerDiv.tagName == "DIV" && providerDiv.innerHTML != "") {
					// there is content so a Facet will have been rendered
					someFacets = true;
					break;
				}
			}
			if(someFacets == false) {
				// no facets - hide the portlet Div
				var facetsPortlet = document.getElementById("wsrrFacetSearchPortletPos");
				facetsPortlet.innerHTML = "";
				facetsPortlet.style.visibility = "hidden";
				// set position to absolute so the help portlet does not flow beneath it
				facetsPortlet.style.position = "absolute";				
			}
		} // end if
	}
}

/**
 * Add a row to the facetsDivHtml to show this facet.
 */
function addFacetHtml(facet, facetsDivHtml, titleSize) {
	facetsDivHtml.append("<a href=\"ApplyFacet.do?facetType=" + facet.type + "&amp;facetContext=" + escape(facet.context) + "\" tabindex=\"1\">");
	
	var size = getFacetSize(titleSize, facet.countText);
	var truncatedName = shortenFacet(facet, size);
	
	if(truncatedName.length < facet.name.length) {
		// need to truncate the name and put the full name in a span
		facetsDivHtml.append("<span title=\"");
		facetsDivHtml.append(filterHtml(facet.name));
		facetsDivHtml.append("\">");
		facetsDivHtml.append(truncatedName);
		facetsDivHtml.append("</span>");
	} else {
		facetsDivHtml.append(facet.name);
	}
		
  var divider="\u202a";
  if (isRTL)
    divider="\u202b";
	facetsDivHtml.append("</a> "+divider+"(" + facet.countText + ")\u202c<br/>");
}

/**
 * Add the title in a heading tag, truncate if needed
 */
function addTitleHtml(title, titleUri, facetsDivHtml, size) {

	facetsDivHtml.append("<h3>");
		
	var truncatedName = shortenTitle(title, titleUri, size);
	if(truncatedName.length < title.length) {
		// need to truncate the title and put the full title in a span
		facetsDivHtml.append("<span title=\"");
		facetsDivHtml.append(filterHtml(title));
		facetsDivHtml.append("\">");
		facetsDivHtml.append(truncatedName);
		facetsDivHtml.append("</span>");
	} else {
		facetsDivHtml.append(title);
	}
	facetsDivHtml.append("</h3>");
}

/**
 * Define the FacetProvider JavaScript object.  Instances of this object are
 * created when a collection view is rendered.  They are used to render the
 * facet providers within the filters portlet.
 */
FacetProvider = function(facetProviderName, facetProviderTitle, facetProviderUseTitle){

	// Facet provider name
	this.name = facetProviderName;
	this.title = facetProviderTitle;
	if(facetProviderUseTitle == "true") {
		this.useTitle = true;
	} else {
		this.useTitle = false;
	}
}

function filterHtml(message) {
		
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
