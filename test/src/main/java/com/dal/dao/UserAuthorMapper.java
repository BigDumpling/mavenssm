package com.dal.dao;

import com.domain.entity.TblBTSSysUsrVO;

/**
 * Created by ligq01 on 2016/11/15.
 */
public interface UserAuthorMapper {
	TblBTSSysUsrVO getAuthorByUserId(String userid);
}
