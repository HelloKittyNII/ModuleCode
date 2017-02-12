package com.mkyong.helloworld.web.service.impl;

import com.mkyong.helloworld.web.service.HelloWorldService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 服务类
 */
@Service
public class HelloWorldServiceImpl implements HelloWorldService
{

	private static final Logger logger = LoggerFactory.getLogger(HelloWorldServiceImpl.class);

	/**
	 * 得到描述信息
	 * @return 描述信息字符串
     */
	public String getDesc()
	{

		logger.debug("getDesc() is executed!");

		return "Gradle + Spring MVC Hello World Example";

	}

	/**
	 * 得到标题
	 * @param name 标题名字
	 * @return 返回标题名字
     */
	public String getTitle(String name)
	{

		logger.debug("getTitle() is executed! $name : {}", name);

		if(StringUtils.isEmpty(name))
		{
			return "Hello World";
		}
		else
		{
			return "Hello " + name;
		}
		
	}

}