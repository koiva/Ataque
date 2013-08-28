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
 * The Class AlianzaDTO.
 */
@Entity
@Table(name = "INF_Alianza", uniqueConstraints = { @UniqueConstraint(columnNames = { "gameID", "servidorID" }) })
public class AlianzaDTO implements Serializable, RecuperarGameID {

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 1370038875165404880L;

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

	/** The tag. */
	@Column
	private String				tag;

	/** The miembros. */
	@Column
	private int					miembros;

	/** The poblados. */
	@Column
	private int					poblados;

	/** The puntos. */
	@Column
	private int					puntos;

	/** The puntos totales. */
	@Column
	private int					puntosTotales;

	/** The rank. */
	@Column
	private int					rank;

	/** The lista jugadores dto. */
	@OneToMany
	@Column
	private List<JugadoresDTO>	listaJugadoresDTO;

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
		if (this.gameID != other.gameID) {
			return false;
		}
		if (this.id != other.id) {
			return false;
		}
		if (this.listaJugadoresDTO == null) {
			if (other.listaJugadoresDTO != null) {
				return false;
			}
		} else if (!this.listaJugadoresDTO.equals(other.listaJugadoresDTO)) {
			return false;
		}
		if (this.miembros != other.miembros) {
			return false;
		}
		if (this.nombre == null) {
			if (other.nombre != null) {
				return false;
			}
		} else if (!this.nombre.equals(other.nombre)) {
			return false;
		}
		if (this.poblados != other.poblados) {
			return false;
		}
		if (this.puntos != other.puntos) {
			return false;
		}
		if (this.puntosTotales != other.puntosTotales) {
			return false;
		}
		if (this.rank != other.rank) {
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
	public int getMiembros() {
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
	public int getPoblados() {
		return this.poblados;
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
	 * Gets the puntos totales.
	 * 
	 * @return the puntos totales
	 */
	public int getPuntosTotales() {
		return this.puntosTotales;
	}

	/**
	 * Gets the rank.
	 * 
	 * @return the rank
	 */
	public int getRank() {
		return this.rank;
	}

	/**
	 * Gets the servidor dto.
	 * 
	 * @return the servidor dto
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.gameID;
		result = prime * result + this.id;
		result = prime * result + ((this.listaJugadoresDTO == null) ? 0 : this.listaJugadoresDTO.hashCode());
		result = prime * result + this.miembros;
		result = prime * result + ((this.nombre == null) ? 0 : this.nombre.hashCode());
		result = prime * result + this.poblados;
		result = prime * result + this.puntos;
		result = prime * result + this.puntosTotales;
		result = prime * result + this.rank;
		result = prime * result + ((this.servidorDTO == null) ? 0 : this.servidorDTO.hashCode());
		result = prime * result + ((this.tag == null) ? 0 : this.tag.hashCode());
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
	public void setMiembros(final int miembros) {
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
	public void setPoblados(final int poblados) {
		this.poblados = poblados;
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
	 * Sets the puntos totales.
	 * 
	 * @param puntosTotales
	 *            the new puntos totales
	 */
	public void setPuntosTotales(final int puntosTotales) {
		this.puntosTotales = puntosTotales;
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
	 * Sets the tag.
	 * 
	 * @param tag
	 *            the new tag
	 */
	public void setTag(final String tag) {
		this.tag = tag;
	}

}
