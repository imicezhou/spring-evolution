package com.neo.servdao.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neo.dubbo.bo.frame.AccountInfo;
import com.neo.servdao.mapper.AccountDao;
import com.neo.servdao.po.Frame_Account;
import com.neo.servdao.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao accountDao;
	
	@Override
	public boolean accountIfExist(Frame_Account account) {
		Map<String,Object> args = new HashMap<String,Object>();
		args.put("account", account.getAccount());
		List<Frame_Account> rs = accountDao.selectByMap(args);
		if(rs != null || rs.size() > 0) {
			return true;
		}
		else
			return false;
	}

	@Override
	public boolean accountIfMatch(Frame_Account account) {
		
		Map<String,Object> args = new HashMap<>();
		args.put("account", account.getAccount());
		args.put("password", account.getPassword());
		List<Frame_Account> rs = accountDao.selectByMap(args);
		if(rs!=null || rs.size()>0) {
			return true;
		}
		else
			return false;
	}

}
