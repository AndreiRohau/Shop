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

		<c:set var="current_page" value="${requestScope.get('currentPage')}"/>
		<c:set var="max_page" value="${requestScope.get('maxPage')}"/>

	</head>
	<body>
		<div class="header">

			<div id="header1">
				<p><c:out value="${shop}"/></p>
				<p><c:out value="${hello}"/> <c:out value="${sessionScope.userName}"/>!!!</p>
			</div>

			<div id="header2" style="display:flex; flex-flow: row wrap; justify-content:space-between">
				<div>
					<form action="FrontController" method="post">
						<input type="hidden" name="command" value="change_language"/>
						<input type="hidden" name="local" value="en"/>
						<input type="submit" value="ENG"/>
					</form>
					<form action="FrontController" method="post">
						<input type="hidden" name="command" value="change_language"/>
						<input type="hidden" name="local" value="ru"/>
						<input type="submit" value="РУС"/>
					</form>
					<form action="FrontController" method="post">
						<input type="hidden" name="command" value="change_language"/>
						<input type="hidden" name="local" value="ch"/>
						<input type="submit" value="汉语"/>
					</form>
				</div>
				<div>
					<form action="FrontController" method="post" style="height:100%">
						<input type="hidden" name="command" value="logout"/>
						<input type="submit" value="Log out" style="height:100%"/>
					</form>
				</div>
			</div>


		</div>


		<div class="middle">
			<div id="menu">
				<p>
					<a href="FrontController?command=goToPage&address=profile.jsp">
						${goToProfile_button}
					</a>
				</p>
				<p>
					<a href="FrontController?command=goToPage&address=basket.jsp">
						${goToBasket_button}
					</a>
				</p>

			</div>

			<div id="content">
				<p><a href="" >Search.</a></p>

				<br/>
				<hr/>

				<c:out value="${requestScope.get('msg')}"/>
				<form action="FrontController" method="post">
					<p><b>Get all products</b>
						<input type="hidden" name="command" value="selectAllProducts"/>
						<input type="hidden" name="page_num" value="1"/>
						<input type="submit" name="get_products" value="Get them!"/>
					</p>
				</form>
				<hr/>
				<br/>

				<c:if test="${current_page != null}">
					<table width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bgcolor="#ffebcd">
						<tr>
							<td>INFO</td>
							<td>COMPANY</td>
							<td>NAME</td>
							<td>TYPE</td>
							<td>PRICE</td>
							<td>BUY</td>
						</tr>
						<c:forEach items="${requestScope.productArray}" var="productToEdit">
							<tr>
								<td>
									<form action="FrontController" method="post">
										<input type="hidden" name="command" value="productInfo" />
										<input type="hidden" name="productId" value="${productToEdit.id}" />
										<input type="submit" name="info" value="INFO" /><br/>
									</form>
								</td>
								<td>${productToEdit.company}</td>
								<td>${productToEdit.name}</td>
								<td>${productToEdit.type}</td>
								<td>${productToEdit.price}</td>
								<td>
									<form action="FrontController" method="post">
										<input type="hidden" name="command" value="addToBasket" />
										<input type="hidden" name="productId" value="${productToEdit.id}" />
										<input type="submit" name="buy" value="BUY" /><br/>
									</form>
								</td>
							</tr>
						</c:forEach>
					</table>

					<div width="100%" style="background-color: deepskyblue; font-size: 1em">    
						<c:forEach begin="1" end="${max_page}" var="i">
				            <c:if test="${i != current_page}">
				        		<a href="FrontController?command=selectAllProducts&page_num=${i}">${i}</a>
				            </c:if>
				            <c:if test="${i == current_page}">
				                <c:out value="${i}"/>
					        </c:if>
					    </c:forEach>
					</div>
				</c:if>
			</div>

		</div>

		<div class="footer" >
			<div id="footer" >
				<h1>footer</h1>
				<p>
					<a href="FrontController?command=goToPage&address=index.jsp">INDEX</a>
					  -->
					<a href="FrontController?command=goToPage&address=main.jsp">MAIN</a>

					<c:if test="${current_page != null}">
						-->
						 <a href="FrontController?command=selectAllProducts&page_num=${current_page}">Page: ${current_page}</a>
					</c:if>
				</p>
			</div>
		</div>
	</body>
</html>