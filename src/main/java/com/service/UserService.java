package com.service;

import com.dal.pojo.UserBo;
import com.domain.entity.UserDo;

import java.util.List;
import java.util.Map;

/**
 * Created by ligq01 on 2016/11/11.
 */
public interface UserService {
	UserBo selectByUsername(String username);

	Map addUser(UserDo userDo);

	List<UserDo> selectAllUser();

	Map deleteUserByUserId(String userId);
}
