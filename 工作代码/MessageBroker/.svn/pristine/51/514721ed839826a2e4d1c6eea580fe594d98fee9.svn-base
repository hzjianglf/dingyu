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

dojo.provide("com.ibm.sr.widgets.AddOrLoadRelationshipTarget");

dojo.require("dojo.i18n");

dojo.require("dijit._Widget");
dojo.require("dijit._Templated");

dojo.require("com.ibm.sr.widgets.AddRelationshipTarget");

dojo.requireLocalization("com.ibm.sr.widgets.AddOrLoadRelationshipTarget", "AddOrLoadRelationshipTargetResources");

dojo.declare("com.ibm.sr.widgets.AddOrLoadRelationshipTarget", [dijit._Widget, dijit._Templated],{

	// The path to the widgets template
	templatePath: dojo.moduleUrl("com.ibm.sr.widgets.AddOrLoadRelationshipTarget", "AddOrLoadRelationshipTarget.html"),
	widgetsInTemplate: true,
	
	// width: String
	//		Width of editor.  By default it's width=100% (ie, block mode)
	width: "100%",
	
	// The name attribute for the widget
	name: "",
	
    /**
     * Define the hashtable of static configuration properties for the widget.
     */
    statics: {
    	msgResourceKeys : [ "textTitleAddTarget"
                          , "textTitleChangeTarget"
                          , "textDescription"
                          , "textLoadDocument"
                          , "textDocumentType"
                          , "buttonLoadDocument"
                          , "buttonCancel"
                          ]
    },

    constructor: function (args) {

		// Log entry to the method
    	//console.debug(">>> constructor");

        // Check to see if we were passed some args
        if (args) {

            // Initialize the relationship name
            if (args.relationshipName) {
                this.relationshipName = args.relationshipName;
            } else {
                this.relationshipName = null;
            }

            // Initialize the target bsr uri
            if (args.targetBsrUri) {
                this.targetBsrUri = args.targetBsrUri;
            } else {
                this.targetBsrUri = null;
            }

            // Initialize the relationship display name
            if (args.displayName) {
                this.displayName = args.displayName;
            } else {
                this.displayName = this.relationshipName;
            }
           
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
            this.relationshipName = null;
            this.targetBsrUri = null;
            this.displayName = null;
            this.contextRoot = "/ServiceRegistry";
            this.searchTypes = null;
            this.displaySavedSearches = false;
            this.savedSearches = null;
        }

		// Initialize the label attributes for the widget
		this.messages = dojo.i18n.getLocalization("com.ibm.sr.widgets.AddOrLoadRelationshipTarget", "AddOrLoadRelationshipTargetResources");
		dojo.forEach(this.statics.msgResourceKeys, function(prop){
			if(!this[prop]){ this[prop] = this.messages[prop]; }
		}, this);

        this.textTitle = this.textTitleAddTarget;

        // Put the relationship name into the description
        var subs = [this.displayName];
        this.textDescription = dojo.string.substitute(this.textDescription, subs);
        
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

        // Set the bsr uri of the target object on the add relationship widget
        this.addRelationshipTargetWidget.setTargetBsrUri(this.targetBsrUri);

        /*
         * Check to see if we have been passed some search types for the auto
         * suggest widget and the load document control.  The setTypes function
         * is expecting to be passed an array of Option objects.  Create an
         * Option object for each search type and add it to an options array.
         * Also create an array of document type options.
         */
        var searchTypeOptions = new Array();
        var documentTypeOptions = new Array();
        if (!this.searchTypes || this.searchTypes.length == 0) {
          this.searchTypes = [ {"displayName": this.allEntitiesSearchType, "id": "BaseObject"} ];
        }
        for (var i = 0; i < this.searchTypes.length; i++) {
            var option = new Option(this.searchTypes[i].displayName, this.searchTypes[i].id);
            searchTypeOptions.push(option);
            if (  this.searchTypes[i].id == "WSDLDocument"
               || this.searchTypes[i].id == "XSDDocument"
               || this.searchTypes[i].id == "XMLDocument"
               || this.searchTypes[i].id == "PolicyDocument"
               || this.searchTypes[i].id == "GenericDocument"
               || this.searchTypes[i].id == "SCAModule"
               ) {
               documentTypeOptions.push(option);
            }
        } // FOR
		
        // Set the search types on the widget    
        this.setTypes(searchTypeOptions);

        // Set the document types on the document types list
        this.setDocumentTypes(documentTypeOptions);
        
        /*
         * Check the displaySavedSearches flag to see if we should bother
         * populating the list of saved searches.
         */
        if (this.displaySavedSearches) {

            // Set the add relationship target widget to display saved searches
            this.addRelationshipTargetWidget.setDisplaySavedSearches(true);

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
            // Set the add relationship target widget to not display saved searches
            this.addRelationshipTargetWidget.setDisplaySavedSearches(false);
        }

        /*
         * Connect to the onSearchButtonClicked, onAddButtonClicked and
         * onChangeButtonClicked functions from the add relationship target
         * widget.  We do this rather than overriding the function because the
         * relevant methods on this widget may also be replaced.
         */ 
        dojo.connect(this.addRelationshipTargetWidget, "onSearchButtonClicked", this, "onSearchButtonClicked");
        dojo.connect(this.addRelationshipTargetWidget, "onAddButtonClicked", this, "onAddButtonClicked");
        dojo.connect(this.addRelationshipTargetWidget, "onChangeButtonClicked", this, "onChangeButtonClicked");

        // Log exit from the method
        //console.debug("<<< postCreate");
    },

    /*
     * The getRelationshipName function returns the name of the relationship
     * whose targets are currently being edited.
     */
    getRelationshipName: function() {

        // Log entry to the method
        //console.debug(">>> getRelationshipName");

        // Log exit from the method        
        //console.debug("<<< getRelationshipName", this.relationshipName);
        
        return this.relationshipName;
    },

    /**
     * Returns the bsr uri of the target object.  This will only return a
     * valid bsr uri if this widget instance is in "Change Target" mode.
     */
    getTargetBsrUri: function() {

        // Log entry to the method
        ////console.debug(">>> getTargetBsrUri");

        // Log exit from the method        
        ////console.debug("<<< getTargetBsrUri ", this.targetBsrUri);
        
        return this.targetBsrUri;
    },

    /**
     * Returns the current content of the auto-suggest widget.
     */
    getTargetEntityName: function() {

        // Log entry to the method
        //console.debug(">>> getTargetEntityName");

        // Log exit from the method        
        //console.debug("<<< getTargetEntityName ", this.addRelationshipTargetWidget.getTargetEntityName());
        
        return this.addRelationshipTargetWidget.getTargetEntityName();
    },

    /*
     * The getTargetEntityType function returns the currently selected entity
     * type in the entity type drop down list.
     */
    getTargetEntityType: function() {

        // Log entry to the method
        //console.debug(">>> getTargetEntityType");
        
        var entityType = this.addRelationshipTargetWidget.getTargetEntityType();

        // Log exit from the method        
        //console.debug("<<< getTargetEntityType ", entityType);
        
        return entityType;
    },

    /**
     * Returns the bsrURI of a single match auto-suggest result, null otherwise.
     */
    getSuggestTargetBsrUri: function() {
        return this.addRelationshipTargetWidget.getSuggestTargetBsrUri();
    },

    /*
     * The getSavedSearchBsrUri function returns the name of the currently
     * selected saved search in the saved search drop down list.
     */
    getSavedSearchBsrUri: function() {

        // Log entry to the method
        //console.debug(">>> getSavedSearchBsrUri");
        
        var savedSearch = this.addRelationshipTargetWidget.getSavedSearchBsrUri();
        
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

        // Simply call down to the embedded widget
        this.addRelationshipTargetWidget.setTypes(types);
		
		// Log exit from the method        
    	//console.debug("<<< setTypes");
	},

    /*
     * The setDocumentTypes function sets the list of document types that are
     * available in the "Document Type" combo box.
     */
    setDocumentTypes: function(/*Option[]*/ types){

        // Log entry to the method
        //console.debug(">>> setDocumentTypes(", types, ")");

        // Make sure that the array of options is valid
        if (types && types.length > 0) {
        
    		/*
             * Iterate over the types adding each one to the document type
             * list. Also, make sure that we set the length for the list
             * element to the length of the types array.
             */ 
            this.documentTypeList.length = types.length;
            for (var i = 0; i < types.length; i++) {
                /*
                 * Simply assign the Option object into the options array for
                 * the select element.  We do not use the add method here
                 * because there are cross-browser compatibility issues.
                 */
                this.documentTypeList.options[i] = new Option(types[i].text, types[i].value);
            } // FOR
        } else { // IF - types && types.length > 0
            // No document types were specified.  Hide the entire div.
            this.loadDocumentFieldSet.style.display = "none";
        }
                
        // Log exit from the method        
        //console.debug("<<< setDocumentTypes");
    },
    
    /*
     * Gets the value of teh currently selected document type 
     *
     */
    getDocumentType: function() {
    	return this.documentTypeList.value;
    },

	/*
	 * The setSavedSearches function sets the list of saved searches that are
	 * available in the "Saved Search" combo box.
	 */
	setSavedSearches: function(/*Option[]*/ savedSearches) {

		// Log entry to the method
    	//console.debug(">>> setSavedSearches(", savedSearches, ")");

        // Simply call down to the embedded widget
        this.addRelationshipTargetWidget.setSavedSearches(savedSearches);

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

    onLoadDocumentButtonClicked: function(event) {

        // Log entry to the method
        //console.debug(">>> onLoadDocumentButtonClicked(", event, ")");
        

        // Log exit from the method        
        //console.debug("<<< onLoadDocumentButtonClicked");
    },
	
    onCancelButtonClicked: function(event) {

        // Log entry to the method
        //console.debug(">>> onCancelButtonClicked(", event, ")");
        

        // Log exit from the method        
        //console.debug("<<< onCancelButtonClicked");
    },
    
    _onDocumentTypeChanged: function() {

        // Log entry to the method
        //console.debug(">>> _onDocumentTypeChanged");

        // Log exit from the method        
        //console.debug("<<< _onDocumentTypeChanged");
    },
    
	reset : function() {
	
		// Log entry to the method
    	//console.debug(">>> reset");
    	
        // Simply call down to the embedded widget
        this.addRelationshipTargetWidget.reset();
    	
		// Log exit from the method        
    	//console.debug("<<< reset");
	}
});
