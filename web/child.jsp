<%--
Created by IntelliJ IDEA.
User: jb9
Date: 2018/4/18
Time: 19:00
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>子页测试</title>
    <script src="<%=path%>/static/js/jquery-2.2.4.min.js"></script>
    <script src="<%=path%>/static/js/win10.child.js"></script>
    <style>
        .btn{
            margin: 20px;
            width: 160px;
            height: 40px;
            line-height: 40px;
            font-size: 16px;
            color: white;
            background-color: #2D93CA;
            border-radius: 3px;
            transition: all 0.5s;
            cursor: pointer;
            text-align: center;
        }
        .btn:hover{
            background-color: #256d95;
        }
    </style>
    <script>
        function f1() {
            Win10_child.newMsg('子页的问候','这是从iframe页调用APi发送的消息哦');
        }
        function f2() {
            Win10_child.openUrl('http://win10ui.yuri2.cn','<img class=\'icon\' src=\'<%=path%>/static/img/icon/win10.png\'/>Win10-UI官网')
        }
        function f3() {
            Win10_child.close();
        }
    </script>
</head>
<body>
    <div class="btn" onclick="f1()">子页给父页发消息</div>
    <div class="btn" onclick="f2()">子页打开子窗口</div>
    <div class="btn" onclick="f3()">关闭子页</div>
</body>
</html>