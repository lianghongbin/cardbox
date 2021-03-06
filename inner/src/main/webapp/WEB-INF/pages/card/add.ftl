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
            if ($("#gameId").val() == "") {
                alert("请选择所属游戏！");
                return false;
            }
            if ($("#platform").val() == "") {
                alert("请选择所属平台");
                return false;
            }

            $.ajax({
                url: '/card/save',// 跳转到 action
                data: $('#myform').serialize(),// 你的formid,
                type: 'post',
                dataType: 'text',
                success: function (data) {
                    if (data == "1") {
                        alert("添加成功");
                        window.location.href = "./all";
                    } else {
                        alert("添加失败");
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
                    $("#imgId").html("<img src=" + data + " width=160 height=160>");
                    $("#show").show();
                }
            });
        });

        function setPlatform(gameId) {
            if(gameId == "") {
                return;
            }
            $.ajax({
                url: '/game/findplatform',// 跳转到 action
                data: {
                    id: gameId
                },
                type: 'post',
                dataType: 'text',
                success: function (data) {
                    if (data == "") {
                        alert("该游戏不存在");
                        return;
                    }

                    if(data == "ALL") {
                        $("#platformId").html("<select name=platform id=platform><option value=ALL>ALL</option><option value=iOS>iOS</option><option value=android>android</option></select>");
                    }
                    else{
                        $("#platformId").html("<input type=text name=platform id=platform value=" + data + " readonly>");
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
                                                                                                   href="./all">礼包管理</a><span
                class="crumb-step">&gt;</span><span>礼包添加</span></div>
    </div>
    <div class="result-wrap">
        <div class="result-content">
            <form id="myform" name="myform">
                <table class="insert-tab" width="100%">
                    <tbody>
                    <tr>
                        <th><i class="require-red">*</i>所属游戏：</th>
                        <td>
                            <select name="gameId" id="gameId" class="required" onchange="setPlatform(this.value)">
                                <option value="">请选择所属游戏</option>
                            <#list games as game>
                                <option value="${game.id}">${game.name}</option>
                            </#list>
                            </select>
                        </td>
                    </tr>
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
                        <th><i class="require-red">*</i>平台：</th>
                        <td>
                            <div id="platformId"></div>
                        </td>
                    </tr>
                    <tr>
                        <th><i class="require-red">*</i>礼包类别：</th>
                        <td>
                            <select name="type" id="type" class="required">
                            <#list types as type>
                                <option value="${type.name()}">${type.name()}</option>
                            </#list>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th><i class="require-red">*</i>排序：</th>
                        <td><input class="common-text" name="sort" id="sort" value="0" size="20" type="text"></td>
                    </tr>
                    <tr>
                        <th><i class="require-red">*</i>消耗数：</th>
                        <td><input class="common-text" name="score" id="score" size="20" value="0" type="text"></td>
                    </tr>
                    <tr>
                        <th><i class="require-red">*</i>是否推荐：</th>
                        <td>
                            <select name="recommend" id="recommend" class="required">
                                <option value="true">推荐</option>
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
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th><i class="require-red">*</i>礼包描述：</th>
                        <td><textarea name="description" class="common-textarea" id="description" cols="20"
                                      style="width: 98%;" rows="5"></textarea></td>
                    </tr>
                    <tr>
                        <th><i class="require-red">*</i>使用流程：</th>
                        <td><textarea name="flow" class="common-textarea" id="flow" cols="20"
                                      style="width: 98%;" rows="5"></textarea></td>
                    </tr>
                    <tr>
                        <th><i class="require-red">*</i>开放时间：</th>
                        <td>
                            <input class="laydate-icon" name="start" id="start" style="width:200px;">
                        </td>
                    </tr>
                    <tr>
                        <th><i class="require-red">*</i>截止时间：</th>
                        <td>
                            <input class="laydate-icon" name="end" id="end" style="width:200px;">
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