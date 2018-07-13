package yarovi.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yarovi.DAO.ClienteDAO;
import yarovi.DAO.DetallePedidoDAO;
import yarovi.entidad.DetallePedido;
import yarovi.utilidad.ListaEnlazada;


@Service
public class DetallePedidoService {

	
	@Autowired
	private DetallePedidoDAO detallePedidoDAO;

	public Iterable<DetallePedido> obtenerTodoElemento() 
	{
		return detallePedidoDAO.obtenerTodoElemento();
	}
	public DetallePedido insertNuevoElementoFinal(DetallePedido a) {
		return detallePedidoDAO.insertNuevoElementoFinal(a);
	}

	public boolean eliminarElemento(DetallePedido a) {
		return detallePedidoDAO.eliminarElemento(a);
	}

	public boolean editarReferenciaElemento(DetallePedido a) {
		return detallePedidoDAO.editarReferenciaElemento(a);
	}
	public DetallePedido buscarElemento(int id) {
		return detallePedidoDAO.buscarElemento(id);
	}
	
	public String CalculoTotalaPagar() {
		return detallePedidoDAO.CalculoTotalaPagar();
	}
	public int CalculoCantidadTotal() {
		return detallePedidoDAO.CalculoCantidadTotal();
	}
	public void vaciarLista() {
		detallePedidoDAO.vaciarLista();
	}
	public ListaEnlazada<DetallePedido> retornarListaDetalle() {
		return detallePedidoDAO.retornarListaDetalle();
	}
}
