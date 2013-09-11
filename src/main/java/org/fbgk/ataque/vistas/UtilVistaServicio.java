package org.fbgk.ataque.vistas;

import org.apache.pivot.collections.List;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.Frame;
import org.apache.pivot.wtk.Window;

/**
 * The Interface UtilVistaServicio.
 */
public interface UtilVistaServicio {

	/**
	 * Open frame.
	 * 
	 * @param frame
	 *            the frame
	 * @param display
	 *            the display
	 * @param ownerArgument
	 *            the owner argument
	 * @param objeto
	 *            the objeto
	 * @param archivo
	 *            the archivo
	 * @return the frame
	 */
	Frame openFrame(Frame frame, Display display, Window ownerArgument, Object objeto, String archivo);

	/**
	 * Recuperar lista.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param listaCambiar
	 *            the lista cambiar
	 * @return the list
	 */
	<T> List<T> recuperarLista(java.util.List<T> listaCambiar);

}
