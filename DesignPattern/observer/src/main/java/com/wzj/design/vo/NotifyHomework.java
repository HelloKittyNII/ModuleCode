package com.wzj.design.vo;

import com.google.gson.Gson;

/**
 * Created by wzj on 2016/11/21.
 * 通知的家庭作业
 */
public class NotifyHomework
{
    private String math;

    private String english;

    public NotifyHomework(String math, String english)
    {
        this.math = math;
        this.english = english;
    }

    public String getMath()
    {
        return math;
    }

    public void setMath(String math)
    {
        this.math = math;
    }

    public String getEnglish()
    {
        return english;
    }

    public void setEnglish(String english)
    {
        this.english = english;
    }

    @Override
    public String toString()
    {
        return new Gson().toJson(this);
    }
}
