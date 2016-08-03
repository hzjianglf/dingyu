package test;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sun.xml.bind.v2.schemagen.xmlschema.List;
import com.ticket.master.dao.FlightDatasDao;
import com.ticket.master.dao.impl.FlightDatasDaoImpl;
import com.ticket.master.entity.HibernateSessionFactory;
import com.ticket.master.entity.User;

public class JsonTest {

	static String jsonStr="{\"students\":[{\"name\":jackson,\"age\":100}, {\"name\":michael,\"age\":51}]}";
	static String str="{\"Res\": { \"Status\": 0, \"FlightDatas\":" +
			" [{ \"DepDate\": 2014-01-26,\"DepCity\":WUH," +
					"\"ArrCity\": PEK, \"DepTime\": 14:30," +
					"\"ArrTime\": 16:25, \"FlightNo\": CA1476," +
					" \"CarrFlightNo\": null,\"Airways\": CA," +
					"\"CabinDatas\": [{ \"Cabin\": V1, \"CabName\": 经济舱," +
					" \"CabType\": 0,\"TToSeatNum\": null," +
					"\"TRewRates\": null,\"PPolRewRates\": 0%}]}]}}";
	
//	public static void main(String[] args) {
//		Students person = new Students();
//        try
//        {// 将json字符串转换为json对象
//        	System.out.println(str);
//        	str=str.replaceAll("null","0");
//        	System.out.println(str);
//        	System.out.println(jsonStr);
//           // JSONObject jsonObj = new JSONObject(str);
//            JSONObject jsonObj2 = new JSONObject(jsonStr);
//            // 得到指定json key对象的value对象
//            JSONArray personList2 = jsonObj2.getJSONArray("students");
//          //  JSONArray personList = jsonObj.getJSONArray("FlightDatas");
//          
//            // 获取之对象的所有属性
//            for (int i = 0; i < personList2.length(); i++)
//            {
//                // 获取每一个json对象
//                JSONObject jsonItem = personList2.getJSONObject(i);
//                // 获取每一个json对象的值
//                System.out.println(jsonItem.getString("name"));
//                System.out.println(jsonItem.getString("age"));
//            }
//            
//            
//            
//           
//        }
//        catch (JSONException e)
//        {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//	}
	
public static void main(String[] args) {
	FlightDatasDao fd=new FlightDatasDaoImpl() ;
	User user=new User();
	user.setPassword("asdf2");
	user.setUsername("sdf2");
	user.setUserId(2);
	
	//fd.saveFlightDatas(user);
  }
}
    

