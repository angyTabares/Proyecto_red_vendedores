package estructuras;

import java.io.Serializable;
import java.util.ArrayList;

public class Nodo<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private T elemento;
	private ArrayList<Enlace<T>> enlaces;
	private String nombre;
	private boolean flag;

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	// Método constructor
	public Nodo(String nombre) {
		this.nombre = nombre;

		// se crea una lista para las conexiones salientes de este nodo
		// hacia otros nodos.
		enlaces = new ArrayList<Enlace<T>>();

		enlaces.add(null);
	}

	public Nodo() {

	}

	// setter

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Nodo(T elemento) {
		super();
		this.elemento = elemento;

		enlaces = new ArrayList<Enlace<T>>();
		this.flag = false;

		enlaces.add(null);
	}

	public T getElemento() {
		return elemento;
	}

	public void setElemento(T elemento) {
		this.elemento = elemento;
	}

	public String getNombre() {
		return nombre;
	}

	public int getSize() {
		return enlaces.size();
	}

	public ArrayList<Enlace<T>> getEnlaces() {
		return enlaces;
	}

	public void setEnlaces(ArrayList<Enlace<T>> enlaces) {
		this.enlaces = enlaces;
	}

	public void conectar(Nodo<T> destino, int indice, double peso) {
		// si la posicion indice existe en el vector de enlaces
		Enlace enlace = new Enlace(destino, peso);
		if (indice < enlaces.size()) {
			enlaces.set(indice, enlace);
		} else {
			int n = indice - enlaces.size();

			// la lista de enlaces crece. En las posiciones intermedias
			// se asigna null
			for (int i = 0; i < n; i++) {
				enlaces.add(null);
			}

			// en la posición indice del vector enlaces se almacena
			// una referencia al nodo destino
			enlaces.add(enlace);
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

	public boolean estaConectado(Nodo nodo) {

		for (int i = 0; i < enlaces.size(); i++) {
			if (enlaces.get(i).getDestino().equals(nodo)) {
				return true;
			}
		}
		return false;
	}

	public Nodo<T> seguirEnlace(int indice) {
		return enlaces.get(indice).getDestino();
	}

	@Override

	public String toString() {
		String s = elemento + ": ";
		// s += "{" + elemento + "}\n";
		s += "Enlaces: " + enlaces.size() + "\n";

		for (int i = 0; i < enlaces.size(); i++) {
			s += "[" + i + "]:";
			if (enlaces.get(i) != null) {
				s += enlaces.get(i).getDestino().getElemento() + "\n";
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