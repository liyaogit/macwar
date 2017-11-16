<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
    <title>注册成功</title>
</head>
<body>
<H2>success</H2>
登录名:${requestScope.user.username}<br>
密码:${requestScope.user.password}<br>
年龄:${requestScope.user.age}<br>
邮箱:${requestScope.user.email}<br>
生日:${requestScope.user.birthday}<br>
电话:${requestScope.user.phoneNo}<br>
</body>
</html>
