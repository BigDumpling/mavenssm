package com.domain.form;

/**
 * Created by ligq01 on 2016/11/8.
 */
public class LoginForm {
	private String username;
	private String password;
	private String captcha;
	private String host;
	private Boolean isRemember;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Boolean getRemember() {
		return isRemember;
	}

	public void setRemember(Boolean remember) {
		isRemember = remember;
	}
}
