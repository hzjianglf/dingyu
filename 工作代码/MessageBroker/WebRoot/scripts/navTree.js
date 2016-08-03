/* begin_generated_IBM_copyright_prolog                            */
/* 
 * Licensed Materials - Property of IBM 
 * 
 * 5724-N72 5655-W17
 * 
 * (c) Copyright IBM Corp. 2009 All Rights Reserved 
 * 
 * US Government Users Restricted Rights - Use, duplication or 
 * disclosure restricted by GSA ADP Schedule Contract with 
 * IBM Corp. 
 */
/* end_generated_IBM_copyright_prolog                              */

/* load required packages */
dojo.require("dojo.rpc.JsonService");

var navService=new dojo.rpc.JsonService(contextRoot + "/RPCAdapter/jsonrpc/NavigationService");

function navToggle(/*string*/item)
{
  var block=document.getElementById("child_"+item);
  var img=document.getElementById("img_"+item);
  var link=document.getElementById("link_"+item);
  var style="";
  if (document.defaultView)
    style=document.defaultView.getComputedStyle(block, null).getPropertyValue("display");
  else if (block.currentStyle)
    style=block.currentStyle.display;
  if (style=="none")
  {
    var srcURL=img.src;
    if (srcURL.indexOf("title_maximize.gif")!=-1)
    {
      block.style.display="";
      img.src="theme/"+currentTheme+"/images/title_minimize.gif";
    }
    else
    {
      block.style.display="block";
      img.src="theme/"+currentTheme+"/images/expanded.gif";
    }
    img.alt=TRANS_GENERAL_MSG_COLLAPSE;
    img.title=TRANS_GENERAL_MSG_COLLAPSE;
    if (link)
      link.title=TRANS_GENERAL_MSG_COLLAPSE;
    // log the action in the session
    var deferred=navService.expandNode(item);
  }
  else
  {
    block.style.display="none";
    var srcURL=img.src;
    if (srcURL.indexOf("title_minimize.gif")!=-1)
      img.src="theme/"+currentTheme+"/images/title_maximize.gif";
    else
      img.src="theme/"+currentTheme+"/images/collapsed.gif";
    img.alt=TRANS_GENERAL_MSG_EXPAND;
    img.title=TRANS_GENERAL_MSG_EXPAND;
    if (link)
      link.title=TRANS_GENERAL_MSG_EXPAND;
    // log the action in the session
    var deferred=navService.collapseNode(item);
  }
}
