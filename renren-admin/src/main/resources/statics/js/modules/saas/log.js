$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'saas/log/list',
        datatype: "json",
        colModel: [			
			/*{ label: 'id', name: 'id', index: 'id', width: 50, key: true },*/
			{ label: '日志标题', name: 'title', index: 'title', width: 80 }, 			
			{ label: '请求地址', name: 'url', index: 'url', width: 80 }, 			
			{ label: '日志类型', name: 'logType', index: 'log_type', width: 80 }, 			
			{ label: '业务类型', name: 'businessType', index: 'business_type', width: 80 }, 			
			{ label: '操作日期', name: 'operationDate', index: 'operation_date', width: 100,formatter:"date" },
			{ label: '响应时间', name: 'responseTime', index: 'response_time', width: 80 }, 			
			{ label: '响应次数', name: 'responseNumber', index: 'response_number', width: 80 }, 			
			{ label: '异常次数', name: 'exceptionNumber', index: 'exception_number', width: 80 }, 			
			{ label: '企业ID', name: 'companyCode', index: 'company_code', width: 80 }, 			
			{ label: '企业名称', name: 'companyName', index: 'company_name', width: 80 }, 			
			{ label: '设备数量', name: 'equipmentQuantity', index: 'equipment_quantity', width: 80 }, 			
			{ label: '浏览器种类数量', name: 'browserNumber', index: 'browser_number', width: 80 }
			/*{ label: '日志的类型(0：前台日志  1：后台日志)', name: 'type', index: 'type', width: 80 }*/
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		log: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.log = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
		    $('#btnSaveOrUpdate').button('loading').delay(1000).queue(function() {
                var url = vm.log.id == null ? "saas/log/save" : "saas/log/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.log),
                    success: function(r){
                        if(r.code === 0){
                             layer.msg("操作成功", {icon: 1});
                             vm.reload();
                             $('#btnSaveOrUpdate').button('reset');
                             $('#btnSaveOrUpdate').dequeue();
                        }else{
                            layer.alert(r.msg);
                            $('#btnSaveOrUpdate').button('reset');
                            $('#btnSaveOrUpdate').dequeue();
                        }
                    }
                });
			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			var lock = false;
            layer.confirm('确定要删除选中的记录？', {
                btn: ['确定','取消'] //按钮
            }, function(){
               if(!lock) {
                    lock = true;
		            $.ajax({
                        type: "POST",
                        url: baseURL + "saas/log/delete",
                        contentType: "application/json",
                        data: JSON.stringify(ids),
                        success: function(r){
                            if(r.code == 0){
                                layer.msg("操作成功", {icon: 1});
                                $("#jqGrid").trigger("reloadGrid");
                            }else{
                                layer.alert(r.msg);
                            }
                        }
				    });
			    }
             }, function(){
             });
		},
		getInfo: function(id){
			$.get(baseURL + "saas/log/info/"+id, function(r){
                vm.log = r.log;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});