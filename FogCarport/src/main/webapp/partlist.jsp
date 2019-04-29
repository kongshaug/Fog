<%-- 
    Document   : partlist
    Created on : 23-04-2019, 15:24:04
    Author     : aamandajuhl
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="FunctionLayer.Part"%>
<%@page import="FunctionLayer.Carport"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Partlist</title>
    </head>
    <%
        Carport carport = (Carport) session.getAttribute("carport");
    %>
    <body>
        <form action="Fog" method="POST">
            <br><br>
            <table>
                <caption>Træ</caption>
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
            <table>
                <caption>Tag</caption>
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
            <table>
                <caption>Beslag og skruer</caption>
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
        </form>
    </body>
</html>
