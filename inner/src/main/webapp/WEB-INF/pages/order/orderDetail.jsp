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
  <title>订单详情</title>
  <%@include file="../header.jsp"%>
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <link href="css/sb-admin-2.css" rel="stylesheet">
  <link href="font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
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
      <div class="col-lg-12">
        <h3 class="page-header">订单详情</h3>
      </div>
      <!-- /.col-lg-12 -->
    </div>

    <div class="row">
      <div class="col-md-6">
        <ul>
          <li>订单时间:<fmt:formatDate value="${order.createTime}" type="both"/></li>
          <li>路线:${order.start}(${order.startCity})-${order.end}(${order.endCity})</li>
          <li>价格:${order.price},${order.distance}公里</li>
          <li>约车时间:${order.time}</li>

        </ul>
      </div>
      <div class="col-md-6">
        <ul>
          <li>订单状态:
            <c:choose>
              <c:when test="${order.status == -3}">争议</c:when>
              <c:when test="${order.status == -2}">订单已取消</c:when>
              <c:when test="${order.status == -1}">乘客申请取消(取消中)</c:when>
              <c:when test="${order.status == 0}">原始状态</c:when>
              <c:when test="${order.status == 1}">已有司机申请</c:when>
              <c:when test="${order.status == 2}">选择司机后待支付</c:when>
              <c:when test="${order.status == 3}">支付完成</c:when>
              <c:when test="${order.status == 4}">乘客确认搭乘</c:when>
              <c:when test="${order.status == 5}">订单结束</c:when>
              <c:otherwise>未定义的状态值</c:otherwise>
            </c:choose>
          </li>
          <li>评论:${order.comment}</li>
          <li>取消说明:${order.cancelRemark}</li>
        </ul>
      </div>
    </div>

    <h3>乘客信息</h3>
    <div class="row">
      <div class="col-md-6">
        <ul>
          <li>用户名:${user.username}</li>
          <li>手机:${user.mobile}</li>
          <li>年龄:${user.age}</li>
          <li>性别:${user.gender}</li>
          <li>常用地点:${user.locations}</li>
        </ul>
      </div>
    </div>

    <h3>车主信息</h3>

    <div class="row">
      <div class="col-md-6">
        <ul>
          <li>用户名:${driver.username}</li>
          <li>手机:${driver.mobile}</li>
          <li>年龄:${driver.age}</li>
          <li>性别:${driver.gender}</li>
          <li>常用地点:${driver.locations}</li>
        </ul>
      </div>
      <c:if test="${car != null}">
        <div class="col-md-6">
          <ul>
            <li>车牌:${car.carnumber}</li>
            <li>车:${car.brandname},${car.seriesname}</li>
            <li>创建时间:${car.create_time}</li>
          </ul>
        </div>
      </c:if>

    </div>

  </div>

</div>

</body>
</html>
