/*
 * 
 */
package org.fbgk.ataque.guerrastribales.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.fbgk.ataque.bbdd.interfaz.RecuperarGameID;
import org.fbgk.ataque.bbdd.interfaz.SetearSerializable;

/**
 * The Class PueblosDTO.
 */
@Entity
@Table(name = "INF_Pueblos", uniqueConstraints = { @UniqueConstraint(columnNames = { "x", "y", "jugadoresID" }) })
public class PueblosDTO implements Serializable, RecuperarGameID, SetearSerializable {

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 1079034277929051557L;

	/** The id. */
	@Id
	@GeneratedValue
	@Column
	private Integer				pueblosID;

	/** The game id. */
	@Column
	private Integer				gameID;

	/** The name. */
	@Column
	private String				name;

	/** The x. */
	@Column
	private Integer				x;

	/** The y. */
	@Column
	private Integer				y;

	/** The player id. */
	@JoinColumn(name = "jugadoresID")
	@ManyToOne
	private JugadoresDTO		jugadoresDTO;

	/** The servidor dto. */
	@JoinColumn(name = "servidorID")
	@ManyToOne
	private ServidorDTO			servidorDTO;

	/** The points. */
	@Column
	private Integer				points;

	/** The rank. */
	@Column
	private Integer				rank;

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
		final PueblosDTO other = (PueblosDTO) obj;
		if (this.gameID == null) {
			if (other.gameID != null) {
				return false;
			}
		} else if (!this.gameID.equals(other.gameID)) {
			return false;
		}
		if (this.jugadoresDTO == null) {
			if (other.jugadoresDTO != null) {
				return false;
			}
		} else if (!this.jugadoresDTO.equals(other.jugadoresDTO)) {
			return false;
		}
		if (this.name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!this.name.equals(other.name)) {
			return false;
		}
		if (this.points == null) {
			if (other.points != null) {
				return false;
			}
		} else if (!this.points.equals(other.points)) {
			return false;
		}
		if (this.pueblosID == null) {
			if (other.pueblosID != null) {
				return false;
			}
		} else if (!this.pueblosID.equals(other.pueblosID)) {
			return false;
		}
		if (this.rank == null) {
			if (other.rank != null) {
				return false;
			}
		} else if (!this.rank.equals(other.rank)) {
			return false;
		}
		if (this.servidorDTO == null) {
			if (other.servidorDTO != null) {
				return false;
			}
		} else if (!this.servidorDTO.equals(other.servidorDTO)) {
			return false;
		}
		if (this.x == null) {
			if (other.x != null) {
				return false;
			}
		} else if (!this.x.equals(other.x)) {
			return false;
		}
		if (this.y == null) {
			if (other.y != null) {
				return false;
			}
		} else if (!this.y.equals(other.y)) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fbgk.ataque.bbdd.RecuperarGameID#getGameID()
	 */
	public Integer getGameID() {
		return this.gameID;
	}

	/**
	 * Gets the jugadores dto.
	 * 
	 * @return the jugadores dto
	 */
	public JugadoresDTO getJugadoresDTO() {
		return this.jugadoresDTO;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the points.
	 * 
	 * @return the points
	 */
	public Integer getPoints() {
		return this.points;
	}

	/**
	 * Gets the pueblos id.
	 * 
	 * @return the pueblos id
	 */
	public Integer getPueblosID() {
		return this.pueblosID;
	}

	/**
	 * Gets the rank.
	 * 
	 * @return the rank
	 */
	public Integer getRank() {
		return this.rank;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fbgk.ataque.bbdd.RecuperarGameID#getServidorDTO()
	 */
	public ServidorDTO getServidorDTO() {
		return this.servidorDTO;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.gameID == null) ? 0 : this.gameID.hashCode());
		result = prime * result + ((this.jugadoresDTO == null) ? 0 : this.jugadoresDTO.hashCode());
		result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
		result = prime * result + ((this.points == null) ? 0 : this.points.hashCode());
		result = prime * result + ((this.pueblosID == null) ? 0 : this.pueblosID.hashCode());
		result = prime * result + ((this.rank == null) ? 0 : this.rank.hashCode());
		result = prime * result + ((this.servidorDTO == null) ? 0 : this.servidorDTO.hashCode());
		result = prime * result + ((this.x == null) ? 0 : this.x.hashCode());
		result = prime * result + ((this.y == null) ? 0 : this.y.hashCode());
		return result;
	}

	/**
	 * Sets the game id.
	 * 
	 * @param gameID
	 *            the new game id
	 */
	public void setGameID(final Integer gameID) {
		this.gameID = gameID;
	}

	public void setId(final Integer id) {
		this.pueblosID = id;
	}

	/**
	 * Sets the jugadores dto.
	 * 
	 * @param jugadoresDTO
	 *            the new jugadores dto
	 */
	public void setJugadoresDTO(final JugadoresDTO jugadoresDTO) {
		this.jugadoresDTO = jugadoresDTO;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the new name
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Sets the points.
	 * 
	 * @param points
	 *            the new points
	 */
	public void setPoints(final Integer points) {
		this.points = points;
	}

	/**
	 * Sets the pueblos id.
	 * 
	 * @param pueblosID
	 *            the new pueblos id
	 */
	public void setPueblosID(final Integer pueblosID) {
		this.pueblosID = pueblosID;
	}

	/**
	 * Sets the rank.
	 * 
	 * @param rank
	 *            the new rank
	 */
	public void setRank(final Integer rank) {
		this.rank = rank;
	}

	/**
	 * Sets the servidor dto.
	 * 
	 * @param servidorDTO
	 *            the new servidor dto
	 */
	public void setServidorDTO(final ServidorDTO servidorDTO) {
		this.servidorDTO = servidorDTO;
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
