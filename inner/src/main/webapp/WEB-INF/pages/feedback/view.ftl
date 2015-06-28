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
                url: '/feedback/process',// 跳转到 action
                data: $('#myform').serialize(),// 你的formid,
                type: 'post',
                dataType: 'text',
                success: function (data) {
                    if (data == "1") {
                        alert("处理反馈成功");
                        window.location.href="/feedback/all";
                    } else {
                        alert("处理反馈失败");
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
                                                                                                   href="./all">反馈管理</a><span
                class="crumb-step">&gt;</span><span>反馈处理</span></div>
    </div>
    <div class="result-wrap">
        <div class="result-content">
            <form id="myform" name="myform"
            ">
            <table class="insert-tab" width="100%">
                <tbody>
                <tr>
                    <th>手机号：</th>
                    <td>
                        <input name="id" id="id" value="${feedback.id}" type="hidden">
                    ${feedback.phone}
                    </td>
                </tr>
                <tr>
                    <th>联系方式：</th>
                    <td>
                    ${feedback.contact}
                    </td>
                </tr>
                <tr>
                    <th>反馈内容：</th>
                    <td>
                    ${feedback.content}
                    </td>
                </tr>
                <tr>
                    <th>处理意见：</th>
                    <td>
                    <textarea name="remark" class="common-textarea" id="remark" cols="20"
                              style="width: 98%;" rows="5"></textarea>
                    </td>
                </tr>
                <tr>
                    <th></th>
                    <td>
                        <input class="btn btn-primary btn6 mr10" id="uploadSubmit" value="提交" onclick="return operate()" type="button">
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