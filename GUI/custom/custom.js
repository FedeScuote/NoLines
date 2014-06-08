var cart;
var cantidades;
var platos;
var idRest;
var yaAgregados;
var ret2;
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

    //muestra el panel con info del restaurant
    $("#info").on("click", function(e) {
        if (mui.viewPort.panelIsOpen())
            mui.viewPort.closePanel();
        else
            mui.viewPort.showPanel('panel1', 'SLIDE_RIGHT');
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
            	//reinicio variables de carrito.
            	cart=" ";
            	ret2=" ";
            	idRest="";
            	cantidades=" ";
            	platos=" ";
            	yaAgregados=0;
            	//termino de reiniciar variables de carrito
         
            	$("#my-cart-list").html(""); //se limpia la lista del carrito.
                var obj=JSON.parse(data);
                var ret = "";
                if(obj.length > 0){
                	for(var i = 0 ; i < obj.length ; i++){
                        ret = ret + "<li class='menu-selector'><div>"+obj[i].name+"<br>$"+obj[i].price+"</div><div class='menu-selector-div'><button class='menu-selector-btn' type='button' onclick='addPlate("+obj[i].id+","+param+",&quot;"+obj[i].name+"&quot;)'>+</button></div></li>";
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
	idRest=id;
	ret2=ret2+"<li class='cart-item'><div class='cart-item-div'>"+name+"</div></li>";
	if(yaAgregados>0){
		platos=platos+","+param;
		cantidades=cantidades+",1";
	}else{
		cantidades="1";
		platos=param;
	}
	yaAgregados=yaAgregados+1;
	
	/*var encontre=0;
	for(var i=0 ; i<yaAgregados ; i++){
		if(platos[i]==param){
			cantidades[i]=cantidades[i]+1;
			encontre=1;
		}
	}
	if(encontre == 0){
		platos[yaAgregados]=param;
		cantidades[yaAgregados]=1;
		yaAgregados=yaAgregados+1;
	}*/
	
	$("#my-cart-list").html(ret2);

	//ret='<form method="post"><input name="idRest" value="'+id+'" type="hidden"/>';
	//cart=cart+name+'<input name="plato" value="'+param+'" type="hidden"/><input name="cantidad" value="1" type="hidden"/><br>';
	//ret=ret+cart+'<div id="confirmar"><input type="buttom" onclick=verifyOrder() value="Confirmar" /></div>';
	//$("#cart-display").html(ret);
	
}

function verifyOrder(){
	 $.ajax({
         url:"http://localhost:8080/WebServices/UserServiceServlet",
         type: "POST",
         crossDomain: true,
         data:{
        	 ws : 3,
             plato: platos,
             cantidad: cantidades,
             idRest: idRest
         }
     })
     	.done(function(data){
		 
	 })
	 .fail(function(jqXHR, textStatus, errorThrown){

            })
	 
}


