package com.service.impl;

import com.dal.dao.MenuBoMapper;
import com.dal.dao.UserAuthorMapper;
import com.dal.dao.UserBoMapper;
import com.dal.pojo.MenuBo;
import com.dal.pojo.MenuBoExample;
import com.dal.pojo.UserBo;
import com.domain.entity.*;
import com.service.UserAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ligq01 on 2016/11/15.
 */
@Service("userAuthorService")
public class UserAuthorServiceImpl implements UserAuthorService {

	@Autowired
	UserBoMapper userBoMapper;

	@Autowired
	MenuBoMapper menuBoMapper;

	@Autowired
	UserAuthorMapper userAuthorMapper;

	public UserDo getAuthorByUserId(String userId) {

		List<RoleDo> roleDoList = new ArrayList<RoleDo>();

		//根据id查询user表，查询用户信息
		UserBo userBo = this.userBoMapper.selectByPrimaryKey(userId);

		if(userBo.getUsrName().equals("admin")){

			//查询系统资源表
			MenuBoExample example = new MenuBoExample();
			example.createCriteria();
			List<MenuBo> menuBoList = this.menuBoMapper.selectByExample(example);

			//把查询系统资源表里的url放入FuncDo中
			List<FuncDo> funcDoList = new ArrayList<FuncDo>();
			FuncDo funcDo = null;
			for(MenuBo menuBo:menuBoList){
				funcDo = new FuncDo();
				funcDo.setFuncUrl(menuBo.getFuncUrl());
				funcDoList.add(funcDo);
			}

			RoleDo roleDo = new RoleDo();
			roleDo.setFuncDOList(funcDoList);
			roleDo.setRoleName("admin");
			roleDoList.add(roleDo);
		}else {
			//夺标查询，查询该用户所拥有的角色与权限
			TblBTSSysUsrVO tblBTSSysUsrVO = this.userAuthorMapper.getAuthorByUserId(userId);
			List<TblBTSSysRoleVO> tblBTSSysRoleVOList = new ArrayList<TblBTSSysRoleVO>();

			//查询到了数据，直接赋值
			if( !(tblBTSSysUsrVO.getTblBTSSysRoleVOList() == null) ){
				tblBTSSysRoleVOList = tblBTSSysUsrVO.getTblBTSSysRoleVOList();
			}

			if( !(tblBTSSysRoleVOList == null) ){
				//遍历tblBTSSysRoleVOList，为每一个角色添加资源
				for(TblBTSSysRoleVO tblBTSSysRoleVO:tblBTSSysRoleVOList){
					RoleDo roleDo = new RoleDo();
					roleDo.setRoleName(tblBTSSysRoleVO.getRoleName());
					roleDo.setRoleId(tblBTSSysRoleVO.getRoleId());

					List<TblBTSSysFunctionVO> tblBTSSysFunctionVOList = tblBTSSysRoleVO.getTblBTSSysFunctionVOList();
					List<FuncDo> funcDoList = new ArrayList<FuncDo>();
					for(TblBTSSysFunctionVO tblBTSSysFunctionVO:tblBTSSysFunctionVOList){
						FuncDo funcDo = new FuncDo();
						funcDo.setFuncUrl(tblBTSSysFunctionVO.getFuncUrl());
						funcDo.setFuncId(tblBTSSysFunctionVO.getFuncId());

						funcDoList.add(funcDo);
					}
					roleDo.setFuncDOList(funcDoList);
					roleDoList.add(roleDo);
				}
			}
		}

		UserDo userDo = new UserDo();
		userDo.setUsrId(userId);
		userDo.setRoleDOList(roleDoList);

		return userDo;
	}
}
