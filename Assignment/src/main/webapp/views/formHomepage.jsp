<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/Assignment/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="/Assignment/fontawesome-free-6.1.1-web/css/all.min.css">
<style type="text/css">
<
style type ="text /css "> < style>.search-form input[type="text"] {
	width: 400px;
	border: 1px solid #76777b;
	color: #181818;
	/* height: 62px; */
	height: 40px;
	padding: 10px 0 10px 11px;
	font-style: italic;
	border-radius: 50px;
}

.search-form .search-btn {
	width: 19px;
	height: 21px;
	background-image:
		url(//cdn.shopify.com/s/files/1/0543/4293/t/263/assets/search-ico.svg?v=2676193283397140169);
	background-position: center;
	background-repeat: no-repeat;
	background-color: transparent;
	font-size: 0;
	border: none;
	position: absolute;
	right: 15px;
	top: 5px;
}
</style>
</head>
<body>
	<div class="controller">
		<nav class="navbar navbar-expand-lg navbar-dark bg-black">
			<div class="container-fluid">
				<a class="navbar-brand" href="/Assignment/home/index"><img
					src="https://zunezx.com/upload/image/data/website/logozunezx-b1c.png"
					height="80" class="img-reponsive"></a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>

				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav me-auto">
						<li class="nav-item"><a class="nav-link"
							href="/Assignment/home/index"><i class="fas fa-home"></i>
								Trang chủ</a></li>
						<li class="nav-item"><a class="nav-link" href="#"><i
								class="fa-solid fa-list"></i> Giới thiệu</a></li>
						<li class="nav-item"><a class="nav-link" href="#"><i
								class="fa-solid fa-vest-patches"></i> Hỏi Đáp</a></li>
						<li class="nav-item"><a class="nav-link" href="#"><i
								class="fa-solid fa-list"></i> Liên hệ</a></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-bs-toggle="dropdown" aria-expanded="false">
								Quản trị </a>
							<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
								<c:if test="${ !empty sessionScope.account && sessionScope.account.admin == 1}">
									<li><a class="dropdown-item"
										href="/Assignment/accounts/create">Quản Lý User</a></li>
									<li><a class="dropdown-item"
										href="/Assignment/categories/create">Quản Lý Category</a></li>
									<li><a class="dropdown-item"
										href="/Assignment/products/create">Quản Lý Product</a></li>
								</c:if>
								<c:if test="${ !empty sessionScope.account }">
								<li><a class="dropdown-item" href="/Assignment/login/logout">Đăng xuất</a></li>
								</c:if>
							</ul></li>

					</ul>

					<ul class="navbar-nav me-5 mb-lg-0">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-bs-toggle="dropdown" aria-expanded="false"><i
								class="fa fa-user"></i></a>
							<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
								<li><a class="dropdown-item" href="/Assignment/login/form">Đăng nhập</a></li>
								<li><a class="dropdown-item" href="#">Đăng ký</a></li>

							</ul></li>
						<li class="nav-item"><a class="nav-link" href="/Assignment/cart/orders/cart"><i
								class="fa-solid fa-cart-plus"></i></a></li>
					</ul>

				</div>
			</div>
		</nav>

		<article>
			<jsp:include page="${ view }"></jsp:include>
		</article>
		<footer class="row text-center text-white p-3 ">
			<div class="container">
				<div class="row bg-black text-white">
					<div class="col-xs-12 col-sm-6">
						<img
							src="https://zunezx.com/upload/image/data/website/logozunezx-b1c.png"
							height="200px" width="400px" class="img-reponsive">
					</div>
					<div class="col-xs-12 col-sm-6">
						<h3>Hệ thống cửa hàng</h3>
						<hr>
						<ul class="list-unstyled">
							<li><i class="fa fa-map-marker"> Hà Nội: 131 Đống Các,
									Đống Đa </i> <br> <i class="fa fa-map-marker"> Đà Nẵng: 65
									Nguyễn Văn Linh, Hải Châu 1 </i> <br> <i
								class="fa fa-map-marker"> HCM: 411 Võ Văn Tân, P5, Q3 </i> <br>
							</li>
							<li><i class="fa fa-phone"> 0927182637 </i></li>
							<li><i class="fa fa-envelope"> webzune0903@gmail.com </i></li>
						</ul>
					</div>

				</div>
			</div>

		</footer>
	</div>
	<script src="/Assignment/js/jquery.min.js"></script>
	<script src="/Assignment/js/popper.min.js"></script>
	<script src="/Assignment/js/bootstrap.min.js"></script>
</body>
</html>