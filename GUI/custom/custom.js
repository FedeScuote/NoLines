/**
 * Created by Fede on 19/05/2014.
 */
function deviceReady() {
    $("#click2").on("click", function(e) {
        $.ajax({
            url:'http://www.portones.com.uy/shopsite/AppService',
            crossDomain: true,
            data:{
                cmdaction:'locales',
                shopping:'ms',
                web:2
            }
            /*url:'http://localhost:8080/WebServices/RestaurantServiceImpl',
            crossDomain: true,
            data:{
            }*/
        })
            .done(function(data){
                var restaurantes=JSON.parse(data);
                var ret = '';
                for(var i = 0 ; i < restaurantes.length ; i++){
                    ret.concat('<li><img src=restaurantes[i].image><div> restaurantes[i].name<br> </div></li>')
                }
                $("#restaurant-list").html(ret);
                mui.viewPort.iScrollRefresh();
                mui.viewPort.showPage("mui-viewport-page2", "SLIDE_LEFT");

            })
            .fail(function(jqXHR, textStatus, errorThrown){

            })
    });


}