package org.fbgk.ataque.actions.base;

import org.fbgk.ataque.actions.AtacadorAutomaticoServicio;
import org.fbgk.ataque.actions.LectorInformesServicio;
import org.fbgk.ataque.actions.URLActionsServicio;
import org.fbgk.ataque.bbdd.AtaqueDao;

/**
 * The Class AtacadorAutomaticoServicioBase.
 */
public abstract class AtacadorAutomaticoServicioBase implements AtacadorAutomaticoServicio {

	/** The ataque dao. */
	protected AtaqueDao					ataqueDao;

	/** The lector informes servicio. */
	protected LectorInformesServicio	lectorInformesServicio;

	/** The url actions servicio. */
	protected URLActionsServicio		urlActionsServicio;

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
	 * Sets the lector informes servicio.
	 * 
	 * @param lectorInformesServicio
	 *            the new lector informes servicio
	 */
	public void setLectorInformesServicio(final LectorInformesServicio lectorInformesServicio) {
		this.lectorInformesServicio = lectorInformesServicio;
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
