package org.fbgk.ataque.vistas.constantes;

/**
 * The Enum Configuracion.
 */
public enum Configuracion {

	/** The atacarcompleto. */
	ATACARCOMPLETO("atacarCompleto"),
	/** The barbaros. */
	BARBAROS("barbaroComprobacion"),
	/** The eliminarinformelista. */
	ELIMINARINFORMELISTA("eliminarInformeLista"),
	/** The leerinformes. */
	LEERINFORMES("leerInforme"),
	/** The minimoataquecompleto. */
	MINIMOATAQUECOMPLETO("minimoAtaque"),
	/** The tiempoataque. */
	TIEMPOATAQUE("tiempoAtaque");

	/** The key. */
	private String	key;

	/**
	 * Instantiates a new configuracion.
	 * 
	 * @param key
	 *            the key
	 */
	private Configuracion(final String key) {
		this.key = key;
	}

	/**
	 * Gets the key.
	 * 
	 * @return the key
	 */
	public String getKey() {
		return this.key;
	}
}
