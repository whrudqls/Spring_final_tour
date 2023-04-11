<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!-- Content 영역 -->
<div class="container mt-5">
	<br> <br> <br> <br> <br>
	<h1>Question</h1>
	<table class="table table-bordered table-hover">
		<thead>
			<tr>
				<th colspan="5" style="text-align: center; font-size: 20px;">No.${qvo.qnum}</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th>Writer</th>
				<td colspan="5" class="text-center">${qvo.qwriter}</td>
			</tr>
			<tr>
				<th>Subject</th>
				<td colspan="5" class="text-center">${qvo.qsubject}</td>
			</tr>
			<tr>
				<th>Content</th>
				<td colspan="5" class="text-center">${qvo.qcontent}</td>
			</tr>
			<tr>
				<th>Date</th>
				<td colspan="5" class="text-center">${qvo.qdate}</td>
			</tr>
		</tbody>
		<tfoot>

		</tfoot>
	</table>

	<h1>Answer</h1>
	<form method="post"
		action="answer?acode=${qvo.qnum}">
		<div class="row mb-3">
			<input type="hidden" name="awriter" value="${sessionScope.sessionID }">
			<label class="col-sm-2 col-form-label">writer</label>
			<div class="col-sm-10">${sessionScope.sessionID }</div>
		</div>
		<div class="row mb-3">
			<label class="col-sm-2 col-form-label">content</label>
			<div class="col-sm-10">
				<textarea name="acontent" rows="10" cols="100"
					placeholder="content를 입력하세요."></textarea>
			</div>
		</div>
		<div class="row mb-3 ">
			<div class="col-sm-10">
				<input type="submit" value="등록" id="btn1" class="btn btn-info" /> <input
					type="button" value="리스트" id="listBtn" class="btn btn-danger" />
			</div>
		</div>
	</form>
</div>