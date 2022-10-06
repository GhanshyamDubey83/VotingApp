
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Register</title>
        <style>
            .input{
                width: 100%;
                padding: 12px;
                border: 1px solid #ccc;
                border-radius: 4px;
                resize: vertical;
            }
            input[type=submit],[type=button],[type=reset] {
                background-color: #04AA6D;
                color: white;
                padding: 12px 20px;
                border-radius: 4px;
                cursor: pointer;
            }

            input[type=submit]:hover {
                background-color: #45a049;
            }

            input[type=button]:hover {
                background-color: #45a049;
            }
            input[type=reset]:hover {
                background-color: #45a049;
            }       
        </style>

        
    </head>

    <body>
        <fieldset style="position: relative; left:450px;top:200px; width:400px; ">
            <legend>Register</legend>
            <form method="post" action="./RegisterServlet">
                <table>
                    <tr>
                        <td>User Name: </td>
                        <td><input type="text" class="input" name="Uname" placeholder="User Name" required></td>
                    </tr>
                    <tr>
                    <tr>
                        <td>Password: </td>
                        <td><input type="password" class="input" name="pass" placeholder="Password" required></td>
                    </tr>
                    <tr>
                        <td>Email: </td>
                        <td><input type="email" class="input" name="email" placeholder="Email" required></td>
                    </tr>
                    <tr>
                        <td>Phone </td>
                        <td><input type="text" class="input" name="Phone" placeholder="Phone" required></td>
                    </tr>
                    <tr>
                        <td><input type="submit"  value="Register"></td>
                        <td><input type="button"  value="Login" onclick="location.href = '\Login.jsp';"></td>
                       <td><input type="reset"  value="Clear Fields"></td>
                    </tr>
                </table>
            </form>
        </fieldset>
    </body>
</html>
