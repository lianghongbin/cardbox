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
    <script>
        function del(id)
        {
            confirm("你确定要删除该反馈吗？");
            $.ajax({
                url: '/feedback/remove',// 跳转到 action
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
                    class="crumb-name">反馈管理</span></div>
        </div>
        <div class="result-wrap">
            <form name="myform" id="myform" method="post">
                <div class="result-title">
                    <div class="result-list">
                        <a id="batchDel" href="javascript:void(0)"><i class="icon-font"></i>批量删除</a>
                    </div>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th>手机号</th>
                            <th>反馈内容</th>
                            <th>已处理</th>
                            <th>处理意见</th>
                            <th width="150">反馈时间</th>
                            <th width="150">处理时间</th>
                            <th width="150">操作</th>
                        </tr>
                    <#list paginationData.pageItems as feedback>
                        <tr>
                            <td>
                                ${feedback.phone}
                            </td>
                            <td>${feedback.content}</td>
                            <td><#if feedback.processed>是<#else>否</#if> </td>
                            <td>${feedback.remark}</td>
                            <td><#if feedback.precessed>${feedback.processTime?number_to_datetime}</#if></td>
                            <td>${feedback.createTime?number_to_datetime}</td>
                            <td align="center">
                                &nbsp; <#if !feedback.precessed> <a class="link-update" href="./find?id=${feedback.id}">处理</a></#if>
                                &nbsp; <a class="link-update" href="javascript:del(${feedback.id})">删除</a>
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