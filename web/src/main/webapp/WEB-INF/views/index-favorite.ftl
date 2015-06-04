<div class="left">
    <a href="javascript:void(0)" class="message_box">10条新更新，点击查看</a>

    <div class="read_filter">
        <form>
            <input type="checkbox" id="checkall"/><label for="checkall">查看全部</label>
            <input type="checkbox" id="checkessence"/><label for="checkessence">查看精华帖</label>
            <input type="checkbox" id="checkmale"/><label for="checkmale">查看男生贴</label>
            <input type="checkbox" id="checkfemale"/><label for="checkfemale">查看女生贴</label>
            <input type="checkbox" id="checkvideo"/><label for="checkvideo">查看视频贴</label>
            <input type="checkbox" id="checkpic"/><label for="checkpic">查看图片贴</label>
            <input type="checkbox" id="checktext"/><label for="checktext">查看文字帖</label>
        </form>
    </div>
    <!--第1条帖子开始 -->
    <div class="note">
        <div class="note_left">
            <div class="note_headpic">
                <img src="images/headpic.jpg"/>
            </div>
            <div class="note_username">
                黑武士
            </div>
            <div class="note_user_info">
                等级：<span>31</span>级<br/>
                关注：<span>522</span><br/>
                粉丝：<span>1258</span><br/>
                作品： <span>40</span><br/>
            </div>
            <div class="cancel">
                <a href="javascript:void(0)">[取消关注]</a>
            </div>
        </div>
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
            <a href="#" class="button_mid_blue detail_button">查看全文</a>

            <div class="clear"></div>
            <div class="feedback_belt">
                <span class="time_b">32分钟前</span>
                <a href="javascript:void(0)" class="collect_b">收藏</a>
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
                        <p>
                            其实这种多应用场景的产品，设计目的是非常不错的，至少出门的时候能够携带更少的数码产品，尤其是当下每一个数码玩家的口袋里都有不少的数码产品，网络已经成为了大多数人离不开的一个沙盒世界。对于暂时没有
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
    <!--第1条帖子结束 -->
</div>
<div class="right">
    <a href="#" class="want_pub">我要发帖</a>
</div>
<script>
    //点击评论按钮
    $(".comment_b").click(function () {
        $(".comment_area").attr("id", "");
        $(".comment_area_topline").attr("id", "");
        $(this).parents('.note').find('.comment_area').attr("id", "comment_area");
        $(this).parents(".note").find(".comment_area_topline").attr("id", "comment_area_topline");
        $('.comment_area').not('#comment_area').slideUp();
        $(".comment_area_topline").not("#comment_area_topline").css("display", "none");
        $("#comment_area").slideToggle();
        $('#comment_area_topline').slideToggle()
    });
</script>
<script src="js/overlay.js"></script>
<script src="js/comment_list_textarea_ajax.js"></script>
<script src="js/homepage_comment.js"></script>