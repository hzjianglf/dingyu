/**
 * <select id='province' onchange='search(this)'></select>
 * <select id='city' style="margin-top: 10px" onchange='search(this)'></select>
 * <select id='district' style="margin-top: 10px" onchange='search(this)'></select>
 *
 * 取值：var province = $("#province").val().split('|')[1];// 省
 *     var city =  $("#city").val().split('|')[1];// 市
 *     var district =  $("#district").val().split('|')[1]; // 区县
 */
var mapObj, district, polygons = [], citycode;
var citySelect = document.getElementById('city');
var districtSelect = document.getElementById('district');
var areaSelect = document.getElementById('biz_area');

var defaultProvince = "";
var defaultCity = "";
var defaultDistrict = "";

mapObj = new AMap.Map('mapContainer', {
    resizeEnable: true,
    center: [116.30946, 39.937629],
    zoom: 3
});

var provinceList = ['北京市', '天津市', '河北省', '山西省', '内蒙古自治区', '辽宁省', '吉林省', '黑龙江省', '上海市', '江苏省', '浙江省', '安徽省', '福建省', '江西省', '山东省', '河南省', '湖北省', '湖南省', '广东省', '广西壮族自治区', '海南省', '重庆市', '四川省', '贵州省', '云南省', '西藏自治区', '陕西省', '甘肃省', '青海省', '宁夏回族自治区', '新疆维吾尔自治区', '台灣', '香港特别行政区', '澳门特别行政区'];
var provinceCodeList = ['110000', '120000', '130000', '140000', '150000', '210000', '220000', '230000', '310000', '320000', '330000', '340000', '350000', '360000', '370000', '410000', '420000', '430000', '440000', '450000', '460000', '500000', '510000', '520000', '530000', '540000', '610000', '620000', '630000', '640000', '650000', '710000', '810000', '820000'];
var provinceSelect = document.getElementById('province');
provinceSelect.add(new Option('--请选择--'));
for (var i = 0, l = provinceList.length,option; i < l; i++) {
    option=new Option(provinceList[i]);
    option.setAttribute("value","province|"+provinceCodeList[i]);
    provinceSelect.add(option);
}

//行政区划查询
var opts = {
    subdistrict: 1,   //返回下一级行政区
    extensions: 'all',  //返回行政区边界坐标组等具体信息
    level: 'city'  //查询行政级别为 市
};
//实例化DistrictSearch
district = new AMap.DistrictSearch(opts);//注意：需要使用插件同步下发功能才能这样直接使用


function getData(e) {
    var dList = e.districtList;
    for (var m = 0, ml = dList.length; m < ml; m++) {
        var data = e.districtList[m].level;
        var bounds = e.districtList[m].boundaries;
        //只绘制 区, 且 本级别行政区划是上一级区划的下级行政区
        if (data == "district" && dList[m].citycode === citycode) {
            if (bounds) {
                for (var i = 0, l = bounds.length; i < l; i++) {
                    //生成行政区划polygon
                    var polygon = new AMap.Polygon({
                        map: mapObj,
                        strokeWeight: 1,
                        strokeColor: '#CC66CC',
                        fillColor: '#CCF3FF',
                        fillOpacity: 0.7,
                        path: bounds[i]
                    });
                    polygons.push(polygon);
                }
                mapObj.setFitView();//地图自适应
            }
        }

        var list = e.districtList || [],
            subList = [], level, nextLevel;
        if (list.length >= 1) {
            subList = list[0].districtList;
            level = list[0].level;
        }

        //清空下一级别的下拉列表
        if (level === 'province') {
            nextLevel = 'city';
            citySelect.innerHTML = '';
            districtSelect.innerHTML = '';
            //areaSelect.innerHTML = '';
        } else if (level === 'city') {
            nextLevel = 'district';
            districtSelect.innerHTML = '';
            //areaSelect.innerHTML = '';
        } else if (level === 'district') {
            nextLevel = 'biz_area';
            //areaSelect.innerHTML = '';
        }

        if (subList) {
            var contentSub =new Option('--请选择--');
            for (var i = 0, l = subList.length; i < l; i++) {
                var name = subList[i].name;
                var levelSub = subList[i].level;
                var cityCode = subList[i].adcode;
                if(i==0){
                    document.querySelector('#' + levelSub).add(contentSub);
                }
                contentSub=new Option(name);
                contentSub.setAttribute("value", levelSub + '|' + cityCode);
                document.querySelector('#' + levelSub).add(contentSub);
            }

            // 设置默认值
            if (level === 'province' && !isNull(defaultCity)){
                $("#city").val(defaultCity);
                $("#city").change();
            } else if (level === 'city' && !isNull(defaultDistrict)) {
                $("#district").val(defaultDistrict);
            }
        }
    }
}

function search(obj) {
    var option = obj[obj.options.selectedIndex];
    var arrTemp = option.value.split('|');
    var level = arrTemp[0];//行政级别
    citycode = arrTemp[1];// 城市编码
    var keyword = option.text; //关键字
    district.setLevel(level); //行政区级别
    //行政区查询
    if("district" != level){
        console.log(keyword);

        district.search(keyword, function(status, result) {
            console.log(result);
            getData(result);
        });
    }

}

/* 设置默认值 */
function setDefaultSelect(province, city, district){
    defaultProvince = province;
    defaultCity = city;
    defaultDistrict = district;
    $("#province").val(province);
    $("#province").change();
}