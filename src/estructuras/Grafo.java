package estructuras;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Grafo<T> implements Serializable {
	private HashMap<T, Nodo<T>> grafo;
	private int size;
	private Nodo<T> inicial;

	public Grafo() {
		grafo = new HashMap<T, Nodo<T>>();
		size = 0;
		inicial = null;
	}

	public HashMap<T, Nodo<T>> getGrafo() {
		return grafo;
	}

	public void setGrafo(HashMap<T, Nodo<T>> grafo) {
		this.grafo = grafo;
	}

	public Nodo<T> getInicial() {
		return inicial;
	}

	public void setInicial(Nodo<T> inicial) {
		this.inicial = inicial;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void agregar(T elemento) throws ErrorExisteNodo {//

		if (!grafo.containsKey(elemento)) {
			Nodo<T> nodo = new Nodo<T>(elemento);
			grafo.put(elemento, nodo);

			size++;

		} else {
			throw new ErrorExisteNodo("Ya existe nodo con ese nombre");
		}
	}

	public void eliminar(T elemento) {//
		if (grafo.containsKey(elemento)) {
			grafo.remove(elemento);
			size--;
		}
	}

	public Nodo<T> Buscar(T elemento) {//
		Nodo<T> nodo = null;
		if (grafo.containsKey(elemento)) {
			nodo = grafo.get(elemento);
		}
		return nodo;
	}

	public void conectar(T elementoOrigen, T elementoDestino, double peso) throws ErrorNodoNoExiste {
		Nodo<T> nodoOrigen;
		Nodo<T> nodoDestino;
		if (grafo.containsKey(elementoOrigen)) {
			nodoOrigen = grafo.get(elementoOrigen);
			if (grafo.containsKey(elementoOrigen)) {
				nodoDestino = grafo.get(elementoDestino);
			} else {
				throw new ErrorNodoNoExiste("Nodo destino no existe");
			}

			if (nodoOrigen.estaConectado(0) == false) {
				nodoOrigen.conectar(nodoDestino, nodoOrigen.getSize() - 1, peso);
				if (nodoDestino.estaConectado(0) == false) {
					nodoDestino.conectar(nodoOrigen, nodoDestino.getSize() - 1, peso);
				} else {
					nodoDestino.conectar(nodoOrigen, nodoDestino.getSize(), peso);
				}

			} else {
				nodoOrigen.conectar(nodoDestino, nodoOrigen.getSize(), peso);
				if (nodoDestino.estaConectado(0) == false) {
					nodoDestino.conectar(nodoOrigen, nodoDestino.getSize() - 1, peso);
				} else {
					nodoDestino.conectar(nodoOrigen, nodoDestino.getSize(), peso);
				}
			}

		} else {
			throw new ErrorNodoNoExiste("Nodo origen no existe");
		}
	}

	public void desconectar(T elementoOrigen, T elementodestino) throws ErrorNodoNoExiste {
		Nodo<T> nodoOrigen;
		Nodo<T> nodoDestino = null;
		int indice;
		if (grafo.containsKey(elementoOrigen)) {
			nodoOrigen = grafo.get(elementoOrigen);
			if (grafo.containsKey(elementodestino)) {
				nodoDestino = grafo.get(elementodestino);
			}

			for (int i = 0; i < nodoOrigen.getSize(); i++) {
				if (nodoOrigen.getEnlaces().get(i).getDestino().equals(nodoDestino)) {
					nodoOrigen.desconectar(i);
				}
			}
			// nodoOrigen.desconectar(indice);
		} else {
			throw new ErrorNodoNoExiste("Nodo origen no existe");
		}
	}

	public boolean estaConectado(T elemento, int indice) throws ErrorNodoNoExiste {
		boolean respuesta = false;
		Nodo<T> nodoOrigen;
		if (grafo.containsKey(elemento)) {
			nodoOrigen = grafo.get(elemento);
			respuesta = nodoOrigen.estaConectado(indice);
		} else {
			throw new ErrorNodoNoExiste("Nodo origen no existe");
		}
		return respuesta;
	}

	public Nodo<T> seguirEnlace(T elemento, int indice) throws ErrorNodoNoExiste {
		Nodo<T> nodoOrigen = null;
		Nodo<T> nodoEnlace = null;
		if (grafo.containsKey(elemento)) {
			nodoOrigen = grafo.get(elemento);
			nodoEnlace = nodoOrigen.seguirEnlace(indice);
		} else {
			throw new ErrorNodoNoExiste("Nodo origen no existe");
		}
		return nodoEnlace;
	}

	public void setInicial(T elemento) throws ErrorNodoNoExiste {
		Nodo<T> nodo = null;
		if (grafo.containsKey(elemento)) {
			nodo = grafo.get(elemento);
			inicial = nodo;
		} else {
			throw new ErrorNodoNoExiste("Nodo origen no existe");
		}
	}

	public void setDato(T elemento) throws ErrorNodoNoExiste {
		Nodo<T> nodo = null;
		if (grafo.containsKey(elemento)) {
			nodo = grafo.get(elemento);
			nodo.setElemento(elemento);
		} else {
			throw new ErrorNodoNoExiste("Nodo origen no existe");
		}
	}

	public T getDato(T elemento) throws ErrorNodoNoExiste {
		T dato;
		Nodo<T> nodo;
		if (grafo.containsKey(elemento)) {
			nodo = grafo.get(elemento);
			dato = nodo.getElemento();
		} else {
			throw new ErrorNodoNoExiste("Nodo origen no existe");
		}
		return dato;
	}

	public int getSize() {
		return size;
	}

	public int getSizeNodo(T elemento) throws ErrorNodoNoExiste {
		int dato;
		Nodo<T> nodo;
		if (grafo.containsKey(elemento)) {
			nodo = grafo.get(elemento);
			dato = nodo.getSize();
		} else {
			throw new ErrorNodoNoExiste("Nodo origen no existe");
		}
		return dato;
	}

	@Override
	public String toString() {
		return "Grafo: [ \n\tsize=" + size + "\n\t inicial=" + inicial + "\n\t grafo=\n\t\t" + grafo + "\n]";
	}

	public void profundidad(ArrayList<T> lista) {
		// resetFlags();
		profundidad(inicial, lista);
	}

	public void recorrerGrafo(ListaEnlazada<T> lista) {
		recorrerGrafo(inicial, lista);
	}

	private void profundidad(Nodo<T> nodo, ArrayList<T> lista) {
		// System.out.println("Profundidad");
		int i;
		T elemento;
		if (nodo != null && nodo.getFlag() == false) {
			nodo.setFlag(true);
			elemento = nodo.getElemento();
			lista.add(elemento);
			// System.out.println("Agregado");
			System.out.println(nodo.getElemento().toString());
			for (i = 0; i < nodo.getSize(); i++) {
				if (nodo.estaConectado(i)) {
					System.out.println("Enlaces de: " + nodo.getElemento().toString());
					profundidad(nodo.seguirEnlace(i), lista);
				}
			}
		}
		return;
	}

	private void recorrerGrafo(Nodo<T> nodo, ListaEnlazada<T> lista) {
		for (HashMap.Entry<T, Nodo<T>> entry : grafo.entrySet()) {
			lista.agregar(entry.getKey());

		}
	}

}