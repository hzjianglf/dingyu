import rtx.RTXSvrApi;   
public class DeptIsExist
 {
	
    public static void main(String[] args) {

    	String deptId= "2";
    	
    	int iRet = -1;
    	
    	RTXSvrApi  RtxsvrapiObj = new RTXSvrApi();        		
    	if( RtxsvrapiObj.Init())
    	{   
    		
    		iRet = RtxsvrapiObj.deptIsExist(deptId);
    		
    		if (iRet == 0)
    		{
    			System.out.println("部门存在");
    			
    		}
    		else 
    		{
    			System.out.println("部门不存在");
    		}
    		

	    }	
    	RtxsvrapiObj.UnInit();
    	
    }
}
