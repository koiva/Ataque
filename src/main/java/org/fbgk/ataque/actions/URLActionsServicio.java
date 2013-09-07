package org.fbgk.ataque.actions;

import java.util.List;
import java.util.Map;

import org.fbgk.ataque.guerrastribales.dto.AtaqueDTO;
import org.fbgk.ataque.guerrastribales.dto.ListaAtaquesDTO;
import org.fbgk.ataque.guerrastribales.dto.LoginDTO;

/**
 * The Interface URLActions.
 */
public interface URLActionsServicio {

	/**
	 * Atacar siguiente barbaro. Ataque al siguiente lista de ataques siempre
	 * que haya tropas suficientes.
	 * 
	 * @param listaAtaquesID
	 *            the lista ataques id
	 * @return the boolean
	 */
	public List<AtaqueDTO> atacarListaBarbaroTodo(final Integer listaAtaquesID, final Integer loginID);

	public List<AtaqueDTO> atacarListaBarbaroTodo(final ListaAtaquesDTO listaAtaquesID, final LoginDTO loginID);

	/**
	 * Atacar siguiente barbaro. Ataque al siguiente lista de ataques siempre
	 * que haya tropas suficientes.
	 * 
	 * @param listaAtaquesID
	 *            the lista ataques id
	 * @return the boolean
	 */
	public AtaqueDTO atacarSiguienteBarbaro(final Integer listaAtaquesID, final Integer loginID);

	/**
	 * Comprobar tropas. Comprobamos las tropas que tenemos.
	 * 
	 * @param loginID
	 *            the login id
	 * @param serverID
	 *            the server id
	 * @param poblado
	 *            the poblado
	 * @return the map
	 */

	public Map<String, String> comprobarTropas(final Integer loginID, final Integer serverID, final Integer poblado);

	/**
	 * Checks if is login.
	 * 
	 * @param loginID
	 *            the login id
	 * @param serverID
	 *            the server id
	 * @return the boolean
	 */
	public Boolean isLogin(final Integer loginID, final Integer serverID);

	/**
	 * Login. Logearse en la aplicacion
	 * 
	 * @param loginID
	 *            the login id
	 * @param serverID
	 *            the server id
	 * @return the boolean
	 */
	public Boolean login(final Integer loginID, final Integer serverID);

	/**
	 * Login si no lo esta. Logea al usurio si no lo esta.
	 * 
	 * @param loginID
	 *            the login id
	 * @param serverID
	 *            the server id
	 * @return the boolean
	 */
	public Boolean loginSiNoLoEsta(final Integer loginID, final Integer serverID);

}
