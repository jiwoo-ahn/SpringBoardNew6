package com.itwillbs.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.service.BoardService;

@Controller
@RequestMapping(value = "/board/*")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	//BoardService 객체 주입
	@Inject
	private BoardService bService;
	
	// http://localhost:8088/board/time
	@RequestMapping(value = "/time", method = RequestMethod.GET)
	public void getServerTimeGET(Model model) {
		logger.info(" DB 서버의 시간 정보를 가져오기");
		String time = bService.getServerTime();
		logger.info("time : {}", time);
		
		model.addAttribute("time", time);
		logger.info("연결된 뷰페이지에 정보 전달");
	}
	
	// 게시판 글쓰기 - GET
	// http://localhost:8088/board/regist
	@RequestMapping(value = "/regist", method = RequestMethod.GET)
	public void registGET() throws Exception{
		logger.info("registGET() 실행");
		logger.info("글쓰기 뷰페이지(/board/regist.jps)를 연결해서 보여줌");
	}
	
	// 게시판 글쓰기 - POST
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String registPOST(BoardVO vo) throws Exception{
		logger.info(" registPOST() 실행! ");
		
		// 인코딩 데이터 처리 => web.xml 처리
		
		// 폼태그에서 전달된 파라메터를 저장 & 출력
		logger.info("vo : {}", vo);
		
		// 서비스 호출 - 게시판 글쓰기 동작을 처리(DAO 호출)
		bService.registBoard(vo);
		
		// 게시판 글 목록 페이지로 이동
		
		// 연결되어있는 뷰페이지 이름을 listAll.jsp로 만들어라 라는 뜻 (주소 안바뀌는데(regist, 글쓰기) 화면만 list 화면)
		// return "/board/listAll";
		
		// 이 동작이 끝나면 이 주소로 이동해라 라는 뜻 (주소도 바뀌고 화면도 바뀜)
		return "redirect:/board/listAll";
	}
}