var prefix = "/feeReductionController/feeReduction";
$(function() {
    load();
});

function load() {
    var h = $(window).height() - 200;
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix + "/getFeeReductionList", // 服务器数据的加载地址
                iconSize : 'outline',
                toolbar : '#exampleToolbar',
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型
                pagination : true, // 设置为true会在底部显示分页条
                singleSelect : true, // 设置为true将禁止多选
                pageSize : 10, // 如果设置了分页，每页数据条数
                pageNumber : 1, // 如果设置了分布，首页页码
                search : false, // 是否显示搜索框
                showColumns : false, // 是否显示内容下拉框（选择显示的列）
                sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
                // "server"
               queryParams : function(params) {
                    return {
                        limit : params.limit,
                        offset : params.offset,
                        repayPlanNo : $('#repayPlanNo').val(),
                        tel : $('#tel').val(),
                        appRovalState:$('#appRovalState').val()
                    };
                },
                columns : [
                    {
                        checkbox : true
                    },
                    {
                        field : 'appNo',
                        title : '申请编号'
                    },
                    {
                        field : 'realName',
                        title : '姓名'
                    },
                    {
                        field : 'tel',
                        title : '手机号'
                    },
                    {
                        field : 'oriLastTime',
                        title : '原还款日'
                    },
                    {
                        field : 'makeLastTime',
                        title : '约定还款日'
                    },
                    {
                        field : 'oriOverdue',
                        title : '原逾期费用'
                    },
                    {
                        field : 'waiverAmt',
                        title : '减免逾期费用'
                    },
                    {
                        field : 'agterReduFee',
                        title : '减免后逾期费用'
                    },
                    {
                        field : 'approvalState',
                        title : '审批状态',
                        align : 'center',
                        formatter : function(value, row, index) {
                            if('0' == value){
                                return '<span class="label label-primary">待审批</span>';
                            }else if('1' == value){
                                return '<span class="label label-success">通过</span>';
                            }else if('2' == value){
                                return '<span class="label label-danger">拒绝</span>';
                            }
                        }
                    },
                    {
                        field : 'repayPlanNo',
                        title : '还款计划编号'
                    },
                    {
                        field : 'applicant',
                        title : '申请人'
                    },
                    {
                        field : 'createTime',
                        title : '申请时间'
                    },
                    {
                        field : 'approver',
                        title : '审批人'
                    },{
                        field : 'approvalTime',
                        title : '审批时间'
                    }
                     ],
                fixedColumns: true,
                fixedNumber: 5,
                height:h,
            });
    //$(".fixed-table-header").height(0);
    $(".clearfix").height(0);
    $("#exampleTable").css({margin:'0px'})
    //$("#exampleTable").find("tbo")
}
$(window).resize(function () {
    var h = $(window).height() - 100;
    var w = $(window).width();
    var $fixedTableBody = $("#exampleTable").closest("div"),
        $fixedTableBodyColumns = $fixedTableBody.prev(),
        $FixedtableContainer = $fixedTableBody.closest("div");
    //$FixedtableContainer.height(h - 100);
    //$fixedTableBodyColumns.height(h - 153);
    /*alert(h);
    $(".table-responsive").width(h);*/
});
function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}
//下载
function exportExcel() {
    data = 'realName='+$('#realName').val()+
        '&mobile='+$('#mobile').val()+
        '&email='+$('#email').val()+
        '&userChannelId='+$('#userChannelId').val()+
        '&proveState='+$('#proveState').val()+
        '&companyName='+$('#companyName').val()+
        '&register='+$('#register').val()+
        '&register1='+$('#register1').val()+
        '&proveSubmitTime='+$('#proveSubmitTime').val()+
        '&proveSubmitTime1='+$('#proveSubmitTime1').val()+
        '&proveAuditTime='+$('#proveAuditTime').val()+
        '&proveAuditTime1='+$('#proveAuditTime1').val();
    //后端导出的方法
    document.location.href = "/oldtoDownExcel/userManageDownExcel?"+ data;
}

function check(){
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要提交的数据");
        return;
    }
    var appNo = "";
    var repayPlanNo = "";
    var accountNo = ""; //账户号
    var approvalState = "";//审批状态
    $.each(rows, function(i, row) {
        appNo = row['appNo'];
        repayPlanNo = row['repayPlanNo'];
        accountNo = row['accountNo'];
        approvalState = row['approvalState'];
        return false;
    });

    if (!(approvalState == "0")){
        layer.msg("已审批完毕!");
        return;
    }

    layer.open({
        type : 2,
        title : '申请费用减免',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '830px', '520px' ],
        content : prefix+ "/getFeeRuctionDetail?appNo="+appNo+"&accountNo="+accountNo+"&repayPlanNo="+repayPlanNo+"&init=2"
    });

}

