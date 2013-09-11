package org.fbgk.ataque.vistas.impl;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.BeanAdapter;
import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.Sequence;
import org.apache.pivot.util.Vote;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.Form;
import org.apache.pivot.wtk.Frame;
import org.apache.pivot.wtk.MessageType;
import org.apache.pivot.wtk.Prompt;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.Span;
import org.apache.pivot.wtk.TableView;
import org.apache.pivot.wtk.TableViewSelectionListener;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.Window;
import org.apache.pivot.wtk.WindowStateListener;
import org.fbgk.ataque.guerrastribales.dto.ServidorDTO;
import org.fbgk.ataque.vistas.base.ServidorServicioBase;
import org.fbgk.ataque.vistas.constantes.ConstantesPantallas;
import org.fbgk.ataque.vistas.constantes.TipoAccion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class ServidorServicioImpl.
 */
public class ServidorServicioImpl extends ServidorServicioBase implements TableViewSelectionListener, ButtonPressListener, WindowStateListener {

	/** The logger. */
	static Logger		logger	= LoggerFactory.getLogger(ServidorServicioImpl.class);

	/** The estado. */
	private TipoAccion	estado;

	/** The gestion tabla. */
	@BXML
	private TableView	gestionTabla;

	/** The id servidor. */
	@BXML
	private TextInput	idServidor;

	/** The servidor. */
	@BXML
	private TextInput	servidor;

	/** The juego. */
	@BXML
	private TextInput	juego;

	/** The internalizacion. */
	@BXML
	private TextInput	internalizacion;

	/** The formulario. */
	@BXML
	private Form		formulario;

	/** The button aceptar. */
	@BXML
	private PushButton	buttonAceptar;

	/** The button eliminar. */
	@BXML
	private PushButton	buttonEliminar;

	/** The button salir. */
	@BXML
	private PushButton	buttonSalir;

	/** The button nuevo. */
	@BXML
	private PushButton	buttonNuevo;

	/** The frame server. */
	@BXML
	private Frame		frameServer;

	/** The prompt eliminacion. */
	private Prompt		promptEliminacion;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.pivot.wtk.ButtonPressListener#buttonPressed(org.apache.pivot
	 * .wtk.Button)
	 */
	public void buttonPressed(final Button button) {
		logger.debug("Boton presionado");
		if (button.equals(this.buttonAceptar)) {
			final ServidorDTO servidorDTO = new ServidorDTO();
			if (TipoAccion.INSERTAR.equals(this.estado)) {
				servidorDTO.setInternalizacion(this.internalizacion.getText());
				servidorDTO.setJuego(this.juego.getText());
				servidorDTO.setServer(this.servidor.getText());
				logger.debug("Insertando nuevo servidor");
				this.ataqueDao.insertar(servidorDTO);
			} else if (TipoAccion.MODIFICAR.equals(this.estado)) {
				this.formulario.store(new BeanAdapter(servidorDTO));
				logger.debug("Actualizando servidor");
				this.ataqueDao.actualizar(servidorDTO);
			}
			this.estado = TipoAccion.CONSULTAR;
			this.formulario.clear();
			this.cargarDatosServidor();
		} else if (button.equals(this.buttonEliminar)) {
			if (TipoAccion.MODIFICAR.equals(this.estado)) {
				logger.debug("Esperando a la confirmación de la eliminación");
				this.promptEliminacion.open(this.frameServer.getDisplay(), this.frameServer.getWindow());
			}
		} else if (button.equals(this.buttonNuevo)) {
			logger.debug("Limpiando el formulario para poder introducir los datos");
			this.formulario.clear();
			this.estado = TipoAccion.INSERTAR;
			this.buttonAceptar.setEnabled(Boolean.TRUE);
		} else if (button.equals(this.buttonSalir)) {
			logger.debug("Saliendo de la pantalla");
			this.frameServer.close();
		}
	}

	/**
	 * Cargar datos servidor.
	 */
	private void cargarDatosServidor() {
		this.gestionTabla.setTableData(this.utilVistaServicio.recuperarLista(this.ataqueDao.recuperarTodo(new ServidorDTO())));
		this.buttonEliminar.setEnabled(Boolean.FALSE);
		this.buttonAceptar.setEnabled(Boolean.FALSE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fbgk.ataque.vistas.ComunVistaServicio#open(org.apache.pivot.wtk.Display
	 * , org.apache.pivot.wtk.Window)
	 */
	public void open(final Display display, final Window window) {
		this.frameServer = this.utilVistaServicio.openFrame(this.frameServer, display, window, this, ConstantesPantallas.PANTALLA_GESTION_SERVIDORES);
		if (this.frameServer != null) {
			this.gestionTabla.getTableViewSelectionListeners().add(this);
			this.cargarDatosServidor();
			this.buttonAceptar.getButtonPressListeners().add(this);
			this.buttonEliminar.getButtonPressListeners().add(this);
			this.buttonSalir.getButtonPressListeners().add(this);
			this.buttonNuevo.getButtonPressListeners().add(this);
			final Sequence<String> sequence = new ArrayList<String>();
			sequence.add("Aceptar");
			sequence.add("Cancelar");
			this.promptEliminacion = new Prompt(MessageType.WARNING, "¿Estás seguro de querer eliminar el servidor?", sequence);
			this.promptEliminacion.getWindowStateListeners().add(this);
		}
	}

	public Vote previewWindowClose(final Window window) {
		final String opcion = (String) this.promptEliminacion.getSelectedOption();
		if (opcion.equals("Aceptar")) {
			final ServidorDTO servidorDTO = new ServidorDTO();
			this.formulario.store(servidorDTO);
			this.ataqueDao.eliminar(servidorDTO);
			this.formulario.clear();
			this.cargarDatosServidor();
		}
		return Vote.APPROVE;
	}

	public Vote previewWindowOpen(final Window window) {
		// TODO Auto-generated method stub
		return Vote.APPROVE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.pivot.wtk.TableViewSelectionListener#selectedRangeAdded(org
	 * .apache.pivot.wtk.TableView, int, int)
	 */
	public void selectedRangeAdded(final TableView tableView, final int rangeStart, final int rangeEnd) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.pivot.wtk.TableViewSelectionListener#selectedRangeRemoved(
	 * org.apache.pivot.wtk.TableView, int, int)
	 */
	public void selectedRangeRemoved(final TableView tableView, final int rangeStart, final int rangeEnd) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.pivot.wtk.TableViewSelectionListener#selectedRangesChanged
	 * (org.apache.pivot.wtk.TableView, org.apache.pivot.collections.Sequence)
	 */
	public void selectedRangesChanged(final TableView tableView, final Sequence<Span> previousSelectedRanges) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.pivot.wtk.TableViewSelectionListener#selectedRowChanged(org
	 * .apache.pivot.wtk.TableView, java.lang.Object)
	 */
	public void selectedRowChanged(final TableView tableView, final Object previousSelectedRow) {
		final int select = tableView.getSelectedIndex();
		if (select != -1) {
			final Object object = tableView.getTableData().get(select);
			if (object instanceof ServidorDTO) {
				this.formulario.load(object);
				this.buttonEliminar.setEnabled(Boolean.TRUE);
				this.buttonAceptar.setEnabled(Boolean.TRUE);
				this.estado = TipoAccion.MODIFICAR;
			}
		}
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
	 * Sets the button eliminar.
	 * 
	 * @param buttonEliminar
	 *            the new button eliminar
	 */
	public void setButtonEliminar(final PushButton buttonEliminar) {
		this.buttonEliminar = buttonEliminar;
	}

	/**
	 * Sets the button nuevo.
	 * 
	 * @param buttonNuevo
	 *            the new button nuevo
	 */
	public void setButtonNuevo(final PushButton buttonNuevo) {
		this.buttonNuevo = buttonNuevo;
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
	 * Sets the frame server.
	 * 
	 * @param frameServer
	 *            the new frame server
	 */
	public void setFrameServer(final Frame frameServer) {
		this.frameServer = frameServer;
	}

	/**
	 * Sets the gestion tabla.
	 * 
	 * @param gestionTabla
	 *            the new gestion tabla
	 */
	public void setGestionTabla(final TableView gestionTabla) {
		this.gestionTabla = gestionTabla;
	}

	/**
	 * Sets the id servidor.
	 * 
	 * @param idServidor
	 *            the new id servidor
	 */
	public void setIdServidor(final TextInput idServidor) {
		this.idServidor = idServidor;
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
	 * Sets the servidor.
	 * 
	 * @param servidor
	 *            the new servidor
	 */
	public void setServidor(final TextInput servidor) {
		this.servidor = servidor;
	}

	public void windowClosed(final Window window, final Display display, final Window owner) {
		// TODO Auto-generated method stub

	}

	public void windowCloseVetoed(final Window window, final Vote reason) {
		// TODO Auto-generated method stub

	}

	public void windowOpened(final Window window) {
		// TODO Auto-generated method stub

	}

	public void windowOpenVetoed(final Window window, final Vote reason) {
		// TODO Auto-generated method stub

	}

}
