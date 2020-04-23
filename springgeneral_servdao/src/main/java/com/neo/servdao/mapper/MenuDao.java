package com.neo.servdao.mapper;

import java.util.List;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neo.servdao.po.FrameMenu;

public interface MenuDao extends BaseMapper<FrameMenu>{

	
	List<FrameMenu> selectMenuByParent(String parent);
	
	List<FrameMenu> selectMenuByUserIdAndParent(Integer userId, String parent);
}
