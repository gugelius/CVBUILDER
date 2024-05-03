<%--<!DOCTYPE html>--%>
<%--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Login</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<h1>Login Page</h1>--%>
<%--<br/>--%>
<%--<form action="controller" method="post">--%>
<%--    <input type="hidden" name="command" value="login"/>--%>
<%--    Login:<input  type="text" required="" name="login" value=""/>--%>
<%--    <br/>--%>
<%--    Password:<input type="password" required="" name="pass" value=""/>--%>
<%--    <br/>--%>
<%--    <input type="submit" name="sub" value="let's go"/>--%>
<%--    <br/>--%>
<%--    ${login_msg}--%>
<%--</form>--%>

<%--<!-- Добавленная кнопка перехода на страницу регистрации -->--%>
<%--<form action="registration.jsp">--%>
<%--    <input type="submit" value="Перейти на страницу регистрации">--%>
<%--</form>--%>
<%--</body>--%>
<%--</html>--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<fmt:setLocale value="${jlocale}" scope="session"/>
<fmt:setBundle basename="resource.messages" var="rb" />
<!DOCTYPE html>
    <html>
    <head>
        <title><fmt:message key="label.title" bundle="${ rb }"/></title>
    </head>
    <body>
        <h1><fmt:message key="label.loginPage" bundle="${ rb }"/></h1>
        <br/>
        <form action="controller" method="post">
            <input type="hidden" name="command" value="login"/>
            <fmt:message key="label.login" bundle="${ rb }"/>:<input  type="text" required="" name="login" value=""/>
            <br/>
            <fmt:message key="label.password" bundle="${ rb }"/>:<input type="password" required="" name="pass" value=""/>
            <br/>
            <input type="submit" name="sub" value="<fmt:message key="label.submit" bundle="${ rb }"/>"/>
            <br/>
            ${login_msg}
        </form>
        <form action="registration.jsp">
            <input type="submit" value="<fmt:message key="label.registrationPage" bundle="${ rb }"/>">
        </form>
        <form action="controller" method="post">
            <input type="hidden" name="command" value="setLocale"/>
            <input type="hidden" name="locale" value="en_US"/>
            <input type="submit" value="English"/>
        </form>
    </body>
    </html>