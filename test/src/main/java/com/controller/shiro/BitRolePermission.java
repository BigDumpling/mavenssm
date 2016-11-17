package com.controller.shiro;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by ligq01 on 2016/11/8.
 */
public class BitRolePermission implements RolePermissionResolver{
	public Collection<Permission> resolvePermissionsInRole(String s) {
		if(s.contains("role1")){
			return Arrays.asList((Permission) new BitPermission("+user+add+1"));
		}
		return null;
	}
}
