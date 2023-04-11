<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Content영역 -->
<br>
<br>
<br>
<br>
<br>
<div class="container mt-5">
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
			<tr>
                <td colspan="3" style="text-align: center;"><a
                    href="uform"><input
                        type="button" value="게시글 수정" id="upbtn" class="btn btn-outline-primary"></a></td>
                <td colspan="3" style="text-align: center;"><a
                    href="delete?qnum=${qvo.qnum }"><input
                        type="button" value="게시글 삭제" id="delbtn" class="btn btn-outline-danger"></a></td>
            </tr>
		</tfoot>
	</table>

	<h1>Answer</h1>
	<table class="table table-bordered table-hover">
		<thead>
			<tr>
				<th class="text-center">Writer</th>
				<th class="text-center">Content</th>
				<th class="text-center">Date</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="e" items="${alist }">
				<tr>
					<td class="text-center">${e.awriter}</td>
					<td class="text-center">${e.acontent}</td>
					<td class="text-center">${e.adate}</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="6" style="text-align: center;"><a
					href="aform?qnum=${qvo.qnum }"><input
						type="button" value="Answer" id="wbtn"
						class="btn btn-outline-info"></a></td>
			</tr>
		</tfoot>
	</table>
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