package com.newtouch.dao;

import java.util.List;

import com.newtouch.entitys.Menu;
import com.newtouch.entitys.User;

public interface ILoginDao {
	/**
	 * 获取菜单
	 * @param parentId 菜单的上级节点id
	 * @return
	 */
     List<Menu> getMenus(String parentId);
     User judgeLogin(String username ,String password);
}
