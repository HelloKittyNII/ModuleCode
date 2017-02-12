package com.mkyong.helloworld.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by wzj on 2016/10/7.
 */
@Controller
public class JspController
{
    /**
     * 输入http://localhost:8080/spring4/template
     * @return 页面
     */
    @RequestMapping(value = "/template", method = RequestMethod.GET)
    public String index()
    {


        return "template";
    }
}
