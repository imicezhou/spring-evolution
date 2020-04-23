package com.neo.dubbo.bo.frame;

import java.util.List;


/**
 * 1 树形结构菜单的数据结构
 * @author 15105
 *
 */
public class HierarchyMenu {

	private Integer menuId;
	private String menucode;
	private String menuname;
	private String menuurl;
	private String menutype;
	private Integer menuorder;
	List<HierarchyMenu> childMenus;
	
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public String getMenucode() {
		return menucode;
	}
	public void setMenucode(String menucode) {
		this.menucode = menucode;
	}
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public String getMenuurl() {
		return menuurl;
	}
	public void setMenuurl(String menuurl) {
		this.menuurl = menuurl;
	}
	public String getMenutype() {
		return menutype;
	}
	public void setMenutype(String menutype) {
		this.menutype = menutype;
	}
	public Integer getMenuorder() {
		return menuorder;
	}
	public void setMenuorder(Integer menuorder) {
		this.menuorder = menuorder;
	}
	public List<HierarchyMenu> getChildMenus() {
		return childMenus;
	}
	public void setChildMenus(List<HierarchyMenu> childMenus) {
		this.childMenus = childMenus;
	}

}
