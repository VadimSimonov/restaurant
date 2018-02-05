<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Restoarant</title>
</head>

<body>

<section>
    <form method="post" action="users">
        <spring:message code="app.login"/>: <select name="userId">
        <option value="100000" selected>User</option>
        <option value="100001">Admin</option>
    </select>
        <button type="submit"><spring:message code="common.select"/></button>
    </form>
    <ul>
        <li><a href="users"><spring:message code="user.title"/></a></li>
        <li><a href="admin"><spring:message code="meal.title"/></a></li>
    </ul>
</section>

</body>
</html>
