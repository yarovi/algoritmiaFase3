package yarovi.entidad;
import yarovi.utilidad.ListaEnlazada;

public class DetallePedido {

	int codigoDetallePedido;
	private Producto producto;
	private int cantidadPedido;
	private double precioTotal;
	private double precioVenta;
	public double getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}
	public double getPrecioTotal() {
		return precioTotal;
	}
	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}
	public int getCantidadPedido() {
		return cantidadPedido;
	}
	public void setCantidadPedido(int cantidadPedido) {
		this.cantidadPedido = cantidadPedido;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	ListaEnlazada<Codigo> lstCodigo=new ListaEnlazada<>();
	public int getCodigoDetallePedido() {
		return codigoDetallePedido;
	}
	public void setCodigoDetallePedido(int codigoDetallePedido) {
		this.codigoDetallePedido = codigoDetallePedido;
	}
	public ListaEnlazada<Codigo> getLstCodigo() {
		return lstCodigo;
	}
	public void setLstCodigo(ListaEnlazada<Codigo> lstCodigo) {
		this.lstCodigo = lstCodigo;
	}
//	@Override
//	public String toString() {
//		return "DetallePedido [codigoDetallePedido=" + codigoDetallePedido + ", producto=" + producto
//				+ ", cantidadPedido=" + cantidadPedido + ", precioTotal=" + precioTotal + ", precioVenta=" + precioVenta
//				+ ", lstCodigo=" + lstCodigo + "]";
//	}
	@Override
	public String toString() {
		return "DetallePedido [codigoDetallePedido=" + codigoDetallePedido + ", producto=" + producto
				+ ", cantidadPedido=" + cantidadPedido + ", precioTotal=" + precioTotal + ", precioVenta=" + precioVenta
				+ ", lstCodigo=" + lstCodigo + "]";
	}
	
	
	
}
