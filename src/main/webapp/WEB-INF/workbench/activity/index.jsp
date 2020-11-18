<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<meta charset="UTF-8">

	<link href="/crm/jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
	<link href="/crm/jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />

	<script type="text/javascript" src="/crm/jquery/jquery-1.11.1-min.js"></script>
	<script type="text/javascript" src="/crm/jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/crm/jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="/crm/jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>

	<%--导入分页插件--%>
	<link href="/crm/jquery/bs_pagination/jquery.bs_pagination.min.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript" src="/crm/jquery/bs_pagination/en.js"></script>
	<script type="text/javascript" src="/crm/jquery/bs_pagination/jquery.bs_pagination.min.js"></script>
	<script type="text/javascript">

</script>
</head>
<body>

	<!-- 创建市场活动的模态窗口 -->
	<div class="modal fade" id="createActivityModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 90%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel1">创建市场活动</h4>
				</div>
				<div class="modal-body">

					<form id="createActivityForm" class="form-horizontal" action="/crm/workbench/activity/saveActivity" method="post" role="form">
						<div class="form-group">
							<label for="create-marketActivityOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" name="owner" id="create-marketActivityOwner">

								</select>
							</div>
							<label for="create-marketActivityName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" name="name" class="form-control" id="create-marketActivityName">
							</div>
						</div>

						<div class="form-group">
							<label for="create-startTime" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" name="startDate" class="form-control" id="create-startTime">
							</div>
							<label for="create-endTime" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" name="endDate" class="form-control" id="create-endTime">
							</div>
						</div>
						<div class="form-group">

							<label for="create-cost" class="col-sm-2 control-label">成本</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" name="cost" class="form-control" id="create-cost">
							</div>
						</div>
						<div class="form-group">
							<label for="create-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" name="description" rows="3" id="create-describe"></textarea>
							</div>
						</div>

					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" id="saveActivityBtn" class="btn btn-primary">保存</button>
				</div>
			</div>
		</div>
	</div>
	<script>
		$.ajax({
			url:'/crm/workbench/activity/queryAllUser',
			type:'post',
			dataType:'json',
			success : function (data) {
				$('#create-marketActivityOwner').html("");

				for (var i = 0; i <data.length ; i++) {
					$('#create-marketActivityOwner').append("<option value="+data[i].id+">"+data[i].name+"</option>");
				}
			}
		});

		//创建市场活动日期
		$("#create-startTime").datetimepicker({
			language:  "zh-CN",
			format: "yyyy-mm-dd",//显示格式
			minView: "month",//设置只显示到月份
			initialDate: new Date(),//初始化当前日期
			autoclose: true,//选中自动关闭
			todayBtn: true, //显示今日按钮
			clearBtn : true,
			pickerPosition: "bottom-left"
		});
		$("#create-endTime").datetimepicker({
			language:  "zh-CN",
			format: "yyyy-mm-dd",//显示格式
			minView: "month",//设置只显示到月份
			initialDate: new Date(),//初始化当前日期
			autoclose: true,//选中自动关闭
			todayBtn: true, //显示今日按钮
			clearBtn : true,
			pickerPosition: "bottom-right"
		});
	/*	$('#create-marketActivityOwner').change(function () {
		alert($('#create-marketActivityOwner').val())

		})*/
		$('#saveActivityBtn').click(function () {
			$.ajax({
				url:'/crm/workbench/activity/addActivity',
				type:'post',
				data:{
					'owner':$('#create-marketActivityOwner').val(),
					'name':$('#create-marketActivityName').val(),
					'startDate':$('#create-startTime').val(),
					'endDate':$('#create-endTime').val(),
					'cost':$('#create-cost').val(),
					'description':$('#create-describe').val()
				},
				dataType:'json',
				success : function (data) {
					alert(data.message);
					$('#createActivityModal').modal('hide');
					$('#create-marketActivityOwner').val(''),
					$('#create-marketActivityName').val(''),
					$('#create-startTime').val(''),
					$('#create-endTime').val(''),
					$('#create-cost').val(''),
					$('#create-describe').val(''),
					pageFlash(1,4);
				}
			})
		})

	</script>


	<!-- 修改市场活动的模态窗口 -->
	<div class="modal fade" id="editActivityModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 95%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel2">修改市场活动</h4>
				</div>
				<div class="modal-body">
				
					<form class="form-horizontal" role="form">
					
						<div class="form-group">
							<label for="edit-marketActivityOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-marketActivityOwner">
								</select>
							</div>
                            <label for="edit-marketActivityName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="edit-marketActivityName" value="">
                            </div>
						</div>

						<div class="form-group">
							<label for="edit-startTime" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-startTime" >
							</div>
							<label for="edit-endTime" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-endTime" >
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-cost" class="col-sm-2 control-label">成本</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-cost" value="5,000">
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="edit-describe"></textarea>
							</div>
						</div>
						
					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" onclick="update2()" data-dismiss="modal">更新</button>
				</div>
			</div>
		</div>
	</div>
	<script>
		function update() {
			var i = $('input:checkbox[name=check]:checked').length;
			if (i==0){
				$('#update').attr('data-toggle','');
				alert("请选一条需要修改的活动");
			}
			if (i>1){
				$('#update').attr('data-toggle','');
				alert("请不要多选");
			}
			if (i==1){
				var id = '';
				$('#update').attr('data-toggle','modal');
				$('input:checkbox[name=check]:checked').each(function () {
					  id =  $(this).parent().parent().children('input:eq(0)').val()
				});
				$.ajax({
					url:"/crm/workbench/activity/forActivityId",
					data:{'id':id},
					type:'post',
					dataType:'json',
					success : function (data) {
						$.ajax({
							url:'/crm/workbench/activity/queryAllUser',
							type:'post',
							dataType:'json',
							success : function (data1) {
								$('#edit-marketActivityOwner').html("");
							//	$('#edit-marketActivityOwner').append("<option value="+data.id+">"+data.name+"</option>");
								for (var i = 0; i <data1.length ; i++) {
									if (data1[i].id == data.owner){
									$('#edit-marketActivityOwner').append("<input type='hidden' id='uu1' value="+data.id+" />"+"<option  value="+data1[i].id+">"+data1[i].name+"</option>");
									}
								}
								for (var i2 = 0; i2 <data1.length ; i2++) {
								     if (data1[i2].id != data.owner){
										$('#edit-marketActivityOwner').append("<option value="+data1[i2].id+">"+data1[i2].name+"</option>");
									}
								}
								$('#edit-marketActivityName').val(data.name);
								$('#edit-startTime').val(data.startDate);
								$('#edit-endTime').val(data.endDate);
								$('#edit-cost').val(data.cost);
								$('#edit-describe').val(data.description)
								$('#saveActivityBtn').click(function () {
								})
							}
						});

					}
				})
			}
		}

	function update2() {
		$.ajax({
			url:'/crm/workbench/activity/updateActivity',
			type:'post',
			data:{
				'id':$('#uu1').val(),
				'owner':$('#edit-marketActivityOwner').val(),
				'name':$('#edit-marketActivityName').val(),
				'startDate':$('#edit-startTime').val(),
				'endDate':$('#edit-endTime').val(),
				'cost':$('#edit-cost').val(),
				'description':$('#edit-describe').val()
			},
			dataType:'json',
			success : function (data) {
				alert(data.message);
				$('#editActivityModal').modal('hide');
				/*$('#edit-marketActivityOwner').val(''),
						$('#edit-marketActivityName').val(''),
						$('#edit-startTime').val(''),
						$('#edit-endTime').val(''),
						$('#edit-cost').val(''),
						$('#edit-describe').val(''),*/
						pageFlash(1,4);
			}
		})
	}

		$("#edit-startTime").datetimepicker({
			language:  "zh-CN",
			format: "yyyy-mm-dd",//显示格式
			minView: "month",//设置只显示到月份
			initialDate: new Date(),//初始化当前日期
			autoclose: true,//选中自动关闭
			todayBtn: true, //显示今日按钮
			clearBtn : true,
			pickerPosition: "bottom-left"
		});
		$("#edit-endTime").datetimepicker({
			language:  "zh-CN",
			format: "yyyy-mm-dd",//显示格式
			minView: "month",//设置只显示到月份
			initialDate: new Date(),//初始化当前日期
			autoclose: true,//选中自动关闭
			todayBtn: true, //显示今日按钮
			clearBtn : true,
			pickerPosition: "bottom-right"
		});

		function allChoose() {
			$('.check').prop('checked', $('#allCheck').prop('checked'))
		}

		function delz() {
			var str = '';
			$('input:checkbox[name=check]:checked').each(function () {
				str= str +  $(this).parent().parent().children('input:eq(0)').val()+','
			});

			 $.ajax({
				 url:'/crm/workbench/activity/deleteActivity',
				 data:{'ids':str},
				 type:'post',
				 dataType:'json',
				 success :function (data) {
					 alert(data.message);
					 pageFlash(1,4)
				 }

			 })

		}


	</script>




	<div>
		<div style="position: relative; left: 10px; top: -10px;">
			<div class="page-header">
				<h3>市场活动列表</h3>
			</div>
		</div>
	</div>
	<div style="position: relative; top: -20px; left: 0px; width: 100%; height: 100%;">
		<div style="width: 100%; position: absolute;top: 5px; left: 10px;">
		
			<div class="btn-toolbar" role="toolbar" style="height: 50px;">
				<form class="form-inline" role="form" style="position: relative;top: 8%; left: 5px;display:inline">
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">名称</div>
				      <input class="form-control" id="name" name="name" type="text" style="width: 100px">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">所有者</div>
				      <input class="form-control" id="owner" name="owner" type="text" style="width: 100px">
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">开始日期</div>
					  <input class="form-control"  name="startTime" type="text" id="startTime" style="width: 150px" />
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">结束日期</div>
					  <input class="form-control" type="text" name="endTime" id="endTime" style="width: 150px" />
				    </div>
				  </div>
				</form>
				<button type="button" id="query" class="btn btn-default">查询</button>
			</div>
			<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;top: 5px;">
				<div class="btn-group" style="position: relative; top: 18%;">
				  <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#createActivityModal"><span class="glyphicon glyphicon-plus"></span> 创建</button>
				  <button type="button" class="btn btn-default" data-toggle="modal" data-target="#editActivityModal" onclick="update()" id="update" ><span class="glyphicon glyphicon-pencil"></span>修改</button>
				  <button type="button" class="btn btn-danger" onclick="delz()" ><span class="glyphicon glyphicon-minus"  ></span> 删除</button>
				</div>
				
			</div>
			<div style="position: relative;top: 8px;">
				<table class="table table-hover">
					<thead>
						<tr style="color: #B3B3B3;">
							<td><input type="checkbox" id="allCheck" onclick="allChoose()" /></td>
							<td>名称</td>
                            <td>所有者</td>
							<td>开始日期</td>
							<td>结束日期</td>
						</tr>
					</thead>
					<tbody id="activityList">

					</tbody>
				</table>
			</div>

			<div  style="height: 50px; position: relative;top: 10px;">
				<div id="activityPage"></div>
			</div>
			
		</div>
		
	</div>
</body>
<script>
	pageFlash(1,4);
	$('#query').click(function () {
		pageFlash(1,4)
	});
	function pageFlash(page,pageSize){

		$.ajax({
			url:'/crm/workbench/activity/queryActivity',
			data:{
			'page':page,
			'pageSize':pageSize,
			'name':$('#name').val(),
			'owner':$('#owner').val(),
			'startTime':$('#startTime').val(),
			'endTime':$('#endTime').val()
			},
			type:'get',
			dataType:'json',
			success : function (data) {
				$('#activityList').html("");
				var list = data.dataList;
				for (var i = 0; i <list.length ; i++) {
					$('#activityList').append("<tr style=\"color: #3278b3;\">\n" +
							"<input class='acid' type=\"hidden\" value="+list[i].id+" />" +
							"<td><input name='check' class='check' type=\"checkbox\" /></td>\n" +
							"<td>"+list[i].name+"</td>\n" +
							"<td>"+list[i].uname+"</td>\n" +
							"<td>"+list[i].startDate+"</td>\n" +
							"<td>"+list[i].endDate+"</td>\n" +
							"</tr>");
				}
				$("#activityPage").bs_pagination({
					currentPage: data.page, // 页码
					rowsPerPage: data.pageSize, // 每页显示的记录条数
					maxRowsPerPage: 20, // 每页最多显示的记录条数
					totalPages: data.pages, // 总页数
					totalRows: data.total, // 总记录条数
					visiblePageLinks: 10, // 显示几个卡片
					showGoToPage: true,
					showRowsPerPage: true,
					showRowsInfo: true,
					showRowsDefaultInfo: true,
					//回调函数，用户每次点击分页插件进行翻页的时候就会触发该函数
					onChangePage : function(event, obj){
						//刷新页面，obj.currentPage:当前点击的页码
						pageFlash(obj.currentPage,obj.rowsPerPage);
					}
				});
			}
		});
	}




	$("#startTime").datetimepicker({
		language:  "zh-CN",
		format: "yyyy-mm-dd",//显示格式
		minView: "month",//设置只显示到月份
		initialDate: new Date(),//初始化当前日期
		autoclose: true,//选中自动关闭
		todayBtn: true, //显示今日按钮
		clearBtn : true,
		pickerPosition: "bottom-left"
	});

	$("#endTime").datetimepicker({
		language:  "zh-CN",
		format: "yyyy-mm-dd",//显示格式
		minView: "month",//设置只显示到月份
		initialDate: new Date(),//初始化当前日期
		autoclose: true,//选中自动关闭
		todayBtn: true, //显示今日按钮
		clearBtn : true,
		pickerPosition: "bottom-left"
	});


</script>
</html>