package com.lmd.info.log.manager.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmd.info.log.dao.InfoMainRzjkDao;
import com.lmd.info.log.manager.InfoMainRzjkManager;
import com.lmd.info.log.model.InfoMainRzjk;
@Service("infoMainRzjkManager")
@Transactional
public class InfoMainRzjkManagerImpl implements InfoMainRzjkManager{

	private static final Logger log = LoggerFactory.getLogger(InfoMainRzjkManagerImpl.class);
	private String currentInfoMainRzjkManagerId4BizLog;
	@Autowired
	private InfoMainRzjkDao infoMainRzjkDao;
	@Override
	public String savaInfoMainRzjk(InfoMainRzjk infoMainRzjk) {
		// TODO 自动生成的方法存根
		this.setCurrentInfoMainRzjkManagerId4BizLog(infoMainRzjk.getId());
		String save="success";
		try {
			infoMainRzjkDao.save(infoMainRzjk);
		} catch (Exception e) {
			// TODO: handle exception
			save="error";
		}
		return save;
	}
	public String getCurrentInfoMainRzjkManagerId4BizLog() {
		return currentInfoMainRzjkManagerId4BizLog;
	}
	public void setCurrentInfoMainRzjkManagerId4BizLog(
			String currentInfoMainRzjkManagerId4BizLog) {
		this.currentInfoMainRzjkManagerId4BizLog = currentInfoMainRzjkManagerId4BizLog;
	}

	
}
