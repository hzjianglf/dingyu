package com.lmd.encanddec.manager.impl;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.xml.ws.WebServiceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmd.encanddec.dao.JjmServiceDao;
import com.lmd.encanddec.manager.JjmServiceManager;
import com.lmd.encanddec.model.JjmService;
import com.lmd.encanddec.service.JjmServiceManagerService;
import com.lmd.encanddec.util.DESUtil;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.HttpRequestHandler;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service("jjmServiceManager")
@Transactional
public class JjmServiceManagerImpl implements JjmServiceManagerService,JjmServiceManager{

	@Autowired
	private JjmServiceDao jjmServiceDao;
	@Resource
	private WebServiceContext context;
	@Override
	public String encrypt(String str) {
		String result="";
		try {
			result = DESUtil.encrypt(str);
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
//			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String decrypt(String str, String charSet) {
		String result="";
		try {
			result = DESUtil.decrypt(str, charSet);
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
//			e.printStackTrace();
		}
		return result;
	}

	/*@Override
	public void saveJjmService(JjmService jjmService) {
		// TODO 自动生成的方法存根
		jjmServiceDao.save(jjmService);
	}

	@Override
	public void deleteOrder(String jjmId) {
		// TODO 自动生成的方法存根
		jjmServiceDao.delete(jjmId);
	}

	@Override
	public JjmService getJjmService(String jjmId) {
		// TODO 自动生成的方法存根
		return jjmServiceDao.loadById(jjmId);
	}*/

}
