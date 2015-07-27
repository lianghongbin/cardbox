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
        function del(id) {
            if (!confirm("你确定要删除该管理员吗？")) {
                return false;
            }
            $.ajax({
                url: '/admin/remove',// 跳转到 action
                data: {
                    id: id
                },
                type: 'get',
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

        function lock(id, flag) {
            var url = "/admin/lock";
            if (flag) {
                url = "/admin/unlock";
            }

            $.ajax({
                url: url,// 跳转到 action
                data: {
                    id: id
                },
                type: 'get',
                dataType: 'text',
                success: function (data) {
                    if (data == "1") {
                        alert("操作成功");
                        window.location.reload();
                    } else {
                        alert("操作失败");
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
                    class="crumb-name">操作员管理</span></div>
        </div>
        <div class="result-wrap">
            <form name="myform" id="myform" method="post">
                <div class="result-title">
                    <div class="result-list">
                        <a href="./add"><i class="icon-font"></i>新增操作员</a>
                    </div>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th>姓名</th>
                            <th>手机</th>
                            <th>超级管理员</th>
                            <th>锁定</th>
                            <th>最后登录时间</th>
                            <th width="150">操作</th>
                        </tr>
                    <#list admins as admin>
                        <tr>
                            <td>
                            ${admin.username!}
                            </td>
                            <td>
                            ${admin.phone}
                            </td>
                            <td>
                                <#if admin.top><font color="red">是</font><#else>否</#if>
                            </td>
                            <td>
                                <#if admin.locked><font color="red">是</font><#else>否${admin.locked}</#if>
                            </td>
                            <td><#if admin.lastTime??>${admin.lastTime?number_to_datetime}</#if></td>
                            <td align="center">
                                <#if phone != admin.phone>
                                    <a class="link-update" href="javascript:del(${admin.id})">删除</a>
                                    &nbsp; &nbsp;<a class="link-update"
                                                    href="javascript:lock(${admin.id}, ${admin.locked?string('true','false')})"><#if admin.locked>
                                    解锁<#else>锁定</#if></a>
                                </#if>
                            </td>
                        </tr>
                    </#list>
                    </table>
                </div>
            </form>
        </div>
    </div>
    <!--/main-->
</div>
</body>
</html>