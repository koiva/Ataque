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

import org.fbgk.ataque.bbdd.RecuperarGameID;

/**
 * The Class PueblosDTO.
 */
@Entity
@Table(name = "INF_Pueblos", uniqueConstraints = { @UniqueConstraint(columnNames = { "x", "y", "jugadorID" }) })
public class PueblosDTO implements Serializable, RecuperarGameID {

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 1079034277929051557L;

	/** The id. */
	@Id
	@GeneratedValue
	@Column
	private int					id;

	/** The game id. */
	@Column
	private int					gameID;

	/** The name. */
	@Column
	private String				name;

	/** The x. */
	@Column
	private int					x;

	/** The y. */
	@Column
	private int					y;

	/** The player id. */
	@JoinColumn(name = "jugadorID")
	@ManyToOne
	private JugadoresDTO		jugadoresDTO;

	/** The servidor dto. */
	@JoinColumn(name = "servidorID")
	@ManyToOne
	private ServidorDTO			servidorDTO;

	/** The points. */
	@Column
	private int					points;

	/** The rank. */
	@Column
	private int					rank;

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
		if (this.gameID != other.gameID) {
			return false;
		}
		if (this.id != other.id) {
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
		if (this.points != other.points) {
			return false;
		}
		if (this.rank != other.rank) {
			return false;
		}
		if (this.x != other.x) {
			return false;
		}
		if (this.y != other.y) {
			return false;
		}
		return true;
	}

	/**
	 * Gets the game id.
	 * 
	 * @return the game id
	 */
	public int getGameID() {
		return this.gameID;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public int getId() {
		return this.id;
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
	public int getPoints() {
		return this.points;
	}

	/**
	 * Gets the rank.
	 * 
	 * @return the rank
	 */
	public int getRank() {
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
	public int getX() {
		return this.x;
	}

	/**
	 * Gets the y.
	 * 
	 * @return the y
	 */
	public int getY() {
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
		result = prime * result + this.gameID;
		result = prime * result + this.id;
		result = prime * result + ((this.jugadoresDTO == null) ? 0 : this.jugadoresDTO.hashCode());
		result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
		result = prime * result + this.points;
		result = prime * result + this.rank;
		result = prime * result + this.x;
		result = prime * result + this.y;
		return result;
	}

	/**
	 * Sets the game id.
	 * 
	 * @param gameID
	 *            the new game id
	 */
	public void setGameID(final int gameID) {
		this.gameID = gameID;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
	public void setId(final int id) {
		this.id = id;
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
	public void setPoints(final int points) {
		this.points = points;
	}

	/**
	 * Sets the rank.
	 * 
	 * @param rank
	 *            the new rank
	 */
	public void setRank(final int rank) {
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
	public void setX(final int x) {
		this.x = x;
	}

	/**
	 * Sets the y.
	 * 
	 * @param y
	 *            the new y
	 */
	public void setY(final int y) {
		this.y = y;
	}

}
