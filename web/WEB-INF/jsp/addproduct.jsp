<%--
  Created by IntelliJ IDEA.
  User: lsq_math
  Date: 2018/7/11
  Time: 11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Home</title>
    <meta name="description" content="">
    <meta name="robots" content="noindex, follow"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="robots" content="noindex, follow"/>
    <%@include file="universal/allcss.jsp"%>
    <style type="text/css">
        .p_tag {
            height: 20px;
            width: 20px;
        }

        #result {
            width: 200px;
            height: 112px;
            border: 1px solid #eee;
        }

        #result img {
            width: 200px;
        }

        .pic_class {
            width: 80px;
            border: none;
            margin: 10% 15%;
        }
    </style>
</head>
<body>


<!--[if lt IE 8]>
<p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade
    your browser</a> to improve your experience.</p>
<![endif]-->

<!--//
TODO
-->
<div class="wrapper">
    <!--网页头-->
    <%--头部引用开始--%>
    <header>
        <div class="header-container">
            <div class="header-area header-absolute header-sticky pt-30 pb-30">
                <div class="container-fluid pl-50 pr-50">
                    <%@include file="universal/headerContent.jsp" %>
                </div>
            </div>
        </div>
    </header>
    <!--Header Area End-->
    <!--网页头end-->

    <!--adding product area-->

    <!--上面还有一个style-->

    <div class="add_area" style=" width:100%; height:auto;margin: 10% auto;">
        <div style="text-align: center">
            <h2>请添加一个商品</h2>
        </div>
        <br/>

        <form action="/addproducts" method="post" enctype="multipart/form-data">

            <div class="single-feature mb-35" style="width: 60%;margin: 0 auto;min-width: 500px">
                <div class="p_photo" style=" width: 30%;float: left;margin-left: 5%">
                    <!--<input type="file" value="上传图片" style="margin-left:13%;height: 200px;width: 200px;">-->
                    <div id="result" style="height: 200px;width: 200px"></div>
                    <input id="pic" class="pic_class" type="file" name="pics" accept="image/*" onchange="selectFile()" multiple/>
                    <!-- <button onclick = "handIn()">提交</button> -->
                </div>
                <div class="customer_info" style="width:70%;float: left;">
                    <p>商品名称</p>
                    <input type="text" id="product_name" name="productName" value="" maxlength="25">
                    <p>商品数量</p>
                    <input type="number" id="product_quantity" name="quantity" value="" maxlength="25">
                    <p>商品价格</p>
                    <input type="number" id="product_price" name="price">
                    <p>商品分类标签</p>
                    <!--根据需求增减-->
                    <%--<input type="checkbox" name="type" class="p_tag" value="" hidden="hidden" checked="checked">--%>
                    <input type="checkbox" name="type" class="p_tag" value="上衣" >上衣
                    <input type="checkbox" name="type" class="p_tag" value="下装" >下装
                    <input type="checkbox" name="type" class="p_tag" value="配件" >配件
                    <input type="checkbox" name="type" class="p_tag" value="虚拟" >虚拟
                    <input type="checkbox" name="type" class="p_tag" value="纸品" >纸品
                    <input type="checkbox" name="type" class="p_tag" value="宿舍" >宿舍
                    <input type="checkbox" name="type" class="p_tag" value="回忆" >回忆
                    <input type="checkbox" name="type" class="p_tag" value="纪念" >纪念<br>
                    <input type="checkbox" name="type" class="p_tag" value="独创" >独创
                    <input type="checkbox" name="type" class="p_tag" value="其他" >其他
                    <p>商品简述</p>
                    <textarea id="product_description" name="proIntro"></textarea>
                    <!--<input type="hidden" value="">-->
                    <br/><br/>
                    <button style="margin-left: 70%" type="submit" value="添加商品" class="form-button">添加商品</button>
                </div>

            </div>
            <script type="text/javascript">//上传图片
            //var files = document.getElementById('pic').files;
            var form = new FormData();//通过HTML表单创建FormData对象
            var url = '127.0.0.1:8080/'

            function selectFile() {
                var files = document.getElementById('pic').files
                console.log(files[0]);
                if (files.length == 0) {
                    return;
                }
                var file = files[0];
                //把上传的图片显示出来
                var reader = new FileReader();
                // 将文件以Data URL形式进行读入页面
                console.log(reader);
                reader.readAsBinaryString(file);
                reader.onload = function (f) {
                    var result = document.getElementById("result");
                    var src = "data:" + file.type + ";base64," + window.btoa(this.result);
                    result.innerHTML = '<img src ="' + src + '"/>';
                }
                console.log('file', file);
                form.append('file', file);
                console.log(form.get('file'));
            }


            </script>
        </form>
    </div>


    <!--adding product area end-->


    <!--网页尾部-->
    <%@include file="universal/footer.jsp" %>
    <!--网页尾部End-->

</div>

<%@include file="universal/alljs.jsp"%>
</body>
</html>
