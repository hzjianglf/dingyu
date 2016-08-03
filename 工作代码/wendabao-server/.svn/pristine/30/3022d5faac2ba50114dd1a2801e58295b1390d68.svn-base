/**
 * Created by apple on 15-7-3.
 */
$.extend({
    /**
     * 下拉选择框-弹出对话框(多选和单选都可以)
     * 取得所选的值：$("#selectedIds_"+id).text()
     * Tips：options的格式为：[{ description="显示的描述文字",  key="文字对应的key或id",  selected="选中：1 未选中：空"},...]
     *
     * @param select 父节点
     * @param title 左边label显示的内容
     * @param id 下拉选择框的id
     * @param data 下拉选择框的默认选项
     * @param options 选项们
     * @param type 单选传‘radio’，多选传‘checkbox’
     * @param editable 是否可编辑
     * @param onchangeCallback 当内容发生改变时的回调函数
     * @constructor
     */
    SelectDialogWidget: function (select, title, id, data, options, type, editable, onchangeCallback) {
        //plog(title+data);
        var selectValues = ""; // 用于显示
        var selectIds = ""; // 用于上传的参数
        var enable = "";

        if(!editable || options.length == 0){
            enable = "ui-state-disabled";
        }

        // 用于显示选择结果的span，点击它弹出选择对话框
        var spanstr =
            "<div id ='div_"+id+"' class=\""+enable+" ui-mini ui-btn ui-icon-carat-d ui-btn-icon-right ui-corner-all ui-shadow selectDialogWidget\">"+
            "    <span id='"+id+"'>"+data+"</span>"+
            "    <div hidden id='selectedIds_"+id+"'></div>"+
            "</div>";

        var parentDiv = $(select);
        parentDiv.append(spanstr);
        parentDiv.trigger("create");

        // 拼接选项html代码
        var optionStr = "";
        $(options).each(function(index, ite){
            var selected = ite.selected;
            var spanText = (data+"").split(',');

            // 如果fieldData中有值，优先选中fieldData中的
            if(spanText.indexOf(ite.description)!=-1){
                selected = "checked='checked'";
            }else{
                if(selected == "1" || selected == 1){
                    selected = "checked='checked'";
                }else{
                    selected = "";
                }
            }

            var inputId = ite.key;
            if(inputId==""){
                // 给空选项指定一个元素用的默认id
                inputId = "emptyEle_"+id;
            }
            optionStr = optionStr +
                "<input name=\""+id+"\" id=\""+inputId+"\" type=\""+type+"\" class='"+id+"' "+selected+" value='" + ite.description + "'>"+
                "<label for=\""+inputId+"\" id=\"label_"+inputId+"\">" + ite.description + "</label>"
            //$("#fieldset"+id).append(optionStr);
        });

        // 对话框的html代码
        var dialogHtmlStr =
            "<div data-dismissible=\"false\" data-confirmed=\"no\" data-overlay-theme=\"a\" data-role=\"popup\" data-theme=\"a\" id=\"selectDialog"+id+"\" style=\"max-width:400px;min-width: 300px;\">"+
            "    <div data-role=\"header\" data-theme=\"a\">"+
            "        <a href=\"#\" data-rel=\"back\" class='ui-btn-left'>返回</a>"+
            "        <h1>"+title+"</h1>"+
            "        <a href=\"#\" class='ui-btn-right' id='selectConfirm"+id+"'>确定</a>"+
            "    </div>"+
            "    <div class=\"ui-content\" style=\"max-height: 300px;min-height: 300px;\">"+
            "    <div id=\"wrapper"+id+"\" class='wrapper'>"+
            "    <div id=\"scroller\">"+
            "        <fieldset data-role=\"controlgroup\" id='fieldset"+id+"'>"+
                        optionStr +
            "        </fieldset>"+
            "    </div>"+
            "    </div>"+
            "    </div>"+
            "</div>";
        $(select).append(dialogHtmlStr);
        $(select).trigger("create");

        $("#div_"+id).bind("click", openSelectDialog);
        $("#selectConfirm"+id).bind("click", closeDialog);

        getSelectedValue();

        // 打开选择对话框
        function openSelectDialog(){
            myScroll = new IScroll('#wrapper'+id, { mouseWheel: true });
            var spanText = $("#"+id).text().split(',');
            // 遍历所有选项，如果spanStr中包含其id，则使其状态变为checked，否则为非选择状态
            var aObj = $("input[class^="+id+"]");

            for(var i=0;i<aObj.length;i++){
                if(spanText.indexOf(aObj[i].value)!=-1){
                    $("#"+aObj[i].id).attr("checked","checked");
                }else{
                    $("#"+aObj[i].id).removeAttr("checked");
                }
            }

            $("#selectDialog"+id).popup();
            $("#selectDialog"+id).popup("open");
        }

        // 将已选择的值显示在选择结果span上，然后关闭对话框
        function getSelectedValue(){
            selectValues = "";
            selectIds="";
            var aObj = $("input[class^="+id+"]");
            for(var i=0;i<aObj.length;i++){
                if(aObj[i].checked == true && aObj[i].id.indexOf("emptyEle_")==-1){
                    selectIds += aObj[i].id + SPECIAL_CHAR;
                    selectValues += $("#label_"+aObj[i].id).text() + ",";
                }
            }
            $("#"+id).text(removeFirstLastChar(selectValues, ","));
            $("#selectedIds_"+id).text(removeFirstLastChar(selectIds, SPECIAL_CHAR));
        }

        // 关闭选择对话框
        function closeDialog(){
            getSelectedValue();
            setTimeout(onchangeCallback,100);
            $("#selectDialog"+id).popup("close");
        }

    }
});