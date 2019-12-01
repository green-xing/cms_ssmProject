package com.briup.config;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.briup.utils.CustomerException;
import com.briup.utils.Message;
import com.briup.utils.MessageUtil;

@RestControllerAdvice
public class CustomerExceptionHandler {

	@ExceptionHandler(value=Exception.class)//捕获controller中抛出的指定类型的异常类，也可以指定其他异常
	public <E> Message handler(Exception exception) {
		exception.printStackTrace();
		if(exception instanceof CustomerException) {
			return MessageUtil.error(exception.getMessage());
		}
		return MessageUtil.error("后台接口异常");
	}
	
	
}
