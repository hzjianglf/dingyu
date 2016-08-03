
	$(function(){
            var screenWidth=$(window).width();
            
            var jsonData={"contents":[{"linkUrl":"","smallPicPath":"","bigPicPath":""},{"linkUrl":"","smallPicPath":"","bigPicPath":""},{"linkUrl":"","smallPicPath":"","bigPicPath":""}],"setData":{"descriPtion":"320*300"}};
            if(jsonData.setData){
                    var imgStr = '',indexStr = '',heightStr = jsonData.setData.descriPtion.split("*")[1];
                    $("#bannerNav").addClass("height" + heightStr);
                    for(var i = 0, jl = jsonData.contents.length; i < jl; i++){
                                    imgStr += '<li><a href="' + (jsonData.contents[i].linkUrl || '#') + '">' +
                                    '<img style="max-width:320px;" alt="" src="' + (jsonData.contents[i].bigPicPath.indexOf('content')!=-1 ? " /manager":"") + jsonData.contents[i].bigPicPath + '" />'+
                                    '</a> </li>';
                                    indexStr += '<em class=' + (i==0?'active':'') + '>'+ (i+1) +'</em> '
                    }
                    $("#bannerScroll ul").css("width",320*(jsonData.contents.length+1)+"px");
                    if(jsonData.contents.length<=1){
                            $("#bannerNav,#prev,#next").hide();
                    } else {
                            $("#indicator").html(indexStr);
                            var sc2=new SwipeCarousel({wraper:'#bannerScroll',item:'li',itemWidth:screenWidth,loop:true,navs:'.sc_nav em'});
                    }
            } else {
                    $("#bannerScroll,#bannerNav").hide();
            }
    });

h