<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean-el" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html-el" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Manhattan Project Online</title>
		<link href="css/signin.css" rel="stylesheet" />
	</head>
	<body>
	
		<section>
			<header>
				<div class="signin"><html:link action="/signin-facebook.do" styleClass="rollover facebook-signin"><span class="displaced">Login with Facebook</span></html:link></div>
				<div class="signin"><html:link action="/signin-twitter.do" styleClass="rollover twitter-signin"><span class="displaced">Login with Facebook</span></html:link></div>
				
				<h1>Manhattan Project Online</h1>
				
			</header>
		</section>

		<section>
		
		User: <c:out value="${USER}"/>
		
		</section>	
	
	</body>
</html>