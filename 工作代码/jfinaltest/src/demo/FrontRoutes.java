package demo;

import com.jfinal.config.Routes;

public class FrontRoutes extends Routes{

	@Override
	public void config() {
		// TODO 自动生成的方法存根
		add("/index",HelloController.class);
		add("/index/login",LoginController.class);
	}
	
}
