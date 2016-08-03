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

dojo.provide("com.ibm.sr.widgets.ProgressPanel");

dojo.require("dojo.i18n");

dojo.require("dijit._Widget");
dojo.require("dijit._Templated");

//dojo.requireLocalization("com.ibm.sr.widgets.DetailView", "DetailViewResources", null, "de,cs,es,fr,hu,ja,it,ko,pl,pt,ru,zh,zh-tw,ROOT");

dojo.declare("com.ibm.sr.widgets.ProgressPanel", [dijit._Widget, dijit._Templated],{

	// The path to the widgets template
	templatePath: dojo.moduleUrl("com.ibm.sr.widgets.ProgressPanel", "ProgressPanel.html"),
	widgetsInTemplate: true,
	
	// width: String
	//		Width of editor.  By default it's width=100% (ie, block mode)
	width: "100%",
	
	// The name attribute for the widget
	name: "",
    
    /**
     * Define the hashtable of static configuration properties for the widget.
     */
    statics: {
    	PROGRESS_DIV_ID : "progressPanelSection"
    },
    
    constructor: function (args) {

		// Log entry to the method
    	//console.debug(">>> constructor(", args, ")");
	
		// Need no args

		// Log exit from the method        
    	//console.debug("<<< constructor");
    },

	/**
	 * The postCreate method is invoked after the widget has been initialized,
     * except for the initialization of any sub-widgets.
	 */
	postCreate: function() {
	
		// Log entry to the method
    	//console.debug(">>> postCreate");

        // Call the parent class method
        this.inherited("postCreate", arguments);

		// Log exit from the method        
    	//console.debug("<<< postCreate");
	},

    /**
     * Destructor
     */
    destroy: function() {

        // Log entry to the method
        //console.debug(">>> destroy");

        // Call the parent class method
        this.inherited("destroy", arguments);

        // Log exit from the method        
        //console.debug("<<< destroy");
    }
});
