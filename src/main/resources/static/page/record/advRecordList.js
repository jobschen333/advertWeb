layui.use(['form','layer','laydate','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;

    $.ajax({
        url: "/index/checkAgent",
        type: "get",
        dataType: "json",
        traditional: true,
        success: function (data) {
            if (data.code == 1) {
                var tableIns = table.render({
                    elem: '#advRecordList',
                    url : '/record/list',
                    cellMinWidth : 95,
                    page : true,
                    height : "full-125",
                    limit : 20,
                    limits : [10,15,20,25],
                    id : "newsListTable",
                    cols : [[
                        {title: '序号', width:"10%", align:"center", type:"numbers"},
                        {field: 'advertId', title: '广告id', width:"20%"},
                        {field: 'title', title: '广告名称', align:'center', width:"30%"},
                        {field: 'money', title: '点击代币', align:'center', width:"20%"},
                        {field: 'addTime',width:"25%", title: '添加时间', align:'center',templet: '<div>{{ layui.laytpl.toDateString(d.add_time) }}</div>'}

                    ]],
                    done: function(res, curr, count) {

                    }
                });
            }else if (data.code == 2) {
                var tableIns = table.render({
                    elem: '#advRecordList',
                    url : '/record/list',
                    cellMinWidth : 95,
                    page : true,
                    height : "full-125",
                    limit : 20,
                    limits : [10,15,20,25],
                    id : "newsListTable",
                    cols : [[
                        {title: '序号', width:"10%", align:"center", type:"numbers"},
                        {field: 'advertId', title: '广告id', width:"20%"},
                        {field: 'title', title: '广告名称', align:'center', width:"30%"},
                        {field: 'money', title: '点击代币', align:'center', width:"20%"},
                        {field: 'addTime',width:"25%", title: '添加时间', align:'center',templet: '<div>{{ layui.laytpl.toDateString(d.add_time) }}</div>'}

                    ]],
                    done: function(res, curr, count) {

                    }
                });

            }

        }
    });




    //是否置顶
    form.on('switch(newsTop)', function(data){
        var index = layer.msg('修改中，请稍候',{icon: 16,time:false,shade:0.8});
        setTimeout(function(){
            layer.close(index);
            if(data.elem.checked){
                layer.msg("置顶成功！");
            }else{
                layer.msg("取消置顶成功！");
            }
        },500);
    })

    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click",function(){
        if($(".searchVal").val() != ''){
            table.reload("newsListTable",{
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    key: $(".searchVal").val()  //搜索的关键字
                }
            })
        }else{
            layer.msg("请输入搜索的内容");
        }
    });

    //发布广告
    function addNews(edit){
        $.ajax({
            url: "/business/checkUpBusiness" ,    //后台方法名称
            type: "get",
            dataType: "json",
            traditional: true,
            success: function (data) {
                if (data.code == 1) {

                } else {
                    layer.msg("您不是商家账号,请申请上商家账号!");
                }

            },
            error: function (msg) {
            }
        });

        var index = layui.layer.open({
            title : "新增广告",
            type : 2,
            content : "newsAdd.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    body.find("#id").val(edit.id);
                    body.find(".title").val(edit.title);
                    body.find(".content").val(edit.content);
                    body.find(".thumbImg").attr("src",edit.pic);
                    body.find(".url").val(edit.url);
                    body.find(".mustClick").val(edit.must_click);
                    body.find(".wasteToken").val(edit.waste_token);
                    form.render();
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回文章列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })
        layui.layer.full(index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(index);
        })


    }
    $(".addNews_btn").click(function(){
        addNews();
    })

    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('newsListTable'),
            data = checkStatus.data,
            newsId = [];
        if(data.length > 0) {
            for (var i in data) {
                newsId.push(data[i].newsId);
            }
            layer.confirm('确定删除选中的文章？', {icon: 3, title: '提示信息'}, function (index) {
                // $.get("删除文章接口",{
                //     newsId : newsId  //将需要删除的newsId作为参数传入
                // },function(data){
                tableIns.reload();
                layer.close(index);
                // })
            })
        }else{
            layer.msg("请选择需要删除的文章");
        }
    })

    //列表操作
    table.on('tool(newsList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
            addNews(data);
        } else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此？',{icon:3, title:'提示信息'},function(index){
                 $.get("删除文章接口",{
                     newsId : data.newsId  //将需要删除的newsId作为参数传入
                 },function(data){
                    tableIns.reload();
                    layer.close(index);
                 })
            });
        } else if(layEvent === 'look'){ //预览
            layer.alert("此功能需要前台展示，实际开发中传入对应的必要参数进行文章内容页面访问")
        }
    });

    //时间戳的处理
    layui.laytpl.toDateString = function(d, format){
        var date = new Date(d || new Date())
            ,ymd = [
            this.digit(date.getFullYear(), 4)
            ,this.digit(date.getMonth() + 1)
            ,this.digit(date.getDate())
        ]
            ,hms = [
            this.digit(date.getHours())
            ,this.digit(date.getMinutes())
            ,this.digit(date.getSeconds())
        ];

        format = format || 'yyyy-MM-dd HH:mm:ss';

        return format.replace(/yyyy/g, ymd[0])
            .replace(/MM/g, ymd[1])
            .replace(/dd/g, ymd[2])
            .replace(/HH/g, hms[0])
            .replace(/mm/g, hms[1])
            .replace(/ss/g, hms[2]);
    };

    //数字前置补零
    layui.laytpl.digit = function(num, length, end){
        var str = '';
        num = String(num);
        length = length || 2;
        for(var i = num.length; i < length; i++){
            str += '0';
        }
        return num < Math.pow(10, length) ? str + (num|0) : num;
    };

})