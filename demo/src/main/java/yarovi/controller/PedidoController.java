package yarovi.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.xml.ws.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import yarovi.Service.AlmacenService;
import yarovi.Service.DetallePedidoService;
import yarovi.Service.EmpleadoService;
import yarovi.Service.PedidoService;
import yarovi.Service.ProductoService;
import yarovi.entidad.Almacen;
import yarovi.entidad.Cliente;
import yarovi.entidad.DetallePedido;
import yarovi.entidad.Empleado;
import yarovi.entidad.Pedido;
import yarovi.entidad.Producto;
import yarovi.utilidad.ListaEnlazada;
import yarovi.utilidad.RutaVista;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

	private static final Log LOG = LogFactory.getLog(AlmacenController.class);

	@Autowired
	private PedidoService pedidoService;
	@Autowired
	private AlmacenService almacenService;
	@Autowired
	private ProductoService productoService;
	@Autowired
	private DetallePedidoService detallePedidoService;
	@Autowired
	private EmpleadoService empleadoService;

	@GetMapping("/todo")
	public ModelAndView todoAlmacen() {

		ModelAndView moc = new ModelAndView(RutaVista.vistaAllPedido);
		LOG.info("lista Pedido :" + pedidoService.obtenerTodoElemento());
		moc.addObject("pedidos", pedidoService.obtenerTodoElemento());

		return moc;
	}

	@GetMapping("/cancelar")
	public String redirectCancelCliente() {
		return "redirect:/pedido/todo";
	}

	@GetMapping("/mostrarfrm")
	public String redireccionCliente(@RequestParam(name = "id", required = false) int id, Model model) {
		Pedido p = new Pedido();
		if (id != 0) {
			p = pedidoService.buscarElemento(id);
			model.addAttribute("listaPedidos", p.getDetallePedido());
		} else {
			p.setPedidoCorrelativo(pedidoService.tamanio() + 1 + "");
			SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
			p.setPedidoFecha(dtf.format(new Date()));
		}
		LOG.info("nuevo Alamacen :" + p);
		model.addAttribute("mdlpedido", p);
		model.addAttribute("almacenes", almacenService.obtenerTodoElemento());
		model.addAttribute("empleados", empleadoService.obtenerTodoElemento());
		
		return RutaVista.vistaFrmPedido;
	}

	@PostMapping("/agregarpedido")
	public String AddredireccionAlmacen(@Valid @ModelAttribute(name = "mdlpedido") Pedido pedido,
			BindingResult bindingResult, Model mdl) {
		String vistaRetorno = "";
		LOG.info("valores :" + pedido.toString());
		if (bindingResult.hasErrors()) {
			vistaRetorno = RutaVista.vistaFrmPedido;
		} else {

			vistaRetorno = "redirect:/pedido/todo";
			if (null != pedido) {

				if (pedido.getPedidoId() == 0) {
					
					pedido.setDetallePedido(detallePedidoService.retornarListaDetalle());
					pedido.setPedidoCantidad(detallePedidoService.CalculoCantidadTotal());
					String FormatoMoneda=detallePedidoService.CalculoTotalaPagar();
					FormatoMoneda=FormatoMoneda.replace(",",".");
					pedido.setPedidoEstado("PENDIENTE");
					pedido.setPedidoTotal(Double.valueOf(FormatoMoneda));
					pedidoService.insertNuevoElementoFinal(pedido);
				} else {
					pedido.setDetallePedido(detallePedidoService.retornarListaDetalle());
					pedido.setPedidoCantidad(detallePedidoService.CalculoCantidadTotal());
					pedido.setPedidoTotal(Double.valueOf(detallePedidoService.CalculoTotalaPagar()));
					pedidoService.editarReferenciaElemento(pedido);
				}

				mdl.addAttribute("result", 1);
				detallePedidoService.vaciarLista();
			} else {
				mdl.addAttribute("result", 0);
			}
		}
		return vistaRetorno;
	}

	@GetMapping("/eliminar")
	public ModelAndView EliminarCliente(@RequestParam(name = "id", required = true) int id) {
		if (id != 0) {
			Pedido a = pedidoService.buscarElemento(id);
			pedidoService.eliminarElemento(a);
		}
		return todoAlmacen();
	}

	// agregando pedido
	@PostMapping(path = "/AgregarNuevoPedido", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Iterable<DetallePedido> AgregarPedido(
			@RequestParam(name = "codigoAlmacen", required = false) String codigoAlmacen,
			@RequestParam(name = "codigoProducto", required = false) String codigoProducto,
			@RequestParam(name = "pedidoCantidad", required = false) int pedidoCantidad) {
		Producto c = productoService.buscarxIdProductoyAlmacen(codigoAlmacen, codigoProducto);
		DetallePedido nuevopedidoElemento = new DetallePedido();
		nuevopedidoElemento.setCantidadPedido(pedidoCantidad);
		nuevopedidoElemento.setProducto(c);
		detallePedidoService.insertNuevoElementoFinal(nuevopedidoElemento);
		LOG.info("lista Pedido :" + detallePedidoService.obtenerTodoElemento());
		return detallePedidoService.obtenerTodoElemento();
	}

	@PostMapping(path = "/EliminarElementoPedido")
	public ResponseEntity EliminarElementoPedido(
			@RequestParam(name = "iddetallePedido", required = false) int iddetallePedido) {

		if (iddetallePedido != 0) {
			DetallePedido a = detallePedidoService.buscarElemento(iddetallePedido);
			detallePedidoService.eliminarElemento(a);
			return new ResponseEntity(HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}

	@GetMapping(path = "/ListarTodoPedido2")
	public String ListarTodoPedido2(Model mdl) {

		String cadena = "";
		mdl.addAttribute("listaPedidos", detallePedidoService.obtenerTodoElemento());
		mdl.addAttribute("pagoTotal", detallePedidoService.CalculoTotalaPagar());
		LOG.info("formato texo  :" + cadena);
		return "pedido/vistaFrmPedido :: listaPedidos";
	}

}
