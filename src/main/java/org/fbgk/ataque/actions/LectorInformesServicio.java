package org.fbgk.ataque.actions;

import org.fbgk.ataque.guerrastribales.dto.LoginDTO;
import org.fbgk.ataque.guerrastribales.dto.ServidorDTO;

public interface LectorInformesServicio {

	/**
	 * Leer informes. Leer los informes del juego para saber lo saqueado, robado
	 * y perdido.
	 * 
	 * @param jugadoresDTO
	 *            the jugadores dto
	 * @param servidorDTO
	 *            the servidor dto
	 */
	public void leerInformes(LoginDTO loginDTO, ServidorDTO servidorDTO);

}
