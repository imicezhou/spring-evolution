package com.neo.servdao.service;

import com.neo.servdao.po.FrameAccount;

public interface AccountService {
	public boolean accountIfExist(FrameAccount account);
	public boolean accountIfMatch(FrameAccount account);
}
