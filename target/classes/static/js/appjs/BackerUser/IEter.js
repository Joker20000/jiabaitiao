var prefix = "/userManageController"
$(function() {
    load();
});

function load() {
    var h = $(window).height() - 200;
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
                        email : $('#email').val(),
                        userChannelId : $('#userChannelId').val(),
                        proveState : $('#proveState').val(),
                        companyName : $('#companyName').val(),
                        register : $('#register').val(),
                        register1 : $('#register1').val(),
                        proveSubmitTime : $('#proveSubmitTime').val(),
                        proveSubmitTime1 : $('#proveSubmitTime1').val(),
                        proveAuditTime : $('#proveAuditTime').val(),
                        proveAuditTime1 : $('#proveAuditTime1').val(),
                        isGuaranteeCard: $('#isGuaranteeCard').val(),
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
                        field : 'userId',
                        title : '用户ID'
                    },
                    {
                        field : 'realName',
                        title : '姓名'
                    },
                    {
                        field : 'mobile',
                        title : '手机号',
                        /*formatter : function(value, row, index) {
                            if (value == '01') {
                                return '<span class="label label-primary">嘉薪平台</span>';
                            } else if (value == '02') {
                                return '<span class="label label-primary">嘉福平台</span>';
                            }
                        }*/
                    },
                    {
                        field : 'email',
                        title : '邮箱'
                    },
                    {
                        field : 'userChannelId',
                        title : '用户渠道',
                        align : 'center',
                        formatter : function(value, row, index) {
                            if (value == '01') {
                                return '<span class="label label-primary">嘉福</span>';
                            } else if (value == '02') {
                                return '<span class="label label-primary">嘉薪</span>';
                            }else if (value == '03') {
                                return '<span class="label label-primary">智慧嘉</span>';
                            }
                        }
                    },
                    {
                        field : 'companyId',
                        title : '企业ID'
                    },
                    {
                        field : 'companyName',
                        title : '企业名称'
                    },
                    {
                        field : 'cardid',
                        title : '身份证号'
                    },
                    {
                        field : 'proveState',
                        title : '认证状态',
                        align : 'center',
                        formatter : function(value, row, index) {
                            if (value == '0') {
                                return '<span class="label label-danger">未认证</span>';
                            } else if (value == '1') {
                                return '<span class="label label-primary">已认证</span>';
                            } else if (value == '2') {
                                return '<span class="label label-danger">认证中</span>';
                            }else if (value == '3') {
                                return '<span class="label label-danger">审核中</span>';
                            }else if (value == '4') {
                                return '<span class="label label-danger">未通过</span>';
                            }else {
                                return '<span class="label label-danger">未认证</span>';
                            }
                        }
                    },
                    {
                        field : 'isGuaranteeCard',
                        title : '担保支付',
                        align : 'center',
                        formatter : function(value, row, index) {
                            if (value == '1') {
                                return '<span class="label label-primary ">已开通</span>';
                            } else
                                return '<span class="label label-danger">未开通</span>';
                        }
                    },
                    {
                        field : 'register',
                        title : '注册时间'
                    },
                    {
                        field : 'proveSubmitTime',
                        title : '认证提交时间'
                    },
                    {
                        field : 'proveAuditTime',
                        title : '认证完成时间'
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
        '&isGuaranteeCard='+$('#isGuaranteeCard').val()+
        '&proveAuditTime1='+$('#proveAuditTime1').val();
    //后端导出的方法
    document.location.href = "/oldtoDownExcel/userManageDownExcel?"+ data;
}

