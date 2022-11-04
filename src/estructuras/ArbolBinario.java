package estructuras;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ArbolBinario<T extends Comparable<T>> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NodoGenerico<T> raiz;
	private int peso;

	/**
	 * Verifica si un árbol está vacío
	 * 
	 * @return true si está vacío
	 */
	public boolean estaVacio() {
		return raiz == null;
	}

	public ArbolBinario() {
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	/**
	 * Agrega un nuevo elemento al árbol
	 * 
	 * @param elemento Nuevo dato
	 * @return true si lo pudo guardar
	 */
	public boolean agregar(T elemento) {
		if (estaVacio()) {
			raiz = new NodoGenerico<T>(elemento);
			peso++;
			return true;
		} else {
			if (raiz.agregar(elemento)) {
				peso++;
				return true;
			} else {
				return false;
			}
		}

	}

	/**
	 * Realiza el recorrido inorden en el árbol binario
	 */
	public void inorden() {
		inorden(raiz);
		System.out.println();
	}

	/**
	 * Realiza el recorrido inorden en el árbol binario
	 * 
	 * @param n Nodo raíz
	 */
	private void inorden(NodoGenerico<T> n) {
		if (n != null) {
			inorden(n.getIzquierdo());
			System.out.print(n.getElemento() + "\t");
			inorden(n.getDerecho());
		}
	}

	/**
	 * Realiza el recorrido preorden en el árbol binario
	 */
	public void preorden(ArrayList<T> lista) {
		preorden(raiz, lista);
		System.out.println();
	}

	/**
	 * Realiza el recorrido preorden en el árbol binario
	 * 
	 * @param n Nodo raíz
	 */
	private void preorden(NodoGenerico<T> n, ArrayList<T> lista) {
		if (n != null) {
			lista.add(n.getElemento());
			preorden(n.getIzquierdo(), lista);
			preorden(n.getDerecho(), lista);
		}
	}

	/**
	 * Realiza el recorrido postorden en el árbol binario
	 */
	public void postorden() {
		postorden(raiz);
		System.out.println();
	}

	/**
	 * Realiza el recorrido postorden en el árbol binario
	 * 
	 * @param n Nodo raíz
	 */
	private void postorden(NodoGenerico<T> n) {
		if (n != null) {
			postorden(n.getIzquierdo());
			postorden(n.getDerecho());
			System.out.println(n.getElemento());
		}
	}

	/**
	 * Vetifica si un elemento existe en el árbol binario
	 * 
	 * @param n        Nodo raíz
	 * @param elemento Elemento a buscar
	 * @return true si lo encuentra
	 */
	public boolean existe(NodoGenerico<T> n, T elemento) {
		if (n != null) {
			if (elemento.compareTo(n.getElemento()) == 0) {
				return true;
			} else if (elemento.compareTo(n.getElemento()) < 0) {
				return existe(n.getIzquierdo(), elemento);
			} else if (elemento.compareTo(n.getElemento()) > 0) {
				return existe(n.getDerecho(), elemento);
			}
		}
		return false;
	}

	/**
	 * Cuenta todos los elementos que hay en el árbol
	 * 
	 * @param n Nodo raíz
	 * @return Peso del árbol
	 */
	public int obtenerPeso(NodoGenerico<T> n) {

		if (n != null) {
			return 1 + obtenerPeso(n.getIzquierdo()) + obtenerPeso(n.getDerecho());
		}

		return 0;
	}

	/**
	 * Devuelve la altura del árbol
	 * 
	 * @param n Nodo raíz
	 * @return Altura
	 */
	public int obtenerAltura(NodoGenerico<T> n, int altura) {
		if (n != null) {
			// System.out.println(altura + "\t" + n.getElemento().toString());
			// System.out.println();
			int alturaIzq = obtenerAltura(n.getIzquierdo(), altura + 1);
			int alturaDer = obtenerAltura(n.getDerecho(), altura + 1);

			if (alturaIzq >= alturaDer) {
				return alturaIzq;
			} else {
				return alturaDer;
			}
		}
		System.out.println(altura);
		return altura;
	}

	/**
	 * Retorna el nivel de un elemento dentro del árbol
	 * 
	 * @param n        Nodo raíz
	 * @param elemento Elemento a buscar
	 * @param nivel    Nivel inicial
	 * @return Nivel del elemento
	 */
	public int obtenerNivel(NodoGenerico<T> n, T elemento, int nivel) {
		if (n != null) {
			if (elemento.compareTo(n.getElemento()) == 0) {
				return nivel;
			} else if (elemento.compareTo(n.getElemento()) < 0) {
				return obtenerNivel(n.getIzquierdo(), elemento, nivel + 1);
			} else if (elemento.compareTo(n.getElemento()) > 0) {
				return obtenerNivel(n.getDerecho(), elemento, nivel + 1);
			}
		}
		return -1;
	}

	/**
	 * Cuenta el número de Hojas del árbol
	 * 
	 * @param n Nodo raíz
	 * @return hojas del árbol
	 */
	public int contarHojas(NodoGenerico<T> n) {

		if (n != null) {
			int c = 0;
			if (n.esHoja()) {
				c = 1;
			}
			return c + contarHojas(n.getIzquierdo()) + contarHojas(n.getDerecho());
		}
		return 0;
	}

	/**
	 * Retorna el valor más pequeño del árbol
	 * 
	 * @return Menor
	 */
	public T obtenerMenor() {

		NodoGenerico<T> aux = raiz;

		{
			aux = aux.getIzquierdo();
		}

		return aux.getElemento();
	}

	/**
	 * Retorna el valor más pequeño del árbol
	 * 
	 * @param n Nodo raíz
	 * @return Menor
	 */
	public T obtenerMenor(NodoGenerico<T> n) {
		if (n.getIzquierdo() != null) {
			return obtenerMenor(n.getIzquierdo());
		}
		return n.getElemento();
	}

	/**
	 * Imprime el árbol de manera horizontal
	 * 
	 * @param n     Nodo raíz
	 * @param nivel Nivel de cada nodo, determina el número de espacios
	 */
	public void imprimirHorizontal(NodoGenerico<T> n, int nivel) {

		if (n != null) {

			imprimirHorizontal(n.getDerecho(), nivel + 1);

			for (int i = 0; i < nivel; i++) {
				System.out.print("\t");
			}

			System.out.println(n.getElemento());

			imprimirHorizontal(n.getIzquierdo(), nivel + 1);

		}

	}

	/**
	 * Elimina un elemento del árbol
	 * 
	 * @param elemento Elemento a borrar
	 */
	public void eliminar(T elemento) {
		eliminar(raiz, elemento);
	}

	/**
	 * Elimina un elemento del árbol
	 * 
	 * @param n        Nodo raíz
	 * @param elemento Elemento a buscar para borrar
	 */
	private void eliminar(NodoGenerico<T> n, T elemento) {

		if (n != null) {
			if (elemento.compareTo(n.getElemento()) == 0) {
				eliminar(n);
			} else if (elemento.compareTo(n.getElemento()) < 0) {
				eliminar(n.getIzquierdo(), elemento);
			} else if (elemento.compareTo(n.getElemento()) > 0) {
				eliminar(n.getDerecho(), elemento);
			}
		}

	}

	/**
	 * Elimina un Nodo del árbol
	 * 
	 * @param n Nodo a eliminar
	 */
	private void eliminar(NodoGenerico<T> n) {

		NodoGenerico<T> padre = n.getPadre();

		// Caso 1
		if (n.esHoja()) {
			if (padre == null) {
				raiz = null;
			} else if (n.getElemento().compareTo(padre.getElemento()) > 0) {
				padre.setDerecho(null);
			} else {
				padre.setIzquierdo(null);

			}
			peso--;
			// Caso 2
		} else if (n.tieneUnHijo()) {
			if (padre == null) {
				if (n.getIzquierdo() != null) {
					raiz = n.getIzquierdo();
				} else {
					raiz = n.getDerecho();
				}
				raiz.setPadre(null);
			} else if (n.getElemento().compareTo(padre.getElemento()) > 0) {
				if (n.getIzquierdo() != null) {
					padre.setDerecho(n.getIzquierdo());

				} else {
					padre.setDerecho(n.getDerecho());

				}
				padre.getDerecho().setPadre(padre);
			} else {
				if (n.getIzquierdo() != null) {
					padre.setIzquierdo(n.getIzquierdo());
				} else {
					padre.setIzquierdo(n.getDerecho());
				}
				padre.getIzquierdo().setPadre(padre);
			}
			peso--;
			// Caso 3-tiene dos hijos
		} else {
			NodoGenerico<T> mayor = obtenerNodoMayor(n.getIzquierdo());
			T aux = mayor.getElemento();
			eliminar(mayor);
			n.setElemento(aux);
		}

	}

	/**
	 * Imprime el árbol usando el recorrido en amplitud (por niveles)
	 */
	public void imprimirAmplitud() {

		Queue<NodoGenerico<T>> cola = new LinkedList<>();
		NodoGenerico<T> aux = raiz;

		cola.add(aux);

		while (!cola.isEmpty()) {

			NodoGenerico<T> primero = cola.poll();
			System.out.print(primero.getElemento() + "\t");
			if (primero.getIzquierdo() != null) {
				cola.add(primero.getIzquierdo());
			}

			if (primero.getDerecho() != null) {
				cola.add(primero.getDerecho());
			}

		}

	}

	/**
	 * Cuenta el número de hojas del árbol sin recursividad
	 * 
	 * @return Hojas
	 */
	public int contarHojas() {

		Queue<NodoGenerico<T>> cola = new LinkedList<>();
		NodoGenerico<T> aux = raiz;
		cola.add(aux);
		int cont = 0;

		while (!cola.isEmpty()) {

			NodoGenerico<T> primero = cola.poll();

			if (primero.esHoja()) {
				cont++;
			}

			if (primero.getIzquierdo() != null) {
				cola.add(primero.getIzquierdo());
			}

			if (primero.getDerecho() != null) {
				cola.add(primero.getDerecho());
			}

		}

		return cont;
	}

	/**
	 * Obtiene la altura del árbol sin recursividad
	 * 
	 * @return altura
	 */
	public int obtenerAltura() {

		Queue<NodoGenerico<T>> cola = new LinkedList<>();
		NodoGenerico<T> aux = raiz;
		cola.add(aux);
		int cont1 = 1, cont2 = 1;

		while (!cola.isEmpty()) {

			NodoGenerico<T> primero = cola.poll();

			if (primero.getIzquierdo() != null) {
				cont1++;
				cola.add(primero.getIzquierdo());
			}

			if (primero.getDerecho() != null) {
				cont2++;
				cola.add(primero.getDerecho());
			}

		}

		return cont1 > cont2 ? cont1 : cont2;
	}

	/**
	 * Obtiene el nodo más grande de un subárbol
	 * 
	 * @param n Nodo Izquierdo del subarbol
	 * @return Nodo más grande
	 */
	public NodoGenerico<T> obtenerNodoMayor(NodoGenerico<T> n) {
		while (n.getDerecho() != null) {
			n = n.getDerecho();
		}
		return n;
	}

	/**
	 * Obtiene el nodo más pequeño de un subárbol
	 * 
	 * @param n Nodo Derecho del subarbol
	 * @return Nodo más pequeño
	 */
	public NodoGenerico<T> obtenerNodoMenor(NodoGenerico<T> n) {
		while (n.getIzquierdo() != null) {
			n = n.getIzquierdo();
		}
		return n;
	}

	/**
	 * @return the raiz
	 */
	public NodoGenerico<T> getRaiz() {
		return raiz;
	}

	/**
	 * @param raiz the raiz to set
	 */
	public void setRaiz(NodoGenerico<T> raiz) {
		this.raiz = raiz;
	}

	/**
	 * @return the peso
	 */
	public int getPeso() {
		return peso;
	}

}
