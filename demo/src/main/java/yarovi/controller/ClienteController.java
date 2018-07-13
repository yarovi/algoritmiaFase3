package yarovi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

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

import yarovi.Service.ClienteService;
import yarovi.entidad.Almacen;
import yarovi.entidad.Cliente;
import yarovi.entidad.DetallePedido;
import yarovi.utilidad.Categoria;
import yarovi.utilidad.CategoriaCliente;
import yarovi.utilidad.RutaVista;



@Controller
@RequestMapping("/cliente")
public class ClienteController {
	private static final Log LOG = LogFactory.getLog(ClienteController.class);
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("/todo")
	public ModelAndView todoCliente()
	{
		ModelAndView moc = new ModelAndView(RutaVista.vistaAllCliente);
		moc.addObject("clientes", clienteService.obtenerTodoElemento());
		LOG.info("lista Cliente :" + clienteService.obtenerTodoElemento());			
		return moc;
	}
	@GetMapping("/cancelar")
	public String redirectCancelCliente() {
		return "redirect:/cliente/todo";
	}
	@GetMapping("/mostrarfrm")
	public String redireccionCliente(@RequestParam(name = "id", required = false) int id, Model model) {
		Cliente c = new Cliente();
		if (id != 0) {
			c = clienteService.buscarElemento(id);
		}
		model.addAttribute("mdlcliente", c);
		LOG.info("nuevo cliente :" + c);	
		return RutaVista.vistaFrmCliente;
	}
	@PostMapping("/agregarcliente")
	public  String AddredireccionCliente(@RequestParam(name="mdlcliente") Cliente cliente,BindingResult bindingResult,Model mdl) {
		String vistaRetorno="";		
		LOG.info("valores :" + cliente.toString());
		if (bindingResult.hasErrors()) {
			vistaRetorno=RutaVista.vistaFrmCliente;
		}else {
			
			vistaRetorno="redirect:/cliente/todo";
			if(null!=cliente) {
				if(cliente.getClienteId()==0) {
					clienteService.insertNuevoElementoFinal(cliente);
				}else {
					clienteService.editarReferenciaElemento(cliente);
				}
				mdl.addAttribute("result",1);
			}else {
				mdl.addAttribute("result",0);
			}
			
		}
		return vistaRetorno;
	}
	@GetMapping("/eliminar")
	public ModelAndView EliminarCliente(@RequestParam(name = "id", required = true) int id) {
		if (id != 0) {
			Cliente c = clienteService.buscarElemento(id);
			clienteService.eliminarElemento(c);
		}		
		return todoCliente();
	}
	
	@PostMapping(path="/ObtenerxNrodocumento",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Cliente ObtenerxNrodocumento(@RequestParam(name = "codigo", required = false) String codigo)
	{
//		ModelAndView moc = new ModelAndView(RutaVista.vistaAllCliente);
		Cliente c = new Cliente();
		
		for (Cliente c2: clienteService.obtenerTodoElemento()) {
			LOG.info("Nro Documento Cliente :" + c2.getClienteNroDocumento() + " =" + codigo);
			if (c2.getClienteNroDocumento().equals(codigo)){
				c=c2;
				break;
			}
		}
		LOG.info("Elegido Cliente :" + c);			
		return c;
	}
	@GetMapping(path = "/ListarTodoCliente", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
		public  Iterable<Cliente>  ListarTodoPedido2() {
				
		return clienteService.obtenerTodoElemento();
	}
	
	@ModelAttribute("categoriaTipo")
		public List<CategoriaCliente> CategoriaTipo(){
		CategoriaCliente tipoA = new CategoriaCliente();
		tipoA.setCodigo("MAFR");
		tipoA.setNombre(Categoria.MAFR);
		CategoriaCliente tipoB = new CategoriaCliente();
		tipoB.setCodigo("MAY");
		tipoB.setNombre(Categoria.MAY);
		CategoriaCliente tipoC = new CategoriaCliente();
		tipoC.setCodigo("MIN");
		tipoC.setNombre(Categoria.MIN);
		CategoriaCliente tipoD = new CategoriaCliente();
		tipoD.setCodigo("MIFR");
		tipoD.setNombre(Categoria.MIFR);
		List<CategoriaCliente> listatipo = new ArrayList<>();
		listatipo.add(tipoA);
		listatipo.add(tipoB);
		listatipo.add(tipoC);
		listatipo.add(tipoD);
		return listatipo;
	}

	@PostConstruct
	public void ClienteDefault() {
		Cliente c= new Cliente();
		c.setClienteNombre("UCSM");
		c.setClienteDocumento("RUC");
		c.setClienteEstado(true);
		c.setClienteDireccion("UMACOLLO SIN #");
		c.setClienteNroDocumento("20141637941");
		c.setClienteTipo(Categoria.MAFR);
		clienteService.insertNuevoElementoFinal(c);
		Cliente c2= new Cliente();
		c2.setClienteNombre("YASMANI");
		c2.setClienteDocumento("DNI");
		c2.setClienteEstado(true);
		c2.setClienteDireccion("SOCABAYA 104");
		c2.setClienteNroDocumento("43129134");
		c2.setClienteTipo(Categoria.MIN);
		clienteService.insertNuevoElementoFinal(c2);
		Cliente c3= new Cliente();
		c3.setClienteNombre("MANUEL");
		c3.setClienteDocumento("DNI");
		c3.setClienteEstado(true);
		c3.setClienteDireccion("SAN MARTIN 103");
		c3.setClienteNroDocumento("12345678");
		c3.setClienteTipo(Categoria.MIN);
		clienteService.insertNuevoElementoFinal(c3);
	}
}
