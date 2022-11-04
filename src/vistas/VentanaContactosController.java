package vistas;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import estructuras.ListaEnlazada;
import estructuras.NodoLista;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import logica.Vendedor;

public class VentanaContactosController implements Initializable {
	@FXML
	private Button usuariosRed;
	@FXML
	private Button usuariosSugeridos;
	@FXML
	private Button verContactosPropios;
	@FXML
	private ListView<BorderPane> listUsuarios;
	@FXML
	private ListView<BorderPane> listaContactos;

	private Main main;
	private VentanaMuroVendedorController muro;
	Vendedor vendedor;

	public ListView<BorderPane> getListUsuarios() {
		return listUsuarios;
	}

	public void setListUsuarios(ListView<BorderPane> listUsuarios) {
		this.listUsuarios = listUsuarios;
	}

	public ListView<BorderPane> getListaContactos() {
		return listaContactos;
	}

	public void setListaContactos(ListView<BorderPane> listaContactos) {
		this.listaContactos = listaContactos;
	}

	public void usuariosRed(MouseEvent event) {
		llenarUsuariosRed();

		// llenarlistaContactosPropios();

	}

	public void llenarUsuariosRed() {
		Vendedor vendedor2;
		listUsuarios.getItems().clear();
		ListaEnlazada<Vendedor> lista = main.getRed().listaVendedores();
		ListaEnlazada<Vendedor> lista2 = main.getRed().listaNueva(lista, vendedor);

		NodoLista<Vendedor> primero = lista2.getPrimero();
		NodoLista<Vendedor> actual = primero;

		while (actual != null) {
			if (!actual.getDato().equals(vendedor)) {
				vendedor2 = actual.getDato();
				mostrarRed(vendedor, vendedor2);

			}
			actual = actual.seguirEnlace(0);
		}
	}

	public void usuariosSugeridos(MouseEvent event) {
		llenarSugeridos();
	}

	public void llenarSugeridos() {
		Vendedor vendedor2;
		listUsuarios.getItems().clear();
		ArrayList<Vendedor> listaContactos = main.getRed().obtenerContactos(vendedor);
		ListaEnlazada<ArrayList<Vendedor>> lista = main.getRed().obtenerSugerencias(listaContactos, vendedor);
		ArrayList<Vendedor> listaCont;

		NodoLista<ArrayList<Vendedor>> primero = lista.getPrimero();
		NodoLista<ArrayList<Vendedor>> actual = primero;

		while (actual != null) {
			listaCont = actual.getDato();

			for (int i = 0; i < listaCont.size(); i++) {
				vendedor2 = listaCont.get(i);
				if (!listaContactos.contains(vendedor2)) {
					mostrarSugerencias(vendedor, vendedor2);
				}
			}
			actual = actual.seguirEnlace(0);
		}
	}

	public void contactosPropios(MouseEvent event) {
		listaContactos.getItems().clear();
		llenarlistaContactosPropios();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		usuariosRed.setOnMouseClicked(event -> usuariosRed(event));
		usuariosSugeridos.setOnMouseClicked(event -> usuariosSugeridos(event));
		verContactosPropios.setOnMouseClicked(event -> contactosPropios(event));
	}

	public void setPrincipal(Main main, VentanaMuroVendedorController muro, Vendedor vendedor) {
		this.main = main;
		this.muro = muro;
		this.vendedor = vendedor;
		llenarlistaContactosPropios();
		listaContactos.setFocusTraversable(false);
		listUsuarios.setFocusTraversable(false);

	}

	public void mostrarRed(Vendedor vendedor, Vendedor vendedor2) {
		try {
			FXMLLoader cargador = new FXMLLoader();
			cargador.setLocation(getClass().getResource("PanelContactos.fxml"));

			BorderPane panelContacto = cargador.load();
			PanelContactosController control = cargador.getController();

			control.setPrincipal(main, this, vendedor, vendedor2, 0);
			listUsuarios.getItems().add(panelContacto);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void mostrarSugerencias(Vendedor vendedor, Vendedor vendedor2) {
		try {
			FXMLLoader cargador = new FXMLLoader();
			cargador.setLocation(getClass().getResource("PanelContactos.fxml"));

			BorderPane panelContacto = cargador.load();
			PanelContactosController control = cargador.getController();

			control.setPrincipal(main, this, vendedor, vendedor2, 1);
			listUsuarios.getItems().add(panelContacto);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void mostrarContactosPropios(Vendedor vendedor, Vendedor vendedor2) {
		try {
			FXMLLoader cargador = new FXMLLoader();
			cargador.setLocation(getClass().getResource("PanelContactosPropios.fxml"));

			BorderPane panelContacto = cargador.load();
			PanelContactosPropiosController control = cargador.getController();

			control.setPrincipal(main, this, vendedor, vendedor2);
			listaContactos.getItems().add(panelContacto);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void llenarlistaContactosPropios() {
		Vendedor vendedor2;
		// listUsuarios.getItems().clear();
		ArrayList<Vendedor> listaContactos = main.getRed().obtenerContactos(vendedor);

		for (int i = 0; i < listaContactos.size(); i++) {
			vendedor2 = listaContactos.get(i);
			mostrarContactosPropios(vendedor, vendedor2);
		}
	}

}