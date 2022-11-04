package logica;

import java.util.Comparator;

public class ComparadorFechas implements Comparator<Producto> {

	@Override
	public int compare(Producto producto1, Producto producto2) {
		// TODO Auto-generated method stub
		return (producto1.getFecha() + " - " + producto1.getHora())
				.compareTo(producto2.getFecha() + " - " + producto2.getHora());

	}

}
