package com.neo.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.neo.dubbo.bo.frame.AccountInfo;
import com.neo.dubbo.facade.frame.AccountServiceFacade;


/**
 * 
 * @author 15105
 *
 */
@Controller
public class LoginController {

	@Autowired
	private AccountServiceFacade accountServiceFacade;
	
	@GetMapping("/signin")
	public ModelAndView signin(
			@CookieValue(name="JSESSIONID") String sessionID,
			@RequestParam String account,
			@RequestParam String password,
			@RequestParam String ifremember,
			HttpSession session,
			HttpServletRequest request
		) 
	{
		
		ModelAndView mv = new ModelAndView();
		
		AccountInfo ainfo = new AccountInfo();
		ainfo.setAccount(account);
		ainfo.setPassword(password);
		
		if (accountServiceFacade.accountIfMatch(ainfo)) {
			mv.setViewName("index");
			session.setMaxInactiveInterval(600);
			session.setAttribute(sessionID, sessionID);   //记住此sessionID
		}
		else {
			mv.setViewName("login");
			request.setAttribute("signin_errorMessage", "账号/密码不正确");
			mv.addObject("signin_errorMessage", "账号/密码不正确");
		}
		return mv;
	}
	
	
	@GetMapping("/exit")
	public ModelAndView exit
		(
			@CookieValue(name="JSESSIONID") String sessionID,
			HttpSession session
		) 
	{
		ModelAndView mv = new ModelAndView();
		session.removeAttribute(sessionID);      //将用户从session中删去
		mv.setViewName("login");    //回到登录页面
		return mv;
	}
}
