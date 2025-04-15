<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>
	<!-- 참고하는 페이지의 요소와 class를 똑같이 주면 css도 똑같이 적용된다 -->
	<div class="content">
		<h1>listAll.jsp</h1>
		${result}
	</div>
	
	<script type="text/javascript">
		// JS에서 EL 데이터는 변수처럼 동작
		// EL 데이터 -> JS에서 출력하려면 ''를 붙여줘야 함
		var result = '${result}';
		//alert(result);
		
		if(result == "createOK"){
			alert(" 글쓰기 완료! ");
		}
	</script>

<%@ include file="../include/footer.jsp"%>