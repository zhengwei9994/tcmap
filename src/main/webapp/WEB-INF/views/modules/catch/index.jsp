<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
    <%--<title>铜川网络空间晴朗一张图</title>--%>
    <title>城市运行中心</title>
    <meta name="decorator" content="blank"/>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="apple-touch-fullscreen" content="yes">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">

    <script src="${ctxStatic}/modules/catch/js/phone.js"></script>
    <link rel="stylesheet" href="${ctxStatic}/modules/catch/css/common.css">
    <style>
        article {
            min-width: 320px;
            margin: auto;
            position: absolute;
            top: 0;
            left: 0;
            width: 100vw;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            background-image: url("${ctxStatic}/modules/catch/img/bg-dl.jpg");
            background-size: cover;
            animation: bg .4s;
        }

        form {
            max-width: 640px;
            width: 80%;
            font-size: 3rem;
        }

        li {
            position: relative;
            margin: 1em 0;
        }

        li:first-child img {
            width: 80%;
            display: block;
            margin: 0 auto 2em;
        }

        li:nth-child(2) img, li:nth-child(3) img, li:nth-child(4) img:first-child {
            width: 1.4em;
            position: absolute;
            top: .4em;
            left: .8em;
        }

        input, button {
            width: 100%;
            height: 2.2em;
            line-height: 2.2;
            color: #fff;
            background: rgba(255, 255, 255, 0.4);
            border-radius: 4px;
        }

        li:nth-child(4) input {
            width: 60%;
        }

        li:nth-child(4) img:last-child {
            width: 36%;
            height: 2.2em;
            margin-left: 4%;
            border-radius: 4px;
        }

        input {
            padding-left: 3em;
            vertical-align: middle;
        }

        button {
            background: #00a7de;
        }

        p {
            text-align: center;
            color: #d8d8d8;
            position: absolute;
            right: 0;
            left: 0;
            bottom: 1em;
        }

        ::-webkit-input-placeholder { /* WebKit browsers */
            color: #fff;
        }

        :-moz-placeholder { /* Mozilla Firefox 4 to 18 */
            color: #fff;
        }

        ::-moz-placeholder { /* Mozilla Firefox 19+ */
            color: #fff;
        }

        :-ms-input-placeholder { /* Internet Explorer 10+ */
            color: #fff;
        }

        @media (max-width: 640px) {
            article {
                background-position: -440px 0;
            }
        }

        @media (max-height: 440px) {
            ul {
                font-size: 1.6rem;
            }

            p {
                display: none;
            }
        }

        @keyframes bg {
            0% {
                transform: scale(2);
            }
            100% {
                transform: scale(1);
            }
        }
    </style>
</head>
<body id="homepage" class="homepage">
<input hidden="hidden" id="ctxstatic" value="${ctxStatic}">
<div class="loader">
    <div class="loader-inner ball-pulse">
        <div></div>
        <div></div>
        <div></div>
    </div>
</div>

<article>
    <form method="post" id="loginForm1" action="${ctx}/login">
        <ul>
            <li><%--<img src="${ctxStatic}/modules/catch/img/ico-dl.png" alt="">--%><p  color="white" align="center" style="font-size: 48px">城市运营指挥中心管理系统</p></li>
            <li><img src="${ctxStatic}/modules/catch/img/ico0-dl.png" alt=""><input id="username" name="username" value="thinkgem"
                                                                                    placeholder="账号" type="text"/></li>
            <li><img src="${ctxStatic}/modules/catch/img/ico1-dl.png" alt=""><input id="password" name="password"
                                                                                    placeholder="密码" type="password" value="admin" autocomplete="off"/>
            </li>
            <li><input type="hidden" name="mobileLogin" value="false"></li>

            <c:if test="${isValidateCodeLogin}">
                <li class="validateCode">
                    <label class="input-label mid" for="validateCode">验证码</label>
                    <sys:validateCode name="validateCode"  inputCssStyle="margin-bottom:0;width:20%;"/>
                </li>
            </c:if>
            <li style="color:#fff">${message}</li>
            <li>
                <button onclick="fnSubmit()">登 录</button>
            </li>
        </ul>
    </form>
    <%--<p>主办单位：中共咸阳市渭城区委<br>咸阳市渭城区人民政府</p>--%>
    <div id="message" class="message"></div>
</article>


<script src="${ctxStatic}/modules/catch/js/jquery-3.1.1.min.js"></script>
<script src="${ctxStatic}/modules/catch/js/common.js"></script>
<script>

    var messagetime = null;

    function fnMessage(txt) {
        clearTimeout(messagetime);
        $('#message').text(txt).addClass('ons');
        messagetime = setTimeout(function () {
            $('#message').text(txt).removeClass('ons');
        }, 3000);
    }

    function fnSubmit() {
        var username = $('#username').val(), password = $('#password').val();
        if (!username) {
            fnMessage('请填写帐号！');
        } else if (!password) {
            fnMessage('请填写密码！');
        }
    }

    $(function () {
        $('link[href$="bootstrap.min.css"]').remove();
    })
</script>
</body>
</html>
