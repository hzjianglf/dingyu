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

dojo.provide("com.ibm.sr.widgets.LongInteger");

dojo.require("dijit.form.ValidationTextBox");

dojo.declare(
  "com.ibm.sr.widgets.LongInteger",
  [dijit.form.RangeBoundTextBox],
{
  // summary: Long Integer
  // description: A number validator for long integer to overcome 32-bit precision limitation of NumberTextBox
  // Max long is: 9223372036854775807, Min long: -9223372036854775808

  required: false,

  _MAX_OUT_OF_PRECISION: 5807,
  _MAX_IN_PRECISION: 922337203685477,

  regExp: "[\\+-]?(0|[1-9]\\d*)",

  isValid: function(/*Boolean*/ isFocused) {
    var normalisedValue=CharUtils.digitString(this.textbox.value);
    return this.validator(normalisedValue, this.constraints);
  },

  isInRange: function(/*Boolean*/ isFocused) {
    var valueString=CharUtils.digitString(this.textbox.value);
    var signPrefix = valueString.match("^[\-\+]?");
    if (signPrefix == "-") {
      valueString = valueString.substr(1);
    } else if (signPrefix ==  "+") {
      valueString = valueString.substr(1);        
    }
    // check range for in-precision part
    var inPrecisionPart = valueString.substr(0,15);
    var digits = valueString.length;
    if (digits > 15) {
      // also check the out of precision digits
      var outOfPrecisionPart = valueString.substr(15);
      // if negative bump the abs limit by 1
      var outOfPrecisionLimit = this._MAX_OUT_OF_PRECISION + ((signPrefix=="-")?1:0);
      var maxOutOfPrecisionLength = this._MAX_OUT_OF_PRECISION.toString().length;
      // check if out of precision part exceeds limit when in precision part is fully populated
      if ((inPrecisionPart >= this._MAX_IN_PRECISION) && (outOfPrecisionPart > outOfPrecisionLimit)) {
        // out of range
        return false;
        // now check for case where out of precision part is longer than 4 digits, e.g. '00000'
      } else if ((inPrecisionPart < this._MAX_IN_PRECISION) && (outOfPrecisionPart.length > maxOutOfPrecisionLength)) {
        // out of range
        return false;
      }
    }
    return true;
  },

  postCreate: function() {
    this.inherited(arguments);
    this.digitRange=getPreferenceValue("preferred.digits");
    if (this.digitRange==null)
      this.digitRange="48";
    var displayValue=CharUtils.convertDigits(this.textbox.value,this.digitRange);
    this.textbox.value=displayValue;
  }
});

