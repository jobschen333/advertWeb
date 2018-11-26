layui.use(['form','layer','jquery'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer
        $ = layui.jquery;

    $(".loginBody .seraph").click(function(){
        layer.msg("这只是做个样式，至于功能，你见过哪个后台能这样登录的？还是老老实实的找管理员去注册吧",{
            time:5000
        });
    })

    //登录按钮
    form.on("submit(login)",function(data){
        var userAccount = $('#userName').val();
        var userPassword = $('#password').val();
        var validateCode = $("#code").val();
        if (userAccount =="" || userPassword ==""){
            layer.msg("账号或者密码不能为空");
            return false;
        }
        $.ajax({
            url: "/index/login",
            type: "POST",
            dataType: "json",
            data: {
                userAccount : userAccount,
                password : userPassword,
                validateCode : validateCode
                }
            ,
            success: function (data) {
                if (data.code == 1){
                    window.location.href = "../../index.html";
                } else if(data.code == 2){
                    layer.msg("账户或者密码不正确!");
                } else if (data.code == 3){
                    layer.msg(data.msg);
                }

            },
            error: function (msg) {
            }
        });

        setTimeout(function(){
        },1000);
        return false;
    });

    //表单输入效果
    $(".loginBody .input-item").click(function(e){
        e.stopPropagation();
        $(this).addClass("layui-input-focus").find(".layui-input").focus();
    })
    $(".loginBody .layui-form-item .layui-input").focus(function(){
        $(this).parent().addClass("layui-input-focus");
    })
    $(".loginBody .layui-form-item .layui-input").blur(function(){
        $(this).parent().removeClass("layui-input-focus");
        if($(this).val() != ''){
            $(this).parent().addClass("layui-input-active");
        }else{
            $(this).parent().removeClass("layui-input-active");
        }
    })
})
