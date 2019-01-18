var form, $,areaData;
layui.config({
    base : "../../js/"
}).extend({
    "address" : "address"
})
layui.use(['form','layer','upload','laydate',"address"],function(){
    form = layui.form;
    $ = layui.jquery;
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        upload = layui.upload,
        laydate = layui.laydate,
        address = layui.address;


    $.ajax({
        url: "/user/userInfo" ,    //后台方法名称
        type: "get",
        dataType: "json",
        traditional: true,
        success: function (data) {
            if (data.code == 1){
                //layer.msg(data.msg);
                $(".account").val(data.data.username);
                $(".realName").val(data.data.realName);
                $(".sex").val(data.data.sex);
                if (data.data.sex == 1) {
                    $(".sex").val("男");
                } else {
                    $(".sex").val("女");
                }

                $(".phone").val(data.data.phone);
                $(".email").val(data.data.email);
                $(".address").val(data.data.address);
                $(".token").val(data.data.balance);

            }
            form.render();

        },
        error: function (msg) {
        }
    });


    //添加验证规则
    form.verify({
        userBirthday : function(value){
            if(!/^(\d{4})[\u4e00-\u9fa5]|[-\/](\d{1}|0\d{1}|1[0-2])([\u4e00-\u9fa5]|[-\/](\d{1}|0\d{1}|[1-2][0-9]|3[0-1]))*$/.test(value)){
                return "出生日期格式不正确！";
            }
        }
    })
    //选择出生日期
    laydate.render({
        elem: '.userBirthday',
        format: 'yyyy年MM月dd日',
        trigger: 'click',
        max : 0,
        mark : {"0-12-15":"生日"},
        done: function(value, date){
            if(date.month === 12 && date.date === 15){ //点击每年12月15日，弹出提示语
                layer.msg('今天是马哥的生日，也是layuicms2.0的发布日，快来送上祝福吧！');
            }
        }
    });

    //获取省信息
    address.provinces();

    //提交个人资料
    form.on("submit(changeUser)",function(data){
        var index = layer.open({
            title : "提币操作",
            type : 2,
            area : ["400px","400px"],
            content : "page/user/serverAdd.html",
            success : function(layero, index){
                //var body = $($(".layui-layer-iframe",parent.document).find("iframe")[0].contentWindow.document.body);
                setTimeout(function(){
                    layui.layer.tips('点击此处返回友链列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })
    })


    form.on("submit(transferAccounts)",function(data){
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        $.post("/transfer/transferAccounts",{
            advAddress : $(".advAddress").val(),  //logo
            balance : $(".balance").val(),  //网站名称
            remark : $(".remark").val(),    //网址
        },function(res){
            if (res.code == 0){
                top.layer.msg(res.message);
            } else if (res.code == 1){
                setTimeout(function(){
                    top.layer.close(index);
                    top.layer.msg("转账成功！");
                    layer.closeAll("iframe");
                    //刷新父页面
                    $(".layui-tab-item.layui-show",parent.document).find("iframe")[0].contentWindow.location.reload();
                },500);
            }


        });

        return false;
    })



    //修改密码
    form.on("submit(changePwd)",function(data){
        var index = layer.msg('提交中，请稍候',{icon: 16,time:false,shade:0.8});
        setTimeout(function(){
            layer.close(index);
            layer.msg("密码修改成功！");
            $(".pwd").val('');
        },2000);
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    })
})