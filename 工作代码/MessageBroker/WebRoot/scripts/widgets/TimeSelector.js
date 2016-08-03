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

dojo.provide("com.ibm.sr.widgets.TimeSelector");

dojo.require("dijit._Widget");
dojo.require("dijit._Templated");
dojo.require("dojo.date.locale");

dojo.declare("com.ibm.sr.widgets.TimeSelector",
	[dijit._Widget, dijit._Templated],
	{
		// summary:
		// A time picker based on spinner controls for hours, minutes, seconds

		// The path to the widgets template
		templatePath: dojo.moduleUrl("com.ibm.sr.widgets.TimeSelector", "TimeSelector.html"),

		widgetsInTemplate:true,

	//	baseClass: "dijitTimePicker",

		timeValue: "",
		hoursLabel: "HH",
		minutesLabel: "MM",
		secondsLabel: "SS",

		// value: String
		//		Date to display.
		//		Defaults to current time and date.
		//		Can be a Date object or an ISO-8601 string
		//		If you specify the GMT time zone ("-01:00"),
		//		the time will be converted to the local time in the local time zone.
		//		Otherwise, the time is considered to be in the local time zone.
		//		If you specify the date and isDate is true, the date is used.
		//		Example: if your local time zone is GMT -05:00,
		//		"10:00:00" becomes "10:00:00-05:00" (considered to be local time),
		//		"10:00:00-01:00" becomes "06:00:00-05:00" (4 hour difference),
		//		"10:00:00Z" becomes "05:00:00-05:00" (5 hour difference between Zulu and local time)
		//		"yyyy-mm-ddThh:mm:ss" is the format to set the date and time
		//		Example: "2007-06-01T09:00:00"
		value: new Date(),

		constraints:{selector:'time', formatLength:'medium'},

		serialize: function(/*Date*/dateObject, /*dojo.date.stamp.__Options?*/options) {
		  var formattedValue=dojo.date.stamp.toISOString(dateObject,options);
		  var firstChar=formattedValue.charAt(0);
		  if (firstChar=="T")
		    formattedValue=formattedValue.substr(1);
		  return(formattedValue);
		},

		_setValueAttr:function(/*Date*/ date, /*Boolean*/ priority){
			// summary:
			//	Set the value of the TimePicker
			//	Redraws the TimePicker around the new date

			//dijit._TimePicker.superclass.setValue.apply(this, arguments);
			this.value=date;
			this._showText();
			this.updateTime(date);
			//console.log("after setValue, this.value: " + this.value);
		},

		_getValueAttr:function() {
			return this.value;
		},

		_showText:function(){

		},


		postCreate:function(){

			// instantiate constraints
			if(!this.constraints){
				this.constraints={};
			}
			// dojo.date.locale needs the lang in the constraints as locale
			if(!this.constraints.locale){
				this.constraints.locale=this.lang;
			}

			this.inherited("postCreate", arguments);
			this.attr("value",this.value);

		},

		onValueSelected:function(value){
		},

		spinnerchange: function(val){

		// now get the values from the spinners
		var hours = this.hoursSpinner.attr("value");
		var minutes = this.minutesSpinner.attr("value");
		var seconds = this.secondsSpinner.attr("value");
		var timeValue = new Date();
		timeValue.setHours(hours, minutes, seconds);
		this.attr("value",timeValue);
		this.timeValue = this.attr("value");
		this.timeValueNode.innerHTML = dojo.date.locale.format(timeValue, this.constraints);
		//console.log("after spinnerchange, this.timeValueNode.innerHTML: " + this.timeValueNode.innerHTML);
		},

		updateTime: function(/*Date*/ date){
		},

		refresh: function(date) {
			
			var hours = date.getHours();
			var minutes = date.getMinutes();
			var seconds = date.getSeconds();

			this.hoursSpinner.attr("value",hours);
			this.minutesSpinner.attr("value",minutes);
			this.secondsSpinner.attr("value",seconds);

			this.timeValue = date;

			this.timeValueNode.innerHTML = dojo.date.locale.format(date, this.constraints);
		}, 
		focus: function(){
			//console.log("TimeSelector.focus");
			//this.hoursSpinner.focus();
		}
	}
);
