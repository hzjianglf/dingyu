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

var tree_root=null;             // node to the root of the tree
var maxNodes=0;                 // total count of the nodes
var nodeIndex=null;             // index of all the nodes in the tree
var lastHighlight=null;
var baseDirectory="";

// Switches the view to show the search tab.
function showSearchTab()
{
  var indexTab=document.getElementById("indextab");
  var indexTabDiv=document.getElementById("indextabdiv");
  var searchTab=document.getElementById("searchtab");
  var searchTabDiv=document.getElementById("searchtabdiv");
  var helpNavTree=document.getElementById("helpnavtree");
  var helpSearch=document.getElementById("helpsearch");
  var link="<a class=\"tab-item\" href=\"javascript:showIndexTab()\" title=\""+TRANS_HELP_INDEX+"\" tabindex=\"1\">"+TRANS_HELP_INDEX+"</a>";
  indexTab.className="tab-off";
  indexTabDiv.innerHTML=link;
  searchTab.className="tab-on";
  searchTabDiv.innerHTML=TRANS_HELP_SEARCH;
  helpNavTree.style.display="none";
  helpSearch.style.display="block";
}

// Switches the view to show the index tab.
function showIndexTab()
{
  var indexTab=document.getElementById("indextab");
  var indexTabDiv=document.getElementById("indextabdiv");
  var searchTab=document.getElementById("searchtab");
  var searchTabDiv=document.getElementById("searchtabdiv");
  var helpNavTree=document.getElementById("helpnavtree");
  var helpSearch=document.getElementById("helpsearch");
  var link="<a class=\"tab-item\" href=\"javascript:showSearchTab()\" title=\""+TRANS_HELP_SEARCH+"\" tabindex=\"1\">"+TRANS_HELP_SEARCH+"</a>";
  searchTab.className="tab-off";
  searchTabDiv.innerHTML=link;
  indexTab.className="tab-on";
  indexTabDiv.innerHTML=TRANS_HELP_INDEX;
  helpNavTree.style.display="block";
  helpSearch.style.display="none";
}

// Shows the given page Uri in the help panel frame.
function showHelpPage(/*string*/pageUri)
{
  dojo.xhrGet( {
    url: pageUri, 
    timeout: 5000,
    load: placeHelpContent,
    error: helpContentError
  });
  if (lastHighlight!=null)
    dojo.removeClass(lastHighlight,"highlighted");
  var node=matchLinkToNode(pageUri);
  if (node)
  {
  	var indexItem=document.getElementById("item"+node.id);
    dojo.addClass(indexItem,"highlighted");
    lastHighlight=indexItem;
    // remember what page we're on for search
    var currentPageInput=document.getElementById("currentpagename");
    if (currentPageInput)
      currentPageInput.value=pageUri;
  }
}

// Callback from XHR GET to place the response HTML in the page
function placeHelpContent(response, ioArgs)
{
  var helpDiv=document.getElementById("helpcontent");
  var bodyStart=response.indexOf("<body");
  if (bodyStart!=-1)
  {
    var realStart=response.indexOf(">",bodyStart)+1;
    var end=response.indexOf("</body>");
    var content=response.substring(realStart,end);
    helpDiv.innerHTML=content;
    // fixup all the contained anchors to have valid links
    var anchors=helpDiv.getElementsByTagName("a");
    if (anchors && anchors.length>0)
    {
      for (var i=0;i<anchors.length;i++)
      {
        var anchor=anchors[i];
        var href=anchor.href;
        var slash=href.lastIndexOf("/");
        if (slash!=-1)
          href=href.substr(slash+1);
        anchor.href="javascript:showHelpPage('"+baseDirectory+"/"+href+"')";
      } // end for
    }
    // fixup all the contained images to have valid URLs
    var images=helpDiv.getElementsByTagName("img");
    if (images && images.length>0)
    {
      for (var i=0;i<images.length;i++)
      {
        var image=images[i];
        var src=image.src;
        var index=src.indexOf("/icons/");
        if (index!=-1)
          image.src="com.ibm.sr.ui.help/nl"+src.substr(index);
        var index=src.indexOf("/graphics/");
        if (index!=-1)
          image.src=baseDirectory+src.substr(index);
      } // end for
    }
  }
  return(response);
}

// Handles fetch errors for help content
function helpContentError(response, ioArgs)
{
  var helpDiv=document.getElementById("helpcontent");
  helpDiv.innerHTML=resolveMessageInserts(TRANS_HELP_READ_FAIL,ioArgs.xhr.status);
  return(response);
}

// Define a Treenode
function TreeNode(/*string*/content,/*string*/link,/*TreeNode*/parent,/*int*/id,/*int*/level,/*boolean*/is_selectable)
{
  // Class Variables
  this.link = link;             // Content Link
  this.level = level;           // Nodes Level wrt root node
  this.content = content;       // Content to be displayed
  this.parent = parent;         // Parent TreeNode
  this.children = new Array;    // Children of the current TreeNode
  this.childCount = 0;          // Number of children of this TreeNode
  this.id = id;                 // TreeNode Id number
  this.expanded = false;        // Are the children visible
  this.visible = false;         // Is the node itself visible
  this.selectable = is_selectable; // Item is Selectable?
}

// Sets the root node for the index.
function addRoot(/*string*/content,/*string*/link)
{
  var selectable=true;
  if (link=="")
    selectable=false;
  // create a new node and set that as the root
  var tempNode=new TreeNode(content,link,null,maxNodes++,0,selectable);
  tree_root=tempNode;
  return(tempNode);
}

// Add an item to the tree
function addItem(/*TreeNode*/parent,/*string*/content,/*string*/link)
{
  var selectable=true;
  if (parent==null)
    parent=tree_root;
  if (link=="")
    selectable=false;
  var tempNode=new TreeNode(content,link,parent,maxNodes++,parent.level+1,selectable);
  parent.children[parent.childCount++]=tempNode;
  return(tempNode);
}

// Create a fast index for the nodes
function createNodeIndex(/*TreeNode*/node)
{
  nodeIndex[node.id]=node;
  for (var i=0;i<node.childCount;i++)
  {
    // recursive
    createNodeIndex(node.children[i]);
  } // end for
}

// Draws the tree
function displayTree()
{
  var node=tree_root;
  if (node!=null && node.childCount>0)
  {
    var container=document.getElementById("helpnavtree");
    // Loop around all the children and add them
    for (var i=0;i<node.childCount;i++)
    {
      var lastnode=false;
      if (i==(node.childCount-1))
        lastnode=true;
      displayTreeNode(node.children[i],container,lastnode,false,1);
    } // end for
  }
}

// Draws a tree node
function displayTreeNode(/*TreeNode*/node,/*DomObject*/container,/*boolean*/lastnode,/*boolean*/root,/*int*/level)
{
  var id="item"+node.id;
  if (level==1)
  {
    // primary section in tree
    var sectionDiv=document.createElement("div");
    sectionDiv.className="navigationsection";
    container.appendChild(sectionDiv);
    // make a title
    var titleDiv=document.createElement("div");
    titleDiv.className="main-task";
    titleDiv.id=id;
    sectionDiv.appendChild(titleDiv);
    if (node.selectable)
    {
      // node title is clickable
      var content="";
      if (node.childCount>0)
      {
        // has children, so make it an expandable section
        content=content+"<a href=\"javascript:toggleSection('"+id+"')\" title=\""+TRANS_GENERAL_MSG_EXPAND+"\" tabindex=\"1\">\n";
        content=content+"<img src=\"theme/"+currentTheme+"/images/collapsed.gif\" id=\"img_"+id+"\" alt=\""+TRANS_GENERAL_MSG_EXPAND+"\"/>\n</a>\n";
      }
      content=content+"<span class=\"activelink\">\n";
      content=content+"<a href=\"javascript:showHelpPage('"+node.link+"')\" tabindex=\"1\">"+node.content+"</a>\n</span>\n";
      titleDiv.innerHTML=content;
    }
    else
    {
      // node title not clickable
      var content="<a href=\"javascript:toggleSection('"+id+"')\" title=\""+TRANS_GENERAL_MSG_EXPAND+"\" tabindex=\"1\">\n";
      content=content+"<img src=\"theme/"+currentTheme+"/images/collapsed.gif\" id=\"img_"+id+"\" alt=\""+TRANS_GENERAL_MSG_EXPAND+"\"/>\n"+node.content+"</a>\n";
      titleDiv.innerHTML=content;
    }
    // now process any children
    if (node.childCount>0)
    {
      // make a basic child container
      var childDiv=document.createElement("div");
      childDiv.className="nav-child-container collapsed";
      childDiv.id="child_"+id;
      sectionDiv.appendChild(childDiv);
      var lastUl=null;
      var lastContainer=childDiv;
      // Loop around all the children and add them
      for (var i=0;i<node.childCount;i++)
      {
        var lastnode=false;
        if (i==(node.childCount-1))
          lastnode=true;
        // check if the child node has children itself
        // if it does, then pass the DIV container as it will make it's own container
        var childNode=node.children[i];
        if (childNode.childCount>0)
        {
          displayTreeNode(childNode,childDiv,lastnode,false,node.level+1);
          lastContainer=childDiv;
        }
        else
        {
          // child node is just a plain list element, give it a UL container
          // try and reuse last UL if previous node also had no children
          var localContainer=null;
          if (lastContainer==lastUl)
            localContainer=lastUl;
          else
          {
            // make a new UL container
            var childList=document.createElement("ul");
            childDiv.appendChild(childList);
            localContainer=childList;
            lastUl=childList;
          }
          displayTreeNode(childNode,localContainer,lastnode,false,node.level+1);
          lastContainer=localContainer;
        }
      } // end for
    }
  }
  else
  {
    // not primary section
    if (node.childCount>0)
    {
      // make a sub-container with title for the children
      // make a title
      var titleDiv=document.createElement("div");
      titleDiv.className="sub-task";
      titleDiv.id=id;
      container.appendChild(titleDiv);
      if (node.selectable)
      {
        // node title is clickable
        content="<a href=\"javascript:toggleSection('"+id+"')\" title=\""+TRANS_GENERAL_MSG_EXPAND+"\" tabindex=\"1\">\n";
        content=content+"<img src=\"theme/"+currentTheme+"/images/collapsed.gif\" id=\"img_"+id+"\" alt=\""+TRANS_GENERAL_MSG_EXPAND+"\"/>\n</a>\n";
        content=content+"<span class=\"activelink\">\n";
        content=content+"<a href=\"javascript:showHelpPage('"+node.link+"')\" tabindex=\"1\">"+node.content+"</a>\n</span>\n";
        titleDiv.innerHTML=content;
      }
      else
      {
        // node title not clickable
        var content="<a href=\"javascript:toggleSection('"+id+"')\" title=\""+TRANS_GENERAL_MSG_EXPAND+"\" tabindex=\"1\">\n";
        content=content+"<img src=\"theme/"+currentTheme+"/images/collapsed.gif\" id=\"img_"+id+"\" alt=\""+TRANS_GENERAL_MSG_EXPAND+"\"/>\n"+node.content+"</a>\n";
        titleDiv.innerHTML=content;
      }
      // now process the children
      // make a basic child container
      var childDiv=document.createElement("div");
      childDiv.className="sub-child-container collapsed";
      childDiv.id="child_"+id;
      container.appendChild(childDiv);
      var lastUl=null;
      var lastContainer=childDiv;
      // Loop around all the children and add them
      for (var i=0;i<node.childCount;i++)
      {
        var lastnode=false;
        if (i==(node.childCount-1))
          lastnode=true;
        // check if the child node has children itself
        // if it does, then pass the DIV container as it will make it's own container
        var childNode=node.children[i];
        if (childNode.childCount>0)
        {
          displayTreeNode(childNode,childDiv,lastnode,false,node.level+1);
          lastContainer=childDiv;
        }
        else
        {
          // child node is just a plain list element, give it a UL container
          // try and reuse last UL if previous node also had no children
          var localContainer=null;
          if (lastContainer==lastUl)
            localContainer=lastUl;
          else
          {
            // make a new UL container
            var childList=document.createElement("ul");
            childDiv.appendChild(childList);
            localContainer=childList;
            lastUl=childList;
          }
          displayTreeNode(childNode,localContainer,lastnode,false,node.level+1);
          lastContainer=localContainer;
        }
      } // end for
    }
    else
    {
      // just add list element
      var listElement=document.createElement("li");
      listElement.id=id;
      container.appendChild(listElement);
      var content="<a href=\"javascript:showHelpPage('"+node.link+"')\" tabindex=\"1\">"+node.content+"</a>\n";
      listElement.innerHTML=content;
    }
  }
}

// Synchronise the index to the current help page
function syncIndex()
{
  var node=matchLinkToNode(currentHelp);
  if (node!=null)
  {
    // found the correct link
    // highlight the match
  	var indexItem=document.getElementById("item"+node.id);
    dojo.addClass(indexItem,"highlighted");
    lastHighlight=indexItem;
    var level=node.level-1;
    // expand each parent node
    for (var j=0;j<level;j++)
    {
      var parent=nodeIndex[node.parent.id];
      forceExpandSection("item"+parent.id);
      node=parent;
    } // end for
    // place in view
    var loc=dojo.coords(indexItem);
  	var navContainer=document.getElementById("helpnavtree");
    var navLoc=dojo.coords(navContainer);
    var pos=0;
    if (browserType=="ie")
    {
      pos=navLoc.h-loc.h-loc.t;
      if (browserVersion=="6")
      {
        var parent=indexItem.parentNode;
        while (parent.id!="helpnavtree")
        {
          var parentLoc=dojo.coords(parent);
          pos=pos-parentLoc.t;
          parent=parent.parentNode;
        } // end while
      }
    }
    else
      pos=navLoc.h-loc.h-loc.t+navLoc.t-5;
    if (pos<0)
    {
      // out of view
      navContainer.scrollTop=-pos;
    }
  }
}

// Find the matching node for the given link
function matchLinkToNode(/*string*/link)
{
  var node=null;
  for (var i=0;i<nodeIndex.length;i++)
  {
    if (nodeIndex[i].link==link)
    {
      node=nodeIndex[i];
      break;
    }
  } // end for
  return(node);
}

// Perform page initialisation
function initPage()
{
  if (currentHelp)
  {
    var slash=currentHelp.lastIndexOf("/");
    if (slash!=-1)
      baseDirectory=currentHelp.substring(0,slash);
  }
  if (tree_root!=null)
  {
    nodeIndex=new Array();
    createNodeIndex(tree_root);
    displayTree();
    showHelpPage(currentHelp);
    syncIndex();
  }
}

dojo.addOnLoad(initPage);
