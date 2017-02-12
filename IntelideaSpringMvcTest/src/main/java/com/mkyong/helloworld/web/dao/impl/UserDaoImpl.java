package com.mkyong.helloworld.web.dao.impl;

import com.mkyong.helloworld.web.bean.User;
import com.mkyong.helloworld.web.dao.UserDao;
import com.mkyong.helloworld.web.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wzj on 2016/10/7.
 */
@Service
public class UserDaoImpl  implements UserDao
{
    @Autowired
    UserMapper userMapper;

    @Override
    public User findUserById(int id)
    {
        return userMapper.findUserById(id);
    }


}
