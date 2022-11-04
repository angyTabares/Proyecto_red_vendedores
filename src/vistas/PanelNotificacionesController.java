package vistas;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PanelNotificacionesController implements Initializable {
	@FXML
	private Label lblNotificacion;
	@FXML
	private ImageView imageNotificacion;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public void setPrincipal(String notificacion, String imagen) {
		Image imagenNotificacion = new Image(getClass().getResourceAsStream(imagen));
		imageNotificacion.setImage(imagenNotificacion);
		lblNotificacion.setText(notificacion);

	}

}
