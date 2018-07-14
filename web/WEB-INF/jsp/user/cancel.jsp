<%@ page import="whustore.model.Customer" %>
<%--
  Created by IntelliJ IDEA.
  User: 葛鹭
  Date: 2018/7/12
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<<!doctype html>
<html class="no-js" lang="en">
<head>
    <title>取消订单申请</title>
    <%@ include file="../universal/allcss.jsp" %>
</head>
<body>
<div class="wrapper">
    <%--头部引用--%>
    <%@include file="../universal/header.jsp" %>
    <div class="cancel_area" style=" width:100%; height:auto;margin: 10% auto;">
        <div style="text-align: center">
            <h2>取消订单</h2>
        </div>
        <br/>
        <form action="" onsubmit="submit_sure()" method="post">
            <div class="single-feature mb-35" style="width: 60%;margin: 0 auto;min-width: 500px">
                <div class="p_photo" style=" width: 30%;float: left;margin-left: 5%">
                    <div id="result" style="height: 200px;width: 200px"><img src=""></div>
                </div>
                <div class="customer_info" style="width:70%;float: left;">
                    <p>商品名称</p><br/>
                    <p>${product.productName}</p>
                    <p>商品价格</p><br/>
                    <p>¥${product.price}</p>
                    <p>商品简述</p><br/>
                    <p>${product.proIntro}</p>
                    <p>取消订单的原因</p>
                    <textarea type="t" id="cancel_description" value="" name=""></textarea>
                    <br/><br/>
                    <button type="submit" value="add_product" style="margin-left: 45%">确认申请</button>
                </div>
                <script type="text/javascript">
                    function submit_sure() {
                        var flag = confirm("您确定要提交申请吗？");
                        if (flag == true) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                </script>
            </div>
        </form>
    </div>
        <%@include file="../universal/footer.jsp" %>
        <%@ include file="../universal/alljs.jsp" %>
</div>
</body>
</html>


