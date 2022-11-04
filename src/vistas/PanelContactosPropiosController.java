package vistas;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import logica.Vendedor;

public class PanelContactosPropiosController implements Initializable {
	@FXML
	private Label lblVendedor;
	@FXML
	private Label lblNombre;
	@FXML
	private ImageView ImageVendedor;

	private Main main;
	private VentanaContactosController muro;
	private Vendedor vendedor;
	private Vendedor vendedor2;

	// Event Listener on ImageView[#btnComentar].onMouseClicked

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}

	public void setPrincipal(Main main, VentanaContactosController muro, Vendedor vendedor, Vendedor vendedor2) {
		// TODO Auto-generated method stub
		this.main = main;
		this.muro = muro;
		this.vendedor = vendedor;
		this.vendedor2= vendedor2;
		lblNombre.setText(vendedor2.getNombre());
	}
	

}
