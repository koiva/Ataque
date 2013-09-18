package org.fbgk.ataque.vistas.impl;

import org.apache.pivot.beans.BXML;
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
import org.fbgk.ataque.guerrastribales.dto.ListaAtaquesDTO;
import org.fbgk.ataque.guerrastribales.dto.PueblosDTO;
import org.fbgk.ataque.vistas.base.GestionDistanciaMaxServicioBase;
import org.fbgk.ataque.vistas.constantes.ConstantesPantallas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class GestionAtaqueServicioImpl.
 */
public class GestionDistanciaMaxServicioImpl extends GestionDistanciaMaxServicioBase implements ButtonPressListener, ListButtonSelectionListener {

	static Logger		logger	= LoggerFactory.getLogger(GestionDistanciaMaxServicioImpl.class);

	/** The button salir. */
	@BXML
	private PushButton	buttonAceptar;

	/** The button salir. */
	@BXML
	private PushButton	buttonSalir;

	@BXML
	private TextInput	distanciaTxt;

	/** The formulario. */
	@BXML
	private Form		formulario;

	/** The frame ataques. */
	@BXML
	private Frame		frameAtaques;

	/** The lista ataque id. */
	@BXML
	private ListButton	listaAtaqueID;

	/** The poblado. */
	@BXML
	private TextInput	poblado;

	/** The usuario. */
	@BXML
	private TextInput	usuario;

	@Override
	public void buttonPressed(final Button button) {
		if (button.equals(this.buttonSalir)) {
			logger.debug("Saliendo de la aplicacion de Gestion de ataques");
			this.frameAtaques.close();
		} else if (button.equals(this.buttonAceptar)) {
			logger.debug("Mirando los ataques a desarrollar");
			final ListaAtaquesDTO listaAtaquesDTO = (ListaAtaquesDTO) this.listaAtaqueID.getSelectedItem();
			final PueblosDTO pueblosDTO = this.ataqueDao.consultar("FROM PueblosDTO WHERE gameID=? and servidorID=?", listaAtaquesDTO.getGameIDPropio(), listaAtaquesDTO.getServidorDTO().getServidorID());
			final Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					GestionDistanciaMaxServicioImpl.this.barbarosServicio.mantenimientoBarbaros(pueblosDTO, Integer.valueOf(GestionDistanciaMaxServicioImpl.this.distanciaTxt.getText()), listaAtaquesDTO);
				}
			});
			thread.start();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fbgk.ataque.vistas.ComunVistaServicio#open(org.apache.pivot.wtk.Display
	 * , org.apache.pivot.wtk.Window)
	 */
	@Override
	public void open(final Display display, final Window window) {
		if (!this.ataqueDao.recuperarTodo(new ListaAtaquesDTO()).isEmpty()) {
			this.frameAtaques = this.utilVistaServicio.openFrame(this.frameAtaques, display, window, this, ConstantesPantallas.PANTALLA_GESTION_DISTANCIA);
			if (this.frameAtaques != null) {
				this.buttonSalir.getButtonPressListeners().add(this);
				this.listaAtaqueID.setListData(this.utilVistaServicio.recuperarLista(this.ataqueDao.recuperarTodo(new ListaAtaquesDTO())));
				this.buttonAceptar.getButtonPressListeners().add(this);
				this.listaAtaqueID.getListButtonSelectionListeners().add(this);
			}
		} else {
			Alert.alert("No se puede abrir la ventana si no hay lista de ataques dados de alta", window);
		}
	}

	@Override
	public void selectedIndexChanged(final ListButton listButton, final int previousSelectedIndex) {

	}

	@Override
	public void selectedItemChanged(final ListButton listButton, final Object previousSelectedItem) {
		final ListaAtaquesDTO listaAtaquesDTO = (ListaAtaquesDTO) listButton.getSelectedItem();
		if (listaAtaquesDTO != null) {
			final PueblosDTO pueblosDTO = this.ataqueDao.consultar("FROM PueblosDTO WHERE gameID=? AND servidorID=?", listaAtaquesDTO.getGameIDPropio(), listaAtaquesDTO.getServidorDTO().getServidorID());
			this.usuario.setText(pueblosDTO.getJugadoresDTO().getNombre());
			this.poblado.setText(String.format("%s (%s|%s)", pueblosDTO.getName(), pueblosDTO.getX(), pueblosDTO.getY()));
		}
	}

	/**
	 * Sets the button salir.
	 * 
	 * @param buttonSalir
	 *            the new button salir
	 */
	public void setButtonSalir(final PushButton buttonSalir) {
		this.buttonSalir = buttonSalir;
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
	 * Sets the frame ataques.
	 * 
	 * @param frameAtaques
	 *            the new frame ataques
	 */
	public void setFrameAtaques(final Frame frameAtaques) {
		this.frameAtaques = frameAtaques;
	}

	/**
	 * Sets the lista ataque id.
	 * 
	 * @param listaAtaqueID
	 *            the new lista ataque id
	 */
	public void setListaAtaqueID(final ListButton listaAtaqueID) {
		this.listaAtaqueID = listaAtaqueID;
	}

	/**
	 * Sets the poblado.
	 * 
	 * @param poblado
	 *            the new poblado
	 */
	public void setPoblado(final TextInput poblado) {
		this.poblado = poblado;
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
