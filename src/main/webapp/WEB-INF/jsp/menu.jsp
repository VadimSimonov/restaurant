<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/menuDatatables.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <h3><spring:message code="menu"/></h3>
        <br/>
        <a class="btn btn-primary" onclick="add()">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            <spring:message code="common.add"/>
        </a>
        <table class="table table-striped display" id="datatable">
            <thead>
            <tr>
                <th><spring:message code="menu.date"/></th>
                <th><spring:message code="restaurants.name"/></th>
                <th><spring:message code="vote.rating"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${menu}" var="menu">
                <jsp:useBean id="menu" scope="page" type="restaurant.model.Menu"/>
                <tr>
                    <td><c:out value="${menu.date}"/></td>
                    <c:forEach items="${menu.restaurants}" var="restaurants">
                        <tr><td><c:out value=""/></td>
                            <td><c:out value="${restaurants.name}"/></td>
                            <td><c:out value="${}"/></td>
                        <td><a onclick="voitePlus(${restaurants.id})"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></a></td>
                        <td><a onclick="voiteMinus(${restaurants.id})"><span class="glyphicon glyphicon-minus" aria-hidden="true"></span></a></td>
                        </tr>
                    </c:forEach>
            </tr>
            </c:forEach>
        </table>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>