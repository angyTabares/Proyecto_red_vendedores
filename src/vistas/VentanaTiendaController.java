package vistas;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import estructuras.ListaEnlazada;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import logica.ComparadorFechas;
import logica.ComparadorLikes;
import logica.Producto;
import logica.Vendedor;

public class VentanaTiendaController implements Initializable {
	@FXML
	private ComboBox<String> comboFiltro;
	@FXML
	private Label lblTipoFiltro;

	@FXML
	private ListView<BorderPane> listProductos;
	private Main main;
	private Vendedor vendedor;
	private ArrayList<Producto> todosLosPoductos;
	private ListaEnlazada<Vendedor> todosLosVendedores;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		comboFiltro.valueProperty().addListener((obs, oldItem, newItem) -> {
			if (newItem != null) {
				llenarListaPorFiltro(comboFiltro.getValue().toString());
				System.out.println(comboFiltro.getValue().toString());
			}

		});

	}

	public void setPrincipal(Main main, Vendedor vendedor) {
		this.main = main;
		this.vendedor = vendedor;
		this.todosLosVendedores = main.getRed().listaVendedores();
		this.todosLosPoductos = main.getRed().todosLosProductos(this.todosLosVendedores);
		comboFiltro.getItems().addAll("Mas Likes", "Mas recientes");
		listProductos.getItems().clear();
		llenarLista(todosLosPoductos);

	}

	public void llenarListaPorFiltro(String tipoFiltro) {

		switch (tipoFiltro) {
		case "Mas Likes":
			listProductos.getItems().clear();
			Collections.sort(todosLosPoductos, new ComparadorLikes());
			llenarLista(todosLosPoductos);

			break;
		case "Mas recientes":
			listProductos.getItems().clear();
			Collections.sort(todosLosPoductos, new ComparadorFechas().reversed());
			llenarLista(todosLosPoductos);
			break;

		default:
			break;
		}
	}

	public void llenarLista(ArrayList<Producto> lista) {
		for (Producto p : lista) {
			if (!p.getDueño().equals(vendedor)) {
				mostrasProductos(p.getDueño(), p, this.vendedor);
			}
		}
	}

	public void mostrasProductos(Vendedor dueñoProducto, Producto producto, Vendedor vendedor) {
		try {
			FXMLLoader cargador = new FXMLLoader();
			cargador.setLocation(getClass().getResource("PanelProductosContactos.fxml"));

			BorderPane panelProductoContacto = cargador.load();
			PanelProductosContactosController control = cargador.getController();

			control.setPrincipal(main, dueñoProducto, producto, vendedor);
			listProductos.getItems().add(panelProductoContacto);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
