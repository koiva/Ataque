package org.fbgk.ataque.vistas.impl;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Checkbox;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.Form;
import org.apache.pivot.wtk.Frame;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.Window;
import org.fbgk.ataque.vistas.base.OpcionesServicioBase;
import org.fbgk.ataque.vistas.constantes.Configuracion;
import org.fbgk.ataque.vistas.constantes.ConstantesPantallas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class OpcionesServicioImpl.
 */
public class OpcionesServicioImpl extends OpcionesServicioBase implements ButtonPressListener {

	/** The logger. */
	static Logger		logger	= LoggerFactory.getLogger(OpcionesServicioImpl.class);

	/** The comprobacion barbaro. */
	@BXML
	private TextInput	atacarEspiado;

	/** The comprobacion barbaro. */
	@BXML
	private Checkbox	atacarNuevoFull;

	/** The button aceptar. */
	@BXML
	private PushButton	buttonAceptar;

	/** The button salir. */
	@BXML
	private PushButton	buttonSalir;

	/** The comprobacion barbaro. */
	@BXML
	private Checkbox	comprobacionBarbaro;

	/** The comprobacion barbaro. */
	@BXML
	private Checkbox	comprobacionInformes;

	/** The comprobacion barbaro. */
	@BXML
	private Checkbox	eliminarInformeLista;

	/** The formulario. */
	@BXML
	private Form		formulario;

	/** The frame opciones. */
	@BXML
	private Frame		frameOpciones;

	/** The tiempo ataque. */
	@BXML
	private TextInput	tiempoAtaque;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.pivot.wtk.ButtonPressListener#buttonPressed(org.apache.pivot
	 * .wtk.Button)
	 */
	@Override
	public void buttonPressed(final Button button) {
		if (button.equals(this.buttonAceptar)) {
			this.configuration.setProperty(Configuracion.BARBAROS.getKey(), this.comprobacionBarbaro.isSelected());
			this.configuration.setProperty(Configuracion.TIEMPOATAQUE.getKey(), this.tiempoAtaque.getText());
			this.configuration.setProperty(Configuracion.ATACARCOMPLETO.getKey(), this.atacarNuevoFull.isSelected());
			this.configuration.setProperty(Configuracion.ELIMINARINFORMELISTA.getKey(), this.eliminarInformeLista.isSelected());
			this.configuration.setProperty(Configuracion.LEERINFORMES.getKey(), this.comprobacionInformes.isSelected());
			this.configuration.setProperty(Configuracion.MINIMOATAQUECOMPLETO.getKey(), this.atacarEspiado.getText());
		}
		this.frameOpciones.close();
	}

	/**
	 * Gets the atacar espiado.
	 * 
	 * @return the atacar espiado
	 */
	public TextInput getAtacarEspiado() {
		return this.atacarEspiado;
	}

	/**
	 * Gets the atacar nuevo full.
	 * 
	 * @return the atacar nuevo full
	 */
	public Checkbox getAtacarNuevoFull() {
		return this.atacarNuevoFull;
	}

	/**
	 * Gets the button aceptar.
	 * 
	 * @return the button aceptar
	 */
	public PushButton getButtonAceptar() {
		return this.buttonAceptar;
	}

	/**
	 * Gets the button salir.
	 * 
	 * @return the button salir
	 */
	public PushButton getButtonSalir() {
		return this.buttonSalir;
	}

	/**
	 * Gets the comprobacion barbaro.
	 * 
	 * @return the comprobacion barbaro
	 */
	public Checkbox getComprobacionBarbaro() {
		return this.comprobacionBarbaro;
	}

	/**
	 * Gets the comprobacion informes.
	 * 
	 * @return the comprobacion informes
	 */
	public Checkbox getComprobacionInformes() {
		return this.comprobacionInformes;
	}

	/**
	 * Gets the eliminar informe lista.
	 * 
	 * @return the eliminar informe lista
	 */
	public Checkbox getEliminarInformeLista() {
		return this.eliminarInformeLista;
	}

	/**
	 * Gets the formulario.
	 * 
	 * @return the formulario
	 */
	public Form getFormulario() {
		return this.formulario;
	}

	/**
	 * Gets the tiempo ataque.
	 * 
	 * @return the tiempo ataque
	 */
	public TextInput getTiempoAtaque() {
		return this.tiempoAtaque;
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
		this.frameOpciones = this.utilVistaServicio.openFrame(this.frameOpciones, display, window, this, ConstantesPantallas.PANTALLA_GESTION_OPCIONES);
		if (this.frameOpciones != null) {
			this.buttonAceptar.getButtonPressListeners().add(this);
			this.buttonSalir.getButtonPressListeners().add(this);
			this.comprobacionBarbaro.setSelected(this.configuration.getBoolean(Configuracion.BARBAROS.getKey(), Boolean.TRUE));
			this.tiempoAtaque.setText(this.configuration.getString(Configuracion.TIEMPOATAQUE.getKey(), "5"));
			this.atacarEspiado.setText(this.configuration.getString(Configuracion.MINIMOATAQUECOMPLETO.getKey(), "1000"));
			this.atacarNuevoFull.setSelected(this.configuration.getBoolean(Configuracion.ATACARCOMPLETO.getKey(), Boolean.TRUE));
			this.comprobacionInformes.setSelected(this.configuration.getBoolean(Configuracion.LEERINFORMES.getKey(), Boolean.TRUE));
			this.eliminarInformeLista.setSelected(this.configuration.getBoolean(Configuracion.ELIMINARINFORMELISTA.getKey(), Boolean.TRUE));
		}
	}

	/**
	 * Sets the atacar espiado.
	 * 
	 * @param atacarEspiado
	 *            the new atacar espiado
	 */
	public void setAtacarEspiado(final TextInput atacarEspiado) {
		this.atacarEspiado = atacarEspiado;
	}

	/**
	 * Sets the atacar nuevo full.
	 * 
	 * @param atacarNuevoFull
	 *            the new atacar nuevo full
	 */
	public void setAtacarNuevoFull(final Checkbox atacarNuevoFull) {
		this.atacarNuevoFull = atacarNuevoFull;
	}

	/**
	 * Sets the button aceptar.
	 * 
	 * @param buttonAceptar
	 *            the new button aceptar
	 */
	public void setButtonAceptar(final PushButton buttonAceptar) {
		this.buttonAceptar = buttonAceptar;
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
	 * Sets the comprobacion barbaro.
	 * 
	 * @param comprobacionBarbaro
	 *            the new comprobacion barbaro
	 */
	public void setComprobacionBarbaro(final Checkbox comprobacionBarbaro) {
		this.comprobacionBarbaro = comprobacionBarbaro;
	}

	/**
	 * Sets the comprobacion informes.
	 * 
	 * @param comprobacionInformes
	 *            the new comprobacion informes
	 */
	public void setComprobacionInformes(final Checkbox comprobacionInformes) {
		this.comprobacionInformes = comprobacionInformes;
	}

	/**
	 * Sets the eliminar informe lista.
	 * 
	 * @param eliminarInformeLista
	 *            the new eliminar informe lista
	 */
	public void setEliminarInformeLista(final Checkbox eliminarInformeLista) {
		this.eliminarInformeLista = eliminarInformeLista;
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
	 * Sets the tiempo ataque.
	 * 
	 * @param tiempoAtaque
	 *            the new tiempo ataque
	 */
	public void setTiempoAtaque(final TextInput tiempoAtaque) {
		this.tiempoAtaque = tiempoAtaque;
	}

}
