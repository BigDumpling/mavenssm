package com.domain.form;

import com.domain.entity.UserDo;

import java.util.List;

/**
 * Created by ligq01 on 2016/11/15.
 */
public class UserForm {

	private String usrId;

	private String usrName;

	private String usrPwd;

	private String usrRemark;

	private String usrDisableTag;

	private String usrEmail;

	public List<UserDo> getUserDoList() {
		return userDoList;
	}

	public void setUserDoList(List<UserDo> userDoList) {
		this.userDoList = userDoList;
	}

	private List<UserDo> userDoList;

	public String getUsrId() {
		return usrId;
	}

	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}

	public String getUsrName() {
		return usrName;
	}

	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}

	public String getUsrPwd() {
		return usrPwd;
	}

	public void setUsrPwd(String usrPwd) {
		this.usrPwd = usrPwd;
	}

	public String getUsrRemark() {
		return usrRemark;
	}

	public void setUsrRemark(String usrRemark) {
		this.usrRemark = usrRemark;
	}

	public String getUsrDisableTag() {
		return usrDisableTag;
	}

	public void setUsrDisableTag(String usrDisableTag) {
		this.usrDisableTag = usrDisableTag;
	}

	public String getUsrEmail() {
		return usrEmail;
	}

	public void setUsrEmail(String usrEmail) {
		this.usrEmail = usrEmail;
	}
}
