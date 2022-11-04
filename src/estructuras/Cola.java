package estructuras;

import java.io.Serializable;

public class Cola<T> implements Serializable {

	private NodoCola<T> primero;
	private NodoCola<T> actual;
	private NodoCola<T> ultimo;

	private int longitud;

	public Cola() {
		super();
	}

	public void encolar(T dato) {
		NodoCola<T> nuevo = new NodoCola<T>();
		nuevo.setElemento(dato);

		if (longitud == 0) {
			primero = nuevo;
		} else {
			ultimo.conectar(nuevo, 0);
		}
		actual = nuevo;
		ultimo = nuevo;

		longitud++;
	}

	public T desencolar() {
		T dato = null;
		if (longitud == 0) {
			System.out.println("no hay nodos a eliminar");
		} else {
			if (primero == ultimo) {
				ultimo = null;
			}
			longitud--;
		}
		return dato;
	}

	public void imprimir() {
		NodoCola<T> aux = primero;

		while (aux != null) {
			System.out.println(aux.getElemento());
			aux = aux.seguirEnlace(0);
		}
	}

	public void irAlPrimero() {
		actual = primero;
	}

	public boolean irSiguiente() {
		if (actual != null) {

			actual = actual.seguirEnlace(0);
			return true;

		} else {

			return false;

		}

	}

	public NodoCola<T> getPrimero() {
		return primero;
	}

	public void setPrimero(NodoCola<T> primero) {
		this.primero = primero;
	}

	public NodoCola<T> getActual() {
		return actual;
	}

	public void setActual(NodoCola<T> actual) {
		this.actual = actual;
	}

	public NodoCola<T> getUltimo() {
		return ultimo;
	}

	public void setUltimo(NodoCola<T> ultimo) {
		this.ultimo = ultimo;
	}

	public int getLongitud() {
		return longitud;
	}

	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}

}