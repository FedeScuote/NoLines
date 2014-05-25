/**
 * Created by Fede on 19/05/2014.
 */
function deviceReady() {
    $("#click2").on("click", function(e) {
        $.ajax({    
            url:'http://localhost:8080/WebServices/RestaurantServiceServlet',
            crossDomain: true,
            data:{
            }
        })
            .done(function(data){
                var obj=JSON.parse(data);
                var ret = "";
                for(var i = 0 ; i < obj.length ; i++){
                	ret = ret + "<li class='restaurant-selector'><button class='mui-clickable default-button'><img src="
                	+obj[i].logo+" width='50' height='50'><div class='restaurant-title'> "+obj[i].name+"<br> </div>" +
                        "<div class='restaurant-info'>"+obj[i].location+"<br>"+obj[i].horario+"</div>" +
                        "</button></li>";
                }
                $("#restaurant-list").html(ret);
                mui.viewPort.iScrollRefresh();
                mui.viewPort.showPage("mui-viewport-page2", "SLIDE_LEFT");

            })
            .fail(function(jqXHR, textStatus, errorThrown){

            })
    });


}