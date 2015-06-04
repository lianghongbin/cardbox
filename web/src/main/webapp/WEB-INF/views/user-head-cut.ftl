<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>无标题文档</title>
    <script src="../../../headcutter/jquery.js"></script>
    <script src="../../../headcutter/jquery.Jcrop.min.js"></script>
    <script src="../../../headcutter/custom.js"></script>
    <script>
        $(document).ready(function (e) {
            var boxW = $(".jcrop-holder").width();
            var boxH = $(".jcrop-holder").height();
            if (boxW > boxH) {
                $(".jcrop-holder").css("margin-top", 149 - boxH / 2);
                $("#preview-pane").css("margin-left", 340)
            }
            else if (boxW < boxH) {
                $(".jcrop-holder").css("margin-left", 149 - boxW / 2);
                $("#preview-pane").css("margin-left", 191 + boxW / 2)
            }
        });
    </script>
    <link rel="stylesheet" href="../../../headcutter/jquery.Jcrop.min.css" type="text/css"/>
    <link href="../../../headcutter/custom.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div id="headpic_cutter">
    <div class="original_picbox">
        <img src="../../../upload/beauty.jpg" id="target"/>
    </div>

    <div id="preview-pane">
        <div style="color:#2175d0; clear:both;margin-bottom:20px; width:280px; line-height:1.5em;">
            您上传的图片将会自动生成三种尺寸头像，请注意中小尺寸的头像是否清晰。
        </div>
        <div style="float:left">
            <div class="preview-container_big">
                <img src="../../../upload/beauty.jpg" class="jcrop-preview"/>
            </div>
            <div style="text-align:center">大尺寸头像180×180像素</div>
        </div>
        <div style="float:left; margin-left:40px">
            <div class="preview-container">
                <img src="../../../upload/beauty.jpg" class="jcrop-preview"/>
            </div>
            <div style="width:64px; text-align:center;">中尺寸头像50×50像素(自动生成)</div>
            <div class="preview-container_small">
                <img src="../../../upload/beauty.jpg" class="jcrop-preview"/>
            </div>
            <div style="width:64px; text-align:center;">小尺寸头像30×30像素(自动生成)</div>
        </div>
    </div>
</div>
</body>
</html>
