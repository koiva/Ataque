package org.fbgk.ataque.guerrastribales.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.fbgk.ataque.bbdd.RecuperarGameID;

/**
 * The Class JugadoresDTO.
 */
@Entity
@Table(name = "INF_Jugador", uniqueConstraints = { @UniqueConstraint(columnNames = { "gameID", "servidorID" }) })
public class JugadoresDTO implements Serializable, RecuperarGameID {

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 35487285450852554L;

	/** The id. */
	@Id
	@GeneratedValue
	@Column
	private int					id;

	/** The game id. */
	@Column
	private int					gameID;

	/** The servidor dto. */
	@ManyToOne
	@JoinColumn(name = "servidorID")
	private ServidorDTO			servidorDTO;

	/** The nombre. */
	@Column
	private String				nombre;

	/** The alianza id. */
	@ManyToOne
	@JoinColumn(name = "alianzaID")
	private AlianzaDTO			alianzaDTO;

	/** The numero pueblos. */
	@Column
	private int					numeroPueblos;

	/** The puntos. */
	@Column
	private int					puntos;

	/** The ranking. */
	@Column
	private int					ranking;

	/** The lista pueblos dto. */
	@OneToMany
	@Column(name = "pueblosID")
	private List<PueblosDTO>	listaPueblosDTO;

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
		final JugadoresDTO other = (JugadoresDTO) obj;
		if (this.alianzaDTO == null) {
			if (other.alianzaDTO != null) {
				return false;
			}
		} else if (!this.alianzaDTO.equals(other.alianzaDTO)) {
			return false;
		}
		if (this.gameID != other.gameID) {
			return false;
		}
		if (this.id != other.id) {
			return false;
		}
		if (this.listaPueblosDTO == null) {
			if (other.listaPueblosDTO != null) {
				return false;
			}
		} else if (!this.listaPueblosDTO.equals(other.listaPueblosDTO)) {
			return false;
		}
		if (this.nombre == null) {
			if (other.nombre != null) {
				return false;
			}
		} else if (!this.nombre.equals(other.nombre)) {
			return false;
		}
		if (this.numeroPueblos != other.numeroPueblos) {
			return false;
		}
		if (this.puntos != other.puntos) {
			return false;
		}
		if (this.ranking != other.ranking) {
			return false;
		}
		if (this.servidorDTO == null) {
			if (other.servidorDTO != null) {
				return false;
			}
		} else if (!this.servidorDTO.equals(other.servidorDTO)) {
			return false;
		}
		return true;
	}

	/**
	 * Gets the alianza dto.
	 * 
	 * @return the alianza dto
	 */
	public AlianzaDTO getAlianzaDTO() {
		return this.alianzaDTO;
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
	 * Gets the nombre.
	 * 
	 * @return the nombre
	 */
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * Gets the numero pueblos.
	 * 
	 * @return the numero pueblos
	 */
	public int getNumeroPueblos() {
		return this.numeroPueblos;
	}

	/**
	 * Gets the puntos.
	 * 
	 * @return the puntos
	 */
	public int getPuntos() {
		return this.puntos;
	}

	/**
	 * Gets the ranking.
	 * 
	 * @return the ranking
	 */
	public int getRanking() {
		return this.ranking;
	}

	/**
	 * Gets the servidor dto.
	 * 
	 * @return the servidor dto
	 */
	public ServidorDTO getServidorDTO() {
		return this.servidorDTO;
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
		result = prime * result + ((this.alianzaDTO == null) ? 0 : this.alianzaDTO.hashCode());
		result = prime * result + this.gameID;
		result = prime * result + this.id;
		result = prime * result + ((this.listaPueblosDTO == null) ? 0 : this.listaPueblosDTO.hashCode());
		result = prime * result + ((this.nombre == null) ? 0 : this.nombre.hashCode());
		result = prime * result + this.numeroPueblos;
		result = prime * result + this.puntos;
		result = prime * result + this.ranking;
		result = prime * result + ((this.servidorDTO == null) ? 0 : this.servidorDTO.hashCode());
		return result;
	}

	/**
	 * Sets the alianza dto.
	 * 
	 * @param alianzaDTO
	 *            the new alianza dto
	 */
	public void setAlianzaDTO(final AlianzaDTO alianzaDTO) {
		this.alianzaDTO = alianzaDTO;
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
	 * Sets the nombre.
	 * 
	 * @param nombre
	 *            the new nombre
	 */
	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Sets the numero pueblos.
	 * 
	 * @param numeroPueblos
	 *            the new numero pueblos
	 */
	public void setNumeroPueblos(final int numeroPueblos) {
		this.numeroPueblos = numeroPueblos;
	}

	/**
	 * Sets the puntos.
	 * 
	 * @param puntos
	 *            the new puntos
	 */
	public void setPuntos(final int puntos) {
		this.puntos = puntos;
	}

	/**
	 * Sets the ranking.
	 * 
	 * @param ranking
	 *            the new ranking
	 */
	public void setRanking(final int ranking) {
		this.ranking = ranking;
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

}
