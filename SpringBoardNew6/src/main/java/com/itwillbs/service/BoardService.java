package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.BoardVO;

/**
 * 	서비스의 영역 - 동작의 제어가 필요한 경우 처리
 */
public interface BoardService {
	public String getServerTime();
	
	//게시판 글쓰기
	public void registBoard(BoardVO vo) throws Exception;
	
	//게시판 글 전체 목록 조회
	public List<BoardVO> getBoardListAll() throws Exception;
}