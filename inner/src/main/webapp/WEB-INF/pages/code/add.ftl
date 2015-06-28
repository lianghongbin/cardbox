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
            var $codesInput = $(":text");
            var codes = [];
            $codesInput.each(function(){
                if ($(this).val().trim() != "") {
                    codes.push($(this).val());
                }
            });

            $.ajax({
                url: '/code/save',// 跳转到 action
                data: {
                    'cardId': $("#cardId").val(),
                    'gameId': $("#gameId").val(),
                    'cardName':$("#cardName").val(),
                    'gameName':$("#gameName").val(),
                    'codes':codes
                },
                traditional :true,
                type: 'post',
                dataType: 'text',
                success: function (data) {
                    if (data == "1") {
                        alert("激活码添加成功");
                        window.location.href="../card/all";
                    } else {
                        alert("激活码添加失败");
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
                                                                                                   href="./all">激活码管理</a><span
                class="crumb-step">&gt;</span><span>激活码添加</span></div>
    </div>
    <div class="result-wrap">
        <div class="result-title">
            <div class="result-list">
                <a href="./input?cardId=${card.id}"><i class="icon-font"></i>批量导入</a>
            </div>
        </div>
        <div class="result-content">
            <form id="myform" name="myform">
                <table class="insert-tab" width="100%">
                    <tbody>
                    <tr>
                        <th width="120">游戏名称：</th>
                        <td><input name="gameId" id="gameId" value="${game.id}" type="hidden">
                            <input name="gameName" id="gameName" value="${game.name}" type="hidden">
                            <input name="cardId" id="cardId" value="${card.id}" type="hidden">
                            <input name="cardName" id="cardName" value="${card.name}" type="hidden">
                            ${game.name}
                        </td>
                    </tr>
                    <tr>
                        <th>礼包名称：</th>
                        <td>
                            ${card.name}
                        </td>
                    </tr>
                    <tr>
                        <th>激活码：</th>
                        <td>
                            <table>
                                <tr><td><input class="common-text" name="codes[]" size="20" type="text"></td><td><input class="common-text" name="codes[]" size="20" type="text"></td><td><input class="common-text" name="codes[]" size="20" type="text"></td><td><input class="common-text" name="codes[]" size="20" type="text"></td></tr>
                                <tr><td><input class="common-text" name="codes[]" size="20" type="text"></td><td><input class="common-text" name="codes[]" size="20" type="text"></td><td><input class="common-text" name="codes[]" size="20" type="text"></td><td><input class="common-text" name="codes[]" size="20" type="text"></td></tr>
                                <tr><td><input class="common-text" name="codes[]" size="20" type="text"></td><td><input class="common-text" name="codes[]" size="20" type="text"></td><td><input class="common-text" name="codes[]" size="20" type="text"></td><td><input class="common-text" name="codes[]" size="20" type="text"></td></tr>
                                <tr><td><input class="common-text" name="codes[]" size="20" type="text"></td><td><input class="common-text" name="codes[]" size="20" type="text"></td><td><input class="common-text" name="codes[]" size="20" type="text"></td><td><input class="common-text" name="codes[]" size="20" type="text"></td></tr>
                                <tr><td><input class="common-text" name="codes[]" size="20" type="text"></td><td><input class="common-text" name="codes[]" size="20" type="text"></td><td><input class="common-text" name="codes[]" size="20" type="text"></td><td><input class="common-text" name="codes[]" size="20" type="text"></td></tr>
                                <tr><td><input class="common-text" name="codes[]" size="20" type="text"></td><td><input class="common-text" name="codes[]" size="20" type="text"></td><td><input class="common-text" name="codes[]" size="20" type="text"></td><td><input class="common-text" name="codes[]" size="20" type="text"></td></tr>

                            </table>

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