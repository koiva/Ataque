package org.fbgk.ataque.url.base;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.fbgk.ataque.url.ClienteHTTPServicio;

/**
 * The Class ClienteHTTPServicioBase. Esta clase llevara la parte de creacion de
 * las URL y puede ejecutar los procesos GET y POST.
 * 
 */
public abstract class ClienteHTTPServicioBase implements ClienteHTTPServicio {

	/** The http client. */
	private HttpClient						httpClient;

	/** The cm. */
	private PoolingClientConnectionManager	cm;

	/** The http context. */
	private HttpContext						httpContext;

	/**
	 * Gets the cm.
	 * 
	 * @return the cm
	 */
	public PoolingClientConnectionManager getCm() {
		return this.cm;
	}

	/**
	 * Gets the http client.
	 * 
	 * @return the http client
	 */
	public HttpClient getHttpClient() {
		return this.httpClient;
	}

	/**
	 * Gets the http context.
	 * 
	 * @return the http context
	 */
	public HttpContext getHttpContext() {
		return this.httpContext;
	}

	/**
	 * Sets the cm.
	 * 
	 * @param cm
	 *            the new cm
	 */
	public void setCm(final PoolingClientConnectionManager cm) {
		this.cm = cm;
	}

	/**
	 * Sets the http client.
	 * 
	 * @param httpClient
	 *            the new http client
	 */
	public void setHttpClient(final HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	/**
	 * Sets the http context.
	 * 
	 * @param httpContext
	 *            the new http context
	 */
	public void setHttpContext(final HttpContext httpContext) {
		this.httpContext = httpContext;
	}

	/**
	 * Sets the http context.
	 * 
	 * @param httpContext
	 *            the new http context
	 */

}
