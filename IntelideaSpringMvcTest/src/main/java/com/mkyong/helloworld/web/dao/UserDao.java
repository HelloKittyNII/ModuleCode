package com.mkyong.helloworld.web.dao;

import com.mkyong.helloworld.web.bean.User;

import java.util.List;

/**
 * Created by wzj on 2016/10/7.
 */
public interface UserDao
{
    //根据用户id查询用户信息
    public User findUserById(int id);

}
