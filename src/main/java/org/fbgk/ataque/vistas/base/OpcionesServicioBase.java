package org.fbgk.ataque.vistas.base;

import org.apache.commons.configuration.Configuration;
import org.fbgk.ataque.vistas.OpcionesServicio;
import org.fbgk.ataque.vistas.UtilVistaServicio;

/**
 * The Class OpcionesServicioBase.
 */
public abstract class OpcionesServicioBase implements OpcionesServicio {

	/** The configuration. */
	protected Configuration		configuration;

	/** The util vista servicio. */
	protected UtilVistaServicio	utilVistaServicio;

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
	 * Sets the util vista servicio.
	 * 
	 * @param utilVistaServicio
	 *            the new util vista servicio
	 */
	public void setUtilVistaServicio(final UtilVistaServicio utilVistaServicio) {
		this.utilVistaServicio = utilVistaServicio;
	}
}
