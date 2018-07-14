<%@ page import="whustore.model.Customer" %><%--
  Created by IntelliJ IDEA.
  User: huhaomeng
  Date: 2018/7/8
  Time: 下午4:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html class="no-js" lang="en">
<head>
    <title>Title</title>

    <%@ include file="../universal/allcss.jsp" %>
    <style>
        .account_area {
            width: 80%;
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

        .birth_day {
            white-space: nowrap;
            width: 80px;
        }

        .c_photo {
            margin-top: -270px;
            margin-left: 27%;
        }
    </style>

</head>
<body>
<div class="wrapper">
    <%--头部引用开始--%>
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
    <div class="account_area">
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
                    <%--<input name="sex" type="text" id="customer_sex" value="" maxlength="25">--%>
                    <br>
                    <br>
                    <p>生日</p>
                    <input name="date" type="date" id="c_birthday" value="${customer.date}">

                    <br/>
                    <p>电话</p>
                    <input name="phone" type="text" id="customer_phone" value="${customer.phone}" maxlength="25">
                    <p>邮箱</p>
                    <input name="email" type="text" id="customer_email" value="${customer.email}" maxlength="60">
                    <p>地址</p>
                    <input name="address" type="text" id="customer_address" value="${customer.address}" maxlength="255">
                    <br/><br/>
                    <button class="btn-outline-dark" type="submit" value="保存" style="margin-left: 220px">保存</button>
                </div>

            </div>
        </form>
    </div>
    <%@include file="../universal/footer.jsp" %>

</div>

<%@ include file="../universal/alljs.jsp" %>
</body>
</html>
