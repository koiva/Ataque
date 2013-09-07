package org.fbgk.ataque.guerrastribales.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.fbgk.ataque.bbdd.interfaz.SetearSerializable;

/**
 * The Class ListaAtaquesDTO.
 */
@Table(name = "INF_ListaAtaque")
@Entity
public class ListaAtaquesDTO implements Serializable, SetearSerializable {

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 8253003412627107069L;

	/** The lista ataques id. */
	@Column
	@Id
	@GeneratedValue
	private Integer				listaAtaquesID;

	/** The nombre. */
	@Column
	private String				nombre;

	/** The game id propio. */
	@Column
	private Integer				gameIDPropio;

	/** The servidor dto. */
	@JoinColumn(name = "servidorID")
	@ManyToOne
	private ServidorDTO			servidorDTO;

	/** The lista ataques dto. */
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "ataquesID")
	private List<AtaqueDTO>		listaAtaquesDTO;

	/** The lancero. */
	@Column
	private Integer				lancero				= Integer.valueOf(0);

	/** The espadas. */
	@Column
	private Integer				espadas				= Integer.valueOf(0);

	/** The hachas. */
	@Column
	private Integer				hachas				= Integer.valueOf(0);

	/** The espias. */
	@Column
	private Integer				espias				= Integer.valueOf(0);

	/** The ligeros. */
	@Column
	private Integer				ligeros				= Integer.valueOf(0);

	/** The pesados. */
	@Column
	private Integer				pesados				= Integer.valueOf(0);

	/** The ariete. */
	@Column
	private Integer				ariete				= Integer.valueOf(0);

	/** The catapulta. */
	@Column
	private Integer				catapulta			= Integer.valueOf(0);

	/** The noble. */
	@Column
	private Integer				noble				= Integer.valueOf(0);

	/** The arquero. */
	@Column
	private Integer				arquero				= Integer.valueOf(0);

	/** The caballo arquero. */
	@Column
	private Integer				caballoArquero		= Integer.valueOf(0);

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
		final ListaAtaquesDTO other = (ListaAtaquesDTO) obj;
		if (this.ariete == null) {
			if (other.ariete != null) {
				return false;
			}
		} else if (!this.ariete.equals(other.ariete)) {
			return false;
		}
		if (this.arquero == null) {
			if (other.arquero != null) {
				return false;
			}
		} else if (!this.arquero.equals(other.arquero)) {
			return false;
		}
		if (this.caballoArquero == null) {
			if (other.caballoArquero != null) {
				return false;
			}
		} else if (!this.caballoArquero.equals(other.caballoArquero)) {
			return false;
		}
		if (this.catapulta == null) {
			if (other.catapulta != null) {
				return false;
			}
		} else if (!this.catapulta.equals(other.catapulta)) {
			return false;
		}
		if (this.espadas == null) {
			if (other.espadas != null) {
				return false;
			}
		} else if (!this.espadas.equals(other.espadas)) {
			return false;
		}
		if (this.espias == null) {
			if (other.espias != null) {
				return false;
			}
		} else if (!this.espias.equals(other.espias)) {
			return false;
		}
		if (this.gameIDPropio == null) {
			if (other.gameIDPropio != null) {
				return false;
			}
		} else if (!this.gameIDPropio.equals(other.gameIDPropio)) {
			return false;
		}
		if (this.hachas == null) {
			if (other.hachas != null) {
				return false;
			}
		} else if (!this.hachas.equals(other.hachas)) {
			return false;
		}
		if (this.lancero == null) {
			if (other.lancero != null) {
				return false;
			}
		} else if (!this.lancero.equals(other.lancero)) {
			return false;
		}
		if (this.ligeros == null) {
			if (other.ligeros != null) {
				return false;
			}
		} else if (!this.ligeros.equals(other.ligeros)) {
			return false;
		}
		if (this.listaAtaquesDTO == null) {
			if (other.listaAtaquesDTO != null) {
				return false;
			}
		} else if (!this.listaAtaquesDTO.equals(other.listaAtaquesDTO)) {
			return false;
		}
		if (this.listaAtaquesID == null) {
			if (other.listaAtaquesID != null) {
				return false;
			}
		} else if (!this.listaAtaquesID.equals(other.listaAtaquesID)) {
			return false;
		}
		if (this.noble == null) {
			if (other.noble != null) {
				return false;
			}
		} else if (!this.noble.equals(other.noble)) {
			return false;
		}
		if (this.nombre == null) {
			if (other.nombre != null) {
				return false;
			}
		} else if (!this.nombre.equals(other.nombre)) {
			return false;
		}
		if (this.pesados == null) {
			if (other.pesados != null) {
				return false;
			}
		} else if (!this.pesados.equals(other.pesados)) {
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
	 * Gets the ariete.
	 * 
	 * @return the ariete
	 */
	public Integer getAriete() {
		return this.ariete;
	}

	/**
	 * Gets the arquero.
	 * 
	 * @return the arquero
	 */
	public Integer getArquero() {
		return this.arquero;
	}

	/**
	 * Gets the caballo arquero.
	 * 
	 * @return the caballo arquero
	 */
	public Integer getCaballoArquero() {
		return this.caballoArquero;
	}

	/**
	 * Gets the catapulta.
	 * 
	 * @return the catapulta
	 */
	public Integer getCatapulta() {
		return this.catapulta;
	}

	/**
	 * Gets the espadas.
	 * 
	 * @return the espadas
	 */
	public Integer getEspadas() {
		return this.espadas;
	}

	/**
	 * Gets the espias.
	 * 
	 * @return the espias
	 */
	public Integer getEspias() {
		return this.espias;
	}

	/**
	 * Gets the game id propio.
	 * 
	 * @return the game id propio
	 */
	public Integer getGameIDPropio() {
		return this.gameIDPropio;
	}

	/**
	 * Gets the hachas.
	 * 
	 * @return the hachas
	 */
	public Integer getHachas() {
		return this.hachas;
	}

	/**
	 * Gets the lancero.
	 * 
	 * @return the lancero
	 */
	public Integer getLancero() {
		return this.lancero;
	}

	/**
	 * Gets the ligeros.
	 * 
	 * @return the ligeros
	 */
	public Integer getLigeros() {
		return this.ligeros;
	}

	/**
	 * Gets the lista ataques dto.
	 * 
	 * @return the lista ataques dto
	 */
	public List<AtaqueDTO> getListaAtaquesDTO() {
		return this.listaAtaquesDTO;
	}

	/**
	 * Gets the lista ataques id.
	 * 
	 * @return the lista ataques id
	 */
	public Integer getListaAtaquesID() {
		return this.listaAtaquesID;
	}

	/**
	 * Gets the noble.
	 * 
	 * @return the noble
	 */
	public Integer getNoble() {
		return this.noble;
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
	 * Gets the pesados.
	 * 
	 * @return the pesados
	 */
	public Integer getPesados() {
		return this.pesados;
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
		result = prime * result + ((this.ariete == null) ? 0 : this.ariete.hashCode());
		result = prime * result + ((this.arquero == null) ? 0 : this.arquero.hashCode());
		result = prime * result + ((this.caballoArquero == null) ? 0 : this.caballoArquero.hashCode());
		result = prime * result + ((this.catapulta == null) ? 0 : this.catapulta.hashCode());
		result = prime * result + ((this.espadas == null) ? 0 : this.espadas.hashCode());
		result = prime * result + ((this.espias == null) ? 0 : this.espias.hashCode());
		result = prime * result + ((this.gameIDPropio == null) ? 0 : this.gameIDPropio.hashCode());
		result = prime * result + ((this.hachas == null) ? 0 : this.hachas.hashCode());
		result = prime * result + ((this.lancero == null) ? 0 : this.lancero.hashCode());
		result = prime * result + ((this.ligeros == null) ? 0 : this.ligeros.hashCode());
		result = prime * result + ((this.listaAtaquesDTO == null) ? 0 : this.listaAtaquesDTO.hashCode());
		result = prime * result + ((this.listaAtaquesID == null) ? 0 : this.listaAtaquesID.hashCode());
		result = prime * result + ((this.noble == null) ? 0 : this.noble.hashCode());
		result = prime * result + ((this.nombre == null) ? 0 : this.nombre.hashCode());
		result = prime * result + ((this.pesados == null) ? 0 : this.pesados.hashCode());
		result = prime * result + ((this.servidorDTO == null) ? 0 : this.servidorDTO.hashCode());
		return result;
	}

	/**
	 * Sets the ariete.
	 * 
	 * @param ariete
	 *            the new ariete
	 */
	public void setAriete(final Integer ariete) {
		this.ariete = ariete;
	}

	/**
	 * Sets the arquero.
	 * 
	 * @param arquero
	 *            the new arquero
	 */
	public void setArquero(final Integer arquero) {
		this.arquero = arquero;
	}

	/**
	 * Sets the caballo arquero.
	 * 
	 * @param caballoArquero
	 *            the new caballo arquero
	 */
	public void setCaballoArquero(final Integer caballoArquero) {
		this.caballoArquero = caballoArquero;
	}

	/**
	 * Sets the catapulta.
	 * 
	 * @param catapulta
	 *            the new catapulta
	 */
	public void setCatapulta(final Integer catapulta) {
		this.catapulta = catapulta;
	}

	/**
	 * Sets the espadas.
	 * 
	 * @param espadas
	 *            the new espadas
	 */
	public void setEspadas(final Integer espadas) {
		this.espadas = espadas;
	}

	/**
	 * Sets the espias.
	 * 
	 * @param espias
	 *            the new espias
	 */
	public void setEspias(final Integer espias) {
		this.espias = espias;
	}

	/**
	 * Sets the game id propio.
	 * 
	 * @param gameIDPropio
	 *            the new game id propio
	 */
	public void setGameIDPropio(final Integer gameIDPropio) {
		this.gameIDPropio = gameIDPropio;
	}

	/**
	 * Sets the hachas.
	 * 
	 * @param hachas
	 *            the new hachas
	 */
	public void setHachas(final Integer hachas) {
		this.hachas = hachas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fbgk.ataque.bbdd.interfaz.SetearSerializable#setId(java.lang.Integer)
	 */
	public void setId(final Integer id) {
		this.listaAtaquesID = id;
	}

	/**
	 * Sets the lancero.
	 * 
	 * @param lancero
	 *            the new lancero
	 */
	public void setLancero(final Integer lancero) {
		this.lancero = lancero;
	}

	/**
	 * Sets the ligeros.
	 * 
	 * @param ligeros
	 *            the new ligeros
	 */
	public void setLigeros(final Integer ligeros) {
		this.ligeros = ligeros;
	}

	/**
	 * Sets the lista ataques dto.
	 * 
	 * @param listaAtaquesDTO
	 *            the new lista ataques dto
	 */
	public void setListaAtaquesDTO(final List<AtaqueDTO> listaAtaquesDTO) {
		this.listaAtaquesDTO = listaAtaquesDTO;
	}

	/**
	 * Sets the lista ataques id.
	 * 
	 * @param listaAtaquesID
	 *            the new lista ataques id
	 */
	public void setListaAtaquesID(final Integer listaAtaquesID) {
		this.listaAtaquesID = listaAtaquesID;
	}

	/**
	 * Sets the noble.
	 * 
	 * @param noble
	 *            the new noble
	 */
	public void setNoble(final Integer noble) {
		this.noble = noble;
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
	 * Sets the pesados.
	 * 
	 * @param pesados
	 *            the new pesados
	 */
	public void setPesados(final Integer pesados) {
		this.pesados = pesados;
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
