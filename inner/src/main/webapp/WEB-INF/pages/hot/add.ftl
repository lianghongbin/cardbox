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
                url: '/hot/save',// 跳转到 action
                data: $('#myform').serialize(),// 你的formid,
                type: 'post',
                dataType: 'text',
                success: function (data) {
                    if (data == "1") {
                        alert("添加成功");
                        window.location = "/hot/all";
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
                                                                                                   href="./all">热词管理</a><span
                class="crumb-step">&gt;</span><span>热词添加</span></div>
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
                        <input class="common-text required" id="sort" name="sort" value="0" size="50" type="text"/>
                    </td>
                </tr>
                <tr>
                    <th>平台：</th>
                    <td>
                        <select name="platform" id="platform" class="required">
                            <option value="ALL">ALL</option>
                            <option value="android">android</option>
                            <option value="iOS">iOS</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>热词：</th>
                    <td>
                        <input class="common-text required" id="name" name="name" size="50" type="text"/>
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