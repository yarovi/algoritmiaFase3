package yarovi.DAO;
import org.springframework.stereotype.Repository;
import yarovi.entidad.Almacen;
import yarovi.utilidad.ListaEnlazada;

@Repository
public class AlmacenDAO {

	ListaEnlazada<Almacen> listaEnlazadaSimple = new ListaEnlazada<Almacen>();
	
	public Iterable<Almacen> obtenerTodoElemento()
	{
		return listaEnlazadaSimple;
	}	
	public Almacen insertNuevoElementoFinal(Almacen entidad)
	{
		entidad.setAlmacenId(listaEnlazadaSimple.size()+1);
		listaEnlazadaSimple.addLast(entidad);
		return entidad;
	}

	public int tamanio()
	{return listaEnlazadaSimple.size();}
	
	public boolean eliminarElemento(Almacen entidad)
	{
		int posicion=listaEnlazadaSimple.indexOf(entidad);
		Almacen tmp= listaEnlazadaSimple.remove(posicion);
		if(tmp !=null)
			return true;
		return false;
	}
	public boolean editarReferenciaElemento(Almacen entidad) {
//		int posicion=listaEnlazadaSimple.indexOf(a);
		
		Almacen tmp= listaEnlazadaSimple.modify(entidad, entidad.getAlmacenId()-1);
		if(tmp !=null)
			return true;
		return false;
		
	}
	public Almacen buscarElemento(int id)
	{
		Almacen a= listaEnlazadaSimple.get(id-1);
		return a;
	}
	
	
	
}
