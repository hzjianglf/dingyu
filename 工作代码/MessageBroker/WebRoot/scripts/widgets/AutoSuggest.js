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

dojo.provide("com.ibm.sr.widgets.AutoSuggest");

dojo.require("dojo.rpc.JsonService");
dojo.require("dijit._Widget");
dojo.require("dijit._Templated");
dojo.require("dojox.string.Builder");

dojo.declare("com.ibm.sr.widgets.AutoSuggest", [dijit._Widget, dijit._Templated],{

	// The path to the widgets template
	templatePath: dojo.moduleUrl("com.ibm.sr.widgets.AutoSuggest", "AutoSuggest.html"),
	
	// The size attribute for the widget
	size: "",
	
	// The name attribute for the widget
	name: "",
	
	// The context root for the service registry application
	contextRoot: "/ServiceRegistry",
	
	/*
	 * Flag to indicate whether the key down event should be suppressed when
	 * the enter key is used to select a suggestion.  Not suppressing the key
	 * down event may result in the default submit button in a form being
	 * fired (when this widget is embedded in a form).  It is up to the user
	 * of the widget to decide whether this is desirable behaviour.  The
	 * default behaviour is not to suppress this event.
	 */
	consumeEnterKeyEvent: false,
	
    /**
     * Define the hashtable of static configuration properties for the widget.
     * These configuration properties are controlled by the auto-suggest
     * preferences and must be applied across all auto-suggest widgets in the
     * UI. 
     */
    statics: {
	    maxDisplaySuggestions : 10,
	    autoSuggestEnabled : true,
	    caseSensitive : false,
	    displayResultCount : true,
	    displayTypeAhead : true,
	    submitTimerInterval : 300,
	    showAllSuggestionsTimerInterval : 2000,
	    highlightFlashTimerInterval : 400,
	    SHOW_ALL_SUGGESTIONS : -1,
	    SUGGESTION_ID : "suggestion",
    	MORE_SUGGESTIONS_ID : "moreSuggestionsRow",
    	BASE_OBJECT : "BaseObject",
        AUTO_SUGGEST_ENABLED_PREF : "auto.suggest.enabled",
        CASE_SENSITIVE_PREF : "case.sensitive",
        TYPE_AHEAD_ENABLED_PREF : "type.ahead.enabled",
        RESULT_COUNT_ENABLED_PREF : "result.count.enabled",
        NUM_SUGGESTIONS_PREF : "number.of.suggestions",
        SUBMIT_DELAY_PREF : "submit.delay",
    	TEXT_ENTRY_CLASSNAME : "textEntry",
    	TEXT_ENTRY_READ_ONLY_CLASSNAME : "textEntryReadOnly",
        RESULT_CLASSNAME : "result",
        COUNT_CLASSNAME : "count",
        CURRENT_ROW_CLASSNAME : "current"
    },

    constructor: function () {

		// Log entry to the method
    	//console.debug(">>> constructor");

        // Initialize the cursor
        this.cursor = -1;

		// The default type to query
		this.type = this.statics.BASE_OBJECT;
		
		// The number of suggestions to display
	    this.currentMaxDisplaySuggestions = this.statics.maxDisplaySuggestions;

		// Handles to the various timers used by the widget
		this.submitTimer = null;
		this.highlightFlashTimer = null;
		this.showAllSuggestionsTimer = null;

		// The actual array of suggestions
		this.suggestions = new Array();

		// Log exit from the method        
    	//console.debug("<<< constructor");
    },

    /**
     * The postCreate method is invoked after the widget has been fully
     * initialized.  We defer the creation of the suggestions div to this
     * point to ensure that the queryField attach point has been
     * initialized by the underlying dojo template code.
     */
    postCreate: function() {
    
        // Log entry to the method
        //console.debug(">>> postCreate");

        /*
         * The actual suggest service itself and the Deferred object returned
         * when invoking the suggest service.
         */
        this.suggestService = new dojo.rpc.JsonService(this.contextRoot + "/RPCAdapter/jsonrpc/SuggestService");
        
		//Bug #5182. On a secure system after recovery from a timeout the Service may not
		//be initialized properly. Check if the ServiceUrl is defined and if not then send
		//the request again and all shall be well.
		if (this.suggestService == null || this.suggestService.serviceUrl == null)
			this.suggestService = new dojo.rpc.JsonService(this.contextRoot + "/RPCAdapter/jsonrpc/SuggestService");
        
        this.deferredSuggestObj = null;

        /*
         * Register the keyup, keydown and blur handlers for the query
         * field. The onblur event fires when the entry field loses focus.
         *
         * We connect the events here rather than using dojoAttachEvents
         * because the events need to be driven by whether the user has
         * enabled auto-suggest.
         */
        this.connect(this.queryField, "onfocus", "onFocusHandler");
        this.connect(this.queryField, "onkeyup", "keyUpHandler");
        this.keyDownHandle = this.connect(this.queryField, "onkeydown", "keyDownHandler");
        this.blurHandle = this.connect(this.queryField, "onblur", "onBlurHandler");

        // Create the suggestions div
        this.createSuggestionsDiv();

        // Log exit from the method        
        //console.debug("<<< postCreate");
    },

    /**
     * The getValue function returns the current value entered in the query
     * field.
     */
    getValue: function() {

        // Log entry to the method
        //console.debug(">>> getValue");
    
        // Log exit from the method        
        //console.debug("<<< getValue ", this.queryField.value);

        return this.queryField.value;
    },

    /**
     * The getType function returns the the type of object for which
     * suggestions will be retrieved from WSRR.
     */
    getType: function() {

        // Log entry to the method
        //console.debug(">>> getType");
    
        // Log exit from the method        
        //console.debug("<<< getType ", this.type);
        
        return this.type;
    },

	/**
	 * The setType function sets the type of object for which suggestions will
	 * be retrieved from WSRR.  For instance, specifying an SDO type of
	 * WSDLDocument will result in only WSDLDocument suggestions being
	 * retrieved from WSRR.  Setting the type to be a classification URI will
	 * result in only objects classified by that URI or any of its children
	 * being retrieved from WSRR.
	 */
	setType: function(type){

		// Log entry to the method
    	//console.debug(">>> setType(", type, ")");
	
		this.type = type;
		
		// Log exit from the method        
    	//console.debug("<<< setType");
	},

    /**
     * Destructor
     */
    destroy: function() {

		// Log entry to the method
    	//console.debug(">>> destroy");

        /*
         * There is no need to manually disconnect any connections here since
         * we used the widget.connect function and not the dojo.connect method.
         */

		// Log exit from the method        
    	//console.debug("<<< destroy");
    },

    /**
     * The createSuggestionsDiv function creates the parent div that will hold
     * the table to display suggestions.  It also attaches a number of event
     * handlers that control the behaviour of the rows in the table when the
     * mouse moves over any displayed suggestions.
     */
    createSuggestionsDiv: function() {

		// Log entry to the method
    	//console.debug(">>> createSuggestionsDiv");

        // Create the suggestions div and set its style
        this.suggestionsDiv = document.createElement("div");
        this.suggestionsDiv.style.zIndex = "4000";
        this.suggestionsDiv.style.opacity ="1";
        this.suggestionsDiv.style.repeat = "repeat";
        this.suggestionsDiv.style.filter = "alpha(opacity=100)";
        this.suggestionsDiv.className = "suggestions navsuggestions";
        this.suggestionsDiv.style.display = "none";

        this.suggestionsDiv.style.backgroundColor = "white";
        this.suggestionsDiv.style.autocomplete = "off";

        // Register the mouseup, mousedown and mouseup handlers for the suggestion div
        this.connect(this.suggestionsDiv, "onmouseup", "mouseUpHandler");
        this.connect(this.suggestionsDiv, "onmouseover", "mouseOverHandler");
        this.connect(this.suggestionsDiv, "onmousedown", "mouseDownHandler");

        // Append the div to the document
        document.body.appendChild(this.suggestionsDiv);

		// Log exit from the method        
    	//console.debug("<<< createSuggestionsDiv");
    },
    
    /**
     * The mouseUpHandler function is attached to the suggestionsDiv.  It will be
     * invoked when the left mouse button is pressed and then released over a
     * suggestion.
     */
    mouseUpHandler : function(inputEvent) {
    
		// Log entry to the method
    	//console.debug(">>> mouseUpHandler(" + inputEvent + ")");
    
        // Set the focus back to the entry field
        this.queryField.focus();
        
		// Log exit from the method        
    	//console.debug("<<< mouseUpHandler");
    },

    /**
     * The mouseOverHandler function is attached to the suggestionsDiv.  It will
     * be invoked when the mouse pointer enters the rectangular space occupied by
     * each suggestion displayed.
     */
    mouseOverHandler : function(inputEvent) {
    
		// Log entry to the method
    	//console.debug(">>> mouseOverHandler(" + inputEvent + ")");
    
        /** 
         * Determine the target suggestion.  The mouse will be over either a
         * result div, a count div or their corresponding TD element.  In order
         * to ensure consistent behaviour, we always navigate up to the row
         * element and pass this as a parameter to other functions.
         */
        var suggestTarget = inputEvent.target || inputEvent.srcElement;
        if (suggestTarget.className == this.statics.RESULT_CLASSNAME || suggestTarget.className == this.statics.COUNT_CLASSNAME) {
            suggestTarget = suggestTarget.parentNode.parentNode;
        } else {
            suggestTarget = suggestTarget.parentNode;
        }
        
        // Highlight the target suggestion
        this.highlightSuggestion(suggestTarget);
        
		// Log exit from the method        
    	//console.debug("<<< mouseOverHandler");
    },

    /**
     * The mouseDownHandler function is attached to the suggestionsDiv.  It will
     * be invoked when the left mouse button is pressed over a suggestion.
     */
    mouseDownHandler : function(inputEvent) {

		// Log entry to the method
    	//console.debug(">>> mouseDownHandler(" + inputEvent + ")");
	
        /* 
         * Determine the target suggestion. The mouse will be over either a
         * result div, a count div or their corresponding TD element.  We need
         * to naviagate the DOM model in order to determine the text for the
         * selected suggestion.
         */
        var suggestTarget = inputEvent.target || inputEvent.srcElement;
        if (suggestTarget.className == this.statics.RESULT_CLASSNAME || suggestTarget.className == this.statics.COUNT_CLASSNAME) {
            suggestTarget = suggestTarget.parentNode.parentNode;
        } else {
            suggestTarget = suggestTarget.parentNode;
        }
        var nodeId = suggestTarget.id;

        // Check to see if the mouse was over the moreSuggestionsRow
        if (nodeId == this.statics.MORE_SUGGESTIONS_ID) {
        
            /*
             * The mouse was over the moreSuggestionsRow when the button was
             * clicked.  Cancel the relevant timers and display all of the
             * suggestions to the user.
             */   
            this.cancelHighlightFlashTimer();
            this.cancelShowAllSuggestionsTimer();
            this.currentMaxDisplaySuggestions = this.statics.SHOW_ALL_SUGGESTIONS;
            this.showSuggestions(this.currentMaxDisplaySuggestions);
        } else {
            // The mouse was over a suggestion when the button was clicked.

            // Cancel any outstanding timers
            this.cancelAllTimers();
       
            // Set the value of the entry field to the selected node text
            var suggestionIndex = nodeId.substr(this.statics.SUGGESTION_ID.length);
            this.queryField.value = this.suggestions[suggestionIndex].name;

            // Hide the suggestions
            this.hideSuggestions();
        
			// Invoke the (hopefully overridden) onSuggestionSelected function
			this.onSuggestionSelected(this.suggestions[suggestionIndex]);
        
            // Reset the number of suggestions to display
            this.currentMaxDisplaySuggestions = this.statics.maxDisplaySuggestions;
        }
        
		// Log exit from the method        
    	//console.debug("<<< mouseDownHandler");
    },

    /**
     * The keyUpHandler function is attached to the query entry field.  It will be
     * invoked when a key is released when the query entry field has focus.
     */
    keyUpHandler : function(inEvent) {

		// Log entry to the method
    	//console.debug(">>> keyUpHandler(" + inEvent + ")");

        // Retrieve the keyCode for the released key
        var keyCode = inEvent.keyCode;

        // Only handle relevant key strokes
        if (  keyCode == dojo.keys.BACKSPACE // Backspace
           || keyCode == dojo.keys.SPACE // Space
           || (45 < keyCode && keyCode < 112) 
           || keyCode > 123
           ) {

			// Retrieve the text currently in the entry field             
            var enteredText = this.queryField.value;

			// Check to see if auto-suggest is enabled    	
    		if (this.statics.autoSuggestEnabled == true) {
    		
	            // Cancel all of the timers
	            this.cancelAllTimers();

	            /*
	             * Make sure that there is actually something to submit in the
	             * entry field.
	             */
	            if (enteredText.length > 0) {
	
	                /* 
	                 * Create a timer to delay submission of the typed text.  This allows
	                 * users to type several characters in quick succession without
	                 * swamping WSRR with requests to retrieve suggested matches.  This
	                 * pattern is known as "submission throttling".
	                 */
					var widgetHandle = this;
	                this.submitTimer = setTimeout( function(){widgetHandle.retrieveSuggestions(keyCode)}
	                							 , this.statics.submitTimerInterval
	                							 );
	            } else {
	                /*
	                 * There is no text in entry field anymore.  It must have been deleted.
	                 * Clear out the suggestions array and hide any suggestions that are
	                 * currently being displayed.  Also, check to see if we are currently
	                 * waiting for a callback for a previous request.  If we are, cancel
	                 * the callback.
	                 */
	                this.suggestions = new Array();
	                this.hideSuggestions();
	                if (this.deferredSuggestObj != null) {
	                    this.deferredSuggestObj.cancel();
	                    this.deferredSuggestObj = null;
	                }
	            }
            }
            
			// Invoke the (hopefully overridden) onSearchStringChanged function
			this.onSearchStringChanged(enteredText);
        }
        
		// Log exit from the method        
    	//console.debug("<<< keyUpHandler");
    },

    /**
     * The keyDownHandler function is attached to the query entry field.  It will
     * be invoked when a key is pressed while the query entry field has focus.
     */
    keyDownHandler : function(inEvent){

		// Log entry to the method
    	//console.debug(">>> keyDownHandler(" + inEvent + ")");
    	//console.debug("key code: " + inEvent.keyCode);

        /*
         * We are only interested in events for the up arrow, down arrow space bar
         * and enter key
         */

        // Retrieve the rows in the suggestion table
        var suggestionRows = this.suggestionsDiv.getElementsByTagName("tr");
	
        switch(inEvent.keyCode) {
            // Up Arrow
            case dojo.keys.UP_ARROW: {
		
                /*
                 * Make sure that we are still within the bounds of the current
                 * suggestions array
                 */
                if (suggestionRows.length > 0 && this.cursor > 0) {

                    // Highlight the previous suggestion
                    var highlightRow = suggestionRows[--this.cursor];
                    this.highlightSuggestion(highlightRow);
				
                    // Set the value of the query field to the current suggestion
                    var nodeId = highlightRow.id;
                    if (nodeId != this.statics.MORE_SUGGESTIONS_ID) {
                        var suggestionIndex = nodeId.substr(this.statics.SUGGESTION_ID.length);
                        this.queryField.value = this.suggestions[suggestionIndex].name;
                    }
                }
                break;
            } // CASE

            // Down Arrow
            case dojo.keys.DOWN_ARROW: {
                /*
                 * Make sure that we are still within the bounds of the current
                 * suggestions array
                 */
                if (  suggestionRows.length > 0
                   && this.cursor < (suggestionRows.length - 1)
                   ) {
				
                    // Highlight the next suggestion
                    var highlightRow = suggestionRows[++this.cursor];
                    this.highlightSuggestion(highlightRow);
				
                    // Set the value of the query field to the current suggestion
                    var nodeId = highlightRow.id;
                    if (nodeId != this.statics.MORE_SUGGESTIONS_ID) {
                        var suggestionIndex = nodeId.substr(this.statics.SUGGESTION_ID.length);
                        this.queryField.value = this.suggestions[suggestionIndex].name;
                    }
                }
                break;
            } // CASE
		
            // Enter Key
            case dojo.keys.ENTER: {

                // Make sure that we have a valid cursor value
                if (this.cursor != -1) {
                    // Retrieve the row represented by the cursor
                    var currentRow = suggestionRows[this.cursor];
					
                    // See if the current node is the moreSuggestionsRow
                    if (currentRow.id == this.statics.MORE_SUGGESTIONS_ID) {
                        /*
                         * The user has pressed the enter key with the 
                         * moreSuggestionsRow selected.  Clear the relevant timers
                         * and show all of the suggestions to the user.
                         */
                        this.cancelHighlightFlashTimer();
                        this.cancelShowAllSuggestionsTimer();
                        this.currentMaxDisplaySuggestions = this.statics.SHOW_ALL_SUGGESTIONS;
                        this.showSuggestions(this.currentMaxDisplaySuggestions);
                        
						// Always consume the key down event in this situation.
						dojo.stopEvent(inEvent);
                    } else {

                        // Check to see whether we need to consume the event
                        if (this.consumeEnterKeyEvent == true) {
							dojo.stopEvent(inEvent);
                        }

                        // This is not the moreSuggestionsRow
                        // Hide the suggestions
                        this.hideSuggestions();

						/*
						 * Retrieve the suggestion represented by the current
						 * row and invoke the (hopefully overridden)
						 * onSuggestionSelected function
						 */
	                    var nodeId = currentRow.id;
                        var suggestionIndex = nodeId.substr(this.statics.SUGGESTION_ID.length);
						this.onSuggestionSelected(this.suggestions[suggestionIndex]);
					
                        // Reset the number of suggestions to display
                        this.currentMaxDisplaySuggestions = this.statics.maxDisplaySuggestions;
                    }
                }
                
                break;
            } // CASE
            
            // Escape key
            case dojo.keys.ESCAPE: {

				// Hide the suggestions
				this.hideSuggestions();
					
				// Clear the relevant timers
                this.cancelHighlightFlashTimer();
                this.cancelShowAllSuggestionsTimer();
					
				// Reset the number of suggestions to display
				this.currentMaxDisplaySuggestions = this.statics.maxDisplaySuggestions;
            
                break;
            } // CASE
        } // SWITCH
        
		// Log exit from the method        
    	//console.debug("<<< keyDownHandler");
    },

	/**
	 * The onFocusHandler function is attached to the query entry field.  It will
	 * be invoked when the query entry gets focus.
	 */
	onFocusHandler : function(inEvent){

		// Log entry to the method
    	//console.debug(">>> onFocusHandler(" + inEvent + ")");
	
		/*
		 * Retrieve the value of the autoSuggestEnabled preference from the WSRR
		 * preferences cookie.
		 */
		var autoSuggestEnabledPref = getPreferenceValue(this.statics.AUTO_SUGGEST_ENABLED_PREF);
		if (autoSuggestEnabledPref == null || autoSuggestEnabledPref == "true") {
        
			// Check to see if auto suggest is currently disabled
			if (this.statics.autoSuggestEnabled == false) {
			
				// Enable auto suggest
				this.statics.autoSuggestEnabled = true;
	
				// Initialize the cursor
				this.cursor = -1;
		
				/*
				 * Register the keyup, keydown, focus and blur handlers for the query
				 * field. The onblur event fires when the entry field loses focus.
				 */
		        this.connect(this.queryField, "onkeyup", "keyUpHandler");
		        this.keyDownHandle = this.connect(this.queryField, "onkeydown", "keyDownHandler");
		        this.blurHandle = this.connect(this.queryField, "onblur", "onBlurHandler");
			}
			
			// Retrieve and set the rest of the auto-suggest preferences
			
			// Case sensitive
			var caseSensitivePref = getPreferenceValue(this.statics.CASE_SENSITIVE_PREF);
			if (caseSensitivePref == null || caseSensitivePref == "false") {
				this.statics.caseSensitive = false;
			} else {
				this.statics.caseSensitive = true;
			}
			
			// Type ahead
			var typeAheadEnabledPref = getPreferenceValue(this.statics.TYPE_AHEAD_ENABLED_PREF);
			if (typeAheadEnabledPref == null || typeAheadEnabledPref == "true") {
				this.statics.displayTypeAhead = true;
			} else {
				this.statics.displayTypeAhead = false;
			}
	
			// Result count
			var resultCountEnabledPref = getPreferenceValue(this.statics.RESULT_COUNT_ENABLED_PREF);
			if (resultCountEnabledPref == null || resultCountEnabledPref == "true") {
				this.statics.displayResultCount = true;
			} else {
				this.statics.displayResultCount = false;
			}
			
			// Number of suggestions
			var numberOfSuggestionsPref = getPreferenceValue(this.statics.NUM_SUGGESTIONS_PREF);
			if (numberOfSuggestionsPref != null) {
				this.statics.maxDisplaySuggestions = numberOfSuggestionsPref;
				this.currentMaxDisplaySuggestions = this.statics.maxDisplaySuggestions;
			}
			
			// Submit delay
			var submitDelayPref = getPreferenceValue(this.statics.SUBMIT_DELAY_PREF);
			if (submitDelayPref != null) {
				this.statics.submitTimerInterval = submitDelayPref;
			}
		} else {
		
			/*
			 * Always hide and clear the current content of the suggestionsDiv.  Also,
			 * always reset the cursor because we are displaying new suggestions
			 */
            this.suggestionsDiv.style.display = "none";
			this.suggestionsDiv.innerHTML = "";
		    this.cursor = -1;
	
			// Disable auto suggest
			this.statics.autoSuggestEnabled = false;
		
			/*
			 * Disconnect the keyup, keydown, focus and blur handlers for the query
			 * field. The onblur event fires when the entry field loses focus.
			 */
			if (this.keyDownHandle) {
				this.disconnect(this.keyDownHandle);
			}
			if (this.blurHandle) {
				this.disconnect(this.blurHandle);
			}
		}
		
		// Log exit from the method        
    	//console.debug("<<< onFocusHandler");
	},

	/**
	 * The onBlurHandler function is attached to the query entry field.  It will
	 * be invoked when the query entry field loses focus.
	 */
	onBlurHandler : function(inEvent){

		// Log entry to the method
    	//console.debug(">>> onBlurHandler(" + inEvent + ")");

		/*
		 * Cancel all the timers and hide any suggestions
		 */
		this.cancelAllTimers();
		this.hideSuggestions();
		
		// Log exit from the method        
    	//console.debug("<<< onBlurHandler");
	},

	/**
	 * The retrieveSuggestions function is invoked when the submitTimer expires.
	 * It uses the JSONRpcClient to retrieve all of the items in WSRR whose name
	 * matches what the user has typed.
	 */
	retrieveSuggestions : function(keyCode) {

		// Log entry to the method
    	//console.debug(">>> retrieveSuggestions(" + keyCode + ")" );

		/*
		 * Retrieve the text currently in the entry field with leading and
		 * trailing spaces removed.
		 */
	    var enteredText = this.queryField.value;
	    
	   	// Make sure that some text was entered
	    if (enteredText.length > 0) {

			/*
			 * Retrieve the the list of suggestions asynchronously.  Use closure to
			 * ensure that we have access to the keyCode from within the callback
			 * function.
			 */
			var widgetHandle = this;
			var suggestionsCallback = function(newSuggestions) {

				// Store the suggestions in the widget
				widgetHandle.suggestions = newSuggestions;
				
			    // If we found some matches
			    if (widgetHandle.suggestions.length > 0) {
			    	/*
			    	 * Only perform type ahead in the entry field if the key pressed was
			    	 * not the delete (keycode = 46) or the backspace (keycode = 8) key.
			    	 */
			        if (keyCode != 46 && keyCode != 8) {
						widgetHandle.typeAhead(widgetHandle.suggestions[0].name);
			        }
			
					// Display the possible matches
					widgetHandle.showSuggestions(widgetHandle.currentMaxDisplaySuggestions);
			    } else {
			        
					// No matches found, hide the suggestions div
					widgetHandle.hideSuggestions();
				}
			}
			
			/*
			 * Check to see if we are currently waiting for a callback for a
			 * previous request.  If we are, cancel the callback before invoking
			 * the suggest service again and adding a new one.
			 */
			if (this.deferredSuggestObj != null) {
				this.deferredSuggestObj.cancel();
				this.deferredSuggestObj = null;
			}
			
			//Get the Generic Object Bean Uri, Edit Rel Uri  and the user correlator so we can suggest internal items
			var goBeanUriE = dojo.byId("genericObjectBeanUri");
			var editBeanUriE = dojo.byId("sourceObjectBsrUri");
			var correlatorE = dojo.byId("userStorageCorrelator");
			if (goBeanUriE)
				var goBeanUri = goBeanUriE.value;
			else
				var goBeanUri = "";

			if (editBeanUriE)
				var editBeanUri = editBeanUriE.value;
			else
				var editBeanUri = "";
				
			if (correlatorE)
				var correlator = parseInt(correlatorE.value);				
			else if (typeof userStorageCorrelator != "undefined")
				var correlator = parseInt(userStorageCorrelator);
			else
				var correlator = 0;

			this.deferredSuggestObj = this.suggestService.retrieveSuggestions(enteredText, this.type, this.statics.caseSensitive, goBeanUri, editBeanUri, correlator);
			this.deferredSuggestObj.addCallback(suggestionsCallback);
		}
		
		// Log exit from the method        
    	//console.debug("<<< retrieveSuggestions");
	},
	
	/**
	 * The trim function is utility function that removes leading and trailing
	 * spaces from the specified string.
	 */
	trim : function(inString) {
	
		// Log entry to the method
    	//console.debug(">>> trim(" + inString + ")");
	
		// Remove leading spaces
		while (inString.substring(0, 1) == ' ') {
			inString = inString.substring(1, inString.length);
		}
		
		// Remove trailing spaces
		while (inString.substring(inString.length - 1, inString.length) == ' ') {
			inString = inString.substring(0, inString.length - 1);
		}
		
		// Log exit from the method        
    	//console.debug("<<< trim " + inString);
		
		return inString;
	},

	/**
	 * The hideSuggestions function hides all of the suggestions.
	 */
	hideSuggestions : function() {
	
		// Log entry to the method
    	//console.debug(">>> hideSuggestions");
	
		// Hide the suggestions div
        this.suggestionsDiv.style.display = "none";
	
		// Clear the suggestionsDiv    
		this.suggestionsDiv.innerHTML = "";
	
	    // Reset the cursor
	    this.cursor = -1;
	    
		// Reset the number of suggestions to display
		this.currentMaxDisplaySuggestions = this.statics.maxDisplaySuggestions;
		
		// Log exit from the method        
    	//console.debug("<<< hideSuggestions");
	},

	/**
	 * The highlightSuggestion function highlights the specified suggestion.  It
	 * also ensures the highlight is removed from the previous selected
	 * suggestion.
	 */
	highlightSuggestion : function(suggestionNode) {
	
		// Log entry to the method
    	//console.debug(">>> highlightSuggestion(" + suggestionNode + ")");
	
		// Iterate over the rows in the suggestion table
		var suggestionRows = this.suggestionsDiv.getElementsByTagName("tr");
	    for (var i = 0; i < suggestionRows.length; i++) 
	    {
	    
	    	// Retrieve the current suggestion row
	    	var currentRow = suggestionRows[i];
	
			// See if this is the suggestion to highlight
			if (currentRow == suggestionNode) {
				/*
				 * Set the class for the suggestion to "current" so that the
				 * relevant style is applied to highlight it.
				 */
				currentRow.className = this.statics.CURRENT_ROW_CLASSNAME;
				
				// Set the cursor to the current suggestion
				this.cursor = i;

		        // Cancel the current highlight flash timer
		        this.cancelHighlightFlashTimer();
			    
			    // Cancel the current show all suggestions timer
			    this.cancelShowAllSuggestionsTimer();
	
				/*
				 * Retrieve the text for the current node.  It's buried in several
				 * children deep: TD -> DIV -> Text Node -> Node Value.
				 */ 
				 //Sometimes currentRow.firstChild.firstChild.firstChild == null, so guard against this
				var node = currentRow.firstChild.firstChild.firstChild;
				var nodeText = "";
				if (node)
				 nodeText = node.nodeValue;
				 
				if (nodeText == "...") {
					/*
					 * The selected row is the "more suggestions" row.  Set the
					 * relevant timers to flash the row and trigger the display of
					 * all of the suggestions.
					 */
					var widgetHandle = this;
					this.highlightFlashTimer = setTimeout( function(){widgetHandle.flashHighlight()}
					                                     , this.statics.highlightFlashTimerInterval
					                                     );
					this.showAllSuggestionsTimer = setTimeout( function(){widgetHandle.showAllSuggestions()}
					 										 , this.statics.showAllSuggestionsTimerInterval
					 										 );
				} else {
					// Retrieve the more suggestions row
					var moreSuggestionsRow = dojo.byId(this.statics.MORE_SUGGESTIONS_ID);
					if (moreSuggestionsRow) {
						// Reset the foreground and background colors
						moreSuggestionsRow.style.backgroundColor = "white";
						moreSuggestionsRow.style.color = "black";
					}
				}
			} else if (currentRow.className == this.statics.CURRENT_ROW_CLASSNAME) {
				/*
				 * This row is no longer the selected suggestion.  We need to set
				 * the empty the class name so that the highlight is removed.
				 */
				currentRow.className = "";
			}
		} // FOR
		
		// Log exit from the method        
    	//console.debug("<<< highlightSuggestion");
	},

	/**
	 * The flashHighlight function alternates the styles on the moreSuggestionsRow
	 * causing it flash.  This is only performed when the moreSuggestionsRow is
	 * highlighted using the arrow keys or by placing the mouse pointer over the
	 * row.
	 */
	flashHighlight : function() {
	
		// Log entry to the method
    	//console.debug(">>> flashHighlight");
	
		// Retrieve the more suggestions row
		var moreSuggestionsRow = dojo.byId(this.statics.MORE_SUGGESTIONS_ID);
		if (moreSuggestionsRow) {

			// Make sure that it's still the current row
			if (moreSuggestionsRow.className == this.statics.CURRENT_ROW_CLASSNAME) {

				// Retrieve the current highlight color
			    var backgroundColor = moreSuggestionsRow.style.backgroundColor;
				
				// Switch the background and foreground colors
				if	(  backgroundColor == "#0a246a" 
					|| backgroundColor == "#0A246A" 
					|| backgroundColor == "rgb(10, 36, 106)" // Firefox converts to RGB!!!
					|| backgroundColor == ""
					) {
					moreSuggestionsRow.style.backgroundColor = "white";
					moreSuggestionsRow.style.color = "black";
				} else {
					moreSuggestionsRow.style.backgroundColor = "#0a246a";
					moreSuggestionsRow.style.color = "white";
				}
	
				// Reset the timer to perform the next flash
				var widgetHandle = this;
				this.highlightFlashTimer = setTimeout( function(){widgetHandle.flashHighlight()}
				                                     , this.statics.highlightFlashTimerInterval
				                                     );
			} else {
	
				/*
				 * The moreSuggestionsRow is no longer selected.  Reset the
				 * foreground and background colors.
				 */
				moreSuggestionsRow.style.backgroundColor = "white";
				moreSuggestionsRow.style.color = "black";
				
				// Clear the flash timer variable
				this.highlightFlashTimer = null;
				
			    // Clear the current show all suggestions timer
			    this.cancelShowAllSuggestionsTimer();
			}
		}
		
		// Log exit from the method        
    	//console.debug("<<< flashHighlight");
	},

	/**
	 * The showSuggestions function displays the specified number of suggestions
	 * to the user.  If the total number of suggestions is greater than the number
	 * displayed, the moreSuggestionsRow is displayed to indicate this.
	 */
	showSuggestions : function(maxSuggestions) {
	
		// Log entry to the method
    	//console.debug(">>> showSuggestions(" + maxSuggestions + ")");
	
		/*
		 * Always hide and clear the current content of the suggestionsDiv.  Also,
		 * always reset the cursor because we are displaying new suggestions
		 */
        this.suggestionsDiv.style.display = "none";
		this.suggestionsDiv.innerHTML = "";
	    this.cursor = -1;
	
		// Create a variable to hold the HTML for the suggestion divs
	   	var suggestionsDivHtml = new dojox.string.Builder();
	    
	    suggestionsDivHtml.append("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");
	    
	    // See how many suggestions we want to display
	    if (maxSuggestions == this.statics.SHOW_ALL_SUGGESTIONS) {
	    
	    	// Iterate over all of the suggestions
			for (i=0; i < this.suggestions.length; i++) {
				// Create a suggestion div for the current result
				suggestionsDivHtml.append(this.createSuggestionDiv(this.suggestions[i].name, this.suggestions[i].resultsString, i));
			} // FOR
		} else {
	
	    	// Iterate over all of the specified number of suggestions
			for (i=0; i < this.suggestions.length && i < maxSuggestions; i++) {
				// Create a suggestion div for the current result
				suggestionsDivHtml.append(this.createSuggestionDiv(this.suggestions[i].name, this.suggestions[i].resultsString, i));
			} // FOR
		
			/*
			 * Add a node to indicate that there are more suggestions.  We do this
			 * by building a string for the HTML because manipulating the DOM
			 * model directly in IE using JavaScript does not perform well.
			 */
			if (this.suggestions.length > maxSuggestions) {
				suggestionsDivHtml.append("<tr id=\"" + this.statics.MORE_SUGGESTIONS_ID + "\"><td><div class=\"" + this.statics.RESULT_CLASSNAME + "\">...</div></td><td><div class=\"" + this.statics.COUNT_CLASSNAME + "\">&nbsp;</div></td></tr>");
			}
		}
	
	    suggestionsDivHtml.append("</table>");
	
		// Set the HTML for the suggestionsDiv
		this.suggestionsDiv.innerHTML = suggestionsDivHtml.toString();
        
        /*
         * We need to position and size the suggestionsDiv prior to display.
         * Because the AutoSuggest widget could be contained within a table,
         * we need to walk up the chain of offsetParents to calculate the
         * absolute x and y positions relative to the document body.
         */ 
        var x = 0;
        var y = 0;
        var offsetPointer = this.queryField;
        while (offsetPointer) {
            x += offsetPointer.offsetLeft;
            y += offsetPointer.offsetTop;
            offsetPointer = offsetPointer.offsetParent;
        }
        this.suggestionsDiv.style.top = (y + this.queryField.offsetHeight) + "px";
        this.suggestionsDiv.style.left = x + "px";

		/*
         * Display the suggestions and modify the width of the suggestions div
         * slightly to account for the miscalculation of the row width on
         * firefox.
		 */	
        this.suggestionsDiv.style.display = "block";
        var table = this.suggestionsDiv.firstChild;
        var corners={"BL":"TL","BR":"TR"};
        if (isRTL)
          corners={"BR":"TR","BL":"TL"};
        this.suggestionsDiv.style.width = table.offsetWidth + 2 + "px";
        dijit.placeOnScreenAroundElement(this.suggestionsDiv, this.queryField, corners);
		
		// Log exit from the method        
    	//console.debug("<<< showSuggestions");
	},

	/**
	 * The createSuggestionDiv function creates a div for each suggestion to be 
	 * displayed.
	 *
	 * In order to display the result and the number of matches in each suggest
	 * div, we need to render two columns.  To achieve this, we place two spans
	 * inside each div, one to hold the result and one to hold the number of
	 * matches for that result.  We set a suitable className for each span so
	 * that the correct style is applied to achieve the desired layout.  The
	 * format of the resulting div is:
	 *
	 *    <div><span class="result">XYZ</span><span class="count">123 results</span><br/></div>
	 * 
	 */
	createSuggestionDiv : function(suggestion, count, suggestionIndex) {
	
		// Log entry to the method
    	//console.debug(">>> createSuggestionDiv(" + suggestion + ", " + count + ", " + suggestionIndex + ")");
	
		/*
		 * Create the suggest div.  We do this by building a string for the HTML
		 * because manipulating the DOM model directly in IE using JavaScript does
		 * not perform well.
		 */
		var divHtml = new dojox.string.Builder();
		
		divHtml.append("<tr id=\"" + this.statics.SUGGESTION_ID + suggestionIndex + "\"><td><div class=\"" + this.statics.RESULT_CLASSNAME + "\">");
		divHtml.append(suggestion);
		divHtml.append("</div></td>");
		
		// Check to see if we are displaying the number of matches
        divHtml.append("<td><div class=\"" + this.statics.COUNT_CLASSNAME + "\">");
		if (this.statics.displayResultCount == true) {
			divHtml.append(count);
		} else {
            divHtml.append("&nbsp;");
        }
        divHtml.append("</div></td>");
		
		// Close the suggestion row
		divHtml.append("</tr>");
	
		// Log exit from the method        
    	//console.debug("<<< createSuggestionDiv " + divHtml.toString());
	
		// Return the HTML string for the div
		return divHtml.toString();
	},

	/**
	 * The showAllSuggestions function simply invokes the showSuggestions function
	 * passing SHOW_ALL_SUGGESTIONS (-1) as a parameter.
	 */
	showAllSuggestions : function() {
	
		// Log entry to the method
    	//console.debug(">>> showAllSuggestions");
	
		// Cancel the highlight flash timer
		this.cancelHighlightFlashTimer();
		this.currentMaxDisplaySuggestions = this.statics.SHOW_ALL_SUGGESTIONS;
		this.showSuggestions(this.currentMaxDisplaySuggestions);
		
		// Log exit from the method        
    	//console.debug("<<< showAllSuggestions");
	},
	
	/**
	 * The selectRange function selects the specified range of characters on the
	 * string contained within the query entry field.
	 */
	selectRange : function(start , end) {
	
		// Log entry to the method
    	//console.debug(">>> selectRange(" + start + ", " + end + ")");
	
		/*
		 * Use the relevant function depending on the browser type.
		 * createTextRange for IE, setSelectionRange for Mozilla.
		 */
		if (this.queryField.createTextRange) {
			var range = this.queryField.createTextRange();
			range.moveStart("character", start);
			range.moveEnd("character", end - this.queryField.value.length);
			range.select();
		} 
		else if (this.queryField.setSelectionRange) {
			this.queryField.setSelectionRange(start, end);
		}
		
		// Place the focus on the query entry field
		this.queryField.focus();     
		
		// Log exit from the method        
    	//console.debug("<<< selectRange");
	},
	
	/**
	 * The typeAhead function sets the value of the query field to specified
	 * suggestion, usually the first one in the ordered list of matches.  It then
	 * selects the characters that were added to those typed by the user, so that
	 * if the user types further characters, the selected characters are deleted.
	 */
	typeAhead : function(suggestion) {
	
		// Log entry to the method
    	//console.debug(">>> typeAhead(" + suggestion + ")");
	
		// Check to see if we are performing type ahead
		if (this.statics.displayTypeAhead == true) {
			if (this.queryField.createTextRange || this.queryField.setSelectionRange) {
				// Check to see if the typed text included a wildcard
				var index = this.queryField.value.indexOf("*");
				if (this.queryField.value.indexOf("*") == -1) {
					/*
					 * No wildcard in the search... simply replace the typed text
					 * with the suggestion text and set the select range
					 */
					var typedCharLength = this.queryField.value.length;
					this.queryField.value = suggestion;
					this.selectRange(typedCharLength, suggestion.length);
				} else {
					/*
					 * This is a little trickier since we implicitly wildcard the
					 * end of the search anyway, so we need to calculate if there
					 * are any chars in the suggestion beyond what the user has
					 * typed.
					 */
					var enteredText = this.queryField.value;
					var lastWildcardIndex = enteredText.lastIndexOf("*");
					var searchSuffix = enteredText.substr(lastWildcardIndex + 1);
					var lastOccurrenceIndex = suggestion.toUpperCase().lastIndexOf(searchSuffix.toUpperCase());
					if ((lastOccurrenceIndex + searchSuffix.length) < suggestion.length) {
						this.queryField.value = enteredText.substring(0, lastWildcardIndex + 1) + suggestion.substr(lastOccurrenceIndex);
						this.selectRange(enteredText.length, this.queryField.value.length);
					}
				}
			}
		}
		
		// Log exit from the method        
    	//console.debug("<<< typeAhead");
	},
	
	/**
	 * The cancelSubmitTimer function cancels the submitTimer and sets the timer
	 * variable to null.
	 */
	cancelSubmitTimer : function() {
	
		// Log entry to the method
    	//console.debug(">>> cancelSubmitTimer");
	
		// Clear the current submit timer
		if (this.submitTimer) {
			clearTimeout(this.submitTimer);
			this.submitTimer = null;
		}
		
		// Log exit from the method        
    	//console.debug("<<< cancelSubmitTimer");
	},
	
	/**
	 * The cancelHighlightFlashTimer function cancels the highlightFlashTimer and
	 * sets the timer variable to null.
	 */
	cancelHighlightFlashTimer : function() {
	
		// Log entry to the method
    	//console.debug(">>> cancelHighlightFlashTimer");
	
		// Clear the highlight flash timer
		if (this.highlightFlashTimer) {
			clearTimeout(this.highlightFlashTimer);
			this.highlightFlashTimer = null;
		}
		
		// Log exit from the method        
    	//console.debug("<<< cancelHighlightFlashTimer");
	},
	
	/**
	 * The cancelShowAllSuggestionsTimer function cancels the
	 * showAllSuggestionsTimer and sets the timer variable to null.
	 */
	cancelShowAllSuggestionsTimer : function() {
	
		// Log entry to the method
    	//console.debug(">>> cancelShowAllSuggestionsTimer");
	
		// Clear the show all suggestions timer
		if (this.showAllSuggestionsTimer) {
			clearTimeout(this.showAllSuggestionsTimer);
			this.showAllSuggestionsTimer = null;
		}
		
		// Log exit from the method        
    	//console.debug("<<< cancelShowAllSuggestionsTimer");
	},
	
	/**
	 * The cancelShowAllSuggestionsTimer function cancels all of the suggest
	 * timers.
	 */
	cancelAllTimers : function() {
	
		// Log entry to the method
    	//console.debug(">>> cancelAllTimers");
	
		this.cancelSubmitTimer();
		this.cancelHighlightFlashTimer();
		this.cancelShowAllSuggestionsTimer();
		
		// Log exit from the method        
    	//console.debug("<<< cancelAllTimers");
	},
	
	/**
	 * This is stub function that will be invoked when a suggestion is
	 * selected by the user using either the mouse or the keyboard.  Clients
	 * making use of this widget should override this method.
	 */
	onSuggestionSelected : function(suggestion) {
	
		// Log entry to the method
    	//console.debug(">>> onSuggestionSelected(" + suggestion + ")");

		// Log exit from the method        
    	//console.debug("<<< onSuggestionSelected");
	},
	
	/**
	 * This is stub function that will be invoked when the text in the query
	 * field is modified.  Clients making use of this widget should override
	 * this method if they need to be notified when the text changes.
	 */
	onSearchStringChanged: function(searchString) {
	
		// Log entry to the method
    	//console.debug(">>> onSearchStringChanged(" + searchString + ")");

		// Log exit from the method        
    	//console.debug("<<< onSearchStringChanged");
	},
	
	
	/*
	 * The setDisabled function sets the disabled state of the widget.
	 */
	setDisabled : function(/*Boolean*/ disabled){

		// Log entry to the method
    	//console.debug(">>> setDisabled(" + disabled + ")");

		this.domNode.disabled = this.disabled = this.queryField.disabled = disabled;
		if (this.queryField.isDisabled == true) {
			this.queryField.className = this.statics.TEXT_ENTRY_READ_ONLY_CLASSNAME;
		} else {
			this.queryField.className = this.statics.TEXT_ENTRY_CLASSNAME;
		}
		
		// Log exit from the method        
    	//console.debug("<<< setDisabled");
	},

	/*
	 * The clear function clears the current currents of the query entry field.
	 */	
	clear : function() {

		// Log entry to the method
    	//console.debug(">>> clear");

		this.queryField.value = "";
		
		// Log exit from the method        
    	//console.debug("<<< clear");
	}
});

