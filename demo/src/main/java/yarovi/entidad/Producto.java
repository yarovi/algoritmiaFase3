package yarovi.entidad;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Producto {

	
	private int ProductoId;
	@NotNull
	@Size(min=3,max=20)
	private String ProductoDescripcion;
	@NotNull
	@Min(1)
    @Max(100)
	private int ProductoCantidad;
	@DecimalMin("1.0")
    @DecimalMax("100.00")
	private Double ProductoPrecioUnitario;
	@DecimalMin("1.0")
    @DecimalMax("100.00")
	private Double ProductoPrecioTotal;
	@NotNull	
	private int AlmacenId;
	private boolean ProductoEstado;
	private Double ProductoPrecioVenta;
	public Double getProductoPrecioVenta() {
		return ProductoPrecioVenta;
	}
	public void setProductoPrecioVenta(Double productoPrecioVenta) {
		ProductoPrecioVenta = productoPrecioVenta;
	}
	public boolean isProductoEstado() {
		return ProductoEstado;
	}
	public void setProductoEstado(boolean productoEstado) {
		ProductoEstado = productoEstado;
	}
	public int getAlmacenId() {
		return AlmacenId;
	}
	public void setAlmacenId(int almacenId) {
		AlmacenId = almacenId;
	}
	public int getProductoId() {
		return ProductoId;
	}
	public void setProductoId(int productoId) {
		ProductoId = productoId;
	}
	public String getProductoDescripcion() {
		return ProductoDescripcion;
	}
	public void setProductoDescripcion(String prodcutoDescripcion) {
		ProductoDescripcion = prodcutoDescripcion;
	}
	public int getProductoCantidad() {
		return ProductoCantidad;
	}
	public void setProductoCantidad(int productoCantidad) {
		ProductoCantidad = productoCantidad;
	}
	public Double getProductoPrecioUnitario() {
		return ProductoPrecioUnitario;
	}
	public void setProductoPrecioUnitario(Double productoPrecioUnitario) {
		ProductoPrecioUnitario = productoPrecioUnitario;
	}
	public Double getProductoPrecioTotal() {
		return ProductoPrecioTotal;
	}
	public void setProductoPrecioTotal(Double productoPrecioTotal) {
		ProductoPrecioTotal = productoPrecioTotal;
	}
	
	
	
	public Producto(int productoId, String prodcutoDescripcion, int productoCantidad, Double productoPrecioUnitario,
			Double productoPrecioTotal, int almacenId) {
		super();
		ProductoId = productoId;
		ProductoDescripcion = prodcutoDescripcion;
		ProductoCantidad = productoCantidad;
		ProductoPrecioUnitario = productoPrecioUnitario;
		ProductoPrecioTotal = productoPrecioTotal;
		AlmacenId = almacenId;
	}
	public Producto() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Producto [ProductoId=" + ProductoId + ", ProdcutoDescripcion=" + ProductoDescripcion
				+ ", ProductoCantidad=" + ProductoCantidad + ", ProductoPrecioUnitario=" + ProductoPrecioUnitario
				+ ", ProductoPrecioTotal=" + ProductoPrecioTotal + ", AlmacenId=" + AlmacenId + "]";
	}
	
	
	
}
