
var NewOrderDo = {
	bathpath:"",
    custSelectModal: ["<div class=\"modal fade\" id=\"custSelectModal\">",
        "  <div class=\"modal-dialog\">",
        "    <div class=\"modal-content\">",
        "      <div class=\"modal-header\" style=\"border-bottom:0px\" style=\"background:#f4f5fe\">",
        "        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button>",
        "        <h4 class=\"modal-title\" style=\"margin:20px;\">客户选择</h4>",
        "           <div class=\"row\" >",
        "      <div class=\"col-sm-1\"  style=\"padding-left:5px\">",
        "      </div>",
        "      	 <div class=\"col-sm-8\"  style=\"padding-left:5px\">",
        "      	 	<input type=\"text\" class=\"form-control\" placeholder=\"请输入客户的手机号\" id=\"buyerPhone\">",
        "      	 </div>",
        "      	 <div class=\"col-sm-3\"  style=\"padding-left:5px\">",
        "      	 <button type=\"button\" class=\"btn btn-primary search_btn\" >查询</button>",
        "      	 </div>",
        "      </div>",
        "      </div>",
        "      <div class=\"modal-body\" style=\"padding:1px;height:25em;overflow-y:scroll\">",
        "	  	 <div class=\"list-group\" id=\"custList\">	</div>",
        "      </div>",
        "      <div class=\"modal-footer\" style=\"border-top:0px\">",
        "        <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">关闭</button>",
        "      </div>",
        "    </div><!-- /.modal-content -->",
        "  </div><!-- /.modal-dialog -->",
        "</div><!-- /.modal -->"].join(""),

    productType: ["<div class=\"modal fade\" id=\"productType\" tabindex=\"-1\" role=\"dialog\" ",
        "   aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">",
        "   <div class=\"modal-dialog\">",
        "      <div class=\"modal-content\">",
        "         <div class=\"modal-header\">",
        "            <button type=\"button\" class=\"close\" data-dismiss=\"modal\" ",
        "               aria-hidden=\"true\">×",
        "            </button>",
        "            <h5 class=\"modal-title\" id=\"productType_title\">",
        "           		  选择商品",
        "            </h5>",
        "         </div>",
        "         <div class=\"modal-body\">",
        "           <div class=\"row\" style=\"border-bottom:1px dotted #f3f4fe;margin-bottom:10px;\">",
        "         	 <div class=\"col-sm-3\"  style=\"padding-left:5px\">",
        "         	 <img src=\"'<%=basePath %>'image/icon.png\" class=\"img-responsive\" id=\"productType_img\" style=\"height:100px;width:100px\">",
        "         	 </div>",
        "         	 <div class=\"col-sm-9\"  style=\"padding-left:5px\">",
        "         	<h4 class=\"productName\" id=\"productType_pname\"></h4>",
        "         	<h5 class=\"\" id=\"productType_desc\" ></h5>",
        "			<h5 class=\"price\" >￥<span id=\"productType_price\"></span></h5>",
        "         	 </div>",
        "         </div>",
        "         <div class=\"row\">",
        "         	 <div class=\"col-sm-3\"  style=\"padding-left:5px;padding-top:10px;\">",
        "         	 	<label style=\"float:right;\">型号</label>",
        "         	 </div>",
        "         	 <div class=\"col-sm-9 \"  style=\"padding-left:5px\">",
        "         	 ",
        "         	 <input type=\"hidden\" id=\"selected_productId\">",
        "	         	<input type=\"hidden\" id=\"selected_weight\">",
        "	         	<input type=\"hidden\" id=\"selected_price\">",
        "	         	<input type=\"hidden\" id=\"selected_typeId\">",
        "				<div class=\"btn-group\" role=\"group\" id=\"productType_group\">",
        "				",
        "				</div>",
        "         	 </div>",
        "         </div>",
        "         <div class=\"row\"   style=\"padding-left:5px;padding-top:10px;\">",
        "         	 <div class=\"col-sm-3\"  style=\"padding-left:5px;padding-top:10px;\"><label style=\"float:right\">数量</label></div>",
        "         	 <div class=\"col-sm-9\"  style=\"padding-left:5px\">",
        "         	  <button type=\"button\" class=\"btn btn-primary sub_number\" style=\"float:left;width:40px\" >-</button> ",
        "            	&nbsp;<input type=\"text\" id=\"productType_number\" class=\"form-control\" readonly style=\"background:#fff;;width:60px;float:left;text-align: right;margin-left:2px;margin-right:2px;\" value=\"1\">",
        "            	&nbsp;<button type=\"button\" class=\"btn btn-primary add_number \" style=\"float:left;width:40px\" >+</button>",
        "         	 </div>",
        "         </div>",
        "         ",
        "         ",
        "         <div class=\"modal-footer\">",
        "         	<span id=\"appendOrderMsg\" style=\"color:#ff0000\"></span>",
        "            <button type=\"button\" class=\"btn btn-default\" ",
        "               data-dismiss=\"modal\">关闭",
        "            </button>",
        "            <button  id=\"add2order_type\" type=\"button\" class=\"btn btn-primary\">",
        "              			 加入订单",
        "            </button>",
        "         </div>",
        "        </div>",
        "      </div><!-- /.modal-content -->",
        "   </div><!-- /.modal-dialog -->",
        "</div><!-- /.modal -->"].join(""),
    orderSumitModal: ["<div class=\"modal fade\" id=\"orderSumitModal\">",
        "  <div class=\"modal-dialog\"  style=\"width:1000px\">",
        "    <div class=\"modal-content\">",
        "      <div class=\"modal-header\" style=\"border-bottom:0px\" style=\"background:#f4f5fe\">",
        "        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button>",
        "        <h4 class=\"modal-title\" style=\"margin:0px;\">新建订单详情</h4>",
        "      </div>",
        "      <div class=\"modal-body\" style=\"padding-top:0px;\">",
        "      <div class=\"row\">",
        "    ",
        "	  	 <div class=\"col-sm-7\" id=\"order_itemList\" style=\"padding:5px;height:25em;overflow-y:scroll\">",
        "			<table class=\"table table-condensed table-hover\" style=\"\">",
        "			<thead>",
        "				<tr>",
        "					<th style=\"width:40%;text-align:center\">名称</th>",
        "					<th style=\"text-align:center\">型号</th>",
        "					<th style=\"text-align:center\">单价</th>",
        "					<th style=\"text-align:center\">数量</th>",
        "					<th style=\"text-align:center\">合计</th>",
        "				</tr>",
        "			</thead>",
        "			<tbody id=\"order_items\">",
        "		",
        "			</tbody>",
        "			  ",
        "			</table>",
        "		</div>",
        "		",
        "		",
        "		  	 <div class=\"col-sm-5\"  >",
        "		  <div class=\"row\" >",
        "		     <div class=\"col-sm-3\"  >",
        "		      	<label style=\"float:right;margin-top:5px;\">客户昵称</label>",
        "		     </div>",
        "	      	 <div class=\"col-sm-9\"  >",
        "	      	 <input type=\"text\" readonly class=\"form-control\" id=\"order_custName\"  style=\"background:#fff\">",
        "	      	 <input type=\"hidden\" id=\"order_itemSize\">",
        "      		 </div>",
        "      	</div>",
        "      	   ",
        "      	 <div class=\"row\">",
        "		     <div class=\"col-sm-3\"  >",
        "		      	<label  style=\"float:right\">收货人</label>",
        "		     </div>",
        "	      	 <div class=\"col-sm-9\"  >",
        "	      	 	<input type=\"text\" id=\"\" class=\"form-control\" id=\"order_receiver\" placeHolder=\"请输入收货联系人\">",
        "      	 	</div>",
        "      	</div>",
        "      	<div class=\"row\">",
        "		     <div class=\"col-sm-3\"  >",
        "		      	<label style=\"float:right\">收货电话</label>",
        "		     </div>",
        "	      	 <div class=\"col-sm-9\"  >",
        "	      	 	<input type=\"text\" id=\"\" class=\"form-control\" id=\"\" placeHolder=\"请输入收货联系电话\">",
        "      	 	</div>",
        "      	</div>",
        "      	",
        "      	<div class=\"row\">",
        "		     <div class=\"col-sm-3\"  >",
        "		      	<label style=\"float:right\">送货方式</label>",
        "		     </div>",
        "	      	 <div class=\"col-sm-9\"  >",
        "	      	 	<select id=\"order_delieverType\"  class=\"form-control\" >",
        "	      	 		<option value=\"1\">自取</option>",
        "	      	 		<option value=\"2\">物流</option>",
        "	      	 	</select>",
        "      	 	</div>",
        "      	</div>",
        "      	",
        "      	<div class=\"row\" id=\"row_orderAddress\" style=\"display:none\">",
        "		     <div class=\"col-sm-3\"  >",
        "		      	<label style=\"float:right\">收货地址</label>",
        "		     </div>",
        "	      	 <div class=\"col-sm-9\"  >",
        "	      	 	<textarea id=\"\" class=\"form-control\" placeHolder=\"请输入收货地址\" rows=\"3\" id=\"order_addressTxt\"></textarea>",
        "      	 	</div>",
        "      	</div>",
        "      	<div class=\"row\">",
        "		     <div class=\"col-sm-3\"  >",
        "		      	<label style=\"float:right\">备注</label>",
        "		     </div>",
        "	      	 <div class=\"col-sm-9\"  >",
        "	      	 	<textarea id=\"\" class=\"form-control\" placeHolder=\"请输入备注\" onchange=\"changeDelieverFee(this)\" rows=\"3\" id=\"order_remark\"></textarea>",
        "      	 	</div>",
        "      	</div>",
        "      	",
        "    ",
        "		     ",
        "		  </div>",
        "		",
        "      </div>",
        "      </div>",
        "      <div class=\"modal-footer\" style=\"border-top:0px;background:#f4f5fe\">",
        "      <div class=\"row\">",
        "      	<div class=\"col-sm-3\"  >",
        "     	 	<label style=\"float:left\">订单总价：￥<span id=\"order_total\" style=\"color:#ff0000;margin-right:15px;\">0.00</span></label>",
        "      	</div>",
        "      	 <div class=\"col-sm-4\"  style=\"text-align:left\">",
        "      		<label>实际付款额:</label> <input type=\"text\"  style=\"width:120px;text-align:right\" id=\"order_realTotal\" value=\"0.00\">",
        "     </div>",
        "     <div class=\"col-sm-5\"  >",
        "     <span id=\"order_msg\" style=\"color:#ff0000\"></span>",
        "       <button type=\"button\" class=\"btn btn-default\"  data-dismiss=\"modal\">关 闭</button>",
        "        <button type=\"button\" class=\"btn btn-primary submit_order\" id=\"order_submitBtn\" onclick=\"NewOrderDo.submitOrder()\">提 交</button>",
        "      	 </div>",
        "      	</div>",
        "      </div>",
        "    </div><!-- /.modal-content -->",
        "  </div><!-- /.modal-dialog -->",
        "</div><!-- /.modal -->"].join(""),
        bindDelieverChange:function(){
        	$("#order_delieverType",parent.document).bind("change",function(){
        		 var type = $("#order_delieverType",parent.document).val();
        	        if (type == "2") {
        	            $("#row_orderAddress",parent.document).show();
        	        } else {
        	            $("#row_orderAddress",parent.document).hide();
        	        }
        	});
        },
        //绑定数量增减按钮
        bindNumberAddSub:function(){
        	$(".add_number",parent.document).unbind("click");
        	$(".add_number",parent.document).bind("click",function(){
        		alert(111);
        		NewOrderDo.alterProductTypeNumber(1)
        	});
        	$(".sub_number",parent.document).unbind("click");
        	$(".sub_number",parent.document).bind("click",function(){
        		NewOrderDo.alterProductTypeNumber(-1)
        	});
        },
        //绑定订单提交按钮
        bindOrderSubmit:function(){
        	$(".submit_order",parent.document).bind("click",function(){
        		NewOrderDo.submitOrder();
        	});
        },
        bindOrderAddType:function(){
        	$("#add2order_type",parent.document).bind("click",function(){
        		 var buyerId = $("#buyerId").val();
        	        var number = $("#productType_number",parent.document).val();
        	        var productType = $("#selected_typeId",parent.document).val();
        	        var price = $("#selected_price",parent.document).val();
        	        var productId = $("#selected_productId",parent.document).val();

        	        if (buyerId == null || buyerId == "") {
        	            $("#appendOrderMsg",parent.document).html("提示：请选择要下订单的客户");
        	            return;
        	        }

        	        if (productType == null || productType == "") {
        	            $("#appendOrderMsg",parent.document).html("提示：请选择商品型号");
        	            return;
        	        }


        	        $.getJSON(
        	            toServerUrl("/bm/order/appendSellerNewOrderItem.do"),
        	            {productId: productId, number: number, productType: productType, price: price, buyerId: buyerId},
        	            function (json) {
        	                NewOrderDo.showOrderItems(json);
        	                $('#productType',parent.document).modal('hide');
        	            }
        	        );
        	});
        },
    //加载商铺下商品
    getProducts: function () {
        $.getJSON(
            toServerUrl("/bm/product/selectByShopId.do"),
            {status: "T", length: 100},
            function (json) {
                var products = json.data.list;
                var rows = "";
                for (var i = 0; i < products.length; i++) {
                    var product = products[i];

                    rows += [
                        '				<div class="row" style="margin:0px;border-bottom:1px dotted #f3f4fe">',
                        '					<div class="col-md-3"  style="">',
                        '						<img src="'+NewOrderDo.bathpath+product.picUrl+' " style="height:100px;width:100px" class="img-responsive">',
                        '					</div>',
                        '					<div class="col-md-9"  style="" data_productId="'+product.id+'">',
                        '						<h4 class="productName">' + product.name + '</h4>',
                        '						<h5 class="price">￥' + product.price + '</h5>',
                        '						<button  class="btn btn-default zs-btn-default add_order" >加入订单</button>',
                        '					</div>',
                        '				</div>'].join("");

                }
                $("#productList").empty();
                $("#productList").html(rows);
                NewOrderDo.bindShowProductType();
            }
        );
    },
    bindShowProductType:function(){
    	$(".add_order").unbind("click");
    	$(".add_order").bind("click",function(){
    		var _this = $(this);
            var id = _this.parent().attr("data_productId");
            if ($("#buyerId").val() == "") {
                //$("#productMsg").html("提示：请先选择要下单的客户");
                showDialog("请先选择要下单的客户", "toast", "", "");
                return;
            }
            if ($("#productType", parent.document).length == 1) {

            } else {
                $("body", parent.document).append(NewOrderDo.productType);

            }
            $.getJSON
            (toServerUrl("/bm/product/queryById.do"),
                {productId: id},
                function (json) {

                    $("#selected_productId",parent.document).val(id);
                    $("#productType_img",parent.document).attr("src", NewOrderDo.bathpath+json.product.picUrl );
                    $("#productType_pname",parent.document).html(json.product.name);
                    $("#productType_price",parent.document).html(json.product.price);
                    $("#productType_group",parent.document).empty();
                    for (var i = 0; i < json.types.length; i++) {
                        var type = json.types[i];

                        var btnClass = "btn-default";
                        if (i == 0) {
                            btnClass = "btn-primary";
                            $("#productType_price",parent.document).html(type.price);
                            $("#selected_typeId",parent.document).val(type.id);
                            $("#selected_price",parent.document).val(type.price);
                            $("#selected_weight",parent.document).val(type.weight);
                        }
                        $("#productType_group",parent.document).append(
                            "<button type=\"button\" class=\"selectType btn " + btnClass + "\""
                            + " typeId=\"" + type.id + "\" price=\""
                            + type.price + "\" weight=\"" + type.weight + "\">" + type.name + "</button>"
                        );
                    }
                    NewOrderDo.bindSelectType();
                    NewOrderDo.bindNumberAddSub();
                    NewOrderDo.bindOrderAddType();
                }
            );
            $("#productType_number",parent.document).val(1);
            $("#productType", parent.document).modal('show');
    	});
    },
    bindSelectType:function(){
    	$(".selectType",parent.document).unbind("click");
        $(".selectType",parent.document).bind("click",function(){
            var _this = $(this);
            $("#productType_group button",parent.document).each(function (i, item) {
                $(item).removeClass("btn-primary");
                $(item).addClass("btn-default");
            });
            $(_this).addClass("btn-primary");
            $("#selected_typeId",parent.document).val(_this.attr("typeId"));
            $("#selected_price",parent.document).val(_this.attr("price"));
            $("#selected_weight",parent.document).val(_this.attr("weight"));
            $("#productType_price",parent.document).html(_this.attr("price"));
        });
    },
    init: function () {
        NewOrderDo.getProducts();
    },
    //显示商品型号选择框
    showProductType: function (productId) {
        if ($("#buyerId").val() == "") {
            //$("#productMsg").html("提示：请先选择要下单的客户");
            showDialog("请先选择要下单的客户", "toast", "", "");
            return;
        }

        if ($("#productType", parent.document).length == 1) {
            $("#productType", parent.document).modal('show');
        } else {
            $("body", parent.document).append(NewOrderDo.productType);
            $("#productType", parent.document).modal('show');
        }
        $("#productMsg").empty();
        $.getJSON
        (toServerUrl("/bm/product/queryById.do"),
            {productId: productId},
            function (json) {

                $("#selected_productId").val(productId);
                $("#productType_img").attr("src",NewOrderDo.bathpath+json.product.picUrl);
                $("#productType_pname").html(json.product.name);
                $("#productType_price").html(json.product.price);
                $("#productType_group").empty();
                for (var i = 0; i < json.types.length; i++) {
                    var type = json.types[i];

                    var btnClass = "btn-default";
                    if (i == 0) {
                        btnClass = "btn-primary";
                        $("#productType_price").html(type.price);
                        $("#selected_typeId").val(type.id);
                        $("#selected_price").val(type.price);
                        $("#selected_weight").val(type.weight);
                    }

                    $("#productType_group").append(
                        "<button type=\"button\" class=\"btn " + btnClass + "\""
                        + "onclick='selectType(this)'"
                        + " typeId=\"" + type.id + "\" price=\""
                        + type.price + "\" weight=\"" + type.weight + "\">" + type.name + "</button>"
                    );
                }
                alert(11);
               NewOrderDo.bindNumberAddSub();
            }
        );
        $("#productType_number",parent.document).val(1);
    },


    //选择商品型号
    selectType: function (btn) {

        $("#productType_group button").each(function (i, item) {
            $(item).removeClass("btn-primary");
            $(item).addClass("btn-default");
        });

        $(btn).addClass("btn-primary");
        $("#selected_typeId").val($(btn).attr("typeId"));
        $("#selected_price").val($(btn).attr("price"));
        $("#selected_weight").val($(btn).attr("weight"));
        $("#productType_price").html($(btn).attr("price"));
    },
    //修改商品数量
    alterProductTypeNumber: function (number) {
    	alert(111);
        var curVal = Number($("#productType_number",parent.document).val());
        curVal += number;
        if (curVal < 1) {
            curVal = 1;
        }
        $("#productType_number",parent.document).val(curVal);

    },
    //加入订单
    appendToOrder: function (btn) {
       
    },
    //根据返回值显示订单
    showOrderItems: function (json) {
        $("#orderItems").empty();
        var rows = "";
        for (var i = 0; i < json.data.items.length; i++) {
            var item = json.data.items[i];
            rows += ["<div class=\"row\" style=\"margin:0px;border-bottom:1px dotted #f3f4fe\">",
                "		<div class=\"col-md-3\"  style=\"\">",
                "		<img src=\"" +NewOrderDo.bathpath+item.picUrl +  "\" class=\"img-responsive\" style=\"height:100px;width:100px\">",
                "		</div>",
                "		<div class=\"col-md-9\"  style=\"\">",
                "		<h4 class=\"productName\">" + item.productName + "</h4>",
                "		<h5 class=\"detail\"><span>型号：" + item.productTypeName + "</span><span>单价：￥" + item.price + "</span><span>数量：" + item.number + "</span></h5>",
                "		<a class=\"zs-table-delete \" onclick=\"NewOrderDo.removeOrderItem('" + item.productId + "','" + item.productType + "')\">删除</a>",
                "		</div>",
                "		</div>"].join("");

        }
        $("#orderTotal").html("￥" + json.data.total);

        $("#orderItems").html(rows);
    }
    ,
    //删除订单中商品
    removeOrderItem: function (productId, productType) {
        $.getJSON(
            toServerUrl("/bm/order/removeSellerNewOrderItem.do"),
            {productId: productId, productType: productType, buyerId: $("#buyerId").val()},
            function (json) {
                NewOrderDo.showOrderItems(json);
            }
        );
    },
    //提交新建订单
    showOrder: function () {
        $("#order_msg").empty();
        $("#order_submitBtn").removeAttr("disabled");
        var buyerId = $("#buyerId").val();
        if (buyerId == null || buyerId == "") {
            showDialog("尚未选择下单客户","toast","","");
            return;
        }

        if ($("#orderSumitModal", parent.document).length == 1) {
            $("#orderSumitModal", parent.document).modal('show');
        } else {
            $("body", parent.document).append(NewOrderDo.orderSumitModal);
            $("#orderSumitModal", parent.document).modal('show');
        }

        $.getJSON(
            toServerUrl("/bm/order/querySellerNewOrder.do"),
            {buyerId: buyerId},
            function (json) {
                var rows = "";
                console.log(json);
                console.log(json.data.userName);
                for (var i = 0; i < json.data.items.length; i++) {
                    var item = json.data.items[i];

                    var sum = powAmount(parseFloat(item.price) * Number(item.number), 2);

                    rows += "<tr>"
                        + "	<td>" + item.productName + "</td>"
                        + "	<td>" + item.productTypeName + "</td>"
                        + "	<td style=\"text-align:right\">" + powAmount(item.price, 2) + "</td> "
                        + "	<td style=\"text-align:right\">" + item.number + "</td> "
                        + "	<td style=\"text-align:right\">" + sum + "</td> "
                        + "</tr>";

                }
                $("#order_itemSize",parent.document).val(json.data.items.length);
                $("#order_custName",parent.document).val(json.data.userName);
                $("#order_total",parent.document).html(powAmount(json.data.total, 2));
                $("#order_realTotal",parent.document).val(powAmount(json.data.total, 2));
                $("#order_items",parent.document).html(rows);
                //$("#orderTotal").html("￥"+json.data.total);
                NewOrderDo.bindOrderSubmit();
                NewOrderDo.bindDelieverChange();
            }
        );
        /*

         */
    },
    //提交订单
    submitOrder: function () {
        var order_receiver = $("#order_receiver",parent.document).val();
        var order_receiverPhone = $("#order_receiverPhone",parent.document).val();
        var order_addressTxt = $("#order_addressTxt",parent.document).val();
        var order_remark = $("#order_remark",parent.document).val();
        var order_delieverType = $("#order_delieverType",parent.document).val();

        var order_realTotal = $("#order_realTotal",parent.document).val();
        var order_total = $("#order_total",parent.document).html();

        var order_itemSize = $("#order_itemSize",parent.document).val();

        if (order_itemSize < 1) {
            $("#order_msg",parent.document).html("该订单下没有商品，无法提交");
            return;
        }

        if (!isNumber(order_realTotal) || order_realTotal < 0) {
            $("#order_msg",parent.document).html("请确认订单付款价格");
            return;
        }

        if (order_total != order_realTotal) {
            if (!window.confirm("订单付款价格发生变动是否提交？")) {
                return;
            }
        }

        $("#order_submitBtn").attr("disabled", "disabled");


        $.getJSON(
            toServerUrl("/bm/order/submitSellerNewOrder.do"),
            {
                buyerId: $("#buyerId").val()
                , addressTxt: order_addressTxt
                , deliverType: order_delieverType
                , realTotal: order_realTotal
                , receiver: order_receiver
                , receiverPhone: order_receiverPhone
                , remark: order_remark
            },
            function (json) {
                window.alert("订单提交成功，订单编号：[" + json.orderId + "]");
                window.location.href = toServerUrl("/bm/order/sellerOrderList") + "&orderId=" + json.orderId;
            }
        );
    },
    //按手机号查询客户
    queryBuyer: function () {
        var buyerPhone = $("#buyerPhone",parent.document).val();
        $.getJSON(
            toServerUrl("/bm/order/queryUserByMobile.do"),
            {mobile: $("#buyerPhone",parent.document).val()},
            function (json) {
                var user = json.data;
                $("#custList",parent.document).empty();
                $("#custList",parent.document).html(
                    "	<a data_id=\""+user.id+"\" data_name =\""+user.name+"\" class=\"list-group-item cust_item\">"
                    + "	<img height=\"50px\" width=\"50px\" src=\""+NewOrderDo.bathpath+user.picUr + "\">"
                    + "	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                    + "	<label>" + user.name + "</label>"
                    + "	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                    + "	<label>" + user.mobile + "</label></a>"
                );
                NewOrderDo.bindCustSelect();
            }
        );
        
    },
    bindCustSelect:function(){
    	$(".cust_item",parent.document).unbind("click");
        $(".cust_item",parent.document).bind("click",function(){
        	alert("11");
        	var _this = $(this);
            $('#custSelectModal', parent.document).modal('hide');
            $("#userName").val(_this.attr("data_name"));
            $("#buyerId").val(_this.attr("data_id"));
            $("#productMsg").empty();
            $.getJSON(
                toServerUrl("/bm/order/querySellerNewOrder.do"),
                {buyerId: _this.attr("data_id")},
                function (json) {
                   NewOrderDo.showOrderItems(json);
                }
            );
        });

    },
    storedCustList: "",
    //显示客户选择框
    showCustSelect: function () {
        if ($("#custSelectModal", parent.document).length == 1) {
            $("#custSelectModal", parent.document).modal('show');
        } else {
            $("body", parent.document).append(NewOrderDo.custSelectModal);
            $("#custSelectModal", parent.document).modal('show');
        }
        //$("#custList").empty();
        if (NewOrderDo.storedCustList == "") {
        	sendRequest("", "/bm/stored/queryStoredCust.do",  function (json) {
                    for (var i = 0; i < json.custList.length; i++) {
                        var user = json.custList[i];
                        NewOrderDo.storedCustList +=
                        	 "	<a data_id=\""+user.user_id+"\" data_name =\""+user.name+"\" class=\"list-group-item cust_item\">"
                            + "	<img height=\"50px\" width=\"50px\" src=\" "+NewOrderDo.bathpath+user.picUrl+"\">"
                            + "	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                            + "	<label>" + user.name + "</label>"
                            + "	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                            + "	<label>" + user.phone + "</label></a>";
                    }
                    $("#custList", parent.document).html(NewOrderDo.storedCustList);
                    NewOrderDo.bindCustSelect();
                });
/*            $.getJSON(
                toServerUrl("/bm/stored/queryStoredCust.do"),
                function (json) {
                    for (var i = 0; i < json.custList.length; i++) {
                        var user = json.custList[i];
                        NewOrderDo.storedCustList +=
                        	 "	<a data_id=\""+user.id+"\" data_name =\""+user.name+"\" class=\"list-group-item cust_item\">"
                            + "	<img height=\"50px\" width=\"50px\" src=\" "+toImgUrl(user.picUrl)+"\">"
                            + "	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                            + "	<label>" + user.name + "</label>"
                            + "	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                            + "	<label>" + user.phone + "</label></a>";
                    }
                    $("#custList", parent.document).html(NewOrderDo.storedCustList);
                }
            );*/
        } else {
            $("#custList", parent.document).html(NewOrderDo.storedCustList);
            NewOrderDo.bindCustSelect();
        }
       
        NewOrderDo.bindCustSearch();
        
        //$('#custSelectModal').modal('show');
    },
    bindCustSearch:function(){
    	$(".search_btn",parent.document).bind("click",function(){
    		alert("1");
    		NewOrderDo.queryBuyer();
    	})
    },
    //下单客户选择
    selectCust: function (custId, custName) {
        $('#custSelectModal', parent.document).modal('hide');
        $("#userName").val(custName);
        $("#buyerId").val(custId);
        $("#productMsg").empty();

        $.getJSON(
            toServerUrl("/bm/order/querySellerNewOrder.do"),
            {buyerId: custId},
            function (json) {
                showOrderItems(json);
            }
        );

    },
}