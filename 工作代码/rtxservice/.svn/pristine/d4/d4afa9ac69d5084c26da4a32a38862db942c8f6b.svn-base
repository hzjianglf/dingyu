import rtx.RTXSvrApi;   
public class AddUser
 {
	//2007测试成功
    public static void main(String[] args) {

    	String userName = "dengtao";
    	String deptID = "1";
    	String chsName = "梁xx";
    	String pwd = "123";
    	
    	for (int i=0;i<10000;i++){
    		int iRet = -1;
        	RTXSvrApi  RtxsvrapiObj = new RTXSvrApi();        		
        	if( RtxsvrapiObj.Init())
        	{   
        		iRet =RtxsvrapiObj.addUser(userName+i,deptID,chsName,pwd);
        		if (iRet==0)
        		{
        			System.out.println("添加"+userName+i+"成功");
        		}
        		else 
        		{
        			System.out.println("添加"+userName+i+"失败");
        		}

    	    }	
        	RtxsvrapiObj.UnInit();
    	}
    	
    }
}
