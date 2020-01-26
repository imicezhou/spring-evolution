package com.neo.dubbo.facade.frame.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.neo.dubbo.bo.frame.AccountInfo;
import com.neo.dubbo.facade.frame.AccountServiceFacade;
import com.neo.servdao.po.Frame_Account;
import com.neo.servdao.service.AccountService;


public class AccountServiceFacadeImpl implements AccountServiceFacade {

	@Autowired
	private AccountService accountService;
	
	@Override
	public boolean accountIfExist(AccountInfo account) {
		Frame_Account fa = new Frame_Account();
		fa.setAccountId(account.getAccountId());
		fa.setAccount(account.getAccount());
		fa.setPassword(account.getPassword());
		return accountService.accountIfExist(fa);
	}

	@Override
	public boolean accountIfMatch(AccountInfo account) {
		Frame_Account fa = new Frame_Account();
		fa.setAccountId(account.getAccountId());
		fa.setAccount(account.getAccount());
		fa.setPassword(account.getPassword());
		return accountService.accountIfMatch(fa);
	}

}
