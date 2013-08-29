package org.fbgk.ataque.transformacion.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import org.fbgk.ataque.guerrastribales.dto.AlianzaDTO;
import org.fbgk.ataque.guerrastribales.dto.JugadoresDTO;
import org.fbgk.ataque.guerrastribales.dto.PueblosDTO;
import org.fbgk.ataque.guerrastribales.dto.ServidorDTO;
import org.fbgk.ataque.transformacion.InsertarInfomacionMapeo;
import org.fbgk.ataque.transformacion.base.TransformacionServicioBase;
import org.fbgk.ataque.transformacion.constantes.TransformacionConstante;
import org.fbgk.ataque.url.constantes.ConstantesURL;
import org.fbgk.ataque.url.dto.EjecutarHTTPDTO;
import org.fbgk.ataque.url.dto.RespuestaHTTPDTO;
import org.fbgk.ataque.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class TransformacionServicioImpl.
 */

public class TransformacionServicioImpl extends TransformacionServicioBase {

	/** The logger. */
	public static Logger	logger	= LoggerFactory.getLogger(TransformacionServicioImpl.class);

	/**
	 * Actualizar bbdd.
	 */
	public void actualizarBBDD() {
		final List<ServidorDTO> servidorDTOs = this.getAtaqueDao().recuperarTodo(new ServidorDTO());
		for (final ServidorDTO servidorDTO : servidorDTOs) {
			this.procesarInformacion(servidorDTO);
		}
	}

	/**
	 * Conseguir archivo informacion. Llama
	 * 
	 * @param ejecucion
	 *            the ejecucion
	 * @param game
	 *            the game
	 * @param servidor
	 *            the servidor
	 * @return the respuesta httpdto
	 */
	private RespuestaHTTPDTO conseguirArchivoInformacion(final String ejecucion, final String game, final String servidor) {
		logger.info("Recuperando informacion del servidor. Ejecucion: {}", ejecucion);
		final EjecutarHTTPDTO ejecutarHTTPDTO = new EjecutarHTTPDTO();
		ejecutarHTTPDTO.setGame(game);
		ejecutarHTTPDTO.setIndRespuesta(true);
		ejecutarHTTPDTO.setServidor(servidor);
		ejecutarHTTPDTO.setUrl(ejecucion);
		return this.getClienteHTTPServicio().ejecutarGet(ejecutarHTTPDTO);
	}

	/**
	 * Crear proceso automatico.
	 * 
	 * @param servidorDTO
	 *            the servidor dto
	 * @param infomacionMapeo
	 *            the infomacion mapeo
	 * @param url
	 *            the url
	 */
	private void crearProcesoAutomatico(final ServidorDTO servidorDTO, final InsertarInfomacionMapeo infomacionMapeo, final String url) {
		final List<String> lista = this.sacarInformacionArchivo(url, servidorDTO.getServer(), servidorDTO.getInternalizacion());
		logger.info("Informacion a procesar: {}. Numero de elementos: {}", url, lista.size());
		if (!lista.isEmpty()) {
			for (final String linea : lista) {
				if (linea != null) {
					final String valores[] = linea.split(",");
					for (int x = 0; x < valores.length; x++) {
						try {
							valores[x] = URLDecoder.decode(valores[x], ConstantesURL.DECODE_DEFAULT);
						} catch (final UnsupportedEncodingException e) {
							logger.error(e.getMessage());
						}
					}
					infomacionMapeo.insertarMapeo(valores, servidorDTO);
				}
			}
		}
		logger.info("Informacion a procesar: {}. Numero de elementos: {}", url, lista.size());
	}

	/**
	 * Procesar informacion.
	 * 
	 * @param servidorDTO
	 *            the servidor dto
	 */
	private void procesarInformacion(final ServidorDTO servidorDTO) {
		final long timeStart = System.nanoTime();
		this.crearProcesoAutomatico(servidorDTO, this.insertarInfomacionMapeoAlianza, TransformacionConstante.URL_ALIANZAS);
		this.crearProcesoAutomatico(servidorDTO, this.insertarInfomacionMapeoJugadores, TransformacionConstante.URL_JUGADORES);
		this.crearProcesoAutomatico(servidorDTO, this.insertarInfomacionMapeoPoblados, TransformacionConstante.URL_POBLADOS);
		if (this.mapeoAlianza.size() != 0 && this.mapeoJugadores.size() != 0 && this.mapeoPueblos.size() != 0) {
			this.procesarInformacionBBDD(servidorDTO.getServidorID());
			this.getAtaqueDao().actualizaTodo(this.mapeoAlianza.values());
			this.getAtaqueDao().actualizaTodo(this.mapeoJugadores.values());
			this.getAtaqueDao().actualizaTodo(this.mapeoPueblos.values());
		}
		logger.info("Tiempo que se ha procesado los datos: {} ms", (System.nanoTime() - timeStart) / 1000000);
	}

	/**
	 * Procesar informacion bbdd. Transpasa la informacion de la BBDD a Map para
	 * una vez finalizada la inserccion y modificacion de los datos se puedan
	 * eliminar completamente
	 * 
	 * @param identificador
	 *            the identificador
	 */
	private void procesarInformacionBBDD(final int identificador) {
		this.getAtaqueDao().eliminarTodoServidor(this.getAtaqueDao().recuperarInformacionServer(identificador, PueblosDTO.class.getName()));
		this.getAtaqueDao().eliminarTodoServidor(this.getAtaqueDao().recuperarInformacionServer(identificador, JugadoresDTO.class.getName()));
		this.getAtaqueDao().eliminarTodoServidor(this.getAtaqueDao().recuperarInformacionServer(identificador, AlianzaDTO.class.getName()));
	}

	/**
	 * Sacar informacion archivo.
	 * 
	 * @param tipo
	 *            the tipo
	 * @param servidor
	 *            the servidor
	 * @return the list
	 */
	private List<String> sacarInformacionArchivo(final String tipo, final String servidor, final String internalizacion) {
		final String descarga = String.format(tipo, servidor, internalizacion);
		logger.info("Sacamos la informacion. Descarga: {}", descarga);
		final RespuestaHTTPDTO respuestaHTTPDTO = this.conseguirArchivoInformacion(descarga, TransformacionConstante.JUEGO_GUERRASTRIBALES, servidor);
		return FileUtils.leerArchivo(TransformacionConstante.JUEGO_GUERRASTRIBALES, servidor, respuestaHTTPDTO.getLectura().getName());
	}

}