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

dojo.provide("com.ibm.sr.widgets.ValidationDigitBox");

dojo.require("dijit.form.ValidationTextBox");

dojo.declare(
  "com.ibm.sr.widgets.ValidationDigitBox",
  [dijit.form.ValidationTextBox],
{
  // summary: Validating digit input box
  // description: A number validator for inputting cultural digits

  isValid: function(/*Boolean*/ isFocused) {
    var normalisedValue=CharUtils.digitString(this.textbox.value);
    return this.validator(normalisedValue, this.constraints);
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

