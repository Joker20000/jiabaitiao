var prefix = "/ticketFlowServiceController"
$(function () {
    load();
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: prefix + "/list", // 服务器数据的加载地址
                // showRefresh : true,
                // showToggle : true,
                // showColumns : true,
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect: false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize: 10, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                search: false, // 是否显示搜索框
                showColumns: false, // 是否显示内容下拉框（选择显示的列）
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者
                // "server"
                queryParams: function (params) {
                    return {
                        // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset: params.offset,
                        btOrderNo: $('#btOrderNo').val(),
                        ticketTransactionFlow: $('#ticketTransactionFlow').val(),
                        ticketNo: $('#ticketNo').val(),
                        ticketFlowType: $('#ticketFlowType').val(),
                        ticketTransactionState: $('#ticketTransactionState').val(),
                        finishTimeStart: $('#finishTimeStart').val(),
                        finishTimeEnd: $('#finishTimeEnd').val()
                    };
                },
                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求
                columns: [
                    {
                        field: 'ticketFlowId',
                        title: '券交易流水号 '
                    },
                    {
                        field: 'ticketFlowType',
                        title: '交易类别 ',
                        formatter: function (value, row, index) {
                            if (value == '01') {
                                return '<span class="label label-primary">销券</span>';
                            } else if (value == '02') {
                                return '<span class="label label-primary">撤券</span>';
                            }
                        }
                    },
                    {
                        field: 'btOrderNo',
                        title: '白条订单号 '
                    },
                    {
                        field: 'orderState',
                        title: '订单状态 ',
                        formatter: function (value, row, index) {
                            if (value == '01') {
                                return '<span class="label label-primary">处理中</span>';
                            } else if (value == '02') {
                                return '<span class="label label-primary">成功</span>';
                            } else if (value == '03') {
                                return '<span class="label label-primary">失败</span>';
                            } else if (value == '04') {
                                return '<span class="label label-primary">部分退款</span>';
                            } else if (value == '05') {
                                return '<span class="label label-primary">全部退款</span>';
                            }
                        }
                    },
                    {
                        field: 'ticketTransactionState',
                        title: '交易状态 ',
                        formatter: function (value, row, index) {
                            if (value == '01') {
                                return '<span class="label label-primary">交易成功</span>';
                            } else if (value == '02') {
                                return '<span class="label label-primary">交易失败</span>';
                            } else if (value == '03') {
                                return '<span class="label label-primary">交易中</span>';
                            }
                        }
                    },
                    {
                        field: 'ticketTransactionFlow',
                        title: '渠道流水号  ',
                    },
                    {
                        field: 'ticketNo',
                        title: '券号  ',
                        align : 'center',
                        formatter : function(value, row, index) {
                            if (value == '' || value == null) {
                                return '<span class="label label-primary"></span>';
                            } else {
                                var alreadySum ="<a href=javascript:ticketflowlist("+row.ticketNo+")>"+row.ticketNo+"</a>";
                                return alreadySum;
                            }
                        }
                    },
                    {
                        field: 'transactionTime',
                        title: '交易时间  ',
                    },]
            });
}

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}
//还款流水
function ticketflowlist(ticketNo) {
    layer.open({
        type : 2,
        title : '券详情',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : prefix + '/ticketflowlist?ticketNo='+ticketNo // iframe的url
    });
}
//下载
function exportExcel() {
    var btOrderNo = $("#btOrderNo").val();
    var ticketTransactionFlow = $("#ticketTransactionFlow").val();
    var ticketNo = $("#ticketNo").val();
    var ticketFlowType = $("#ticketFlowType").val();
    var ticketTransactionState = $("#ticketTransactionState").val();
    var finishTimeStart = $("#finishTimeStart").val();
    var finishTimeEnd = $("#finishTimeEnd").val();

    var data = "btOrderNo=" + btOrderNo +
        "&ticketTransactionFlow=" + ticketTransactionFlow + "&ticketNo=" + ticketNo + "&ticketFlowType=" + ticketFlowType + "&ticketTransactionState=" + ticketTransactionState
        + "&finishTimeStart=" + finishTimeStart + "&finishTimeEnd=" + finishTimeEnd;
    //后端导出的方法
    document.location.href = "/oldtoDownExcel/ticketFlowServiceController?" + data;
}