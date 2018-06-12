/**
 * Created by 马东 on 2018/3/31.
 */
layui.use(['layer','form', 'myinitsize'], function(){
    var layer = layui.layer
    var initSize = layui.myinitsize;
    initSize.init_size($("#login_container"));

    var form = layui.form //获取form模块

    //监听提交按钮
    form.on('submit(formLogin)', function(data){
        alert("7878878");
        console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
        console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
        console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
        $.ajax({
            url: "/login",
            data: data.field,
            type: "post",
            dataType: "json",
            success: function (data) {

                console.log(data.code)
                var code = data.code;
                if(code != 200){
                    $("#hint").text(data.message);
                }else {

                    $("#hint").text("");
                    $("#hint").text("登陆成功，正在跳转...");
                    token = data.data.xAuthToken;
                    //
                    //xhr.setRequestHeader("xAuthToken",token);
                    window.location.href = "/admin/index";
                //
                }
                },
            error: function (XMLHttpRequest, textStatus, errorThrown) {

               },
            beforeSend: function () {

               },
            complete: function () {

               }
        });
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

    //实例化一个上传控件
    /*upload({
        url: '上传接口url'
        ,success: function(data){
            console.log(data);
        }
    })*/

});