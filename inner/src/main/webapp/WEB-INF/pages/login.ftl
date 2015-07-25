<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>后台管理</title>
    <link href="/css/admin_login.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="/css/main.css"/>
    <script type="text/javascript" src="/js/libs/modernizr.min.js"></script>
    <script type="text/javascript" src="/js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript">
        var wait=60;
        function time(o) {
            if (wait == 0) {
                o.removeAttribute("disabled");
                o.value="免费获取验证码";
                wait = 60;
            } else {
                o.setAttribute("disabled", true);
                o.value="重新发送(" + wait + ")";
                wait--;
                setTimeout(function() {
                            time(o)
                        },
                        1000)
            }
        }

        function send(o) {
            if($("#phone").val() == "") {
                alert("请输入登录手机号！");
                $("#phone").focus();
                return false;
            }

            $.ajax({
                url: '/checkcode',// 跳转到 action
                data: {
                    phone : $("#phone").val()
                },
                type: 'get',
                dataType: 'text',
                success: function (data) {
                    if (data == "") {
                        alert("验证码发送成功");
                        time(o);
                    } else {
                        alert(data);
                    }
                },
                error: function () {
                    alert("异常！");
                }
            });
        }

        function operate() {
            $.ajax({
                url: '/login/check',// 跳转到 action
                data: $('#myform').serialize(),// 你的formid,
                type: 'post',
                dataType: 'text',
                success: function (data) {
                    if (data == "") {
                        window.location = "/";
                    } else {
                        alert(data);
                        return false;
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
<div class="admin_login_wrap">
    <h1 align="center">后台登录</h1>
    <div class="adming_login_border">
        <div class="admin_input">
            <form id="myform" name="myform">
                <ul class="admin_items">
                    <li>
                        <label for="phone">用户名：</label>
                        <input type="text" name="phone" id="phone" size="40" class="admin_input_style" />
                    </li>
                    <li>
                        <input type="button" id="checkCodeBtn" value="发送验证码" class="btn" onclick="send(this)"/>
                    </li>
                    <li>
                        <label for="checkCode">验证码：</label>
                        <input type="text" name="checkCode" id="checkCode" size="40" class="admin_input_style" />
                    </li>
                    <li>
                        <input type="button" tabindex="3" value="登录" class="btn btn-primary" onclick="return operate()"/>
                    </li>
                </ul>
            </form>
        </div>
    </div>
</div>
</body>
</html>