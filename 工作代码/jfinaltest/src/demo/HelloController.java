package demo;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

public class HelloController extends Controller{
	public void index() {
		renderText("Hello JFinal World."+getPara(0)+","+getPara(1));
		}
	@ActionKey("/testOut")
	public void testOut(){
		renderText("<html><head>" +
				"<title>测试</title>"+
				"</head><body>" +
				"<h1>访问方法名：localhost/hello/testOut</h1>" +
				getPara(0)+","+getPara(1)+
				"</body></html>");
	}
}
