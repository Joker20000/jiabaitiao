var prefix = "/repayRecordController"
$(function() {
    load();
});

function load() {
    var h = $(window).height() - 160;
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix + "/repayList", // 服务器数据的加载地址
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
                        mobile:$("#mobile").val(),
                        realname:$("#realname").val(),
                        email:$("#email").val(),
                        jfRepayNo:$("#jfRepayNo").val(),
                        finishTimeStart : $('#finishTimeStart').val(),
                        finishTimeEnd : $('#finishTimeEnd').val(),
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
                        field : 'repaySerianlNo',
                        title : '还款订单号'
                    },
                    {
                        field : 'companyName',
                        title : '企业名称'
                    },
                    {
                        field : 'realname',
                        title : '姓名',
                    },
                    {
                        field : 'email',
                        title : '邮箱',
                    },
                    {
                        field : 'mobile',
                        title : '手机',
                    },
                    {
                        field : 'repayAmountSum',
                        title : '金额',
                    },
                    {
                        field : 'amount',
                        title : '本金还款',
                    },
                    {
                        field : 'repayInterestSum',
                        title : '费用还款',
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
                            }else if (value == '3') {
                                return '<span class="label label-primary">人工处理</span>';
                            }
                        }
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
                            } else if (value == '08') {
                                return '<span class="label label-primary">福豆</span>';
                            }
                        }
                    },
                    {
                        field : 'jfRepayNo',
                        title : '嘉福流水号',
                    },
                    {
                        field : 'finishTime',
                        title : '时间',
                    },
                     ],
                fixedColumns: true,
                fixedNumber: 5,
                height:h,
            });
}
function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}
// 下载
function exportExcel() {
    data = 'mobile='+$('#mobile').val()+
        '&realname='+$('#realname').val()+
        '&email='+$('#email').val()+
        '&jfRepayNo='+$('#jfRepayNo').val()+
        '&finishTimeStart='+$('#finishTimeStart').val()+
        '&finishTimeEnd='+$('#finishTimeEnd').val();
    //后端导出的方法
    document.location.href = "/oldtoDownExcel/repayRecordDownExcel?"+ data;
}

