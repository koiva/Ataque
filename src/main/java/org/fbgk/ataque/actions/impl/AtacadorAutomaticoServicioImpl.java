package org.fbgk.ataque.actions.impl;

import java.util.List;

import org.fbgk.ataque.actions.base.AtacadorAutomaticoServicioBase;
import org.fbgk.ataque.guerrastribales.dto.ListaAtaquesDTO;
import org.fbgk.ataque.guerrastribales.dto.LoginDTO;
import org.fbgk.ataque.guerrastribales.dto.PueblosDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class AtacadorAutomaticoServicioImpl.
 */
public class AtacadorAutomaticoServicioImpl extends AtacadorAutomaticoServicioBase {

	/** The logger. */
	static Logger	logger	= LoggerFactory.getLogger(AtacadorAutomaticoServicioImpl.class);

	/**
	 * Atacar lista.
	 * 
	 * @param listaAtaquesDTO
	 *            the lista ataques dto
	 */
	private void atacarLista(final ListaAtaquesDTO listaAtaquesDTO) {
		logger.debug("Intentando sacar la informacion del Login a partir de la lista de ataque");
		final PueblosDTO pueblosDTO = this.ataqueDao.consultar("FROM PueblosDTO WHERE gameID=? and servidorID=?", listaAtaquesDTO.getGameIDPropio(), listaAtaquesDTO.getServidorDTO().getServidorID());
		if (pueblosDTO != null) {
			logger.debug("Pueblo encontrado para el jugador {}", pueblosDTO.getJugadoresDTO().getNombre());
			final LoginDTO loginDTO = this.ataqueDao.consultar("FROM LoginDTO WHERE usuario=?", (Object) pueblosDTO.getJugadoresDTO().getNombre());
			if (loginDTO != null) {
				logger.debug("Encontrado el Login. Empieza el Ataque.");
				this.urlActionsServicio.atacarListaBarbaroTodo(listaAtaquesDTO.getListaAtaquesID(), loginDTO.getLoginID());
			}
		}
	}

	public void atacarTodasListas() {
		final List<ListaAtaquesDTO> listaAtaques = this.ataqueDao.recuperarTodo(new ListaAtaquesDTO());
		for (final ListaAtaquesDTO listaAtaquesDTO : listaAtaques) {
			if (listaAtaquesDTO.getIsActivo()) {
				logger.debug("Empieza el ataque con la lista: {}", listaAtaquesDTO.getNombre());
				this.atacarLista(listaAtaquesDTO);
			}
		}
	}

}
