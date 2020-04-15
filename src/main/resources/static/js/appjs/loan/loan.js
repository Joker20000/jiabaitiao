
var prefix = "/loan/loanController"
$(function() {
	load();
});

function load() {
	$('#exampleTable')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : prefix + "/list", // 服务器数据的加载地址
				//	showRefresh : true,
				//	showToggle : true,
				//	showColumns : true,
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
				//search : true, // 是否显示搜索框
				showColumns : false, // 是否显示内容下拉框（选择显示的列）
				sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
				queryParams : function(params) {
					return {
						//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
						limit : params.limit,
						offset : params.offset,
                        chackState:$('#chackState').val(),
                        qBeginTime:$('#qBeginTime').val(),
                        qEndTime:$('#qEndTime').val(),
                        eName:$('#eName').val(),
                        loanNum:$('#loanNum').val(),
                        orderState:$('#orderState').val(),
                        repayState:$('#repayState').val(),
                        overState:$('#overState').val()
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
						field : 'loanNum',
						title : '借款订单号'
					},
                    {
                        field : 'eName',
                        title : '企业名称'
                    },
                    {
                        field : 'amount',
                        title : '借款金额'
                    },
                    {
                        field : 'repayDate',
                        title : '最迟还款日'
                    },
                    {
                        field : 'orderState',
                        title : '订单状态',
                        align : 'center',
                        formatter : function(value, row, index) {
                            if (value == '0') {
                                return '<span class="label label-primary">待处理</span>';
                            } else if (value == '1') {
                                return '<span class="label label-primary">处理中</span>';
                            } else if (value == '2') {
                                return '<span class="label label-primary">成功</span>';
                            } else if (value == '3') {
                                return '<span class="label label-danger">失败</span>';
                            }
                        }
                    },
                    {
                        field : 'chackState',
                        title : '审批状态',
                        align : 'center',
                        formatter : function(value, row, index) {
                            if (value == '0') {
                                return '<span class="label label-primary">待审批</span>';
                            } else if (value == '1') {
                                return '<span class="label label-primary">审批通过</span>';
                            } else if (value == '2') {
                                return '<span class="label label-danger">审批驳回</span>';
                            }
                        }
                    },
                    {
                        field : 'overState',
                        title : '逾期状态',
                        align : 'center',
                        formatter : function(value, row, index) {
                            if (value == '0') {
                                return '<span class="label label-primary">未逾期</span>';
                            } else if (value == '1') {
                                return '<span class="label label-danger">已逾期</span>';
                            }
                        }
                    },
                    {
                        field : 'repayState',
                        title : '还款状态',
                        align : 'center',
                        formatter : function(value, row, index) {
                            if (value == '0') {
                                return '<span class="label label-danger">待还款</span>';
                            } else if (value == '1') {
                                return '<span class="label label-primary">还款中</span>';
                            } else if (value == '2') {
                                return '<span class="label label-primary">已还清</span>';
                            }
                        }
                    },
                    {
                        field : 'createDate',
                        title : '订单生成时间'
                    }]
			});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}


function batchRemove() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要审核的借款",{icon: 2});
		return;
	}
	var fag = true;
	var amountSum = 0;
    $.each(rows, function(i, row) {
        amountSum+=Number(row['amount']);
        if (row['chackState'] != '0'){
            layer.msg(row['loanNum']+"该笔借款订单已审核完毕！",{icon: 2});
            fag = false;
            return;
        }
    });

    //多窗口模式，层叠置顶
    if(fag){
    layer.open({
        type: 1 //此处以iframe举例
        ,title: '放款审批'
        ,area: ['390px', '330px']
        ,maxmin: true
        ,content: '<div style="padding: 50px; line-height: 22px;font-weight: 600;"><div>借款总笔数：'+ rows.length + '笔</div><br><div>借款总金额：'+ amountSum + '元</div><br><div style="color:#FF0000;line-height: 22px;font-weight: 800;font-size:20px;">'+ digitUppercase(amountSum) + '</div></div>'
        ,btn: ['通过','驳回','取消'] //只是为了演示
        ,yes: function(){
            layer.confirm("确认放款？", {
                btn : ['确认','取消']
            }, function(){
                var ids = new Array();
                // 遍历所有选择的行数据，取每条数据对应的ID
                $.each(rows, function(i, row) {
                    ids[i] = row['loanNum'];
                });
                $.ajax({
                    type : 'POST',
                    data : {
                        "ids" : ids,
                        "state" : '1'
                    },
                    url : prefix + '/batchRemove',
                    success : function(r) {
                        layer.closeAll();
                        if (r.code == 0) {
                            layer.msg(r.msg,{icon: 1});
                            reLoad();
                        } else {
                            layer.msg(r.msg,{icon: 2});
                        }
                    }
                });
            }, function(){
                layer.closeAll();
            })
        }
        ,btn2: function(){
            layer.confirm("确认驳回？", {
                btn : ['驳回','取消']
            }, function(){
                var ids = new Array();
                // 遍历所有选择的行数据，取每条数据对应的ID
                $.each(rows, function(i, row) {
                    ids[i] = row['loanNum'];
                });
                $.ajax({
                    type : 'POST',
                    data : {
                        "ids" : ids,
                        "state" : '2'
                    },
                    url : prefix + '/batchRemove',
                    success : function(r) {
                        layer.closeAll();
                        if (r.code == 0) {
                            layer.msg(r.msg,{icon: 1});
                            reLoad();
                        } else {
                            layer.msg(r.msg,{icon: 2});
                        }
                    }
                });
            }, function(){
                layer.closeAll();
            })
        }
        ,btn3: function(){
            layer.closeAll();
        }
    });
    }
}


function loanOrderDownExcel() {
        $.ajax({
            url :"/toDownExcel/loanOrderDownExcel",
            type : "post",
            data : {
                'loanNum' : $('#loanNum').val(),
                'eName' : $('#eName').val(),
                'orderState' : $('#orderState').val(),
                'overState' : $('#overState').val(),
                'repayState' : $('#repayState').val(),
                'qBeginTime' : $('#qBeginTime').val(),
                'qEndTime' : $('#qEndTime').val(),
                'chackState' : $('#chackState').val()
            },
            success : function(r) {
            }
        });
}



function exportExcel() {
    data = 'loanNum='+$('#loanNum').val()+
            '&eName='+$('#eName').val()+
            '&orderState='+$('#orderState').val()+
            '&overState='+$('#overState').val()+
            '&repayState='+$('#repayState').val()+
            '&qBeginTime='+$('#qBeginTime').val()+
            '&qEndTime='+$('#qEndTime').val()+
            '&chackState='+$('#chackState').val();
    //后端导出的方法
    document.location.href = "/toDownExcel/loanOrderDownExcel?"+ data;
}

var digitUppercase = function(n) {
    var fraction = ['角', '分'];
    var digit = [
        '零', '壹', '贰', '叁', '肆',
        '伍', '陆', '柒', '捌', '玖'
    ];
    var unit = [
        ['元', '万', '亿'],
        ['', '拾', '佰', '仟']
    ];
    var head = n < 0 ? '欠' : '';
    n = Math.abs(n);
    var s = '';
    for (var i = 0; i < fraction.length; i++) {
        s += (digit[Math.floor(n * 10 * Math.pow(10, i)) % 10] + fraction[i]).replace(/零./, '');
    }
    s = s || '整';
    n = Math.floor(n);
    for (var i = 0; i < unit[0].length && n > 0; i++) {
        var p = '';
        for (var j = 0; j < unit[1].length && n > 0; j++) {
            p = digit[n % 10] + unit[1][j] + p;
            n = Math.floor(n / 10);
        }
        s = p.replace(/(零.)*零$/, '').replace(/^$/, '零') + unit[0][i] + s;
    }
    return head + s.replace(/(零.)*零元/, '元')
        .replace(/(零.)+/g, '零')
        .replace(/^整$/, '零元整');
};