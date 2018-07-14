<%@ page import="whustore.model.Recommend" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="whustore.model.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.ui.ModelMap" %><%--
  Created by IntelliJ IDEA.
  User: huhaomeng
  Date: 2018/7/9
  Time: 下午3:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html class="no-js" lang="en">
<head>
    <title>逛一逛————WHU STORE</title>

    <%@ include file="universal/allcss.jsp" %>

</head>
<body>
<div class="wrapper">
    <%--头部引用开始--%>
    <header>
        <div class="header-container">
            <div class="header-area header-sticky pt-30 pb-30">
                <div class="container">
                    <%@include file="universal/headerContent.jsp" %>
                </div>
            </div>
        </div>
    </header>
    <!--Header Area End-->
    <div class="product-area mt-85" style="margin:10% auto">
        <div class="container">
            <div class="row">
                <!--Section Title Start-->
                <div class="col-12">
                    <div class="section-title text-center mb-35">
                        <span><h3>逛一逛</h3></span>
                        <br/><br/><br/>
                        <h3>一些有意思的商品，了解一下？</h3>
                        <br/><br/>
                    </div>
                </div>
                <!--Section Title End-->
            </div>
            ﻿
            <!--商品们  两个一组  目标是11组 22个-->
            <div class="row">
                <div class="product-slider-active">
                    <%
                        //为每一个商品添加html元素
                        Recommend recommend = (Recommend) request.getSession().getAttribute("recommend");
                        ArrayList<Product> list = recommend.getRecommendList();
                        for (int i = 0; i < 22; i++) {
                            //每两个一个列头
                            Product product = list.get(i);
                            out.println(i % 2 == 0 ? "<div class=\"col-md-3 col-lg-3 col-sm-4 col-xs-12\">" : "");
                    %>
                    <!--Single Product Start-->
                    <div class="single-product mb-25">
                        <div class="product-img img-full">
                            <a href="product?productID=<%=product.getId()%>">
                                <img src="<%="../../" + product.getPicPath().get(0)%>" alt="">
                            </a>
                            <span class="onsale">Sale!</span>
                            <div class="product-action">
                                <ul>
                                    <li><a href="#open-modal" data-toggle="modal"
                                           title="Quick view"><i class="fa fa-search"></i></a>
                                    </li>
                                    <li><a href="#" title="Whishlist"><i
                                            class="fa fa-heart-o"></i></a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="product-content">
                            <h2><a href="product?productID=<%=product.getId()%>">Eleifend quam</a></h2>
                            <div class="product-price">
                                <div class="price-box">
                                    <span class="regular-price"><%=product.getPrice()%></span>
                                </div>
                                <div class="add-to-cart">
                                    <a onclick="add(<%=product.getId()%>,2,1)">加入购物车</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--Single Product End-->
                    <%
                            out.println(i % 2 == 1 ? "</div>" : "");
                        }
                    %>       ﻿
                    <%--<div class="col-md-3 col-lg-3 col-sm-4 col-xs-12">
                        <!--Single Product Start-->
                        <div class="single-product mb-25">
                            <div class="product-img img-full">
                                <a href="single-product.html">
                                    <img src="img/product/product1.jpg" alt="">
                                </a>
                                <span class="onsale">Sale!</span>
                                <div class="product-action">
                                    <ul>
                                        <li><a href="#open-modal" data-toggle="modal"
                                               title="Quick view"><i class="fa fa-search"></i></a>
                                        </li>
                                        <li><a href="#" title="Whishlist"><i
                                                class="fa fa-heart-o"></i></a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="product-content">
                                <h2><a href="single-product.html">Eleifend quam</a></h2>
                                <div class="product-price">
                                    <div class="price-box">
                                        <span class="regular-price">￥115.00</span>
                                    </div>
                                    <div class="add-to-cart">
                                        <a href="#">加入购物车</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--Single Product End-->
                        <!--Single Product Start-->
                        <div class="single-product mb-25">
                            <div class="product-img img-full">
                                <a href="single-product.html">
                                    <img src="img/product/product2.jpg" alt="">
                                </a>
                                <div class="product-action">
                                    <ul>
                                        <li><a href="#open-modal" data-toggle="modal"
                                               title="Quick view"><i class="fa fa-search"></i></a>
                                        </li>
                                        <li><a href="#" title="Whishlist"><i
                                                class="fa fa-heart-o"></i></a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="product-content">
                                <h2><a href="single-product.html">Odio tortor consequat</a></h2>
                                <div class="product-price">
                                    <div class="price-box">
                                        <span class="regular-price">￥90.00</span>
                                    </div>
                                    <div class="add-to-cart">
                                        <a href="#">加入购物车</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--Single Product End-->
                    </div>--%>
                    <%--<%
                        String groupHead = "<div class=\"col-md-3 col-lg-3 col-sm-4 col-xs-12\">";
                        String groupFoot = "</div>";
                        String beforeUrl = "<!--Single Product Start-->\n" +
                                "                        <div class=\"single-product mb-25\">\n" +
                                "                            <div class=\"product-img img-full\">\n" +
                                "                                <a href=\"";
                        String afterUrlBeforeImgSrc = "\">\n" +
                                "                                    <img src=\"";
                        String afterImgSrcBeforeSale = "\" alt=\"\">\n" +
                                "                                </a>";
                        String sale = "<span class=\"促销!\">Sale!</span>";
                        //等待添加打开悬浮商品界面和添加到心仪列表功能
                        String afterSaleBeforeUrl2 = "<div class=\"product-action\">\n" +
                                "                                    <ul>\n" +
                                "                                        <li><a href=\"#open-modal\" data-toggle=\"modal\"\n" +
                                "                                               title=\"Quick view\"><i class=\"fa fa-search\"></i></a>\n" +
                                "                                        </li>\n" +
                                "                                        <li><a href=\"#\" title=\"Whishlist\"><i\n" +
                                "                                                class=\"fa fa-heart-o\"></i></a></li>\n" +
                                "                                    </ul>\n" +
                                "                                </div>\n" +
                                "                            </div>\n" +
                                "                            <div class=\"product-content\">\n" +
                                "                                <h2><a href=\"";
                        String afterUrl2BeforeName = "\">";
                        String afterNameBeforePrice = "</a></h2>\n" +
                                "                                <div class=\"product-price\">\n" +
                                "                                    <div class=\"price-box\">\n" +
                                "                                        <span class=\"regular-price\">￥";
                        //等待添加加入购物车功能
                        String afterPrice = "</span>\n" +
                                "                                    </div>\n" +
                                "                                    <div class=\"add-to-cart\">\n" +
                                "                                        <a href=\"#\">加入购物车</a>\n" +
                                "                                    </div>\n" +
                                "                                </div>\n" +
                                "                            </div>\n" +
                                "                        </div>\n" +
                                "                        <!--Single Product End-->";
                        //根据传入的List生产商品
                        Recommend recommend = (Recommend) request.getSession().getAttribute("recommend");
                        ArrayList<Product> list = recommend.getRecommendList();
                        int num = list.size();
                        if (num >= 22) {
                            for (int i = 0; i < 22; i++) {
                                String temp = "";
                                Product current = list.get(i);
                                //组头
                                if (i % 2 == 0)
                                    out.println(groupHead);

                                out.println(beforeUrl);
                                //url
                                out.println("#");

                                temp += afterUrlBeforeImgSrc;
                                out.println(afterUrlBeforeImgSrc);
                                //图片地址
                                temp += "../../" + current.getPicPath().get(0);
                                out.println("../../" + current.getPicPath().get(0));
                                temp += afterImgSrcBeforeSale;
                                out.println(afterImgSrcBeforeSale);
                                //促销？
                                //out.println(sale);
                                out.println(afterSaleBeforeUrl2);
                                //url2
                                out.println("#open-modal");
                                out.println(afterUrl2BeforeName);
                                //商品名
                                out.println(list.get(i).getProductName());
                                out.println(afterNameBeforePrice);
                                //价格
                                out.println(current.getPrice());
                                out.println(afterPrice);

                                //组尾
                                if (i % 2 == 1)
                                    out.println(groupFoot);
                            }
                        }
                    %>--%>
                </div>
            </div>
            <!--商品完-->
        </div>
    </div>
    <%@include file="universal/footer.jsp" %>

</div>

<%@ include file="universal/alljs.jsp" %>
</body>
</html>