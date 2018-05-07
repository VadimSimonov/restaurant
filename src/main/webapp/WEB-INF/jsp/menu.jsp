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
        <p><a class="btn btn-primary" onclick="addMenu()">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            <spring:message code="common.add"/>
        </a></p>

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

            <input type="hidden" id="user_id" name="user_id" value="100040">
            <!--
            <c:forEach items="${menu}" var="menu">
                <jsp:useBean id="menu" scope="page" type="restaurant.model.Menu"/>

                    <c:forEach items="${menu.restaurants}" var="restaurants">
                        <tr>
                            <td><c:out value="${menu.date}"/></td>
                            <td><c:out value="${restaurants.name}"/></td>
                            <td><a onclick="listMeals(${restaurants.id})"><span class="glyphicon glyphicon-list" aria-hidden="true"></span></a></td>

                            <c:set var="sumRating" value="${0}" />
                            <c:forEach items="${restaurants.vote}" var="vote">
                                <c:set var="sumRating" value="${sumRating + vote.vote}" />
                            </c:forEach>

                            <td><c:out value="${sumRating}"/></td>
                            <td><a onclick="voitePlus(${restaurants.id},1)"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></a></td>
                            <td><a onclick="voiteMinus(${restaurants.id},-1)"><span class="glyphicon glyphicon-minus" aria-hidden="true"></span></a></td>
                        </tr>
                    </c:forEach>

            </c:forEach>
            -->
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
                        <h3><spring:message code="meals.list"/></h3>
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