<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dongzhaocheng
  Date: 2015/4/29
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>车主审核</title>
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
    <div class="row">
      <div class="col-lg-12">
        <h1 class="page-header">车主审核</h1>
      </div>
      <!-- /.col-lg-12 -->
    </div>
    <div class="row">
      <div class="col-lg-3">
        时间:
        <input id="begin" type="text" value="${begin}">
      </div>
      <div class="col-lg-3">
        至
        <input id="end" type="text" value="${end}">
      </div>
      <div class="col-lg-3">
        状态:<select id="status">
          <option value="100">全部</option>
          <option value="0">未上传</option>
          <option value="1">待审核</option>
          <option value="2">已通过</option>
          <option value="-1">已拒绝</option>
        </select>
      </div>
    </div>


    <div class="row">
      <div class="col-lg-3">关键字:<input id="keyword" type="text" placeholder="手机号,车牌号" value="${keyword}"></div>
    </div>

    <div class="row">
      <div class="col-lg-3"><button class="btn btn-success btn-sm" onclick="mainQuery()">查询</button></div>
    </div>

    <div>
      <a href="account/carAudit.do?page=${page-1}&rows=${rows}&city=${city}&begin=${begin}&end=${end}&keyword=${keyword}&status=${status}">前一页</a>
      <a>${page}</a>
      <a href="account/carAudit.do?page=${page+1}&rows=${rows}&city=${city}&begin=${begin}&end=${end}&keyword=${keyword}&status=${status}">${page+1}</a>
      <a href="account/carAudit.do?page=${page+2}&rows=${rows}&city=${city}&begin=${begin}&end=${end}&keyword=${keyword}&status=${status}">${page+2}</a>
      <a href="account/carAudit.do?page=${page+3}&rows=${rows}&city=${city}&begin=${begin}&end=${end}&keyword=${keyword}&status=${status}">${page+3}</a>
      <a href="account/carAudit.do?page=${page+1}&rows=${rows}&city=${city}&begin=${begin}&end=${end}&keyword=${keyword}&status=${status}">后一页</a>

      <input id="num" type="text">
      <input type="button" value="跳" onclick="jump()">

    </div>

    <div class="row">
      <table class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
          <td><b>ID</b></td>
          <td><b>姓名,手机号</b></td>
          <td><b>车系</b></td>
          <td><b>车牌号</b></td>
          <td><b>车辆照片1</b></td>
          <td><b>车辆照片2</b></td>
          <td><b>驾驶证</b></td>
          <td><b>行驶证</b></td>
          <td><b>备注</b></td>
          <td><b>操作</b></td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${carOwnerList}" var="carOwnerInfo">
          <tr>
            <td>${carOwnerInfo.id}</td>
            <td>${carOwnerInfo.userInfo}</td>
            <td>${carOwnerInfo.brandname},${carOwnerInfo.seriesname}</td>
            <td>${carOwnerInfo.carnumber}</td>
            <td><a href="${carOwnerInfo.carimage1}" target="_blank"><img src="${carOwnerInfo.carimage1}" width="40" height="30"></a></td>
            <td><a href="${carOwnerInfo.carimage2}" target="_blank"><img src="${carOwnerInfo.carimage2}"  width="40" height="30"></a></td>
            <td><a href="${carOwnerInfo.driverlicense}" target="_blank"><img src="${carOwnerInfo.driverlicense}" width="40" height="30"></a></td>
            <td><a href="${carOwnerInfo.runninglicense}" target="_blank"><img src="${carOwnerInfo.runninglicense}" width="40" height="30"></a></td>
            <td><input type="text" value="${carOwnerInfo.remark}"></td>
            <td>
              <c:choose>
                <c:when test="${carOwnerInfo.status==0}">未提交</c:when>
                <c:when test="${carOwnerInfo.status==1}"><div>
                  <button class="btn btn-success btn-mini" onclick="passAudit(this)">通过</button>
                  <button class="btn btn-danger btn-mini" onclick="failAudit(this)">拒绝</button>
                </div></c:when>
                <c:when test="${carOwnerInfo.status==2}">已通过
                  <button class="btn btn-mini" onclick="revert(this)">回退</button></c:when>
                <c:when test="${carOwnerInfo.status==-1}">已拒绝
                  <button class="btn btn-mini" onclick="revert(this)">回退</button></c:when>
                <c:otherwise >无法识别的状态</c:otherwise>
              </c:choose>
            </td>

          </tr>
        </c:forEach>
        </tbody>
      </table>

    </div>
    <!-- /.row -->
  </div>
  <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<!-- jQuery Version 1.11.0 -->
<script src="js/jquery-1.11.0.js"></script>
<script src="js/jquery-ui.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap.min.js"></script>
<!-- Custom Theme JavaScript -->
<script src="js/sb-admin-2.js"></script>

<script type="text/javascript">

  $(init());

  function revert(obj){
    var userId = $($(obj).parent().parent().children()[0]).text();
    var remark = $($(obj).parent().parent().children()[8]).children().val();
    $.ajax({
      type:"POST",
      dataType:"json",
      url:"account/carAudit/revert.do",
      data:"userId="+userId+"&remark="+remark,
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

  function changeStatus(s){
    status = s;
  }

  function init(){
    $("#accountCenter").attr('class','active');
    $("#city").val("${city==null ? '全部' : city}");
    $( "#begin" ).datepicker().datepicker("option", "dateFormat","yy-mm-dd").val("${begin}");
    $( "#end" ).datepicker().datepicker("option", "dateFormat","yy-mm-dd").val("${end}");
    $("#status").val("${status==null ? 1 : status}");
  }

  /**
  通过审核*
* @param obj
   */
  function passAudit(obj){

    var carId = $($(obj).parent().parent().parent().children()[0]).text();
    var remark = $($(obj).parent().parent().parent().children()[8]).children().val();
    $.ajax({
      type:"POST",
      dataType:"json",
      url:"account/carAudit/pass.do",
      data:"carId="+carId+"&remark="+remark,
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

  /**
  审核失败*
* @param obj
   */
  function failAudit(obj){
    var carId = $($(obj).parent().parent().parent().children()[0]).text();
    var remark = $($(obj).parent().parent().parent().children()[8]).children().val();
    $.ajax({
      type:"POST",
      dataType:"json",
      url:"account/carAudit/fail.do",
      data:"carId="+carId+"&remark="+remark,
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

  /**
  * 主查询
   */
  function mainQuery(){

    var beginTxt = $("#begin").val();
    var endTxt = $("#end").val();
    var keyTxt = $("#keyword").val();
    var cityTxt = $("#city").val();
    var statusInt = $("#status").val();
    var para = "?page=${page}&rows=${rows}&city="+cityTxt+"&begin="+beginTxt+"&end="+endTxt+"&keyword="+keyTxt+"&status="+statusInt;

    window.location.href = "account/carAudit.do"+para;
  }

  function jump(){
    var n = $("#num");
    window.location.href = "account/carAudit.do?page="+ n.val()+"&rows=${rows}";
  }


</script>
</body>
</html>
