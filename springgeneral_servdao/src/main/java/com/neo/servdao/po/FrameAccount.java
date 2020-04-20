package com.neo.servdao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * PO(persistent object) is better to match with the corresponding table fields
 * PO 最好和它对应的表的字段一致
 */

@TableName("frame_account")
public class FrameAccount {

	@TableId(type=IdType.AUTO)
	private Integer accountId;
	
	@TableField("account")
	private String account;
	
	@TableField("password")
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
