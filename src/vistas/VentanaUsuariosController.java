package vistas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import estructuras.NodoLista;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import logica.Vendedor;

public class VentanaUsuariosController implements Initializable {
	@FXML
	private ListView<Button> listUsuarios;
	@FXML
	private ImageView btnAtras;
	@FXML
	private BorderPane center;
	@FXML
	private Label tituloVentana;
	private Pane panelMuro;
	private Main main;
	private VentanaInicialController inicial;
	private Stage prueba;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}

	public void setPrincipal(Main main, VentanaInicialController inicial) {
		// TODO Auto-generated method stub
		this.inicial = inicial;
		this.main = main;
		tituloVentana.setText("Vendedores");
		NodoLista<Vendedor> nodo = main.getRed().listaVendedores().getPrimero();
		NodoLista<Vendedor> actual = nodo;

		while (actual != null) {
			Vendedor v = actual.getDato();
			Button botonUsuario = new Button();
			Scene scene = new Scene(botonUsuario);
			botonUsuario.setText(v.getNombre());
			botonUsuario.setStyle("-fx-text-fill: White; -fx-background-color: #F1C40F;-fx-text-fill: Black");

			botonUsuario.prefWidth(170.0);
			botonUsuario.prefHeight(170.0);
			botonUsuario.setMaxWidth(170.0);

			botonUsuario.setStyle("-fx-text-fill: White ");
			botonUsuario.setTextFill(Color.WHITE);
			botonUsuario.setOnMouseClicked(event -> abrirMuro(v));

			// botonUsuario.addEventHandler(MouseEvent.MOUSE_CLICKED,
			// abrirMuro(actual.getDato()));
			listUsuarios.getItems().add(botonUsuario);
			actual = actual.seguirEnlace(0);
		}

	}

	public void abrirMuro(Vendedor p) {
		try {
			FXMLLoader cargador = new FXMLLoader();
			cargador.setLocation(getClass().getResource("VentanaMuroVendedor.fxml"));

			BorderPane vistaMuroVendedor = cargador.load();
			VentanaMuroVendedorController control = cargador.getController();

			control.setPrincipal(this, main, p);
			center.setCenter(vistaMuroVendedor);
			tituloVentana.setText("Hola, " + p.getNombre());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
