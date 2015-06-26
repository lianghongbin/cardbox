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
            if(!confirm("你确定要删除该游戏的图片吗？")){
                return false;
            }
            $.ajax({
                url: '/photo/remove',// 跳转到 action
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
            <div class="crumb-list"><i class="icon-font"></i>首页<span class="crumb-step">&gt;</span>
                <a class="crumb-name" href="../game/all">游戏管理</a><span class="crumb-step">&gt;</span>
                <span class="crumb-name">游戏图片管理</span>
            </div>
        </div>

        <div class="result-wrap">
            <form name="myform" id="myform" method="post">
                <div class="result-title">
                    <div class="result-list">
                        <a href="./add?id=${game.id}&type=GAME"><i class="icon-font"></i>新增图片</a>
                    </div>
                </div>

                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <td>游戏名称</td>
                            <td>${game.name}</td>
                        </tr>
                        <tr>
                            <td>ICON</td>
                            <td><img src="${game.icon}"></td>
                        </tr>
                        <tr>
                            <td width="100">图片</td>
                            <td>
                                <table class="result-tab" width="100%">
                                <tr>
                                <#list paginationData.pageItems as photo>
                                    <#if photo_index%5==0 && photo_index!=0 ></tr>
                                    <tr></#if>
                                    <td width="20%">
                                        <table class="result-tab" width="100%">
                                            <tr>
                                                <td><img src="${photo.url}"/></td>
                                            </tr>
                                            <tr>
                                                <td align="center"><a href="javascript:del(${photo.id})">删除</a> </td>
                                            </tr>
                                        </table>

                                    </td>
                                </#list>
                                <#if paginationData.pageItems?size%5!=0>
                                    <#list 0..paginationData.pageItems?size as num>
                                        <td width="20%">&nbsp;</td>
                                    </#list>
                                </#if>
                                </tr>
                                </table>
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