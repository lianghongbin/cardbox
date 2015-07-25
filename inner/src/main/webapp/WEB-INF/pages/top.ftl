<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>『豪情』后台管理</title>
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
    <script type="text/javascript" src="js/libs/modernizr.min.js"></script>
    <script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript">
        function quit(phone) {
            $.ajax({
                url: '/logout',// 跳转到 action
                data: {
                    phone : phone
                },
                type: 'get',
                dataType: 'text',
                success: function (data) {
                    if (data == "") {
                        alert("成功退出！");
                        window.parent.location = "/";
                    } else {
                        alert(data);
                    }
                },
                error: function () {
                    alert("异常！");
                }
            });
        }
    </script>
</head>
<body>
<div class="topbar-wrap white">
    <div class="topbar-inner clearfix">
        <div class="topbar-logo-wrap clearfix">
            <h1 class="topbar-logo none"><a href="/" class="navbar-brand">后台管理</a></h1>
            <ul class="navbar-list clearfix">
                <li><a class="on" href="/">首页</a></li>
                <li><a href="http://www.7k7k.com" target="_blank">网站首页</a></li>
            </ul>
        </div>
        <div class="top-info-wrap">
            <ul class="top-info-list clearfix">
                <li>管理员:${phone}</li>
                <li><a href="#" onclick="quit(${phone})">退出</a></li>
            </ul>
        </div>
    </div>
</div>
</body>
</html>