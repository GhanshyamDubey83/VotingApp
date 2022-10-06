package com.voting.app;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout extends HttpServlet {

    Connection con;
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/voting_app","root","Root");
            Statement stmt = con.createStatement();
            HttpSession session = request.getSession(false);
            Date createTime = new Date(session.getCreationTime());
            System.out.println("User=" + session.getAttribute("userName1"));
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("JSESSIONID")) {
                        System.out.println("JSESSIONID=" + cookie.getValue());
                        break;
                    }
                }
            }
            if (session != null) {
                
                String sql = "UPDATE R5SESSION "
                        + "SET SES_SIGNOUT = CURDATE() WHERE SES_SIGNOUT is null and SES_UNAME= " + "'"+session.getAttribute("userName1")+"'";
                session.invalidate();
                int i = stmt.executeUpdate(sql);
                stmt.close();
            }
            response.sendRedirect("Login.jsp");

        } catch (ClassNotFoundException | SQLException e) {
            out.println(e);
        }
    }

}
