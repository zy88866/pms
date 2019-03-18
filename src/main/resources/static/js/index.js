var $,tab;
layui.config({
	base : "js/"
}).use(['bodyTab','layer','jquery'],function(){
	var layer = layui.layer,
		$ = layui.jquery;
		tab = layui.bodyTab({
			openTabNum : "50",  //最大可打开窗口数量
			url : "json/navs.json" //获取菜单json地址
		});
	//渲染左侧菜单
	tab.render();

    // 添加新窗口
    $("body").on("click",".layui-nav .layui-nav-item a",function(){
        //如果不存在子级
        if($(this).siblings().length == 0){
            addTab($(this));
        }
        $(this).parent("li").siblings().removeClass("layui-nav-itemed");
    })

});

//打开新窗口
function addTab(_this){
	tab.tabAdd(_this);
}


