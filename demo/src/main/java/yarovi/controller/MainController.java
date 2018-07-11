package yarovi.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import yarovi.utilidad.RutaVista;


@Controller
public class MainController {
	
		private static final Log LOG = LogFactory.getLog(MainController.class);
		
		@GetMapping("/")		
		public String  redirectHome(){
			LOG.info("Home ...  " + getClass().getPackage().getImplementationVersion());
			return RutaVista.vistaDefecto;			
		}
}
