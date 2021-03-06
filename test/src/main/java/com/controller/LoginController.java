package com.controller;

import com.domain.entity.MenuDo;
import com.shiro.CaptchaUsernamePasswordToken;
import com.domain.form.LoginForm;
import com.service.MenuService;
import com.shiro.UserRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ligq01 on 2016/11/8.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	MenuService menuService;

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public String login(){
		return "login";
	}

	@RequestMapping(value = "/index",method = RequestMethod.POST)
	public String login(@ModelAttribute("loginForm")LoginForm loginForm,HttpServletRequest request){
		Map<String,String> resultMap = new HashMap<String, String>();
		String host = request.getRemoteHost();
		loginForm.setHost(host);

		Subject currentUser = SecurityUtils.getSubject();

		if(!currentUser.isAuthenticated()){
			resultMap = login(currentUser,loginForm);
		}else {
			currentUser.logout();
			resultMap = login(currentUser,loginForm);
		}
		if("200".equals(resultMap.get("statusCode"))) {
//			登录成功，重定向到主页面
			return "redirect:/login/main";
		} else {
//			登录失败，返回登录页面
			request.setAttribute("error", resultMap);
			return "login";
		}
	}

	public Map<String,String> login(Subject currentUser, LoginForm loginForm){
		Map<String,String> resultMap = new HashMap<String, String>();

//		继承UsernamePasswordToken的一个类，封装了验证码
		CaptchaUsernamePasswordToken captchaUsernamePasswordToken = new CaptchaUsernamePasswordToken(loginForm.getUsername(),loginForm.getPassword(),true,loginForm.getHost(),loginForm.getCaptcha());
		try{
			currentUser.login(captchaUsernamePasswordToken);
			resultMap.put("statusCode","200");
			resultMap.put("message","登录成功！");
		} catch (UnknownAccountException uae){
			resultMap.put("statusCode","300");
			resultMap.put("message","用户账户不存在！");
		}catch (IncorrectCredentialsException ice) {
			resultMap.put("statusCode", "300");
			resultMap.put("message", "用户密码错误!");
		} catch (LockedAccountException lae) {
			resultMap.put("statusCode", "300");
			resultMap.put("message", "账户已被锁定!");
		}
		catch (AuthenticationException ae) {
			if(ae.getMessage().equals("验证码错误!")){
				resultMap.put("statusCode", "300");
				resultMap.put("message", "验证码错误!");
			}else {
				resultMap.put("statusCode", "300");
				resultMap.put("message", "认证异常!");
			}
		}
		return resultMap;
	}

	@RequestMapping(value = "/main")
	public String main(Map<String,Object> model){
		UserRealm.ShiroUser shiroUser = (UserRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
		if (shiroUser == null){
			return "redirect:/login";
		}

//		根据登录的用户名判断是否为管理员角色，如果说管理员权限，就展示所有菜单；如果不是管理员角色，就根据角色id展示相应权限的菜单
		String userId = shiroUser.getId();
		List<MenuDo> menus = new ArrayList<MenuDo>();
		if(shiroUser.getLoginName().equals("admin")){
			menus = this.menuService.getAllEnabledMenu();
		}else {
//			menus = this.menuService.getAllEnabledMenuByUserId(shiroUser.getId());
			menus = this.menuService.getAllEnabledMenu();
		}
		model.put("models",menus);
		return "success";
	}

	@RequestMapping("logout")
	public String logOut(){
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		logger.info(" 用户退出登录 " );
		return "redirect:/login/login";
	}
}
