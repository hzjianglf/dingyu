/**
 * Created by Administrator on 2015/11/28.
 */
var ProductDo = {
    productId: "",
    imageServer:"",
    isEdit: true,
    /* 商品一级分类  */
    selectData: "",
    status: "",
    basePath: "",
    pictures: "",
    table_header_type: ["<table class=\"table table-bordered table_type\">",
        "							<tr>",
        "								<td>型号</td>",
        "								<td >价格</td>",
        "								<td >库存</td>",
        "								<td >重量(kg)</td>",
        "								<td >操作</td>",
        "							</tr>",
        "<table>"].join(""),
    table_header_param: ["<table class=\"table table-bordered table_param\">",
        "		<tr>",
        "			<td >名称</td>",
        "			<td >属性</td>",
        "			<td>操作</td>",
        "		</tr>",
        "</table>"].join(""),
    init: function () {
        plog(ProductDo.basePath);
        plog(ProductDo.basePath + "image/hd.png");
        if (ProductDo.isEdit) {
            ProductDo.requestEditProduct();
            $(".class_edit").hide();
        } else {
            ProductDo.requestAddProduct();
            ProductDo.bindClass();
        }
        ProductDo.bindTypeAdd();
        ProductDo.bindParamAdd();
        ProductDo.bindUpload();
        ProductDo.bindSubmit();
    },
    bindUpload: function () {
        $("#upload").unbind("click");
        $("#upload").bind("click", ProductDo.upload);
//    	$("#exampleInputFile").change(ProductDo.upload);
    },
    requestEditProduct: function () {
        $.ajax({
            async: true,
            url: toServerUrl("/bm/product/open/queryById.do"),
            data: {"productId": ProductDo.productId},
            dataType: 'json',
            success: function (json) {
                var product = json.product;
                var types = json.types;
                var params = json.params;
                ProductDo.pictures = json.pictures;
                ProductDo.status = json.product.status;
                $("#name").val(product.name);
                $("#describe").val(product.descText);
                if (types && types.length > 0) {
                    $(".type_sum").append(ProductDo.table_header_type);
                }
                $(types).each(function (i, type) {
                    var item =
                        ["<tr class=\"type_item type_item_has\">",
                            "		<td><input type=\"text\"",
                            "				class=\"form-control td_input type_item_a_input\"",
                            "				name=\"modal_type\" value=\"" + type.name + "\"></td>",
                            "		<td><input type=\"text\"",
                            "				class=\"form-control td_input type_item_b_input\"",
                            "				name=\"modal_price\" value=\"" + type.price + "\"></td>",
                            "		<td><input type=\"text\"",
                            "				class=\"form-control td_input type_item_c_input\"",
                            "				name=\"modal_number\" value=\"" + type.number + "\"></td>",
                            "		<td><input type=\"text\"",
                            "				class=\"form-control td_input type_item_d_input\"",
                            "				name=\"modal_weight\" value=\"" + type.weight + "\"></td>",
                            "		<td><a class=\"type_delete\" data_typeId =\"" + type.id + "\">删除</a></td>",
                            "				</tr>"].join("");
                    $(".table_type").append(item);
                    ProductDo.bindTypeDelete();
                });
                if (params.length > 0) {
                    $(".param_sum").append(ProductDo.table_header_param);
                }
                $(params).each(function (i, param) {
                    var item =
                        ["<tr class=\"param_item\">",
                            "		<td><input type=\"text\"class=\"form-control td_input param_item_a_input\" value=\"" + param.paramName + "\"></td>",
                            "		<td><input type=\"text\" class=\"form-control td_input param_item_b_input\" value=\"" + param.paramValue + "\"></td>",
                            "		<td><a class=\"param_delete\" data_paramId=\"" + param.id + "\">删除</a></td>",
                            "</tr>"].join("");
                    $(".table_param").append(item);
                    ProductDo.bindParamDelete();
                });
                ProductDo.picturesToShow();
            }
        });
    },
    picturesToShow: function () {
        //$(".picture_sum").empty();
        //var isBegin = true;
        //var is3Bei = true;
        //var pic_l = ProductDo.pictures.length;
        $(ProductDo.pictures).each(function (i, picture) {
            var image_show = "";
//            image_show +=  "<li class=\"img_li\"";
//            image_show +=        "data-url=\"\"";
//            image_show +=       "style=\"position: relative; opacity: 1; z-index: 0;\">";
//            if (picture.url) {
//                image_show += "	<img src=\"" + toImgUrl(picture.url) + "\"  > ";
//            } else {
//                image_show += "	<img src=\"" + ProductDo.basePath + "image/hd.png\" ";
//            }
//            image_show +=       "width=\"200px\" height=\"200px\">";
//            image_show +=    "<div class=\"img_action\" data_picId = \"" + picture.id + "\">";
//            image_show +=       "<a href=\"javascript:void(0)\" class=\"img_action_item update_img\"";
//            image_show +=           ">设为封面</a><a href=\"javascript:void(0)\"";
//            image_show +=      "class=\"img_action_item del_img\">删除</a>";
//            image_show +=   "</div><span class=\"btn_modify_txt\">&nbsp;</span><a  class=\"icon_add\">添加描述</a></li>";
            image_show += "<li data_picId = \"" + picture.id + "\"  class=\"img_li rel img_inDB\"";
            image_show += "					data-url=\"" + ProductDo.imageServer+picture.url + "\"";
            image_show += "					data-title=\"\" style=\"position: relative; opacity: 1; z-index: 0;\"><img class=\"product_image\"";
            image_show += "					src=\"" + ProductDo.imageServer+picture.url + "\"";
            image_show += "					 >";
            if (picture.header == "T") {
                image_show += "<img class=\" isHead head_picture\">";
            } else {
                image_show += "<img class=\"isHead\">";
            }
            image_show += "					<div class=\"img_action\" data_picId = \"" + picture.id + "\">";
            image_show += "						<a href=\"javascript:void(0)\" class=\"img_action_item update_img\"";
            image_show += "							>设为封面</a><a href=\"javascript:void(0)\"";
            image_show += "							class=\"img_action_item del_img\">删除</a>";
            image_show += "					</div> <span data_picId =\"" + picture.id + "\" class=\"btn_modify_txt\">&nbsp;</span><a  href=\"#\" rel=\"4\"";
            if (picture.descText) {
                var des = "";
                if (picture.descText.length > 10) {
                    des = picture.descText.substring(0, 10) + "...";
                } else {
                    des = picture.descText;
                }
                image_show += "data_des=\"" + picture.descText + "\" class=\"icon_modify\">" + des + "</a></li>";
            } else {
                image_show += "					class=\"icon_add\">添加描述</a></li>";
            }
            $("#img_ul").append(image_show);
            ProductDo.bindPicDelete();
            ProductDo.bindSetHeader();
            ProductDo.bindDescribeUpdate();
            ProductDo.bindMouseOutIn();
            //    if (i % 3 == 0) {
            //        is3Bei = true;
            //    } else {
            //        is3Bei = false;
            //    }
            //    if (is3Bei && isBegin) {
            //        image_show += "<div class=\"row\">";
            //        isBegin = false;
            //    }
            //    image_show += "<div class=\"col-md-3\">";
            //    image_show += "<div class=\"thumbnail\" >";
            //    if (picture.url) {
            //        image_show += "	<img src=\"" + toImgUrl(picture.url) + "\"  > ";
            //    } else {
            //        image_show += "	<img src=\"" + ProductDo.basePath + "image/hd.png\"  > ";
            //    }
            //
            //    image_show += "			<p style=\"margin-top: 10px; width: 100%; text-align: center\" index=\""+i+"\" data_picId = \"" + picture.id + "\">";
            //    image_show += "				<a  class=\"btn btn-primary as_head\"  >设为封面</a> <a";
            //    image_show += "								class=\"btn btn-default img_del\" >删除</a>";
            //    image_show += "			</p>";
            //    if (picture.descText) {
            //        image_show += "<p style=\"padding: 10px;\"><h4>" + picture.descText + "</h4></p>";
            //    }
            //    image_show += "</div>";
            //    image_show += "</div>";
            //    if (is3Bei && isBegin) {
            //        image_show += "</div>";
            //        isBegin = true;
            //    }
            //    if (i == pic_l - 1 && i % 3 != 0) {
            //        image_show += "</div>";
            //    }
        });
        //$(".picture_sum").append(image_show);

    },
    bindSetHeader: function () {
        $(".update_img").unbind("click");
        $(".update_img").bind("click", function () {
            var _this = $(this);
            var picId = _this.parent().attr("data_picId");
            var param = {
                "picId": picId,
                "relateId": ProductDo.productId,
                "type": "product"
            };
            sendRequest(param, "/bm/picture/setHeader.do", function (json) {
                _this.parents(".ui-sortable").find(".head_picture").removeClass("head_picture");
                _this.parents(".img_li").find(".isHead").addClass("head_picture");
            })
        });
    },
    bindDescribeUpdate: function () {
        $(".icon_add,.icon_modify").unbind("click");
        $(".icon_add,.icon_modify").bind("click", function () {
            var _this = $(this);
            var oldDes = "";
            if (_this.hasClass("icon_modify")) {
                oldDes = _this.attr("data_des");
            }
            showDialog("", "input", {
                onOk: function (newNumber) {
                    if (newNumber) {
                        var params = {
                            "descText": newNumber,
                            "id": _this.parents(".img_li").attr("data_picId"),
                            "relateId": ProductDo.productId,
                            "type": "product",
                        }
                        //alert(_this.parents(".img_li").attr("data_picId"));
                        sendRequest(params, "/bm/picture.do?method=descTextUpdate", function (json) {
                            new MallDialog("toast", "修改成功", "");
                            _this.removeClass("icon_add");
                            _this.addClass("icon_modify");
                            _this.attr("data_des", newNumber);
                            if (newNumber.length > 10) {
                                _this.html(newNumber.substring(0, 10) + "...");
                            } else {
                                _this.html(newNumber);
                            }
                        });
                    }
                },
            }, oldDes);
        });
    },
    bindMouseOutIn: function () {
        $(".img_li").unbind("mouseover mouseout");
        $(".img_li").bind("mouseover",function(){
            $(this).find(".img_action").show();
        });
        $(".img_li").bind("mouseout",function(){
            $(this).find(".img_action").hide();
        });
        //$(".img_li").mouseover(function () {
        //    $(this).find(".img_action").show();
        //});
        //$(".img_li").mouseout(function () {
        //    $(this).find(".img_action").hide();
        //})
    },
    bindPicDelete: function () {
        $(".del_img").unbind("click");
        $(".del_img").bind("click", function () {
            var _this = $(this);
            var imgLi = _this.parents(".img_li");
           
            new MallDialog("confirm", "确定删除吗？", {
                leftBtnTxt: "取消",
                rightBtnTxt: "删除",
                onOk: function () {
                    // 当点击确定按钮时的处理
                    var param = {
                        "pictureId": imgLi.attr("data_picId"),
                        "relateId": ProductDo.productId,
                        "type": "product",
                    }
                    $.ajax({
                        url: toServerUrl("/bm/picture.do?method=delete"),
                        data: param,
                        type: "post",
                        dataType: "json",
                        success: function (data) {
                            //ProductDo.pictures.splice(_this.parent().attr("index"),1);
                            //ProductDo.picturesToShow();
                        	
                        	imgLi.fadeOut(function () {
                        		if(imgLi.find(".head_picture").length==1){
                             		 var nextImg = imgLi.next();
                             		 if(nextImg.length!=0){
                             			 imgLi.next().find(".isHead").addClass("head_picture");
                             			//默认第一张为封面图片
                                       	var param = {
                                                   "picId": nextImg.attr("data_picid"),
                                                   "relateId": ProductDo.productId,
                                                   "type": "product"
                                               };
                                           sendRequest(param, "/bm/picture/setHeader.do", function (json) {
                                         	  
                                           });
                             		 }
                             	 }
                                $(this).remove();
                            });
                           
                        }
                    });
                },
                onCancel: function () {
                    // 当点击取消按钮时的处理
                }
            });
        });
    },
    requestAddProduct: function () {
        ProductDo.status = "T";
        $(".type_sum").append(ProductDo.table_header_type);
        var type_item =
            ["<tr class=\"type_item\">",
                "		<td><input type=\"text\"",
                "				class=\"form-control td_input type_item_a_input\"",
                "				placeholder=\"请输入型号\" ></td>",
                "		<td><input type=\"text\"",
                "				class=\"form-control td_input type_item_b_input\"",
                "				placeholder=\"请输入价格\" ></td>",
                "		<td><input type=\"text\"",
                "				class=\"form-control td_input type_item_c_input\"",
                "				placeholder=\"请输入库存\" value=\"0\"></td>",
                "		<td><input type=\"text\"",
                "				class=\"form-control td_input type_item_d_input\"",
                "				placeholder=\"请输入重量\" value=\"0\"></td>",
                "		<td><a class=\"type_delete\">删除</a></td>",
                "				</tr>"].join("");
        $(".table_type").append(type_item);
        ProductDo.bindTypeDelete();
        $(".param_sum").append(ProductDo.table_header_param);
        var param_item =
            ["<tr   class=\"param_item\">",
                "<td><input type=\"text\"class=\"form-control td_input param_item_a_input\"placeholder=\"请输入名称\"></td>",
                "<td><input type=\"text\" class=\"form-control td_input param_item_b_input\" placeholder=\"请输入属性\"></td>",
                "<td><a class=\"param_delete\">删除</a></td>",
                "</tr>"].join("");
        $(".table_param").append(param_item);
        ProductDo.bindParamDelete();
    },
    bindTypeAdd: function () {
        $(".type_add").unbind("click");
        $(".type_add").bind("click", function () {
            if ($(".table_type").length == 0) {
                $(".type_sum").append(ProductDo.table_header_type);
            }
            var type_item =
                ["<tr class=\"type_item\">",
                    "		<td><input type=\"text\"",
                    "				class=\"form-control td_input type_item_a_input\"",
                    "				placeholder=\"请输入型号\" ></td>",
                    "		<td><input type=\"text\"",
                    "				class=\"form-control td_input type_item_b_input\"",
                    "				placeholder=\"请输入价格\" ></td>",
                    "		<td><input type=\"text\"",
                    "				class=\"form-control td_input type_item_c_input\"",
                    "				placeholder=\"请输入库存\" value=\"0\"></td>",
                    "		<td><input type=\"text\"",
                    "				class=\"form-control td_input type_item_d_input\"",
                    "				placeholder=\"请输入重量\" value=\"0\"></td>",
                    "		<td><a class=\"type_delete\">删除</a></td>",
                    "				</tr>"].join("");
            $(".table_type").append(type_item);
            ProductDo.bindTypeDelete();
        });
    },
    bindParamAdd: function () {
        $(".param_add").unbind("click");
        $(".param_add").bind("click", function () {
            if ($(".table_param").length == 0) {
                $(".param_sum").append(ProductDo.table_header_param);
            }
            var param_item =
                ["<tr   class=\"param_item\">",
                    "<td><input type=\"text\"class=\"form-control td_input param_item_a_input\"placeholder=\"请输入名称\"></td>",
                    "<td><input type=\"text\" class=\"form-control td_input param_item_b_input\" placeholder=\"请输入属性\"></td>",
                    "<td><a class=\"param_delete\">删除</a></td>",
                    "</tr>"].join("");
            $(".table_param").append(param_item);
            ProductDo.bindParamDelete();
        });
    },
    bindTypeDelete: function () {
        $(".type_delete").unbind("click");
        $(".type_delete").bind("click", function () {
            var _this = $(this);
            var typeId = _this.attr("data_typeId");
            if (typeId) {
                new MallDialog("confirm", "确定删除吗？", {
                    leftBtnTxt: "取消",
                    rightBtnTxt: "删除",
                    onOk: function () {
                    	if($(".type_item").length==1){
                    		showDialog("型号必须留一条，您可以尝试修改","toast","","");
                    		return;
                    	}
                        // 当点击确定按钮时的处理
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
                        // 当点击取消按钮时的处理
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
    },
    bindParamDelete: function () {
        $(".param_delete").unbind("click");
        $(".param_delete").bind("click", function () {
            var _this = $(this);
            var paramId = _this.attr("data_paramId");
            if (paramId) {
                new MallDialog("confirm", "确定删除吗？", {
                    leftBtnTxt: "取消",
                    rightBtnTxt: "删除",
                    onOk: function () {
                        // 当点击确定按钮时的处理
                        var param = {
                            "productId": ProductDo.productId,
                            "paramId": paramId
                        }
                        sendRequest(param, "/bm/product/deleteParam.do", function (json) {
                            _this.parent().parent().fadeOut(function () {
                                $(this).remove();
                                if ($(".param_item").length == 0) {
                                    $(".table_param").fadeOut(function () {
                                        $(this).remove();
                                    });
                                }
                            });
                        });
                    },
                    onCancel: function () {
                        // 当点击取消按钮时的处理
                    }
                });
            } else {
                _this.parent().parent().fadeOut(function () {
                    $(this).remove();
                    if ($(".param_item").length == 0) {
                        $(".table_param").fadeOut(function () {
                            $(this).remove();
                        });
                    }
                });
            }
        });
    },
    bindSubmit: function () {
        $(".submit").unbind("click");
        $(".submit").bind("click", function () {
            var _this = $(this);
            if (_this.hasClass("isLoading")) {
                return;
            }
            _this.addClass("isLoading").html("loading...");
            var classId = $("#selectThree").val();
            if (!classId && !ProductDo.isEdit) {
                new MallDialog("toast", "请选择商品分类", "");
                _this.removeClass("isLoading").html("提交");
                return;
            }

            var title = $("#name").val();
            var descrbie = $("#describe").val();
            if (!title) {
                new MallDialog("toast", "标题不能为空", "");
                _this.removeClass("isLoading").html("提交");
                return;
            }
            if (!descrbie) {
                new MallDialog("toast", "描述不能为空", "");
                _this.removeClass("isLoading").html("提交");
                return;
            }
            var typeId_s = "";
            var typeName_s = "";
            var typeNumber_s = "";
            var typeWeight_s = "";
            var typePrice_s = "";
            var paramId_s = "";
            var paramName_s = "";
            var paramValue_s = "";
            //检查各个型号的输入情况
            var item_type = $(".type_item");
            var item_type_l = item_type.length;
            var typeArr = new Array();
            var temp_i = 0;//干啥用的？自己猜～～～
            if (item_type_l) {
                for (var i = 0; i < item_type_l; i++) {
                    var _target = item_type.eq(i);
                    var type_item_a_input = _target.find(".type_item_a_input").val();
                    var type_item_b_input = _target.find(".type_item_b_input").val();
                    var type_item_c_input = _target.find(".type_item_c_input").val();
                    var type_item_d_input = _target.find(".type_item_d_input").val();
                    if (type_item_a_input || type_item_b_input || type_item_c_input || type_item_d_input) {
                        if (!(type_item_a_input && type_item_b_input && type_item_c_input && type_item_d_input)) {
                            new MallDialog("toast", "请完整填写第" + (i + 1) + "个型号", "");
                            _this.removeClass("isLoading").html("提交");
                            return;
                        }
                        else {
                            if (isNaN(type_item_b_input)) {//价格
                                //   showToast("型号" + (i + 1) + " 的价格不是数字");
                                new MallDialog("toast", "型号" + (i + 1) + " 的价格不是数字", "");
                                _this.removeClass("isLoading").html("提交");
                                return;
                            }
                            if (type_item_b_input <= 0) {
                                new MallDialog("toast", "型号" + (i + 1) + " 的价格不能为0", "");
                                _this.removeClass("isLoading").html("提交");
                                return;
                            }

                            if (isNaN(type_item_c_input)) {//库存
                                new MallDialog("toast", "型号" + (i + 1) + " 的库存不是数字", "");
                                _this.removeClass("isLoading").html("提交");
                                return;
                            }

                            if (type_item_c_input < 0) {
                                new MallDialog("toast", "型号" + (i + 1) + " 的库存不能低于0", "");
                                _this.removeClass("isLoading").html("提交");
                                return;
                            }
                            if (isNaN(type_item_d_input)) {//库存
                                new MallDialog("toast", "型号" + (i + 1) + " 的重量不是数字", "");
                                _this.removeClass("isLoading").html("提交");
                                return;
                            }

                            if (type_item_d_input < 0) {
                                new MallDialog("toast", "型号" + (i + 1) + " 的重量不能低于0", "");
                                _this.removeClass("isLoading").html("提交");
                                return;
                            }
                            var obj = new Object();
                            if (ProductDo.isEdit) {//如果是更新操作 带着sku-id
                                obj.id = _target.find(".type_delete").attr("data_typeId");
                            }
                            obj.title = type_item_a_input;
                            obj.price = type_item_b_input;
                            obj.stock = type_item_c_input;
                            obj.weight = type_item_d_input;//型号
                            typeArr.push(obj);
                        }
                    } else {
                        temp_i++;
                    }
                }
                if (temp_i == item_type_l) {
                    new MallDialog("toast", "型号都空着呢呀", "");
                    _this.removeClass("isLoading").html("提交");
                    return;
                }
                if (temp_i > 0) {
                    new MallDialog("toast", "存在未填写的型号哦", "");
                    _this.removeClass("isLoading").html("提交");
                    return;
                }
            } else {
                new MallDialog("toast", "请至少填写一种型号", "");
                _this.removeClass("isLoading").html("提交");
                return;
            }
            //检查各个参数的输入情况
            var item_param = $(".param_item");
            var item_param_l = item_param.length;
            var paramArr = new Array();
            var temp_param_i = 0;
            if (item_param_l) {
//                    $(item_param).each(function (i, param) {
                for (var i = 0; i < item_param_l; i++) {
                    var _target = item_param.eq(i);
                    var param_item_a_input = _target.find(".param_item_a_input").val();
                    var param_item_b_input = _target.find(".param_item_b_input").val();
                    if (param_item_a_input || param_item_b_input) {
                        if (!(param_item_a_input && param_item_b_input)) {
                            new MallDialog("toast", "请完整填写第" + (i + 1) + "个参数", "");
                            _this.removeClass("isLoading").html("提交");
                            return;
                        }
                        var obj = new Object();
                        if (ProductDo.isEdit) {//如果是更新操作 带着sku-id
                            obj.id = _target.find(".param_delete").attr("data_paramId");
                        }
                        obj.title = param_item_a_input;
                        obj.value = param_item_b_input;
                        paramArr.push(obj);
                    } else {
                        temp_param_i++;
                    }
                }
                if (temp_param_i == item_param_l) {
                    new MallDialog("toast", "参数都空着呢呀", "");
                    _this.removeClass("isLoading").html("提交");
                    return;
                }
                if (temp_param_i > 0) {
                    new MallDialog("toast", "存在未填写的参数", "");
                    _this.removeClass("isLoading").html("提交");
                    return;
                }
            }
            //遍历型号数组，取得值
            $(typeArr).each(function (t, type) {
                if (!type.id) {
                    type.id = "0";
                }
                typeId_s += type.id + SPECIAL_CHAR;
                typeName_s += type.title + SPECIAL_CHAR;
                typeNumber_s += type.stock + SPECIAL_CHAR;
                typeWeight_s += type.weight + SPECIAL_CHAR;
                typePrice_s += type.price + SPECIAL_CHAR;
            });
            typeId_s = removeFirstLastChar(typeId_s, SPECIAL_CHAR);
            typeName_s = removeFirstLastChar(typeName_s, SPECIAL_CHAR);
            typeNumber_s = removeFirstLastChar(typeNumber_s, SPECIAL_CHAR);
            typeWeight_s = removeFirstLastChar(typeWeight_s, SPECIAL_CHAR);
            typePrice_s = removeFirstLastChar(typePrice_s, SPECIAL_CHAR);
            //遍历参数数组，取得值
            $(paramArr).each(function (t, param) {
                if (!param.id) {
                    param.id = "0";
                }
                paramId_s += param.id + SPECIAL_CHAR;
                paramName_s += param.title + SPECIAL_CHAR;
                paramValue_s += param.value + SPECIAL_CHAR;
            });
            paramId_s = removeFirstLastChar(paramId_s, SPECIAL_CHAR);
            paramName_s = removeFirstLastChar(paramName_s, SPECIAL_CHAR);
            paramValue_s = removeFirstLastChar(paramValue_s, SPECIAL_CHAR);
            var params = {
                "descText": descrbie,
                "name": title,
                "id": ProductDo.productId,
                "paramIds": paramId_s,
                "paramNames": paramName_s,
                "paramValues": paramValue_s,
                "productClass": classId,
                "typeId": typeId_s,
                "status": ProductDo.status,
                "typeName": typeName_s,
                "typeNumber": typeNumber_s,
                "typeWeight": typeWeight_s,
                "typePrice": typePrice_s,
            }
            plog("params:" + params);
            sendRequest(params, "/bm/product/save.do", function () {
                if (ProductDo.isEdit) {
                    new MallDialog("toast", "商品更新成功", "");
                } else {
                    new MallDialog("toast", "商品保存成功", "");
                }
                setTimeout(function(){
                	 _this.removeClass("isLoading").html("提交");
                	  $("#changeIframe",parent.document).attr("src", toServerPageUrl("/bm/product/productDetail.do?productId="+ProductDo.productId));
                }, 2000)
              
            });
            //$.ajax({
            //    url: toServerUrl("/bm/product/save.do"),
            //    data: params,
            //    type: "post",
            //    dataType: "json",
            //    success: function (data) {
            //
            //    }
            //});
        });
    },
    bindClass: function () {
        ProductDo.selectSort();
        //ProductDo.selectTowSort;
        //ProductDo.selectThreeSort;

    },
    selectSort: function () {
        $.ajax({
            url: toServerUrl("/bm/product/classTree.do"),
            type: "post",
            dataType: "json",
            success: function (data) {
                plog("---------获得总分类-------");
                plog(data);
                if (data.header.success == true) {
                    var treeOne = data.tree.subNodes;
                    ProductDo.selectData = treeOne;
                    $("#selectOne").append("<option value='0'>选择分类</option>");
                    for (var i = 0; i < treeOne.length; i++) {
                        var element = treeOne[i].element;
                        var name = element.name;
                        var id = element.id;
                        var parentId = element.parentId;
                        $("#selectOne").append("<option value='" + id + "'>" + name + "</option>");
                    }
                }
                $("#selectOne").change(ProductDo.selectTowSort);
                $("#selectTwo").change(ProductDo.selectThreeSort);
            }
        });
    },
    /* 商品二级分类  */
    selectTowSort: function () {
        plog("---------获得二级分类-------");
        $("#selectTwo").empty();
        $("#selectThree").empty();
        var selectOne = $("#selectOne").val();
        var selectTow = null;
        for (var m = 0; m < ProductDo.selectData.length; m++) {
            var element1 = ProductDo.selectData[m].element;
            if (element1.id == selectOne) {
                var selectTowData = ProductDo.selectData[m].subNodes;
                if (null != selectTowData && selectTowData.length != 0) {
                    $("#selectTwo").append("<option value='-1'>请选择分类</option>");
                    for (var n = 0; n < selectTowData.length; n++) {
                        var element2 = selectTowData[n].element;
                        var name = element2.name;
                        var id = element2.id;
                        var parentId = element2.parentId;
                        if (selectOne == parentId) {
                            $("#selectTwo").append("<option value='" + id + "'>" + name + "</option>");
                        }
                    }
                }
            }
        }
    },
    /* 商品三级分类  */
    selectThreeSort: function () {
        plog("---------获得三级分类-------");
        $("#selectThree").empty();
        var selectTwo = $("#selectTwo").val();
        for (var m = 0; m < ProductDo.selectData.length; m++) {
            var selectTowData = ProductDo.selectData[m].subNodes;
            if (null != selectTowData && selectTowData.length != 0) {
                for (var n = 0; n < selectTowData.length; n++) {
                    var element2 = selectTowData[n];
                    var parentId = element2.element.id;
                    if (selectTwo == parentId) {
                        var selectThreeData = element2.subNodes;
                        $("#selectThree").append("<option value='-1'>请选择分类</option>");
                        for (var i = 0; i < selectThreeData.length; i++) {
                            var element3 = selectThreeData[i].element;
                            var name = element3.name;
                            var id = element3.id;
                            $("#selectThree").append("<option value='" + id + "'>" + name + "</option>");
                        }
                    }
                }
            }
        }
    },

    upload: function () {
    	if($(".img_li").length>9){
    		showDialog("最多只能上传9张图片","toast", "","");
    		return;
    	}

        var id = ProductDo.productId;
        var myfile = $("#myfile").val();
        if (myfile != "") {
            $.ajaxFileUpload({
                url: toServerUrl("/bm/picture/picUpload.do?type=" + "product&relateId=" + id),
                type: "post",
                fileElementId: "myfile",
                dataType: "text",
                success: function (data) {

                    plog(data);
                    var text = $(data).html();
                    var obj = JSON.parse(text);
                    var pics = obj.data;
                    var header = obj.header;
                    plog("success:"+header.success);
                    if(!header.success){
                        showDialog("你的图片过大","toast","","");
                        return ;
                    }
                    plog("pics:" + pics);
                   
                    
                    $(pics).each(function (i, picture) {
                    	
                        var image_show = "";
                        image_show += "<li data_picId = \"" + picture.id + "\"  class=\"img_li rel img_inDB\"";
                        image_show += "					data-url=\"" +ProductDo.imageServer+picture.realUrl + "\"";
                        image_show += "					data-title=\"\" style=\"position: relative; opacity: 1; z-index: 0;\"><img class=\"product_image \"";
                        image_show += "					src=\"" +ProductDo.imageServer+picture.realUrl + "\"";
                        image_show += "					width=\"200\" height=\"200\" >";
                        if (picture.header == "T") {
                            image_show += "<img class=\" isHead head_picture\">";
                        } else {
                            image_show += "<img class=\"isHead\">";
                        }
                        image_show += "					<div class=\"img_action\" data_picId = \"" + picture.id + "\">";
                        image_show += "						<a href=\"javascript:void(0)\" class=\"img_action_item update_img\"";
                        image_show += "							>设为封面</a><a href=\"javascript:void(0)\"";
                        image_show += "							class=\"img_action_item del_img\">删除</a>";
                        image_show += "					</div> <span data_picId =\"" + picture.id + "\" class=\"btn_modify_txt\">&nbsp;</span><a  href=\"#\" rel=\"4\"";
                        if (picture.descText) {
                            var des = "";
                            if (picture.descText.length > 10) {
                                des = picture.descText.substring(0, 10) + "...";
                            } else {
                                des = picture.descText;
                            }
                            image_show += "data_des=\"" + picture.descText + "\" class=\"icon_modify\">" + des + "</a></li>";
                        } else {
                            image_show += "					class=\"icon_add\">添加描述</a></li>";
                        }
                        setTimeout(function () {
                            $("#img_ul").append(image_show);
                            ProductDo.bindPicDelete();
                            ProductDo.bindSetHeader();
                            ProductDo.bindDescribeUpdate();
                            ProductDo.bindMouseOutIn();
                            if($(".img_li").length==1){
                            	$(".img_li").find(".isHead").addClass("head_picture");
                             	//默认第一张为封面图片
                             	var param = {
                                         "picId": picture.id,
                                         "relateId": ProductDo.productId,
                                         "type": "product"
                                     };
                                 sendRequest(param, "/bm/picture/setHeader.do", function (json) {
                                 });
                             }
                        }, 2000);
                      
                    });
                  
                }
            });
        } else {
            alert("请至少选择一个文件执行上传操作");
        }

    },

    /* 上传商品图片  */
    upload1: function () {
        var id = ProductDo.productId;
        alert(id);
        var myfile = $("#exampleInputFile").val();
        plog(myfile);
        if (myfile) {
            $.ajaxFileUpload({
                url: toServerUrl("/bm/picture/picUpload.do?type=" + "product&relateId=" + id),
                type: "post",
                fileElementId: "exampleInputFile",
                dataType: "json",
                success: function (data) {
                    plog(data);
                    //var text = $(data).html();
                    //var obj = JSON.parse(text);
                    //var pics = obj.data;
                    //for (var i = 0; i < pics.length; i++) {
                    //    var d = document.getElementById("div1");
                    //    d.style.backgroundColor = "white";
                    //    $("#productImg").append("<div class='col-md-2'>" + "<img class='img-rounded delImg' data-picId='" + pics[0].id + "' style='width:100px;height:100px' src='" + picPath + pics[0].url + "'/></div>");
                    //}
                    ///* 绑定事件之前先解除绑定  */
                    //$(".delImg").unbind("click");
                    //$(".delImg").bind("click", function () {
                    //    var _this = $(this);
                    //    var picId = _this.attr("data-picId");
                    //    if (confirm("确定要删除该图片吗？")) {
                    //        $.ajax({
                    //            url: toServerUrl("/bm/picture.do?method=delete"),
                    //            data: {
                    //                pictureId: picId,
                    //                relateId: productId,
                    //                type: "product",
                    //            },
                    //            type: "post",
                    //            dataType: "json",
                    //            success: function (data) {
                    //                if (data.header.success == true) {
                    //                    _this.parent().remove();
                    //                }
                    //            }
                    //        });
                    //    }
                    //});
                }
            });
        } else {
            alert("请至少选择一个文件执行上传操作");
        }
    }
}

