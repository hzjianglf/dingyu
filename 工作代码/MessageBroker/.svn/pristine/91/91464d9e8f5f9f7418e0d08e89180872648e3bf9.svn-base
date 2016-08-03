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

// The array that will the bit flags for the characters
var CHARACTERS = null;
var DIGIT_MAP = null;

// utils class
var CharUtils={};

// The mask that will be applied to the bit flags
var NCNAME_START_CHAR_MASK = 0x01;
var NCNAME_CHAR_MASK = 0x02;
var DIGIT_CHAR_MASK = 0x04;

// Arrays used to initialize the CHARACTERS array
var nameStartChars = [0x005F]; // '_'... NOTE: No colon!!!
var letterRanges = [0x0041, 0x005A, 0x0061, 0x007A, 0x00C0, 0x00D6, 0x00D8, 0x00F6, 0x00F8, 0x0131, 0x0134, 0x013E, 0x0141, 0x0148, 0x014A, 0x017E, 0x0180, 0x01C3, 0x01CD, 0x01F0, 0x01F4, 0x01F5, 0x01FA, 0x0217, 0x0250, 0x02A8, 0x02BB, 0x02C1, 0x0388, 0x038A, 0x038E, 0x03A1, 0x03A3, 0x03CE, 0x03D0, 0x03D6, 0x03E2, 0x03F3, 0x0401, 0x040C, 0x040E, 0x044F, 0x0451, 0x045C, 0x045E, 0x0481, 0x0490, 0x04C4, 0x04C7, 0x04C8, 0x04CB, 0x04CC, 0x04D0, 0x04EB, 0x04EE, 0x04F5, 0x04F8, 0x04F9, 0x0531, 0x0556, 0x0561, 0x0586, 0x05D0, 0x05EA, 0x05F0, 0x05F2, 0x0621, 0x063A, 0x0641, 0x064A, 0x0671, 0x06B7, 0x06BA, 0x06BE, 0x06C0, 0x06CE, 0x06D0, 0x06D3, 0x06E5, 0x06E6, 0x0905, 0x0939, 0x0958, 0x0961, 0x0985, 0x098C, 0x098F, 0x0990, 0x0993, 0x09A8, 0x09AA, 0x09B0, 0x09B6, 0x09B9, 0x09DC, 0x09DD, 0x09DF, 0x09E1, 0x09F0, 0x09F1, 0x0A05, 0x0A0A, 0x0A0F, 0x0A10, 0x0A13, 0x0A28, 0x0A2A, 0x0A30, 0x0A32, 0x0A33, 0x0A35, 0x0A36, 0x0A38, 0x0A39, 0x0A59, 0x0A5C, 0x0A72, 0x0A74, 0x0A85, 0x0A8B, 0x0A8F, 0x0A91, 0x0A93, 0x0AA8, 0x0AAA, 0x0AB0, 0x0AB2, 0x0AB3, 0x0AB5, 0x0AB9, 0x0B05, 0x0B0C, 0x0B0F, 0x0B10, 0x0B13, 0x0B28, 0x0B2A, 0x0B30, 0x0B32, 0x0B33, 0x0B36, 0x0B39, 0x0B5C, 0x0B5D, 0x0B5F, 0x0B61, 0x0B85, 0x0B8A, 0x0B8E, 0x0B90, 0x0B92, 0x0B95, 0x0B99, 0x0B9A, 0x0B9E, 0x0B9F, 0x0BA3, 0x0BA4, 0x0BA8, 0x0BAA, 0x0BAE, 0x0BB5, 0x0BB7, 0x0BB9, 0x0C05, 0x0C0C, 0x0C0E, 0x0C10, 0x0C12, 0x0C28, 0x0C2A, 0x0C33, 0x0C35, 0x0C39, 0x0C60, 0x0C61, 0x0C85, 0x0C8C, 0x0C8E, 0x0C90, 0x0C92, 0x0CA8, 0x0CAA, 0x0CB3, 0x0CB5, 0x0CB9, 0x0CE0, 0x0CE1, 0x0D05, 0x0D0C, 0x0D0E, 0x0D10, 0x0D12, 0x0D28, 0x0D2A, 0x0D39, 0x0D60, 0x0D61, 0x0E01, 0x0E2E, 0x0E32, 0x0E33, 0x0E40, 0x0E45, 0x0E81, 0x0E82, 0x0E87, 0x0E88, 0x0E94, 0x0E97, 0x0E99, 0x0E9F, 0x0EA1, 0x0EA3, 0x0EAA, 0x0EAB, 0x0EAD, 0x0EAE, 0x0EB2, 0x0EB3, 0x0EC0, 0x0EC4, 0x0F40, 0x0F47, 0x0F49, 0x0F69, 0x10A0, 0x10C5, 0x10D0, 0x10F6, 0x1102, 0x1103, 0x1105, 0x1107, 0x110B, 0x110C, 0x110E, 0x1112, 0x1154, 0x1155, 0x115F, 0x1161, 0x116D, 0x116E, 0x1172, 0x1173, 0x11AE, 0x11AF, 0x11B7, 0x11B8, 0x11BC, 0x11C2, 0x1E00, 0x1E9B, 0x1EA0, 0x1EF9, 0x1F00, 0x1F15, 0x1F18, 0x1F1D, 0x1F20, 0x1F45, 0x1F48, 0x1F4D, 0x1F50, 0x1F57, 0x1F5F, 0x1F7D, 0x1F80, 0x1FB4, 0x1FB6, 0x1FBC, 0x1FC2, 0x1FC4, 0x1FC6, 0x1FCC, 0x1FD0, 0x1FD3, 0x1FD6, 0x1FDB, 0x1FE0, 0x1FEC, 0x1FF2, 0x1FF4, 0x1FF6, 0x1FFC, 0x212A, 0x212B, 0x2180, 0x2182, 0x3041, 0x3094, 0x30A1, 0x30FA, 0x3105, 0x312C, 0xAC00, 0xD7A3, 0x3021, 0x3029, 0x4E00, 0x9FA5];
var letterChars = [0x0386, 0x038C, 0x03DA, 0x03DC, 0x03DE, 0x03E0, 0x0559, 0x06D5, 0x093D, 0x09B2, 0x0A5E, 0x0A8D, 0x0ABD, 0x0AE0, 0x0B3D, 0x0B9C, 0x0CDE, 0x0E30, 0x0E84, 0x0E8A, 0x0E8D, 0x0EA5, 0x0EA7, 0x0EB0, 0x0EBD, 0x1100, 0x1109, 0x113C, 0x113E, 0x1140, 0x114C, 0x114E, 0x1150, 0x1159, 0x1163, 0x1165, 0x1167, 0x1169, 0x1175, 0x119E, 0x11A8, 0x11AB, 0x11BA, 0x11EB, 0x11F0, 0x11F9, 0x1F59, 0x1F5B, 0x1F5D, 0x1FBE, 0x2126, 0x212E, 0x3007];
var nameChars = [0x002D, 0x002E]; // '.' and '-'
var digitRanges = [0x0030, 0x0039, 0x0660, 0x0669, 0x06F0, 0x06F9, 0x0966, 0x096F, 0x09E6, 0x09EF, 0x0A66, 0x0A6F, 0x0AE6, 0x0AEF, 0x0B66, 0x0B6F, 0x0BE7, 0x0BEF, 0x0C66, 0x0C6F, 0x0CE6, 0x0CEF, 0x0D66, 0x0D6F, 0x0E50, 0x0E59, 0x0ED0, 0x0ED9, 0x0F20, 0x0F29];
var extraDigitRanges = [0x07c0, 0x07c9, 0x1040, 0x1049, 0x1090, 0x1099, 0x17e0, 0x17e9, 0x1810, 0x1819, 0x1946, 0x194f, 0x19d0, 0x19d9, 0x1b50, 0x1b59, 0x1bb0, 0x1bb9, 0x1c40, 0x1c49, 0x1c50, 0x1c59, 0xa620, 0xa629, 0xa8d0, 0xa8d9, 0xa900, 0xa909, 0xaa50, 0xaa59, 0xff10, 0xff19];
var combiningCharRanges = [0x0300, 0x0345, 0x0360, 0x0361, 0x0483, 0x0486, 0x0591, 0x05A1, 0x05A3, 0x05B9, 0x05BB, 0x05BD, 0x05C1, 0x05C2, 0x064B, 0x0652, 0x06D6, 0x06DC, 0x06DD, 0x06DF, 0x06E0, 0x06E4, 0x06E7, 0x06E8, 0x06EA, 0x06ED, 0x0901, 0x0903, 0x093E, 0x094C, 0x0951, 0x0954, 0x0962, 0x0963, 0x0981, 0x0983, 0x09C0, 0x09C4, 0x09C7, 0x09C8, 0x09CB, 0x09CD, 0x09E2, 0x09E3, 0x0A40, 0x0A42, 0x0A47, 0x0A48, 0x0A4B, 0x0A4D, 0x0A70, 0x0A71, 0x0A81, 0x0A83, 0x0ABE, 0x0AC5, 0x0AC7, 0x0AC9, 0x0ACB, 0x0ACD, 0x0B01, 0x0B03, 0x0B3E, 0x0B43, 0x0B47, 0x0B48, 0x0B4B, 0x0B4D, 0x0B56, 0x0B57, 0x0B82, 0x0B83, 0x0BBE, 0x0BC2, 0x0BC6, 0x0BC8, 0x0BCA, 0x0BCD, 0x0C01, 0x0C03, 0x0C3E, 0x0C44, 0x0C46, 0x0C48, 0x0C4A, 0x0C4D, 0x0C55, 0x0C56, 0x0C82, 0x0C83, 0x0CBE, 0x0CC4, 0x0CC6, 0x0CC8, 0x0CCA, 0x0CCD, 0x0CD5, 0x0CD6, 0x0D02, 0x0D03, 0x0D3E, 0x0D43, 0x0D46, 0x0D48, 0x0D4A, 0x0D4D, 0x0E34, 0x0E3A, 0x0E47, 0x0E4E, 0x0EB4, 0x0EB9, 0x0EBB, 0x0EBC, 0x0EC8, 0x0ECD, 0x0F18, 0x0F19, 0x0F71, 0x0F84, 0x0F86, 0x0F8B, 0x0F90, 0x0F95, 0x0F99, 0x0FAD, 0x0FB1, 0x0FB7, 0x20D0, 0x20DC, 0x302A, 0x302F];
var combiningCharChars = [0x05BF, 0x05C4, 0x0670, 0x093C, 0x094D, 0x09BC, 0x09BE, 0x09BF, 0x09D7, 0x0A02, 0x0A3C, 0x0A3E, 0x0A3F, 0x0ABC, 0x0B3C, 0x0BD7, 0x0D57, 0x0E31, 0x0EB1, 0x0F35, 0x0F37, 0x0F39, 0x0F3E, 0x0F3F, 0x0F97, 0x0FB9, 0x20E1, 0x3099, 0x309A];
var extenderRanges = [0x3031, 0x3035, 0x309D, 0x309E, 0x30FC, 0x30FE];
var extenderChars = [0x00B7, 0x02D0, 0x02D1, 0x0387, 0x0640, 0x0E46, 0x0EC6, 0x3005];

/**
 * The initialize function initializes the elements of the CHARACTERS array.
 * Each element represents the character whose unicode character code is equal
 * to the index of the element in the array, e.g., the 32nd element of the
 * array is used to represent the space ' ' character (unicode character code
 * 32).
 *
 * Each element is initialized with a value that indicates whether that
 * character represents a valid NC name start character or NC name character.
 * Performing a bitwise and of a value with a suitable masks indicates whether
 * the relevant character represents a valid NC name start character or NC
 * name character.
 */
function initialize() {

  // Create the character array
  CHARACTERS = new Array();
  // digit map converts cultural digits to European digits
  DIGIT_MAP = new Array();

  // Set the bit flags for all of the valid NC name start characters
  for (var i = 0; i < nameStartChars.length; i++) {
    CHARACTERS[nameStartChars[i]] |= NCNAME_START_CHAR_MASK | NCNAME_CHAR_MASK;
  }
  for (var i = 0; i < letterRanges.length; i += 2) {
    for (var j = letterRanges[i]; j <= letterRanges[i + 1]; j++) {
      CHARACTERS[j] |= NCNAME_START_CHAR_MASK | NCNAME_CHAR_MASK;
    }
  }
  for (var i = 0; i < letterChars.length; i++) {
    CHARACTERS[letterChars[i]] |= NCNAME_START_CHAR_MASK | NCNAME_CHAR_MASK;
  }

  // Set the bit flags for all of the remaining valid NC name characters
  for (var i = 0; i < nameChars.length; i++) {
    CHARACTERS[nameChars[i]] |= NCNAME_CHAR_MASK;
  }
  for (var i = 0; i < digitRanges.length; i += 2) {
    var firstDigit=digitRanges[i];
    for (var j = digitRanges[i]; j <= digitRanges[i + 1]; j++) {
      CHARACTERS[j] |= NCNAME_CHAR_MASK | DIGIT_CHAR_MASK;
      DIGIT_MAP[j]=j-firstDigit+0x30;
    }
  }
  for (var i = 0; i < extraDigitRanges.length; i += 2) {
    var firstDigit=extraDigitRanges[i];
    for (var j = extraDigitRanges[i]; j <= extraDigitRanges[i + 1]; j++) {
      CHARACTERS[j] |= DIGIT_CHAR_MASK;
      DIGIT_MAP[j]=j-firstDigit+0x30;
    }
  }
  for (var i = 0; i < combiningCharRanges.length; i += 2) {
    for (var j = combiningCharRanges[i]; j <= combiningCharRanges[i + 1]; j++) {
      CHARACTERS[j] |= NCNAME_CHAR_MASK;
    }
  }
  for (var i = 0; i < combiningCharChars.length; i++) {
    CHARACTERS[combiningCharChars[i]] |= NCNAME_CHAR_MASK;
  }
  for (var i = 0; i < extenderRanges.length; i += 2) {
    for (var j = extenderRanges[i]; j <= extenderRanges[i + 1]; j++) {
      CHARACTERS[j] |= NCNAME_CHAR_MASK;
    }
  }
  for (var i = 0; i < extenderChars.length; i++) {
    CHARACTERS[extenderChars[i]] |= NCNAME_CHAR_MASK;
  }
}

/**
 * The isValidNCName function checks the String passed in to determine if it
 * is a valid NC name.
 */
function isValidNCName(ncName) {

  // Create the variable to return
  var valid = true;
  
  // Make sure that the name contains some content
  if (ncName && ncName.length > 0) {
    
    // Make sure that the name starts with a valid NC name start char
    var charCode = ncName.charCodeAt(0);
    if (isValidNCNameStartChar(charCode)) {
    
      // Make sure that the rest of the chars are valid NC name chars
      for (var i = 1; i < ncName.length; i++) {
        charCode = ncName.charCodeAt(i);
        if (!isValidNCNameChar(charCode)) {
          valid = false;
          break;
        }
      } // FOR
    } else {
      // ncName does not start with a valid start char
      valid = false;
    }
  } else {
    // ncName is undefined or an empty string
    valid = false;
  }
  return valid;
}

/**
 * The isValidNCnameStartChars function checks the character code passed passed
 * in to determine if it represents a valid start character for an NC name.
 */
function isValidNCNameStartChar(charCode) {

  // Create the variable to return
  var valid = false;
  
  // Make sure that the charecter code is in range for our array
  if (charCode < 65536) {
    
    // Now apply the mask to see if the character is valid NC name start char
    if ((CHARACTERS[charCode] & NCNAME_START_CHAR_MASK) != 0) {
      valid = true;
    }
  }
  
  return valid;
}

/**
 * The isValidNCnameChars function checks the character code passed passed in
 * to determine if it represents a valid character for an NC name.
 */
function isValidNCNameChar(charCode) {

  // Create the variable to return
  var valid = false;
  
  // Make sure that the charecter code is in range for our array
  if (charCode < 65536) {

    // Now apply the mask to see if the character is valid NC name char
    if ((CHARACTERS[charCode] & NCNAME_CHAR_MASK) != 0) {
      valid = true;
    }
  }
  return valid;
}

if (!CHARACTERS) {
  initialize();
}

/**
 * Returns whether the given char code is a unicode digit.
 */
CharUtils.isDigit=function(charCode)
{
  var ret=false;
  if (charCode<65536)
  {
    // check against digit mask
    if ((CHARACTERS[charCode] & DIGIT_CHAR_MASK) != 0)
      ret=true;
  }
  return(ret);
}

/**
 * Converts any supported cultural digit back into its normalised
 * European form. Returns the actual character value for the digit, not the
 * numeric value. Returns -1 if the char is not a supported digit.
 */
CharUtils.digitToEuropean=function(charCode)
{
  var ret=-1;
  if (charCode<65536)
  {
    // check against digit map
    if (DIGIT_MAP[charCode])
      ret=DIGIT_MAP[charCode];
  }
  return(ret);
}

/**
 * Returns the numeric value of the given digit. Returns -1 if the char
 * is not a supported digit.
 */
CharUtils.digit=function(charCode)
{
  var ret=-1;
  if (charCode<65536)
  {
    // check against digit map
    if (DIGIT_MAP[charCode])
      ret=DIGIT_MAP[charCode]-0x30;
  }
  return(ret);
}

/**
 * Returns the given string with all digits normalised to their European
 * values. Non-digit chars are left untouched.
 */
CharUtils.digitString=function(/*String*/digits)
{
  var ret="";
  for (var i=0;i<digits.length;i++)
  {
    var ch=digits.charCodeAt(i);
    var newCh=CharUtils.digitToEuropean(ch);
    if (newCh==-1)
      ret=ret+digits.charAt(i);
    else
      ret=ret+String.fromCharCode(newCh);
  } // end for
  return(ret);
}

/**
 * Returns the given string with all digits converted from European to
 * the requested digit range. Non-digit chars are left untouched.
 */
CharUtils.convertDigits=function(/*String*/digits,/*String*/digitRange)
{
  var ret=digits;
  var unicodeStart=parseInt(digitRange);
  // dont convert if range is European
  if (unicodeStart!=48)
  {
    var newValue="";
    for (var i=0;i<digits.length;i++)
    {
      var ch=digits.charCodeAt(i);
      if (ch>=48 && ch<=57)
      {
        // european digit range
        var newChar=ch-48+unicodeStart;
        newValue=newValue+String.fromCharCode(newChar);
      }
      else
        newValue=newValue+String.fromCharCode(ch);
    } // end for
    ret=newValue;
  }
  return(ret);
}
