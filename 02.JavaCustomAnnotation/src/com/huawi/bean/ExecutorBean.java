package com.huawi.bean;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * Created by wzj on 2016/10/1.
 */
public class ExecutorBean
{
    private Object object;

    private Method method;

    public Object getObject()
    {
        return object;
    }

    public void setObject(Object object)
    {
        this.object = object;
    }

    public Method getMethod()
    {
        return method;
    }

    public void setMethod(Method method)
    {
        this.method = method;
    }
}
