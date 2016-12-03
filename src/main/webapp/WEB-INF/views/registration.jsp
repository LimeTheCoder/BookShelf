<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<html>
<head>
    <title>Welcome</title>
    <link rel='stylesheet' href='webjars/bootstrap/3.3.7-1/css/bootstrap.min.css'>
</head>

<body>
<div class="page-header">
    <h1>Create new user account</h1>
</div>

<div class="container">
    <div class="row">
        <div class="col-sm-6">
            <form:form modelAttribute="user" method="POST" enctype="multipart/form-data" class="form-vertical">
                <c:set var="loginErrors"><form:errors path="login"/></c:set>
                <div class="form-group">
                    <label>Login:</label>
                    <td><form:input path="login" class="form-control" value="" /></td>

                    <c:if test="${not empty loginErrors}">
                        <div class="alert alert-danger">
                            <strong>Error!</strong> ${loginErrors}
                        </div>
                    </c:if>
                </div>
                <div class="form-group">
                    <c:set var="nameErrors"><form:errors path="name"/></c:set>
                    <label>Name:</label>

                    <td><form:input path="name" value="" class="form-control" /></td>
                    <c:if test="${not empty nameErrors}">
                        <div class="alert alert-danger">
                            <strong>Error!</strong> ${nameErrors}
                        </div>
                    </c:if>
                </div>
                <div class="form-group">
                    <c:set var="surnameErrors"><form:errors path="surname"/></c:set>
                    <label>Surname: </label>

                    <td><form:input path="surname" value="" class="form-control" /></td>
                    <c:if test="${not empty surnameErrors}">
                        <div class="alert alert-danger">
                            <strong>Error!</strong> ${surnameErrors}
                        </div>
                    </c:if>
                </div>
                <div class="form-group">
                    <c:set var="cityErrors"><form:errors path="city"/></c:set>
                    <label>City:</label>

                    <td><form:input path="city" value="" class="form-control" /></td>
                    <c:if test="${not empty cityErrors}">
                        <div class="alert alert-danger">
                            <strong>Error!</strong> ${cityErrors}
                        </div>
                    </c:if>
                </div>
                <div class="form-group">
                    <c:set var="photoErrors"><form:errors path="photo"/></c:set>
                    <label>Photo: </label>

                    <td><form:input path="photo" value="" type="file" class="form-control" /></td>
                    <c:if test="${not empty photoErrors}">
                        <div class="alert alert-danger">
                            <strong>Error!</strong> ${photoErrors}
                        </div>
                    </c:if>
                </div>
                <div class="form-group">
                    <c:set var="passwordErrors"><form:errors path="password"/></c:set>
                    <label>Password:</label>

                    <td>
                        <form:input path="password" value="" type="password" class="form-control" /></td>
                    <c:if test="${not empty passwordErrors}">
                        <div class="alert alert-danger">
                            <strong>Error!</strong> ${passwordErrors}
                        </div>
                    </c:if>
                </div>
                <div class="form-group">
                    <c:set var="Errors"><form:errors/></c:set>
                    <label>Confirm password:</label>

                    <td><form:input path="matchingPassword" value="" type="password" class="form-control" /></td>
                    <c:if test="${not empty Errors}">
                        <div class="alert alert-danger">
                            <strong>Error!</strong> ${Errors}
                        </div>
                    </c:if>
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </form:form>
        </div>
    </div>
</div>
<script src="webjars/jquery/3.1.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
</body>
</html>