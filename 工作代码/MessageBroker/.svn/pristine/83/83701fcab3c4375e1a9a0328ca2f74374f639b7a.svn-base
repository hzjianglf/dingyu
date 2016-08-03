package com.ibm.struts;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	public String intercept(ActionInvocation invocation) throws Exception {
        Map<String, Object> map = invocation.getInvocationContext().getSession();            
        if (map.get("username") == null) {
            return Action.LOGIN;
            
        } else {
            return invocation.invoke();
        }
    }

}
