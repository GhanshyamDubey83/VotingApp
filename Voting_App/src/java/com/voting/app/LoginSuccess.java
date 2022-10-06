package com.voting.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginSuccess extends HttpServlet {

    Connection con;
    PreparedStatement pst;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {

            String userName = request.getParameter("email");
            String password = request.getParameter("pass");

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/voting_app", "root", "Root");
            Statement smt = con.createStatement();
            ResultSet rs = smt.executeQuery("SELECT * FROM R5USERS where usr_email='" + userName + "' and USR_PASSWORD='"+ password +"'" );

            if (!rs.next()) {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login.jsp");
                    out = response.getWriter();
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");

                    out.println("<style>");

                    out.println("h1 {");
                    out.println("color:blue;");

                    out.println("position:relative; left:455px;top:200px;");
                    out.println("}");

                    out.println("</style>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Either user name or password is wrong.</h1>");
                    out.println("</body>");
                    out.println("</html>");
                    rd.include(request, response);

                    pst.close();
                    con.close();

            } else {

                String userName1 = rs.getString("USR_EMAIL");
                String password1 = rs.getString("USR_PASSWORD");
                String Roles = rs.getString("USR_ROLES");

                if (userName.equals(userName1) && password.equals(password1)) {
                    if (!Roles.equals("AD")) {
                        HttpSession session = request.getSession();
                        session.setAttribute("userName1", userName1);
                        //setting session to expiry in 30 mins
                        session.setMaxInactiveInterval(30 * 60);
                        Cookie userName12 = new Cookie("userName1", userName1);
                        userName12.setMaxAge(30 * 60);
                        response.addCookie(userName12);
                        pst = con.prepareStatement("insert into R5SESSION (SES_UNAME,SES_SIGNIN) values(?,CURDATE())");
                        pst.setString(1, userName1);
                        response.sendRedirect("Voting.jsp");
                        pst.close();
                        con.close();
                    } else {
                        HttpSession session = request.getSession();
                        session.setAttribute("userName1", userName1);
                        //setting session to expiry in 30 mins
                        session.setMaxInactiveInterval(30 * 60);
                        Cookie userName12 = new Cookie("userName1", userName1);
                        userName12.setMaxAge(30 * 60);
                        response.addCookie(userName12);
                        pst = con.prepareStatement("insert into R5SESSION (SES_UNAME,SES_SIGNIN) values(?,CURDATE())");
                        pst.setString(1, userName1);
                        response.sendRedirect("AdminManagement.jsp");
                        pst.close();
                        con.close();
                    }
                }
            }

        } catch (ClassNotFoundException | SQLException e) {
            out.println(e);
        }
    }
}
