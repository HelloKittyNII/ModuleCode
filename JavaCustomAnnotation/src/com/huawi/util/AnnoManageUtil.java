package com.huawi.util;

import com.huawi.annotation.RequestMapping;
import com.huawi.bean.ExecutorBean;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
//import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * Created by wzj on 2016/10/1.
 */
public final class AnnoManageUtil
{
    /**
     * 获取当前包路径下指定的Controller注解类型的文件
     * @param packageName 包名
     * @param annotation 注解类型
     * @return 文件
     */
    public static  List<Class<?>> getPackageController(String packageName, Class<? extends Annotation> annotation)
    {
        List<Class<?>> classList = new ArrayList<Class<?>>();

        String packageDirName = packageName.replace('.', '/');

        Enumeration<URL> dirs = null;

        //获取当前目录下面的所有的子目录的url
        try
        {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        while (dirs.hasMoreElements())
        {
            URL url = dirs.nextElement();

            //得到但钱url的类型
            String protocol = url.getProtocol();

            //如果当前类型是文件类型
            if ("file".equals(protocol))
            {
                //获取包的物理路径
                String filePath = null;
                try
                {
                    filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                }
                catch (UnsupportedEncodingException e)
                {
                    e.printStackTrace();
                }

                filePath = filePath.substring(1);
                getFilePathClasses(packageName,filePath,classList,annotation);
            }
        }


            return classList;
    }

    /**
     * 从指定的包下面找到文件名
     * @param packageName
     * @param filePath
     * @param classList
     * @param annotation 注解类型
     */
    private static void getFilePathClasses(String packageName,String filePath,List<Class<?>> classList,
                                           Class<? extends Annotation> annotation)
    {
        Path dir = Paths.get(filePath);

        DirectoryStream<Path> stream = null;
        try
        {
            //获得当前目录下的文件的stream流
            stream = Files.newDirectoryStream(dir);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        for(Path path : stream)
        {
            String fileName = String.valueOf(path.getFileName());

            String className = fileName.substring(0, fileName.length() - 6);

            Class<?> classes = null;
            try
            {
                classes = Thread.currentThread().getContextClassLoader().loadClass(packageName + "." + className);
            }
            catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }

            //判断该注解类型是不是所需要的类型
            if (null != classes && null != classes.getAnnotation(annotation))
            {
                //把这个文件加入classlist中
                classList.add(classes);
            }
        }
    }

    /**
     * 获取classList下面的RequestMapping方法保存在mapp中
     * @param classList 保存加了Controller的类
     * @param mapp  存放url和ExecutorBean的对应关系
     */
    public static void getRequestMappingMethod(List<Class<?>> classList, Map<String,ExecutorBean> mapp)
    {
        for (Class classes : classList)
        {
            //得到该类下面的所有方法
            Method[] methods = classes.getDeclaredMethods();

            for (Method method : methods)
            {
                //得到该类下面的RequestMapping注解
                RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                if (null != requestMapping)
                {
                    ExecutorBean executorBean = new ExecutorBean();
                    try
                    {
                        executorBean.setObject(classes.newInstance());
                    }
                    catch (InstantiationException e)
                    {
                        e.printStackTrace();
                    }
                    catch (IllegalAccessException e)
                    {
                        e.printStackTrace();
                    }

                    executorBean.setMethod(method);

                    mapp.put(requestMapping.value(),executorBean);

                }
            }
        }
    }

    /**
     * 通知classList类中的beforeEate方法
     * @param classList 包含Controller类的list
     * @param str 字符串
     */
    public static void invokeBeforeEateMethod(List<Class<?>> classList, String str)
    {
        for (Class classes : classList)
        {
            Method method = null;

            try
            {
                //找到定义的beforeEate方法
                method = classes.getMethod("beforeEate",String.class);
            }
            catch (NoSuchMethodException e)
            {
                e.printStackTrace();
            }

            try
            {
                method.invoke(classes.newInstance(),str);
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
            catch (InvocationTargetException e)
            {
                e.printStackTrace();
            }
            catch (InstantiationException e)
            {
                e.printStackTrace();
            }


        }
    }



}
