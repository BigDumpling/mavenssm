package com.controller;

import com.dal.pojo.UserBo;
import com.domain.constant.ShiroPermissionsConstant;
import com.domain.entity.UserDo;
import com.domain.form.UserForm;
import com.service.UserService;
import com.shiro.CustomCredentialsMatcher;
import com.shiro.UserRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by ligq01 on 2016/11/15.
 */
@Controller
@RequestMapping("/userManagement")
public class UserManagerController {

	@Autowired
	UserService userService;

	/**
	 * 查询所有用户，需要有 user:query权限才能执行
	 * @param userForm
	 * @return
	 */
	@RequiresPermissions(ShiroPermissionsConstant.USER_QUERY)
	@RequestMapping("/query_all_user")
	public String queryAllUser(@ModelAttribute("userForm")UserForm userForm){

		UserDo userDo = new UserDo();
		List<UserDo> userDoList = this.userService.selectAllUser();
		userForm.setUserDoList(userDoList);
		return "/user/useList";
	}

	/**
	 * 跳转至添加用户页面，需要有 user:add权限才能执行，否则就会抛出异常
	 * @param userForm
	 * @return
	 */
	@RequiresPermissions(ShiroPermissionsConstant.USER_ADD)
	@RequestMapping("/addnewpage")
	public String  addPage(@ModelAttribute("userForm")UserForm userForm){
		return "/user/addPage";
	}

	/**
	 * 添加新用户，需要有 user:add权限才能执行，否则就会抛出异常
	 * @param userForm
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions(ShiroPermissionsConstant.USER_ADD)
	@RequestMapping("/addUser")
	public Map add(UserForm userForm){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		Random random = new Random();
		String userid = String.valueOf(random.nextInt(100));
		UserDo userDo = new UserDo();
		userDo.setUsrId(userid);
		userDo.setUsrName(userForm.getUsrName());
		CustomCredentialsMatcher customCredentialsMatcher = new CustomCredentialsMatcher();
		String password = customCredentialsMatcher.encrypt(userForm.getUsrPwd());
		userDo.setUsrPwd(password);
		userDo.setUsrRemark(userForm.getUsrRemark());
		userDo.setUsrEmail(userForm.getUsrEmail());
		userDo.setUsrDisableTag("1");

		UserRealm.ShiroUser current = (UserRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
		userDo.setUsrCreateBy(current.getLoginName());
		userDo.setUsrUpdateBy(current.getLoginName());
		resultMap = this.userService.addUser(userDo);

		return resultMap;
	}

	@ResponseBody
	@RequiresPermissions(ShiroPermissionsConstant.USER_DEL)
	@RequestMapping("/deleteUserByUserId")
	public Map deleteUserByUserId(HttpServletRequest request,UserForm userForm){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		String userId = request.getParameter("userId");
		resultMap = this.userService.deleteUserByUserId(userId);
		return resultMap;
	}


}
