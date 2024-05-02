<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Result page</title>
</head>
<body>
Welcome ${user}
<form action="controller" method="post">
    <input type="hidden" name="command" value="CHANGE_PROFILE"/>
    <br/>
    Current password:<input type="password" name="oldPass" value=""/>
    <br/>
    New login<input type="text" name="newLogin" value=""/>
    <br/>
    new password<input type="password" name="newPass" value=""/>
    <br/>
    <input type="submit" name="sub" value="Confirm changes"/>
    <br/>
    ${chng_msg}
</form>
<form action="controller" method="post">
    <input type="hidden" name="command" value="LOGOUT">
    <input type="submit" value="Back to login page"/>
</form>
<form action="controller" method="post" enctype="multipart/form-data">
    <input type="hidden" name="command" value="FILE_UPLOAD">
    <input type="file" name="file" />
    <input type="submit" value="Upload" />
</form>
<%--<a href="controller?command=FILE_DOWNLOAD_FROM_DB&fileId=123">Download file</a>--%>
</body>
</html>
