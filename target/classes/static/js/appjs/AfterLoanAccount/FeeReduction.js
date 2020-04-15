var prefix = "/feeReductionController/feeReduction";
var submited = false;
var customNo = "";
$(function () {
    var ident = $("#init").val();
    if ("2" == ident) {
        $("#refuseDiv").show();
        $("#appPerson").show();
        $("#appTime").show();
        $("#appmoney").attr("readonly", "readonly");
        $("#appmoney").attr("disabled", "disabled");
        $("input[type='text']").attr("readonly", "readonly");//设为只读
        $("input[type='text']").attr("disabled", "disabled");//不可点击
        $('#reson').attr("readonly", "readonly");//设为只读
    }
});
function close(){
    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    parent.layer.close(index);
    window.parent.location.reload();
}

$("#submmit").click(function () {
    var appmoney = $("#appmoney").val().trim();//减免预期费用
    var latestTimeEnd = $("#latestTimeEnd").val().trim();//约定还款日
    var reson = $("#reson").val().trim();//原因
    var repayPlanNo = $("#repayPlanNo").val();//还款计划编号
    var lastPayDay = $("#lastPayDay").val();//原最后还款日
    var overdueFee = $("#overdueFee").text();//应还逾期费用（当期逾期费用-已还逾期费用）
    var nowoverdueFee = $("#nowoverdueFee").val();//当期逾期费用


    var ident = $("#init").val();
    if ("2" == ident) {//审核费用减免
        $.ajax({
            type: "POST",
            data: JSON.stringify({
                'state': "1",
                'nowoverdueFee': $("#nowoverdueFee").val(),
                'appmoney': $("#appmoney").val(),
                'makeTime': $("#latestTimeEnd").val(),
                'repayPlanNo': $("#repayPlanNo").val(),
                'appNo': $("#appNo").val()
            }),
            dataType: "json",
            url: prefix + "/modifyRepayPlanByNo",
            contentType: "application/json;charset=utf-8",
            success: function (response) {
                if ("200" == response.statu) {
                    alert("成功");
                    close();
                    // layer.msg("减免成功");
                    // $('.aler').css('display','inline-block');
                    // $('.aler').html("减免成功1！").fadeOut(3000);
                }
                if ("500" == response.statu) {
                    alert("通过失败");
                    close();
                    // layer.msg("减免失败");
                    // $('.aler').css('display','inline-block');
                    // $('.aler').html("减免失败！").fadeOut(3000);
                }
            }
        });

    } else {//添加费用减免
        if ("" == appmoney) {
            alert("请输入减免预期费用");
            return false;
        }
        if (appmoney <= 0) {
            alert("减免金额必须大于0");
            return false;
        }
        var reg = /^\d{0,8}\.{0,1}(\d{1,2})?$/;
        if (!reg.test(appmoney)) {
            alert("请输入小数位不超过2位的数值！");
            return false;
        }

        if (parseInt(appmoney) > parseInt(overdueFee)) {
            alert("减免金额必须小等于应还逾期费用");
            return false;
        }
        if ("" == latestTimeEnd) {
            alert("请输入约定还款日");
            return false;
        }
        if ("" == reson) {
            alert("请输入减免原因");
            return false;
        }

        $.ajax({
            type: "POST",
            data: JSON.stringify({
                'repayPlanNo': repayPlanNo,
                'lastPayDay': lastPayDay,
                'overdueFee': overdueFee,
                'nowoverdueFee': nowoverdueFee,
                'appmoney': appmoney,
                'latestTimeEnd': latestTimeEnd,
                'reson': reson
            }), //JSON.stringify(json),
            dataType: "json",
            url: prefix + "/addRepayLastDayAndOverdue",
            contentType: "application/json;charset=utf-8",
            success: function (response) {
                // var json = JSON.stringify(response);
                console.info(response);
                if ("100" == response.statu) {
                    alert("该笔还款计划在审批中");
                    close();
                }
                if ("200" == response.statu) {
                    alert("申请成功");
                    close();
                    // layer.msg("减免成功");
                    // $('.aler').css('display','inline-block');
                    // $('.aler').html("减免成功1！").fadeOut(3000);
                }
                if ("500" == response.statu) {
                    alert("申请失败");
                    close();
                    // layer.msg("减免失败");
                    // $('.aler').css('display','inline-block');
                    // $('.aler').html("减免失败！").fadeOut(3000);
                }
            }
        });
    }
});
$("#refuse").click(function () {
    $.ajax({
        type: "POST",
        data: JSON.stringify({
            'state': "2",
            'nowoverdueFee': $("#nowoverdueFee").val(),
            'appmoney': $("#appmoney").val(),
            'makeTime': $("#latestTimeEnd").val(),
            'repayPlanNo': $("#repayPlanNo").val(),
            'appNo': $("#appNo").val()
        }),
        dataType: "json",
        url: prefix + "/modifyRepayPlanByNo",
        contentType: "application/json;charset=utf-8",
        success: function (response) {
            if ("200" == response.statu) {
                alert("拒绝成功");
                close();
                // layer.msg("减免成功");
                // $('.aler').css('display','inline-block');
                // $('.aler').html("减免成功1！").fadeOut(3000);
            }
            if ("500" == response.statu) {
                alert("拒绝失败");
                close();
                // layer.msg("减免失败");
                // $('.aler').css('display','inline-block');
                // $('.aler').html("减免失败！").fadeOut(3000);
            }
        }
    });
});