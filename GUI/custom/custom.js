var cart;
function deviceReady() {
    $("#click2").on("click", function (e) {
        $.ajax({
            url: 'http://localhost:8080/WebServices/RestaurantServiceServlet',
            crossDomain: true,
            data: {
                ws: 1
            }
        })
            .done(function (data) {
                var obj = JSON.parse(data);
                var ret = "";
                if(obj.length > 0){
                	for (var i = 0; i < obj.length; i++) {
                        ret = ret + "<li class='restaurant-selector'><button class='mui-clickable default-button' onclick=restaurantSelection(" + obj[i].id +")><img src="
                            + obj[i].logo + " width='50' height='50'><div class='restaurant-title'> " + obj[i].name + "<br> </div>" +
                            "<div class='restaurant-info'>" + obj[i].location + "<br>" + obj[i].horario + "</div>" +
                            "</button></li>";
                    }
                }else{
                	ret = "No hay restaurantes disponibles, revise su coneccion a internet. Si el problema persiste, puede deberse a un error en la aplicacion. Saludos de NoLines team.";
                }
                
                $("#restaurant-list").html(ret);
                mui.viewPort.iScrollRefresh();
                mui.viewPort.showPage("mui-viewport-page2", "SLIDE_LEFT");

            })
            .fail(function (jqXHR, textStatus, errorThrown) {

            })
    });
}
function restaurantSelection(param) {
        $.ajax({
            url:'http://localhost:8080/WebServices/RestaurantServiceServlet',
            crossDomain: true,
            data:{
                ws: 2,
                idRestaurant: param
            }
        })
            .done(function(data){
            	cart=" ";
            	$("#cart-display").html("");
                var obj=JSON.parse(data);
                var ret = "";
                if(obj.length > 0){
                	for(var i = 0 ; i < obj.length ; i++){
                        ret = ret + "<li class='menu-selector'><div>"+obj[i].name+"<br><br>"+obj[i].description+"</div><div class='menu-selector-div'><button class='menu-selector-btn' type='button' onclick='addPlate("+obj[i].id+","+param+",&quot;"+obj[i].name+"&quot;)'>+</button></div></li>";
                    }
                }else{
                	ret="No hay un menu disponible, revise su coneccion a internet. Si el problema persiste, puede deberse a un error en la aplicacion. Saludos de NoLines team.";
                }
                $("#menu-list").html(ret);
                mui.viewPort.iScrollRefresh();
                mui.viewPort.showPage("mui-viewport-page3", "SLIDE_LEFT");

            })
            .fail(function(jqXHR, textStatus, errorThrown){

            })
}

function addPlate(param,id,name){
	ret='<form action="http://localhost:8080/WebServices/UserServiceServlet" method="post"><input name="idRest" value="'+id+'" type="hidden"/>';
	cart=cart+name+'<input name="plato" value="'+param+'" type="hidden"/><input name="cantidad" value="1" type="hidden"/><br>';
	ret=ret+cart+'<input type="submit" />';
	$("#cart-display").html(ret);
	
}


