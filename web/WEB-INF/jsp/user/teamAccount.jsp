<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="whustore.model.User" %>
<%--
  Created by IntelliJ IDEA.
  User: 葛鹭
  Date: 2018/7/14
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html class="no-js" lang="en">
<head>
    <%@include file="../universal/allcss.jsp" %>
    <style>
        .account_area {
            width: 100%;
            height: 750px;
            margin: 100px auto;
            margin-top: 200px;
            padding-left: 250px;
            font-size: 1em;
        }

        input {
            text-align: center;
        }

        .customer_photo {
            width: 30%;
            float: left;
        }

        .customer_info {
            width: 70%;
            float: left;
            padding-left: 5%;
            min-width: 200px
        }

        .c_photo {
            margin-top: -270px;
            margin-left: 27%;
        }
    </style>
</head>
<body>


<!--[if lt IE 8]>
<p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade
    your browser</a> to improve your experience.</p>
<![endif]-->

<div class="wrapper">
    <!--Header Area Start-->
    <header>
        <div class="header-container">
            <div class="header-area header-sticky pt-30 pb-30">
                <div class="container">
                    <%@include file="../universal/headerContent.jsp" %>
                </div>
            </div>
        </div>
    </header>
    <!--Header Area End-->
    <div class="team_container" style="margin:10% auto;width:75% ">
        <div class="team-sidebar" style="width: 30%;float: left">
            <h2>团队行为</h2>
            <br/>
            <div class="categori-checkbox" style="height: 1000px">
                <ul>
                    <li>
                        <button class="form-button" style="width:50%"><a href="/gotoAddproduct" style="color: white;font-size: larger;margin: auto auto">添加商品</a>
                        </button>
                    </li>
                    <br/>
                    <li>
                        <button class="form-button" style="width:50%"><a href="/teamProductList" style="color: white;font-size: larger;margin: auto auto">本组商品</a>
                        </button>
                    </li>
                    <br/>
                    <li>
                        <button class="form-button" style="width:50%"><p style="color: white;font-size: larger">添加商品</p>
                        </button>
                    </li>
                    <br/>
                    <li>
                        <button class="form-button" style="width:50%"><p style="color: white;font-size: larger">添加商品</p>
                        </button>
                    </li>
                    <br/>
                    <li>
                        <button class="form-button" style="width:50%"><p style="color: white;font-size: larger">添加商品</p>
                        </button>
                    </li>
                    <br/>
                    <li>
                        <button class="form-button" style="width:50%"><p style="color: white;font-size: larger">添加商品</p>
                        </button>
                    </li>
                    <br/>
                </ul>
            </div>
        </div>
        <div class="account_area" style="align:left;">
            <form action="/user/changeInfo" method="post">
                <div class="single-feature mb-35" style="width: 75%">
                    <!--<div class="con4">-->
                    <!--<canvas id="cvs" width="200" height="200" ></canvas>-->
                    <!--<span class="btn upload">上传头像<input type="file" class="upload_pic" id="upload" /></span>-->
                    <!--</div>-->
                    <div class="customer_photo">
                        <img class="c_photo"
                             src="//wwc.alicdn.com/avatar/getAvatar.do?userId=2239377814&amp;width=80&amp;height=80&amp;type=sns"
                             style="padding-right: 30px; margin-top: 0px">
                        <button class="btn-outline-dark" type="submit" style="margin-left:30%; margin-top: 20px">上传图片
                        </button>
                        <br>
                        <div style="margin-left: 30px">
                            <h4 style=" margin-top: 100px;margin-left: 42px">姓名</h4>
                            <input name="lname" value="${customer.lname}" class="" type="text" placeholder="姓"
                                   style="height: 30px;width: 60px;text-align: center">
                            <input name="fname" value="${customer.fname}" type="text " placeholder="名"
                                   style="height: 30px;width: 60px;text-align: center">
                        </div>
                    </div>
                    <div class="customer_info">
                        <p>性别</p>
                        <select name="sex">

                            <option value="m" ${ms}>男</option>
                            <option value="f" ${fs}>女</option>
                        </select>
                        <br>
                        <br>
                        <p>生日</p>
                        <input name="date" type="date" id="c_birthday" value="${customer.date}" readonly>

                        <br/>
                        <p>电话</p>
                        <input name="phone" type="text" id="customer_phone" value="${customer.phone}" maxlength="25"
                               readonly>
                        <p>邮箱</p>
                        <input name="email" type="text" id="customer_email" value="${customer.email}" maxlength="60"
                               readonly>
                        <p>地址</p>
                        <input name="address" type="text" id="customer_address" value="${customer.address}"
                               maxlength="255" readonly>
                        <br/><br/>
                        <button class="btn-outline-dark" type="submit" value="保存" style="margin-left: 220px">保存</button>
                    </div>

                </div>
            </form>
        </div>
    </div>
    <!--Footer Area Start-->
    <%@include file="../universal/footer.jsp" %>
    <!--Footer Area End-->
    <!-- Modal Area Strat -->
</div>

<%@include file="../universal/alljs.jsp" %>
</body>
</html>

