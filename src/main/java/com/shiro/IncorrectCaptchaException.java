package com.shiro;


import com.sun.xml.internal.txw2.output.IndentingXMLFilter;
import org.apache.shiro.authc.AuthenticationException;

/**
 * Created by ligq01 on 2016/11/15.
 */
public class IncorrectCaptchaException extends AuthenticationException {

	/** 描述  */
	private static final long serialVersionUID = 6146451562810994591L;

	public IncorrectCaptchaException(){
		super();
	}

	public IncorrectCaptchaException(String message){
		super(message);
	}

	public IncorrectCaptchaException(Throwable cause){
		super(cause);
	}

	public IncorrectCaptchaException(String message, Throwable cause){
		super(message,cause);
	}
}
