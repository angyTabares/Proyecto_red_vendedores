package logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import estructuras.ArbolBinario;
import estructuras.ListaEnlazada;
import estructuras.NodoLista;

public class Vendedor implements Serializable {

	private String nombre;
	private String telefono;
	private String direccion;
	private String correo;
	private ArbolBinario<Producto> arbolProductos;
	private ListaEnlazada<MensajeYComentario> mensajes;

	public Vendedor(String nombre, String telefono, String direccion, String correo) {
		super();
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
		this.correo = correo;
		this.arbolProductos = new ArbolBinario<Producto>();
		this.mensajes = new ListaEnlazada<MensajeYComentario>();

	}

	public Vendedor() {

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArbolBinario<Producto> getArbolProductos() {
		return arbolProductos;
	}

	public void setArbolProductos(ArbolBinario<Producto> arbolProductos) {
		this.arbolProductos = arbolProductos;
	}

	public ListaEnlazada<MensajeYComentario> getMensajes() {
		return mensajes;
	}

	public void setMensajes(ListaEnlazada<MensajeYComentario> mensajes) {
		this.mensajes = mensajes;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public boolean agregarProducto(Producto producto) {
		return arbolProductos.agregar(producto);
	}

	public ListaEnlazada<Producto> obtenerProductos() {
		ArrayList<Producto> listaPreOrden = new ArrayList<>();
		ListaEnlazada<Producto> listaProductosOrdenados = new ListaEnlazada<Producto>();
		arbolProductos.preorden(listaPreOrden);

		Collections.sort(listaPreOrden, new ComparadorFechas().reversed());

		for (Producto p : listaPreOrden) {
			listaProductosOrdenados.agregar(p);
		}

		return listaProductosOrdenados;
	}

	public int cantidadProductos(ListaEnlazada<Producto> productos) {
		return productos.getLongitud();
	}

	@Override
	public String toString() {
		return nombre;
	}

	@Override
	public int hashCode() {
		return nombre.length() * this.nombre.length();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Vendedor) {
			Vendedor v = (Vendedor) obj;

			return this.nombre.equals(v.getNombre());
		} else {
			return false;
		}

	}

	public void agregarMensaje(String mensaje, Vendedor vendedor) {

		NodoLista<MensajeYComentario> primero = mensajes.getPrimero();
		NodoLista<MensajeYComentario> actual = primero;
		String texto;

		if (mensajes.getLongitud() == 0) {
			MensajeYComentario mensajeDestino = new MensajeYComentario(vendedor, mensaje);
			mensajes.agregar(mensajeDestino);
		} else {
			while (actual != null) {

				if (actual.getDato().getVendedor().equals(vendedor)) {
					texto = actual.getDato().getTexto();
					texto += "\n" + mensaje;
					actual.getDato().setTexto(texto);
				} else {
					if (estaMensajeVendedor(vendedor) == false) {
						MensajeYComentario mensajeDestino = new MensajeYComentario(vendedor, mensaje);
						mensajes.agregar(mensajeDestino);
						break;
					}
				}
				actual = actual.seguirEnlace(0);
			}
		}

	}

	public String buscarMensajeVendedor(Vendedor vendedor) {
		NodoLista<MensajeYComentario> primero = mensajes.getPrimero();
		NodoLista<MensajeYComentario> actual = primero;
		String cadena = "";

		while (actual != null) {
			if (actual.getDato().getVendedor().equals(vendedor)) {
				cadena += actual.getDato().toString();
				// return cadena;
			}
			actual = actual.seguirEnlace(0);
		}
		return cadena;
	}

	public boolean estaMensajeVendedor(Vendedor vendedor) {
		NodoLista<MensajeYComentario> primero = mensajes.getPrimero();
		NodoLista<MensajeYComentario> actual = primero;
		Vendedor vendedor2 = null;
		boolean cent = false;

		while (actual != null) {
			if (actual.getDato().getVendedor().equals(vendedor)) {
				cent = true;
			}
			actual = actual.seguirEnlace(0);
		}

		return cent;
	}
}
