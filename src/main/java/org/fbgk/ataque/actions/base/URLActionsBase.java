package org.fbgk.ataque.actions.base;

import org.fbgk.ataque.actions.MapeoGuerrasTribalesServicio;
import org.fbgk.ataque.actions.URLActionsServicio;
import org.fbgk.ataque.bbdd.AtaqueDao;
import org.fbgk.ataque.url.ClienteHTTPServicio;

/**
 * The Class URLActionsBase.
 */
public abstract class URLActionsBase implements URLActionsServicio {

	/** The ataque dao. */
	protected AtaqueDao				ataqueDao;

	/** The cliente http servicio. */
	protected ClienteHTTPServicio	clienteHTTPServicio;

	/** The mapeo guerras tribales. */
	protected MapeoGuerrasTribalesServicio	mapeoGuerrasTribales;

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
	 * Sets the cliente http servicio.
	 * 
	 * @param clienteHTTPServicio
	 *            the new cliente http servicio
	 */
	public void setClienteHTTPServicio(final ClienteHTTPServicio clienteHTTPServicio) {
		this.clienteHTTPServicio = clienteHTTPServicio;
	}

	/**
	 * Sets the mapeo guerras tribales.
	 * 
	 * @param mapeoGuerrasTribales
	 *            the new mapeo guerras tribales
	 */
	public void setMapeoGuerrasTribales(final MapeoGuerrasTribalesServicio mapeoGuerrasTribales) {
		this.mapeoGuerrasTribales = mapeoGuerrasTribales;
	}

}
