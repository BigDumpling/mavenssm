package com.controller;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by ligq01 on 2016/11/1.
 */
public class HelloWorldController implements Controller {

	private Logger logger = Logger.getLogger(this.getClass().getName());
	private String helloWorld;
	private String viewPage;
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Map model = new HashMap();
		System.out.println("-------------" + getHelloWorld());
		System.out.println("-------------" + getViewPage());
		model.put("hello",getHelloWorld());
		return new ModelAndView(getViewPage(),model);
	}

	public String getHelloWorld() {
		return helloWorld;
	}

	public void setHelloWorld(String helloWorld) {
		this.helloWorld = helloWorld;
	}

	public String getViewPage() {
		return viewPage;
	}

	public void setViewPage(String viewPage) {
		this.viewPage = viewPage;
	}
}
