package com.wxpt.site.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.wxpt.site.entity.Area;
import com.wxpt.site.entity.Canton;
import com.wxpt.site.entity.Customers;
import com.wxpt.site.entity.Industry;
import com.wxpt.site.dao.CustomersDao;
import com.wxpt.site.service.CustomersService;

public class CustomersServiceImpl extends ParentServieImpl implements CustomersService {

	@Autowired
	CustomersDao customersDao;

	@Transactional
	@Override
	public List<Customers> getCustomersByInId(int enterId,int industryId) {
		// TODO Auto-generated method stub
		return customersDao.getCustomersByInId(enterId,industryId);
	}

	@Override
	public List<Customers> getCustomersByAreaId(int enterId,int areaId) {
		// TODO Auto-generated method stub
		return customersDao.getCustomersByAreaId(enterId,areaId);
	}

	@Override
	public List<Customers> getCustomersByCantonId(int enterId,int cantonId) {
		// TODO Auto-generated method stub
		return customersDao.getCustomersByCantonId(enterId,cantonId);
	}

	@Override
	public List<Customers> getCustomersList(int enterId) {
		// TODO Auto-generated method stub
		return customersDao.getCustomersList(enterId);
	}

	@Override
	public List<Customers> getCustomersList(int enterId,int page, int rows) {
		// TODO Auto-generated method stub
		return customersDao.getCustomersList(enterId,page,rows);
	}

	@Override
	public int getCustomersCount(int enterId) {
		// TODO Auto-generated method stub
		return customersDao.getCustomersCount(enterId);
	}

	@Override
	public Customers getCustomersByID(int enterId,int customersId) {
		// TODO Auto-generated method stub
		return customersDao.getCustomersByID(enterId,customersId);
	}

	@Override
	public void deleteById(int enterId,int customersId) {
		// TODO Auto-generated method stub
		customersDao.deleteById(enterId,customersId);
	}

	@Override
	public List<Customers> getCustomersList(int enterId,String hql, int page, int rows) {
		// TODO Auto-generated method stub
		return customersDao.getCustomersList(enterId,hql,page,rows);
	}

	@Override
	public int getCustomersCount(int enterId,String hql) {
		// TODO Auto-generated method stub
		return customersDao.getCustomersCount(enterId,hql);
	}
@Transactional
	@Override
	public List<Customers> getCustomersList(int enterId,String hql) {
		// TODO Auto-generated method stub
		return customersDao.getCustomersList(enterId,hql);
	}

	@Override
	public Customers getCustomersByNo(int enterId,String no) {
		// TODO Auto-generated method stub
		return customersDao.getCustomersByNo(enterId,no);
	}

	@Transactional
	public List<Customers> getCustomersListByXY(int enterId) {
		// TODO Auto-generated method stub
		return customersDao.getCustomersListByXY(enterId);
	}

	@Override

	public void updateCustomer(int enterId, String Location_x,String Location_y,int customers_id) {
		customersDao.updateCustomer(enterId,  Location_x,Location_y,customers_id);
		
	}

	@Override
	public void addCustomers(int enterId, Customers customers) {
		// TODO Auto-generated method stub
		customersDao.addCustomers(enterId, customers);
	}
	@Transactional
	@Override
	public Canton getbyId(String sql) {
		// TODO Auto-generated method stub
		return customersDao.getbyId(sql);
	}
	@Transactional
	@Override
	public Area getById(String sql) {
		// TODO Auto-generated method stub
		return customersDao.getById(sql);
	}
	@Transactional
	@Override
	public Industry getIndustryById(String sql2) {
		// TODO Auto-generated method stub
		return customersDao.getIndustryById(sql2);
	}
	@Transactional
	@Override
	public void update(int enterId, String sql) {
		// TODO Auto-generated method stub
		customersDao.update(enterId, sql);
	}

	

}
