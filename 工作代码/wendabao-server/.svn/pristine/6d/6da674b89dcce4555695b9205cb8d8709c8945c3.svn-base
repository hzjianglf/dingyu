/**
 * Created by Administrator on 2015/11/28.
 */
var DelieverProviderDo = {
    status: "",
    basePath: "",
    table_header_type: ["<table class=\"table table-bordered table_type\">",
        "							<tr>",
        "								<td width='34%'>重量(区间)</td>",
        "								<td width='14%'>区内</td>",
        "								<td width='14%'>市内</td>",
        "								<td width='14%'>省内</td>",
        "								<td width='14%'>省外</td>",
        "								<td width='10%'>操作</td>",
        "							</tr>",
        "<table>"].join(""),
    init: function () {
        //DelieverProviderDo.showContainer('tab-base', 'costContainer');
        DelieverProviderDo.querySysDelivers();
        DelieverProviderDo.bindTypeAdd();
        DelieverProviderDo.initDeliver();
    },
    querySysDelivers: function(){
        sendRequest({"length": "1000"},"/bm/delieverProvider/list.do",function(json){
            $(json.data.list).each(function(i,ite){
                var html = "<option id=\""+ite.id+"\">"+ite.name+"</option>";
                $("#add_companySelect").append(html);
            });
        });
    },
    showContainer: function(tab, container){
        $("[role='presentation']").each(function (i) {
            $(this).removeClass("active");
        });
        $("#" + tab).addClass("active");
        $(".contentContainer").each(function (i) {
            $(this).hide();
        });
        $("#" + container).show();
    },
    initDeliver: function(){
        if ($(".table_type").length == 0) {
            $(".type_sum").append(DelieverProviderDo.table_header_type);
        }
    },
    bindTypeAdd: function () {
        $(".type_add").unbind("click");
        $(".type_add").bind("click", function () {
            if ($(".table_type").length == 0) {
                $(".type_sum").append(DelieverProviderDo.table_header_type);
            }
            var type_item =
                ["<tr class=\"type_item\">",
                    "		<td class='form-inline'><input type=\"text\" class=\"form-control td_input type_item_a_input\" value=\"0\" style='width: 45%;'>" +
                    "&nbsp;&nbsp;-&nbsp;&nbsp;<input type=\"text\" class=\"form-control td_input type_item_a_input\" value=\"0\"  style='width: 45%;'></td>",
                    "		<td><input type=\"text\" class=\"form-control td_input type_item_b_input\" value=\"0\"></td>",
                    "		<td><input type=\"text\" class=\"form-control td_input type_item_c_input\" value=\"0\"></td>",
                    "		<td><input type=\"text\" class=\"form-control td_input type_item_d_input\" value=\"0\"></td>",
                    "		<td><input type=\"text\" class=\"form-control td_input type_item_e_input\" value=\"0\"></td>",
                    "		<td><a class=\"type_delete\">删除</a></td>",
                    "				</tr>"].join("");
            $(".table_type").append(type_item);
            DelieverProviderDo.bindTypeDelete();
        });
    },
    bindTypeDelete: function () {
        $(".type_delete").unbind("click");
        $(".type_delete").bind("click", function () {
            var _this = $(this);
            var typeId = _this.attr("data_typeId");
            if (typeId) {
                new MallDialog("confirm", "确定删除这条价格信息吗？", {
                    leftBtnTxt: "取消",
                    rightBtnTxt: "删除",
                    onOk: function () {
                        var param = {
                            "productId": ProductDo.productId,
                            "typeId": typeId
                        }
                        sendRequest(param, "/bm/product/deleteType.do", function (json) {
                            _this.parent().parent().fadeOut(function () {
                                $(this).remove();
                                if ($(".type_item").length == 0) {
                                    $(".table_type").fadeOut(function () {
                                        $(this).remove();
                                    });
                                }
                            });
                        });
                    },
                    onCancel: function () {
                    }
                });
            } else {
                _this.parent().parent().fadeOut(function () {
                    $(this).remove();
                    if ($(".type_item").length == 0) {
                        $(".table_type").fadeOut(function () {
                            $(this).remove();
                        });
                    }
                });
            }

        });
    }
}

