$(document).ready(function(){
	
		selectProvince11(); 			  			
});
function getProvinceName(code){
	var region = getRegionData();
	var result ;
	$(region.provinces).each(function(i,ite){
		if(ite.c == code){
			result = ite.n;
		}
	});
	return result;
}
function getCityName(code){
	var region = getRegionData();
	var result ;
	$(region.cities).each(function(i,ite){
		$(ite.city).each(function(j,item){
			if(item.c == code){
				result = item.n;
			}
		});
	});
	return result;
}
function getCountryName(code){
	var region = getRegionData();
	var result ;
	$(region.counties).each(function(i,ite){
		$(ite.county).each(function(j,item){
			if(item.c == code){
				result = item.n;
			}
		});
	});
	return result;
}

function selectProvince11(){  	
	
	
	var p = getRegionData().provinces;
	
	$("#provinceSearch").append("<option value='0'>选择省份</option>");
	for(var i = 0; i < p.length; i ++){
		$("#provinceSearch").append("<option value='"+p[i].c+"'>"+p[i].n+"</option>");						
	}
	}

function searchConditionCity(){
	$("#citySearch").empty();
	$("#countySearch").empty();
	var proviceCode = $("#provinceSearch").find("option:selected").val();	
	
	if(proviceCode != '0'){
		
		var cities = getRegionData().cities;
		
		for(var i = 0; i < cities.length; i ++){
			if(cities[i].province == proviceCode){
				var c = cities[i].city;
				$("#citySearch").append("<option value='0'>选择城市</option>");
				for(var j = 0; j < c.length; j ++){
					$("#citySearch").append("<option value='"+c[j].c+"'>"+c[j].n+"</option>");
				}
			}
		}
	
	}

	// 默认城市
	if(null != defaultCity  && "" != defaultCity){
		$("#citySearch").val(defaultCity);
		searchConditionCounty();
	}	

	
}

function searchConditionCounty(){
	$("#countySearch").empty();
	var cityCode = $("#citySearch").find("option:selected").val();
	if(cityCode != '0'){
		
		var counties = getRegionData().counties;
		for(var i = 0; i < counties.length; i ++){
			if(counties[i].city == cityCode){
				var c = counties[i].county;
				$("#countySearch").append("<option value='0'>选择县区</option>");
				
				for(var j = 0; j < c.length; j ++){
					$("#countySearch").append("<option value='"+c[j].c+"'>"+c[j].n+"</option>");
				}
			}
		}
		
		
	}

	// 默认区县
	if(null != defaultCounty && "" != defaultCounty){		
		$("#countySearch").val(defaultCounty);
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
var defaultCounty = "";

/* 设置默认值 */
function setDefaultSelect(provinceSearch, citySearch, countySearch){
	defaultProvince = provinceSearch;
	defaultCity = citySearch;
	defaultCounty = countySearch;

	// 默认省份
	$("#provinceSearch").val(provinceSearch);
	searchConditionCity();
}
function getRootPath(){
    var curWwwPath=window.document.location.href;
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    var localhostPaht=curWwwPath.substring(0,pos);
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}