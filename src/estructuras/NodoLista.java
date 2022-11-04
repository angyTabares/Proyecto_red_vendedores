package estructuras;

import java.io.Serializable;
import java.util.ArrayList;

public class NodoLista<T> implements Serializable {
	private T dato;
	private ArrayList<NodoLista<T>> enlaces;
	private String nombre;
	private boolean flag;

	public ArrayList<NodoLista<T>> getEnlaces() {
		return enlaces;
	}

	public void setEnlaces(ArrayList<NodoLista<T>> enlaces) {
		this.enlaces = enlaces;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	// Método constructor
	public NodoLista(String nombre) {
		this.nombre = nombre;

		// se crea una lista para las conexiones salientes de este nodo
		// hacia otros nodos.
		enlaces = new ArrayList<NodoLista<T>>();

		enlaces.add(null);
	}

	public NodoLista() {
		this.dato = null;
		this.nombre = null;

		// se crea una lista para las conexiones salientes de este nodo
		// hacia otros nodos.
		enlaces = new ArrayList<NodoLista<T>>();

		enlaces.add(null);
	}

	// setter
	public void setDato(T dato) {
		this.dato = dato;
	}

	// getters
	public T getDato() {
		return dato;
	}

	public String getNombre() {
		return nombre;
	}

	public int getSize() {
		return enlaces.size();
	}

	// Administración de conexiones
	public void conectar(NodoLista<T> destino, int indice) {
		// si la posicion indice existe en el vector de enlaces
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

	public void desconectar(int indice) {
		// la referencia especificada se reemplaza por null
		enlaces.set(indice, null);
	}

	public boolean estaConectado(int indice) {
		if (enlaces.get(indice) != null) {
			return true;
		} else {
			return false;
		}
		// return enlaces.get(indice) != null;
	}

	public NodoLista<T> seguirEnlace(int indice) {
		return enlaces.get(indice);
	}

	@Override
	public String toString() {
		String s = nombre + ": ";
		s += "{" + dato + "}\n";
		s += "Enlaces: " + enlaces.size() + "\n";

		for (int i = 0; i < enlaces.size(); i++) {
			s += "[" + i + "]:";
			if (enlaces.get(i) != null) {
				s += enlaces.get(i).getDato() + "\n";
			} else {
				s += null + "\n";
			}
		}

		return s;
	}

	public void setFlag(boolean valor) {
		flag = valor;
	}

	public boolean getFlag() {
		return flag;
	}

}
