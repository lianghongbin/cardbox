<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>后台管理</title>
    <link rel="stylesheet" type="text/css" href="../css/common.css"/>
    <link rel="stylesheet" type="text/css" href="../css/main.css"/>
    <script type="text/javascript" src="../js/libs/modernizr.min.js"></script>
</head>
<body>

<!--/sidebar-->
<div class="main-wrap">

    <div class="crumb-wrap">
        <div class="crumb-list"><i class="icon-font"></i>首页<span class="crumb-step">&gt;</span><a class="crumb-name"
                                                                                                   href="./all">游戏管理</a><span
                class="crumb-step">&gt;</span><span>游戏修改</span></div>
    </div>
    <div class="result-wrap">
        <div class="result-content">
            <form id="myform" name="myform"
            ">
            <table class="insert-tab" width="100%">
                <tbody>
                <tr>
                    <th width="120">ID：</th>
                    <td>
                    ${game.id}
                    </td>
                </tr>
                <tr>
                    <th width="120">名称：</th>
                    <td>
                    ${game.name}
                    </td>
                </tr>
                <tr>
                    <th>图标：</th>
                    <td>
                        <img src="${game.icon}"/>
                    </td>
                </tr>
                <tr>
                    <th>下载地址：</th>
                    <td>
                    ${game.url}
                    </td>
                </tr>
                <tr>
                    <th>iOS ID</th>
                    <td
                ${game.isoId}
                    </td>
                </tr>
                <tr>
                    <th>包名：</th>
                    <td>
                    ${game.identifier}
                    </td>
                </tr>
                <tr>
                    <th>礼包数：</th>
                    <td>
                    ${game.total}
                    </td>
                </tr>
                <tr>
                    <th>评分：</th>
                    <td>
                    ${game.score}
                    </td>
                </tr>
                <tr>
                    <th>排序：</th>
                    <td>
                    ${game.sort}
                    </td>
                </tr>
                <tr>
                    <th>平台：</th>
                    <td>
                    ${game.platform}
                    </td>
                </tr>
                <tr>
                    <th>是否推荐：</th>
                    <td>
                    <#if game.recommend>推荐<#else>不推荐</#if>
                    </td>
                </tr>
                <tr>
                    <th>是否上线：</th>
                    <td>
                    <#if game.closed>未上线<#else>上线</#if>
                    </td>
                </tr>
                <tr>
                    <th>描述：</th>
                    <td>${game.description}</td>
                </tr>
                <tr>
                    <th></th>
                    <td>
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