<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page session="false"%>
<html>
<head>
    <title>User page</title>
    <link rel='stylesheet'
          href='${pageContext.request.contextPath}/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css'>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-sm-6">
            <form:form modelAttribute="user" method="POST" enctype="multipart/form-data" class="form-vertical">
                <div class="form-group">
                    <label>Login:</label>
                    <td><form:input path="login" class="form-control" value="" disabled="true"/></td>
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

                <form:hidden path="password" />
                <form:hidden path="photoUrl" />

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
                    <form:label path="photo">Photo:</form:label>

                    <td><label class="btn btn-default btn-file">
                        Browse <form:input path="photo" style="display: none;"
                                                type="file" class="form-control"
                                                onchange="$('#upload-file-info').html($(this).val());"/>
                        <span class='label label-info' id="upload-file-info">${user.photoUrl}</span>
                    </label></td>
                    <c:if test="${not empty photoErrors}">
                        <div class="alert alert-danger">
                            <strong>Error!</strong> ${photoErrors}
                        </div>
                    </c:if>
                </div>

                <div class="form-group">
                    <form:label path="roles">Roles:</form:label>
                    <td>
                        <form:select path="roles" multiple="true" class="form-control">
                            <form:options items="${roles}" itemValue="name" itemLabel="name" />
                        </form:select>
                    </td>
                </div>

                <div class="form-group">
                    <label>Is enabled:</label>
                    <form:checkbox path="enabled" />
                </div>

                <div class="form-group">
                    <div class="col-sm-12">
                        <button type="submit" class="btn btn-primary" name="edit_btn">Save</button>
                        <button type="submit" class="btn btn-primary" name="delete_btn">Delete</button>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/webjars/jquery/3.1.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
</body>
</html>
