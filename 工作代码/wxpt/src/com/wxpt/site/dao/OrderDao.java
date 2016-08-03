package com.wxpt.site.dao;

import java.util.List;

import com.wxpt.site.entity.Appraise;
import com.wxpt.site.entity.BuyProduct;
import com.wxpt.site.entity.EnterInfor;
import com.wxpt.site.entity.Logistics;
import com.wxpt.site.entity.Order;
import com.wxpt.site.entity.ShoppingAddress;
import com.wxpt.site.entity.ShoppingCar;
import com.wxpt.site.entity.pojo.ShoppingCar1;
public interface OrderDao {
	public void save(int enterId,int mid,int sid,int sum);
	public ShoppingCar1 getOne(int enterId,int mid,int sid);
	public int getChangeCar(int enterId,int mid,int sid,int sum);
	public void getDelete(int enterId,int mid,int sid);
	public List<ShoppingCar> getListShoppingCar(int enterId,int mid);
	public List<ShoppingAddress> getlsa(int enterId,int mid);
	public List<Logistics> getll(int enterId);
	public int getChangeShoppingAddress(int enterId,int lid,String name,String address,String phone);
	public void getDeleteAddress(int enterId,int lid);
	public void saveAddress(int enterId,int mid,String name,String address,String phone);
	public ShoppingAddress getOneShoppingAddress(int enterId,int id);
	public void saveOrder(int enterId,String product_id,String orderno,String savetime,Double money,int paytype,String name,String address,String phone,
			int sendtype,int logisticsId,int mid,String leave);
	public int getChangeCar(int enterId,int mid);
	public List<Order> getAllNewOrder(int enterId,int mid);
	public List<ShoppingCar> getListShoppingCar(int enterId,int mid,int sid);
	public Order getOrder(int enterId,String no);
	public void getSaveBuy(int enterId,int sid,int oid,int sum);
	public void getDeleteShopping(int enterId,int mid);
	public List<BuyProduct> getOneByOrderId(int enterId,int id);
	public List<Order> getAllSendOrder(int enterId,int mid);
	public List<Order> getAllTakeOrder(int enterId,int mid);
	public List<Order> getAllOverOrder(int enterId,int mid);
	public List<Order> getAllNewOrderFen(int enterId,int mid,int start, int number);
	public List<Order> getAllSendOrderFen(int enterId,int mid,int start, int number);
	public List<Order> getAlltakeOrderFen(int enterId,int mid,int start, int number);
	public List<Order> getAllOverOrderFen(int enterId,int mid,int start, int number);
	public int getCannelOrder(int enterId,String no,String value);
	public EnterInfor getOneEnterinfor(int enterId);
	public int getChangeEnterOrder(int enterId,String no);
	public int getChangeProduct(int enterId,int id,int sell,int sum);
	public int getChangeShoppingCar(int enterId,int id,int pid,int sum);
	//订单监听方法
	public List<Order> getAllOrder(int enterId,int payType);
	public List<Order> getAllOrders(int enterId,int takeType,int sendType);
	public int updateOrder(int enterId,int orderId,int takeType);
	public int delOrder(String hql);
	public int updateOrderType(String hql);
	public void getDeleteOrder(int enterId,String no);
	public Appraise getOneAppraise(int enterId,int mid,int sid,int id);
	public int getAllShopping(int enterId,int mid);
	public Logistics getOneLogistics(int enterId,int id);
	public Order getLastOne(int enterId,int mid);
}
