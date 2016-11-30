package com.controller.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;


/**
 * Created by ligq01 on 2016/11/4.
 */
public class TestShiro {

	//基于角色的访问控制,隐式角色控制
//	public static void main(String[] args){
//		Subject subject = login("classpath:shiro.ini");
//		Assert.assertTrue(subject.hasRole("role1"));
//		System.out.println("---1---" + subject.hasRole("role1"));
//		Assert.assertTrue(subject.hasAllRoles(Arrays.asList("role1","role2")));
//		System.out.println("---2---" + subject.hasAllRoles(Arrays.asList("role1","role2")));
//		boolean[] result = subject.hasRoles(Arrays.asList("role1","role2","role3"));
//		System.out.println(result);
//		Assert.assertEquals(true,result[0]);
//		Assert.assertEquals(true,result[1]);
//		Assert.assertEquals(true,result[2]);
//	}

//	基于资源的访问控制
	public static void main(String[] args){
//		Subject subject = login("classpath:shiro-authorizer.ini");
//		System.out.println("---1---" + subject.isPermitted("user:create"));
//		System.out.println("---2---" + subject.isPermittedAll("user:update","user:delete"));
//		System.out.println("---3---" + subject.isPermitted("user:view"));

		Subject subject = login("classpath:shiro-authorizer.ini");
		System.out.println(subject.hasRole("role1"));
		subject.checkPermission("+user+add+1");

	}

	public static Subject login(String configFile){
		Factory factory = new IniSecurityManagerFactory(configFile);

		//得到SecurityManager实例，并绑定给SecurityyUtils
		SecurityManager securityManager = (SecurityManager)factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		//得到Subject及创建用户名/密码身份验证Token(即用户身份/凭证)
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("ligq","ligq#098");

		try{
			//登录，即身份验证
			subject.login(token);
			System.out.println("Login Success!");

		}catch (AuthenticationException e){
			System.out.println("Hello,ligq");
		}
		return subject;
	}
}
