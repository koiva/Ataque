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

import org.fbgk.ataque.bbdd.interfaz.RecuperarGameID;
import org.fbgk.ataque.bbdd.interfaz.SetearSerializable;

/**
 * The Class JugadoresDTO.
 */
@Entity
@Table(name = "INF_Jugador", uniqueConstraints = { @UniqueConstraint(columnNames = { "gameID", "servidorID" }) })
public class JugadoresDTO implements Serializable, RecuperarGameID, SetearSerializable {

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 35487285450852554L;

	/** The id. */
	@Id
	@GeneratedValue
	@Column
	private Integer				jugadoresID;

	/** The game id. */
	@Column
	private Integer				gameID;

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
	private Integer				numeroPueblos;

	/** The puntos. */
	@Column
	private Integer				puntos;

	/** The ranking. */
	@Column
	private Integer				ranking;

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
		if (this.gameID == null) {
			if (other.gameID != null) {
				return false;
			}
		} else if (!this.gameID.equals(other.gameID)) {
			return false;
		}
		if (this.jugadoresID == null) {
			if (other.jugadoresID != null) {
				return false;
			}
		} else if (!this.jugadoresID.equals(other.jugadoresID)) {
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
		if (this.numeroPueblos == null) {
			if (other.numeroPueblos != null) {
				return false;
			}
		} else if (!this.numeroPueblos.equals(other.numeroPueblos)) {
			return false;
		}
		if (this.puntos == null) {
			if (other.puntos != null) {
				return false;
			}
		} else if (!this.puntos.equals(other.puntos)) {
			return false;
		}
		if (this.ranking == null) {
			if (other.ranking != null) {
				return false;
			}
		} else if (!this.ranking.equals(other.ranking)) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fbgk.ataque.bbdd.RecuperarGameID#getGameID()
	 */
	public Integer getGameID() {
		return this.gameID;
	}

	/**
	 * Gets the jugadores id.
	 * 
	 * @return the jugadores id
	 */
	public Integer getJugadoresID() {
		return this.jugadoresID;
	}

	/**
	 * Gets the lista pueblos dto.
	 * 
	 * @return the lista pueblos dto
	 */
	public List<PueblosDTO> getListaPueblosDTO() {
		return this.listaPueblosDTO;
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
	public Integer getNumeroPueblos() {
		return this.numeroPueblos;
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
	 * Gets the ranking.
	 * 
	 * @return the ranking
	 */
	public Integer getRanking() {
		return this.ranking;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fbgk.ataque.bbdd.RecuperarGameID#getServidorDTO()
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
		result = prime * result + ((this.gameID == null) ? 0 : this.gameID.hashCode());
		result = prime * result + ((this.jugadoresID == null) ? 0 : this.jugadoresID.hashCode());
		result = prime * result + ((this.listaPueblosDTO == null) ? 0 : this.listaPueblosDTO.hashCode());
		result = prime * result + ((this.nombre == null) ? 0 : this.nombre.hashCode());
		result = prime * result + ((this.numeroPueblos == null) ? 0 : this.numeroPueblos.hashCode());
		result = prime * result + ((this.puntos == null) ? 0 : this.puntos.hashCode());
		result = prime * result + ((this.ranking == null) ? 0 : this.ranking.hashCode());
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
	public void setGameID(final Integer gameID) {
		this.gameID = gameID;
	}

	public void setId(final Integer id) {
		this.jugadoresID = id;
	}

	/**
	 * Sets the jugadores id.
	 * 
	 * @param jugadoresID
	 *            the new jugadores id
	 */
	public void setJugadoresID(final Integer jugadoresID) {
		this.jugadoresID = jugadoresID;
	}

	/**
	 * Sets the lista pueblos dto.
	 * 
	 * @param listaPueblosDTO
	 *            the new lista pueblos dto
	 */
	public void setListaPueblosDTO(final List<PueblosDTO> listaPueblosDTO) {
		this.listaPueblosDTO = listaPueblosDTO;
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
	public void setNumeroPueblos(final Integer numeroPueblos) {
		this.numeroPueblos = numeroPueblos;
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
	 * Sets the ranking.
	 * 
	 * @param ranking
	 *            the new ranking
	 */
	public void setRanking(final Integer ranking) {
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
