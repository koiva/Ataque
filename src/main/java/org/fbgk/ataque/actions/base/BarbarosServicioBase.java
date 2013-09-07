package org.fbgk.ataque.actions.base;

import org.fbgk.ataque.actions.BarbarosServicio;
import org.fbgk.ataque.bbdd.AtaqueDao;

/**
 * The Class BarbarosServicioBase.
 */
public abstract class BarbarosServicioBase implements BarbarosServicio {

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
