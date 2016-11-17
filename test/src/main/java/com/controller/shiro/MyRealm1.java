package com.controller.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;


/**
 * Created by ligq01 on 2016/11/7.
 */
public class MyRealm1 implements Realm{

	public String getName() {
		return "myrealm1";
	}

	public boolean supports(AuthenticationToken token) {
		return token instanceof UsernamePasswordToken;
	}

	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String)token.getPrincipal(); //得到用户名
		String password = new String((char[])token.getCredentials()); //得到密码

		if(!"ligq".equals(username)){
			throw new UnknownAccountException();  //如果用户名报错
		}
		if(!"ligq#098".equals(password)){
			throw new IncorrectCredentialsException();  //如果密码错误
		}

		return new SimpleAuthenticationInfo(username,password,getName());
	}
}
