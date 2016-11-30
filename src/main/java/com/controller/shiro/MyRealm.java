package com.controller.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * Created by ligq01 on 2016/11/7.
 */
public class MyRealm extends AuthorizingRealm {
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.addRole("role1");
		authorizationInfo.addRole("role2");
		authorizationInfo.addObjectPermission(new BitAndWildPermissionResolver().resolvePermission("+user1+10"));
		authorizationInfo.addObjectPermission(new WildcardPermission("user1:*"));
		authorizationInfo.addStringPermission("+user2+10");
		authorizationInfo.addStringPermission("user:*");
		return authorizationInfo;
	}

	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		String username = (String)authenticationToken.getPrincipal(); //得到用户名
		String password = new String((char[])authenticationToken.getCredentials()); //得到密码

		if(!"ligq".equals(username)){
			throw new UnknownAccountException();  //如果用户名报错
		}
		if(!"ligq#098".equals(password)){
			throw new IncorrectCredentialsException();  //如果密码错误
		}
		this.doGetAuthorizationInfo(new SimpleAuthenticationInfo("ligq4","ligq4#098",getName()).getPrincipals());
		return new SimpleAuthenticationInfo("ligq","ligq#098",getName());
	}
}
