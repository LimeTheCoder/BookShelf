<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
</head>
<body>
<form:form method="POST" modelAttribute="book" enctype="multipart/form-data">
    <table>
        <tr>
            <td><form:label path="title">Title</form:label></td>
            <td><form:input path="title"/></td>
        </tr>
        <tr>
            <td><form:label path="authors">Authors</form:label></td>
            <td>
                <form:select path="authors">
                    <form:options items="${authors}" itemValue="id" itemLabel="displayName"/>
                </form:select>
            </td>
        </tr>
        <tr>
            <td><form:label path="description">Description</form:label></td>
            <td><form:input path="description"/></td>
        </tr>
        <tr>
            <td><form:label path="pagesCnt">Page count</form:label></td>
            <td><form:input path="pagesCnt"/></td>
        </tr>
        <tr>
            <td><form:label path="publishYear">Year of publish</form:label></td>
            <td><form:input path="publishYear"/></td>
        </tr>
        <tr>
            <td><form:label path="genres">Genres</form:label></td>
            <td>
                <form:select path="genres" multiple="true">
                    <form:options items="${genres}" itemValue="id" itemLabel="name"/>
                </form:select>
            </td>
        </tr>
        <tr>
            <td><form:label path="publisher">Publishers</form:label></td>
            <td>
                <form:select path="publisher">
                    <form:options items="${publishers}" itemValue="id" itemLabel="name"/>
                </form:select>
            </td>
        </tr>
        <tr>
            <td><label for="image">Image</label></td>
            <td><input type="file" name="image" id="image" accept="image/jpeg, image/png"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>
