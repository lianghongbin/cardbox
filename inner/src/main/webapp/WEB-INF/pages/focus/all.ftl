<#import "/spring.ftl" as spring />
<#import "/macros/pagination.ftl" as pagination />
<#setting url_escaping_charset='utf-8'>
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
        function operate(id,enabled)
        {
            confirm("你确定要对该焦点图进行上线/下线操作？");
            if(enabled == null) {
                enabled = false;
            }

            $.ajax({
                url: '/focus/enabled',// 跳转到 action
                data: {
                    id: id,
                    enabled: !enabled
                },
                type: 'get',
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

        function del(id)
        {
            confirm("你确定要删除该焦点图吗？");
            $.ajax({
                url: '/focus/remove',// 跳转到 action
                data: {
                    id: id
                },
                type: 'post',
                dataType: 'text',
                success: function (data) {
                    if (data == "1") {
                        alert("删除成功");
                        window.location.reload();
                    } else {
                        alert("删除失败");
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
<div class="container clearfix">
    <!--/sidebar-->
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i>首页<span class="crumb-step">&gt;</span><span
                    class="crumb-name">焦点图管理</span></div>
        </div>
        <div class="result-wrap">
            <form name="myform" id="myform" method="post">
                <div class="result-title">
                    <div class="result-list">
                        <a href="./add"><i class="icon-font"></i>新增焦点图</a>
                        <a id="batchDel" href="javascript:void(0)"><i class="icon-font"></i>批量删除</a>
                        <a id="updateOrd" href="javascript:void(0)"><i class="icon-font"></i>更新排序</a>
                    </div>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th class="tc" width="30"><input class="allChoose" name="" type="checkbox"></th>
                            <th>排序</th>
                            <th>类别</th>
                            <th>Item ID</th>
                            <th>状态</th>
                            <th>图片</th>
                            <th width="150">添加时间</th>
                            <th width="100">操作</th>
                        </tr>
                    <#list paginationData.pageItems as focus>
                        <tr>
                            <td class="tc"><input name="id[]" value="${focus.id}" type="checkbox"></td>
                            <td><input name="ids[]" value="${game.id}" type="hidden">
                                <input class="common-input sort-input" name="sort" value="${focus.sort}" type="text">
                            </td>
                            <td>
                                ${focus.type}
                            </td>
                            <td>
                            ${focus.itemId}
                            </td>
                            <td><#if focus.enabled>线上<#else><font color="red">下线</font></#if> </td>
                            <td><img src="${focus.photo}"></td>
                            <td>${focus.createTime?number_to_datetime}</td>
                            <td align="center">
                                <#if focus.enabled><a class="link-update" href="javascript:operate(${focus.id},'${focus.enabled!false}')">下线</a><#else><a class="link-update" href="javascript:operate(${focus.id},'${focus.enabled!false}')">上线</a></#if>
                                &nbsp; <a class="link-update" href="javascript:del(${focus.id})">删除</a>
                            </td>
                        </tr>
                    </#list>
                    </table>
                    <div class="list-page">
                        <nav style="float:right;">
                        <@pagination.counter /> &nbsp;&nbsp;&nbsp;
                        <@pagination.first />
                        <@pagination.previous />
                        <@pagination.numbers />
                        <@pagination.next />
                        <@pagination.last />
                        </nav>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <!--/main-->
</div>
</body>
</html>