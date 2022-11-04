package vistas;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logica.Producto;
import logica.Vendedor;

public class PanelMisProductosController implements Initializable {
	@FXML
	private Label lblNombre;
	@FXML
	private Label lblFechaYhora;
	@FXML
	private TextArea txtDatos;
	@FXML
	private Button btnVerLikes;
	@FXML
	private Button btnVerComentarios;
	@FXML
	private ImageView misLikes;
	@FXML
	private ImageView misComents;

	private Main main;
	private Vendedor vendedor;
	private Producto producto;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		misLikes.setOnMouseClicked(event -> verMisLikes(event));
		misComents.setOnMouseClicked(event -> verMisComents(event));

	}

	@FXML
	public void verMisLikes(MouseEvent event) {
		System.out.println("Hola");
		mostrarDetalleProducto(this.producto);
	}

	@FXML
	public void verMisComents(MouseEvent event) {
		System.out.println("Hola");
		mostrarDetalleProducto(this.producto);
	}

	public void setPrincipal(Main main, Vendedor vendedor, Producto producto) {
		this.main = main;
		this.vendedor = vendedor;
		this.producto = producto;
		lblNombre.setText(this.producto.getNombre());
		lblFechaYhora.setText(this.producto.getFecha() + " - " + this.producto.getHora());
		txtDatos.setText(producto.toString());
	}

	public void mostrarDetalleProducto(Producto p) {
		try {
			System.out.println("Click" + p.getNombre());
			FXMLLoader cargador = new FXMLLoader();
			cargador.setLocation(getClass().getResource("PanelLikesYComentarios.fxml"));
			BorderPane vista = (BorderPane) cargador.load();

			PanelLikesYComentariosController control = cargador.getController();
			control.setPrincipal(main, p);

			Stage escenario = new Stage();
			escenario.initModality(Modality.WINDOW_MODAL);
			escenario.initOwner(main.getEscenarioPrincipal());

			Scene escena = new Scene(vista);
			escenario.setScene(escena);

			escenario.showAndWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
