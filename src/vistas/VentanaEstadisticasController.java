package vistas;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.SortedSet;
import java.util.TreeSet;

import estructuras.NodoLista;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logica.ComparadorLikes;
import logica.Producto;
import logica.Vendedor;
import utilidades.AutoCompleteTextField;

public class VentanaEstadisticasController implements Initializable {
	@FXML
	private AnchorPane center;
	@FXML
	private TextField numeroPorUsuario;
	@FXML
	private TextField numeroPorFecha;
	@FXML
	private TextArea top10;
	@FXML
	private TextArea mensajesEnviados;
	@FXML
	private TextArea cantidadContactos;
	@FXML
	private DatePicker fecha;
	@FXML
	private Button buscar;

	private AutoCompleteTextField<Vendedor> vendedoresRed;
	private Main main;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		fecha.setOnAction(event -> cantidadProductosXFecha(fecha.getValue().toString()));
		top10.setOnMouseClicked(event -> abrirListaZoom(event, 1));
		cantidadContactos.setOnMouseClicked(event -> abrirListaZoom(event, 2));
		mensajesEnviados.setOnMouseClicked(event -> abrirListaZoom(event, 3));

	}

	private void abrirListaZoom(MouseEvent event, int i) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			if (event.getClickCount() == 2) {
				if (i == 1) {
					zoomLista(top10, "Top 10");
				} else {
					if (i == 2) {
						zoomLista(cantidadContactos, "Cantidad de Contactos");
					} else {
						zoomLista(mensajesEnviados, "Mensajes Enviados");
					}
				}
				System.out.println("Doble");
			}
		}
	}

	public void cantidadProductosXFecha(String fecha) {
		ArrayList<Producto> listaProductos = main.getRed().todosLosProductos(main.getRed().listaVendedores());
		int cantidad = main.getRed().cantProductosPublicadosXFecha(listaProductos, fecha);
		numeroPorFecha.setText("" + cantidad);
	}

	public void cantidadProductosVendedor() {
		Vendedor vendedor = vendedoresRed.getLastSelectedObject();
		int cantidad = main.getRed().cantidadProductosPorXUsuario(vendedor);
		numeroPorUsuario.setText("" + cantidad);
	}

	public void mensajesEntreUsuarios() {

		String texto = main.getRed().infocantidadMensajesEnviadosEntreUsuarios(main.getRed().listaVendedores());
		mensajesEnviados.setText(texto);
	}

	public void cantidadContactos() {
		String cantidad = main.getRed().infoCantidadContactosPorUsuario(main.getRed().listaVendedores());
		cantidadContactos.setText(cantidad);
	}

	public void top10() {
		String top = "";
		int cont = 0;
		ArrayList<Producto> lista = main.getRed().todosLosProductos(main.getRed().listaVendedores());
		Collections.sort(lista, new ComparadorLikes());
		for (Producto p : lista) {
			if (cont < 10) {
				top += (cont + 1) + ") " + p.getNombre() + "." + "\n";
				cont++;
			} else {
				break;
			}
		}

		top10.setText(top);

	}

	private void autocomplete() {
		SortedSet<Vendedor> entries = new TreeSet<>(
				(Vendedor o1, Vendedor o2) -> o1.toString().compareTo(o2.toString()));
		NodoLista<Vendedor> nodoInicial = main.getRed().listaVendedores().getPrimero();
		NodoLista<Vendedor> nodoActual = nodoInicial;

		while (nodoActual != null) {
			entries.add(nodoActual.getDato());
			nodoActual = nodoActual.seguirEnlace(0);
		}

		vendedoresRed = new AutoCompleteTextField<Vendedor>(entries);

		vendedoresRed.getEntryMenu().setOnAction(e -> {
			((MenuItem) e.getTarget()).addEventHandler(Event.ANY, event -> {
				if (vendedoresRed.getLastSelectedObject() != null) {
					vendedoresRed.setText(vendedoresRed.getLastSelectedObject().getNombre());

				}
			});
		});

		vendedoresRed.setPrefWidth(175);
		vendedoresRed.setPrefHeight(25);
		vendedoresRed.setPromptText("Ingrese el nombre de Usuario");
		center.getChildren().add(vendedoresRed);
		vendedoresRed.setLayoutX(328);
		vendedoresRed.setLayoutY(76);

	}

	public void setPrinicipal(Main main) {
		this.main = main;
		mensajesEntreUsuarios();
		top10();
		cantidadContactos();
		autocomplete();

	}

	public void zoomLista(TextArea lista, String nombre) {
		try {
			FXMLLoader cargador = new FXMLLoader();
			cargador.setLocation(getClass().getResource("ZoomListas.fxml"));
			AnchorPane vista = (AnchorPane) cargador.load();
			ZoomListasController control = cargador.getController();
			control.setPrincipal(lista, nombre);
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
