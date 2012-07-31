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
	
	<h1>Create Instance</h1>
	
	<html:form action="/create-instance" method="POST" focus="name">
	
		Game Name: <html:text property="name" />
		<br />
		
		Min Players:
		<html:select property="minPlayers">
			<html:optionsCollection name="numPlayers" />
		</html:select>
		<br />
		
		Max Players:
		<html:select property="maxPlayers">
			<html:optionsCollection name="numPlayers" />
		</html:select>
		<br />
		
		Time Limit A:
		<html:text property="timeLimitA" />
		<html:select property="timeLimitAUnits">
			<html:optionsCollection name="timeUnits" />
		</html:select>
		<br />
		
		My Color:
		<html:select property="myColor">
			<html:option value="">(random)</html:option>
			<html:optionsCollection name="colors" />
		</html:select>
		<br />
	
		<html:submit>Create Instance</html:submit>
	</html:form>
	
	
	</body>
</html>