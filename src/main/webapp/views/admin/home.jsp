<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>hello</title>
    <c:catch var="importError0">
        <c:import url="../common/base.jsp" charEncoding="utf-8"></c:import>
    </c:catch>
    <c:out value="${importError0}"></c:out>
    <jsp:include page="NewFile.jsp"></jsp:include>
</head>
<body>
        <!-- 验证当前用户是否为“访客”，即未认证（包含未记住）的用户。 -->
        <shiro:guest> 
           <a href="<%=request.getContextPath() %>/login.jsp">游客请先登录</a> 
        </shiro:guest>
        <!-- 验证当前用户是否属于以下任意一个角色 -->
     <shiro:hasAnyRoles name="admin,user"> 
          欢迎${user.username}登录
    <a href="<c:url value='/logout.do'/>"><button>退出登录</button></a>
</shiro:hasAnyRoles>
<!-- 验证当前用户是否拥有指定权限。 -->
    <shiro:hasPermission name="一组"> 
	  <div class="container-fluid">
		<div class="row">
		  <div class="col-xs-12" style="height: 80px;">
		  	<nav class="navbar navbar-default navbar-static-top">
			  <div class="container-fluid">
			    <form class="navbar-form navbar-left" role="search">
				  <div class="form-group">
				  </div>
				</form>
			  </div>
			</nav>
		  </div>
		</div>
		<div class="row" style="height:  625px;">
		  <div class="col-xs-2"  style="height: 100%;">
		  	<div id="tree"></div>
		  </div>
		  <div class="col-xs-10 " style="height: 100%;">
		  	<div class="nav nav-tabs"></div>
		  	<div class="tab-content"></div>
		  </div>
		</div>
	</div>
</shiro:hasPermission>
<br>
<shiro:hasPermission name="二组"> 
 <h1><font color="red">您未拥有创建角色权限，请先申请！！！</font></h1>
</shiro:hasPermission>

<script type="text/javascript">
	 $(function () {
		  function getTree(){
				var value;
				$.ajax({
					url:"<%=request.getContextPath()%>/power/queryTree.do",
					type:"post",
					dataType:"json",
					async:false,
					success:function(data){
						value =data;
					}
				})
				return value;
			}
			$("#tree").treeview({
				data:getTree(),
				collapseIcon:"glyphicon glyphicon-star-empty",
				expandIcon:"glyphicon glyphicon-star",
				onNodeSelected: function(event, node) {
					if(node.nodes == undefined){
						if(node.url!=null){
							$.ajax({
								url:'<%=request.getContextPath()%>'+node.url,
								type:"post",
								dataType:"html",
								success:function(data){
									$.addtabs.add({
										title:node.text,
										content:data
									})
								}
							}) 
						}
					}
				}
			})
	  })
</script>
</body>
</html>