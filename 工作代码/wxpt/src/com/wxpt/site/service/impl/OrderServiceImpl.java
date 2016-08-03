package com.wxpt.site.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.wxpt.site.dao.OrderDao;
import com.wxpt.site.entity.Appraise;
import com.wxpt.site.entity.BuyProduct;
import com.wxpt.site.entity.EnterInfor;
import com.wxpt.site.entity.Logistics;
import com.wxpt.site.entity.Order;
import com.wxpt.site.entity.ShoppingAddress;
import com.wxpt.site.entity.ShoppingCar;
import com.wxpt.site.entity.pojo.ShoppingCar1;
import com.wxpt.site.service.OrderService;


public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderDao orderDao;
	@Override
	@Transactional
	public void save(int enterId,int mid, int sid,int sum) {
		// TODO Auto-generated method stub
		orderDao.save(enterId,mid, sid,sum);
	}
	@Override
	public ShoppingCar1 getOne(int enterId,int mid, int sid) {
		// TODO Auto-generated method stub
		return orderDao.getOne(enterId,mid, sid);
	}
	@Override
	public int getChangeCar(int enterId,int mid, int sid, int sum) {
		// TODO Auto-generated method stub
		return orderDao.getChangeCar(enterId,mid, sid, sum);
	}
	@Override
	public void getDelete(int enterId,int mid, int sid) {
		// TODO Auto-generated method stub
		orderDao.getDelete(enterId,mid, sid);
	}
	@Override
	public List<ShoppingCar> getListShoppingCar(int enterId,int mid) {
		// TODO Auto-generated method stub
		return orderDao.getListShoppingCar(enterId,mid);
	}
	@Override
	public List<ShoppingAddress> getlsa(int enterId,int mid) {
		// TODO Auto-generated method stub
		return orderDao.getlsa(enterId,mid);
	}
	@Override
	public List<Logistics> getll(int enterId) {
		// TODO Auto-generated method stub
		return orderDao.getll(enterId);
	}
	@Override
	public int getChangeShoppingAddress(int enterId,int lid, String name, String address,
			String phone) {
		// TODO Auto-generated method stub
		return orderDao.getChangeShoppingAddress( enterId,lid, name, address, phone);
	}
	@Override
	public void getDeleteAddress(int enterId,int lid) {
		// TODO Auto-generated method stub
		orderDao.getDeleteAddress(enterId,lid);
	}
	@Override
	@Transactional
	public void saveAddress(int enterId,int mid, String name, String address, String phone) {
		// TODO Auto-generated method stub
		orderDao.saveAddress(enterId,mid, name, address, phone);
	}
	@Override
	public ShoppingAddress getOneShoppingAddress(int enterId,int id) {
		// TODO Auto-generated method stub
		return orderDao.getOneShoppingAddress(enterId,id);
	}
	@Override
	@Transactional
	public void saveOrder(int enterId,String product_id, String orderno, String savetime,
			Double money, int paytype, String name, String address,
			String phone, int sendtype, int logisticsId, int mid, String leave) {
		// TODO Auto-generated method stub
		orderDao.saveOrder(enterId,product_id, orderno, savetime, money, paytype, name, address, phone, sendtype, logisticsId, mid, leave);
	}
	@Override
	public int getChangeCar(int enterId,int mid) {
		// TODO Auto-generated method stub
		return orderDao.getChangeCar(enterId,mid);
	}
	@Override
	public List<Order> getAllNewOrder(int enterId,int mid) {
		// TODO Auto-generated method stub
		return orderDao.getAllNewOrder(enterId,mid);
	}
	@Override
	public List<ShoppingCar> getListShoppingCar(int enterId,int mid, int sid) {
		// TODO Auto-generated method stub
		return orderDao.getListShoppingCar(mid, sid);
	}
	@Override
	public Order getOrder(int enterId,String no) {
		// TODO Auto-generated method stub
		return orderDao.getOrder(enterId,no);
	}
	@Override
	@Transactional
	public void getSaveBuy(int enterId,int sid, int oid, int sum) {
		// TODO Auto-generated method stub
		orderDao.getSaveBuy(enterId,sid, oid, sum);
	}
	@Override
	public void getDeleteShopping(int enterId,int mid) {
		// TODO Auto-generated method stub
		orderDao.getDeleteShopping(enterId,mid);
	}
	@Override
	public List<BuyProduct> getOneByOrderId(int enterId,int id) {
		// TODO Auto-generated method stub
		return orderDao.getOneByOrderId(enterId,id);
	}
	@Override
	public List<Order> getAllSendOrder(int enterId,int mid) {
		// TODO Auto-generated method stub
		return orderDao.getAllSendOrder(enterId,mid);
	}
	@Override
	public List<Order> getAllTakeOrder(int enterId,int mid) {
		// TODO Auto-generated method stub
		return orderDao.getAllTakeOrder(enterId,mid);
	}
	@Override
	public List<Order> getAllNewOrderFen(int enterId,int mid,int start, int number) {
		// TODO Auto-generated method stub
		return orderDao.getAllNewOrderFen(enterId,mid,start,number);
	}
	@Override
	public List<Order> getAllSendOrderFen(int enterId,int mid, int start, int number) {
		// TODO Auto-generated method stub
		return orderDao.getAllSendOrderFen(enterId,mid, start, number);
	}
	@Override
	public List<Order> getAlltakeOrderFen(int enterId,int mid, int start, int number) {
		// TODO Auto-generated method stub
		return orderDao.getAlltakeOrderFen( enterId,mid, start, number);
	}
	@Override
	public List<Order> getAllOverOrderFen(int enterId,int mid, int start, int number) {
		// TODO Auto-generated method stub
		return orderDao.getAllOverOrderFen(enterId,mid, start, number);
	}
	@Override
	public int getCannelOrder(int enterId,String no, String value) {
		// TODO Auto-generated method stub
		return orderDao.getCannelOrder(enterId,no, value);
	}
	@Override
	public int getChangeEnterOrder(int enterId,String no) {
		// TODO Auto-generated method stub
		return orderDao.getChangeEnterOrder(enterId,no);
	}
	@Override
	public int getChangeProduct(int enterId,int id, int sell, int sum) {
		// TODO Auto-generated method stub
		return orderDao.getChangeProduct(enterId,id, sell, sum);
	}
	@Override
	public int getChangeShoppingCar(int enterId,int id, int pid, int sum) {
		// TODO Auto-generated method stub
		return orderDao.getChangeShoppingCar(enterId,id, pid, sum);
	}
	@Transactional
	@Override
	public List<Order> getAllOrder(int enterId,int payType) {
		// TODO Auto-generated method stub
		return orderDao.getAllOrder(enterId,payType);
	}
	@Transactional
	@Override
	public List<Order> getAllOrders(int enterId,int takeType, int sendType) {
		// TODO Auto-generated method stub
		return orderDao.getAllOrders(enterId,takeType, sendType);
	}
	@Transactional
	@Override
	public int updateOrder(int enterId,int orderId, int takeType) {
		// TODO Auto-generated method stub
		return orderDao.updateOrder(enterId,orderId, takeType);
	}
	@Transactional
	@Override
	public int delOrder(String hql) {
		// TODO Auto-generated method stub
		return orderDao.delOrder(hql);
	}
	@Transactional
	@Override
	public int updateOrderType(String hql) {
		// TODO Auto-generated method stub
		return orderDao.updateOrderType(hql);
	}
	@Override
	public void getDeleteOrder(int enterId,String no) {
		// TODO Auto-generated method stub
		orderDao.getDeleteOrder(enterId,no);
	}
	@Override
	public Appraise getOneAppraise(int enterId,int mid, int sid, int id) {
		// TODO Auto-generated method stub
		return orderDao.getOneAppraise(enterId,mid, sid, id);
	}
	@Override
	public List<Order> getAllOverOrder(int enterId,int mid) {
		// TODO Auto-generated method stub
		return orderDao.getAllOverOrder(enterId,mid);
	}
	public EnterInfor getOneEnterinfor(int enterId) {
		// TODO Auto-generated method stub
		return orderDao.getOneEnterinfor(enterId);
	}
	@Override
	public int getAllShopping(int enterId,int mid) {
		// TODO Auto-generated method stub
		return orderDao.getAllShopping(enterId,mid);
	}
	@Override
	public Logistics getOneLogistics(int enterId,int id) {
		// TODO Auto-generated method stub
		return orderDao.getOneLogistics(enterId,id);
	}
	@Override
	public Order getLastOne(int enterId,int mid) {
		// TODO Auto-generated method stub
		return orderDao.getLastOne(enterId,mid);
	}
}
