<%--
  Created by IntelliJ IDEA.
  User: 葛鹭
  Date: 2018/7/12
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<<!doctype html>
<html class="no-js" lang="en">
<head>
    <title>商品评价</title>
    <%@ include file="../universal/allcss.jsp" %>
    <script src="../../../js/jquery.js"></script>
    <style>
        #starRating .starRating span {
            position: relative;
            display: inline-block;
            width: 44px;
            height: 42px;
            overflow: hidden;
            margin-right: 23px;
            cursor: pointer;
        }
        #starRating .starRating span:last-child {
            margin-right: 0px;
        }
        #starRating .starRating span .nohigh {
            position: absolute;
            width: 44px;
            height: 42px;
            top: 0;
            left: 0;
            background: url("../../../img/starRating/star.png");
        }
        #starRating .starRating span .high {
            position: absolute;
            width: 44px;
            height: 42px;
            top: 0;
            left: 0;
            background: url("../../../img/starRating/star1.png");
        }
        #starRating .starNum {
            font-size: 26px;
            color: #de4414;
            margin-top: 4px;
            margin-bottom: 10px;
        }
        #starRating .bottoms {
            height: 54px;
            border-top: 1px solid #d8d8d8;
        }
        #starRating .starRating {
            margin-top: 30px;
        }
        #starRating .bottoms a {
            margin-bottom: 0;
        }
        #starRating .bottoms .garyBtn {
            margin-right: 57px!important;
        }
        #starRating .bottoms a {
            width: 130px;
            height: 35px;
            line-height: 35px;
            border-radius: 3px;
            display: inline-block;
            font-size: 16px;
            transition: all 0.2s linear;
            margin: 16px 0 22px;
            text-align: center;
            cursor: pointer;
        }

    </style>
</head>
<body>
<div class="wrapper">
    <%--头部引用--%>
    <%@include file="../universal/header.jsp" %>
        <div class="comment_area" style=" width:100%; height:auto;margin: 10% auto;">
            <div style="text-align: center">
                <h2>为此商品提供您宝贵的建议</h2>
            </div>
            <br/>

            <form action="/comments/add" onsubmit="submit_sure()" method="post">

                <div class="single-feature mb-35" style="width: 60%;margin: 0 auto;min-width: 500px">
                    <div class="p_photo" style=" width: 30%;float: left;margin-left: 5%">
                        <!--<input type="file" value="上传图片" style="margin-left:13%;height: 200px;width: 200px;">-->
                        <div id="result" style="height: 200px;width: 200px"><img src="${product.picPath.get(i)}"> </div>
                        <!-- <button onclick = "handIn()">提交</button> -->

                    </div>
                    <div class="customer_info" style="width:70%;float: left;">
                        <p>商品名称</p><br/>
                        <p>${product.productName}</p>
                        <p>商品简述</p><br/>
                        <p>${product.proIntro}</p>
                        <p>你的评价</p>
                        <div id="starRating">
                            <p class="starRating">
                                <span><i class="high"></i><i class="nohigh"></i></span>
                                <span><i class="high"></i><i class="nohigh"></i></span>
                                <span><i class="high"></i><i class="nohigh"></i></span>
                                <span><i class="high"></i><i class="nohigh"></i></span>
                                <span><i class="high"></i><i class="nohigh"></i></span>
                            </p>
                        </div>
                        <input type="hidden" value="1" id="rate" name="level">
                        <input type="hidden" name="idproduct" value="${product.id}">
                        <input type="hidden" name="clevel" value="${comment.clevel}">
                        <input name="title" type="text" value="${comment.ctitle}" placeholder="请填写评价标题">
                        <br/>
                        <textarea type="t" id="comment-content" value="" name="content" placeholder="请填写评价内容">${comment.ccontent}</textarea>

                        <br/><br/>
                        <button type="submit" class="form-button" value="comment_product" style="margin-left: 45%">提交评价</button>
                    </div>
                </div>
            </form>
        </div>
        <script type="text/javascript">
            function submit_sure()
            {
                var flag = confirm("您确定要提交评价吗？");
                if (flag == true) {
                    return true;
                } else {
                    return false;
                }
            }
            $(function () {
                //评分
                var starRating = 0;
                $('.starRating span').on('mouseenter',function () {
                    var index = $(this).index()+1;
                    $(this).prevAll().find('.high').css('z-index',1)
                    $(this).find('.high').css('z-index',1)
                    $(this).nextAll().find('.high').css('z-index',0)

                })
                $('.starRating').on('mouseleave',function () {
                    $(this).find('.high').css('z-index',0)
                    var count = starRating / 2
                    if(count == 5) {
                        $('.starRating span').find('.high').css('z-index',1);
                    } else {
                        $('.starRating span').eq(count).prevAll().find('.high').css('z-index',1);
                    }

                })
                $('.starRating span').on('click',function () {
                    var index = $(this).index()+1;
                    $(this).prevAll().find('.high').css('z-index',1)
                    $(this).find('.high').css('z-index',1)
                    starRating = index*2;
                    document.getElementById("rate").value = Number(starRating/2);
                })
                //取消评分
                //确定评分
                $('.sureStar').on('click',function () {
                    if(starRating===0) {
                        alert('请滑动评分');
                    }
                })
                return starRating;
            })
        </script>
            </div>
        </form>
    </div>
    <%@include file="../universal/footer.jsp" %>
    <%@ include file="../universal/alljs.jsp" %>
</div>
</body>
</html>

