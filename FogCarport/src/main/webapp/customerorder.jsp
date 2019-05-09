<%-- 
    Document   : customerorder
    Created on : 09-05-2019, 10:06:12
    Author     : sofieamalielandt
--%>

<%@page import="FunctionLayer.Enum.Paid"%>
<%@page import="FunctionLayer.Enum.Status"%>
<%@page import="FunctionLayer.HelpingClasses.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file = "header.jsp" %>

<center class="index" id="index"> 
    <form action="Fog" method="POST">
        <div id="shop">
            <%                Order order = (Order) session.getAttribute("order");
                out.println("Ordrer: " + order.getOrder_id() + ", Bestilt: " + order.getOrder_date() + "<br>");
                out.println("Afsendt: " + order.getShipped() + "<br>");
            %>
            <br>
            <select name="status" disabled="disabled">
                <%                    out.println("<option selected> " + order.getStatus() + "</option>");
                %>
            </select>
            &nbsp;&nbsp;
            <select name="paid" disabled="disabled">
                <%
                    out.println("<option selected> " + order.getPaid() + "</option>");
                %>
            </select> 
            <br><br>
            <b>Pris:&nbsp;&nbsp;<%=order.getSales_price()%> Kr.</b>
        </div>
    </form>
    <br>

    <form action="Fog" method="POST">
        <div id="shop">
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
                <%                    out.println("<option selected value=\"" + order.getCarport().getRoof().getId() + "\" class=\"fladt\">" + order.getCarport().getRoof().getType().getName() + "</option>");
                %>
            </select>
            <br><br>
            <select name="slope" id="hældning" disabled="disabled">
                <%
                    out.println("<option selected> " + order.getCarport().getRoof().getSlope() + "</option>");
                %>
            </select>
            <br><br>
            <select name="shed" id="shed" onchange="show(this.value)" disabled="disabled">
                <%                    
                    if (order.getCarport().getShed() == null)
                    {
                        out.println("<option selected value=\"Uden skur\">Uden skur</option>");
                    } else
                    {
                        out.println("<option selected value=\"Med skur\">Med skur</option>");
                    }
                %>
            </select>       
        </div>
        <br>
        <%        
            if (order.getCarport().getShed() == null)
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
                out.println("Bredde af skur:&nbsp;&nbsp;<input type=\"number\" pattern=\"[0-2000]*\" name=\"shedDepth\" id=\"shedDepth\" value=\"" + order.getCarport().getShed().getDepth() + "\"min=\"210\" max=\"720\" disabled=\"disabled\">&nbsp;&nbsp;");
                out.println("Dybde afskur:&nbsp;&nbsp;<input type=\"number\" pattern=\"[0-2000]*\" name=\"shedWidth\" id=\"shedWidth\" value=\"" + order.getCarport().getShed().getWidth() + "\" min=\"210\" max=\"770\" disabled=\"disabled\">");
            } 

        %>
        </div>
        <br>
        <%
            if(order.getPaid().equals(Paid.BETALT))
            {
                out.println("<div><button name=\"command\" value=\"viewpartlist\">Se stykliste</button></div>");
            }
        
        %>
    </form>
</body>
</html>
