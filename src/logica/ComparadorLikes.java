package logica;

import java.util.Comparator;

public class ComparadorLikes implements Comparator<Producto> {

	@Override
	public int compare(Producto producto1, Producto producto2) {
		if (producto1.obtenerCantMeGusta() > producto2.obtenerCantMeGusta()) {
			return -1;
		} else {
			if (producto1.obtenerCantMeGusta() < producto2.obtenerCantMeGusta()) {
				return 1;
			}
		}
		return 0;
	}

}
