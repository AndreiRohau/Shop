<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Edit Client</title>

        <fmt:setLocale value="${sessionScope.local}" />
        <fmt:setBundle basename="localization.local" var="loc" />
        <fmt:message bundle="${loc}" key="local.shop" var="shop" />
        <fmt:message bundle="${loc}" key="local.hello" var="hello" />
        <fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
        <fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
        <fmt:message bundle="${loc}" key="local.locbutton.name.ch" var="ch_button" />
        <fmt:message bundle="${loc}" key="local.deleteAccountButton" var="deleteAccountButton" />
        <c:set var="product_id" value="${requestScope.get('productToEdit').id}"/>
        <c:set var="product_name" value="${requestScope.get('productToEdit').name}"/>
        <c:set var="product_type" value="${requestScope.get('productToEdit').type}"/>
        <c:set var="product_price" value="${requestScope.get('productToEdit').price}"/>
        <c:set var="product_description" value="${requestScope.get('productToEdit').description}"/>

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
                    <p>Get all users</p>
                    <input type="hidden" name="command" value="selectAllProducts"/>
                    <input type="submit" name="get_products" value="Get them!"/>
                </form>
            </div>

            <div id="content">
                <div style="display:flex; flex-flow: row wrap; justify-content:space-between">
                    <div>
                        <H1></H1>
                        <p>Edit product</p>
                        <p>ID is: ${product_id}</p>
                        <p>Name is: ${product_name}</p>
                        <p>Type is: ${product_type}</p>
                        <p>Price is: ${product_price}</p>
                    </div>
                    <div>
                        <form action="FrontController" method="post">
                            <input type="hidden" name="command" value="delete_product"/><br/>
                            <input type="hidden" name="id" value="${product_id}"/><br/>
                            <input type="submit" name="delete" value="${deleteAccountButton}"/>
                        </form>
                    </div>
                </div>
                <hr/>
                <form action="FrontController" method="post">
                    <input type="hidden" name="command" value="updateProduct" />
                    <input type="hidden" name="id" value="${product_id}" />
                    <p>Name:<br/>
                    <input type="text" name="name" value="${product_name}" title=""/></p><br/>
                    <p>Type:<br/>
                    <input type="text" name="type" value="${product_type}" title=""/></p><br/>
                    <p>Price:<br/>
                    <input type="text" name="price" value="${product_price}" title=""/></p><br/>
                    <p>Set description:<br/>
                        <textarea rows="10" cols="45" name="description">${product_description}</textarea></p><br/>
                    <input type="submit" name="edit" value="Edit" />
                </form>

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
                    -->
                    <a href="FrontController?command=editProduct&productId=${product_id}" style="text-transform: uppercase">${product_name}</a>
                </p>
            </div>
        </div>
    </body>
</html>