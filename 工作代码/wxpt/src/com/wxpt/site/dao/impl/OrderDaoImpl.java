package com.wxpt.site.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.wxpt.site.dao.OrderDao;
import com.wxpt.site.entity.Appraise;
import com.wxpt.site.entity.BuyProduct;
import com.wxpt.site.entity.EnterInfor;
import com.wxpt.site.entity.Logistics;
import com.wxpt.site.entity.Order;
import com.wxpt.site.entity.ShoppingAddress;
import com.wxpt.site.entity.ShoppingCar;
import com.wxpt.site.entity.pojo.ShoppingCar1;

public class OrderDaoImpl implements OrderDao {
	@Autowired
	SessionFactory sessionfactory;
	protected Session session=null;
	private List<ShoppingCar> listshop;
	private List<ShoppingAddress> lsa;
	private List<Order> lorder;
	private Order or;
	private BuyProduct bp;
	private List<BuyProduct> lbp;
	private EnterInfor enter;
	private List<Appraise> app;
	public List<Appraise> getApp() {
		return app;
	}
	public void setApp(List<Appraise> app) {
		this.app = app;
	}
	List<EnterInfor> enterList= new  ArrayList<EnterInfor>();
	public EnterInfor getEnter() {
		return enter;
	}
	public void setEnter(EnterInfor enter) {
		this.enter = enter;
	}
	public List<BuyProduct> getLbp() {
		return lbp;
	}
	public void setLbp(List<BuyProduct> lbp) {
		this.lbp = lbp;
	}
	public BuyProduct getBp() {
		return bp;
	}
	public void setBp(BuyProduct bp) {
		this.bp = bp;
	}
	public Order getOr() {
		return or;
	}
	public void setOr(Order or) {
		this.or = or;
	}
	public List<Order> getLorder() {
		return lorder;
	}
	public void setLorder(List<Order> lorder) {
		this.lorder = lorder;
	}
	private ShoppingAddress sa;
	public ShoppingAddress getSa() {
		return sa;
	}
	public void setSa(ShoppingAddress sa) {
		this.sa = sa;
	}
	private List<Logistics> lll;
	public List<Logistics> getLll() {
		return lll;
	}
	public void setLll(List<Logistics> lll) {
		this.lll = lll;
	}
	public List<ShoppingAddress> getLsa() {
		return lsa;
	}
	public void setLsa(List<ShoppingAddress> lsa) {
		this.lsa = lsa;
	}
	public List<ShoppingCar> getListshop() {
		return listshop;
	}
	public void setListshop(List<ShoppingCar> listshop) {
		this.listshop = listshop;
	}
	@Override
	public void save(int enterId,int mid, int sid,int sum) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO wxpt"+enterId+".shopping_car(`member_id`, `product_id`, `product_sum`) VALUES ("+mid+","+sid+","+sum+");";
		try {
			session=(Session) this.sessionfactory.getCurrentSession();
			session.beginTransaction();
			session.connection().createStatement().executeUpdate(sql);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public ShoppingCar1 getOne(int enterId,int mid, int sid) {
		// TODO Auto-generated method stub
		ShoppingCar1 sc=new ShoppingCar1();
		String sql="SELECT * FROM wxpt"+enterId+".shopping_car WHERE `member_id`="+mid+" and `product_id`="+sid;
		try {
			List<Object[]> ob=(List<Object[]>) this.sessionfactory.getCurrentSession().createSQLQuery(sql).list();
			
			sc.setShoppingId(Integer.parseInt(ob.get(0)[0].toString()));
			sc.setMemberId(Integer.parseInt(ob.get(0)[1].toString()));
			sc.setProductId(Integer.parseInt(ob.get(0)[2].toString()));
			sc.setProductSum((Integer)ob.get(0)[3]);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sc;
	}
	@Override
	public int getChangeCar(int enterId,int mid, int sid, int sum) {
		// TODO Auto-generated method stub
		int i=0;
		String sql="UPDATE  wxpt"+enterId+".`shopping_car` SET `product_sum`="+sum+" WHERE `member_id`="+mid+" and `product_id`="+sid;
		session=(Session) this.sessionfactory.getCurrentSession();
		Query query = session.createSQLQuery(sql);
		i=query.executeUpdate();
		return i;
	}
	@Override
	public void getDelete(int enterId,int mid, int sid) {
		// TODO Auto-generated method stub
		String sql="DELETE FROM  wxpt"+enterId+".`shopping_car` WHERE `member_id`="+mid+" and `product_id`="+sid;
		try {
			this.sessionfactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public List<ShoppingCar> getListShoppingCar(int enterId,int mid) {
		// TODO Auto-generated method stub
		listshop=new ArrayList<ShoppingCar>();
		String Sql="SELECT * FROM  wxpt"+enterId+".shopping_car WHERE `member_id`="+mid;
		try {
			listshop=this.sessionfactory.getCurrentSession().createSQLQuery(Sql).addEntity(ShoppingCar.class).list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listshop;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ShoppingAddress> getlsa(int enterId,int mid) {
		// TODO Auto-generated method stub
		lsa=new ArrayList<ShoppingAddress>();
		String sql="SELECT * FROM  wxpt"+enterId+".shopping_address WHERE `member_id`="+mid;
		try {
			lsa=this.sessionfactory.getCurrentSession().createSQLQuery(sql).addEntity(ShoppingAddress.class).list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lsa;
	}
	@Override
	public List<Logistics> getll(int enterId) {
		// TODO Auto-generated method stub
		lll=new ArrayList<Logistics>();
		String sql="SELECT * FROM wxpt"+enterId+".logistics";
		try {
			lll=this.sessionfactory.getCurrentSession().createSQLQuery(sql).addEntity(Logistics.class).list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lll;
	}
	@Override
	public int getChangeShoppingAddress(int enterId,int lid, String name, String address,
			String phone) {
		// TODO Auto-generated method stub
		int i=0;
		String sql="UPDATE wxpt"+enterId+".`shopping_address` SET `name`='"+name+"',`address`='"+address+"',`phone`='"+phone+"' WHERE `shopping_address_id`="+lid;
		session=(Session) this.sessionfactory.getCurrentSession();
		Query query = session.createSQLQuery(sql);
		i=query.executeUpdate();
		return i;
	}
	@Override
	public void getDeleteAddress(int enterId,int lid) {
		// TODO Auto-generated method stub
		String sql="DELETE FROM wxpt"+enterId+".`shopping_address` WHERE `shopping_address_id`="+lid;
		try {
			this.sessionfactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void saveAddress(int enterId,int mid, String name, String address, String phone) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO wxpt"+enterId+".`shopping_address`(`member_id`, `name`, `address`, `phone`) VALUES ("+mid+",'"+name+"','"+address+"','"+phone+"')";
		try {
			session=(Session) this.sessionfactory.getCurrentSession();
			session.beginTransaction();
			session.connection().createStatement().executeUpdate(sql);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public ShoppingAddress getOneShoppingAddress(int enterId,int id) {
		// TODO Auto-generated method stub
		sa=new ShoppingAddress();
		String sql="SELECT * FROM wxpt"+enterId+".`shopping_address` WHERE `shopping_address_id`="+id;
		try {
			sa=(ShoppingAddress) this.sessionfactory.getCurrentSession().createSQLQuery(sql).addEntity(ShoppingAddress.class).list().get(0);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sa;
	}
	@Override
	public void saveOrder(int enterId,String product_id, String orderno, String savetime,
			Double money, int paytype, String name, String address,
			String phone, int sendtype, int logisticsId, int mid, String leave) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO wxpt"+enterId+".`order`( `order_product_id`, `order_num`, `order_time`, `pay_money`, `pay_type`, `receive_person`, `receive_address`, `receive_phone`, `send_type`, `Logistics_id`, `member_id`, `order_leave`) " +
				"VALUES ('"+product_id+"','"+orderno+"','"+savetime+"',"+money+","+paytype+",'"+name+"','"+address+"','"+phone+"',"+sendtype+","+logisticsId+","+mid+",'"+leave+"')";
		try {
			session=(Session) this.sessionfactory.getCurrentSession();
			session.beginTransaction();
			session.connection().createStatement().executeUpdate(sql);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public int getChangeCar(int enterId,int mid) {
		// TODO Auto-generated method stub
		int i=0;
		String sql="UPDATE wxpt"+enterId+".`shopping_car` SET `member_id`="+mid+" WHERE `shopping_car_type`=1";
		try {
			session=(Session) this.sessionfactory.getCurrentSession();
			Query query = session.createSQLQuery(sql);
			i=query.executeUpdate();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	@Override
	public List<Order> getAllNewOrder(int enterId,int mid) {
		// TODO Auto-generated method stub
		lorder=new ArrayList<Order>();
		String sql="SELECT * FROM wxpt"+enterId+".`order` WHERE `member_id`="+mid+" and `pay_type`=0 and `order_type`=0";
		System.out.println(sql);
		try {
			lorder=this.sessionfactory.getCurrentSession().createSQLQuery(sql).addEntity(Order.class).list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lorder;
	}
	@Override
	public List<ShoppingCar> getListShoppingCar(int enterId,int mid, int sid) {
		// TODO Auto-generated method stub
		listshop=new ArrayList<ShoppingCar>();
		String sql="SELECT * FROM wxpt"+enterId+".`shopping_car` WHERE `member_id`="+mid+" and `product_id`="+sid;
		try {
			listshop=this.sessionfactory.getCurrentSession().createSQLQuery(sql).addEntity(ShoppingCar.class).list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listshop;
	}
	@Override
	public Order getOrder(int enterId,String no) {
		// TODO Auto-generated method stub
		or=new Order();
		String sql="SELECT * FROM wxpt"+enterId+".`order` WHERE `order_num`='"+no+"'";
		try {
			or=(Order) this.sessionfactory.getCurrentSession().createSQLQuery(sql).addEntity(Order.class).list().get(0);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return or;
	}
	@Override
	public void getSaveBuy(int enterId,int sid, int oid, int sum) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO wxpt"+enterId+".`buy_product`(`buy_product_order`, `buy_product_product`, `buy_product_sum`) VALUES ("+oid+","+sid+","+sum+")";
		try {
			session=(Session) this.sessionfactory.getCurrentSession();
			session.beginTransaction();
			session.connection().createStatement().executeUpdate(sql);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void getDeleteShopping(int enterId,int mid) {
		// TODO Auto-generated method stub
		String sql="DELETE FROM wxpt"+enterId+".`shopping_car` WHERE `member_id`="+mid;
		try {
			this.sessionfactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public List<BuyProduct> getOneByOrderId(int enterId,int id) {
		// TODO Auto-generated method stub
		lbp=new ArrayList<BuyProduct>();
		String sql="SELECT * FROM wxpt"+enterId+".`buy_product` WHERE `buy_product_order`="+id;
	     System.out.println(sql);
		try {
			lbp= this.sessionfactory.getCurrentSession().createSQLQuery(sql).addEntity(BuyProduct.class).list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lbp;
	}
	@Override
	public List<Order> getAllSendOrder(int enterId,int mid) {
		// TODO Auto-generated method stub
		lorder=new ArrayList<Order>();
		String sql="SELECT * FROM wxpt"+enterId+".`order` WHERE `member_id`="+mid+" and `pay_type`=1 and `send_type`=0";
		System.out.println(sql);
		try {
			lorder=this.sessionfactory.getCurrentSession().createSQLQuery(sql).addEntity(Order.class).list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lorder;
	}
	@Override
	public List<Order> getAllTakeOrder(int enterId,int mid) {
		// TODO Auto-generated method stub
		lorder=new ArrayList<Order>();
		String sql="SELECT * FROM wxpt"+enterId+".`order` WHERE `member_id`="+mid+" and `pay_type`=1 and `send_type`=1 and `take_type`=0";
		try {
			lorder=this.sessionfactory.getCurrentSession().createSQLQuery(sql).addEntity(Order.class).list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lorder;
	}
	@Override
	public List<Order> getAllOverOrder(int enterId,int mid) {
		// TODO Auto-generated method stub
		lorder=new ArrayList<Order>();
		String sql="SELECT * FROM wxpt"+enterId+".`order` WHERE `member_id`="+mid+" and `pay_type`=1 and `send_type`=1 and `take_type`=1";
		try {
			lorder=this.sessionfactory.getCurrentSession().createSQLQuery(sql).addEntity(Order.class).list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lorder;
	}
	@Override
	public List<Order> getAllNewOrderFen(int enterId,int mid, int start, int number) {
		// TODO Auto-generated method stub
		lorder=new ArrayList<Order>();
		String sql="SELECT * FROM wxpt"+enterId+".`order` WHERE `member_id`="+mid+" and `pay_type`=0 and `order_type`=0";
		try {
			Query query=this.sessionfactory.getCurrentSession().createSQLQuery(sql).addEntity(Order.class);
			query.setFirstResult(start);
			query.setMaxResults(number);
			lorder = query.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lorder;
	}
	@Override
	public List<Order> getAllSendOrderFen(int enterId,int mid, int start, int number) {
		// TODO Auto-generated method stub
		lorder=new ArrayList<Order>();
		String sql="SELECT * FROM wxpt"+enterId+".`order` WHERE `member_id`="+mid+" and `pay_type`=1 and `send_type`=0";
		try {
			Query query=this.sessionfactory.getCurrentSession().createSQLQuery(sql).addEntity(Order.class);
			query.setFirstResult(start);
			query.setMaxResults(number);
			lorder = query.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lorder;
	}
	@Override
	public List<Order> getAlltakeOrderFen(int enterId,int mid, int start, int number) {
		// TODO Auto-generated method stub
		lorder=new ArrayList<Order>();
		String sql="SELECT * FROM wxpt"+enterId+".`order` WHERE `member_id`="+mid+" and `pay_type`=1 and `send_type`=1 and `take_type`=0";
		try {
			Query query=this.sessionfactory.getCurrentSession().createSQLQuery(sql).addEntity(Order.class);
			query.setFirstResult(start);
			query.setMaxResults(number);
			lorder = query.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lorder;
	}
	@Override
	public List<Order> getAllOverOrderFen(int enterId,int mid, int start, int number) {
		// TODO Auto-generated method stub
		lorder=new ArrayList<Order>();
		String sql="SELECT * FROM wxpt"+enterId+".`order` WHERE `member_id`="+mid+" and `pay_type`=1 and `send_type`=1 and `take_type`=1";
		try {
			Query query=this.sessionfactory.getCurrentSession().createSQLQuery(sql).addEntity(Order.class);
			query.setFirstResult(start);
			query.setMaxResults(number);
			lorder = query.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lorder;
	}
	@Override
	public int getCannelOrder(int enterId,String no, String value) {
		// TODO Auto-generated method stub
		int i=0;
		String sql="UPDATE wxpt"+enterId+".`order` SET `order_type`=1,`order_remark`='"+value+"' WHERE `order_num`='"+no+"'";
		try {
			session=(Session) this.sessionfactory.getCurrentSession();
			Query query = session.createSQLQuery(sql);
			i=query.executeUpdate();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	@SuppressWarnings("unchecked")
	@Override
	public EnterInfor getOneEnterinfor(int enterId) {
		// TODO Auto-generated method stub
		enter=new EnterInfor();
		String sql="SELECT * FROM webchat.enter_infor WHERE `enter_infor_id`="+enterId;
		try {
			enterList=  this.sessionfactory.getCurrentSession().createSQLQuery(sql).addEntity(EnterInfor.class).list();
			if(enterList.size()==0){
				return null;
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return enterList.get(0);
	}
	@Override
	public int getChangeEnterOrder(int enterId,String no) {
		// TODO Auto-generated method stub
		int i=0;
		String sql="UPDATE wxpt"+enterId+".`order` SET `take_type`=1 WHERE `order_num`='"+no+"'";
		try {
			session=(Session) this.sessionfactory.getCurrentSession();
			Query query = session.createSQLQuery(sql);
			i=query.executeUpdate();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	@Override
	public int getChangeProduct(int enterId,int id, int sell, int sum) {
		// TODO Auto-generated method stub
		int i=0;
		String sql="UPDATE wxpt"+enterId+".`product` SET `inventory_num`="+sell+",`sell_num`="+sum+" WHERE `product_id`="+id;
		try {
			session=(Session) this.sessionfactory.getCurrentSession();
			Query query = session.createSQLQuery(sql);
			i=query.executeUpdate();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	@Override
	public int getChangeShoppingCar(int enterId,int id, int pid, int sum) {
		// TODO Auto-generated method stub
		int i=0;
		String sql="UPDATE wxpt"+enterId+".shopping_car SET `product_sum`="+sum+" WHERE `member_id`="+id+" and `product_id`="+pid;
		try {
			session=(Session) this.sessionfactory.getCurrentSession();
			Query query = session.createSQLQuery(sql);
			i=query.executeUpdate();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	@Override
	public List<Order> getAllOrder(int enterId,int payType) {
		// TODO Auto-generated method stub
		lorder=new ArrayList<Order>();
		String sql="SELECT * FROM wxpt"+enterId+".`order` WHERE  `pay_type`="+payType;
		try {
			lorder=this.sessionfactory.getCurrentSession().createSQLQuery(sql).addEntity(Order.class).list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lorder;
	}
	@Override
	public List<Order> getAllOrders(int enterId,int takeType, int sendType) {
		// TODO Auto-generated method stub
		lorder=new ArrayList<Order>();
		String sql="SELECT * FROM wxpt"+enterId+".`order` WHERE `send_type`="+sendType+" and `take_type`="+takeType;
		try {
			lorder=this.sessionfactory.getCurrentSession().createSQLQuery(sql).addEntity(Order.class).list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lorder;
	}
	@Override
	public int updateOrder(int enterId,int orderId,int takeType) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		int i=0;
		String sql="UPDATE wxpt"+enterId+".`order` SET `take_type`=1 WHERE `order_num`='"+orderId+"'";
		try {
			session=(Session) this.sessionfactory.getCurrentSession();
			Query query = session.createSQLQuery(sql);
			i=query.executeUpdate();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	@Override
	public int delOrder(String hql) {
		// TODO Auto-generated method stub
		int i = 0;
		try {
			i = this.sessionfactory.getCurrentSession().createQuery(hql).executeUpdate();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	@Override
	public int updateOrderType(String hql) {
		// TODO Auto-generated method stub
		int i=0;
		try {
			session=(Session) this.sessionfactory.getCurrentSession();
			Query query = session.createSQLQuery(hql);
			i=query.executeUpdate();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	@Override
	public void getDeleteOrder(int enterId,String no) {
		// TODO Auto-generated method stub
		String sql="DELETE FROM wxpt"+enterId+".`order` WHERE `order_num`='"+no+"'";
		try {
			this.sessionfactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public Appraise getOneAppraise(int enterId,int mid, int sid, int id) {
		// TODO Auto-generated method stub
		app=new ArrayList<Appraise>();
		String sql="SELECT * FROM wxpt"+enterId+".`appraise` WHERE `member_id`="+mid+" and `product_id`="+sid+" and `order_id`="+id;
		try {
			app=  this.sessionfactory.getCurrentSession().createSQLQuery(sql).addEntity(Appraise.class).list();
			if(app.size()==0){
				return null;
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return app.get(0);
	}
	@Override
	public int getAllShopping(int enterId,int mid) {
		ArrayList<ShoppingCar>	shopcartlist=new ArrayList<ShoppingCar>();
		// TODO Auto-generated method stub
		int size=0;
		String sql="SELECT * FROM wxpt"+enterId+".`shopping_car` WHERE `member_id`="+mid;
		shopcartlist=(ArrayList<ShoppingCar>) this.sessionfactory.getCurrentSession().createSQLQuery(sql).addEntity(ShoppingCar.class).list();
		if(shopcartlist.size()!=0){
			size=shopcartlist.size();
			return size;
		}
		
			return size;
		
		
	}
	@Override
	public Logistics getOneLogistics(int enterId,int id) {
		// TODO Auto-generated method stub
		lll=new ArrayList<Logistics>();
		String sql="SELECT * FROM wxpt"+enterId+".`logistics` WHERE `logistics_id`="+id;
		try {
			lll=this.sessionfactory.getCurrentSession().createSQLQuery(sql).addEntity(Logistics.class).list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(lll.size()==0){
			return null;
		}
		return lll.get(0);
	}
	@Override
	public Order getLastOne(int enterId,int mid) {
		// TODO Auto-generated method stub
		or=new Order();
		String sql="SELECT * FROM wxpt"+enterId+".`order` WHERE `order_id`in(SELECT max(`order_id`) FROM `order` WHERE `member_id`="+mid+" and `pay_type`=0)";
		try {
			or=(Order) this.sessionfactory.getCurrentSession().createSQLQuery(sql).addEntity(Order.class).list().get(0);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return or;
	}

}
