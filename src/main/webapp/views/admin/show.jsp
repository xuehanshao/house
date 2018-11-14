<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="NewFile.jsp"></jsp:include>
</head>
<body>
<table id="table" class="table"></table>
<div id="yhfjs"></div>
   <script type="text/javascript">
   $("#table").bootstrapTable({
		url:"<%=request.getContextPath()%>/power/queryUsers.do",//获取数据的请求路径
		columns:[//绑定数据
		        {field:'userid',title:'用户编号'},    
		        {field:'username',title:'用户名称'},    
		        {field:'s',title:'操作',formatter:function(value,row,index){
		        	return   "<input type='button' class='btn btn-success' value='用户赋角色' onclick='getRoleById("+row.userid+")'/>";
		        }
                    },    
		       ],
               })		
               
               var uid = 0;
				$(function (){
					   $('#table').on("click-row.bs.table",function(e, row, $element) {     
						     uid = row.userid;
						});
				})
    
		function getRoleById(id){
				  BootstrapDialog.show({
					    title: '用户赋角色',
					    type: BootstrapDialog.TYPE_PRIMARY,
				        size: BootstrapDialog.SIZE_NORMAL,
			            message: $('<div></div>').load('views/admin/relo.jsp'),
			            buttons : [ {
			            	label : '保存',
			                        action : function(dialogItself) {
			                        	var temp = "";
			                			var de = $('#relo').bootstrapTable("getSelections");
			                			for (var i = 0; i < de.length; i++) {
			                				temp+=","+de[i].rid;
			                			}
			                			temp = temp.substr(1);
			                			$.ajax({
			                				url:"<%=request.getContextPath()%>/power/addRelo.do",
			                				type:"post",
			                				data:{id:id,rids:temp},
			                				async:false,
			                				success:function (){
			                					$("#table").bootstrapTable("refresh",{pageNumber:1});
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