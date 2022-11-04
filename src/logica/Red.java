package logica;

import java.io.Serializable;
import java.util.ArrayList;

import estructuras.ErrorExisteNodo;
import estructuras.ErrorNodoNoExiste;
import estructuras.Grafo;
import estructuras.ListaEnlazada;
import estructuras.Nodo;
import estructuras.NodoLista;

public class Red implements Serializable {

	private Grafo<Vendedor> red;

	public Red() {
		this.red = new Grafo<Vendedor>();
	}

	public Grafo<Vendedor> getRed() {
		return red;
	}

	public void setRed(Grafo<Vendedor> red) {
		this.red = red;
	}

	public boolean AgregarVendedor(Vendedor vendedor) throws ErrorExisteNodo {
		try {

			red.agregar(vendedor);
			System.out.println("Agregado");
			return true;

		} catch (Exception e) {
			throw new ErrorExisteNodo(e.getMessage());
		}

	}

	public void Conectar(Vendedor vendedorOrigen, Vendedor vendedorDestino) {
		try {
			red.conectar(vendedorOrigen, vendedorDestino, 0);

		} catch (ErrorNodoNoExiste e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Vendedor buscarVendedor(Vendedor vendedor) {
		return red.Buscar(vendedor).getElemento();
	}

	public ArrayList<Vendedor> obtenerContactos(Vendedor vendedor) {
		ArrayList<Vendedor> contactos = new ArrayList<Vendedor>();
		Nodo<Vendedor> nodo = red.Buscar(vendedor);

		for (int i = 0; i < nodo.getSize(); i++) {

			if (nodo.getEnlaces().get(i) != null) {
				contactos.add(nodo.getEnlaces().get(i).getDestino().getElemento());
			}
		}
		return contactos;
	}

	public ListaEnlazada<ListaEnlazada<Producto>> obtenerProductosContactos(ArrayList<Vendedor> arrayContactos) {
		ListaEnlazada<ListaEnlazada<Producto>> productosContactos = new ListaEnlazada<ListaEnlazada<Producto>>();

		for (int i = 0; i < arrayContactos.size(); i++) {

			productosContactos.agregar(arrayContactos.get(i).obtenerProductos());
		}

		return productosContactos;
	}

	public ListaEnlazada<ArrayList<Vendedor>> obtenerSugerencias(ArrayList<Vendedor> arrayContactos,
			Vendedor vendedor) {
		Vendedor vendedor2;
		int indice;
		ArrayList<Vendedor> sugerenciasVendedor = new ArrayList<Vendedor>();

		ListaEnlazada<ArrayList<Vendedor>> lista = new ListaEnlazada<ArrayList<Vendedor>>();

		for (int i = 0; i < arrayContactos.size(); i++) {
			vendedor2 = arrayContactos.get(i);
			sugerenciasVendedor = obtenerContactos(vendedor2);
			indice = sugerenciasVendedor.indexOf(vendedor);
			sugerenciasVendedor.remove(indice);
			lista.agregar(sugerenciasVendedor);
		}
		return lista;
	}

	public ListaEnlazada<Vendedor> listaVendedores() {
		ListaEnlazada<Vendedor> lista = new ListaEnlazada<>();
		red.recorrerGrafo(lista);
		return lista;
	}

	public String infoCantidadContactosPorUsuario(ListaEnlazada<Vendedor> listaVendedores) {
		NodoLista<Vendedor> primero = listaVendedores.getPrimero();
		NodoLista<Vendedor> actual = primero;
		ArrayList<Vendedor> contactos = new ArrayList<Vendedor>();

		String cadena = "";

		while (actual != null) {
			Vendedor vendedor = actual.getDato();
			contactos = obtenerContactos(vendedor);
			cadena += actual.getDato().getNombre() + ": " + contactos.size() + "\n";
			actual = actual.seguirEnlace(0);
		}

		return cadena;
	}

	public int cantidadProductosPorXUsuario(Vendedor vendedor) {
		Nodo<Vendedor> nodo = red.Buscar(vendedor);
		ListaEnlazada<Producto> listaProductos = vendedor.obtenerProductos();
		int cantidad = vendedor.cantidadProductos(listaProductos);
		return cantidad;
	}

	public void enviarMensajes(Vendedor vendedor, Vendedor destino, MensajeYComentario mensaje) {
		ArrayList<Vendedor> contactos = obtenerContactos(vendedor);
		for (Vendedor v : contactos) {
			if (v.equals(destino)) {
				v.getMensajes().agregar(mensaje);
			}
		}
	}

	public String recibirMensajes(Vendedor vendedor, Vendedor origen) {
		String chat = "";
		ListaEnlazada<MensajeYComentario> mensajes = vendedor.getMensajes();
		NodoLista<MensajeYComentario> nodo = mensajes.getPrimero();
		NodoLista<MensajeYComentario> actual = nodo;

		while (actual != null) {
			Vendedor aux = actual.getDato().getVendedor();
			if (aux.equals(origen)) {
				chat += actual.toString() + "\n";
			}
		}

		return chat;
	}

	public ArrayList<Producto> todosLosProductos(ListaEnlazada<Vendedor> listaVendedores) {
		NodoLista<Vendedor> primero = listaVendedores.getPrimero();
		NodoLista<Vendedor> actual = primero;
		ListaEnlazada<Producto> listaProductos = new ListaEnlazada<Producto>();
		ArrayList<Producto> arrayProductos = new ArrayList<Producto>();

		while (actual != null) {
			Vendedor vendedor = actual.getDato();
			listaProductos = vendedor.obtenerProductos();

			NodoLista<Producto> primero2 = listaProductos.getPrimero();
			NodoLista<Producto> actual2 = primero2;

			while (actual2 != null) {
				arrayProductos.add(actual2.getDato());
				actual2 = actual2.seguirEnlace(0);
			}

			actual = actual.seguirEnlace(0);
		}
		return arrayProductos;
	}

	public int cantProductosPublicadosXFecha(ArrayList<Producto> listaProductos, String fecha) {
		int cantidad = 0;

		for (Producto p : listaProductos) {
			if (p.getFecha().equalsIgnoreCase(fecha)) {
				cantidad++;
			}
		}

		return cantidad;
	}

	public ListaEnlazada<Vendedor> listaNueva(ListaEnlazada<Vendedor> lista, Vendedor vendedor) {
		NodoLista<Vendedor> primero = lista.getPrimero();
		NodoLista<Vendedor> actual = primero;
		ListaEnlazada<Vendedor> listaNueva = new ListaEnlazada<Vendedor>();
		ArrayList<Vendedor> contactos = obtenerContactos(vendedor);

		if (contactos.size() == 0) {
			return lista;
		}
		while (actual != null) {

			if (!contactos.contains(actual.getDato())) {
				listaNueva.agregar(actual.getDato());
			}
			actual = actual.seguirEnlace(0);
		}
		return listaNueva;
	}

	public String infocantidadMensajesEnviadosEntreUsuarios(ListaEnlazada<Vendedor> lista) {
		NodoLista<Vendedor> primero = lista.getPrimero();
		NodoLista<Vendedor> actual = primero;
		ListaEnlazada<MensajeYComentario> listaMensajes = new ListaEnlazada<MensajeYComentario>();

		String cadena = "";

		while (actual != null) {
			int cont = 0;
			listaMensajes = actual.getDato().getMensajes();
			if (listaMensajes.getLongitud() != 0) {

				NodoLista<MensajeYComentario> primero2 = listaMensajes.getPrimero();
				NodoLista<MensajeYComentario> actual2 = primero2;
				cadena += "Cantidad de mensajes de " + actual.getDato().getNombre() + " enviados por:" + "\n";
				while (actual2 != null) {
					String[] arrSplit = actual2.getDato().getTexto().split("\n");
					for (int i = 0; i < arrSplit.length; i++) {
						cont++;
					}

					cadena += "\t" + actual2.getDato().getVendedor() + ":" + cont + "\n";
					actual2 = actual2.seguirEnlace(0);
				}
			}
			actual = actual.seguirEnlace(0);

		}

		return cadena;
	}

	public Vendedor buscarVendedorNombre(ListaEnlazada<Vendedor> lista, String nombre) {
		NodoLista<Vendedor> primero = lista.getPrimero();
		NodoLista<Vendedor> actual = primero;
		Vendedor vendedor = null;

		while (actual != null) {
			if (actual.getDato().getNombre().equalsIgnoreCase(nombre)) {
				vendedor = actual.getDato();
			}
			actual = actual.seguirEnlace(0);
		}
		return vendedor;
	}

}
