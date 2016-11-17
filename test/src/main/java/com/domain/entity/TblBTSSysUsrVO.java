package com.domain.entity;

import java.util.List;

/**
 * Created by ligq01 on 2016/11/15.
 */
public class TblBTSSysUsrVO {

	private String usrId;

	private String usrName;

	private String usrPwd;

	private String usrRemark;

	private String usrDisableTag;

	private String usrEmail;

	private String usrCreateBy;

	private String usrUpdateBy;

	private String usrCreateDate;

	private String usrUpdateDate;

	private List<TblBTSSysRoleVO> tblBTSSysRoleVOList;

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

	public String getUsrCreateBy() {
		return usrCreateBy;
	}

	public void setUsrCreateBy(String usrCreateBy) {
		this.usrCreateBy = usrCreateBy;
	}

	public String getUsrUpdateBy() {
		return usrUpdateBy;
	}

	public void setUsrUpdateBy(String usrUpdateBy) {
		this.usrUpdateBy = usrUpdateBy;
	}

	public String getUsrCreateDate() {
		return usrCreateDate;
	}

	public void setUsrCreateDate(String usrCreateDate) {
		this.usrCreateDate = usrCreateDate;
	}

	public String getUsrUpdateDate() {
		return usrUpdateDate;
	}

	public void setUsrUpdateDate(String usrUpdateDate) {
		this.usrUpdateDate = usrUpdateDate;
	}

	public List<TblBTSSysRoleVO> getTblBTSSysRoleVOList() {
		return tblBTSSysRoleVOList;
	}

	public void setTblBTSSysRoleVOList(List<TblBTSSysRoleVO> tblBTSSysRoleVOList) {
		this.tblBTSSysRoleVOList = tblBTSSysRoleVOList;
	}
}
