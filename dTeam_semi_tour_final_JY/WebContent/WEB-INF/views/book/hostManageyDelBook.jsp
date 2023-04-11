<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctrpath" value="${pageContext.request.contextPath}" />



<style>
@import
	url('https://fonts.googleapis.com/css2?family=Noto+Serif+KR:wght@300&display=swap')
	;

#frame {
	max-width: 1500px;
	min-height: 300px;
	margin: 0 auto;
}

.btn {
	margin-right: 30px;
}
</style>

<article>
	<ul class="list-unstyled">
		<li class="border-top my-3"></li>
	</ul>

	<br>

	<div id="frame">
		<table class="table">
			<thead>
				<tr align="center">
					<th>�����ȣ</th>
					<th>������ID</th>
					<th>�����ݾ�</th>
					<th>����������Ʈ</th>
					<th>��ҳ�¥</th>
					<th>�������</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="e" items="${locallist }">
						<tr align="center">
							<th>${e.bid }</th>
							<th>${e.mid }</th>
							<th><fmt:formatNumber value="${e.pay }" pattern="###,###" /></th>
							<th>${e.spoint }</th>
							<th>${e.btime }</th>
							<th>${e.status }</th>
						</tr>
				</c:forEach>
			</tbody>

			<tfoot>

			</tfoot>
		</table>
	</div>
</article>

<script>

</script>