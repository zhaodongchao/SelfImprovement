package com.webserivce;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class TestConnection {
   public static void main(String[] args){
	   try{
	  // String url = "http://127.0.0.1:8080/axis/HelloJWS.jws" ;
	   String url = "http://127.0.0.1:8080/axis/services/Hello" ;
	   //创建一个webService服务
	   Service service = new Service();
	   
	   //创建一个服务的调用
	   Call cal = (Call) service.createCall();
	   
	   //指定服务来源
	   cal.setTargetEndpointAddress(url);
	   
	   //指定调用的具体的方法名称
	   cal.setOperationName(new QName(url, "sayHellon"));
	   
	   Object result=  cal.invoke(new Object[]{new String("xiaochu"),new String("女")});
	   System.out.println(result.toString());
	   }catch(Exception e){
		   
	   }
   }
}
