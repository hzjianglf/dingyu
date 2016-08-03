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

function isValidRelationshipName(/*String*/ relationshipName, /*WSRRObject[]*/ wsrrObjects) {

  // Log entry to the method
  //console.debug(">>> isValidRelationshipName(", relationshipName, ", ", wsrrObjects, ")");

  // Create the variable to return
  var valid = true;

  // Make sure that the relationship name passed to us is valid
  if (relationshipName && relationshipName.length > 0) {
  
    /*
     * The naming rules for WSRR properties and relationships state that they
     * cannot start with an underscore and that the name 'sdoType' is reserved
     */
    if (relationshipName.charAt(0) != '_' && relationshipName != "sdoType") {
    
      // Now make sure that we have some objects to validate the name against
      if (wsrrObjects) {
    
        /*
         * Iterate over the elements of the objects array and make sure that none
         * of the objects have a property or relationship with the same name.
         */
        for (var i = 0; i < wsrrObjects.length && valid == true; i++) {
    
          // Check the property names first.
          var properties = wsrrObjects[i].properties;
          for (var j = 0; j < properties.length; j++) {
            if (relationshipName == properties[j].name) {
              valid = false;
              break;
            }
          } // FOR
    
          // Now check the relationship names
          if (valid == true) {
            var relationships = wsrrObjects[i].relationships;
            for (var j = 0; j < relationships.length; j++) {
              if (relationshipName == relationships[j].name) {
                valid = false;
                break;
              }
            } // FOR
          } // IF - valid == true
        } // FOR
      } // IF - wsrrObjects
    } else { // IF - relationshipName.charAt(0) == '_'
      // Relationship name does not conform to WSRR naming rules
      valid = false;
    }
  } else {
    // Empty relationshipName
    valid = false;
  }

  // Log exit from the method
  //console.debug("<<< isValidRelationshipName ", valid);
  
  return valid;
}

function getProperty(/*String*/ propertyName, /*WSRRObject*/ wsrrObject) {

  // Log entry to the method
  //console.debug(">>> getProperty(", propertyName, ", ", wsrrObject, ")");

  // Create the variable to return
  var property = null;
  
  // Make sure that the object passed to us is valid
  if (propertyName && propertyName.length > 0 && wsrrObject) {

    // Retrieve the property with the specified name
    var properties = wsrrObject.properties;
    for (var i = 0; i < properties.length; i++) {
      if (properties[i].name == propertyName) {
        property = properties[i];
        break;
      }
    } // FOR
  } // IF - propertyName && etc...

  // Log exit from the method
  //console.debug("<<< getProperty ", property);
  
  return property;
}

function getRelationship(/*String*/ relationshipName, /*WSRRObject*/ wsrrObject) {

  // Log entry to the method
  //console.debug(">>> getRelationship(", relationshipName, ", ", wsrrObject, ")");

  // Create the variable to return
  var relationship = null;
  
  // Make sure that the object passed to us is valid
  if (relationshipName && relationshipName.length > 0 && wsrrObject) {

    // Retrieve the relationship with the specified name
    var relationships = wsrrObject.relationships;
    for (var i = 0; i < relationships.length; i++) {
      if (relationships[i].name == relationshipName) {
        relationship = relationships[i];
        break;
      }
    } // FOR
  } // IF - relationshipName && etc...

  // Log exit from the method
  //console.debug("<<< getRelationship ", relationship);
  
  return relationship;
}

function getObjectName(/* WSRRObject*/ wsrrObject) {

  // Log entry to the method
  //console.debug(">>> getObjectName(", wsrrObject, ")");

  // Create the variable to return
  var objectName = null;
  
  // Make sure that the object passed to us is valid
  if (wsrrObject) {
  
    // Retrieve the name of the object from the name property
    var property = getProperty("name", wsrrObject);
    if (property) {
      objectName = property.value;
    }
  } // IF - wsrrObject

  // Log exit from the method
  //console.debug("<<< getObjectName ", objectName);
  
  return objectName;
}

function getBsrUri(/* WSRRObject*/ wsrrObject) {

  // Log entry to the method
  //console.debug(">>> getBsrUri(", wsrrObject, ")");

  // Create the variable to return
  var bsrUri = null;
  
  // Make sure that the object passed to us is valid
  if (wsrrObject) {
  
    // Retrieve the name of the object from the name property
    var property = getProperty("bsrURI", wsrrObject);
    if (property) {
      bsrUri = property.value;
    }
  } // IF - wsrrObject

  // Log exit from the method
  //console.debug("<<< getBsrUri ", bsrUri);
  
  return bsrUri;
}

function getPrimaryType(wsrrObject) {

  // Log entry to the method
  //console.debug(">>> getPrimaryType(", wsrrObject, ")");

  // Create the variable to return
  var primaryType = null;

  // Make sure that the object passed to us is valid
  if (wsrrObject) {
  
    // Retrieve the primary type of the object from the primaryType property
    var property = getProperty("primaryType", wsrrObject);
    if (property) {
      primaryType = property.value;
    }
  } // IF - wsrrObject
  
  // Log exit from the method
  //console.debug("<<< getPrimaryType ", primaryType);
  
  return primaryType;
}

function getObjectDisplayType(wsrrObject) {

  // Log entry to the method
  //console.debug(">>> getObjectDisplayType(", wsrrObject, ")");

  // Create the variable to return
  var type = null;

  // Make sure that the object passed to us is valid
  if (wsrrObject) {
  
    // Retrieve the display name of the object from the __typeDisplayName property
    var property = getProperty("__typeDisplayName", wsrrObject);
    if (property) {
      type = property.value;
    }
  } // IF - wsrrObject
  
  // Log exit from the method
  //console.debug("<<< getObjectDisplayType ", type);
  
  return type;
}
