package yarovi.entidad;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import yarovi.utilidad.ListaEnlazada;

public class Pedido {

		private int PedidoId;
		public int getPedidoId() {
			return PedidoId;
		}
		public void setPedidoId(int pedidoId) {
			PedidoId = pedidoId;
		}
		private String PedidoCorrelativo;
		private String PedidoFecha;
		private String PedidoDireccionEntrega;
		private Integer PedidoNroDiasRetraspo;
		private Double PedidoPorcentajeDescuento;
		private Double PedidoTotal;
		private String PedidoNombreCliente;
		private String PedidoRucCliente;
		private String PedidoNombreVendedor;
		private String PedidoTipoCliente;
		private int PedidoCantidad;		
		
				
		public Double getPedidoTotal() {
			return PedidoTotal;
		}
		public String getPedidoTipoCliente() {
			return PedidoTipoCliente;
		}
		public void setPedidoTipoCliente(String pedidoTipoCliente) {
			PedidoTipoCliente = pedidoTipoCliente;
		}
		public void setPedidoTotal(Double pedidoTotal) {
			PedidoTotal = pedidoTotal;
		}
		public String getPedidoNombreCliente() {
			return PedidoNombreCliente;
		}
		public void setPedidoNombreCliente(String pedidoNombreCliente) {
			PedidoNombreCliente = pedidoNombreCliente;
		}
		public String getPedidoRucCliente() {
			return PedidoRucCliente;
		}
		public void setPedidoRucCliente(String pedidoRucCliente) {
			PedidoRucCliente = pedidoRucCliente;
		}
		public String getPedidoNombreVendedor() {
			return PedidoNombreVendedor;
		}
		public void setPedidoNombreVendedor(String pedidoNombreVendedor) {
			PedidoNombreVendedor = pedidoNombreVendedor;
		}
		public int getPedidoCantidad() {
			return PedidoCantidad;
		}
		public void setPedidoCantidad(int pedidoCantidad) {
			PedidoCantidad = pedidoCantidad;
		}
		
		private String PedidoEstado;
		public String getPedidoEstado() {
			return PedidoEstado;
		}
		public void setPedidoEstado(String pedidoEstado) {
			PedidoEstado = pedidoEstado;
		}
		private ListaEnlazada<DetallePedido> detallePedido=new ListaEnlazada<>();

		
		public ListaEnlazada<DetallePedido> getDetallePedido() {
			return detallePedido;
		}
		public void setDetallePedido(ListaEnlazada<DetallePedido> detallePedido) {
			this.detallePedido = detallePedido;
		}
		public Pedido() {
			 
		}
		public String getPedidoCorrelativo() {
			return PedidoCorrelativo;
		}
		public void setPedidoCorrelativo(String pedidoCorrelativo) {
			PedidoCorrelativo = pedidoCorrelativo;
		}
		public String getPedidoFecha() {
			return PedidoFecha;
		}
		public void setPedidoFecha(String pedidoFecha) {
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
		@Override
		public String toString() {
			return "Pedido [PedidoId=" + PedidoId + ", PedidoCorrelativo=" + PedidoCorrelativo + ", PedidoFecha="
					+ PedidoFecha + ", PedidoDireccionEntrega=" + PedidoDireccionEntrega + ", PedidoNroDiasRetraspo="
					+ PedidoNroDiasRetraspo + ", PedidoPorcentajeDescuento=" + PedidoPorcentajeDescuento
					+ ", PedidoTotal=" + PedidoTotal + ", PedidoNombreCliente=" + PedidoNombreCliente
					+ ", PedidoRucCliente=" + PedidoRucCliente + ", PedidoNombreVendedor=" + PedidoNombreVendedor
					+ ", PedidoTipoCliente=" + PedidoTipoCliente + ", PedidoCantidad=" + PedidoCantidad
					+ ", PedidoEstado=" + PedidoEstado + ", detallePedido=" + detallePedido + "]";
		}
		
		

		
		
		
}
