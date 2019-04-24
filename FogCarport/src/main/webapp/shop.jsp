<%-- 
    Document   : formular
    Created on : 23-04-2019, 11:07:25
    Author     : benja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shop</title>
    </head>
    <body>
        <form action="Fog" method="POST">
            Bredde: <br>
            <input type="number" name="depth" value="200" min="200"><br>
            Dybde: <br>
            <input type="number" name="width" value="200" min="200"><br><br>
            <label> <input type="radio" name="roof" id="flat" value="flat" checked="checked"><span>Fladt tag</span></label>
            <label> <input type="radio" name="roof" id="sloped" value="sloped"><span>Tag med rejsning</span></label>

            <select name="slope">
                <option disabled selected>Vælg hældning</option>
                <%
                    for (int i = 15; i <= 45; i = i + 5)
                    {
                        out.println("<option>" + i + "</option>");
                    }
                %>
            </select>
            <select name="roof">
                <option disabled selected></option>
                <%
                    for (int i = 15; i <= 45; i = i + 5)
                    {
                        out.println("<option>" + i + "</option>");
                    }
                %>
            </select>
            <button name="command" value="calculate">Beregn carport</button>
        </form> 
    </body>
</html>
