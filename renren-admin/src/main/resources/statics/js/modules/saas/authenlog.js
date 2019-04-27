$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'saas/authenlog/selectByCondition',
        datatype: "json",
        colModel: [			
			// { label: 'authenLogId', name: 'authenLogId', index: 'authen_log_id', width: 50, key: true },
            { label: '企业ID', name: 'companyCode', index: 'company_code', width: 80 ,key: true},
            { label: '企业名称', name: 'companyName', index: 'name', width: 80 },
			{ label: '组织机构代码', name: 'organizationCode', index: 'organization_code', width: 80 },
			{ label: '成立时间', name: 'setupDate', index: 'setup_date', width: 80 },
			{ label: '注册资金', name: 'registeredCapital', index: 'registered_capital', width: 80 },
			{ label: '法人代表', name: 'legalEntity', index: 'legal_entity', width: 80 },
			{ label: '企业地址', name: 'companyAddress', index: 'company_address', width: 80 },
			{ label: '企业状态', name: 'companyState', index: 'company_state', width: 80 },
			{ label: '认证人', name: 'authenticator', index: 'authenticator', width: 80 },
			{ label: '认证时间', name: 'authenTime', index: 'authen_time', width: 80 },
			{ label: '状态', name: 'state', index: 'state', width: 80 },
            { label: '经办人', name: 'authenEntity.operator', index: 'operator', width: 80 },
            { label: '经办时间', name: 'authenEntity.operatorTime', index: 'operator_time', width: 80 },
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
	    showDetail:false,
		showList: true,
		title: null,
		authenLog: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.authenLog = {};
		},
		update: function (event) {
			var authenLogId = getSelectedRow();
			if(authenLogId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(authenLogId)
		},
		saveOrUpdate: function (event) {
		    $('#btnSaveOrUpdate').button('loading').delay(1000).queue(function() {
                var url = vm.authenLog.authenLogId == null ? "saas/authenlog/save" : "saas/authenlog/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.authenLog),
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
			var authenLogIds = getSelectedRows();
			if(authenLogIds == null){
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
                        url: baseURL + "saas/authenlog/delete",
                        contentType: "application/json",
                        data: JSON.stringify(authenLogIds),
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
		getInfo: function(authenLogId){
			$.get(baseURL + "saas/authenlog/info/"+authenLogId, function(r){
                vm.authenLog = r.authenLog;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		},

        //通过条件查询
        selectByCondition:function (event) {
                vm.showList = true;
                //查询条件
                $("#jqGrid").jqGrid('setGridParam',{
                    postData:{
                        "jsonobj":JSON.stringify(
                                    {
                                        "companyCode":$('#companyCode').val(),
                                        "companyName":$('#companyName').val(),
                                        "organizationCode":$('#organizationCode').val(),
                                        "setupDate":$('#setupDate').val(),
                                        "registeredCapital":$('#registeredCapital').val(),
                                        "legalEntity":$('#legalEntity').val(),
                                        "companyState":$('#companyState').val(),
                                        "authenticator":$('#authenticator').val(),
                                        "authenEntity":{"operator" : $('#operator').val()},
                                    }
                                ),
                    },
                }).trigger('reloadGrid');
        },
        //进入查询列表
        selectCondition:function (event) {
		    vm.showList=false;
            vm.title = "根据条件查询";
            vm.authenLog = {};
        },
        detail:function (event) {

        },
        authenPage:function (event) {

        },
	}
});