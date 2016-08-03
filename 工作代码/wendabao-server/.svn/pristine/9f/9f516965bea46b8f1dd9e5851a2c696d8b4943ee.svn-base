//$(document).ready(function(){
//		selectProvince();
//});

function selectProvinceWithIndex(index){
	var p = getRegionData().provinces;
	$("#province"+index).append("<option value=''>== 请选择 ==</option>");
	$("#province"+index).append("<option value='000000'>全国</option>");
	for(var i = 0; i < p.length; i ++){
		$("#province"+index).append("<option value='"+p[i].c+"'>"+p[i].n+"</option>");
	}
	}

function searchCity(index){
	$("#city"+index).empty();
	var proviceCode = $("#province"+index).find("option:selected").val();
	if(proviceCode != '000000'){
		
		var cities = getRegionData().cities;
		for(var i = 0; i < cities.length; i ++){
			if(cities[i].province == proviceCode){
				var c = cities[i].city;
				$("#city"+index).append("<option value=''>== 请选择 ==</option>");
				$("#city"+index).append("<option value='999999'>全部区域</option>");
				for(var j = 0; j < c.length; j ++){
					$("#city"+index).append("<option value='"+c[j].c+"'>"+c[j].n+"</option>");
				}
			}
		}

	}

	// 默认城市
	if(null != defaultCity  && "" != defaultCity){
		$("#city"+index).val(defaultCity);
		searchCounty();
	}	
}

var region = null;

function getRegionData(){
	
	if(region == null){
		$.ajax({
			url:getRootPath()+"/js/region.json",
			type:"post",
			dataType:"json",
			async: false,
			success:function(data){  
				region = data.region;			
			}
		}); 
	}
	return region;
}


var defaultProvince = "";
var defaultCity = "";

/* 设置默认值 */
function setDefaultSelect(index, province, city, county){
	defaultProvince = province;
	defaultCity = city;

	// 默认省份
	$("#province"+index).val(province);
	searchCity(index);
}
function getRootPath(){
    var curWwwPath=window.document.location.href;
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    var localhostPaht=curWwwPath.substring(0,pos);
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}