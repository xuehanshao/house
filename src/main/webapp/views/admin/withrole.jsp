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
</head>
<body>
${login}

欢迎${user.username}登录
<shiro:hasRole name="admin">
   你有admin角色访问该页面
</shiro:hasRole>

<shiro:hasRole name="user">
    你有user角色访问该页面
</shiro:hasRole>

<a href="<c:url value='/logout.do'/>"><button>退出登录</button></a>


<shiro:hasPermission name="user:create">
     <a href="admin.jsp">创建角色</a>
</shiro:hasPermission>

</body>
</html>