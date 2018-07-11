package yarovi.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yarovi.DAO.ProductoDAO;
import yarovi.entidad.Producto;

@Service
public class ProductoService {

	@Autowired
	private ProductoDAO productoDAO;
	
	public Iterable<Producto> obtenerTodoElemento()
	{
		return productoDAO.obtenerTodoElemento();
	}	
	public Producto insertNuevoElementoFinal(Producto entidad){

		return productoDAO.insertNuevoElementoFinal(entidad);
	}

//	public int tamanio()
//	{return listaProducto.size();}
	
	public boolean eliminarElemento(Producto entidad)
	{
		return productoDAO.eliminarElemento(entidad);
	}
	public boolean editarReferenciaElemento(Producto entidad) {

		return productoDAO.editarReferenciaElemento(entidad);
		
	}
	public Producto buscarElemento(int id)
	{
		Producto entidad= productoDAO.buscarElemento(id-1);
		return entidad;
	}
	
	//metodos adicionales
	public Producto buscarxIdProductoyAlmacen(String codigoAlmacen, String codigoProducto) {
		return productoDAO.buscarxIdProductoyAlmacen(codigoAlmacen,codigoProducto);
	}
}
