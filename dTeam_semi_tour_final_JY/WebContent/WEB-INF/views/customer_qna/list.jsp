<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Content영역 -->
<div class="container mt-5">
	<br> <br> <br> <br> <br>
	<h1>Q&A</h1>
	<table class="table table-bordered table-hover">
		<thead>
			<tr>
				<th class="text-center">No</th>
				<th class="text-center">Writer</th>
				<th class="text-center">Subject</th>
				<th class="text-center">Content</th>
				<th class="text-center">Date</th>
			</tr>
		</thead>
		<tbody>
			<%-- 반복시작 --%>
			<c:forEach var="e" items="${qlist}">
				<tr>
					<td class="text-center">${e.qnum}</td>
					<td class="text-center">${e.qwriter}</td>
					<td class="text-center"><a
						href="detail?qnum=${e.qnum }">${e.qsubject}</a></td>
					<td class="text-center">${e.qcontent}</td>
					<td class="text-center">${e.qdate}</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
		
		</tfoot>
	</table>
	<div class="col-sm-10">
		<a href="wform"><input type="button"
			value="글작성" id="wbtn" class="btn btn-outline-info"></a>
	</div>
	<br> <br> <br> <br> <br>
</div>
<script type="text/javascript">
	$(function() {
		// wBtn 바톤 크릭 이벤트
		$('#dBtn').click(function() {
			// 이동
			location = "pro.kosmo?cmd=myboard&scmd=bform";
		});
	});
	<!--
//-->
</script>