package lmd.extend.jicheng.dao;
 
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;

import lmd.extend.jicheng.util.Dom4j;
import tebie.applib.api.APIContext;
import tebie.applib.api.IClient;

public class DaoImp {

	APIContext ret;
    IClient cli;
    String sid="";
    String providerId = "1";
    String orgId = "a"; 
    String domainName = "sddlr.gov.cn";
       
    public ArrayList<Map<String, String>> getNewEmail(String userId)  throws Exception {
    	
    	ArrayList<Map<String, String>> array = new ArrayList<Map<String,String>>();

        String userEmail = userId + '@' + domainName; 
        Socket socket = new Socket("10.10.10.6", 6195);
        cli = APIContext.getClient(socket);
        
        try { 
        	ret = cli.getNewMailInfos(userEmail,"limit=10&format=xml");
        	if (ret.getRetCode() == 0) {

                sid = ret.getResult(); 
                String[] sysId = {"root","mail","mid","msid","fid","flag","from","to","subject","size","date"}; 
                array = new Dom4j().test(sid, sysId);
                

            }else{
            } 
        }catch (Exception e) {
			e.printStackTrace();
		} finally {
            cli.close();
        }

    	
    	
		return array;

    	

	}
	public int getNum(String userId)  throws Exception {
		
		int emailNum = 0;
		
  
        String userEmail = userId + '@' + domainName; 
        Socket socket = new Socket("10.10.10.6", 6195);
        cli = APIContext.getClient(socket);
        
        try { 
        	ret = cli.getAttrs(userEmail, "mbox_newmsgcnt"); 
        	if (ret.getRetCode() == 0) {

                sid = ret.getResult(); 
                sid = sid.split("=")[1];
                

            }else{
            	//ƽ̨����ҳ��
            } 
        }catch (Exception e) {
			e.printStackTrace();
		} finally {
            cli.close();
        }

		return Integer.parseInt(sid);

	}
		
	public String getUrlName( String userId) throws Exception {
		 
	        String userEmail = userId + '@' + domainName; 
	        Socket socket = new Socket("10.10.10.6", 6195);
	        cli = APIContext.getClient(socket);
	         
	        try { 
	        	ret = cli.userLogin(userEmail);  
	        	if (ret.getRetCode() == 0) {

	                sid = ret.getResult(); 
 
	            }else{
	            	//ƽ̨����ҳ��
	            } 
	        }catch (Exception e) {
				e.printStackTrace();
			} finally {
	            cli.close();
	        }
 
			return sid;
	}
}
