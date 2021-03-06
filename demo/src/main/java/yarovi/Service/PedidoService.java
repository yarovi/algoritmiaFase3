package yarovi.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yarovi.DAO.PedidoDAO;
import yarovi.entidad.Pedido;
import yarovi.utilidad.ListaEnlazada;

@Service
public class PedidoService {

	@Autowired
	private PedidoDAO pedidoDAO;

	public Iterable<Pedido> obtenerTodoElemento() 
	{
		return pedidoDAO.obtenerTodoElemento();
	}
	public Pedido insertNuevoElementoFinal(Pedido a) {
		return pedidoDAO.insertNuevoElementoFinal(a);
	}

	public boolean eliminarElemento(Pedido a) {
		return pedidoDAO.eliminarElemento(a);
	}

	public boolean editarReferenciaElemento(Pedido a) {
		return pedidoDAO.editarReferenciaElemento(a);
	}
	public Pedido buscarElemento(int id) {
		return pedidoDAO.buscarElemento(id);
	}
	public int tamanio() {
		return pedidoDAO.tamanio();
	}
//	public ListaEnlazada<Pedido> retornarListaPedido() {
//		return pedidoDAO.retornarListaPedido();
//	}
	public ListaEnlazada<Pedido> getListaEnlazadaSimple() {
		return pedidoDAO.getListaEnlazadaSimple();
	}

	public void setListaEnlazadaSimple(ListaEnlazada<Pedido> listaEnlazadaSimple) {
		pedidoDAO.setListaEnlazadaSimple(listaEnlazadaSimple);
	}
}
