package com.newtouch.handlers;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.HttpException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newtouch.utils.SendMessageUtil;

@Controller
@RequestMapping("/phone/")
public class PhoneMessageHandler {

	private static final String VALIDATE_PHONE_CODE = "VALIDATE_PHONE_CODE";
	private static final String VALIDATE_PHONE = "VALIDATE_PHONE";
	private static final String SEND_CODE_TIME = "SEND_CODE_TIME";


	@RequestMapping("sendCode")
	@ResponseBody
	protected void sendCode(String phone, HttpServletRequest request) throws HttpException, IOException{
		StringBuilder code = new StringBuilder();
		Random random = new Random();
		// 6位验证码
		for (int i = 0; i < 6; i++) {
			code.append(String.valueOf(random.nextInt(10)));
		}
		HttpSession session = request.getSession();
		session.setAttribute(VALIDATE_PHONE, phone);
		session.setAttribute(VALIDATE_PHONE_CODE, code.toString());
		session.setAttribute(SEND_CODE_TIME, new Date().getTime());
		//String smsText = "验证码：" + code;
		String smsText = "孕妈妈健康小贴士："+
						   "1、三餐需准时，饮食需均衡,睡眠要充足。"+
						   "2、睡前用热水泡脚；调整好睡姿，尽可能采用左侧卧位。"+
					       "3、伸懒腰的时候两脚不要伸太直；注意下肢保暖；平时多晒太阳。";
		System.out.println("手机号：" + phone + ", " + smsText);
		SendMessageUtil.sendMessage(phone, smsText);
	}

	@RequestMapping("validate.html")
	@ResponseBody
	protected String validate(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String code = (String) session.getAttribute(VALIDATE_PHONE_CODE);
		String phone = (String) session.getAttribute(VALIDATE_PHONE);
		String inputCode = request.getParameter("code");
		String inputPhone = request.getParameter("phone");
		if (phone.equals(inputPhone) && code.equalsIgnoreCase(inputCode)) {
			return "success";
		} else {
			return "failure";
		}
	}
}
