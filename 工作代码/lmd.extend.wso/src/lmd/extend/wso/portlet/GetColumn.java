package lmd.extend.wso.portlet;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import net.risesoft.soa.framework.service.config.Config;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
public class GetColumn {
	private static List list = new ArrayList();
    public List getColumn(String  rootid,String userid){
    	;
    	String url_str = getSystemURI()+"/portlecontrol/getcolumn.do?id="+rootid+"&userid="+userid;
        String columns = opration(url_str);           
//    	String  columns= "{id:'001',text:'���ܽڵ�',url:'',leaf:false,children:[{id:'_m10kUHktEeWSKZJrYJwJxg',text:'��������',url:'null',openType:'null',leaf:false,children:[{id:'_vdngkHktEeWSKZJrYJwJxg',text:'�������',url:'http://10.10.10.207:8090/MessageBroker/login.jsp?tourl=selectfortopology',openType:'panel',leaf:true},{id:'_xH_XsHktEeWSKZJrYJwJxg',text:'��������',url:'/asf/bundles/index.html',openType:'panel',leaf:true},{id:'_0fHrkHktEeWSKZJrYJwJxg',text:'Ӧ�ù���',url:'null',openType:'panel',leaf:false,children:[{id:'_5DAqIH3mEeWy_coA49CJXw',text:'����',url:'/xtlist.html?id=_0WniwHuREeW2-cfpjsMtnw',openType:'panel',leaf:true},{id:'_4d2SIH3nEeWy_coA49CJXw',text:'����',url:'/xtlist.html?id=_dtW24HuREeW2-cfpjsMtnw',openType:'panel',leaf:true},{id:'_ESXCAH3oEeWy_coA49CJXw',text:'�ۺϹ�������',url:'/xtlist.html?id=_H2BYYHurEeW2-cfpjsMtnw',openType:'panel',leaf:true},{id:'_A0ZGUH3oEeWy_coA49CJXw',text:'����',url:'/xtlist.html?id=_CV3igHurEeW2-cfpjsMtnw',openType:'panel',leaf:true},{id:'_9QoEoH3nEeWy_coA49CJXw',text:'���ʻ���',url:'/xtlist.html?id=_LHkosHurEeW2-cfpjsMtnw',openType:'panel',leaf:true}]},{id:'_Bl57oHkuEeWSKZJrYJwJxg',text:'������־',url:'/asf/ssoinfo/loginLogs.do',openType:'panel',leaf:true},{id:'_5hW2YHktEeWSKZJrYJwJxg',text:'ͳ�Ʒ���',url:'/falsh/Pie3D2.html',openType:'panel',leaf:true},{id:'_8MdKsHktEeWSKZJrYJwJxg',text:'����ע��',url:'null',openType:'panel',leaf:true},{id:'__5cZ0HktEeWSKZJrYJwJxg',text:'��������',url:'null',openType:'panel',leaf:true},{id:'_3mxZAHktEeWSKZJrYJwJxg',text:'���м��',url:'/asf/monitor/index.html',openType:'panel',leaf:true}]},{id:'_qqGuQHktEeWSKZJrYJwJxg',text:'��������',url:'null',openType:'null',leaf:false,children:[{id:'_4LZ6oHkuEeWSKZJrYJwJxg',text:'�������',url:'http://10.10.10.207:8000/sso/jump.jsp?appId=_CMbRkA6dEeWR6qpHmUmPoQ',openType:'panel',leaf:true},{id:'_z2YbIHkuEeWSKZJrYJwJxg',text:'��Ŀ���ȹ���',url:'http://10.10.10.207:8000/sso/jump.jsp?appId=_E9mYsA6dEeWR6qpHmUmPoQ',openType:'panel',leaf:true}]},{id:'_odXJ4HktEeWSKZJrYJwJxg',text:'��Դ����',url:'null',openType:'null',leaf:false,children:[{id:'_XWkyoHkuEeWSKZJrYJwJxg',text:'��Դͳ��',url:'null',openType:'panel',leaf:true},{id:'_YwbhgHkuEeWSKZJrYJwJxg',text:'��Դ��ѯ',url:'null',openType:'panel',leaf:true},{id:'_bbXdwHkuEeWSKZJrYJwJxg',text:'��֯������Դ',url:'/org/indexnew.jsp',openType:'panel',leaf:true},{id:'_gFqpsHkuEeWSKZJrYJwJxg',text:'������Դ',url:'/jsp/wsoindex.jsp?id=_08o0UQ6NEeWobedMQdjMkA',openType:'panel',leaf:true},{id:'_v03GcHkuEeWSKZJrYJwJxg',text:'������Դ',url:'/asf/bundles/index.html',openType:'panel',leaf:true},{id:'_lLLK8HkuEeWSKZJrYJwJxg',text:'������Դ',url:'/dbexplorer/',openType:'panel',leaf:true},{id:'_mip7QHkuEeWSKZJrYJwJxg',text:'��׼��Դ',url:'/filecube/',openType:'panel',leaf:true},{id:'_p2NWsHkuEeWSKZJrYJwJxg',text:'�Ż���Դ',url:'null',openType:'panel',leaf:true},{id:'_r3f5AHkuEeWSKZJrYJwJxg',text:'ҵ����Դ',url:'null',openType:'panel',leaf:true},{id:'_hTnqkHkuEeWSKZJrYJwJxg',text:'Ӧ��ϵͳ��Դ',url:'null',openType:'panel',leaf:true}]}]}";//this.opration("http://10.10.10.207:8000/operlist.do?id=_ZQ1VUXktEeWSKZJrYJwJxg");   	
//    	System.out.println(">>>>>>>>>columnscolumnscolumns>>>>>>>>>>"+columns);
        String [] aa = columns.split("\\}\\{");    	
		for(int i=0;i<aa.length;i++){
			if(i==0){
				columns=aa[i]+"}";		
				Map map=new HashMap();
				JsonConfig jc=new JsonConfig();
				jc.setClassMap(map);
				jc.setRootClass(Map.class);
				jc.setArrayMode(JsonConfig.MODE_LIST);
				JSONObject jobj=JSONObject.fromObject(columns,jc);				
				decodeJSONObject(jobj);		
			}else if(i==aa.length-1){
				columns="{"+aa[i];					
				Map map=new HashMap();
				JsonConfig jc=new JsonConfig();
				jc.setClassMap(map);
				jc.setRootClass(Map.class);
				jc.setArrayMode(JsonConfig.MODE_LIST);
				JSONObject jobj=JSONObject.fromObject(columns,jc);			
				decodeJSONObject(jobj);
			}else{
				columns="{"+aa[i]+"}";
				Map map=new HashMap();
				JsonConfig jc=new JsonConfig();
				jc.setClassMap(map);
				jc.setRootClass(Map.class);
				jc.setArrayMode(JsonConfig.MODE_LIST);
				JSONObject jobj=JSONObject.fromObject(columns,jc);
				decodeJSONObject(jobj);
			}
		}   	
    	return list;
    }
	public static  void decodeJSONObject(JSONObject json){
		Iterator<String> keys=json.keys();
		JSONObject jo=null;
		Object o;
		String key;
		Map onemap = new HashMap();
		while(keys.hasNext()){
			key=keys.next();
			o=json.get(key);			
			onemap.put(key, o);
		}
		list.add(onemap);
	}
	//��ȡƽ̨�����ļ��е�system.uri����ֵ
    public String getSystemURI(){// add by sunming 20150924
       URL sysConfigUrl = Config.getConfigFileAsURL("/META-INF/config/system.properties");
       String systemuri="";
       if (sysConfigUrl != null) {
            Properties prop = new Properties();
            try {
               prop.load(sysConfigUrl.openStream());
            } catch (Exception ex) {
               System.err.println("IO Error: " + ex.getMessage());
            }
            Properties directProp = System.getProperties();
            systemuri=directProp.getProperty("system.uri");
        }       
	    return systemuri;
	}
  //���ڲ�����action
    public String opration(String url_str){// add by sunming 20150924
  	   String returnmess="";
       try {
  			URL url = new URL(url_str);
  			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
  			connection.setRequestMethod("POST");
  			connection.connect();
  			int code = connection.getResponseCode();
  			if (code == 404) {
  			    throw new Exception("��֤��Ч���Ҳ����˴���֤�ĻỰ��Ϣ��");
  			}
  			if (code == 500) {
  			    throw new Exception("��֤�����������ڲ�����");
  			}
  			if (code != 200) {
  			    throw new Exception("��������������֤���������� " + code);
  			}				
  	        InputStream is = connection.getInputStream();
  	        byte[] response = new byte[is.available()];
  	        is.read(response);
  	        is.close();
  	        if (response == null || response.length == 0) {
  	            throw new Exception("��֤��Ч���Ҳ����˴���֤�ĻỰ��Ϣ��");
  	        }
  	        returnmess = new String(response, "UTF-8");
  		} catch (Exception e) {
  			e.printStackTrace();
  		}
        return returnmess;
     }
     public static void main(String [] args){
    	 GetColumn  aa = new GetColumn();
     }
}
