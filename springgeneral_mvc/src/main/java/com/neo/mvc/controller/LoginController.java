package com.neo.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	/*
	 * 登录
	 */
	@PostMapping("/signin")
	public ModelAndView signin(
			@CookieValue(name="JSESSIONID") String sessionID,
			@RequestParam(name = "username") String username,
			@RequestParam(name = "password") String password,
			HttpSession session,
			HttpServletRequest request
		) 
	{
		
		ModelAndView mv = new ModelAndView();
		
		AccountInfo ainfo = new AccountInfo();
		ainfo.setAccount(username);
		ainfo.setPassword(password);
		
		if("neo".equals(username)) {
			mv.setViewName("redirect:index");
			session.setMaxInactiveInterval(600);
			session.setAttribute(sessionID, sessionID); 
		}
		else {
			mv.setViewName("redirect:login");
			session.setAttribute("signin_errorMessage", "账号/密码不正确");
		}
//		if (accountServiceFacade.accountIfMatch(ainfo)) {
//			mv.setViewName("index");
//			session.setMaxInactiveInterval(600);
//			session.setAttribute(sessionID, sessionID);   //记住此sessionID
//		}
//		else {
//			mv.setViewName("redirect:login");
//			session.setAttribute("signin_errorMessage", "账号/密码不正确");
//		}
		return mv;
	}
	
	/*
	 * 退出
	 */
	@GetMapping("/exit")
	public String exit
		(
			@CookieValue(name="JSESSIONID") String sessionID,
			HttpSession session
		) 
	{
		ModelAndView mv = new ModelAndView();
		session.removeAttribute(sessionID);      //将用户从session中删去
		return "redirect:login";
	}
	
	/*
	 * 登录成功后，跳转到index
	 */
	@GetMapping("/index")
	public String index() {
		return "index";
	}
}
