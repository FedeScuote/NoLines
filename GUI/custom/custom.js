var cart;
var infoRest;
var cantidades;
var platos;
var idRest;
var yaAgregados;
var ret2;
var timeTotal;
var priceTotal;
var userLogged;
var servidor='http://localhost:8080/'
function deviceReady() {
}
function showRestaurantInfo(){
    	if (mui.viewPort.panelIsOpen())
    		mui.viewPort.closePanel();
    	else
    		mui.viewPort.showPanel('panel1', 'SLIDE_RIGHT');
}
function buscarRestaurantes(categoria){
    $.ajax({
        url: servidor + '/WebServices/RestaurantServiceServlet',
        crossDomain: true,
        data: {
            ws: 1,
            category: categoria,
            user: userLogged
        }
    })
        .done(function (data) {
            var obj = JSON.parse(data);
            var ret = "";
            if(obj.length > 0){
            	for (var i = 0; i < obj.length; i++) {
                    ret = ret + "<li class='restaurant-selector'><button class='mui-clickable default-button' onclick='restaurantSelection(" + obj[i].id +",&quot;"+obj[i].name+"&quot;,&quot;"+obj[i].location+"&quot;,&quot;"+obj[i].horario+"&quot;,&quot;"+obj[i].description+"&quot;,&quot;"+obj[i].logo+"&quot;)'><img src="
                        + servidor + obj[i].logo + " width='50' height='50'><div class='restaurant-title'> " + obj[i].name + "<br> </div>" +
                        "<div class='restaurant-info'>" + obj[i].location + "<br>" + obj[i].horario + "</div>" +
                        "</button></li>";
                }
            	$("#restaurant-list").html(ret);
                mui.viewPort.iScrollRefresh();
                mui.viewPort.showPage("mui-viewport-page2", "SLIDE_LEFT");
            }else{
            	mui.alert("No se encuentran restaurantes disponibles para su seleccion, intente con otra categoria.");
            }
        })
        .fail(function (jqXHR, textStatus, errorThrown) {

        })
}

function restaurantSelection(param, name, location, hr, description, logo) {
        $.ajax({
            url: servidor + 'WebServices/RestaurantServiceServlet',
            crossDomain: true,
            data:{
                ws: 2,
                user: userLogged,
                idRestaurant: param
            }
        })
            .done(function(data){
            	var muestra = "<br><img src="+ servidor + logo + " width='50' height='50'><h1>"+name+"</h1><p>"+description+"</p><p>"+location+"</p><p>"+hr+"</p>";
            	$("#panel1").html(muestra);
            	cart=" ";
            	ret2=" ";
            	idRest="";
            	cantidades=" ";
            	platos=" ";
            	yaAgregados=0;
            	priceTotal=0;
            	timeTotal=0;
            	
            	$("#my-cart-list").html(""); //se limpia la lista del carrito.
            	var btnUNO = document.getElementById("confirm-cart");
            	btnUNO.style.visibility  = 'hidden'; // No se ve
            	
                var obj=JSON.parse(data);
                var ret = "";
                if(obj.length > 0){
                	for(var i = 0 ; i < obj.length ; i++){
                        ret = ret + "<li class='menu-selector'><div>"+obj[i].name+" $"+obj[i].price+"</div><img id='imgMenu' src="+servidor + obj[i].picture+"><div class='menu-selector-div'><button class='menu-selector-btn' type='button' onclick='addPlate("+obj[i].id+","+param+","+obj[i].time+","+obj[i].price+",&quot;"+obj[i].name+"&quot;)'>+</button></div></li>";
                    }
                    $("#menu-list").html(ret);
                    mui.viewPort.iScrollRefresh();
                    mui.viewPort.showPage("mui-viewport-page3", "SLIDE_LEFT");
                }else{
                	mui.alert("Este restaurante no tiene menus disponibles. Intentelo mas tarde.");
                }
            })
            .fail(function(jqXHR, textStatus, errorThrown){

            })
}
function ocultarBarraInferior(){
	var hola = document.getElementById("footer");
	hola.style.visibility  = 'hidden'; // No se ve
}
function addPlate(param,id,time,price,name){
	priceTotal=priceTotal+price;
	timeTotal=timeTotal+time;
	idRest=id;
	ret2=ret2+"<li class='cart-item'><div class='cart-item-div'>"+name+" $"+price+"</div></li>";
	if(yaAgregados>0){
		platos=platos+","+param;
		cantidades=cantidades+",1";
	}else{
		cantidades="1";
		platos=param;
        var btnUNO = document.getElementById("confirm-cart");
        btnUNO.style.visibility  = 'visible'; // No se ve
	}
	yaAgregados=yaAgregados+1;
	$("#my-cart-list").html(ret2);
	mui.viewPort.iScrollRefresh();

}

function verifyOrder(){
	var apreto=0;
	/*mui.confirm(
			"Confirmar compra por $"+priceTotal+". El tiempo estimado es de "+timeTotal+" minutos.",
			function (buttonIndex) {
				apreto=buttonIndex;
			},
			"¿Confirmar compra?",
			"Confirmar,Cancelar"
		);*/
	mui.alert("Confirmar compra por $"+priceTotal+". El tiempo estimado es de "+timeTotal+" minutos.")
   // if (apreto == 1) {
        $.ajax({
            url: servidor + "WebServices/UserServiceServlet",
            type: "POST",
            crossDomain: true,
            data:{
                ws : 3,
                plato: platos,
                cantidad: cantidades,
                idRest: idRest,
                user: userLogged
          }
        })
            .done(function(data){
                getRandomVoucher();
                mui.viewPort.showPage("mui-viewport-page4", "SLIDE_DOWN");
                mui.viewPort.iScrollRefresh();

            })
            .fail(function(jqXHR, textStatus, errorThrown){

            })
    //}


}

function login(){
    var retrievedMail = $("#username-input").val();
    var retrievedPass = $("#password-input").val();
    userLogged=retrievedMail;
    $.ajax({
        url: servidor + "WebServices/UserServiceServlet",
        type: "POST",
        crossDomain: true,
        data:{
            ws : 7,
            mail: retrievedMail,
            password: retrievedPass
        }
    })
       .done(function(data){

            var obj = JSON.parse(data);
            if(obj.id==1){
            	mui.alert("Error en el servidor pruebe mas tarde.");
            }else if(obj.id==0){
            	mui.alert("Usuario y/o clave invalido.")
            }else{
                mail=obj.id;
                var info="<li>Usuario : "+mail+"</li><li>Nombre : "+obj.name+"</li>";
                $("#user-info").html(info);
                mui.viewPort.showPage("mui-viewport-page-home", "SLIDE_LEFT");
                var barra = document.getElementById("footer");
            	barra.style.visibility  = 'visible'; // No se ve
            }
        })
        .fail(function(jqXHR, textStatus, errorThrown){

        })
}
function goToRegister() {
    mui.viewPort.showPage("mui-viewport-page6", "SLIDE_RIGHT");
}
function doRegistration() {
    var retrievedMail = $("#register-email").val();
    var retrievedPass = $("#register-password").val();
    var retrievedName = $("#register-name").val();
    var retrievedConfirm = $("#register-confirm").val();
    if (retrievedPass == retrievedConfirm) {
        $.ajax({
            url: servidor + "WebServices/UserServiceServlet",
            type: "POST",
            crossDomain: true,
            data: {
                ws: 6,
                mail: retrievedMail,
                password: retrievedPass,
                name: retrievedName,
                fb: "hola"
            }
        })
            .done(function (data) {
                var obj = JSON.parse(data);
                if(obj.registro=='1'){
                    mui.alert("Registro completo!","Felicitaciones");
                    mui.viewPort.showPage("mui-viewport-page5", "SLIDE_LEFT");
                }else{
                    mui.alert("Ese mail ya esta en uso","Error");
                }

            })
            .fail(function (jqXHR, textStatus, errorThrown) {

            })
    }else{
        mui.alert("Los passwords no coinciden","Intenten denuevo");
    }

}
function goBackLogin() {
    mui.viewPort.showPage("mui-viewport-page5", "SLIDE_LEFT");
}
function getRandomVoucher(){
    $.ajax({
        url: servidor + "WebServices/UserServiceServlet",
        type: "GET",
        crossDomain: true,
        data:{
            ws : 4,
            user: userLogged
        }
    })
        .done(function(data){

            var obj = JSON.parse(data);
            var ret = "<p id='discount'>-"+obj.discount+" %</p><p id='info-discount'>Para disfrutar en: "+obj.shop+"</p>"+"<img id='imgVoucherCompra' src="+servidor + obj.shopImage+"><br>";
            $("#thanks-voucher").html("");
            $("#thanks-voucher").html(ret);
        })
        .fail(function(jqXHR, textStatus, errorThrown){

        })

}

function goHome(){
    mui.history.reset();
    mui.viewPort.showPage("mui-viewport-page-home", "SLIDE_LEFT");
}
function enConstruccion(){
	mui.alert("En construccion");
}

function comoIr(){
		 mui.viewPort.showPage("mui-viewport-ir", "SLIDE_LEFT");
         mui.viewPort.iScrollRefresh();
         map = new GMaps({
   		  div: '#map',
   		  zoom: 15,
   		lat: -34.903301,
      	 lng: -56.136902
   		});
        GMaps.geolocate({
        	  success: function(position) {
        	    map.setCenter(position.coords.latitude, position.coords.longitude);
        	    map.addMarker({
               	 lat: position.coords.latitude,
             	lng: position.coords.longitude,
               	  title: 'Tu',
               	  infoWindow: {
               		  content: 'Tu ubicacion'
               		}
             	});
        	    map.addMarker({
        	       	 lat: -34.903301,
        	       	 lng: -56.136902,
        	       	  title: 'Shopping',
        	       	  infoWindow: {
        	       		  content: 'Shopping'
        	       		}
        	     	});
        	        map.drawRoute({
        	        	  origin: [position.coords.latitude, position.coords.longitude],
        	        	  destination: [-34.903301, -56.136902],
        	        	  travelMode: 'driving',
        	        	  strokeColor: '#e04a59',
        	        	  strokeOpacity: 0.6,
        	        	  strokeWeight: 6
        	        	});
        	  },
        	  error: function(error) {
        	    alert('Geolocation failed: '+error.message);
        	  },
        	  not_supported: function() {
        	    alert("Your browser does not support geolocation");
        	  }
        	});

        
}

function loadCuenta(){
	loadLikes();
	loadNoLikes();
	mui.viewPort.showPage("mui-viewport-page-profile", "SLIDE_LEFT");
}
function loadLikes(){
	$.ajax({
        url: servidor + 'WebServices/UserServiceServlet',
        crossDomain: true,
        data:{
            ws: 8,
            user: userLogged
        }
    })
        .done(function(data){
            var obj=JSON.parse(data);
            var ret = "";
            if(obj.length > 0){
            	for(var i = 0 ; i < obj.length ; i++){
                    ret = ret + "<div id='div-gustos' class='mui-clickable' onclick='dislike("+obj[i].id+")'><img id='image-gustos' src="+servidor+obj[i].logo+"></div>";
                }
            }else{
            	ret="Aun no tiene gustos asignados, seleccione de la lista siguiente presionando sobre sus gustos.";
            }
            $("#perfil-gustos").html(ret);
            mui.viewPort.iScrollRefresh();
        })
        .fail(function(jqXHR, textStatus, errorThrown){

        })
}
function loadNoLikes(){
	$.ajax({
        url: servidor + 'WebServices/UserServiceServlet',
        crossDomain: true,
        data:{
            ws: 9,
            user: userLogged
        }
    })
        .done(function(data){
        	var obj=JSON.parse(data);
            var ret = "";
            if(obj.length > 0){
            	for(var i = 0 ; i < obj.length ; i++){
                    ret = ret + "<div id='div-gustos' class='mui-clickable' onclick='like("+obj[i].id+")'><img id='image-gustos' src="+servidor+obj[i].logo+"></div>";
                }
            }else{
            	ret="No hay locales que no le gusten.";
            }
            $("#perfil-nogustos").html(ret);
            mui.viewPort.iScrollRefresh();
        })
        .fail(function(jqXHR, textStatus, errorThrown){

        })
}
function dislike(id){
	$.ajax({
        url: servidor + 'WebServices/UserServiceServlet',
        crossDomain: true,
        data:{
            ws: 11,
            user: userLogged,
            idLocal: id
        }
    })
        .done(function(data){
        	loadLikes();
        	loadNoLikes();
        })
        .fail(function(jqXHR, textStatus, errorThrown){

        })
}
function like(id){
	$.ajax({
        url: servidor + 'WebServices/UserServiceServlet',
        crossDomain: true,
        data:{
            ws: 10,
            user: userLogged,
            idLocal: id
        }
    })
        .done(function(data){
        	loadLikes();
        	loadNoLikes();
        })
        .fail(function(jqXHR, textStatus, errorThrown){

        })
}
function showDiscounts(){
	 $.ajax({
         url: servidor + 'WebServices/UserServiceServlet',
         crossDomain: true,
         data: {
             ws: 5,
             user: userLogged
         }
     })
         .done(function (data) {
             var obj = JSON.parse(data);
             var ret = "";
             if(obj.length > 0){
             	for (var i = 0; i < obj.length; i++) {
                     ret = ret + "<li class='voucher-item'><div class='voucher'><br><div><img class='voucherImage' src='"+servidor+obj[i].shopImage+"'></div><div class='descuento'>-"+obj[i].discount+"%</div><b>En:</b> " + obj[i].shop + " <b>generado:</b> "+ obj[i].generetedTime + " <b>venc:</b> "+ obj[i].expirationTime +  " <b>voucher numero :</b> "+ obj[i].id +"</div></li>";
                 }         
                 $("#voucher-list").html(ret);
                 mui.viewPort.showPage("mui-viewport-page7", "SLIDE_LEFT");
                 mui.viewPort.iScrollRefresh();
             }else{
             	mui.alert("Usted no tiene ningun voucher activo. Para obtenerlo puede realizar una compra!");
             }

         })
         .fail(function (jqXHR, textStatus, errorThrown) {

         })
}
function logout(){
	var barra = document.getElementById("footer");
	barra.style.visibility  = 'hidden'; // No se ve
	mui.viewPort.showPage("mui-viewport-page5", "SLIDE_LEFT");
    mui.viewPort.iScrollRefresh();
}