package com.domain.entity;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by ligq01 on 2016/11/11.
 */
public class MenuDo implements Comparable<MenuDo>{
	private String funcId;

	private String funcName;

	private String funcFatherId;

	private String funcDesc;

	private String funcLevel;

	private String funcUrl;

	private String funcIcon;

	private BigDecimal funcPriority;

	public List<MenuDo> getChildMenuDoList() {
		return childMenuDoList;
	}

	public void setChildMenuDoList(List<MenuDo> childMenuDoList) {
		this.childMenuDoList = childMenuDoList;
	}

	private List<MenuDo> childMenuDoList;

	public String getFuncId() {
		return funcId;
	}

	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}

	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public String getFuncFatherId() {
		return funcFatherId;
	}

	public void setFuncFatherId(String funcFatherId) {
		this.funcFatherId = funcFatherId;
	}

	public String getFuncDesc() {
		return funcDesc;
	}

	public void setFuncDesc(String funcDesc) {
		this.funcDesc = funcDesc;
	}

	public String getFuncLevel() {
		return funcLevel;
	}

	public void setFuncLevel(String funcLevel) {
		this.funcLevel = funcLevel;
	}

	public String getFuncUrl() {
		return funcUrl;
	}

	public void setFuncUrl(String funcUrl) {
		this.funcUrl = funcUrl;
	}

	public String getFuncIcon() {
		return funcIcon;
	}

	public void setFuncIcon(String funcIcon) {
		this.funcIcon = funcIcon;
	}

	public BigDecimal getFuncPriority() {
		return funcPriority;
	}

	public void setFuncPriority(BigDecimal funcPriority) {
		this.funcPriority = funcPriority;
	}

	public int compareTo(MenuDo o) {
		System.out.println("继承的方法，暂时未写，标记一哈");
		return 0;
	}
}
