package com.huawi.controller;

import com.huawi.annotation.Controller;
import com.huawi.annotation.RequestMapping;

/**
 * Created by wzj on 2016/10/1.
 */
@Controller
public class CatController
{

    public void beforeEate(String str)
    {
        System.out.println("CatController->beforeEate() " + str);
    }

    @RequestMapping(value = "/test1")
    public void test1(String str)
    {
        System.out.println("CatController->test1() " + str);
    }

    @RequestMapping(value = "/test2")
    public void test2()
    {
        System.out.println("CatController->test2()");
    }
}
