package yarovi.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yarovi.DAO.ClienteDAO;
import yarovi.entidad.Cliente;

@Service
public class ClienteService {

	@Autowired
	private ClienteDAO clienteDAO;

	public Iterable<Cliente> obtenerTodoElemento() 
	{
		return clienteDAO.obtenerTodoElemento();
	}
	public Cliente insertNuevoElementoFinal(Cliente a) {
		return clienteDAO.insertNuevoElementoFinal(a);
	}

	public boolean eliminarElemento(Cliente a) {
		return clienteDAO.eliminarElemento(a);
	}

	public boolean editarReferenciaElemento(Cliente a) {
		return clienteDAO.editarReferenciaElemento(a);
	}
	public Cliente buscarElemento(int id) {
		return clienteDAO.buscarElemento(id);
	}
}
