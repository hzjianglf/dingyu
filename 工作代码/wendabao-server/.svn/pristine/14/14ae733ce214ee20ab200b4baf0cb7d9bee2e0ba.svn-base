package com.handany.base.push;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.PushMsgToSingleDeviceRequest;
import com.baidu.yun.push.model.PushMsgToSingleDeviceResponse;
import com.handany.base.common.ApplicationConfig;
import com.handany.base.common.CommonUtils;

public class BaiduPush {
	
	private static final Logger logger = LoggerFactory.getLogger(BaiduPush.class);
	

	/*
	 * 1. 创建PushKeyPair 用于app的合法身份认证 apikey和secretKey可在应用详情中获取
	 */
	static String apiKey_android = ApplicationConfig.getConfig("baidu.push.android.apiKey");
	static String secretKey_android = ApplicationConfig.getConfig("baidu.push.android.secretKey");
	static PushKeyPair pair_android = new PushKeyPair(apiKey_android, secretKey_android);

	static String apiKey_iOS = ApplicationConfig.getConfig("baidu.push.iOS.apiKey");
	static String secretKey_iOS = ApplicationConfig.getConfig("baidu.push.iOS.secretKey");
	static PushKeyPair pair_iOS = new PushKeyPair(apiKey_iOS, secretKey_iOS);
	
	
	// 2. 创建BaiduPushClient，访问SDK接口
	static BaiduPushClient pushClient_android = new BaiduPushClient(pair_android, BaiduPushConstants.CHANNEL_REST_URL);
	static{
		
		// 3. 注册YunLogHandler，获取本次请求的交互信息
		pushClient_android.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				logger.debug("YunLogEvent  Msg :{}",event.getMessage());
			}
		});
	}
	
	static BaiduPushClient pushClient_iOS = new BaiduPushClient(pair_iOS, BaiduPushConstants.CHANNEL_REST_URL);
	static{
		
		// 3. 注册YunLogHandler，获取本次请求的交互信息
		pushClient_iOS.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				logger.debug("YunLogEvent  Msg :{}",event.getMessage());
			}
		});
	}

	public static void push(MessageContent mc) {
		
		String jsonMsg =mc.toJsonString();
		logger.debug("push Msg :{}",jsonMsg);
		try {
			
			// 4. 设置请求参数，创建请求实例			
			PushMsgToSingleDeviceRequest request = new PushMsgToSingleDeviceRequest()
					.addChannelId(mc.channelId())
					//.addMsgExpires(new Integer(3600)) // 设置消息的有效时间,单位秒,默认3600*5.
					.addMessageType(1) // 设置消息类型,0表示透传消息,1表示通知,默认为0.
					.addMessage(jsonMsg)
					.addDeviceType(CommonUtils.toInt(mc.deviceType()));
			
			if("4".equals(mc.deviceType())){
				request.setDeployStatus(CommonUtils.toInt(ApplicationConfig.getConfig("iOS.deploy.status")));
			}
			
			// 5. 执行Http请求
			PushMsgToSingleDeviceResponse response = null;
			
			
			if("3".equals(mc.deviceType())){
				 response = pushClient_android.pushMsgToSingleDevice(request);
			}else if("4".equals(mc.deviceType())){
				 response = pushClient_iOS.pushMsgToSingleDevice(request);
			}else{
				logger.debug("unknown deviceType !!!");
			}
			
			if(response != null){
				// 6. Http请求返回值解析
				logger.debug("msgId:{} ,sendTime:{}", response.getMsgId(), response.getSendTime());
			}
		
		} catch (PushClientException e) {
			logger.error("PushClientException",e);
		} catch (PushServerException e) {
			logger.error("PushServerException: requestId: {}, errorCode: {}, errorMsg: {}",  e.getRequestId(),
						e.getErrorCode(), e.getErrorMsg());
		}
	}
	
	public static void main(String[] args) {
		
		
		MessageContent mc = new MessageContent();
		mc.setDeviceType("4");
		mc.setTitle("12345");
		mc.setChannelId("5220863901655803442");
		push(mc);
	}
}
