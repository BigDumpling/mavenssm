package com.service.impl;

import com.dal.dao.MenuBoMapper;
import com.domain.entity.MenuDo;
import com.dal.pojo.MenuBo;
import com.dal.pojo.MenuBoExample;
import com.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ligq01 on 2016/11/11.
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService{

	@Autowired
	MenuBoMapper menuBoMapper;

	public List<MenuDo> getAllEnabledMenu(){
		MenuBoExample menuBoExample = new MenuBoExample();

//		查询所有 可用 并且是 菜单 的数据
		menuBoExample.createCriteria().andFuncDisableTagEqualTo("1").andFuncTagEqualTo("0");
		List<MenuBo> menuBoList = this.menuBoMapper.selectByExample(menuBoExample);

		List<MenuDo> menuDo = new ArrayList<MenuDo>();
//		遍历数据，分离一级 二级  三级菜单
		for(MenuBo menu : menuBoList){

//			如果是一级菜单
			if(menu.getFuncLevel().equals("1")){
//				取到一级菜单的信息
				MenuDo firstMenuDo = new MenuDo();
				firstMenuDo.setFuncId(menu.getFuncId());
				firstMenuDo.setFuncName(menu.getFuncName());
				firstMenuDo.setFuncDesc(menu.getFuncDesc());
				firstMenuDo.setFuncFatherId(menu.getFuncFatherId());
				firstMenuDo.setFuncIcon(menu.getFuncIcon());
				firstMenuDo.setFuncLevel(menu.getFuncLevel());
				firstMenuDo.setFuncPriority(menu.getFuncPriority());
				firstMenuDo.setFuncUrl(menu.getFuncUrl());

//				取一级菜单下的二级菜单的数据
				List<MenuDo> secondMenuDoList = new ArrayList<MenuDo>();
				for(MenuBo menu2 : menuBoList){
//					如果func_level是“2”，且FUNC_FATHER_ID等于FUNC_ID，就是二级菜单
					if(menu2.getFuncLevel().equals("2") && menu2.getFuncFatherId().equals(menu.getFuncId())){
						MenuDo secondMenuDo = new MenuDo();
						secondMenuDo.setFuncName(menu2.getFuncName());
						secondMenuDo.setFuncId(menu2.getFuncId());
						secondMenuDo.setFuncLevel(menu2.getFuncLevel());
						secondMenuDo.setFuncFatherId(menu2.getFuncFatherId());
						secondMenuDo.setFuncPriority(menu2.getFuncPriority());
						secondMenuDo.setFuncUrl(menu2.getFuncUrl());
						secondMenuDo.setFuncIcon(menu2.getFuncIcon());
						secondMenuDo.setFuncDesc(menu2.getFuncDesc());

//						取二级菜单下的三级菜单
						List<MenuDo> thirdMenuDoList = new ArrayList<MenuDo>();
						for(MenuBo menu3:menuBoList){
							if(menu3.getFuncLevel().equals("3") && menu3.getFuncFatherId().equals(menu2.getFuncId())){
								MenuDo thirdMenuDo = new MenuDo();
								thirdMenuDo.setFuncName(menu3.getFuncName());
								thirdMenuDo.setFuncId(menu3.getFuncId());
								thirdMenuDo.setFuncLevel(menu3.getFuncLevel());
								thirdMenuDo.setFuncFatherId(menu3.getFuncFatherId());
								thirdMenuDo.setFuncPriority(menu3.getFuncPriority());
								thirdMenuDo.setFuncUrl(menu3.getFuncUrl());
								thirdMenuDo.setFuncIcon(menu3.getFuncIcon());
								thirdMenuDo.setFuncDesc(menu3.getFuncDesc());
								thirdMenuDoList.add(thirdMenuDo);
								Collections.sort(thirdMenuDoList);
							}
						}

						secondMenuDo.setChildMenuDoList(thirdMenuDoList);
						secondMenuDoList.add(secondMenuDo);
						Collections.sort(secondMenuDoList);
					}

				}
				firstMenuDo.setChildMenuDoList(secondMenuDoList);
				menuDo.add(firstMenuDo);
			}
		}

		return menuDo;
	}

	public List<MenuDo> getAllEnabledMenuByUserId(String userid) {
		return null;
	}

	public int compareTo(MenuDo o) {
		return 0;
	}
}
