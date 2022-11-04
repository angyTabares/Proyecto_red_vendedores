package logica;

import java.io.Serializable;

public class MensajeYComentario implements Serializable {

	private Vendedor vendedor;
	private Vendedor origen;
	private String texto;

	public MensajeYComentario(Vendedor vendedor, String texto) {
		super();
		this.vendedor = vendedor;
		this.texto = texto;
	}

	public MensajeYComentario() {

	}

	public Vendedor getOrigen() {
		return origen;
	}

	public void setOrigen(Vendedor origen) {
		this.origen = origen;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	@Override
	public String toString() {
		return "De: " + vendedor.getNombre() + ": " + texto + "\n";
	}

}
