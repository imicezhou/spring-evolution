package com.neo.dubbo.bo.frame;

import java.io.Serializable;

/**
 * BO is a medium of being transferred between controller and facade
 * It's no need to match with back-end fields one-to-one.
 * BO(business object) 用于控制层与服务层沟通使用，因此它的字段没有必要与数据库表一一对应
 */
public class AccountInfo implements Serializable{

	private Integer accountId;
	private String account;
	private String password;
	
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
