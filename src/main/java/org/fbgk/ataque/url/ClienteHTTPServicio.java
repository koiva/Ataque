package org.fbgk.ataque.url;

import org.fbgk.ataque.url.dto.EjecutarHTTPDTO;
import org.fbgk.ataque.url.dto.RespuestaHTTPDTO;

/**
 * The Interface ClienteHTTPServicio.
 */
public interface ClienteHTTPServicio {

	/**
	 * Ejecutar get. Ejecuta una sentencia GET y devuelve la respuesta.
	 * 
	 * @param ejecutarHTTPDTO
	 *            the ejecutar httpdto
	 * @return the respuesta httpdto
	 */
	public RespuestaHTTPDTO ejecutarGet(EjecutarHTTPDTO ejecutarHTTPDTO);

	/**
	 * Ejecutar post.Ejecuta una sentencia POST y devuelve la respuesta.
	 * 
	 * @param ejecutarHTTPDTO
	 *            the ejecutar httpdto
	 * @return the respuesta httpdto
	 */
	public RespuestaHTTPDTO ejecutarPost(EjecutarHTTPDTO ejecutarHTTPDTO);

	/**
	 * Inits the.
	 */
	public void init();

}
