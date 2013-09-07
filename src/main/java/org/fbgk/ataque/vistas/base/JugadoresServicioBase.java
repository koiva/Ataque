package org.fbgk.ataque.vistas.base;

import org.apache.pivot.wtk.Frame;
import org.fbgk.ataque.bbdd.AtaqueDao;

/**
 * The Class JugadoresServicioBase.
 */
public abstract class JugadoresServicioBase extends Frame {

	/** The ataque dao. */
	protected AtaqueDao	ataqueDao;

	/**
	 * Sets the ataque dao.
	 * 
	 * @param ataqueDao
	 *            the new ataque dao
	 */
	public void setAtaqueDao(final AtaqueDao ataqueDao) {
		this.ataqueDao = ataqueDao;
	}

}
