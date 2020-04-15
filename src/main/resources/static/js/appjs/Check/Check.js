
var prefix = "/ch/ec"
$(function() {
    load();
});

//预留
function getchackType(val){
    /*if (val == null || val == '') {
        val = '101';
    }*/
    return val;
}

function load() {
    var h = $(window).height() - 120;
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
                        chackNo : $('#chackNo').val(),
                        realname : $('#realname').val(),
                        mobile : $('#mobile').val(),
                        chackType : getchackType($('#chackType').val()), //暂定
                        chackState : $('#chackState').val(),
                        chackResult : $('#chackResult').val(),
                        applyTimeStart : $('#applyTimeStart').val(),
                        applyTimeEnd : $('#applyTimeEnd').val(),
                        lastTimeStart : $('#lastTimeStart').val(),
                        lastTimeEnd : $('#lastTimeEnd').val(),
                        /*valid : $('#valid').val()*/
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
                        field : 'chackno',
                        title : '信审编号',
                        formatter: function(value,row,index){
                            var e = '<a href="#" onclick="add(\''+row.chackNo+'\',\''+row.accountNo+'\',\''+row.userId+'\')">'+row.chackNo+'</a>'
                            return e;
                        }
                    },
                    {
                        field : 'realname',
                        title : '姓名'
                    },
                    {
                        field : 'mobile',
                        title : '手机'
                    },
                    {
                        field : 'chackType',
                        title : '认证类型',
                        align : 'center',
                        formatter : function(value, row, index) {
                            if (row.chackType == '01') {
                                return '<span class="label label-primary">自动认证</span>';
                            } else if (row.chackType == '02') {
                                return '<span class="label label-primary">联系信息认证</span>';
                            }else if (row.chackType == '03') {
                                return '<span class="label label-primary">手机认证</span>';
                            }else if (row.chackType == '04') {
                                return '<span class="label label-primary">淘宝认证</span>';
                            }else if (row.chackType == '05') {
                                return '<span class="label label-primary">社保认证</span>';
                            }
                        }
                    },
                    {
                        field : 'chackState',
                        title : '审核状态',
                        align : 'center',
                        formatter : function(value, row, index) {
                            if (row.chackState == '0') {
                                return '<span class="label label-primary">待初审</span>';
                            } else if (row.chackState == '1') {
                                return '<span class="label label-primary">初审挂起</span>';
                            }
                            else if (row.chackState == '2') {
                                return '<span class="label label-primary">待复审</span>';
                            }
                            else if (row.chackState == '3') {
                                return '<span class="label label-primary">复审挂起</span>';
                            }
                            else if (row.chackState == '4') {
                                return '<span class="label label-primary">复审退回</span>';
                            }
                            else if (row.chackState == '5') {
                                return '<span class="label label-primary">已完成</span>';
                            }
                        }
                    },
                    {
                        field : 'applyTime',
                        title : '申请时间'
                    },
                    {
                        field : 'lastTime',
                        title : '完成时间'
                    },
                    {
                        field : 'chackResult',
                        title : '审核结果',
                        formatter : function(value, row, index) {
                            if (row.chackResult == '0') {
                                return '<span class="label label-primary">审核中</span>';
                            } else if (row.chackResult == '1') {
                                return '<span class="label label-primary">通过</span>';
                            }else if (row.chackResult == '2') {
                                return '<span class="label label-primary">拒绝</span>';
                            }
                        }
                    },
                    {
                        field : 'valid',
                        title : '审核时效(h)'
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
function add(chackno,accountNo,userId) {
    var overview = $("#overview").val();<!--区分初审、复审、总览-->
    var pass = 4;
    layer.open({
        type: 2,
        title: '审核',
        maxmin: true,
        shadeClose: false,
        area: ['800px', '520px'],
        content: '/ch/ec/personal/' + chackno +"/" + accountNo +"/"+ pass+"/"+userId+"?overview="+overview
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
function exportExcel() {
    data = 'eid='+$('#searchName').val()+
        '&ename='+$('#searchName1').val()+
        '&chackType='+$('#chackType option:selected').val()+
        '&realname='+$('#realname').val()+
        '&mobile='+$('#mobile').val()+
        '&chackState='+$('#chackState option:selected').val()+
        '&applyTimeStart='+$('#applyTimeStart').val()+
        '&applyTimeEnd='+$('#applyTimeEnd').val()+
        '&lastTimeStart='+$('#lastTimeStart').val()+
        '&chackResult='+$('#chackResult').val()+
        '&lastTimeEnd='+$('#lastTimeEnd').val();
    //后端导出的方法
    document.location.href = "/toDownExcel/DownCheExcel?"+ data;
}

