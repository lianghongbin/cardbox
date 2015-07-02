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
        function del(id)
        {
            if(!confirm("你确定要删除该激活码吗？")) {
                return false;
            }

            $.ajax({
                url: '/code/remove',// 跳转到 action
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
                    class="crumb-name">激活码管理</span></div>
        </div>
        <div class="search-wrap">
            <div class="search-content">
                <form action="#" method="post">
                    <table class="search-tab">
                        <tr>
                            <th width="100">关键字搜索:</th>
                            <td><input type="hidden" name="cardId" id="cardId" value="${cardId}">
                                <input class="common-text" placeholder="关键字" name="key" value="" id="" type="text">
                            </td>
                            <td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div class="result-wrap">
            <form name="myform" id="myform" method="post">
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th width="100">手机号</th>
                            <th>激活码</th>
                            <th>所属游戏</th>
                            <th>所属礼包</th>
                            <th width="50">已分发</th>
                            <th width="50">已使用</th>
                            <th width="150">分发时间</th>
                            <th width="150">使用时间</th>
                            <th>操作</th>
                        </tr>
                    <#list paginationData.pageItems as code>
                        <tr>
                            <td>
                                ${code.phone!}
                            </td>
                            <td>
                                ${code.code!}
                            </td>
                            <td>
                                ${code.gameName!}
                            </td>
                            <td>${code.cardName!}</td>
                            <td><#if code.assigned><font color="red">是</font><#else>否</#if></td>
                            <td><#if code.used><font color="red">是</font><#else>否</#if></td>
                            <td><#if code.assignTime??> ${code.assignTime?number_to_datetime}</#if></td>
                            <td><#if code.useTime??> ${code.useTime?number_to_datetime}</#if></td>
                            <td><a class="link-update" href="javascript:del(${code.id})">删除</a></td>
                        </tr>
                    </#list>
                        <tr>
                            <td colspan="9" align="center">
                                <input class="btn btn6" onclick="history.go(-1)" value="返回" type="button">
                            </td>
                        </tr>
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