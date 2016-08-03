package com.lmd.encanddec.manager.impl;

import org.springframework.stereotype.Service;

import com.lmd.encanddec.service.IpService;
import com.lmd.encanddec.util.ProperUtil;
@Service("IpService")
public class IpServiceImpl implements IpService{

	@Override
	public String getFwIp() {
		// TODO 自动生成的方法存根
		String enableAuth = ProperUtil.getProperty("asf.ip");
		return enableAuth;
	}

}
