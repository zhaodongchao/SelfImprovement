package com.newtouch.test;

import org.junit.Test;

import com.newtouch.securitys.MD5;

public class TestMd5 {
	@Test
  public void testMd5(){
	  System.out.println(MD5.getMD5ofStr("123newtouch"));
  }
}
