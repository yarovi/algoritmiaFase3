package yarovi.entidad;

import java.util.ArrayList;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Almacen {

	private int AlmacenId;
	@NotNull
	@Size(min=3,max=20)
	private String AlmacenNombre;
	private boolean AlmacenEstado;
	
	
	
	public int getAlmacenId() {
		return AlmacenId;
	}
	public void setAlmacenId(int almacenId) {
		AlmacenId = almacenId;
	}
	public String getAlmacenNombre() {
		return AlmacenNombre;
	}
	public void setAlmacenNombre(String almacenNombre) {
		AlmacenNombre = almacenNombre;
	}
	public boolean isAlmacenEstado() {
		return AlmacenEstado;
	}
	public void setAlmacenEstado(boolean almacenEstado) {
		AlmacenEstado = almacenEstado;
	}
	
	
	public Almacen(int almacenId, String almacenNombre, boolean almacenEstado) {
		super();
		AlmacenId = almacenId;
		AlmacenNombre = almacenNombre;
		AlmacenEstado = almacenEstado;
	}
	
	public Almacen() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Almacen [AlmacenId=" + AlmacenId + ", AlmacenNombre=" + AlmacenNombre + ", AlmacenEstado="
				+ AlmacenEstado + "]";
	}

	
	
}
