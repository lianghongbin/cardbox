<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>后台管理</title>
    <link rel="stylesheet" type="text/css" href="../css/common.css"/>
    <link rel="stylesheet" type="text/css" href="../css/main.css"/>
    <script type="text/javascript" src="../js/libs/modernizr.min.js"></script>
    <script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript">
        function operate() {
            if($("#username").val() == "") {
                $("#username").focus();
                alert("用户名不能为空");
                return false;
            }
            if($("#phone").val() == "") {
                $("#phone").focus();
                alert("手机号不能为空");
                return false;
            }

            $.ajax({
                url: '/admin/save',// 跳转到 action
                data: $('#myform').serialize(),// 你的formid,
                type: 'post',
                dataType: 'text',
                success: function (data) {
                    if (data == "1") {
                        alert("添加成功");
                        window.location = "/admin/all";
                    } else {
                        alert("添加失败");
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

<!--/sidebar-->
<div class="main-wrap">

    <div class="crumb-wrap">
        <div class="crumb-list"><i class="icon-font"></i>首页<span class="crumb-step">&gt;</span><a class="crumb-name"
                                                                                                   href="./all">操作员管理</a><span
                class="crumb-step">&gt;</span><span>添加操作员</span></div>
    </div>
    <div class="result-wrap">
        <div class="result-content">
            <form id="myform" name="myform">
            <table class="insert-tab" width="100%">
                <tbody>
                <tr>
                    <th width="120">姓名：</th>
                    <td>
                        <input class="common-text required" id="username" name="username" size="50" type="text"/>
                    </td>
                </tr>
                <tr>
                    <th width="120">手机：</th>
                    <td>
                        <input class="common-text required" id="phone" maxlength="11" name="phone" size="50" type="text"/>
                    </td>
                </tr>
                <tr>
                    <th>超级管理员：</th>
                    <td>
                        <input type="radio" name="top" value="1">是 &nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="top" value="0" checked>否
                    </td>
                </tr>
                <tr>
                    <th>锁定：</th>
                    <td>
                        <input type="radio" name="locked" value="1">是 &nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="locked" value="0" checked>否
                    </td>
                </tr>
                <tr>
                    <th></th>
                    <td>
                        <input class="btn btn-primary btn6 mr10" id="uploadSubmit" value="添加" onclick="return operate()"
                               type="button">
                        <input class="btn btn6" onclick="history.go(-1)" value="返回" type="button">
                    </td>
                </tr>
                </tbody>
            </table>
            </form>
        </div>
    </div>

</div>
<!--/main-->
</body>
</html>