package com.wxpt.site.service;

import java.util.List;

import com.wxpt.site.entity.Area;
import com.wxpt.site.entity.Canton;
import com.wxpt.site.entity.Customers;
import com.wxpt.site.entity.Industry;

public interface CustomersService extends ParentService {
	public void addCustomers(int enterId,Customers customers);

	public List<Customers> getCustomersByInId(int enterId,int industryId);

	public List<Customers> getCustomersByAreaId(int enterId,int areaId);

	public List<Customers> getCustomersByCantonId(int enterId,int cantonId);

	public List<Customers> getCustomersList(int enterId);

	public List<Customers> getCustomersList(int enterId,int page, int rows);

	public List<Customers> getCustomersList(int enterId,String hql, int page, int rows);

	public int getCustomersCount(int enterId);

	public int getCustomersCount(int enterId,String hql);

	public Customers getCustomersByID(int enterId,int customersId);

	public void deleteById(int enterId,int customersId);

	public List<Customers> getCustomersList(int enterId,String hql);

	public Customers getCustomersByNo(int enterId,String parseInt);

	public List<Customers> getCustomersListByXY(int enterId);

	public void updateCustomer(int enterId,String Location_X,String Location_y,int customers_id);
	
	public Canton getbyId(String sql);
	
    public Area getById(String sql);
	
	public Industry getIndustryById(String sql2);
	
	public void update(int enterId,String sql);
}
