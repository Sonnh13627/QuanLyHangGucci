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
	<div class="container">
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
		<h4>Category</h4>
		<div>
			<form:form action="/Assignment/categories/${ url }" method="POST"
				modelAttribute="entity">
				<div class="form-group my-3">
					<label>Category Id:</label>
					<form:input path="id" class="form-control" />
				</div>
				<div class="form-group my-3">
					<label>Tên danh mục:</label>
					<form:input path="name" class="form-control" />
					<form:errors path="name" class="badge text-danger"></form:errors>
				</div>
				<div class="form-group my-2">
					<c:if test="${ url == 'store' }">

						<!-- Button trigger modal -->
						<button type="button" class="btn btn-primary"
							data-bs-toggle="modal" data-bs-target="#exampleModal">
							Thêm mới</button>
						<!-- Modal -->
						<div class="modal fade" id="exampleModal" tabindex="-1"
							aria-labelledby="exampleModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="exampleModalLabel">Thêm mới
											Category</h5>
										<button type="button" class="btn-close"
											data-bs-dismiss="modal" aria-label="Close"></button>
									</div>
									<div class="modal-body">Bạn chắc chắn muốn thêm mới
										Category này ?</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-bs-dismiss="modal">Close</button>
										<button class="btn btn-primary">Thêm mới</button>
									</div>
								</div>
							</div>
						</div>
					</c:if>
					<c:if test="${ url != 'store' }">
						<!-- Button trigger modal -->
						<button type="button" class="btn btn-success"
							data-bs-toggle="modal" data-bs-target="#exampleModal">
							Cập nhật</button>

						<!-- Modal -->
						<div class="modal fade" id="exampleModal" tabindex="-1"
							aria-labelledby="exampleModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="exampleModalLabel">Cập nhật
											Category</h5>
										<button type="button" class="btn-close"
											data-bs-dismiss="modal" aria-label="Close"></button>
									</div>
									<div class="modal-body">Bạn chắc chắn muốn cập nhật
										Category này ?</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-bs-dismiss="modal">Close</button>
										<button class="btn btn-success">Cập nhật</button>
									</div>
								</div>
							</div>
						</div>
					</c:if>
					<a class="btn btn-outline-secondary"
						href="/Assignment/categories/create">Làm mới</a>
				</div>
			</form:form>
		</div>
		<hr>
		<div class="row shadow-lg p-3" style="border-radius: 10px">
			<h4 class="col-7">
				<b><i class="fa-solid fa-table"></i> DANH SÁCH CATEGORY</b>
			</h4>
			<table class="table table-bordered mt-3">
				<thead class="text-light">
					<tr class="text-center">
						<th>STT</th>
						<th>Tên danh mục</th>
						<th colspan="2">Thao tác</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ page.content }" var="item">
						<c:set var="stt" value="${ stt + 1 }"></c:set>
						<tr class="align-middle text-center">
							<td>${ stt }</td>
							<td>${ item.name }</td>
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
													category</h5>
												<button type="button" class="btn-close"
													data-bs-dismiss="modal" aria-label="Close"></button>
											</div>
											<div class="modal-body">Bạn chắc chắn muốn xóa danh mục
												này ?</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-secondary"
													data-bs-dismiss="modal">Hủy</button>
												<a class="btn btn-danger"
													href="/Assignment/categories/delete/${ item.id }">Xóa</a>
											</div>
										</div>
									</div>
								</div>
							</td>
							<td><a class="btn btn-success"
								href="/Assignment/categories/edit/${ item.id }">Edit</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<nav aria-label="Page navigation example">
				
				<ul class="pagination float-end">
					<c:if test="${ page.number > 0 }">
						<li class="page-item"><a class="page-link"
							href="/Assignment/categories/create?page=0&field=${ sort }"
							style="color: blue"><i class="fa-solid fa-backward-step"></i></a></li>
					</c:if>
					<c:if test="${ page.number > 0}">
						<li class="page-item"><a class="page-link"
							href="/Assignment/categories/create?page=${ page.number - 1 }&field=${ sort }"
							style="color: blue"><i class="fa-solid fa-angles-left"></i></a></li>
					</c:if>
					<c:if test="${ page.number < (page.totalPages - 1)}">
						<li class="page-item"><a class="page-link"
							href="/Assignment/categories/create?page=${ page.number + 1 }&field=${ sort }"
							style="color: blue"><i class="fa-solid fa-angles-right"></i></a></li>
					</c:if>
					<c:if test="${ page.number < (page.totalPages - 1)}">
						<li class="page-item"><a class="page-link"
							href="/Assignment/categories/create?page=${ page.totalPages - 1 }&field=${ sort }"
							style="color: blue"><i class="fa-solid fa-forward-step"></i></a></li>
					</c:if>
				</ul>
			</nav>
		</div>
	</div>
</body>
</html>