package org.fbgk.ataque.url.dto;

import java.io.File;

import org.apache.http.HttpResponse;

/**
 * The Class RespuestaHTTPDTO. Respuesta de la llamada HTTP.
 */
public class RespuestaHTTPDTO {

	/** The respuesta. */
	private HttpResponse	respuesta;

	/** The estado. */
	private int				estado;

	/** The lectura. */
	private File			lectura;

	/**
	 * Gets the estado.
	 * 
	 * @return the estado
	 */
	public int getEstado() {
		return this.estado;
	}

	/**
	 * Gets the lectura.
	 * 
	 * @return the lectura
	 */
	public File getLectura() {
		return this.lectura;
	}

	/**
	 * Gets the respuesta.
	 * 
	 * @return the respuesta
	 */
	public HttpResponse getRespuesta() {
		return this.respuesta;
	}

	/**
	 * Sets the estado.
	 * 
	 * @param estado
	 *            the new estado
	 */
	public void setEstado(final int estado) {
		this.estado = estado;
	}

	/**
	 * Sets the lectura.
	 * 
	 * @param lectura
	 *            the new lectura
	 */
	public void setLectura(final File lectura) {
		this.lectura = lectura;
	}

	/**
	 * Sets the respuesta.
	 * 
	 * @param respuesta
	 *            the new respuesta
	 */
	public void setRespuesta(final HttpResponse respuesta) {
		this.respuesta = respuesta;
	}

}
