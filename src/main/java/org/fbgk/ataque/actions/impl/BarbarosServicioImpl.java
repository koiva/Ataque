package org.fbgk.ataque.actions.impl;

import java.util.ArrayList;
import java.util.List;

import org.fbgk.ataque.actions.base.BarbarosServicioBase;
import org.fbgk.ataque.guerrastribales.dto.AtaqueDTO;
import org.fbgk.ataque.guerrastribales.dto.ListaAtaquesDTO;
import org.fbgk.ataque.guerrastribales.dto.PueblosDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class BarbarosServicioImpl.
 */
public class BarbarosServicioImpl extends BarbarosServicioBase {

	static Logger	logger	= LoggerFactory.getLogger(BarbarosServicioImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fbgk.ataque.actions.BarbarosServicio#buscarBarbaros(org.fbgk.ataque
	 * .guerrastribales.dto.PueblosDTO, java.lang.Integer)
	 */
	public List<AtaqueDTO> buscarBarbaros(final PueblosDTO pueblosDTO, final Integer distMax) {
		logger.debug("Buscando los barbaros entre con la distancia máxima: {}", distMax);
		final List<AtaqueDTO> listAtaquesDTO = new ArrayList<AtaqueDTO>();
		final int xMin = pueblosDTO.getX() - distMax;
		final int xMax = pueblosDTO.getX() + distMax;
		final int yMin = pueblosDTO.getY() - distMax;
		final int yMax = pueblosDTO.getY() + distMax;
		final List<PueblosDTO> listaPueblos = this.ataqueDao.buscar("FROM PueblosDTO WHERE x between ? AND ? AND y between ? AND ? AND jugadoresID IS NULL AND servidorID = ?", xMin, xMax, yMin, yMax, pueblosDTO.getServidorDTO().getServidorID());
		logger.debug("Encontrado {} bárbaros en la BBDD", listaPueblos.size());
		for (final PueblosDTO pobladoSec : listaPueblos) {
			final Double distanciaEntre = Math.hypot(Math.abs(pueblosDTO.getX() - pobladoSec.getX()), Math.abs(pueblosDTO.getY() - pobladoSec.getY()));
			if (distanciaEntre <= distMax) {
				logger.debug("Encontrado un barbaro con la distancia especificada. GameID: {}", pobladoSec.getGameID());
				final AtaqueDTO ataqueDTO = new AtaqueDTO();
				ataqueDTO.setDistanciaMax(distanciaEntre);
				ataqueDTO.setGameIDAtaque(pobladoSec.getGameID());
				listAtaquesDTO.add(ataqueDTO);
			}
		}
		return listAtaquesDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fbgk.ataque.actions.BarbarosServicio#mantenimientoBarbaros(org.fbgk
	 * .ataque.guerrastribales.dto.PueblosDTO, java.lang.Integer,
	 * org.fbgk.ataque.guerrastribales.dto.ListaAtaquesDTO)
	 */
	public void mantenimientoBarbaros(final PueblosDTO pueblosDTO, final Integer distMax, final ListaAtaquesDTO listaAtaquesDTO) {
		logger.debug("Mantenimiento de los barbaros invocado");
		final List<AtaqueDTO> listaAtaques = this.buscarBarbaros(pueblosDTO, distMax);
		final ListaAtaquesDTO dto = this.ataqueDao.consultar(new ListaAtaquesDTO(), listaAtaquesDTO.getListaAtaquesID());
		logger.debug("Se esta procesando el numero de bárbaros segun la distancia máxima y si ha sido conquistado");
		for (final AtaqueDTO ataqueDTO : dto.getListaAtaquesDTO()) {
			this.ataqueDao.eliminar(ataqueDTO);
		}
		logger.debug("Se inserta en la BBDD la diferencia");
		for (final AtaqueDTO ataqueDTO : listaAtaques) {
			ataqueDTO.setListaAtaquesDTO(listaAtaquesDTO);
			this.ataqueDao.insertar(ataqueDTO);
		}
	}

}
