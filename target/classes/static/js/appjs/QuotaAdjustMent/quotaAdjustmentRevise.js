var prefix = "/quotaAdjustmentController"

$(function() {

    //调整金额内容  改变 - 触发
    $("#quotaq").keyup(function () {
        var showChange = $("#quotaq").val();
        $("#showChange").html(digitUppercase(showChange))
        var reviseMoney = $("#creditLimit").text();
        var revise = reviseShowxin();
        if(revise == "0"){
            var moneyAdd = floatAdd(showChange,reviseMoney);
            $("#shouxin").html(moneyAdd);
            $("#shouxinUp").html(digitUppercase(moneyAdd));
        }else if(revise == "1"){
            var moneySub = floatSub(reviseMoney,showChange);
            if (moneySub >= 0){
                $("#shouxin").html(moneySub);
                $("#shouxinUp").html(digitUppercase(moneySub));
            }else {
                $("#shouxin").html(moneySub);
                $("#shouxinUp").html(digitUppercase(moneySub));
                $('.aler').css('display','inline-block');
                $('.aler').html("系统提示,调整后授信额度必须>=0!").fadeOut(3000);
            }
        }
    })
    // 点击 增加  减少 按钮 触发
    $("#down").click(function () {
        var moneyAdd = floatAdd($("#quotaq").val(),$("#creditLimit").text());
        $("#shouxin").html(moneyAdd);
        $("#shouxinUp").html(digitUppercase(moneyAdd));
    })
    $("#up").click(function () {
        var moneySub = floatSub($("#creditLimit").text(),$("#quotaq").val());
        if (moneySub >= 0){
            $("#shouxin").html(moneySub);
            $("#shouxinUp").html(digitUppercase(moneySub));
        }else {
            $("#shouxin").html(moneySub);
            $("#shouxinUp").html(digitUppercase(moneySub));
            $('.aler').css('display','inline-block');
            $('.aler').html("系统提示,调整后授信额度必须>=0!").fadeOut(3000);
        }
    })
    //金额汉化
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
    $("#creditLimitUp").html(digitUppercase($("#creditLimit").text()));
    //点击提交  校检 返回状态
    $("#sub").click(function () {
        var shouxin= $("#shouxin").html(); //调整后授信额度
        var revise=reviseShowxin();//获得单选按钮的值 0增加1减少 -1未选中
        var vipType = changeVipLevel();
        var Change=$("#quotaq").val();//授信改动值
        var adjustReason=$("#adjustReason").val();//原因
        var pwd=$("#pwd").val();//密码
        var userId=$("#userId").text();//用户id
        var beforeVipLevel = $("#vipLevel").text().substr(3,1);
        console.log(beforeVipLevel)
        var uname=$("#uname").val();//调整人
        var realName=$("#realName").text();//用户id
        console.log(vipType)
        if(revise == '-1'&& vipType == '-1'){
            /*$('.aler').css('display','inline-block');
            $('.aler').html("系统提示,请选择调整方式!").fadeOut(3000);*/
            layer.msg("系统提示,请选择调整方式!");
            return;
        }
        if(vipType == '-2'){
            /*$('.aler').css('display','inline-block');
            $('.aler').html("系统提示,请选择调整方式!").fadeOut(3000);*/
            layer.msg("系统提示,请选择要调整的会员级别!");
            return;
        }else if(vipType == beforeVipLevel){
               layer.msg("系统提示,不可选择和当前会员级别一致!");
               return;
        }
        if(revise == '-1'){
            Change = '0';
        }

       if(revise != '-1') {
           if(revise == '0'){
               var revise1 = "增加";
           }else {
               var revise1 = "减少";
           }
           if (Change != '') {
               var mon = parseInt(Change);
               if (mon <= 0) {
                   $('.aler').css('display', 'inline-block');
                   $('.aler').html("系统提示,请填写调整额度且大于0!").fadeOut(3000);
                   return;
               }
           } else {
               $('.aler').css('display', 'inline-block');
               $('.aler').html("系统提示,请填写调整额度且大于0!").fadeOut(3000);
               return;
           }
           if (shouxin == '' || shouxin < 0) {
               $('.aler').css('display', 'inline-block');
               $('.aler').html("系统提示,调整后授信额度必须>=0!").fadeOut(3000);
               return;
           }
       }
        if(adjustReason == ''){
            $('.aler').css('display','inline-block');
            $('.aler').html("系统提示,请填写调整原因!").fadeOut(3000);
            return;
        }
        if(pwd == ''){
            $('.aler').css('display','inline-block');
            $('.aler').html("系统提示,请填写密码!").fadeOut(3000);
            return;
        }
        if(uname == ''){
            $('.aler').css('display','inline-block');
            $('.aler').html("系统提示,当前用户未登录，请登录后再来！").fadeOut(3000);
            return;
        }
        var remindMsg = '';
        console.log(Change)
        if(revise1!=''&& Change!='' && Change!='0')
         remindMsg = '确定为'+realName+revise1+Change+'元额度吗？'
        if(vipType!='-1')
         remindMsg = '确定为'+realName+'调整会员级别为VIP'+vipType+'吗？';
        if(revise1!=''&& Change!='' && Change!='0' && vipType!='-1')
         remindMsg = '确定为'+realName+revise1+Change+'元额度,' +
             '\n并调整会员级别为VIP'+vipType+'吗？';
        layer.msg(remindMsg, {
            time: 0 //不自动关闭
            ,btn: ['确定', '取消']
            ,yes: function(index){

                $.get(
                    prefix+"/updateAuota",
                    {userId:userId,state1:revise,quota:Change,adjustReason:adjustReason,pwd:pwd,beforeVipLevel:beforeVipLevel,afterVipLevel:vipType},
                    function (data) {
                        if(data=='0'){
                            $('.aler').css('display','inline-block');
                            $('.aler').html("系统提示,密码与当前登录用户密码不一致，请重新输入！").fadeOut(3000);
                        }else if(data=='1'){
                            /*$('.aler').css('display','inline-block');
                            $('.aler').html("系统提示,调整额度成功！").fadeOut(2000);*/
                            layer.msg("系统提示,调整额度成功！");
                            layer.close(index);
                            setTimeout("close()",1000);
                        }else if(data=='2'){
                            $('.aler').css('display','inline-block');
                            $('.aler').html("系统提示,请选择调额方式 ！").fadeOut(3000);
                        }else if(data=='4'){
                            $('.aler').css('display','inline-block');
                            $('.aler').html("系统提示,授信后授信额度不能<0！").fadeOut(3000);
                        }
                    }
                )
            }
        });
    })
});

//获取 单选框 加减
function reviseShowxin() {
    var revise = document.getElementsByName("revise");
    for (var i = 0 ; i < revise.length ; i++){
        if(revise[i].checked){
            return revise[i].value;
        }
    }
    return "-1";
}
function changeVipLevel() {
    var options = document.getElementsByName("options");
    console.log(options)
    if(!options[0].checked)   return "-1";
    var vipType = document.getElementById("vipType")
    console.log(vipType.length)

    var index = vipType.selectedIndex;
    console.log(index)
    if(index == '0'){
        return "-2";
    }
    else
        return vipType.options[index].value;
    // for (var i = 0 ; i < vipType.length ; i++){
    //     console.log(vipType.options[i].value)
    //     if(vipType[i].selected ){
    //         return vipType[i].value;
    //     }
    // }

}
//数字 加
function floatAdd(arg1, arg2) {
    var r1, r2, m;
    try {
        r1 = arg1.toString().split(".")[1].length;
    } catch (e) {
        r1 = 0;
    }
    try {
        r2 = arg2.toString().split(".")[1].length;
    } catch (e) {
        r2 = 0;
    }
    m = Math.pow(10, Math.max(r1, r2));
    return ((arg1 * m + arg2 * m) / m).toFixed(2);
}


//数字 减
function floatSub(arg1, arg2) {
    var r1, r2, m, n;
    try {
        r1 = arg1.toString().split(".")[1].length;
    } catch (e) {
        r1 = 0;
    }
    try {
        r2 = arg2.toString().split(".")[1].length;
    } catch (e) {
        r2 = 0;
    }
    m = Math.pow(10, Math.max(r1, r2));
    //动态控制精度长度
    n = (r1 >= r2) ? r1 : r2;
    return ((arg1 * m - arg2 * m) / m).toFixed(n);
}
function close(){
    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    parent.layer.close(index);
    window.parent.location.reload();
}