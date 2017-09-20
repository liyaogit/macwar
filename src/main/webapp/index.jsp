<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<body>
<a href="/hello/hello">hello world</a>
<a href="/user">User_Model</a>
<a href="/user1">User_ModelAndView</a>

    <br>
        <form action="/login" method="get">
            <tr>登录名: <input type="text" id="name" name="name"></tr>
           <%-- <tr>年龄: <input type="text" id="age" name="age"></tr>--%>
            <tr><input type="submit" id="submit" value="登录"></tr>
            </form>>
    </br>
</body>
</html>
