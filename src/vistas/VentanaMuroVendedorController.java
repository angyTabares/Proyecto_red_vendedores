package vistas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import logica.Vendedor;

public class VentanaMuroVendedorController implements Initializable {
	@FXML
	private ImageView btnAgregarProducto;
	@FXML
	private ImageView btnAgregarContacto;
	@FXML
	private ImageView btnVerMensaje;
	@FXML
	private ImageView btnVerTienda;
	@FXML
	private BorderPane center;
	@FXML
	private ListView<BorderPane> productos;
	private Main main;
	private VentanaUsuariosController usuario;
	private Vendedor vendedor;

	// Event Listener on ImageView[#btnAgregarProducto].onMouseClicked
	@FXML
	public void agregarProducto(MouseEvent event) {
		// TODO Autogenerated
		System.out.println(vendedor.getNombre());
		mostrarAgregarProducto(vendedor);
		main.guardarArchivo(Main.ruta);

	}

	// Event Listener on ImageView[#btnAgregarContacto].onMouseClicked
	@FXML
	public void agregarContacto(MouseEvent event) {
		System.out.println(vendedor.getNombre());
		mostrarContactos(vendedor);
	}

	// Event Listener on ImageView[#btnVerMensaje].onMouseClicked
	@FXML
	public void verMensajes(MouseEvent event) {
		// TODO Autogenerated
		mostrarMensajes(vendedor);
	}

	public void verTienda(MouseEvent event) {
		mostrarTienda(vendedor);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		btnVerTienda.setOnMouseClicked(event -> verTienda(event));

	}

	public void setPrincipal(VentanaUsuariosController usuarios, Main main, Vendedor vendedor) {
		// TODO Auto-generated method stub
		this.main = main;
		this.usuario = usuarios;
		this.vendedor = vendedor;

	}

	public void mostrarTienda(Vendedor vendedor) {
		try {
			FXMLLoader cargador = new FXMLLoader();
			cargador.setLocation(getClass().getResource("VentanaTienda.fxml"));

			AnchorPane panelAgregarProductos = cargador.load();
			VentanaTiendaController control = cargador.getController();

			control.setPrincipal(main, vendedor);
			center.setCenter(panelAgregarProductos);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void mostrarAgregarProducto(Vendedor vendedor) {
		try {
			FXMLLoader cargador = new FXMLLoader();
			cargador.setLocation(getClass().getResource("VentanaAgregarProductos.fxml"));

			AnchorPane panelAgregarProductos = cargador.load();
			VentanaAgregarProductosController control = cargador.getController();

			control.setPrincipal(main, this, vendedor);
			center.setCenter(panelAgregarProductos);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void mostrarContactos(Vendedor vendedor) {
		try {
			FXMLLoader cargador = new FXMLLoader();
			cargador.setLocation(getClass().getResource("VentanaContactos.fxml"));

			BorderPane panelContactos = cargador.load();
			VentanaContactosController control = cargador.getController();

			control.setPrincipal(main, this, vendedor);
			center.setCenter(panelContactos);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void mostrarMensajes(Vendedor vendedor) {
		try {
			FXMLLoader cargador = new FXMLLoader();
			cargador.setLocation(getClass().getResource("VentanaMensajes.fxml"));

			BorderPane panelMensajes = cargador.load();
			VentanaMensajesController control = cargador.getController();

			control.setPrincipal(main, this, vendedor);
			center.setCenter(panelMensajes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
