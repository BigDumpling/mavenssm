package com.domain.entity;

import java.util.Date;
import java.util.List;
import java.util.ListResourceBundle;

/**
 * Created by ligq01 on 2016/11/15.
 */
public class TblBTSSysRoleVO {

	private String roleId;

	private String roleName;

	private String roleDisableTag;

	private String roleRemark;

	private String roleCreateBy;

	private Date roleCreateDatetime;

	private String roleUpdatetimeBy;

	private Date roleUpdatetimeDatetime;

	private List<TblBTSSysFunctionVO> tblBTSSysFunctionVOList;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDisableTag() {
		return roleDisableTag;
	}

	public void setRoleDisableTag(String roleDisableTag) {
		this.roleDisableTag = roleDisableTag;
	}

	public String getRoleRemark() {
		return roleRemark;
	}

	public void setRoleRemark(String roleRemark) {
		this.roleRemark = roleRemark;
	}

	public String getRoleCreateBy() {
		return roleCreateBy;
	}

	public void setRoleCreateBy(String roleCreateBy) {
		this.roleCreateBy = roleCreateBy;
	}

	public Date getRoleCreateDatetime() {
		return roleCreateDatetime;
	}

	public void setRoleCreateDatetime(Date roleCreateDatetime) {
		this.roleCreateDatetime = roleCreateDatetime;
	}

	public String getRoleUpdatetimeBy() {
		return roleUpdatetimeBy;
	}

	public void setRoleUpdatetimeBy(String roleUpdatetimeBy) {
		this.roleUpdatetimeBy = roleUpdatetimeBy;
	}

	public Date getRoleUpdatetimeDatetime() {
		return roleUpdatetimeDatetime;
	}

	public void setRoleUpdatetimeDatetime(Date roleUpdatetimeDatetime) {
		this.roleUpdatetimeDatetime = roleUpdatetimeDatetime;
	}

	public List<TblBTSSysFunctionVO> getTblBTSSysFunctionVOList() {
		return tblBTSSysFunctionVOList;
	}

	public void setTblBTSSysFunctionVOList(List<TblBTSSysFunctionVO> tblBTSSysFunctionVOList) {
		this.tblBTSSysFunctionVOList = tblBTSSysFunctionVOList;
	}
}
