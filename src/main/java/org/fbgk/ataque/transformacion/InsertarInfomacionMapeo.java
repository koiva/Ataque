package org.fbgk.ataque.transformacion;

import org.fbgk.ataque.guerrastribales.dto.ServidorDTO;

/**
 * The Interface InsertarInfomacionMapeo.
 */
public interface InsertarInfomacionMapeo {

	/**
	 * Insertar mapeo. Para que la parte de insertar sea dinamica se crea esta
	 * interface
	 * 
	 * @param valores
	 *            the valores
	 */
	public void insertarMapeo(String[] valores, ServidorDTO servidorDTO);

}
