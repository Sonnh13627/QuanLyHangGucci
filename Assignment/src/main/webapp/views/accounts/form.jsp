<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container mt-3">
		<div>
			<c:if test="${ !empty message }">
				<div class="alert alert-success" role="alert">${ message }</div>
				<c:remove var="message" scope="session" />
			</c:if>
		</div>
		<div>
			<c:if test="${ !empty error }">
				<div class="alert alert-danger" role="alert">${ error }</div>
				<c:remove var="error" scope="session" />
			</c:if>
		</div>
		<h4>Account</h4>
		<div>
			<form:form action="/Assignment/accounts/${ url }" method="POST"
				modelAttribute="entity" enctype="multipart/form-data">
				<div class="form-group">
					<label>Fullname:</label>
					<form:input path="fullname" class="form-control" />
					<form:errors path="fullname" class="badge text-danger"></form:errors>
				</div>
				<div class="form-group">
					<label>Email:</label>
					<form:input path="email" class="form-control" />
					<form:errors path="email" class="badge text-danger"></form:errors>
				</div>
				<div class="form-group">
					<label>Username:</label>
					<form:input path="username" class="form-control" />
					<form:errors path="username" class="badge text-danger"></form:errors>
				</div>
				<div class="form-group">
					<label>Password:</label>
					<form:input path="password" type="password" class="form-control" />
					<form:errors path="password" class="badge text-danger"></form:errors>
				</div>
				<div class="form-group">
					<label>Photo:</label> <input type="file" name="photoFile"
						class="form-control">
				</div>
				<div class="form-group">
					<label>Role:&nbsp;</label>
					<form:radiobuttons items="${ admins }" path="admin" />
					<form:errors path="admin" class="badge text-danger"></form:errors>
				</div>
				<div class="form-group my-2">
					<c:if test="${ url == 'store' }">

						<!-- Button trigger modal -->
						<button type="button" class="btn btn-primary"
							data-bs-toggle="modal" data-bs-target="#exampleModal">
							Create</button>
						<!-- Modal -->
						<div class="modal fade" id="exampleModal" tabindex="-1"
							aria-labelledby="exampleModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="exampleModalLabel">Thêm mới
											Account</h5>
										<button type="button" class="btn-close"
											data-bs-dismiss="modal" aria-label="Close"></button>
									</div>
									<div class="modal-body">Bạn chắc chắn muốn thêm mới
										Account này ?</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-bs-dismiss="modal">Close</button>
										<button class="btn btn-primary">Create</button>
									</div>
								</div>
							</div>
						</div>
					</c:if>
					<c:if test="${ url != 'store' }">
						<!-- Button trigger modal -->
						<button type="button" class="btn btn-success"
							data-bs-toggle="modal" data-bs-target="#exampleModal">
							Update</button>

						<!-- Modal -->
						<div class="modal fade" id="exampleModal" tabindex="-1"
							aria-labelledby="exampleModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="exampleModalLabel">Cập nhật
											Account</h5>
										<button type="button" class="btn-close"
											data-bs-dismiss="modal" aria-label="Close"></button>
									</div>
									<div class="modal-body">Bạn chắc chắn muốn cập nhật
										Account này ?</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-bs-dismiss="modal">Close</button>
										<button class="btn btn-success">Update</button>
									</div>
								</div>
							</div>
						</div>
					</c:if>
					<a class="btn btn-outline-secondary"
						href="/Assignment/accounts/create">Reset</a>
				</div>
			</form:form>
		</div>
		<hr>
		<div class="row shadow-lg p-3" style="border-radius: 10px">
			<h4 class="col-7">
				<b> DANH SÁCH ACCOUNT</b>
			</h4>
			<table class="table table-bordered my-3">
				<thead>
					<th>STT</th>
					<th>Fullname</th>
					<th>Username</th>
					<th>Email</th>
					<th>Ảnh</th>
					<th>Activated</th>
					<th>Admin?</th>
					<th colspan="2">Thao tác</th>
				</thead>
				<tbody>
					<c:forEach items="${ page.content }" var="item">
						<c:set var="stt" value="${ stt + 1 }"></c:set>
						<tr class="align-middle text-center">
							<td>${ stt }</td>
							<td>${ item.fullname }</td>
							<td>${ item.username }</td>
							<td>${ item.email }</td>
							<td class="text-center"><img
								src="/Assignment/image/${ item.photo }"
								class="img-fluid" width="80px"></td>
							<td>${ item.activated == 1 ? "Đã kích hoạt":"Chưa kích hoạt" }</td>
							<td>${ item.admin == 1 ? "Quản trị viên":"Người dùng" }</td>
							<td>
								<!-- Button trigger modal -->
								<button type="button" class="btn btn-danger"
									data-bs-toggle="modal"
									data-bs-target="#exampleModal${ item.id }">Xóa</button> <!-- Modal -->
								<div class="modal fade" id="exampleModal${ item.id }"
									tabindex="-1" aria-labelledby="exampleModalLabel"
									aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title" id="exampleModalLabel">Xóa
													account</h5>
												<button type="button" class="btn-close"
													data-bs-dismiss="modal" aria-label="Close"></button>
											</div>
											<div class="modal-body">Bạn chắc chắn muốn xóa người
												dùng này ?</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-secondary"
													data-bs-dismiss="modal">Hủy</button>
												<a class="btn btn-danger"
													href="/Assignment/accounts/delete/${ item.id }">Xóa</a>
											</div>
										</div>
									</div>
								</div>
							</td>
							<td><a class="btn btn-success"
								href="/Assignment/accounts/edit/${ item.id }">Edit</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<nav aria-label="Page navigation example">
				
				<ul class="pagination float-end">
					<c:if test="${ page.number > 0 }">
						<li class="page-item"><a class="page-link"
							href="/Assignment/accounts/create?page=0"
							style="color: blue"><i class="fa-solid fa-backward-step"></i></a></li>
					</c:if>
					<c:if test="${ page.number > 0}">
						<li class="page-item"><a class="page-link"
							href="/Assignment/accounts/create?page=${ page.number - 1 }"
							style="color: blue"><i class="fa-solid fa-angles-left"></i></a></li>
					</c:if>
					<c:if test="${ page.number < (page.totalPages - 1)}">
						<li class="page-item"><a class="page-link"
							href="/Assignment/accounts/create?page=${ page.number + 1 }"
							style="color: blue"><i class="fa-solid fa-angles-right"></i></a></li>
					</c:if>
					<c:if test="${ page.number < (page.totalPages - 1)}">
						<li class="page-item"><a class="page-link"
							href="/Assignment/accounts/create?page=${ page.totalPages - 1 }"
							style="color: blue"><i class="fa-solid fa-forward-step"></i></a></li>
					</c:if>
				</ul>
			</nav>
		</div>
	</div>
</body>
</html>