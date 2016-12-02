<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<h1>Registry process</h1>

<form:form modelAttribute="user" method="POST" enctype="utf8">
    <br>
    <tr><td>
        <label>Login:</label>
    </td>
        <td><form:input path="login" value="" /></td>
        <form:errors path="login" element="div" />
    </tr>
    <tr><td>
        <label>Name:</label>
    </td>
        <td><form:input path="name" value="" /></td>
        <form:errors path="name" element="div"/>
    </tr>
    <tr><td>
        <label>City:</label>
    </td>
        <td><form:input path="city" value="" /></td>
        <form:errors path="city" element="div"/>
    </tr>
    <tr><td>
        <label>Surname: </label>
    </td>
        <td><form:input path="surname" value="" /></td>
        <form:errors path="surname" element="div" />
    </tr>
    <tr><td>
        <label>Password:</label>
    </td>
        <td>
            <form:input path="password" value="" type="password" /></td>
        <form:errors path="password" element="div" />
    </tr>
    <tr><td>
        <label>Confirm password:</label>
    </td>
        <td><form:input path="matchingPassword" value="" type="password" /></td>
        <form:errors element="div" />
    </tr>
    <button type="submit">Sumbit</button>
</form:form>
<br>
</body>
</html>
