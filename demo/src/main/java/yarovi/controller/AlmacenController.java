package yarovi.controller;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import yarovi.Service.AlmacenService;
import yarovi.entidad.Almacen;
import yarovi.utilidad.RutaVista;

@Controller
@RequestMapping("/almacen")
public class AlmacenController {

private static final Log LOG = LogFactory.getLog(AlmacenController.class);
	
	@Autowired
	private AlmacenService almacenService;
	
	@GetMapping("/todo")
	public ModelAndView todoAlmacen()
	{
		ModelAndView moc = new ModelAndView(RutaVista.vistaAllAlmacen);		
		LOG.info("lista Almacen :" + almacenService.obtenerTodoElemento());
		moc.addObject("almacenes", almacenService.obtenerTodoElemento());
		return moc;
	}
	@GetMapping("/cancelar")
	public String redirectCancelCliente() {
		return "redirect:/almacen/todo";
	}
	@GetMapping("/mostrarfrm")
	public String redireccionCliente(@RequestParam(name = "id", required = false) int id, Model model) {
		Almacen a = new Almacen();
		if (id != 0) {
			a = almacenService.buscarElemento(id);
		}
		LOG.info("nuevo Alamacen :" + a);		
		model.addAttribute("mdlalmacen", a);
		return RutaVista.vistaFrmAlmacen;
	}
	@PostMapping("/agregaralmacen")
	public  String AddredireccionAlmacen(@Valid @ModelAttribute(name="mdlalmacen") Almacen almacen,BindingResult bindingResult,Model mdl) {
		String vistaRetorno="";		
		LOG.info("valores :" + almacen.toString());
		if (bindingResult.hasErrors()) {
			vistaRetorno=RutaVista.vistaFrmAlmacen;
		}else {
			
			vistaRetorno="redirect:/almacen/todo";
			if(null!=almacen) {
				
				if (almacen.getAlmacenId()==0) {
					almacenService.insertNuevoElementoFinal(almacen);
				}else {
					almacenService.editarReferenciaElemento(almacen);
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
			Almacen a = almacenService.buscarElemento(id);
			almacenService.eliminarElemento(a);
		}		
		return todoAlmacen();
	}
	
	@PostConstruct
	public void AlmacenDefault() {
		Almacen a = new Almacen();
		a.setAlmacenNombre("Arequipa 2018 -I");
		a.setAlmacenEstado(true);
		almacenService.insertNuevoElementoFinal(a);
	}
}
