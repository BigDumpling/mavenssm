package com.shiro;

import com.controller.CaptchaImageCreateController;
import com.dal.pojo.UserBo;
import com.domain.entity.FuncDo;
import com.domain.entity.RoleDo;
import com.domain.entity.TblBTSSysRoleVO;
import com.domain.entity.UserDo;
import com.service.UserAuthorService;
import com.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * Created by ligq01 on 2016/11/9.
 */
public class UserRealm extends AuthorizingRealm {

	@Autowired
	UserService userService;

	@Autowired
	UserAuthorService userAuthorService;

	public UserRealm(){
		super();
		CustomCredentialsMatcher customCredentialsMatcher = new CustomCredentialsMatcher();
		setCredentialsMatcher(customCredentialsMatcher);
	}

	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		ShiroUser shiroUser = (ShiroUser)principals.fromRealm(getName()).iterator().next();

		//根据id查询该用户的角色和权限（资源）
		UserDo userDo = this.userAuthorService.getAuthorByUserId(shiroUser.getId());

		if( !userDo.getRoleDOList().equals("") ){
			SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

			//遍历查询结果集中的角色列表，添加角色名称
			 for(RoleDo roleDo:userDo.getRoleDOList()){
				 simpleAuthorizationInfo.addRole(roleDo.getRoleName());

				 //遍历角色中的权限（资源）列表，为角色添加权限（资源）
				 for(FuncDo funcDo:roleDo.getFuncDOList()){
					 String permission = funcDo.getFuncUrl();
					 if( !(permission == null) && !permission.equals("")){
						 simpleAuthorizationInfo.addStringPermission(permission);
					 }
				 }
			 }

			return simpleAuthorizationInfo;
		}else {
			return null;
		}
	}

	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authtoken) throws AuthenticationException {

		//使用自定义的CaptchaUsernamePasswordToken类封装authtoken，获取验证码
		CaptchaUsernamePasswordToken token = (CaptchaUsernamePasswordToken)authtoken;

		String username = token.getUsername();
		String captcha = token.getCaptcha();

		String sessionCaptcha = (String) SecurityUtils.getSubject().getSession().getAttribute(CaptchaImageCreateController.CAPTCHA_KEY);

		//验证码错误，抛出自定义的IncorrectCaptchaException异常，更加精确的确定错误原因
		if(!captcha.equals(sessionCaptcha)){
			throw new IncorrectCaptchaException("验证码错误!");
		}
//		String password = new String((char[]) token.getCredentials());

//		根据填写的用户名到数据库里查找用户信息，然后进行身份认证
		UserBo userBo = this.userService.selectByUsername(username);

//		如果根据用户名查找到数据，但是用户是被禁用的，报错
		if(userBo != null){
			if(userBo.getUsrDisableTag().equals("0")){
				throw new DisabledAccountException();
			}
			ShiroUser shiroUser = new ShiroUser(userBo.getUsrId(),userBo.getUsrName(),userBo);

			return new SimpleAuthenticationInfo(shiroUser,userBo.getUsrPwd(),getName());
		}else {
			return null;
		}
	}


	/**
	 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
	 */
	public static class ShiroUser implements Serializable {
		private static final long serialVersionUID = -1602382963711884L;
		private String id;
		private String loginName;
		private UserBo userBo;

		public ShiroUser(){}

		public ShiroUser(String id,String loginName,UserBo user){
			this.id = id;
			this.loginName = loginName;
			this.userBo = user;
		}

		/**
		返回id的值
		@return id
		*/
		public String getId(){
			return id;
		}

		/**
		 * 返回loginName的值
		 * @return loginName
		 * */
		public String getLoginName(){
			return loginName;
		}

		/**
		 * 返回user的值
		 * @return user
		 * */
		public UserBo getUser(){
			return userBo;
		}

		/**
		 * 本函数将作为默认的<shiro:principal>输出
		 * */
		public String toString(){
			return loginName;
		}

	}


}
