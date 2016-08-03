package lmd.extend.wso.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lmd.extend.wso.util.BundleHelper;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.springframework.osgi.context.BundleContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import egov.appservice.ac.exception.AccessControlException;
import egov.appservice.asf.exception.ServiceClientException;
@Controller
@RequestMapping
public class RtxLisenerController implements BundleContextAware {
	private BundleContext bundleContext;
	
	@RequestMapping({"/rtx.html"})
	  public ModelAndView getResource(HttpServletResponse res, HttpServletRequest req) throws ServiceClientException, IOException, AccessControlException{
		 Bundle[] bundles = getBundleContext().getBundles();
		 String status="";  
		 for (Bundle b : bundles) {
		      if (BundleHelper.matches(b, "即时通讯构件")) {
		    	  System.out.println("bundleId :"+Long.valueOf(b.getBundleId()));
		    	  System.out.println("SymbolicName :"+b.getSymbolicName());
		    	  System.out.println("Name :"+b.getHeaders().get("Bundle-Name"));
		    	  System.out.println("State :"+BundleHelper.toStateString(b.getState()));
		    	  System.out.println("Version :"+b.getHeaders().get("Bundle-Version"));
		    	  System.out.println("Gjly : A1");
		    	  status=BundleHelper.toStateString(b.getState());
		    	  
		      }
		    }
		ModelAndView mv = new ModelAndView("../../rtx"); 
		if("".equals(status)){
			status="未部署";
		}
		mv.addObject("status", status);
		   return mv;
	  }

	@Override
	public void setBundleContext(BundleContext bundleContext) {
		// TODO 自动生成的方法存根
		this.bundleContext = bundleContext;
	}

	public BundleContext getBundleContext() {
		return this.bundleContext;
	}

	
	
}
