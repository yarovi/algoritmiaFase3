package yarovi.DAO;

import static org.mockito.Matchers.anyInt;

import java.text.DecimalFormat;

import org.springframework.stereotype.Repository;

import yarovi.entidad.Codigo;
import yarovi.entidad.DetallePedido;
import yarovi.entidad.Producto;
import yarovi.utilidad.ListaEnlazada;

@Repository
public class DetallePedidoDAO {
	ListaEnlazada<DetallePedido> listaPedido = new ListaEnlazada<>();
	ListaEnlazada<Codigo> lsttemporal;
	DetallePedido nombrePedido;

	public ListaEnlazada<DetallePedido> retornarListaDetalle(){
		return listaPedido;
	}
	public void vaciarLista() {
		listaPedido = new ListaEnlazada<>();
	}
	public Iterable<DetallePedido> obtenerTodoElemento() {
		return listaPedido;
	}

	public Iterable<Codigo> obtenerTodoElementoDetalle() {
		return lsttemporal;
	}

	public DetallePedido insertNuevoElementoFinal(DetallePedido entidad) {
		boolean estadomodificacion = false;
		// buscamos si existe el item
		if (listaPedido.size() != 0) {

			if (existeMismoProducto(entidad)) {

				int posicion = listaPedido.indexOfProducto(entidad);
				if (posicion >= 0) {
					DetallePedido pdt = listaPedido.get(posicion);
					entidad = actualizarDatoPedido(entidad, pdt,false);
					listaPedido.modify(entidad, posicion);
					estadomodificacion = true;
				}
			} else {

				if (existeProductoOtroAlmacen(entidad)) {

					if (nombrePedido != null) {
						int posicion = listaPedido.indexOfProducto(nombrePedido);
						if (posicion > 0) {
							DetallePedido pdt = listaPedido.get(posicion);
							entidad = actualizarDatoPedido(entidad, pdt,true);
							listaPedido.modify(entidad, posicion);
							estadomodificacion = true;
						}
					}

				} else {
					preparandoElementoyAgregar(entidad);
				}
			}
		} else {
			/*
			 * sea grega por primera vez
			 * */
			preparandoElementoyAgregar(entidad);
		}
		return entidad;
	}

	public int tamanio() {
		return listaPedido.size();
	}

	public boolean eliminarElemento(DetallePedido entidad) {
		int posicion = listaPedido.indexOf(entidad);
		DetallePedido tmp = listaPedido.remove(posicion);
		if (tmp != null)
			return true;
		return false;
	}

	public boolean editarReferenciaElemento(DetallePedido entidad) {

		DetallePedido tmp = listaPedido.modify(entidad, entidad.getCodigoDetallePedido() - 1);
		if (tmp != null)
			return true;
		return false;

	}

	public DetallePedido buscarElemento(int id) {
		DetallePedido entidad = listaPedido.get(id - 1);
		return entidad;
	}

	private Codigo agregarCodigoReferencial(DetallePedido entidad) {
		Codigo codigoTemporal = new Codigo();
		codigoTemporal.setCodigoAlmacen(entidad.getProducto().getAlmacenId());
		codigoTemporal.setCodigoProducto(entidad.getProducto().getProductoId());
		codigoTemporal.setNombreProducto(entidad.getProducto().getProductoDescripcion());
		codigoTemporal.setCantidad(entidad.getCantidadPedido());
		return codigoTemporal;
	}

	private DetallePedido actualizarDatoPedido(DetallePedido nuevoEntidad, DetallePedido antPedido, boolean estado) {
		/*
		 * calculamos los datos para el nuevo pedido
		 *
		 */
		nuevoEntidad.setPrecioTotal(nuevoEntidad.getProducto().getProductoPrecioVenta() * nuevoEntidad.getCantidadPedido());
		nuevoEntidad.setPrecioVenta(nuevoEntidad.getProducto().getProductoPrecioVenta() * nuevoEntidad.getCantidadPedido());
		/*
		 * actualizamos los datos con el pedido anterior
		 */
		nuevoEntidad.setCodigoDetallePedido(antPedido.getCodigoDetallePedido());
		nuevoEntidad.setCantidadPedido(nuevoEntidad.getCantidadPedido() + antPedido.getCantidadPedido());
		nuevoEntidad.setPrecioTotal(nuevoEntidad.getPrecioTotal() + antPedido.getPrecioTotal());
		nuevoEntidad.setPrecioVenta(nuevoEntidad.getPrecioVenta() + antPedido.getPrecioVenta());
		/* fin de la actualizacion */

		/*
		 * actualizamos el codigo de referencia en base al antPedido
		 */
		ListaEnlazada<Codigo> lstCodigoLocal = antPedido.getLstCodigo();
		if (estado) {
			Codigo elemento=agregarCodigoReferencial(nuevoEntidad);
			lstCodigoLocal.addFirst(elemento);
		} else {
			for (Codigo cod : lstCodigoLocal) {
				if (cod.getCodigoAlmacen() == nuevoEntidad.getProducto().getAlmacenId()
						&& cod.getCodigoProducto() == nuevoEntidad.getProducto().getProductoId()) {
					cod.setCantidad(nuevoEntidad.getCantidadPedido());
					break;
				}
			}
		}
		nuevoEntidad.setLstCodigo(lstCodigoLocal);
		return nuevoEntidad;
	}

	/*
	 * retorna true si el producto existen en el mismo alamcen
	 */
	private boolean existeMismoProducto(DetallePedido entidad) {
		boolean estado = false;
		for (DetallePedido d : obtenerTodoElemento()) {
			for(Codigo c:d.getLstCodigo()) {
				if (c.getCodigoProducto()==entidad.getProducto().getProductoId()
						&&
						c.getNombreProducto().equals(entidad.getProducto().getProductoDescripcion())
						&&
						c.getCodigoAlmacen()==entidad.getProducto().getAlmacenId()) {
					estado = true;
					break;
				}
			}
			Producto p = d.getProducto();
			if (p.equals(entidad.getProducto())) {
				estado = true;
				break;
			}
		}
		return estado;
	}

	/* retorna true si el producto existe en otro almacen */
	private boolean existeProductoOtroAlmacen(DetallePedido entidad) {
		boolean estado = false;
		nombrePedido = null;
		for (DetallePedido d : obtenerTodoElemento()) {
			Producto p = d.getProducto();
			if (p.getProductoDescripcion().equals(entidad.getProducto().getProductoDescripcion())) {
				nombrePedido = d;
				estado = true;
				break;
			}
		}
		return estado;
	}

	/*
	 * agregamos un nuevo pedido
	 * */
	private void preparandoElementoyAgregar(DetallePedido entidad) {
		ListaEnlazada<Codigo> lsttemporal = new ListaEnlazada<>();
		lsttemporal.addFirst(agregarCodigoReferencial(entidad));
		entidad.setLstCodigo(lsttemporal);
		entidad.setCodigoDetallePedido(listaPedido.size() + 1);
		entidad.setPrecioTotal(entidad.getCantidadPedido() * entidad.getProducto().getProductoPrecioVenta());
		entidad.setPrecioVenta(entidad.getCantidadPedido() * entidad.getProducto().getProductoPrecioVenta());
		
		listaPedido.addLast(entidad);
	}

	public String CalculoTotalaPagar() {

		double respuesta = 0.0;
		if (listaPedido.size() == 0) {
			return "0.00";
		} else {

			for (DetallePedido dp : obtenerTodoElemento()) {
				respuesta = respuesta + dp.getPrecioTotal();
			}
		}
		String rpta = String.format("%.2f", respuesta);

		return rpta;
	}
	
	public int CalculoCantidadTotal() {

		int respuesta = 0;
		if (listaPedido.size() == 0) {
			return 0;
		} else {

			for (DetallePedido dp : obtenerTodoElemento()) {
				respuesta = respuesta + dp.getCantidadPedido();
			}
		}
		
		return respuesta;
	}

}
