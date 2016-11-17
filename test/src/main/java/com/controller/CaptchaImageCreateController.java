package com.controller;

import com.google.code.kaptcha.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

/**
 * Created by ligq01 on 2016/11/14.
 */
@Controller
public class CaptchaImageCreateController {
	private static final Logger logger = LoggerFactory.getLogger(CaptchaImageCreateController.class);
	private Producer captchaProducer = null;
	public static final String CAPTCHA_KEY = 	"captcha_key";

	@Autowired
	public void setcaptchaProducer(Producer captchaProducer) {
		this.captchaProducer = captchaProducer;
	}

	@RequestMapping("/captcha/captcha-image")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//这些配置不知道干什么用的
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		response.setDateHeader("Expires",0);
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("image/jpeg");

		//生成验证码的内容，create the text for the image
		String captext = captchaProducer.createText();

		//创建一个session,把验证码放入session中，方便进行验证码校验
		HttpSession session = request.getSession(true);
		session.setAttribute(CAPTCHA_KEY,captext);

		//生成图片
		BufferedImage bi = captchaProducer.createImage(captext);
		ServletOutputStream out = response.getOutputStream();

		ImageIO.write(bi,"jpg",out);

		try{
			out.flush();
			logger.debug("session为： " + request.getSession() + " 验证码为： " + captext);
		}finally {
			out.close();
		}

		return null;
	}
}
