var prefix = "/guaranteeOrderController"
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
                        guaranteeOrderId : $('#guaranteeOrderId').val(),
                        companyName : $('#companyName').val(),
                        mobile : $('#mobile').val(),
                        repayState : $('#repayState').val(),
                        createTimeStart : $('#createTimeStart').val(),
                        createTimeEnd : $('#createTimeEnd').val(),
                        repayTimeStart : $('#repayTimeStart').val(),
                        repayTimeEnd : $('#repayTimeEnd').val()
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
                        field : 'guaranteeOrderId',
                        title : '白条订单号',/*,
                        width:'10%'*/
                    },
                    {
                        field : 'companyName',
                        title : '企业名称'/*,
                        width:'10%'*/
                    },
                    {
                        field : 'realName',
                        title : '姓名'/*,
                        width:'10%'*/
                    },
                    {
                        field : 'email',
                        title : '邮箱'/*,
                        width:'10%'*/
                    },
                    {
                        field : 'mobile',
                        title : '手机'/*,
                        width:'10%'*/
                    },
                    {
                        field : 'amount',
                        title : '金额',
                        /*width:'10%',*/
                    },
                    {
                        field : 'repayState',
                        title : '状态',
                        /*width:'10%',*/
                        align : 'center',
                        formatter : function(value, row, index) {
                            if (value == '01') {
                                return '<span class="label label-primary">待还款</span>';
                            } else if (value == '02') {
                                return '<span class="label label-primary">已还清</span>';
                            }
                        }
                    },
                    {
                        field : 'extOrderId',
                        title : '报销买券订单号'/*,
                     width:'10%'*/
                    },
                    
                    {
                        field : 'createDate',
                        title : '买券时间'/*,
                     width:'10%'*/
                    },
                    {
                        field : 'guaranteeRepayId',
                        title : '白条还款订单号',/*,
                        width:'10%'*/
                    },
                    {
                        field : 'extRepayOrderId',
                        title : '报销还款订单号'/*,
                     width:'10%'*/
                    },
                    {
                        field : 'jfRepayNo',
                        title : '嘉福还款流水号'/*,
                     width:'10%'*/
                    },
                    {
                        field : 'repayDate',
                        title : '还款时间'/*,
                     width:'10%'*/
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

//下载
function exportExcel() {

    data = 'realName='+$('#realName').val()+
        '&mobile='+$('#mobile').val()+
        '&email='+$('#email').val()+
        '&guaranteeOrderId='+$('#guaranteeOrderId').val()+
        '&repayState='+$('#repayState').val()+
        '&companyName='+$('#companyName').val()+
        '&createTimeStart='+$('#createTimeStart').val()+
        '&createTimeEnd='+$('#createTimeEnd').val()+
        '&repayTimeStart='+$('#repayTimeStart').val()+
        '&repayTimeEnd='+$('#repayTimeEnd').val()
    //后端导出的方法
    document.location.href = "/oldtoDownExcel/guaranteeOrderDownExcel?"+ data;
}