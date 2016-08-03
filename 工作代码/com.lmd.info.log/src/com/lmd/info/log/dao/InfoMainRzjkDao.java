package com.lmd.info.log.dao;

import org.springframework.stereotype.Component;

import com.lmd.info.log.model.InfoMainRzjk;

import net.risesoft.soa.framework.dao.support.GenericHibernateDaoSupport;
@Component
public class InfoMainRzjkDao extends GenericHibernateDaoSupport<InfoMainRzjk,String> {

	@Override
	protected Class<InfoMainRzjk> getEntityClass() {
		// TODO 自动生成的方法存根
		return InfoMainRzjk.class;
	}

}
