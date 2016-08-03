package com.handany.rbac.common;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.handany.base.common.ApplicationConfig;
import com.handany.base.common.HttpUtil;
import com.handany.base.common.ThreadLocalContainer;
import com.handany.base.container.CacheContainer;
import com.handany.rbac.model.PmUser;

/**
 * 用户登录上下文管理
 * @author lhb
 *
 */
public class UserContextManager {

	private static final Logger logger= LoggerFactory.getLogger(UserContextManager.class);
	
	/**
	 * 获取登录用户
	 * @return
	 */
	public static PmUser getLoginUser() {

		String tokenId = getTokenId();

		if(tokenId == null){
			logger.debug("getLoginUser return null because tokenId is null");
			return null;
		}
		
		PmUser user = ThreadLocalContainer.get("loginUser", PmUser.class);
		
		if(user == null){
			Map context = CacheContainer.get(Constants.CACHE_LOGIN_CONTEXT + tokenId, Map.class);
			if(context != null){
				user = (PmUser)context.get("user");
				ThreadLocalContainer.put("loginUser", user);
			}
		}
		return user;
	}

	
	/**
	 * 验证tokenId是否有效
	 * @return
	 */
	public static boolean validateTokenId(String tokenId){
		
		Map context = CacheContainer.get(Constants.CACHE_LOGIN_CONTEXT + tokenId, Map.class);
		
		return context != null;
	}
	
	/**
	 * 获得tokenId
	 * @return
	 */
	public static String getTokenId(){
		
		String tokenId = (String)ThreadLocalContainer.get("tokenId");

		if(tokenId == null){
			tokenId = HttpUtil.getParameter("tokenId");//HttpUtil.getRequest().getParameter("tokenId");
			ThreadLocalContainer.put("tokenId",tokenId);
		}
		
		return tokenId;
	}
	
	
	/**
	 * 用户登录
	 * @param user
	 */
	public static String login(PmUser user) {

		String tokenId = UUID.randomUUID().toString();

		Map context = new HashMap();

		String deviceType = HttpUtil.getParameter("deviceType");
		context.put("deviceType",deviceType);
		
		context.put("user", user);
		
		String channelId = HttpUtil.getParameter("channelId");
		
		
		
		if(channelId != null && channelId.trim().length() >= 0){
			Map map = new HashMap();
			map.put("channelId", channelId);
			map.put("deviceType", deviceType);
			CacheContainer.put(Constants.CACHE_CHANNEL+user.getId(),map ,com.handany.base.common.Constants.SECONDS_WEEK);
		}
		
		ThreadLocalContainer.put("tokenId", tokenId);
		CacheContainer.put(Constants.CACHE_LOGIN_CONTEXT + tokenId, context,
				com.handany.base.common.Constants.SECONDS_MINUTE * ApplicationConfig.getConfigInt("login.timeout.min"));

		return tokenId;
	}
	
	public static String getDeviceType(){
		String tokenId = HttpUtil.getParameter("tokenId");
		Map context = CacheContainer.get(Constants.CACHE_LOGIN_CONTEXT + tokenId, Map.class);
		return (String)context.get("deviceType");
	}
	
	
	/**
	 * 重新设置上下文中的用户信息
	 */
	public static void resetLoginUser(PmUser user){
		setAttribute("user",user);
	}
	
	
	/**
	 * 设置上下文属性
	 * @param name
	 * @param value
	 */
	public static void setAttribute(String name,Object value){
		
		String tokenId = getTokenId();
		if(null == tokenId){
			tokenId = (String)HttpUtil.getRequest().getAttribute("tokenId");
		}
		
		if(tokenId == null){
			logger.debug("tokenId is null");
			throw new RuntimeException("tokenId is null");
		}
		
		Map context = CacheContainer.get(Constants.CACHE_LOGIN_CONTEXT + tokenId, Map.class);
		
		
		if(context == null){
			throw new RuntimeException("user context is invalid");
		}
		
		context.put(name, value);
		
		
		CacheContainer.put(Constants.CACHE_LOGIN_CONTEXT + tokenId, context,
				com.handany.base.common.Constants.SECONDS_MINUTE * 30);
		
	}
	
	
	/**
	 * 获取上下文属性
	 * @param name
	 * @return
	 */
	public static Object getAttribute(String name){

		String tokenId = getTokenId();
		
		if(tokenId == null){
			logger.debug("tokenId is null");
			throw new RuntimeException("tokenId is null");
		}
		Map context = CacheContainer.get(Constants.CACHE_LOGIN_CONTEXT + tokenId, Map.class);
		
		if(context == null){
			throw new RuntimeException("user context is invalid");
		}
		
		return context.get(name);
		
	}
	
	/**
	 * 注销用户登录
	 */
	public static void logout(){
		String tokenId = getTokenId();
		if(tokenId == null){
			return;
		}
		CacheContainer.remove(Constants.CACHE_LOGIN_CONTEXT + tokenId);
	}
	
}
