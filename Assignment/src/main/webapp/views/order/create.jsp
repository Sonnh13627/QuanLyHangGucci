<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
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
		<div class="row shadow-lg p-3" style="border-radius: 10px">
			<div class="row">
				<h4>
					<b> THÊM VÀO GIỎ HÀNG</b>
				</h4>
			</div>
			<div class="row mt-3">
				<div class="col-md-3 col-12">
					<div class="card p-2 shadow border-white h-100"
						style="border-radius: 10px;">
						<!-- <span class="badge text-bg-danger me-5">Giảm 35%</span> -->
						<img src="/Assignment/photoProducts/${ product.image }"
							class="card-img-top">
						<div class="card-body">
							<h5 class="card-title">
								<b>${ product.name }</b>
							</h5>
							<p class="card-text">
								<b class="text-danger">${ product.price } ₫</b>
							</p>
						</div>
					</div>
				</div>
				<div class="col-md-9 col-12">
					<form:form action="/Assignment/cart/orders/store/${ product.id }" method="POST"
						modelAttribute="entity">
						<div class="form-group my-3">
							<label>Địa chỉ</label>
							<form:input path="address" class="form-control" />
							<form:errors path="address" class="badge text-bg-danger"></form:errors>
						</div>
						<div class="form-group my-3">
							<label>Số lượng</label>
							<form:input path="quatity" class="form-control" />
							<form:errors path="quatity" class="badge text-bg-danger"></form:errors>
						</div>
						<div class="form-group my-2">
							<!-- Button trigger modal -->
							<button type="button" class="btn btn-success"
								data-bs-toggle="modal" data-bs-target="#exampleModal">
								Đặt Hàng</button>

							<!-- Modal -->
							<div class="modal fade" id="exampleModal" tabindex="-1"
								aria-labelledby="exampleModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" id="exampleModalLabel">Xác nhận
												mua đơn hàng</h5>
											<button type="button" class="btn-close"
												data-bs-dismiss="modal" aria-label="Close"></button>
										</div>
										<div class="modal-body">Bạn chắc chắn muốn thêm đơn hàng
											này vào giỏ hàng ?</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary"
												data-bs-dismiss="modal">Close</button>
											<button class="btn btn-success">Xác nhận</button>
										</div>
									</div>
								</div>
							</div>
							<a class="btn btn-outline-secondary"
								href="/Assignment/home/index">Hủy bỏ</a>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>