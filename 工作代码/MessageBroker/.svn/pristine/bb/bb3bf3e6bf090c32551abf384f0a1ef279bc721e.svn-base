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

dojo.provide("com.ibm.sr.widgets.DateTimeTextBox");
dojo.require("dojo.i18n");
dojo.require("dijit.form._DateTimeTextBox");

dojo.requireLocalization("com.ibm.sr.widgets.DateTimeTextBox", "DateTimeTextBoxResources");

dojo.declare("com.ibm.sr.widgets.DateTimeTextBox", [dijit.form._DateTimeTextBox],{

		// default setting if no attribute specified
		selector: "date",

		baseClass: "dijitTextBox",

		// The path to the widgets template
		templatePath: dojo.moduleUrl("com.ibm.sr.widgets.DateTimeTextBox", "DateTimeTextBox.html"),

		templateString: "",
		
		iconTitle: "",
		
		/**
		 * Define the hashtable of static configuration properties for the widget.
		 */
		statics: {
			msgResourceKeys : [ "dateIconText"
                          , "timeIconText"
                          , "dateTimeIconText"
                          ]
        },		

    regExpGen: function(/*dijit.form.ValidationTextBox.__Constraints*/constraints){
      var plainTextExpr=dojo.date.locale.regexp(constraints);
      var isoExpr=null;
      if (constraints.selector=="time")
        isoExpr="?:(\\d{2}):(\\d{2})(?::(\\d{2})(.\\d+)?)?((?:[+-](\\d{2}):(\\d{2}))|Z)?";
      else if (constraints.selector=="date")
        isoExpr="?:(\\d{4})(?:-(\\d{2})(?:-(\\d{2}))?)?";
      else if (constraints.selector=="datetime")
        isoExpr="(?:(\\d{4})(?:-(\\d{2})(?:-(\\d{2}))?)?)?(?:T(\\d{2}):(\\d{2})(?::(\\d{2})(.\\d+)?)?((?:[+-](\\d{2}):(\\d{2}))|Z)?)";
      var expression="("+plainTextExpr+")";
      if (isoExpr)
        expression=expression+"|("+isoExpr+")";
      return expression;
    },
    
    parse: function(/*String*/ value, /*dojo.date.locale.__FormatOptions*/ constraints){
      //  summary: parses the value as a Date, according to constraints
      var parsedDate=dojo.date.locale.parse(value, constraints) || undefined; /* can't return null to getValue since that's special */
      if (!parsedDate && value)
      {
        parsedDate=dojo.date.stamp.fromISOString(value);
        if (parsedDate==null)
          parsedDate=undefined;
      }
      if (parsedDate && constraints.selector=="time")
      {
        var currentDate=new Date();
        currentDate.setHours(parsedDate.getHours(),parsedDate.getMinutes(),parsedDate.getSeconds());
        parsedDate=currentDate;
      }
      return parsedDate;
    },

		postMixInProperties: function(){

			this.inherited('postMixInProperties', arguments);
			this.popupClass= "com.ibm.sr.widgets.DateTimePopup";

			if (this.selector=="date") {
				this.constraints.selector = 'date';
				this.iconTitle = this.dateIconText;
	
			} else if (this.selector=="time") {
				this.constraints.selector = 'time';
				this.iconTitle = this.timeIconText;
	
			} else if (this.selector=="datetime") {
				this.constraints.selector = 'datetime';
				this.iconTitle = this.dateTimeIconText;
			}
		},
				
	    constructor: function (args) {
			// Log entry to the method
	    	//console.debug(">>> constructor");
	
			// Initialize the label attributes for the widget
			this.messages = dojo.i18n.getLocalization("com.ibm.sr.widgets.DateTimeTextBox", "DateTimeTextBoxResources");
			dojo.forEach(this.statics.msgResourceKeys, function(prop){
				if(!this[prop]){ this[prop] = this.messages[prop]; }
			}, this);
	
			// Log exit from the method        
	    	//console.debug("<<< constructor");
	    },		

		postCreate:function(){
			if (this.selector=="date") {
				this.propertyIconNode.className="dateicon";

			} else if (this.selector=="time") {
				this.propertyIconNode.className="timeicon";

			} else if (this.selector=="datetime") {
				this.propertyIconNode.className="datetimeicon";

			}
      this.textbox.name=""; // clear name on presentation input
			this.inherited('postCreate', arguments);

		},

		
		mouseDown: function(evt) {

			this._open();
		},

		_open: function() {
			var self = this;

			if(!this._picker){

				// get the prototype of the popup picker
				var popupProto=dojo.getObject(this.popupClass, false);
				// create a new picker
				this._picker = new popupProto({

					// add an event handler
					onValueSelected: function(value){
						// self is the reference to the input text box *****!

// Note the following methods were active for TimeTextBox, but we don't want the popup to close when a value is selected - well only when OK button says value is selected
						self.focus(); // focus the textbox before the popup closes to avoid reopening the popup
						setTimeout(dojo.hitch(self, "_close"), 1); // allow focus time to take

			// set the value on the text box (self) according to what was selected in the picker
						// this will cause InlineEditBox and other handlers to do stuff so make sure it's last
						com.ibm.sr.widgets.DateTimeTextBox.superclass.attr.call(self, "value", value);

					},
					
					// close the widget, don't set value
					onCancel: function() {
						self.focus(); // focus the textbox before the popup closes to avoid reopening the popup
						setTimeout(dojo.hitch(self, "_close"), 1); // allow focus time to take
					},

					lang: this.lang,
					constraints:this.constraints,
					
					isDisabledDate: function(/*Date*/ date){
						// summary:
						// 	disables dates outside of the min/max of the TimeTextBox
						return self.constraints && (dojo.date.compare(self.constraints.min,date) > 0 || dojo.date.compare(self.constraints.max,date) < 0);
					}
					
					
				});
			}

			this._picker.attr("value",(this.attr("value") || new Date()));

			this.inherited('_open', arguments);
		},

	buildRendering:  function(){
		this.inherited('buildRendering', arguments);
	},
		toggleOpenClose: function() {
			if(!this._opened){
				this._open();
			} else {
				this._close();
			}
		},						
		toggleClose: function() {
			if(this._opened){
				this._close();
			}
		},
		toggleOpen: function() {
			if(!this._opened){
				this._open();
			}else{
				// give focus to the popup
			}			
		},

		handleKeys: function(/*Event*/ evt){
			// summary: handles keyboard events

			//except for pasting case - ctrl + v(118)
			if(evt.altKey || (evt.ctrlKey && evt.charCode != 118)){
				return;
			}

			if (evt.keyCode == 0) {
				// check for charCode

				switch(evt.charCode){
					case dojo.keys.SPACE:
						// prevent submitting form if user presses enter
						this.toggleOpenClose();
						dojo.stopEvent(evt);
						break;
	
						default:// non char keys (F1-F12 etc..)  shouldn't open list
						//dojo.stopEvent(evt);
						break;
				}
			} else {
				// check for keyCode

				switch(evt.keyCode){
					case dojo.keys.PAGE_DOWN:
					case dojo.keys.RIGHT_ARROW: // fall through
					case dojo.keys.DOWN_ARROW:
						this.toggleOpen();
						dojo.stopEvent(evt);
						break;
	
					case dojo.keys.PAGE_UP:
					case dojo.keys.LEFT_ARROW: // fall through
					case dojo.keys.UP_ARROW:
						this.toggleClose();
						dojo.stopEvent(evt);
						break;
	
					case dojo.keys.SPACE:
					case dojo.keys.ENTER:
						// prevent submitting form if user presses enter
						this.toggleOpenClose();
						dojo.stopEvent(evt);
						break;
	
					case dojo.keys.TAB:
						break;
	
					case dojo.keys.ESCAPE:
						break;
	
					case dojo.keys.DELETE:
					case dojo.keys.BACKSPACE:
	
					default:// non char keys (F1-F12 etc..)  shouldn't open list
						//dojo.stopEvent(evt);
						break;
				}
			} 
		},
		_onFocus: function(/*Event*/ evt){
			// override this to prevent the popup showing when field gets focus
		}
	}
);
