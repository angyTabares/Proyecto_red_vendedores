package vistas;

import java.net.URL;
import java.util.ResourceBundle;

import estructuras.ListaEnlazada;
import estructuras.NodoLista;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import logica.Vendedor;

public class VentanaMensajesController implements Initializable {
	@FXML
	private TextArea areaConversacion;
	@FXML
	private TextField txtMensaje;
	@FXML
	private Button enviar;

	@FXML
	private Label lblNombre;
	@FXML
	private ListView<Button> listaUsuarios;

	private Main main;
	private Vendedor vendedor;
	private VentanaMuroVendedorController muro;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}

	public void setPrincipal(Main main, VentanaMuroVendedorController muro, Vendedor vendedor) {
		this.main = main;
		this.muro = muro;
		this.vendedor = vendedor;

		// Vendedor vendedor2;
		ListaEnlazada<Vendedor> lista = main.getRed().listaVendedores();

		NodoLista<Vendedor> primero = lista.getPrimero();
		NodoLista<Vendedor> actual = primero;

		while (actual != null) {
			if (!actual.getDato().equals(vendedor)) {
				Vendedor vendedor2 = actual.getDato();

				Button botonUsuario = new Button();
				botonUsuario.setText(vendedor2.getNombre());
				botonUsuario.setStyle("-fx-text-fill: White; -fx-background-color: #E80505;-fx-text-fill: Black");

				botonUsuario.prefWidth(170.0);
				botonUsuario.prefHeight(170.0);
				botonUsuario.setMaxWidth(170.0);
				botonUsuario.setStyle("-fx-text-fill: White ");
				botonUsuario.setTextFill(Color.WHITE);
				areaConversacion.setText("");
				txtMensaje.setText("");
				botonUsuario.setOnMouseClicked(event -> abrirChat(vendedor2));

				listaUsuarios.getItems().add(botonUsuario);
			}
			actual = actual.seguirEnlace(0);
		}
	}

	public void abrirChat(Vendedor vendedor2) {
		lblNombre.setText(vendedor2.getNombre());
		areaConversacion.setText("");
		if (vendedor2.estaMensajeVendedor(vendedor) == true || vendedor.estaMensajeVendedor(vendedor2) == true) {
			areaConversacion.setText(vendedor.buscarMensajeVendedor(vendedor2));
			System.out.println(vendedor.buscarMensajeVendedor(vendedor2));
		}
		enviar.setOnMouseClicked(event -> enviar(vendedor2));
	}

	public void enviar(Vendedor vendedor2) {

		vendedor2.agregarMensaje(txtMensaje.getText(), vendedor);
		main.guardarArchivo(main.ruta);
		areaConversacion.setText(txtMensaje.getText());
		txtMensaje.setText("");
	}

}
