<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Book</title>
</head>
<body>
<c:choose>
    <c:when test="${empty book}">
        No such book here
    </c:when>
    <c:otherwise>
        <div>
            <div><c:out value="${book.title}" /></div>
            <div><c:out value="${book.description}" /></div>
            <div><c:out value="${book.publisher.name}" /></div>
        </div>
    </c:otherwise>
</c:choose>

</body>
</html>