package yarovi.entidad;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Cliente {

	private int ClienteId;
	public int getClienteId() {
		return ClienteId;
	}
	public void setClienteId(int clienteId) {
		ClienteId = clienteId;
	}
	@NotNull
	@Size(min=3,max=20)
	private String ClienteNombre;
	@NotNull
	private String ClienteDocumento;
	@Size(min=8,max=10)
	private String ClienteNroDocumento;
	@NotNull
	private String ClienteTipo;
	@Size(min=3,max=20)
	private String ClienteDireccion;
	@NotNull
	private boolean ClienteEstado;
		
	public Cliente() {
		// TODO Auto-generated constructor stub
		ClienteId=0;
		ClienteNombre="";
		ClienteNroDocumento="";
		ClienteDireccion="";
	}

	public String getClienteNombre() {
		return ClienteNombre;
	}

	public void setClienteNombre(String clienteNombre) {
		ClienteNombre = clienteNombre;
	}

	public String getClienteDireccion() {
		return ClienteDireccion;
	}

	public void setClienteDireccion(String clienteDireccion) {
		ClienteDireccion = clienteDireccion;
	}

	public String getClienteNroDocumento() {
		return ClienteNroDocumento;
	}

	public void setClienteNroDocumento(String clienteNroDocumento) {
		ClienteNroDocumento = clienteNroDocumento;
	}

	public String getClienteTipo() {
		return ClienteTipo;
	}

	public void setClienteTipo(String clienteTipo) {
		ClienteTipo = clienteTipo;
	}

	public String getClienteDocumento() {
		return ClienteDocumento;
	}

	public void setClienteDocumento(String clienteDocumento) {
		ClienteDocumento = clienteDocumento;
	}

	public boolean isClienteEstado() {
		return ClienteEstado;
	}

	public void setClienteEstado(boolean clienteEstado) {
		ClienteEstado = clienteEstado;
	}

	public Cliente(int clienteId, String clienteNombre, String clienteDocumento, String clienteNroDocumento,
			String clienteTipo, String clienteDireccion, boolean clienteEstado) {
		super();
		ClienteId = clienteId;
		ClienteNombre = clienteNombre;
		ClienteDocumento = clienteDocumento;
		ClienteNroDocumento = clienteNroDocumento;
		ClienteTipo = clienteTipo;
		ClienteDireccion = clienteDireccion;
		ClienteEstado = clienteEstado;
	}
	@Override
	public String toString() {
		return "Cliente [ClienteId=" + ClienteId + ", ClienteNombre=" + ClienteNombre + ", ClienteDocumento="
				+ ClienteDocumento + ", ClienteNroDocumento=" + ClienteNroDocumento + ", ClienteTipo=" + ClienteTipo
				+ ", ClienteDireccion=" + ClienteDireccion + ", ClienteEstado=" + ClienteEstado + "]";
	}


	
	
	
}
