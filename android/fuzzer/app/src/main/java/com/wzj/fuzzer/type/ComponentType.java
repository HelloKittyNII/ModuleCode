package com.wzj.fuzzer.type;

/**
 * Created by wzj on 2017/10/14.
 */
public enum ComponentType
{
    /**
     * Activity
     */
    ACTIVITY("Activity"),

    /**
     * Receiver
     */
    RECEIVER("Receiver"),

    /**
     * Service
     */
    SERVICE("Service");

    /**
     * 显示状态
     */
    private String type;

    ComponentType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }
}
