package com.huawi.test;

import com.huawi.annotation.Controller;
import com.huawi.bean.ExecutorBean;
import com.huawi.util.AnnoManageUtil;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args)
    {
        //利用jdk的反射功能，来调用加了注解的函数
        List<Class<?>> classesList = null;
        classesList = AnnoManageUtil.getPackageController("com.huawi.controller",Controller.class);

        Map<String,ExecutorBean> mmap = new HashMap<String,ExecutorBean>();

        AnnoManageUtil.getRequestMappingMethod(classesList,mmap);

        ExecutorBean bean = mmap.get("/test1");
        try
        {
            bean.getMethod().invoke(bean.getObject(),"hello");
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            e.printStackTrace();
        }

        //去调用classesList中的函数
        AnnoManageUtil.invokeBeforeEateMethod(classesList,"invoke test");

    }
}
