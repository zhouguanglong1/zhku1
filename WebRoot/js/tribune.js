/**
 * Created by Administrator on 2016/12/22.
 */


$(function(){
    tribune();
})

function tribune(){
    var getData=function(callback){
        $.ajax({
            type:"get",
            url:"../json/json2.json",
            data:{},
            success:function(result){
                callback(result);
            }
        });
    }
        var rander=function(){
            getData(function(result){
                var html=template("discuss",{"items":result});
                $(".tribune-textl").html(html);
            })
        }
    rander();
}






















