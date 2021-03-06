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
    <script type="text/javascript" src="../laydate/laydate.js"></script>
    <script type="text/javascript">
        function push()
        {
            var total = ${count};
            if(!confirm("你确定要给这"+total+"个用户推送消息？推送时长依据推送数量，推送结果请查看推送服务商后台！")) {
                return false;
            }

            if($("#count").val() == 0) {
                alert("请选择推送对象！");
                return false;
            }

            if($("#title").val() == "") {
                alert("推送标题不能为空!");
                $("#title").focus();
                return false;
            }

            if($("#content").val() == "") {
                alert("推送内容不能为空！");
                $("#content").focus();
                return false;
            }

            $.ajax({
                url: '/h5/push',// 跳转到 action
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
                    class="crumb-name">H5订阅管理</span></div>
        </div>
        <div class="search-wrap">
            <div class="search-content">
                <form action="./subscribe" method="post">
                    <table class="search-tab">
                        <tr>
                            <th width="90">手机:</th>
                            <td>
                                <input type="text" size="15" name="phone" value="${phone}" placeholder="手机号">
                            </td>
                            <th width="120">开始时间:</th>
                            <td>
                                <input class="laydate-icon" name="start" value="${start}" id="start" style="width:200px;">
                            </td>
                            <th width="120">结束时间:</th>
                            <td>
                                <input class="laydate-icon" name="end" value="${end}" id="end" style="width:200px;">
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
                        <a href="../push/view"><i class="icon-font"></i>订阅推送</a>
                        <a href="../push/user"><i class="icon-font"></i>积分推送</a>
                    </div>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th>手机号</th>
                            <th>订阅时间</th>
                        </tr>
                    <#list paginationData.pageItems as subscribe>
                        <tr>
                            <td>
                                ${subscribe.phone}
                            </td>
                            <td>${subscribe.createTime?number_to_datetime}</td>
                        </tr>
                    </#list>

                        <tr>
                            <td colspan="7" align="center">
                                <input type="text" name="title" size="51" placeholder="推送标题" id="title" value="${title}"><br>
                                <textarea name="content" id="content" cols="50" rows="3" placeholder="推送内容"></textarea><br>
                                <input class="btn btn-primary" name="p" value="向当前 ${count} 位用户推送消息" onclick="push()" type="button">
                                <input type="hidden" name="start" value="${start}">
                                <input type="hidden" name="end" value="${end}">
                                <input type="hidden" name="phone" id="phone" value="${phone}">
                            </td>
                        </tr>
                    </table>
                    <div class="list-page">
                        <nav style="float:right;">
                            ${count} 条订阅 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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


<script>
    var start = {
        elem: '#start',
        format: 'YYYY-MM-DD hh:mm:ss',
        min: '2015-07-01 00:00:00', //设定最小日期为当前日期
        max: '2099-06-16 23:59:59', //最大日期
        istime: true,
        istoday: false,
        choose: function (datas) {
            end.min = datas; //开始日选好后，重置结束日的最小日期
            end.start = datas //将结束日的初始值设定为开始日
        }
    };
    var end = {
        elem: '#end',
        format: 'YYYY-MM-DD hh:mm:ss',
        min: '2015-07-01 00:00:00',
        max: '2099-06-16 23:59:59',
        istime: true,
        istoday: false,
        choose: function (datas) {
            start.max = datas; //结束日选好后，重置开始日的最大日期
        }
    };
    laydate(start);
    laydate(end);

</script>
</body>
</html>