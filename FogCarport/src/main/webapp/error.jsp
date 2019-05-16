<%-- 
    Document   : error
    Created on : 26-04-2019, 10:43:11
    Author     : aamandajuhl
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@include file = "header.jsp" %>
<center id="partlist" class="partlist">
    <div>
        <%=request.getAttribute("message")%>
    </div>
</center>
</body>
</html>
