(function($) {
	var version = '1.0.0';
	var author = "Gustavo Gretter";
	var idCount = 1;
	var methods = {
		init : function(options) {
			return this.each(function() {
				var $this = $(this);
				if ($.type($this.data('wait-attributes'))=="undefined") {
					// Defino los atributos y los anexo al elemento.
					var attributes = $.extend({
					   	position: "absolute",
					   	zIndex:  "1000",
				   		bgColor: "#ffffff",
				   		bgOpacity: "0.8",
				   		bgAlphaFilter: "80",
				   		bgImage: "/innovaportal/images/wait.gif",
						id: "wait-id-"			
					}, options);
					attributes.id=attributes.id + idCount++;
					$this.data('wait-attributes', attributes);
				}
			});
		},
		show : function() {
			return this.each(function() {
				var $this = $(this);
				//Obtengo la data
				var dt = $this.data('wait-attributes');
				if ($.type(dt)=='undefined') {
					$this.jqwait();
					dt = $this.data('wait-attributes');
				}
				//Obtengo el primer hijo div. Si no existe lo creo.
				$wait = $('#'+dt.id);
				if ($wait.size()==0) {
					$div = $("<div id='" + dt.id + "'/>");
					$div.css({'display':'block','position': dt.position, 'z-index':dt.zIndex,'top':'0','left':'0','height':'100%','width':'100%'});
					$div.css({"background-color":dt.bgColor, 'background-image':"url('" + dt.bgImage + "')",'background-position':'50% 50%','background-repeat':'no-repeat'});
					if (document.addEventListener) { //IE8
						$div.css("opacity",dt.bgOpacity);
					} else {
						$div.css("filter","alpha(opacity=" + dt.bgAlphaFilter + ");");
					}
					$this.prepend($div);
				} else {
					$wait.show();
				}
			});
		},
		hide: function() {
			return this.each(function() {
				var $this = $(this);
				var dt = $this.data('wait-attributes');
				$wait = $('#'+dt.id).hide();
			});
		},
		destroy: function() {
			return this.each(function() {

			});
		}
	};
	$.fn.jqwait = function(method) {
		// Invocación del método
		if (methods[method]) {
			return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
		} else if (typeof method === 'object' || !method) {
			return methods.init.apply(this, arguments);
		} else {
			$.error('Method ' + method + ' does not exist.');
		}
	};
})(jQuery);