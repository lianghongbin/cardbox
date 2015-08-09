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
        function push()
        {
            var total = ${count};

            if(!confirm("你确定要给这"+total+"个用户推送消息？推送时长依据推送数量，推送结果请查看推送服务商后台！")) {
                return false;
            }

            if($("#count").val() ==0) {
                alert("请选择推送对象！");
                return false;
            }

            if($("#content").val() == "") {
                alert("推送内容不能为空！");
                return false;
            }

            $.ajax({
                url: '/push/userpush',// 跳转到 action
                data: $('#myform').serialize(),// 你的formid,
                type: 'post',
                dataType: 'text',
                success: function (data) {
                    if (data == "0") {
                        alert("异步推送进行中......");
                    } else {
                        alert("推送失败");
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
                    class="crumb-name">积分推送</span></div>
        </div>
        <div class="search-wrap">
            <div class="search-content">
                <form action="./user" method="get">
                    <table class="search-tab">
                        <tr>
                            <th width="90">最低分:</th>
                            <td>
                                <input type="text" size="18" name="min" value="${min}" placeholder="推送最低分">
                            </td>
                            <th width="90">最高分:</th>
                            <td>
                                <input type="text" size="15" name="max" value="${max}" placeholder="推送最高分">
                            </td>
                            <td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div class="result-wrap">
            <form name="myform" id="myform" method="post">
                <div class="result-title">
                    <div class="result-list">
                        <a href="./view"><i class="icon-font"></i>订阅推送</a>
                    </div>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th width="50">手机号</th>
                            <th width="30">积分</th>
                            <th width="150">注册时间</th>
                            <th width="150">最后登录</th>
                        </tr>
                    <#list paginationData.pageItems as user>
                        <tr>
                            <td>
                                ${user.phone}
                            </td>
                            <td>
                                ${user.score}
                            </td>
                            <td>
                                ${user.createTime?number_to_datetime}
                            </td>
                            <td><#if user.lastTime??> ${user.lastTime?number_to_datetime}</#if></td>
                        </tr>
                    </#list>
                        <tr>
                            <td colspan="4" align="center">
                                <textarea name="content" id="content" cols="50" rows="4" placeholder="推送内容"></textarea><br>
                                <input class="btn btn-primary" name="p" value="向当前 ${count} 位用户推送消息" onclick="push()" type="button">
                                <input type="hidden" name="min" value="${min}">
                                <input type="hidden" name="max" value="${max}">
                                <input type="hidden" name="count" id="count" value="${count}">
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