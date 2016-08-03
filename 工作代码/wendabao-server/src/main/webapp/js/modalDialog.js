/**
 * 使用方法：参数中，type和content为必填项，options为选填项，options中的条目也为选填。
 * 【1 确认弹出框 两个按钮】
 *     new ModalDialog("confirm","确定删除吗？",{
            leftBtnTxt: "不删除",
            rightBtnTxt: "删除",
            onOk: function(){
                // 当点击确定按钮时的处理
            },
            onCancel: function(){
                // 当点击取消按钮时的处理
            }
        });
 * 【2 提示框 一个按钮】
 *     new ModalDialog("info","信息填写有误",{
            rightBtnTxt: "知道了",
            onOk: function(){
                // 当点击确定按钮时的处理
            }
       });
 * 【3 提示框 没有按钮 延时消失】
 *      new ModalDialog("toast","信息填写有误",{
                toastTimeout: 5000
        });
 * 【4 点击查看大图】
 * new ModalDialog("image","http://192.168.1.2:8888/201511/a0b1d16b-40d6-42ca-81b2-1a11b43f6a9f.jpg");
 */
var ModalDialog = function (type, content, options) {
    var defaultOptions = {
        leftBtnTxt: "取消",
        rightBtnTxt: "确定",
        toastTimeout: 1000,
        onCancel: onCancel,
        onOk: onOk
    };

    init();

    /**
     * 生成DOM
     */
    if ("info" == type) {
        // 只有一个按钮的提示框
        $("#_confirm_bg",parent.document).remove(); // 防止重复弹出
        $("body",parent.document).append(
            "<div id=\"_confirm_bg\">"+
            "    <div id=\"_confirm_content\" style=\"display: block;\">"+
            "        <p>"+content+"</p>"+
            "        <em id=\"_confirm_shadowA\"></em>"+
            "        <em id=\"_confirm_shadowB\"></em>"+
            "        <div id=\"_confirm_btnW\">"+defaultOptions.rightBtnTxt+"</div>"+
            "    </div>"+
            "</div>");
    } else if ("confirm" == type) {
        // 有两个按钮的确认框
        $("#_confirm_bg",parent.document).remove(); // 防止重复弹出
        $("body",parent.document).append(
            "<div id=\"_confirm_bg\">"+
            "    <div id=\"_confirm_content\" style=\"display: block;\">"+
            "        <p>"+content+"</p>"+
            "        <em id=\"_confirm_shadowA\"></em>"+
            "        <em id=\"_confirm_shadowB\"></em>"+
            "        <div id=\"_confirm_btnW\">"+
            "            <div id=\"_confirm_btnA\" class=\"\">"+defaultOptions.leftBtnTxt+"</div>"+
            "            <em id=\"_confirm_shadowC\"></em>"+
            "            <em id=\"_confirm_shadowD\"></em>"+
            "            <div id=\"_confirm_btnB\" class=\"\">"+defaultOptions.rightBtnTxt+"</div>"+
            "        </div>"+
            "    </div>"+
            "</div>");
//        var c_Height=($(document.body)[0].clientHeight-$("#_confirm_content").outerHeight())/2;  
//        var c_Width=($(document.body)[0].clientWidth-$("#_confirm_content").outerWidth())/2;
//        $("#_confirm_content").css("top",c_Height);
//        $("#_confirm_content").css("left",c_Width);
//        $("#_confirm_content").css("position","fixed");
    } else if("image" == type){
        // 显示图片大图
        $("#_image_bg",parent.document).remove(); // 防止重复弹出
        $("body",parent.document).append(
            "<div id=\"_image_bg\">"+
            "    <a id=\"_image_close\">×</a>"+
            "    <img src=\""+content+"\" id=\"_image_img\">"+
            "</div>"
        );
    } else if("toast" == type){
        // 没有按钮 弹出后在指定时间内消失
        $("body",parent.document).append(
            "<div id=\"_confirm_bg\">"+
            "    <div id=\"_confirm_content\" style=\"display: block;\">"+
            "        <p>"+content+"</p>"+
            "    </div>"+
            "</div>"
        );
    }

    bind();

    //图片按比例缩放
    var flag=false;
    function DrawImage(ImgD,iwidth,iheight){
        //参数(图片,允许的宽度,允许的高度)
        var image=new Image();
        image.src=ImgD.src;
        if(image.width>0 && image.height>0){
            flag=true;
            if(image.width/image.height>= iwidth/iheight){
                if(image.width>iwidth){
                    ImgD.width=iwidth;
                    ImgD.height=(image.height*iwidth)/image.width;
                }else{
                    ImgD.width=image.width;
                    ImgD.height=image.height;
                }
                ImgD.alt=image.width+"×"+image.height;
            }
            else{
                if(image.height>iheight){
                    ImgD.height=iheight;
                    ImgD.width=(image.width*iheight)/image.height;
                }else{
                    ImgD.width=image.width;
                    ImgD.height=image.height;
                }
                ImgD.alt=image.width+"×"+image.height;
            }
//            ImgD.style.cssText = "width:"+ImgD.width +"; height:"+ImgD.height;
        }
    }

    /**
     * 绑定事件
     */
    function bind(){
        if("info" == type){
            $("#_confirm_btnW",parent.document).bind("click", function(){
                defaultOptions.onOk();
                onOk();
            });
        }else if("confirm" == type){
            $("#_confirm_btnA",parent.document).bind("click", function(){
                defaultOptions.onCancel();
                onCancel();
            });

            $("#_confirm_btnB",parent.document).bind("click", function(){
                defaultOptions.onOk();
                onOk();
            });
        }else if("image" == type){
            $("#_image_bg",parent.document).bind("click", function(){
                $("#_image_bg",parent.document).fadeOut("fast",function(){
                    $(this).remove();
                });
               // $("#_image_bg").remove();
            });
            $("#_image_close",parent.document).bind("click", function(){
                $("#_image_bg",parent.document).fadeOut("fast",function(){
                    $(this).remove();
                });
               // $("#_image_bg").remove();
            });
            $("#_image_bg>img",parent.document).load(function(){
                var imgD = document.getElementById("_image_img");
                DrawImage(imgD,800,800,$("#_image_bg>img"));
            });
        }else if("toast" == type){
            setTimeout(function(){
                $("#_confirm_bg",parent.document).fadeOut("fast",function(){
                    $(this).remove();
                });
                //$("#_confirm_bg",parent.document).remove();
            },defaultOptions.toastTimeout);
        }
    }

    /**
     * 初始化配置项
     */
    function init(){
        if(null != options && "undefined"!=options && "null" != options){
            if(options.leftBtnTxt){
                defaultOptions.leftBtnTxt = options.leftBtnTxt;
            }
            if(options.rightBtnTxt){
                defaultOptions.rightBtnTxt = options.rightBtnTxt;
            }
            if(options.onCancel){
                defaultOptions.onCancel = options.onCancel;
            }
            if(options.onOk){
                defaultOptions.onOk = options.onOk;
            }
            if(options.toastTimeout){
                defaultOptions.toastTimeout = options.toastTimeout;
            }
        }
    }

    function onCancel(){
       // $("#_confirm_bg",parent.document).remove();
        $("#_confirm_bg",parent.document).fadeOut("fase",function(){
            $(this).remove();
        });
    }

    function onOk(){
        $("#_confirm_bg",parent.document).fadeOut("fase",function(){
            $(this).remove();
        });
      //  $("#_confirm_bg",parent.document).remove();
    }

}
