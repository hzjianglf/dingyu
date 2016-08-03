/**
 * Created by apple on 15-7-24.
 */
/**
 * @author 雷蕾
 */
$.extend({

    /**
     * 树
     * @param select
     * @param data 数据格式见treeTest.json
     * @param type
     * @constructor
     */
    TreeWidget: function (select, data, type) {
        var selectIds = ""; // 用于上传的参数

        // 非叶子节点
        var liStr =
            "<li data-role=\"collapsible\" data-iconpos=\"left\" data-shadow=\"false\" data-corners=\"false\" id='{0}'" +
            " class='ui-li-static ui-body-inherit  ui-last-child'>"+
            "    <h2>" +
            "       <span>{1}</span>" +
            "   </h2>"+
            "</li>";

        // 叶子节点(再下面就是该节点包含的人员信息)
        var liCheckStr =
            "<li data-role=\"collapsible\" data-iconpos=\"left\" data-shadow=\"false\" data-corners=\"false\" id='{0}'" +
            " class='ui-li-static ui-body-inherit  ui-last-child'>"+
            "    <h2>" +
            "       <span>{1}</span>" +
            "       <div class='collapsibleCheck check-off' id='div_{2}'>" +
            "           <input type='button' id='btn_{3}' data-mini='true' data-inline='true' />" +
            "       </div>" +
            "   </h2>"+
            "</li>";

        // 人员选项
        var liEmpStr =
            "<input type='checkbox' name='{0}' id='{1}' value='{2}' class='leafCheckbox {3}'>"+
            "<label for='{4}'>{5}</label>";

        // 非叶子节点的集合
        var ulStr =
            "<ul data-role=\"listview\" class=\"ui-listview-outer\" id='{0}'>"+
            "</ul>";

        // 人员集合
        var fieldSetStr =
            "<fieldset data-role=\"controlgroup\" id='{0}' data-corners='false'>"+
            "</fieldset>";

        // 遍历根节点的子节点
        $(data).each(function(index, ite){
            setTreeItem(select, ite);
        });

        function setTreeItem(parentSelect, json){

            var orgid = json.orgId;
            var orgname = json.orgName;
            var children = json.children;

            if(json.isLeafOrg){
                // 叶子节点，子集是人员
                $(parentSelect).append(String.format(liCheckStr,orgid, orgname, orgid, orgid));
                $(parentSelect).trigger("create");
                $("#"+orgid+" > div").append(String.format(fieldSetStr,"emps_"+orgid));
                $("#"+orgid+" > div").trigger("create");
                $(children).each(function(index, ite){
                    var empId = ite.empId;
                    var empName = ite.empName;
                    $("#emps_"+orgid +" > div[class^=ui-controlgroup-controls]").append(String.format(liEmpStr, orgid, empId, empId,orgid, empId, empName));
                    $("#"+orgid+" > div").trigger("create");
                });
                $("fieldset").trigger("create");
            }else{
                // 非叶子节点，子集是机构
                $(parentSelect).append(String.format(liStr,orgid, orgname));
                $(parentSelect).trigger("create");
                $("#"+orgid+" > div").append(String.format(ulStr,"sub_"+orgid));
                $("#"+orgid+" > div").trigger("create");
                // 遍历非叶子节点的子节点
                $(children).each(function(index, ite){
                    setTreeItem("#sub_"+orgid,ite);
                });
            };
        };
        $(select).trigger("create");

        // 设置每个叶子节点上的全选按钮的点击事件（checkbox不好使）
        $("input[type='button']").bind("click", function(e){
            // 叶子节点上的全选按钮
            var inputDivSelector = "#div_"+this.id.replace("btn_","");

            // 叶子节点下的人员信息列表选项
            var checkboxesSelector = "."+this.id.replace("btn_","");

            if($(inputDivSelector).hasClass("check-on")){
                // 选中->非选中
                $(inputDivSelector).removeClass("check-on");
                $(inputDivSelector).addClass("check-off");
                $(checkboxesSelector).prop("checked",false).checkboxradio("refresh");;
            }else{
                // 非选中->选中
                $(inputDivSelector).removeClass("check-off");
                $(inputDivSelector).addClass("check-on");
                $(checkboxesSelector).prop('checked',true).checkboxradio("refresh");;
            }

            // 阻止事件冒泡
            e.stopPropagation();
            e.stopImmediatePropagation();
        });

        // 返回已选择的值
        this.getSelectListValue = function(){
            selectIds="";
            selectDisp="";
            var aObj = $("input[class^=leafCheckbox]");
            for(var i=0;i<aObj.length;i++){
                if(aObj[i].checked == true){
                    selectIds += aObj[i].id + SPECIAL_CHAR;
                }
            }
            return removeFirstLastChar(selectIds, SPECIAL_CHAR);
        }
    }
});