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
	
		<section class="header">
			<header>
				<div class="signin"><html:link action="/signin-facebook.do" styleClass="rollover facebook-signin"><span class="displaced">Login with Facebook</span></html:link></div>
				<div class="signin"><html:link action="/signin-twitter.do" styleClass="rollover twitter-signin"><span class="displaced">Login with Facebook</span></html:link></div>
				<!--<div class="signin"><html:link action="/signin-google.do" styleClass="">Login with Google</html:link></div>-->
				
				<h1>Manhattan Project Online</h1>
				
			</header>
		</section>

		<section>
		User: <c:out value="${user.name}"/>
		</section>
		
		<section>
			Your Instances:
			<table border="1" cellspacing="0" cellpadding="3">
				<tr>
					<th>Status</th>
					<th>Name</th>
				</tr>
				<logic:iterate id="instance" name="myInstances">
				<tr>
					<td>${instance.status}</td>
					<td><html:link action="/view-instance.do" paramId="instanceId" paramName="instance" paramProperty="instanceId">${instance.name}</html:link></td>
				</tr>
				</logic:iterate>
			</table>
		</section>
		
		<hr />
		
		<section>
			Instances recruiting<br />
			<table border="1" cellspacing="0" cellpadding="3">
				<tr>
					<th>Name</th>
					<th>Min</th>
					<th>Max</th>
					<th>Current</th>
					<th></th>
				</tr>
				<logic:iterate id="instance" name="instancesRecruiting">
				<tr>
					<td><html:link action="/view-instance.do" paramId="instanceId" paramName="instance" paramProperty="instanceId">${instance.name}</html:link></td>
					<td>${instance.minPlayers}</td>
					<td>${instance.maxPlayers}</td>
					<td>${instance.playerCount}</td>
					<td>
						<html:form action="/join-instance.do" method="post">
							<html:hidden property="instanceId" value="${instance.instanceId}"/>
							<html:select property="color">
								<html:option value="">(random)</html:option>
								<html:optionsCollection name="instance" property="availableColors"/>
							</html:select>
							<c:if test="${empty user}">

								<html:submit disabled="true">Join</html:submit>

							</c:if>

							<c:if test="${not empty user}">

								<html:submit>Join</html:submit>

							</c:if>
						</html:form>
					</td>
				</tr>
				</logic:iterate>
			</table>
			<html:link action="/create-instance.do">Create Instance</html:link>
		</section>
		
		<hr />
		
		<section>
		Instances looking for a replacement
		</section>
		
		<hr />
		
		<section>
		Instances in progress
		</section>
		
		<hr />
		
		<section>
		Finished instances
		</section>
			
	
	</body>
</html>