var prefix = "/ch/ec";
var submited = false;
var customNo = "";
$(function () {
        $("#submit").click(function () {
        var message = $("input[name='checkOne']:checked").parent().next().next().val();
        var mobile = $("#mobile").val();
        var mobile1 = $("#mobile1").text();
        var loanName = $("#loanName").text();
        var userChannelId = $("#userChannelId").text();
        if(message == "" || message == null){
            alert("短信内容为空！请填写或者选择短信内容~");
            return;
        }
        if(mobile == "" || mobile == null){
            alert("手机号不能为空！");
            return;
        }
        $.get("/sendMessageController/sendMessage",
            {"message":message,"mobile":mobile,"mobile1":mobile1,"loanName":loanName,"userChannelId":userChannelId},
            function(data,status){
                console.log(data);
                if(data=='0000'){
                    layer.msg("发送成功");
                    setTimeout(function (){
                        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                        parent.layer.close(index);
                        window.parent.location.reload();
                    },1000);
                }else {
                    layer.msg("发送失败");
                }
            }
        );
    });
    function close(){
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.close(index);
        window.parent.location.reload();
    }
});

