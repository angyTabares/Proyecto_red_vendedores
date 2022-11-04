package persistencia;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Persistencia {

	/**
	 * Metodo para serializar en XML (almacenarlos dentro del disco duro)
	 * 
	 * @param nombre Direccion donde se guardara el archivo (debera poner el nombre
	 *               del archivo con extencion XML)
	 * @param objeto objecto que sera serializado (almacenado)
	 * @throws FileNotFoundException En caso de no existir la direccion.
	 */
	public static void serializarObjetoXML(String nombre, Object objeto) throws FileNotFoundException {
		XMLEncoder codificador = new XMLEncoder(new FileOutputStream(nombre));
		codificador.writeObject(objeto);
		codificador.close();
	}

	/**
	 * Metodo para deserializar en XML (extraerlos del disco duro)
	 * 
	 * @param nombre Direccion de donde sera extraido el archivo (debera poner el
	 *               nombre del archivo con extencion XML)
	 * @return Objecto deserializado.
	 * @throws FileNotFoundException En caso de no existir el objeto.
	 */
	public static Object deserializarObjetoXML(String nombre) throws FileNotFoundException {
		XMLDecoder decodificador = new XMLDecoder(new FileInputStream(nombre));
		Object objeto;

		objeto = decodificador.readObject();
		decodificador.close();

		return objeto;
	}
}
