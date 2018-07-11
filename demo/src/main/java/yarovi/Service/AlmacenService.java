package yarovi.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yarovi.DAO.AlmacenDAO;
import yarovi.entidad.Almacen;


@Service
public class AlmacenService {

	@Autowired
	private AlmacenDAO almacenDAO;

	public Iterable<Almacen> obtenerTodoElemento() 
	{
		return almacenDAO.obtenerTodoElemento();
	}
	public Almacen insertNuevoElementoFinal(Almacen a) {
		return almacenDAO.insertNuevoElementoFinal(a);
	}

	public boolean eliminarElemento(Almacen a) {
		return almacenDAO.eliminarElemento(a);
	}

	public boolean editarReferenciaElemento(Almacen a) {
		return almacenDAO.editarReferenciaElemento(a);
	}
	public Almacen buscarElemento(int id) {
		return almacenDAO.buscarElemento(id);
	}
}
