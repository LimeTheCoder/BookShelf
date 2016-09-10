<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>List of all books</title>
</head>
<body>
<c:choose>
    <c:when test="${empty bookList}">
        No books
    </c:when>
    <c:otherwise>
        <h1>Books</h1>
        <ul>
            <c:forEach items="${bookList}" var="book" >
                <li id="book_<c:out value="book.id"/>">
                    <div><c:out value="${book.title}" /></div>
                    <div><c:out value="${book.description}" /></div>
                    <div><c:out value="${book.publisher.name}" /></div>
                </li>
            </c:forEach>
        </ul>
    </c:otherwise>
</c:choose>

</body>
</html>