var prefix = "/refundOrderController"
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
                        refundOrderNo : $('#refundOrderNo').val(),
                        loanOrderNo : $('#loanOrderNo').val(),
                        extRefOrderId : $('#extRefOrderId').val(),
                        createTimeStart : $('#createTimeStart').val(),
                        createTimeEnd : $('#createTimeEnd').val()
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
                        field : 'refundOrderNo',
                        title : '白条退款订单号'
                    },
                    {
                        field : 'companyName',
                        title : '企业名称',
                    },
                    {
                        field : 'realName',
                        title : '姓名'
                    },
                    {
                        field : 'mobile',
                        title : '手机',
                    },
                    {
                        field: 'refundAmount',
                        title: '退款金额',
                        align: 'center'
                    },
                    {
                        field : 'offsetAmount',
                        title : '抵充金额 ',
                        align: 'center'
                    },
                    {
                        field : 'rechargeAmount',
                        title : '充值金额',
                        align: 'center'
                    },
                    {
                        field : 'rechargeOrderTid',
                        title : '嘉福充值流水号',
                        align : 'center',
                    },
                    {
                        field : 'totalRefundAmount',
                        title : '累计退款金额',
                        align: 'center'
                    },
                    {
                        field : 'refundState',
                        title : '状态',
                        align: 'center',
                        formatter: function (value, row, index) {
                            if(value=='01' )
                                return '<span class="label label-primary">处理中</span>';
                            if(value=='02' )
                                return '<span class="label label-primary">成功</span>';
                            if(value=='03' )
                                return '<span class="label label-primary">失败</span>';
                        }
                    },
                    {
                        field : 'loanOrderNo',
                        title : '白条消费订单号',
                    },
                    {
                        field : 'extRefOrderId',
                        title : '渠道退款订单号',
                    },
                    {
                        field : 'createDate',
                        title : '时间',
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
    data = 'refundOrderNo='+$('#refundOrderNo').val()+
        '&loanOrderNo='+$('#loanOrderNo').val()+
        '&extRefOrderId='+$('#extRefOrderId').val()+
        '&createTimeStart='+$('#createTimeStart').val()+
        '&createTimeEnd='+$('#createTimeEnd').val();
    //后端导出的方法
    document.location.href = "/oldtoDownExcel/refundOrderDownExcel?"+ data;
}
