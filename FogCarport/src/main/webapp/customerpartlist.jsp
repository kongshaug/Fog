<%-- 
    Document   : customerpartlist
    Created on : 09-05-2019, 14:00:50
    Author     : sofieamalielandt
--%>

<%@page import="FunctionLayer.HelpingClasses.Carport"%>
<%@page import="FunctionLayer.HelpingClasses.Order"%>
<%@page import="FunctionLayer.HelpingClasses.Part"%>
<%@page import="java.util.ArrayList"%>
<%@page import="FunctionLayer.Enum.Role"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@include file = "header.jsp" %>
<%    Order order = (Order) session.getAttribute("order");
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
                            }
                        }
                    }
                %>
            </table>
            <br><br>
            <button name="command" value="customerorder">Gå tilbage</button>
            <input type="hidden" id="order_id" name="selected" value="<%=order.getOrder_id()%>">
            </div>
            </center>
            <br><br><br>
            <div class="footer">
                <p>&copy; Copyright 2019 Amanda Juhl Hansen, Sofie Amalie Landt & Benjamin Kongshaug&nbsp;&nbsp;</p>
            </div>
        </form>
        </body>
        </html>
