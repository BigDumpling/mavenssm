package com.shiro;

import com.domain.bean.EncryptUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.crypto.hash.Sha384Hash;

/**
 * Created by ligq01 on 2016/11/14.
 */
public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {

	public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
		UsernamePasswordToken token = (UsernamePasswordToken)authcToken;

		//第一个是输入的密码，从token中获取，没有加密，需要加密；第二个是数据库查询返回的密码，是经过加密的
		//char类型转化为string类型：String.valueOf()
		Object tokenCredentials = encrypt(String.valueOf(token.getPassword()));
		Object accountCredentials = getCredentials(info);

		return this.equals(tokenCredentials, accountCredentials);
	}

	public String encrypt(String data){
		//先经过MD5加密
		data = EncryptUtils.encryptMD5(data);

		//貌似也是一种加密方式是，但是我不懂
		String sha384Hex = new Sha384Hash(data).toBase64();
		System.out.println(data + ":" + sha384Hex);
		return sha384Hex;
	}
}
