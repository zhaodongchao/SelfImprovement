package com.newtouch.test;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TestString {
    @Test
	public void testRandStr(){
		System.out.println(new BigInteger(165, new Random()).toString(36).toUpperCase());
	}
    
    @Test
    public void testStringToMap(){
    	
    	String str = "{newtouch.token.K1RO50IQY1EQY4C161MYR54V0XPGAKP2=K1RO50IQY1EQY4C161MYR54V0XPGAKP2, newtouch.token.NI6LJY2HCPZPXK3D3ZG0A0NFD93VMOXD=NI6LJY2HCPZPXK3D3ZG0A0NFD93VMOXD}";
         str = str.replaceAll("=", ":");
        
    	//JSONArray jsa = JSONArray.fromObject(str);
         JSONObject jso = JSONObject.fromObject(str);
         System.out.println(jso);
    }
    @Test
    public void testRandUtils(){
    	
    	System.out.println(RandomStringUtils.randomAscii(36).toUpperCase());
    }
    @Test
    public void testDate(){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
      System.out.println(sdf.format(""));
    }
}
