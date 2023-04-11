<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
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
					<th>���Ҹ�</th>
					<th>�Խ�</th>
					<th>���</th>
					<th>�����ڸ�</th>
					<th>����ó</th>
					<th>�������</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="e" items="${locallist }">
					<c:forEach var="i" items="${e.book }">
						<tr align="center">
							<th>${i.bid }</th>
							<th>${e.ltitle }</th>
							<th>${i.sdate }</th>
							<th>${i.edate }</th>
							<th>${e.mname }</th>
							<th>${e.mphone }</th>
							<th>
								<button class="btn btn-outline-secondary" type="button" id="d"
									data-bs-toggle="modal" data-bs-target="#exampleModal"
									data-bs-whatever="@fat">${i.bstatus }</button>
							</th>
						</tr>
						<!-- ��� START -->
						<div class="modal fade" id="exampleModal" tabindex="-1"
							aria-labelledby="exampleModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h1 class="modal-title fs-5" id="exampleModalLabel">���� ����
											����</h1>
										<button type="button" class="btn-close"
											data-bs-dismiss="modal" aria-label="Close"></button>
									</div>
									<div class="modal-body">

										<form action="updateBookHost" method="post">
											<input type="hidden" name="bid" value="${i.bid }">
											<div class="mb-3">
												<label for="recipient-name" class="col-form-label">����
													���� ����</label> <input type="text" class="form-control"
													id="recipient-name" readonly="readonly"
													value="${i.bstatus }">
											</div>

											<div class="mb-3">
												<label for="recipient-name" class="col-form-label">����
													���� ����</label>
												<p>
													<input type="radio" id="bstatus" name="bstatus"
														required="required" value="������">������
												<p>
													<input type="radio" id="bstatus" name="bstatus"
														required="required" value="����Ȯ��">����Ȯ��
												<p>
											</div>

											<div class="modal-footer">
												<button type="submit" class="btn btn-primary">�����ϱ�</button>
												<button type="button" class="btn btn-secondary"
													data-bs-dismiss="modal">�ݱ�</button>
											</div>
										</form>
									</div>
								</div>
							</div>
						</div>
						<!-- ��� END -->
					</c:forEach>
				</c:forEach>
			</tbody>

			<tfoot>

			</tfoot>
		</table>
	</div>
</article>

<script>

</script>