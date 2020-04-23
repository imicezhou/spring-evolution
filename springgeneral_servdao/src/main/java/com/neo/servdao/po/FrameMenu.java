package com.neo.servdao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("frame_menu")
public class FrameMenu {

	@TableId(type=IdType.AUTO)
	private Integer menuId;
	
	@TableField("menucode")
	private String menuCode;
	
	@TableField("menuname")
	private String menuName;
	
	@TableField("menuurl")
	private String menuUrl;
	
	@TableField("menutype")
	private String menuType;
	
	@TableField("menuparent")
	private String menuParent;
	
	@TableField("menuorder")
	private Integer menuOrder;
	
	@TableField("iflock")
	private String ifLock;

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getMenuParent() {
		return menuParent;
	}

	public void setMenuParent(String menuParent) {
		this.menuParent = menuParent;
	}

	public Integer getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(Integer menuOrder) {
		this.menuOrder = menuOrder;
	}

	public String getIfLock() {
		return ifLock;
	}

	public void setIfLock(String ifLock) {
		this.ifLock = ifLock;
	}
	
}
