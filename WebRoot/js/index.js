/**
 * Created by Administrator on 2016/12/20.
 */
//�ֲ�ͼ
$(function(){
    banner();
    //table();
})
function banner(){
    //��������

    var getData=function(callback){
        $.ajax({
            type:"get",
            url:"../json/json1.json",
            data: {},
            success: function (result) {
                //�ص�callback����
                callback(result);
            }
        });
    }
//���ñ��

    var render = function () {

        //����ģ������
        getData(function (result) {
            var html = template("imgTemp", {"items": result});
            console.log(result);
            $(".carousel-inner").html(html);
            /*ʹ��underscore��ʵ��ģ��������滻*/
            /*1.ģ���ַ���(�Լ��ȶ�ȡ��) */
            //var indHTML = document.getElementById("indicatorTemp").innerHTML;
            ///*����ģ�����*/
            //console.log(result);
            //var indicator = _.template(indHTML);
            ///*�������ݣ����ɿ���Ⱦ��html����*/
            //console.log(indicator)
            //var indicatorHTML = indicator({"items": result});
            ///*��Ⱦ*/
            //console.log(indicatorHTML)
            //$(".carousel-indicators").html(indicatorHTML);
        })
    }
    render();
}
/*����¼����������̴���һ��*/
/*$(window).on("resize",function(){
 render();
 }).trigger("resize");*/
/*����Ļ��ȷ����任��ʱ����Ҫ������Ⱦ*/
//--------------------------------------------
//  �����¼�
//��ʼλ��
var startX=0;
//�ƶ�λ��
var moveX=0;
//λ��
var distanceX=0;
/*�������touch�¼�*/
var carouselinner=document.querySelector(".carousel-inner");
//eֻ��һ����ͨ���β�
carouselinner.addEventListener("touchstart",function(e){
    startX= e.touches[0].clientX;
});
carouselinner.addEventListener("touchmove",function(e){
    moveX= e.touches[0].clientX;
    distanceX=moveX-startX;
});
/*��������*/
carouselinner.addEventListener("touchend",function(e){
    /*�жϻ����ľ���*/
    if(Math.abs(distanceX)>100){
        if(distanceX>0){
            //prev��ʲô������һ�ŵ���˼������jQ���涨��õģ�
            $(".carousel").carousel('prev');
        }
        else if(distanceX<0){
            $(".carousel").carousel('next');
        }
    }
});