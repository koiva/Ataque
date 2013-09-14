package org.fbgk.ataque.vistas.base;

import org.apache.pivot.wtk.Application;
import org.apache.pivot.wtk.Window;
import org.fbgk.ataque.actions.URLActionsServicio;
import org.fbgk.ataque.transformacion.TransformacionServicio;
import org.fbgk.ataque.vistas.GestionAtaquesServicio;
import org.fbgk.ataque.vistas.JugadoresServicio;
import org.fbgk.ataque.vistas.ServidorServicio;
import org.fbgk.ataque.vistas.impl.JugadoresServicioImpl;

/**
 * The Class PantallaPrincipalBase.
 */
public abstract class PantallaPrincipalBase extends Window implements Application {

	/** The jugadores servicio. */
	protected JugadoresServicio			jugadoresServicio;

	/** The transformacion servicio. */
	protected TransformacionServicio	transformacionServicio;

	/** The url actions servicio. */
	protected URLActionsServicio		urlActionsServicio;

	/** The servidor servicio. */
	protected ServidorServicio			servidorServicio;

	/** The gestion ataques servicio. */
	protected GestionAtaquesServicio	gestionAtaquesServicio;

	/** The window. */
	protected Window					window;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.pivot.wtk.Application#resume()
	 */
	public void resume() throws Exception {

	}

	/**
	 * Sets the gestion ataques servicio.
	 * 
	 * @param gestionAtaquesServicio
	 *            the new gestion ataques servicio
	 */
	public void setGestionAtaquesServicio(final GestionAtaquesServicio gestionAtaquesServicio) {
		this.gestionAtaquesServicio = gestionAtaquesServicio;
	}

	/**
	 * Sets the jugadores servicio.
	 * 
	 * @param jugadoresServicio
	 *            the new jugadores servicio
	 */
	public void setJugadoresServicio(final JugadoresServicio jugadoresServicio) {
		this.jugadoresServicio = jugadoresServicio;
	}

	/**
	 * Sets the jugadores servicio.
	 * 
	 * @param jugadoresServicio
	 *            the new jugadores servicio
	 */
	public void setJugadoresServicio(final JugadoresServicioImpl jugadoresServicio) {
		this.jugadoresServicio = jugadoresServicio;
	}

	/**
	 * Sets the servidor servicio.
	 * 
	 * @param servidorServicio
	 *            the new servidor servicio
	 */
	public void setServidorServicio(final ServidorServicio servidorServicio) {
		this.servidorServicio = servidorServicio;
	}

	/**
	 * Sets the transformacion servicio.
	 * 
	 * @param transformacionServicio
	 *            the new transformacion servicio
	 */
	public void setTransformacionServicio(final TransformacionServicio transformacionServicio) {
		this.transformacionServicio = transformacionServicio;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.pivot.wtk.Application#shutdown(boolean)
	 */
	public boolean shutdown(final boolean arg0) throws Exception {
		this.window.close();
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.pivot.wtk.Application#suspend()
	 */
	public void suspend() throws Exception {

	}
}
