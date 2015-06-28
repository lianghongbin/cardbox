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
                url: '/splash/save',// 跳转到 action
                data: $('#myform').serialize(),// 你的formid,
                type: 'post',
                dataType: 'text',
                success: function (data) {
                    if (data == "1") {
                        alert("添加成功");
                        window.location = "/splash/all";
                    } else {
                        alert("添加失败");
                    }
                },
                error: function () {
                    alert("异常！");
                }
            });
        }

        function onChange(type) {
            if(type == "GAME") {
                $("#itemGame").show();
                $("#itemCard").hide();
            }
            else if(type == "CARD") {
                $("#itemGame").hide();
                $("#itemCard").show();
            }
        }
    </script>
</head>
<body>

<!--/sidebar-->
<div class="main-wrap">

    <div class="crumb-wrap">
        <div class="crumb-list"><i class="icon-font"></i>首页<span class="crumb-step">&gt;</span><a class="crumb-name"
                                                                                                   href="./all">启动页管理</a><span
                class="crumb-step">&gt;</span><span>启动页添加</span></div>
    </div>
    <div class="result-wrap">
        <div class="result-content">
            <form id="myform" name="myform"
            ">
            <table class="insert-tab" width="100%">
                <tbody>
                <tr>
                    <th width="120">排序：</th>
                    <td>
                        <input class="common-text required" id="sort" name="sort" size="50" type="text"/>
                    </td>
                </tr>
                <tr>
                    <th>图片：</th>
                    <td>
                        <input class="common-text required" id="photo" name="photo" size="50" type="text"/>
                    </td>
                </tr>
                <tr>
                    <th>URL：</th>
                    <td>
                        <input class="common-text required" id="url" name="url" size="50" type="text"/>
                    </td>
                </tr>
                <tr>
                    <th>是否上线：</th>
                    <td>
                        <input type="radio" name="enabled" value="true" checked>上线 &nbsp;&nbsp;
                        <input type="radio" name="enabled" value="false">不上线
                    </td>
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