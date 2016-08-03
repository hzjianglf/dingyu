package com.wxpt.action.site.web;
/**
 * ��ƣ�֧������
 * ���ܣ�֧�����ⲿ����ӿڿ���
 * �ӿ���ƣ���׼ʵ��˫�ӿ�
 * �汾��2.0
 * ���ڣ�2008-12-25
 * ���ߣ�֧������˾���۲�����֧���Ŷ�
 * ��ϵ��0571-26888888
 * ��Ȩ��֧������˾
 * */
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

public class Payment {

    public static String CreateUrl(String paygateway,String service,String sign_type,String out_trade_no,
    		      String input_charset,String partner,String key,String seller_email,
                  String body,String subject,String pricez,String quantity,String show_url,String payment_type,
                  String discount,String logistics_type,String logistics_fee,String logistics_payment,
                  String return_url,String notify_url) {

        Map params = new HashMap();
        params.put("service", service);//create_direct_pay_by_user=直接付款,trade_create_by_buyer 担保付款
        params.put("out_trade_no", out_trade_no);//外部交易号,最好具有唯一性,在获取支付宝发来的付款信息时使用.        
        params.put("show_url", show_url); //商品展示地址，例如：http://www.alipay.com；如果锁定具体商品页面，可以传入（http://www.alipay.com?pid=变量）
        params.put("quantity", quantity);//订单商品数量,一般都是写1,它是按照整个订单包来计算  
        params.put("partner", partner);//partnerId(合作伙伴ID)  
        params.put("payment_type", payment_type);//支付类型 1=商品购买,2=服务购买,...  
        params.put("discount", discount);//填写折扣信息 -5表示抵扣5元      
        params.put("body", body);//填写在跳到支付宝页面上显示的付款内容信息
        params.put("notify_url", notify_url);//客户付款后,支付宝调用的页面  http://www.xxx.com/notifyurl.jsp
        params.put("price", pricez);//订单金额信息     
        params.put("return_url", return_url);//客户付款成功后,显示给客户的页
        params.put("seller_email", seller_email);//你的支付宝账户email     
        params.put("logistics_type", logistics_type); //物流配送方式：POST(平邮)、EMS(EMS)、EXPRESS(其他快递)
        params.put("logistics_fee", logistics_fee); //物流配送费用x
        params.put("logistics_payment", logistics_payment);    //物流配送费用付款方式：SELLER_PAY(卖家支付)、BUYER_PAY(买家支付)、BUYER_PAY_AFTER_RECEIVE(货到付款)
        params.put("subject", subject);//商品名称，如果想显示多个商品名称，可以将多个商品叠加传入。
        params.put("_input_charset", input_charset); //页面编码，此时默认GBK

        String prestr = "";

        prestr = prestr + key;
       // System.out.println("prestr=" + prestr);

        String sign =Md5Encrypt.md5(getContent(params, key));

        String parameter = "";
        parameter = parameter + paygateway;//paygateway支付接口(不可修改)//跳转到支付宝的url

        List keys = new ArrayList(params.keySet());
        for (int i = 0; i < keys.size(); i++) {
          	String value =(String) params.get(keys.get(i));
            if(value == null || value.trim().length() ==0){
            	continue;
            }
            try {
                parameter = parameter + keys.get(i) + "="
                    + URLEncoder.encode(value, input_charset) + "&";
            } catch (UnsupportedEncodingException e) {

                e.printStackTrace();
            }
        }

        parameter = parameter + "sign=" + sign + "&sign_type=" + sign_type;

        return parameter;

    }


    private static String getContent(Map params, String privateKey) {
        List keys = new ArrayList(params.keySet());
        Collections.sort(keys);

        String prestr = "";

		boolean first = true;
		for (int i = 0; i < keys.size(); i++) {
			String key = (String) keys.get(i);
			String value = (String) params.get(key);
			if (value == null || value.trim().length() == 0) {
				continue;
			}
			if (first) {
				prestr = prestr + key + "=" + value;
				first = false;
			} else {
				prestr = prestr + "&" + key + "=" + value;
			}
		}
        return prestr + privateKey;
    }
}