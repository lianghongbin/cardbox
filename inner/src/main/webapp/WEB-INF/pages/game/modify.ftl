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
                url: '/game/update',// 跳转到 action
                data: $('#myform').serialize(),// 你的formid,
                type: 'post',
                dataType: 'text',
                success: function (data) {
                    if (data == "1") {
                        alert("修改成功");
                        window.location.reload();
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
                                                                                                   href="./all">游戏管理</a><span
                class="crumb-step">&gt;</span><span>游戏修改</span></div>
    </div>
    <div class="result-wrap">
        <div class="result-content">
            <form id="myform" name="myform"">
                <table class="insert-tab" width="100%">
                    <tbody>
                    <tr>
                        <th width="120"><i class="require-red">*</i>名称：</th>
                        <td>
                            <input name="id" id="id" value="${game.id}" type="hidden">
                            <input class="common-text required" id="name" name="name" size="50" value="${game.name}"
                                   type="text"/>
                        </td>
                    </tr>
                    <tr>
                        <th><i class="require-red">*</i>图标：</th>
                        <td>
                            <img src="${game.icon}"/>
                        </td>
                    </tr>
                    <tr>
                        <th>下载地址：</th>
                        <td><input class="common-text" name="url" id="url" size="50" value="${game.url}" type="text">
                        </td>
                    </tr>
                    <tr>
                        <th><i class="require-red">*</i>iOS ID</th>
                        <td><input class="common-text" name="iosId" id="iosId" size="50" value="${game.iosId}"
                                   type="text"></td>
                    </tr>
                    <tr>
                        <th>包名：</th>
                        <td><input class="common-text" name="identifier" id="identifier" size="50"
                                   value="${game.identifier}" type="text"></td>
                    </tr>
                    <tr>
                        <th>礼包数：</th>
                        <td><input class="common-text" name="total" id="total" size="50" value="${game.total}"
                                   type="text"></td>
                    </tr>
                    <tr>
                        <th>评分：</th>
                        <td><input class="common-text" name="score" id="score" size="50" value="${game.score}"
                                   type="text"></td>
                    </tr>
                    <tr>
                        <th>排序：</th>
                        <td><input class="common-text" name="sort" id="sort" size="50" value="${game.sort}"
                                   type="text"></td>
                    </tr>
                    <tr>
                        <th>平台：</th>
                        <td>
                            <select name="platform" id="platform" class="required">
                                <option value="android" <#if game.platform=="android"> selected</#if>>android</option>
                                <option value="iOS" <#if game.platform=="iOS"> selected</#if>>iOS</option>
                                <option value="ALL" <#if game.platform=="ALL"> selected</#if>>ALL</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>是否推荐：</th>
                        <td>
                            <select name="recommend" id="recommend" class="required">
                                <option value="true" <#if game.recommend> selected</#if>>推荐</option>
                                <option value="false" <#if !game.recommend> selected</#if>>不推荐</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>是否上线：</th>
                        <td>
                            <select name="closed" id="closed" class="required">
                                <option value="false" <#if !game.closed> selected</#if>>上线</option>
                                <option value="true" <#if game.closed> selected</#if>>关闭</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>描述：</th>
                        <td><textarea name="description" class="common-textarea" id="description" cols="20"
                                      style="width: 98%;" rows="5">${game.description}</textarea></td>
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