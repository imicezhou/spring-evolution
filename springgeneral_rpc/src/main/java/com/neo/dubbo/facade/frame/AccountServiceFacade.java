package com.neo.dubbo.facade.frame;

import com.neo.dubbo.bo.frame.AccountInfo;

public interface AccountServiceFacade {

	boolean accountIfExist(AccountInfo account);
	
	boolean accountIfMatch(AccountInfo account);
}
