package org.fbgk.ataque.vistas.impl;

import org.apache.pivot.wtk.Window;
import org.fbgk.ataque.bbdd.AtaqueDao;

/**
 * The Class JugadoresServicioBase.
 */
public abstract class JugadoresServicioBase extends Window {

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
