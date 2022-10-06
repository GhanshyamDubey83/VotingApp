

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Admin Console</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <style>
            body {
                margin: 0;
                box-sizing: border-box;
            }

            .container {
                line-height: 150%;
            }

            .header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                padding: 15px;
                background-color: #e9e9e9;
            }

            .header h1 {
                color: #222222;
                font-size: 30px;
                font-family: 'Pacifico', cursive;
            }

            .header .social a {
                padding: 0 5px;
                color: #222222;
            }

            .left {
                float: left;
                width: 180px;
                margin: 0;
                padding: 1em;
            }

            .content {
                margin-left: 190px;
                border-left: 1px solid #d4d4d4;
                padding: 1em;
                overflow: hidden;
                height:500px;
            }

            ul {
                list-style-type: none;
                margin: 0;
                padding: 0;
                font-family: sans-serif;
            }

            li a {
                display: block;
                color: #000;
                padding: 8px 16px;
                text-decoration: none;
            }

            li a.active {
                background-color: #84e4e2;
                color: white;
            }

            li a:hover:not(.active) {
                background-color: #29292a;
                color: white;
            }





            .footer {
                padding: 55px 20px;
                background-color: #2e3550;
                color: white;
                text-align: center;

            }
            input[type=submit],[type=reset] {
                background-color: #04AA6D;
                color: white;
                padding: 12px 20px;
                border-radius: 4px;
                cursor: pointer;
            }

            input[type=submit]:hover {
                background-color: #45a049;
            }

            #table02 {
                font-family: arial, sans-serif;
                border-collapse: collapse;
                width: 100%;
            }

            .td2, .th2 {
                border: 1px solid #dddddd;
                text-align: left;
                padding: 8px;
            }

            .tr2:nth-child(even) {
                background-color: #dddddd;
            }
        </style>
    </head>

    <body>
        <div class="container">
            <header class="header">
                <h1>Admin Console</h1>

                <%
                    String UserName = null;
                    String SeesionId = null;
                    Cookie[] cookies = request.getCookies();
                    if (cookies != null) {
                        for (Cookie cookie : cookies) {
                            if (cookie.getName().equals("userName1")) {
                                UserName = cookie.getValue();
                            }
                        }
                    }
                    Connection con;
                    PreparedStatement pst;
                %>
                <table style="float:right;">
                    <tr>
                    <div class="social">
                        <a href="#"><i class="fab fa-facebook"></i></a>
                        <a href="#"><i class="fab fa-instagram"></i></a>
                        <a href="#"><i class="fab fa-twitter"></i></a>
                    </div>

                    </tr>
                    <tr>
                        <td><h3>Hi <%=UserName%></h3></td>
                    <form method="post" action="./Logout">
                        <td><input type="submit"  value="Sign Out"></td>
                    </form>
                    </tr>

                </table>
            </header>
            <aside class="left">
                <img src="Image/Admin.png" width="160px" />
                <ul>
                    <li><a class="active" href="AdminManagement.jsp">Home</a></li>
                    <li><a href="VoteCount.jsp">Vote Count</a></li>
                    <li><a href="UserManagement.jsp">Manage Users</a></li>
                    <li><a href="#about">About</a></li>
                </ul>
                <br><br>

            </aside>
            <main class="content">
                <h3>Welcome to the User Management Page</h3>

                <table id="table02">
                    <tr class="tr2">
                        <th class="th2">User ID</th>
                        <th  class="th2">User Name</th>
                        <th  class="th2">Email</th>
                        <th  class="th2">Password.</th>
                        <th  class="th2">Phone No.</th>
                        <th  class="th2">Roles.</th>
                        <th  class="th2">Action.</th>
                    </tr>
                    <%
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/voting_app", "root", "Root");
                        Statement smt = con.createStatement();
                        ResultSet rs = smt.executeQuery("select * from r5users;");
                        while (rs.next()) {
                    %>
                    <tr class="tr2">
                        <td class="td2"><%=rs.getString("USR_ID")%></td>
                        <td class="td2"><%=rs.getString("USR_NAME")%></td>
                        <td class="td2"><%=rs.getString("USR_EMAIL")%></td>
                        <td class="td2"><%=rs.getString("USR_PASSWORD")%></td>
                        <td class="td2"><%=rs.getString("USR_PHONE")%></td>
                        <td class="td2"><%=rs.getString("USR_ROLES")%></td>
                        <td class="td2"><input type="submit" value="Edit"/> 
                        <input type="submit" value="Delete"/></td>                   
                    
                    </tr>
                    <%}%>
                </table>

            </main>
            <footer class="footer">&copy; Copyright Mr. Ghanshyam Dubey</footer>
        </div>
    </body>

</html>
