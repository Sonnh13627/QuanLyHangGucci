<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head> 
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container mt-3">
            
            <div class="row mt-5">
                <div class="row">
                    <c:forEach items="${ listProducts }" var="item">
                    	<div class="col-md-3 col-12 product mt-4">
                        <div class="card p-2 shadow border-white h-100" style="border-radius: 10px;">
                            <!-- <span class="badge text-bg-danger me-5">Giảm 35%</span> -->
                            <img src="/Assignment/image/${ item.image }" class="card-img-top">
                            <div class="card-body">
                                <h5 class="card-title"><b>${ item.name }</b></h5>
                                    <p class="card-text">
                                        <b class="text-danger">${ item.price } ₫</b>
                                    </p>
                                    <a href="/Assignment/cart/orders/create/${ item.id }" class="btn btn-success">Mua ngay</a>
                            </div>
                        </div>
                    </div>
                    </c:forEach>
                </div>
            </div>
        </div>
</body>
</html>