var prefix = "/blackListController"
$(function() {
    load();
});

function load() {
    var h = $(window).height() - 150;
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix + "/addBlackList", // 服务器数据的加载地址
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
                        realname : $('#realname').val(),
                        email : $('#email').val(),
                        companyName : $('#companyName').val(),
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
                        field : 'companyId',
                        title : '企业编号'
                    },
                    {
                        field : 'companyName',
                        title : '企业名称'
                    },
                    {
                        field : 'realName',
                        title : '员工姓名',
                    },
                    {
                        field : 'email',
                        title : '邮箱'
                    },
                    /*{
                        field : 'userState',
                        title : '注销状态',
                        align : 'center',
                        formatter : function(value, row, index) {
                            if (value == '0') {
                                return '<span class="label label-primary">无效</span>';
                            } else if (value == '1') {
                                return '<span class="label label-primary">有效</span>';
                            }
                        }
                    },*/
                     ],
                fixedColumns: true,
                fixedNumber: 5,
                height:h,
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
        content : prefix + '/blacklistAdd' // iframe的url
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

function resetPwd(id) {
}
function batchRemove() {
    //获得备注信息
    var Remarks=$("#Remarks").val();
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要提交的数据");
        return;
    }else if(Remarks==""||Remarks==null){
        layer.msg("请填写备注信息");
        return;
    }else{
        var strIds = new Array();

        // 遍历所有选择的行数据，取每条数据对应的ID
        $.each(rows, function(i, row) {
            strIds[i] = row['userId'];
        });
        var ids=strIds.join(",");
        //信息框-例2

        layer.msg('确认将所选的'+strIds.length+'名用户加入黑名单吗？', {
            time: 0 //不自动关闭
            ,btn: ['确定', '取消']
            ,yes: function(index){
                layer.close(index);
                $.ajax({
                    type : 'POST',
                    data : {
                        "ids" : ids,
                        "Remarks" : Remarks
                    },
                    dataType : "json",
                    url : prefix + '/insertBlackListState',
                    success : function(result) {
                        //alert(result.success);
                        if (result.success==true) {
                            layer.msg("成功");
                            setTimeout("close()",1000);
                            reLoad();
                        } else {
                            layer.msg("失败");
                        }
                    }
                });
            }
        });
    }
}
function close(){
    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    parent.layer.close(index);
    window.parent.location.reload();
}