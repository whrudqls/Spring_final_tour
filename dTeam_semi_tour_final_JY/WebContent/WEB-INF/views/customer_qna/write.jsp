<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!-- Content ���� -->
<div class="container mt-5">
	<br> <br> <br> <br> <br>
	<h1>Question</h1>
	<form method="post" action="write">
				<div class="row mb-3">
					<label class="col-sm-2 col-form-label">writer</label>
					<div class="col-sm-10">${sessionScope.sessionID }</div>
				</div>
				<div class="row mb-3">
					<label class="col-sm-2 col-form-label">subject</label>
					<div class="col-sm-10">
						<input type="text" name="qsubject"
							placeholder="subject�� �Է��ϼ���." />
					</div>
				</div>
				<div class="row mb-3">
					<label class="col-sm-2 col-form-label">content</label>
					<div class="col-sm-10">
						<textarea name="qcontent" rows="10" cols="100" placeholder="content�� �Է��ϼ���."></textarea>
					</div>
				</div>
				<div class="row mb-3 ">
					<div class="col-sm-10">
						<input type="submit" value="���" id="btn1" class="btn btn-info" />
						<input type="button" value="����Ʈ" id="listBtn"
							class="btn btn-danger" />
					</div>
				</div>

			</form>
</div>