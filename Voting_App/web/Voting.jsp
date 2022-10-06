
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Voting</title>
        <style>

            .input{
                width: 100%;
                padding: 12px;
                border: 1px solid #ccc;
                border-radius: 4px;
                resize: vertical;
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
            input[type=reset]:hover {
                background-color: #45a049;
            }
            label{
                color: teal;
                font-weight: 500;
            }

        </style>


    </head>

    <body>
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
        %>
        <fieldset style="position: relative; left:450px;top:200px; width:400px; ">
            <legend>Voting</legend>
            <table style="position: relative; left:10px;">
                <tr>
                    <td><h3>Hi <%=UserName%>, Please vote for any of the following Candidates.</h3></td>
                </tr>
            </table>
            <form  method="post" action="./VoteGiven">
                <table>
                    <tr>
                        <td><label for="Condi1">Candidate 1:</label></td>
                        <td><input type="radio" class="input" value="Candidate1" name="Candidate"></td>
                    </tr>
                    <tr>
                    <tr>
                        <td><label for="Condi2">Candidate 2:</label></td>
                        <td><input type="radio" class="input" value="Candidate2" name="Candidate"></td>
                    </tr>
                    <tr>
                        <td><label for="Condi3">Candidate 3:</label></td>
                        <td><input type="radio" class="input" value="Candidate3" name="Candidate"></td>
                    </tr>
                    <tr>
                        <td><label for="Condi4">Candidate 4:</label></td>
                        <td><input type="radio" class="input" value="
                                   Candidate4" name="Candidate"></td>
                    </tr>
                    <tr>
                    </tr>
                    <tr>
                    </tr>
                    <tr>
                    </tr>
                    <tr>
                        <td><input type="submit"  value="Vote"></td>
                        <td><input type="reset"  value="Clear Selection"></td>
                    </tr>
                </table>
            </form>
            <form method="post" action="./Logout">
                <tr>
                    <td><input type="submit"  value="Sign Out"></td>
            </form>
        </fieldset>
    </body>
</html>

