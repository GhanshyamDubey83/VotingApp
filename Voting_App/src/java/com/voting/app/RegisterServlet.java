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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {

    Connection con;
    PreparedStatement pst;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        // String connectionURL = "jdbc:mysql://localhost:3306/;"
        //      + "databaseName=voting_app;"
        //   + "user=root;" + "password=Root;";

        try {

            String userName = request.getParameter("Uname");
            String password = request.getParameter("pass");
            String email = request.getParameter("email");
            String phone = request.getParameter("Phone");

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/voting_app", "root", "Root");

            Statement smt = con.createStatement();
            ResultSet rs = smt.executeQuery("SELECT * FROM R5USERS where usr_email='" + email + "'");
            if (!rs.next()) {
                pst = (PreparedStatement) con.prepareStatement("insert into R5USERS (USR_NAME,USR_EMAIL,USR_PASSWORD,USR_PHONE,usr_roles) values(?,?,?,?,'US')");
                pst.setString(1, userName);
                pst.setString(2, email);
                pst.setString(3, password);
                pst.setString(4, phone);

                int i = pst.executeUpdate();

                if (i != 0) {
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/Register.jsp");
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
                    out.println("<h1>Great! You have registered successfully.</h1>");
                    out.println("</body>");
                    out.println("</html>");
                    rd.include(request, response);

                    pst.close();
                    con.close();

                } else {
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/Register.jsp");
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
                    out.println("<h1>Opps! Something went wrong.</h1>");
                    out.println("</body>");
                    out.println("</html>");
                    rd.include(request, response);

                    pst.close();
                    con.close();

                }

            } else {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Register.jsp");
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
                out.println("<h1>Ohh! Seems like you have chosen matching user id or email.</h1>");
                out.println("</body>");
                out.println("</html>");
                rd.include(request, response);

            }
            pst.close();
            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            out.println(e);
        }
    }

}
