/* begin_generated_IBM_copyright_prolog                            */
/* 
 * Licensed Materials - Property of IBM 
 * 
 * 5724-N72 5655-W17
 * 
 * (c) Copyright IBM Corp. 2006, 2009 All Rights Reserved 
 * 
 * US Government Users Restricted Rights - Use, duplication or 
 * disclosure restricted by GSA ADP Schedule Contract with 
 * IBM Corp. 
 */
/* end_generated_IBM_copyright_prolog                              */

var COOKIE_SEPARATOR = "```";

/**
 * The getPreferencesCookieName function extracts the name of the WSRR
 * preferences cookie.
 */
function getPreferencesCookieName() {
	/*
	 * Retrieve the preferences cookie for the user and locate the WSRR
	 * preferences cookie.  The name of the cookie will be in the form
	 * WSRRPreferences_XXX, where XXX is the name of the current user.
	 *
	 * The name of the current user, urlencoded, should be stored in
	 * the global USER_NAME_ENCODED by the header.
	 */
	var cookieName = null;
	var cookie = document.cookie;
	var cookieStartIndex = -1;
	if(typeof(USER_NAME_ENCODED) != "undefined" && USER_NAME_ENCODED != "") {
		cookieStartIndex = cookie.indexOf("WSRRpreferences_" + USER_NAME_ENCODED + "=");
	} else {
		cookieStartIndex = cookie.indexOf("WSRRpreferences_=");
	}
	if (cookieStartIndex != -1) {
	
		// Retrieve the full name of the preferences cookie
		var cookieEndIndex = cookie.indexOf("=", cookieStartIndex);
		cookieName = cookie.substring(cookieStartIndex, cookieEndIndex);
		cookieName = decodeURIComponent(cookieName);
	} // IF - index != -1
	
	return cookieName;
}

/**
 * The getCookieValue function extracts the value of the WSRR preferences
 * cookie.
 */
function getCookieValue(cookieName) {
	/*
	 * Retrieve the preferences cookie for the user and locate the WSRR
	 * preferences cookie.  The name of the cookie will be in the form
	 * WSRRPreferences_XXX, where XXX is the name of the current user.
	 */
	var cookieValue = null;
	var cookie = document.cookie;
	// look for name plus equals so we always get the exactly correct cookie
	var cookieIndex = cookie.indexOf(cookieName + "=");
	if (cookieIndex != -1) {
	
		// Retrieve the value for the preferences cookie
		var valueStartIndex = cookie.indexOf("=", cookieIndex) + 1;
		var valueEndIndex = cookie.indexOf(";", valueStartIndex);
		cookieValue = cookie.substring(valueStartIndex, valueEndIndex);
		cookieValue = decodeURIComponent(cookieValue);
	} // IF - cookieIndex != -1
	
	return cookieValue;
}

/*
 * The getPreferenceValue function extracts the value for the specified
 * preference name.
 */
function getPreferenceValue(preferenceName) {
	/*
	 * Retrieve the name of the WSRR preferences cookie for this user and
	 * then get the value for the cookie.
	 */
	var preferenceValue = null;
	var cookieName = getPreferencesCookieName();
	var cookieValue = getCookieValue(cookieName);
	
	/*
	 * Retrieve the value for the specified preference name
	 */
	if (cookieValue != null) {
		var preferenceIndex = cookieValue.indexOf(preferenceName);
		if (preferenceIndex != -1) {
			var valueStartIndex = cookieValue.indexOf("=", preferenceIndex) + 1;
			var valueEndIndex = cookieValue.indexOf(COOKIE_SEPARATOR, valueStartIndex);
		
			// Check to see if we found a separator
			if (valueEndIndex != -1) {
				preferenceValue = cookieValue.substring(valueStartIndex, valueEndIndex);
			} else {
				preferenceValue = cookieValue.substr(valueStartIndex);
			}
		
			preferenceValue = decodeURIComponent(preferenceValue);
		} // IF - preferenceIndex != -1
	} // IF - cookieValue != null
	
	return preferenceValue;
}
