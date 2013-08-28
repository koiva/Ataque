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
import org.apache.pivot.wtk.Prompt;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.Window;
import org.fbgk.ataque.guerrastribales.dto.LoginDTO;
import org.fbgk.ataque.guerrastribales.dto.ServidorDTO;

/**
 * The Class JugadoresServicio.
 */
public class JugadoresServicio extends JugadoresServicioBase implements ButtonPressListener, ListButtonSelectionListener {
	/** The servidor. */
	@BXML
	protected TextInput		servidor;

	/** The id juego. */
	@BXML
	protected TextInput		idJuego;

	/** The id login. */
	@BXML
	protected TextInput		idLogin;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.pivot.wtk.ButtonPressListener#buttonPressed(org.apache.pivot
	 * .wtk.Button)
	 */
	public void buttonPressed(final Button button) {
		if (this.servidor.getText().isEmpty() || this.juego.getText().isEmpty() || this.internalizacion.getText().isEmpty() || this.usuario.getText().isEmpty() || this.password.getText().isEmpty()) {
			Alert.alert("Todos los datos son obligatorios", this);
		} else {
			ServidorDTO servidorDTO = new ServidorDTO();
			servidorDTO.setServer(this.servidor.getText().trim());
			servidorDTO.setJuego(this.juego.getText().trim());
			servidorDTO.setInternalizacion(this.internalizacion.getText().trim());
			final ServidorDTO servidorDTO2 = this.ataqueDao.consultar(servidorDTO);
			if (servidorDTO2 != null) {
				servidorDTO = servidorDTO2;
			} else {
				servidorDTO = this.ataqueDao.insertar(servidorDTO);
			}
			final LoginDTO loginDTO = new LoginDTO();
			loginDTO.setUsuario(this.usuario.getText().trim());
			final LoginDTO loginDTO2 = this.ataqueDao.consultar(loginDTO);
			if (loginDTO2 == null) {
				loginDTO.setPassword(this.password.getText());
				loginDTO.setListaServidorDTO(new ArrayList<ServidorDTO>());
				loginDTO.getListaServidorDTO().add(servidorDTO);
				this.ataqueDao.insertar(loginDTO);
			} else {
				if (loginDTO.getListaServidorDTO().contains(servidorDTO)) {
					Prompt.prompt("Este usuario ya esta introducido", this);
				} else {
					loginDTO.setPassword(this.password.getText());
					loginDTO.getListaServidorDTO().add(servidorDTO);
					this.ataqueDao.actualizar(loginDTO);
				}
			}
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
		final BXMLSerializer bxmlSerializer = new BXMLSerializer();
		bxmlSerializer.getNamespace().put("application", this);
		try {
			this.getDisplay().add((Frame) bxmlSerializer.readObject(this.getClass().getResource("/apache-pivot/view/AplicacionNuevoServidor.bxml")));
		} catch (final IOException e) {
			e.printStackTrace();
		} catch (final SerializationException e) {
			e.printStackTrace();
		}
		this.setName("Insertar o modificar los datos");
		bxmlSerializer.bind(this);
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
		this.idJuego = idJuego;
	}

	/**
	 * Sets the id login.
	 * 
	 * @param idLogin
	 *            the new id login
	 */
	public void setIdLogin(final TextInput idLogin) {
		this.idLogin = idLogin;
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
