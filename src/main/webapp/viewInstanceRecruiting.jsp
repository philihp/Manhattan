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
	</head>
	<body>
	
	<h1>View Recruiting Instance</h1>
	
	<table>
	<c:forEach items="${instance.players}" var="player">
		<c:if test="${not empty player.user}">
			<tr>
				<td>${player.user.name}</td>
				<td>${player.color}</td>
			</tr>
		</c:if>
	</c:forEach>
	</table>
	
	Minimum Players: ${instance.minPlayers}<br />
	Maximum Players: ${instance.maxPlayers}<br />
	
	<c:if test="${instance.playerCount >= instance.minPlayers and instance.playerCount <= instance.maxPlayers}">
		<html:form action="/start-instance.do" method="POST">
			<html:hidden property="instanceId" value="${instance.instanceId}" />
			<html:submit>Start Instance</html:submit>
		</html:form>
	</c:if>

	<html:form action="/leave-instance.do" method="POST">
		<html:hidden property="instanceId" value="${instance.instanceId}" />
		<html:submit>Leave Instance</html:submit>
	</html:form>
	
	</body>
</html>