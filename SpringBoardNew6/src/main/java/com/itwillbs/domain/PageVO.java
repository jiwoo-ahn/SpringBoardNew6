package com.itwillbs.domain;

/*  페이징 처리를 위한 객체 */ 
//페이징 블럭을 위한 정보를 저장

/**
 * 
 * 시작페이지 번호 (startPage)
 * startPage = (endPage-pageBlock)+1
 * 
 * 끝 페이지 번호 (endPage)
 * endPage = (int)Math.ceil(page/(double)pageBlock) * pageBlock
 * 
 * 전체 데이터(글) 개수 (totalCount)
 * totalCount => DB에서 조회 & endPage 다시 계산하기
 * 
 * tmpEndPage = (int)Math.ceil(totalCount / (double)pageSize)
 * => 끝페이지와 비교해서 변경  
 * 
 * 이전 페이지 링크 (prev)
 * prev(boolean 타입) = startPage == 1? false : true;
 * prev = startPage != 1; (1이면 false, 1이 아니면 true 반환)
 * 
 * 다음 페이지 링크 (next)
 * next = endPage * pageSize >= totalCount? false : true;
 * next = endPage * pageSize < totalCount; (작으면 true->다음 필요, 크거나 같으면 false->다음불필요)
 *
 */

/*	
	한 페이지에 10개씩 출력
	글이 총 122개 => 13 페이지 필요
	페이지 블럭의 크기 : 10개 (1,2,3, ... , 10 >> 11,12,13)
	
	- 3 페이지
	  startPage 값 : 1, endPage 값 : 10, next 필요, prev 불필요
	  
	- 10 페이지
	  startPage 값 : 1, endPage 값 : 10, next 필요, prev 불필요
	  
	- 11 페이지
	  startPage 값 : 11, endPage 값 : 13, next 불필요, prev 필요
	
 * */

public class PageVO {
	private int totalCount;			// 총 글의 개수
	private int startPage;			// (페이지블럭)시작 페이지 번호
	private int endPage;			// (페이지블럭)끝 페이지 번호
	private boolean prev;			// 이전 버튼
	private boolean next;			// 다음 버튼
	
	private int pageBlock = 5;		// 페이지 블럭의 크기
	
	//private int page;
	//private int pageSize;			// 한 페이지에 출력하는 개수
	// -> Criteria 객체에 있는 정보
	private Criteria cri;
	
	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}
	
	// 내가 만든 페이징 처리 계산 함수
	private void calcData() {
		System.out.println("페이징 처리 계산 시작");
		
		//endPage = (int)Math.ceil(page/(double)pageBlock) * pageBlock
		endPage = (int)Math.ceil(cri.getPage()/(double)pageBlock) * pageBlock;
		
		//startPage = (endPage-pageBlock)+1
		startPage = (endPage-pageBlock)+1;
		
		//endPage 다시 계산하기
		//tmpEndPage = (int)Math.ceil(totalCount / (double)pageSize)
		int tmpEndPage = (int)Math.ceil(totalCount / (double)cri.getPageSize());
		
		if(endPage > tmpEndPage) {
			//endPage값이 내가 가진 페이지수 보다 클 때
			endPage = tmpEndPage;
		}
		
		//prev = startPage != 1;
		prev = startPage != 1;
		
		//next = endPage * pageSize < totalCount;
		next = endPage * cri.getPageSize() < totalCount;
		
		System.out.println("페이징 처리 계산 끝");
	}
	
	public int getTotalCount() {
		return totalCount;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getPageBlock() {
		return pageBlock;
	}

	public void setPageBlock(int pageBlock) {
		this.pageBlock = pageBlock;
	}

	public Criteria getCri() {
		return cri;
	}

	@Override
	public String toString() {
		return "PageVO [totalCount=" + totalCount + ", startPage=" + startPage + ", endPage=" + endPage + ", prev="
				+ prev + ", next=" + next + ", pageBlock=" + pageBlock + ", cri=" + cri + "]";
	}
}