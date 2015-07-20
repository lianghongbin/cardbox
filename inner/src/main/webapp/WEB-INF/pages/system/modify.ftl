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
            $.ajax({
                url: '/system/update',// 跳转到 action
                data: $('#myform').serialize(),// 你的formid,
                type: 'post',
                dataType: 'text',
                success: function (data) {
                    if (data == "1") {
                        alert("修改成功");
                        window.location = "/system/all";
                    } else {
                        alert("修改失败");
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
                                                                                                   href="./all">系统配置管理</a><span
                class="crumb-step">&gt;</span><span>系统配置添加</span></div>
    </div>
    <div class="result-wrap">
        <div class="result-content">
            <form id="myform" name="myform" ">
            <table class="insert-tab" width="100%">
                <tbody>
                <tr>
                    <th width="120">版本：</th>
                    <td><input name="id" id="id" value="${setting.id}" type="hidden">
                        <input class="common-text required" id="v" name="v" value="${setting.v}" readonly size="50" type="text"/>
                    </td>
                </tr>
                <tr>
                    <th>每天登录得分：</th>
                    <td>
                        <input class="common-text required" id="daily" name="daily" value="${setting.daily}" size="50" type="text"/>
                    </td>
                </tr>
                <tr>
                    <th>注册得分：</th>
                    <td>
                        <input class="common-text required" id="registry" name="registry" value="${setting.registry}" size="50" type="text"/>
                    </td>
                </tr>
                <tr>
                    <th>微信分享得分：</th>
                    <td>
                        <input class="common-text required" id="weixin" name="weixin" size="50" value="${setting.weixin}" type="text"/>
                    </td>
                </tr>
                <tr>
                    <th>QQ分享得分：</th>
                    <td>
                        <input class="common-text required" id="qq" name="qq" value="${setting.qq}" size="50" type="text"/>
                    </td>
                </tr>
                <tr>
                    <th>下载游戏得分：</th>
                    <td>
                        <input class="common-text required" id="download" name="download" value="${setting.download}" size="50" type="text"/>
                    </td>
                </tr>
                <tr>
                    <th>android下载地址：</th>
                    <td>
                        <input class="common-text required" id="android" name="android" value="${setting.android}" size="50" type="text"/>
                    </td>
                </tr>
                <tr>
                    <th>iOS下载ID</th>
                    <td>
                        <input class="common-text required" id="ios" value="100" name="ios" value="${setting.ios}" size="50" type="text"/>
                    </td>
                </tr>
                <tr>
                    <th>关于我们：</th>
                    <td><textarea name="us" class="common-textarea" id="us" cols="20"
                                  style="width: 98%;" rows="5">${setting.us}</textarea></td>
                </tr>
                <tr>
                    <th>启动公告：</th>
                    <td><textarea name="announce" class="common-textarea" id="announce" cols="20"
                                  style="width: 98%;" rows="5">${setting.announce}</textarea></td>
                </tr>
                <tr>
                    <th></th>
                    <td>
                        <input class="btn btn-primary btn6 mr10" id="uploadSubmit" value="提交" onclick="return operate()"
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