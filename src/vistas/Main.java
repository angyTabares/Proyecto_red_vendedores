package vistas;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logica.Red;
import persistencia.Persistencia;

public class Main extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	private Red red;
	public static String ruta = "src/persistencia.xml";

	@Override
	public void start(Stage primaryStage) throws Exception {
		realizarCarga(ruta);
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Red");
		if (red == null) {
			red = new Red();
		}
		initRootLayout();
		mostraVentanaIncial();
		// mostraVistaRegistro();

	}

	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("rootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			// primaryStage.setFullScreen(true);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void mostraVistaRegistro() {
		try {
			FXMLLoader cargador = new FXMLLoader();
			cargador.setLocation(getClass().getResource("VentanaCrearVendedor.fxml"));

			BorderPane vistaCrearVendedor = cargador.load();
			VentanaCrearVendedorController control = cargador.getController();
			control.setPrincipal(this);
			rootLayout.setCenter(vistaCrearVendedor);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void mostraVentanaIncial() {
		try {
			FXMLLoader cargador = new FXMLLoader();
			cargador.setLocation(getClass().getResource("VentanaInicial.fxml"));

			BorderPane vistaCrearVendedor = cargador.load();
			VentanaInicialController control = cargador.getController();
			control.setPrincipal(this);
			rootLayout.setCenter(vistaCrearVendedor);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void mostrarNotificacion(String notificacion, String imagen) {
		try {
			FXMLLoader cargador = new FXMLLoader();
			cargador.setLocation(getClass().getResource("PanelNotificaciones.fxml"));
			AnchorPane vista = (AnchorPane) cargador.load();
			PanelNotificacionesController control = cargador.getController();
			control.setPrincipal(notificacion, imagen);
			Stage escenario = new Stage();
			escenario.initModality(Modality.WINDOW_MODAL);
			escenario.initOwner(this.getEscenarioPrincipal());
			Scene escena = new Scene(vista);
			escenario.setScene(escena);
			escenario.showAndWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public Stage getEscenarioPrincipal() {
		return primaryStage;
	}

	public Red getRed() {
		return red;
	}

	public void realizarCarga(String ruta) {
		System.out.println("cargando");
		Red red = null;
		File fOrig;
		fOrig = new File(ruta);
		if (fOrig.exists()) {
			try {
				red = (Red) Persistencia.deserializarObjetoXML(ruta);

			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			this.red = red;
		}
	}

	public void guardarArchivo(String ruta) {
		try {
			Persistencia.serializarObjetoXML(ruta, red);
			System.out.println("Guardando");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
