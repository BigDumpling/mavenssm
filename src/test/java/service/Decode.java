package service;

import com.alibaba.druid.support.logging.Log;
import com.domain.form.LoginForm;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by ligq01 on 2016/11/28.
 */
public class Decode {
	public static void main(String[] args) throws IOException {
		String url = "callback=loginCallback&{%22username%22:%22admin%22,%22password%22:%22admin%22,%22captcha%22:%227pcc%22,%22isRemember%22:true}&_=1480319290110";
		String path = URLDecoder.decode(url,"utf-8");
		String data = path.substring(path.indexOf("{"),path.indexOf("}")+1);
		System.out.println(path);
		System.out.println(data);
		ObjectMapper objectMapper = new ObjectMapper();
		LoginForm loginForm = objectMapper.readValue(data,LoginForm.class);
		System.out.println(loginForm.getUsername());
	}
}
