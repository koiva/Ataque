package org.fbgk.ataque.url.base;

import org.apache.http.client.HttpClient;
import org.fbgk.ataque.url.ClienteHTTPServicio;

/**
 * The Class ClienteHTTPServicioBase. Esta clase llevara la parte de creacion de
 * las URL y puede ejecutar los procesos GET y POST.
 * 
 */
public abstract class ClienteHTTPServicioBase implements ClienteHTTPServicio {

	/** The http client. */
	private HttpClient	httpClient;

	/**
	 * Gets the http client.
	 * 
	 * @return the http client
	 */
	public HttpClient getHttpClient() {
		return this.httpClient;
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

}
