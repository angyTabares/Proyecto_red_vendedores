package estructuras;

import java.io.Serializable;

public class Enlace<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private Nodo<T> destino;
	private double peso;

	public Enlace() {
	}

	public Enlace(Nodo<T> destino, double peso) {
		super();
		this.destino = destino;
		this.peso = peso;
	}

	/**
	 * @return the destino
	 */
	public Nodo<T> getDestino() {
		return destino;
	}

	/**
	 * @param destino the destino to set
	 */
	public void setDestino(Nodo<T> destino) {
		this.destino = destino;
	}

	/**
	 * @return the peso
	 */
	public double getPeso() {
		return peso;
	}

	/**
	 * @param peso the peso to set
	 */
	public void setPeso(double peso) {
		this.peso = peso;
	}

	@Override
	public String toString() {
		return "Enlace [destino=" + destino + ", peso=" + peso + "]";
	}

}