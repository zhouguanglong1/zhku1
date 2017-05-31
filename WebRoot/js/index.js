/**
 * Created by Administrator on 2016/12/20.
 */
//轮播图
$(function(){
    banner();
    //table();
})
function banner(){
    //请求数据

    var getData=function(callback){
        $.ajax({
            type:"get",
            url:"../json/json1.json",
            data: {},
            success: function (result) {
                //回调callback函数
                callback(result);
            }
        });
    }
//设置标记

    var render = function () {

        //调用模板引擎
        getData(function (result) {
            var html = template("imgTemp", {"items": result});
            console.log(result);
            $(".carousel-inner").html(html);
            /*使用underscore来实现模板引擎的替换*/
            /*1.模板字符串(自己先读取好) */
            //var indHTML = document.getElementById("indicatorTemp").innerHTML;
            ///*生成模板对象*/
            //console.log(result);
            //var indicator = _.template(indHTML);
            ///*传入数据，生成可渲染的html代码*/
            //console.log(indicator)
            //var indicatorHTML = indicator({"items": result});
            ///*渲染*/
            //console.log(indicatorHTML)
            //$(".carousel-indicators").html(indicatorHTML);
        })
    }
    render();
}
/*添加事件，并且立刻触发一次*/
/*$(window).on("resize",function(){
 render();
 }).trigger("resize");*/
/*当屏幕宽度发生变换的时候，需要重新渲染*/
//--------------------------------------------
//  滑动事件
//开始位置
var startX=0;
//移动位置
var moveX=0;
//位移
var distanceX=0;
/*添加三个touch事件*/
var carouselinner=document.querySelector(".carousel-inner");
//e只是一个普通的形参
carouselinner.addEventListener("touchstart",function(e){
    startX= e.touches[0].clientX;
});
carouselinner.addEventListener("touchmove",function(e){
    moveX= e.touches[0].clientX;
    distanceX=moveX-startX;
});
/*触摸结束*/
carouselinner.addEventListener("touchend",function(e){
    /*判断滑动的距离*/
    if(Math.abs(distanceX)>100){
        if(distanceX>0){
            //prev是什么，是上一张的意思，那是jQ里面定义好的？
            $(".carousel").carousel('prev');
        }
        else if(distanceX<0){
            $(".carousel").carousel('next');
        }
    }
});