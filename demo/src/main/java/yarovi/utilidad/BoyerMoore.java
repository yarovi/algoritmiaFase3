package yarovi.utilidad;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

public class BoyerMoore {

	public String leerText(String cadena,String nombre)
	{
		String rpta="-1";
		try {

		BufferedReader br = new BufferedReader(new StringReader(cadena));
		
		try {
		    String line = br.readLine();
		    BoyerMoore bm = new BoyerMoore(); 
	         
		    int result = bm.busquedaPatron(line, nombre);
			
		    while ( line != null && result==-1) {

		        
		        System.out.println(line);
		        
		        result=bm.busquedaPatron(line, nombre);
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
			
		}
		
		return rpta;
	}
	public int busquedaPatron(String t, String p)
    {
        char[] text = t.toCharArray();
        char[] pattern = p.toCharArray();
        int pos = indicePatron(text, pattern);
        return pos;
    }
	
	public int indicePatron(char[] texto, char[] patron) 
    {
        if (patron.length == 0) 
            return 0;
        int caracterTabla[] = tablaCaracter(patron);
        int offsetTable[] = tablaDesplazamiento(patron);
        for (int i = patron.length - 1, j; i < texto.length;) 
        {
            for (j = patron.length - 1; patron[j] == texto[i]; --i, --j) 
                     if (j == 0) 
                    return i;
 
              // i += pattern.length - j; // For naive method
              i += Math.max(offsetTable[patron.length - 1 - j], caracterTabla[texto[i]]);
        }
        return -1;
      }
	
	private int[] tablaCaracter(char[] patron) 
    {
      final int ALPHABET_SIZE = 256;
      int[] t = new int[ALPHABET_SIZE];
      for (int i = 0; i < t.length; ++i) 
             t[i] = patron.length;
      for (int i = 0; i < patron.length - 1; ++i) 
             t[patron[i]] = patron.length - 1 - i;
      return t;
    }
	private static int[] tablaDesplazamiento(char[] patron) 
    {
      int[] tabla = new int[patron.length];
      int ultimaPosPrefijo = patron.length;
      for (int i = patron.length - 1; i >= 0; --i) 
      {
          if (esPrefijo(patron, i + 1)) 
                 ultimaPosPrefijo = i + 1;
            tabla[patron.length - 1 - i] = ultimaPosPrefijo - i + patron.length - 1;
      }
      for (int i = 0; i < patron.length - 1; ++i) 
      {
            int slen = tamanoSufijo(patron, i);
            tabla[slen] = patron.length - 1 - i + slen;
      }
      return tabla;
  }
	
	private static boolean esPrefijo(char[] patron, int p) 
    {
        for (int i = p, j = 0; i < patron.length; ++i, ++j) 
            if (patron[i] != patron[j]) 
                  return false;
        return true;
    }
	private static int tamanoSufijo(char[] patron, int p) 
    {
        int len = 0;
        for (int i = p, j = patron.length - 1; i >= 0 && patron[i] == patron[j]; --i, --j) 
               len += 1;
        return len;
    }
	
	
}
