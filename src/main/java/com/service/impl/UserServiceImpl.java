package com.service.impl;

import com.dal.dao.UserBoMapper;
import com.dal.pojo.UserBo;
import com.dal.pojo.UserBoExample;
import com.domain.entity.UserDo;
import com.domain.util.DateUtil;
import com.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ligq01 on 2016/11/11.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserBoMapper userBoMapper;

	public UserBo selectByUsername(String username) {
		logger.info("查询的用户名为：" + username);
		UserBoExample userBoExample = new UserBoExample();
		userBoExample.createCriteria().andUsrNameEqualTo(username);
		List<UserBo> userBoList = new ArrayList<UserBo>();

		try{
			userBoList = this.userBoMapper.selectByExample(userBoExample);
		}catch (Exception e){
			logger.error(e.getMessage());
		}

		return userBoList.get(0);
	}

	public Map addUser(UserDo userDo) {
		Map<String ,Object> resultMap = new HashMap<String, Object>();
		UserBo userBo = new UserBo();
		userBo.setUsrId(userDo.getUsrId());
		userBo.setUsrName(userDo.getUsrName());
		userBo.setUsrPwd(userDo.getUsrPwd());
		userBo.setUsrDisableTag(userDo.getUsrDisableTag());
		userBo.setUsrEmail(userDo.getUsrEmail());
		if(userDo.getUsrDisableTag() != null && userDo.getUsrDisableTag().trim().equals("")){

		}

		userBo.setUsrCreateBy(userDo.getUsrCreateBy());
		userBo.setUsrUpdateBy(userDo.getUsrUpdateBy());
		userBo.setUsrCreateDate(DateUtil.currentTimestamp());
		userBo.setUsrUpdateDate(DateUtil.currentTimestamp());

		int i = this.userBoMapper.insert(userBo);
		if(i == 0){
			resultMap.put("statusCode",300);
			resultMap.put("message","操作失败！");
		}else {
			resultMap.put("statusCode",200);
			resultMap.put("message","操作成功！");
		}
		return resultMap;
	}

	public List<UserDo> selectAllUser() {

		List<UserDo> userDoList = new ArrayList<UserDo>();
		UserBoExample example = new UserBoExample();
		example.createCriteria();
		List<UserBo> userBoList = this.userBoMapper.selectByExample(example);
		if(userBoList != null){
			for(UserBo userBo:userBoList){
				UserDo userDo = new UserDo();
				userDo.setUsrId(userBo.getUsrId());
				userDo.setUsrName(userBo.getUsrName());
				userDo.setUsrPwd(userBo.getUsrPwd());
				userDo.setUsrRemark(userBo.getUsrRemark());
				userDo.setUsrEmail(userBo.getUsrEmail());
				if(userBo.getUsrDisableTag().equals("1")){
					userDo.setUsrDisableTag("启用");
				}else {
					userDo.setUsrDisableTag("禁用");
				}
				userDoList.add(userDo);
			}
		}

		return userDoList;
	}

	public Map deleteUserByUserId(String userId) {
		Map<String,Object> resultMap = new HashMap<String, Object>();
//		UserBoExample example = new UserBoExample();
//		example.createCriteria().andUsrIdEqualTo(userId);
		int i = this.userBoMapper.deleteByPrimaryKey(userId);

		if(i != 0 ){
			resultMap.put("statusCode",200);
			resultMap.put("message", "操作成功!");
		} else {
			resultMap.put("statusCode",200);
			resultMap.put("message", "操作成功!");
		}
		return resultMap;
	}
}
