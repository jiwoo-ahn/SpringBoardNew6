<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>
<h1>/board/upload.jsp</h1>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">게시판 파일 업로드</h3>
	</div>
	<!-- /.box-header -->
	<!-- form start -->
	<!-- action 해당하는 속성정보가 없음 
		  => 자기 자신의 페이지 호출
		     (/board/regist 호출)
	-->
	
	<!-- 
		파일 업로드 사용을 위한 폼태그 작성 규칙
		1) method는 항상 post 방식
		2) enctype 속성을 multipart/form-data 설정
	 -->
	 
	<form role="form" method="post" enctype="multipart/form-data">
		<div class="box-body">
			<div class="form-group">
				<label for="exampleInputEmail1">제 목</label> 
				<input type="text" name="title" class="form-control" id="exampleInputEmail1" placeholder="제목을 입력하시오.">
			</div>

			<div class="form-group">
				<label for="exampleInputEmail1">작성자</label> 
				<input type="text" name="writer" class="form-control" id="exampleInputEmail1" placeholder="이름을 입력하시오.">
			</div>

			<div class="form-group">
				<label>내용</label>
				<textarea name="content" class="form-control" rows="3" placeholder="내용을 입력하시오."></textarea>
			</div>
			
			<div class="form-group fileDiv">
				<label for="exampleInputEmail1">첨부파일</label> 
				<!-- <input type="file" name="writer" class="form-control" id="exampleInputEmail1"> -->
			</div>
		</div>
		<!-- /.box-body -->

		<div class="box-footer">
			<button type="submit" class="btn btn-primary">파일 업로드</button>
			<button type="button" class="btn btn-danger">파일 추가</button>
		</div>
	</form>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		//alert("jQuery 실행!");
		
		var cnt = 1;
		$(".btn-danger").click(function() {
			alert("파일 추가 버튼 클릭!");
			$(".fileDiv").append(" <input type='file' name='file" + cnt + "' class='form-control' id='exampleInputEmail1'> ");
			cnt ++;
		});
		
	});
</script>

<%@ include file="../include/footer.jsp"%>