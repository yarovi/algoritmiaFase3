package yarovi.DAO;

import org.springframework.stereotype.Repository;
import yarovi.entidad.Cliente;
import yarovi.utilidad.ListaEnlazada;

@Repository
public class ClienteDAO {
	
	ListaEnlazada<Cliente> listaEnlazadaSimple = new ListaEnlazada<Cliente>();
	
	public Iterable<Cliente> obtenerTodoElemento()
	{
		return listaEnlazadaSimple;
	}	
	public Cliente insertNuevoElementoFinal(Cliente entidad)
	{
		entidad.setClienteId(listaEnlazadaSimple.size()+1);
		listaEnlazadaSimple.addLast(entidad);
		return entidad;
	}

	public int tamanio()
	{return listaEnlazadaSimple.size();}
	
	public boolean eliminarElemento(Cliente entidad)
	{
		int posicion=listaEnlazadaSimple.indexOf(entidad);
		Cliente tmp= listaEnlazadaSimple.remove(posicion);
		if(tmp !=null)
			return true;
		return false;
	}
	public boolean editarReferenciaElemento(Cliente entidad) {

		Cliente tmp= listaEnlazadaSimple.modify(entidad, entidad.getClienteId()-1);
		if(tmp !=null)
			return true;
		return false;
		
	}
	public Cliente buscarElemento(int id)
	{
		Cliente a= listaEnlazadaSimple.get(id-1);
		return a;
	}
		
		
}

