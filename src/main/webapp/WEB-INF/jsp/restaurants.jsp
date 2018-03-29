<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/restaurantsDatatables.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <h3><spring:message code="restaurants.title"/></h3>
        <br/>
        <a class="btn btn-primary" onclick="add()">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            <spring:message code="common.add"/>
        </a>
        <table class="table table-striped display" id="datatableRestaurants">
            <thead>
            <tr>
                <th><spring:message code="restaurants.name"/></th>
                <th><spring:message code="restaurants.meals.size"/></th>
                <th><spring:message code="restaurants.meals.add"/></th>
                <th><spring:message code="edit"/></th>
                <th><spring:message code="delete"/></th>
            </tr>
            </thead>
            <c:forEach items="${restaurants}" var="restaurants">
                <jsp:useBean id="restaurants" scope="page" type="restaurant.model.Restaurants"/>
                <tr>
                    <td><c:out value="${restaurants.name}"/></td>
                    <td><c:out value="${restaurants.meals.size()}"/></td>
                    <td><a onclick="addMeals(${restaurants.id})"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></a></td>
                    <td><a onclick="restaurantsEditRow(${restaurants.id})"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a></td>
                    <td><a class="delete" id="${restaurants.id}"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title"><spring:message code="restaurants.add"/></h2>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="detailsFormRestaurant">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="name" class="control-label col-xs-3"><spring:message code="restaurants.name"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="name" name="name" placeholder="<spring:message code="restaurants.name"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button type="submit" class="btn btn-primary">
                                <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="editMeals">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title"><spring:message code="restaurants.meals.add"/></h2>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="detailsFormMeals">
                    <input type="hidden" id="restaurant_id" name="restaurant_id">
                    <input type="hidden" id="meal_id" name="id">

                    <div class="form-group">
                        <label for="name" class="control-label col-xs-3"><spring:message code="meal.name"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="meal_name" name="name" placeholder="<spring:message code="meal.name"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="name" class="control-label col-xs-3"><spring:message code="price"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="meal_price" name="price" placeholder="<spring:message code="price"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button type="submit" class="btn btn-primary">
                                <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>



<jsp:include page="fragments/footer.jsp"/>
</body>
</html>