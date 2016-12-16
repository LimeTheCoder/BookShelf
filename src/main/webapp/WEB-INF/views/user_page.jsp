<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <link rel='stylesheet'
          href='${pageContext.request.contextPath}/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css'>
    <link rel='stylesheet'
          href='${pageContext.request.contextPath}/webjars/bootstrap/3.3.7-1/css/bootstrap-theme.min.css'>

    <title>User page</title>

    <style>
        .card {
            margin-top: 20px;
            padding: 30px;
            background-color: rgba(214, 224, 226, 0.2);
            -webkit-border-top-left-radius:5px;
            -moz-border-top-left-radius:5px;
            border-top-left-radius:5px;
            -webkit-border-top-right-radius:5px;
            -moz-border-top-right-radius:5px;
            border-top-right-radius:5px;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
        }
        .card.hovercard {
            position: relative;
            padding-top: 0;
            overflow: hidden;
            text-align: center;
            background-color: #fff;
            background-color: rgba(255, 255, 255, 1);
        }
        .card.hovercard .card-background {
            height: 130px;
        }
        .card-background img {
            -webkit-filter: blur(25px);
            -moz-filter: blur(25px);
            -o-filter: blur(25px);
            -ms-filter: blur(25px);
            filter: blur(25px);
            margin-left: -100px;
            margin-top: -200px;
            min-width: 130%;
        }
        .card.hovercard .useravatar {
            position: absolute;
            top: 15px;
            left: 0;
            right: 0;
        }
        .card.hovercard .useravatar img {
            width: 100px;
            height: 100px;
            max-width: 100px;
            max-height: 100px;
            -webkit-border-radius: 50%;
            -moz-border-radius: 50%;
            border-radius: 50%;
            border: 5px solid rgba(255, 255, 255, 0.5);
        }
        .card.hovercard .card-info {
            position: absolute;
            bottom: 14px;
            left: 0;
            right: 0;
        }
        .card.hovercard .card-info .card-title {
            padding:0 5px;
            font-size: 20px;
            line-height: 1;
            color: #262626;
            background-color: rgba(255, 255, 255, 0.1);
            -webkit-border-radius: 4px;
            -moz-border-radius: 4px;
            border-radius: 4px;
        }
        .card.hovercard .card-info {
            overflow: hidden;
            font-size: 12px;
            line-height: 20px;
            color: #737373;
            text-overflow: ellipsis;
        }
        .card.hovercard .bottom {
            padding: 0 20px;
            margin-bottom: 17px;
        }
        .btn-pref .btn {
            -webkit-border-radius:0 !important;
        }
        .shape{
            border-style: solid; border-width: 0 70px 40px 0; float:right; height: 0px; width: 0px;
            -ms-transform:rotate(360deg); /* IE 9 */
            -o-transform: rotate(360deg);  /* Opera 10.5 */
            -webkit-transform:rotate(360deg); /* Safari and Chrome */
            transform:rotate(360deg);
        }
        .offer{
            background:#fff; border:1px solid #ddd; box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2); margin: 15px 0; overflow:hidden;
        }
        .offer:hover {
            -webkit-transform: scale(1.1);
            -moz-transform: scale(1.1);
            -ms-transform: scale(1.1);
            -o-transform: scale(1.1);
            -webkit-transition: all 0.4s ease-in-out;
            -moz-transition: all 0.4s ease-in-out;
            -o-transition: all 0.4s ease-in-out;
            transition: all 0.4s ease-in-out;
        }
        .shape {
            border-color: rgba(255,255,255,0) #d9534f rgba(255,255,255,0) rgba(255,255,255,0);
        }
        .offer-radius{
            border-radius:7px;
        }
        .offer-danger {	border-color: #d9534f; }
        .offer-danger .shape{
            border-color: transparent #d9534f transparent transparent;
        }

        .offer-warning {	border-color: #f0ad4e; }
        .offer-warning .shape{
            border-color: transparent #f0ad4e transparent transparent;
        }

        .shape-text{
            color:#fff; font-size:12px; font-weight:bold; position:relative; right:-40px; top:2px; white-space: nowrap;
            -ms-transform:rotate(30deg); /* IE 9 */
            -o-transform: rotate(360deg);  /* Opera 10.5 */
            -webkit-transform:rotate(30deg); /* Safari and Chrome */
            transform:rotate(30deg);
        }
        .offer-content{
            padding:0 20px 10px;
        }
    </style>
</head>
<body>

<div class="row">
<div class="col-md-2"></div>
<div class="col-md-8">
    <div class="card hovercard">
        <div class="card-background">
            <img class="card-bkimg" alt="" src="/getIcon/${user.login}">
            <!-- http://lorempixel.com/850/280/people/9/ -->
        </div>
        <div class="useravatar">
            <img alt="" src="/getIcon/${user.login}">
        </div>
        <div class="card-info"> <span class="card-title">${user.toString()}</span>

        </div>
    </div>
    <div class="btn-pref btn-group btn-group-justified btn-group-lg" role="group" aria-label="...">
        <div class="btn-group" role="group">
            <button type="button" id="following" class="btn btn-primary" href="#tab1" data-toggle="tab"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                <div class="hidden-xs">Information</div>
            </button>
        </div>
        <div class="btn-group" role="group">
            <button type="button" id="stars" class="btn btn-default" href="#tab2" data-toggle="tab"><span class="glyphicon glyphicon-star" aria-hidden="true"></span>
                <div class="hidden-xs">Stars</div>
            </button>
        </div>
        <div class="btn-group" role="group">
            <button type="button" id="favorites" class="btn btn-default" href="#tab3" data-toggle="tab"><span class="glyphicon glyphicon-heart" aria-hidden="true"></span>
                <div class="hidden-xs">Favorites</div>
            </button>
        </div>
    </div>


    <div class="well">
        <div class="tab-content">
            <div class="tab-pane fade in active" id="tab1">
                <div class="table">
                    <table class="table table-condensed">
                        <tbody>
                        <tr>
                            <td>Login</td>
                            <td>${user.login}</td>
                        </tr>
                        <tr>
                            <td>Name</td>
                            <td>${user.name}</td>
                        </tr>
                        <tr>
                            <td>Surname</td>
                            <td>${user.surname}</td>
                        </tr>
                        <tr>
                            <td>City</td>
                            <td>${user.city}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="tab-pane fade in" id="tab2">
                <h3>This is tab 2</h3>
            </div>
            <div class="tab-pane fade in" id="tab3">
                    <div class="row">
                        <c:forEach var="book" items="${liked}" >
                            <div class="col-xs-12 col-sm-6 col-md-3 col-lg-2 clickable"
                                 data-href="<c:url value="/admin/books" />">
                                <div class="offer offer-radius offer-danger">
                                    <div class="shape">
                                        <div class="shape-text">
                                            liked
                                        </div>
                                    </div>
                                    <div class="offer-content">
                                        <h3 class="lead">
                                            <c:out value="${book.title}" />
                                        </h3>
                                        <p>
                                            <c:out value="${book.description}" />
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>

            </div>
        </div>
    </div>
</div>

<div class="col-md-2"></div>
</div>

<script src="${pageContext.request.contextPath}/webjars/jquery/3.1.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
<script>$(document).ready(function() {
    $(".btn-pref .btn").click(function () {
        $(".btn-pref .btn").removeClass("btn-primary").addClass("btn-default");
        // $(".tab").addClass("active"); // instead of this do the below
        $(this).removeClass("btn-default").addClass("btn-primary");
    });
});
</script>

<script>
jQuery(document).ready(function($) {
    $(".clickable").click(function() {
        window.document.location = $(this).data("href");
    });
});
</script>
</body>
</html>
