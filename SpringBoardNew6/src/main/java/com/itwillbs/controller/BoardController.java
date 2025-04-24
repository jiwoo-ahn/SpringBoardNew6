package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		logger.info("글쓰기 뷰페이지(/board/regist.jsp)를 연결해서 보여줌");
	}
	
	// 게시판 글쓰기 - POST
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String registPOST(BoardVO vo, RedirectAttributes rttr) throws Exception{
		logger.info(" registPOST() 실행! ");
		
		// 인코딩 데이터 처리 => web.xml 처리
		
		// 폼태그에서 전달된 파라메터를 저장 & 출력
		logger.info("vo : {}", vo);
		
		// 서비스 호출 - 게시판 글쓰기 동작을 처리(DAO 호출)
		bService.registBoard(vo);
		
		// 정보를 listAll로 하나 전달 - createAll
		// 글쓰고 listAll 페이지로 왔다는 것을 구분하기 위해
		rttr.addFlashAttribute("result", "createOK");
		// => 1회성 데이터 전달(Flash) => 주소줄에 안 남음, 새로고침하면 콘솔에서 사라지는 걸 볼 수 있음
		// 계속 유지하고 싶으면 그냥 addAttribute => 주소줄에 남음
		
		// 게시판 글 목록 페이지로 이동
		
		// 연결되어있는 뷰페이지 이름을 listAll.jsp로 만들어라 라는 뜻 (주소 안바뀌는데(regist, 글쓰기) 화면만 list 화면)
		// return "/board/listAll";
		
		// 이 동작이 끝나면 이 주소로 이동해라 라는 뜻 (주소도 바뀌고 화면도 바뀜)
		return "redirect:/board/listAll";
	}
	
	// 게시판 목록 - GET
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	// @GetMapping(value="/listAll")
	// @GetMapping은 4.3.X 버전대 부터 사용 가능
	// @RequestMapping(value = "/listAll")
	// 도 가능 기본이 GET 이기 때문
	public void listAllGET(HttpSession session, 
			               Model model, 
			               @ModelAttribute("result") String result) throws Exception{
		logger.info("listAllGET() 실행");
		
		// 전달정보 result 저장
		logger.info("result : " + result);
		
		// DB 데이터를 가져와서 화면(view)에 출력
		// => 서비스 -> DAO 호출
		List<BoardVO> boardList = bService.getBoardListAll();
		
		logger.info("boardList : {} 개", boardList.size());
		
		// => 생성된 데이터를 뷰페이지에 전달 (컨트롤러의 정보를 -> jsp : Model객체)
		model.addAttribute("boardList", boardList);
		
		// 조회수 증가해도 되는지 안되는지 체크하기 위한 용도
		// list에서 read 로 왔을 때만 true => 조회수 증가
		// session 영역에 정보를 저장 & 전달
		session.setAttribute("updateCheck", true);
		
		// 연결된 뷰페이지로 이동(/board/listAll.jsp)
		
	}
	
	// 게시판 본문보기 GET
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String readGET(HttpSession session, Model model, @RequestParam("bno") int bno) throws Exception{
		
		logger.info("readGET() 실행");
		
		// 서비스 -> DAO -> mapper 호출

		// 전달정보(bno)를 저장(글의 정보를 구분할 수 있는 키워드를 받아와야 함)
		logger.info("bno : {}", bno);
		
		// 리스트 -> 본문 이동 시마다 조회수가 증가
		// (본문에서 새로고침 수행시 조회수 증가 X)
		boolean updateCheck = (boolean)session.getAttribute("updateCheck");
		if(updateCheck) {
			// 서비스 -> 글 조회수를 1씩 증가 동작
			bService.increaseViewCnt(bno);
			session.setAttribute("updateCheck", false);
		}
		
		// 서비스 -> 글 하나의 정보를 조회하는 동작 호출
		BoardVO vo = bService.getBoard(bno);
		logger.info("vo : {}", vo);
		
		// DAO에서 받아온 글 정보를 연결된 뷰페이지(/board/read.jsp)로 이동
		model.addAttribute(vo);
		model.addAttribute(bService.getBoard(bno));
		
		return "/board/read";
	}
	
	// 글정보 수정하기-GET
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyGET(Model model, @ModelAttribute("bno") int bno) throws Exception{
		logger.info("modifyGET() 실행");
		
		// 전달정보 저장(파라메터)
		logger.info("bno : {}", bno);
		
		// bno를 사용해서 정보를 DB에서 가져오기
		BoardVO vo = bService.getBoard(bno);
		
		// 연결된 뷰페이지에 출력
		model.addAttribute(vo);
	}
	
	// 글정보 수정하기-POST
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(RedirectAttributes rttr,
			/*@ModelAttribute*/ BoardVO uvo) throws Exception {
		logger.info("modifyPOST() 실행 ");
		
		// 한글처리 인코딩 생략 -> 필터처리 해놨음(web.xml)
		
		// 전달된 정보(수정할 내용 - 파라메터) 저장 
		// => 매개변수에 BoardVO를 적어줌으로써 파라메터 자동 수집
		logger.info("uvo : " + uvo);
		
		// 서비스 - 사용자 게시판 글 수정하는 메서드 호출
		bService.modifyBoard(uvo);
		logger.info("게시판 수정 성공");
		
		// 리스트(글 목록) 페이지로 이동 + "수정 완료" alert 출력
		rttr.addFlashAttribute("result", "modifyOK");
		
		return "redirect:/board/listAll";
	}
	
}