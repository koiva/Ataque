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
 * The Class AlianzaDTO.
 */
@Entity
@Table(name = "INF_Alianza", uniqueConstraints = { @UniqueConstraint(columnNames = { "gameID", "servidorID" }) })
public class AlianzaDTO implements Serializable, RecuperarGameID, SetearSerializable {

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 1370038875165404880L;

	/** The id. */
	@Id
	@GeneratedValue
	@Column
	private Integer				alianzaID;

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

	/** The tag. */
	@Column
	private String				tag;

	/** The miembros. */
	@Column
	private Integer				miembros;

	/** The poblados. */
	@Column
	private Integer				poblados;

	/** The puntos. */
	@Column
	private Integer				puntos;

	/** The puntos totales. */
	@Column
	private Integer				puntosTotales;

	/** The rank. */
	@Column
	private Integer				rank;

	/** The lista jugadores dto. */
	@OneToMany
	@Column(name = "jugadoresID")
	private List<JugadoresDTO>	listaJugadoresDTO;

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
		final AlianzaDTO other = (AlianzaDTO) obj;
		if (this.alianzaID == null) {
			if (other.alianzaID != null) {
				return false;
			}
		} else if (!this.alianzaID.equals(other.alianzaID)) {
			return false;
		}
		if (this.gameID == null) {
			if (other.gameID != null) {
				return false;
			}
		} else if (!this.gameID.equals(other.gameID)) {
			return false;
		}
		if (this.listaJugadoresDTO == null) {
			if (other.listaJugadoresDTO != null) {
				return false;
			}
		} else if (!this.listaJugadoresDTO.equals(other.listaJugadoresDTO)) {
			return false;
		}
		if (this.miembros == null) {
			if (other.miembros != null) {
				return false;
			}
		} else if (!this.miembros.equals(other.miembros)) {
			return false;
		}
		if (this.nombre == null) {
			if (other.nombre != null) {
				return false;
			}
		} else if (!this.nombre.equals(other.nombre)) {
			return false;
		}
		if (this.poblados == null) {
			if (other.poblados != null) {
				return false;
			}
		} else if (!this.poblados.equals(other.poblados)) {
			return false;
		}
		if (this.puntos == null) {
			if (other.puntos != null) {
				return false;
			}
		} else if (!this.puntos.equals(other.puntos)) {
			return false;
		}
		if (this.puntosTotales == null) {
			if (other.puntosTotales != null) {
				return false;
			}
		} else if (!this.puntosTotales.equals(other.puntosTotales)) {
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
		if (this.tag == null) {
			if (other.tag != null) {
				return false;
			}
		} else if (!this.tag.equals(other.tag)) {
			return false;
		}
		return true;
	}

	/**
	 * Gets the alianza id.
	 * 
	 * @return the alianza id
	 */
	public Integer getAlianzaID() {
		return this.alianzaID;
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
	 * Gets the lista jugadores dto.
	 * 
	 * @return the lista jugadores dto
	 */
	public List<JugadoresDTO> getListaJugadoresDTO() {
		return this.listaJugadoresDTO;
	}

	/**
	 * Gets the miembros.
	 * 
	 * @return the miembros
	 */
	public Integer getMiembros() {
		return this.miembros;
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
	 * Gets the poblados.
	 * 
	 * @return the poblados
	 */
	public Integer getPoblados() {
		return this.poblados;
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
	 * Gets the puntos totales.
	 * 
	 * @return the puntos totales
	 */
	public Integer getPuntosTotales() {
		return this.puntosTotales;
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
	 * Gets the tag.
	 * 
	 * @return the tag
	 */
	public String getTag() {
		return this.tag;
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
		result = prime * result + ((this.alianzaID == null) ? 0 : this.alianzaID.hashCode());
		result = prime * result + ((this.gameID == null) ? 0 : this.gameID.hashCode());
		result = prime * result + ((this.listaJugadoresDTO == null) ? 0 : this.listaJugadoresDTO.hashCode());
		result = prime * result + ((this.miembros == null) ? 0 : this.miembros.hashCode());
		result = prime * result + ((this.nombre == null) ? 0 : this.nombre.hashCode());
		result = prime * result + ((this.poblados == null) ? 0 : this.poblados.hashCode());
		result = prime * result + ((this.puntos == null) ? 0 : this.puntos.hashCode());
		result = prime * result + ((this.puntosTotales == null) ? 0 : this.puntosTotales.hashCode());
		result = prime * result + ((this.rank == null) ? 0 : this.rank.hashCode());
		result = prime * result + ((this.servidorDTO == null) ? 0 : this.servidorDTO.hashCode());
		result = prime * result + ((this.tag == null) ? 0 : this.tag.hashCode());
		return result;
	}

	/**
	 * Sets the alianza id.
	 * 
	 * @param alianzaID
	 *            the new alianza id
	 */
	public void setAlianzaID(final Integer alianzaID) {
		this.alianzaID = alianzaID;
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
		this.alianzaID = id;
	}

	/**
	 * Sets the lista jugadores dto.
	 * 
	 * @param listaJugadoresDTO
	 *            the new lista jugadores dto
	 */
	public void setListaJugadoresDTO(final List<JugadoresDTO> listaJugadoresDTO) {
		this.listaJugadoresDTO = listaJugadoresDTO;
	}

	/**
	 * Sets the miembros.
	 * 
	 * @param miembros
	 *            the new miembros
	 */
	public void setMiembros(final Integer miembros) {
		this.miembros = miembros;
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
	 * Sets the poblados.
	 * 
	 * @param poblados
	 *            the new poblados
	 */
	public void setPoblados(final Integer poblados) {
		this.poblados = poblados;
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
	 * Sets the puntos totales.
	 * 
	 * @param puntosTotales
	 *            the new puntos totales
	 */
	public void setPuntosTotales(final Integer puntosTotales) {
		this.puntosTotales = puntosTotales;
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
	 * Sets the tag.
	 * 
	 * @param tag
	 *            the new tag
	 */
	public void setTag(final String tag) {
		this.tag = tag;
	}

}
