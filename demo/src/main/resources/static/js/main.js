function BuscarClientexNroDocumento() {

	var codigoBusqueda = $("#NumeroDocumento").val();

	$.post("/cliente/ObtenerxNrodocumento", {
		codigo : codigoBusqueda
	}, function(data, status) {

		if (data["clienteId"] == 0) {
			alert("No se encontro el documento.");
		} else {
			$('#nombrecliente').val(data["clienteNombre"]);
			$('#direccionCliente').val(data["clienteDireccion"]);
			$('#tipoCliente').val(data["clienteTipo"]);
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

		$.post("/pedido/AgregarPedido", {
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
		alert("valor es" + status);
		listarpedido();

	});

}
