package estructuras;

import java.io.Serializable;

public class ListaEnlazada<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NodoLista<T> primero;
	private NodoLista<T> actual;
	private NodoLista<T> ultimo;
	private int longitud;

	public ListaEnlazada() {
		primero = null;
		actual = null;
		ultimo = null;

		longitud = 0;
	}

	public void agregar(T elemento) {
		NodoLista<T> nuevo = new NodoLista<T>();
		nuevo.setDato(elemento);

		if (longitud == 0) {
			primero = nuevo;
		} else {
			ultimo.conectar(nuevo, 0);
		}
		actual = nuevo;
		ultimo = nuevo;

		longitud++;
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

	public T getDato() {
		return actual.getDato();
	}

	public int getLongitud() {
		return longitud;
	}

	@Override
	public String toString() {

		String salida = " ";
		salida = "Estado de la lista: \n";
		NodoLista<T> temporal = primero;

		for (int i = 0; i < this.getLongitud(); i++) {

			salida += temporal;
			temporal = temporal.seguirEnlace(0);

		}
		this.irSiguiente();
		salida += "\n";
		salida += "Primero: " + primero.getNombre() + ":" + primero.getDato() + "\n";

		if (actual != null) {

			salida += "Actual: " + actual.getNombre() + ":" + actual.getDato() + "\n";
		} else {

			salida += "Actual: null" + "\n";
		}
		salida += "Ultimo: " + ultimo.getNombre() + ":" + ultimo.getDato() + "\n";
		salida += "Longitud: " + longitud + "\n";
		return salida;
	}

	public void recorrer() {
		actual = primero;

		if (actual != null) {
			while (actual != null) {
				System.out.print(actual.getDato() + "->");
				actual = actual.seguirEnlace(0);
			}
		} else {
			System.out.println("esta vacia");
		}
	}

	public boolean contains(T elemento) {
		NodoLista<T> auxPrimero = primero;
		NodoLista<T> auxActual = auxPrimero;
		// actual= primero;

		if (auxActual != null) {
			while (auxActual != null) {
				if (auxActual.getDato().equals(elemento)) {
					return true;
				} else {
					auxActual = auxActual.seguirEnlace(0);
				}

			}
			return false;
		} else {
			return false;

		}
	}

	public T get(T elemento) {
		NodoLista<T> auxPrimero = primero;
		NodoLista<T> auxActual = auxPrimero;

		if (actual != null) {
			while (actual != null) {
				if (actual.getDato().equals(elemento)) {
					return actual.getDato();
				} else {
					actual = actual.seguirEnlace(0);
				}

			}
			return null;
		} else {
			return null;

		}
	}

	public String getNombres() {
		actual = primero;
		String cadena = "";

		if (actual != null) {
			while (actual != null) {
				cadena += actual.getDato() + " ";
				actual = actual.seguirEnlace(0);
			}
		} else {
			System.out.println("esta vacia");
		}
		return cadena;
	}

	public void recorrerRecursivo(NodoLista<T> actual) {
		if (actual != null) {
			System.out.println(actual.getDato());
			actual = actual.seguirEnlace(0);
			recorrerRecursivo(actual);
		}
	}

	public void recorrerFor() {

		actual = primero;

		for (int i = 0; i < longitud; i++) {
			actual = actual.seguirEnlace(0);
		}
	}

	public NodoLista<T> getPrimero() {
		return primero;
	}

	public void setPrimero(NodoLista<T> primero) {
		this.primero = primero;
	}

	public NodoLista<T> getActual() {
		return actual;
	}

	public void setActual(NodoLista<T> actual) {
		this.actual = actual;
	}

	public NodoLista<T> getUltimo() {
		return ultimo;
	}

	public void setUltimo(NodoLista<T> ultimo) {
		this.ultimo = ultimo;
	}

	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}

}
