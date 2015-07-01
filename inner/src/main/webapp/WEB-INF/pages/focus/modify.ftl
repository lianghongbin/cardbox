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
                url: '/focus/update',// 跳转到 action
                data: $('#myform').serialize(),// 你的formid,
                type: 'post',
                dataType: 'text',
                success: function (data) {
                    if (data == "1") {
                        alert("修改成功");
                        window.location = "/focus/all";
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
            $("#photoFile").uploadify({
                method: 'post',
                swf: '/uploadify/uploadify.swf',  // uploadify.swf在项目中的路径
                uploader: '/photo/single',  // 后台UploadController处理上传的方法
                fileObjName: 'file',         // The name of the file object to use in your server-side script
                successTimeout: 30,                 // The number of seconds to wait for Flash to detect the server's response after the file has finished uploading
                removeCompleted: false,              // Remove queue items from the queue when they are done uploading
                fileSizeLimit: '50MB',
                buttonText: '选择文件',
                queueID: 'photoQueue',
                multi: false,
                onUploadSuccess: function (file, data, response) {
                    $("#photo").val(data);
                    $("#imgId").html("<img src=" + data + " width=160 height=160>");
                }
            });
        });

        function onChange(type) {
            if (type == "GAME") {
                $("#itemGame").show();
                $("#itemCard").hide();
            }
            else if (type == "CARD") {
                $("#itemGame").hide();
                $("#itemCard").show();
            }
        }

        function setV(v) {
            $("#itemId").val(v);
        }
    </script>
</head>
<body>

<!--/sidebar-->
<div class="main-wrap">

    <div class="crumb-wrap">
        <div class="crumb-list"><i class="icon-font"></i>首页<span class="crumb-step">&gt;</span><a class="crumb-name"
                                                                                                   href="./all">焦点图管理</a><span
                class="crumb-step">&gt;</span><span>焦点图添加</span></div>
    </div>
    <div class="result-wrap">
        <div class="result-content">
            <form id="myform" name="myform">
                <table class="insert-tab" width="100%">
                    <tbody>
                    <tr>
                        <th>所属类别：</th>
                        <td>
                            <input type="radio" name="type" value="GAME" <#if focus.type=="GAME">checked</#if>
                                   onclick="onChange('GAME')"> GAME &nbsp;&nbsp;

                            <input type="radio" name="type" value="CARD" <#if focus.type=="CARD">checked</#if>
                                   onclick="onChange('CARD')"> CARD
                        </td>
                    </tr>
                    <tr>
                        <th>关联ITEM：</th>
                        <td>
                            <div id="itemGame" <#if focus.type=="CARD">style="display:none"</#if> >
                                关联游戏：<select name="t" id="t" class="required" onchange="setV(this.value)">
                            <#list games as game>
                                <option value="${game.id}"
                                        <#if game.id==focus.itemId>selected</#if>>${game.name}</option>
                            </#list>
                            </select>
                            </div>

                            <div id="itemCard"  <#if focus.type=="GAME">style="display:none"</#if>>
                                关联礼包：<select name="t" id="t" class="required"  onchange="setV(this.value)">
                            <#list cards as card>
                                <option value="${card.id}"
                                        <#if card.id==focus.itemId>selected</#if>>${card.name}</option>
                            </#list>
                            </select>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th width="120">排序：</th>
                        <td>
                            <input class="common-text required" id="sort" name="sort" value="${focus.sort}" size="20"
                                   type="text"/>
                        </td>
                    </tr>
                    <tr>
                        <th>配图：</th>
                        <td>
                            <span id="imgId"><img src="${focus.photo}" width="160" height="160"/></span>
                        </td>
                    </tr>
                    <tr>
                        <th>替换：</th>
                        <td>
                            <table class="insert-tab">
                                <tr>
                                    <td width="200"><input id="photoFile" name="photoFile" type="file"></td>
                                    <td>
                                        <div id="photoQueue"></div>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <th>URL：</th>
                        <td>
                            <input name="id" id="id" value="${focus.id}" type="hidden">
                            <input name="photo" id="photo" value="${focus.photo}" type="hidden">
                            <input name="itemId" id="itemId" value="${focus.itemId}" type="hidden"/>
                            <input class="common-text required" id="url" name="url" value="${focus.url}" size="50"
                                   type="text"/>
                        </td>
                    </tr>
                    <tr>
                        <th>是否上线：</th>
                        <td>
                            <input type="radio" name="enabled" value="true" <#if focus.enabled>checked</#if>> 上线 &nbsp;&nbsp;
                            <input type="radio" name="enabled" value="false" <#if !focus.enabled>checked</#if>>下线
                        </td>
                    </tr>
                    <tr>
                        <th></th>
                        <td>
                            <input class="btn btn-primary btn6 mr10" id="uploadSubmit" value="提交"
                                   onclick="return operate()"
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