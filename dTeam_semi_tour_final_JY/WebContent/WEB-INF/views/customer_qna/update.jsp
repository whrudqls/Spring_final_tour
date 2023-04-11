<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- Content 영역 -->
<div class="container mt-5">
	<br> <br> <br> <br> <br>
	<h1>Question</h1>
	<form action="update" method="post">
				<div class="row mb-3">
					<input type="hidden" name="qwriter" value="${sessionScope.sessionID }">
					<label class="col-sm-2 col-form-label">작성자</label>
					<div class="col-sm-10">${sessionScope.sessionID }</div>
				</div>
				<div class="row mb-3">
					<label class="col-sm-2 col-form-label">제목</label>
					<div class="col-sm-10">
						<input type="text" name="qsubject"
							placeholder="subject를 입력하세요." />
					</div>
				</div>
				<div class="row mb-3">
					<label class="col-sm-2 col-form-label">내용</label>
					<div class="col-sm-10">
						<textarea name="qcontent" rows="10" cols="100" placeholder="content를 입력하세요."></textarea>
					</div>
				</div>
				<div class="row mb-3 ">
					<div class="col-sm-10">
						<input type="submit" value="등록" id="btn1" class="btn btn-info" />
						<a href="pro.kosmo?cmd=qna&scmd=qnalist"><input type="button" value="리스트" id="listBtn"
							class="btn btn-danger" /></a>
					</div>
				</div>

			</form>
</div>