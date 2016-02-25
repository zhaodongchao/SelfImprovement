package com.newtouch.securitys;

import org.springframework.security.authentication.encoding.BasePasswordEncoder;
import org.springframework.stereotype.Repository;

/**
 * 自定义md5盐值加密,也可以是其他加密方式，且必须继承SpringSecurity的BasePasswordEncoder
 * 用于对用户密码的MD5加密
 * @author 赵东朝
 * 2015年12月8日
 */
@Repository("Md5Encoder")
public class MD5Encoder extends BasePasswordEncoder{
    
	/**
	 * 对密码进行加密的算法
	 */
	@Override
	public String encodePassword(String origPwd, Object salt) {
       String saltstr = (String) salt ;
		return MD5.getMD5ofStr(origPwd+saltstr);
	}
   /**
    * 密码验证的算法
    */
	@Override
	public boolean isPasswordValid(String encPwd, String origPwd, Object salt) {
		// TODO Auto-generated method stub
		return encPwd.equals(encodePassword(origPwd,salt));
	}

}
