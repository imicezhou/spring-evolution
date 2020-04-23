package com.neo.servdao.mapper;

import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neo.servdao.po.FrameAccount;

public interface AccountDao extends BaseMapper<FrameAccount> {

	Map<String,Object> selectRoleByAccount(String account);
}
