<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table id="relo" class="table"></table>
   <script type="text/javascript">
    	 $("#relo").bootstrapTable({
    			url:"<%=request.getContextPath()%>/power/queryRelos.do",//获取数据的请求路径
    			columns:[//绑定数据
    	                {field : 'state',checkbox:true, formatter : stateFormatter},   
    			        {field:'rid',title:'角色id'},    
    			        {field:'rname',title:'角色名称'},    
    			       ],
    			       queryParams : {
                           "id":uid
                       },
    			       
    	   })	
    	   function stateFormatter(value, row, index) {
    			  if (row.state == true)
    			      return {
    			      disabled : false,//设置是否可用
    			      checked : true//设置选中
    			    };
    			  return value;
    			}
    	     
   
  
   
		
   </script>
</body>
</html>