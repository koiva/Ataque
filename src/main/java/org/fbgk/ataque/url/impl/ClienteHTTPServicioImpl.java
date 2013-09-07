package org.fbgk.ataque.url.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
import org.fbgk.ataque.url.base.ClienteHTTPServicioBase;
import org.fbgk.ataque.url.constantes.ConstantesURL;
import org.fbgk.ataque.url.dto.EjecutarHTTPDTO;
import org.fbgk.ataque.url.dto.RespuestaHTTPDTO;
import org.fbgk.ataque.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class ClienteHTTPServicioImpl.
 */
public class ClienteHTTPServicioImpl extends ClienteHTTPServicioBase {

	/** The logger. */
	static Logger	logger	= LoggerFactory.getLogger(ClienteHTTPServicioImpl.class);

	/**
	 * Commons llamada. Llamada en comun, tanto para la zona GET y POST
	 * 
	 * @param request
	 *            the request
	 * @param ejecutarHTTPDTO
	 *            the ejecutar httpdto
	 * @return the respuesta httpdto
	 */
	private RespuestaHTTPDTO commonsLlamada(final HttpRequestBase request, final EjecutarHTTPDTO ejecutarHTTPDTO) {
		request.addHeader(CoreProtocolPNames.USER_AGENT, ConstantesURL.USER_AGENT_MOZILLA);
		final RespuestaHTTPDTO respuestaHTTPDTO = new RespuestaHTTPDTO();
		HttpResponse response = null;
		try {
			if (ejecutarHTTPDTO.getCookie()) {
				response = this.getHttpClient().execute(request, this.getHttpContext());
			} else {
				response = this.getHttpClient().execute(request);
			}
			logger.info("Enviando la informacion: {}", ejecutarHTTPDTO.getUrl());
			logger.info("Codigo respuesta: {}", response.getStatusLine().getStatusCode());

			/** Creamos la respuesta */
			respuestaHTTPDTO.setRespuesta(response);
			if (ejecutarHTTPDTO.isIndRespuesta()) {
				respuestaHTTPDTO.setLectura(FileUtils.escribirArchivo(response.getEntity().getContent(), ejecutarHTTPDTO.getGame(), ejecutarHTTPDTO.getServidor(), FileUtils.nombreArchivo(ejecutarHTTPDTO.getUrl())));
			}
			respuestaHTTPDTO.setEstado(response.getStatusLine().getStatusCode());
		} catch (final ClientProtocolException e) {
			logger.error(e.getMessage());
		} catch (final IOException e) {
			logger.error(e.getMessage());
		}
		return respuestaHTTPDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fbgk.ataque.url.ClienteHTTPServicio#ejecutarGet(org.fbgk.ataque.url
	 * .dto.EjecutarHTTPDTO)
	 */
	public RespuestaHTTPDTO ejecutarGet(final EjecutarHTTPDTO ejecutarHTTPDTO) {
		logger.debug("Ejecutando metodo de tipo GET");
		return this.commonsLlamada(new HttpGet(ejecutarHTTPDTO.getUrl()), ejecutarHTTPDTO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fbgk.ataque.url.ClienteHTTPServicio#ejecutarPost(org.fbgk.ataque.
	 * url.dto.EjecutarHTTPDTO)
	 */
	public RespuestaHTTPDTO ejecutarPost(final EjecutarHTTPDTO ejecutarHTTPDTO) {
		logger.debug("Ejecutando metodo de tipo POST");
		final HttpPost post = new HttpPost(ejecutarHTTPDTO.getUrl());

		if (!ejecutarHTTPDTO.getParametros().isEmpty()) {
			/** Creamos los parametros de envio */
			final List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			for (final String key : ejecutarHTTPDTO.getParametros().keySet()) {
				urlParameters.add(new BasicNameValuePair(key, ejecutarHTTPDTO.getParametros().get(key)));
			}
			UrlEncodedFormEntity urlEncodedFormEntity = null;
			try {
				urlEncodedFormEntity = new UrlEncodedFormEntity(urlParameters);
				post.setEntity(urlEncodedFormEntity);
			} catch (final UnsupportedEncodingException e) {
				logger.error(e.getMessage());
			}
		}
		return this.commonsLlamada(post, ejecutarHTTPDTO);
	}

	public void init() {
		logger.debug("Restableciendo la configuracion del cliente http");
		this.getHttpContext().setAttribute(ClientContext.COOKIE_STORE, new BasicCookieStore());
		this.getHttpClient().getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BEST_MATCH);
		this.getHttpClient().getParams().setParameter(ClientPNames.CONN_MANAGER_TIMEOUT, Long.valueOf(360000000));
		this.getHttpClient().getParams().setParameter(ClientPNames.HANDLE_REDIRECTS, Boolean.TRUE);
		this.getHttpClient().getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		this.getHttpClient().getParams().setParameter(ClientPNames.MAX_REDIRECTS, 50);
	}
}
