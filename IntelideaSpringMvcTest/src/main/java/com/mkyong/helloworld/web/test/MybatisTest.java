package com.mkyong.helloworld.web.test;

import com.mkyong.helloworld.web.bean.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wzj on 2016/10/6.
 */
public class MybatisTest
{
    // 根据用户id查询用户信息，通过最原始的方式来读取数据库的数据
    @Test
    public void findUserById() throws IOException {
        // 全局配置文件
        String resource = "jdbc/mybatis-config.xml";

        // 创建配置文件的流
        InputStream inputStream = Resources.getResourceAsStream(resource);

        // 根据配置文件创建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
                .build(inputStream);

        // 创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 通过sqlsession操作数据库
        // 第一个参数：statemnet的id，是namespace+"."+statemnet的id
        // 第二个参数：输入 参数
        User user = sqlSession.selectOne("test.findUserById", 1);

        System.out.println(user);

        // 关闭sqlsession
        sqlSession.close();

    }
}
