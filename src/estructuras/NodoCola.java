package estructuras;

import java.io.Serializable;
import java.util.ArrayList;

public class NodoCola<T> implements Serializable {

	private T elemento;
	private ArrayList<NodoCola<T>> enlaces;
	private boolean flag;

	public NodoCola() {
		enlaces = new ArrayList<>();
		enlaces.add(null);
	}

	public void conectar(NodoCola destino, int indice) {
		if (indice < enlaces.size()) {
			enlaces.set(indice, destino);
		} else {
			int n = indice - enlaces.size();

			// la lista de enlaces crece. En las posiciones intermedias
			// se asigna null
			for (int i = 0; i < n; i++) {
				enlaces.add(null);
			}

			// en la posición indice del vector enlaces se almacena
			// una referencia al nodo destino
			enlaces.add(destino);
		}
	}

	public NodoCola<T> seguirEnlace(int indice) {
		return enlaces.get(indice);
	}

	public void desconectar() {
		enlaces.set(0, null);
	}

	public boolean estaConectado() {
		return enlaces.get(0) != null;

	}

	public ArrayList<NodoCola<T>> getEnlaces() {
		return enlaces;
	}

	public void setEnlaces(ArrayList<NodoCola<T>> enlaces) {
		this.enlaces = enlaces;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public void setElemento(T elemento) {
		this.elemento = elemento;
	}

	public T getElemento() {
		return elemento;
	}

	public void desconectar(int indice) {
		// la referencia especificada se reemplaza por null
		enlaces.set(indice, null);
	}
}
