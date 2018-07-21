<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: huhaomeng
  Date: 2018/7/11
  Time: 上午10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                    <li><a href="/shop">找一找</a></li>
                    <li><a href="/HangingAround">逛一逛</a></li>
                    <li><a href="/user/recommendForMe">为我推荐</a></li>
                </ul>
            </nav>
        </div>
        <!--Main Menu Area End-->
        <!--Header Option Start-->
        <div class="header-option">
            <div class="mini-cart-search">
                <div class="mini-cart">
                    <a href="/cart/myCart">
                        <span class="cart-icon">
                            <span class="cart-quantity">${sessionScope.cart.items.keySet().size()}</span>
                        </span>
                        <span class="cart-title">  购物车 <br><strong>¥${sessionScope.cart.getTotal()}</strong></span>
                    </a>
                    <!--Cart Dropdown Start-->
                    <div class="cart-dropdown">
                        <ul>
                            <c:if test="${sessionScope.cart!=null}">
                                <c:forEach items="${sessionScope.cart.items.keySet()}" var="product"
                                           varStatus="status" end="3">
                                    <li class="single-cart-item">
                                        <div class="cart-img">
                                            <a href="/product?productID=${product.id}"><img
                                                    src="../../${product.picPath.get(0)}"
                                                    alt=""></a>
                                        </div>
                                        <div class="cart-content">
                                            <h5 class="product-name"><a
                                                    href="/product?productID=${product.id}">
                                                    ${product.productName}
                                            </a></h5>
                                            <span class="cart-price">¥${product.price}</span>
                                        </div>
                                        <div class="cart-remove">
                                            <a title="Remove" onclick="remove(${product.id})"><i
                                                    class="fa fa-times"></i></a>
                                        </div>
                                    </li>
                                </c:forEach>
                                <c:if test="${sessionScope.cart.items.keySet().size() > 4}">
                                    <h3 style="text-align: center">。。。</h3>
                                </c:if>
                            </c:if>
                        </ul>
                        <p class="cart-subtotal"><strong>合计:</strong> <span
                                class="float-right">¥${sessionScope.cart.getTotal()}</span></p>
                        <p class="cart-btn">
                            <a href="/cart/myCart">购物车</a>
                            <a href="/addorder">一键下单</a>
                        </p>
                    </div>
                    <!--Cart Dropdown End-->
                </div>
                <div class="header-search">
                    <div class="search-box">
                        <a href="#"><i class="fa fa-search"></i></a>
                        <div class="search-dropdown">
                            <form action="/shop/search">
                                <input name="searching" id="search" placeholder="在此处输入搜索信息"
                                       onblur="if(this.value==''){this.value='在此处输入搜索信息'}"
                                       onfocus="if(this.value=='在此处输入搜索信息'){this.value=''}"
                                       type="text">
                                <button type="submit"><i class="fa fa-search"></i></button>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="currency">
                    <div class="currency-box">
                        <a href="#"><i class="fa fa-user"></i></a>
                        <div class="currency-dropdown">
                            <ul class="menu-top-menu">
                                <c:if test="${sessionScope.idteam != null}">
                                    <li><a href="/gototeamAccount">团队账户</a></li>
                                </c:if>
                                <c:if test="${sessionScope.user.getUserid() == 0}">
                                    <li><a href="/administrator">管理员</a></li>
                                </c:if>
                                <c:choose>
                                    <c:when test="${sessionScope.user != null}">
                                        <li><a href="/user/home">${user.getUsername()}的账户</a></li>
                                        <li><a href="/order/myOrders">历史订单</a></li>
                                        <li><a href="/fav/my">我的收藏</a></li>
                                        <li><a href="/cart/myCart">购物车</a></li>
                                        <li><a href='/logOut'>注销</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li><a href="/log">请登录</a></li>
                                        <li><a href='/reg'>注册</a></li>
                                    </c:otherwise>
                                </c:choose>
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
