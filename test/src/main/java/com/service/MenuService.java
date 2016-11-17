package com.service;

import com.domain.entity.MenuDo;
import java.util.List;

/**
 * Created by ligq01 on 2016/11/11.
 */
public interface MenuService {
	List<MenuDo> getAllEnabledMenu();

	List<MenuDo> getAllEnabledMenuByUserId(String userid);
}
