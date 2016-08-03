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

function buildMenus()
{
  var isIE7=false;
  var isIE8=false;
  var isOpera=false;
  if (navigator.appVersion.indexOf("MSIE 7.0")!=-1)
    isIE7=true;
  if (navigator.appVersion.indexOf("MSIE 8.0")!=-1)
    isIE8=true;
  if (navigator.userAgent.indexOf("Opera")!=-1)
    isOpera=true;
  if (navigator.appVersion.indexOf("MSIE 6.0")!=-1)
  {
    var reg=/\s*hvr/;
    var cs='hvr';
    var count='';
    for (var i=0;i<10;i++)
    {
      var root=document.getElementById("menubar"+count);
      if (root)
      {
        var listElements=root.getElementsByTagName("LI");
        if (listElements)
        {
          for (var k=0;k<listElements.length;k++)
          {
            listElements[k].onmouseover=function() {
              var c=this.className;
              var cl=(c)?c+' '+cs:cs;
              this.className=cl;
              var currentList=this;
              var ul=this.getElementsByTagName("ul")[0];
              if (ul)
              {
                var frame=this.getElementsByTagName("iframe")[0];
                if (frame)
                {
                  if (frame.id)
                  {
                    var callback=function() {
                      var currentLoc=dojo.coords(currentList);
                      var parentUl=currentList.parentNode;
                      var parentLoc=dojo.coords(parentUl);
                      var parentWidth=parentLoc.w;
                      if (!parentUl.id)
                      {
                        var adjust=1;
                        var parentLi=parentUl.parentNode;
                        var parentUlUl=parentLi.parentNode;
                        if (!parentUlUl.id)
                          adjust=2;
                        ul.style.left=parentWidth-adjust+"px";
                        if (isRTL)
                        {
                          var submenuWidth=dojo.coords(ul).w;
                          ul.style.left=-submenuWidth+"px";
                        }
                        ul.style.top=currentLoc.t+"px";
                      }
                      frame.style.width=ul.offsetWidth+"px";
                      frame.style.height=ul.offsetHeight+"px";
                      var loc=dojo.coords(ul);
                      var x=loc.x;
                      var y=loc.y
                      x=x-parentLoc.x;
                      y=y-parentLoc.y;
                      frame.style.top=y+"px";
                      if (isRTL)
                        frame.style.left=x-1+"px";
                      else
                        frame.style.left=x+"px";
                      frame.style.display="block";
                    };
                    window.setTimeout(callback,0);
                  }
                  else
                  {
                    frame.style.width=ul.offsetWidth+"px";
                    frame.style.height=ul.offsetHeight+"px";
                    frame.style.display="block";
                  }
                }
              }
            };
            listElements[k].onmouseout=function() {
              var c=this.className;
              this.className=(c)?c.replace(reg,''):'';
              var ul=this.getElementsByTagName("ul")[0];
              if (ul)
              {
                var frame=this.getElementsByTagName("iframe")[0];
                if (frame)
                {
                  frame.style.display="none";
                }
              }
            };
            // now we must remove all the empty text nodes that are immediate children of <LI>
            // elements since they are bogus.
            var children=listElements[k].childNodes;
            if (children && children.length>0)
            {
              for (j=0;j<children.length;j++)
              {
                var child=children[j];
                if (child.nodeType==3)
                {
                  var value=dojo.trim(child.nodeValue);
                  if (value.length==0)
                    listElements[k].removeChild(child);
                }
              } // end for
            }
          }
        }
      }
      count=i+1;
    } // end for
  }
  else
  {
    // all other browsers
    var count='';
    for (var i=0;i<10;i++)
    {
      var root=document.getElementById("menubar"+count);
      if (root)
      {
        var listElements=root.getElementsByTagName("LI");
        if (listElements)
        {
          for (var k=0;k<listElements.length;k++)
          {
            listElements[k].onmouseover=function() {
              var currentList=this;
              var parentUl=currentList.parentNode;
              var currentLoc=dojo.coords(currentList);
              if (!parentUl.id)
              {
                var adjust=1;
                var parentLi=parentUl.parentNode;
                var parentUlUl=parentLi.parentNode;
                if (!parentUlUl.id)
                  adjust=2;
                var callback=function() {
                  var parentWidth=dojo.coords(parentUl).w;
                  var ul=currentList.getElementsByTagName("ul")[0];
                  if (ul)
                  {
                    ul.style.left=parentWidth-adjust+"px";
                    if (isRTL)
                    {
                      var submenuWidth=dojo.coords(ul).w;
                      ul.style.left=-submenuWidth+"px";
                    }
                    ul.style.top=currentLoc.t+"px";
                  }
                };
                if (isIE7)
                  window.setTimeout(callback,0);
                else
                  callback();
              }
              else
              {
                if ((isIE7 || isIE8) && isRTL)
                {
                  var ul=this.getElementsByTagName("ul")[0];
                  if (ul)
                  {
                    var callback=function() {
                      var submenuLoc=dojo.coords(ul);
                      ul.style.left=currentLoc.l+currentLoc.w-submenuLoc.w+"px";
                    };
                    window.setTimeout(callback,0);
                  }
                }
                else if (isOpera && isRTL && parentUl.id!="menubar2")
                {
                  var ul=this.getElementsByTagName("ul")[0];
                  if (ul)
                  {
                    var submenuLoc=dojo.coords(ul);
                    ul.style.left=currentLoc.l+currentLoc.w-submenuLoc.w+"px";
                  }
                }
              }
            };
          } // end for
        }
      }
      count=i+1;
    } // end for
  }
}

