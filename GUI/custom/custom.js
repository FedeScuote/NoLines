/**
 * Created by Fede on 19/05/2014.
 */
function deviceReady() {
    $("#click2").on("click", function(e) {
        $.ajax({
            /*url:'http://www.portones.com.uy/shopsite/AppService',
            crossDomain: true,
            data:{
                cmdaction:'locales',
                shopping:'ms',
                web:2
            }*/
            url:'http://localhost:8080/PruebaTomEE/HelloServlet',
            crossDomain: true,
            //dataType:'json',
            data:{
            }
        })
            .done(function(data){
                var obj=JSON.parse(data);
                var ret = "";
                for(var i = 0 ; i < obj.restaurant.length ; i++){
                	ret = ret + "<li><img src="+obj.restaurant[i].image+" width='50' height='50'><div> "+obj.restaurant[i].name+"<br> </div></li>";
                }
               // alert(ret);
                $("#restaurant-list").html(ret);
                mui.viewPort.iScrollRefresh();
                mui.viewPort.showPage("mui-viewport-page2", "SLIDE_LEFT");

            })
            .fail(function(jqXHR, textStatus, errorThrown){

            })
    });


}