<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <%=session.getId()%>
        <%=request.getParameter("command")%>
        
        <%=session.getAttribute("auth")%>
        <form action="MainServlet" method="post">
            <input type="hidden" name="command" value="login">
            <p>Login</p><input name="login" type="text"/>
            <p>Password</p><input name="password" type="password"/>
            <br/>
            <input name="submit" value="ok" type="submit"/>
        </form>
        <form action="MainServlet" method="post">
            <input type="submit" name="command" value="register" />
        </form>
    </body>
</html>
