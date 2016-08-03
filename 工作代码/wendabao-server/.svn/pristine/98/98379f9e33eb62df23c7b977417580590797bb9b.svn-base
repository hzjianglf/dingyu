/**
 * @author 雷蕾
 */
$.extend({
    /**
     * 选择列表(多选和单选都可以)
     * SelectListWidget对象.getSelectListValue()
     * Tips：options的格式为：[{ description="显示的描述文字",  key="文字对应的key或id",  selected="选中：1 未选中：空"},...]
     *
     * @param select 父节点
     * @param id 下拉选择框的id
     * @param options 选项们
     * @param type 单选传‘radio’，多选传‘checkbox’
     * @param detailKey 如果选项中有detail要显示，就传一下detailkey，当然options中的对象要包含这个key才可以
     * @constructor
     */
    SelectListWidget: function (select,id, options, type, detailKey) {
        var selectIds = ""; // 用于上传的参数
        var selectDisp = "";

        // 拼接选项html代码
        var optionsStr = getOptionsStr(options);

        var listHtmlStr =
            "    <div id=\"wrapper"+id+"\" class='wrapper'>"+
            "    <div id=\"scroller\">"+
            "        <fieldset data-role=\"controlgroup\" id='fieldset"+id+"' data-corners='false'>"+
            optionsStr +
            "        </fieldset>"+
            "    </div>"+
            "    </div>";

        $(select).append(listHtmlStr);
        $(select).trigger("create");

        myScroll = new IScroll('#wrapper'+id, { mouseWheel: true });

        // 返回已选择的值
        this.getSelectListValue = function(){
            selectIds="";
            selectDisp="";
            var aObj = $("input[class^="+id+"]");
            for(var i=0;i<aObj.length;i++){
                if(aObj[i].checked == true){
                    selectIds += aObj[i].id + SPECIAL_CHAR;
                    if(Validator.isEmpty(detailKey)){
                        selectDisp += $("#label_"+aObj[i].id).text() + SPECIAL_CHAR;
                    }else{
                        selectDisp += $("#label_"+aObj[i].id+" span[class^=disp]").text() + SPECIAL_CHAR;
                    }
                }
            }
            return [removeFirstLastChar(selectIds, SPECIAL_CHAR), removeFirstLastChar(selectDisp, SPECIAL_CHAR)];
        }

        this.addPagerFooter = function (callback){
            var footerStr = "<div class='ui-checkbox ui-btn' id='pagerFooter_"+id+"'>点击加载下一页</div>";
            $("fieldset > div[class^=ui-controlgroup-controls]").append(footerStr);
            $("#pagerFooter_"+id).bind("click", callback);
            $(select).trigger("create");
            myScroll.refresh();
        }

        this.removePagerFooter = function(){
            $("#pagerFooter_"+id).remove();
            myScroll.refresh();
        }

        this.addData = function(addOptions){
            var addOptionStr = getOptionsStr(addOptions);
            $("#pagerFooter_"+id).remove();
            $("fieldset > div[class^=ui-controlgroup-controls]").append(addOptionStr);
            $(select).trigger("create");
            myScroll.refresh();
        }

        /**
         * 将json数据拼接成html代码
         * @param optionsJson
         * @returns {string}
         */
        function getOptionsStr(optionsJson) {
            var optionStr = "";
            $(optionsJson).each(function(index, ite){
                var selected = ite.selected;

                if(selected == "1" || selected == 1){
                    selected = "checked='checked'";
                }else{
                    selected = "";
                }

                var inputId = ite.key;
                var labelStr = "<label for=\""+inputId+"\" id=\"label_"+inputId+"\">" + ite.description +"</label>";

                if(detailKey!=null && detailKey!=""){
                    labelStr = "<label for=\""+inputId+"\" id=\"label_"+inputId+"\">"
                        + "<span class='disp'>"+ite.description+"</span>"
                        + "<br><span style='font-weight: 400;font-size: small;'>"+ite[detailKey]+"</span>"
                        + "</label>";
                }

                optionStr = optionStr +
                    "<input name=\""+id+"\" id=\""+inputId+"\" type=\""+type+"\" class='"+id+"' "+selected+" value='" + ite.description + "'>"+
                    labelStr;
            });
            return optionStr;
        }
    }
});