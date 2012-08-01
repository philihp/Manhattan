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
		<style>
.building {
	height: 160px;
	width: 100px;
	-webkit-transition-duration: 0.2s;

	-moz-transition-duration: 0.2s;

	-o-transition-duration: 0.2s;

	transition-duration: 0.2s;

	-webkit-transition-property: -webkit-transform;

	-webkit-transform:rotate(-2deg);

}

.building:hover {

	-webkit-transform:rotate(2deg);
}
		</style>
	</head>
	<body>
	
	<h1>View in progress Instance</h1>
	
	<table border="1" cellspacing="0" cellpadding="3">
	<c:forEach items="${instance.players}" var="player">
		<c:if test="${not empty player.user}">
			<tr>
				<td>${player.user.name}</td>
				<td>${player.color}</td>
			</tr>
		</c:if>
	</c:forEach>
	</table>
	
	Implosion Counters:
	<c:forEach items="${board.implosionCounters}" var="value">[${value}]</c:forEach>
	<br />
	
	<img src="images/${board.buildingProposal1}.png" class="building" />
	<img src="images/${board.buildingProposal2}.png" class="building" />
	<img src="images/${board.buildingProposal3}.png" class="building" />
	<img src="images/${board.buildingProposal4}.png" class="building" />
	<img src="images/${board.buildingProposal5}.png" class="building" />
	<img src="images/${board.buildingProposal6}.png" class="building" />
	<img src="images/${board.buildingProposal7}.png" class="building" />
	
	<hr />
	

	<img src="images/mainboard.jpg" />

	<img src="images/board-red.jpg" />

	<img src="images/board-yellow.jpg" />

	<img src="images/board-green.jpg" />

	<img src="images/board-blue.jpg" />

	<img src="images/board-purple.jpg" />
	
	<hr />
	
	Moves:
	<ul>
	<c:forEach items="${instance.moves}" var="move">
		<li>${move.command}</li>
	</c:forEach>
	</ul>
	
	<html:form action="/make-move" method="POST">
		<html:hidden property="instanceId" value="${instance.instanceId}"/>
		Command: <html:text property="command" />
		<html:submit>Preview</html:submit>
	</html:form>
	
	</body>
</html>