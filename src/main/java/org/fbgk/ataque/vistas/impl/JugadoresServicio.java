package org.fbgk.ataque.vistas.impl;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.beans.BeanAdapter;
import org.apache.pivot.serialization.SerializationException;
import org.apache.pivot.wtk.Alert;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.Form;
import org.apache.pivot.wtk.Frame;
import org.apache.pivot.wtk.ListButton;
import org.apache.pivot.wtk.ListButtonSelectionListener;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.Window;
import org.fbgk.ataque.guerrastribales.dto.LoginDTO;
import org.fbgk.ataque.guerrastribales.dto.ServidorDTO;
import org.fbgk.ataque.vistas.base.JugadoresServicioBase;

/**
 * The Class JugadoresServicio.
 */
public class JugadoresServicio extends JugadoresServicioBase implements ButtonPressListener, ListButtonSelectionListener {
	/** The servidor. */
	@BXML
	protected TextInput		servidor;

	/** The id juego. */
	@BXML
	protected TextInput		idServidor;

	/** The juego. */
	@BXML
	protected TextInput		juego;

	/** The internalizacion. */
	@BXML
	protected TextInput		internalizacion;

	/** The usuario. */
	@BXML
	protected TextInput		usuario;

	/** The password. */
	@BXML
	protected TextInput		password;

	/** The submit button. */
	@BXML
	protected PushButton	submitButton;

	/** The list login. */
	@BXML
	protected ListButton	listLogin;

	/** The list servidor. */
	@BXML
	protected ListButton	listServidor;

	/** The form servidor. */
	@BXML
	protected Form			formulario;

	/**
	 * Actualizar login. Actualizamos la informacion del login.
	 * 
	 * @param servidorDTO
	 *            the servidor dto
	 * @return the login dto
	 */
	private LoginDTO actualizarLogin(final ServidorDTO servidorDTO) {
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setUsuario(this.usuario.getText());
		loginDTO.setPassword(this.password.getText());
		loginDTO.setListaServidorDTO(new ArrayList<ServidorDTO>());
		loginDTO.getListaServidorDTO().add(servidorDTO);
		final LoginDTO loginDTO2 = this.ataqueDao.consultar("FROM LoginDTO WHERE usuario=?", new Object[] { loginDTO.getUsuario() });
		if (loginDTO2 == null) {
			this.ataqueDao.insertar(loginDTO);
		} else {
			loginDTO.setListaServidorDTO(loginDTO2.getListaServidorDTO());
			if (!loginDTO2.getListaServidorDTO().contains(servidorDTO)) {
				loginDTO.getListaServidorDTO().add(servidorDTO);
			}
			this.ataqueDao.actualizar(loginDTO);
		}
		loginDTO = loginDTO2;
		return loginDTO;
	}

	/**
	 * Actualizar servidor. Actualizamos la informacion del servidor
	 * 
	 * @return the servidor dto
	 */
	private ServidorDTO actualizarServidor() {
		ServidorDTO servidorDTO = new ServidorDTO();
		servidorDTO.setInternalizacion(this.internalizacion.getText());
		servidorDTO.setJuego(this.juego.getText());
		servidorDTO.setServer(this.servidor.getText());
		if (this.idServidor.getText().isEmpty()) {
			final ServidorDTO servidorDTO2 = this.ataqueDao.consultar("FROM ServidorDTO WHERE server=? AND juego=? AND internalizacion=?", this.servidor.getText().trim(), this.juego.getText().trim(), this.internalizacion.getText().trim());
			if (servidorDTO2 != null) {
				servidorDTO = servidorDTO2;
			} else {
				servidorDTO = this.ataqueDao.insertar(servidorDTO);
			}
		} else {
			servidorDTO.setId(Integer.valueOf(this.idServidor.getText()));
			this.ataqueDao.actualizar(servidorDTO);
		}
		return servidorDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.pivot.wtk.ButtonPressListener#buttonPressed(org.apache.pivot
	 * .wtk.Button)
	 */
	public void buttonPressed(final Button button) {
		/** Comprobamos que los datos sean correctos */
		if (this.servidor.getText().isEmpty() || this.juego.getText().isEmpty() || this.internalizacion.getText().isEmpty() || this.usuario.getText().isEmpty() || this.password.getText().isEmpty()) {
			Alert.alert("Todos los datos son obligatorios", this);
		} else {
			final ServidorDTO servidorDTO = this.actualizarServidor();
			this.actualizarLogin(servidorDTO);
			this.getOwner().close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.pivot.wtk.Window#open(org.apache.pivot.wtk.Display,
	 * org.apache.pivot.wtk.Window)
	 */
	@Override
	public void open(final Display display, final Window ownerArgument) {
		super.open(display, ownerArgument);
		// Creamos un BXMLSerializer para pasar el objeto de la vista.
		final BXMLSerializer bxmlSerializer = new BXMLSerializer();
		bxmlSerializer.getNamespace().put("application", this);
		try {
			// Insertamos la pantalla.
			this.getDisplay().add((Frame) bxmlSerializer.readObject(this.getClass().getResource("/apache-pivot/view/AplicacionNuevoServidor.bxml")));
		} catch (final IOException e) {
			e.printStackTrace();
		} catch (final SerializationException e) {
			e.printStackTrace();
		}
		this.setName("Insertar o modificar los datos");
		// Seteamos los datos de la anotacion BXML
		bxmlSerializer.bind(this);

		/** Transpasamos los datos de listas de servidores y logins */
		final org.apache.pivot.collections.List<LoginDTO> listLogings = new org.apache.pivot.collections.ArrayList<LoginDTO>();
		final org.apache.pivot.collections.List<ServidorDTO> listServidors = new org.apache.pivot.collections.ArrayList<ServidorDTO>();
		for (final LoginDTO loginDTO : this.ataqueDao.recuperarTodo(new LoginDTO())) {
			listLogings.add(loginDTO);
		}
		for (final ServidorDTO servidorDTO : this.ataqueDao.recuperarTodo(new ServidorDTO())) {
			listServidors.add(servidorDTO);
		}
		if (!listLogings.isEmpty()) {
			this.listLogin.setListData(listLogings);
		}
		if (!listServidors.isEmpty()) {
			this.listServidor.setListData(listServidors);
		}
		/** Seteamos los lanzadores */
		this.listLogin.getListButtonSelectionListeners().add(this);
		this.listServidor.getListButtonSelectionListeners().add(this);
		this.submitButton.getButtonPressListeners().add(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.pivot.wtk.ListButtonSelectionListener#selectedIndexChanged
	 * (org.apache.pivot.wtk.ListButton, int)
	 */
	public void selectedIndexChanged(final ListButton listButton, final int previousSelectedIndex) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.pivot.wtk.ListButtonSelectionListener#selectedItemChanged(
	 * org.apache.pivot.wtk.ListButton, java.lang.Object)
	 */
	public void selectedItemChanged(final ListButton listButton, final Object previousSelectedItem) {
		if (listButton.equals(this.listLogin)) {
			this.formulario.load(new BeanAdapter(listButton.getSelectedItem()));
		} else if (listButton.equals(this.listServidor)) {
			this.formulario.load(new BeanAdapter(listButton.getSelectedItem()));
		}
	}

	/**
	 * Sets the formulario.
	 * 
	 * @param formulario
	 *            the new formulario
	 */
	public void setFormulario(final Form formulario) {
		this.formulario = formulario;
	}

	/**
	 * Sets the id juego.
	 * 
	 * @param idJuego
	 *            the new id juego
	 */
	public void setIdJuego(final TextInput idJuego) {
		this.idServidor = idJuego;
	}

	/**
	 * Sets the internalizacion.
	 * 
	 * @param internalizacion
	 *            the new internalizacion
	 */
	public void setInternalizacion(final TextInput internalizacion) {
		this.internalizacion = internalizacion;
	}

	/**
	 * Sets the juego.
	 * 
	 * @param juego
	 *            the new juego
	 */
	public void setJuego(final TextInput juego) {
		this.juego = juego;
	}

	/**
	 * Sets the list login.
	 * 
	 * @param listLogin
	 *            the new list login
	 */
	public void setListLogin(final ListButton listLogin) {
		this.listLogin = listLogin;
	}

	/**
	 * Sets the list servidor.
	 * 
	 * @param listServidor
	 *            the new list servidor
	 */
	public void setListServidor(final ListButton listServidor) {
		this.listServidor = listServidor;
	}

	/**
	 * Sets the password.
	 * 
	 * @param password
	 *            the new password
	 */
	public void setPassword(final TextInput password) {
		this.password = password;
	}

	/**
	 * Sets the servidor.
	 * 
	 * @param servidor
	 *            the new servidor
	 */
	public void setServidor(final TextInput servidor) {
		this.servidor = servidor;
	}

	/**
	 * Sets the submit button.
	 * 
	 * @param submitButton
	 *            the new submit button
	 */
	public void setSubmitButton(final PushButton submitButton) {
		this.submitButton = submitButton;
	}

	/**
	 * Sets the usuario.
	 * 
	 * @param usuario
	 *            the new usuario
	 */
	public void setUsuario(final TextInput usuario) {
		this.usuario = usuario;
	}

}
