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
            if ($("#itemId").val()=="" && $("#t").val() =="") {
                alert("请你选择焦点图关联类型!");
                return;
            }
            if($("#photo").val() == "") {
                alert("请上传焦点图！");
                return false;
            }
            if($("input[name='type']:checked").val() == "PAGE" && $("#url").val() == "") {
                alert("请填写焦点图对应URL");
                return false;
            }

            $.ajax({
                url: '/focus/save',// 跳转到 action
                data: $('#myform').serialize(),// 你的formid,
                type: 'post',
                dataType: 'text',
                success: function (data) {
                    if (data == "1") {
                        alert("添加成功");
                        window.location = "/focus/all";
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
                onUploadSuccess : function(file, data, response) {
                    if(data == "") {
                        alert("上传文件失败");
                        return false;
                    }

                    $("#photo").val(data);
                    $("#imgId").html("<img src=" + data + ">");
                    $("#show").show();
                }
            });
        });

        function onChange(type) {
            if(type == "GAME") {
                $("#itemGame").show();
                $("#itemCard").hide();
                $("#itemPage").hide();
                $("#t").val("");
            }
            else if(type == "CARD") {
                $("#itemGame").hide();
                $("#itemCard").show();
                $("#itemPage").hide();
                $("#t").val("");
            }
            else {
                $("#itemGame").hide();
                $("#itemCard").hide();
                $("#itemPage").show();
                $("#t").val("Page");
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
                        <input type="radio" name="type" value="GAME"
                               onclick="onChange('GAME')" checked> GAME &nbsp;&nbsp;

                        <input type="radio" name="type" value="CARD"
                               onclick="onChange('CARD')"> CARD &nbsp;&nbsp;

                        <input type="radio" name="type" value="PAGE"
                               onclick="onChange('PAGE')"> PAGE
                    </td>
                </tr>
                <tr>
                    <th>关联ITEM：</th>
                    <td>
                        <div id="itemGame">
                            关联游戏：<select name="gId" id="gId" class="required" onchange="setV(this.value)">
                            <option value="">请选择关联游戏</option>
                        <#list games as game>
                            <option value="${game.id}">${game.name}</option>
                        </#list>
                        </select>
                        </div>

                        <div id="itemCard" STYLE="display: none">
                            关联礼包：<select name="cId" id="cId" class="required" onchange="setV(this.value)">
                            <option value="">请选择关联礼包</option>
                        <#list cards as card>
                            <option value="${card.id}">${card.name}</option>
                        </#list>
                        </select>
                        </div>

                        <div id="itemPage" style="display:none">
                            外部链接
                        </div>
                    </td>
                </tr>
                <tr>
                    <th width="120">排序：</th>
                    <td>
                        <input type="hidden" name="itemId" id="itemId"/>
                        <input class="common-text required" id="sort" name="sort" value="0" size="20" type="text"/>
                    </td>
                </tr>
                <tr id="show" style="display: none">
                    <th>配图：</th>
                    <td>
                        <span id="imgId"></span>
                    </td>
                </tr>
                <tr>
                    <th>上传：</th>
                    <td>
                        <table class="insert-tab">
                            <tr>
                                <td width="200"><input id="photoFile" name="photoFile" type="file"></td><td><div id="photoQueue"></div></td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <th>URL：</th>
                    <td>
                        <input name="t" id="t" type="hidden">
                        <input name="photo" id="photo" type="hidden">
                        <input class="common-text required" id="url" name="url" size="50" type="text"/>
                    </td>
                </tr>
                <tr>
                    <th>是否上线：</th>
                    <td>
                        <input type="radio" name="enabled" value="true" checked>上线 &nbsp;&nbsp;
                        <input type="radio" name="enabled" value="false">下线
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