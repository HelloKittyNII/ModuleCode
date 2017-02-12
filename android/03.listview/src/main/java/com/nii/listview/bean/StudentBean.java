package com.nii.listview.bean;

/**
 * Created by wzj on 2017/2/12.
 */
public class StudentBean
{
    private int id;

    private String name;

    private int score;

    private String desc;

    public StudentBean()
    {

    }

    public StudentBean(String name, String desc)
    {
        this.name = name;
        this.desc = desc;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }
}
