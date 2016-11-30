package com.controller;

import com.dal.pojo.UserBo;
import com.domain.constant.ShiroPermissionsConstant;
import com.domain.entity.UserDo;
import com.domain.form.UserForm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.UserService;
import com.shiro.CustomCredentialsMatcher;
import com.shiro.UserRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
//	@RequiresPermissions(ShiroPermissionsConstant.USER_QUERY)
//	@RequestMapping("/query_all_user")
//	public String queryAllUser(@ModelAttribute("userForm")UserForm userForm){
//
//		UserDo userDo = new UserDo();
//		List<UserDo> userDoList = this.userService.selectAllUser();
//		userForm.setUserDoList(userDoList);
//		return "/user/useList";
//	}

	@ResponseBody
	@RequestMapping("/query_all_user")
	public Object queryAllUser(HttpServletRequest request, HttpServletResponse response,String callback) throws JsonProcessingException {

		UserDo userDo = new UserDo();
		List<UserDo> userDoList = this.userService.selectAllUser();
		response.setContentType("text/plain");
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setHeader("Access-Control-Allow-Origin","http://localhost:7777");
		response.setDateHeader("Expires",0);
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(userDoList);
		mappingJacksonValue.setJsonpFunction(callback);
//		ObjectMapper mapper = new ObjectMapper();
//		String result = mapper.writeValueAsString(userDoList);
//		jsonpCallback = jsonpCallback + "(" + result + ")";
		return mappingJacksonValue;
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
//	@ResponseBody
//	@RequiresPermissions(ShiroPermissionsConstant.USER_ADD)
//	@RequestMapping("/addUser")
//	public Map add(UserForm userForm){
//		Map<String,Object> resultMap = new HashMap<String, Object>();
//		Random random = new Random();
//		String userid = String.valueOf(random.nextInt(100));
//		UserDo userDo = new UserDo();
//		userDo.setUsrId(userid);
//		userDo.setUsrName(userForm.getUsrName());
//		CustomCredentialsMatcher customCredentialsMatcher = new CustomCredentialsMatcher();
//		String password = customCredentialsMatcher.encrypt(userForm.getUsrPwd());
//		userDo.setUsrPwd(password);
//		userDo.setUsrRemark(userForm.getUsrRemark());
//		userDo.setUsrEmail(userForm.getUsrEmail());
//		userDo.setUsrDisableTag("1");
//
//		UserRealm.ShiroUser current = (UserRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
//		userDo.setUsrCreateBy(current.getLoginName());
//		userDo.setUsrUpdateBy(current.getLoginName());
//		resultMap = this.userService.addUser(userDo);
//
//		return resultMap;
//	}


	@ResponseBody
	@RequestMapping(value = "/addUser",method = {RequestMethod.POST,RequestMethod.GET})
	public Object add(UserForm userForm,HttpServletRequest request,HttpServletResponse response,String callback) throws IOException {

		Map<String,Object> resultMap = new HashMap<String, Object>();
		response.setHeader("Access-Control-Allow-Origin","http://localhost:7777");
		ObjectMapper objectMapper = new ObjectMapper();

		String path = request.getQueryString();
		String pathDecode = URLDecoder.decode(path,"utf-8");
		String data = pathDecode.substring(pathDecode.indexOf("{"),pathDecode.indexOf("}")+1);
		userForm = objectMapper.readValue(data,UserForm.class);

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
		userDo.setUsrCreateBy("admin");
		userDo.setUsrUpdateBy("admin");
		resultMap = this.userService.addUser(userDo);

		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(resultMap);
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;
	}

//	@ResponseBody
//	@RequiresPermissions(ShiroPermissionsConstant.USER_DEL)
//	@RequestMapping("/deleteUserByUserId")
//	public Map deleteUserByUserId(HttpServletRequest request,UserForm userForm){
//		Map<String,Object> resultMap = new HashMap<String, Object>();
//		String userId = request.getParameter("userId");
//		resultMap = this.userService.deleteUserByUserId(userId);
//		return resultMap;
//	}

	@ResponseBody
	@RequestMapping("/deleteUserByUserId")
	public Object deleteUserByUserId(HttpServletRequest request,UserForm userForm,HttpServletResponse response,String callback) throws IOException {
		Map<String,Object> resultMap = new HashMap<String, Object>();
		response.setHeader("Access-Control-Allow-Origin","http://localhost:7777");
//		ObjectMapper objectMapper = new ObjectMapper();
//
//		String path = request.getQueryString();
//		String pathDecode = URLDecoder.decode(path,"utf-8");
//		String data = pathDecode.substring(pathDecode.indexOf("{"),pathDecode.indexOf("}")+1);
//		userForm = objectMapper.readValue(data,UserForm.class);
//
//		resultMap = this.userService.deleteUserByUserId(userForm.getUsrId());

		String userID = request.getParameter("usrId");
		resultMap = this.userService.deleteUserByUserId(userID);
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(resultMap);
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;
	}
}
