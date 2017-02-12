package com.huawi.controller;

import com.huawi.annotation.Controller;
import com.huawi.annotation.RequestMapping;

/**
 * Created by wzj on 2016/10/1.
 */
@Controller
public class DogController
{

    public void beforeEate(String str)
    {
        System.out.println("DogController->beforeEate() " + str);
    }

    @RequestMapping(value = "/test3")
    public void test3()
    {
        System.out.println("DogController->test3()");
    }

    @RequestMapping(value = "/test4")
    public void test4()
    {
        System.out.println("DogController->test4()");
    }
}
