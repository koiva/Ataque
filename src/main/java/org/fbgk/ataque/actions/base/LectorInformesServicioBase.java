package org.fbgk.ataque.actions.base;

import org.apache.commons.configuration.Configuration;
import org.fbgk.ataque.actions.LectorInformesServicio;
import org.fbgk.ataque.actions.URLActionsServicio;
import org.fbgk.ataque.bbdd.AtaqueDao;
import org.fbgk.ataque.url.ClienteHTTPServicio;

/**
 * The Class LectorInformesServicioBase.
 */
public abstract class LectorInformesServicioBase implements LectorInformesServicio {

	/** The ataque dao. */
	protected AtaqueDao				ataqueDao;

	/** The cliente http servicio. */
	protected ClienteHTTPServicio	clienteHTTPServicio;

	/** The configuration. */
	protected Configuration			configuration;

	/** The url actions servicio. */
	protected URLActionsServicio	urlActionsServicio;

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
	 * Sets the configuration.
	 * 
	 * @param configuration
	 *            the new configuration
	 */
	public void setConfiguration(final Configuration configuration) {
		this.configuration = configuration;
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
