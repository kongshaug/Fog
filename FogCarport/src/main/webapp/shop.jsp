<%-- 
    Document   : formular
    Created on : 23-04-2019, 11:07:25
    Author     : benja
--%>

<%@page import="java.util.List"%>
<%@page import="FunctionLayer.RoofType"%>
<%@page import="javafx.scene.control.RadioButton"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shop</title>
    </head>
    <body>
        <br>
        <%

            String errormessage = (String) session.getAttribute("errormessage");

            if (errormessage != null)
            {
        %>
        <p><%=errormessage%></p>
        <%
                session.removeAttribute("errormessage");
            }
        %>

        <form action="Fog" method="POST">
            Bredde: <br>
            <input type="number" pattern="[0-2000]*" name="depth" value="240" min="240" max="750"><br>
            Dybde: <br>
            <input type="number" pattern="[0-2000]*" name="width" value="240" min="240" max="800"><br><br>
            <label> <input type="radio" name="roof" id="flat" value="flat" onclick="disable()"><span>Fladt tag</span></label>
            <label> <input type="radio" name="roof" id="sloped" value="sloped"onclick="enable()" checked="checked"><span>Tag med rejsning</span></label>

            <br><br>
            <select name="type" id="type">
                <option disabled selected value="0">Vælg tagtype</option>            
                <%
                    List<RoofType> slopedroofs = (List<RoofType>) session.getAttribute("slopedroofs");
                    List<RoofType> flatroofs = (List<RoofType>) session.getAttribute("flatroofs");

                    for (RoofType ro : flatroofs)
                    {
                        out.println("<option value=\"" + ro.getId() + "\" class=\"fladt\" disabled=\"disabled\" hidden=\"hidden\">" + ro.getName() + "</option>");
                    }

                    for (RoofType r : slopedroofs)
                    {
                        out.println("<option value=\"" + r.getId() + "\" class=\"rejsning\">" + r.getName() + "</option>");
                    }

                %>
            </select>

            <br><br>
            <select name="slope" id="hældning">
                <option disabled selected value="0">Vælg hældning</option>
                <%                    for (int i = 15;
                            i <= 45; i = i + 5)
                    {
                        out.println("<option> " + i + "</option>");
                    }
                %>
            </select>

            <br>
            <br>

            <select name="shed" onchange="show(this.value)">
                <option disabled selected>Vælg skur</option>
                <option value="Med skur">Med skur</option>
                <option value="Uden skur">Uden skur</option>
            </select>

            <br>
            <br>
            <div id="skur" hidden="true">
                <p>OBS! Skuret skal have min. 15 cm udhæng på alle sider <br>
                    og skal derfor være mindst 30 cm smallere og kortere end carporten</p>
                Bredde af skur:<br>
                <input type="number" pattern="[0-2000]*" name="shedDepth" value="210" min="210" max="720"><br>
                Dybde afskur: <br>
                <input type="number" pattern="[0-2000]*" name="shedWidth" value="210" min="210" max="770"><br><br>
            </div>

            <br><br>
            <button name="command" value="calculate">Beregn carport</button>

            <script>

                function disable()
                {
                    var flatoptions = [...document.getElementsByClassName("fladt")];

                    flatoptions.forEach(function (x)
                    {
                        x.removeAttribute("disabled");
                        x.removeAttribute("hidden");
                    });
                    var slopeoptions = [...document.getElementsByClassName("rejsning")];

                    slopeoptions.forEach(function (x)
                    {
                        
                        x.setAttribute("disabled","disabled");
                        x.setAttribute("hidden","hidden");
                    });

                    document.getElementById("hældning").disabled = true;
                    document.getElementById("type").value = 0;
                    document.getElementById("hældning").value = 0;

                }

                function enable()
                {
                    var flatoptions = [...document.getElementsByClassName("fladt")];

                    flatoptions.forEach(function (x)
                    {
                        x.setAttribute("disabled","disabled");
                        x.setAttribute("hidden","hidden");
                    });
                    var slopeoptions = [...document.getElementsByClassName("rejsning")];

                    slopeoptions.forEach(function (x)
                    {
                        x.removeAttribute("disabled");
                        x.removeAttribute("hidden");
                    });

                    document.getElementById("hældning").disabled = false;
                    document.getElementById("type").value = 0;
                    document.getElementById("hældning").value = 0;
                }

                function show(value)
                {
                    if (value === "Med skur")
                    {
                        document.getElementById("skur").removeAttribute("hidden");
                    } else 
                    {
                        document.getElementById("skur").setAttribute("hidden", "true");
                    }
                }

            </script>
        </form> 
    </body>
</html>
