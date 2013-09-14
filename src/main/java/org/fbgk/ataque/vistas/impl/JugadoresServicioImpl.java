package org.fbgk.ataque.vistas.impl;

import java.util.Iterator;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.BeanAdapter;
import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.Sequence;
import org.apache.pivot.util.Vote;
import org.apache.pivot.wtk.Alert;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.Form;
import org.apache.pivot.wtk.Frame;
import org.apache.pivot.wtk.ListView;
import org.apache.pivot.wtk.MessageType;
import org.apache.pivot.wtk.Prompt;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.Span;
import org.apache.pivot.wtk.TableView;
import org.apache.pivot.wtk.TableViewSelectionListener;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.Window;
import org.apache.pivot.wtk.WindowStateListener;
import org.fbgk.ataque.guerrastribales.dto.LoginDTO;
import org.fbgk.ataque.guerrastribales.dto.ServidorDTO;
import org.fbgk.ataque.vistas.base.JugadoresServicioBase;
import org.fbgk.ataque.vistas.constantes.ConstantesPantallas;
import org.fbgk.ataque.vistas.constantes.TipoAccion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class JugadoresServicio.
 */
public class JugadoresServicioImpl extends JugadoresServicioBase implements TableViewSelectionListener, ButtonPressListener, WindowStateListener {

	/** The logger. */
	static Logger		logger	= LoggerFactory.getLogger(JugadoresServicioImpl.class);

	/** The estado. */
	private TipoAccion	estado;

	/** The gestion tabla. */
	@BXML
	private TableView	gestionTabla;

	/** The id servidor. */
	@BXML
	private TextInput	loginID;

	/** The servidor. */
	@BXML
	private TextInput	usuario;

	/** The juego. */
	@BXML
	private TextInput	password;

	/** The internalizacion. */
	@BXML
	private ListView	servidoresView;

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
	private Frame		frameUser;

	/** The prompt eliminacion. */
	private Prompt		promptEliminacion;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.pivot.wtk.ButtonPressListener#buttonPressed(org.apache.pivot
	 * .wtk.Button)
	 */
	@SuppressWarnings("unchecked")
	public void buttonPressed(final Button button) {
		logger.debug("Boton presionado");
		if (button.equals(this.buttonAceptar)) {
			final LoginDTO loginDTO = new LoginDTO();
			loginDTO.setListaServidorDTO(new java.util.ArrayList<ServidorDTO>());
			final Sequence<ServidorDTO> sequence = (Sequence<ServidorDTO>) this.servidoresView.getSelectedItems();
			for (int x = 0; x < sequence.getLength(); x++) {
				loginDTO.getListaServidorDTO().add(sequence.get(x));
			}
			if (TipoAccion.INSERTAR.equals(this.estado)) {
				loginDTO.setUsuario(this.usuario.getText());
				loginDTO.setPassword(this.password.getText());
				logger.debug("Insertando nuevo jugador");
				this.ataqueDao.insertar(loginDTO);
			} else if (TipoAccion.MODIFICAR.equals(this.estado)) {
				this.formulario.store(new BeanAdapter(loginDTO));
				logger.debug("Actualizando servidor");
				this.ataqueDao.actualizar(loginDTO);
			}
			this.estado = TipoAccion.CONSULTAR;
			this.formulario.clear();
			this.cargarDatosLogin();
		} else if (button.equals(this.buttonEliminar)) {
			if (TipoAccion.MODIFICAR.equals(this.estado)) {
				logger.debug("Esperando a la confirmación de la eliminación");
				this.promptEliminacion.open(this.frameUser.getDisplay(), this.frameUser.getWindow());
			}
		} else if (button.equals(this.buttonNuevo)) {
			logger.debug("Limpiando el formulario para poder introducir los datos");
			this.formulario.clear();
			this.estado = TipoAccion.INSERTAR;
			this.buttonAceptar.setEnabled(Boolean.TRUE);
		} else if (button.equals(this.buttonSalir)) {
			logger.debug("Saliendo de la pantalla");
			this.frameUser.close();
		}
	}

	/**
	 * Cargar datos servidor.
	 */
	private void cargarDatosLogin() {
		this.gestionTabla.setTableData(this.utilVistaServicio.recuperarLista(this.ataqueDao.recuperarTodo(new LoginDTO())));
		this.buttonEliminar.setEnabled(Boolean.FALSE);
		this.buttonAceptar.setEnabled(Boolean.FALSE);
	}

	public PushButton getButtonAceptar() {
		return this.buttonAceptar;
	}

	public PushButton getButtonEliminar() {
		return this.buttonEliminar;
	}

	public PushButton getButtonNuevo() {
		return this.buttonNuevo;
	}

	public PushButton getButtonSalir() {
		return this.buttonSalir;
	}

	public TipoAccion getEstado() {
		return this.estado;
	}

	public Form getFormulario() {
		return this.formulario;
	}

	public Frame getFrameUser() {
		return this.frameUser;
	}

	public TableView getGestionTabla() {
		return this.gestionTabla;
	}

	public TextInput getLoginID() {
		return this.loginID;
	}

	public TextInput getPassword() {
		return this.password;
	}

	public Prompt getPromptEliminacion() {
		return this.promptEliminacion;
	}

	public ListView getServidoresView() {
		return this.servidoresView;
	}

	public TextInput getUsuario() {
		return this.usuario;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fbgk.ataque.vistas.ComunVistaServicio#open(org.apache.pivot.wtk.Display
	 * , org.apache.pivot.wtk.Window)
	 */
	public void open(final Display display, final Window window) {
		if (!this.ataqueDao.recuperarTodo(new ServidorDTO()).isEmpty()) {
			this.frameUser = this.utilVistaServicio.openFrame(this.frameUser, display, window, this, ConstantesPantallas.PANTALLA_GESTION_JUGADORES);
			if (this.frameUser != null) {
				this.gestionTabla.getTableViewSelectionListeners().add(this);
				this.cargarDatosLogin();
				this.buttonAceptar.getButtonPressListeners().add(this);
				this.buttonEliminar.getButtonPressListeners().add(this);
				this.buttonSalir.getButtonPressListeners().add(this);
				this.buttonNuevo.getButtonPressListeners().add(this);
				final Sequence<String> sequence = new ArrayList<String>();
				sequence.add("Aceptar");
				sequence.add("Cancelar");
				this.promptEliminacion = new Prompt(MessageType.WARNING, "¿Estás seguro de querer eliminar el servidor?", sequence);
				this.promptEliminacion.getWindowStateListeners().add(this);
				this.servidoresView.setListData(this.utilVistaServicio.recuperarLista(this.ataqueDao.recuperarTodo(new ServidorDTO())));
			}
		} else {
			Alert.alert("No se puede abrir la ventana si no hay servidores dados de alta", window);
		}
	}

	public Vote previewWindowClose(final Window window) {
		final String opcion = (String) this.promptEliminacion.getSelectedOption();
		if (opcion.equals("Aceptar")) {
			final LoginDTO loginDTO = new LoginDTO();
			this.formulario.store(loginDTO);
			this.ataqueDao.eliminar(loginDTO);
			this.formulario.clear();
			this.cargarDatosLogin();
		}
		return Vote.APPROVE;
	}

	public Vote previewWindowOpen(final Window window) {
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
	@SuppressWarnings("unchecked")
	public void selectedRowChanged(final TableView tableView, final Object previousSelectedRow) {
		final int select = tableView.getSelectedIndex();
		if (select != -1) {
			final Object object = tableView.getTableData().get(select);
			if (object instanceof LoginDTO) {
				this.formulario.load(object);
				this.buttonEliminar.setEnabled(Boolean.TRUE);
				this.buttonAceptar.setEnabled(Boolean.TRUE);
				if (((LoginDTO) object).getListaServidorDTO() != null) {
					for (final ServidorDTO servidorDTO : ((LoginDTO) object).getListaServidorDTO()) {
						final Boolean boolean1 = Boolean.FALSE;
						int x = 0;
						final Iterator<ServidorDTO> iterator = (Iterator<ServidorDTO>) this.servidoresView.getListData().iterator();
						while (!boolean1 && iterator.hasNext()) {
							if (iterator.next().getServidorID().equals(servidorDTO.getServidorID())) {
								this.servidoresView.setSelectedIndex(x);
							}
							x++;
						}
					}
				}
				this.estado = TipoAccion.MODIFICAR;
			}
		}
	}

	public void setButtonAceptar(final PushButton buttonAceptar) {
		this.buttonAceptar = buttonAceptar;
	}

	public void setButtonEliminar(final PushButton buttonEliminar) {
		this.buttonEliminar = buttonEliminar;
	}

	public void setButtonNuevo(final PushButton buttonNuevo) {
		this.buttonNuevo = buttonNuevo;
	}

	public void setButtonSalir(final PushButton buttonSalir) {
		this.buttonSalir = buttonSalir;
	}

	public void setEstado(final TipoAccion estado) {
		this.estado = estado;
	}

	public void setFormulario(final Form formulario) {
		this.formulario = formulario;
	}

	public void setFrameUser(final Frame frameUser) {
		this.frameUser = frameUser;
	}

	public void setGestionTabla(final TableView gestionTabla) {
		this.gestionTabla = gestionTabla;
	}

	public void setLoginID(final TextInput loginID) {
		this.loginID = loginID;
	}

	public void setPassword(final TextInput password) {
		this.password = password;
	}

	public void setPromptEliminacion(final Prompt promptEliminacion) {
		this.promptEliminacion = promptEliminacion;
	}

	public void setServidoresView(final ListView servidoresView) {
		this.servidoresView = servidoresView;
	}

	public void setUsuario(final TextInput usuario) {
		this.usuario = usuario;
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
