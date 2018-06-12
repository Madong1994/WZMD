/**
 * Created by 马东 on 2018/3/31.
 */
layui.define('layer',function(exports){
    var myinitsize = {
        init_size:function ($obj, isIndex)
        {

            var width = document.documentElement.clientWidth;
            var height = $(window).height();
            if(isIndex)
            {
                $obj.css({width: width, height: height});//112是控制栏
            }
            else
            {
                $obj.css({width:width,height:height});
            }
             //onWindowResize();
        }
    }
    exports('myinitsize',myinitsize);
})
//config的设置是全局的
layui.config({
    base: '/layui/mmod/' //假设这是你存放拓展模块的根目录
}).extend({ //设定模块别名
    //如果 mymod.js 是在根目录，也可以不用设定别名
    //相对于上述 base 目录的子目录
});
