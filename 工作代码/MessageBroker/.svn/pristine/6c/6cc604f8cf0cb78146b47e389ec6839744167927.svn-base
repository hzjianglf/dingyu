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
dojo.require("dojo.rpc.JsonService");
dojo.require("com.ibm.sr.widgets.DetailView");
dojo.require("com.ibm.sr.widgets.AddRelationship");
dojo.require("com.ibm.sr.widgets.AddLoadOrCreateRelationshipTarget");
dojo.require("com.ibm.sr.widgets.ProgressPanel");
dojo.require("dojo.fx");
dojo.require("dojo.parser");

// Create an instance of the BaseObjectService
var baseObjectService = new dojo.rpc.JsonService(contextRoot + "/RPCAdapter/jsonrpc/BaseObjectService");

// Reference to the current widget displayed in the action div
var currentWidget = null;

// Deferred object used to control our asynchronous tasks
var deferredObj = null;

// Duration of the wipe in/out animation
var wipeDuration = 400;

// Cache of Tab definitions used to render the detail view panel
var tabDefinitionCache = new Object();

// Cache of business model info used to render the detail view panel
var businessModelInfoCache = new Object();

// id of the div to show progress in the ProgressPanel widget - no idea how to refer to the static in the widget itself
var PROGRESS_DIV_ID = "progressPanelSection";
// title to show for the progress panel when/if it appears
var progressTabTitle = null;
var lastClientSize={width: 0, height: 0};

var errorBsrUrisMap={};
var errorBsrUriFinalObj=null;

var previousScrollPos = -1;

// height of the scrollable table in pixels. Used to position the new element in
// the middle of the table.
var tableHeight = 170;

var lastTargetViewed = null;

/**
 * Initialise all the required stuff on first page load.
 */
function initPage() {

  // Log entry to the method
  //console.debug(">>> initPage");

  /*
   * The first thing that we need to do is to populate the relationships table
   * with all of the user defined relationships on the source object.  If the
   * source object is a GenericObject that has been created from a business
   * model, this needs to take into account any modelled or optional
   * relationships.
   */
  processBusinessModelRelationships();
  processUserDefinedRelationships();
  
  // sort out convenience data
  if (errorBsrUris && errorBsrUris.data)
  {
    // convenience reference to get a hashmap of the error bsruris
	for(x in errorBsrUris.data) {
		errorBsrUrisMap[errorBsrUris.data[x]] = true;
		errorBsrUriFinalObj = errorBsrUris.data[x];		
	}
  }
  
  if (viewMe != null) {
	viewTargetDetails(viewMe);  
  } else  
	  // Display the details of the source object
	  showDetailsPanel(sourceObject, generalProperties, modelledPropertyInfos, optionalPropertyInfos, modelledRelationshipInfos, optionalRelationshipInfos);
  
  if (dojo.isIE==6)
  {
    if (typeof window != "undefined")
      dojo.connect(window, 'onresize', 'handleWindowResize');  // window resize
  }
  // Log exit from the method        
  //console.debug("<<< initPage");
}

function processBusinessModelRelationships() {

  // Log entry to the method
  //console.debug(">>> processBusinessModelRelationships");

  // Make sure that we have a valid array of modelled relationship infos
  if (modelledRelationshipInfos && modelledRelationshipInfos.length > 0) {

    // Iterate over the modelledRelationshipInfos array
    for (var i = 0; i < modelledRelationshipInfos.length; i++) {
    
      // Attempt to retrieve a relationship with the specified name
      var ncName = modelledRelationshipInfos[i].NCName;
      var relationship = getRelationship(ncName, sourceObject);
      if (relationship) {

        // Now render the relationship row based on the relevant constraints
        var ncName = relationship.name;
        var displayName = modelledRelationshipInfos[i].name;
        var displayType = modelledRelationshipInfos[i].targetTypeName;
        var fieldHelp = modelledRelationshipInfos[i].description;
        var removeRelationshipAllowed = false;
        var constraints = modelledRelationshipInfos[i].constraints;
        var addTargetAllowed = false;
        if (  constraints.maxCardinality == -1
           || relationship.targets.length < constraints.maxCardinality
           ) {
           addTargetAllowed = true;
        }
        var parentRow = createRelationshipRow(ncName, displayName, displayType, removeRelationshipAllowed, addTargetAllowed);

        // Now render the target rows based on the relevant constraints
        var removeTargetAllowed = false;
        if (  constraints.minCardinality == 0
           || relationship.targets.length > constraints.minCardinality
           ) {
           removeTargetAllowed = true;
        }
        processTargetRows(parentRow, ncName, relationship.targets, removeTargetAllowed, addTargetAllowed);
      } else {
        /*
         * A modelled relationship should always exist on the
         * object, unless it is being created!!!
         */
      }
    } // FOR
  } // IF - modelledRelationshipInfos &&, etc...

  // See if we have a valid array of optional relationship infos
  if (optionalRelationshipInfos && optionalRelationshipInfos.length > 0) {

    // Iterate over the optionalRelationshipInfos array
    for (var i = 0; i < optionalRelationshipInfos.length; i++) {
    
      // Attempt to retrieve a relationship with the specified name
      var ncName = optionalRelationshipInfos[i].NCName;
      var relationship = getRelationship(ncName, sourceObject);
      if (relationship) {
      
        // Now render the relationship row based on the relevant constraints
        var ncName = relationship.name;
        var displayName = optionalRelationshipInfos[i].name;
        var displayType = optionalRelationshipInfos[i].targetTypeName;
        var fieldHelp = optionalRelationshipInfos[i].description;
        var removeRelationshipAllowed = true;
        var constraints = optionalRelationshipInfos[i].constraints;
        var addTargetAllowed = false;
        if (  constraints.maxCardinality == -1
           || relationship.targets.length < constraints.maxCardinality
           ) {
           addTargetAllowed = true;
        }
        var parentRow = createRelationshipRow(ncName, displayName, displayType, removeRelationshipAllowed, addTargetAllowed);
        
        // Now render the target rows based on the relevant constraints
        var removeTargetAllowed = false;
        if (  constraints.minCardinality == 0
           || relationship.targets.length > constraints.minCardinality
           ) {
           removeTargetAllowed = true;
        }
        processTargetRows(parentRow, ncName, relationship.targets, removeTargetAllowed, addTargetAllowed);
      }
    } // FOR
  } // IF - modelledRelationshipInfos &&, etc...

  // Log exit from the method        
  //console.debug("<<< processBusinessModelRelationships");
}

function processUserDefinedRelationships() {

  // Log exit from the method        
  //console.debug("<<< processUserDefinedRelationships");

  /*
   * Process the user defined relationships on the source object, only
   * rendering those relationships that have not already been rendered when
   * processing the business model relationships.
   */
  var relationships = sourceObject.relationships;
  for (var i = 0; i < relationships.length; i++) {
    if (  relationships[i].modelled == false
       && !isBusinessModelRelationship(relationships[i].name)
       ) {
      var ncName = relationships[i].name;
      var targets = relationships[i].targets;
      
      var parentRow = createRelationshipRow(ncName, ncName, null, true, true);
      
      processTargetRows(parentRow, ncName, relationships[i].targets, true, true);
    }
  } // FOR

  // Log entry to the method
  //console.debug(">>> processUserDefinedRelationships");
}

function createRelationshipRow(/*String*/ relationshipName, /*String*/ displayName, /*String*/ displayType, /*Boolean*/ removeAllowed, /*Boolean*/ addTargetAllowed) {

  // Log entry to the method
  //console.debug(">>> createRelationshipRow(", relationshipName, ", ", displayName, ", ", displayType, ", ", removeAllowed, ", ", addTargetAllowed, ")");

  // Create the variable to return
  var relationshipRow = null;

  // Retrieve the relationship table
  var table = dojo.byId("relationshiptable");
  if (table) {

    /*
     * We need to modify the styles of the current last row to make sure
     * that the cells render correctly when a row is added below.
     */
    var rows = table.tBodies[0].rows;
    var lastRow = rows[rows.length - 1];
    var cells = lastRow.cells;
    for (var i = 0; i < cells.length; i++) {
      if (i != cells.length - 1) {
        cells[i].className = "centerleft";
      } else {
        cells[i].className = "right";
      }
    } // FOR
    
    // Create a new row in the table
    relationshipRow = table.tBodies[0].insertRow(-1);
    relationshipRow.className = "notselected";
    relationshipRow.id = relationshipName;
      
    /*
     * Now create all of the relevant cells within the new row.  We start
     * with the name cell.
     */
      var nameCell = relationshipRow.insertCell(0);
      nameCell.className = "bottomleft";
      var img = document.createElement("img");
      img.setAttribute("src", "theme/"+currentTheme+"/images/"+rtlMode+"arrow_down_right.gif");
      img.setAttribute("alt", "");
      nameCell.appendChild(img);
      if (removeAllowed) {
        var divider="\u202a|\u202c";
        if (isRTL)
          divider="\u202b|\u202c";
        var textNode = document.createTextNode(displayName + "\u00a0"+divider+"\u00a0");
        nameCell.appendChild(textNode);
        var removeSpan = document.createElement("span");
        removeSpan.setAttribute("id", relationshipName + "#Remove");
        nameCell.appendChild(removeSpan);        
        var removeAnchor = document.createElement("a");
        removeAnchor.setAttribute("href", "javascript:removeRelationship('" + relationshipName + "')");
        removeAnchor.setAttribute("title", removeTitleTxt);
        removeAnchor.appendChild(document.createTextNode(removeTxt));
        removeSpan.appendChild(removeAnchor);
      } else {
        var textNode = document.createTextNode(displayName);
        nameCell.appendChild(textNode);
      }

      // Targets cell
      var targetsCell = relationshipRow.insertCell(1);
      targetsCell.className = "bottomright";
      if (addTargetAllowed) {
        var addTargetAnchor = document.createElement("a");
        addTargetAnchor.setAttribute("href", "javascript:viewAddLoadOrCreateRelationshipTargetPanel('" + relationshipName + "')");
        addTargetAnchor.setAttribute("title", addTargetTitleTxt);
        if (displayType) {
          var subs = [displayType];
          var addTypedTargetTxt = dojo.string.substitute(addTxt, subs);
          addTargetAnchor.appendChild(document.createTextNode(addTypedTargetTxt));
        } else {
          addTargetAnchor.appendChild(document.createTextNode(addTargetTxt));
        }
        targetsCell.appendChild(addTargetAnchor);
      } else {
        targetsCell.innerHTML = "&nbsp;";
      }
      
      // Adjust the size of the table as required
      adjustTableSize(table);
    } // IF - table

  // Log exit from the method        
  //console.debug("<<< createRelationshipRow", relationshipRow);
  
  return relationshipRow;
}

function processTargetRows(/*DOM Node*/ parentRow, /*String*/ relationshipName, /*WSRRObject[]*/ targets, /*Boolean*/ removeTargetAllowed, /*Boolean*/ addTargetAllowed) {

  // Log entry to the method
  //console.debug(">>> processTargetRows(", parentRow, ", ", relationshipName, ", ", targets, ", ", removeTargetAllowed, ", ", addTargetAllowed, ")");

  // Check that there are some targets
  if (targets && targets.length > 0) {
  
    /*
     * The addTargetAllowed flag indicates whether the first target should be
     * rendered on the same row as the relationship name, i.e., if it is false
     */
    if (!addTargetAllowed) {
    
      // Retrieve the first target and construct the id for the cell    
      var targetObject = targets[0];
      var targetCellId = relationshipName + "#" + getBsrUri(targetObject);
      var targetsCell = parentRow.cells[1];
      targetsCell.id = targetCellId;
      
      // Clear any contents in the cell
      targetsCell.innerHTML = "";

      // Construct the new contents for the cell
      var targetNameAnchor = document.createElement("a");
      targetNameAnchor.setAttribute("href", "javascript:viewTargetDetails('" + targetCellId + "')");
      targetNameAnchor.setAttribute("title", viewTargetDetailsTitleTxt);
      var targetNameSpan = document.createElement("span");
      targetNameSpan.className = "bold";
      targetNameSpan.appendChild(document.createTextNode(getObjectName(targetObject)));
      targetNameAnchor.appendChild(targetNameSpan);
      targetsCell.appendChild(targetNameAnchor);
      var divider="\u202a|\u202c";
      if (isRTL)
        divider="\u202b|\u202c";
      targetsCell.appendChild(document.createTextNode("\u00a0"+divider+"\u00a0"));

      if (getBsrUri(targetObject).charAt(0) == "_" && getBsrUri(targetObject).charAt(1) != "_") {
        var targetEditAnchor = document.createElement("a");
        targetEditAnchor.setAttribute("href", "javascript:editTarget('" + targetCellId + "')");
        targetEditAnchor.setAttribute("title", editTargetTitleTxt);
        targetEditAnchor.appendChild(document.createTextNode(editTxt));
        targetsCell.appendChild(targetEditAnchor);
        targetsCell.appendChild(document.createTextNode("\u00a0"+divider+"\u00a0"));
      }
      
      var changeTargetAnchor = document.createElement("a");
      changeTargetAnchor.setAttribute("href", "javascript:viewAddLoadOrCreateRelationshipTargetPanel('" + targetCellId + "')");
      changeTargetAnchor.setAttribute("title", changeTitleTxt);
      changeTargetAnchor.appendChild(document.createTextNode(changeTxt));
      targetsCell.appendChild(changeTargetAnchor);
      
      if (removeTargetAllowed) {
        targetsCell.appendChild(document.createTextNode("\u00a0"+divider+"\u00a0"));
        var removeSpan = document.createElement("span");
        removeSpan.setAttribute("id", targetCellId + "#Remove");
        targetsCell.appendChild(removeSpan);
        var removeTargetAnchor = document.createElement("a");
        removeTargetAnchor.setAttribute("href", "javascript:removeTarget('" + targetCellId + "')");
        removeTargetAnchor.setAttribute("title", removeTargetTitleTxt);
        removeTargetAnchor.appendChild(document.createTextNode(removeTxt));
        removeSpan.appendChild(removeTargetAnchor);
      } // IF - removeTargetAllowed
    } // IF - targets && targets.length > 0
  
    // Iterate over each target
    var startIndex = (addTargetAllowed) ? 0 : 1;
    for (var i = startIndex; i < targets.length; i++) {
    
      // Create a row for the current target
      parentRow = createTargetRow(parentRow, relationshipName, targets[i], removeTargetAllowed);
    } // FOR
  } // IF - targets && targets.length > 0

  // Log exit from the method        
  //console.debug("<<< processTargetRows");
}

function createTargetRow(/*DOM Node*/ parentRow, /*String*/ relationshipName, /*WSRRObject*/ targetObject, /*Boolean*/ removeTargetAllowed) {

  // Log entry to the method
  //console.debug(">>> createTargetRow(", parentRow, ", ", relationshipName, ", ", targetObject, ", ", removeTargetAllowed, ")");
  
  // Create the variable to return
  var targetRow = null;
  
  // Retrieve the table
  var table = dojo.byId("relationshiptable");
  if (table) {
  
    /*
     * Create a new row in the table.  The insertRow method creates the new
     * row before the row with the specified index.  Therefore, we need to
     * add one to the relationship row index and then make sure that this
     * does not take us beyond the end of the table.
     */
    var leftClass = "centerleft";
    var rightClass = "right";
    var rowIndex = parentRow.sectionRowIndex + 1;
    if (rowIndex == table.tBodies[0].rows.length) {
      /*
       * The new row will be the last row in the table.  We need to modify
       * the styles of the current last row to make sure that the cells
       * render correctly.
       */
      rowIndex = -1;
      var rows = table.tBodies[0].rows;
      var lastRow = rows[rows.length - 1];
      var cells = lastRow.cells;
      for (var i = 0; i < cells.length; i++) {
        if (i != cells.length - 1) {
          cells[i].className = leftClass;
        } else {
          cells[i].className = rightClass;
        }
      } // FOR
          
      // Set the left and right class names for the row to be inserted
      leftClass = "bottomleft";
      rightClass = "bottomright";
    } // IF - rowIndex == table.tBodies[0].rows.length
        
    /*
     * Now create the new row in the table.  Don't bother selecting it at
     * this point since this will happen when we display the details of
     * the target object.
     */
    targetRow = table.tBodies[0].insertRow(rowIndex);
    var targetRowId = relationshipName + "#" + getBsrUri(targetObject);
    targetRow.id = targetRowId;
        
    /*
     * Now create all of the relevant cells within the new row.  We start
     * with the name cell.
     */
    var nameCell = targetRow.insertCell(0);
    nameCell.className = leftClass;
    nameCell.innerHTML = "&nbsp;";
        
    // Targets cell
    var targetsCell = targetRow.insertCell(1);
    targetsCell.className = rightClass;
    var targetNameAnchor = document.createElement("a");
    targetNameAnchor.setAttribute("href", "javascript:viewTargetDetails('" + targetRowId + "')");
    targetNameAnchor.setAttribute("title", viewTargetDetailsTitleTxt);
    var targetNameSpan = document.createElement("span");
    targetNameSpan.className = "bold";
    targetNameSpan.appendChild(document.createTextNode(getObjectName(targetObject)));
    targetNameAnchor.appendChild(targetNameSpan);
    targetsCell.appendChild(targetNameAnchor);
    var divider="\u202a|\u202c";
    if (isRTL)
      divider="\u202b|\u202c";
    targetsCell.appendChild(document.createTextNode("\u00a0"+divider+"\u00a0"));      
    
      if (getBsrUri(targetObject).charAt(0) == "_" && getBsrUri(targetObject).charAt(1) != "_") {
        var targetEditAnchor = document.createElement("a");
      	var targetCellId = relationshipName + "#" + getBsrUri(targetObject);
        targetEditAnchor.setAttribute("href", "javascript:editTarget('" + targetCellId + "')");
        targetEditAnchor.setAttribute("title", editTargetTitleTxt);
        targetEditAnchor.appendChild(document.createTextNode(editTxt));
        targetsCell.appendChild(targetEditAnchor);
        targetsCell.appendChild(document.createTextNode("\u00a0"+divider+"\u00a0"));
      }
      
    var changeTargetAnchor = document.createElement("a");
    changeTargetAnchor.setAttribute("href", "javascript:viewAddLoadOrCreateRelationshipTargetPanel('" + targetRowId + "')");
    changeTargetAnchor.setAttribute("title", changeTitleTxt);
    changeTargetAnchor.appendChild(document.createTextNode(changeTxt));
    targetsCell.appendChild(changeTargetAnchor);
    if (removeTargetAllowed) {
      targetsCell.appendChild(document.createTextNode("\u00a0"+divider+"\u00a0"));
      var removeSpan = document.createElement("span");
      var targetCellId = relationshipName + "#" + getBsrUri(targetObject);
      removeSpan.setAttribute("id", targetCellId + "#Remove");
      targetsCell.appendChild(removeSpan);      
      var removeTargetAnchor = document.createElement("a");
      removeTargetAnchor.setAttribute("href", "javascript:removeTarget('" + targetRowId + "')");
      removeTargetAnchor.setAttribute("title", removeTargetTitleTxt);
      removeTargetAnchor.appendChild(document.createTextNode(removeTxt));
      removeSpan.appendChild(removeTargetAnchor);
    } // IF - removeTargetAllowed
    
    // Adjust the size of the table as required
    adjustTableSize(table);
  } // IF - table
  
  // Log exit from the method        
  //console.debug("<<< createTargetRow ", targetRow);
  
  return targetRow;
}

/**
 * Makes sure the top table scrolls correctly
 */
function adjustTableSize() {

  // Log entry to the method
  //console.debug(">>> adjustTableSize");

  var table = dojo.byId("relationshiptable");
  if (table) {
    var tbody = table.tBodies[0];
    if (document.defaultView) {
      var height = document.defaultView.getComputedStyle(tbody, null).getPropertyValue("height");
      height = height.substring(0, height.length - 2);
      if (height > 170) {
        tbody.style.height = "170px";
      } // IF - style > 170
    } // IF - document.defaultView
    adjustScrollbox();
  } // IF - table
  adjustTargetCells();

  // Log exit from the method        
  //console.debug("<<< adjustTableSize");
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
          previousRow.cells[1].className="multiright";
        }
        else
        {
          // non-target row - adjust previous row
          previousRow.cells[0].className="centerleft";
          previousRow.cells[1].className="right";
        }
        previousRow=row;
      } // end for
    }
  }
}

/**
 * The showCurrentWidget function shows the current widget using a dojo
 * animation.
 */
function showCurrentWidget() {

  // Log entry to the method
  //console.debug(">>> showCurrentWidget");

  if (currentWidget) {
  	// show widget
  	currentWidget.domNode.style.height = "auto";
  	currentWidget.domNode.style.overflow = "hidden";
  }

  // Log exit from the method        
  //console.debug("<<< showCurrentWidget");
}

/**
 * The hideCurrentWidget function hides the current widget using a dojo
 * animation.  The callback method specified is invoked once the animation
 * is complete.
 */
function hideCurrentWidget(callback) {

  // Log entry to the method
  //console.debug(">>> hideCurrentWidget(", callback, ")");

  if (currentWidget) {
  
  	// hide
  	currentWidget.domNode.style.height = "auto";
  	currentWidget.domNode.style.display = "none";
  }  	
  	// call back
  	callback();
  

  // Log exit from the method        
  //console.debug("<<< hideCurrentWidget");
}

function viewSourceDetails(rowId) {

  // Log entry to the method
  //console.debug(">>> viewSourceDetails(", rowId, ")");

  // Make sure that we are not waiting for the response to another action
  if (!deferredObj) {

    // Retrieve the relationship table
    var table = dojo.byId("relationshiptable");
    if (table) {

      // First clear any error message that might be displayed
      clearMessages();

      // Check that the details of the source object are not already displayed
      if (!isRowSelected(rowId)) {

        // Select the row containing the source object
        selectRow(rowId);
  
        // Hide the current widget and show the details panel
        var callback = function(){showDetailsPanel(sourceObject, generalProperties, modelledPropertyInfos, optionalPropertyInfos, modelledRelationshipInfos, optionalRelationshipInfos);};
        hideCurrentWidget(callback);
      } else {
        // The source object is selected, but are its details being displayed
        if (currentWidget.id != "actionDiv_detailsViewWidget") {
          // Hide the current widget and show the details panel
          var callback = function(){showDetailsPanel(sourceObject, generalProperties, modelledPropertyInfos, optionalPropertyInfos, modelledRelationshipInfos, optionalRelationshipInfos);};
          hideCurrentWidget(callback);
        }
      }
    } // IF - table
  } // IF - !deferredObj

  // Log exit from the method        
  //console.debug("<<< viewSourceDetails");
}

function showDetailsPanel(wsrrObject, generalProperties, modelledPropertyInfos, optionalPropertyInfos, modelledRelationshipInfos, optionalRelationshipInfos) {

  // Log entry to the method
  //console.debug(">>> showDetailsPanel(", wsrrObject, ", ", generalProperties, ", ", modelledPropertyInfos, ", ", optionalPropertyInfos, ", ", modelledRelationshipInfos, ", ", optionalRelationshipInfos, ")");

  // Locate the actionDiv
  var actionDiv = dojo.byId("actionDiv");
  if (actionDiv) {

    /*
     * Check to see if there is a widget currently displayed in the action
     * div.  If there is destroy it.
     */
    if (currentWidget) {
        currentWidget.destroyRecursive();
        currentWidget = null;
    }

    /*
     * The DetailView widget contains its own content table in its template.
     * Therefore, we do not need to create our own content table here.  We
     * just need to clear out the contents of the actionDiv.
     */
    actionDiv.innerHTML = "";

    /*
     * Create a child to div to hang the add detail view widget off.
     * 
     * NOTE: It is important to do this to prevent the placeholder div from
     *       being removed from the DOM tree when we destroy the widget.
     */
    var widgetDiv = document.createElement("div");
    widgetDiv.id = actionDiv.id + "_detailsViewWidget";
    actionDiv.appendChild(widgetDiv);
  
    /*
     * Create the DetailView widget... make sure to pass all of the
     * required args to the constuctor
     */
    var args = {
      wsrrObject: wsrrObject,
      generalProperties: generalProperties,
      modelledPropertyInfos: modelledPropertyInfos, 
      optionalPropertyInfos: optionalPropertyInfos,
      modelledRelationshipInfos: modelledRelationshipInfos,
      optionalRelationshipInfos: optionalRelationshipInfos,
      themeName: currentTheme
    };
    var detailViewWidget = new com.ibm.sr.widgets.DetailView(args, widgetDiv);
    currentWidget = detailViewWidget;

    // Set the appropriate title for the tab
    setTabTitle(detailsTabTitleTxt);
    
    // Show the current widget
    showCurrentWidget();
  } // IF - actionDiv

  // Log exit from the method        
  //console.debug("<<< showDetailsPanel");
}

/**
 * Show the progress panel in place of the details panel.
 */
function showProgressPanel(tabText) {
  // Locate the actionDiv
  var actionDiv = dojo.byId("actionDiv");
  if (actionDiv) {

    /*
     * Check to see if there is a widget currently displayed in the action
     * div.  If there is destroy it.
     */
    if (currentWidget) {
        currentWidget.destroyRecursive();
        currentWidget = null;
    }

    /*
     * The progress panel widget needs to be displayed within a
     * content table.  We need to create this table manually since it is not
     * embedded within the widget.
     *
     * The createContentTable function returns a child to div to hang the
     * add/load relationship targets widget off.
     * 
     * NOTE: It is important to do this to prevent the placeholder div from
     *       being removed from the DOM tree when we destroy the widget.
     */
    var widgetDiv = createContentTable(actionDiv);
    widgetDiv.id = actionDiv.id + "_progressPanelWidget";
  
    /*
     * Create the ProgressPanel widget... make sure to pass all of the
     * required args to the constuctor
     */
    var args = {
    };
    var progressPanelWidget = new com.ibm.sr.widgets.ProgressPanel(args, widgetDiv);
    currentWidget = progressPanelWidget;

    // Set the appropriate title for the tab from the global, set when startProgressFunction was called
    setTabTitle(progressTabTitle);
    
    // Show the current widget
    showCurrentWidget();
  } // IF - actionDiv

}

function viewAddRelationshipPanel() {

  // Log entry to the method
  //console.debug(">>> viewAddRelationshipPanel");

  // Make sure that we are not waiting for the response to another action
  if (!deferredObj) {

    // Check to see if the source object row is currently selected
    if (!isRowSelected(sourceObject.bsrUri)) {
    
      // Select the row containing the source object
      selectRow(sourceObject.bsrUri);

      // Hide the current widget and show the relevant panel
      hideCurrentWidget(showAddRelationshipPanel);
    } else { // IF - !isRowSelected(sourceObject.bsrUri)
      // The row is selected, but is the add relationship widget displayed
      if (currentWidget.id != "actionDiv_addRelationshipWidget") {
        // Hide the current widget and show the relevant panel
        hideCurrentWidget(showAddRelationshipPanel);
      }
    }
  } // IF - !deferredObj
  
  // Log exit from the method        
  //console.debug("<<< viewAddRelationshipPanel");
}

function showAddRelationshipPanel() {
  
  // Log entry to the method
  //console.debug(">>> showAddRelationshipPanel");

  // Locate the actionDiv
  var actionDiv = dojo.byId("actionDiv");
  if (actionDiv) {

    /*
     * Check to see if there is a widget currently displayed in the action
     * div.  If there is destroy it.
     */
    if (currentWidget) {
        currentWidget.destroyRecursive();
        currentWidget = null;
    }

    /*
     * The add relationships widget needs to be displayed within a content
     * table.  We need to create this table manually since it is not embedded
     * within the widget.
     *
     * The createContentTable function returns a child to div to hang the add
     * relationship widget off.
     * 
     * NOTE: It is important to do this to prevent the placeholder div from
     *       being removed from the DOM tree when we destroy the widget.
     */
    var widgetDiv = createContentTable(actionDiv);
    widgetDiv.id = actionDiv.id + "_addRelationshipWidget";
  
    /*
     * Create the AddRelationship widget... make sure to pass all of the
     * required args to the constuctor
     */
    var args = {
      contextRoot: contextRoot,
      sourceObjectType: objectType,
      sourceObject: sourceObject,
      optionalRelationships: optionalRelationshipInfos
    };
    var addRelationshipWidget = new com.ibm.sr.widgets.AddRelationship(args, widgetDiv);
    addRelationshipWidget.startup();
    currentWidget = addRelationshipWidget;
    
    // Replace the handlers for the add and cancel buttons
    addRelationshipWidget.onAddButtonClicked = addRelationshipAddButtonClicked;
    addRelationshipWidget.onCancelButtonClicked = addRelationshipCancelButtonClicked;

    // Set the appropriate title for the tab
    setTabTitle(addRelationshipTabTitleTxt);

    // Show the current widget
    showCurrentWidget();
  } // IF - actionDiv

  // Log exit from the method        
  //console.debug("<<< showAddRelationshipPanel");
}

function addRelationship(sourceObjectBsrUri, relationshipName) {

  // Log entry to the method
  //console.debug(">>> addRelationship(", sourceObjectBsrUri, ", ", relationshipName, ")");

  // Make sure that we are not waiting for the response to another action
  if (!deferredObj) {

    // Invoke the relevant method on the baseObjectService
    deferredObj = baseObjectService.addRelationship(sourceObjectBsrUri, userStorageCorrelator, relationshipName);
    deferredObj.addCallback(addRelationshipCallback);
    deferredObj.addErrback(processRPCException);
    
    // progress
    progressTabTitle = getTabTitle();
    startProgressFunction(PROGRESS_DIV_ID, processingTxt, showProgressPanel);
  } // IF - !deferredObj
  
  // Log exit from the method        
  //console.debug("<<< addRelationship");
}

function addRelationshipCallback(response) {

  // Log entry to the method
  //console.debug(">>> addRelationshipCallback(", response, ")");

  // First clear any error message that might be displayed
  clearMessages();

  // Make sure that we clear our deferred object
  deferredObj = null;
  
  // progress
  stopProgress();

  // Make sure that the response does not contain an error
  if (!response.error) {
  
    /*
     * The relationship has been successfully added to the edit relationships
     * session.  We now need to add a corresponding row to the relationships
     * table and update our array of user defined relationships.
     */
    var relationshipName = response.data[0];
    var newRelationship = new Object();
    newRelationship.name = relationshipName;
    newRelationship.targets = new Array();
    newRelationship.modelled = false;
    sourceObject.relationships.push(newRelationship);
    
    /*
     * Now we need to create the row in the table to represent this
     * relationship.  We need to determine if the relationship is a business
     * modelled relationship in order to retrieve the correct display name.
     * This is possible if the user has added an optional relationship to
     * the source object.
     */
    var displayName = relationshipName;
    var displayType = null;
    if (isBusinessModelRelationship(relationshipName)) {
      var relationshipInfo = getRelationshipInfo(relationshipName);
      if (relationshipInfo) {
        // Get the display name for the relationship
        displayName = relationshipInfo.name;
        displayType = relationshipInfo.targetTypeName;
      } // IF - relationshipInfo
    } // IF - isBusinessModelRelationship

    // Create the actual relationship row
    createRelationshipRow(relationshipName, displayName, displayType, true, true);

    // Display the details of the source object
    viewSourceDetails(sourceObject.bsrUri);
  } else { // IF = !reponse.error
    // Display the error in the messages portlet
    displayErrorMessage(response.error.message, response.error.error);
  }

  // Log exit from the method        
  //console.debug("<<< addRelationshipCallback");
}

/**
 * The addRelationshipAddButtonClicked function replaces the
 * onAddButtonClicked of the addRelationshipWidget.  It executes within the
 * context of this widget, i.e., IT IS NOT A GLOBAL FUNCTION.
 */
function addRelationshipAddButtonClicked(event) {

  // Log entry to the method
  //console.debug(">>> addRelationshipAddButtonClicked", event, ")");

  // Retrieve the relationship name that has been specified
  var sourceObjectBsrUri = this.sourceObject.bsrUri;
  var relationshipName = this.getRelationshipName();
  addRelationship(sourceObjectBsrUri, relationshipName);
  
  fixScrollbarPosition(relationshipName);

  // Log exit from the method        
  //console.debug("<<< addRelationshipAddButtonClicked");
}

function addRelationshipCancelButtonClicked(event) {

  // Log entry to the method
  //console.debug(">>> addRelationshipCancelButtonClicked(", event, ")");

  // Display the details of the source object or last viewed object
  if (lastTargetViewed == null || lastTargetViewed == sourceObject.bsrUri) {
	  viewSourceDetails(sourceObject.bsrUri);
	  fixScrollbarPosition(sourceObject.bsrUri);
  } else {
	  viewTargetDetails(lastTargetViewed);
	  fixScrollbarPosition(lastTargetViewed);
  }

  // Log exit from the method        
  //console.debug("<<< addRelationshipCancelButtonClicked");
}

function addRelationshipTargetSearchButtonClicked(event) {

  // Log entry to the method
  //console.debug(">>> addRelationshipTargetSearchButtonClicked(", event, ")");

  /*
   * This action will navigate the user away from the current page.  In this
   * situation, we should cancel any callbacks that we may be waiting for.
   */
  if (deferredObj) {
    deferredObj.cancel();
    deferredObj = null;
  }
  
  // Find the EditRelationshipsForm
  var editRelationshipsForm = dojo.byId("EditRelationshipsForm");
  if (editRelationshipsForm) {

    /*
     * Place the bsrUri of the source object into the form and the 
     * userStorageCorrelator value.
     */
    var sourceObjectBsrUriInput = document.createElement("input");
    sourceObjectBsrUriInput.type = "hidden";
    sourceObjectBsrUriInput.name = "uri";
    sourceObjectBsrUriInput.value = sourceObject.bsrUri;
    editRelationshipsForm.appendChild(sourceObjectBsrUriInput);

    var userStorageCorrelatorInput = document.createElement("input");
    userStorageCorrelatorInput.type = "hidden";
    userStorageCorrelatorInput.name = "userStorageCorrelator";
    userStorageCorrelatorInput.value = userStorageCorrelator;
    editRelationshipsForm.appendChild(userStorageCorrelatorInput);

    /*
     * Now retrieve the name of the relationship whose targets are being edited
     * and place it in the form.  Also, retrieve the bsr uri of the target
     * that is being changed (if any) and place it in the form.
     */
    var relationshipName = currentWidget.getRelationshipName();
    var targetBsrUri = currentWidget.getTargetBsrUri();
    
    var relationshipNameInput = document.createElement("input");
    relationshipNameInput.type = "hidden";
    relationshipNameInput.name = "relationshipName";
    relationshipNameInput.value = relationshipName;
    editRelationshipsForm.appendChild(relationshipNameInput);
    
    /*
     * Defect #: 5133
     *
     * Make sure that we have a valid targetBsrUri before creating the hidden
     * input element in the form.  If we don't do this, IE sends the string
     * "null" to the server as the value of the parameter, which breaks the
     * server side parameter validation.
     */
    if (targetBsrUri) {
      var targetBsrUriInput = document.createElement("input");
      targetBsrUriInput.type = "hidden";
      targetBsrUriInput.name = "oldTargetBsrUri";
      targetBsrUriInput.value = targetBsrUri;
      editRelationshipsForm.appendChild(targetBsrUriInput);
    }
    
    if (isBusinessModelRelationship(relationshipName)) {
      var relationship = getRelationship(relationshipName, sourceObject);
      var relationshipInfo = getRelationshipInfo(relationshipName);
      if (relationship && relationshipInfo) {
        
        // Use the constraints to determine what to do
        var constraints = relationshipInfo.constraints;
        if (constraints.maxCardinality != -1) {
          var maxAllowedSelections = constraints.maxCardinality - relationship.targets.length;
          
          //If we are replacing an entry then must up the max allowed by 1
          if (targetBsrUri)
          	maxAllowedSelections++;
          
          var maxAllowedSelectionsInput = document.createElement("input");
          maxAllowedSelectionsInput.type = "hidden";
          maxAllowedSelectionsInput.name = "maxAllowedSelections";
          maxAllowedSelectionsInput.value = maxAllowedSelections;
          editRelationshipsForm.appendChild(maxAllowedSelectionsInput);
        } // IF - constraints.maxCardinality != -1
      } // IF - relationshipInfo
    } // IF - isBusinessModelRelationship(relationshipName)

    // Modify the action to perform the search
    editRelationshipsForm.action = contextRoot + "/EditRelationshipsSearch.do";
    editRelationshipsForm.submit();
  }

  // Log exit from the method        
  //console.debug("<<< addRelationshipTargetSearchButtonClicked");
}

function addRelationshipTargetAddButtonClicked(event) {

  // Log entry to the method
  //console.debug(">>> addRelationshipTargetAddButtonClicked(", event, ")");

  // Make sure that we are not waiting for the response to another action
  if (!deferredObj) {
    var sourceObjectBsrUri = sourceObject.bsrUri;
    var relationshipName = currentWidget.getRelationshipName();
    var targetObjectBsrUri = currentWidget.getSuggestTargetBsrUri();

    deferredObj = baseObjectService.addRelationshipTarget(sourceObjectBsrUri, userStorageCorrelator, relationshipName, targetObjectBsrUri);
    deferredObj.addCallback(addTargetCallback);
    deferredObj.addErrback(processRPCException);
    
    // progress
    progressTabTitle = getTabTitle();
    startProgressFunction(PROGRESS_DIV_ID, processingTxt, showProgressPanel);
  } // IF - !deferredObj

  // Log exit from the method        
  //console.debug("<<< addRelationshipTargetAddButtonClicked");
}

function addRelationshipTargetChangeButtonClicked(event) {

  // Log entry to the method
  //console.debug(">>> addRelationshipTargetChangeButtonClicked(", event, ")");

  // Make sure that we are not waiting for the response to another action
  if (!deferredObj) {
    var sourceObjectBsrUri = sourceObject.bsrUri;
    var relationshipName = currentWidget.getRelationshipName();
    var currentTargetBsrUri = currentWidget.getTargetBsrUri();
    var newTargetBsrUri = currentWidget.getSuggestTargetBsrUri();

    deferredObj = baseObjectService.changeRelationshipTarget(sourceObjectBsrUri, userStorageCorrelator, relationshipName, currentTargetBsrUri, newTargetBsrUri);
    deferredObj.addCallback(changeTargetCallback);
    deferredObj.addErrback(processRPCException);
    
    // progress
    progressTabTitle = getTabTitle();
    startProgressFunction(PROGRESS_DIV_ID, processingTxt, showProgressPanel);
  } // IF - !deferredObj

  // Log exit from the method        
  //console.debug("<<< addRelationshipTargetChangeButtonClicked");
}


function addRelationshipTargetLoadDocumentButtonClicked(event) {

  // Log entry to the method
  //console.debug(">>> addRelationshipTargetLoadDocumentButtonClicked(", event, ")");

  /*
   * This action will navigate the user away from the current page.  In this
   * situation, we should cancel any callbacks that we may be waiting for.
   */
  if (deferredObj) {
    deferredObj.cancel();
    deferredObj = null;
  }
  
  // Find the EditRelationshipsForm
  var editRelationshipsForm = dojo.byId("EditRelationshipsForm");
  if (editRelationshipsForm) {

    /*
     * Place the bsrUri of the source object into the form and the 
     * userStorageCorrelator value.
     */
    var sourceObjectBsrUriInput = document.createElement("input");
    sourceObjectBsrUriInput.type = "hidden";
    sourceObjectBsrUriInput.name = "uri";
    sourceObjectBsrUriInput.value = sourceObject.bsrUri;
    editRelationshipsForm.appendChild(sourceObjectBsrUriInput);

    var userStorageCorrelatorInput = document.createElement("input");
    userStorageCorrelatorInput.type = "hidden";
    userStorageCorrelatorInput.name = "userStorageCorrelator";
    userStorageCorrelatorInput.value = userStorageCorrelator;
    editRelationshipsForm.appendChild(userStorageCorrelatorInput);

    /*
     * Now retrieve the name of the relationship whose targets are being edited
     * and place it in the form.  Also, retrieve the bsr uri of the target
     * that is being changed (if any) and place it in the form.
     */
    var relationshipName = currentWidget.getRelationshipName();
    var targetBsrUri = currentWidget.getTargetBsrUri();
    
    var relationshipNameInput = document.createElement("input");
    relationshipNameInput.type = "hidden";
    relationshipNameInput.name = "relationshipName";
    relationshipNameInput.value = relationshipName;
    editRelationshipsForm.appendChild(relationshipNameInput);
    
    /*
     * Defect #: 5133
     *
     * Make sure that we have a valid targetBsrUri before creating the hidden
     * input element in the form.  If we don't do this, IE sends the string
     * "null" to the server as the value of the parameter, which breaks the
     * server side parameter validation.
     */
    if (targetBsrUri) {
      var targetBsrUriInput = document.createElement("input");
      targetBsrUriInput.type = "hidden";
      targetBsrUriInput.name = "oldTargetBsrUri";
      targetBsrUriInput.value = targetBsrUri;
      editRelationshipsForm.appendChild(targetBsrUriInput);
    }
    
    if (isBusinessModelRelationship(relationshipName)) {
      var relationship = getRelationship(relationshipName, sourceObject);
      var relationshipInfo = getRelationshipInfo(relationshipName);
      if (relationship && relationshipInfo) {
        
        // Use the constraints to determine what to do
        var constraints = relationshipInfo.constraints;
        if (constraints.maxCardinality != -1) {
          var maxAllowedSelections = constraints.maxCardinality - relationship.targets.length;
          
          //If we are replacing an entry then must up the max allowed by 1
          if (targetBsrUri)
          	maxAllowedSelections++;
          	
          var maxAllowedSelectionsInput = document.createElement("input");
          maxAllowedSelectionsInput.type = "hidden";
          maxAllowedSelectionsInput.name = "maxAllowedSelections";
          maxAllowedSelectionsInput.value = maxAllowedSelections;
          editRelationshipsForm.appendChild(maxAllowedSelectionsInput);
        } // IF - constraints.maxCardinality != -1
      } // IF - relationshipInfo
    } // IF - isBusinessModelRelationship(relationshipName)

    // Modify the action to perform the search
    editRelationshipsForm.action = contextRoot + "/EditRelationshipsLoadDocumentPrepare.do";
    editRelationshipsForm.submit();
  }

  // Log exit from the method        
  //console.debug("<<< addRelationshipTargetLoadDocumentButtonClicked");
}

function addRelationshipTargetCancelButtonClicked(event) {

  // Log entry to the method
  //console.debug(">>> addRelationshipTargetCancelButtonClicked(", event, ")");

  // Display the details of the source object or last viewed object
  if (lastTargetViewed == null || lastTargetViewed == sourceObject.bsrUri) {
	  viewSourceDetails(sourceObject.bsrUri);
	  fixScrollbarPosition(sourceObject.bsrUri);
  } else {
	  viewTargetDetails(lastTargetViewed);
	  fixScrollbarPosition(lastTargetViewed);
  }

  // Log exit from the method        
  //console.debug("<<< addRelationshipTargetCancelButtonClicked");
}

function removeRelationship(relationshipName) {

  // Log entry to the method
  //console.debug(">>> removeRelationship(", relationshipName, ")");

  // Make sure that we are not waiting for the response to another action
  if (!deferredObj) {

    // Make sure that the relationship name is valid
    if (relationshipName) {

      // Invoke the relevant method on the base object service
      deferredObj = baseObjectService.removeRelationship(sourceObject.bsrUri, userStorageCorrelator, relationshipName);
      deferredObj.addCallback(removeRelationshipCallback);
      deferredObj.addErrback(processRPCException);
      
      // progress
      startProgress(relationshipName + "#Remove", removingTxt);
    } // IF - relationshipName
  } // IF - !deferredObj

  // Log exit from the method        
  //console.debug("<<< removeRelationship");
}

function removeRelationshipCallback(response) {

  // Log entry to the method
  //console.debug(">>> removeRelationshipCallback(", response, ")");

  // First clear any error message that might be displayed
  clearMessages();

  // Make sure that we clear our deferred object
  deferredObj = null;

  // progress
  stopProgress();

  // Make sure that the response does not contain an error
  if (!response.error) {
  
    /*
     * The id of the row is simply the name of the relationship that was
     * removed.  This is returned to us in the response.  Simply remove the
     * row in the table with this id.
     */
    var rowId = response.data[0];
    removeRow(rowId);
    
    /*
     * Check to see if the details of the source object are being displayed.
     * If they are, we need to get the detail view widget to remove the
     * relationship from its relationships section as well.  We know that the
     * details of the source object are being display if it is selected and
     * detail view widget is the current widget.
     */
    var table = dojo.byId("relationshiptable");
    if (table) {
      if (  isRowSelected(sourceObject.bsrUri)
         && currentWidget.id == "actionDiv_detailsViewWidget"
         ) {
        currentWidget.removeRelationship(rowId);
      }
    } // IF - table
    
    /*
     * We also need to remove the relationship object from the array of
     * relationships on the source object.  This is not so simple in
     * javascript.  The first thing to do is determine the index of the
     * element to remove from the array.
     */
    for (var i = 0; i < sourceObject.relationships.length; i++) {
      if (sourceObject.relationships[i].name == rowId) {
        // Remove the element from the array of user defined relationships    
        var modifiedArray = removeElementAtIndex(sourceObject.relationships, i);
        if (modifiedArray) {
          // Overwrite the user defined relationships array 
          sourceObject.relationships = modifiedArray;
        }
        break;
      }
    } // FOR
  } else { // IF = !reponse.error
    // Display the error in the messages portlet
    displayErrorMessage(response.error.message, response.error.error);
  }

  // Log exit from the method        
  //console.debug("<<< removeRelationshipCallback");
}

function viewAddLoadOrCreateRelationshipTargetPanel(rowId) {

  // Log entry to the method
  //console.debug(">>> viewAddLoadOrCreateRelationshipTargetPanel(", rowId, ")");

  // Make sure that we are not waiting for the response to another action
  if (!deferredObj) {

    // Make sure that the rowId is valid
    if (rowId) {
    
      // Extract the relationship name and target bsr uri from the row id
      var elements = rowId.split("#");
      var relationshipName = elements[0];
      var targetBsrUri = (elements.length == 2) ? elements[1] : null;

      // Check to see if the row is currently selected
      if (!isRowSelected(rowId)) {

        // Select the row containing the relationship or target
        selectRow(rowId);

        // Hide the current widget and show the relevant panel
        var callback = function(){showAddLoadOrCreateRelationshipTargetPanel(relationshipName, targetBsrUri);};
        hideCurrentWidget(callback);
      } else {
        // The row is selected, but is the add targets panel displayed
        if (currentWidget.id != "actionDiv_AddLoadOrCreateRelationshipTargetWidget") {
          // Hide the current widget and show the relevant panel
          var callback = function(){showAddLoadOrCreateRelationshipTargetPanel(relationshipName, targetBsrUri);};
          hideCurrentWidget(callback);
        }
      }
    } // IF - rowId
  } // IF - !deferredObj
  
  // Log exit from the method        
  //console.debug("<<< viewAddLoadOrCreateRelationshipTargetPanel");
}

function showAddLoadOrCreateRelationshipTargetPanel(relationshipName, targetBsrUri) {

  // Log entry to the method
  //console.debug(">>> showAddLoadOrCreateRelationshipTargetPanel(", relationshipName, ", ", targetBsrUri, ")");

  // Locate the actionDiv
  var actionDiv = dojo.byId("actionDiv");
  if (actionDiv) {

    /*
     * Check to see if there is a widget currently displayed in the action
     * div.  If there is destroy it.
     */
    if (currentWidget) {
        currentWidget.destroyRecursive();
        currentWidget = null;
    }
    
    /*
     * The add/load relationship targets widget needs to be displayed within a
     * content table.  We need to create this table manually since it is not
     * embedded within the widget.
     *
     * The createContentTable function returns a child to div to hang the
     * add/load relationship targets widget off.
     * 
     * NOTE: It is important to do this to prevent the placeholder div from
     *       being removed from the DOM tree when we destroy the widget.
     */
    var widgetDiv = createContentTable(actionDiv);
    widgetDiv.id = actionDiv.id + "_AddLoadOrCreateRelationshipTargetWidget";
  
    /*
     * Create the AddLoadOrCreateRelationshipTarget widget... make sure to pass all
     * of the required args to the constuctor.  The search type may need to be
     * restricted based on any relationship info that has defined for the
     * relationship in question.
     */
    var displaySavedSearches = false;
    var actualSearchTypes = searchTypes;
    var relationshipDisplayName = relationshipName;
    if (objectType == "GenericObject") {
      var relationshipInfo = getRelationshipInfo(relationshipName);
      if (relationshipInfo) {
      
        // Get the display name for the relationship
        relationshipDisplayName = relationshipInfo.name;
        
        // Retrieve the OWL uris of target types for this relationship
        var targetTypeUris = relationshipInfo.urisForTargets;
        if (targetTypeUris && targetTypeUris.length > 0) {
        
          /*
           * Iterate over the list of targets for the relationship and build
           * up a JSON representation of an AutoSuggestSearchType for each.
           * For each target type uri, we need to retrieve the display name to
           * render in the entity type drop down and the actual type that the
           * auto-suggest widget will search by.  If the acutal type is a
           * business model representation of an internal SDO type, we need to
           * retrieve the actual SDO type name.
           */
          actualSearchTypes = new Array();
          for (var i = 0; i < targetTypeUris.length; i++) {
            var targetTypeUri = targetTypeUris[i];
            var displayName = getTargetTypeName(targetTypeUri);
            var actualType = getSDOTypeFromTargetUri(targetTypeUri);
            if (!actualType) {
              actualType = targetTypeUri;
            }
            
            /*
             * Now we need to fix up the label for the BaseObject type
             * because the label in the system OWL file is not correct.
             */
            if (actualType == "BaseObject") {
              displayName = getSearchTypeDisplayName(actualType);
              
              /*
               * This relationship allows targets of type BaseObject, which
               * means that all object types in WSRR are allowed.  It is
               * therefore safe to allow the user to perform saved searches
               * when selecting target entities.
               */
              displaySavedSearches = true;
            }
            
            // Now create the AutoSuggestSearchType object
            var searchType = new Object();
            searchType.displayName = displayName;
            searchType.id = actualType;
            actualSearchTypes.push(searchType);
          } // FOR
        }
      } else { // IF - relationshipInfo
        /*
         * This is a generic object but the relationship being edited is not
         * defined in a business model.  It is therefore safe to allow the
         * user to perform saved searches when selecting target entities.
         */
        displaySavedSearches = true;
      }
    } else { // IF - objectType == "GenericObject"
    
      /*
       * This is not a generic object and so it cannot have any business model
       * relationships (type relationships).  It is therefore safe to allow
       * the user to perform saved searches when selecting target entities.
       */
      displaySavedSearches = true;
    }
    
    var args = {
      relationshipName: relationshipName,
      targetBsrUri: targetBsrUri,
      displayName: relationshipDisplayName,
      contextRoot: contextRoot,
      searchTypes: actualSearchTypes,
      displaySavedSearches: displaySavedSearches,
      savedSearches: savedSearches,
      allowCreate: true      
    };
    
    var addLoadOrCreateRelationshipTargetWidget = new com.ibm.sr.widgets.AddLoadOrCreateRelationshipTarget(args, widgetDiv);
    currentWidget = addLoadOrCreateRelationshipTargetWidget;
    
    // Replace the handlers for the add and cancel buttons
    addLoadOrCreateRelationshipTargetWidget.onSearchButtonClicked = addRelationshipTargetSearchButtonClicked;
    addLoadOrCreateRelationshipTargetWidget.onAddButtonClicked = addRelationshipTargetAddButtonClicked;
    addLoadOrCreateRelationshipTargetWidget.onChangeButtonClicked = addRelationshipTargetChangeButtonClicked;
    addLoadOrCreateRelationshipTargetWidget.onLoadDocumentButtonClicked = addRelationshipTargetLoadDocumentButtonClicked;
    addLoadOrCreateRelationshipTargetWidget.onCancelButtonClicked = addRelationshipTargetCancelButtonClicked;
    addLoadOrCreateRelationshipTargetWidget.onCreateEntityButtonClicked = addRelationshipTargetCreateButtonClicked;

    // Set the appropriate title for the tab
    if (targetBsrUri) {
      setTabTitle(changeTargetTabTitleTxt);
    } else {
      setTabTitle(addTargetTabTitleTxt);
    }

    // Show the current widget
    showCurrentWidget();
  } // IF - actionDiv
  
  // Log exit from the method        
  //console.debug("<<< showAddLoadOrCreateRelationshipTargetPanel");
}

function addTargetCallback(response) {

  // Log entry to the method
  //console.debug(">>> addTargetCallback(", response, ")");

  // First clear any error message that might be displayed
  clearMessages();

  // Make sure that we clear our deferred object
  deferredObj = null;
  
  // progress
  stopProgress();

  // Make sure that the response does not contain an error
  if (!response.error) {
  
    /*
     * We are expecting the name of the relationship and the target WSRRObject
     * in the response.  We need to add the WSRRObject as a target of the 
     * relationship on the source object.
     */
    var relationshipName = response.data[0];
    var targetObject = response.data[1];
    var relationship = getRelationship(relationshipName, sourceObject);
    if (relationship) {
      relationship.targets.push(targetObject);
      
      // Retrieve the relationship row
      var relationshipRow = dojo.byId(relationshipName);
      if (relationshipRow) {
      
        /*
         * Now we need to create the row in the table to represent this
         * relationship target.  We need to determine if the relationship being
         * is a business modelled relationship in order to decide where to
         * render the target.
         */
        var addTargetAllowed = false;
        var removeTargetAllowed = false;
        if (isBusinessModelRelationship(relationshipName)) {
          var relationshipInfo = getRelationshipInfo(relationshipName);
          if (relationshipInfo) {
        
            // Use the constraints to determine what to do
            var constraints = relationshipInfo.constraints;
            if (  constraints.maxCardinality == -1
               || relationship.targets.length < constraints.maxCardinality
               ) {
               addTargetAllowed = true;
            }
            if (  constraints.minCardinality == 0
               || relationship.targets.length > constraints.minCardinality
               ) {
               removeTargetAllowed = true;
            }
            
            /*
             * The addTargetAllowed flag indicates whether the first target
             * should be rendered on the same row as the relationship name,
             * i.e., if it is false
             */
            if (!addTargetAllowed) {
    
              // Construct the id for the cell
              var targetCellId = relationshipName + "#" + getBsrUri(targetObject);
              var targetsCell = relationshipRow.cells[1];
              targetsCell.id = targetCellId;
      
              // Clear any contents in the cell
              targetsCell.innerHTML = "";

              // Construct the new contents for the cell
              var targetNameAnchor = document.createElement("a");
              targetNameAnchor.setAttribute("href", "javascript:viewTargetDetails('" + targetCellId + "')");
              targetNameAnchor.setAttribute("title", viewTargetDetailsTitleTxt);
              var targetNameSpan = document.createElement("span");
              targetNameSpan.className = "bold";
              targetNameSpan.appendChild(document.createTextNode(getObjectName(targetObject)));
              targetNameAnchor.appendChild(targetNameSpan);
              targetsCell.appendChild(targetNameAnchor);
              var divider="\u202a|\u202c";
              if (isRTL)
                divider="\u202b|\u202c";                            
              targetsCell.appendChild(document.createTextNode("\u00a0"+divider+"\u00a0"));
              
		      if (getBsrUri(targetObject).charAt(0) == "_" && getBsrUri(targetObject).charAt(1) != "_") {
		        var targetEditAnchor = document.createElement("a");
		        targetEditAnchor.setAttribute("href", "javascript:editTarget('" + targetCellId + "')");
		        targetEditAnchor.setAttribute("title", editTargetTitleTxt);
		        targetEditAnchor.appendChild(document.createTextNode(editTxt));
		        targetsCell.appendChild(targetEditAnchor);
		        targetsCell.appendChild(document.createTextNode("\u00a0"+divider+"\u00a0"));
		      }
              
              var changeTargetAnchor = document.createElement("a");
              changeTargetAnchor.setAttribute("href", "javascript:viewAddLoadOrCreateRelationshipTargetPanel('" + targetCellId + "')");
              changeTargetAnchor.setAttribute("title", changeTitleTxt);
              changeTargetAnchor.appendChild(document.createTextNode(changeTxt));
              targetsCell.appendChild(changeTargetAnchor);
              if (removeTargetAllowed) {
                targetsCell.appendChild(document.createTextNode("\u00a0"+divider+"\u00a0"));
                var removeTargetAnchor = document.createElement("a");
                removeTargetAnchor.setAttribute("href", "javascript:removeTarget('" + targetCellId + "')");
                removeTargetAnchor.setAttribute("title", removeTargetTitleTxt);
                removeTargetAnchor.appendChild(document.createTextNode(removeTxt));
                targetsCell.appendChild(removeTargetAnchor);
              } // IF - removeTargetAllowed
            }
          } // IF - relationshipInfo
        } else { // IF - isBusinessModelRelationship
        
          /*
           * This is a user defined relationship, so targets can be added and
           * the relationship can be removed
           */
          addTargetAllowed = true;
          removeTargetAllowed = true;
        }
        
        // If targets can be added, we still need to add a row
        if (addTargetAllowed) {
            
          /*
           * Targets can be added to the current relationship.  Therefore,
           * the new target needs to be added to the bottom of the current
           * list of target objects.  We need to walk down the rows below
           * the relationship row to find the last target.
           */
          var table = dojo.byId("relationshiptable");
          if (table) {
            var parentRow = relationshipRow;
            for ( var i = parentRow.rowIndex + 1
                ; table.rows[i] && table.rows[i].id.indexOf(relationshipName + "#") != -1
                ; i++) {
              parentRow = table.rows[i];
            } // FOR
               
            // Create a row for the target object
            createTargetRow(parentRow, relationshipName, targetObject, removeTargetAllowed);
          } // IF - table
        } // IF - addTargetAllowed
        
        // Now apply contraints to any remaining targets
        applyConstraints(relationship);
      } // IF - relationshipRow
    } // IF - relationship

    // Now view the details of the new target
    viewTargetDetails(relationshipName + "#" + getBsrUri(targetObject));
  } else { // IF = !reponse.error
    // Display the error in the messages portlet
    displayErrorMessage(response.error.message, response.error.error);
  }

  // Log exit from the method        
  //console.debug("<<< addTargetCallback");
}

function changeTargetCallback(response) {

 // Log entry to the method
  //console.debug(">>> changeTargetCallback(", response, ")");

  // First clear any error message that might be displayed
  clearMessages();

  // Make sure that we clear our deferred object
  deferredObj = null;
  
  // progress
  stopProgress();

  // Make sure that the response does not contain an error
  if (!response.error) {
  
    /*
     * We are expecting the name of the relationship, the target WSRRObject
     * and the bsr uri of the current target object in the response.  We need
     * to remove the current target from the relationship and add the new
     * WSRRObject as a target of the  relationship on the source object.
     */
    var relationshipName = response.data[0];
    var targetObject = response.data[1];
    var currentTargetBsrUri = response.data[2];
    var relationship = getRelationship(relationshipName, sourceObject);
    if (relationship) {
      // Find the current target object and replace it with the new target
      for (var i = 0; i < relationship.targets.length; i++) {
        if (relationship.targets[i].bsrUri == currentTargetBsrUri) {
          relationship.targets[i] = targetObject;
          break;
        }
      } // FOR
      
      /*
       * Retrieve the cell that represents the old target.  If this is
       * rendered on the same row as the relationship name then DOM node
       * returned will already be the targets cell.  If it is not, we need to
       * retrieve the targets cell from the row.
       */
      var oldRowId = relationshipName + "#" + currentTargetBsrUri;
      var newRowId = relationshipName + "#" + getBsrUri(targetObject)
      var relationshipRow = dojo.byId(oldRowId);
      if (relationshipRow) {
        var targetsCell = null;
        if (relationshipRow.tagName.toUpperCase() == "TD") {
          targetsCell = relationshipRow;
          relationshipRow = relationshipRow.parentNode;
          targetsCell.id = newRowId;
        } else {
          targetsCell = relationshipRow.cells[1];
          relationshipRow.id = newRowId;
        }

		var relationshipInfo = getRelationshipInfo(relationshipName);
		var constraints;
		
		if (relationshipInfo)
        	constraints = relationshipInfo.constraints;        

        var removeTargetAllowed = false;
        if (  ! constraints || constraints.minCardinality == 0
           || relationship.targets.length > constraints.minCardinality
           ) {
           removeTargetAllowed = true;
        }

		//Clear the cell and recreate it        
        var isGO = getBsrUri(targetObject).charAt(0) == "_" && getBsrUri(targetObject).charAt(1) != "_";
        var targetCellId = newRowId;
      // Clear any contents in the cell
      targetsCell.innerHTML = "";

      // Construct the new contents for the cell
      var targetNameAnchor = document.createElement("a");
      targetNameAnchor.setAttribute("href", "javascript:viewTargetDetails('" + targetCellId + "')");
      targetNameAnchor.setAttribute("title", viewTargetDetailsTitleTxt);
      var targetNameSpan = document.createElement("span");
      targetNameSpan.className = "bold";
      targetNameSpan.appendChild(document.createTextNode(getObjectName(targetObject)));
      targetNameAnchor.appendChild(targetNameSpan);
      targetsCell.appendChild(targetNameAnchor);
      var divider="\u202a|\u202c";
      if (isRTL)
        divider="\u202b|\u202c";
      targetsCell.appendChild(document.createTextNode("\u00a0"+divider+"\u00a0"));

      if (isGO) {
        var targetEditAnchor = document.createElement("a");
        targetEditAnchor.setAttribute("href", "javascript:editTarget('" + targetCellId + "')");
        targetEditAnchor.setAttribute("title", editTargetTitleTxt);
        targetEditAnchor.appendChild(document.createTextNode(editTxt));
        targetsCell.appendChild(targetEditAnchor);
        targetsCell.appendChild(document.createTextNode("\u00a0"+divider+"\u00a0"));
      }
      
      var changeTargetAnchor = document.createElement("a");
      changeTargetAnchor.setAttribute("href", "javascript:viewAddLoadOrCreateRelationshipTargetPanel('" + targetCellId + "')");
      changeTargetAnchor.setAttribute("title", changeTitleTxt);
      changeTargetAnchor.appendChild(document.createTextNode(changeTxt));
      targetsCell.appendChild(changeTargetAnchor);
      
      if (removeTargetAllowed) {
        targetsCell.appendChild(document.createTextNode("\u00a0"+divider+"\u00a0"));
        var removeSpan = document.createElement("span");
        removeSpan.setAttribute("id", targetCellId + "#Remove");
        targetsCell.appendChild(removeSpan);
        var removeTargetAnchor = document.createElement("a");
        removeTargetAnchor.setAttribute("href", "javascript:removeTarget('" + targetCellId + "')");
        removeTargetAnchor.setAttribute("title", removeTargetTitleTxt);
        removeTargetAnchor.appendChild(document.createTextNode(removeTxt));
        removeSpan.appendChild(removeTargetAnchor);
      } // IF - removeTargetAllowed
      
      } // IF - relationshipRow
      
      // Now view the details of the new target
      viewTargetDetails(newRowId);
    } // IF - relationship
  } else { // IF = !reponse.error
    // Display the error in the messages portlet
    displayErrorMessage(response.error.message, response.error.error);
  }

  // Log exit from the method        
  //console.debug("<<< changeTargetCallback");
}


function removeTarget(rowId) {

  // Log entry to the method
  //console.debug(">>> removeTarget(", rowId, ")");

  // Make sure that we are not waiting for the response to another action
  if (!deferredObj) {

    // Make sure that the rowId is valid
    if (rowId) {

      // Extract the relationship name and bsrUri from the row id
      var elements = rowId.split("#");
      if (elements.length == 2) {
        var relationshipName = elements[0];
        var bsrUri = elements[1];

        // Invoke the relevant method on the base object service      
        deferredObj = baseObjectService.removeTargetEntityFromRelationship(sourceObject.bsrUri, userStorageCorrelator, relationshipName, bsrUri);
        deferredObj.addCallback(removeTargetCallback);
        deferredObj.addErrback(processRPCException);
        
        // progress
        startProgress(rowId + "#Remove", removingTxt);
      }
    } // IF - rowId
  } // IF - !deferredObj

  // Log exit from the method        
  //console.debug("<<< removeTarget");
}

function removeTargetCallback(response) {

  // Log entry to the method
  //console.debug(">>> removeTargetCallback(", response, ")");

  // First clear any error message that might be displayed
  clearMessages();

  // Make sure that we clear our deferred object
  deferredObj = null;
  
  // progress
  stopProgress();

  // Make sure that the response does not contain an error
  if (!response.error) {
  
    // Recreate the id of the row
    var relationshipName = response.data[0];
    var targetBsrUri = response.data[1];
    var rowId = relationshipName + "#" + targetBsrUri;

    /*
     * Remove the target from the relationship stored in our array of
     * relationships on the source object.
     */
    var relationship = getRelationship(relationshipName, sourceObject);
    if (relationship) {
        var targets = relationship.targets;
        for (var j = 0; j < targets.length; j++) {
          if (targets[j].bsrUri == targetBsrUri) {
            // Remove the element from the array of targets
            var modifiedArray = removeElementAtIndex(targets, j);
            if (modifiedArray) {
              // Overwrite the targets array 
              relationship.targets = modifiedArray;
            }
            break;
          }
        } // FOR
    } // IF - relationship

    /*
     * If the relationship is a business model relationship, attempt to
     * retrieve its relationship info.
     */
    var relationshipInfo = null;
    if (isBusinessModelRelationship(relationshipName)) {
      relationshipInfo = getRelationshipInfo(relationshipName);
    } // IF - isBusinessModelRelationship(relationshipName)

    /*
     * Check to see if the details of the source object are being displayed.
     * If they are, we need to get the detail view widget to remove the target
     * from its relationships section as well.  We know that the details of
     * the source object are being display if it is selected and detail view
     * widget is the current widget.
     */
    var table = dojo.byId("relationshiptable");
    if (table) {
      if (  isRowSelected(sourceObject.bsrUri)
         && currentWidget.id == "actionDiv_detailsViewWidget"
         ) {
        currentWidget.removeRelationshipTarget(relationshipName, targetBsrUri);
      }
    } // IF - table

    /*
     * The target that has been removed may have been rendered on the same 
     * line as the relationship name.  In this situation, we do not want to
     * remove the entire row, we just need to replace the contents of the
     * targets cell.  We can always assume that it is valid to add targets
     * to the corresponding relationship since we have just allowed a
     * a relationship to be removed.
     */
    var row = dojo.byId(rowId);
    if (row) {
      if (row.tagName.toUpperCase() == "TD") {
        /* 
         * This row is a relationship name/target combo and our row variable
         * actually refers to the targets cell on the row.
         */
        var oldTargetsCell = row;
        var newTargetsCell = document.createElement("td");
        newTargetsCell.className = oldTargetsCell.className;
        var addTargetAnchor = document.createElement("a");
        addTargetAnchor.setAttribute("href", "javascript:viewAddLoadOrCreateRelationshipTargetPanel('" + relationshipName + "')");
        addTargetAnchor.setAttribute("title", addTargetTitleTxt);
        if (relationshipInfo) {
          var subs = [relationshipInfo.targetTypeName];
          var addTypedTargetTxt = dojo.string.substitute(addTxt, subs);
          addTargetAnchor.appendChild(document.createTextNode(addTypedTargetTxt));
        } else {
          addTargetAnchor.appendChild(document.createTextNode(addTargetTxt));
        }
        newTargetsCell.appendChild(addTargetAnchor);
  
        // If this row is selected, display the details of the source object
        if (isRowSelected(relationshipName)) {
          viewSourceDetails(sourceObject.bsrUri);
        }
        
        // Set the row variable to the correct value
        row = row.parentNode;
        
        // Now replace the old cell content with the new cell content
        row.replaceChild(newTargetsCell, oldTargetsCell);
      } else {
        // This is a normal row, simply remove it from the table
        removeRow(rowId);
        
        /*
         * Now we need to check whether the 'Add Target' link needs to be
         * displayed.  This is only relevant if the relationship is a business
         * model relationship since user defined relationships will always
         * have this link displayed.  We know the relationship is a business
         * model relationship if we have a valid relationshipInfo object.
         */
        if (relationship && relationshipInfo) {
          /*
           * Retrieve the constraints for the relationship and see if are
           * allowed to add targets and, if so, whether the 'Add Target'
           * link is currently being displayed.
           */
          var relationshipRow = dojo.byId(relationshipName);
          if (relationshipRow) {
            var constraints = relationshipInfo.constraints;
            if (  constraints.maxCardinality == -1
               || relationship.targets.length < constraints.maxCardinality
               ) {
              // Make sure that the add target link is not already displayed
              var targetsCell = relationshipRow.cells[1];
              if (targetsCell.getElementsByTagName("A").length > 1) {

                // Create a new cell to replace the current one
                var addTargetCell = document.createElement("td");
                addTargetCell.className = "right";
                var addTargetAnchor = document.createElement("a");
                addTargetAnchor.setAttribute("href", "javascript:viewAddLoadOrCreateRelationshipTargetPanel('" + relationshipName + "')");
                addTargetAnchor.setAttribute("title", addTargetTitleTxt);
                var subs = [relationshipInfo.targetTypeName];
                var addTypedTargetTxt = dojo.string.substitute(addTxt, subs);
                addTargetAnchor.appendChild(document.createTextNode(addTypedTargetTxt));
                addTargetCell.appendChild(addTargetAnchor);

                // Now swap the contents over
                var oldTargetsCell = relationshipRow.replaceChild(addTargetCell, targetsCell);
              
                // Now create a new row directly below the relationship row
                var leftClass = "centerleft";
                var rightClass = "right";
                var rowIndex = relationshipRow.sectionRowIndex + 1;
                if (rowIndex == table.tBodies[0].rows.length) {
                  /*
                   * The new row will be the last row in the table.  We
                   * need to modify the styles of the current last row to
                   * make sure that the cells render correctly.
                   */
                  rowIndex = -1;
                  var rows = table.tBodies[0].rows;
                  var lastRow = rows[rows.length - 1];
                  var cells = lastRow.cells;
                  for (var i = 0; i < cells.length; i++) {
                    if (i != cells.length - 1) {
                      cells[i].className = leftClass;
                    } else {
                      cells[i].className = rightClass;
                    }
                  } // FOR
          
                  /* 
                   * Set the left and right class names for the row to be
                   * inserted
                   */
                  leftClass = "bottomleft";
                  rightClass = "bottomright";
                } // IF - rowIndex == table.tBodies[0].rows.length

                /*
                 * Now create the new row in the table.  Don't bother
                 * selecting it at this point since this will happen when
                 * we display the details of the target object.
                 */
                var newTargetRow = table.tBodies[0].insertRow(rowIndex);
                newTargetRow.id = oldTargetsCell.id
        
                /*
                 * Now create all of the relevant cells within the new
                 * row.  We start with the name cell.
                 */
                var nameCell = newTargetRow.insertCell(0);
                nameCell.className = leftClass;
                nameCell.innerHTML = "&nbsp;";
        
                // Targets cell
                newTargetRow.appendChild(oldTargetsCell);
                newTargetRow.cells[1].id = "";
                newTargetRow.cells[1].className = rightClass;
  
                /*
                 * If the relationship row was selected, select the new
                 * row.
                 */
                if (isRowSelected(relationshipName)) {
                  selectRow(newTargetRow.id);
                }
              } // IF - targetsCell.getElementsByTagName("A").length > 1
            }
          } // IF - relationshipRow
        } // IF - relationship && relationshipInfo
      }

      // Now apply contraints to any remaining targets
      if (relationship) {
        applyConstraints(relationship);
      }
    } // IF - row

  } else { // IF = !reponse.error
    // Display the error in the messages portlet
    displayErrorMessage(response.error.message, response.error.error);
  }

  // Log exit from the method        
  //console.debug("<<< removeTargetCallback");
}

function viewTargetDetails(rowId) {

  // Log entry to the method
  //console.debug(">>> viewTargetDetails(", rowId, ")");

  // Make sure that we are not waiting for the response to another action
  if (!deferredObj) {

    // Make sure that the rowId is valid
    if (rowId) {

	  lastTargetViewed = rowId;

      // Extract the relationship name and bsrUri from the row id
      var elements = rowId.split("#");
      var relationshipName = null;
      var bsrUri = null;
      if (elements.length == 2) {
        relationshipName = elements[0];
        bsrUri = elements[1];
      } // IF - elements.length == 2

      // Check to see the row is currently selected
      if (!isRowSelected(rowId)) {

        // Select the row containing the target entity
        selectRow(rowId);
        fixScrollbarPosition(rowId);

        /*
         * Before we can display the details of the target object, we need to
         * retrieve the details from the server using the bsrUri of the target
         * object.
         */
        getTargetDetails(relationshipName, bsrUri);
      } else { // IF - !isRowSelected(rowId)
   
        // The target object is selected, but are its details being displayed
        if (currentWidget.id != "actionDiv_detailsViewWidget") {
          /*
           * Before we can display the details of the target object, we need to
           * retrieve the details from the server using the bsrUri of the target
           * object.
           */
          getTargetDetails(relationshipName, bsrUri);
        }
      }            
    } // IF - rowId
  } // IF - !deferredObj  

  // Log exit from the method        
  //console.debug("<<< viewTargetDetails");
}

function getTargetDetails(/*String*/ relationshipName, /*String*/ bsrUri) {

  // Log entry to the method
  //console.debug(">>> getTargetDetails(", relationshipName, ", ", bsrUri, ")");

  // Make sure that we are not waiting for the response to another action
  if (!deferredObj) {

    // Attempt to retrieve the target type
    var targetType = getTargetType(relationshipName, bsrUri);
    var fetchTabDefinition = true;
    if (tabDefinitionCache[targetType]) {
      fetchTabDefinition = false;
    }
  
    // Attempt to retrieve business model info
    var fetchBusinessModelInfo = true;
    if (targetType == "GenericObject") {
      var primaryType = getTargetObjectPrimaryType(relationshipName, bsrUri);
      if (primaryType) {
        if (businessModelInfoCache[primaryType]) {
          fetchBusinessModelInfo = false;
        }
      } else { // IF - primaryType
        // No primary type defined on this generic object
        fetchBusinessModelInfo = false;
      }
    } // IF - targetType == "GenericObject"

    // Invoke the relevant method on the baseObjectService
	var sourceObjectBsrUri = dojo.byId("sourceObjectBsrUri");
    deferredObj = baseObjectService.retrieveByInternalBsrUri(bsrUri, null, sourceObjectBsrUri.value, parseInt(userStorageCorrelator), fetchTabDefinition, fetchBusinessModelInfo);
    deferredObj.addCallback(getTargetDetailsCallback);
    deferredObj.addErrback(processRPCException);

	// set title to show in tab when/if progress panel shown
    progressTabTitle = detailsTabTitleTxt;
    // start progress
    startProgressFunction(PROGRESS_DIV_ID, loadingTxt, showProgressPanel);
  } // IF - !deferredObj

  // Log exit from the method        
  //console.debug("<<< getTargetDetails");
}

function getTargetDetailsCallback(response) {

  // Log entry to the method
  //console.debug(">>> getTargetDetailsCallback(", response, ")");

  // First clear any error message that might be displayed
  //clearMessages();

  // Make sure that we clear our deferred object
  deferredObj = null;

  // stop progress
  stopProgress();

  // Make sure that the response does not contain an error
  if (!response.error) {
  
    // Retrieve the details of the target object from the response
    var targetObject = response.data[0];
    if (targetObject) {

    // delete it's bsrUri if it is an internal one
    var bsrUri=null;
    var properties=targetObject.properties;
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
    
      /*
       * Now check to see if there is a tab definition and/or a business model
       * info object include in the response
       */
      var tabDefinition = null;
      var businessModelInfo = null;
      if (response.data.length > 1) {
      
        for (var i = 1; i < response.data.length; i++) {
          // Retrieve the next object from the response and inspect it
          var object = response.data[i];
          if (  "generalProperties" in object
             && "additionalProperties" in object
             && "action" in object
             ) {
           
             // The object is a tab definition
             tabDefinition = object;
           
            // Place the tab definition in the cache
            tabDefinitionCache[targetObject.sdoType] = tabDefinition;
          } else if (  "modelPropertyInfos" in object
                    && "optionalPropertyInfos" in object
                    && "modelRelationshipInfos" in object
                    && "optionalRelationshipInfos" in object
                    ) {
                  
            // The object is a business model info
            businessModelInfo = object;
           
            // Place the tab definition in the cache
            var primaryType = getPrimaryType(targetObject);
            if (primaryType) {
              businessModelInfoCache[primaryType] = businessModelInfo;
            }
          }
        } // FOR
      } // IF - response.data.length > 1
      
      /*
       * Now check to see if we have a valid tab definition and whether we
       * have and need a business model info object
       */
      if (!tabDefinition) {
        // The tab definition must be in the cache
        tabDefinition = tabDefinitionCache[targetObject.sdoType];
      }
      if (targetObject.sdoType == "GenericObject" && !businessModelInfo) {
        var primaryType = getPrimaryType(targetObject);
        if (primaryType) {
          // The busines model info object must be in the cache
          businessModelInfo = businessModelInfoCache[primaryType];
        } // IF - primaryType &&, etc...
      } // IF - targetObject.sdoType == "GenericObject" &&, etc...

      /*
       * Now we need to extract the general properties section from the tab
       * definition.  It is used in the detail view to layout the top portion
       * of the list of general properties.
       */
      var targetTypeGeneralProperties = null;
      if (tabDefinition.generalProperties && tabDefinition.generalProperties.length > 0) {
        targetTypeGeneralProperties = tabDefinition.generalProperties[0].properties;
      }

      /*
       * Now we need to extract the modelled and optional property and 
       * relationship info objects from the business model info object. They
       * are used in the detail view to layout the top portion of the list of
       * relationships.
       */
      var modelledPropertyInfos = null,
          optionalPropertyInfos = null,
          modelledRelationshipInfos = null,
          optionalRelationshipInfos = null;
      if (businessModelInfo) {
        modelledPropertyInfos = businessModelInfo.modelPropertyInfos,
        optionalPropertyInfos = businessModelInfo.optionalPropertyInfos,
        modelledRelationshipInfos = businessModelInfo.modelRelationshipInfos;
        optionalRelationshipInfos = businessModelInfo.optionalRelationshipInfos;
      }

      // Display the details of the target object
      var callback = function(){showDetailsPanel(targetObject, targetTypeGeneralProperties, modelledPropertyInfos, optionalPropertyInfos, modelledRelationshipInfos, optionalRelationshipInfos);};
      hideCurrentWidget(callback);
      
    } // IF - targetObject
  } else { // IF = !reponse.error
    // Display the error in the messages portlet
    displayErrorMessage(response.error.message, response.error.error);
  }

  // Log exit from the method        
  //console.debug("<<< getTargetDetailsCallback");
}

function selectRow(rowId) {

  // Log entry to the method
  //console.debug(">>> selectRow(", rowId, ")");

  var table = dojo.byId("relationshiptable");
  if (table) {
    var tbody = table.tBodies[0];
    var rows = tbody.rows;
    for (var i = 0; i < rows.length; i++) {
      /*
       * We may have been passed the id of a target cell that has been
       * rendered on the same line as the relationship name.  So we need to
       * check against the id of the row and the id of the target cell.
       */
      if (  rows[i].id == rowId
         || rows[i].cells[1].id == rowId
         ) {
        rows[i].className = "selected";

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
          headerRowHeight = rows[i].offsetHeight;
        }
    
        // Calculate the y position of the top of the selected row
        var rowTop = rows[i].offsetTop - rows[i].offsetHeight + headerRowHeight;

        // Retrieve the current location of the top of the scrollbar
        var scrollTop = scrollableSection.scrollTop;

        /*
         * Now workout if the newly selected row lies outside the visible area
         * of the viewport and, if it does, move the scrollbar so that the row
         * is visible.
         */
        if (rowTop < scrollTop || rowTop >= (scrollTop + 170 - rows[i].offsetHeight)) {
          scrollableSection.scrollTop = rowTop;
        }
      } else {
        rows[i].className = "notselected";
      }
    }
  }

  // Log exit from the method
  //console.debug("<<< selectRow");
}

function isRowSelected(rowId) {

  // Log entry to the method
  //console.debug(">>> isRowSelected(", rowId, ")");

  // Create the variable to return
  var isSelected = false;

  // Make sure that the rowId is valid
  if (rowId) {

    var row = dojo.byId(rowId);
    if (row) {
      /*
       * We may have been passed the id of a target cell that has been
       * rendered on the same line as the relationship name.  Check the tag
       * name just to make sure.
       */
      if (row.tagName.toUpperCase() == "TD") {
        row = row.parentNode;
      }
      if (row.className == "selected") {
        isSelected = true;
      }
    }
  }
  
  // Log exit from the method
  //console.debug("<<< isRowSelected ", isSelected);
  
  return isSelected;
}

function removeRow(rowId) {

  // Log entry to the method
  //console.debug(">>> removeRow(", rowId, ")");

  // Make sure that the rowId passed is valid
  if (rowId) {

    // Retrieve the relationship table
    var table = dojo.byId("relationshiptable");
    if (table) {

      // Retrieve the row with the specified id
      var row = dojo.byId(rowId);
      if (row) {
      
        /*
         * Now build the list of rows to remove.  If the row represents a
         * relationship with targets we also need to delete each row that
         * represents a target of the deleted relationship.  This can be
         * determined by inspecting the rowId... if it represents a
         * relationship it will not contain the '#' separator.
         */
        var rowsToRemove = new Array();
        rowsToRemove.push(row);
        if (rowId.indexOf("#") == -1) {
          for (var i = row.rowIndex + 1; table.rows[i] && table.rows[i].id.indexOf(rowId + "#") != -1; i++) {
            rowsToRemove.push(table.rows[i]);
          } // FOR
        }
        
        /*
         * Now remove all of the relevant rows.  Set the height of the table
         * body to "auto" before we do this to force a redraw.  We can then
         * determine if we need to restrict the height back to 170px after the
         * redraw.  Also, before removing each row, see if it is selected.  If
         * it is, update the details area to display details for the source
         * entity.
         */
        table.tBodies[0].style.height = "auto";
        for (var j = 0; j < rowsToRemove.length; j++) {
          if (isRowSelected(rowsToRemove[j].id)) {
            viewSourceDetails(sourceObject.bsrUri);
          }
          table.deleteRow(rowsToRemove[j].rowIndex);
        } // FOR

        // Set the correct classes for the cells in the last row
        rows = table.tBodies[0].rows;
        if (rows.length > 0) {
          var lastRow = rows[rows.length - 1];
          var cells = lastRow.cells;
          for (var i = 0; i < cells.length; i++) {
            if (i != cells.length - 1) {
              cells[i].className = "bottomleft";
            } else {
              cells[i].className = "bottomright";
            }
          } // FOR
        } // IF - rows.length > 0

        // Adjust the size of the table as required
        adjustTableSize();
      } // IF - row
    } // IF - table
  } // IF - rowId

  // Log exit from the method
  //console.debug("<<< removeRow");
}

/*
 * Utility function to remove the item at the specified index from the
 * specified array.
 */
function removeElementAtIndex(array, index) {

  // Log entry to the method
  //console.debug(">>> removeElementAtIndex(", array, ", ", index, ")");
  
  // Create the variable to return
  var modifiedArray = null;
  
  // Make sure that the array and index are valid
  if (array && array.length > 0 && index >= 0 && array.length > index) {
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
  
  // Log exit from the method
  //console.debug("<<< removeElementAtIndex ", modifiedArray);
  
  return modifiedArray;
}

function getTargetType(/*String*/ relationshipName, /*String*/ bsrUri) {

  // Log entry to the method
  //console.debug(">>> getTargetType(", relationshipName, ", ", bsrUri, ")");

  // Create the variable to return
  var targetType = "BaseObject";
  
  if (relationshipName && bsrUri) {
     
    // Iterate over the user defined relationships
    var targetFound = false;
    for (var i = 0; i < sourceObject.relationships.length && targetFound == false; i++) {
      if (sourceObject.relationships[i].name == relationshipName) {
        // Iterate over the targets of this relationship
        var targets = sourceObject.relationships[i].targets;
        for (var j = 0; j < targets.length; j++) {
          if (targets[j].bsrUri == bsrUri) {
            targetType = targets[j].sdoType;
            targetFound = true;
            break;
          } // IF - targets[j].bsrUri == bsrUri
        } // FOR
      }  // IF - sourceObject.relationships[i].name == relationshipName
    } // FOR
  } // IF - relationshipName && bsrUri
  
  // Log exit from the method
  //console.debug("<<< getTargetType ", targetType);

  return targetType;
}

function getTargetObjectPrimaryType(/*String*/ relationshipName, /*String*/ bsrUri) {

  // Log entry to the method
  //console.debug(">>> getTargetObjectPrimaryType(", relationshipName, ", ", bsrUri, ")");

  // Create the variable to return
  var primaryType = null;
  
  if (relationshipName && bsrUri) {
     
    // Iterate over the relationships
    var targetFound = false;
    for (var i = 0; i < sourceObject.relationships.length && targetFound == false; i++) {
      if (sourceObject.relationships[i].name == relationshipName) {
        // Iterate over the targets of this relationship
        var targets = sourceObject.relationships[i].targets;
        for (var j = 0; j < targets.length && targetFound == false; j++) {
          if (targets[j].bsrUri == bsrUri) {
            primaryType = getPrimaryType(targets[j]);
            targetFound = true;
            break;
          } // IF - targets[j].bsrUri == bsrUri
        } // FOR
      }  // IF - sourceObject.relationships[i].name == relationshipName
    } // FOR
  } // IF - relationshipName && bsrUri
  
  // Log exit from the method
  //console.debug("<<< getTargetObjectPrimaryType ", primaryType);

  return primaryType;
}

function getRelationshipInfo(/*String*/ relationshipName) {

  // Log entry to the method
  //console.debug(">>> getRelationshipInfo(", relationshipName, ")");

  // Create the variable to return
  var relationshipInfo = null;

  // Make sure that the relationship name passed is valid  
  if (relationshipName) {
  
    // Check the modelled relationship infos first
    if (modelledRelationshipInfos && modelledRelationshipInfos.length > 0) {
      for (var i = 0; i < modelledRelationshipInfos.length; i++) {
        if (modelledRelationshipInfos[i].NCName == relationshipName) {
          relationshipInfo = modelledRelationshipInfos[i];
          break;
        }
      } // FOR
    } // IF - modelledRelationshipInfos &&, etc...
    
    // If we did not find a match, check the optional relationship infos
    if (  !relationshipInfo
       && optionalRelationshipInfos && optionalRelationshipInfos.length > 0
       ) {
      for (var i = 0; i < optionalRelationshipInfos.length; i++) {
        if (optionalRelationshipInfos[i].NCName == relationshipName) {
          relationshipInfo = optionalRelationshipInfos[i];
          break;
        }
      } // FOR
    } // IF - !relationshipInfo &&, etc...
  } // IF - relationshipName

  // Log exit from the method
  //console.debug("<<< getRelationshipInfo ", relationshipInfo);

  return relationshipInfo;
}

function getTargetTypeName(/*String*/ targetTypeUri) {

  // Log entry to the method
  //console.debug(">>> getTargetTypeName(", targetTypeUri, ")");

  // Create the variable to return
  var targetTypeName = null;

  // Make sure that the OWL uri passed is valid  
  if (targetTypeUri) {
  
    // Find the target model info with a matching uri
    if (targetModelInfos) {
      var modelInfo = targetModelInfos[targetTypeUri];
      if (modelInfo) {
        targetTypeName = modelInfo.name;
      }
    } // IF - targetModelInfos &&, etc...
  } // IF - targetTypeUri

  // Log exit from the method
  //console.debug("<<< getTargetTypeName ", targetTypeName);

  return targetTypeName;
}

/**
 * The getSearchTypeDisplayName function returns the display name for the
 * specified search type.  This function should be used to determine the
 * display name for the BaseObject type, since the label contained
 * in the relevant OWL file in WSRR is not correct.
 */
function getSearchTypeDisplayName(/*String*/ sdoType) {

  // Log entry to the method
  //console.debug(">>> getSearchTypeDisplayName(", sdoType, ")");

  // Create the variable to return
  var displayName = null;

  // Make sure that the SDO type passed is valid  
  if (sdoType) {
  
    // Find the search type with a matching id
    if (searchTypes && searchTypes.length > 0) {
      for (var i = 0; i < searchTypes.length; i++) {
        if (searchTypes[i].id == sdoType) {
          displayName = searchTypes[i].displayName;
          break;
        }
      } // FOR
    } // IF - searchTypes &&, etc...
  } // IF - sdoType

  // Log exit from the method
  //console.debug("<<< getSearchTypeDisplayName ", displayName);

  return displayName;
}

function createContentTable(/*DOM node*/ actionDiv) {

  // Log entry to the method
  //console.debug(">>> createContentTable(", actionDiv, ")");
  
  // Create the variable to return
  var widgetDiv = null;
  
  // Make sure that the action div is valid
  if (actionDiv) {
  
    // Clear out the current contents of the div
    actionDiv.innerHTML = "";
    
    // Now create a new table and add it as a child of the div
    var contentTable = document.createElement("table");
    contentTable.id = "detailContentTable";
    contentTable.className = "detailcontenttable";
    contentTable.setAttribute("border", "0");
    contentTable.setAttribute("cellpadding", "0");
    contentTable.setAttribute("cellspacing", "0");
    actionDiv.appendChild(contentTable);

    // Now create a single row with a single cell in the table
    var row = contentTable.insertRow(-1);
    var cell = row.insertCell(0);
    
    // Now create the div that will contain the widget
    widgetDiv = document.createElement("div");
    cell.appendChild(widgetDiv);
  } // IF - actionDiv

  // Log exit from the method
  //console.debug("<<< createContentTable ", widgetDiv);
  
  return widgetDiv;
}

function setTabTitle(/*String*/ title) {

  // Log entry to the method
  //console.debug(">>> setTabTitle(", title, ")");

  // Locate the titleDiv
  var titleDiv = dojo.byId("tabTitle");
  if (titleDiv) {
  
    // Clear out the current contents of the div
    titleDiv.innerHTML = "";
  
    // Create the child text node for the title
    titleDiv.appendChild(document.createTextNode(title));
  } // IF - titleDiv

  // Log exit from the method
  //console.debug("<<< setTabTitle ");
}

function getTabTitle() {
  // return variable
  var tabTitle = "";
  
  // Locate the titleDiv
  var titleDiv = dojo.byId("tabTitle");
  if (titleDiv) {
    // get the child text node for the title
    var children = titleDiv.childNodes;
    if(children != null && children.length > 0) {
    	tabTitle = children[0].textContent;
    }
  } // IF - titleDiv
  
  return tabTitle;
}	

function isBusinessModelRelationship(/*String*/ ncName) {

  // Log entry to the method
  //console.debug(">>> isBusinessModelRelationship(", ncName, ")");
    
  // Create the variable to return
  var isBusinessModelRelationship = false;
    
  /*
   * In order to determine if the relationship specified is defined by
   * the business model for the object, we need to check both arrays of
   * relationship infos.  Check the array of modelled relationships
   * first.
   */
  if (modelledRelationshipInfos && modelledRelationshipInfos.length > 0) {

    // Iterate over the modelledRelationshipInfos array
    for (var i = 0; i < modelledRelationshipInfos.length; i++) {
    
      // Check specified name against the NCName of the relationship info
      if (modelledRelationshipInfos[i].NCName == ncName) {
        
        // Match.  Set the flag and break from the loop.
        isBusinessModelRelationship = true;
        break;
      }
    } // FOR
  } // IF - modelledRelationshipInfos &&, etc...
    
  // Now check the array of optional relationship infos, if necessary
  if (  isBusinessModelRelationship == false
     && optionalRelationshipInfos && optionalRelationshipInfos.length > 0
     ) {
       
    // Iterate over the optionalRelationshipInfos array
    for (var i = 0; i < optionalRelationshipInfos.length; i++) {
    
      // Check specified name against the NCName of the relationship info
      if (optionalRelationshipInfos[i].NCName == ncName) {
        
        // Match.  Set the flag and break from the loop.
        isBusinessModelRelationship = true;
        break;
      }
    } // FOR
  } // IF - isBusinessModelRelationship == false &&, etc...
    
  // Log exit from the method        
  //console.debug("<<< isBusinessModelRelationship ", isBusinessModelRelationship);
    
  return isBusinessModelRelationship;
}

function applyConstraints(/*Relationship*/ relationship) {

  // Log entry to the method
  //console.debug(">>> applyConstraints(", relationship, ")");

  // Ensure that we have a valid relationship
  if (relationship) {
  
    /*
     * Check to see if this is a business model relationship and, if so,
     * retrieve the RelationshipInfo object for it.
     */ 
    if (isBusinessModelRelationship(relationship.name)) {
      var relationshipInfo = getRelationshipInfo(relationship.name);
      if (relationshipInfo) {
      
        // Use the constraints to determine what to do
        /*
         * We need to iterate over all of the targets for the relationship and
         * add or remove the 'remove' link as appropriate.  In order to
         * determine where the first target is rendered, we need to use the
         * constraints to check whether the 'Add Target' link in displayed.
         * We also need to use the constraints to determine whether targets
         * can be removed.
         */
        var addTargetAllowed = false;
        var removeTargetAllowed = false;
        var constraints = relationshipInfo.constraints;
        if (  constraints.maxCardinality == -1
           || relationship.targets.length < constraints.maxCardinality
           ) {
          addTargetAllowed = true;
        }
        if (  constraints.minCardinality == 0
           || relationship.targets.length > constraints.minCardinality
           ) {
          removeTargetAllowed = true;
        }
        
        /*
         * Now retrieve the row that represents the relationship and iterate
         * over each of the displayed targets and modify them accordinly.
         */
        var table = dojo.byId("relationshiptable");
        var relationshipRow = dojo.byId(relationship.name);
        if (table && relationshipRow) {
          var startIndex = (addTargetAllowed) ? relationshipRow.rowIndex + 1 : relationshipRow.rowIndex;
          for ( var i = startIndex
              ; table.rows[i] && table.rows[i].id.indexOf(relationship.name) != -1
              ; i++) {

            /*
             * Retrieve the target cell for the current row and apply the
             * constraints
             */            
            var targetsCell = table.rows[i].cells[1];            
            var elements = targetsCell.getElementsByTagName("A");
         
            if (removeTargetAllowed && elements[elements.length-1].getAttribute("href").substr(0,23) != "javascript:removeTarget") {
              /* 
               * Targets can be removed but the remove link is not displayed
               * on the current row.  Add the remove link.
               */
              var divider="\u202a|\u202c";
              if (isRTL)
                divider="\u202b|\u202c";
              targetsCell.appendChild(document.createTextNode("\u00a0"+divider+"\u00a0"));
              var removeTargetAnchor = document.createElement("a");
              removeTargetAnchor.setAttribute("href", "javascript:removeTarget('" + table.rows[i].id + "')");
              removeTargetAnchor.setAttribute("title", removeTargetTitleTxt);
              removeTargetAnchor.appendChild(document.createTextNode(removeTxt));
              targetsCell.appendChild(removeTargetAnchor);
            } else if (!removeTargetAllowed && elements[elements.length-1].getAttribute("href").substr(0,23) == "javascript:removeTarget") {
              /* 
               * Targets cannot be removed but the remove link is displayed on
               * the current row.  Remove the remove link.
               */
              targetsCell.removeChild(targetsCell.lastChild);
              targetsCell.removeChild(targetsCell.lastChild);
            }
          } // FOR
        } // IF - table && relationshipRow
      } // IF - relationshipInfo
    } // IF - isBusinessModelRelationship(relationship.name)
  } // IF - relationship

  // Log exit from the method
  //console.debug("<<< applyConstraints ");
}

/** 
 * Deals with exceptions thrown from the JSON RPC stuff.
 */
function processRPCException(/*object*/e) {

  // Log entry to the method
  //console.debug(">>> processRPCException(", e, ")");
  
  // Make sure that we clear our deferred object
  if (deferredObj != null) {
  deferredObj = null;

  // Now clear any error message that might be displayed
  clearMessages();

  // Build up the error message  
  var msg = TRANS_ERROR_EXCEPTION + " " + e.message;
  if (e.fileName) {
    msg = msg + "; " + TRANS_SOURCE_FILE + " " + e.fileName;
  }
  if (e.lineNumber) {
    msg = msg + "; " + TRANS_FAILING_LINE + " " + e.lineNumber;
  }

  // Display the error message
  displayErrorMessage(msg, null);
  }
  // Log exit from the method
  //console.debug("<<< processRPCException ");
}

//edit a create generic object session
function editTarget(rowId) {

  /*
   * This action will navigate the user away from the current page.  In this
   * situation, we should cancel any callbacks that we may be waiting for.
   */
  if (deferredObj) {
    deferredObj.cancel();
    deferredObj = null;
  }

    // Make sure that the rowId is valid
    if (rowId) {

      // Extract the relationship name and bsrUri from the row id
      var elements = rowId.split("#");
      if (elements.length == 2) {
        var relationshipName = elements[0];
        var bsrUri = elements[1];
  
		  // Find the EditRelationshipsForm
		  var editRelationshipsForm = dojo.byId("EditRelationshipsForm");
		  if (editRelationshipsForm) {

		    /*
		     * Place the bsrUri of the source object into the form and the 
		     * userStorageCorrelator value.
		     */
		    var sourceObjectBsrUriInput = document.createElement("input");
		    sourceObjectBsrUriInput.type = "hidden";
		    sourceObjectBsrUriInput.name = "editRelationshipUri";
		    sourceObjectBsrUriInput.value = sourceObject.bsrUri;
		    editRelationshipsForm.appendChild(sourceObjectBsrUriInput);
		
		    var userStorageCorrelatorInput = document.createElement("input");
		    userStorageCorrelatorInput.type = "hidden";
		    userStorageCorrelatorInput.name = "userStorageCorrelator";
		    userStorageCorrelatorInput.value = userStorageCorrelator;
		    editRelationshipsForm.appendChild(userStorageCorrelatorInput);
		
		    /*
		     * Now retrieve the name of the relationship whose targets are being edited
		     * and place it in the form.  Also, retrieve the bsr uri of the target
		     * that is being changed (if any) and place it in the form.
		     */
		    var targetBsrUri = bsrUri;
		    
		    var relationshipNameInput = document.createElement("input");
		    relationshipNameInput.type = "hidden";
		    relationshipNameInput.name = "relationshipName";
		    relationshipNameInput.value = relationshipName;
		    editRelationshipsForm.appendChild(relationshipNameInput);
		    
		    /*
		     * Defect #: 5133
		     *
		     * Make sure that we have a valid targetBsrUri before creating the hidden
		     * input element in the form.  If we don't do this, IE sends the string
		     * "null" to the server as the value of the parameter, which breaks the
		     * server side parameter validation.
		     */
		    if (targetBsrUri) {
		      var targetBsrUriInput = document.createElement("input");
		      targetBsrUriInput.type = "hidden";
		      targetBsrUriInput.name = "oldTargetBsrUri";
		      targetBsrUriInput.value = targetBsrUri;
		      editRelationshipsForm.appendChild(targetBsrUriInput);
		    }
	    
		    // Modify the action to perform the search
		    editRelationshipsForm.action = contextRoot + "/EditRelationshipsEditPrepare.do";
		    editRelationshipsForm.submit();
	  }
	}// end if elements.length == 2
	}//end if rowId
}

/**
 * Handles user pressed create from the add relationship target panel.
 */

function addRelationshipTargetCreateButtonClicked(event) {

  // Log entry to the method
  //console.debug(">>> addRelationshipTargetLoadDocumentButtonClicked(", event, ")");

  /*
   * This action will navigate the user away from the current page.  In this
   * situation, we should cancel any callbacks that we may be waiting for.
   */
  if (deferredObj) {
    deferredObj.cancel();
    deferredObj = null;
  }
  
  // Find the EditRelationshipsForm
  var editRelationshipsForm = dojo.byId("EditRelationshipsForm");
  if (editRelationshipsForm) {

    /*
     * Place the bsrUri of the source object into the form and the 
     * userStorageCorrelator value.
     */
    var sourceObjectBsrUriInput = document.createElement("input");
    sourceObjectBsrUriInput.type = "hidden";
    sourceObjectBsrUriInput.name = "editRelationshipUri";
    sourceObjectBsrUriInput.value = sourceObject.bsrUri;
    editRelationshipsForm.appendChild(sourceObjectBsrUriInput);

    var userStorageCorrelatorInput = document.createElement("input");
    userStorageCorrelatorInput.type = "hidden";
    userStorageCorrelatorInput.name = "userStorageCorrelator";
    userStorageCorrelatorInput.value = userStorageCorrelator;
    editRelationshipsForm.appendChild(userStorageCorrelatorInput);

    /*
     * Now retrieve the name of the relationship whose targets are being edited
     * and place it in the form.  Also, retrieve the bsr uri of the target
     * that is being changed (if any) and place it in the form.
     */
    var relationshipName = currentWidget.getRelationshipName();
    var targetBsrUri = currentWidget.getTargetBsrUri();
    
    var relationshipNameInput = document.createElement("input");
    relationshipNameInput.type = "hidden";
    relationshipNameInput.name = "relationshipName";
    relationshipNameInput.value = relationshipName;
    editRelationshipsForm.appendChild(relationshipNameInput);
    
    /*
     * Defect #: 5133
     *
     * Make sure that we have a valid targetBsrUri before creating the hidden
     * input element in the form.  If we don't do this, IE sends the string
     * "null" to the server as the value of the parameter, which breaks the
     * server side parameter validation.
     */
    if (targetBsrUri) {
      var targetBsrUriInput = document.createElement("input");
      targetBsrUriInput.type = "hidden";
      targetBsrUriInput.name = "oldTargetBsrUri";
      targetBsrUriInput.value = targetBsrUri;
      editRelationshipsForm.appendChild(targetBsrUriInput);
    }
    
    // Modify the action to perform the search
    editRelationshipsForm.action = contextRoot + "/EditRelationshipsCreatePrepare.do";
    editRelationshipsForm.submit();
  }

  // Log exit from the method        
  //console.debug("<<< addRelationshipTargetLoadDocumentButtonClicked");
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
	
	var ret = 0;
	
	if (relTable != null && rowId != currentBsrUri) {
		var rowId = id;
		
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
