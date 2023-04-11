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
						href="pro.kosmo?cmd=qna&scmd=detail&qnum=${e.qnum }">${e.qsubject}</a></td>
					<td class="text-center">${e.qcontent}</td>
					<td class="text-center">${e.qdate}</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="5">
						<c:choose>
								<c:when test="${endPage > pagePerBlock }">
									<a href="pro.kosmo?cmd=qna&scmd=qna&nextPage=${startPage - 1 }">Previous</a>
								</c:when>
								<c:otherwise>
									<a href="">Previous</a>
								</c:otherwise>
							</c:choose>
							<a href="pro.kosmo?cmd=qna&scmd=qna&nextPage"></a>
							<c:choose>
								<c:when test="${endPage < totalPage }">
									<a href="pro.kosmo?cmd=qna&scmd=qna&nextPage=${endPage + 1 }">Next</a>
								</c:when>
								<c:otherwise>
									<a href="">Next</a>
								</c:otherwise>
							</c:choose>
				</td>
			</tr>
		</tfoot>
	</table>
	<div class="col-sm-10">
		<a href="pro.kosmo?cmd=qna&scmd=wform"><input type="button"
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