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
                url: '/system/save',// 跳转到 action
                data: $('#myform').serialize(),// 你的formid,
                type: 'post',
                dataType: 'text',
                success: function (data) {
                    if (data == "1") {
                        alert("添加成功");
                        window.location = "/system/all";
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
                    <td>
                        <input class="common-text required" id="v" name="v" size="50" type="text"/>
                    </td>
                </tr>
                <tr>
                    <th>每天登录得分：</th>
                    <td>
                        <input class="common-text required" id="daily" value="300" name="daily" size="50" type="text"/>
                    </td>
                </tr>
                <tr>
                    <th>注册得分：</th>
                    <td>
                        <input class="common-text required" id="registry" value="10" name="registry" size="50" type="text"/>
                    </td>
                </tr>
                <tr>
                    <th>微信分享得分：</th>
                    <td>
                        <input class="common-text required" id="weixin" value="10" name="weixin" size="50" type="text"/>
                    </td>
                </tr>
                <tr>
                    <th>QQ分享得分：</th>
                    <td>
                        <input class="common-text required" id="qq" name="qq" size="50" type="text"/>
                    </td>
                </tr>
                <tr>
                    <th>下载游戏得分：</th>
                    <td>
                        <input class="common-text required" id="download" value="100" name="download" size="50" type="text"/>
                    </td>
                </tr>
                <tr>
                    <th>关于我们：</th>
                    <td><textarea name="us" class="common-textarea" id="us" cols="20"
                                  style="width: 98%;" rows="5"></textarea></td>
                </tr>
                <tr>
                    <th>启动公告：</th>
                    <td><textarea name="announce" class="common-textarea" id="announce" cols="20"
                                  style="width: 98%;" rows="5"></textarea></td>
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