package yarovi.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yarovi.DAO.EmpleadoDAO;
import yarovi.entidad.Empleado;

@Service
public class EmpleadoService {

	@Autowired
	private EmpleadoDAO empleadoDAO;

	public Iterable<Empleado> obtenerTodoElemento() 
	{
		return empleadoDAO.obtenerTodoElemento();
	}
	public Empleado insertNuevoElementoFinal(Empleado a) {
		return empleadoDAO.insertNuevoElementoFinal(a);
	}

	public boolean eliminarElemento(Empleado a) {
		return empleadoDAO.eliminarElemento(a);
	}

	public boolean editarReferenciaElemento(Empleado a) {
		return empleadoDAO.editarReferenciaElemento(a);
	}
	public Empleado buscarElemento(int id) {
		return empleadoDAO.buscarElemento(id);
	}
}
