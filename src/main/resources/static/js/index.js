/**
 * Created by 马东 on 2018/3/31.
 */
layui.use(['layer','form', 'myinitsize','element'], function(){
    var element = layui.element;
    var initSize = layui.myinitsize;
    initSize.init_size($("#index_container"));
    //一些事件监听
    element.on('tab(index)', function(data){
        console.log(data);
    });
});