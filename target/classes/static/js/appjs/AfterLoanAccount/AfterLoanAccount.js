var prefix = "/afterLoanAccounController"
$(function() {
    load();
});

function load() {
    var h = $(window).height() - 180;
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix + "/afterLoanAccountlist", // 服务器数据的加载地址
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
                singleSelect : true ,
                // "server"
               queryParams : function(params) {
                    return {
                        // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit : params.limit,
                        offset : params.offset,
                        repayPlanNo : $('#repayPlanNo').val(),
                        realname : $('#realname').val(),
                        loanOrderNo : $('#loanOrderNo').val(),
                        orderType : $('#orderType').val(),
                        repayState : $('#repayState').val(),
                        latestTimeStart : $('#latestTimeStart').val(),
                        latestTimeEnd : $('#latestTimeEnd').val(),
                        mobile : $('#mobile').val(),
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
                        field : 'manyReturn',
                        title : '还款序號'
                    },
                    {
                        title: '序号',
                        field: '',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var pageSize = $('#exampleTable').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                            var pageNumber = $('#exampleTable').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
                            return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
                        }
                    },
                    {
                        field : 'repayPlanNo',
                        title : '还款计划编号'
                    },
                    {
                        field : 'loanOrderNo',
                        title : '白条订单号'
                    },
                    {
                        field : 'realname',
                        title : '姓名',
                    },
                    {
                        field : 'mobile',
                        title : '手机',
                    },
                    {
                        field : 'vipLevel',
                        title : '会员等级',
                        align: 'center',
                        formatter: function (value, row, index) {
                            if(value!='' && value!=null)
                                return '<span class="label label-primary">' + "VIP" + value + '</span>';
                        }
                    },
                    {
                        field : 'orderType',
                        title : '订单类型',
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
                        field : 'nowPeriod',
                        title : '当前期数',
                    },
                    {
                        field : 'periodSum',
                        title : '总期数',
                    },
                    {
                        field : 'nowOverdue',
                        title : '当期逾期费用',
                        align : 'center',
                        formatter : function(value, row, index) {
                            if (value == '0.00') {

                                return '<span class="label label-primary">0.00</span>';
                            } else {
                                var alreadySum ="<a href=javascript:overdue('"+row.repayPlanNo+"')>"+row.nowOverdue+"</a>";
                                return alreadySum;
                            }
                        }
                    },{
                        field : 'refundAmount',
                        title : '退款抵充',
                    },
                    {
                        field : 'repaymentSum',
                        title : '当期应还总额',
                    },
                    {
                        field : 'latestTime',
                        title : '最迟还清日',
                    },
                    {
                        field : 'alreadyRepaidSum',
                        title : '当期累计还款',
                        align : 'center',
                        formatter : function(value, row, index) {
                            if (value == '0.00' || value == null) {
                                return '<span class="label label-primary">0.00</span>';
                            } else {
                                var alreadySum ="<a href=javascript:repayplanlist('"+row.repayPlanNo+"')>"+row.alreadyRepaidSum+"</a>";
                                return alreadySum;
                            }
                        }
                    },
                    {
                        field : 'nowSurplusAmountSum',
                        title : '当期剩余待还',
                    },
                    {
                        field : 'repayState',
                        title : '还款状态',
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
                        field : 'overdueState',
                        title : '逾期状态',
                        align : 'center',
                        formatter : function(value, row, index) {
                            if (value == '00') {
                                return '<span class="label label-primary">未逾期</span>';
                            } else if (value == '01') {
                                return '<span class="label label-primary">已逾期</span>';
                            }
                        }
                    },
                    {
                        field : 'fullRepaymentTime',
                        title : '还清时间',
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

function overdue(repayPlanNo) {
    layer.open({
        type : 2,
        title : '逾期费用',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : '/repayPlanManageController/overdue?repayPlanNo='+repayPlanNo // iframe的url
    });
}

//还款流水
function repayplanlist(repayPlanNo) {
    layer.open({
        type : 2,
        title : '还款流水',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : '/repayPlanManageController/repayplanlist?repayPlanNo='+repayPlanNo // iframe的url
    });
}
//发送消息
function sendmessage() {
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    console.info("rows："+rows);
    if (rows.length == 0) {
        layer.msg("请选择要提交的数据");
        return;
    }else{
        var accountNo = ""; //账户号
        var mobile = ""; //手机号
        var repayaccountNo = ""; //还款订单号
        // repayaccountNo，取每条数据对应的ID
        $.each(rows, function(i, row) {
            accountNo = row['accountNo'];
            mobile = row['mobile'];
            repayaccountNo = row['repayPlanNo'];
            userChannelId = row['userChannelId'];
            return false;
        });

        layer.open({
            type : 2,
            title : '发送消息',
            maxmin : true,
            shadeClose : false, // 点击遮罩关闭层
            area : [ '830px', '520px' ],
            content : prefix+ '/sendMessagePage?accountNo='+accountNo+"&mobile="+mobile+"&repayaccountNo="+repayaccountNo+"&userChannelId="+userChannelId
        });
    }
}
//申请费用减免
function feeReduction() {
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要提交的数据");
        return;
    }else{
        var accountNo = ""; //账户号
        var loanaccountNo = ""; //白条订单号
        var repayaccountNo = ""; //还款订单号
        // repayaccountNo，取每条数据对应的ID
        $.each(rows, function(i, row) {
            accountNo = row['accountNo'];
            loanaccountNo = row['loanOrderNo'];
            repayaccountNo = row['repayPlanNo'];
            return false;
        });

        layer.open({
            type : 2,
            title : '申请费用减免',
            maxmin : true,
            shadeClose : false, // 点击遮罩关闭层
            area : [ '830px', '520px' ],//init=1是申请费用减免。init=2是查看费用减免
            content : prefix+ '/tofeeReduction?accountNo='+accountNo+"&loanaccountNo="+loanaccountNo+"&repayaccountNo="+repayaccountNo+"&init=1"
        });
    }
}

//贷后信息
function loanAfterInfo() {
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    console.info("rows："+rows);
    if (rows.length == 0) {
        layer.msg("请选择要提交的数据");
        return;
    }else{
        var accountNo = ""; //账户号
        // repayaccountNo，取每条数据对应的ID
        $.each(rows, function(i, row) {
            accountNo = row['accountNo'];
            return false;
        });

        layer.open({
            type : 2,
            title : '贷后信息',
            maxmin : true,
            shadeClose : false, // 点击遮罩关闭层
            area : [ '830px', '520px' ],
            content : prefix+ '/toLoanAfterInfo?accountNo='+accountNo
        });
    }
}


// 下载
function exportExcel() {
    var overdueState = "01";
    var repayState = "01";
    if ($('#repayState').val() != '' && $('#repayState').val() != null){
        repayState = $('#repayState').val();
    }
    data = 'realname='+$('#realname').val()+
        '&repayPlanNo='+$('#repayPlanNo').val()+
        '&orderType='+$('#orderType').val()+
        '&loanOrderNo='+$('#loanOrderNo').val()+
        '&repayState='+repayState+
        '&overdueState='+overdueState+
        '&latestTimeStart='+$('#latestTimeStart').val()+
        '&latestTimeEnd='+$('#latestTimeEnd').val()+
        '&fullRepaymentTimeStart='+
        '&fullRepaymentTimeEnd='+
        '&mobile='+$('#mobile').val(),
    //后端导出的方法
    document.location.href = "/oldtoDownExcel/repayPlanDownExcel?"+ data;
}

