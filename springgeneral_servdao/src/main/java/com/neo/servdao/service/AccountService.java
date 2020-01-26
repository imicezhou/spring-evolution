package com.neo.servdao.service;

import com.neo.servdao.po.Frame_Account;

public interface AccountService {
	public boolean accountIfExist(Frame_Account account);
	public boolean accountIfMatch(Frame_Account account);
}
