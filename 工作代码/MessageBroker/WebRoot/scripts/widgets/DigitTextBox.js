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

dojo.provide("com.ibm.sr.widgets.DigitTextBox");

dojo.require("dijit.form.NumberTextBox");

dojo.declare(
  "com.ibm.sr.widgets.DigitTextBox",
  [dijit.form.NumberTextBox],
{
  // summary: Digit text box
  // description: A number validator that handles cultural digit input

  constructor: function (args) {
    this.digitRange=getPreferenceValue("preferred.digits");
    if (this.digitRange==null)
      this.digitRange="48";
  },

  isValid: function(/*Boolean*/ isFocused){
    var normalisedValue=CharUtils.digitString(this.textbox.value);
    var checkIt=this.validator(normalisedValue, this.constraints);
    return checkIt && ((this._isEmpty(normalisedValue) && !this.required) || this.isInRange(isFocused)); // Boolean
  },

  _formatter: function(/*Number*/value, /*dojo.number.__FormatOptions?*/options) {
    var displayValue=dojo.number.format(value,options);
    displayValue=CharUtils.convertDigits(displayValue,this.digitRange);
    return(displayValue);
  },

  parse: function(/*String*/value, /*dojo.number.__ParseOptions?*/constraints) {
    //  summary: parses the value as a Number, according to constraints
    var normalisedValue=CharUtils.digitString(value);
    return(dojo.number.parse(normalisedValue));
  },

  postCreate: function() {
    this.inherited(arguments);
    var displayValue=CharUtils.convertDigits(this.textbox.value,this.digitRange);
    this.textbox.value=displayValue;
  }

});

