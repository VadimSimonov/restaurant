<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<body>
<section>
    <jsp:useBean id="user" type="restaurant.model.User" scope="request"/>
    <h3><spring:message code="${user.isNew() ? 'user.add' : 'user.edit'}"/></h3>
    <hr>
    <form method="post" action="users">
        <input type="hidden" name="id" value="${user.id}">
        <dl>
            <dt><spring:message code="user.name"/>:</dt>
            <dd><input type="datetime-local" value="${user.name}" name="name" required></dd>
        </dl>
        <dl>
            <dt><spring:message code="user.password"/>:</dt>
            <dd><input type="password" value="${user.password}" size=40 name="password" required></dd>
        </dl>
        <dl>
            <dt><spring:message code="user.email"/>:</dt>
            <dd><input type="email" value="${user.email}" name="email" required></dd>
        </dl>
        <dl>
            <dt><spring:message code="user.roles"/>:</dt>
            <dd><input type="text" value="${user.roles}" name="role" required></dd>
        </dl>
        <button type="submit"><spring:message code="common.save"/></button>
        <button onclick="window.history.back()" type="button"><spring:message code="common.cancel"/></button>
    </form>
</section>
</body>
</html>
