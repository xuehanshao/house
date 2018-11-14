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
  <div id="jsfqx"></div>
  <script type="text/javascript">
  $("#relo").bootstrapTable({
		url:"<%=request.getContextPath()%>/power/getRelo.do",//获取数据的请求路径
		columns:[//绑定数据
		        {field:'rid',title:'角色id'},    
		        {field:'rname',title:'角色名称'},    
		        {field:'ame',title:'操作',formatter:function(value,row,index){
		        	return   "<input type='button' class='btn btn-success' value='角色赋权限' onclick='getRole("+row.rid+")'/>";
		        }}    
		       ],
		       
 })	
 var id = 0;
$(function (){
	   $('#relo').on("click-row.bs.table",function(e, row, $element) {     
		     id = row.rid;
		});
})
 
 function getRole(id){
	  BootstrapDialog.show({
		    title: '角色赋权限',
		    size : BootstrapDialog.SIZE_WIDE,  //默认尺寸  
          message: $('<div></div>').load('views/admin/showPower.jsp'),
          buttons : [ {
          	label : '保存',
                      action : function(dialogItself) {
                      	
                      	var temp = "";
              			var de = $('#powersss').treeview('getChecked');
              			for (var i = 0; i < de.length; i++) {
              				temp+=","+de[i].id;
              			}
              			temp = temp.substr(1);
              			$.ajax({
              				url:"<%=request.getContextPath()%>/power/addPower.do",
              				type:"post",
              				data:{id:id,pids:temp},
              				async:false,
              				success:function (){
              					dialogItself.close();
              				}
              			})
              			
		                }
              },
                      {// 设置关闭按钮
              label : '关闭',
              action : function(dialogItself) {
                  dialogItself.close();
              }
          } ],
      });
  }
  </script>
</body>
</html>