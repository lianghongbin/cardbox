<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>后台管理</title>
    <link rel="stylesheet" type="text/css" href="/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="/css/main.css"/>
    <script type="text/javascript" src="/js/libs/modernizr.min.js"></script>
    <script src="/js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="/uploadify/uploadify.css">
    <script type="text/javascript">
        $(function () {
            $("#file").uploadify({
                method: 'post',
                swf: '/uploadify/uploadify.swf',  // uploadify.swf在项目中的路径
                uploader: '/photo/upload',  // 后台UploadController处理上传的方法
                fileObjName: 'file',         // The name of the file object to use in your server-side script
                successTimeout: 30,                 // The number of seconds to wait for Flash to detect the server's response after the file has finished uploading
                removeCompleted: false,              // Remove queue items from the queue when they are done uploading
                fileSizeLimit: '50MB',
                buttonText: '选择文件',
                queueID: 'queue',
                formData:{
                    id:$("#id").val(),
                    type:$("#type").val()
                }
            });
        });
    </script>
</head>
<body>

<!--/sidebar-->
<div class="main-wrap">

    <div class="crumb-wrap">
        <div class="crumb-list"><i class="icon-font"></i>首页<span class="crumb-step">&gt;</span>图片管理<span
                class="crumb-step">&gt;</span><span>图片添加</span></div>
    </div>
    <div class="result-wrap">
        <div class="result-content">
            <form id="myform" name="myform" method="POST" enctype="multipart/form-data">
                <table class="insert-tab" width="100%">
                    <tbody>
                    <tr>
                    <#if type?lower_case == "game">
                        <th width="100">
                            游戏名称：<input type="hidden" name="id" id="id" value="${item.id}"/>
                        </th>
                    <#else>
                        <th width="100">
                            礼包名称：<input type="hidden" name="id" id="id" value="${item.id}"/>
                        </th>
                    </#if>
                        <td><input type="hidden" name="type" id="type" value="${type}"/>
                        ${item.name}
                        </td>
                    </tr>
                    <tr>
                        <th>上传图片：</th>
                        <td>
                            <input id="file" name="file" type="file" multiple="true">
                        </td>
                    </tr>
                    <tr>
                        <th></th>
                        <td>
                            <div id="queue"></div>
                        </td>
                    </tr>
                    <tr>
                        <th></th>
                        <td>
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