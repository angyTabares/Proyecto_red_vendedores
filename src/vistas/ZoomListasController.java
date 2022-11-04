package vistas;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ZoomListasController implements Initializable {
	@FXML
	private Label labelLista;
	@FXML
	private TextArea lista;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public void setPrincipal(TextArea lista, String nombre) {
		this.lista.setText(lista.getText());
		labelLista.setText(nombre);
	}

}
