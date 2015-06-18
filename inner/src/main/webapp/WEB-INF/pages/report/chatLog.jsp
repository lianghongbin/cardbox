<%--
  Created by IntelliJ IDEA.
  User: dongzhaocheng
  Date: 2015/5/21
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>聊天记录</title>
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
        <h3 class="page-header">举报-聊天记录</h3>
      </div>
      <!-- /.col-lg-12 -->
    </div>

    <div class="row">
      <div class="col-md-3">
        <h4>举报人:${response.reporterInfo}</h4>
      </div>
      <div class="col-md-3">
        <h4>被举报人:${response.reportedInfo}</h4>
      </div>
    </div>
    <br>
    <div class="row">
      <div class="col-md-6">
        <table class="table">
          <tr><th>聊天记录</th></tr>
          <c:forEach items="${response.chatLog}" var="chatInfo">
            <tr><td>
            <c:choose>
              <c:when test="${chatInfo.sendId==response.reporterId}">
                ${fn:split(response.reporterInfo, ',')[0]}(<fmt:formatDate value="${chatInfo.sendTime}" type="both"/>):${chatInfo.message}(<c:choose><c:when test="${chatInfo.isRead}">已读</c:when><c:otherwise>未读</c:otherwise></c:choose>)
              </c:when>
              <c:when test="${chatInfo.sendId==response.reportedId}">
                ${fn:split(response.reportedInfo,',')[0]}(<fmt:formatDate value="${chatInfo.sendTime}" type="both"/>):<i style="color: rgba(255, 24, 19, 0.94);">${chatInfo.message}</i>(<c:choose><c:when test="${chatInfo.isRead}">已读</c:when><c:otherwise>未读</c:otherwise></c:choose>)
              </c:when>
              <c:otherwise>
                ${chatInfo.sendId}(<fmt:formatDate value="${chatInfo.sendTime}" type="both"/>):${chatInfo.message}(<c:choose><c:when test="${chatInfo.isRead}">已读</c:when><c:otherwise>未读</c:otherwise></c:choose>)
              </c:otherwise>
            </c:choose>
            </td></tr>
          </c:forEach>
        </table>
      </div>
    </div>

  </div>

</div>

</body>
</html>

