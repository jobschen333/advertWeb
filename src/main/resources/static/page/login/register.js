layui.use(['form','layer','jquery'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer
        $ = layui.jquery;


    //登录按钮
    form.on("submit(register)",function(data){
        var username = $('#userName').val();
        var password = $('#password').val();
        var password2 = $('#password2').val();
        var phone = $("#phone").val();
        var email = $("#email").val();

        if (password != password2) {
            layer.msg("两次密码输入不一致")
        }

        $.ajax({
            url: "/index/register",
            type: "POST",
            dataType: "json",
            data: {
                username : username,
                password : password,
                phone : phone,
                email : email
                }
            ,
            success: function (data) {
                if (data.code == 1) {
                    window.location = "login.html";
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
