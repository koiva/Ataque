package org.fbgk.ataque.transformacion.base;

import java.util.HashMap;
import java.util.Map;

import org.fbgk.ataque.bbdd.AtaqueDao;
import org.fbgk.ataque.guerrastribales.dto.AlianzaDTO;
import org.fbgk.ataque.guerrastribales.dto.JugadoresDTO;
import org.fbgk.ataque.guerrastribales.dto.PueblosDTO;
import org.fbgk.ataque.guerrastribales.dto.ServidorDTO;
import org.fbgk.ataque.transformacion.InsertarInfomacionMapeo;
import org.fbgk.ataque.transformacion.TransformacionServicio;
import org.fbgk.ataque.url.ClienteHTTPServicio;

/**
 * The Class TransformacionServicioBase.
 */
public abstract class TransformacionServicioBase implements TransformacionServicio {

	/** The ataque dao. */
	private AtaqueDao							ataqueDao;

	/** The cliente http servicio. */
	private ClienteHTTPServicio					clienteHTTPServicio;

	/** The insertar infomacion mapeo alianza. */
	protected InsertarInfomacionMapeo			insertarInfomacionMapeoAlianza		= new InsertarInfomacionMapeo() {

																						@Override
																						public void insertarMapeo(final String[] valores, final ServidorDTO servidorDTO) {
																							final AlianzaDTO alianzaDTO = new AlianzaDTO();
																							alianzaDTO.setGameID(Integer.valueOf(valores[0]));
																							alianzaDTO.setServidorDTO(servidorDTO);
																							alianzaDTO.setNombre(valores[1]);
																							alianzaDTO.setTag(valores[2]);
																							alianzaDTO.setMiembros(Integer.valueOf(valores[3]));
																							alianzaDTO.setPoblados(Integer.valueOf(valores[4]));
																							alianzaDTO.setPuntos(Integer.valueOf(valores[5]));
																							alianzaDTO.setPuntosTotales(Integer.valueOf(valores[6]));
																							alianzaDTO.setRank(Integer.valueOf(valores[7]));
																							TransformacionServicioBase.this.insertar(alianzaDTO);
																						}
																					};

	/** The insertar infomacion mapeo jugadores. */
	protected InsertarInfomacionMapeo			insertarInfomacionMapeoJugadores	= new InsertarInfomacionMapeo() {

																						@Override
																						public void insertarMapeo(final String[] valores, final ServidorDTO servidorDTO) {
																							final JugadoresDTO jugadoresDTO = new JugadoresDTO();
																							jugadoresDTO.setGameID(Integer.valueOf(valores[0]));
																							jugadoresDTO.setServidorDTO(servidorDTO);
																							jugadoresDTO.setNombre(valores[1]);
																							final int alianza = Integer.valueOf(valores[2]);
																							if (alianza != 0) {
																								jugadoresDTO.setAlianzaDTO(TransformacionServicioBase.this.recuperarAlianza(alianza));
																							}
																							jugadoresDTO.setNumeroPueblos(Integer.valueOf(valores[3]));
																							jugadoresDTO.setPuntos(Integer.valueOf(valores[4]));
																							jugadoresDTO.setRanking(Integer.valueOf(valores[5]));
																							TransformacionServicioBase.this.insertar(jugadoresDTO);
																						}
																					};

	/** The insertar infomacion mapeo poblados. */
	protected InsertarInfomacionMapeo			insertarInfomacionMapeoPoblados		= new InsertarInfomacionMapeo() {

																						@Override
																						public void insertarMapeo(final String[] valores, final ServidorDTO servidorDTO) {
																							final PueblosDTO pueblosDTO = new PueblosDTO();
																							pueblosDTO.setGameID(Integer.valueOf(valores[0]));
																							pueblosDTO.setName(valores[1]);
																							pueblosDTO.setX(Integer.valueOf(valores[2]));
																							pueblosDTO.setY(Integer.valueOf(valores[3]));
																							final int jugador = Integer.valueOf(valores[4]);
																							if (jugador != 0) {
																								pueblosDTO.setJugadoresDTO(TransformacionServicioBase.this.recuperarJugador(jugador));
																							}
																							pueblosDTO.setServidorDTO(servidorDTO);
																							pueblosDTO.setPoints(Integer.valueOf(valores[5]));
																							pueblosDTO.setRank(Integer.valueOf(valores[6]));

																							if (jugador != 0 || !pueblosDTO.getName().contains("Mercader")) {

																								TransformacionServicioBase.this.insertar(pueblosDTO);
																							}
																						}
																					};

	/** The mapeo alianza. */
	protected final Map<Integer, AlianzaDTO>	mapeoAlianza						= new HashMap<Integer, AlianzaDTO>();

	/** The mapeo jugadores. */
	protected final Map<Integer, JugadoresDTO>	mapeoJugadores						= new HashMap<Integer, JugadoresDTO>();

	/** The mapeo pueblos. */
	protected final Map<Integer, PueblosDTO>	mapeoPueblos						= new HashMap<Integer, PueblosDTO>();

	/**
	 * Gets the ataque dao.
	 * 
	 * @return the ataque dao
	 */
	public AtaqueDao getAtaqueDao() {
		return this.ataqueDao;
	}

	/**
	 * Gets the cliente http servicio.
	 * 
	 * @return the cliente http servicio
	 */
	public ClienteHTTPServicio getClienteHTTPServicio() {
		return this.clienteHTTPServicio;
	}

	/**
	 * Insertar alianza.
	 * 
	 * @param alianzaDTO
	 *            the alianza dto
	 */
	public void insertar(final AlianzaDTO alianzaDTO) {
		this.mapeoAlianza.put(alianzaDTO.getGameID(), alianzaDTO);
	}

	/**
	 * Insertar jugador.
	 * 
	 * @param jugadoresDTO
	 *            the jugadores dto
	 */
	public void insertar(final JugadoresDTO jugadoresDTO) {
		this.mapeoJugadores.put(jugadoresDTO.getGameID(), jugadoresDTO);
	}

	/**
	 * Insertar pueblo.
	 * 
	 * @param pueblosDTO
	 *            the pueblos dto
	 */
	public void insertar(final PueblosDTO pueblosDTO) {
		this.mapeoPueblos.put(pueblosDTO.getGameID(), pueblosDTO);
	}

	/**
	 * Recuperar alianza.
	 * 
	 * @param integer
	 *            the integer
	 * @return the alianza dto
	 */
	public AlianzaDTO recuperarAlianza(final Integer integer) {
		return this.mapeoAlianza.get(integer);
	}

	/**
	 * Recuperar jugador.
	 * 
	 * @param integer
	 *            the integer
	 * @return the jugadores dto
	 */
	public JugadoresDTO recuperarJugador(final Integer integer) {
		return this.mapeoJugadores.get(integer);
	}

	/**
	 * Recuperar pueblos dto.
	 * 
	 * @param integer
	 *            the integer
	 * @return the pueblos dto
	 */
	public PueblosDTO recuperarPueblosDTO(final Integer integer) {
		return this.mapeoPueblos.get(integer);
	}

	/**
	 * Sets the ataque dao.
	 * 
	 * @param ataqueDao
	 *            the new ataque dao
	 */
	public void setAtaqueDao(final AtaqueDao ataqueDao) {
		this.ataqueDao = ataqueDao;
	}

	/**
	 * Sets the cliente http servicio.
	 * 
	 * @param clienteHTTPServicio
	 *            the new cliente http servicio
	 */
	public void setClienteHTTPServicio(final ClienteHTTPServicio clienteHTTPServicio) {
		this.clienteHTTPServicio = clienteHTTPServicio;
	}

}
