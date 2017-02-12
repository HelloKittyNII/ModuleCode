package com.mkyong.helloworld.web.controller;

import java.util.Map;

import com.mkyong.helloworld.web.bean.User;
import com.mkyong.helloworld.web.dao.UserDao;
import com.mkyong.helloworld.web.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mkyong.helloworld.web.service.impl.HelloWorldServiceImpl;

@Controller
public class WelcomeController
{

	private final Logger logger = LoggerFactory.getLogger(WelcomeController.class);
	private final HelloWorldServiceImpl helloWorldService;

	@Autowired
	UserDao userDao;

	@Autowired
	public WelcomeController(HelloWorldServiceImpl helloWorldService)
	{
		this.helloWorldService = helloWorldService;
	}

	/**
	 * 输入http://localhost:8080/spring4/hello
	 * @param model model
	 * @return 页面
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Map<String, Object> model)
	{

		logger.debug("index() is executed!");

		model.put("title", helloWorldService.getTitle(""));
		model.put("msg", helloWorldService.getDesc());

		return "index";
	}

	/**
	 * 输入http://localhost:8080/spring4/hello/name
	 * @param name 名字
	 * @return 把页面返回
	 */
	@RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
	public ModelAndView hello(@PathVariable("name") String name)
	{

		logger.debug("hello() is executed - $name {}", name);

		ModelAndView model = new ModelAndView();
		model.setViewName("index");

		model.addObject("title", helloWorldService.getTitle(name));
		model.addObject("msg", helloWorldService.getDesc());

		return model;

	}

	/**
	 * 测试整合的mybatis
	 * 输入http://localhost:8080/spring4/mybatis
	 * @return 把页面返回
	 */
	@RequestMapping(value = "/mybatis", method = RequestMethod.GET)
	public ModelAndView mybatis()
	{

		logger.debug("hello() is executed - $name {}");

		ModelAndView model = new ModelAndView();
		model.setViewName("index");

		User user = null;

		user = userDao.findUserById(1);


		System.out.println(user);

		model.addObject("title", helloWorldService.getTitle("mybatis"));
		model.addObject("msg", user.toString());

		return model;

	}

}