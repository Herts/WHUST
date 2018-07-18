<%@ page import="java.util.List" %>
<%@ page import="whustore.model.Product" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: huhaomeng
  Date: 2018/7/10
  Time: 下午3:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html class="no-js" lang="en">
<head>
    <title>我与WHU STORE的故事</title>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta name="description" content="">
    <meta name="robots" content="noindex, follow"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="robots" content="noindex, follow"/>
    <%@ include file="universal/allcss.jsp" %>
    <script>
        function changeTimes(newNum) {
            document.getElementById("counter").innerHTML = newNum + "次";
        }
    </script>
    <script src="../../js/echarts.min.js"></script>
</head>


<!--[if lt IE 8]>
<p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade
    your browser</a> to improve your experience.</p>
<![endif]-->

<div class="wrapper">
    <!--Header Area Start-->
    <header>
        <div class="header-container">
            <div class="header-area header-sticky pt-30 pb-30">
                <div class="container-fluid pl-50 pr-50">
                    <%@include file="universal/headerContent.jsp" %>
                </div>
            </div>
        </div>
    </header>
    <!--Header Area End-->
    <%--页头图--%>
    <div class="breadcrumb-one mb-120" style="padding-top: 10%">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="breadcrumb-img">
                        <img src="../../img/page-banner/product-banner.jpg" alt="">
                    </div>
                    <div class="breadcrumb-content">
                        <ul>
                            <li><h3 style="color: #abd373">WHUStore与您的故事</h3></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%--页头图end--%>
    <%--我们的故事--%>
    <div class="our-history-area mt-85" style="padding-top: 10%">
        <div class="container">
            <div class="row">
                <!--Section Title Start-->
                <div class="col-12">
                    <div class="section-title text-center mb-35">
                        <h2>从相识到不相离</h2>
                        <span>公元2018年7月3日～</span>
                    </div>
                </div>
                <!--Section Title End-->
            </div>
            <div class="row">
                <div class="col-lg-8 ml-auto mr-auto">
                    <div class="history-area-content text-center">
                        <p><strong>从公元2018年7月3日与你相遇<br/>到2018年7月16号你第一次信任并选择我们<br/>再到如今</strong></p>
                        <p>也许你还没有意识到<br/>
                            你已经在WHU Store累计下单了${orderSize}次<br/>
                            购买商品${productNumber}件涉及2所大学<br/>总计¥${totalMoney}元<br/>
                            在我们的用户中排名前90%<br/>
                            支持了${teamSize}个文化创意团队的发展</p>
                    </div>

                    <div style="text-align: center">
                        <a href="/user/myStory?userId=???">
                            右键复制链接向他人分享您的用户故事
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%--我们的故事end--%>

    <%--购买最多的商品--%>
    <div class="also-like-product" style="padding-top: 15%">
        <div class="container">
            <div class="row">
                <!--Section Title Start-->
                <div class="col-12">
                    <div class="section-title text-center mb-35">
                        <h3>您的最爱</h3>
                        <span>购买数量最多的商品</span>
                    </div>
                </div>
                <div class="row" style="padding-left: 41%;padding-right: 41%; height: 100px;">
                    <!--Single Feature Start-->
                    <div class="single-feature mb-35">
                        <div class="feature-icon">
                            <span class="lnr lnr-select"></span>
                        </div>
                        <div class="feature-content" style="text-align: center">
                            <h3 id="counter"></h3>
                            <p>购买该商品</p>
                        </div>
                    </div>
                    <!--Single Feature End-->
                </div>
                <!--Section Title End-->
            </div>
            <div class="row" style="padding-top: 3%">
                <div class="product-slider-active slick-initialized slick-slider">
                    <div class="slick-list draggable">
                        <div class="slick-track"
                             style="padding-left: 10%;padding-right: 10%;opacity: 1; width: 1152px; transform: translate3d(0px, 0px, 0px);">
                            <c:forEach items="${favProducts}" var="product" varStatus="status">
                                <div class="col-md-3 col-lg-3 col-sm-4 col-xs-12 slick-slide slick-active"
                                     data-slick-index="2" aria-hidden="false" style="width: 240px;" tabindex="0"
                                     role="tabpanel" id="slick-slide02" aria-describedby="slick-slide-control02"
                                     onmouseover="changeTimes('${productNumMap.get(product)}')">
                                    <!--Single Product Start-->
                                    <div class="single-product mb-25">
                                        <div class="product-img img-full">
                                            <a href="/product?productID=${product.id}" tabindex="0">
                                                <img src="../../${product.picPath.get(0)}" alt="">
                                            </a>
                                            <div class="product-action">
                                                <ul>
                                                    <li><a onclick="addFav(${product.id})" title="收藏商品"><i
                                                            class="fa fa-heart-o"></i></a></li>
                                                    <li><a target="_blank"
                                                           href="http://wpa.qq.com/msgrd?v=3&uin=2392651490&site=qq&menu=yes"
                                                           title="联系客服">
                                                        <i class="fa fa-qq"></i>
                                                    </a></li>
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="product-content">
                                            <h2><a href="/product?productID=${product.id}"
                                                   tabindex="0">${product.productName}</a>
                                            </h2>
                                            <div class="product-price">
                                                <div class="price-box">
                                                    <span class="regular-price">¥${product.price}</span>
                                                </div>
                                                <div class="add-to-cart">
                                                    <a href="/cart/add?productID=${product.id}" tabindex="0">再续前缘</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!--Single Product End-->
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%--购买最多的商品End--%>

    <%--最喜爱的商品类别--%>
    <div class="categories-area mt-115" style="padding-top: 10%">
        <div class="container">
            <div class="row">
                <!--Section Title Start-->
                <div class="col-12">
                    <div class="section-title text-center mb-35">
                        <span><h3>您最爱的</h3></span>
                        <h5>商品分类</h5>
                    </div>
                </div>
                <!--Section Title End-->
            </div>
        </div>
        <div class="container-fluid pl-50 pr-50" style="width: 80%">
            <div class="row">
                <div class="cat-1 col-md-4">
                    <div class="categories-img img-full mb-30">
                        <a href="/shop/byCates?categories=${favCates.get(0)}"><img
                                src="../../img/category/home1-category-1.jpg" alt=""></a>
                        <div class="categories-content">
                            <h3>${favCates.get(0)}</h3>
                            <h4>${cateNumMap.get(favCates.get(0))}次购买</h4>
                        </div>
                    </div>
                </div>
                <div class="cat-2 col-md-8">
                    <div class="row">
                        <div class="cat-3 col-md-7">
                            <div class="categories-img img-full mb-30">
                                <a href="#"><img src="../../img/category/home1-category-2.jpg" alt=""></a>
                                <div class="categories-content">
                                    <h3>${favCates.get(1)}</h3>
                                    <h4>${cateNumMap.get(favCates.get(1))}次购买</h4>
                                </div>
                            </div>
                        </div>
                        <div class="cat-4 col-md-5">
                            <div class="categories-img img-full mb-30">
                                <a href="#"><img src="../../img/category/home1-category-3.jpg" alt=""></a>
                                <div class="categories-content">
                                    <h3>Potted Plant</h3>
                                    <h4>18 items</h4>
                                </div>
                            </div>
                        </div>
                        <div class="cat-5 col-md-4">
                            <div class="categories-img img-full mb-30">
                                <a href="#"><img src="../../img/category/home1-category-4.jpg" alt=""></a>
                                <div class="categories-content">
                                    <h3>Potted Plant</h3>
                                    <h4>18 items</h4>
                                </div>
                            </div>
                        </div>
                        <div class="cat-6 col-md-8">
                            <div class="categories-img img-full mb-30">
                                <a href="#"><img src="../../img/category/home1-category-5.jpg" alt=""></a>
                                <div class="categories-content">
                                    <h3>Potted Plant</h3>
                                    <h4>18 items</h4>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%--最爱商品end--%>
    <%--数据展示--%>
    <div class="our-history-area mt-85" style="padding-top: 10%">
        <div class="container">
            <div class="row">
                <!--Section Title Start-->
                <div class="col-12">
                    <div class="section-title text-center mb-35">
                        <h2>从数据看我们的故事</h2>
                        <span>购买记录可视化分析</span>
                    </div>
                </div>
                <!--Section Title End-->
                <div class="row">
                    <div class="col-lg-8 ml-auto mr-auto">
                        <div class="history-area-content text-center">
                            <%--商品分类表--%>
                            <div id="cates" style="padding-left: 10%;padding-right: 10%;width: 890px;height: 600px">
                                <script>
                                    var myChart = echarts.init(document.getElementById('cates'));

                                    // 指定图表的配置项和数据
                                    var option = {
                                        backgroundColor: '#ccc',

                                        title: {
                                            text: '各分类商品数量图'
                                        },

                                        tooltip: {
                                            trigger: 'item',
                                            formatter: "{a} <br/>{b} : {c} ({d}%)"
                                        },

                                        visualMap: {
                                            show: false,
                                            min: 0,
                                            max: ${maxSingleCateNums*1.5},
                                            inRange: {
                                                colorLightness: [0, 1]
                                            }
                                        },
                                        series: [
                                            {
                                                name: '各分类商品数量图',
                                                type: 'pie',
                                                radius: '55%',
                                                center: ['50%', '50%'],
                                                data: [
                                                    <c:forEach items="${favCates}" var="cate">
                                                    {value: ${cateNumMap.get(cate)}, name: '${cate}'},
                                                    </c:forEach>
                                                    {value: ${otehrCateSize}, name: '其他分类'}
                                                ].sort(function (a, b) {
                                                    return a.value - b.value;
                                                }),
                                                roseType: 'radius',
                                                label: {
                                                    normal: {
                                                        textStyle: {
                                                            color: 'rgba(255, 255, 255, 0.3)'
                                                        }
                                                    }
                                                },
                                                labelLine: {
                                                    normal: {
                                                        lineStyle: {
                                                            color: 'rgba(255, 255, 255, 0.3)'
                                                        },
                                                        smooth: 0.2,
                                                        length: 10,
                                                        length2: 20
                                                    }
                                                },
                                                itemStyle: {
                                                    normal: {
                                                        color: '#abd373',
                                                        shadowBlur: 200,
                                                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                                                    }
                                                },

                                                animationType: 'scale',
                                                animationEasing: 'elasticOut',
                                                animationDelay: function (idx) {
                                                    return Math.random() * 200;
                                                }
                                            }
                                        ]
                                    };

                                    // 使用刚指定的配置项和数据显示图表。
                                    myChart.setOption(option);
                                </script>
                            </div>
                            <div id="totalGrow" style="padding-left: 10%;padding-right: 10%;width: 890px;height: 600px">
                                <script>
                                    var myChart = echarts.init(document.getElementById('totalGrow'));

                                    // 指定图表的配置项和数据
                                    var option = {
                                        xAxis: {
                                            type: 'category',
                                            data: [
                                                <c:forEach items="${totalGrow.keySet()}" var="date">
                                                '${date}',
                                                </c:forEach>]
                                        },
                                        title: {
                                            text: '消费数额增长图'
                                        },
                                        yAxis: {
                                            type: 'value'
                                        },
                                        series: [{
                                            data: [
                                                <c:forEach items="${totalGrow.keySet()}" var="date">
                                                ${totalGrow.get(date)},
                                                </c:forEach>
                                            ],
                                            type: 'line',
                                            smooth: true
                                        }]
                                    };

                                    // 使用刚指定的配置项和数据显示图表。
                                    myChart.setOption(option);
                                </script>
                            </div>
                            <div id="orderGrow" style="padding-left: 10%;padding-right: 10%;width: 890px;height: 600px">
                                <script>
                                    var myChart = echarts.init(document.getElementById('orderGrow'));

                                    var option = {
                                        title: {
                                            text: '购买商品数&订单数 增长图'
                                        },
                                        tooltip: {
                                            trigger: 'axis'
                                        },
                                        legend: {
                                            data: ['订单数', '商品数']
                                        },
                                        grid: {
                                            left: '3%',
                                            right: '4%',
                                            bottom: '3%',
                                            containLabel: true
                                        },
                                        toolbox: {
                                            feature: {
                                                saveAsImage: {}
                                            }
                                        },
                                        xAxis: {
                                            type: 'category',
                                            boundaryGap: false,
                                            data: [
                                                <c:forEach items="${orderGrow.keySet()}" var="date">
                                                '${date}',
                                                </c:forEach>
                                            ]
                                        },
                                        yAxis: {
                                            type: 'value'
                                        },
                                        series: [
                                            {
                                                name: '订单数',
                                                type: 'line',
                                                smooth: true,
                                                data: [
                                                    <c:forEach items="${orderGrow.keySet()}" var="date">
                                                    ${orderGrow.get(date)[0]},
                                                    </c:forEach>
                                                ]
                                            },
                                            {
                                                name: '商品数',
                                                type: 'line',
                                                smooth: true,
                                                data: [
                                                    <c:forEach items="${orderGrow.keySet()}" var="date">
                                                    ${orderGrow.get(date)[1]},
                                                    </c:forEach>
                                                ]
                                            }
                                        ]
                                    };


                                    myChart.setOption(option);
                                </script>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <%--数据展示end--%>
        <!--Footer Area Start-->
        <%@include file="universal/footer.jsp" %>
        <!--Footer Area End-->
    </div>
</div>
<%@include file="universal/alljs.jsp" %>
</body>
</html>

