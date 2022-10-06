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

public class VoteGiven extends HttpServlet {

    Connection con;
    PreparedStatement pst;
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String voteGiven = request.getParameter("Candidate");

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/voting_app", "root", "Root");
            Statement smt = con.createStatement();

            String GetCandiVote = request.getParameter("Candidate");
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
            ResultSet rs = smt.executeQuery("SELECT * FROM R5VOTEDCANDIDATE WHERE VC_UNAME= '" + UserName + "'");
            if (!rs.next()) {
                pst = con.prepareStatement("insert into R5VOTEDCANDIDATE (VC_UNAME,VC_CANDIDATE) values(?,?)");

                pst.setString(1, UserName);
                pst.setString(2, GetCandiVote);
                int i = pst.executeUpdate();
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Voting.jsp");
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
                out.println("<h1>Great work! You just voted your favorite candidate.</h1>");
                out.println("</body>");
                out.println("</html>");
                rd.include(request, response);
                pst.close();
                con.close();
            } else {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Voting.jsp");
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
                out.println("<h1>Sorry! You has already voted.</h1>");
                out.println("</body>");
                out.println("</html>");

                rd.include(request, response);

            }

        } catch (ClassNotFoundException | SQLException e) {
            out.println(e);
        }
    }
}
