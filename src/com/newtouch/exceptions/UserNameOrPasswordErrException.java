package com.newtouch.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.FORBIDDEN,reason="用户或密码错误")
public class UserNameOrPasswordErrException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8298474089890802667L;

}
