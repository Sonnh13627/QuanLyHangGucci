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
	<div class="container my-5">
	<div>
			<c:if test="${ !empty error }">
				<div class="alert alert-danger" role="alert">${ error }</div>
				<c:remove var="error" scope="session" />
			</c:if>
		</div>
		<div class="row bg white w-auto m-auto style="box-shadow: 0 0 20px gray;">
			<h1>LOGIN</h1>
			<div class="w-50 m-auto">
				<form:form action="/Assignment/login/login" method="POST"
					modelAttribute="login">
					<div class="form-group my-4">
						<label>Email</label>
						<form:input path="email" class="form-control"/>
						<form:errors path="email" class="badge text-danger"></form:errors>
					</div>
					<div class="form-group my-4">
						<label> Password</label>
						<form:input type="password" path="password" class="form-control" />
						<form:errors path="password" class="badge text-danger"></form:errors>
					</div>
					<div class="form-group my-4 text-center">
					<button class="btn btn-secondary">
						Login
					</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>