package org.fbgk.ataque.vistas.impl;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.Sequence;
import org.apache.pivot.util.Vote;
import org.apache.pivot.wtk.Alert;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.Button.State;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Checkbox;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.Form;
import org.apache.pivot.wtk.Frame;
import org.apache.pivot.wtk.ListButton;
import org.apache.pivot.wtk.ListButtonSelectionListener;
import org.apache.pivot.wtk.MessageType;
import org.apache.pivot.wtk.Prompt;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.Span;
import org.apache.pivot.wtk.TableView;
import org.apache.pivot.wtk.TableViewSelectionListener;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.Window;
import org.apache.pivot.wtk.WindowStateListener;
import org.fbgk.ataque.guerrastribales.dto.JugadoresDTO;
import org.fbgk.ataque.guerrastribales.dto.ListaAtaquesDTO;
import org.fbgk.ataque.guerrastribales.dto.LoginDTO;
import org.fbgk.ataque.guerrastribales.dto.PueblosDTO;
import org.fbgk.ataque.guerrastribales.dto.ServidorDTO;
import org.fbgk.ataque.vistas.base.GestionAtaquesServicioBase;
import org.fbgk.ataque.vistas.constantes.ConstantesPantallas;
import org.fbgk.ataque.vistas.constantes.TipoAccion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class GestionAtaquesServicioImpl.
 */
public class GestionAtaquesServicioImpl extends GestionAtaquesServicioBase implements TableViewSelectionListener, ButtonPressListener, WindowStateListener, ListButtonSelectionListener {

	/** The logger. */
	Logger				logger	= LoggerFactory.getLogger(GestionAtaquesServicioImpl.class);

	/** The estado. */
	private TipoAccion	estado;

	/** The formulario. */
	@BXML
	private Frame		frameAtaque;

	/** The formulario. */
	@BXML
	private Form		formulario;

	/** The gestion tabla. */
	@BXML
	private TableView	gestionTabla;

	/** The usuario cbx. */
	@BXML
	private ListButton	usuarioCbx;

	/** The poblado cbx. */
	@BXML
	private ListButton	pobladoCbx;
	/** The lista ataques id. */
	@BXML
	private TextInput	listaAtaquesID;

	/** The nombre ataque. */
	@BXML
	private TextInput	nombreAtaque;

	/** The activar chk. */
	@BXML
	private Checkbox	activarChk;

	/** The lancero txt. */
	@BXML
	private TextInput	lanceroTxt;

	/** The espadas txt. */
	@BXML
	private TextInput	espadasTxt;

	/** The hachas txt. */
	@BXML
	private TextInput	hachasTxt;

	/** The arqueros txt. */
	@BXML
	private TextInput	arquerosTxt;

	/** The espias txt. */
	@BXML
	private TextInput	espiasTxt;

	/** The ligeros txt. */
	@BXML
	private TextInput	ligerosTxt;

	/** The pesados txt. */
	@BXML
	private TextInput	pesadosTxt;

	/** The caballo arquero txt. */
	@BXML
	private TextInput	caballoArqueroTxt;

	/** The ariete txt. */
	@BXML
	private TextInput	arieteTxt;

	/** The catapulta txt. */
	@BXML
	private TextInput	catapultaTxt;

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
		this.logger.debug("Boton presionado");
		if (button.equals(this.buttonAceptar)) {
			final ListaAtaquesDTO listaAtaquesDTO = this.cargarTxt();
			if (TipoAccion.INSERTAR.equals(this.estado)) {
				this.logger.debug("Insertando nuevo jugador");
				this.ataqueDao.insertar(listaAtaquesDTO);
			} else if (TipoAccion.MODIFICAR.equals(this.estado)) {
				listaAtaquesDTO.setId(Integer.valueOf(this.listaAtaquesID.getText()));
				this.logger.debug("Actualizando servidor");
				this.ataqueDao.actualizar(listaAtaquesDTO);
			}
			this.estado = TipoAccion.CONSULTAR;
			this.formulario.clear();
			this.cargarDatos();
		} else if (button.equals(this.buttonEliminar)) {
			if (TipoAccion.MODIFICAR.equals(this.estado)) {
				this.logger.debug("Esperando a la confirmación de la eliminación");
				this.promptEliminacion.open(this.frameAtaque.getDisplay(), this.frameAtaque.getWindow());
			}
		} else if (button.equals(this.buttonNuevo)) {
			this.logger.debug("Limpiando el formulario para poder introducir los datos");
			this.formulario.clear();
			this.usuarioCbx.setListData(this.utilVistaServicio.recuperarLista(this.ataqueDao.recuperarTodo(new LoginDTO())));
			this.estado = TipoAccion.INSERTAR;
			this.buttonAceptar.setEnabled(Boolean.TRUE);
		} else if (button.equals(this.buttonSalir)) {
			this.logger.debug("Saliendo de la pantalla");
			this.frameAtaque.close();
		}
	}

	/**
	 * Cargar datos.
	 */
	private void cargarDatos() {
		this.gestionTabla.setTableData(this.utilVistaServicio.recuperarLista(this.ataqueDao.recuperarTodo(new ListaAtaquesDTO())));
		this.buttonEliminar.setEnabled(Boolean.FALSE);
		this.buttonAceptar.setEnabled(Boolean.FALSE);
	}

	/**
	 * Cargar txt.
	 * 
	 * @return the lista ataques dto
	 */
	private ListaAtaquesDTO cargarTxt() {
		final ListaAtaquesDTO listaAtaquesDTO = new ListaAtaquesDTO();
		listaAtaquesDTO.setAriete(this.convertirString(this.arieteTxt));
		listaAtaquesDTO.setArquero(this.convertirString(this.arquerosTxt));
		listaAtaquesDTO.setCaballoArquero(this.convertirString(this.caballoArqueroTxt));
		listaAtaquesDTO.setCatapulta(this.convertirString(this.catapultaTxt));
		listaAtaquesDTO.setEspadas(this.convertirString(this.espadasTxt));
		listaAtaquesDTO.setEspias(this.convertirString(this.espiasTxt));
		listaAtaquesDTO.setHachas(this.convertirString(this.hachasTxt));
		listaAtaquesDTO.setLancero(this.convertirString(this.lanceroTxt));
		listaAtaquesDTO.setLigeros(this.convertirString(this.ligerosTxt));
		listaAtaquesDTO.setPesados(this.convertirString(this.pesadosTxt));
		final PueblosDTO pueblosDTO = (PueblosDTO) this.pobladoCbx.getSelectedItem();
		listaAtaquesDTO.setServidorDTO(pueblosDTO.getServidorDTO());
		listaAtaquesDTO.setGameIDPropio(pueblosDTO.getGameID());
		listaAtaquesDTO.setIsActivo(this.activarChk.getState() == State.SELECTED);
		listaAtaquesDTO.setNombre(this.nombreAtaque.getText());
		return listaAtaquesDTO;
	}

	/**
	 * Convertir string.
	 * 
	 * @param input
	 *            the input
	 * @return the integer
	 */
	private Integer convertirString(final TextInput input) {
		String dato = "0";
		if (!input.getText().isEmpty()) {
			dato = input.getText();
		}
		return Integer.valueOf(dato);
	}

	/**
	 * Load txt.
	 * 
	 * @param listaAtaquesDTO
	 *            the lista ataques dto
	 */
	private void loadTxt(final ListaAtaquesDTO listaAtaquesDTO) {
		this.arieteTxt.setText(listaAtaquesDTO.getAriete().toString());
		this.arquerosTxt.setText(listaAtaquesDTO.getArquero().toString());
		this.caballoArqueroTxt.setText(listaAtaquesDTO.getCaballoArquero().toString());
		this.catapultaTxt.setText(listaAtaquesDTO.getCatapulta().toString());
		this.espadasTxt.setText(listaAtaquesDTO.getEspadas().toString());
		this.espiasTxt.setText(listaAtaquesDTO.getEspias().toString());
		this.hachasTxt.setText(listaAtaquesDTO.getHachas().toString());
		this.lanceroTxt.setText(listaAtaquesDTO.getLancero().toString());
		this.ligerosTxt.setText(listaAtaquesDTO.getLigeros().toString());
		this.pesadosTxt.setText(listaAtaquesDTO.getPesados().toString());
		this.activarChk.setState(listaAtaquesDTO.getIsActivo() ? State.SELECTED : State.UNSELECTED);
		this.nombreAtaque.setText(listaAtaquesDTO.getNombre());
		this.listaAtaquesID.setText(listaAtaquesDTO.getListaAtaquesID().toString());
		this.usuarioCbx.setListData(this.utilVistaServicio.recuperarLista(this.ataqueDao.recuperarTodo(new LoginDTO())));
		final PueblosDTO pueblosDTO = this.ataqueDao.consultar("FROM PueblosDTO WHERE gameID=?", new Object[] { listaAtaquesDTO.getGameIDPropio() });
		final LoginDTO loginDTO = this.ataqueDao.consultar("FROM LoginDTO WHERE usuario=?", new Object[] { pueblosDTO.getJugadoresDTO().getNombre() });
		final JugadoresDTO jugadoresDTO = this.ataqueDao.consultar("FROM JugadoresDTO WHERE nombre=?", new Object[] { loginDTO.getUsuario() });
		this.pobladoCbx.setListData(this.utilVistaServicio.recuperarLista(this.ataqueDao.buscar("FROM PueblosDTO WHERE jugadoresID=?", jugadoresDTO.getJugadoresID())));
		for (final Object loginDTO2 : this.usuarioCbx.getListData()) {
			if (loginDTO.getLoginID().equals(((LoginDTO) loginDTO2).getLoginID())) {
				this.usuarioCbx.setSelectedItem(loginDTO2);
				;
			}
		}
		for (final Object poblado : this.pobladoCbx.getListData()) {
			if (pueblosDTO.getPueblosID().equals(((PueblosDTO) poblado).getPueblosID())) {
				this.pobladoCbx.setSelectedItem(poblado);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fbgk.ataque.vistas.ComunVistaServicio#open(org.apache.pivot.wtk.Display
	 * , org.apache.pivot.wtk.Window)
	 */
	public void open(final Display display, final Window window) {
		if (!this.ataqueDao.recuperarTodo(new ServidorDTO()).isEmpty() && !this.ataqueDao.recuperarTodo(new LoginDTO()).isEmpty()) {
			this.frameAtaque = this.utilVistaServicio.openFrame(this.frameAtaque, display, window, this, ConstantesPantallas.PANTALLA_GESTION_ATAQUES);
			if (this.frameAtaque != null) {
				this.gestionTabla.getTableViewSelectionListeners().add(this);
				this.buttonAceptar.getButtonPressListeners().add(this);
				this.buttonEliminar.getButtonPressListeners().add(this);
				this.buttonSalir.getButtonPressListeners().add(this);
				this.buttonNuevo.getButtonPressListeners().add(this);
				final Sequence<String> sequence = new ArrayList<String>();
				sequence.add("Aceptar");
				sequence.add("Cancelar");
				this.cargarDatos();
				this.promptEliminacion = new Prompt(MessageType.WARNING, "¿Estás seguro de querer eliminar la lista de ataque?", sequence);
				this.promptEliminacion.getWindowStateListeners().add(this);
				this.usuarioCbx.getListButtonSelectionListeners().add(this);
			}
		} else {
			Alert.alert("No se puede abrir la ventana si no hay servidores o usuarios dados de alta", window);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.pivot.wtk.WindowStateListener#previewWindowClose(org.apache
	 * .pivot.wtk.Window)
	 */
	public Vote previewWindowClose(final Window window) {
		final String opcion = (String) this.promptEliminacion.getSelectedOption();
		if (opcion.equals("Aceptar")) {
			final ListaAtaquesDTO listaAtaquesDTO = this.cargarTxt();
			listaAtaquesDTO.setId(Integer.valueOf(this.listaAtaquesID.getText()));
			this.ataqueDao.eliminar(listaAtaquesDTO);
			this.formulario.clear();
			this.cargarDatos();
		}
		return Vote.APPROVE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.pivot.wtk.WindowStateListener#previewWindowOpen(org.apache
	 * .pivot.wtk.Window)
	 */
	public Vote previewWindowOpen(final Window window) {
		return Vote.APPROVE;
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
		if (listButton.getSelectedItem() != null) {
			final JugadoresDTO jugadoresDTO = this.ataqueDao.consultar("FROM JugadoresDTO WHERE nombre=?", new Object[] { ((LoginDTO) listButton.getSelectedItem()).getUsuario() });
			this.pobladoCbx.setListData(this.utilVistaServicio.recuperarLista(this.ataqueDao.buscar("FROM PueblosDTO WHERE jugadoresID=?", jugadoresDTO.getJugadoresID())));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.pivot.wtk.TableViewSelectionListener#selectedRangeAdded(org
	 * .apache.pivot.wtk.TableView, int, int)
	 */
	public void selectedRangeAdded(final TableView tableView, final int rangeStart, final int rangeEnd) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.pivot.wtk.TableViewSelectionListener#selectedRangeRemoved(
	 * org.apache.pivot.wtk.TableView, int, int)
	 */
	public void selectedRangeRemoved(final TableView tableView, final int rangeStart, final int rangeEnd) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.pivot.wtk.TableViewSelectionListener#selectedRangesChanged
	 * (org.apache.pivot.wtk.TableView, org.apache.pivot.collections.Sequence)
	 */
	public void selectedRangesChanged(final TableView tableView, final Sequence<Span> previousSelectedRanges) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.pivot.wtk.TableViewSelectionListener#selectedRowChanged(org
	 * .apache.pivot.wtk.TableView, java.lang.Object)
	 */
	public void selectedRowChanged(final TableView tableView, final Object previousSelectedRow) {
		final int select = this.gestionTabla.getSelectedIndex();
		if (select != -1) {
			final Object object = this.gestionTabla.getTableData().get(select);
			if (object instanceof ListaAtaquesDTO) {
				this.loadTxt((ListaAtaquesDTO) object);
			}
		}
		this.buttonEliminar.setEnabled(Boolean.TRUE);
		this.buttonAceptar.setEnabled(Boolean.TRUE);
		this.estado = TipoAccion.MODIFICAR;
	}

	/**
	 * Sets the activar chk.
	 * 
	 * @param activarChk
	 *            the new activar chk
	 */
	public void setActivarChk(final Checkbox activarChk) {
		this.activarChk = activarChk;
	}

	/**
	 * Sets the ariete txt.
	 * 
	 * @param arieteTxt
	 *            the new ariete txt
	 */
	public void setArieteTxt(final TextInput arieteTxt) {
		this.arieteTxt = arieteTxt;
	}

	/**
	 * Sets the arqueros txt.
	 * 
	 * @param arquerosTxt
	 *            the new arqueros txt
	 */
	public void setArquerosTxt(final TextInput arquerosTxt) {
		this.arquerosTxt = arquerosTxt;
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
	 * Sets the caballo arquero txt.
	 * 
	 * @param caballoArqueroTxt
	 *            the new caballo arquero txt
	 */
	public void setCaballoArqueroTxt(final TextInput caballoArqueroTxt) {
		this.caballoArqueroTxt = caballoArqueroTxt;
	}

	/**
	 * Sets the catapulta txt.
	 * 
	 * @param catapultaTxt
	 *            the new catapulta txt
	 */
	public void setCatapultaTxt(final TextInput catapultaTxt) {
		this.catapultaTxt = catapultaTxt;
	}

	/**
	 * Sets the espadas txt.
	 * 
	 * @param espadasTxt
	 *            the new espadas txt
	 */
	public void setEspadasTxt(final TextInput espadasTxt) {
		this.espadasTxt = espadasTxt;
	}

	/**
	 * Sets the espias txt.
	 * 
	 * @param espiasTxt
	 *            the new espias txt
	 */
	public void setEspiasTxt(final TextInput espiasTxt) {
		this.espiasTxt = espiasTxt;
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
	 * Sets the frame ataque.
	 * 
	 * @param frameAtaque
	 *            the new frame ataque
	 */
	public void setFrameAtaque(final Frame frameAtaque) {
		this.frameAtaque = frameAtaque;
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
	 * Sets the hachas txt.
	 * 
	 * @param hachasTxt
	 *            the new hachas txt
	 */
	public void setHachasTxt(final TextInput hachasTxt) {
		this.hachasTxt = hachasTxt;
	}

	/**
	 * Sets the lancero txt.
	 * 
	 * @param lanceroTxt
	 *            the new lancero txt
	 */
	public void setLanceroTxt(final TextInput lanceroTxt) {
		this.lanceroTxt = lanceroTxt;
	}

	/**
	 * Sets the ligeros txt.
	 * 
	 * @param ligerosTxt
	 *            the new ligeros txt
	 */
	public void setLigerosTxt(final TextInput ligerosTxt) {
		this.ligerosTxt = ligerosTxt;
	}

	/**
	 * Sets the lista ataques id.
	 * 
	 * @param listaAtaquesID
	 *            the new lista ataques id
	 */
	public void setListaAtaquesID(final TextInput listaAtaquesID) {
		this.listaAtaquesID = listaAtaquesID;
	}

	/**
	 * Sets the nombre ataque.
	 * 
	 * @param nombreAtaque
	 *            the new nombre ataque
	 */
	public void setNombreAtaque(final TextInput nombreAtaque) {
		this.nombreAtaque = nombreAtaque;
	}

	/**
	 * Sets the pesados txt.
	 * 
	 * @param pesadosTxt
	 *            the new pesados txt
	 */
	public void setPesadosTxt(final TextInput pesadosTxt) {
		this.pesadosTxt = pesadosTxt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.pivot.wtk.WindowStateListener#windowClosed(org.apache.pivot
	 * .wtk.Window, org.apache.pivot.wtk.Display, org.apache.pivot.wtk.Window)
	 */
	public void windowClosed(final Window window, final Display display, final Window owner) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.pivot.wtk.WindowStateListener#windowCloseVetoed(org.apache
	 * .pivot.wtk.Window, org.apache.pivot.util.Vote)
	 */
	public void windowCloseVetoed(final Window window, final Vote reason) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.pivot.wtk.WindowStateListener#windowOpened(org.apache.pivot
	 * .wtk.Window)
	 */
	public void windowOpened(final Window window) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.pivot.wtk.WindowStateListener#windowOpenVetoed(org.apache.
	 * pivot.wtk.Window, org.apache.pivot.util.Vote)
	 */
	public void windowOpenVetoed(final Window window, final Vote reason) {

	}

}
