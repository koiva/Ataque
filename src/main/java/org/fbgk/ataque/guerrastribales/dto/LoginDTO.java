package org.fbgk.ataque.guerrastribales.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.fbgk.ataque.bbdd.interfaz.SetearSerializable;

/**
 * The Class LoginDTO.
 */
@Entity
@Table(name = "INF_Login")
public class LoginDTO implements Serializable, SetearSerializable {

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 9055957322138973209L;

	/** The id. */
	@Id
	@Column
	@GeneratedValue
	private Integer				loginID;

	/** The usuario. */
	@Column
	private String				usuario;

	/** The password. */
	@Column
	private String				password;

	/** The lista servidor dto. */
	@ManyToMany(fetch = FetchType.EAGER)
	@Column(name = "servidorID")
	private List<ServidorDTO>	listaServidorDTO;

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
		final LoginDTO other = (LoginDTO) obj;
		if (this.listaServidorDTO == null) {
			if (other.listaServidorDTO != null) {
				return false;
			}
		} else if (!this.listaServidorDTO.equals(other.listaServidorDTO)) {
			return false;
		}
		if (this.loginID == null) {
			if (other.loginID != null) {
				return false;
			}
		} else if (!this.loginID.equals(other.loginID)) {
			return false;
		}
		if (this.password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!this.password.equals(other.password)) {
			return false;
		}
		if (this.usuario == null) {
			if (other.usuario != null) {
				return false;
			}
		} else if (!this.usuario.equals(other.usuario)) {
			return false;
		}
		return true;
	}

	/**
	 * Gets the lista servidor dto.
	 * 
	 * @return the lista servidor dto
	 */
	public List<ServidorDTO> getListaServidorDTO() {
		return this.listaServidorDTO;
	}

	/**
	 * Gets the login id.
	 * 
	 * @return the login id
	 */
	public Integer getLoginID() {
		return this.loginID;
	}

	/**
	 * Gets the password.
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Gets the usuario.
	 * 
	 * @return the usuario
	 */
	public String getUsuario() {
		return this.usuario;
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
		result = prime * result + ((this.listaServidorDTO == null) ? 0 : this.listaServidorDTO.hashCode());
		result = prime * result + ((this.loginID == null) ? 0 : this.loginID.hashCode());
		result = prime * result + ((this.password == null) ? 0 : this.password.hashCode());
		result = prime * result + ((this.usuario == null) ? 0 : this.usuario.hashCode());
		return result;
	}

	public void setId(final Integer id) {
		this.loginID = id;
	}

	/**
	 * Sets the lista servidor dto.
	 * 
	 * @param listaServidorDTO
	 *            the new lista servidor dto
	 */
	public void setListaServidorDTO(final List<ServidorDTO> listaServidorDTO) {
		this.listaServidorDTO = listaServidorDTO;
	}

	/**
	 * Sets the login id.
	 * 
	 * @param loginID
	 *            the new login id
	 */
	public void setLoginID(final Integer loginID) {
		this.loginID = loginID;
	}

	/**
	 * Sets the password.
	 * 
	 * @param password
	 *            the new password
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

	/**
	 * Sets the usuario.
	 * 
	 * @param usuario
	 *            the new usuario
	 */
	public void setUsuario(final String usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return String.format("Usuario: %s", this.usuario);
	}

}
