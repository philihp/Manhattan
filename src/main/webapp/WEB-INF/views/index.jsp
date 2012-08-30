<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

	<head>
		<title>Manhattan Project Online</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	</head>
	<body>

<h1>Manhattan Project Online</h1>

<section id="login">


User: 
<c:if test="${empty user}">
	<i>not logged in</i>
</c:if>
<c:if test="${not empty user}">
	${user}
</c:if>


</section>

<section id="content" class="cf">
	<!-- ajax loader -->
	<div id="loader"></div>
	<h2 class="head">Login</h2>
	<section id="login" class="cf">
		<nav>
			<ul>
				<li>
					<a href="<c:url value="/login-twitter" />" class="twitter">Login with Twitter</a> | 
					<a href="<c:url value="/logout-twitter" />">Logout</a>
				</li>
				<li>
					<a href="<c:url value="/login-facebook" />" class="facebook">Login with Facebook</a> | 
					<a href="<c:url value="/logout-facebook" />">Logout</a>
				</li>
			</ul>
		</nav>
	</section>
</section>
	
	</body>
</html>
