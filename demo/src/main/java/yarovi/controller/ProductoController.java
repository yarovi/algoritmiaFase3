package yarovi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import yarovi.Service.ProductoService;
import yarovi.entidad.Producto;
import yarovi.utilidad.RutaVista;

@Controller
@RequestMapping("/producto")
public class ProductoController {
	private static final Log LOG = LogFactory.getLog(ProductoController.class);

	@Autowired
	private ProductoService productoService;
	@Autowired
	private AlmacenService almacenService2;
	List<Producto> ListaProducto = new ArrayList<>();

	@GetMapping("/todo")
	public ModelAndView todoProducto() {
		ModelAndView moc = new ModelAndView(RutaVista.vistaAllProducto);
		moc.addObject("almacenes", almacenService2.obtenerTodoElemento());
		moc.addObject("productos", productoService.obtenerTodoElemento());
		LOG.info("llamada Productos :" + "getAllPersona");
		return moc;
	}

	@GetMapping("/cancelar")
	public String redirectCancelProdcuto() {
		return "redirect:/producto/todo";
	}

	@GetMapping("/mostrarfrm")
	public String redireccionProducto(@RequestParam(name = "id", required = false) int id, Model model) {
		Producto a = new Producto();

		if (id != 0) {
			a = productoService.buscarElemento(id);
		}

		model.addAttribute("almacenes", almacenService2.obtenerTodoElemento());
		LOG.info("nuevo Producto :" + a);
		model.addAttribute("mdlproducto", a);
		return RutaVista.vistaFrmProducto;
	}

	@PostMapping("/agregarproducto")
	public String AddredireccionAlmacen(@Valid @ModelAttribute(name = "mdlprodcuto") Producto producto,
			BindingResult bindingResult, Model mdl) {
		String vistaRetorno = "";
		LOG.info("valores :" + producto.toString());
		if (bindingResult.hasErrors()) {
			vistaRetorno = RutaVista.vistaFrmProducto;
		} else {

			vistaRetorno = "redirect:/producto/todo";
			if (null != producto) {

				if (producto.getProductoId() == 0) {
					productoService.insertNuevoElementoFinal(producto);
				} else {
					productoService.editarReferenciaElemento(producto);
				}

				mdl.addAttribute("result", 1);
			} else {
				mdl.addAttribute("result", 0);
			}
		}
		return vistaRetorno;
	}

	@GetMapping("/eliminar")
	public ModelAndView EliminarCliente(@RequestParam(name = "id", required = true) int id) {
		if (id != 0) {
			Producto a = productoService.buscarElemento(id);
			productoService.eliminarElemento(a);
		}
		return todoProducto();
	}

	@PostMapping(path = "/ObtenerProductoxCodigo", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Producto ObtenerProductoxCodigo(@RequestParam(name = "codigoAlmacen", required = false) String codigoAlmacen,
			@RequestParam(name = "codigoProducto", required = false) String codigoProducto) {

		Producto p = new Producto();
		for (Producto temp : productoService.obtenerTodoElemento()) {

			if (temp.getAlmacenId() == Integer.parseInt(codigoAlmacen)
					&& temp.getProductoId() == Integer.parseInt(codigoProducto)) {
				LOG.info("Prodcuto :" + temp.getAlmacenId() + " " + temp.getProductoId() + "  " + codigoAlmacen + "  "
						+ codigoProducto);
				p = temp;
				break;
			}
		}
		LOG.info("Elegido Producto :" + p);
		return p;
	}

	@PostMapping(path = "/ObtenerxProductoxAlmacen", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Producto ObtenerxProductoxAlmacen(
			@RequestParam(name = "codigoAlmacen", required = false) String codigoAlmacen,
			@RequestParam(name = "codigoProducto", required = false) String codigoProducto) {
		Producto c = productoService.buscarxIdProductoyAlmacen(codigoAlmacen, codigoProducto);
		return c;
	}


//	funciona OK
//	@PostMapping(path = "/AgregarPedido", produces = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public List<Producto> obtenerListaPeroducto(
//			@RequestParam(name = "codigoAlmacen", required = false) String codigoAlmacen,
//			@RequestParam(name = "codigoProducto", required = false) String codigoProducto) {
//		Producto c = buscarxIdProducto(codigoAlmacen, codigoProducto);
//		ListaProducto.add(c);
//		return ListaProducto;
//	}
	@PostMapping(path = "/AgregarPedido")
	public ModelAndView obtenerListaPeroducto(
			@RequestParam(name = "codigoAlmacen", required = false) String codigoAlmacen,
			@RequestParam(name = "codigoProducto", required = false) String codigoProducto) {
		Producto c = productoService.buscarxIdProductoyAlmacen(codigoAlmacen, codigoProducto);
		ListaProducto.add(c);
		ListaProducto.add(c);
		ModelAndView moc = new ModelAndView(RutaVista.vistaFrmPedido);
		moc.addObject("productos",ListaProducto);
		return moc;
	}

	@PostConstruct
	public void ProductoDefault() {
		Producto p1 = new Producto();
		p1.setProductoCantidad(20);
		p1.setProductoPrecioUnitario(3.5);
		p1.setProductoDescripcion("Arroz");
		p1.setProductoEstado(true);
		p1.setAlmacenId(1);
		p1.setProductoPrecioVenta(4.2);
		Producto p2 = new Producto();
		p2.setProductoCantidad(5);
		p2.setProductoPrecioUnitario(5.0);
		p2.setProductoDescripcion("Trigo");
		p2.setProductoEstado(true);
		p2.setAlmacenId(1);
		p2.setProductoPrecioVenta(7.0);
		Producto p3 = new Producto();
		p3.setProductoCantidad(20);
		p3.setProductoPrecioUnitario(2.5);
		p3.setProductoDescripcion("Azucar");
		p3.setProductoEstado(true);
		p3.setAlmacenId(1);
		p3.setProductoPrecioVenta(3.0);
		Producto p4 = new Producto();
		p4.setProductoCantidad(2);
		p4.setProductoPrecioUnitario(8.0);
		p4.setProductoDescripcion("Leche");
		p4.setProductoEstado(true);
		p4.setAlmacenId(1);
		p4.setProductoPrecioVenta(10.0);
		productoService.insertNuevoElementoFinal(p1);
		productoService.insertNuevoElementoFinal(p2);
		productoService.insertNuevoElementoFinal(p3);
		productoService.insertNuevoElementoFinal(p4);
	}

}
