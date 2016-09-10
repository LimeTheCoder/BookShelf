<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>List of all authors</title>
</head>
<body>
<c:choose>
    <c:when test="${empty authorList}">
        No authors
    </c:when>
    <c:otherwise>
        <h1>Authors</h1>
        <ul class="spittleList">
            <c:forEach items="${authorList}" var="author" >
                <li id="author_<c:out value="author.id"/>">
                    <div><c:out value="${author.name}" /></div>
                    <div><c:out value="${author.surname}" /></div>
                </li>
            </c:forEach>
        </ul>
    </c:otherwise>
</c:choose>

</body>
</html>