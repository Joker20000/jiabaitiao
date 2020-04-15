var prefix = "/loanOrderController"
$(function() {
    //获取当前时间，格式YYYY-MM-DD
    function getNowFormatDate() {
        var date = new Date();
        var seperator1 = "-";
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = year + seperator1 + month + seperator1 + strDate;
        return currentdate;
    }
    $('#createTimeStart').val(getNowFormatDate());
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
                        loanOrderNo : $('#loanOrderNo').val(),
                        mobile : $('#mobile').val(),
                        orderType : $('#orderType').val(),
                        repayState : $('#repayState').val(),
                        orderState : $('#orderState').val(),
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
                        field : 'loanOrderNo',
                        title : '白条订单号',/*,
                        width:'10%'*/
                        formatter: function(value,row,index){
                            if(row.orderType =='01')
                            return '<a href="#" onclick="getConsumeDetail(\''+row.loanOrderNo+'\')">'+row.loanOrderNo+'</a>';
                            else
                               return '<span>'+row.loanOrderNo+'</span>';
                        }
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
                        field : 'orderType',
                        title : '订单类型',
                        /*width:'10%',*/
                        align : 'center',
                        formatter : function(value, row, index) {
                            if (value == '01') {
                                return '<span class="label label-primary">消费分期</span>';
                            } else if (value == '02') {
                                return '<span class="label label-primary">现金分期</span>';
                            } else if (value == '03') {
                                return '<span class="label label-primary">信用卡分期</span>';
                            } else if (value == '04') {
                                return '<span class="label label-primary">员工专享</span>';
                            }
                        }
                    },
                    {
                        field : 'nowAmountSum',
                        title : '本金'/*,
                        width:'10%'*/
                    },
                    {
                        field : 'nowInterestSum',
                        title : '分期服务费'/*,
                        width:'10%'*/
                    },
                    {
                        field : 'nowOverdueSum',
                        title : '逾期费用'/*,
                        width:'10%'*/
                    },
                    {
                        field : 'preiodSum',
                        title : '总期数'/*,
                        width:'10%'*/
                    },
                    {
                        field : 'readyAmountSum',
                        title : '累计还款'/*,
                        width:'10%'*/
                    },
                    {
                        field : 'repaymentAmountSum',
                        title : '剩余待还'/*,
                        width:'10%'*/
                    },
                    {
                        field : 'repayState',
                        title : '还款状态',
                        /*width:'10%',*/
                        align : 'center',
                        formatter : function(value, row, index) {
                            if (value == '01') {
                                return '<span class="label label-primary">待还款</span>';
                            } else if (value == '02') {
                                return '<span class="label label-primary">部分还款</span>';
                            } else if (value == '03') {
                                return '<span class="label label-primary">已还清</span>';
                            }
                        }
                    },
                    {
                        field : 'orderState',
                        title : '订单状态',
                        /*width:'10%',*/
                        align : 'center',
                        formatter : function(value, row, index) {
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
                        field : 'createTime',
                        title : '订单生成时间'/*,
                     width:'10%'*/
                    },
                     ],
                fixedColumns: true,
                fixedNumber: 5,
                height:h,
            });
}
function reLoad() {
    if($('#createTimeStart').val()==""||$('#createTimeStart').val()==null){
        $('.aler').css('display','inline-block');
        $('.aler').html("订单生成时间必选！").fadeOut(3000);
        return false;
    }
    $('#exampleTable').bootstrapTable('refresh');
}
function getConsumeDetail(id) {
    layer.open({
        type : 2,
        title : '消费详情',
        maxmin : false,
        shadeClose : false, // 点击遮罩关闭层
        move: false,
        scrollbar: false,
        area : [ '400px', '250px' ],
        content :[prefix + '/getConsumeDetail/' + id ,'no'],// iframe的url
        yes: function(index, layero){
            //事件
            layer.close(index);
        }
    });
}
//下载
function exportExcel() {
    if($('#createTimeStart').val()==""||$('#createTimeStart').val()==null){
        $('.aler').css('display','inline-block');
        $('.aler').html("订单生成时间必选！").fadeOut(3000);
        return false;
    }
    data = 'realName='+$('#realName').val()+
        '&mobile='+$('#mobile').val()+
        '&email='+$('#email').val()+
        '&loanOrderNo='+$('#loanOrderNo').val()+
        '&orderType='+$('#orderType').val()+
        '&repayState='+$('#repayState').val()+
        '&orderState='+$('#orderState').val()+
        '&createTimeStart='+$('#createTimeStart').val()+
        '&createTimeEnd='+$('#createTimeEnd').val()
    //后端导出的方法
    document.location.href = "/oldtoDownExcel/orderManagerDownExcel?"+ data;
}

