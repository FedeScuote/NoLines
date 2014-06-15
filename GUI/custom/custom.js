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
    $("#click2").on("click", function (e) {
        $.ajax({
            url: servidor + '/WebServices/RestaurantServiceServlet',
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
                        ret = ret + "<li class='restaurant-selector'><button class='mui-clickable default-button' onclick='restaurantSelection(" + obj[i].id +",&quot;"+obj[i].name+"&quot;,&quot;"+obj[i].location+"&quot;,&quot;"+obj[i].horario+"&quot;,&quot;"+obj[i].description+"&quot;,&quot;"+obj[i].logo+"&quot;)'><img src="
                            + servidor + obj[i].logo + " width='50' height='50'><div class='restaurant-title'> " + obj[i].name + "<br> </div>" +
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
    
    
    $("#click3").on("click", function (e) {
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
                        ret = ret + "<li class='voucher-item'><div class='voucher'><br><div class='descuento'>-"+obj[i].discount+"%</div><b>En:</b> " + obj[i].shop + " <b>generado:</b> "+ obj[i].generetedTime + " <b>venc:</b> "+ obj[i].expirationTime + " <b>usado:</b> "+ obj[i].usedTime + " <b>voucher numero :</b> "+ obj[i].id +"</div></li>";
                    }
                }else{
                	ret = "No tiene vouchers";
                }
                
                $("#voucher-list").html(ret);
                mui.viewPort.showPage("mui-viewport-page7", "SLIDE_LEFT");
                mui.viewPort.iScrollRefresh();

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
                        ret = ret + "<li class='menu-selector'><div>"+obj[i].name+"<br>$"+obj[i].price+"</div><div class='menu-selector-div'><button class='menu-selector-btn' type='button' onclick='addPlate("+obj[i].id+","+param+","+obj[i].time+","+obj[i].price+",&quot;"+obj[i].name+"&quot;)'>+</button></div></li>";
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
	mui.viewPort.iScrollRefresh();
	
	//ret='<form method="post"><input name="idRest" value="'+id+'" type="hidden"/>';
	//cart=cart+name+'<input name="plato" value="'+param+'" type="hidden"/><input name="cantidad" value="1" type="hidden"/><br>';
	//ret=ret+cart+'<div id="confirmar"><input type="buttom" onclick=verifyOrder() value="Confirmar" /></div>';
	//$("#cart-display").html(ret);

}

function verifyOrder(){
	var apreto=0;
	mui.confirm(
			"Confirmar compra por $"+priceTotal+". El tiempo estimado es de "+timeTotal+" minutos.",
			function (buttonIndex) {
				apreto=buttonIndex;
			},
			"¿Confirmar compra?",
			"Confirmar,Cancelar"
		);
    if (apreto == 1) {
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
    }


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
            	mui.alert("Usuario o contraseña invalido.")
            }else{
                mail=obj.id;
                mui.viewPort.showPage("mui-viewport-page1", "SLIDE_LEFT");
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
                if(obj.registro==1){
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
            var ret = "<p id='discount'>"+obj.discount+" % de descuento en la siguiente tienda!</p>"+"<img id='imgVoucherCompra' src="+sevidor + obj.shopImage+"><br>";
            $("#thanks-voucher").html("");
            $("#thanks-voucher").html(ret);
        })
        .fail(function(jqXHR, textStatus, errorThrown){

        })

}

function goHome(){
    mui.history.reset();
    mui.viewPort.showPage("mui-viewport-page1", "SLIDE_LEFT");
}