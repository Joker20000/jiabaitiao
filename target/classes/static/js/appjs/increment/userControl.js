var prefix = "/userControlController"
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
                        userName : $('#realName').val(),
                        userMobile : $('#mobile').val(),
                        userEmail : $('#email').val(),
                        userChannelId : $('#userChannelId').val(),
                        entName : $('#companyName').val(),
                        createDateStart : $('#register').val(),
                        createDateEnd : $('#register1').val(),
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
                        field : 'userName',
                        title : '姓名'
                    },
                    {
                        field : 'userMobile',
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
                        field : 'userEmail',
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
                        field : 'entId',
                        title : '企业ID'
                    },
                    {
                        field : 'entName',
                        title : '企业名称'
                    },
                    {
                        field : 'createDate',
                        title : '开通时间'
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
        '&companyName='+$('#companyName').val()+
        '&register='+$('#register').val()+
        '&register1='+$('#register1').val();
    //后端导出的方法
    document.location.href = "/userControlController/excel?"+ data;
}

