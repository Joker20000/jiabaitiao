var prefix = "/quotaAdjustmentController"

$(function() {
    load();
});

function load() {
    var h = $(window).height() - 110;
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
                        mobile : $('#mobile').val(),
                        cardId : $('#cardId').val(),
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
                        field : 'companyName',
                        title : '企业名称'
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
                        field : 'cardId',
                        title : '身份证',
                    },
                    {
                        field: 'vipLevel',
                        title: '会员等级',
                        align: 'center',
                        formatter: function (value, row, index) {
                            if(value!='' && value!=null)
                                return '<span class="label label-primary">' + "VIP" + value + '</span>';
                        }
                    },
                    {
                        field : 'creditLimit',
                        title : '当前授信额度',
                    },
                    {
                        field : 'usableLimit',
                        title : '当前可用授信额度',
                    },
                    {
                        field : 'operation',
                        title : '操作',
                        align : 'center',
                        formatter : function(value, row, index) {
                            if (value == '0.00' || value == null) {
                                return '<span class="label label-primary">0.00</span>';
                            } else {
                                var alreadySum ="<a href=javascript:QuotaUp('"+JSON.stringify(row)+"')>"+row.operation+"</a>";
                                return alreadySum;
                            }
                        }
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
//调整额度 iframe
function QuotaUp(row) {
    layer.open({
        type : 2,
        title : '调整额度',
        maxmin : true,
        style : 'background-color:#09C1FF;',
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : prefix + '/quotaUp?userId='+JSON.parse(row).userId // iframe的url
    });
}
