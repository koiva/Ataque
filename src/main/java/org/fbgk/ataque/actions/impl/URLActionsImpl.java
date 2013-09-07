package org.fbgk.ataque.actions.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.fbgk.ataque.actions.base.URLActionsBase;
import org.fbgk.ataque.actions.constantes.GuerrasTribalesActions;
import org.fbgk.ataque.guerrastribales.dto.AtaqueDTO;
import org.fbgk.ataque.guerrastribales.dto.ListaAtaquesDTO;
import org.fbgk.ataque.guerrastribales.dto.LoginDTO;
import org.fbgk.ataque.guerrastribales.dto.PueblosDTO;
import org.fbgk.ataque.guerrastribales.dto.ServidorDTO;
import org.fbgk.ataque.url.constantes.ConstantesURL;
import org.fbgk.ataque.url.dto.EjecutarHTTPDTO;
import org.fbgk.ataque.url.dto.RespuestaHTTPDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class URLActionsImpl.
 */
public class URLActionsImpl extends URLActionsBase {

	/** The logger. */
	static Logger	logger	= LoggerFactory.getLogger(URLActionsImpl.class);

	/**
	 * Atacar con ataque dto.
	 * 
	 * @param servidorDTO
	 *            the servidor dto
	 * @param ataqueDTO
	 *            the ataque dto
	 * @param loginID
	 *            the login id
	 * @param gameID
	 *            the game id
	 * @return the boolean
	 */
	private Boolean atacarConAtaqueDTO(final ServidorDTO servidorDTO, final AtaqueDTO ataqueDTO, final Integer loginID, final Integer gameID, final Boolean comprobarBarbaros) {
		logger.debug("Atacando al poblado {}", gameID);
		Boolean respuesta = Boolean.FALSE;
		String url = String.format(GuerrasTribalesActions.ATAQUE_PRIMER_PROCESO, servidorDTO.getServer(), servidorDTO.getJuego(), servidorDTO.getInternalizacion(), gameID, ataqueDTO.getGameIDAtaque());
		logger.debug("Url del ataque", url);
		final RespuestaHTTPDTO respuestaHTTPDTO = this.clienteHTTPServicio.ejecutarGet(new EjecutarHTTPDTO(url, null, false, null, null, Boolean.TRUE));
		url = String.format(GuerrasTribalesActions.ATAQUE_SEGUNDO_PROCESO, servidorDTO.getServer(), servidorDTO.getJuego(), servidorDTO.getInternalizacion(), gameID);
		logger.debug("Primera parte del Ataque");
		final RespuestaHTTPDTO respuestaHTTPDTO2 = this.clienteHTTPServicio.ejecutarPost(new EjecutarHTTPDTO(url, this.mapeoAtaqueInput(respuestaHTTPDTO, ataqueDTO.getListaAtaquesDTO()), false, null, null, Boolean.TRUE));
		final Document document = Jsoup.parse(this.respuestaString(respuestaHTTPDTO2));
		final Elements elements2 = document.select("table.vis > tbody > tr > td");
		final Elements elements3 = elements2.get(3).getAllElements();
		if (!elements3.get(0).text().startsWith("<")) {
			logger.debug("Atacando al barbaro");
			final Elements elements = document.select("input");
			final Map<String, String> mapeo = new HashMap<String, String>();
			for (final Element element : elements) {
				mapeo.put(element.attr("name"), element.attr("value"));
			}
			url = String.format("%s%s", GuerrasTribalesActions.ATAQUE_PROCESO, document.select("form").get(0).attr("action"));
			url = String.format(url, servidorDTO.getServer(), servidorDTO.getJuego(), servidorDTO.getInternalizacion());
			this.clienteHTTPServicio.ejecutarPost(new EjecutarHTTPDTO(url, mapeo, false, null, null, Boolean.TRUE));
			logger.debug("Ataque confirmado. Se ha actualizado la hora del ataque.");
			respuesta = Boolean.TRUE;
			ataqueDTO.setTiempoAtaque(Calendar.getInstance());
			this.ataqueDao.actualizar(ataqueDTO);
		} else {
			logger.warn("USUARIO!!! Se ha encontrado un usuario. Se elimina de la Base de datos como atacante.");
			this.ataqueDao.eliminar(ataqueDTO);
		}
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fbgk.ataque.actions.URLActionsServicio#atacarListaBarbaroTodo(java
	 * .lang.Integer, java.lang.Integer)
	 */
	public List<AtaqueDTO> atacarListaBarbaroTodo(final Integer listaAtaquesID, final Integer loginID) {
		logger.debug("Buscamos los barbaros necesarios");
		final List<AtaqueDTO> ataqueDTOs = this.ataqueDao.buscar("FROM AtaqueDTO WHERE listaAtaquesID=? order by tiempoAtaque,distanciaMax asc", listaAtaquesID);
		final List<AtaqueDTO> listaAtacados = new ArrayList<AtaqueDTO>();
		logger.debug("Comprobando login");
		if (this.loginSiNoLoEsta(loginID, ataqueDTOs.get(0).getListaAtaquesDTO().getServidorDTO().getServidorID())) {
			final Integer integer = this.numeroAtaques(this.ataqueDao.consultar(new ListaAtaquesDTO(), listaAtaquesID), loginID);
			logger.debug("Numero de ataques disponibles con la configuración actual: {}", integer);
			for (int x = 0; x < integer; x++) {
				final AtaqueDTO ataqueDTO = ataqueDTOs.get(x);
				if (this.atacarConAtaqueDTO(ataqueDTO.getListaAtaquesDTO().getServidorDTO(), ataqueDTO, loginID, ataqueDTO.getListaAtaquesDTO().getGameIDPropio(), Boolean.TRUE)) {
					listaAtacados.add(ataqueDTO);
				}
			}
		}
		return listaAtacados;
	}

	public List<AtaqueDTO> atacarListaBarbaroTodo(final ListaAtaquesDTO listaAtaquesID, final LoginDTO loginID) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fbgk.ataque.actions.URLActionsServicio#atacarSiguienteBarbaro(java
	 * .lang.Integer, java.lang.Integer)
	 */
	public AtaqueDTO atacarSiguienteBarbaro(final Integer listaAtaquesID, final Integer loginID) {
		logger.debug("Se busca el siguiente ataque de Barbaro");
		final List<AtaqueDTO> ataqueDTOs = this.ataqueDao.buscar("FROM AtaqueDTO WHERE listaAtaquesID=? order by tiempoAtaque,distanciaMax asc", listaAtaquesID);
		AtaqueDTO ataqueDTO = ataqueDTOs.get(0);
		if (this.loginSiNoLoEsta(loginID, ataqueDTO.getListaAtaquesDTO().getServidorDTO().getServidorID())) {
			final Integer integer = this.numeroAtaques(this.ataqueDao.consultar(new ListaAtaquesDTO(), listaAtaquesID), loginID);
			logger.debug("Se busca el siguiente ataque de Barbaro");
			if (integer > 0) {
				if (!this.atacarConAtaqueDTO(ataqueDTO.getListaAtaquesDTO().getServidorDTO(), ataqueDTO, loginID, ataqueDTO.getListaAtaquesDTO().getGameIDPropio(), Boolean.TRUE)) {
					ataqueDTO = null;
				}
			} else {
				logger.debug("El ataque no es posible. No hay suficientes tropas.");
				ataqueDTO = null;
			}
		}
		return ataqueDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fbgk.ataque.actions.URLActions#comprobarTropas(java.lang.Integer,
	 * java.lang.Integer, java.lang.Integer)
	 */
	public Map<String, String> comprobarTropas(final Integer loginID, final Integer serverID, final Integer poblado) {
		Map<String, String> mapeo = new HashMap<String, String>();
		if (this.loginSiNoLoEsta(loginID, serverID)) {
			final ServidorDTO servidorDTO = this.ataqueDao.consultar(new ServidorDTO(), serverID);
			final PueblosDTO pueblosDTO = this.ataqueDao.consultar(new PueblosDTO(), poblado);
			if (pueblosDTO != null) {
				final String url = String.format(GuerrasTribalesActions.TROPAS_PAGINA_COMPROBAR, servidorDTO.getServer(), servidorDTO.getJuego(), servidorDTO.getInternalizacion(), pueblosDTO.getGameID());
				final RespuestaHTTPDTO respuestaHTTPDTO = this.clienteHTTPServicio.ejecutarGet(new EjecutarHTTPDTO(url, null, false, null, null, Boolean.TRUE));
				final String contenido = this.respuestaString(respuestaHTTPDTO);
				mapeo = this.mapeoGuerrasTribales.mapeoTropasDisponibles(contenido);
			}
		}
		return mapeo;
	}

	/**
	 * Creacion url login. Crea la url para la generacion automatica de la URL
	 * 
	 * @param servidorDTO
	 *            the servidor dto
	 * @return the string
	 */
	private String creacionURLLogin(final ServidorDTO servidorDTO) {
		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(String.format(GuerrasTribalesActions.ACTION_URL, servidorDTO.getJuego(), servidorDTO.getInternalizacion()));
		stringBuilder.append(String.format(GuerrasTribalesActions.ACTION_FORM_LOGIN, servidorDTO.getServer()));
		return stringBuilder.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fbgk.ataque.actions.URLActions#isLogin(java.lang.Integer)
	 */
	public Boolean isLogin(final Integer loginID, final Integer serverID) {
		Boolean respuesta = Boolean.FALSE;
		logger.info("Buscando los datos facilitados");
		final LoginDTO loginDTO = this.ataqueDao.consultar(new LoginDTO(), loginID);
		final ServidorDTO servidorDTO = this.ataqueDao.consultar(new ServidorDTO(), serverID);
		if (loginDTO != null && serverID != null) {
			logger.info("Recuperados los datos solicitados");
			respuesta = this.isLogin(loginDTO, servidorDTO);
		} else {
			logger.error("Los datos facilitados son erroneos");
		}
		return respuesta;
	}

	/**
	 * Checks if is login. Checkea si el acceso ha sido correcto.
	 * 
	 * @param loginDTO
	 *            the login dto
	 * @param servidorDTO
	 *            the servidor dto
	 * @return the boolean
	 */
	private Boolean isLogin(final LoginDTO loginDTO, final ServidorDTO servidorDTO) {
		logger.debug("Comprobando el login");
		Boolean respuesta = Boolean.FALSE;
		final String url = String.format(GuerrasTribalesActions.LOGIN_COMPROBADOR, servidorDTO.getServer(), servidorDTO.getJuego(), servidorDTO.getInternalizacion());
		final RespuestaHTTPDTO respuestaHTTPDTO = this.clienteHTTPServicio.ejecutarGet(new EjecutarHTTPDTO(url, null, false, servidorDTO.getJuego(), servidorDTO.getServer(), Boolean.TRUE));
		if (respuestaHTTPDTO.getRespuesta() != null) {
			final String respuestaString = this.respuestaString(respuestaHTTPDTO);
			final Elements elements = Jsoup.parse(respuestaString).select("title");
			if (elements.size() == 1 && !elements.get(0).text().isEmpty()) {
				respuesta = Boolean.TRUE;
				logger.debug("Esta logueado :D");
			}
		} else {
			logger.debug("No esta logueado, y se ha accedido desde la web. Se restablece el Login.");
			respuesta = this.loginConDTO(loginDTO, servidorDTO);
		}
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fbgk.ataque.actions.URLActions#login(java.lang.Integer)
	 */
	public Boolean login(final Integer loginID, final Integer serverID) {
		logger.info("Se intenta hacer Login con el usuario: {} y en el server: {}", loginID, serverID);
		final LoginDTO loginDTO = this.ataqueDao.consultar(new LoginDTO(), loginID);
		final ServidorDTO servidorDTO = this.ataqueDao.consultar(new ServidorDTO(), serverID);
		return this.loginConDTO(loginDTO, servidorDTO);
	}

	/**
	 * Login con dto. Loguea con el DTO
	 * 
	 * @param loginDTO
	 *            the login dto
	 * @param servidorDTO
	 *            the servidor dto
	 * @return the boolean
	 */
	private Boolean loginConDTO(final LoginDTO loginDTO, final ServidorDTO servidorDTO) {
		Boolean respuesta = Boolean.FALSE;
		logger.info("Se intenta hacer login a la aplicacion: Usuario: {}. Servidor: {}", loginDTO.getUsuario(), servidorDTO.getServer());
		this.clienteHTTPServicio.init();
		if (loginDTO != null && servidorDTO != null) {
			logger.info("Se ha recuperado correctamente los datos de la BBDD");
			RespuestaHTTPDTO respuestaHTTPDTO = this.primerLogin(loginDTO, servidorDTO);
			if (respuestaHTTPDTO.getEstado() == ConstantesURL.PETICION_OK) {
				logger.debug("Primer nivel de acceso correcto");
				respuestaHTTPDTO = this.segundoLogin(loginDTO, servidorDTO, respuestaHTTPDTO);
				if (respuestaHTTPDTO.getEstado() == ConstantesURL.PETICION_LOGIN_OK || respuestaHTTPDTO.getEstado() == ConstantesURL.PETICION_OK) {
					logger.debug("Segundo nivel de acceso correcto");
					respuesta = this.isLogin(loginDTO, servidorDTO);
				}
			} else {
				logger.error("No se ha podido tener acceso a la maquina");
			}
		} else {
			logger.error("Los datos facilitados son erroneos");
		}
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fbgk.ataque.actions.URLActions#loginSiNoLoEsta(java.lang.Integer,
	 * java.lang.Integer)
	 */
	public Boolean loginSiNoLoEsta(final Integer loginID, final Integer serverID) {
		Boolean respuesta = Boolean.FALSE;
		final LoginDTO loginDTO = this.ataqueDao.consultar(new LoginDTO(), loginID);
		final ServidorDTO servidorDTO = this.ataqueDao.consultar(new ServidorDTO(), serverID);
		if (!this.isLogin(loginDTO, servidorDTO)) {
			respuesta = this.loginConDTO(loginDTO, servidorDTO);
		} else {
			respuesta = Boolean.TRUE;
		}
		return respuesta;
	}

	private Map<String, String> mapeoAtaqueInput(final RespuestaHTTPDTO respuestaHTTPDTO, final ListaAtaquesDTO listaAtaquesDTO) {
		final Map<String, String> ataque = this.mapeoGuerrasTribales.mapeoTropasListAtaque(listaAtaquesDTO);
		final Document document = Jsoup.parse(this.respuestaString(respuestaHTTPDTO));
		Elements elements = document.select("form#units_form > input");
		ataque.put(elements.get(0).attr("name"), elements.get(0).attr("value"));
		elements = document.select("input[name=x]");
		ataque.put(elements.get(0).attr("name"), elements.get(0).attr("value"));
		elements = document.select("input[name=y]");
		ataque.put(elements.get(0).attr("name"), elements.get(0).attr("value"));
		ataque.put("template_id", "");
		elements = document.select("#target_attack");
		ataque.put(elements.get(0).attr("name"), elements.get(0).attr("value"));
		return ataque;
	}

	/**
	 * Numero ataques.
	 * 
	 * @param listaAtaquesDTO
	 *            the lista ataques dto
	 * @param loginID
	 *            the login id
	 * @return the integer
	 */
	private Integer numeroAtaques(final ListaAtaquesDTO listaAtaquesDTO, final Integer loginID) {
		logger.debug("Comprobando el numero de Ataques posibles");
		final Map<String, String> mapeoComprobador = this.comprobarTropas(loginID, listaAtaquesDTO.getServidorDTO().getServidorID(), ((PueblosDTO) this.ataqueDao.consultar("FROM PueblosDTO WHERE gameID=? AND servidorID=?", listaAtaquesDTO.getGameIDPropio(), listaAtaquesDTO.getServidorDTO().getServidorID())).getPueblosID());
		final Map<String, String> mapeoAtaque = this.mapeoGuerrasTribales.mapeoTropasListAtaque(listaAtaquesDTO);
		Integer valor = 0;
		Boolean comprobador = Boolean.FALSE;
		for (final String key : mapeoAtaque.keySet()) {
			final Integer ataque = Integer.valueOf(mapeoAtaque.get(key));
			final String valorComprobador = mapeoComprobador.get(String.format("unit_%s", key));
			if (ataque != 0 && valorComprobador != null) {
				final Integer nuevo = Integer.valueOf(valorComprobador) / ataque;
				if (valor > nuevo || !comprobador) {
					valor = nuevo;
					comprobador = Boolean.TRUE;
				}
			}
		}
		return valor;
	}

	/**
	 * Primer login. Creacion del primer login
	 * 
	 * @param loginDTO
	 *            the login dto
	 * @param servidorDTO
	 *            the servidor dto
	 * @return the respuesta httpdto
	 */
	private RespuestaHTTPDTO primerLogin(final LoginDTO loginDTO, final ServidorDTO servidorDTO) {
		final String url = String.format("%s%s", this.creacionURLLogin(servidorDTO), GuerrasTribalesActions.ACTION_FORM_SELECT_SERVER);
		logger.info("La url de conexion es: {}", url);
		final Map<String, String> mapeo = new HashMap<String, String>();
		mapeo.put(GuerrasTribalesActions.ACTION_FORM_LOGIN_USER, loginDTO.getUsuario());
		mapeo.put(GuerrasTribalesActions.ACTION_FORM_LOGIN_PASS, loginDTO.getPassword());
		mapeo.put("cookie", "false");
		mapeo.put("clear", "true");
		return this.clienteHTTPServicio.ejecutarPost(new EjecutarHTTPDTO(url, mapeo, false, servidorDTO.getJuego(), servidorDTO.getServer(), Boolean.FALSE));
	}

	/**
	 * Respuesta string. Devuelve la respuesta que ha dado el servidor.
	 * 
	 * @param respuestaHTTPDTO
	 *            the respuesta httpdto
	 * @return the string
	 */
	private String respuestaString(final RespuestaHTTPDTO respuestaHTTPDTO) {
		logger.debug("Descodificando la respuesta");
		final StringWriter writer = new StringWriter();
		try {
			IOUtils.copy(respuestaHTTPDTO.getRespuesta().getEntity().getContent(), writer, "UTF-8");
		} catch (final IllegalStateException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return writer.toString();
	}

	/**
	 * Segundo login.
	 * 
	 * @param loginDTO
	 *            the login dto
	 * @param servidorDTO
	 *            the servidor dto
	 * @param httpdto
	 *            the httpdto
	 * @return the respuesta httpdto
	 */
	private RespuestaHTTPDTO segundoLogin(final LoginDTO loginDTO, final ServidorDTO servidorDTO, final RespuestaHTTPDTO httpdto) {
		logger.debug("Se empieza la comprobación del segundo nivel de Acceso");
		RespuestaHTTPDTO respuestaHTTPDTO = null;
		final String respuestaString = this.respuestaString(httpdto);
		final Pattern pattern = Pattern.compile(GuerrasTribalesActions.REGEX_PASSWORD_CODE);
		final Matcher matcher2 = pattern.matcher(respuestaString);
		if (matcher2.find()) {
			logger.debug("Se ha encontrado el Hash de la password");
			final String password = matcher2.group();
			final String valores[] = password.split("\\\"");
			final Map<String, String> mapeo = new HashMap<String, String>();
			mapeo.put(GuerrasTribalesActions.ACTION_FORM_LOGIN_USER, loginDTO.getUsuario());
			mapeo.put(GuerrasTribalesActions.ACTION_FORM_LOGIN_PASS, valores[5].replace("\\", ""));
			String url = String.format("%s%s", this.creacionURLLogin(servidorDTO), GuerrasTribalesActions.ACTION_FORM_SERVER);
			url = String.format(url, servidorDTO.getServer());
			respuestaHTTPDTO = this.clienteHTTPServicio.ejecutarPost(new EjecutarHTTPDTO(url, mapeo, false, servidorDTO.getJuego(), servidorDTO.getServer(), Boolean.TRUE));
			if (respuestaHTTPDTO.getEstado() == ConstantesURL.PETICION_LOGIN_OK || respuestaHTTPDTO.getEstado() == ConstantesURL.PETICION_OK) {
				logger.debug("Buscando la direccion de entrada localizada en el header > Location");
				final Header urlEnvio = respuestaHTTPDTO.getRespuesta().getHeaders("Location")[0];
				respuestaHTTPDTO = this.clienteHTTPServicio.ejecutarGet(new EjecutarHTTPDTO(urlEnvio.getValue(), null, Boolean.FALSE, null, null, Boolean.TRUE));
			}
		} else {
			logger.error("Error general en la aplicación. No se ha podido acceder a la pasword cifrada.");
		}
		return respuestaHTTPDTO;
	}

}
