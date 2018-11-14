<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="col-md-9"  style="height: 100%;">
		  	<div id="powersss"></div>
</div>
<script type="text/javascript">

$(function () {
	     $.ajax({
	         type: "Post",
	         url: '<%=request.getContextPath()%>/power/queryPower.do',  
	         dataType: "json",
	         data:{id:id},
	         success: function (result) {
	        	 $("#powersss").treeview({
	     			data:result,
	     			 levels: 0,
	     			icon: "glyphicon glyphicon-stop",
	     			  selectedIcon: "glyphicon glyphicon-stop",
	     			  color: "#000000",
	     			  backColor: "#FFFFFF",
	     			  href: "#node-1",
	     			  selectable: true,
					  showCheckbox:true,
					  multiSelect:true,
	     		})
	         },
	         error: function () {
	             alert("菜单加载失败！")
	         }
	     });
	 })

</script>
</body>
</html>