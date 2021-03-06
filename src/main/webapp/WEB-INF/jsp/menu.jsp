<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<jsp:include page="fragments/i18n.jsp"/>
<body>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/menuDatatables.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <h3><spring:message code="menu"/></h3>
        <br/>
        <sec:authorize access="isAuthenticated()">
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <p><a class="btn btn-primary" onclick="addMenu()">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                    <spring:message code="common.add"/></a></p>
            </sec:authorize>
        </sec:authorize>

        <table class="table table-striped display" id="datatableMenu">
            <thead>
            <tr>
                <th><spring:message code="menu.date"/></th>
                <th><spring:message code="restaurants.name"/></th>
                <th><spring:message code="meals.list"/></th>
                <th><spring:message code="vote.rating"/></th>
                <th><spring:message code="rating.plus"/></th>
                <th><spring:message code="rating.minus"/></th>
            </tr>
            </thead>

            <input type="hidden" id="user_id" name="user_id" value="${user_id}">

        </table>
    </div>
</div>

<div class="modal fade" id="listMeals">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title"><spring:message code="meals.list"/></h2>
            </div>
            <div class="modal-body">
                <div class="jumbotron">
                    <div class="container">
                        <br/>
                        <table class="table table-striped display" id="listFormMeals">
                            <thead>
                            <tr>
                                <th><spring:message code="meal.name"/></th>
                                <th><spring:message code="price"/></th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="listRestaurants">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title"><spring:message code="restaurants.add.menu"/></h2>
            </div>
            <div class="modal-body">
                <div class="jumbotron">
                    <div class="container">
                        <br/>
                        <table class="table table-striped display" id="listFormRestaurants">
                            <thead>
                            <tr>
                                <th><spring:message code="restaurants.name"/></th>
                            </tr>
                            </thead>
                        </table>
                        <a class="btn btn-primary" onclick="addRestaurant()">
                            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                            <spring:message code="common.add"/>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>