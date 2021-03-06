<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

</head>
<body>
	<!-- 查找市场活动 -->	
	<div class="modal fade" id="findMarketActivity" role="dialog">
		<div class="modal-dialog" role="document" style="width: 80%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title">查找市场活动</h4>
				</div>
				<div class="modal-body">
					<div class="btn-group" style="position: relative; top: 18%; left: 8px;">
						<form class="form-inline" role="form">
						  <div class="form-group has-feedback">
							  <input type="text" class="form-control" style="width: 300px;" id="activity" placeholder="请输入市场活动名称，支持模糊查询">
							  <a href="javascript:void(0);" onclick="queryActivity()"><span class="glyphicon glyphicon-search"></span></a>
						  </div>
						</form>
					</div>
					<table id="activityTable4" class="table table-hover" style="width: 900px; position: relative;top: 10px;">
						<thead>
							<tr style="color: #B3B3B3;">
								<td></td>
								<td>名称</td>
								<td>开始日期</td>
								<td>结束日期</td>
								<td>所有者</td>
							</tr>
						</thead>
						<tbody id="t1">

						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<!-- 查找联系人 -->	
	<div class="modal fade" id="findContacts" role="dialog">
		<div class="modal-dialog" role="document" style="width: 80%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title">查找联系人</h4>
				</div>
				<div class="modal-body">
					<div class="btn-group" style="position: relative; top: 18%; left: 8px;">
						<form class="form-inline" role="form">
						  <div class="form-group has-feedback">
							  <input type="text" class="form-control" style="width: 300px;" id="contacts" placeholder="请输入联系人名称，支持模糊查询">
							  <a href="javascript:void(0);" onclick="queryContacts()"><span class="glyphicon glyphicon-search"></span></a>
						  </div>
						</form>
					</div>
					<table id="activityTable" class="table table-hover" style="width: 900px; position: relative;top: 10px;">
						<thead>
							<tr style="color: #B3B3B3;">
								<td></td>
								<td>名称</td>
								<td>邮箱</td>
								<td>手机</td>
							</tr>
						</thead>
						<tbody id="t2">

						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	
	<div style="position:  relative; left: 30px;">
		<h3>更新交易</h3>
	  	<div style="position: relative; top: -40px; left: 70%;">
			<button type="button" class="btn btn-primary" id="updateTran">更新</button>
			<button type="button" class="btn btn-default">取消</button>
		</div>
		<hr style="position: relative; top: -40px;">
	</div>
	<form id="tranForm" class="form-horizontal" role="form" style="position: relative; top: -30px;">
		<div class="form-group">
			<label for="edit-transactionOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
			<div class="col-sm-10" style="width: 300px;">
				<select class="form-control" id="edit-transactionOwner" name="owner" >

				</select>
			</div>
			<input type="hidden" value="${tran.id}" name="id" />
			<label for="edit-amountOfMoney" class="col-sm-2 control-label">金额</label>
			<div class="col-sm-10" style="width: 300px;">
				<input  name="money" type="text" class="form-control" id="edit-amountOfMoney" value="${tran.money}">
			</div>
		</div>
		
		<div class="form-group">
			<label for="edit-transactionName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="edit-transactionName" name="name" value="${tran.name}">
			</div>
			<label for="edit-expectedClosingDate" class="col-sm-2 control-label">预计成交日期<span style="font-size: 15px; color: red;">*</span></label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="edit-expectedClosingDate" name="expectedDate" value="${tran.expectedDate}">
			</div>
		</div>
		
		<div class="form-group">
			<label for="edit-accountName" class="col-sm-2 control-label">客户名称<span style="font-size: 15px; color: red;">*</span></label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="edit-accountName" value="${tran.tranQueryVo.contactsFullName}" placeholder="支持自动补全，输入客户不存在则新建">
				<input  name="customerId" value="${tran.customerId}" type="hidden" />
			</div>
			<label for="edit-transactionStage" class="col-sm-2 control-label">阶段<span style="font-size: 15px; color: red;">*</span></label>
			<div class="col-sm-10" style="width: 300px;">
			  <select class="form-control" id="edit-transactionStage" name="stage" >
				  <c:forEach items="${caChes}" var="caChe">
					  <c:if test="${caChe.code == 'stage'}">
						  <c:forEach items="${caChe.caCheValueList}" var="caCheValue">
							  <c:if test="${caCheValue.value == tran.stage}">
								  <option selected>${caCheValue.value}</option>>
							  </c:if>
							  <c:if test="${caCheValue.value != tran.stage}">
								  <option>${caCheValue.value}</option>>
							  </c:if>
						  </c:forEach>
					  </c:if>
				  </c:forEach>
			  </select>
			</div>
		</div>
		
		<div class="form-group">
			<label for="edit-transactionType" class="col-sm-2 control-label">类型</label>
			<div class="col-sm-10" style="width: 300px;">
				<select class="form-control" id="edit-transactionType" name="type">
					<c:forEach items="${caChes}" var="caChe">
						<c:if test="${caChe.code == 'transactionType'}">
							<c:forEach items="${caChe.caCheValueList}" var="caCheValue">
								<c:if test="${caCheValue.value == tran.type}">
									<option selected>${caCheValue.value}</option>>
								</c:if>
								<c:if test="${caCheValue.value != tran.type}">
									<option>${caCheValue.value}</option>>
								</c:if>
							</c:forEach>
						</c:if>
					</c:forEach>
				</select>
			</div>
			<label for="edit-possibility" class="col-sm-2 control-label">可能性</label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="edit-possibility" value="10%">
			</div>
		</div>
		
		<div class="form-group">
			<label for="edit-clueSource" class="col-sm-2 control-label">来源</label>
			<div class="col-sm-10" style="width: 300px;">
				<select class="form-control" id="edit-clueSource" name="source">
					<c:forEach items="${caChes}" var="caChe">
						<c:if test="${caChe.code == 'source'}">
							<c:forEach items="${caChe.caCheValueList}" var="caCheValue">
								<c:if test="${caCheValue.value == tran.source}">
									<option selected>${caCheValue.value}</option>>
								</c:if>
								<c:if test="${caCheValue.value != tran.source}">
									<option>${caCheValue.value}</option>>
								</c:if>
							</c:forEach>
						</c:if>
					</c:forEach>
				</select>
			</div>
			<label for="edit-activitySrc" class="col-sm-2 control-label">市场活动源&nbsp;&nbsp;<a href="javascript:void(0);" data-toggle="modal" data-target="#findMarketActivity"><span class="glyphicon glyphicon-search"></span></a></label>
			<div class="col-sm-10" style="width: 300px;">
				<input  type="text" class="form-control" id="edit-activitySrc" value="${tran.tranQueryVo.activityName}">
				<input type="hidden" name="activityId"  id="acid" value="${tran.activityId}">
			</div>
		</div>
		
		<div class="form-group">
			<label for="edit-contactsName" class="col-sm-2 control-label">联系人名称&nbsp;&nbsp;<a href="javascript:void(0);" data-toggle="modal" data-target="#findContacts"><span class="glyphicon glyphicon-search"></span></a></label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="edit-contactsName" value="${tran.tranQueryVo.contactsFullName}">
				<input type="hidden" name="contactsId"  id="contactsId" value="${tran.contactsId}">
			</div>
		</div>
		
		<div class="form-group">
			<label for="create-describe" class="col-sm-2 control-label">描述</label>
			<div class="col-sm-10" style="width: 70%;">
				<textarea class="form-control" rows="3" id="create-describe" name="description"  >${tran.description}</textarea>
			</div>
		</div>
		
		<div class="form-group">
			<label for="create-contactSummary" class="col-sm-2 control-label">联系纪要</label>
			<div class="col-sm-10" style="width: 70%;">
				<textarea class="form-control" rows="3" id="create-contactSummary" name="contactSummary" >${tran.contactSummary}</textarea>
			</div>
		</div>
		
		<div class="form-group">
			<label for="create-nextContactTime" class="col-sm-2 control-label">下次联系时间</label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="create-nextContactTime" name="nextContactTime" value="${tran.nextContactTime}">
			</div>
		</div>
		
	</form>
</body>
</html>
<script>


	$('#updateTran').click(function () {
		var tran = $('#tranForm').serialize();
		var tranId = '${tran.id}';
		$.ajax({
			url:'/crm/workbench/transaction/updateTran',
			data:tran,
			type:'post',
			success :function (data) {
			//workbench/transaction/queryTranById	location.href = "/crm/toView/workbench/transaction/detail?id="+tranId;
				location.href = "/crm/workbench/transaction/queryTranById?id="+tranId;
			}
		})
	});


		$.ajax({
			url:'/crm/workbench/activity/queryAllUser',
			type:'post',
			dataType:'json',
			success : function (data) {
				$('#edit-transactionOwner').html("");
				for (var i = 0; i <data.length ; i++) {
					if (data[i].id == '${tran.owner}') {
						$('#edit-transactionOwner').append("<option selected value="+data[i].id+">"+data[i].name+"</option>");
					}else{
						$('#edit-transactionOwner').append("<option value="+data[i].id+">"+data[i].name+"</option>");
					}

				}
			}
		});
		//页面进入时候刷新进度
		$.ajax({
			url : '/crm/workbench/tran/queryPossibilityByStage',
			data : {
				"stage" : '${tran.stage}'
			},
			type : 'get',
			dataType : 'json',
			success : function(data){
				$('#edit-possibility').val(data);
			}
		});

	$('#edit-transactionStage').change(function () {
		$.ajax({
			url : '/crm/workbench/tran/queryPossibilityByStage',
			data : {
				"stage" : $(this).val()
			},
			type : 'get',
			dataType : 'json',
			success : function(data){
				$('#edit-possibility').val(data);
			}
		});
	});

	function queryActivity(){
		var text = $('#activity').val();
		$.post(
				"/crm/workbench/activity/queryActivityByName",
				{ "name" : text },
				function (data) {
					$('#t1').html('');
					for (var i = 0; i <data.length ; i++) {
						$('#t1').append("<tr>\n" +
								"<td><input value="+data[i].id+" onclick=activityId($(this)) type=\"radio\" name=\"activity\"/></td>\n" +
								"<td>"+data[i].name+"</td>\n" +
								"<td>"+data[i].startDate+"</td>\n" +
								"<td>"+data[i].endDate+"</td>\n" +
								"<td>"+data[i].owner+"</td>\n" +
								"</tr>")
					}
				},
				"json"
		)
	}

	function queryContacts(){
		var text = $('#contacts').val();
		$.post(
				"/crm/workbench/contacts/queryContactsByName",
				{ "name" : text },
				function (data) {
					$('#t2').html('');
					for (var i = 0; i <data.length ; i++) {
						$('#t2').append("<tr>\n" +
								"<td><input value="+data[i].id+" onclick=contactsId($(this)) type=\"radio\" name=\"contacts\"/></td>\n" +
								"<td>"+data[i].fullname+"</td>\n" +
								"<td>"+data[i].mphone+"</td>\n" +
								"<td>"+data[i].email+"</td>\n" +
								"</tr>")
					}
				},
				"json"
		)
	}

	function activityId(momo){
		$('#edit-activitySrc').val(momo.parent().next().html());
		$('#acid').val(momo.val());
		$('#findMarketActivity').modal('hide');
	}

	function contactsId(momo){
		$('#edit-contactsName').val(momo.parent().next().html());
		$('#contactsId').val(momo.val());
		$('#findContacts').modal('hide');
	}
</script>