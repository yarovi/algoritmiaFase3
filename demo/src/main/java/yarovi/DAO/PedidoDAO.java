package yarovi.DAO;

import org.springframework.stereotype.Repository;

import yarovi.entidad.Pedido;
import yarovi.utilidad.ListaEnlazada;

@Repository
public class PedidoDAO {

ListaEnlazada<Pedido> listaEnlazadaSimple = new ListaEnlazada<Pedido>();
	
	public Iterable<Pedido> obtenerTodoElemento()
	{
		return listaEnlazadaSimple;
	}	
	public Pedido insertNuevoElementoFinal(Pedido entidad)
	{
		entidad.setPedidoId(listaEnlazadaSimple.size()+1);
		listaEnlazadaSimple.addLast(entidad);
		return entidad;
	}

	public int tamanio()
	{return listaEnlazadaSimple.size();}
	
	public boolean eliminarElemento(Pedido entidad)
	{
		int posicion=listaEnlazadaSimple.indexOf(entidad);
		Pedido tmp= listaEnlazadaSimple.remove(posicion);
		if(tmp !=null)
			return true;
		return false;
	}
	public boolean editarReferenciaElemento(Pedido entidad) {
	
		Pedido tmp= listaEnlazadaSimple.modify(entidad, entidad.getPedidoId()-1);
		if(tmp !=null)
			return true;
		return false;
		
	}
	public Pedido buscarElemento(int id)
	{
		Pedido a= listaEnlazadaSimple.get(id-1);
		return a;
	}
	
}
