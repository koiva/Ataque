package org.fbgk.ataque.actions.constantes;

public class GuerrasTribalesActions {

	// %s es el servidor que corresponde a servidor
	public final static String	ACTION_FORM_LOGIN			= "index.php?action=login";

	public final static String	ACTION_FORM_LOGIN_USER		= "user";

	public final static String	ACTION_FORM_LOGIN_PASS		= "password";

	public final static String	ACTION_FORM_SERVER			= "&server_%s";

	public final static String	ACTION_FORM_SELECT_SERVER	= "&show_server_selection=1";

	/**
	 * 1º %s es el juego 2º %s es la internalizacion
	 */
	public final static String	ACTION_URL					= "http://www.%s.%s/";

	public final static String	REGEX_PASSWORD_CODE			= "name=\\\\\"password\\\\\" type=\\\\\"hidden\\\\\" value=\\\\\"(.+)\\\\\" \\\\/>";

	public final static String	LOGIN_COMPROBADOR			= "http://%s.%s.%s/game.php?screen=overview_villages&intro";

	public final static String	TROPAS_PAGINA_COMPROBAR		= "http://%s.%s.%s/game.php?village=%s&screen=place&mode=units";

	public final static String	ATAQUE_PRIMER_PROCESO		= "http://%s.%s.%s/game.php?village=%s&target=%s&screen=place";

	public final static String	ATAQUE_SEGUNDO_PROCESO		= "http://%s.%s.%s/game.php?village=%s&try=confirm&screen=place";

	public final static String	ATAQUE_PROCESO				= "http://%s.%s.%s";

}
