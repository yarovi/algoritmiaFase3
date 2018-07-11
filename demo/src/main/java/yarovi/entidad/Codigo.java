package yarovi.entidad;

public class Codigo {

	int codigoAlmacen;
	int codigoProducto;
	String nombreProducto;
	int cantidad;
//	int cantidadP;
	public int getCodigoAlmacen() {
		return codigoAlmacen;
	}
	public void setCodigoAlmacen(int codigoAlmacen) {
		this.codigoAlmacen = codigoAlmacen;
	}
	public int getCodigoProducto() {
		return codigoProducto;
	}
	public void setCodigoProducto(int codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidadI) {
		this.cantidad = cantidadI;
	}
	@Override
	public String toString() {
		return "Codigo [codigoAlmacen=" + codigoAlmacen + ", codigoProducto=" + codigoProducto + ", nombreProducto="
				+ nombreProducto + ", cantidad=" + cantidad + "]";
	}
	
	
	
	
}
