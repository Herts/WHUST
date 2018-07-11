<%@ page import="java.util.List" %>
<%@ page import="whustore.model.Product" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--
  Created by IntelliJ IDEA.
  User: huhaomeng
  Date: 2018/7/10
  Time: 下午3:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html class="no-js" lang="en">
<head>
    <title>逛一逛————WHU STORE</title>

    <%@ include file="universal/allcss.jsp" %>
</head>
<body>
<!--[if lt IE 8]>
<p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade
    your browser</a> to improve your experience.</p>
<![endif]-->

<div class="wrapper">
    <%--头部引用开始--%>
    <%@include file="universal/header.jsp" %>
    <div class="shop-area mb-70" style="padding-top: 10%">
        <div class="container">
            <div class="row">
                <!--Product Category Widget Start-->
                <div class="col-lg-3 order-2 order-lg-1">

                    <div class="shop-sidebar">
                        <h4>分类检索</h4>
                        <div class="categori-checkbox">
                            <form action="#">
                                <ul>
                                    <%
                                        for (String current :
                                                (List<String>) request.getSession().getAttribute("categories")) {
                                    %>
                                    <li>
                                        <input name="product-categori" type="checkbox" value="<%=current%>>">
                                        <a href="/shopByCate?category=<%=current%>">
                                            <%=current%>
                                        </a>
                                    </li>
                                    <%
                                        }
                                    %>

                                </ul>
                            </form>
                        </div>
                    </div>

                </div>
                <!--Product Category Widget End-->
                <%--商品页和排序页--%>
                <div class="col-lg-9 order-1 order-lg-2">
                    <div class="shop-layout">
                        <!--Grid & List View Start-->
                        <div class="shop-topbar-wrapper d-md-flex justify-content-md-between align-items-center">
                            <div class="grid-list-option">
                                <ul class="nav">
                                    <li>
                                        <a class="active show" data-toggle="tab" href="#grid"><i
                                                class="fa fa-th-large"></i></a>
                                    </li>
                                    <li>
                                        <a data-toggle="tab" href="#list" class=""><i class="fa fa-th-list"></i></a>
                                    </li>
                                </ul>
                            </div>
                            <!--Toolbar Short Area Start-->
                            <div class="toolbar-short-area d-md-flex align-items-center">
                                <div class="toolbar-shorter ">
                                    <label>Sort By:</label>
                                    <select class="wide" style="display: none;">
                                        <option data-display="Select">Nothing</option>
                                        <option value="Relevance">Relevance</option>
                                        <option value="Name, A to Z">Name, A to Z</option>
                                        <option value="Name, Z to A">Name, Z to A</option>
                                        <option value="Price, low to high">Price, low to high</option>
                                        <option value="Price, high to low">Price, high to low</option>
                                    </select>
                                    <div class="nice-select wide" tabindex="0"><span class="current">Select</span>
                                        <ul class="list">
                                            <li data-value="Nothing" data-display="Select" class="option selected">
                                                Nothing
                                            </li>
                                            <li data-value="Relevance" class="option">Relevance</li>
                                            <li data-value="Name, A to Z" class="option">Name, A to Z</li>
                                            <li data-value="Name, Z to A" class="option">Name, Z to A</li>
                                            <li data-value="Price, low to high" class="option">Price, low to high</li>
                                            <li data-value="Price, high to low" class="option focus">Price, high to
                                                low
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <p class="show-product">Showing 1–9 of 42 results</p>
                            </div>
                            <!--Toolbar Short Area End-->
                        </div>
                        <!--Grid & List View End-->
                        <!--Shop Product Start-->
                        <div class="shop-product">
                            <div id="myTabContent-2" class="tab-content">
                                <div id="grid" class="tab-pane fade active show">
                                    <div class="product-grid-view">
                                        <div class="row">


                                            <%
                                                List<Product> products = (List<Product>) request.getSession().getAttribute("productList");
                                                for (Product product
                                                        : products
                                                        ) {
                                            %>
                                            <div class="col-md-4">
                                                <!--Single Product Start-->
                                                <div class="single-product mb-25">
                                                    <div class="product-img img-full">
                                                        <a href="product?productID=<%=product.getId()%>">
                                                            <img src="<%=product.getPicPath().get(0)%>" alt="">
                                                        </a>
                                                        <span class="onsale">Sale!</span>
                                                        <div class="product-action">
                                                            <ul>
                                                                <%--<li><a href="#open-modal" data-toggle="modal"
                                                                       title="Quick view"><i
                                                                        class="fa fa-search"></i></a></li>--%>
                                                                <li><a href="#" title="Whishlist"><i
                                                                        class="fa fa-heart-o"></i></a></li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                    <div class="product-content">
                                                        <h2>
                                                            <a href="product?productID=<%=product.getId()%>"><%=product.getProductName()%>
                                                            </a></h2>
                                                        <div class="product-price">
                                                            <div class="price-box">
                                                                <span class="regular-price">¥<%=product.getPrice()%></span>
                                                            </div>
                                                            <div class="add-to-cart">
                                                                <a href="#">加入购物车</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!--Single Product End-->
                                            </div>
                                            <%
                                                }
                                            %>


                                        </div>
                                    </div>
                                </div>
                                <div id="list" class="tab-pane fade">
                                    <div class="product-list-view">
                                        <div class="product-list-item mb-40">
                                            <div class="row">
                                                <%
                                                    for (Product product
                                                            : products
                                                            ) {
                                                %>
                                                <!--Single List Product Start-->
                                                <div class="col-md-4">
                                                    <div class="single-product">
                                                        <div class="product-img img-full">
                                                            <a href="product?productID=<%=product.getId()%>">
                                                                <img src="<%=product.getPicPath().get(0)%>" alt="">
                                                            </a>
                                                            <span class="onsale">Sale!</span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-8">
                                                    <div class="product-content shop-list">
                                                        <h2><a href=""
                                                               product?productID=<%=product.getId()%>""><%=product.getProductName()%>
                                                        </a></h2>
                                                        <div class="product-reviews">
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star-o"></i>
                                                        </div>
                                                        <div class="product-description">
                                                            <p><%=product.getProIntro()%>
                                                            </p>
                                                        </div>
                                                        <div class="product-price">
                                                            <div class="price-box">
                                                                <span class="price">¥<%=product.getPrice()%></span>
                                                            </div>
                                                        </div>
                                                        <div class="product-list-action">
                                                            <div class="add-btn">
                                                                <a href="#">加入购物车</a>
                                                            </div>
                                                            <ul>
                                                                <li><a href="#open-modal" data-toggle="modal"
                                                                       title="Quick view"><i
                                                                        class="fa fa-search"></i></a></li>
                                                                <li><a href="#" title="Whishlist"><i
                                                                        class="fa fa-heart-o"></i></a></li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!--Single List Product End-->
                                                <%
                                                    }
                                                %>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!--商品页--button>
                                <div class="product-pagination">
                                    <ul>
                                        <li class="active"><a href="#">1</a></li>
                                        <li><a href="#">2</a></li>
                                        <li><a href="#">3</a></li>
                                        <li><a href="#">4</a></li>
                                        <li><a href="#">5</a></li>
                                        <li><a href="#"><i class="fa fa-angle-double-right"></i></a></li>
                                    </ul>
                                </div>
                                <!--页button End-->
                            </div>
                        </div>
                        <!--Shop Product End-->
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@include file="universal/footer.jsp" %>
    <%@ include file="universal/alljs.jsp" %>
</div>
</body>
</html>
