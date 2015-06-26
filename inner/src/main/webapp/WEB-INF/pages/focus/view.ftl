<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>后台管理</title>
    <link rel="stylesheet" type="text/css" href="../css/common.css"/>
    <link rel="stylesheet" type="text/css" href="../css/main.css"/>
    <link rel="stylesheet" type="text/css" href="../tcal/tcal.css"/>
    <script type="text/javascript" src="../js/libs/modernizr.min.js"></script>
    <script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
</head>
<body>

<!--/sidebar-->
<div class="main-wrap">

    <div class="crumb-wrap">
        <div class="crumb-list"><i class="icon-font"></i>首页<span class="crumb-step">&gt;</span><a class="crumb-name"
                                                                                                   href="./all">礼包管理</a><span
                class="crumb-step">&gt;</span><span>礼包详情</span></div>
    </div>
    <div class="result-wrap">
        <div class="result-content">
            <form id="myform" name="myform"
            ">
            <table class="insert-tab" width="100%">
                <tbody>
                <tr>
                    <th>所属游戏：</th>
                    <td>
                    ${card.gameName}
                    </td>
                </tr>
                <tr>
                    <th width="120">名称：</th>
                    <td>
                    ${card.name}
                    </td>
                </tr>
                <tr>
                    <th>图标：</th>
                    <td>
                    <img src="{card.icon}"/>
                    </td>
                </tr>
                <tr>
                    <th>礼包数量：</th>
                    <td>${card.total}
                    </td>
                </tr>
                <tr>
                    <th>领取数量：</th>
                    <td>
                        <font color="red">${card.assignTotal} </font>
                    </td>
                </tr>
                <tr>
                    <th>礼包类别：</th>
                    <td>
                    ${card.type}
                    </td>
                </tr>
                <tr>
                    <th>分数：</th>
                    <td>${card.score}</td>
                </tr>
                <tr>
                    <th>是否推荐：</th>
                    <td>
                    <#if card.recommend>推荐<#else >不推荐</#if>
                    </td>
                </tr>
                <tr>
                    <th>是否上线：</th>
                    <td>
                        <#if card.closed>关闭<#else>上线</#if>
                    </td>
                </tr>
                <tr>
                    <th>描述：</th>
                    <td>${card.description}
                    </td>
                </tr>
                <tr>
                    <th>使用流程：</th>
                    <td>${card.flow}
                    </td>
                </tr>
                <tr>
                    <th>开放时间：</th>
                    <td>${card.openTime?number_to_date}</td>
                </tr>
                <tr>
                    <th>截止时间：</th>
                    <td>${card.expireTime?number_to_date}</td>
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