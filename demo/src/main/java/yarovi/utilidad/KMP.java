package yarovi.utilidad;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import yarovi.controller.ControllerOrdenador;

public class KMP {

	private static final Log LOG = LogFactory.getLog(ControllerOrdenador.class);

	@Autowired
	public String leerText(String cadena,String nombre)
	{
		String rpta="-1";
		try {

		BufferedReader br = new BufferedReader(new StringReader(cadena));
		
		try {
		    String line = br.readLine();

		    int result = KMP(line, nombre);
			
		    while ( line != null && result==-1) {

		        
		        System.out.println(line);
		        
		        result=KMP(line, nombre);
		        if (result != -1) {
					rpta=line;		
		        	break;
				}
		        line = br.readLine();
		    }

		} finally {
		    br.close();
		}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error "+e.getMessage());
			LOG.info("Error ruta :" + e.getMessage());
		}
		
		return rpta;
	}
	
	public static int KMP(String busqueda, String objetivo) {
		int[] tablaDeError = tablaDeErrorBase(objetivo);

		int entradaObjetivo = 0; 
		int entradaBusqueda = 0; 

		while (entradaBusqueda < busqueda.length()) { 
			if (busqueda.charAt(entradaBusqueda) == objetivo.charAt(entradaObjetivo)) { 
				
				entradaObjetivo++;
				if (entradaObjetivo == objetivo.length()) { 
					int x = objetivo.length() + 1;
					return entradaBusqueda - x;
				}
				entradaBusqueda++; 
			} else if (entradaObjetivo > 0) { // case 2

				entradaObjetivo = tablaDeError[entradaObjetivo];
			} else { 
				entradaBusqueda++;
			}
		}
		return -1;
	}

	public static int[] tablaDeErrorBase(String patron) {
		int[] tablita = new int[patron.length() + 1];

		tablita[0] = -1;
		tablita[1] = 0;


		int izq = 0;
		int dre = 2;

		while (dre < tablita.length) { 
			if (patron.charAt(dre - 1) == patron.charAt(izq)) { 
																	
				izq++;
				tablita[dre] = izq;
				dre++;
			} else if (izq > 0) { 
								
				izq = tablita[izq];
			} else {
				tablita[dre] = izq;
				dre++;
			}
		}
		return tablita;
	}
}
