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
                url: '/game/save',// 跳转到 action
                data: $('#myform').serialize(),// 你的formid,
                type: 'post',
                dataType: 'text',
                success: function (data) {
                    if (data == "1") {
                        alert("游戏添加成功");
                        window.location.reload();
                    } else {
                        alert("游戏添加失败");
                    }
                },
                error: function (XMLHttpRequest) {
                    alert("异常请求状态：" +　XMLHttpRequest.status);
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
                    if(data==null) {
                        alert("图片上传失败");
                        return false;
                    }
                    $("#icon").val(data);
                    $("#imgId").html("<img src=" + data + " width=160 height=160>");
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
                class="crumb-step">&gt;</span><span>游戏添加</span></div>
    </div>
    <div class="result-wrap">
        <div class="result-content">
            <form id="myform" name="myform">
                <table class="insert-tab" width="100%">
                    <tbody>
                    <tr>
                        <th width="120"><i class="require-red">*</i>名称：</th>
                        <td>
                            <input name="icon" id="icon" type="hidden">
                            <input class="common-text required" id="name" name="name" size="50" type="text"/>
                        </td>
                    </tr>
                    <tr id="show" style="display: none">
                        <th>配图：</th>
                        <td>
                            <span id="imgId"></span>
                        </td>
                    </tr>
                    <tr>
                        <th>ICON：</th>
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
                        <th><i class="require-red">*</i>下载地址：</th>
                        <td><input class="common-text" name="url" id="url" size="50" type="text">
                        </td>
                    </tr>
                    <tr>
                        <th>iOS ID</th>
                        <td><input class="common-text" name="iosId" id="iosId" size="50" type="text"></td>
                    </tr>
                    <tr>
                        <th>包名：</th>
                        <td><input class="common-text" name="identifier" id="identifier" size="50" type="text"></td>
                    </tr>
                    <tr>
                        <th><i class="require-red">*</i>评分：</th>
                        <td><select name="score" id="score">
                            <#assign step = 0/>
                            <#list 1..10 as num>
                                <#assign step=step + 0.5/>
                            <option value=${step}>${step}</option>
                            </#list>
                        </select> </td>
                    </tr>
                    <tr>
                        <th><i class="require-red">*</i>排序：</th>
                        <td><input class="common-text" name="sort" id="sort" size="50" value="0" type="text"></td>
                    </tr>
                    <tr>
                        <th><i class="require-red">*</i>分类：</th>
                        <td>
                            <#list typesList as type>
                                <input type="checkbox" name="types" value="${type.name}">${type.name}&nbsp;
                            </#list>
                        </td>
                    </tr>
                    <tr>
                        <th><i class="require-red">*</i>平台：</th>
                        <td>
                            <select name="platform" id="platform" class="required">
                                <option value="ALL">ALL</option>
                                <option value="android">android</option>
                                <option value="iOS">iOS</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th><i class="require-red">*</i>是否推荐：</th>
                        <td>
                            <select name="recommend" id="recommend" class="required">
                                <option value="true" selected>推荐</option>
                                <option value="false">不推荐</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th><i class="require-red">*</i>是否上线：</th>
                        <td>
                            <select name="closed" id="closed" class="required">
                                <option value="false">上线</option>
                                <option value="true" selected>关闭</option>
                            </select> <span class="require-red">(新增游戏建议先关闭，完善之后再开启上线)</span>
                        </td>
                    </tr>
                    <tr>
                        <th><i class="require-red">*</i>描述：</th>
                        <td><textarea name="description" class="common-textarea" id="description" cols="20"
                                      style="width: 98%;" rows="5"></textarea></td>
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