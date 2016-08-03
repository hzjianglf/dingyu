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

dojo.provide("com.ibm.sr.widgets.AddRelationshipTarget");

dojo.require("dojo.i18n");

dojo.require("dijit._Widget");
dojo.require("dijit._Templated");

dojo.require("dojox.string.Builder");

dojo.require("com.ibm.sr.widgets.AutoSuggest");

dojo.requireLocalization("com.ibm.sr.widgets.AddRelationshipTarget", "AddRelationshipTargetResources");

dojo.declare("com.ibm.sr.widgets.AddRelationshipTarget", [dijit._Widget, dijit._Templated],{

	// The path to the widgets template
	templatePath: dojo.moduleUrl("com.ibm.sr.widgets.AddRelationshipTarget", "AddRelationshipTarget.html"),
	widgetsInTemplate: true,
	
	// width: String
	//		Width of editor.  By default it's width=100% (ie, block mode)
	width: "100%",
	
	// The name attribute for the widget
	name: "",
	
	// The context root for the service registry application
	contextRoot: "/ServiceRegistry",

    // The valid search types for the embedded auto-suggest widget
    searchTypes: null,

    // The valid saved searches
    savedSearches: null,
    
    // the auto-suggest target bsrURI in the case of a single match
    suggestTargetBsrUri: null,
	
    /**
     * Define the hashtable of static configuration properties for the widget.
     */
    statics: {
    	msgResourceKeys : [ "textTitleAddTarget"
                          , "textTitleChangeTarget"
                          , "textName"
                          , "textEntityType"
                          , "textSavedSearch"
                          , "buttonSearch"
                          , "buttonAdd"
                          , "buttonChange"
                          , "allEntitiesSearchType"
                          ],
    	RADIO_BUTTON_ENABLED_STYLE : "property notmandatory radiochoice",
    	RADIO_BUTTON_DISABLED_STYLE : "property notmandatory disabledradiochoice",
    	TEXT_COLOR_ENABLED : "#000000",
    	TEXT_COLOR_DISABLED : "#CCCCCC",
        BASE_OBJECT : "BaseObject"
    },

    constructor: function (args) {

		// Log entry to the method
    	//console.debug(">>> constructor");

        // Check to see if we were passed some args
        if (args) {
        
            // Initialize the context root
            if (args.contextRoot) {
                this.contextRoot = args.contextRoot;
            } else {
                this.contextRoot = "/ServiceRegistry";
            }

            // Initialize the search types
            if (args.searchTypes) {
                this.searchTypes = args.searchTypes;
            } else {
                this.searchTypes = null;
            }

            // Initialize the display saved searches flag
            if (args.displaySavedSearches) {
                this.displaySavedSearches = args.displaySavedSearches;
            } else {
                this.displaySavedSearches = false;
            }

            // Initialize the saved searches
            if (args.savedSearches) {
                this.savedSearches = args.savedSearches;
            } else {
                this.savedSearches = null;
            }
        } else { // IF - args
            this.contextRoot = "/ServiceRegistry";
            this.searchTypes = null;
            this.displaySavedSearches = false;
            this.savedSearches = null;
        }

		// Initialize the label attributes for the widget
		this.messages = dojo.i18n.getLocalization("com.ibm.sr.widgets.AddRelationshipTarget", "AddRelationshipTargetResources");
		dojo.forEach(this.statics.msgResourceKeys, function(prop){
			if(!this[prop]){ this[prop] = this.messages[prop]; }
		}, this);

        // Default to add mode
        this.targetBsrUri = null;
        this.textTitle = this.textTitleAddTarget;

		// Log exit from the method        
    	//console.debug("<<< constructor");
    },

    /**
     * The postCreate method is invoked after the widget has been fully
     * initialized.  We defer most of the initialization code to this point
     * to ensure that the attach points have been properly initialized by the
     * underlying dojo template code
     */
    postCreate: function() {
    
        // Log entry to the method
        //console.debug(">>> postCreate");

        /*
         * Check to see if we have been passed some search types for the auto
         * suggest widget.  The setTypes function is expecting to be passed an
         * array of Option objects.  Create an Option object for each search
         * type and add it to an options array.
         */
        var searchTypeOptions = new Array();
        if (!this.searchTypes || this.searchTypes.length == 0) {
          this.searchTypes = [ {"displayName": this.allEntitiesSearchType, "id": "BaseObject"} ];
        }
        for (var i = 0; i < this.searchTypes.length; i++) {
            var option = new Option(this.searchTypes[i].displayName, this.searchTypes[i].id);
            searchTypeOptions.push(option);
        } // FOR

        // Now set the types on the widget    
        this.setTypes(searchTypeOptions);

        /*
         * Check the displaySavedSearches flag to see if we should bother
         * populating the list of saved searches.
         */
        if (this.displaySavedSearches) {

            /*
             * Now initialize the list of saved searches in the widget and
             * enable/disable the relevant radio button accordingly.
             */
            var savedSearchOptions = new Array();
            if (this.savedSearches && this.savedSearches.length > 0) {
                /*
                 * The saved searches are displayed in a drop down list box within
                 * the add relationship target widget.  We need to construct
                 * Option objects to pass to this widget in order to populate the
                 * list box.
                 */
                for (var i = 0; i < this.savedSearches.length; i++) {
                    var option = new Option(this.savedSearches[i].name, this.savedSearches[i].bsrUri);
                    savedSearchOptions.push(option);
                } // FOR
            } // IF - savedSearches && savedSearches.length > 0
    
            // Now set the saved searches on the widget    
            this.setSavedSearches(savedSearchOptions);
        } else { // IF - this.displaySavedSearches

            // Hide the saved search div
            this.savedSearchDiv.style.display = "none";
            
            /*
             * Hide the entity name radio button and modify the style of the
             * custom search div.
             */ 
            this.entityNameButton.style.display = "none";
            this.customSearchDiv.className = "property notmandatory";
        }

        // Set the style of the button group div
        this.buttonGroupDiv.className = "widgetbuttongroup";

        // Default to add mode
        this.addButton.style.display = "";
        this.changeButton.style.display = "none";

        /*
         * Connect to the onSuggestionSelected event from the AutoSuggest
         * widget.  We do this rather than overriding the function so that
         * have access to the attributes of this widget in the event handler
         * when the event fires.
         */ 
        dojo.connect(this.autoSuggestWidget, "onSuggestionSelected", this, "_onSuggestionSelected");
        dojo.connect(this.autoSuggestWidget, "onSearchStringChanged", this, "_onSearchStringChanged");

        // Log exit from the method        
        //console.debug("<<< postCreate");
    },

    /**
     * Returns the bsr uri of the target object.  This will only return a
     * valid bsr uri if this widget instance is in "Change Target" mode.
     */
    getTargetBsrUri: function() {

        // Log entry to the method
        //console.debug(">>> getTargetBsrUri");

        // Log exit from the method        
        //console.debug("<<< getTargetBsrUri ", this.targetBsrUri);
        
        return this.targetBsrUri;
    },

    /**
     * Set the bsr uri of the target object.  This will only return a
     * valid bsr uri if this widget instance is in "Change Target" mode.
     */
    setTargetBsrUri: function(targetBsrUri) {

        // Log entry to the method
        //console.debug(">>> setTargetBsrUri(", targetBsrUri, ")");

        this.targetBsrUri = targetBsrUri;
        if (this.targetBsrUri) {
            this.textTitle = this.textTitleChangeTarget;
            this.addButton.style.display = "none";
            this.changeButton.style.display = "";
        } else {
            this.textTitle = this.textTitleAddTarget;
            this.addButton.style.display = "";
            this.changeButton.style.display = "none";
        }

        this.fieldsetLegend.setAttribute("title", this.textTitle);
        this.fieldsetLegend.innerHTML = this.textTitle;

        // Log exit from the method        
        //console.debug("<<< setTargetBsrUri");
    },

    /**
     * Returns the current content of the auto-suggest widget.
     */
    getTargetEntityName: function() {

        // Log entry to the method
        //console.debug(">>> getTargetEntityName");

        // Log exit from the method        
        //console.debug("<<< getTargetEntityName ", this.autoSuggestWidget.getValue());
        
        return this.autoSuggestWidget.getValue();
    },

    /*
     * The getTargetEntityType function returns the currently selected entity
     * type in the entity type drop down list.
     */
    getTargetEntityType: function() {

        // Log entry to the method
        //console.debug(">>> getTargetEntityType");
        
        var entityType = this.entityTypeList.options[this.entityTypeList.selectedIndex].value;

        // Log exit from the method        
        //console.debug("<<< getTargetEntityType ", entityType);
        
        return entityType;
    },

    /**
     * Returns the bsrURI of a single match auto-suggest result, null otherwise.
     */
    getSuggestTargetBsrUri: function() {
        return this.suggestTargetBsrUri;
    },

    /*
     * The getSavedSearchBsrUri function returns the name of the currently
     * selected saved search in the saved search drop down list.
     */
    getSavedSearchBsrUri: function() {

        // Log entry to the method
        //console.debug(">>> getSavedSearchBsrUri");
        var selected = this.savedSearchList.selectedIndex;
        var savedSearch = "";
        if(selected != -1) {
        	savedSearch = this.savedSearchList.options[selected].value;
        }
        
        // Log exit from the method        
        //console.debug("<<< getSavedSearchBsrUri ", savedSearch);
        
        return savedSearch;
    },

	/*
	 * The setTypes function sets the list of types that are available in the
	 * "Entity Type" combo box.  By default, this combo box will only contain
     * the "All Entities" label which maps to a search type in the
     * auto-suggest widget of "BaseObject".
	 */
	setTypes: function(/*Option[]*/ types){

		// Log entry to the method
    	//console.debug(">>> setTypes(", types, ")");

        // Make sure that the array of options is valid
        if (types && types.length > 0) {
        
    		/*
             * Iterate over the types adding each one to the entity type list.
             * Also, make sure that we set the length for the list element to
             * the length of the types array.
             */ 
            this.entityTypeList.length = types.length;
            for (var i = 0; i < types.length; i++) {
                /*
                 * Simply assign the Option object into the options array for the
                 * select element.  We do not use the add method here because
                 * there are cross-browser compatibility issues.
                 */
                this.entityTypeList.options[i] = types[i];
                this.entityTypeList.options[i].title=this.entityTypeList.options[i].text;
            } // FOR
        } else { // IF - types && types.length > 0
            // Default to "All Entities"
            var option = new Option(this.allEntitiesSearchType, this.statics.BASE_OBJECT);
            this.entityTypeList.length = 1;
            this.entityTypeList.options[0] = option;
            this.entityTypeList.options[0].title=this.entityTypeList.options[0].text;
        }
        
        /*
         * Finally, set the search type on the auto-suggest widget to the
         * first option in the list.
         */
        var entityType = this.entityTypeList.options[0].value;
        if (entityType) {
            this.autoSuggestWidget.setType(entityType);
        }
		
		// Log exit from the method        
    	//console.debug("<<< setTypes");
	},

    /*
     * The setDisplaySavedSearches function sets the value of the
     * displaySavedSearches flag within this instance of the widget.  This flag
     * controls whether the list of saved searches will be displayed to the
     * user.  Saved searches should not be displayed to the user when they are
     * adding targets to a typed relationship.
     */
    setDisplaySavedSearches: function(/*boolean*/ displaySavedSearches) {

        // Log entry to the method
        //console.debug(">>> setDisplaySavedSearches(", displaySavedSearches, ")");
        
        // Store the flag value passed to us
        this.displaySavedSearches = displaySavedSearches;

        // Take the appropriate action
        if (this.displaySavedSearches) {
            // Show the saved search div
            this.savedSearchDiv.style.display = "block";
            
            /*
             * Show the entity name radio button and modify the style of the
             * custom search div.
             */ 
            this.entityNameButton.style.display = "inline";
            this.customSearchDiv.className = "property notmandatory radiochoice";
        } else {
            // Hide the saved search div
            this.savedSearchDiv.style.display = "none";
            
            /*
             * Hide the entity name radio button and modify the style of the
             * custom search div.
             */ 
            this.entityNameButton.style.display = "none";
            this.customSearchDiv.className = "property notmandatory";
        }
        
        // Log exit from the method        
        //console.debug("<<< setDisplaySavedSearches");
    },

	/*
	 * The setSavedSearches function sets the list of saved searches that are
	 * available in the "Saved Search" combo box.
	 */
	setSavedSearches: function(/*Option[]*/ savedSearches) {

		// Log entry to the method
    	//console.debug(">>> setSavedSearches(", savedSearches, ")");

		// Make sure that the array contains some elements
		if (savedSearches.length > 0) {	

			// Enable the saved search radio button
			this.savedSearchButton.disabled = false;

			/*
			 * Iterate over the saved searches adding each one to the saved
			 * searches list 
			 */
			for (var i = 0; i < savedSearches.length; i++) {
				/*
				 * Simply assign the Option object into the options array for the
				 * select element.  We do not use the add method here because
				 * there are cross-browser compatibility issues.
				 */
				this.savedSearchList.options[i] = savedSearches[i];
			} // FOR
		} else {
			// Disable the saved search radio button
			this.savedSearchButton.disabled = true;
		}
		
		// Log exit from the method        
    	//console.debug("<<< setSavedSearches");
	},

	/*
	 * Handles the Search button being clicked within the widget
	 */
	onSearchButtonClicked: function(event) {

		// Log entry to the method
    	//console.debug(">>> onSearchButtonClicked(", event, ")");
    	

		// Log exit from the method        
    	//console.debug("<<< onSearchButtonClicked");
	},
	
	/*
	 * Handles the Add button being clicked within the widget
	 */
	onAddButtonClicked: function(event) {

		// Log entry to the method
    	//console.debug(">>> onAddButtonClicked(", event, ")");
    	

		// Log exit from the method        
    	//console.debug("<<< onAddButtonClicked");
	},
	
    /*
     * Handles the Change button being clicked within the widget
     */
    onChangeButtonClicked: function(event) {

        // Log entry to the method
        //console.debug(">>> onChangeButtonClicked(", event, ")");
        

        // Log exit from the method        
        //console.debug("<<< onChangeButtonClicked");
    },
    
	/*
	 * Handles changes to the search string in the auto-suggest widget
	 */
	_onSearchStringChanged: function(searchString) {
		
		// Log entry to the method
    	//console.debug(">>> _onSuggestStringChanged(", searchString, ")");
    	
		// Always disable the add button    	
  		this.addButton.disabled = true;
        this.changeButton.disabled = true;
      // reset the suggest bsrURI since the search result is no longer matching
      this.suggestTargetBsrUri=null;
    
    	// Check to see if there is any text in the query field
		if (searchString == "") {
		
			// Disable the search button
    		this.searchButton.disabled = true;
		} else {
		    // Enable the search button
    		this.searchButton.disabled = false;
		}

		// Log exit from the method        
    	//console.debug("<<< _onSuggestStringChanged");
	},	
	
	/*
	 * Handles a click on one of the radio buttons within the widget
	 */
	_onRadioButtonClick: function(event) {
	
		// Log entry to the method
    	//console.debug(">>> _onRadioButtonClick(", event, ")");
    	
    	if (this.entityNameButton.checked == true) {
    		// Enable the relevant controls
    		this.autoSuggestWidget.setDisabled(false);
    		this.entityTypeList.disabled = false;
    		this.customSearchDiv.className = this.statics.RADIO_BUTTON_ENABLED_STYLE;

    		// Disable the relevant controls
    		this.savedSearchList.disabled = true;
    		this.savedSearchDiv.className = this.statics.RADIO_BUTTON_DISABLED_STYLE;
    		
    		/* 
    		 * Auto-suggest widget should have been cleared when the saved
    		 * search radio button was selected... so disable the search button
    		 * until some text is entered.
    		 */
			this.searchButton.disabled = true; 
    	} else if (this.savedSearchButton.checked == true) {
    		// Clear the contents of the auto suggest widget
    		this.autoSuggestWidget.clear();
    	
    		// Enable the relevant controls
    		this.savedSearchList.disabled = false;
    		this.savedSearchDiv.className = this.statics.RADIO_BUTTON_ENABLED_STYLE;

    		// Disable the relevant controls
    		this.autoSuggestWidget.setDisabled(true);
    		this.entityTypeList.disabled = true;
    		this.customSearchDiv.className = this.statics.RADIO_BUTTON_DISABLED_STYLE;
    		this.addButton.disabled = true;
            this.changeButton.disabled = true;
    		
    		/*
    		 * Check to see if the saved search list control actually contains
    		 * any options... there may be no saved searches in the system!!!
    		 */
    		if (this.entityTypeList.options.length == 0) {
				this.searchButton.disabled = true; 
    		} else {
				this.searchButton.disabled = false; 
    		}
    	}

		// Log exit from the method        
    	//console.debug("<<< _onRadioButtonClick");
	},
	
	/*
	 * This function is used to override the onSuggestionSelected function
	 * defined by the AutoSuggest widget.
	 */
	_onSuggestionSelected : function(suggestion) {
	
		// Log entry to the method
    	//console.debug(">>> _onSuggestionSelected(", suggestion, ")");

		/*
		 * Check to see if the seleted suggestion matches more than one object
		 * in WSRR.  If it only matches one object, enable the Add button.  If
		 * it matches more than one object, disable the Add button.  Also,
		 * always enable the search button... even if there is only one match.
		 */
		this.searchButton.disabled = false;
		if (suggestion.count == 1) {
			this.addButton.disabled = false;
      this.changeButton.disabled = false;
      this.suggestTargetBsrUri=suggestion.bsrUri;
		} else {
			this.addButton.disabled = true;
      this.changeButton.disabled = true;
      this.suggestTargetBsrUri=null;
		}

		// Log exit from the method        
    	//console.debug("<<< _onSuggestionSelected");
	},
	
	_onEntityTypeChanged : function(event) {

		// Log entry to the method
    	//console.debug(">>> _onEntityTypeChanged(", event, ")");
    	
    	// Retrieve the selected entity type from the list
		var entityType = this.entityTypeList.options[this.entityTypeList.selectedIndex].value;
		if (entityType) {
    		this.autoSuggestWidget.setType(entityType);
		}
		
		// Log exit from the method        
    	//console.debug("<<< _onEntityTypeChanged");
	},
	
	reset : function() {
	
		// Log entry to the method
    	//console.debug(">>> reset");
    	
    	// Reset all of the fields for the control
		this.entityNameButton.checked = true;
		this.savedSearchButton.checked = false;
		this.autoSuggestWidget.setDisabled(false);
		this.autoSuggestWidget.clear();
   		this.entityTypeList.disabled = false;
   		this.customSearchDiv.className = this.statics.RADIO_BUTTON_ENABLED_STYLE;
   		this.savedSearchList.disabled = true;
   		this.savedSearchDiv.className = this.statics.RADIO_BUTTON_DISABLED_STYLE;
   		this.addButton.disabled = true;
        this.changeButton.disabled = true;
   		this.searchButton.disabled = true;
      this.suggestTargetBsrUri=null;
    	
		// Log exit from the method        
    	//console.debug("<<< reset");
	}
});
