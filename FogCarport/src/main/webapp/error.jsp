<%-- 
    Document   : error
    Created on : 26-04-2019, 10:43:11
    Author     : aamandajuhl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body>
        <%=request.getAttribute("message")%>
    </body>
</html>
