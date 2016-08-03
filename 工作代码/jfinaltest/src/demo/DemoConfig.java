package demo;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.render.ViewType;

public class DemoConfig extends JFinalConfig {

	@Override
	public void configConstant(Constants me) {
		// TODO 自动生成的方法存根
		me.setDevMode(true);
		me.setViewType(ViewType.JSP);
	}

	@Override
	public void configRoute(Routes me) {
		// TODO 自动生成的方法存根
		me.add(new FrontRoutes());
	}

	@Override
	public void configPlugin(Plugins me) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void configInterceptor(Interceptors me) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void configHandler(Handlers me) {
		// TODO 自动生成的方法存根

	}

}
