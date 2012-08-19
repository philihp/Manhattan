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
		<link rel="stylesheet" href="css/manhattan.css" />
	</head>
	<body>
	
	<h1>View Proposed Move</h1>
	
	<table border="1">
	<c:forEach items="${instance.players}" var="player">
		<c:if test="${not empty player.user}">
			<tr>
				<td>${player.user.name}</td>
				<td>${player.color}</td>
			</tr>
		</c:if>
	</c:forEach>
	</table>
	
	Moves:
	<ul>
	<c:forEach items="${instance.transitions}" var="transition">
		<li>${transition.command}</li>
	</c:forEach>
	</ul>
	
	<html:form action="/make-move" method="POST">
		<html:hidden property="instanceId" value="${instance.instanceId}"/>
		Command: <html:text property="command" />
		<html:submit>Preview</html:submit>
	</html:form>
	
	<html:form action="/commit-move" method="POST">
		<html:hidden property="instanceId" />
		Command: <html:text property="command" disabled="true"/><html:hidden property="command"/>
		<html:submit>Commit</html:submit>
	</html:form>
	
	</body>
</html>