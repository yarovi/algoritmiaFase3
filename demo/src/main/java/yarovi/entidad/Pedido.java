package yarovi.entidad;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido {

		private int PedidoId;
		public int getPedidoId() {
			return PedidoId;
		}
		public void setPedidoId(int pedidoId) {
			PedidoId = pedidoId;
		}
		private String PedidoCorrelativo;
		private Date PedidoFecha;
		private String PedidoDireccionEntrega;
		private Integer PedidoNroDiasRetraspo;
		private Double PedidoPorcentajeDescuento;
		private Empleado empleado;
		private Cliente cliente; 
		public Cliente getCliente() {
			return cliente;
		}
		public void setCliente(Cliente cliente) {
			this.cliente = cliente;
		}
		private String PedidoEstado;
		public String getPedidoEstado() {
			return PedidoEstado;
		}
		public void setPedidoEstado(String pedidoEstado) {
			PedidoEstado = pedidoEstado;
		}
		private List<Producto> productos =new ArrayList<Producto>();
		public Pedido(String pedidoCorrelativo, Date pedidoFecha, String pedidoDireccionEntrega,
				Integer pedidoNroDiasRetraspo, Double pedidoPorcentajeDescuento, Empleado empleado,
				List<Producto> productos) {
			super();
			PedidoCorrelativo = pedidoCorrelativo;
			PedidoFecha = pedidoFecha;
			PedidoDireccionEntrega = pedidoDireccionEntrega;
			PedidoNroDiasRetraspo = pedidoNroDiasRetraspo;
			PedidoPorcentajeDescuento = pedidoPorcentajeDescuento;
			this.empleado = empleado;
			this.productos = productos;
		}
		public Pedido() {
			super();
			// TODO Auto-generated constructor stub
		}
		public String getPedidoCorrelativo() {
			return PedidoCorrelativo;
		}
		public void setPedidoCorrelativo(String pedidoCorrelativo) {
			PedidoCorrelativo = pedidoCorrelativo;
		}
		public Date getPedidoFecha() {
			return PedidoFecha;
		}
		public void setPedidoFecha(Date pedidoFecha) {
			PedidoFecha = pedidoFecha;
		}
		public String getPedidoDireccionEntrega() {
			return PedidoDireccionEntrega;
		}
		public void setPedidoDireccionEntrega(String pedidoDireccionEntrega) {
			PedidoDireccionEntrega = pedidoDireccionEntrega;
		}
		public Integer getPedidoNroDiasRetraspo() {
			return PedidoNroDiasRetraspo;
		}
		public void setPedidoNroDiasRetraspo(Integer pedidoNroDiasRetraspo) {
			PedidoNroDiasRetraspo = pedidoNroDiasRetraspo;
		}
		public Double getPedidoPorcentajeDescuento() {
			return PedidoPorcentajeDescuento;
		}
		public void setPedidoPorcentajeDescuento(Double pedidoPorcentajeDescuento) {
			PedidoPorcentajeDescuento = pedidoPorcentajeDescuento;
		}
		public Empleado getEmpleado() {
			return empleado;
		}
		public void setEmpleado(Empleado empleado) {
			this.empleado = empleado;
		}
		public List<Producto> getProductos() {
			return productos;
		}
		public void setProductos(List<Producto> productos) {
			this.productos = productos;
		}
		@Override
		public String toString() {
			return "Pedido [PedidoCorrelativo=" + PedidoCorrelativo + ", PedidoFecha=" + PedidoFecha
					+ ", PedidoDireccionEntrega=" + PedidoDireccionEntrega + ", PedidoNroDiasRetraspo="
					+ PedidoNroDiasRetraspo + ", PedidoPorcentajeDescuento=" + PedidoPorcentajeDescuento + ", empleado="
					+ empleado + ", productos=" + productos + "]";
		}
		
		
}
