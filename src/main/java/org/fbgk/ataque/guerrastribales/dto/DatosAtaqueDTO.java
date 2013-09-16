package org.fbgk.ataque.guerrastribales.dto;

/**
 * The Class DatosAtaqueDTO.
 */
public class DatosAtaqueDTO {

	/** The distancia. */
	private Double	distancia;

	/** The fecha. */
	private String	fecha;

	/** The puntos. */
	private Integer	puntos;

	/** The x. */
	private Integer	x;

	/** The y. */
	private Integer	y;

	public Double getDistancia() {
		return this.distancia;
	}

	/**
	 * Gets the fecha.
	 * 
	 * @return the fecha
	 */
	public String getFecha() {
		return this.fecha;
	}

	/**
	 * Gets the puntos.
	 * 
	 * @return the puntos
	 */
	public Integer getPuntos() {
		return this.puntos;
	}

	/**
	 * Gets the x.
	 * 
	 * @return the x
	 */
	public Integer getX() {
		return this.x;
	}

	/**
	 * Gets the y.
	 * 
	 * @return the y
	 */
	public Integer getY() {
		return this.y;
	}

	public void setDistancia(final Double distancia) {
		this.distancia = distancia;
	}

	/**
	 * Sets the fecha.
	 * 
	 * @param fecha
	 *            the new fecha
	 */
	public void setFecha(final String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Sets the puntos.
	 * 
	 * @param puntos
	 *            the new puntos
	 */
	public void setPuntos(final Integer puntos) {
		this.puntos = puntos;
	}

	/**
	 * Sets the x.
	 * 
	 * @param x
	 *            the new x
	 */
	public void setX(final Integer x) {
		this.x = x;
	}

	/**
	 * Sets the y.
	 * 
	 * @param y
	 *            the new y
	 */
	public void setY(final Integer y) {
		this.y = y;
	}

}
