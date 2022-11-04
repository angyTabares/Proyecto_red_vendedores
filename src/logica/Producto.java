package logica;

import java.io.Serializable;

import estructuras.ListaEnlazada;

public class Producto implements Serializable, Comparable<Producto> {

	private String nombre;
	private double precio;
	private String categoria;
	private String fecha;
	private String hora;
	private Vendedor dueño;
	private ListaEnlazada<MensajeYComentario> comentarios;
	private ListaEnlazada<Vendedor> meGusta;

	public Producto(String nombre, double precio, String categoria, String fecha, String hora, Vendedor dueño) {

		this.nombre = nombre;
		this.precio = precio;
		this.categoria = categoria;
		this.fecha = fecha;
		this.hora = hora;
		this.dueño = dueño;
		this.comentarios = new ListaEnlazada<MensajeYComentario>();
		this.meGusta = new ListaEnlazada<Vendedor>();
	}

	public Producto() {

	}

	public Vendedor getDueño() {
		return dueño;
	}

	public void setDueño(Vendedor dueño) {
		this.dueño = dueño;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public ListaEnlazada<MensajeYComentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(ListaEnlazada<MensajeYComentario> comentarios) {
		this.comentarios = comentarios;
	}

	public ListaEnlazada<Vendedor> getMeGusta() {
		return meGusta;
	}

	public void setMeGusta(ListaEnlazada<Vendedor> meGusta) {
		this.meGusta = meGusta;
	}

	@Override
	public String toString() {
		return "Nombre: " + nombre + "\n" + "Precio: " + precio + "\n" + "Categoria: " + categoria + "\n";
	}

	public int obtenerCantMeGusta() {
		return meGusta.getLongitud();
	}

	public int obtenerCantComentarios() {
		return comentarios.getLongitud();
	}

	@Override
	public int compareTo(Producto arg0) {
		// TODO Auto-generated method stub

		return this.getNombre().compareToIgnoreCase(arg0.getNombre());
	}

}
