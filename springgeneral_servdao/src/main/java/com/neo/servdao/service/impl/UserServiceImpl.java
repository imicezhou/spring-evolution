package com.neo.servdao.service.impl;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.neo.dubbo.bo.frame.HierarchyMenu;
import com.neo.servdao.mapper.MenuDao;
import com.neo.servdao.po.FrameMenu;
import com.neo.servdao.service.UserService;

@Transactional
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private MenuDao menuDao;
	
	@Override
	public HierarchyMenu selectUserMenuList(Integer userId) {
		
		QueryWrapper<FrameMenu> queryConditon = new QueryWrapper<>();
		queryConditon.isNull("menuparent");
		FrameMenu fm = menuDao.selectOne(queryConditon);
		if(fm == null) {
			return null;
		}
		
		//根目录
		HierarchyMenu root = new HierarchyMenu();
		root.setMenucode(fm.getMenuCode());
		root.setChildMenus(new ArrayList<HierarchyMenu>());
		
		//定义保存菜单节点的队列
		Queue<HierarchyMenu> queue = new LinkedList<>();
		
		//采用层次遍历的方式找出用户所有的菜单，然后构建出成树形结构
		queue.offer(root);
		
		int n = 0;
		
		while(queue.size()>0) {
			HierarchyMenu parent = queue.poll();	//弹出队列的第一个记录
			
			//查找用户是否有以parent为父节点的菜单
			//如果有则做两件事：
			//  1、加入队列
			//  2、保存到父目录对象中
			List<FrameMenu> childs = menuDao.selectMenuByUserIdAndParent(userId, parent.getMenucode());
			if(childs == null) {
				continue;
			}
			childs.forEach((child) -> {
				HierarchyMenu hchild = wrapFrameMenu(child);
				queue.offer(hchild);	//将这个菜单的子菜单放入队列
				
				parent.getChildMenus().add(hchild);  //将子菜单保存到父菜单的属性中
			});
		}
		
		return root;
	}
	
	
	private HierarchyMenu wrapFrameMenu(FrameMenu menu) {
		
		HierarchyMenu hmenu = new HierarchyMenu();
		hmenu.setMenuId(menu.getMenuId());
		hmenu.setMenucode(menu.getMenuCode());
		hmenu.setMenuname(menu.getMenuName());
		hmenu.setMenutype(menu.getMenuType());
		hmenu.setMenuurl(menu.getMenuUrl());
		hmenu.setMenuorder(menu.getMenuOrder());
		hmenu.setChildMenus(new ArrayList<>());
		return hmenu;
	}
}
