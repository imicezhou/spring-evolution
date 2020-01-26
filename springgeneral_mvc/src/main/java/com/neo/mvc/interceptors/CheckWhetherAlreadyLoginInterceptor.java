package com.neo.mvc.interceptors;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class CheckWhetherAlreadyLoginInterceptor implements HandlerInterceptor {

	/*
	 * if user exited from the app, than he has no rights to visit internal pages
	 */
	@Override
	public boolean preHandle(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler) throws IOException {
		
		if(null == request.getSession().getAttribute(request.getCookies()[0].getValue())) {
			response.sendRedirect(request.getContextPath()+"/login");
			return false;
		}
		return true;
	}
	
}
