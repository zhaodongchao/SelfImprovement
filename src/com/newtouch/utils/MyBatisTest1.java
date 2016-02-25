package com.newtouch.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.newtouch.entitys.Menu;

public class MyBatisTest1 {
	@Test
   public void test1(){
		// mybatis配置文件
		String resource = "mybatis.xml";
		
		try {
			// 得到配置文件流
			InputStream inputStream = Resources.getResourceAsStream(resource);
		
		    //创建回话工厂
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			
			// 创建一个回话
		    SqlSession sqlSession= sqlSessionFactory.openSession();
		    
		    //执行操作：
		  List<Menu> alls =   sqlSession.selectList("Menu.findMenu", "root");
		} catch (IOException e) {
			e.printStackTrace();
		}
   }
	
}
