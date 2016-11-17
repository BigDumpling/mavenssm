package com.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Created by ligq01 on 2016/11/8.
 */
public class CaptchaUsernamePasswordToken extends UsernamePasswordToken {
	private String captcha;

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public CaptchaUsernamePasswordToken(){
		super();
	}

	public CaptchaUsernamePasswordToken(String username,String password,boolean isRemember,String host,String captcha){
		super(username,password,isRemember,host);
		this.captcha = captcha;
	}


}
