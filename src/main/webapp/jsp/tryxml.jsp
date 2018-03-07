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

</head>
<body>
	<div class="header">

		<div id="header1">
			<p>TRY XML COMMAND</p>
		</div>

		<div id="header2" style="display:flex; flex-flow: row wrap; justify-content:space-between">
			<div>
				<form action="FrontController" method="post">
					<input type="hidden" name="command" value="change_language">
					<input type="hidden" name="local" value="en">
					<input type="submit" value="ENG">
				</form>
				<form action="FrontController" method="post">
					<input type="hidden" name="command" value="change_language">
					<input type="hidden" name="local" value="ru">
					<input type="submit" value="РУС">
				</form>
				<form action="FrontController" method="post">
					<input type="hidden" name="command" value="change_language">
					<input type="hidden" name="local" value="ch">
					<input type="submit" value="汉语">
				</form>
			</div>
			<div>
				<form action="FrontController" method="post" style="height:100%">
					<input type="hidden" name="command" value="logout">
					<input type="submit" value="Log out" style="height:100%">
				</form>
			</div>
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
			<p>
				<a style="background-color: aquamarine" href="FrontController?command=goToPage&address=index.jsp">INDEX</a>
				  -->
				<a style="background-color: aquamarine" href="FrontController?command=try_xml&address=%2Fjsp%2Ftryxml.jsp">
					try new xml command
				</a>
			</p>
		</div>
	</div>
</body>
</html>