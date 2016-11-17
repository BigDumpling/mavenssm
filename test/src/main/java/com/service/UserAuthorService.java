package com.service;

import com.domain.entity.UserDo;

/**
 * Created by ligq01 on 2016/11/15.
 */
public interface UserAuthorService {
	UserDo getAuthorByUserId(String userId);
}
