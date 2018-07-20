<%--
  Created by IntelliJ IDEA.
  User: 葛鹭
  Date: 2018/7/20
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="whustore.model.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>隐私政策</title>
    <%@ include file="../universal/allcss.jsp" %>
    <style type="text/css">
        .blackcolor {
            color: black;
        }

        .title {
            text-align: center;
            position: relative;
            font-weight: normal;
            margin-bottom: 30px;
            color: #abd373;
        }

        .title:after, .title:before {
            content: '';
            position: absolute;
            top: 50%;
            width: 45%;
            background: #abd373;
            height: 1px;
        }

        .title:before {
            left: 0;
        }

        .title:after {
            right: 0;
        }

        .tab thead {
            background-color: black;
            color: white;
            vertical-align: center;
        }

        .tab thead th {
            padding: 10px 0;
            font-weight: normal;
            vertical-align: center;
        }

        .tab td {
            text-align: center;
            padding: 10px 0;
            vertical-align: center;
        }

        .goods {
            width: 40%;
            position: relative;
            vertical-align: center;
        }

        .goods-left {
            width: 20%;
            float: left;
            vertical-align: center;
        }

        .goods-right {
            width: 78%;
            float: right;
            text-align: left;
            vertical-align: center;
        }

        .goods-right .tip {
            color: #999;
            position: absolute;
            bottom: 10px;
            font-size: 12px;
            vertical-align: center;
        }

        .num input {
            width: 50px;
            text-align: center;
            vertical-align: center;
        }

        .num a {
            color: #333;
            text-decoration: none;
            vertical-align: center;
        }

        .del {
            cursor: pointer;
            vertical-align: center;
        }

        .footer td {
            border-top: 1px solid #abd373;
            vertical-align: center;
        }

        .footer td:nth-child(2) {
            text-align: left;
            vertical-align: center;
        }

        .footer td:nth-child(3) {
            text-align: right;
            vertical-align: center;
        }

        .chbsty {
            height: 15px;
            width: 15px;
        }
    </style>
</head>
<body>
<div class="wrapper">
    <%--头部引用开始--%>
    <header>
        <div class="header-container">
            <div class="header-area header-absolute header-sticky pt-30 pb-30">
                <div class="container-fluid pl-50 pr-50">
                    <%@include file="../universal/headerContent.jsp" %>
                </div>
            </div>
        </div>
    </header>
    <!--Header Area End-->
        <div class="privacy-container" style="width: 80%;margin:10% auto">
            <div class="privacy-title">
                <h1 style="text-align: center">网站隐私条款</h1>
            </div>
            <br/>
            <div class="privacy-content">
                <h3><B>承诺</B></h3>
                <p style="font-size:large;line-height:40px">
                    我们致力于保护您在使用我们网站时所提供的个人资料, 使我们在收集、使用、储存和传送个人资料方面符合 与个人资料私隐有关的法律法规及消费者保护方面的最高标准。<br/>
                    为确保您对我们在处理个人资料上有充分信心, 您切要详细阅读及理解隐私政策的条文。特别是您一旦使用我们的网站, 将被视为接受、同意、承诺和确认:<br/>
                    您在自愿下连同所需的同意向我们披露个人资料;<br/>
                    您会遵守本隐私政策的全部条款和限制;<br/>
                    您在我们的网站上作登记、资料会被收集;<br/>
                    您同意日后我们对隐私政策的任何修改;<br/>
                    您同意我们的分公司、附属公司、雇员、就您可能会感兴趣的产品和服务与您联络( 除非您已经表示不想收到该等讯息 )。
                </p>
                <ul>>
                    <li><B>被收集的个人资料的种类</B><br/>
                        经您同意, 我们会收集、管理和监控个人资料。为了向您提供我们的各项服务, 您需要供給那些我们认为为达到你的指示和进一步改善我们的服务所需的个人资料和不具名的资料, 包括但不限于:<br/>
                        您的姓名、性别、年龄、出生日期、、电话号码、传真号码、住址或通讯地址、电子邮箱地址。<br/>
                        不具名的资料.</li>
                    <br/>
                    <li><B>收集个人资料及不具名的资料的目的及用途</B><br/>
                        通过我们的网站向您提供我们的各项服务；<br/>
                        当您使用我们的网站时, 能辨认及确认您的身份；<br/>
                        让您使用我们的网站时得到为您而设的感受；<br/>
                        我们的顾客服务人员有需要时可以与您联系；<br/>
                        统计我们网站使用量的数据；<br/>
                        令您使用我们网站时更方便；<br/>
                        为改进我们的产品、服务及网站内容而进行市场研究调查；<br/>
                        为我们搞的活动、市场销售和推广计划收集资料；<br/>
                        遵守法律、政府和监管机关的规定, 包括但不限于对个人资料披露及通知的规定;；<br/>
                        让我们及可能处于您住的国家之外的我们的分公司、附属公司、关联公司、雇员、代理人、服务伙伴或其它跟我们合作的第三者进行产品及/或服务的推广；<br/>
                        就我们提供的各项服务, 分析、核对及/或审查您的信用、付款及/或地位；<br/>
                        处理在您要求下的任何付款指示, 直接扣帐及/或信用安排；<br/>
                        使您能运作您的账户及/或使我们能从账户支取尚欠的服务费。</li>
                    <br/>
                    <li><B>个人资料的保护</B><br/>
                        我们实施妥适的实物、电子、管理及技术方面的措施来保护和保障您的个人资料的安全。我们采取的安全措施包括但不限于:<br/>
                        实物措施: 存有您个人资料的记录会被存放在有锁的地方。<br/>
                        电子措施: 存有您个人资料的电脑数据会被存放在受严格登入限制的电脑系统和存储媒体上。<br/>
                        管理措施: 只有经我们授权的职员才能接触到您的个人资料, 这些职员需要遵守我们个人资料保密的内部守则。<br/>
                        技术措施: 可能采用如Secure Socket Layer Encryption这种加密技术来输送您的个人资料。<br/>
                        其它措施: 我们的网络服务器受到"防火墙"的保护。<br/>
                        若您知悉我们的网站上有任何安全方面的漏洞, 请不要遟疑去联絡我们, 使我们可以尽快采取妥适的行动。</li>
                    <br/>
                    <li><B>未成年人</B><br/>
                        若任何家长或监护人相信有未成年人在未经家长或监护人批准或同意下向我们提供了个人资料, 请随便联系我们的客户服务部, 以确保该资料被除去, 并从我们的促销名单中撤下。</li>
                    <br/>
                    <li><B>您有权</B><br/>
                        查询我们是否持有您的任何个人资料;<br/>
                        接达我们所持有的您的个人资料;<br/>
                        要求我们更正任何不正确的个人资料;<br/>
                        不时地征询有关我们所持有的个人资料的性质, 政策和执行方法。<br/>
                        然而在法律允许的极端有限的情况下, 我们可以不允许您接达您的个人资料, 例如:<br/>
                        如您接达及得到您个人资料可能会对您有危险;<br/>
                        当您的个人资料可能会影响一项正在进行的调查;<br/>
                        当您的个人资料涉及到法庭程序, 并且可能受到发现的限制。<br/>
                        当您的个人资料涉及一项商业上敏感的决策过程;<br/>
                        当另外一個人的个人资料也包含在同一份记录中。<br/>
                        若您欲接达或更正个人资料, 或索取有关个人资料的政策、执行方法和被持有的个人资料的种类, 应致函到我们的下列的地址。 要求接达或更正资料可能要付合理的处理费用。</li>
                </ul>
            </div>
        </div>
    <%@include file="../universal/footer.jsp" %>

</div>

<%@ include file="../universal/alljs.jsp" %>
</body>
</html>
