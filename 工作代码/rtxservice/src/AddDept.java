import rtx.RTXSvrApi;   
public class AddDept
 {
	//2007测试通过,添加成功
    public static void main(String[] args) {

    	String deptId= "1";
    	deptId="kfjkdsajflsdjlgjisgriolkgd";
    	String DetpInfo = "测试T0001";
    	String DeptName = "T15";
    	String ParentDeptId = "";
    	
    	int iRet = -1;
    	
    	RTXSvrApi  RtxsvrapiObj = new RTXSvrApi();        		
    	if( RtxsvrapiObj.Init())
    	{   
    		iRet = RtxsvrapiObj.addDept(deptId,DetpInfo,DeptName,ParentDeptId);
    		System.out.println(deptId+" "+DetpInfo+"  "+DeptName+"  "+ParentDeptId);
    		if (iRet == 0)
    		{
    			System.out.println("添加部门成功");
    			
    		}
    		else 
    		{
    			System.out.println("添加部门失败");
    		}

	    }	
    	RtxsvrapiObj.UnInit();
    	
    }
}
