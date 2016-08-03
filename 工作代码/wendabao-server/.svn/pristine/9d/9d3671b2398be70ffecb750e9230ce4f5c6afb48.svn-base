var OrderDo = {
    status: "",
    orderId: "",
    //2物流 1自取
    method:"2",
    region1code:"",
	region2code:"",
	region3code :"",
    isInEdit: false,
    init: function () {
    	if(OrderDo.method == "1"){
    		$(".method_log").hide();
    		$("#operate_btn").hide();
    		$("#self").attr("checked","checked");

    	}else{
    		$(".method_log").show();
    		$("#operate_btn").show();
    		$("#log").attr("checked","checked");
    	}
        if (status == "1") {
            //待付款
            $(".time_submit").show();
            $(".time_pay").css("display", "none");
            $(".time_send").css("display", "none");
            $(".time_receive").css("display", "none");
            $(".appeal").css("display", "none");
            $(".appeal_txt").css("display", "none");
            $(".red_txt").html("待付款");
            $("input[name=method]").attr("disabled","disabled");
            OrderDo.bindUpdatePrice();
            OrderDo.notEditLogistics();
        } else if (status == "2") {
            //待发货
            $(".time_submit").show();
            $(".time_pay").show();
            $(".time_send").css("display", "none");
            $(".time_receive").css("display", "none");
            $(".appeal").css("display", "none");
            $(".appeal_txt").css("display", "none");
            $(".red_txt").html("待发货");
            OrderDo.getLog();
            OrderDo.notEditLogistics();
            OrderDo.bindMethodSelect();
        } else if (status == "3") {
            //待收货
            $(".time_submit").show();
            $(".time_pay").show();
            $(".time_send").show();
            $(".time_receive").css("display", "none");
            $(".appeal").css("display", "none");
            $(".appeal_txt").css("display", "none");
            $(".red_txt").html("待收货");
            OrderDo.notEditLogistics();
            $("input[name=method]").attr("disabled","disabled");
        } else if (status == "4") {
            //已收货
            $(".time_submit").show();
            $(".time_pay").show();
            $(".time_send").show();
            $(".time_receive").show();
            $(".appeal").css("display", "none");
            $(".appeal_txt").css("display", "none");
            $(".red_txt").html("完成");
            OrderDo.notEditLogistics();
            $("input[name=method]").attr("disabled","disabled");
        }else if(status == "6"){
        	  $(".time_submit").show();
              $(".time_pay").show();
              $(".time_send").show();
              $(".time_receive").show();
              $(".time_appeal").show();
              $(".time_appeal_commit").css("display", "none");
              $(".reason").show();
              $(".result").css("display", "none");
              $(".red_txt").html("申诉中");
              OrderDo.notEditLogistics();
              $("input[name=method]").attr("disabled","disabled");
        }else if(status == "7"){
        	 $(".time_submit").show();
             $(".time_pay").show();
             $(".time_send").show();
             $(".time_receive").show();
             $(".time_appeal").show();
             $(".time_appeal_commit").show();
             $(".reason").show();
             $(".result").show();
             $(".red_txt").html("申诉处理完成");
             OrderDo.notEditLogistics();
             $("input[name=method]").attr("disabled","disabled");
        }
        
    },
    bindMethodSelect: function () {
        $("input[name=method]").bind("click", function () {
            switch ($("input[name=method]:checked").val()) {
                case "2":
                    $(".logistics_wrap").find(".method_log").show();
                    $("#operate_btn").show();
                    break;
                case "1":
                    $(".logistics_wrap").find(".method_log").hide();
                    $("#operate_btn").hide();
                    break;
                default :
                    break;
            }
        });
    }
    ,
    cancel:function(){
    	 OrderDo.notEditLogistics();
    	 $("#operate_btn").show();
    },
    
    bindUpdatePrice: function () {
        $("#price_update").bind("click", function () {
            showDialog("新价格", "input", {

                onOk: function (newNumber) {
                    if (!isNaN(newNumber)) {
                        var param = {
                            "orderId": OrderDo.orderId,
                            "realTotal": newNumber,
                        }
                        sendRequest(param, "/bm/order/updateOrder.do", function (data) {
                            if ($(".two_price").length == 0) {
                                $(".one_price").addClass("old_price");
                                $(".one_price").parent().append("<span class=\"two_price\">" + newNumber + "</span>");
                            } else {
                                $(".two_price").html(newNumber)
                            }
                        });
                    } else {
                        showDialog("价格只能是数字", "toast", "", "");
                    }

                }
            }, $(".one_price").html());
        });
    },
    //获取物流信息
    getLog: function () {
        /* 修改input的背景色   */
        $("input").css("background-color", "#FFF");
        var dp = $("#deliverProvider1").val();
        sendRequest("", "/bm/delieverProvider/queryByShopId.do", function (data) {
            if (data.header.success == true) {
                var obj = data.data;
                if (obj != null
                    && obj.length != 0) {
                    for (var i = 0; i < obj.length; i++) {
                        $("#selectDeliever")
                            .append("<option data_txt='" + obj[i].companyName + "'  value='" + obj[i].id + "'" + (obj[i].id == dp ? 'selected' : '') + ">"
                            + obj[i].companyName + "</option>");
                    }
                }
            }
        });
    },
    //可编辑物流
    editLogistics: function () {
        OrderDo.isInEdit = true;

        var number = $("#log_number").find("span").html();
        var receiver = $("#log_receiver").find("span").html();
        var phone = $("#log_phone").find("span").html();
        var address = $("#log_address").find("span").html();

        $("#log_company").find("span").hide();
        $("#log_number").find("span").hide();
        $("#log_receiver").find("span").hide();
        $("#log_phone").find("span").hide();
        $("#log_address").find(".region_show").hide();

        $("#log_company").find("select").show();
        $("#log_number").find("input").show();
        $("#log_receiver").find("input").show();
        $("#log_phone").find("input").show();
        $("#log_address").find(".region_edit").show();
        var province_code = $("#province_show").attr("code");
        var city_code = $("#city_show").attr("code");
        var country_code = $("#country_show").attr("code");
        var address_show = $("#address_show").html();
        if (province_code && city_code && country_code && address_show) {
            setDefaultSelect(province_code, city_code, country_code);
            $("#address_edit").val(address_show);
        }
        if (number && number != "暂无") {
            $("#log_number").find("input").val(number);
        }
        if (receiver && receiver != "暂无") {
            $("#log_receiver").find("input").val(receiver);
        }
        if (phone && phone != "暂无") {
            $("#log_phone").find("input").val(phone);
        }
        $("#operate_btn").hide();
    },
    //不可编辑物流
    notEditLogistics: function () {

        $("#log_company").find("span").show();
        $("#log_number").find("span").show();
        $("#log_receiver").find("span").show();
        $("#log_phone").find("span").show();
        $("#log_address").find(".region_show").show();

        $("#log_company").find("select").hide();
        $("#log_number").find("input").hide();
        $("#log_receiver").find("input").hide();
        $("#log_phone").find("input").hide();
        $("#log_address").find(".region_edit").hide();
    },
    //保存物流信息
    saveLog: function () {
        OrderDo.isInEdit = false;
       
        //获取编辑的物流信息
        //物流公司Id
        var company_id = $("#log_company").find("select").val();
        //物流公司名称
        var companyName = $("#log_company").find("select").find("option:selected").text();
        //物流编号
        var number = $("#log_number").find("input").val();
        //收货人
        var receiver = $("#log_receiver").find("input").val();
        //联系电话
        var phone = $("#log_phone").find("input").val();
        //region1
        var pro_edit_code = $("#provinceSearch").val();
        //region2
        var city_edit_code = $("#citySearch").val();
        //region3
        var country_edit_code = $("#countySearch").val();
        //详细地址
        var address_edit = $("#address_edit").val();
        //编辑地址 地址name
        var pro_edit_name = $("#provinceSearch").find("option:selected").text();
        var city_edit_name = $("#citySearch").find("option:selected").text();
        var country_edit_name = $("#countySearch").find("option:selected").text();
        if(!company_id){
        	showDialog("请选择物流","toast","","");
            return;
        }
//        if(!number){
//        	showDialog("请填写物流编号","toast","","");
//            return;
//        }
        if (!(pro_edit_code && pro_edit_code != 0 &&address_edit &&city_edit_code && city_edit_code != 0 && country_edit_code && country_edit_code != 0)) {
           showDialog("请填写完整地址","toast","","");
           return;
        }
        if (phone) {
        	if(!checkDataReg(REG_TEL,phone)){
        		showDialog("请输入正确的手机格式", "toast", "", "");
        		return;
        	}
            $("#log_phone").find("span").html(phone);
        }
        $("#province_show").attr("code", pro_edit_code);
        $("#city_show").attr("code", city_edit_code);
        $("#country_show").attr("code", country_edit_code);
        if (pro_edit_name && city_edit_name && country_edit_name) {
            $("#province_show").html(pro_edit_name);
            $("#city_show").html(city_edit_name);
            $("#country_show").html(country_edit_name);
        }
        if (address_edit) {
            $("#address_show").html(address_edit);
        }
        //保存展示
        if (company_id && companyName) {
            $("#log_company").find("span").html(companyName);
            $("#log_company").find("span").attr("data_id",company_id);
        }
        if (number) {
        	$("#log_number").find("span").html(number);
            $("#log_number").find("span").attr("data_number",number);
        }
        if (receiver) {
            $("#log_receiver").find("span").html(receiver);
        }
        OrderDo.notEditLogistics();
        $("#operate_btn").show();
    },
    send: function () {
        if (OrderDo.isInEdit) {
            showDialog("请先保存物流信息", "toast", "", "");
            return;
        }
        var deliverType = $("input[name=method]:checked").val();
        plog("发货方式："+deliverType);
        var  orderId = OrderDo.orderId;
        var param = "";
        if(deliverType == "1"){
        	//自取
        	param= {
        			"orderId":orderId,
        			"deliverType":deliverType
        	}
        }else if(deliverType == "2"){
        	 var company_id = $("#log_company").find("span").attr("data_id");
              var companyName = $("#log_company").find("span").html();
              var number = $("#log_number").find("span").attr("data_number");
              var receiver = $("#log_receiver").find("span").html();
              var phone = $("#log_phone").find("span").html();
              var region1Code = $("#province_show").attr("code");
              var region2Code = $("#city_show").attr("code");
              var region3Code = $("#country_show").attr("code");
              var addressTxt = $("#address_show").html();
//              if(!company_id){
//            	  showDialog("请选择物流公司", "toast", "", "");
//            	  return;
//              }
//              if(!number){
//            	  showDialog("请输入物流单号", "toast", "", "");
//            	  return;
//              }
              if(!receiver){
            	  showDialog("请输入收货人", "toast", "", "");
            	  return;
              }
              if(!(region1Code&&region2Code&&region3Code&&addressTxt)){
            	  showDialog("请输入完整的物流地址", "toast", "", "");
            	  return;
              }
        	//物流
        	param= {
        			"orderId":orderId,
        			"deliverType":deliverType,
        			"addressTxt":addressTxt,
        			"deliverNumber":number,
        			"deliverProvider":company_id,
        			"region1":region1Code,
        			"region2":region2Code,
        			"region3":region3Code,
        			"receiverPhone":phone,
        			"receiver":receiver,
        	}
        }
        new MallDialog("confirm", "确定发货吗？", {
        	onOk:function(){
        		sendRequest(param, "/bm/order/send.do", function(){
    	        	window.location=toServerPageUrl("/bm/order/querySellerOrderDetail.do?orderId=" + orderId);
    	        });
        	},
        	  onCancel: function () {
                  // 当点击取消按钮时的处理
              }
        });
       

//        $("#province_show").attr("code", pro_edit_code);
//        $("#city_show").attr("code", city_edit_code);
//        $("#contry_show").attr("code", country_edit_code);
       // sendRequest("")
    }
}

