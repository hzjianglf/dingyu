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

dojo.provide("com.ibm.sr.widgets.DateTimePopup");
dojo.require("dojo.i18n");
dojo.require("dojo.cldr.supplemental");
dojo.require("dojo.date");
dojo.require("dojo.date.locale");

dojo.require("dijit._Widget");
dojo.require("dijit._Templated");
dojo.require("dijit.form.Button");

dojo.requireLocalization("com.ibm.sr.widgets.DateTimePopup", "DateTimePopupResources");

dojo.declare(
	"com.ibm.sr.widgets.DateTimePopup",
	[dijit._Widget, dijit._Templated],
	{
		/*
		summary:
			A popup for choosing a date and/or time with a month to view calendar and/or time chooser.

		description:
			This widget is used internally by other widgets and is not accessible
			as a standalone widget.
			This widget can't be used in a form because it doesn't serialize the date to an
			<input> field.  For a form element, use DateTextBox instead.

			Note that the parser takes all dates attributes passed in the `RFC 3339` format:
			http://www.faqs.org/rfcs/rfc3339.html (2005-06-30T08:05:00-07:00)
			so that they are serializable and locale-independent.

		usage:
			var calendar = new dijit._Calendar({}, dojo.byId("calendarNode"));
		 	-or-
			<div dojoType="dijit._Calendar"></div>
		*/

		templateString: null,
		// The path to the widgets template
		templatePath: dojo.moduleUrl("com.ibm.sr.widgets.DateTimePopup", "DateTimePopup.html"),

		// pulling in multiple widgets
		widgetsInTemplate:true,

		// value: Date
		// the currently selected Date
		value: new Date(),
		timeValue: new Date(),
		dateValue: new Date(),	

    /**
     * Define the hashtable of static configuration properties for the widget.
     */
    	statics: {
    		msgResourceKeys : [ "textHour"
    	                      , "textMinute"
        	              	  , "textSecond"
	                          , "buttonOK"
	                          , "buttonCancel"
	                          ]
	    },			

    constructor: function (args) {

		// Log entry to the method
    	//console.debug(">>> constructor");

		// Initialize the label attributes for the widget
		this.messages = dojo.i18n.getLocalization("com.ibm.sr.widgets.DateTimePopup", "DateTimePopupResources");
		dojo.forEach(this.statics.msgResourceKeys, function(prop){
			if(!this[prop]){ this[prop] = this.messages[prop]; }
		}, this);

		// Log exit from the method        
    	//console.debug("<<< constructor");
    },

		_setValueAttr: function(/*Date*/ value){

		this.value = value;

		if (this.constraints.selector == "date" || this.constraints.selector == "datetime") {

			this.dateselector.attr("value",value);
		}	
		if (this.constraints.selector == "time" || this.constraints.selector == "datetime") {
			this.timeselector.refresh(value);
		}
		},

		postCreate: function(){

			if (this.constraints.selector == "date") {
				// hide time selector
				dojo.style(this.timeselector.domNode, "display", "none");
			} else if (this.constraints.selector == "time") {
				// hide date selector
				dojo.style(this.dateselector.domNode, "display", "none");
			}

			this.value = null;
			this.attr("value",new Date());
		},

		onValueSelected: function(/*Date*/date){
			//summary: a date cell was selected.  It may be the same as the previous value.
		},

		onCancel: function() {
			// cancel button was clicked
		},

		_onOKClick: function(/*Event*/ evt){
			
			//var timeZoneOffsetMinutes = this.dateValue.getTimezoneOffset();
			var timeZoneOffsetMinutes = 0;

			// subtract the offset
			this.dateValue = dojo.date.add(this.dateValue, "minute", -timeZoneOffsetMinutes);

			var year = this.dateValue.getFullYear();
			var month = this.dateValue.getMonth();
			var day = this.dateValue.getDate();
			var hour = this.timeValue.getHours();
			var minute = this.timeValue.getMinutes();
			var second = this.timeValue.getSeconds();

//			var year = this.dateValue.getUTCFullYear();
//			var month = this.dateValue.getUTCMonth();
//			var day = this.dateValue.getUTCDate();
//			var hour = this.timeValue.getUTCHours();
//			var minute = this.timeValue.getUTCMinutes();
//			var second = this.timeValue.getUTCSeconds();

			if (this.constraints.selector == "date") {
				this.value = new Date(year, month, day);
				// this is still using locale offset!
			} else if (this.constraints.selector == "datetime") {
				this.value = new Date(year, month, day, hour, minute, second);
			} else if (this.constraints.selector == "time") {
				this.value = new Date(year, month, day, hour, minute, second);
			}			
 			this.onValueSelected(this.value);
		},

		_onCancelClick: function(/*Event*/ evt){
 			this.onCancel();
		},

		onClose: function(){
		},

		onChange: function(/*Date*/date){
			//summary: called only when the selected date has changed
		}, 

		_dateSelected: function(/*Event*/ evt){

			this.dateValue = evt;
		},

		_timeSelected: function(/*Event*/ evt){

			this.timeValue = evt;
		}
	}
);
