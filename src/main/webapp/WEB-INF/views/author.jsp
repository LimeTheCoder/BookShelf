<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Author</title>
</head>
<body>
<c:choose>
    <c:when test="${empty author}">
        No such author here
    </c:when>
    <c:otherwise>
        <div>
            <div><c:out value="${author.name}" /></div>
            <div>
                <span><c:out value="${author.surname}" /></span>
            </div>
        </div>
    </c:otherwise>
</c:choose>

</body>
</html>