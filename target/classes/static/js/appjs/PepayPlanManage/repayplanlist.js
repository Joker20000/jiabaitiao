var prefix = "/repayPlanManageController"

$(function() {
    load();
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix + "/repayplanlistone", // 服务器数据的加载地址
                // showRefresh : true,
                // showToggle : true,
                // showColumns : true,
                iconSize : 'outline',
                toolbar : '#exampleToolbar',
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型
                pagination : true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect : false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize : 10, // 如果设置了分页，每页数据条数
                pageNumber : 1, // 如果设置了分布，首页页码
                search : false, // 是否显示搜索框
                showColumns : false, // 是否显示内容下拉框（选择显示的列）
                sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
                // "server"
               queryParams : function(params) {
                    return {
                        // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit : params.limit,
                        offset : params.offset,
                        repayPlanNo : $('#repayPlanNo').text(),
                    };
                },
                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求
                columns : [
                    {
                        checkbox : true
                    },
                    {
                        field : 'repaySerialNo',
                        title : '还款订单号'
                    },
                    {
                        field : 'finishTime',
                        title : '时间'
                    },
                    {
                        field : 'repayAmountSum',
                        title : '还款金额'
                    },
                    {
                        field : 'repayAmount',
                        title : '当期还款'
                    },
                    {
                        field : 'jfRepayNo',
                        title : '嘉福系统流水号',
                    },
                    {
                        field : 'repayType',
                        title : '还款账户',
                        align : 'center',
                        formatter : function(value, row, index) {
                            if (value == '01') {
                                return '<span class="label label-primary">福利账户</span>';
                            } else if (value == '02') {
                                return '<span class="label label-primary">工资账户</span>';
                            } else if (value == '03') {
                                return '<span class="label label-primary">支付宝</span>';
                            } else if (value == '04') {
                                return '<span class="label label-primary">微信</span>';
                            } else if (value == '05') {
                                return '<span class="label label-primary">银行卡</span>';
                            }else if (value == '06') {
                                return '<span class="label label-primary">企业支付宝</span>';
                            }else if (value == '07') {
                                return '<span class="label label-primary">人事代扣</span>';
                            }else if (value == '08') {
                                return '<span class="label label-primary">福豆</span>';
                            }
                        }
                    },
                    {
                        field : 'repayFlag',
                        title : '还款方式',
                        align : 'center',
                        formatter : function(value, row, index) {
                            if (value == '0') {
                                return '<span class="label label-primary">系统自动</span>';
                            } else if (value == '1') {
                                return '<span class="label label-primary">用户主动</span>';
                            } else if (value == '3') {
                                return '<span class="label label-primary">人工处理</span>';
                            }
                        }
                    },
                     ]
            });
}
function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}
//下载
function exportExcel() {
    data = 'realName='+$('#realName').val()+
        '&mobile='+$('#mobile').val()+
        '&email='+$('#email').val()+
        '&groupId='+$('#groupId').val()+
        '&proveState='+$('#proveState').val()+
        '&state='+$('#searchName2').val()+
        '&starTime='+$('#searchName').val()+
        '&companyName='+$('#companyName').val()+
        '&proveSubmitTime='+$('#proveSubmitTime').val()+
        '&proveSubmitTime1='+$('#proveSubmitTime1').val()+
        '&proveAuditTime='+$('#proveAuditTime').val()+
        '&proveAuditTime1='+$('#proveAuditTime1').val();
    //后端导出的方法
    document.location.href = "/oldtoDownExcel/userManageDownExcel?"+ data;
}

