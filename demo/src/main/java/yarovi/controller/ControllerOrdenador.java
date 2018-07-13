package yarovi.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import yarovi.Service.PedidoService;
import yarovi.entidad.Pedido;
import yarovi.utilidad.BoyerMoore;
import yarovi.utilidad.KMP;
import yarovi.utilidad.ListaEnlazada;
import yarovi.utilidad.Ordenar;
import yarovi.utilidad.RutaVista;

@Controller
@RequestMapping("/reporte")
public class ControllerOrdenador {

	private static final Log LOG = LogFactory.getLog(ControllerOrdenador.class);
	@Autowired
	private PedidoService pedidoService;
	Ordenar miordenador = new Ordenar();

	@GetMapping("/todo")
	public ModelAndView todoReporte() {

		ModelAndView moc = new ModelAndView(RutaVista.FrmReporte);
		LOG.info("lista Pedido :" + pedidoService.obtenerTodoElemento());
		moc.addObject("listaordenada", pedidoService.obtenerTodoElemento());
		return moc;
	}
	
	@GetMapping("/buscar")
	public ModelAndView buscar() {
		ModelAndView moc = new ModelAndView(RutaVista.FrmBusqueda);		
		return moc;
	}
	
	@GetMapping(path = "/Ordenar")
	public String OrdenarPorCliente(@RequestParam(name = "tipoOrdenacion", required = false) String tipoOrdenacion,
			Model mdl) {

		String cadena = "";
		if (tipoOrdenacion.equals("A")) {
			pedidoService.setListaEnlazadaSimple(miordenador.metodoBurbuja(pedidoService.getListaEnlazadaSimple()));
		} else if (tipoOrdenacion.equals("B")) {
			pedidoService.setListaEnlazadaSimple(miordenador.metodoInserccionDirecta(pedidoService.getListaEnlazadaSimple()));
			
		}
		mdl.addAttribute("listaordenada", pedidoService.obtenerTodoElemento());
		LOG.info("formato texo  :" + pedidoService.obtenerTodoElemento());
		return "reporte/FrmReporte :: listaordenada";
	}
	@PostMapping(path = "/BuscarTexto")
	@ResponseBody
	public String MetodoBusqueda(
			@RequestParam(name = "tipoBusqueda", required = false) String tipoBusqueda,
			@RequestParam(name = "nombre", required = false) String nombre,
			@RequestParam(name = "formatoTexto", required = false) String formatoTexto
) {
		String cadena = "";
		LOG.info("valores enviados :" + formatoTexto +" : "+ nombre);
		KMP mikmp= new KMP();
		BoyerMoore bm = new BoyerMoore(); 
		if (tipoBusqueda.equals("A")) {
				cadena=mikmp.leerText(formatoTexto, nombre);
		} else if (tipoBusqueda.equals("B")) {
			cadena=bm.leerText(formatoTexto, nombre);
		}
		LOG.info("formato texo  :" + cadena);
		return cadena;
	}

}
