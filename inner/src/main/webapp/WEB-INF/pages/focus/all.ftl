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
            if (!confirm("你确定要对该焦点图进行上线/下线操作？")) {
                return false;
            }

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
            if(!confirm("你确定要删除该焦点图吗？")) {
                return false;
            }

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


        function saveSort(id, sort) {
            $.ajax({
                url: '/focus/update',// 跳转到 action
                data: {
                    id: id,
                    sort: sort
                },
                type: 'post',
                dataType: 'text',
                success: function (data) {
                    if (data == "1") {
                        alert("排序修改成功");
                        window.location.reload();
                    } else {
                        alert("排序修改失败");
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
                    </div>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th>排序</th>
                            <th>类别</th>
                            <th>平台类型</th>
                            <th>状态</th>
                            <th>图片</th>
                            <th>URL</th>
                            <th width="150">添加时间</th>
                            <th width="120">操作</th>
                        </tr>
                    <#list paginationData.pageItems as focus>
                        <tr>
                            <td>
                                <input class="common-input sort-input" name="sort" value="${focus.sort}" type="text"  onblur="javascript:saveSort(${focus.id}, this.value)">
                            </td>
                            <td>
                            ${focus.type!}
                            </td>
                            <td>
                            ${focus.platform!}
                            </td>
                            <td><#if focus.enabled>线上<#else><font color="red">下线</font></#if> </td>
                            <td><img src="${focus.photo}" width="160" height="160"></td>
                            <td>${focus.url}</td>
                            <td>${focus.createTime?number_to_datetime}</td>
                            <td align="center">
                                <#if focus.enabled><a class="link-update" href="javascript:operate(${focus.id},'${focus.enabled!false}')">下线</a><#else><a class="link-update" href="javascript:operate(${focus.id},'${focus.enabled!false}')">上线</a></#if>
                                &nbsp; <a class="link-update" href="./modify?id=${focus.id}">修改</a>
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