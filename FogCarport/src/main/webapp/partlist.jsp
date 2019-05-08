<%-- 
    Document   : partlist
    Created on : 23-04-2019, 15:24:04
    Author     : aamandajuhl
--%>

<%@page import="FunctionLayer.HelpingClasses.Order"%>
<%@page import="FunctionLayer.HelpingClasses.Carport"%>
<%@page import="FunctionLayer.HelpingClasses.Part"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file = "header.jsp" %>
<%        Order order = (Order) session.getAttribute("order");
          Carport carport = order.getCarport();
%>
<center id="partlist" class="partlist">
<div>
    <form action="Fog" method="POST">
        <h1>Stykliste</h1>
        <table class="partlist" id="partlist">
            <caption><h1>Træ</h1></caption>
            <tr>
                <th><b>Materiale</b></th>
                <th><b>Længde</b></th>
                <th><b>Antal</b></th>
                <th><b>Enhed</b></th>
                <th><b>Beskrivelse</b></th>
                <th><b>Pris</b></th>
            </tr>
            <%
                ArrayList<Part> parts = carport.getParts();
                ArrayList<Part> roof_parts = carport.getRoof().getParts();

                for (Part p : parts)
                {
                    if (p.getMaterial_class().equals("træ"))
                    {
                        out.println("<tr>");
                        out.println("<td>" + p.getName() + "</td>");
                        if (p.getLength() != 0)
                        {
                            out.println("<td>" + p.getLength() + "</td>");
                        } else
                        {
                            out.println("<td> </td>");
                        }
                        out.println("<td>" + p.getQuantity() + "</td>");
                        out.println("<td>" + p.getUnit() + "</td>");
                        out.println("<td>" + p.getDescription() + "</td>");
                        out.println("<td>" + p.getTotal_price() + " kr. </td>");
                    }
                }

                for (Part p : roof_parts)
                {
                    if (p.getMaterial_class().equals("træ"))
                    {
                        out.println("<tr>");
                        out.println("<td>" + p.getName() + "</td>");
                        if (p.getLength() != 0)
                        {
                            out.println("<td>" + p.getLength() + "</td>");
                        } else
                        {
                            out.println("<td> </td>");
                        }
                        out.println("<td>" + p.getQuantity() + "</td>");
                        out.println("<td>" + p.getUnit() + "</td>");
                        out.println("<td>" + p.getDescription() + "</td>");
                        out.println("<td>" + p.getTotal_price() + " kr. </td>");
                    }
                }
                if (carport.getShed() != null)
                {

                    ArrayList<Part> shed_parts = carport.getShed().getParts();
                    for (Part p : shed_parts)
                    {
                        if (p.getMaterial_class().equals("træ"))
                        {
                            out.println("<tr>");
                            out.println("<td>" + p.getName() + "</td>");
                            if (p.getLength() != 0)
                            {
                                out.println("<td>" + p.getLength() + "</td>");
                            } else
                            {
                                out.println("<td> </td>");
                            }
                            out.println("<td>" + p.getQuantity() + "</td>");
                            out.println("<td>" + p.getUnit() + "</td>");
                            out.println("<td>" + p.getDescription() + "</td>");
                            out.println("<td>" + p.getTotal_price() + " kr. </td>");
                        }
                    }
                }

            %>
        </table>
        <br><br>
        <table class="partlist" id="partlist">
            <caption><h1>Tag</h1></caption>
            <tr>
                <th><b>Materiale</b></th>
                <th><b>Længde</b></th>
                <th><b>Antal</b></th>
                <th><b>Enhed</b></th>
                <th><b>Beskrivelse</b></th>
                <th><b>Pris</b></th>
            </tr>
            <%                    for (Part p : roof_parts)
                {
                    if (p.getMaterial_class().equals("tag"))
                    {
                        out.println("<tr>");
                        out.println("<td>" + p.getName() + "</td>");
                        if (p.getLength() != 0)
                        {
                            out.println("<td>" + p.getLength() + "</td>");
                        } else
                        {
                            out.println("<td> </td>");
                        }
                        out.println("<td>" + p.getQuantity() + "</td>");
                        out.println("<td>" + p.getUnit() + "</td>");
                        out.println("<td>" + p.getDescription() + "</td>");
                        out.println("<td>" + p.getTotal_price() + " kr. </td>");
                    }
                }
            %>
        </table>
        <br><br>
        <table class="partlist" id="partlist">
            <caption><h1>Beslag og skruer</h1></caption>
            <tr>
                <th><b>Materiale</b></th>
                <th><b>Længde</b></th>
                <th><b>Antal</b></th>
                <th><b>Enhed</b></th>
                <th><b>Beskrivelse</b></th>
                <th><b>Pris</b></th>
            </tr>
            <%
                for (Part p : parts)
                {
                    if (p.getMaterial_class().equals("beslag og skruer"))
                    {
                        out.println("<tr>");
                        out.println("<td>" + p.getName() + "</td>");
                        if (p.getLength() != 0)
                        {
                            out.println("<td>" + p.getLength() + "</td>");
                        } else
                        {
                            out.println("<td> </td>");
                        }
                        out.println("<td>" + p.getQuantity() + "</td>");
                        out.println("<td>" + p.getUnit() + "</td>");
                        out.println("<td>" + p.getDescription() + "</td>");
                        out.println("<td>" + p.getTotal_price() + " kr. </td>");
                    }
                }

                for (Part p : roof_parts)
                {
                    if (p.getMaterial_class().equals("beslag og skruer"))
                    {
                        out.println("<tr>");
                        out.println("<td>" + p.getName() + "</td>");
                        if (p.getLength() != 0)
                        {
                            out.println("<td>" + p.getLength() + "</td>");
                        } else
                        {
                            out.println("<td> </td>");
                        }
                        out.println("<td>" + p.getQuantity() + "</td>");
                        out.println("<td>" + p.getUnit() + "</td>");
                        out.println("<td>" + p.getDescription() + "</td>");
                        out.println("<td>" + p.getTotal_price() + " kr. </td>");
                    }
                }
                if (carport.getShed() != null)
                {
                    ArrayList<Part> shed_parts = carport.getShed().getParts();
                    for (Part p : shed_parts)
                    {
                        if (p.getMaterial_class().equals("beslag og skruer"))
                        {
                            out.println("<tr>");
                            out.println("<td>" + p.getName() + "</td>");
                            if (p.getLength() != 0)
                            {
                                out.println("<td>" + p.getLength() + "</td>");
                            } else
                            {
                                out.println("<td> </td>");
                            }
                            out.println("<td>" + p.getQuantity() + "</td>");
                            out.println("<td>" + p.getUnit() + "</td>");
                            out.println("<td>" + p.getDescription() + "</td>");
                            out.println("<td>" + p.getTotal_price() + " kr. </td>");
                        }
                    }
                }
            %>
        </table>
        <br>
        <button name="command" value="employeeorder">Gå tilbage</button>
        <input type="hidden" id="order_id" name="selected" value="<%=order.getOrder_id()%>">

</div>
</center>
</form>
</body>
</html>
