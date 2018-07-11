package yarovi.entidad;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Empleado {

	private int EmpleadoId;
	private String EmpleadoDocumento;
	@Size(min=8,max=10)
	private  String EmpleadoNroDocumento;
	@NotNull
	@Size(min=3,max=20)
	private String EmpleadoNombre;
	@NotNull
	@Size(min=3,max=20)
	private String EmpleadoApellido;
	public int getEmpleadoId() {
		return EmpleadoId;
	}
	public void setEmpleadoId(int empleadoId) {
		EmpleadoId = empleadoId;
	}
	public String getEmpleadoDocumento() {
		return EmpleadoDocumento;
	}
	public void setEmpleadoDocumento(String empleadoDocumento) {
		EmpleadoDocumento = empleadoDocumento;
	}
	public String getEmpleadoNroDocumento() {
		return EmpleadoNroDocumento;
	}
	public void setEmpleadoNroDocumento(String empleadoNroDocumento) {
		EmpleadoNroDocumento = empleadoNroDocumento;
	}
	public String getEmpleadoNombre() {
		return EmpleadoNombre;
	}
	public void setEmpleadoNombre(String empleadoNombre) {
		EmpleadoNombre = empleadoNombre;
	}
	public String getEmpleadoApellido() {
		return EmpleadoApellido;
	}
	public void setEmpleadoApellido(String eMpleadoApellido) {
		EmpleadoApellido = eMpleadoApellido;
	}
	public Empleado(int empleadoId, String empleadoDocumento, String empleadoNroDocumento, String empleadoNombre,
			String empleadoApellido) {
		super();
		EmpleadoId = empleadoId;
		EmpleadoDocumento = empleadoDocumento;
		EmpleadoNroDocumento = empleadoNroDocumento;
		EmpleadoNombre = empleadoNombre;
		EmpleadoApellido = empleadoApellido;
	}
	public Empleado() {
		//super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Empleado [EmpleadoId=" + EmpleadoId + ", EmpleadoDocumento=" + EmpleadoDocumento
				+ ", EmpleadoNroDocumento=" + EmpleadoNroDocumento + ", EmpleadoNombre=" + EmpleadoNombre
				+ ", EMpleadoApellido=" + EmpleadoApellido + "]";
	}
	
	
}
