package com.ticket.master.common;

import com.ticket.master.dao.impl.FlightDiscountDaoImpl;
import com.ticket.master.entity.FlightDiscount;

public class FlightDiscountDiscount {
	FlightDiscountDaoImpl flightDiscountDaoImpl=new FlightDiscountDaoImpl();
	public String  getCondition(String DepartCity,String ArrivalCity,String Airways,String Cabin,String DepartDate){
		FlightDiscount flightDiscount=new FlightDiscount();
		/**
		 * 1)符合所有条件
		 * */
		String sql0="SELECT * FROM `flight_discount` WHERE  '"+DepartDate+"' between STR_TO_DATE(`start_time`,'%Y-%m-%d') and  STR_TO_DATE(`end_time`,'%Y-%m-%d') and `start_city` like '%("+DepartCity+")%' and `end_city` like '%("+ArrivalCity+")%'   and `cabin`='"+Cabin+"' and `airway` like '%"+Airways+"%'";
		flightDiscount=flightDiscountDaoImpl.getDiscount(sql0);
		if(flightDiscount!=null){
			return  flightDiscount.getDiscount();
		}else{
			/**
			 * 2)判断出发与到达城市
			 * */
			String sql1="SELECT * FROM `flight_discount` WHERE `start_city` like '%("+DepartCity+")%' and `end_city` like '%("+ArrivalCity+")%'";
			flightDiscount=flightDiscountDaoImpl.getDiscount(sql1);
			if(flightDiscount!=null){
				/**
				 * 判断时间段*/
				String sql3=" and  '"+DepartDate+"' between STR_TO_DATE(`start_time`,'%Y-%m-%d') and  STR_TO_DATE(`end_time`,'%Y-%m-%d')";
				flightDiscount=flightDiscountDaoImpl.getDiscount(sql1+sql3);
				if(flightDiscount!=null){
					/**
					 * 判断仓*/
					sql3+=" and `cabin`='"+Cabin+"'";
					flightDiscount=flightDiscountDaoImpl.getDiscount(sql1+sql3);
					if(flightDiscount!=null){
						sql1+=" and `airway` like '%"+Airways+"%'";
						flightDiscount=flightDiscountDaoImpl.getDiscount(sql1+sql3);
						if(flightDiscount!=null){
							return flightDiscount.getDiscount();
						}else{
							return null;
						}
					}else{
						String sql5=" and `airway` like '%"+Airways+"%'";
						flightDiscount=flightDiscountDaoImpl.getDiscount(sql1+sql5);
						if(flightDiscount!=null){
							return flightDiscount.getDiscount();
						}else{
							return null;
						}
					}
				}else{
					String sql4=" and `cabin`='"+Cabin+"'";
					flightDiscount=flightDiscountDaoImpl.getDiscount(sql1+sql4);
					if(flightDiscount!=null){
					sql4+=" and `airway` like '%"+Airways+"%'";	
					flightDiscount=flightDiscountDaoImpl.getDiscount(sql1+sql4);
					if(flightDiscount!=null){
						return flightDiscount.getDiscount();
					}else{
						return null;
					}
					}else{
						String sql5=" and `airway` like '%"+Airways+"%'";
						flightDiscount=flightDiscountDaoImpl.getDiscount(sql1+sql5);
						if(flightDiscount!=null){
							return flightDiscount.getDiscount();
						}else{
							return null;
						}
					}
				}
			}else{
				/**
				 * 不在起始城市   查询时间段*/
				String sql2="SELECT * FROM `flight_discount` WHERE  '"+DepartDate+"' between STR_TO_DATE(`start_time`,'%Y-%m-%d') and  STR_TO_DATE(`end_time`,'%Y-%m-%d')";
				flightDiscount=flightDiscountDaoImpl.getDiscount(sql2);
				if(flightDiscount!=null){
					String sql10=" and `cabin`='"+Cabin+"'";
					flightDiscount=flightDiscountDaoImpl.getDiscount(sql2+sql10);
					if(flightDiscount!=null){
						sql10+=" and `airway` like '%"+Airways+"%'";
						flightDiscount=flightDiscountDaoImpl.getDiscount(sql10);
						if(flightDiscount!=null){
							return flightDiscount.getDiscount();
						}else{
							return null;
						}
					}else{
						String sql11="  and `airway` like '%"+Airways+"%'";
						flightDiscount=flightDiscountDaoImpl.getDiscount(sql2+sql11);
						if(flightDiscount!=null){
							return flightDiscount.getDiscount();
						}else{
							return null;
						}
					}
				}else{
					String sql6=" SELECT * FROM `flight_discount` WHERE  `cabin`='"+Cabin+"'";
					flightDiscount=flightDiscountDaoImpl.getDiscount(sql6);
					if(flightDiscount!=null){
						sql6+=" and `airway` like '%"+Airways+"%'";
						flightDiscount=flightDiscountDaoImpl.getDiscount(sql6);
						if(flightDiscount!=null){
							return flightDiscount.getDiscount();
						}else{
							return null;
						}
					}else{
						String sql7="SELECT * FROM `flight_discount` WHERE `airway` like '%"+Airways+"%'";
						flightDiscount=flightDiscountDaoImpl.getDiscount(sql7);
						if(flightDiscount!=null){
							return flightDiscount.getDiscount();
						}else{
							return null;
						}
					}
				}
			}
		}
	}
/*public String gets(String DepartCity,String ArrivalCity,String Airways,String Cabin,String DepartDate){
	FlightDiscount flightDiscount=new FlightDiscount();
	if(DepartCity!=null&&DepartCity.equals("")==false&&ArrivalCity!=null&&ArrivalCity.equals("")==false&&Airways!=null&&Airways.equals("")==false&&Cabin!=null&&Cabin.equals("")==false&&DepartDate.equals("")==false&&DepartDate!=null){
		String sql0="SELECT * FROM `flight_discount` WHERE  '"+DepartDate+"' between STR_TO_DATE(`start_time`,'%Y-%m-%d') and  STR_TO_DATE(`end_time`,'%Y-%m-%d') and `start_city` like '%("+DepartCity+")%' and `end_city` like '%("+ArrivalCity+")%'   and `cabin`='"+Cabin+"' and `airway` like '%"+Airways+"%'";
		flightDiscount=flightDiscountDaoImpl.getDiscount(sql0);
		if(flightDiscount!=null){
			return  flightDiscount.getDiscount();
		}else{
			return  null;
		}
	}
	
	
	
	
	
	return sql0;
}*/
}
