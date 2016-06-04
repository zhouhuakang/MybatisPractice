package com.hank.helloworld;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

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
			User user = session.selectOne("helloworld.findUserById", 1);
			System.out.println(user.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 释放会话资源
		session.close();
	}

	// 根据用户名进行模糊查询
	@Test
	public void findUserByNameTest() {
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
			List<User> users = session.selectList("helloworld.findUserByName",
					"小");
			for (User user : users) {
				System.out.println(user.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 释放会话资源
		session.close();
	}

	@Test
	public void insertUserTest() throws IOException {
		// Mybatis配置文件名
		String resource = "SqlMapConfig.xml";
		InputStream inputstream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(inputstream);
		SqlSession sqlSession = sqlSessionFactory.openSession();

		User user = new User();
		user.setUsername("Hank");
		user.setBirthday(new Date());
		user.setSex("1");
		user.setAddress("杭州");

		sqlSession.insert("helloworld.insertUser", user);
		// 提交事务
		sqlSession.commit();

		System.out.println(user.getId());
		sqlSession.close();

	}

	// 根据id删除用户
	@Test
	public void deleteUserTest() throws IOException {
		// Mybatis配置文件名
		String resource = "SqlMapConfig.xml";
		InputStream inputstream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(inputstream);
		SqlSession sqlSession = sqlSessionFactory.openSession();

		sqlSession.delete("helloworld.deleteUser", 28);
		// 提交事务
		sqlSession.commit();

		sqlSession.close();

	}

	// 更新用户记录
	@Test
	public void updateUserTest() throws IOException {
		// Mybatis配置文件名
		String resource = "SqlMapConfig.xml";
		InputStream inputstream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(inputstream);
		SqlSession sqlSession = sqlSessionFactory.openSession();

		User user = new User();
		// 根据id更新记录，则必须设置id字段
		user.setId(27);
		user.setUsername("KK");
		user.setBirthday(new Date());
		user.setSex("1");
		user.setAddress("金华");

		sqlSession.insert("helloworld.updateUser", user);
		// 提交事务
		sqlSession.commit();

		System.out.println(user.getId());
		sqlSession.close();

	}
}
