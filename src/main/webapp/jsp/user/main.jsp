<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="style.css">
		<title>Profile</title>

		<fmt:setLocale value="${sessionScope.local}" />
		<fmt:setBundle basename="localization.local" var="loc" />
		<fmt:message bundle="${loc}" key="local.shop" var="shop" />
		<fmt:message bundle="${loc}" key="local.hello" var="hello" />
		<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
		<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
		<fmt:message bundle="${loc}" key="local.locbutton.name.ch" var="ch_button" />
		<fmt:message bundle="${loc}" key="local.goToProfile" var="goToProfile_button" />
		<fmt:message bundle="${loc}" key="local.goToBasket" var="goToBasket_button" />

	</head>
	<body>
		<div class="header">

			<div id="header1">
				<p><c:out value="${shop}"/></p>
				<p><c:out value="${hello}"/> <c:out value="${sessionScope.userName}"/>!!!</p>
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
				<form action="FrontController" method="post">
					<input type="hidden" name="command" value="logout"/>
					<input type="submit" value="Log out"/>
				</form>
			</div>

		</div>


		<div class="middle">
			<div id="menu">
				<p>
					<a href="FrontController?command=goToPage&address=%2Fjsp%2Fuser%2Fprofile.jsp">
						${goToProfile_button}
					</a>
				</p>
				<p>
					<a href="FrontController?command=goToPage&address=%2Fjsp%2Fuser%2Fbasket.jsp">
						${goToBasket_button}
					</a>
				</p>

			</div>

			<div id="content">
				<H1>Easy start.</H1>
				<p><a href="" >Search.</a></p>
				<p><a href="" >View all products.</a></p>
			</div>

		</div>

		<div class="footer" >
			<div id="footer" >
				<h1>footer</h1>
				<p>
					<a style="background-color: aquamarine" href="FrontController?command=goToPage&address=index.jsp">INDEX</a>
					<a style="background-color: aquamarine" href="FrontController?command=goToPage&address=%2Fjsp%2Fuser%2Fmain.jsp">main</a>

					
				</p>
			</div>
		</div>
	</body>
</html>