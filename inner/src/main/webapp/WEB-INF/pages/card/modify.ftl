<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>后台管理</title>
    <link rel="stylesheet" type="text/css" href="../css/common.css"/>
    <link rel="stylesheet" type="text/css" href="../css/main.css"/>
    <script type="text/javascript" src="../js/libs/modernizr.min.js"></script>
    <script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="../laydate/laydate.js"></script>
    <script src="/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="/uploadify/uploadify.css">
    <script type="text/javascript">
        function operate() {
            $.ajax({
                url: '/card/update',// 跳转到 action
                data: $('#myform').serialize(),// 你的formid,
                type: 'post',
                dataType: 'text',
                success: function (data) {
                    if (data == "1") {
                        alert("更新成功");
                        window.location.reload();
                    } else {
                        alert(data);
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
                    if(data =="") {
                        alert("图片上传失败");
                    }

                    $("#icon").val(data);
                    $("#imgId").html("<img src=" + data + ">");
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
                                                                                                   href="./all">礼包管理</a><span
                class="crumb-step">&gt;</span><span>礼包更新</span></div>
    </div>
    <div class="result-wrap">
        <div class="result-content">
            <form id="myform" name="myform">
                <table class="insert-tab" width="100%">
                    <tbody>
                    <th><i class="require-red">*</i>所属游戏：</th>
                    <td>
                        <select name="gameId" id="gameId" class="required">
                        <#list games as game>
                            <option value="${game.id}" <#if game.id==card.gameId>selected</#if>>${game.name}</option>
                        </#list>
                        </select>
                    </td>
                    <tr>
                        <th width="120">名称：</th>
                        <td>
                            <input name="id" id="id" value="${card.id}" type="hidden">
                            <input class="common-text required" id="name" name="name" value="${card.name}" size="50"
                                   type="text"/>
                        </td>
                    </tr>
                    <tr>
                        <th width="120">ICON：</th>
                        <td>
                            <input name="id" id="id" value="${card.id}" type="hidden">
                            <input name="icon" id="icon" value="${card.icon}" type="hidden">
                            <span id="imgId"><img src="${card.icon}"></span>
                        </td>
                    </tr>
                    <tr>
                        <th>替换：</th>
                        <td>
                            <table class="insert-tab">
                                <tr>
                                    <td width="200"><input id="iconFile" name="iconFile" type="file"></td><td><div id="iconQueue"></div></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <th><i class="require-red">*</i>平台：</th>
                        <td>
                            <select name="platform" id="platform" class="required">
                                <option value="ALL" <#if card.platform =="ALL">selected</#if>> ALL</option>
                                <option value="android" <#if card.platform =="android">selected</#if>>android</option>
                                <option value="iOS" <#if card.platform =="iOS">selected</#if>>iOS</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>礼包类别：</th>
                        <td>
                            <select name="type" id="type" class="required">
                            <#list types as type>
                                <option value="${type.name()}"
                                        <#if type.name() == card.type>selected</#if>>${type.name()}</option>
                            </#list>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>分数：</th>
                        <td><input class="common-text" name="score" id="score" value="${card.score}" size="20"
                                   type="text"></td>
                    </tr>
                    <tr>
                        <th>是否推荐：</th>
                        <td>
                            <select name="recommend" id="recommend" class="required">
                                <option value="true" <#if card.recommend>selected</#if>>推荐</option>
                                <option value="false" <#if !card.recommend>selected</#if>>不推荐</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>是否上线：</th>
                        <td>
                            <select name="closed" id="closed" class="required">
                                <option value="false" <#if !card.closed>selected</#if>>上线</option>
                                <option value="true" <#if card.closed>selected</#if>>关闭</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>描述：</th>
                        <td><textarea name="description" class="common-textarea" id="description" cols="20"
                                      style="width: 98%;" rows="5">${card.description}</textarea></td>
                    </tr>
                    <tr>
                        <th>使用流程：</th>
                        <td><textarea name="flow" class="common-textarea" id="flow" cols="20"
                                      style="width: 98%;" rows="5">${card.flow}</textarea></td>
                    </tr>
                    <tr>
                        <th>开放时间：</th>
                        <td>
                            <input class="laydate-icon" name="start" id="start"
                                   value="${card.openTime?number_to_datetime}" style="width:200px;">
                        </td>
                    </tr>
                    <tr>
                        <th>截止时间：</th>
                        <td>
                            <input class="laydate-icon" name="end" id="end"
                                   value="${card.expireTime?number_to_datetime}" style="width:200px;">
                        </td>
                    </tr>
                    <tr>
                        <th></th>
                        <td>
                            <input class="btn btn-primary btn6 mr10" id="uploadSubmit" value="提交"
                                   onclick="return operate()" type="button">
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

<script>
    var start = {
        elem: '#start',
        format: 'YYYY-MM-DD hh:mm:ss',
        min: laydate.now(), //设定最小日期为当前日期
        max: '2099-06-16 23:59:59', //最大日期
        istime: true,
        istoday: false,
        choose: function (datas) {
            end.min = datas; //开始日选好后，重置结束日的最小日期
            end.start = datas //将结束日的初始值设定为开始日
        }
    };
    var end = {
        elem: '#end',
        format: 'YYYY-MM-DD hh:mm:ss',
        min: laydate.now(),
        max: '2099-06-16 23:59:59',
        istime: true,
        istoday: false,
        choose: function (datas) {
            start.max = datas; //结束日选好后，重置开始日的最大日期
        }
    };
    laydate(start);
    laydate(end);
</script>
</body>
</html>