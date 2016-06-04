package com.hank.helloworld;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.hank.pojo.User;

public class MybaticHelloworld {
	// 根据id查询用户信息
	@Test
	public void findUserByIdTest() {
		// mybatis配置文件
		String resource = "SqlMapConfig.xml";
		SqlSession session = null;
		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);

			// 创建会话工厂
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
					.build(inputStream);
			// 通过工厂得到SqlSession
			session = sqlSessionFactory.openSession();
			// 通过SqlSession操作数据库
			// 第一个参数：映射文件User.xml中statement的id,格式：namespace+statement的ID；
			// 第二个参数：指定和映射文件中所指定的parameterType类型的参数
			User user = session.selectOne("query.findUserById", 1);
			System.out.println(user.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 释放会话资源
		session.close();
	}
}
