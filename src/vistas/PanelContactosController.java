package vistas;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import logica.Vendedor;

public class PanelContactosController implements Initializable {
	@FXML
	private Label lblVendedor;
	@FXML
	private Label lblNombre;
	@FXML
	private ImageView Imagenvendedor;
	@FXML
	private Button agregar;
	private int tipo;
	private Main main;
	private VentanaContactosController muro;
	private Vendedor vendedor;
	private Vendedor vendedor2;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		agregar.setOnMouseClicked(event -> agregarARedPropia(event));
	}

	public void setPrincipal(Main main, VentanaContactosController muro, Vendedor vendedor, Vendedor vendedor2,
			int tipo) {
		// TODO Auto-generated method stub
		this.main = main;
		this.muro = muro;
		this.vendedor = vendedor;
		this.vendedor2 = vendedor2;
		this.tipo = tipo;
		lblNombre.setText(vendedor2.getNombre());
	}

	public void agregarARedPropia(MouseEvent event) {
		main.getRed().Conectar(vendedor, vendedor2);
		main.guardarArchivo(Main.ruta);

		main.mostrarNotificacion(vendedor2 + " se agregó a la red de " + vendedor, "../Imagener/etiqueta.png");

		muro.getListUsuarios().getItems().clear();
		muro.getListaContactos().getItems().clear();
		if (tipo == 0) {
			muro.llenarUsuariosRed();
		} else {
			muro.llenarSugeridos();
		}
		muro.llenarlistaContactosPropios();

	}

}