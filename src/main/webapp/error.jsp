<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String message = (String) request.getAttribute("errorMessage");%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Error page</title>
</head>
<body>

<hr/>

<h1>Error ocupied:</h1>
<%=message%>
 <h1></h1> 
 
 <h1><a href="index.jsp">Return to INDEX</a></h1>
</body>
</html>