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
  <title>订单列表</title>
  <%@include file="../header.jsp"%>
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <link href="css/sb-admin-2.css" rel="stylesheet">
  <link href="font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  <link rel="stylesheet" href="css/jquery-ui.css" >
</head>
<body>

<div id="wrapper">

  <!-- Navigation -->
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
    <h2>订单列表</h2>
    <div class="row">
      <div class="col-md-3">时间:<input type="text" id="begin" value="${begin}"></div>
      <div class="col-md-3">至<input type="text" id="end" value="${end}"></div>
      <div class="col-md-3">关键词:<input type="text" id="keyword" value="${keyword}"></div>
      <div class="col-md-3">
        <select id="city">
          <option value="全部">全部</option>
          <option value="北京市">北京市</option>
          <option value="上海市">上海市</option>
          <option value="广州市">广州市</option>
          <option value="深圳市">深圳市</option>
          <option value="石家庄市">石家庄市</option>
          <option value="大连市">大连市</option>
          <option value="未开通">未开通</option>
        </select>
      </div>
    </div>

    <div class="row">
      <div class="col-md-2">进行状态:</div>
      <div class="col-md-3">
        <select id="status">
          <option value="100">全部</option>
          <option value="0">正常</option>
          <option value="1">已有司机申请</option>
          <option value="2">已经选择司机</option>
          <option value="3">订单支付中</option>
          <option value="4">乘客已经支付</option>
          <option value="5">乘客确认搭乘</option>
          <option value="6">订单完成</option>
          <option value="60">订单未确认超时自动完成</option>
          <option value="61">争议订单乘客获胜完成</option>
          <option value="62">争议订单司机获胜完成</option>
          <option value="-310">乘客申请取消,司机没有操作并超时</option>
          <option value="-300">争议订单状态</option>
          <option value="-2">司机同意取消</option>
          <option value="-13">已经支付后取消</option>
          <option value="-12">已经选择司机后取消</option>
          <option value="-11">已有司机申请状态下取消</option>
          <option value="-10">正常状态下取消</option>
          <option value="-100">无司机接单超时</option>
          <option value="-110">司机申请后乘客没选择超时</option>
          <option value="-120">选择司机后没支付超时</option>
        </select>
      </div>
    </div>


    <div class="row">
      <button class="btn-primary" onclick="mainQuery()">查询</button>
    </div>

    <div>
      <a href="order/list.do.do?page=${page-1}&rows=${rows}&city=${city}&begin=${begin}&end=${end}&keyword=${keyword}&status=${status}">前一页</a>
      <a>${page}</a>
      <a href="order/list.do.do?page=${page+1}&rows=${rows}&city=${city}&begin=${begin}&end=${end}&keyword=${keyword}&status=${status}">${page+1}</a>
      <a href="order/list.do.do?page=${page+2}&rows=${rows}&city=${city}&begin=${begin}&end=${end}&keyword=${keyword}&status=${status}">${page+2}</a>
      <a href="order/list.do.do?page=${page+3}&rows=${rows}&city=${city}&begin=${begin}&end=${end}&keyword=${keyword}&status=${status}">${page+3}</a>
      <a href="order/list.do.do?page=${page+1}&rows=${rows}&city=${city}&begin=${begin}&end=${end}&keyword=${keyword}&status=${status}">后一页</a>

      <input id="num" type="text">
      <input type="button" value="跳" onclick="jump()">

    </div>

    <div class="row">
      <table class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
          <td>订单号</td>
          <td>下单时间</td>
          <td>接单时间</td>
          <td>乘客信息</td>
          <td>车主信息</td>
          <td>路线</td>
          <td>订单金额</td>
          <td>搭乘时间</td>
          <td>取消时间</td>
          <td>取消理由</td>
          <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${orderList}" var="orderInfo">
          <tr>
            <td><a target="_blank" href="order/detail.do?orderId=${orderInfo.id}">${orderInfo.id}</a></td>
            <td><fmt:formatDate value="${orderInfo.createTime}" type="both"/></td>
            <td>接单时间</td>
            <td>${orderInfo.userId}</td>
            <td>${orderInfo.driverId}</td>
            <td>${orderInfo.start}-${orderInfo.end},${orderInfo.distance}KM</td>
            <td>${orderInfo.price}</td>
            <td>${orderInfo.time}</td>
            <td>取消时间</td>
            <td>${orderInfo.cancelRemark}</td>
            <td><c:if test="${orderInfo.status == -300}">
              <button class="btn-primary btn-sm" onclick="payToCar(this)">支付</button>
              <button class="btn-primary btn-sm" onclick="backToUser(this)">返还</button><br>
              <button class="btn-primary btn-sm">支付-1</button>
              <button class="btn-primary btn-sm">返还-1</button>
            </c:if> </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>

    </div>

  </div>
</div>

<script src="js/jquery-1.11.0.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/sb-admin-2.js"></script>
<script src="js/jquery-ui.js"></script>

<script type="text/javascript">

  //100表示全部类型
  $(init());


  function init(){
    $("#city").val("${city==null ? '全部' : city}");
    $("#status").val("${status==null ? -300:status}");
    $( "#begin" ).datepicker().datepicker("option", "dateFormat","yy-mm-dd").val("${begin}");
    $( "#end" ).datepicker().datepicker("option", "dateFormat","yy-mm-dd").val("${end}");
  }

  function mainQuery(){

    var beginTxt = $("#begin").val();
    var endTxt = $("#end").val();
    var keyTxt = $("#keyword").val();
    var cityTxt = $("#city").val();
    var statusInt = $("#status").val();
    var para = "?page=${page}&rows=${rows}&city="+cityTxt+"&begin="+beginTxt+"&end="+endTxt+"&keyword="+keyTxt+"&status="+statusInt;
    window.location.href = "order/list.do"+para;
  }


  function jump(){
    var n = $("#num");
    window.location.href = "order/list.do?page="+ n.val()+"&rows=${rows}";
  }

  function payToCar(obj){
    var orderId = $($(obj).parent().parent().children()[0]).text();
    $.ajax({
      type:"POST",
      dataType:"json",
      url:"order/payCarOwner.do",
      data:"orderId="+orderId,
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

  function backToUser(obj){
    var orderId = $($(obj).parent().parent().children()[0]).text();
    $.ajax({
      type:"POST",
      dataType:"json",
      url:"order/payUser.do",
      data:"orderId="+orderId,
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

</script>
</body>
</html>
