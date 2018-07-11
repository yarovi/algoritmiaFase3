package yarovi.DAO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;


import yarovi.entidad.Producto;
import yarovi.utilidad.ListaEnlazada;

@Repository
public class ProductoDAO {
	private static final Log LOG = LogFactory.getLog(ProductoDAO.class);
	ListaEnlazada<Producto> listaProducto= new ListaEnlazada<Producto>();
	
	public Iterable<Producto> obtenerTodoElemento()
	{
		return listaProducto;
	}	
	public Producto insertNuevoElementoFinal(Producto entidad)
	{
		entidad.setProductoId(listaProducto.size()+1);
		listaProducto.addLast(entidad);
		return entidad;
	}

	public int tamanio()
	{return listaProducto.size();}
	
	public boolean eliminarElemento(Producto entidad)
	{
		int posicion=listaProducto.indexOf(entidad);
		Producto tmp= listaProducto.remove(posicion);
		if(tmp !=null)
			return true;
		return false;
	}
	public boolean editarReferenciaElemento(Producto entidad) {
	
		Producto tmp= listaProducto.modify(entidad, entidad.getProductoId()-1);
		if(tmp !=null)
			return true;
		return false;
		
	}
	public Producto buscarElemento(int id)
	{
		Producto entidad= listaProducto.get(id-1);
		return entidad;
	}
	
	public Producto buscarxIdProductoyAlmacen(String codigoAlmacen, String codigoProducto) {
		Producto c = new Producto();
		for (Producto c2 : obtenerTodoElemento()) {
			LOG.info("IDAlmacen es :" + c2.getAlmacenId() + " =" + codigoAlmacen + "IDProducto es :"
					+ c2.getProductoId());
			if (c2.getAlmacenId() == Integer.parseInt(codigoAlmacen)
					&& c2.getProductoId() == Integer.parseInt(codigoProducto)) {
				c = c2;
				break;
			}
		}
		LOG.info("Elegido Producto :" + c);
		return c;
	}
}
