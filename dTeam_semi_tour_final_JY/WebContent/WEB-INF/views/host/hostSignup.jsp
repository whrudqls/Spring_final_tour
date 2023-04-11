<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!-- 2023. 1. 19. / Kosmo -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctrpath" value="${pageContext.request.contextPath}" />

<ul class="list-unstyled">
	<li class="border-top my-3"></li>
</ul>
<div class="container">
	<h3>Host ȸ������</h3>
	<form action="hsignUpProcess" method="post" id="signUpInfo">
		<div class="form-group">
			<label for="hname">�̸�</label> <input type="text" class="form-control"
				id="hname" placeholder="�̸� �Է�" name="hname" maxlength="10"
				required="required" pattern=".{2,10}">
		</div>
		<div class="form-group">
			<label for="hid">ID</label> <input type="text" class="form-control"
				id="hid" placeholder="���̵� �Է�" name="hid" maxlength="10"
				required="required" pattern=".{2,10}">

			<button type="button" class="btn btn-primary" id="hidchk">�ߺ�Ȯ��</button>
			<div id="target"></div>

		</div>
		<div class="form-group">
			<label for="hpwd">PWD</label> <input type="password"
				class="form-control" id="hpwd" placeholder="******" name="hpwd"
				maxlength="10" required="required" pattern=".{2,10}">
		</div>
		<!-- �ڵ�����ȣ �޴� �������� �ޱ� -->
		<div class="form-group">
			<label for="hphone">����ó</label> <input type="text"
				class="form-control" id="hphone" placeholder="����ó �Է�" name="hphone"
				maxlength="15" required="required">
		</div>
		<div class="form-group">
			<label for="haddr">�ּ�</label> <input type="text" class="form-control"
				id="haddr" placeholder="�ּ� �Է�" name="haddr" required="required">
		</div>
		<div class="form-group">
			<label for="hemail">�̸���</label> <input type="text"
				class="form-control" id="hemail" placeholder="�̸��� �Է�" name="hemail"
				required="required">
		</div>
		<div class="form-group">
			<label for="hduns">����ڹ�ȣ</label> <input type="text"
				class="form-control" id="hduns" placeholder="����ڹ�ȣ �Է�" name="hduns">
		</div>
		<div class="form-group">
			<label for="haccount">���� ���</label> <input type="text"
				class="form-control" id="haccount" placeholder="�������"
				name="haccount" required="required">
		</div>
		<!-- 
		<input type="hidden" id="mgrade" name="mgrade" value="10">
		<input type="hidden" id="mpoint" name="mpoint" value="1000">
		 -->
		<input type="hidden" id="hnum" name="hnum" value="1">

		<div class="form-group" style="text-align: right; margin-top: 10px;">
			<div id="target2"></div>
			<button id="subbtn" class="btn btn-primary">���</button>

			&nbsp; <a href="${ctrpath}/login/idpwdSearch">
				<button type="button" class="btn btn-primary" id="idpwdBtn">���̵�/���ã��</button>
			</a>
		</div>

	</form>

</div>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<script>
	/*
	 $(function() {
	 $('#idpwdBtn').click(function() {
	 location="/login/idpwdSearch";
	 });
	 });
	 */

	$(function() {
		$('#hidchk').click(
				function() {
					let param = $('#hid').val();
					$.ajax({
						url : "../hidcheck?hid=" + param,
						success : function(data) {
							console.log(data);
							if (data == 1) {
								$('#target').css('background-color', 'red')
										.css('color', 'white').html(
												'������� ���̵� �Դϴ�.');
							} else {
								$('#target').css('background-color', 'blue')
										.css('color', 'white').html(
												'��� ������ ���̵� �Դϴ�.');
							}
						}
					});
				});
	});
	$(function() {
		$('#subbtn').click(
				function() {
					let param = $('#hid').val();
					$.ajax({
						url : "../hidcheck?hid=" + param,
						success : function(data) {
							console.log(data);
							if (data == 1) {
								$('#target2').css('background-color', 'red')
										.css('color', 'white').html(
												'������� ���̵� �ٸ� ���̵� �Է����ּ���.');
							} else if (param == "") {
								$('#target2').css('background-color', 'red')
										.css('color', 'white').html(
												'�ʼ������� �Է����ּ���.');
							} else if (data == 0) {
								$('#signUpInfo').submit()

							}
						}
					});
				});
	});
</script>