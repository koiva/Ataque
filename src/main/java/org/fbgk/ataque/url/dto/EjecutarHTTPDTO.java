package org.fbgk.ataque.url.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class EjecutarHTTPDTO. DTO que describe el funcionamiento de una
 */
public class EjecutarHTTPDTO {

	/** The url. */
	private String				url;

	/** The parametros. */
	private Map<String, String>	parametros		= new HashMap<String, String>();

	/** The ind respuesta. */
	private boolean				indRespuesta	= true;

	/** The game. */
	private String				game;

	/** The servidor. */
	private String				servidor;

	/** The cookie. */
	private Boolean				cookie			= Boolean.FALSE;

	/**
	 * Instantiates a new ejecutar httpdto.
	 */
	public EjecutarHTTPDTO() {
	}

	/**
	 * Instantiates a new ejecutar httpdto.
	 * 
	 * @param url
	 *            the url
	 * @param parametros
	 *            the parametros
	 * @param indRespuesta
	 *            the ind respuesta
	 * @param game
	 *            the game
	 * @param servidor
	 *            the servidor
	 */
	public EjecutarHTTPDTO(final String url, final Map<String, String> parametros, final boolean indRespuesta, final String game, final String servidor, final Boolean cookie) {
		super();
		this.url = url;
		this.parametros = parametros;
		this.indRespuesta = indRespuesta;
		this.game = game;
		this.servidor = servidor;
		this.cookie = cookie;
	}

	/**
	 * Gets the cookie.
	 * 
	 * @return the cookie
	 */
	public Boolean getCookie() {
		return this.cookie;
	}

	/**
	 * Gets the game.
	 * 
	 * @return the game
	 */
	public String getGame() {
		return this.game;
	}

	/**
	 * Gets the parametros.
	 * 
	 * @return the parametros
	 */
	public Map<String, String> getParametros() {
		return this.parametros;
	}

	/**
	 * Gets the servidor.
	 * 
	 * @return the servidor
	 */
	public String getServidor() {
		return this.servidor;
	}

	/**
	 * Gets the url.
	 * 
	 * @return the url
	 */
	public String getUrl() {
		return this.url;
	}

	/**
	 * Checks if is ind respuesta.
	 * 
	 * @return true, if is ind respuesta
	 */
	public boolean isIndRespuesta() {
		return this.indRespuesta;
	}

	/**
	 * Sets the cookie.
	 * 
	 * @param cookie
	 *            the new cookie
	 */
	public void setCookie(final Boolean cookie) {
		this.cookie = cookie;
	}

	/**
	 * Sets the game.
	 * 
	 * @param game
	 *            the new game
	 */
	public void setGame(final String game) {
		this.game = game;
	}

	/**
	 * Sets the ind respuesta.
	 * 
	 * @param indRespuesta
	 *            the new ind respuesta
	 */
	public void setIndRespuesta(final boolean indRespuesta) {
		this.indRespuesta = indRespuesta;
	}

	/**
	 * Sets the parametros.
	 * 
	 * @param parametros
	 *            the parametros
	 */
	public void setParametros(final Map<String, String> parametros) {
		this.parametros = parametros;
	}

	/**
	 * Sets the servidor.
	 * 
	 * @param servidor
	 *            the new servidor
	 */
	public void setServidor(final String servidor) {
		this.servidor = servidor;
	}

	/**
	 * Sets the url.
	 * 
	 * @param url
	 *            the new url
	 */
	public void setUrl(final String url) {
		this.url = url;
	}
}
