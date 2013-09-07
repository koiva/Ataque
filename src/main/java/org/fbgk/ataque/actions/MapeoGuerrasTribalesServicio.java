package org.fbgk.ataque.actions;

import java.util.Map;

import org.fbgk.ataque.guerrastribales.dto.ListaAtaquesDTO;

/**
 * The Interface MapeoGuerrasTribales.
 */
public interface MapeoGuerrasTribalesServicio {

	/**
	 * Mapeo tropas disponibles.
	 * 
	 * @param cadena
	 *            the cadena
	 * @return the map
	 */
	public Map<String, String> mapeoTropasDisponibles(String cadena);

	/**
	 * Mapeo tropas list ataque.
	 * 
	 * @param ataquesDTO
	 *            the ataques dto
	 * @return the map
	 */
	public Map<String, String> mapeoTropasListAtaque(ListaAtaquesDTO ataquesDTO);

}
