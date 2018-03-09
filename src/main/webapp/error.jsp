<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Error page</title>
</head>
<body style="background-color: burlywood">

<hr/>

<h1>Error ocupied:</h1>

 <h2><%=(String) request.getAttribute("errorMessage")%></h2>
 <!-- sessionScope.address = /jsp/FOLDER/PAGE.JSP - нужно вытянуть только PAGE.JSP -->
 <h3><a href="FrontController?command=goToPage&address=${sessionScope.address}">return to last page</a></h3>
 <h3><a href="FrontController?command=goToPage&address=index.jsp">return to INDEX</a></h3>
</body>
</html>