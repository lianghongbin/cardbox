<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>后台管理</title>
    <link rel="stylesheet" type="text/css" href="../css/common.css"/>
    <link rel="stylesheet" type="text/css" href="../css/main.css"/>
    <link rel="stylesheet" type="text/css" href="../tcal/tcal.css" />
    <script type="text/javascript" src="../js/libs/modernizr.min.js"></script>
    <script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="../tcal/tcal.js"></script>
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
                <tr>
                    <th>所属游戏：</th>
                    <td>${game.name}
                        <input name="name" value="${game.name}" type="hidden">
                    </td>
                </tr>
                <tr>
                    <th width="120">名称：</th>
                    <td>
                        <input name="id" id="id" value="${card.id}" type="hidden">
                        <input class="common-text required" id="name" name="name" value="${card.name}" size="50" type="text"/>
                    </td>
                </tr>
                <tr>
                    <th><i class="require-red">*</i>图标：</th>
                    <td>
                        <img src="${card.icon}"/>
                    </td>
                </tr>
                <tr>
                    <th>礼包数量：</th>
                    <td>${card.total}
                         <font color="red">礼包已分发:${card.assignTotal} </font><input name="assignTotal" id="assignTotal" value="${card.assignTotal}" type="hidden"/>
                    </td>
                </tr>
                <tr>
                    <th>礼包类别：</th>
                    <td>
                        <select name="type" id="type" class="required">
                        <#list types as type>
                            <option value="${type.name()}" <#if type.name() == card.type>selected</#if>>${type.name()}</option>
                        </#list>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>分数：</th>
                    <td><input class="common-text" name="score" id="score" value="${card.score}" size="50" type="text"></td>
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
                    <td><input class="tcal" name="openTimeString" value="${card.openTime?number_to_datetime}" id="openTimeString" size="50" type="text"></td>
                </tr>
                <tr>
                    <th>截止时间：</th>
                    <td><input class="tcal" name="expireTimeString" value="${card.expireTime?number_to_datetime}" id="openTimeString" size="50" type="text"></td>
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