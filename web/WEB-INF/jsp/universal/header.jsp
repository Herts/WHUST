<%--
  Created by IntelliJ IDEA.
  User: huhaomeng
  Date: 2018/7/8
  Time: 下午4:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="whustore.model.User" %>
<!--网页头-->
<header>
    <div class="header-container">
        <div class="header-area header-absolute header-sticky pt-30 pb-30">
            <div class="container-fluid pl-50 pr-50">
                <div class="row">
                    <!--Header Logo Start-->
                    <div class="col-lg-3 col-md-3">
                        <div class="logo-area">
                            <a href="/home">
                                <img src="../../img/logo/logo.png" alt="">
                            </a>
                        </div>
                    </div>
                    <!--Header Logo End-->
                    <!--Header Menu And Mini Cart Start-->
                    <div class="col-lg-9 col-md-9 text-lg-right">
                        <!--Main Menu Area Start-->
                        <div class="header-menu">
                            <nav>
                                <ul class="main-menu">
                                    <li><a href="/home">首页</a></li>
                                    <li><a href="/shop">找一找</a> </li>
                                    <li><a href="/HangingAround">逛一逛</a></li>
                                    <li><a href="single-product.html">为我推荐</a></li>
                                </ul>
                            </nav>
                        </div>
                        <!--Main Menu Area End-->
                        <!--Header Option Start-->
                        <div class="header-option">
                            <div class="mini-cart-search">
                                <div class="mini-cart">
                                    <a href="#">
                                                <span class="cart-icon">
                                                   <span class="cart-quantity">2</span>
                                                </span>
                                        <span class="cart-title">  购物车 <br><strong>¥190.00</strong></span>
                                    </a>
                                    <!--Cart Dropdown Start-->
                                    <div class="cart-dropdown">
                                        <ul>
                                            <li class="single-cart-item">
                                                <div class="cart-img">
                                                    <a href="single-product.html"><img
                                                            src="../../img/cart/cart1.jpg" alt=""></a>
                                                </div>
                                                <div class="cart-content">
                                                    <h5 class="product-name"><a href="single-product.html">Odio
                                                        tortor consequat</a></h5>
                                                    <span class="cart-price">1 × ¥90.00</span>
                                                </div>
                                                <div class="cart-remove">
                                                    <a title="Remove" href="#"><i class="fa fa-times"></i></a>
                                                </div>
                                            </li>
                                            <li class="single-cart-item">
                                                <div class="cart-img">
                                                    <a href="single-product.html"><img
                                                            src="../../img/cart/cart2.jpg" alt=""></a>
                                                </div>
                                                <div class="cart-content">
                                                    <h5 class="product-name"><a href="single-product.html">Auctor
                                                        sem</a></h5>
                                                    <span class="cart-price">1 × ¥100.00</span>
                                                </div>
                                                <div class="cart-remove">
                                                    <a title="Remove" href="#"><i class="fa fa-times"></i></a>
                                                </div>
                                            </li>
                                        </ul>
                                        <p class="cart-subtotal"><strong>Subtotal:</strong> <span
                                                class="float-right">$190.00</span></p>
                                        <p class="cart-btn">
                                            <a href="#">购物车</a>
                                            <a href="#">结算</a>
                                        </p>
                                    </div>
                                    <!--Cart Dropdown End-->
                                </div>
                                <div class="header-search">
                                    <div class="search-box">
                                        <a href="#"><i class="fa fa-search"></i></a>
                                        <div class="search-dropdown">
                                            <form action="#">
                                                <input name="search" id="search" placeholder=""
                                                       value="Search product..."
                                                       onblur="if(this.value==''){this.value='Search product...'}"
                                                       onfocus="if(this.value=='Search product...'){this.value=''}"
                                                       type="text">
                                                <button type="submit"><i class="fa fa-search"></i></button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                <div class="currency">
                                    <div class="currency-box">
                                        <a href="#"><i class="fa fa-th"></i></a>
                                        <div class="currency-dropdown">
                                            <ul class="menu-top-menu">
                                                <%
                                                    User user = (User) session.getAttribute("user");
                                                    if (user != null)
                                                        out.println("<li><a href=\"/user/home\">" +
                                                                user.getUsername() +
                                                                "的账户</a></li>" +
                                                                "<li><a href=\"#\">历史订单</a></li>\n" +
                                                                "<li><a href=\"#\">购物车</a></li>\n" +
                                                                "<li><a href=\"#\">结算</a></li>" +
                                                                "<li><a href='/logOut'>注销</a></li>");
                                                    else
                                                        out.println("<li><a href=\"/log\">"+
                                                                "请登录" +
                                                                "</a></li>" +
                                                                "<li><a href='/reg'>注册</a></li>");
                                                %>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--Header Option End-->
                    </div>
                    <!--Header Menu And Mini Cart End-->
                </div>
                <div class="row">
                    <div class="col-12">
                        <!--Mobile Menu Area Start-->
                        <div class="mobile-menu d-lg-none"></div>
                        <!--Mobile Menu Area End-->
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>
<!--网页头end-->
