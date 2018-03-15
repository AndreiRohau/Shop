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
                <form action="FrontController" method="post">
                    <span><c:out value="${requestScope.isAdded}"/></span>
                    <input type="hidden" name="command" value="addNewProduct"/>
                    <p><b>Set product name: </b><br/>
                    <input type="text" name="name" value=""/></p><br/>
                    <p><b>Set product type: </b><br/>
                    <input type="text" name="type" value=""/></p><br/>
                    <p><b>Set product price: </b><br/>
                    <input type="text" name="price" value=""/></p><br/>
                    <p><b>Set description: </b><br/>
                    <textarea rows="10" cols="45" name="description"></textarea></p><br/>
                    <p><input type="submit" name="Save_new_product" value="ADD"/></p>
                </form>
            </div>

            <div id="content">
                <form action="FrontController" method="post">
                    <p><b>Get all products</b>
                        <input type="hidden" name="command" value="selectAllProducts"/>
                        <input type="submit" name="get_products" value="Get them!"/>
                    </p>
                </form>
                <hr/>
                <H1></H1>
                <p>HERE ARE</p>
                <c:forEach items="${requestScope.productArray}" var="productToEdit">
                    <div style="display:inline-block;">
                        <p>Id - ${productToEdit.id} | Name - ${productToEdit.name} | Type - ${productToEdit.type} | Price - ${productToEdit.price}</p>
                        <form action="FrontController" method="post">
                            <input type="hidden" name="command" value="editProduct" />
                            <input type="hidden" name="productId" value="${productToEdit.id}" />
                            <input type="submit" name="edit" value="Edit" /><br/>
                        </form>
                    </div>
                    <hr/>
                </c:forEach>
            </div>
        </div>

        <div class="footer" >
            <div id="footer" >
                <h1>footer</h1>
                <p>
                    <a href="FrontController?command=goToPage&address=index.jsp">INDEX</a>
                      -->
                    <a href="FrontController?command=goToPage&address=main.jsp">ADMINISTRATION</a>
                    -->
                    <a href="FrontController?command=goToPage&address=manageProducts.jsp">GOODS</a>
                </p>
            </div>
        </div>
    </body>
</html>