<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Book</title>
    <link rel='stylesheet'
          href='${pageContext.request.contextPath}/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css'>
    <style>
        .rating .glyphicon {font-size: 22px; margin-right:5px;}
        .rating-num { margin-top:0px;font-size: 54px; }
        .progress { margin-bottom: 5px;}
        .progress-bar { text-align: left; }
        .rating-desc .col-md-3 {padding-right: 0px;}
        .sr-only { margin-left: 5px;overflow: visible;clip: auto; }
        .stars
        {
            margin: 20px 0;
            font-size: 24px;
            color: #d17581;
        }
        .golden-star
        {
            color: #d17581;
        }
        .review-block{
            background-color:#FAFAFA;
            border:1px solid #EFEFEF;
            padding:15px;
            border-radius:3px;
            margin-bottom:15px;
        }
        .review-block-name{
            font-size:12px;
            margin:10px 0;
        }
        .review-block-date{
            font-size:12px;
        }
        .review-block-rate{
            font-size:13px;
            margin-bottom:15px;
        }
        .review-block-description{
            font-size:13px;
        }
        .animated {
            -webkit-transition: height 0.2s;
            -moz-transition: height 0.2s;
            transition: height 0.2s;
        }
    </style>
</head>
<body>
<div class="row">
    <div class="col-md-1"></div>
    <div class="col-md-10">
<div class="panel panel-primary">
    <div class="panel-heading">${book.title}</div>
    <div class="panel-body">

<div class="row">
    <div class="col-md-4"><img src="/getCover/${book.id}" height="222" width="350" /> </div>
    <div class="col-md-8">
        <div class="table">
            <table class="table table-condensed">
                <tbody>
                <tr>
                    <td>Authors</td>
                    <td>${book.printAuthors()}</td>
                </tr>
                <tr>
                    <td>Pages count</td>
                    <td>${book.pagesCnt}</td>
                </tr>
                <tr>
                    <td>Publisher</td>
                    <td>${book.publisher}</td>
                </tr>
                <tr>
                    <td>Publish year</td>
                    <td>${book.publishYear}</td>
                </tr>
                <tr>
                    <td>Genres</td>
                    <td>${book.printGenres()}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
        <hr/>
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <div class="well well-sm">
                        <div class="text-right">
                            <a class="btn btn-success btn-green" href="#reviews-anchor" id="open-review-box">Leave a Review</a>
                        </div>

                        <div class="row" id="post-review-box" style="display:none;">
                            <div class="col-md-12">
                                <form:form modelAttribute="newReview" accept-charset="UTF-8" method="POST">
                                    <form:input path="rate" id="ratings-hidden" name="rating" type="hidden" />
                                    <form:textarea path="text" class="form-control animated" cols="50" id="new-review" name="comment" placeholder="Enter your review here..." rows="5" />

                                    <div class="text-right">
                                        <div class="stars starrr" data-rating="0"></div>
                                        <a class="btn btn-danger btn-sm" href="#" id="close-review-box" style="display:none; margin-right: 10px;">
                                            <span class="glyphicon glyphicon-remove"></span>Cancel</a>
                                        <button class="btn btn-success btn-lg" type="submit">Submit</button>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="well well-sm">
                        <div class="row">
                            <div class="col-xs-6 col-md-6 text-center">
                                <h1 class="rating-num">
                                    ${book.averageRate}</h1>
                                <div class="rating">
                                    <c:choose>
                                        <c:when test="${book.averageRate >= 0.5}">
                                            <span class="glyphicon glyphicon-star golden-star"></span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="glyphicon glyphicon-star-empty golden-star"></span>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${book.averageRate >= 1.5}">
                                            <span class="glyphicon glyphicon-star golden-star"></span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="glyphicon glyphicon-star-empty golden-star"></span>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${book.averageRate >= 2.5}">
                                            <span class="glyphicon glyphicon-star golden-star"></span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="glyphicon glyphicon-star-empty golden-star"></span>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${book.averageRate >= 3.5}">
                                            <span class="glyphicon glyphicon-star golden-star"></span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="glyphicon glyphicon-star-empty golden-star"></span>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${book.averageRate >= 4.5}">
                                            <span class="glyphicon glyphicon-star golden-star"></span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="glyphicon glyphicon-star-empty golden-star"></span>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div>
                                    <span class="glyphicon glyphicon-user"></span> ${book.reviewsCnt} total
                                </div>
                            </div>
                            <div class="col-xs-6 col-md-6">
                                <div class="row rating-desc">
                                    <div class="col-xs-3 col-md-3 text-right">
                                        <span class="glyphicon glyphicon-star golden-star"></span>5
                                    </div>
                                    <div class="col-xs-8 col-md-9">
                                        <div class="progress progress-striped">
                                            <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="20"
                                                 aria-valuemin="0" aria-valuemax="100" style="width: ${book.findRatePercent(5)}%">
                                                <span class="sr-only">${book.findRatePercent(5)}%</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-3 col-md-3 text-right">
                                        <span class="glyphicon glyphicon-star golden-star"></span>4
                                    </div>
                                    <div class="col-xs-8 col-md-9">
                                        <div class="progress">
                                            <div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="20"
                                                 aria-valuemin="0" aria-valuemax="100" style="width: ${book.findRatePercent(4)}%">
                                                <span class="sr-only">${book.findRatePercent(4)}%</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-3 col-md-3 text-right">
                                        <span class="glyphicon glyphicon-star golden-star"></span>3
                                    </div>
                                    <div class="col-xs-8 col-md-9">
                                        <div class="progress">
                                            <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="20"
                                                 aria-valuemin="0" aria-valuemax="100" style="width: ${book.findRatePercent(3)}%">
                                                <span class="sr-only">${book.findRatePercent(3)}%</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-3 col-md-3 text-right">
                                        <span class="glyphicon glyphicon-star golden-star"></span>2
                                    </div>
                                    <div class="col-xs-8 col-md-9">
                                        <div class="progress">
                                            <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="20"
                                                 aria-valuemin="0" aria-valuemax="100" style="width: ${book.findRatePercent(2)}%">
                                                <span class="sr-only">${book.findRatePercent(2)}%</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-3 col-md-3 text-right">
                                        <span class="glyphicon glyphicon-star golden-star"></span>1
                                    </div>
                                    <div class="col-xs-8 col-md-9">
                                        <div class="progress">
                                            <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="80"
                                                 aria-valuemin="0" aria-valuemax="100" style="width: ${book.findRatePercent(1)}%">
                                                <span class="sr-only">${book.findRatePercent(1)}%</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <hr/>
        <c:forEach var="review" items="${book.reviews}">
            <div class="row">
                <div class="col-sm-7">
                    <div class="review-block">
                        <div class="row">
                            <div class="col-sm-3">
                                <img src="/getIcon/${review.user.login}" class="img-rounded" width="100" height="100">
                                <div class="review-block-name"><a href="#">${review.user.login}</a></div>
                                <div class="review-block-date">${review.date}</div>
                            </div>
                            <div class="col-sm-9">
                                <div class="review-block-rate">
                                    <c:choose>
                                        <c:when test="${review.rate >= 0.5}">
                                            <span class="glyphicon glyphicon-star stars"></span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="glyphicon glyphicon-star-empty stars"></span>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${review.rate >= 1.5}">
                                            <span class="glyphicon glyphicon-star stars"></span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="glyphicon glyphicon-star-empty stars"></span>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${review.rate >= 2.5}">
                                            <span class="glyphicon glyphicon-star stars"></span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="glyphicon glyphicon-star-empty stars"></span>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${review.rate >= 3.5}">
                                            <span class="glyphicon glyphicon-star stars"></span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="glyphicon glyphicon-star-empty stars"></span>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${review.rate >= 4.5}">
                                            <span class="glyphicon glyphicon-star stars"></span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="glyphicon glyphicon-star-empty stars"></span>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="review-block-description">${review.text}</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
</div>
</div>
    </div>
    <div class="col-md-1"></div>
</div>

<script src="${pageContext.request.contextPath}/webjars/jquery/3.1.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
<script src="<c:url value="/resources/js/stars.js" />"></script>
</body>
</html>