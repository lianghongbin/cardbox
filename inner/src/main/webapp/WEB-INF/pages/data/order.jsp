<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dongzhaocheng
  Date: 2015/5/14
  Time: 9:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>订单数据</title>
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
      <div class="col-md-3">
        时间:<input type="text" id="begin">
      </div>
      <div class="col-md-3">
        至<input type="text" id="end">
      </div>
      <div class="col-md-3">
        城市:
        <select id="city">
          <option value="全部">全部</option>
          <option value="北京">北京</option>
          <option value="上海">上海</option>
          <option value="广州">广州</option>
          <option value="深圳">深圳</option>
          <option value="石家庄">石家庄</option>
        </select>
      </div>
    </div>
    <div class="row">
      <div class="col-md-3">
        <button class="btn btn-primary" onclick="mainQuery()">查询</button>
      </div>
    </div>

    <div class="row">
      <table class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
          <td>日期</td>
          <td>完成订单</td>
          <td>交易额</td>
          <td>发起订单</td>
          <td>已取消:无人接单</td>
          <td>已取消:未选择</td>
          <td>已取消:未付款</td>
          <td>已取消:付款</td>
          <td>争议</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${dataList}" var="orderData">
          <tr>
            <td>${orderData.date}</td>
            <td>${orderData.complete}</td>
            <td>${orderData.income}</td>
            <td>${orderData.total}</td>
            <td>${orderData.noCar}</td>
            <td>${orderData.noSelect}</td>
            <td>${orderData.noPay}</td>
            <td>${orderData.cancelAgree}</td>
            <td>${orderData.argument}</td>
          </tr>
        </c:forEach>
        </tbody>

      </table>
    </div>

  </div>

</div>
</body>

<script src="js/jquery-1.11.0.js"></script>
<script src="js/jquery-ui.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/sb-admin-2.js"></script>

<script type="text/javascript">
  $(init());

  function init(){
    $( "#begin" ).datepicker().datepicker("option", "dateFormat","yy-mm-dd").val("${begin}");
    $( "#end" ).datepicker().datepicker("option", "dateFormat","yy-mm-dd").val("${end}");
  }

  function mainQuery(){
    var beginTxt = $("#begin").val();
    var endTxt = $("#end").val();
    var cityTxt = $("#city").val();
    window.location.href = "data/order.do?begin="+beginTxt+"&end="+endTxt+"&city="+cityTxt;
  }
</script>
</html>
