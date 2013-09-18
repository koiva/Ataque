package org.fbgk.ataque.vistas.base;

import org.fbgk.ataque.actions.BarbarosServicio;
import org.fbgk.ataque.bbdd.AtaqueDao;
import org.fbgk.ataque.vistas.GestionDistanciaMaxServicio;
import org.fbgk.ataque.vistas.UtilVistaServicio;

/**
 * The Class GestionAtaqueServicioBase.
 */
public abstract class GestionDistanciaMaxServicioBase implements GestionDistanciaMaxServicio {

	/** The ataque dao. */
	protected AtaqueDao			ataqueDao;

	/** The barbaros servicio. */
	protected BarbarosServicio	barbarosServicio;

	/** The util vista servicio. */
	protected UtilVistaServicio	utilVistaServicio;

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
	 * Sets the barbaros servicio.
	 * 
	 * @param barbarosServicio
	 *            the new barbaros servicio
	 */
	public void setBarbarosServicio(final BarbarosServicio barbarosServicio) {
		this.barbarosServicio = barbarosServicio;
	}

	/**
	 * Sets the util vista servicio.
	 * 
	 * @param utilVistaServicio
	 *            the new util vista servicio
	 */
	public void setUtilVistaServicio(final UtilVistaServicio utilVistaServicio) {
		this.utilVistaServicio = utilVistaServicio;
	}

}
