<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: dongzhaocheng
  Date: 2015/4/29
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>评价管理</title>
  <%@include file="../header.jsp"%>
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <link href="css/sb-admin-2.css" rel="stylesheet">
  <link href="font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  <link rel="stylesheet" href="css/jquery-ui.css" >
</head>
<body>
<div id="wrapper">
  <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
    <div class="navbar-header">
      <a class="navbar-brand" href="common/dashboard.do">同行</a>
    </div>
    <!-- /.navbar-header -->
    <!-- /.navbar-top-links -->
    <%@include file="../sidebar.jsp"%>

    <!-- /.navbar-static-side -->
  </nav>
  <div id="page-wrapper">
    <div class="row">
      <div class="col-md-3">时间:<input type="text" id="begin"></div>
      <div class="col-md-3">至<input type="text" id="end"></div>
      <div class="col-md-3">关键词:<input type="text" id="keyword" placeholder="用户名/手机"></div>
      <div class="col-md-3">城市<select id="city">
        <option value="全部">全部</option>
        <option value="北京">北京</option>
        <option value="上海">上海</option>
        <option value="广州">广州</option>
        <option value="深圳">深圳</option>
        <option value="石家庄">石家庄</option>
        <option value="大连">大连</option>
        <option value="未开通">未开通</option>
      </select></div>
    </div>
    <div class="row">
      <div class="col-md-3">星级:
        <select id="star">
          <option value="6">全部</option>
          <option value="5">五星</option>
          <option value="4">五星以下</option>
        </select>
      </div>
      <div class="col-md-3">状态
        <select id="status">
        <option value="-1">全部</option>
        <option value="0">待处理</option>
        <option value="1">已处理</option>
        <option value="2">已忽略</option>
        </select>
      </div>

    </div>
    <div class="row">
      <button class="btn btn-primary" onclick="mainQuery()">查询</button>
    </div>

    <div>
      <a href="comment/list.do?page=${page-1}&rows=${rows}&city=${city}&begin=${begin}&end=${end}&keyword=${keyword}&status=${status}&score=${score}">前一页</a>
      <a>${page}</a>
      <a href="comment/list.do?page=${page+1}&rows=${rows}&city=${city}&begin=${begin}&end=${end}&keyword=${keyword}&status=${status}&score=${score}">${page+1}</a>
      <a href="comment/list.do?page=${page+2}&rows=${rows}&city=${city}&begin=${begin}&end=${end}&keyword=${keyword}&status=${status}&score=${score}">${page+2}</a>
      <a href="acomment/list.do?page=${page+3}&rows=${rows}&city=${city}&begin=${begin}&end=${end}&keyword=${keyword}&status=${status}&score=${score}">${page+3}</a>
      <a href="comment/list.do?page=${page+1}&rows=${rows}&city=${city}&begin=${begin}&end=${end}&keyword=${keyword}&status=${status}&score=${score}">后一页</a>

      <input id="num" type="text">
      <input type="button" value="跳" onclick="jump()">

    </div>

    <div class="row">
      <table class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
          <td>ID</td>
          <td>订单ID</td>
          <td>乘客信息</td>
          <td>车主信息</td>
          <td>搭乘时间</td>
          <td>评分</td>
          <td>评论内容</td>
          <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${commentList}" var="comment">
          <tr>
            <td>${comment.id}</td>
            <td><a target="_blank" href="order/detail.do?orderId=${comment.orderId}">${comment.orderId}</a></td>
            <td>${comment.passengerName},${comment.passengerPhone}</td>
            <td>${comment.driverName},${comment.driverPhone}</td>
            <td>${comment.departureTime}</td>
            <td>${comment.score}</td>
            <td>${comment.content}</td>
            <td>
              <c:choose>
                <c:when test="${comment.status == 0}"><button class="btn btn-mini" onclick="commentDone(this)">信用-1</button>
                  <button class="btn btn-mini" onclick="commentIgnore(this)">忽略</button> </c:when>
                <c:when test="${comment.status == 1}">已处理</c:when>
                <c:when test="${comment.status == 2}">已忽略</c:when>
                <c:otherwise>未定义的状态</c:otherwise>
              </c:choose>

            </td>
          </tr>
        </c:forEach>

        </tbody>

      </table>

    </div>

  </div>

</div>

</body>

<!-- jQuery Version 1.11.0 -->
<script src="js/jquery-1.11.0.js"></script>
<script src="js/jquery-ui.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap.min.js"></script>
<!-- Custom Theme JavaScript -->
<script src="js/sb-admin-2.js"></script>

<script type="text/javascript">
  $(init());

  function init(){
    $("#city").val("${city==null ? '全部' : city}");
    $("#status").val("${status==null ? 0 : status}");
    $("#star").val("${score==null ? '6' : score}");
    $( "#begin" ).datepicker().datepicker("option", "dateFormat","yy-mm-dd").val("${begin}");
    $( "#end" ).datepicker().datepicker("option", "dateFormat","yy-mm-dd").val("${end}");
  }

  function commentDone(obj){
    var id = $($(obj).parent().parent().children()[0]).text();
    ajaxQ(id,'/done.do');
  }

  function commentIgnore(obj){
    var id = $($(obj).parent().parent().children()[0]).text();
    ajaxQ(id,'/ignore.do');
  }

  function ajaxQ(id,url){
    $.ajax({
      type:"POST",
      dataType:"json",
      url:"comment/"+url,
      data:"id="+id,
      success:function(data){
        alert(data.message);
        mainQuery();
      },
      error:function(xml){
        alert("服务器错误");
        mainQuery();
      }
    });
  }

  function mainQuery(){
    var beginTxt = $("#begin").val();
    var endTxt = $("#end").val();
    var keyTxt = $("#keyword").val();
    var cityTxt = $("#city").val();
    var starTxt = $("#star").val();
    var statusTxt = $("#status").val();

    var para = "?page=${page}&rows=${rows}&begin="+beginTxt+"&end="+endTxt+"&keyword="+keyTxt+"&city="+cityTxt+"&score="+starTxt+"&status="+statusTxt;
    window.location.href = "comment/list.do"+para;
  }

  function jump(){
    var n = $("#num");
    window.location.href = "comment/list.do?page="+ n.val()+"&rows=${rows}";
  }
</script>
</html>
