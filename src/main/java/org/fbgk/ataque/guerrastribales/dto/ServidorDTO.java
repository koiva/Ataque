package org.fbgk.ataque.guerrastribales.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.fbgk.ataque.bbdd.interfaz.SetearSerializable;

/**
 * The Class ServidorDTO.
 */
@Entity
@Table(name = "INF_Servidor", uniqueConstraints = { @UniqueConstraint(columnNames = { "juego", "server" }) })
public class ServidorDTO implements Serializable, SetearSerializable {

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 1L;

	/** The id. */
	@Id
	@GeneratedValue
	@Column
	private Integer				servidorID;

	/** The juego. */
	@Column
	private String				juego;

	/** The server. */
	@Column
	private String				server;

	/** The internalizacion. */
	@Column
	private String				internalizacion;

	/** The lista login dto. */
	@ManyToMany
	@Column(name = "loginID")
	private List<LoginDTO>		listaLoginDTO;

	/** The lista alianza dto. */
	@OneToMany
	@Column(name = "alianzaID")
	private List<AlianzaDTO>	listaAlianzaDTO;

	/** The lista jugadores dto. */
	@OneToMany
	@Column(name = "jugadoresID")
	private List<JugadoresDTO>	listaJugadoresDTO;

	/** The lista poblados dto. */
	@OneToMany
	@Column(name = "pueblosID")
	private List<PueblosDTO>	listaPobladosDTO;

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
		final ServidorDTO other = (ServidorDTO) obj;
		if (this.internalizacion == null) {
			if (other.internalizacion != null) {
				return false;
			}
		} else if (!this.internalizacion.equals(other.internalizacion)) {
			return false;
		}
		if (this.juego == null) {
			if (other.juego != null) {
				return false;
			}
		} else if (!this.juego.equals(other.juego)) {
			return false;
		}
		if (this.listaAlianzaDTO == null) {
			if (other.listaAlianzaDTO != null) {
				return false;
			}
		} else if (!this.listaAlianzaDTO.equals(other.listaAlianzaDTO)) {
			return false;
		}
		if (this.listaJugadoresDTO == null) {
			if (other.listaJugadoresDTO != null) {
				return false;
			}
		} else if (!this.listaJugadoresDTO.equals(other.listaJugadoresDTO)) {
			return false;
		}
		if (this.listaLoginDTO == null) {
			if (other.listaLoginDTO != null) {
				return false;
			}
		} else if (!this.listaLoginDTO.equals(other.listaLoginDTO)) {
			return false;
		}
		if (this.listaPobladosDTO == null) {
			if (other.listaPobladosDTO != null) {
				return false;
			}
		} else if (!this.listaPobladosDTO.equals(other.listaPobladosDTO)) {
			return false;
		}
		if (this.server == null) {
			if (other.server != null) {
				return false;
			}
		} else if (!this.server.equals(other.server)) {
			return false;
		}
		if (this.servidorID == null) {
			if (other.servidorID != null) {
				return false;
			}
		} else if (!this.servidorID.equals(other.servidorID)) {
			return false;
		}
		return true;
	}

	/**
	 * Gets the internalizacion.
	 * 
	 * @return the internalizacion
	 */
	public String getInternalizacion() {
		return this.internalizacion;
	}

	/**
	 * Gets the juego.
	 * 
	 * @return the juego
	 */
	public String getJuego() {
		return this.juego;
	}

	/**
	 * Gets the lista alianza dto.
	 * 
	 * @return the lista alianza dto
	 */
	public List<AlianzaDTO> getListaAlianzaDTO() {
		return this.listaAlianzaDTO;
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
	 * Gets the lista login dto.
	 * 
	 * @return the lista login dto
	 */
	public List<LoginDTO> getListaLoginDTO() {
		return this.listaLoginDTO;
	}

	/**
	 * Gets the lista poblados dto.
	 * 
	 * @return the lista poblados dto
	 */
	public List<PueblosDTO> getListaPobladosDTO() {
		return this.listaPobladosDTO;
	}

	/**
	 * Gets the server.
	 * 
	 * @return the server
	 */
	public String getServer() {
		return this.server;
	}

	/**
	 * Gets the servidor id.
	 * 
	 * @return the servidor id
	 */
	public Integer getServidorID() {
		return this.servidorID;
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
		result = prime * result + ((this.internalizacion == null) ? 0 : this.internalizacion.hashCode());
		result = prime * result + ((this.juego == null) ? 0 : this.juego.hashCode());
		result = prime * result + ((this.listaAlianzaDTO == null) ? 0 : this.listaAlianzaDTO.hashCode());
		result = prime * result + ((this.listaJugadoresDTO == null) ? 0 : this.listaJugadoresDTO.hashCode());
		result = prime * result + ((this.listaLoginDTO == null) ? 0 : this.listaLoginDTO.hashCode());
		result = prime * result + ((this.listaPobladosDTO == null) ? 0 : this.listaPobladosDTO.hashCode());
		result = prime * result + ((this.server == null) ? 0 : this.server.hashCode());
		result = prime * result + ((this.servidorID == null) ? 0 : this.servidorID.hashCode());
		return result;
	}

	public void setId(final Integer id) {
		this.servidorID = id;
	}

	/**
	 * Sets the internalizacion.
	 * 
	 * @param internalizacion
	 *            the new internalizacion
	 */
	public void setInternalizacion(final String internalizacion) {
		this.internalizacion = internalizacion;
	}

	/**
	 * Sets the juego.
	 * 
	 * @param juego
	 *            the new juego
	 */
	public void setJuego(final String juego) {
		this.juego = juego;
	}

	/**
	 * Sets the lista alianza dto.
	 * 
	 * @param listaAlianzaDTO
	 *            the new lista alianza dto
	 */
	public void setListaAlianzaDTO(final List<AlianzaDTO> listaAlianzaDTO) {
		this.listaAlianzaDTO = listaAlianzaDTO;
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
	 * Sets the lista login dto.
	 * 
	 * @param listaLoginDTO
	 *            the new lista login dto
	 */
	public void setListaLoginDTO(final List<LoginDTO> listaLoginDTO) {
		this.listaLoginDTO = listaLoginDTO;
	}

	/**
	 * Sets the lista poblados dto.
	 * 
	 * @param listaPobladosDTO
	 *            the new lista poblados dto
	 */
	public void setListaPobladosDTO(final List<PueblosDTO> listaPobladosDTO) {
		this.listaPobladosDTO = listaPobladosDTO;
	}

	/**
	 * Sets the server.
	 * 
	 * @param server
	 *            the new server
	 */
	public void setServer(final String server) {
		this.server = server;
	}

	/**
	 * Sets the servidor id.
	 * 
	 * @param servidorID
	 *            the new servidor id
	 */
	public void setServidorID(final Integer servidorID) {
		this.servidorID = servidorID;
	}

	@Override
	public String toString() {
		return String.format("http://%s.%s.%s", this.server, this.juego, this.internalizacion);
	}

}
