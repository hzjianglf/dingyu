package cn.udrm.dev.demo.dao;

import net.risesoft.soa.framework.dao.support.GenericHibernateDaoSupport;

import org.springframework.stereotype.Component;

import cn.udrm.dev.demo.model.Product;

@Component
public class ProductDao extends GenericHibernateDaoSupport<Product, String>{

	protected Class<Product> getEntityClass() {
		return Product.class;
	}
	
}