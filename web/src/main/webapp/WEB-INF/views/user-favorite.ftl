<!--用户中心右侧头部开始 -->
<div class="user_list_top">
    <ul class="user_note_order">
        <li>
            <a href="javascript:;" style="display:inline-block">最新发布</a>

            <div class="arrow_box">
                <a href="javascript:;" class="up"><em></em></a>
                <a href="javascript:;" class="down"><em></em></a>
            </div>
        </li>
        <li>
            <a href="javascript:;">点击最高</a>

            <div class="arrow_box">
                <a href="javascript:;" class="up"><em></em></a>
                <a href="javascript:;" class="down"><em></em></a>
            </div>
        </li>
        <li>
            <a href="javascript:;">评论最多</a>

            <div class="arrow_box">
                <a href="javascript:;" class="up"><em></em></a>
                <a href="javascript:;" class="down"><em></em></a>
            </div>
        </li>
    </ul>
    <div class="search ns">
        <input type="text" value="开始日期" id="from" name="from" readonly="readonly"/> 至 <input value="结束日期" type="text"
                                                                                             id="to" name="to"
                                                                                             readonly="readonly"/>
        <input type="submit" value="搜索" class="date_search_button"/>

        <div class="search_box" style="width:140px">
            <form method="post" action="#"><input type="text" value="搜索我的帖子" style="width:80px"
                                                  class="search_user"/><input type="submit" class="zoom" value=""
                                                                              style="width:20px"/></form>
        </div>
    </div>
</div>
<!--用户中心右侧头部结束 -->
<!--第1条收藏帖子开始 -->
<div class="note">
    <div class="note_right">
        <h4>首开？雷蛇Leviathan利维坦巨兽
            <div class="rank_click"><span>2587</span>点击</div>
        </h4>
        <p>
            利维坦呢，音质并不能跟很多专业的桌面2.1相比，但无论音乐电影都还算很不错的，而且是它还是一个soundbar、一个2.1音箱的集成物，不仅用于电脑，还可以用于手机、电视，用途着实不少，外观低调朴实的之中仍然不失个性，而且在它应该发挥的游戏方面的表现是符合预期的，看上去雷蛇是倾注了大量的思想来做出了这个全能的音箱，很值得夸奖
            最后响应号召，把低音炮搬到地上了。。
        </p>
        <ul class="pic_list">
            <li><a href="javascript:void(0);"><img src="upload/01.jpg"/></a></li>
            <li><a href="javascript:void(0);"><img src="upload/02.jpg"/></a></li>
            <li><a href="javascript:void(0);"><img src="upload/03.jpg"/></a></li>
            <li><a href="javascript:void(0);"><img src="upload/04.jpg"/></a></li>
            <li><a href="javascript:void(0);"><img src="upload/05.jpg"/></a></li>
            <li><a href="javascript:void(0);"><img src="upload/06.jpg"/></a></li>
            <li><a href="javascript:void(0);"><img src="upload/07.jpg"/></a></li>
            <li><a href="javascript:void(0);"><img src="upload/08.jpg"/></a></li>
            <li><a href="javascript:void(0);"><img src="upload/09.jpg"/></a></li>
        </ul>
        <a href="#" class="button_blue detail_button">查看全文</a>

        <div class="clear"></div>
        <div class="feedback_belt">
            <span class="time_b">2015年1月9日</span>
            <a href="javascript:void(0)" class="collect_b">取消收藏</a>
            <a href="javascript:void(0)" class="comment_b" onclick=""><span>258</span>条评论</a>
            <a href="javascript:void(0)" class="up_b"><span>258</span>点赞</a>
        </div>
    </div>
    <!--帖子评论区域开始 -->
    <div class="comment_area_topline"></div>
    <div class="comment_area" name="comment_area">
        <h2>发表评论</h2>

        <form class="comment_textarea">
            <textarea rows="5">请发表评论，评论中不要带有侮辱国家，他人</textarea>
            <input type="button" class="button_blue follow_comment_submit" value="发表"/>
        </form>
        <div class="clear"></div>
        <!--留言列表开始 -->
        <ul class="comment_list">
            <!--第二条留言开始 -->
            <li>
                <!--左侧头像 -->
                <div class="comment_headpic"><a href="#"><img src="upload/headpic.jpg"/></a></div>
                <!--右侧内容 -->
                <div class="comment_content">
                    <!--用户名、发布时间、楼 -->
                    <h6><a href="#" class="comment_username">任性的柚子</a><span
                            class="comment_date_floor">17分钟前[290楼]</span></h6>
                    <!--帖子正文 -->
                    <p>其实这种多应用场景的产品，设计目的是非常不错的，至少出门的时候能够携带更少的数码产品，尤其是当下每一个数码玩家的口袋里都有不少的数码产品，网络已经成为了大多数人离不开的一个沙盒世界。对于暂时没有
                        4G 设备，又想要享受到 4G 带来的网速提升玩家的口袋里都有不少的数码产品，网络已经成为了大多数人离不开的一个沙盒世界。</p>
                </div>
                <a href="javascript:void(0)" class="button_feedback">回复</a>
                <a href="javascript:void(0)" class="button_feedback_active">回复</a>
                <a href="javascript:void(0)" class="button_up">顶 (<span>1288</span>)</a>
                <!--留言回复表单 homepage_comment.js调用comment_list_textarea-ajax.html的内容显示表单对象-->
                <form class="comment_list_textarea">
                </form>
            </li>
            <!--第二条留言结束 -->
        </ul>
        <a href="#" class="comment_feedback_more">查看更多评论，请点此查看原文</a>
    </div>
    <!--帖子评论区域结束 -->
</div>
<!--第1条收藏帖子结束 -->
<script src="js/user_center_note_main.js"></script>
<script src="js/comment_list_textarea_ajax.js"></script>
<script src="js/homepage_comment.js"></script>
<script src="js/overlay.js"></script>
<script src="js/focus-blur.js"></script>
<script src="date/jquery.ui.core.js"></script>
<script src="date/jquery.ui.datepicker.js"></script>
<script src="date/jquery.ui.datepicker-zh-TW.js"></script>
<link rel="stylesheet" href="date/date.css">
<script>
    //点击评论按钮
    $(".comment_b").click(function () {
        $(".comment_area").attr("id", "");
        $(".comment_area_topline").attr("id", "");
        $(this).parents('.note').find('.comment_area').attr("id", "comment_area");
        $(this).parents(".note").find(".comment_area_topline").attr("id", "comment_area_topline");
        $('.comment_area').not('#comment_area').slideUp();
        $(".comment_area_topline").not("#comment_area_topline").css("display", "none");
        $(".comment_area").slideToggle();
        $('.comment_area_topline').slideToggle()
    });
    $(function () {
        $("#from").datepicker({
            defaultDate: "+1w",
            changeMonth: true,
            changeYear: true,
            numberOfMonths: 1,
            onClose: function (selectedDate) {
                $("#c_to").datepicker("option", "minDate", selectedDate);
            }
        });
        $("#to").datepicker({
            defaultDate: "+1w",
            changeMonth: true,
            changeYear: true,
            numberOfMonths: 1,
            onClose: function (selectedDate) {
                $("#c_from").datepicker("option", "maxDate", selectedDate);
            }
        });

    });
</script>

