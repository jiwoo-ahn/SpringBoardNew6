<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../include/header.jsp"%>
	<!-- 참고하는 페이지의 요소와 class를 똑같이 주면 css도 똑같이 적용된다 -->
	<div class="content">
		<h1>listAll.jsp</h1>
		${result}
		<%-- ${boardList} --%>
		<!-- 게시판 목록 출력 -->
		<div class="box">
            <div class="box-header with-border">
              <h3 class="box-title">ITWILL 게시판 목록</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table class="table table-bordered">
                <tbody><tr>
                  <th style="width: 10px">BNO</th>
                  <th>TITLE</th>
                  <th>WRITER</th>
                  <th>REGDATE</th>
                  <th style="width: 40px">VIEWCNT</th>
                </tr>
                <c:forEach var="vo" items="${boardList}">
                <tr>
                  <td>${vo.bno}</td>
                  <td>
                  	<a href="/board/read?bno=${vo.bno}">${vo.title}</a>
                  </td>
                  <td>${vo.writer}</td>
                  <td><fmt:formatDate value="${vo.regdate}"/></td>
                  <td><span class="badge bg-red">${vo.viewcnt}</span></td>
                </tr>
                </c:forEach>
              </tbody></table>
            </div>
            <!-- /.box-body -->
            <div class="box-footer clearfix">
              <ul class="pagination pagination-sm no-margin pull-right">
                <li><a href="#">«</a></li>
                <li><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">»</a></li>
              </ul>
            </div>
          </div>
	</div>
	
	<script type="text/javascript">
		// JS에서 EL 데이터는 변수처럼 동작
		// EL 데이터 -> JS에서 출력하려면 ''를 붙여줘야 함
		var result = '${result}';
		//alert(result);
		
		if(result == "createOK"){
			//alert(" 글쓰기 완료! ");
			Swal.fire({
				  title: "글쓰기 완료!",
				  text: "게시판 리스트로 이동합니다",
				  icon: "success"
				});
			
		}
		
		if(result == "modifyOK"){
			Swal.fire("글 수정 완료!");
		}
	</script>
	
	<!-- 헤더에 추가해 놓음 -->
	<!-- <script src="~~/jQuery-2.1.4.min.js"></script> -->
	<script type="text/javascript">
	// JS사용
	// 사용가능
	// Swal.fire("SweetAlert2 is working!");
	
	//제이쿼리(jQuery) 사용 선언
		/* $(document).ready(function(){
			Swal.fire("SweetAlert2 is working!");
		}); */
	</script>

<%@ include file="../include/footer.jsp"%>