<%-- 
    Document   : employeeorder
    Created on : 08-05-2019, 09:55:26
    Author     : sofieamalielandt
--%>

<%@page import="FunctionLayer.Enum.Paid"%>
<%@page import="FunctionLayer.Enum.Status"%>
<%@page import="java.util.List"%>
<%@page import="FunctionLayer.HelpingClasses.RoofType"%>
<%@page import="FunctionLayer.HelpingClasses.Order"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@include file = "header.jsp" %>
<center class="index" id="index"> 
    <form action="Fog" method="POST">
        <div id="shop">
            <%                Order order = (Order) session.getAttribute("order");
                out.println("Ordrer: " + order.getOrder_id() + ", Bestilt: " + order.getOrder_date() + "<br><br>");
                out.println("Kundenummer: " + order.getUser().getId() + " - Navn: " + order.getUser().getName() + "<br>");
                out.println("E-mail: " + order.getUser().getEmail() + " - Telefon: " + order.getUser().getPhone() + "<br>");
                out.println("Adresse: " + order.getUser().getAddress() + ", " + order.getUser().getZipcode() + "<br><br>");
                out.println("Afsendt: " + order.getShipped() + "<br>");

                if (order.getShipped().equals("Ordren er endnu ikke afsendt"))
                {
                    out.println("<br><button name=\"command\" value=\"shipped\">Afsend ordren</button><br>");
                }
            %>
            <br>
            <select name="status">
                <%
                    for (Status s : Status.values())
                    {
                        if (s.equals(order.getStatus()))
                        {
                            out.println("<option selected> " + s + "</option>");
                        } else
                        {
                            out.println("<option> " + s + "</option>");
                        }
                    }
                %>
            </select>
            &nbsp;&nbsp;
            <select name="paid">
                <%
                    for (Paid p : Paid.values())
                    {
                        if (p.equals(order.getPaid()))
                        {
                            out.println("<option selected> " + p + "</option>");
                        } else
                        {
                            out.println("<option> " + p + "</option>");
                        }
                    }
                %>
            </select> 
            <br><br><button name="command" value="update">Opdater</button>
        </div>
    </form>
    <br>
    <form action="Fog" method="POST">
        <div>
            <%
                String pricemessage = (String) request.getAttribute("pricemessage");
                if (pricemessage != null)
                {
            %>
            <p><%=pricemessage%></p><br>  
            <%            }
            %>
            Salgspris:&nbsp;&nbsp;<input type="number" name="salesprice" value="<%=order.getSales_price()%>"> Kr.
            <br><br>
            Købspris:&nbsp;&nbsp;<%=order.getCarport().getTotal_price()%> Kr. 
            <br>
            Profit:&nbsp;&nbsp;<%=order.getProfit()%>%
            <br><br>
            <button  name="command" value="profit" onclick="setUpdate(0)">Beregn profit</button>
            &nbsp;&nbsp;
            <button  name="command" value="profit" onclick="setUpdate(1)">Fastlæg prisen</button>

            <input type="hidden" id="update" name="update" value="0">
            <script>
                function setUpdate(update) {
                    document.getElementById('update').value = update;
                }
            </script>
        </div>
    </form>
    <br>

    <form action="Fog" method="POST">
        <div id="shop">
            <%
                String errormessage = (String) request.getAttribute("errormessage");
                if (errormessage != null)
                {
            %>
            <p><%=errormessage%></p>
            <%            }
            %>
            <br>
            Bredde:&nbsp;&nbsp;<input type="number" pattern="[0-2000]*" name="width" id="width" value="<%=order.getCarport().getWidth()%>" min="240" max="750" disabled="disabled">
            &nbsp;&nbsp;
            Dybde:&nbsp;&nbsp;<input type="number" pattern="[0-2000]*" name="depth" id="depth" value="<%=order.getCarport().getDepth()%>" min="240" max="800" disabled="disabled">
            <br><br>
            <% if (order.getCarport().getRoof().getSlope() == 0)
                {
                    out.println("<label> <input type=\"radio\" name=\"roof\" id=\"flat\" value=\"flat\" onclick=\"disable()\" checked=\"checked\" disabled=\"disabled\"><span>Fladt tag</span></label>");
                    out.println("<label> <input type=\"radio\" name=\"roof\" id=\"sloped\" value=\"sloped\" onclick=\"enable()\" disabled=\"disabled\"><span>Tag med rejsning</span></label>");
                } else
                {
                    out.println("<label> <input type=\"radio\" name=\"roof\" id=\"flat\" value=\"flat\" onclick=\"disable()\" disabled=\"disabled\"><span>Fladt tag</span></label>");
                    out.println("<label> <input type=\"radio\" name=\"roof\" id=\"sloped\" value=\"sloped\" onclick=\"enable()\" checked=\"checked\" disabled=\"disabled\"><span>Tag med rejsning</span></label>");
                }
            %>
            <br><br>
            <select name="type" id="type" disabled="disabled">
                <option disabled value="0">Vælg tagtype</option> 
                <%
                    List<RoofType> slopedroofs = (List<RoofType>) session.getAttribute("slopedroofs");
                    List<RoofType> flatroofs = (List<RoofType>) session.getAttribute("flatroofs");

                    if (order.getCarport().getRoof().getSlope() == 0)
                    {
                        for (RoofType ro : flatroofs)
                        {
                            if (order.getCarport().getRoof().getType().getId() == ro.getId())
                            {
                                out.println("<option selected value=\"" + ro.getId() + "\" class=\"fladt\">" + ro.getName() + "</option>");

                            } else
                            {

                                out.println("<option value=\"" + ro.getId() + "\" class=\"fladt\">" + ro.getName() + "</option>");
                            }
                        }

                        for (RoofType r : slopedroofs)
                        {
                            out.println("<option value=\"" + r.getId() + "\" class=\"rejsning\" disabled=\"disabled\" hidden=\"hidden\">" + r.getName() + "</option>");
                        }
                    } else
                    {
                        for (RoofType ro : flatroofs)
                        {
                            out.println("<option value=\"" + ro.getId() + "\" class=\"fladt\"  disabled=\"disabled\" hidden=\"hidden\">" + ro.getName() + "</option>");
                        }

                        for (RoofType r : slopedroofs)
                        {
                            if (order.getCarport().getRoof().getType().getId() == r.getId())
                            {
                                out.println("<option selected value=\"" + r.getId() + "\" class=\"rejsning\">" + r.getName() + "</option>");
                            } else
                            {
                                out.println("<option value=\"" + r.getId() + "\" class=\"rejsning\">" + r.getName() + "</option>");
                            }
                        }
                    }

                %>
            </select>
            <br><br>
            <select name="slope" id="hældning" disabled="disabled">
                <option selected>0</option>
                <%                    for (int i = 15; i <= 45; i = i + 5)
                    {
                        out.println("<option> " + i + "</option>");

                        if (i == order.getCarport().getRoof().getSlope())
                        {
                            out.println("<option selected> " + i + "</option>");
                        }
                    }
                %>
            </select>
            <br><br>
            <select name="shed" id="shed" onchange="show(this.value)" disabled="disabled">
                <%
                    if (order.getCarport().getShed() == null)
                    {
                        out.println("<option value=\"Med skur\">Med skur</option>");
                        out.println("<option selected value=\"Uden skur\">Uden skur</option>");
                    } else
                    {
                        out.println("<option selected value=\"Med skur\">Med skur</option>");
                        out.println("<option value=\"Uden skur\">Uden skur</option>");
                    }

                %>
            </select>       
        </div>
        <br>
        <%            if (order.getCarport().getShed() == null)
            {
                out.println("<div id=\"skur\" hidden=\"true\">");
            } else
            {
                out.println("<div id=\"skur\">");
            }
        %>

        OBS! Skuret skal have min. 15 cm udhæng på alle sider <br>
        og skal derfor være mindst 30 cm smallere og kortere end carporten 
        <br><br>
        <%
            if (order.getCarport().getShed() != null)
            {
                out.println("Bredde af skur:&nbsp;&nbsp;<input type=\"number\" pattern=\"[0-2000]*\" name=\"shedWidth\" id=\"shedWidth\" value=\"" + order.getCarport().getShed().getWidth() + "\" min=\"210\" max=\"770\" disabled=\"disabled\">");
                out.println("Dybde af skur:&nbsp;&nbsp;<input type=\"number\" pattern=\"[0-2000]*\" name=\"shedDepth\" id=\"shedDepth\" value=\"" + order.getCarport().getShed().getDepth() + "\"min=\"210\" max=\"720\" disabled=\"disabled\">&nbsp;&nbsp;");

            } else
            {
                out.println("Bredde af skur:&nbsp;&nbsp;<input type=\"number\" pattern=\"[0-2000]*\" name=\"shedWidth\" id=\"shedWidth\" value=\"0\" min=\"210\" max=\"770\" disabled=\"disabled\">");
                out.println("Dybde af skur:&nbsp;&nbsp;<input type=\"number\" pattern=\"[0-2000]*\" name=\"shedDepth\" id=\"shedDepth\" value=\"0\"min=\"210\" max=\"720\" disabled=\"disabled\">&nbsp;&nbsp;");
            }
        %>
        </div>
        <br>
        <div>
            <button id="save" name="command" value="updatecarport" style="display:none;">Gem ændringer</button>
            &nbsp;&nbsp;
            <button name="command" value="viewpartlist">Se stykliste</button>
            &nbsp;&nbsp;
            <button name="command" value="viewdrawing">Se tegning</button>
        </div>
    </form>
    <br>
    <div id="button"><button id="rediger" onclick="update()">Rediger carport</button> </div>

    <script>

        function update() {
            document.getElementById("width").removeAttribute("disabled");
            document.getElementById("depth").removeAttribute("disabled");
            document.getElementById("flat").removeAttribute("disabled");
            document.getElementById("sloped").removeAttribute("disabled");
            document.getElementById("type").removeAttribute("disabled");
            document.getElementById("hældning").removeAttribute("disabled");
            document.getElementById("shed").removeAttribute("disabled");
            document.getElementById("shedDepth").removeAttribute("disabled");
            document.getElementById("shedWidth").removeAttribute("disabled");
            document.getElementById("save").style.display = "inline-block";
            document.getElementById("rediger").style.display = "none";
            document.getElementById("button").setAttribute("hidden", "true");

        }
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
                x.setAttribute("disabled", "disabled");
                x.setAttribute("hidden", "hidden");
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
                x.setAttribute("disabled", "disabled");
                x.setAttribute("hidden", "hidden");
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
</center>
<br><br><br>
<div class="footer">
    <p>&copy; Copyright 2019 Amanda Juhl Hansen, Sofie Amalie Landt & Benjamin Kongshaug&nbsp;&nbsp;</p>
</div>
</body>
</html>
