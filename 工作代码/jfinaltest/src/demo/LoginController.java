package demo;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

public class LoginController extends Controller{
	
	public void login(){
		renderText("login");
	}
	
}
