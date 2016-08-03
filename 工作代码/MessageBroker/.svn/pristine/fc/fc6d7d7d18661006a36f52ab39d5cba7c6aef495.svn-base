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

dojo.provide("com.ibm.sr.widgets.DetailView");

dojo.require("dojo.i18n");

dojo.require("dijit._Widget");
dojo.require("dijit._Templated");

dojo.requireLocalization("com.ibm.sr.widgets.DetailView", "DetailViewResources");

dojo.declare("com.ibm.sr.widgets.DetailView", [dijit._Widget, dijit._Templated],{

	// The path to the widgets template
	templatePath: dojo.moduleUrl("com.ibm.sr.widgets.DetailView", "DetailView.html"),
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
                          , "textGeneralProperties"
                          , "textRelationships"
                          , "textClassifications"
                          , "textNone"
                          , "textNoneHoverHelp"
                          , "textCollapse"
                          ],
    	RADIO_BUTTON_ENABLED_STYLE : "property notmandatory radiochoice",
    	RADIO_BUTTON_DISABLED_STYLE : "property notmandatory disabledradiochoice",
    	TEXT_COLOR_ENABLED : "#000000",
    	TEXT_COLOR_DISABLED : "#CCCCCC"
    },

    constructor: function (args) {

		// Log entry to the method
    	//console.debug(">>> constructor(", args, ")");

        // Check to see if we were passed some args
        if (args) {
        
            // Initialize the source object
            if (args.wsrrObject) {
                this.wsrrObject = args.wsrrObject;
            }
            
            // Initialize the general property definitions
            if (args.generalProperties) {
                this.generalProperties = args.generalProperties;
            }
            
            // Initialize the modelled properties info objects
            if (args.modelledPropertyInfos) {
                this.modelledPropertyInfos = args.modelledPropertyInfos;
            }

            // Initialize the optional properties info objects
            if (args.optionalPropertyInfos) {
                this.optionalPropertyInfos = args.optionalPropertyInfos;
            }
            
            // Initialize the modelled relationship info objects
            if (args.modelledRelationshipInfos) {
                this.modelledRelationshipInfos = args.modelledRelationshipInfos;
            }

            // Initialize the optional relationship info objects
            if (args.optionalRelationshipInfos) {
                this.optionalRelationshipInfos = args.optionalRelationshipInfos;
            }

            // Initialize the theme name
            if (args.themeName) {
                this.themeName = args.themeName;
            }
            else
              this.themeName = "standard";
        } else {
            this.wsrrObject = null;
            this.generalProperties = null;
            this.modelledProertyInfos = null;
            this.optionalPropertyInfos = null;
            this.modelledRelationshipInfos = null;
            this.optionalRelationshipInfos = null;
            this.themeName = "standard";
        }

		// Initialize the label attributes for the widget
		this.messages = dojo.i18n.getLocalization("com.ibm.sr.widgets.DetailView", "DetailViewResources");
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

        /*
         * We need to separate out the modelled relationships and user defined
         * relationships at this point by iterating over the properties on the
         * wsrr object and checking the modelled flag.  Create arrays to hold
         * each type of relationship
         */
        this.modelledRelationships = new Array();
        this.userDefinedRelationships = new Array();
        
        // Sort the relationships into their respective arrays
        var relationships = this.wsrrObject.relationships;
        for (var i = 0; i < relationships.length; i++) {
            if (relationships[i].modelled) {
                this.modelledRelationships.push(relationships[i]);
            } else {
                this.userDefinedRelationships.push(relationships[i]);
            }
        } // FOR
        
        /*
         * We should now have all of the information that we need to render
         * the widget.
         */ 
        this._processProperties();
        this._processRelationships();
        this._processClassifications();

		// Log exit from the method        
    	//console.debug("<<< postCreate");
	},

    /**
     * Destructor
     */
    destroy: function() {

        // Log entry to the method
        //console.debug(">>> destroy");

        // Call the parent class method
        this.inherited("destroy", arguments);

        // Log exit from the method        
        //console.debug("<<< destroy");
    },

    /**
     * The removeRelationship function removes the relationship with the
     * specified name from the relationships section of the detail view.
     */
    removeRelationship: function(/*String*/ relationshipName) {

        // Log entry to the method
        //console.debug(">>> removeRelationship(", relationshipName, ")");

        /*
         * Attempt to find the list item that represents the specified
         * relationship.
         */ 
        var listItem = dojo.byId(this.id + "_" + relationshipName);
        if (listItem) {
            this.relationshipsList.removeChild(listItem);
        } // IF - listItem
        
        // Log exit from the method        
        //console.debug("<<< removeRelationship");
    },
    
    /**
     * The removeRelationshipTarget function removes the relationship target
     * with the specified bsrUri from the relationship with the specified
     * name.
     */
    removeRelationshipTarget: function(/*String*/ relationshipName, /*String*/ bsrUri) {

        // Log entry to the method
        //console.debug(">>> removeRelationshipTarget(", relationshipName, ", ", bsrUri, ")");

        /*
         * Attempt to find the list item that represents the specified
         * relationship and its child list item that represents the target of
         * the relationship.
         */ 
        var targetsList = dojo.byId(this.id + "_" + relationshipName + "_targets");
        var targetListItem = dojo.byId(this.id + "_" + relationshipName + "_targets_" + bsrUri);
        if (targetsList && targetListItem) {
            targetsList.removeChild(targetListItem);
            
            // Now check to see if we need to add the "None" item back
            if (!targetsList.hasChildNodes()) {
                // There are not targets so insert the None item
                var noneListItem = document.createElement("li");
                noneListItem.title = this.textNoneHoverHelp;
                noneListItem.appendChild(document.createTextNode(this.textNone));
                targetsList.appendChild(noneListItem);
            } // IF - targetsList.hasChildNodes()
        } // IF - listItem
        
        // Log exit from the method        
        //console.debug("<<< removeRelationshipTarget");
    },
    
    _processProperties: function() {
        // Log entry to the method
        //console.debug(">>> _processProperties");

        /*
         * First process any properties that have been defined within the
         * the general properties section of the view definition
         */
        this._processGeneralProperties();
        
        /*
         * Now process any modelled and optional properties that may have been
         * defined for the object.
         */
        this._processBusinessModelProperties();
        
        /*
         * Now iterate over the properties on the source object and render the
         * non-modelled properties at the bottom of the general properties
         * section.
         */
        var properties = this.wsrrObject.properties;
        for (var i = 0; i < properties.length; i++) {
            /*
             * Check to make sure that this is not a modelled property or the
             * special __typeDisplayName property.
             */ 
            if (  !this._isGeneralProperty(properties[i].name)
               && !this._isBusinessModelProperty(properties[i].name)
               && properties[i].name != "__typeDisplayName"
               ) {
                this._createPropertyDiv(properties[i].name, properties[i].value, properties[i].name, null, this.propertiesDiv);
            }
        } // FOR

        // Log exit from the method        
        //console.debug("<<< _processProperties");
    },
    
    _processGeneralProperties: function() {
        // Log entry to the method
        //console.debug(">>> _processGeneralProperties");

        // Make sure that we have a valid array of general properties
        if (this.generalProperties && this.generalProperties.length > 0) {

            // Iterate over the general properties array
            for (var i = 0; i < this.generalProperties.length; i++) {
            
                /*
                 * Retrieve the name and value for the property and
                 * render it in the general properties section
                 */
                var name = this.generalProperties[i].name;
                var property = getProperty(this.generalProperties[i].NCName, this.wsrrObject);
                var value = "";
                if (property) {
                    value = property.value;
                }
                var fieldHelp = this.generalProperties[i].fieldHelpMessage;
                var displayAs = this.generalProperties[i].displayAs;
                this._createPropertyDiv(name, value, fieldHelp, displayAs, this.propertiesDiv);
            } // FOR
        } // IF - generalProperties &&, etc...

        // Log exit from the method        
        //console.debug("<<< _processGeneralProperties");
    },

    _processBusinessModelProperties: function() {
        // Log entry to the method
        //console.debug(">>> _processBusinessModelProperties");

        // Make sure that we have a valid array of modelled property infos
        if (this.modelledPropertyInfos && this.modelledPropertyInfos.length > 0) {

            // Iterate over the modelledPropertyInfos array
            for (var i = 0; i < this.modelledPropertyInfos.length; i++) {

                // Retrieve the NC name of the property
                var ncName = this.modelledPropertyInfos[i].NCName;

                // Make sure that we have not already rendered this property
                if (!this._isGeneralProperty(ncName)) {
                
                    // Attempt to retrieve a property with the specified name
                    var property = getProperty(ncName, this.wsrrObject);
                    if (property) {
                        var name = this.modelledPropertyInfos[i].name;
                        var fieldHelp = this.modelledPropertyInfos[i].description;
                        var displayAs = this.modelledPropertyInfos[i].constraints.displayAs;
                        this._createPropertyDiv(name, property.value, fieldHelp, displayAs, this.propertiesDiv);
                    } else {
                        /*
                         * A modelled property should always exist on the
                         * object, unless it is being created!!!
                         */
                    }
                } // IF - this._isGeneralProperty(ncName)
            } // FOR
        } // IF - this.modelledPropertyInfos &&, etc...

        // See if we have a valid array of optional property infos
        if (this.optionalPropertyInfos && this.optionalPropertyInfos.length > 0) {

            // Iterate over the optionalPropertyInfos array
            for (var i = 0; i < this.optionalPropertyInfos.length; i++) {

                // Retrieve the NC name of the property
                var ncName = this.optionalPropertyInfos[i].NCName;

                // Make sure that we have not already rendered this property
                if (!this._isGeneralProperty(ncName)) {
        
                    // Attempt to retrieve a property with the specified name
                    var property = getProperty(ncName, this.wsrrObject);
                    if (property) {
                        var name = this.optionalPropertyInfos[i].name;
                        var fieldHelp = this.optionalPropertyInfos[i].description;
                        var displayAs = this.optionalPropertyInfos[i].constraints.displayAs;
                        this._createPropertyDiv(name, property.value, fieldHelp, displayAs, this.propertiesDiv);
                    }
                } // IF - this._isGeneralProperty(ncName)
            } // FOR
        } // IF - this.optionalPropertyInfos &&, etc...

        // Log exit from the method        
        //console.debug("<<< _processBusinessModelProperties");
    },

    _createPropertyDiv: function(name, value, fieldHelp, displayAs, parentDiv) {

        // Log entry to the method
        //console.debug(">>> _createPropertyDiv(", name, ", ", value, ", ", fieldHelp, ", ", displayAs, ", ", parentDiv, ")");

        // Create the parent div
        var propertyDiv = document.createElement("div");
        propertyDiv.className = "readonlyproperty notmandatory";
        
        // Create the label for input control
        var label = document.createElement("label");
        label.htmlFor = this.id + "_" + name;
        if (fieldHelp) {
            label.title = fieldHelp;
        } else {
            label.title = name;
        }
        label.appendChild(document.createTextNode(name));
        
        // Create the input control itself
        var input = null;
        if (displayAs != "textarea") {
            input = document.createElement("input");
            input.type = "text";
        } else {
            input = document.createElement("textarea");
            input.rows = 3;
        }
        input.id = this.id + "_" + name;
        input.setAttribute("tabindex", "1");
        input.readOnly = true;
        input.value = value;
        
        // Now add the elements into the DOM tree
        propertyDiv.appendChild(label);
        propertyDiv.appendChild(document.createElement("br"));
        propertyDiv.appendChild(input);
        parentDiv.appendChild(propertyDiv);

        // Log exit from the method        
        //console.debug("<<< _createPropertyDiv");
    },
    
    _processRelationships: function() {

        // Log entry to the method
        //console.debug(">>> _processRelationships");

        /*
         * First process any relationships that have been defined within the
         * business model for the object (if any).
         */
        this._processBusinessModelRelationships(this.relationshipsList);

        /*
         * Now process the remainder of the relationships, only rendering
         * those relationships that have not already been rendered when
         * processing the business model relationships.  Start with the
         * relationships on the source object.
         */
        var relationships = this.modelledRelationships;
        for (var i = 0; i < relationships.length; i++) {
            if (!this._isBusinessModelRelationship(relationships[i].name)) {
                var name = relationships[i].name;
                var targets = relationships[i].targets;
                this._createRelationshipListItem(name, name, targets, name, this.relationshipsList);
            }
        } // FOR
        
        // Now iterate over the user defined relationships being edited
        if (this.userDefinedRelationships) {
            relationships = this.userDefinedRelationships;
            for (var i = 0; i < relationships.length; i++) {
                if (!this._isBusinessModelRelationship(relationships[i].name)) {
                    var name = relationships[i].name;
                    var targets = relationships[i].targets;
                    this._createRelationshipListItem(name, name, targets, name, this.relationshipsList);
                }
            } // FOR
        } // IF - this.userDefinedRelationships
        
        // Log exit from the method        
        //console.debug("<<< _processRelationships");
    },

    _processBusinessModelRelationships: function(parentList) {
        // Log entry to the method
        //console.debug(">>> _processBusinessModelRelationships(", parentList, ")");

        // Make sure that we have a valid array of modelled relationship infos
        if (this.modelledRelationshipInfos && this.modelledRelationshipInfos.length > 0) {

            // Iterate over the modelledRelationshipInfos array
            for (var i = 0; i < this.modelledRelationshipInfos.length; i++) {
        
                // Attempt to retrieve a relationship with the specified name
                var ncName = this.modelledRelationshipInfos[i].NCName;
                var relationship = this._getUserDefinedRelationship(ncName);
                if (relationship) {
                    var name = this.modelledRelationshipInfos[i].name;
                    var fieldHelp = this.modelledRelationshipInfos[i].description;
                    this._createRelationshipListItem(ncName, name, relationship.targets, fieldHelp, parentList);
                } else {
                    /*
                     * A modelled relationship should always exist on the
                     * object, unless it is being created!!!
                     */
                }
            } // FOR
        } // IF - modelledRelationshipInfos &&, etc...

        // See if we have a valid array of optional relationship infos
        if (this.optionalRelationshipInfos && this.optionalRelationshipInfos.length > 0) {

            // Iterate over the optionalRelationshipInfos array
            for (var i = 0; i < this.optionalRelationshipInfos.length; i++) {
        
                // Attempt to retrieve a relationship with the specified name
                var ncName = this.optionalRelationshipInfos[i].NCName;
                var relationship = this._getUserDefinedRelationship(ncName);
                if (relationship) {
                    var name = this.optionalRelationshipInfos[i].name;
                    var fieldHelp = this.optionalRelationshipInfos[i].description;
                    this._createRelationshipListItem(ncName, name, relationship.targets, fieldHelp, parentList);
                }
            } // FOR
        } // IF - modelledRelationshipInfos &&, etc...

        // Log exit from the method        
        //console.debug("<<< _processBusinessModelRelationships");
    },

    _createRelationshipListItem: function(ncName, displayName, targets, fieldHelp, parentList) {

        // Log entry to the method
        //console.debug(">>> _createRelationshipListItem(", ncName, ", ", displayName, ", ", targets, ", ", fieldHelp, ", ", parentList, ")");

        // Create the list item for the relationship
        var relationshipListItem = document.createElement("li");
        relationshipListItem.id = this.id + "_" + ncName;
        if (fieldHelp) {
            relationshipListItem.title = fieldHelp;
        } else {
            relationshipListItem.title = displayName;
        }
        relationshipListItem.appendChild(document.createTextNode(displayName));

        // Now create the list to hold the targets
        var targetsList = document.createElement("ul");
        targetsList.id = this.id + "_" + ncName + "_targets";
        if (targets && targets.length > 0) {
            // Iterate over the targets adding an item for each
            for (var i = 0; i < targets.length; i++) {
                var targetListItem = document.createElement("li");
                targetListItem.id = this.id + "_" + ncName + "_targets_" + targets[i].bsrUri;
                targetListItem.title = targets[i].name;
                targetListItem.appendChild(document.createTextNode(targets[i].name));
                targetsList.appendChild(targetListItem);
            } // FOR
        } else { // IF - targets.length > 0
            // There are not targets so insert the None item
            var targetListItem = document.createElement("li");
            targetListItem.title = this.textNoneHoverHelp;
            targetListItem.appendChild(document.createTextNode(this.textNone));
            targetsList.appendChild(targetListItem);
        }
        
        // Add the relevant objects to the DOM tree
        relationshipListItem.appendChild(targetsList);
        parentList.appendChild(relationshipListItem);

        // Log exit from the method        
        //console.debug("<<< _createRelationshipListItem");
    },

    _processClassifications: function() {

        // Log entry to the method
        //console.debug(">>> _processClassifications");

        // Create the list to hold the classifications
        var classificationsList = document.createElement("ul");
        
        // Now iterate over the classifications on the source object
        var classifications = this.wsrrObject.classifications;
        for (var i = 0; i < classifications.length; i++) {
            this._createClassificationListItem(classifications[i].label, classifications[i].systemName, classificationsList);
        } // FOR
        
        // Now add the list into the DOM tree
        this.classificationsDiv.appendChild(classificationsList);

        // Log exit from the method        
        //console.debug("<<< _processClassifications");
    },
    
    _createClassificationListItem: function(name, systemName, parentList) {

        // Log entry to the method
        //console.debug(">>> _createClassificationListItem(", name, ", ", systemName, ", ", parentList, ")");

        // Create the list item for the classification
        var classificationListItem = document.createElement("li");
        classificationListItem.title = systemName + ": " + name;
        classificationListItem.appendChild(document.createTextNode(name));

        // Add the relevant objects to the DOM tree
        parentList.appendChild(classificationListItem);

        // Log exit from the method        
        //console.debug("<<< _createClassificationListItem");
    },
    
    _isGeneralProperty: function(/*String*/ ncName) {
    
        // Log entry to the method
        //console.debug(">>> _isGeneralProperty(", ncName, ")");
        
        // Create the variable to return
        var isGeneral = false;
        
        // Make sure that we have a valid array of general properties
        if (this.generalProperties && this.generalProperties.length > 0) {

            // Iterate over the general properties array
            for (var i = 0; i < this.generalProperties.length; i++) {
        
                // Check specified name against the NCName of the general property
                if (this.generalProperties[i].NCName == ncName) {
            
                    // Match.  Set the flag and break from the loop.
                    isGeneral = true;
                    break;
                }
            } // FOR
        } // IF - generalProperties &&, etc...
        
        // Log exit from the method        
        //console.debug("<<< _isGeneralProperty ", isGeneral);
        
        return isGeneral;
    },
    
    _isBusinessModelProperty: function(/*String*/ ncName) {
        // Log entry to the method
        //console.debug(">>> _isBusinessModelProperty(", ncName, ")");
        
        // Create the variable to return
        var isBusinessModelProperty = false;
        
        /*
         * In order to determine if the property specified is defined by
         * the business model for the object, we need to check both arrays of
         * property infos.  Check the array of modelled properties
         * first.
         */
        if (this.modelledPropertyInfos && this.modelledPropertyInfos.length > 0) {

            // Iterate over the modelledPropertyInfos array
            for (var i = 0; i < this.modelledPropertyInfos.length; i++) {
        
                // Check specified name against the NCName of the property info
                if (this.modelledPropertyInfos[i].NCName == ncName) {
            
                    // Match.  Set the flag and break from the loop.
                    isBusinessModelProperty = true;
                    break;
                }
            } // FOR
        } // IF - this.modelledPropertyInfos &&, etc...
        
        // Now check the array of optional property infos, if necessary
        if (  isBusinessModelProperty == false
           && this.optionalPropertyInfos && this.optionalPropertyInfos.length > 0
           ) {
           
            // Iterate over the optionalPropertyInfos array
            for (var i = 0; i < this.optionalPropertyInfos.length; i++) {
        
                // Check specified name against the NCName of the property info
                if (this.optionalPropertyInfos[i].NCName == ncName) {
            
                    // Match.  Set the flag and break from the loop.
                    isBusinessModelProperty = true;
                    break;
                }
            } // FOR
        } // IF - isBusinessModelProperty == false &&, etc...
        
        // Log exit from the method        
        //console.debug("<<< _isBusinessModelProperty ", isBusinessModelProperty);
        
        return isBusinessModelProperty;
    },
    
    _isBusinessModelRelationship: function(/*String*/ ncName) {
        // Log entry to the method
        //console.debug(">>> _isBusinessModelRelationship(", ncName, ")");
        
        // Create the variable to return
        var isBusinessModelRelationship = false;
        
        /*
         * In order to determine if the relationship specified is defined by
         * the business model for the object, we need to check both arrays of
         * relationship infos.  Check the array of modelled relationships
         * first.
         */
        if (this.modelledRelationshipInfos && this.modelledRelationshipInfos.length > 0) {

            // Iterate over the modelledRelationshipInfos array
            for (var i = 0; i < this.modelledRelationshipInfos.length; i++) {
        
                // Check specified name against the NCName of the relationship info
                if (this.modelledRelationshipInfos[i].NCName == ncName) {
            
                    // Match.  Set the flag and break from the loop.
                    isBusinessModelRelationship = true;
                    break;
                }
            } // FOR
        } // IF - modelledRelationshipInfos &&, etc...
        
        // Now check the array of optional relationship infos, if necessary
        if (  isBusinessModelRelationship == false
           && this.optionalRelationshipInfos && this.optionalRelationshipInfos.length > 0
           ) {
           
            // Iterate over the optionalRelationshipInfos array
            for (var i = 0; i < this.optionalRelationshipInfos.length; i++) {
        
                // Check specified name against the NCName of the relationship info
                if (this.optionalRelationshipInfos[i].NCName == ncName) {
            
                    // Match.  Set the flag and break from the loop.
                    isBusinessModelRelationship = true;
                    break;
                }
            } // FOR
        } // IF - isBusinessModelRelationship == false &&, etc...
        
        // Log exit from the method        
        //console.debug("<<< _isBusinessModelRelationship ", isBusinessModelRelationship);
        
        return isBusinessModelRelationship;
    },
    
    _getUserDefinedRelationship: function(/*String*/ ncName) {
        // Log entry to the method
        //console.debug(">>> _getUserDefinedRelationship(", ncName, ")");
        
        // Create the variable to return
        var udr = null;
        
        // Make sure that we have a valid array of user defined relationships
        if (this.userDefinedRelationships && this.userDefinedRelationships.length > 0) {

            // Iterate over the user defined relationship array
            for (var i = 0; i < this.userDefinedRelationships.length; i++) {

                // Check specified name against the NCName of the udr
                if (this.userDefinedRelationships[i].name == ncName) {
                    // Match.  Set the return variable and break from the loop.
                    udr = this.userDefinedRelationships[i];
                    break;
                }
            } // FOR
        } else { // IF - userDefinedRelationships &&, etc...
            //console.debug("No user defined relationships defined!!! ", this.userDefinedRelationships);
        }
        
        // Log exit from the method        
        //console.debug("<<< _getUserDefinedRelationship ", udr);
        
        return udr;
    }
});
