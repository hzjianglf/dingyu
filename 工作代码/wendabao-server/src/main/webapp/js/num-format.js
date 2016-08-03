/**
 * Created by Administrator on 2015/11/14.
 */
//在引用页面，可以采用document.forms[0].field1.value.trim()引用去空格
String.prototype.Trim = function () {
    return this.replace("/(^/s*)|(/s*$)/g", "");
}

var NumberFormat = {
    /*
     将数值四舍五入后格式化.
     @param num 数值(Number或者String)
     @param cent 要保留的小数位(Number)
     @param isThousand 是否需要千分位 0:不需要,1:需要(数值类型);
     @return 格式的字符串,如'1,234,567.45'
     @type String
     */
    formatNumber : function (num, isThousand) {
        var cent = 2;
        if(!num){
        	return '0.00';
        }
        num = num.toString().replace(/\$|\,/g, '');
        if (isNaN(num))//检查传入数值为数值类型.
            num = "0";
        if (isNaN(cent))//确保传入小数位为数值型数值.
            cent = 0;
        cent = parseInt(cent);
        cent = Math.abs(cent);//求出小数位数,确保为正整数.
        if (isNaN(isThousand))//确保传入是否需要千分位为数值类型.
            isThousand = 0;
        isThousand = parseInt(isThousand);
        if (isThousand < 0)
            isThousand = 0;
        if (isThousand >= 1) //确保传入的数值只为0或1
            isThousand = 1;
        sign = (num == (num = Math.abs(num)));//获取符号(正/负数)
        //Math.floor:返回小于等于其数值参数的最大整数
        num = Math.floor(num * Math.pow(10, cent) + 0.50000000001);//把指定的小数位先转换成整数.多余的小数位四舍五入.
        cents = num % Math.pow(10, cent); //求出小数位数值.
        num = Math.floor(num / Math.pow(10, cent)).toString();//求出整数位数值.
        cents = cents.toString();//把小数位转换成字符串,以便求小数位长度.
        while (cents.length < cent) {//补足小数位到指定的位数.
            cents = "0" + cents;
        }
        if (isThousand == 0) //不需要千分位符.
            return (((sign) ? '' : '-') + num + '.' + cents);
        //对整数部分进行千分位格式化.
        for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
            num = num.substring(0, num.length - (4 * i + 3)) + ',' +
                num.substring(num.length - (4 * i + 3));
        return (((sign) ? '' : '-') + num + '.' + cents);
    },

    arabiaToChinese : function(Num){
        for (i = Num.length - 1; i >= 0; i--) {
            Num = Num.replace(",", "")
            Num = Num.replace(" ", "")
        }
        Num = Num.replace("￥", "")
        if (isNaN(Num)) {
            alert("请检查小写金额是否正确");
            return;
        }
        part = String(Num).split(".");
        newchar = "";
        for (i = part[0].length - 1; i >= 0; i--) {
            if (part[0].length > 10) {
                alert("位数过大，无法计算");
                return "";
            }
            tmpnewchar = ""
            perchar = part[0].charAt(i);
            switch (perchar) {
                case "0":
                    tmpnewchar = "零" + tmpnewchar;
                    break;
                case "1":
                    tmpnewchar = "壹" + tmpnewchar;
                    break;
                case "2":
                    tmpnewchar = "贰" + tmpnewchar;
                    break;
                case "3":
                    tmpnewchar = "叁" + tmpnewchar;
                    break;
                case "4":
                    tmpnewchar = "肆" + tmpnewchar;
                    break;
                case "5":
                    tmpnewchar = "伍" + tmpnewchar;
                    break;
                case "6":
                    tmpnewchar = "陆" + tmpnewchar;
                    break;
                case "7":
                    tmpnewchar = "柒" + tmpnewchar;
                    break;
                case "8":
                    tmpnewchar = "捌" + tmpnewchar;
                    break;
                case "9":
                    tmpnewchar = "玖" + tmpnewchar;
                    break;
            }
            switch (part[0].length - i - 1) {
                case 0:
                    tmpnewchar = tmpnewchar + "元";
                    break;
                case 1:
                    if (perchar != 0) tmpnewchar = tmpnewchar + "拾";
                    break;
                case 2:
                    if (perchar != 0) tmpnewchar = tmpnewchar + "佰";
                    break;
                case 3:
                    if (perchar != 0) tmpnewchar = tmpnewchar + "仟";
                    break;
                case 4:
                    tmpnewchar = tmpnewchar + "万";
                    break;
                case 5:
                    if (perchar != 0) tmpnewchar = tmpnewchar + "拾";
                    break;
                case 6:
                    if (perchar != 0) tmpnewchar = tmpnewchar + "佰";
                    break;
                case 7:
                    if (perchar != 0) tmpnewchar = tmpnewchar + "仟";
                    break;
                case 8:
                    tmpnewchar = tmpnewchar + "亿";
                    break;
                case 9:
                    tmpnewchar = tmpnewchar + "拾";
                    break;
            }
            newchar = tmpnewchar + newchar;
        }
        if (Num.indexOf(".") != -1) {
            if (part[1].length > 2) {
                part[1] = part[1].substr(0, 2)
            }
            for (i = 0; i < part[1].length; i++) {
                tmpnewchar = ""
                perchar = part[1].charAt(i)
                switch (perchar) {
                    case "0":
                        tmpnewchar = "零" + tmpnewchar;
                        break;
                    case "1":
                        tmpnewchar = "壹" + tmpnewchar;
                        break;
                    case "2":
                        tmpnewchar = "贰" + tmpnewchar;
                        break;
                    case "3":
                        tmpnewchar = "叁" + tmpnewchar;
                        break;
                    case "4":
                        tmpnewchar = "肆" + tmpnewchar;
                        break;
                    case "5":
                        tmpnewchar = "伍" + tmpnewchar;
                        break;
                    case "6":
                        tmpnewchar = "陆" + tmpnewchar;
                        break;
                    case "7":
                        tmpnewchar = "柒" + tmpnewchar;
                        break;
                    case "8":
                        tmpnewchar = "捌" + tmpnewchar;
                        break;
                    case "9":
                        tmpnewchar = "玖" + tmpnewchar;
                        break;
                }
                if (i == 0) tmpnewchar = tmpnewchar + "角";
                if (i == 1) tmpnewchar = tmpnewchar + "分";
                newchar = newchar + tmpnewchar;
            }
        }
        while (newchar.search("零零") != -1)
            newchar = newchar.replace("零零", "零");
        newchar = newchar.replace("零亿", "亿");
        newchar = newchar.replace("亿万", "亿");
        newchar = newchar.replace("零万", "万");
        newchar = newchar.replace("零元", "元");
        newchar = newchar.replace("零角", "");
        newchar = newchar.replace("零分", "");
        if (newchar.charAt(newchar.length - 1) == "元" || newchar.charAt(newchar.length - 1) == "角")
            newchar = newchar + "整"
        return newchar;
    }
}

