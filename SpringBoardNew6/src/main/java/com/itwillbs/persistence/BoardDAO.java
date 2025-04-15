package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.BoardVO;

/**
 * DB의 정보를 처리하는 객체
 * 	=> DB에서 수행해야 하는 동작(메서드)를 정의
 */
public interface BoardDAO {
	
	// DB 서버의 시간 정보를 가져오기
	public String getServerTime();
	
	// 게시판에 글 정보를 저장하는 동작(글쓰기)
	public void insertBoard(BoardVO vo) throws Exception;
	
	// 게시판 글 전체 목록 조회 동작 (글조회)
	public List<BoardVO> selectBoardListAll() throws Exception;
	
	// 게시판 특정 글 정보를 조회
	public BoardVO selectBoard(int bno) throws Exception;
	
	// 특정 글 조회수 1증가
	public void updateViewCnt(int bno) throws Exception;
}
