package com.itwillbs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 *  AOP(관점지향프로그래밍) 개념 사용
 *  보조기능을 구현
 *  
 *  => 공통의 예외를 처리하는 객체
 *
 */

//@ControllerAdvice
// => 컨트롤러에서 발생한 예외(보조기능)을 처리하는 객체 선언
@ControllerAdvice
public class CommonExceptionAdvice {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonExceptionAdvice.class);
	
	//@ExceptionHandler(Exception.class) => 모든 예외를 전부 처리할 수 있는 객체 (모든 예외에서 업캐스팅 가능함)
	@ExceptionHandler(Exception.class)
	public String common(Model model,Exception e) {
		logger.info("CommonExceptionAdvice_common() 실행");
		logger.info("예외발생!");
		
		logger.info("" + e.toString());
		model.addAttribute("e", e);
		
		//ModelAndView 객체 사용해서 전달 => Model과 View를 한번에 저장
		//		ModelAndView mav = new ModelAndView();
		//		mav.setView(viewName);
		//		mav.addObject(attributeValue);
		
		return "ITWILL";
	}
	
	//@ExceptionHandler(NumberFormatException.class)
	//@ExceptionHandler(NullPointerException.class) => 
	@ExceptionHandler(NullPointerException.class)
	public String common2() {
		logger.info("CommonExceptionAdvice_common() 실행");
		logger.info("NumberFormatException 예외발생!");
		return "ITWILL";
	}
}