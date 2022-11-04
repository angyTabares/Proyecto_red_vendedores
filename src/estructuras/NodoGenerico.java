package estructuras;

import java.io.Serializable;
import java.util.ArrayList;

public class NodoGenerico<T extends Comparable<T>> implements Serializable {

	private T elemento;
	private ArrayList<NodoGenerico<T>> enlaces;
	private boolean flag;

	public NodoGenerico(T elemento) {
		setElemento(elemento);
		enlaces = new ArrayList<>();
		enlaces.add(null);
		enlaces.add(null);
		enlaces.add(null);

	}

	public NodoGenerico() {
		super();
	}

	public NodoGenerico(T elemento, NodoGenerico<T> padre) {
		setElemento(elemento);
		enlaces = new ArrayList<>();
		enlaces.add(padre);
		enlaces.add(null);
		enlaces.add(null);

	}

	public boolean agregar(T nuevo) {
		NodoGenerico<T> nodo = new NodoGenerico<T>(nuevo, this);
		if (nuevo.compareTo(elemento) < 0) {
			if (getIzquierdo() == null) {
				this.setIzquierdo(nodo);
				return true;
			} else {
				return getIzquierdo().agregar(nuevo);
			}
		} else {
			if (nuevo.compareTo(elemento) > 0) {
				if (getDerecho() == null) {
					this.setDerecho(nodo);
					return true;
				} else {
					return getDerecho().agregar(nuevo);
				}
			}
		}
		return false;

	}

	public void conectar(NodoGenerico<T> destino, int indice) {

		System.out.println(indice);
		System.out.println(enlaces.size());
		if (indice == 0) {
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

	public boolean esHoja() {
		return getIzquierdo() == null && getDerecho() == null;
	}

	public boolean tieneUnHijo() {
		return (getIzquierdo() != null && getDerecho() == null) || (getIzquierdo() == null && getDerecho() != null);
	}

	public NodoGenerico<T> seguirEnlace(int indice) {
		return enlaces.get(indice);
	}

	public NodoGenerico<T> getIzquierdo() {
		return enlaces.get(1);
	}

	public NodoGenerico<T> getDerecho() {
		return enlaces.get(2);
	}

	public NodoGenerico<T> getPadre() {
		return enlaces.get(0);
	}

	public void setDerecho(NodoGenerico<T> derecho) {
		this.getEnlaces().set(2, derecho);
	}

	public void setIzquierdo(NodoGenerico<T> izquierdo) {
		this.getEnlaces().set(1, izquierdo);
	}

	public void setPadre(NodoGenerico<T> padre) {
		this.getEnlaces().set(0, padre);
	}

	public void desconectar() {
		enlaces.set(0, null);
	}

	public boolean estaConectado() {
		return enlaces.get(0) != null;

	}

	public ArrayList<NodoGenerico<T>> getEnlaces() {
		return enlaces;
	}

	public void setEnlaces(ArrayList<NodoGenerico<T>> enlaces) {
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
}
