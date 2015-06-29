<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>后台管理</title>
    <link rel="stylesheet" type="text/css" href="../css/common.css"/>
    <link rel="stylesheet" type="text/css" href="../css/main.css"/>
    <script type="text/javascript" src="../js/libs/modernizr.min.js"></script>
    <script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
    <script src="/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="/uploadify/uploadify.css">
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

        $(function () {
            $("#iconFile").uploadify({
                method: 'post',
                swf: '/uploadify/uploadify.swf',  // uploadify.swf在项目中的路径
                uploader: '/photo/single',  // 后台UploadController处理上传的方法
                fileObjName: 'file',         // The name of the file object to use in your server-side script
                successTimeout: 30,                 // The number of seconds to wait for Flash to detect the server's response after the file has finished uploading
                removeCompleted: false,              // Remove queue items from the queue when they are done uploading
                fileSizeLimit: '50MB',
                buttonText: '选择文件',
                queueID: 'iconQueue',
                multi: false,
                onUploadSuccess: function (file, data, response) {
                    $("#icon").val(data);
                    $("#imgId").html("<img src=" + data + ">");
                    $("#show").show();
                }
            });
        });
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
            <form id="myform" name="myform">
                <table class="insert-tab" width="100%">
                    <tbody>
                    <tr>
                        <th width="120">名称：</th>
                        <td>
                            <input name="icon" id="icon" type="hidden">
                            <input name="id" id="id" value="${game.id}" type="hidden">
                            <input class="common-text required" id="name" name="name" size="50" value="${game.name}"
                                   type="text"/>
                        </td>
                    </tr>
                    <tr>
                        <th>图标：</th>
                        <td>
                            <span id="imgId"><img src="${game.icon}"/></span>
                        </td>
                    </tr>
                    <tr>
                        <th>替换：</th>
                        <td>
                            <table class="insert-tab">
                                <tr>
                                    <td width="200"><input id="iconFile" name="iconFile" type="file"></td>
                                    <td>
                                        <div id="iconQueue"></div>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <th>下载地址：</th>
                        <td><input class="common-text" name="url" id="url" size="50" value="${game.url}" type="text">
                        </td>
                    </tr>
                    <tr>
                        <th>iOS ID</th>
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