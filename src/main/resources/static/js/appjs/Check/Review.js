var prefix = "/ch/ec"
var submited = false;
var customNo = "";
$(function () {
    /*    load();*/
    $("#month1").text(getDate("sub", 0));
    $("#month2").text(getDate("sub", 1));
    $("#month3").text(getDate("sub", 2));
    $("#month4").text(getDate("sub", 3));
    $("#month5").text(getDate("sub", 4));

    var money =  $("#moneys").val();
    var n =$("#anNiu1").val();
    $("#modifyPwd input[name='options']").each(function () {
        var j =  $(this).val() ;
        if(n == j){
            $(this).prop("checked",true);
        }
    });

    var n =$("#anNiu2").val();
    $("#selfushen input[name='options']").each(function () {
        var j =  $(this).val() ;
        if(n == j){
            $(this).prop("checked",true);
        }
    });


    function submitForm() {
        var f = document.getElementById("saveButton");
        if (!submited)
        {
            parent.layer.msg(1);
            submited = true;
            f.submit();
        }
        else {
            parent.layer.msg("请不要重复提交！");
        }
    }






    var pass =  $("#pass").val();
    if (pass == 1){
        $("#saveButtons").hide();
    }
    else if (pass == 2) {
        $("#saveButton").hide();
    }
    else if (pass == 3) {
        $("#saveButton").hide();
        $("#saveButtons").hide();
    }
    else if (pass == 4) {
        $("#saveButton").hide();
        $("#saveButtons").hide();
    }

    /* 联系地址刷新 */
    loadAddress();
    /* 刷新拼接联系人 */
    onloadLXR();
    /* 手机认证刷新 */
    loadMobileReport($("#accountNo").val());



    //初审
    $("#saveButton").click(function () {
        var type=  $(this).prev().val();
        var form ;
        if(type =="1"){
            form = new FormData(document.getElementById("modifyPwd"));
            var result = $("#modifyPwd input[name='options']:checked").val();
            form.append("result",result);
        }else {
            return;
        }
        $.ajax({
            url: "/ch/ec/updateStu",
            type: "post",
            data: form,
            processData: false,
            contentType: false,
            success: function (data) {
                if (data.code=="200") {
                    parent.layer.msg("操作成功");
                    parent.reLoad();
                    var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                    parent.layer.close(index);
                    /*alert(data.msg);
                    window.location.href="/ch/ec/fir";*/
                }else{
                    parent.layer.msg(data.msg);
                }
            },
            error: function (e) {
                parent.layer.msg("错误！！"+e.exception);
            }
        });
    });

    //复审
    $("#saveButtons").click(function () {
        var type=  $(this).prev().val();
        var form ;
        if(type =="2"){
            form = new FormData(document.getElementById("selfushen"));
            var result = $("#selfushen input[name='options']:checked").val();
            form.append("result",result);
        }else {
            return;
        }

        $.ajax({
            url: "/ch/ec/updateStu",
            type: "post",
            data: form,
            processData: false,
            contentType: false,
            success: function (data) {
                if (data.code=="200") {
                    parent.layer.msg("操作成功");
                    parent.reLoad();
                    var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                    parent.layer.close(index);
                    /*alert(data.msg);
                    window.location.href="/ch/ec/sec";*/
                }else{
                    parent.layer.msg(data.msg);
                }
            },
            error: function (e) {
                parent.layer.msg("错误！！"+e.exception);
            }
        });
    });

    // laydate({
    //     elem: '#birth'
    // });

});
//查看
function view(jfid) {
    $.ajax({
        url: "/ch/ec/rechJfOrderDetail",   //请求参数
        data: {"jfid" : jfid},
        cache: false,
        type: "post",
        dataType: "json",
        success: function (data) {
            // var json = data.parseJSON();
            // console.info(json);
            $("#month5Detail").text(data.monthNum5);
            $("#month4Detail").text(data.monthNum4);
            $("#month3Detail").text(data.monthNum3);
            $("#month2Detail").text(data.monthNum2);
            $("#month1Detail").text(data.monthNum1);
            $("#monthSumDetail").text(data.summary);
        }
    });
    // $("#views").hide();//查看
    // $("#collapses").show();//收起
    $("#jfData").show();//数据Table
};
//收起
function collapse () {
    $("#views").show();//查看
    $("#collapses").hide();
    $("#jfData").hide();//数据Table
};

function getDate(way, month) {
    var time = new Date();
    time.setDate(time.getDate());//获取Day天后的日期
    var y = time.getFullYear();
    var m;
    if("sub" == way){//减
        if (time.getMonth() - month + 1>12){
            y = y-1;
            m = time.getMonth() + month - 11;//获取当前月份的日期 d
        }else{
            if(time.getMonth() - month + 1<1){//如果当前月份为11月,month-11时,月份会为00,所以会有月+13=12月,年-1
                y = y-1;
                m = time.getMonth() - month + 13;
            }else{
                m = time.getMonth() - month + 1;
            }
        }
    }else{//加
        if (time.getMonth() + month + 1>12){
            y = y+1;
            m = time.getMonth() + month - 11;//获取当前月份的日期 d
        }else{
            m = time.getMonth() + month + 1;//获取当前月份的日期 d
        }
    }
    //var d = time.getDate();
    m =(m<10 ? "0"+m:m);
    var result = y + "-" + m;
    return result
}

/**
 * 初始化加载手机认证数据。
 * @param accountNo
 */
function loadMobileReport(accountNo){
    var mobileFormData = new FormData();
    mobileFormData.append("accountNo", accountNo);
    $.ajax({
        url:"/ch/ec/queryMobile",   //请求参数
        data: mobileFormData,
        cache: false,
        type:"post",
        dataType:"json",
        processData: false,
        contentType: false,
        async:false,
        success:function(data) {
            var mobile = data.mobile;
            var html = " <table class='table'> <tr>  <th colspan='3' style='text-align: center'>手机认证</th>"
                + "</tr> <tr><td>文件路径</td><td>文件名称</td> <td>操作</td> </tr>";
            var html1 = " <table class='table'> <tr>  <th colspan='3' style='text-align: center'>手机认证（联系人）</th>"
                + "</tr> <tr><td>文件路径</td><td>文件名称</td> <td>操作</td> </tr>";
            var html2 = " <table class='table'> <tr>  <th colspan='3' style='text-align: center'>淘宝认证</th>"
                + "</tr> <tr><td>文件路径</td><td>文件名称</td> <td>操作</td> </tr>";
            var html3 = " <table class='table'> <tr>  <th colspan='3' style='text-align: center'>社保认证</th>"
                + "</tr> <tr><td>文件路径</td><td>文件名称</td> <td>操作</td> </tr>";

            if(mobile.length == 1){
                if("01" == mobile[0].dataType){
                    customNo = mobile[0].accountNo;
                    // html1 += "<tr><td></td><td></td><td><a href='http://jbttest.jia-fu.cn/kaixinjie/AuthController/viewReportByAccountData?accountNo="+mobile[0].accountNo+"&filePath="+mobile[0].filePath+"&fileName="+''+"&cardId="+mobile[0].cardId+"' onclick='viewReport()' target='_blank'>查看</a></td> </tr>";
                    html1 += "<tr><td></td><td></td><td><a href='"+mobile[0].requesetUrl+"/AuthController/viewReportByAccountData?accountNo="+mobile[0].accountNo+"&filePath="+mobile[0].filePath+"&fileName="+''+"&cardId="+mobile[0].cardId+"' onclick='viewReport()' target='_blank'>查看</a></td> </tr>";
                }
            }
            $.each(mobile,function(i,val){
                if("02" == val.dataType){<!--用户联系人报告-->
                    html1+="<tr><td>"+val.filePath+"</td> <td>"+val.fileName+"</td>";
                    html1+="<td><a href='"+val.requesetUrl+"/AuthController/viewReportByAccountData?accountNo="+val.accountNo+"&filePath="+val.filePath+"&fileName="+val.fileName+"&cardId="+val.cardId+"' target='_blank'>查看</a></td> </tr>";
                }else if("01" == val.dataType){
                    html+="<tr><td>"+val.filePath+"</td> <td>"+val.fileName+"</td>";
                    html+= "<td><a href='"+val.requesetUrl+"/AuthController/viewReportByBasicData?filePath="+val.filePath+"&fileName="+val.fileName+"&mobile="+val.mobile+"&chackType=01' target='_blank'>查看</a></td></tr>"
                    html+="<tr><td>无</td> <td>运营商JSON信息</td>";
                    html+="<td><a href='"+val.requesetUrl+"/AuthController/supplementInfor?fileName="+val.fileName+"&mobile="+val.mobile+"&chackType=01' target='_blank'>查看</a></td></tr>"
                }else if ("03" == val.dataType){
                    html2+="<tr><td>"+val.filePath+"</td> <td>"+val.fileName+"</td>";
                    html2+= "<td><a href='"+val.requesetUrl+"/AuthController/viewReportByBasicData?filePath="+val.filePath+"&fileName="+val.fileName+"&mobile="+val.mobile+"&chackType=04' target='_blank'>查看</a></td></tr>"
                    html2+="<tr><td>无</td> <td>支付宝补充信息</td>";
                    html2+= "<td><a href='"+val.requesetUrl+"/AuthController/supplementInfor?fileName="+val.fileName+"&mobile="+val.mobile+"&chackType=04&suppleType=1' target='_blank'>查看</a></td></tr>"
                    html2+="<tr><td>无</td> <td>淘宝补充信息</td>";
                    html2+= "<td><a href='"+val.requesetUrl+"/AuthController/supplementInfor?fileName="+val.fileName+"&mobile="+val.mobile+"&chackType=04&suppleType=2' target='_blank'>查看</a></td></tr>"
                }else if ("05" == val.dataType){
                    html3+="<tr><td>"+val.filePath+"</td> <td>"+val.fileName+"</td>";
                    html3+= "<td><a href='"+val.requesetUrl+"/AuthController/viewReportByBasicData?filePath="+val.filePath+"&fileName="+val.fileName+"&mobile="+val.mobile+"&chackType=05' target='_blank'>查看</a></td></tr>"
                }
            });
            html += "</table>";
            html1 += "</table>";
            html2 += "</table>";
            html3 += "</table>";
            var allTable = html+html1+html2+html3;
            $("#shouji .gg-formGroup").html(allTable);
        }, error:function() {
            parent.layer.msg("异常哦！");
        }
    });
}

function viewReport(){
    setTimeout("loadMobileReport('"+customNo+"')",4000);
};

/*初始化与异步刷新联系人*/
function onloadLXR(){
    var contactsFormDatas = new FormData();
    contactsFormDatas.append("chackNo", $("#chackno").val());
    contactsFormDatas.append("accountNo", $("#accountNo").val())
    $.ajax({
        url:"/ch/ec/getContacts",   //请求参数
        data: contactsFormDatas,
        cache: false,
        type:"post",
        dataType:"json",
        processData: false,
        contentType: false,
        async:false,
        success:function(data) {
            $("#contacts").html("");
            var userLink = data.userLink;
            var html = "";
            /*alert(userLink);
            alert(userLink.length);*/
            for( var i = 0 ; i < userLink.length; i++){
                html = html+"<div id='fromDiv"+i+"'><form id='userLinkFrom"+i+"'  class='form-horizontal m-t' >" +
                    "<input type='hidden'  name='id' value='"+userLink[i].id+"'/>" +
                    "<em>联系人"+(i+1)+"信息</em>" +
                    "<div class='form-group'>" +
                    "<label class='col-sm-3 control-label'>姓名：</label>" +
                    "<div class='col-sm-8'>" +
                    "<input type='text' class='form-control' id='realnames1' name='realname' value='"+userLink[i].realname+"' disabled/>" +
                    "</div> </div>" +

                    "<div class='form-group'>" +
                    "<label class='col-sm-3 control-label'>关系：</label>" +
                    "<div class='col-sm-8'>" +
                    selectHtml(userLink[i].relation,"1","0") +
                    "</div> </div>" +

                    "<div class='form-group'>" +
                    "<label class='col-sm-3 control-label'>手机：</label>" +
                    "<div class='col-sm-8'>" +
                    "<input type='text' class='form-control' id='mobiles1' name='mobile' value='"+userLink[i].mobile+"' disabled/>" +
                    "</div> </div>" +

                    "<div class='form-group'>" +
                    "<label class='col-sm-3 control-label'>核实情况：</label>" +
                    "<div class='col-sm-8'>" +
                    "<textarea id='situation' name='situation'class='form-control' style='height: 100px;' disabled>" + valNull(userLink[i].situation) + "</textarea>" +
                    "</div> </div>" +

                    "<div class='form-group'>" +
                    "<div class='col-sm-8 col-sm-offset-3'>" +
                    "<input class='btn btn-primary contactsUpdate' type='button'  value='修改'>" +
                    " <input class='btn btn-primary saveContacts' type='button'  value='保存' style='display: none'>"+
                    " </div>  </div>"+
                    "</form></div>";
            }
            /* document.getElementById("contacts").innerHTML= html;*/
            $("#contacts").html($("#contacts").html()+html);

            html = "";
            userLinkSize = userLink.length;
            var changdu = 5-userLinkSize;
            if (changdu > 0) {
                html += "<form class='form-horizontal m-t' id = 'saveContactsFrom'>";
                for( var i = 0 ; i < changdu ; i++){
                    html +=  "<em>联系人"+(userLinkSize+i+1)+"信息</em>" +
                        "<div class='form-group'>" +
                        "<label class='col-sm-3 control-label'>姓名：</label>" +
                        "<div class='col-sm-8'>" +
                        "<input type='text' class='form-control' id='realnames1' name='tbUserLinkmanInfos["+i+"].realname' value='' />" +
                        "</div> </div>" +

                        "<div class='form-group'>" +
                        "<label class='col-sm-3 control-label'>关系：</label>" +
                        "<div class=\"col-sm-8\">" +
                        selectHtml("00","2",i) +
                        "</div> </div>" +


                        "<div class='form-group'>" +
                        "<label class='col-sm-3 control-label'>手机：</label>" +
                        "<div class='col-sm-8'>" +
                        "<input type='text' class='form-control' id='mobiles1' name='tbUserLinkmanInfos["+i+"].mobile' value=''  />" +
                        "</div> </div>" ;
                }
                html += "<div class='form-group'>" +
                    "<div class='col-sm-8 col-sm-offset-3'>" +
                    " <input class='btn btn-primary' type='button' value='保存' onclick=\"saveUserAccountInfo()\">"+
                    " </div>  </div></form>";
            }
            $("#contacts").html($("#contacts").html()+html);
        },
        error:function() {
            parent.layer.msg("异常哦！");
        }
    });

    var overview = $("#overview").val();
    if("y" == overview){
        $("input[type='button']").hide();//修改、保存按钮
    }

    $(".contactsUpdate").click(function(){
        $(this).parent().parent().parent().find("#relation").hide();
        $(this).parent().parent().parent().find("#relation").next().show();
        $(this).parent().parent().parent().find("input").attr("disabled",false);
        $(this).parent().parent().parent().find("textarea").attr("disabled",false);
        $(this).next().show();
        $(this).hide();
    });
    $(".saveContacts").click(function(){

        /*$(this).hide();
        $(this).prev().show();*/
        var updateContactsFrom =  $(this).parent().parent().parent()[0];
        var form = new FormData(updateContactsFrom);
        var accountNo = $("#accountNo").val();
        var chackNo = $("#chackno").val();
        form.append("chackNo", chackNo);
        form.append("accountNo", accountNo);
        $.ajax({
            url: "/ch/ec/updateUserAccountInfo",
            type: "post",
            data: form,
            processData: false,
            contentType: false,
            success: function (data) {
                if (data.code=="200") {
                    parent.layer.msg(data.msg);
                    onloadLXR();
                }else{
                    parent.layer.msg(data.msg);
                }
            },
            error: function (e) {
                parent.layer.msg("错误！！"+e.exception);
            }
        });
    });

}
/*保存联系人*/
function saveUserAccountInfo() {
    var form = new FormData(document.getElementById("saveContactsFrom"));
    var accountNo = $("#accountNo").val();
    var chackno = $("#chackno").val();
    form.append("accountNo", accountNo);
    form.append("chackno", chackno);
    $.ajax({
        url: "/ch/ec/saveUserAccountInfo",
        type: "post",
        data: form,
        processData: false,
        contentType: false,
        success: function (data) {
            if (data.code=="200") {
                parent.layer.msg(data.msg);
                onloadLXR();
            }else{
                parent.layer.msg(data.msg);
            }
        },
        error: function (e) {
            parent.layer.msg("错误！！"+e.exception);
        }
    });
}
/*初始化与异步刷新联系地址*/
function loadAddress(){
    var addressForm = new FormData();
    addressForm.append("accountNo", $("#accountNo").val());
    $.ajax({
        url: "/ch/ec/findAddress",
        type: "post",
        data: addressForm,
        cache: false,
        dataType:"json",
        processData: false,
        contentType: false,
        async:false,
        success: function (data) {
            var html = "";
            var live = data.live;
            html +="<form class='form-horizontal m-t' id='liveFrom'>" +
                "<em>地址信息</em>" +
                "<div class='form-group'>" +
                "<label class='col-sm-3 control-label'>所在省份：</label>" +
                "<div class='col-sm-8'>" +
                "<input type='text' value='"+valNull(live.liveProvinceName)+"' class='form-control' disabled>" +
                "</div> </div>" +
                "<div class='form-group'>" +
                "<label class='col-sm-3 control-label'>所在城市：</label>" +
                "<div class='col-sm-8'>" +
                "<input type='text' value='"+valNull(live.liveDistrictName)+"' class='form-control' disabled>" +
                "</div> </div>" +
                "<div class='form-group'>" +
                "<label class='col-sm-3 control-label'>所在区域：</label>" +
                "<div class='col-sm-8'>" +
                "<input type='text' value='"+valNull(live.liveCityName)+"' class='form-control' disabled>" +
                "</div> </div>" +
                "<div class='form-group'>" +
                "<label class='col-sm-3 control-label'>详细地址：</label>" +
                "<div class='col-sm-8'>" +
                "<input type='text' id='liveAddress' name='liveAddress' value='"+valNull(live.liveAddress)+"' class='form-control' disabled>" +
                "</div> </div>" +
                "<div class='form-group'>" +
                "<label class='col-sm-3 control-label'>核实情况：</label>" +
                "<div class='col-sm-8'>" +
                "<textarea id='addressSituation' name='addressSituation' style='height: 100px;' class='form-control'disabled>"+valNull(live.addressSituation)+"</textarea>" +
                "</div> </div>" +
                "<div class='form-group'>" +
                "<div class='col-sm-8 col-sm-offset-3' id='buttonLive'>" +
                "<input class='btn btn-primary liveUpdateButton' type='button' value='修改' />" +
                "<input class='btn btn-primary liveSaveButton' type='button' value='保存' style='display: none' />" +
                "</div> </div> </form>";
            $("#address").html(html);
        }
    });

    $(".liveUpdateButton").click(function(){
        $(this).hide();
        $(this).next().show();
        $(this).parent().parent().parent().find("input").attr("disabled",false);
        $(this).parent().parent().parent().find("textarea").attr("disabled",false);
    });
    $(".liveSaveButton").click(function(){
        var updateLiveFrom =  $(this).parent().parent().parent()[0];
        var form = new FormData(updateLiveFrom);
        form.append("accountNo", $("#accountNo").val());
        $.ajax({
            url: "/ch/ec/updateLice",
            type: "post",
            data: form,
            processData: false,
            contentType: false,
            success: function (data) {
                if (data.code == "200") {
                    parent.layer.msg(data.msg);
                    loadAddress();
                }else{
                    parent.layer.msg(data.msg);
                }
            }
        });

    });

}





function valNull(val){
    if (val == null || val == "") {
        return "";
    }else{
        return val;
    }
}




function returnSelected(def,val){
    if (def == val) {
        return "selected";
    }else{
        return "";
    }
}

function defStr(def){
    if (def == "1") {return "父亲"};
    if (def == "2") {return "母亲"};
    if (def == "3") {return "配偶"};
    if (def == "4") {return "儿子"};
    if (def == "5") {return "女儿"};
    if (def == "6") {return "哥哥"};
    if (def == "7") {return "弟弟"};
    if (def == "8") {return "姐姐"};
    if (def == "9") {return "妹妹"};
    if (def == "10") {return "同事"};
    if (def == "11") {return "朋友"};
    if (def == "12") {return "同学"};
}
function selectHtml(def,type,i) {

    var html = "";
    if (type == "1") {
        html += "<input type='text' id='relation' class='form-control'  value='" + defStr(def) + "' disabled/>";
        html += "<select style='display: none'  name='relation' class='form-control chosen-select' tabindex='2' >";
    } else {
        html += "<select name='tbUserLinkmanInfos[" + i + "].relation' class='form-control chosen-select' tabindex='2' >";
    }

    html +=   "<option value='1'" + returnSelected(def, "1") + ">父亲</option>"
        + "<option value='2'" + returnSelected(def, "2") + ">母亲</option>"
        + "<option value='3'" + returnSelected(def, "3") + ">配偶</option>"
        + "<option value='4'" + returnSelected(def, "4") + ">儿子</option>"
        + "<option value='5'" + returnSelected(def, "5") + ">女儿</option>"
        + "<option value='6'" + returnSelected(def, "6") + ">哥哥</option>"
        + "<option value='7'" + returnSelected(def, "7") + ">弟弟</option>"
        + "<option value='8'" + returnSelected(def, "8") + ">姐姐</option>"
        + "<option value='9'" + returnSelected(def, "9")+ ">妹妹</option>"
        + "<option value='10'"+ returnSelected(def, "10")+ ">同事</option>"
        + "<option value='11'"+ returnSelected(def, "11")+ ">朋友</option>"
        + "<option value='11'"+ returnSelected(def, "12")+ ">同学</option>"
        + "</select>";
    return html;
}
/*function load() {
    var chackno = $("#modifyPwd input[name='chackno']").val();
    var formData1 = new FormData();
    formData1.append("chackno", chackno);
    $.ajax({
        url:"/ch/ec/allPicture",
        data: formData1,
        cache: false,
        type:"post",
        dataType:"json",
        processData: false,
        contentType: false,
        async:false,
        success: function (result) {
            var choose = result.length;
            if (choose != 0) {
            $.each(result,function(){
                    var a = "<div class=\"gg-formGroup\">" +
                        "<div class=\"gg-formTitle\">" +
                        "<em class=\"gg-star\">*</em>" +
                        "<span>" + this.type + ":</span>" +
                        "</div>" +
                        "<div class=\"gg-formDetail\">";
                        if(this.addr != null && this.addr != "") {
                            a += "<a href=\"" + this.addr + "\" id=\"picShow\" target=\"view_window\" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                            "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;查看</a>";
                        }else {
                            a += "<font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;无</font>";
                        }
                        a += "</div>" +
                                "</div>";
                    $("#exampleTable").append(a);
                });
                }else{
                    var b = "<div class=\"gg-formGroup\">" +
                        "<div class=\"gg-formTitle\">" +
                        "<em class=\"gg-star\">*</em>" +
                        "<span>暂无数据</span>" +
                        "</div>" +
                        "</div>" +
                        "</div>";
                    $("#exampleTable").append(b);
                }
        },
    });

}*/



// $("#picShow").click(function () {
//     window.open();
//     $(this).val()
// })