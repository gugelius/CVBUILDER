<%--
  Created by IntelliJ IDEA.
  User: Axeliuse
  Date: 19.02.2024
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register now!</title>
</head>
<body>
<h1>Registration page</h1>
<form action="controller" method="post">
    <input type="hidden" name="command" value="ADD_USER"/>
    Login:<input type="text" required="" name="login" value=${login_value}>
    <br/>
    <% String password = request.getAttribute("pass") != null ? (String) request.getAttribute("pass") : ""; %>
    Password:<input type="password" required="" name="pass" value="<%=password%>"/>
<%--на случай, если я захочу убрать функцию сохранения пароля в поле ввода--%>
<%--    Password:<input type="password" name="pass" value=${pass_in}/>--%>
    <br/>
    <input type="submit" name="sub" value="let's go"/>
    <br/>
    ${reg_msg}
</form>
<form action="index.jsp">
    <input type="submit" value="Назад">
</form>
</body>
</html>
