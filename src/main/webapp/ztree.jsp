<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
  <script type="text/javascript" src="<%=request.getContextPath()%>/ztree/js/jquery-1.4.4.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/ztree/js/jquery.ztree.core.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/ztree/js/jquery.ztree.exedit.js"></script>
</head>
<body>
<ul id="treeDemo" class="ztree"></ul>
<script type="text/javascript">
$(function (){
	var settings={    //在setting中做我们树的一些配置    setting  是创建树之前的要干的事情 
		
		   check:{   //复选框  配置   
				enable: true,    //  开启 复选框
				}, 
		   data: {				//  data 中  是一些基础信息
				key:{
					name: "text",   //    默认 是name
					url:"uri",
					//abc:"uriTab"
				}, 
			simpleData: {    	 //简单数据设置
			enable: true,	//简单数据开启  默认为false
			idKey: "id",  		// 指定 那个是id 可以自定义 
			pIdKey: "pid",  //  那个是父节点	可以自定义
			rootPId: 0   	//  将几定义为父节点	可以自定义
				       }
			},
		view:{              //显示的设置
		selectedMulti:false, //是否允许多个被选中		
		nameIsHTML: true,  //支持HTML  格式
		},
		callback:{  	// 回掉函数   的设置
		  onClick:function (event,treeId,treeNode){  // event 设置的对象   treeId  标签的id  treeNode该节点的所有参数
		  alert(event+"~~~"+treeId+"~~~~~"+treeNode.uriTabs);
		  }
	     }
	  }
	
	$.ajax({
		type:"post",
		url:"<%=request.getContextPath()%>/ztree/queryZtree.do",
		dataType:"json",
		success:function (msg){
			$.fn.zTree.init($("#treeDemo"), settings, msg);
		},
		error:function (){
			alert("出错了");
		}
	});
})



</script>
</body>
</html>