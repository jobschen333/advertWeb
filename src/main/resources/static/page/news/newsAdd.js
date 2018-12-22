layui.use(['form','layer','layedit','laydate','upload'],function(){
    var form = layui.form
        layer = parent.layer === undefined ? layui.layer : top.layer,
        laypage = layui.laypage,
        upload = layui.upload,
        layedit = layui.layedit,
        laydate = layui.laydate,
        $ = layui.jquery;

    //用于同步编辑器内容到textarea
    layedit.sync(editIndex);

    //上传缩略图
    upload.render({
        elem: '.thumbBox',
        url: '/advert/upload',
        method : "post",
        done: function(res, index, upload){
            if (res.code == 1){
                $('.thumbImg').attr('src',res.path);
                $('.thumbBox').css("background","#fff");
                $("#imageUrl").val(res.path)
            }

        }
    });

    //格式化时间
    function filterTime(val){
        if(val < 10){
            return "0" + val;
        }else{
            return val;
        }
    }
    //定时发布
    var time = new Date();
    var submitTime = time.getFullYear()+'-'+filterTime(time.getMonth()+1)+'-'+filterTime(time.getDate())+' '+filterTime(time.getHours())+':'+filterTime(time.getMinutes())+':'+filterTime(time.getSeconds());
    laydate.render({
        elem: '#release',
        type: 'datetime',
        trigger : "click",
        done : function(value, date, endDate){
            submitTime = value;
        }
    });
    form.on("radio(release)",function(data){
        if(data.elem.title == "定时发布"){
            $(".releaseDate").removeClass("layui-hide");
            $(".releaseDate #release").attr("lay-verify","required");
        }else{
            $(".releaseDate").addClass("layui-hide");
            $(".releaseDate #release").removeAttr("lay-verify");
            submitTime = time.getFullYear()+'-'+(time.getMonth()+1)+'-'+time.getDate()+' '+time.getHours()+':'+time.getMinutes()+':'+time.getSeconds();
        }
    });

    form.verify({
        newsName : function(val){
            if(val == ''){
                return "文章标题不能为空";
            }
        },
        content : function(val){
            if(val == ''){
                return "文章内容不能为空";
            }
        }
    })
    form.on("submit(addNews)",function(data){
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        if ($(".thumbImg").attr("src")== "" || typeof($(".thumbImg").attr("src")) == "undefined") {
            top.layer.msg("请上传广告图片!");
            return false;
        }
         $.post("/advert/addAdvert",{
             title : $(".title").val(),  //文章标题
             wasteToken : $(".wasteToken").val(),  //文章摘要
             pic : $(".thumbImg").attr("src"),  //缩略图
             content: $(".content").val(),
             mustClick : $(".mustClick").val(),
             url : $(".url").val(),
             clickToken : $(".clickToken").val(),
         },function(res){

         })
        setTimeout(function(){
            top.layer.close(index);
            top.layer.msg("广告添加成功！");
            layer.closeAll("iframe");
            //刷新父页面
            parent.location.reload();
        },500);
        return false;
    })

    //预览
    form.on("submit(look)",function(){
        layer.alert("此功能需要前台展示，实际开发中传入对应的必要参数进行文章内容页面访问");
        return false;
    })

    //创建一个编辑器
    var editIndex = layedit.build('news_content',{
        height : 535,
        uploadImage : {
            url : "../../json/newsImg.json"
        }
    });
    


})

//计算之和
function  calculatePay() {
    var mustClick = $(".mustClick").val();
    var clickToken = $(".clickToken").val();
    if (mustClick != "" && clickToken != "") {
        $(".wasteToken").val(numMulti(mustClick,clickToken));
        $(".wasteToken").html(numMulti(mustClick,clickToken));
    }

   // alert("11");
}


function numMulti(num1, num2) {
    var baseNum = 0;
    try {
        baseNum += num1.toString().split(".")[1].length;
    } catch (e) {
    }
    try {
        baseNum += num2.toString().split(".")[1].length;
    } catch (e) {
    }
    return Number(num1.toString().replace(".", "")) * Number(num2.toString().replace(".", "")) / Math.pow(10, baseNum);
};