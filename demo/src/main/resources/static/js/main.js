function BuscarClientexNroDocumento() {

	var codigoBusqueda = $("#NumeroDocumento").val();

	$.post("/cliente/ObtenerxNrodocumento", {
		codigo : codigoBusqueda
	}, function(data, status) {

		if (data["clienteId"] == 0) {
			alert("No se encontro el documento.");
		} else {
			$('#PedidoNombreCliente').val(data["clienteNombre"]);
			$('#PedidoDireccionEntrega').val(data["clienteDireccion"]);
			$('#PedidoTipoCliente').val(data["clienteTipo"]);
		}

	});

}

function LimpiarDato() {

	$("#NumeroDocumento").val();
	$('#nombrecliente').val("");
	$('#direccionCliente').val("");
	$('#tipoCliente').val("");
}

$('form').bind("keypress", function(e) {
	if (e.keyCode == 13) {
		e.preventDefault();
		return false;
	}
});

$("#codigoproducto").on(
		"keydown",
		function() {

			if (event.which == 13) {
				// alert("buscadn");

				var codigoAlmacenLocal = $("#listaalmacenes").val();
				var codigoProductoLocal = $("#codigoproducto").val();

				$.post("/producto/ObtenerxProductoxAlmacen", {
					codigoAlmacen : codigoAlmacenLocal,
					codigoProducto : codigoProductoLocal
				}, function(data, status) {

					if (data["productoId"] == 0) {
						alert("El prodcuto con codigo :" + codigoProductoLocal
								+ " no existe.");
						LimpiarBusqueda();
					} else {
						$("#ProductoDescripcion").val(
								data["productoDescripcion"])
						$('#ProductoPrecioVenta').val(
								data["productoPrecioVenta"]);
						$('#cantidaddisponible').val(data["productoCantidad"]);
						$('#ProductoCantidad').val(1);
					}

				});
			}

		});

function LimpiarBusqueda() {
	$("#ProductoDescripcion").val("")
	$('#ProductoCantidad').val("");
	$('#ProductoPrecioVenta').val("");
	$("#codigoproducto").val("");
	$("#cantidaddisponible").val("");
}

function AgregarProductoaPedido() {

	var codigoAlmacenLocal = $("#listaalmacenes").val();
	var codigoProductoLocal = $("#codigoproducto").val();
	var disponibilidadProductoLocal = $("#cantidaddisponible").val();
	var cantidadPedidoLocal = $("#ProductoCantidad").val();

	if (parseInt(disponibilidadProductoLocal) < parseInt(cantidadPedidoLocal)) {

		$('#exampleModal').modal('show');
		$('#contenidoModal').html("No hay disponibilidad para esa cantidad.")
	} else {

		$.post("/pedido/AgregarNuevoPedido", {
			codigoAlmacen : codigoAlmacenLocal,
			codigoProducto : codigoProductoLocal,
			pedidoCantidad : cantidadPedidoLocal
		}, function(data, status) {

			if (data["productoId"] == 0) {
			} else {
						
				listarpedido();
			}
		});
	}
}

function listarpedido() {

	$("#tabladetalle").load("/pedido/ListarTodoPedido2",$("#tabladetalle").serialize());

}

function EliminarItemPedido(elemento) {

	$.post("/pedido/EliminarElementoPedido", {
		iddetallePedido : elemento
	}, function(data, responseText,status) {
		//alert("valor es" + status);
		listarpedido();

	});

}

$('#ordenacion').on('change',function(){
	
	var valor=$(this).val();
	$("#tablaordenada").load("/reporte/Ordenar?tipoOrdenacion="+valor,$("#tablaordenada").serialize());
});


function BusquedaTexto(){
	
	var valor=$('#busquedatexto').val();
	var nombreBuscar=$('#nombreBuscar').val();
	var cadena=document.getElementById("mostrartexto").innerText
	//alert(document.getElementById("mostrartexto").innerText);
//	console.log(nombreBuscar  +" : "+ cadena + " tipo busqueda "+valor );
//	
	$.post("/reporte/BuscarTexto", {
		tipoBusqueda : valor,
		nombre : nombreBuscar,
		formatoTexto : cadena
	}, function(data, status) {

		if(data!="-1")
			{
				$("#resultado").text(data);
			}else{
				$("#resultado").text("No se encontro ... :(");
				
			}
				
		console.log(data);
	});
	
	};

window.onload = function() {
    var fileInput = document.getElementById('rutaArchivoPlano');
    var fileDisplayArea = document.getElementById('mostrartexto');

    fileInput.addEventListener('change', function(e) {
    	var file = fileInput.files[0];
		var textType = /text.*/;

		if (file.type.match(textType)) {
			var reader = new FileReader();
			
			reader.onload = function(e) {
				fileDisplayArea.innerText = reader.result;
			}

			reader.readAsText(file,"ISO-8859-8");	
		} else {
			fileDisplayArea.innerText = "Formato no es correcto!"
		}
    });
}