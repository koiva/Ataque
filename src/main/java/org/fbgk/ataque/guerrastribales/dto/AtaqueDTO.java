package org.fbgk.ataque.guerrastribales.dto;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.fbgk.ataque.bbdd.interfaz.SetearSerializable;

/**
 * The Class AtaqueDTO.
 */
@Entity
@Table(name = "INF_Ataque")
public class AtaqueDTO implements Serializable, SetearSerializable {

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 1461069021754043320L;

	/** The ataque id. */
	@GeneratedValue
	@Column
	@Id
	private Integer				ataqueID;

	/** The distancia max. */
	@Column
	private Double				distanciaMax;

	/** The game id. */
	@Column
	private Integer				gameIDAtaque;

	/** The ind barbaro. */
	@Column
	private Boolean				indBarbaro;

	/** The lista ataques dto. */
	@ManyToOne
	@JoinColumn(name = "listaAtaquesID")
	private ListaAtaquesDTO		listaAtaquesDTO;

	/** The tiempo ataque. */
	@Column
	private Calendar			tiempoAtaque;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final AtaqueDTO other = (AtaqueDTO) obj;
		if (this.ataqueID == null) {
			if (other.ataqueID != null) {
				return false;
			}
		} else if (!this.ataqueID.equals(other.ataqueID)) {
			return false;
		}
		if (this.distanciaMax == null) {
			if (other.distanciaMax != null) {
				return false;
			}
		} else if (!this.distanciaMax.equals(other.distanciaMax)) {
			return false;
		}
		if (this.gameIDAtaque == null) {
			if (other.gameIDAtaque != null) {
				return false;
			}
		} else if (!this.gameIDAtaque.equals(other.gameIDAtaque)) {
			return false;
		}
		if (this.listaAtaquesDTO == null) {
			if (other.listaAtaquesDTO != null) {
				return false;
			}
		} else if (!this.listaAtaquesDTO.equals(other.listaAtaquesDTO)) {
			return false;
		}
		if (this.tiempoAtaque == null) {
			if (other.tiempoAtaque != null) {
				return false;
			}
		} else if (!this.tiempoAtaque.equals(other.tiempoAtaque)) {
			return false;
		}
		return true;
	}

	/**
	 * Gets the ataque id.
	 * 
	 * @return the ataque id
	 */
	public Integer getAtaqueID() {
		return this.ataqueID;
	}

	/**
	 * Gets the distancia max.
	 * 
	 * @return the distancia max
	 */
	public Double getDistanciaMax() {
		return this.distanciaMax;
	}

	/**
	 * Gets the game id ataque.
	 * 
	 * @return the game id ataque
	 */
	public Integer getGameIDAtaque() {
		return this.gameIDAtaque;
	}

	/**
	 * Gets the ind barbaro.
	 * 
	 * @return the ind barbaro
	 */
	public Boolean getIndBarbaro() {
		return this.indBarbaro;
	}

	/**
	 * Gets the lista ataques dto.
	 * 
	 * @return the lista ataques dto
	 */
	public ListaAtaquesDTO getListaAtaquesDTO() {
		return this.listaAtaquesDTO;
	}

	/**
	 * Gets the tiempo ataque.
	 * 
	 * @return the tiempo ataque
	 */
	public Calendar getTiempoAtaque() {
		return this.tiempoAtaque;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.ataqueID == null) ? 0 : this.ataqueID.hashCode());
		result = prime * result + ((this.distanciaMax == null) ? 0 : this.distanciaMax.hashCode());
		result = prime * result + ((this.gameIDAtaque == null) ? 0 : this.gameIDAtaque.hashCode());
		result = prime * result + ((this.listaAtaquesDTO == null) ? 0 : this.listaAtaquesDTO.hashCode());
		result = prime * result + ((this.tiempoAtaque == null) ? 0 : this.tiempoAtaque.hashCode());
		return result;
	}

	/**
	 * Sets the ataque id.
	 * 
	 * @param ataqueID
	 *            the new ataque id
	 */
	public void setAtaqueID(final Integer ataqueID) {
		this.ataqueID = ataqueID;
	}

	/**
	 * Sets the distancia max.
	 * 
	 * @param distanciaMax
	 *            the new distancia max
	 */
	public void setDistanciaMax(final Double distanciaMax) {
		this.distanciaMax = distanciaMax;
	}

	/**
	 * Sets the game id ataque.
	 * 
	 * @param gameIDAtaque
	 *            the new game id ataque
	 */
	public void setGameIDAtaque(final Integer gameIDAtaque) {
		this.gameIDAtaque = gameIDAtaque;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fbgk.ataque.bbdd.interfaz.SetearSerializable#setId(java.lang.Integer)
	 */
	@Override
	public void setId(final Integer id) {
		this.ataqueID = id;
	}

	/**
	 * Sets the ind barbaro.
	 * 
	 * @param indBarbaro
	 *            the new ind barbaro
	 */
	public void setIndBarbaro(final Boolean indBarbaro) {
		this.indBarbaro = indBarbaro;
	}

	/**
	 * Sets the lista ataques dto.
	 * 
	 * @param listaAtaquesDTO
	 *            the new lista ataques dto
	 */
	public void setListaAtaquesDTO(final ListaAtaquesDTO listaAtaquesDTO) {
		this.listaAtaquesDTO = listaAtaquesDTO;
	}

	/**
	 * Sets the tiempo ataque.
	 * 
	 * @param tiempoAtaque
	 *            the new tiempo ataque
	 */
	public void setTiempoAtaque(final Calendar tiempoAtaque) {
		this.tiempoAtaque = tiempoAtaque;
	}

}
