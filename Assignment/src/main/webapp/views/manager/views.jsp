<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
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
		<div class="row shadow-lg p-3" style="border-radius: 10px">
			<div class="row">
				<div class="col-4 text-center bg-warning pt-2">
					Người đặt hàng: <b class="text-danger">${ list1.account.fullname }</b>
				</div>
				<div class="col-4 offset-4">
					<!-- Button trigger modal -->
					<button type="button" class="btn btn-success"
						data-bs-toggle="modal" data-bs-target="#exampleModal">
						Xác nhận đơn hàng</button>

					<!-- Modal -->
					<div class="modal fade" id="exampleModal" tabindex="-1"
						aria-labelledby="exampleModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLabel">Bạn chắc
										chắn muốn xác nhận đơn hàng này</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-bs-dismiss="modal">Close</button>
									<button class="btn btn-success">Xác nhận</button>
								</div>
							</div>
						</div>
					</div>
					<!-- Button trigger modal -->
					<button type="button" class="btn btn-danger" data-bs-toggle="modal"
						data-bs-target="#exampleModal">Hủy bỏ đơn hàng</button>

					<!-- Modal -->
					<div class="modal fade" id="exampleModal" tabindex="-1"
						aria-labelledby="exampleModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLabel">Bạn chắc
										chắn muốn hủy bỏ đơn hàng này</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-bs-dismiss="modal">Close</button>
									<a class="btn btn-danger" href="">Hủy bỏ đơn hàng</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<hr>
			<table class="table mt-3">
				<thead>
					<tr class="text-center">
						<th>STT</th>
						<th>Ảnh</th>
						<th>Tên sản phẩm</th>
						<th>Giá</th>
						<th>Số lượng</th>
						<th>Địa chỉ nhận</th>
						<th>Ngày tạo</th>
						<th>Thành tiền</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ cart }" var="item">
						
							<c:set var="stt" value="${ stt + 1 }"></c:set>
							<tr class="align-middle text-center">
								<td>${ stt }</td>
								<td class="text-center"><img
									src="/Assignment/photoProducts/${ item.product.image }"
									class="img-fluid" width="100px"></td>
								<td>${ item.product.name }</td>
								<td><fmt:formatNumber value="${ item.product.price }"
										pattern="#,###"></fmt:formatNumber> ₫</td>
								<td>${ item.quatity }</td>
								<td>${ item.order.address }</td>
								<td>${ item.order.createDate }</td>
								<td><fmt:formatNumber
										value="${ item.quatity * item.product.price }" pattern="#,###"></fmt:formatNumber>
									₫</td>
								<td>Chờ xác nhận</td>
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
														product</h5>
													<button type="button" class="btn-close"
														data-bs-dismiss="modal" aria-label="Close"></button>
												</div>
												<div class="modal-body">Bạn chắc chắn muốn xóa đơn
													hàng này ?</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-secondary"
														data-bs-dismiss="modal">Hủy</button>
													<a class="btn btn-danger"
														href="/Assignment/cart/orders/delete/${ item.id }">Xóa</a>
												</div>
											</div>
										</div>
									</div>
								</td>
							</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>