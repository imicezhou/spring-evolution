package com.neo.servdao.service;


import com.neo.dubbo.bo.frame.HierarchyMenu;

public interface UserService {

	public HierarchyMenu selectUserMenuList(Integer userId);
}
