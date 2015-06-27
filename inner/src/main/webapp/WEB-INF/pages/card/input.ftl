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
    <script type="text/javascript">
        function operate() {
            $.ajax({
                url: '/card/save',// 跳转到 action
                data: $('#myform').serialize(),// 你的formid,
                type: 'post',
                dataType: 'text',
                success: function (data) {
                    if (data == "1") {
                        alert("添加成功");
                        window.location.reload();
                    } else {
                        alert("添加失败");
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
                        <td>${game.name}
                            <input name="gameId" id="gameId" value="${game.id}" type="hidden">
                        </td>
                    </tr>
                    <tr>
                        <th width="120"><i class="require-red">*</i>名称：</th>
                        <td>
                            <input class="common-text required" id="name" name="name" size="50" type="text"/>
                        </td>
                    </tr>
                    <th><i class="require-red">*</i>平台：</th>
                    <td>
                        <select name="platform" id="platform" class="required">
                            <option value="ALL">ALL</option>
                            <option value="android">android</option>
                            <option value="iOS">iOS</option>
                        </select>
                    </td>
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
                        <th><i class="require-red">*</i>分数：</th>
                        <td><input class="common-text" name="score" id="score" size="20" type="text"></td>
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
                        <th><i class="require-red">*</i>描述：</th>
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


<script>
    var start = {
        elem: '#start',
        format: 'YYYY-MM-DD hh:mm:ss',
        min: laydate.now(), //设定最小日期为当前日期
        max: '2099-06-16 23:59:59', //最大日期
        istime: true,
        istoday: false,
        choose: function(datas){
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
        choose: function(datas){
            start.max = datas; //结束日选好后，重置开始日的最大日期
        }
    };
    laydate(start);
    laydate(end);
</script>
</body>
</html>