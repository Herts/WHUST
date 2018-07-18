<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html class="no-js" lang="en">
<head>
    <title>User Registration Form</title>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <%@ include file="../universal/allcss.jsp" %>
    <style type="text/css">
        .blackcolor {
            color: black;
        }

        .title {
            text-align: center;
            position: relative;
            font-weight: normal;
            margin-bottom: 30px;
            color: #abd373;
        }

        .title:after, .title:before {
            content: '';
            position: absolute;
            top: 50%;
            width: 45%;
            background: #abd373;
            height: 1px;
        }

        .title:before {
            left: 0;
        }

        .title:after {
            right: 0;
        }

        .tab thead {
            background-color: black;
            color: white;
            vertical-align: center;
        }

        .tab thead th {
            padding: 10px 0;
            font-weight: normal;
            vertical-align: center;
        }

        .tab td {
            text-align: center;
            padding: 10px 0;
            vertical-align: center;
        }

        .goods {
            width: 40%;
            position: relative;
            vertical-align: center;
        }

        .goods-left {
            width: 20%;
            float: left;
            vertical-align: center;
        }

        .goods-right {
            width: 78%;
            float: right;
            text-align: left;
            vertical-align: center;
        }

        .goods-right .tip {
            color: #999;
            position: absolute;
            bottom: 10px;
            font-size: 12px;
            vertical-align: center;
        }

        .num input {
            width: 50px;
            text-align: center;
            vertical-align: center;
        }

        .num a {
            color: #333;
            text-decoration: none;
            vertical-align: center;
        }

        .del {
            cursor: pointer;
            vertical-align: center;
        }

        .footer td {
            border-top: 1px solid #abd373;
            vertical-align: center;
        }

        .footer td:nth-child(2) {
            text-align: left;
            vertical-align: center;
        }

        .footer td:nth-child(3) {
            text-align: right;
            vertical-align: center;
        }

    </style>
    <script>
        function hid(id) {
            document.getElementById(id).remove();
            for (var a in document.getElementsByClassName(id))
                a.remove();
        }
    </script>
</head>
<body>
<!--[if lt IE 8]>
<p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade
    your browser</a> to improve your experience.</p>
<![endif]-->

<div class="wrapper">
    <%--头部引用开始--%>
    <header>

        <div class="header-container">
            <div class="header-area header-absolute header-sticky pt-30 pb-30">
                <div class="container-fluid pl-50 pr-50">
                    <%@include file="../universal/headerContent.jsp" %>
                </div>
            </div>
        </div>
    </header>
    <!--Header Area End-->
    <div style="width: 75%;margin-top:10%">
        <div class="well lead" style="margin-top: 20%"><h2>User Registration Form</h2></div>
        <form:form method="POST" modelAttribute="user" class="form-horizontal">
            <div class="row" style="width: 75%;margin:0 auto">
                <div class="form-group col-md-12">
                    <c:choose>
                    <c:when test="${edit}">
                    <label class="col-md-3 control-lable" for="userid">User ID</label>
                    <div class="col-md-7">
                        <form:input type="text" path="userid" id="userid" class="form-control input-sm"
                                    disabled="true"/>
                        </c:when>
                        <c:otherwise>
                        </c:otherwise>
                        </c:choose>

                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="username">Username</label>
                    <div class="col-md-7">
                        <form:input type="text" path="username" id="username" class="form-control input-sm"/>
                        <div class="has-error">
                            <form:errors path="username" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="password">Password</label>
                    <div class="col-md-7">
                        <form:input type="password" path="password" id="password" class="form-control input-sm"/>
                        <div class="has-error">
                            <form:errors path="password" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>


            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="phone">Phone</label>
                    <div class="col-md-7">
                        <form:input type="text" path="phone" id="phone" class="form-controlinput-sm"/>
                    </div>
                </div>
            </div>


            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="email">Email</label>
                    <div class="col-md-7">
                        <form:input type="text" path="email" id="email" class="form-control input-sm"/>
                        <div class="has-error">
                            <form:errors path="email" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>


            <div class="row">
                <div class="form-actions floatRight">
                    <c:choose>
                        <c:when test="${edit}">
                            <input style="width: 115px" type="submit" value="Update" class="form-button"/><br/><br/>
                            <button style="width: 115px" class="form-button"><a style="color: white"
                                                                                href="<c:url value='/manageUser' />">Cancel</a>
                            </button>
                            <br/><br/><br/>
                        </c:when>
                        <c:otherwise>
                            <input style="width: 115px" type="submit" value="Register"
                                   class="form-button"/><br/><br/>
                            <button style="width: 115px" class="form-button"><a style="color: white"
                                                                                href="<c:url value='/manageUser' />">Cancel</a>
                            </button>
                            <br/><br/><br/>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </form:form>
    </div>


    <%@include file="../universal/footer.jsp" %>
</div>
<%@ include file="../universal/alljs.jsp" %>
</body>
</html>
