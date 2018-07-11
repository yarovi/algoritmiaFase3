package yarovi.DAO;

import org.springframework.stereotype.Repository;

import yarovi.entidad.Empleado;
import yarovi.utilidad.ListaEnlazada;

@Repository
public class EmpleadoDAO {

ListaEnlazada<Empleado> listaEnlazadaSimple = new ListaEnlazada<>();
	
	public Iterable<Empleado> obtenerTodoElemento()
	{
		return listaEnlazadaSimple;
	}	
	public Empleado insertNuevoElementoFinal(Empleado entidad)
	{
		entidad.setEmpleadoId(listaEnlazadaSimple.size()+1);
		listaEnlazadaSimple.addLast(entidad);
		return entidad;
	}

	public int tamanio()
	{return listaEnlazadaSimple.size();}
	
	public boolean eliminarElemento(Empleado entidad)
	{
		int posicion=listaEnlazadaSimple.indexOf(entidad);
		Empleado tmp= listaEnlazadaSimple.remove(posicion);
		if(tmp !=null)
			return true;
		return false;
	}
	public boolean editarReferenciaElemento(Empleado entidad) {
		Empleado tmp= listaEnlazadaSimple.modify(entidad, entidad.getEmpleadoId()-1);
		if(tmp !=null)
			return true;
		return false;
		
	}
	public Empleado buscarElemento(int id)
	{
		Empleado a= listaEnlazadaSimple.get(id-1);
		return a;
	}
}
