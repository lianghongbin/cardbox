<%--
  Created by IntelliJ IDEA.
  User: dongzhaocheng
  Date: 2015/4/28
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://" +request.getServerName()+":"+request.getServerPort()+path+"/" ;
%>
<base href="<%=basePath%>" >