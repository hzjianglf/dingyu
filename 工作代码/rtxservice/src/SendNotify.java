import rtx.RTXSvrApi;   
public class SendNotify
 {
	//2007测试成功
    public static void main(String[] args) {

    	String receivers= "lisi,zhangsan";
    	String title = "测试";
    	String msg = "消息提醒测试,[腾讯|http://www.qq.com]";
    	String type = "1";
    	String delayTime = "5000";
    	
    	int iRet= -1;
    	RTXSvrApi  RtxsvrapiObj = new RTXSvrApi();        		
    	if( RtxsvrapiObj.Init())
    	{   
    		iRet = RtxsvrapiObj.sendNotify(receivers,title,msg, type,delayTime);
    		if (iRet == 0)
    		{
    			System.out.println("发送成功");
    			
    		}
    		else 
    		{
    			System.out.println("发送失败");
    		}

    	}
    	RtxsvrapiObj.UnInit();
    	
    }
}
