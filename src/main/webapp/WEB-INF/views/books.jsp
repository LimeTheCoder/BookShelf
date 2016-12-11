<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <link rel='stylesheet'
          href='${pageContext.request.contextPath}/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css'>
    <link rel='stylesheet'
          href='${pageContext.request.contextPath}/webjars/bootstrap/3.3.7-1/css/bootstrap-theme.min.css'>

    <title>Books list</title>

    <style>
        .inner {
            width: 50%;
            margin: 0 auto;
        }
    </style>
</head>
<body>

<c:url var="firstUrl" value="/admin/books?page=1" />
<c:url var="lastUrl" value="/admin/books?page=${books.totalPages}" />
<c:url var="prevUrl" value="/admin/books?page=${current - 1}" />
<c:url var="nextUrl" value="/admin/books?page=${current + 1}" />

<div class="container">
    <table class="table table-hover table-responsive">
        <thead>
        <tr>
            <th>Title</th>
            <th>Authors</th>
            <th>Pages</th>
            <th>Publisher</th>
            <th>Publish Year</th>
            <th>Reviews</th>
            <th>Genres</th>
            <th>Rating</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${books.content}" var="book" >
            <tr class='clickable-row'
                data-href=<c:url value="/admin/books/${book.id}" />>
                <td><c:out value="${book.title}" /></td>
                <td><c:out value="${book.printAuthors()}" /></td>
                <td><c:out value="${book.pagesCnt}" /></td>
                <td><c:out value="${book.publisher}" /></td>
                <td><c:out value="${book.publishYear}" /></td>
                <td><c:out value="${book.reviews.size()}" /></td>
                <td><c:out value="${book.printGenres()}" /></td>
                <td><c:out value="${book.rateValue / book.rateCnt}" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="container">
        <div class="span12">
            <a type="button" class="btn btn-primary" name="add_button"
               href="${pageContext.request.contextPath}/admin/books/add">Create
            </a>
        </div>
    </div>

    <div class="inner">
        <ul class="pagination">
            <c:if test="${current != 1}">
                <li><a href="${firstUrl}">&lt;&lt;</a></li>
                <li><a href="${prevUrl}">&lt;</a></li>
            </c:if>

            <c:forEach var="i" begin="${begin}" end="${end}">
                <c:url var="pageUrl" value="/admin/books?page=${i}" />
                <c:choose>
                    <c:when test="${i == current}">
                        <li class="active"><a href="${pageUrl}"><c:out value="${i}" /></a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${current != books.totalPages}">
                <li><a href="${nextUrl}">&gt;</a></li>
                <li><a href="${lastUrl}">&gt;&gt;</a></li>
            </c:if>
        </ul>
    </div>
</div>

<script src="${pageContext.request.contextPath}/webjars/jquery/3.1.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
<script>
    jQuery(document).ready(function($) {
        $(".clickable-row").click(function() {
            window.document.location = $(this).data("href");
        });
    });
</script>


</body>
</html>
