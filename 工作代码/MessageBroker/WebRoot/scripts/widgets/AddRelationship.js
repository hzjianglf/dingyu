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

dojo.provide("com.ibm.sr.widgets.AddRelationship");

dojo.require("dojo.i18n");

dojo.require("dijit._Widget");
dojo.require("dijit._Templated");
dojo.require("dijit._Container");
dojo.require("dijit.form.ValidationTextBox");

dojo.requireLocalization("com.ibm.sr.widgets.AddRelationship", "AddRelationshipResources");

dojo.declare("com.ibm.sr.widgets.AddRelationship", [dijit._Widget, dijit._Templated],{

	// The path to the widgets template
	templatePath: dojo.moduleUrl("com.ibm.sr.widgets.AddRelationship", "AddRelationship.html"),
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
    	msgResourceKeys : [ "textTitle"
                          , "textDescription"
                          , "optionalRelationshipName"
                          , "customRelationshipName"
                          , "buttonAdd"
                          , "buttonCancel"
                          , "invalidRelationshipNameMsg"
                          , "relationshipNameAlreadyExistsMsg"
                          , "relationshipNamePromptMsg"
                          ],
    	RADIO_BUTTON_ENABLED_STYLE : "property notmandatory radiochoice",
    	RADIO_BUTTON_DISABLED_STYLE : "property notmandatory disabledradiochoice",
    	TEXT_COLOR_ENABLED : "#000000",
    	TEXT_COLOR_DISABLED : "#CCCCCC"
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
           
            // Initialize the object type
            if (args.sourceObjectType) {
                this.sourceObjectType = args.sourceObjectType;
            } else {
                this.sourceObjectType = "BaseObject";
            }

            // Initialize the source object
            if (args.sourceObject) {
                this.sourceObject = args.sourceObject;
            } else {
                this.sourceObject = null;
            }
            
            // Initialize the optional relationships
            if (args.optionalRelationships) {
                this.optionalRelationships = args.optionalRelationships;
            } else {
                this.optionalRelationships = new Array();
            }
        } else { // IF - args
            this.contextRoot = "/ServiceRegistry";
            this.sourceObjectType = "BaseObject";
            this.sourceObject = null;
            this.optionalRelationships = new Array();
        }

		// Initialize the label attributes for the widget
		this.messages = dojo.i18n.getLocalization("com.ibm.sr.widgets.AddRelationship", "AddRelationshipResources");
		dojo.forEach(this.statics.msgResourceKeys, function(prop){
			if(!this[prop]){ this[prop] = this.messages[prop]; }
		}, this);

		// Log exit from the method        
    	//console.debug("<<< constructor");
    },

	/**
	 * The postCreate method is invoked after the widget has been initialized,
     * except for the initialization of any sub-widgets.  We defer most of the
     * initialization code to this point to ensure that the attach points have
     * been properly initialized by the underlying dojo template code.
	 */
	postCreate: function() {
	
		// Log entry to the method
    	//console.debug(">>> postCreate");

        // Call the parent class method
        this.inherited("postCreate", arguments);

        // Process the optional relationships
        if (this.optionalRelationships.length > 0) {

            /*
             * We want to build up a list of the options to add to the list
             * of optional relationships in order to be able to set the length
             * property correctly.  Store the options to add in an array.
             */
            var options = new Array();
            this.optionalRelationshipNameList.length = this.optionalRelationships.length;
            for (var i = 0; i < this.optionalRelationships.length; i++) {
            
                /*
                 * If the user creates an optional relationship, it will be
                 * created using the NC name.  Before adding an optional
                 * relationship to the list, make sure that it does not
                 * already exist on the source object.
                 */
                if (this._isValidRelationshipName(this.optionalRelationships[i].NCName)) {
                    var option = new Option(this.optionalRelationships[i].name, this.optionalRelationships[i].NCName);
                    options.push(option);
                }
            } // FOR
            
            // Make sure that we have some optional relationships
            if (options.length > 0) {
            
                // Add the options to the list control
                //console.debug("Number of options: " + options.length);
                this.optionalRelationshipNameList.length = options.length;
                for (var i = 0; i < options.length; i++) {
                    this.optionalRelationshipNameList.options[i] = options[i];
                } // FOR

                /*
                 * Populate the description of the optional relationship by
                 * pretending that the selection in the drop down has changed.
                 */
                this._onOptionalRelationshipNameChanged();

                /*
                 * Enable the relevant controls by pretending that the
                 * optional relationship radio button has been selected
                 */
                this.optionalRelationshipNameButton.disabled = false;
                this.optionalRelationshipNameButton.checked = true;
                this._onRadioButtonClick();
            }
        }  // IF - this.optionalRelationships.length > 0

        /*
         * Make sure that the relationship name widget has access to the
         * relevant validation messages.
         */
        this.customRelationshipField.invalidRelationshipNameMsg = this.messages["invalidRelationshipNameMsg"];
        this.customRelationshipField.relationshipNameAlreadyExistsMsg = this.messages["relationshipNameAlreadyExistsMsg"];
        this.customRelationshipField.promptMessage = this.messages["relationshipNamePromptMsg"];

		// Log exit from the method        
    	//console.debug("<<< postCreate");
	},

    /**
     * Destructor
     */
    destroy: function() {

        // Log entry to the method
        //console.debug(">>> destroy");

        // Make sure that there are no validation messages left displayed
        this.customRelationshipField.displayMessage();

        // Call the parent class method
        this.inherited("destroy", arguments);
        
        // Log exit from the method        
        //console.debug("<<< destroy");
    },

	/*
	 * Handles the Add button being clicked within the widget
	 */
	onAddButtonClicked: function(event) {

		// Log entry to the method
    	//console.debug(">>> onAddButtonClicked(", event , ")");
    	

		// Log exit from the method        
    	//console.debug("<<< onAddButtonClicked");
	},

    /*
     * Handles the Search button being clicked within the widget
     */
    onCancelButtonClicked: function(event) {

        // Log entry to the method
        //console.debug(">>> onCancelButtonClicked(", event, ")");
        

        // Log exit from the method        
        //console.debug("<<< onCancelButtonClicked");
    },
	
    /*
     * Returns the name of the relationship that has been set/entered within
     * the widget.
     */
    getRelationshipName: function() {

        // Log entry to the method
        //console.debug(">>> getRelationshipName");
        
        // Create the variable to return
        var relationshipName = null;

        // Check to see which radio button is selected        
        if (this.optionalRelationshipNameButton.checked == true) {
        
            /*
             * Retrieve the selected relationship name from the list of
             * optional relationships.
             */
            var index = this.optionalRelationshipNameList.selectedIndex;
            relationshipName = this.optionalRelationshipNameList.options[index].value;
        } else if (this.customRelationshipNameButton.checked == true) {

            /*
             * Retrieve the selected relationship name from the custom
             * relationship name widget.
             */
            relationshipName = this.customRelationshipField.attr("value");
        }

        // Log exit from the method        
        //console.debug("<<< getRelationshipName ", relationshipName);
        
        return relationshipName;
    },

    /*
     * Validates the relationship name.
     */
    _isValidRelationshipName: function(relationshipName) {

        // Log entry to the method
        //console.debug(">>> _isValidRelationshipName(", relationshipName, ")");

        // Create the variable to return
        var valid = true;
        
        /*
         * Check the relationship name against the modelled properties, user
         * defined properties and modelled relationships for the source
         * object. 
         */
        var wsrrObjects = [ this.sourceObject ];
        valid = isValidRelationshipName(relationshipName, wsrrObjects);

        // Log exit from the method        
        //console.debug("<<< _isValidRelationshipName ", valid);
        
        return valid;
    },
    
    /*
     * Handles changes to the optional relationship name drop down
     */
    _onOptionalRelationshipNameChanged: function(event) {
    
        // Log entry to the method
        //console.debug(">>> _onOptionalRelationshipNameChanged(", event, ")");

        // Clear the current description
        this.optionalRelationshipDescription.innerHTML = "";

        /*
         * Retrieve the selected relationship name from the list of
         * optional relationships.
         */
        var index = this.optionalRelationshipNameList.selectedIndex;
        relationshipName = this.optionalRelationshipNameList.options[index].value;
        if (relationshipName) {
            // Now retrieve the corresponding relationship info
            var relationshipInfo = this._getRelationshipInfo(relationshipName);
            if (relationshipInfo && relationshipInfo.description) {
                this.optionalRelationshipDescription.appendChild(document.createTextNode(relationshipInfo.description));
            } // IF - relationshipInfo
        } // IF - relationshipName

        // Log exit from the method        
        //console.debug("<<< _onOptionalRelationshipNameChanged");
    },
    
    /**
     * Handles keypress events for the optional relationship name drop down
     */
    _showNameChange: function() {
    
        // Log entry to the method
        //console.debug(">>> _showNameChange");
    
        // Delay invoking the real function
        var widgetHandle = this;
        setTimeout( function(){widgetHandle._onOptionalRelationshipNameChanged(null)}
                  , 0
                  );
                  
        // Log exit from the method        
        //console.debug("<<< _showNameChange");
    },
    
	/*
	 * Handles a click on one of the radio buttons within the widget
	 */
	_onRadioButtonClick: function(event) {
	
		// Log entry to the method
    	//console.debug(">>> _onRadioButtonClick(", event, ")");
    	
    	if (this.optionalRelationshipNameButton.checked == true) {
            // Clear the contents of the custom relationship name text field
            this.customRelationshipField.attr("value","");
        
            /*
             * Check to see if the optional relationship name list control
             * actually contains any options... there may be no optional
             * relationships defined for this type of object.
             */
            if (this.optionalRelationshipNameList.options.length > 0) {
                // Enable the relevant controls
                this.optionalRelationshipNameButton.disabled = false;
                this.optionalRelationshipNameList.disabled = false;
                this.optionalRelationshipDiv.className = this.statics.RADIO_BUTTON_ENABLED_STYLE;
                this.addButton.disabled = false;
            } else {
                this.optionalRelationshipNameButton.disabled = true;
                this.addButton.disabled = true; 
            }
            
            // Disable the relevant controls
            this.customRelationshipField.attr("disabled", true);
            this.customRelationshipField.attr("value",""); // Clear the contents
            this.customRelationshipField.displayMessage(); // Clear any message
            this.customRelationshipDiv.className = this.statics.RADIO_BUTTON_DISABLED_STYLE;
    	} else if (this.customRelationshipNameButton.checked == true) {
    		// Enable the relevant controls
            this.customRelationshipField.attr("disabled", false);
    		this.customRelationshipDiv.className = this.statics.RADIO_BUTTON_ENABLED_STYLE;

    		// Disable the relevant controls
    		this.optionalRelationshipNameList.disabled = true;
    		this.optionalRelationshipDiv.className = this.statics.RADIO_BUTTON_DISABLED_STYLE;
    		this.addButton.disabled = true;
    	}

		// Log exit from the method        
    	//console.debug("<<< _onRadioButtonClick");
	},
	
    _getRelationshipInfo : function(/*String*/ relationshipName) {

        // Log entry to the method
          //console.debug(">>> _getRelationshipInfo(", relationshipName, ")");

        // Create the variable to return
        var relationshipInfo = null;

        // Make sure that the relationship name passed is valid  
        if (relationshipName) {
  
            // Look for the optional relationship info with a matching name
            if (this.optionalRelationships && this.optionalRelationships.length > 0) {
                for (var i = 0; i < this.optionalRelationships.length; i++) {
                    if (this.optionalRelationships[i].NCName == relationshipName) {
                        relationshipInfo = this.optionalRelationships[i];
                        break;
                    }
                } // FOR
            } // IF - optionalRelationshipInfos &&, etc...
        } // IF - relationshipName

        // Log exit from the method
        //console.debug("<<< _getRelationshipInfo ", relationshipInfo);

        return relationshipInfo;
    },
    
	reset : function() {
	
		// Log entry to the method
    	//console.debug(">>> reset");
    	
    	// Reset all of the fields for the control
		this.optionalRelationshipNameButton.checked = false;
		this.customRelationshipNameButton.checked = true;
   		this.optionalRelationshipNameList.disabled = true;
   		this.optionalRelationshipDiv.className = this.statics.RADIO_BUTTON_DISABLED_STYLE;
        this.customRelationshipField.attr("value",""); // Clear the contents
        this.customRelationshipField.displayMessage(); // Clear any message
        this.customRelationshipField.attr("disabled", false);
   		this.customRelationshipDiv.className = this.statics.RADIO_BUTTON_ENABLED_STYLE;
   		this.addButton.disabled = true;
   		this.cancelButton.disabled = false;
    	
		// Log exit from the method        
    	//console.debug("<<< reset");
	}
});
