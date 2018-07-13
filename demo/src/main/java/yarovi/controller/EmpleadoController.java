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

import yarovi.Service.EmpleadoService;
import yarovi.entidad.Almacen;
import yarovi.entidad.Empleado;
import yarovi.utilidad.RutaVista;


@Controller
@RequestMapping("/empleado")
public class EmpleadoController {

private static final Log LOG = LogFactory.getLog(EmpleadoController.class);
	
	@Autowired
	private EmpleadoService empleadoService;
	
	@GetMapping("/todo")
	public ModelAndView todoCliente()
	{
		ModelAndView moc = new ModelAndView(RutaVista.vistaAllEmpleado);
		moc.addObject("empleados", empleadoService.obtenerTodoElemento());
		LOG.info("lista Empleados :" + empleadoService.obtenerTodoElemento());			
		return moc;
	}
	@GetMapping("/cancelar")
	public String redirectCancelCliente() {
		return "redirect:/empleado/todo";
	}
	@GetMapping("/mostrarfrm")
	public String redireccionCliente(@RequestParam(name = "id", required = false) int id, Model model) {
		Empleado empleado = new Empleado();
		if (id != 0) {
			empleado = empleadoService.buscarElemento(id);
		}
		model.addAttribute("mdlempleado", empleado);

		return RutaVista.vistaFrmEmpleado;
	}
	@PostMapping("/agregarempleado")
	public  String AddredireccionEmpleado(@Valid @ModelAttribute(name="mdlempleado") Empleado empleado,BindingResult bindingResult,Model mdl) {
		String vistaRetorno="";
		
		LOG.info("valores :" + empleado.toString());
		if (bindingResult.hasErrors()) {
			vistaRetorno=RutaVista.vistaFrmEmpleado;
		}else {
			vistaRetorno="redirect:/empleado/todo";
			if(null!=empleadoService.insertNuevoElementoFinal(empleado)) {
				mdl.addAttribute("result",1);
			}else {
				mdl.addAttribute("result",0);
			}
		}
		return vistaRetorno;
	}
	@GetMapping("/eliminar")
	public ModelAndView EliminarEmpleado(@RequestParam(name = "id", required = true) int id) {
		if (id != 0) {
			Empleado e = empleadoService.buscarElemento(id);
			empleadoService.eliminarElemento(e);
		}		
		return todoCliente();
	}
	
	@PostConstruct
	public void EmpleadosDefault() {
		Empleado e= new Empleado();
		e.setEmpleadoNombre("Daniel");
		e.setEmpleadoApellido("Traviezo");
		e.setEmpleadoNroDocumento("43129135");
		e.setEmpleadoDocumento("DNI");
		Empleado e2= new Empleado();
		e2.setEmpleadoNombre("Popeye");
		e2.setEmpleadoApellido("Marino");
		e2.setEmpleadoNroDocumento("43129136");
		e2.setEmpleadoDocumento("DNI");
		empleadoService.insertNuevoElementoFinal(e);
		empleadoService.insertNuevoElementoFinal(e2);
		
		Empleado e3= new Empleado();
		e3.setEmpleadoNombre("Ximena");
		e3.setEmpleadoApellido("Prada");
		e3.setEmpleadoNroDocumento("22222222");
		e3.setEmpleadoDocumento("DNI");
		empleadoService.insertNuevoElementoFinal(e3);
		Empleado e4= new Empleado();
		e4.setEmpleadoNombre("Andrea");
		e4.setEmpleadoApellido("Carrillo");
		e4.setEmpleadoNroDocumento("55555555");
		e4.setEmpleadoDocumento("DNI");
		empleadoService.insertNuevoElementoFinal(e4);
		
	}
	
	
}
