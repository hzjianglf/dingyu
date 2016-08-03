package test;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class RobotDemo
{
    private Robot robot = null;
    
    public RobotDemo()
    {
        try
        {
            robot = new Robot();
        }
        catch (AWTException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    //模拟Ctrl+Alt+Z的按下和抬起
    public void keyBoardDemo()
    {
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ADD);
//        robot.keyPress(KeyEvent.VK_Z);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_ADD);
//        robot.keyRelease(KeyEvent.VK_Z);
    }
    
    public static void main(String[] args) throws InterruptedException
    {
    	for(int i=0;i<100;i++){
    		Thread t = new Thread();
    		//t.sleep(1000);
    		System.out.println(i);
    		RobotDemo demo = new RobotDemo();
            demo.keyBoardDemo();
            if(i==6){
            	break;
            }
    	}
//        RobotDemo demo = new RobotDemo();
//        demo.keyBoardDemo();
    }
}