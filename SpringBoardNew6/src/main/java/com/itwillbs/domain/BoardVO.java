package com.itwillbs.domain;

import java.sql.Timestamp;

import lombok.Data;

/*
 * tbl_board 테이블의 정보를 처리하기 위한 객체
 * 가능하면 변수의 이름과 테이블 컬럼의 이름을 동일하게 만들기
 * 
 * */

@Data
// DB의 tbl_board 테이블 참조
public class BoardVO {
	private int bno;
	private String title;
	//DB에서 TEXT 타입도 String으로 커버 가능
	private String content;
	private String writer;
	private int viewcnt;
	private Timestamp regdate;
}
