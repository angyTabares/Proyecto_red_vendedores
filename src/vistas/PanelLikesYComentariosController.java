package vistas;

import java.net.URL;
import java.util.ResourceBundle;

import estructuras.NodoLista;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import logica.MensajeYComentario;
import logica.Producto;
import logica.Vendedor;

public class PanelLikesYComentariosController implements Initializable {
	@FXML
	private Label lblNombreProducto;
	@FXML
	private TextArea txtLikes;
	@FXML
	private TextArea txtComentarios;

	private Main main;

	private Producto producto;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public void setPrincipal(Main main, Producto producto) {

		this.main = main;
		this.producto = producto;
		System.out.println(this.producto.getNombre());
		lblNombreProducto.setText(producto.getNombre());

		NodoLista<MensajeYComentario> primerComentario = this.producto.getComentarios().getPrimero();
		NodoLista<MensajeYComentario> comentarioActual = primerComentario;
		String comentarios = "";
		String likes = "";
		while (comentarioActual != null) {
			comentarios += comentarioActual.getDato().toString();
			comentarioActual = comentarioActual.seguirEnlace(0);
		}
		txtComentarios.setText(comentarios);

		NodoLista<Vendedor> primerLike = this.producto.getMeGusta().getPrimero();
		NodoLista<Vendedor> likeActual = primerLike;
		while (likeActual != null) {
			System.out.println(likeActual.getDato().getNombre());
			likes += likeActual.getDato().getNombre() + "\n";
			likeActual = likeActual.seguirEnlace(0);
		}
		txtLikes.setText(likes);

	}

}
