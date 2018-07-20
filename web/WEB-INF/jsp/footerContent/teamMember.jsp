<%--
  Created by IntelliJ IDEA.
  User: 葛鹭
  Date: 2018/7/20
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="whustore.model.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <title>团队成员</title>
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

        .chbsty {
            height: 15px;
            width: 15px;
        }
    </style>
</head>
<body>
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
        <div style="width: 60%;margin:10% auto">
            <div><h1 style="text-align: center">团队成员</h1></div><br/>
            <div >
                <img src="../../../img/footer/teamMember.png" style="width: 100%">
            </div>
        </div>
    <%@include file="../universal/footer.jsp" %>

</div>

<%@ include file="../universal/alljs.jsp" %>
</body>
</html>
