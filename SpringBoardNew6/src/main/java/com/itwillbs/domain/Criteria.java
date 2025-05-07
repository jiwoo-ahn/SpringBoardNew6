package com.itwillbs.domain;

/**
 * 
 * 페이징 처리에 필요한 정보 저장 객체
 * page(시작인덱스), size(크기)
 *
 */

// 롬복 사용하지 않을 것
public class Criteria {
	private int page;	//페이지 정보(몇 페이지인지)
	private int pageSize;	//페이지 크기(한 페이지에 몇개씩 출력할 것인지)
	
	// 기본 생성자
	public Criteria() {
		// 페이징 처리 객체 기본 생성자
		// 기본 페이지로 첫번째 페이지, 10개씩 한페이지에 보여주겠다는 뜻
		this.page = 1;
		this.pageSize = 10;
	}
	
	// alt + shift + s + r -> get/set 메서드
	public void setPage(int page) {
		// 올바르지 않은 페이지일 때
		if(page <= 0) {
			this.page = 1;
			return ;
		}
		this.page = page;
	}
	public void setPageSize(int pageSize) {
		if(pageSize <= 0 || pageSize > 100) {
			this.pageSize = 10;
			return ;
		}
		this.pageSize = pageSize;
	}

	// 객체(VO)의 get 메서드는 mapper에 #{이름} 코드와 연결
	// 객체의 정보를 매퍼로 전달할 수 있음
	// 쿼리(mapper)에서 #{} 형태로 변수를 가져다 쓸 수 있는 것!
	public int getPage() {
		return page;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	// 변수와 상관없이
	// mapper에 값을 전달하기 위한 메서드
	// if문 제어는 setPage에서 했으므로 여기서는 안 해도 됨
	// => 쿼리에서 호출할 때는 메서드 이름에서 get을 떼고 첫글자를 소문자로 바꾸면 된다
	public int getStartPage() {
		// page 번호를 조회할 데이터의 인덱스 번호로 변경하는 계산
		return (this.page - 1) * pageSize;
	}
	
	// alt + shift + s + s
	@Override
	public String toString() {
		return "Criteria [page = " + page + ", pageSize = " + pageSize ;
	}
}