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

dojo.provide("com.ibm.sr.widgets.PaddedSpinner");

dojo.require("dijit.form._Spinner");

dojo.declare(
"com.ibm.sr.widgets.PaddedSpinner",
[dijit.form._Spinner],
{
	// summary: Padded Spinner
	// description: A number spinner which pads string value with leading zeroes

	required: false,

	// false: number stops at extremes, true: number flips to opposite extreme
	rollover: false,

	// number of digits - if abs min/max > digits, the larger value is used
	digits: 2,

	adjust: function(/* Object */ val, /*Number*/ delta){
		newval = val+delta;

		// prevent trying to add to already invalid value
		if (isNaN(val) == true) {
			return this.constraints.min;
		}

		if((typeof this.constraints.max == "number") && (newval > this.constraints.max)){
			if (this.rollover==true) {
				newval = this.constraints.min;
			} else {
				newval = this.constraints.max;
			}
		}
		if((typeof this.constraints.min == "number") && (newval < this.constraints.min)){
			if (this.rollover==true) {
				newval = this.constraints.max;
			} else {
				newval = this.constraints.min;
			}
		}

		return newval;
	},

	adjusted: function(val){
		// callback hook-in
		return val;
	},

	_setValueAttr: function(val) {

		// call super method to set the value
		this.inherited('_setValueAttr', arguments);
		// allow listeners to detect successful change in value
		this.adjusted(val);
	},

	isValid: function(){

		var valid = true;
		var val = this.attr("value");

		if (typeof val != "number" || isNaN(parseInt(val,10)) ) {
			valid = false;
			//this.setValue(0);
		}

		if((typeof this.constraints.max == "number") && (val > this.constraints.max)){
			valid = false;
		}
		if((typeof this.constraints.min == "number") && (val < this.constraints.min)){
			valid = false;
		}

		return valid;
	},

	smallDelta: 1,

	largeDelta: 10,

	timeoutChangeRate: 0.50,	

	parse: function(val){
		// specify radix to prevent '09' being evaluated as 0
		var numericValue=parseInt(val,10);

		return numericValue;
	},

	format: function(val){

		var strVal = Math.abs(val).toString();
		var currDigits = strVal.length;
		var digitsToAdd = this.digits - currDigits;
		var zeroes = "";

		for (var i = 0; i < digitsToAdd; i++) {
			zeroes+="0";
		} 
		// add a '-' if negative
		var sign = (val < 0) ? "-":"";
		val = sign + zeroes + strVal;

		return val;
	},

	serialize: function(val){
		return ""+val;
	},

	value: "12",

	calcMaxDigits: function(max, min) {
		var absMax = Math.abs(max);
		var absMin = Math.abs(min);
		var maxLimit = Math.max(absMax, absMin);

		// this is the max according to limits of constraints
		var maxDigits = maxLimit.toString().length;

		// check if the user-specified limit is higher
 		maxDigits = Math.max(maxDigits, this.digits);
		
		return maxDigits;
	},

	postCreate: function(){
		//adjust the max digits according to constraints
		this.digits=this.calcMaxDigits(this.constraints.max, this.constraints.min);

		// call super method to format the display
		this.inherited('postCreate', arguments);
	},

	onChange: function(newValue){
		//this.inherited('onChange', arguments);
	},

	_onKeyPress: function(evt) {
  }
});

