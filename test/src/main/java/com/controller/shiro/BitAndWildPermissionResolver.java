package com.controller.shiro;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

/**
 * Created by ligq01 on 2016/11/7.
 */
public class BitAndWildPermissionResolver implements PermissionResolver{

	public Permission resolvePermission(String s) {
		if(s.startsWith("+")){
			return new BitPermission(s);
		}
		return new WildcardPermission(s);
	}
}