package org.fbgk.ataque.actions;

import java.util.List;

import org.fbgk.ataque.guerrastribales.dto.AtaqueDTO;
import org.fbgk.ataque.guerrastribales.dto.ListaAtaquesDTO;
import org.fbgk.ataque.guerrastribales.dto.PueblosDTO;

/**
 * The Interface BarbarosServicio.
 */
public interface BarbarosServicio {

	/**
	 * Buscar barbaros. Busca los barbaros desde la poblacion pasada hasta la
	 * distancia maxima.
	 * 
	 * @param pueblosDTO
	 *            the pueblos dto
	 * @param distMax
	 *            the dist max
	 * @return the lista ataques dto
	 */
	public List<AtaqueDTO> buscarBarbaros(PueblosDTO pueblosDTO, Integer distMax);

	/**
	 * Insertar barbaros. Inserta en una lista de ataques los pueblos faltantes
	 * y eliminas los antiguos.
	 * 
	 * @param pueblosDTO
	 *            the pueblos dto
	 * @param distMax
	 *            the dist max
	 * @param listaAtaquesDTO
	 *            the lista ataques dto
	 */
	public void mantenimientoBarbaros(PueblosDTO pueblosDTO, Integer distMax, ListaAtaquesDTO listaAtaquesDTO);

}
