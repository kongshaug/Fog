<%-- 
    Document   : customerheader
    Created on : 02-05-2019, 12:42:20
    Author     : sofieamalielandt
--%>

<%@page import="FunctionLayer.Enum.Role"%>
<%@page import="FunctionLayer.HelpingClasses.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <title>Fog</title>
        <style>
            .menu ul {
                list-style-type: none;
                margin: 0;
                padding: 0;
                overflow: hidden;
                background-color: rgba(7, 72, 133, 1.0);
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;  
                height: 80px;
                color: whitesmoke;
                font-family: Avenir Next Condensed;  
                z-index: 9999;
            }

            .menu li p {
                float: right;
                display: block;
                color: whitesmoke;
                text-align: center;
                padding: 10px 15px;
                text-decoration: none;
                font-size: 18px;
                height: 80px;
            }

            .menu li button {
                float: right;
                display: inline-block;
                text-align: center;
                background-color: rgba(7, 72, 133, 1.0);
                text-decoration: none;
                position: static; width: 120px; 
                color: whitesmoke;
                border: none;
                font-family: Avenir Next Condensed;
                font-size: 18px;
                height: 80px;
            }

            .menu li #img {
                float: left;
                display: inline-block;
                text-align: center;
                padding: 5px 0px;
                text-decoration: none; 
                position: static; width: 80px; 
                display: -webkit-flex;
                -webkit-align-items: center; 
            }

            .menu li #user {
                float: left;
                display: inline-block;
                text-align: center;
                padding: 5px 5px;
                text-decoration: none; 
                position: static; width: 80px; 
                display: -webkit-flex;
                -webkit-align-items: center; 
            }

            .menu li #home {
                float: left;
                display: inline-block;
                text-align: center;
                padding: 5px 5px;
                text-decoration: none; 
                position: static; width: 80px; 
                display: -webkit-flex;
                -webkit-align-items: center; 
            }
            .menu li #img img {
                text-align: center;
                padding: 10px 10px 5px 10px;
                width: 60px;
                height: 60px;
                text-decoration: none;  
                display: -webkit-flex;
                -webkit-align-items: center; 
            }

            .menu li #user img {
                text-align: center;
                padding: 10px 10px 0px 10px;
                width: 40px;
                height: 40px;
                text-decoration: none;  
                display: -webkit-flex;
                -webkit-align-items: center; 
            }

            .menu li #home img {
                text-align: center;
                padding: 10px 10px 10px 10px;
                width: 50px;
                height: 50px;
                text-decoration: none;  
                display: -webkit-flex;
                -webkit-align-items: center; 
            }

            .menu li button img:hover, .dropdown:hover {
                opacity: 0.6;
            }

            .menu button:hover {
                font-size: 20px;
                opacity: 0.6;
            }

            #menu img{
                position: fixed;
                left:0;
                top: 11%;
            }

            #index select{
                position: relative;  
                display: inline-block;
                align-items: center;
                background-color: transparent;
                border: solid;
                border-color: whitesmoke;
                text-decoration: none;
                font-size: 16px;
                text-align: center;
                padding: 5px 5px;
                border-radius: 10px;
                border-width: thin;
                color: whitesmoke;
                font-family: Avenir Next Condensed;
            }

            .order button {
                background-color: rgba(7, 72, 133, 1.0);
                border:  none;
                color: whitesmoke;
                padding: 10px 24px; /* Some padding */
                width: 100%; /* Set a width if needed */
                display: block; /* Make the buttons appear below each other */
                font-family: Avenir Next Condensed;
                font-size: 16px;
                align-items: center;
                margin: 0;
                border-radius: 10px;
            }

            .order button:not(:last-child) {
                border-bottom: none; /* Prevent double borders */
            }

            /* Add a background color on hover */
            .order button:hover {
                background-color: whitesmoke;
                color: rgba(7, 72, 133, 1.0);
            }

            .order div {
                border-style: none;
                border-radius: 10px;
                padding: 40px 0px 40px 0px;
                width: 55%;
                background-color: rgba(7, 72, 133, 1.0);
                text-align: center; 
                color: whitesmoke;
                font-family: Avenir Next Condensed;
            }

            .order input {
                position: relative;  
                display: inline-block;
                align-items: center;
                background-color: transparent;
                border: solid;
                border-color: whitesmoke;
                text-decoration: none;
                font-size: 16px;
                text-align: center;
                padding: 5px 5px 5px 5px;
                border-radius: 10px; 
                border-width: thin;
                color: whitesmoke;
                font-family: Avenir Next Condensed;
                width: 50%;
            }

            .order input:hover {
                background-color: whitesmoke;
                color: rgba(7, 72, 133, 1.0);
                border-radius: 10px;
            }

            .order ::-webkit-input-placeholder {
                color: whitesmoke;
                opacity: 0.8;

            }


        </style>
    </head>
    <body>
        <%
            User user = (User) session.getAttribute("user");
        %>
        <form action="Fog" method="post">
            <center class="menu">
                <ul>
                    <li><button id="img" name="command" value="shop"><img src="images/logo.png"></button></li>

                    <%
                        if (user != null)
                        {
                            out.println("<li><button name=\"command\" value=\"logout\">Log ud</button></li>");
                            out.println("<li><p>" + user.getEmail() + "&nbsp;&nbsp</p></li>");

                            if (user.getRole() == Role.CUSTOMER)
                            {
                                out.println(" <li><button id=\"user\" name=\"command\" value=\"customer\"><img src=\"images/user.png\"></button></li>");
                            } else if (user.getRole() == Role.ADMIN)
                            {
                                out.println(" <li><button id=\"home\" name=\"command\" value=\"employee\"><img src=\"images/home.png\"></button></li>");
                                out.println(" <li><button id=\"home\" name=\"command\" value=\"employee\"><img src=\"images/tools.png\"></button></li>");
                                out.println(" <li><button id=\"home\" name=\"command\" value=\"employee\"><img src=\"images/adduser.png\"></button></li>");
                            } else if (user.getRole() == Role.EMPLOYEE)
                            {
                                out.println(" <li><button id=\"home\" name=\"command\" value=\"employee\"><img src=\"images/home.png\"></button></li>");
                                out.println(" <li><button id=\"home\" name=\"command\" value=\"employee\"><img src=\"images/tools.png\"></button></li>");
                            }
                        } else
                        {
                            out.println("<li><button id=\"log\" name=\"command\" value=\"logout\">Log ind</button></li>");
                        }

                    %>
                </ul>
            </center>
        </form>
        <br><br><br><br><br>