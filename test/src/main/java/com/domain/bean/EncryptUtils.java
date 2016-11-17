package com.domain.bean;

import org.apache.shiro.codec.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by ligq01 on 2016/11/14.
 */

public class EncryptUtils {
	private static final Logger logger = LoggerFactory.getLogger(EncryptUtils.class);
	private static final String KEY_MD5 = "md5";
	private static final String CHARS = "utf-8";

	private static final String DESKEY = "E2a0s1y6";

	public static String encryptMD5(String data){
		try {
			//生成一个MessageDigest实例，确定计算方法
			MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);

			//添加要进行计算摘要的信息
			md5.update(data.getBytes(CHARS));

			//生成摘要
			byte[] digest = md5.digest();

			//发给其他人你的信息和摘要，其他人用相同的方法初始化，添加信息，最后进行比较摘要是否相同
			return new String(digest,CHARS);
		} catch (Exception e) {
			logger.error("md5加密失败");
			e.printStackTrace();
		}
		return null;
	}

	public static String encryptPwd(String strIn,String mobno) throws Exception {
		return encryptDES(strIn,getPwdKey(mobno));
	}

	public static String getPwdKey(String mobno){
		char[] str = mobno.substring(3,7).toCharArray();
		String pwdKey = String.format("c%sa%ss%sh%s", str[0], str[1], str[2], str[3]);
		return  pwdKey;
	}

	public static Key getKey(String strKey){
		byte[] arrBTmp = strKey.getBytes();
		byte[] arrB = new byte[8];
		for(int i = 0;( (i<arrBTmp.length) && (i<arrB.length) );++i){
			arrB[i] = arrBTmp[i];
		}
		Key key = new SecretKeySpec(arrB,"DES");
		return key;
	}


	public static String encryptDES(String strIn, String newdesKey) throws Exception {
		if(strIn == null){
			return null;
		}
		Key key = getKey((newdesKey == null) ? DESKEY : newdesKey);

		//SecureRandom类，提供加密的强随机数生成器，主要应用场景：用于安全目的的数据数；例如生成密钥或者会话标识（sessionid）。继承Random类
		SecureRandom sr = new SecureRandom();

		//创建Cipher对象，调用getInstance方法并将所请求转换的名称（DES）传递给它
		Cipher encryptCipher = Cipher.getInstance("DES");

		//用密钥和随机源初始化此Cipher
		encryptCipher.init(Cipher.ENCRYPT_MODE,key,sr);

		//按单部分操作加密或者解密数据，或者结束一个多部分操作；数据将被加密或者解密
		byte[] enc = encryptCipher.doFinal(strIn.getBytes());

		return new String(Base64.encode(enc));
	}


	/**
	 * MD5加密，没有解密
	 * @param str
	 * @param salt
	 * @return
	 */
//	public static String encryptMD5(String str,String salt){
//		return new Md5Hash(str,salt).toString();
//	}

	/**
	 * Base64加密
	 * @param str
	 * @return
	 */
	public static String encryptENC(String str){
		return Base64.encodeToString(str.getBytes());
	}

	/**
	 * Base64解密
	 * @param str
	 * @return
	 */
	public static String encryptDEC(String str){
		return Base64.decodeToString(str);
	}


	public static void main(String[] args){
		String mobno = "13170017850";
		System.out.println(getPwdKey(mobno));
	}

}
