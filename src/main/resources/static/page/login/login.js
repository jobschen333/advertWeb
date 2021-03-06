layui.use(['form','layer','jquery'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer
        $ = layui.jquery;

    //注册按钮
    form.on("submit(register)",function(data){

        window.location = "register.html";
        setTimeout(function(){
        },1000);
        return false;
    });

    //登录按钮
    form.on("submit(login)",function(data){
        var userAccount = $('#userName').val();
        var userPassword = $('#password').val();
        var validateCode = $("#code").val();
        if (userAccount =="" || userPassword ==""){
            layer.msg("账号或者密码不能为空!");
            return false;
        }

        if (validateCode == "") {
            lay.msg("请输入验证码!");
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
                debugger;
                if (data.code == 1){
                    window.location.href = "../../index.html";
                } else if(data.code == 2){
                    layer.msg("账户或者密码不正确!");
                } else {
                    layer.msg(data.message);
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
