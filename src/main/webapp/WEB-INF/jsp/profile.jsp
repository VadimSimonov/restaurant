<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="restaurant" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<jsp:include page="fragments/i18n.jsp"/>
<body>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/userProfile.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <h2>${userTo.name} <spring:message code="${register ? 'app.register' : 'app.profile'}"/></h2>
        <form:form modelAttribute="userTo" id="EditFormUser"
                   class="form-horizontal" method="post"
                   action="${register ? 'register' : 'profile'}"
                   charset="utf-8" accept-charset="UTF-8">

            <input type="hidden" id="id" value="${id}">

            <div class="form-group">
                <label for="name" class="control-label col-xs-3"><spring:message code="user.name"/></label>

                <div class="col-sm-3">
                    <input type="text" class="form-control" id="name" name="name" placeholder="<spring:message code="user.name"/>">
                </div>
            </div>

            <div class="form-group">
                <label for="email" class="control-label col-xs-3"><spring:message code="user.email"/></label>

                <div class="col-sm-3">
                    <input type="email" class="form-control" id="email"  placeholder="<spring:message code="user.email"/>">
                </div>
            </div>
            <sec:authorize access="isAnonymous()">
                <div class="form-group">
                    <label for="role" class="control-label col-xs-3"><spring:message code="user.roles"/></label>
                    <div class="col-sm-3">
                        <select id="role">
                            <option value="None">-- Select --</option>
                            <option value="ROLE_ADMIN">ROLE_ADMIN</option>
                            <option value="ROLE_USER">ROLE_USER</option>
                        </select>
                    </div>
                </div>
            </sec:authorize>

            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <div class="form-group">
                    <label for="role" class="control-label col-xs-3"><spring:message code="user.roles"/></label>
                    <div class="col-sm-3">
                        <select id="role">
                            <option value="None">-- Select --</option>
                            <option value="ROLE_ADMIN">ROLE_ADMIN</option>
                            <option value="ROLE_USER">ROLE_USER</option>
                        </select>
                    </div>
                </div>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_USER')">
                <input type="hidden" id="role" value="ROLE_USER">
            </sec:authorize>

            <div class="form-group">
                <label for="password" class="control-label col-xs-3"><spring:message code="user.password"/></label>

                <div class="col-sm-3">
                    <input type="password" class="form-control" id="password" name="password" placeholder="<spring:message code="user.password"/>">
                </div>
            </div>

            <div class="form-group">
                <div class="col-xs-offset-3 col-sm-3">
                    <button type="submit" class="btn btn-primary">
                        <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                    </button>
                </div>
            </div>
        </form:form>

    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>