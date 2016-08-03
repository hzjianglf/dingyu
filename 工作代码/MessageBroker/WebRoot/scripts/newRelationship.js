/* begin_generated_IBM_copyright_prolog                            */
/* 
 * Licensed Materials - Property of IBM 
 * 
 * 5724-N72 5655-W17
 * 
 * (c) Copyright IBM Corp. 2008 All Rights Reserved 
 * 
 * US Government Users Restricted Rights - Use, duplication or 
 * disclosure restricted by GSA ADP Schedule Contract with 
 * IBM Corp. 
 */
/* end_generated_IBM_copyright_prolog                              */

/* load required packages */
dojo.registerModulePath("com.ibm.sr.widgets", "../widgets");
dojo.require("dijit.form.ComboBox");
dojo.require("dojo.data.ItemFileWriteStore");
dojo.require("com.ibm.sr.widgets.AddRelationshipTarget");
dojo.require("dojo.parser");

// Create an instance of the BaseObjectService
var baseObjectService = new dojo.rpc.JsonService(contextRoot + "/RPCAdapter/jsonrpc/BaseObjectService");

function setSelectionStyle(checkboxId) {
  var checkbox = dojo.byId(checkboxId);
  var tableId = getTableId(checkbox);
  var removeButton = getRemoveButton(tableId);
  
  if (checkbox.checked == true) {
    findParentRow(checkbox, "selected");
    
    /*
     * Only enable the remove button on the source entities table if there is
     * at least one row not selected.  We do not want the user to remove all
     * of the source entities... the new relationship must have at least one
     * source entity to be created on!!!
     */ 
    if (tableId == "sourceEntitiesTable") {
      if (hasAllRowsSelected(tableId) == false) {
        removeButton.disabled = false;
      } else {
        removeButton.disabled = true;
      }
    } else {
      removeButton.disabled = false;
    }
  } else {
    findParentRow(checkbox, "notselected");
    
    // Check to see if the table has any selected rows
    if (hasRowSelected(tableId) == true) {
      removeButton.disabled = false;
    } else {
      removeButton.disabled = true;
    }
  }
}

function getTableId(element) {
  var parent = element.parentNode;
  var id;
  if (parent.tagName == "TABLE" || parent.tagName == "table") {
    id = parent.id;
  } else {
    id = getTableId(parent);
  }

  return id;
}

function getRemoveButton(tableId) {
  var removeButton;
  if (tableId == "sourceEntitiesTable") {
    removeButton = dojo.byId("removeSourceEntityButton");
  } else if (tableId == "targetEntitiesTable") {
    removeButton = dojo.byId("removeTargetEntityButton");
  }
  return removeButton;
}

function hasRowSelected(tableId) {
  
  // Create the variable to return
  var rowSelected = false;
  
  // Retrieve the table with the specified id
  var table = dojo.byId(tableId);
  if (table) {

    /*
     * Now iterate over all of the rows table looking for any that have a
     * style of selected.  If we find a selected row, we can set our flag
     * and break from the loop.
     */
    for (var i = 0; i < table.rows.length; i++) {
      if (table.rows[i].className == "selected") {
        rowSelected = true;
        break;
      }
    } // FOR
  }
  
  return rowSelected;
}

function hasAllRowsSelected(tableId) {
  
  // Create the variable to return
  var allRowsSelected = true;
  
  // Retrieve the table with the specified id
  var table = dojo.byId(tableId);
  if (table) {

    /*
     * Now iterate over all of the rows table looking for any that have a
     * style of notselected.  If we find a row that is not selected, we can
     * set our flag and break from the loop.
     */
    for (var i = 0; i < table.rows.length; i++) {
      if (table.rows[i].className == "notselected") {
        allRowsSelected = false;
        break;
      }
    } // FOR
  }
  
  return allRowsSelected;
}

function selectAllRows(id) {

  // Retrieve the table with the specified id
  var table = dojo.byId(id);
  if (table) {
  
    /*
     * Now iterate over all of the input elements in the table... making sure
     * that each one is of type "checkbox".  Then, for each one, make sure that
     * it is checked and update the style accordingly.
     */
    var checkboxes = table.getElementsByTagName("input");
    var numRows = checkboxes.length;
    for (var i = 0; i < checkboxes.length; i++) {
      if (checkboxes[i].type == "CHECKBOX" || checkboxes[i].type == "checkbox") {
        checkboxes[i].checked = true;
        findParentRow(checkboxes[i], "selected");
      }
    } // FOR

    /*
     * Only enable the remove button on the target entities table since we do
     * not want the user to remove all of the source entities... the new
     * relationship must have at least one source entity to be created on!!!
     */
    var removeButton = getRemoveButton(id);
    if (id == "targetEntitiesTable") {
      /*
       * Check to see how many rows were found that contain checkboxes.  If
       * there were none, then we must be displaying the "none" row and,
       * therefore, we do not want to enable the remove button.
       */
      if (numRows > 0) {
        removeButton.disabled = false;
      }
    } else {
      removeButton.disabled = true;
    }
  } // IF - table
}

function deselectAllRows(id) {

  // Retrieve the table with the specified id
  var table = dojo.byId(id);
  if (table) {
  
    /*
     * Now iterate over all of the input elements in the table... making sure
     * that each one is of type "checkbox".  Then, for each one, make sure that
     * it is unchecked and update the style accordingly.
     */
    var checkboxes = table.getElementsByTagName("input");
    for (var i = 0; i < checkboxes.length; i++) {
      if (checkboxes[i].type == "CHECKBOX" || checkboxes[i].type == "checkbox") {
        checkboxes[i].checked = false;
        findParentRow(checkboxes[i], "notselected");
      }
    } // FOR
    
    // Now enable the remove button for this table
    var removeButton = getRemoveButton(id);
    removeButton.disabled = true;
  } // IF - table
}

function getSelectedItemIds(table) {

  // Create the variable to return
  var selectedItemIds = new Array();

  // Make sure that the table ref is valid
  if (table) {
  
    // Iterate over the checkboxes in the table
    var checkboxes = table.getElementsByTagName("input");
    for (var i = 0; i < checkboxes.length; i++) {
      if (checkboxes[i].type == "CHECKBOX" || checkboxes[i].type == "checkbox") {
      
        // See if the current checkbox is checked
        if (checkboxes[i].checked == true) {
        
          /*
           * Add the id to the return array.  The ids of all of the checkboxes
           * are prefixed with either "source" or "target"... both of which
           * are six characters long.
           */ 
          var id = checkboxes[i].id.substr(6);
          selectedItemIds.push(id);
        }
      }
    } // FOR
  }  // IF - table
  
  return selectedItemIds;
}

function removeSelectedRows(table) {
  
  // Make sure that the table ref is valid
  if (table) {

    // Retrieve the rows in the body of the table
    var rows = table.tBodies[0].rows;
    
    /* 
     * Iterate over the rows in the table and build up an array of all of the
     * rows that need to be removed.  We do this so that we are not modifying
     * the collection of objects that we are iterating over.
     */
    var rowsToRemove = new Array();
    for (var i = 0; i < rows.length; i++) {

      // See if the current row is selected
      if (rows[i].className == "selected") {
        rowsToRemove.push(rows[i]);
      }
    } // FOR
    
    /*
     * Now remove all of the relevant rows.  Set the height of the table body
     * to "auto" before we do this to force a redraw.  We can then determine
     * if we need to restrict the height back to 170px after the redraw.
     */
    table.tBodies[0].style.height = "auto";
    for (var j = 0; j < rowsToRemove.length; j++) {
      table.deleteRow(rowsToRemove[j].rowIndex);
    } // FOR
    
    // Set the correct classes for the cells in the last row, if it exists
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
    adjustTableSize(table);
  }  // IF - table
}

function removeSourceEntities() {

  // Retrieve the relevant table
  var table = dojo.byId("sourceEntitiesTable");
  if (table) {

    // Retrieve the array of selected item ids
    var selectedItemIds = getSelectedItemIds(table);

    // Invoke the relevant method on the BaseObjectService
    var deferredObj = baseObjectService.removeSourceEntitiesFromNewRelationship(userStorageCorrelator, selectedItemIds);
    deferredObj.addCallback(removeSourceEntitiesCallback);
    deferredObj.addErrback(processRPCException);
    
    // progress
	startProgress(SOURCE_PROGRESS_DIV_ID, TRANS_REMOVING);
  } // IF - table
}

function removeTargetEntities() {

  // Retrieve the relevant table
  var table = dojo.byId("targetEntitiesTable");
  if (table) {

    // Retrieve the array of selected item ids
    var selectedItemIds = getSelectedItemIds(table);

    // Create an instance of the BaseObjectService and invoke the relevant method
    var deferredObj = baseObjectService.removeTargetEntitiesFromNewRelationship(userStorageCorrelator, selectedItemIds);
    deferredObj.addCallback(removeTargetEntitiesCallback);
    deferredObj.addErrback(processRPCException);

    // progress
	startProgress(TARGET_PROGRESS_DIV_ID, TRANS_REMOVING);
  } // IF - table
}

function removeSourceEntitiesCallback(response) {

  // First clear any error message that might be displayed
  clearMessages();
  relationshipNameWidget.displayMessage();
  stopProgress();

  // Make sure that the response does not contain an error
  if (!response.error) {
  
    // Now store the new source entites array
    sourceObjects = response.data[0];
    
    // Retrieve the relevant table
    var table = dojo.byId("sourceEntitiesTable");

    // Remove the selected rows from the table
    removeSelectedRows(table);
    
    // Disable the remove button on the table
    var removeButton = getRemoveButton("sourceEntitiesTable");
    removeButton.disabled = true;
    
    // Revalidate the relationship name against the new 
    relationshipNameWidget.validate(true);
  } else { // IF = !reponse.error
    // Display the error in the messages portlet
    displayErrorMessage(response.error.message, response.error.error);
  }
}

function removeTargetEntitiesCallback(response) {

  // First clear any error message that might be displayed
  clearMessages();
  relationshipNameWidget.displayMessage();
  stopProgress();

  // Make sure that the response does not contain an error
  if (!response.error) {
  
    // Retrieve the relevant table
    var table = dojo.byId("targetEntitiesTable");

    // Remove the selected rows from the table
    removeSelectedRows(table);
    
    // Disable the remove button on the table
    var removeButton = getRemoveButton("targetEntitiesTable");
    removeButton.disabled = true;
    
    // Check to see if we need to recreate the "none" row
    var rows = table.tBodies[0].rows;
    if (rows.length == 0) {
    
      // Create a new row in the table
      var row = table.tBodies[0].insertRow(-1);
      row.id = "none";
      row.className = "notselected";
      var noneCell = row.insertCell(0);
      noneCell.colSpan = table.tHead.rows[0].cells.length;
      noneCell.innerHTML = noneTxt;
    }
  } else { // IF = !reponse.error
    // Display the error in the messages portlet
    displayErrorMessage(response.error.message, response.error.error);
  }
}

function addTargetEntityCallback(response) {

  // First clear any error message that might be displayed
  clearMessages();
  relationshipNameWidget.displayMessage();

  // stop any progress
  stopProgress();

  // Make sure that the response does not contain an error
  if (!response.error) {
    
    /*
     * We are expecting a single WSRRObject in the data element of the
     * response.  We need to extract the information contained in this object 
     * and use it to create a new row in the target entities table.
     */
    var properties = response.data[0].properties;
    var wsrrObject = new Object();
    for (var i = 0; i < properties.length; i++) {
      wsrrObject[properties[i].name] = properties[i].value;
    } // FOR
    
    // Retrieve the target entities table
    var table = dojo.byId("targetEntitiesTable");
    if (table) {
    
      // Check to see if the table is currently displaying the "none" row
      var rows = table.tBodies[0].rows;
      if (rows.length == 1 && rows[0].id == "none") {
        // Remove the "none" row from the table
        table.tBodies[0].deleteRow(0);
      } else {
        /*
         * We need to modify the styles of the current last row to make sure
         * that the cells render correctly when a row is added below.
         */
         var lastRow = rows[rows.length - 1];
         var cells = lastRow.cells;
         for (var i = 0; i < cells.length; i++) {
           if (i != cells.length - 1) {
             cells[i].className = "centerleft";
           } else {
             cells[i].className = "right";
           }
         } // FOR
      }
    
      // Create a new row in the table
      var row = table.tBodies[0].insertRow(-1);
      row.className = "notselected";
      
      /*
       * Now create all of the relevant cells within the new row.  We start
       * with the most complex cell which contains the selection checkbox for
       * row.
       */
      var selectCell = row.insertCell(0);
      selectCell.width = "1%";
      selectCell.className = "bottomleft";
      var label = document.createElement("label");
      label.htmlFor = wsrrObject.bsrURI;
      label.title = selectTxt + ": " + wsrrObject.name;
      var input = document.createElement("input");
      input.type = "checkbox";
      input.name = "selectedItemIds";
      input.value = wsrrObject.bsrURI;
      input.id = "target" + wsrrObject.bsrURI;
      dojo.connect(input, "onclick", function() {setSelectionStyle(input.id)});
      dojo.connect(input, "onkeypress", function() {setSelectionStyle(input.id)});
      label.appendChild(input);
      selectCell.appendChild(label);

      // Name cell
      var nameCell = row.insertCell(1);
      nameCell.className = "bottomleft";
      nameCell.innerHTML = ((wsrrObject.name == "") ? "&nbsp;" : wsrrObject.name);

      // Description cell
      var descriptionCell = row.insertCell(2);
      descriptionCell.className = "bottomleft";
      descriptionCell.innerHTML = ((wsrrObject.description == "") ? "&nbsp;" : wsrrObject.description);

      // Object type cell
      var typeCell = row.insertCell(3);
      typeCell.className = "bottomleft";
      typeCell.innerHTML = ((wsrrObject.__typeDisplayName == "") ? "&nbsp;" : wsrrObject.__typeDisplayName);

      // Version cell
      var versionCell = row.insertCell(4);
      versionCell.className = "bottomright";
      versionCell.innerHTML = ((wsrrObject.version == "") ? "&nbsp;" : wsrrObject.version);
      
      // Adjust the size of the table as required
      adjustTableSize(table);
    } // IF - table
  } else { // IF = !reponse.error
    // Display the error in the messages portlet
    displayErrorMessage(response.error.message, response.error.error);
  }

  // Reset the add relationship target widget
  addRelationshipTargetWidget.reset();
}

function initializeRelationshipNameWidget() {

  /*
   * The first thing to do is to populate the widget with the names of all of
   * the user defined relationships that have defined on objects within WSRR.
   */
  if (udrNames.length > 0) {
    
    /*
     * The relationshipNameWidget is a Dijit ComboBox.  This type of widget
     * is backed by a dojo.data data store.  In order to populate the widget
     * with options we need to create a data store on the fly and set it on
     * the widget.
     */
    var options = new dojo.data.ItemFileWriteStore({data: {identifier: "name", items:[]}});
    for (var i = 0; i < udrNames.length; i++) {
      options.newItem({name: udrNames[i]});
    } // FOR
    relationshipNameWidget.store = options;
  } // IF - udrNames.length > 0

  /*
   * Listen for changes to the relationship name in the relationshipNameWidget.
   * In the case of key presses, we need to delay the validation of the 
   * relationship name.  When a suggested relationship name is selected from
   * the drop down, however, we can validate the relationship name immediately.
   * Selections in the drop down list drive the onChange event of the ComboBox
   * widget, the handler for which is configured by setting the onChange
   * attribute in the widget markup.
   */
  relationshipNameWidget.connect(relationshipNameWidget.focusNode, "onkeypress", onRelationshipNameChanged);  
  relationshipNameWidget.validate(true); // Specify isFocused = true to force prompt message to be displayed
}

/*
 * The Dojo ComboBox widget has some strange behaviour because it attempts
 * to autocomplete the users input against the list of options that have been
 * defined for the widget.  However, it uses a timeout to delay the search, so
 * retrieving the value from the widget immediately does not return the
 * correct value.  For this reason, we also need to delay our validation
 * to give the ComboBox a chance to put the correct value in its textbox.
 */
var relationshipNameTimer = null;
function onRelationshipNameChanged() {

  // Clear any validation messages that are currently displayed
//  relationshipNameWidget.displayMessage();

  if(relationshipNameTimer){
    clearTimeout(relationshipNameTimer);
    relationshipNameTimer = null;
  }
  // ComboBox uses a search delay of 100 milliseconds... use the same delay
  relationshipNameTimer = setTimeout(function(){relationshipNameWidget.validate(true)}, 250);
}

function initializeAddRelationshipTargetWidget() {

  /*
   * We create the AddRelationshipTarget widget declaratively in the markup
   * for the page.  This means that we are unable to pass any args to the
   * constructor and need to set the auto-suggest search types and saved
   * search names on page load.
   *
   * First, initialize the search types for the auto-suggest widget. The
   * setTypes function is expecting to be passed an array of Option objects.
   * Create an Option object for each search type and add it to an options
   * array.
   */
  var searchTypeOptions = new Array();
  if (searchTypes && searchTypes.length > 0) {
    for (var i = 0; i < searchTypes.length; i++) {
      var option = new Option(searchTypes[i].displayName, searchTypes[i].id);
      searchTypeOptions.push(option);
    } // FOR
    
    // Now set the types on the widget    
    addRelationshipTargetWidget.setTypes(searchTypeOptions);
  }

  /*
   * Now initialize the list of saved searches for the add relationship target
   * widget.
   */
  addRelationshipTargetWidget.setDisplaySavedSearches(true);
  var savedSearchOptions = new Array();
  if (savedSearches && savedSearches.length > 0) {
    /*
     * The saved searches are displayed in a drop down list box within
     * the add relationship target widget.  We need to construct
     * Option objects to pass to this widget in order to populate the
     * list box.
     */
    for (var i = 0; i < savedSearches.length; i++) {
      var option = new Option(savedSearches[i].name, savedSearches[i].bsrUri);
      savedSearchOptions.push(option);
    } // FOR
    
    // Now set the saved searches on the widget    
    addRelationshipTargetWidget.setSavedSearches(savedSearchOptions);
  } // IF - savedSearches && savedSearches.length > 0
}

function adjustTableSize(table) {
  if (table) {
    var tbody = table.tBodies[0];
    if (document.defaultView) {
      var height = document.defaultView.getComputedStyle(tbody, null).getPropertyValue("height");
      height = height.substring(0, height.length - 2);
      if (height > 170) {
        tbody.style.height = "170px";
      } // IF - style > 170
    } // IF - document.defaultView
  } // IF - table
}

function adjustEntityTableSizes() {
  var table = dojo.byId("sourceEntitiesTable");
  adjustTableSize(table);
  var table = dojo.byId("targetEntitiesTable");
  adjustTableSize(table);
}

/** 
 * Deals with exceptions thrown from the JSON RPC stuff.
 */
function processRPCException(/*object*/e) {

  // Log entry to the method
  //console.debug(">>> processRPCException(", e, ")");
  
  // Make sure that we clear our deferred object
  deferredObj = null;

  // Now clear any error message that might be displayed
  clearMessages();
  
  // stop any progress
  stopProgress();

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

  // Log exit from the method
  //console.debug("<<< processRPCException ");
}

dojo.addOnLoad(initializeRelationshipNameWidget);
dojo.addOnLoad(initializeAddRelationshipTargetWidget);
dojo.addOnLoad(adjustEntityTableSizes);
