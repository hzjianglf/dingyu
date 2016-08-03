package com.wxpt.site.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.wxpt.site.dao.ZaixianzhifuDao;
import com.wxpt.site.service.ZaixianzhifuService;

public class ZaixianzhifuServiceImpl implements ZaixianzhifuService {

	@Autowired
	ZaixianzhifuDao zaixianzhifudao;
	
	
	
	@Transactional
	
	public void fukuanchenggong(int enterId,String ordermum) {
		// TODO Auto-generated method stub
		zaixianzhifudao.fukuanchenggong(enterId,ordermum);
	}



	@Override
	public void yifahuo(int enterId,String ordermum) {
		// TODO Auto-generated method stub
		zaixianzhifudao.yifahuo(enterId,ordermum);
	}

}
