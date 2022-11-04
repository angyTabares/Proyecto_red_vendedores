package vistas;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import estructuras.NodoLista;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import logica.CamposVaciosEx;
import logica.Producto;
import logica.Vendedor;

public class VentanaAgregarProductosController implements Initializable {
	@FXML
	private TextField txtNombre;
	@FXML
	private TextField txtCategoria;
	@FXML
	private TextField txtPrecio;
	@FXML
	private DatePicker calendario;
	@FXML
	private ImageView agregarProducto;
	@FXML
	private ListView<BorderPane> listMisProductos;
	@FXML
	private ListView<BorderPane> listProductosContactos;

	private Main main;
	private VentanaMuroVendedorController muro;
	private Vendedor vendedor;
	public static String ruta = "src/utilidades/persistencia.xml";

	// Event Listener on ImageView[#agregarProducto].onMouseClicked
	@FXML
	public void agregarProducto(MouseEvent event) {
		// TODO Autogenerated

		try {
			String nombre = txtNombre.getText();
			Double precio = Double.parseDouble(txtPrecio.getText());
			String fecha = calendario.getValue().toString();
			String categoria = txtCategoria.getText();
			GregorianCalendar hora = new GregorianCalendar();
			DateFormat horaFormat = new SimpleDateFormat("HH:mm:ss");
			String horaActual = horaFormat.format(hora.getTime());

			if (nombre.trim().equals("") || precio == null || fecha.trim().equals("") || categoria.trim().equals("")) {
				throw new CamposVaciosEx("Por favor llene todos los campos");
			}
			Producto producto = new Producto(nombre, precio, categoria, fecha, horaActual, vendedor);
			if (vendedor.agregarProducto(producto)) {
				main.guardarArchivo(Main.ruta);
				listMisProductos.getItems().clear();
				listProductosContactos.getItems().clear();
				llenarListas();
			}

		} catch (CamposVaciosEx camposEx) {
			main.mostrarNotificacion(camposEx.getMessage(), "../Imagener/alerta.png");
		} catch (NumberFormatException precioEX) { //

			main.mostrarNotificacion("El precio debe estar en numeros", "../Imagener/alerta.png");
		}

	}

	public void prueba(MouseEvent event) {
		String nombre = txtNombre.getText();
		Double precio = Double.parseDouble(txtPrecio.getText());
		String fecha = calendario.getValue().toString();
		String categoria = txtCategoria.getText();
		GregorianCalendar hora = new GregorianCalendar();
		DateFormat horaFormat = new SimpleDateFormat("HH:mm:ss");
		String horaActual = horaFormat.format(hora.getTime());
		Producto producto = new Producto(nombre, precio, categoria, fecha, horaActual, vendedor);
		if (vendedor.agregarProducto(producto)) {
			main.guardarArchivo(ruta);
			listMisProductos.getItems().clear();
			listProductosContactos.getItems().clear();
			llenarListas();
		}

	}

	public void setPrincipal(Main main, VentanaMuroVendedorController muro, Vendedor vendedor) {
		this.main = main;
		this.muro = muro;
		this.vendedor = vendedor;
		listMisProductos.getItems().clear();
		listProductosContactos.getItems().clear();

		llenarListas();

	}

	public void mostrarMisProductos(Vendedor vendedor, Producto producto) {
		try {
			FXMLLoader cargador = new FXMLLoader();
			cargador.setLocation(getClass().getResource("PanelMisProductos.fxml"));

			BorderPane panelMiProducto = cargador.load();
			PanelMisProductosController control = cargador.getController();

			control.setPrincipal(main, vendedor, producto);
			listMisProductos.getItems().add(panelMiProducto);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void mostrasProductosContactos(Vendedor due�oProducto, Producto producto, Vendedor vendedor) {
		try {
			FXMLLoader cargador = new FXMLLoader();
			cargador.setLocation(getClass().getResource("PanelProductosContactos.fxml"));

			BorderPane panelProductoContacto = cargador.load();
			PanelProductosContactosController control = cargador.getController();

			control.setPrincipal(main, due�oProducto, producto, vendedor);
			listProductosContactos.getItems().add(panelProductoContacto);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		calendario.setOnAction(event -> calendario.getValue());
	}

	public void llenarListas() {
		vendedor.obtenerProductos();
		ArrayList<Vendedor> contactos = main.getRed().obtenerContactos(vendedor);
		// System.out.println(contactos.get(0).getNombre());
		NodoLista<Producto> auxMisProductos = vendedor.obtenerProductos().getPrimero();
		NodoLista<Producto> actualMisProductos = auxMisProductos;
		while (actualMisProductos != null) {
			Producto p = actualMisProductos.getDato();
			mostrarMisProductos(vendedor, p);
			actualMisProductos = actualMisProductos.seguirEnlace(0);
		}
		for (Vendedor contacto : contactos) {
			System.out.println(contacto.getNombre());
			NodoLista<Producto> auxProductosContacto = contacto.obtenerProductos().getPrimero();
			NodoLista<Producto> actualProductosContacto = auxProductosContacto;
			while (actualProductosContacto != null) {
				Producto p = actualProductosContacto.getDato();
				System.out.println(p);
				mostrasProductosContactos(contacto, p, vendedor);
				actualProductosContacto = actualProductosContacto.seguirEnlace(0);
			}
		}
	}
}