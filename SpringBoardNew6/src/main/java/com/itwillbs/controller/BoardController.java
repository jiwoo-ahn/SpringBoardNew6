package com.itwillbs.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.service.BoardService;

@Controller
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	//BoardService 객체 주입
	@Inject
	private BoardService bService;
	
	@RequestMapping(value = "/time", method = RequestMethod.GET)
	public void getServerTimeGET(Model model) {
		logger.info(" DB 서버의 시간 정보를 가져오기");
		String time = bService.getServerTime();
		logger.info("time : {}", time);
		
		model.addAttribute("time", time);
		logger.info("연결된 뷰페이지에 정보 전달");
	}
}