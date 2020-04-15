var prefix = "/accounTtradingManageController"
$(function() {
    load();
});

function load() {
    var h = $(window).height() - 180;
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix + "/list", // 服务器数据的加载地址
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
                        realName : $('#realName').val(),
                        email : $('#email').val(),
                        flowId : $('#flowId').val(),
                        channelBizid : $('#channelBizid').val(),
                        bizid : $('#bizid').val(),
                        transType : $('#transType').val(),
                        mobile : $('#mobile').val(),
                        transTimeStart : $('#transTimeStart').val(),
                        transTimeEnd : $('#transTimeEnd').val()
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
                        field : 'flowId',
                        title : '交易流水号'
                    },
                    {
                        field : 'realName',
                        title : '姓名',
                    },
                    {
                        field : 'email',
                        title : '邮箱'
                    },
                    {
                        field : 'mobile',
                        title : '手机号',
                    },
                    {
                        field : 'addAndSubtract',
                        title : '交易金额',
                        align : 'center',
                        formatter : function(value, row, index) {
                            if (value == '01') {
                                return '<span class="label label-primary">'+"+"+row.amount+'</span>';
                            } else if (value == '02') {
                                return '<span class="label label-primary">'+"-"+row.amount+'</span>';
                            }
                        }
                    },
                    {
                        field : 'creditLimit',
                        title : '交易后授信额度'
                    },
                    {
                        field : 'usableLimit',
                        title : '交易后可用授信额度'
                    },
                    {
                        field : 'transType',
                        title : '业务类型',
                        align : 'center',
                        formatter : function(value, row, index) {
                            if (value == '11') {
                                return '<span class="label label-primary">主动还款</span>';
                            } else if (value == '12') {
                                return '<span class="label label-primary">系统代扣</span>';
                            } else if (value == '21') {
                                return '<span class="label label-primary">消费分期</span>';
                            } else if (value == '31') {
                                return '<span class="label label-primary">现金分期</span>';
                            } else if (value == '32') {
                                return '<span class="label label-primary">信用卡分期</span>';
                            } else if (value == '33') {
                                return '<span class="label label-primary">员工专享</span>';
                            } else if (value == '41') {
                                return '<span class="label label-primary">消费退款</span>';
                            } else if (value == '42') {
                                return '<span class="label label-primary">信用卡退款</span>';
                            } else if (value == '51') {
                                return '<span class="label label-primary">授信</span>';
                            } else if (value == '52') {
                                return '<span class="label label-primary">自动认证</span>';
                            } else if (value == '53') {
                                return '<span class="label label-primary">联系人授信</span>';
                            } else if (value == '54') {
                                return '<span class="label label-primary">手机认证授信</span>';
                            } else if (value == '55') {
                                return '<span class="label label-primary">淘宝认证授信</span>';
                            } else if (value == '56') {
                                return '<span class="label label-primary">社保认证授信</span>';
                            } else if (value == '61') {
                                return '<span class="label label-primary">预支还款</span>';
                            }
                        }
                    },
                    {
                        field : 'bizid',
                        title : '白条订单号',
                    },
                    {
                        field : 'channelBizid',
                        title : '嘉福流水号',
                    },
                    {
                        field : 'transTime',
                        title : '交易时间',
                    },
                     ],
                fixedColumns: true,
                fixedNumber: 5,
                height:h,
            });
    var w = $(window).width();
    $(".clearfix").height(0);
    //$("#exampleTable").width(w);
}
function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}
//下载
function exportExcel() {
    data = 'realName='+$('#realName').val()+
        '&flowId='+$('#flowId').val()+
        '&email='+$('#email').val()+
        '&channelBizid='+$('#channelBizid').val()+
        '&bizid='+$('#bizid').val()+
        '&transType='+$('#transType').val()+
        '&mobile='+$('#mobile').val()+
        '&transTimeStart='+$('#transTimeStart').val()+
        '&transTimeEnd='+$('#transTimeEnd').val();
    //后端导出的方法
    document.location.href = "/oldtoDownExcel/accountTradingDownExcel?"+ data;
}
