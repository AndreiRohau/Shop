<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style.css">
<title>MAIN page</title>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.ch" var="ch_button" />

<c:set var="isChanged" scope="session">${request.getAttribute("isChanged")}</c:set>
<c:set var="userName" scope="session">${request.getAttribute("userName")}</c:set>

</head>
<body>
	<div class="header">

		<div id="header1">
			<p>TRY XML COMMAND</p>
		</div>
		
		<div id="header2">
			<form action="FrontController" method="post">
				<input type="hidden" name="command" value="change_language"/>
				<input type="hidden" name="local" value="en"/>
				<input type="submit" value="${en_button}"/>
			</form>
			<form action="FrontController" method="post">
				<input type="hidden" name="command" value="change_language"/>
				<input type="hidden" name="local" value="ru"/>
				<input type="submit" value="${ru_button}"/>
			</form>
			<form action="FrontController" method="post">
				<input type="hidden" name="command" value="change_language"/>
				<input type="hidden" name="local" value="ch"/>
				<input type="submit" value="${ch_button}"/>
			</form>
		</div>

	</div>
	
	
	<div class="middle">
		<div id="menu">
		


		</div>
		
		<div id="content">

		</div>

	</div>

	<div class="footer" >
		<div id="footer" >
			<a href="http://localhost:8080/ilib/" style="background-color: #a1311f">HOME page</a>
		</div>
	</div>
</body>
</html>