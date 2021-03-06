
var prefix = "/rt/re"
$(function() {
    load();
    //reLoad();
    $('#qBeginTime').datepicker({
        todayBtn : "linked",
        autoclose : true,
        todayHighlight : true,
        endDate : new Date()
    }).on('changeDate',function(e){
        var startTime = e.date;
        $('#qEndTime').datepicker('setStartDate',startTime);
    });
//结束时间：
    $('#qEndTime').datepicker({
        todayBtn : "linked",
        autoclose : true,
        todayHighlight : true,
        endDate : new Date()
    }).on('changeDate',function(e){
        var endTime = e.date;
        $('#qBeginTime').datepicker('setEndDate',endTime);
    });
});

function load() {
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
                // search : true, // 是否显示搜索框
                showColumns : false, // 是否显示内容下拉框（选择显示的列）
                sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
                // "server"
                queryParams : function(params) {
                    return {
                        // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit : params.limit,
                        offset : params.offset,
                        repaynum : $('#searchName').val(),
                        cflow : $('#searchName1').val(),
                        starTime : $('#qBeginTime').val(),
                        endTime : $('#qEndTime').val(),
                        loannum : $('#searchName2').val()
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
                        field : 'repaynum',
                        title : '还款订单号'
                    },
                    {
                        field : 'ename',
                        title : '企业名称'
                    },
                    {
                        field : 'ramount',
                        title : '本金还款'
                    },
                    {
                        field : 'rproccost',
                        title : '手续费还款'
                    },
                    {
                        field : 'rovercost',
                        title : '逾期费用还款'
                    },
                    {
                        field : 'stayamountsum',
                        title : '还款总额'
                    },
                    {
                        field : 'repaydate',
                        title : '还款时间'
                    },
                    {
                        field : 'repaytype',
                        title : '还款方式',
                        formatter : function(value, row, index) {
                            if (value == '0') {
                                return '<span class="label label-primary">系统代扣</span>';
                            } else if (value == '1') {
                                return '<span class="label label-primary">主动还款</span>';
                            }
                        }
                    },
                    {
                        field : 'loannum',
                        title : '借款订单号'
                    },
                    {
                        field : 'cflow',
                        title : '渠道流水号'
                    }]
            });
}
function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}
function add() {
    layer.open({
        type : 2,
        title : '增加',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : prefix + '/add' // iframe的url
    });
}
function read(id) {
    layer.open({
        type : 2,
        title : '查看',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : prefix + '/read/' + id // iframe的url
    });
}
function remove(id) {
    layer.confirm('确定要删除选中的记录？', {
        btn : [ '确定', '取消' ]
    }, function() {
        $.ajax({
            url : prefix + "/remove",
            type : "post",
            data : {
                'id' : id
            },
            success : function(r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    })
}

function resetPwd(id) {
}
function batchRemove() {
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要删除的数据");
        return;
    }
    layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
        btn : [ '确定', '取消' ]
        // 按钮
    }, function() {
        var ids = new Array();
        // 遍历所有选择的行数据，取每条数据对应的ID
        $.each(rows, function(i, row) {
            ids[i] = row['id'];
        });
        $.ajax({
            type : 'POST',
            data : {
                "ids" : ids
            },
            url : prefix + '/batchRemove',
            success : function(r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    }, function() {});
}

/* 下载*/
function DownRepExcel() {
    $.ajax({
        url :"/toDownExcel/DownRepExcel",
        type : "post",
        data : {

            'repaynum' : $('#searchName').val(),
            'cflow': $('#searchName1').val(),
            'starTime' : $('#qBeginTime').val(),
            'endTime' : $('#qEndTime').val(),
            'loannum' : $('#searchName2').val(),
        },
        success : function() {
            layer.msg("成功保存至D/loanExcel下",{icon: 1});
        }
    });
}


function exportExcel() {
    data = 'repaynum='+$('#searchName').val()+
        '&cflow='+$('#searchName1').val()+
        '&starTime='+$('#qBeginTime').val()+
        '&endTime='+$('#qEndTime').val()+
        '&loannum='+$('#searchName2').val();
    //后端导出的方法
    document.location.href = "/toDownExcel/DownRepExcel?"+ data;
}