package org.fbgk.ataque.actions.base;

import org.fbgk.ataque.actions.AtacadorAutomaticoServicio;
import org.fbgk.ataque.actions.URLActionsServicio;
import org.fbgk.ataque.bbdd.AtaqueDao;

/**
 * The Class AtacadorAutomaticoServicioBase.
 */
public abstract class AtacadorAutomaticoServicioBase implements AtacadorAutomaticoServicio {

	/** The url actions servicio. */
	protected URLActionsServicio	urlActionsServicio;

	/** The ataque dao. */
	protected AtaqueDao				ataqueDao;

	/**
	 * Sets the ataque dao.
	 * 
	 * @param ataqueDao
	 *            the new ataque dao
	 */
	public void setAtaqueDao(final AtaqueDao ataqueDao) {
		this.ataqueDao = ataqueDao;
	}

	/**
	 * Sets the url actions servicio.
	 * 
	 * @param urlActionsServicio
	 *            the new url actions servicio
	 */
	public void setUrlActionsServicio(final URLActionsServicio urlActionsServicio) {
		this.urlActionsServicio = urlActionsServicio;
	}

}
