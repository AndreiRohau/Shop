<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>ManageShop</title>

    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="localization.local" var="loc" />
    <fmt:message bundle="${loc}" key="local.shop" var="shop" />
    <fmt:message bundle="${loc}" key="local.hello" var="hello" />
    <fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
    <fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
    <fmt:message bundle="${loc}" key="local.locbutton.name.ch" var="ch_button" />

    <fmt:message bundle="${loc}" key="local.login" var="login" />


</head>
<body>
<div class="header">

    <div id="header1">
        <p><c:out value="${shop}"/></p>
        <p><c:out value="${hello}"/> <c:out value="${sessionScope.userName}"/> !!!</p>
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
        <form action="FrontController" method="post">
            <span><c:out value="${requestScope.isAdded}"/></span>
            <input type="hidden" name="command" value="addNewProduct"/>
            <c:out value="${login}" />: PRODUCT!!! NAME<br/>
            <input type="text" name="name" value=""/><br/>
            <c:out value="${login}" />: PRODUCT!!! TYPE<br/>
            <input type="text" name="type" value=""/><br/>
            <c:out value="${login}" />: PRODUCT!!! PRICE<br/>
            <input type="text" name="price" value=""/><br/>
            <input type="submit" name="sign in" value="ADD_NEW_PROD!!!"/>
        </form>
    </div>

    <div id="content">
        <H1>SOME IMPORTANT CONTANT OF THIS PAGE</H1>
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
            tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
            quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
            consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
            cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
            proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
    </div>

</div>

        <div class="footer" >
            <div id="footer" >
                <h1>footer</h1>
                <p>
                    <a href="FrontController?command=goToPage&address=index.jsp">INDEX</a>
                      -->
                    <a href="FrontController?command=goToPage&address=main.jsp">administration</a>
                </p>
            </div>
        </div>
    </body>
</html>