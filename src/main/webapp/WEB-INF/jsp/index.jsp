<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <p/>

        <form method="post" action="users">
            <spring:message code="app.login"/>: <select name="userId">
            <option value="100000" selected>User</option>
            <option value="100001">Admin</option>
        </select>
            <button type="submit"><spring:message code="common.select"/></button>
        </form>
        <ul>
            <li><a href="users"><spring:message code="user.title"/></a></li>
            <li><a href="meals"><spring:message code="meal.title"/></a></li>
        </ul>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>

<!--
<div class="container">
    <h1>POST-GET AJAX</h1>
    <div id="postdiv">
        <form id="customerForm">
            <label>FirstName</label>
            <input type="text" id="firstname" class="form-control"/>
            <label>LastName</label>
            <input type="text" id="lastname" class="form-control"/>
            <button type="submit" id="postBtn" class="btn btn-primary">Submit</button>
        </form>
    </div>
    <div id="postResultDiv">
    </div>
    <br/>
    <div id="getdiv">
        <button id="getBtn" >Get All Customers</button>
    </div>
    <div id="getResultDiv">
        <ul class="list-group">
        </ul>
    </div>
    -->
