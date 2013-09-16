package org.fbgk.ataque.vistas.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
import org.apache.pivot.wtk.TableView;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.Window;
import org.fbgk.ataque.guerrastribales.dto.AtaqueDTO;
import org.fbgk.ataque.guerrastribales.dto.DatosAtaqueDTO;
import org.fbgk.ataque.guerrastribales.dto.ListaAtaquesDTO;
import org.fbgk.ataque.guerrastribales.dto.PueblosDTO;
import org.fbgk.ataque.vistas.base.GestionAtaqueServicioBase;
import org.fbgk.ataque.vistas.constantes.ConstantesPantallas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class GestionAtaqueServicioImpl.
 */
public class GestionAtaqueServicioImpl extends GestionAtaqueServicioBase implements ButtonPressListener, ListButtonSelectionListener {

	static Logger		logger	= LoggerFactory.getLogger(GestionAtaqueServicioImpl.class);

	/** The button salir. */
	@BXML
	private PushButton	buttonSalir;

	/** The formulario. */
	@BXML
	private Form		formulario;

	/** The frame ataques. */
	@BXML
	private Frame		frameAtaques;

	/** The gestion tabla. */
	@BXML
	private TableView	gestionTabla;

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
		}
	}

	public Double distanciaMax(final PueblosDTO pueblosDTOA, final PueblosDTO pueblosDTOB) {
		final int xMin = pueblosDTOA.getX() - pueblosDTOB.getX();
		final int yMin = pueblosDTOA.getY() - pueblosDTOB.getY();
		return Math.hypot(Math.abs(xMin), Math.abs(yMin));
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
			this.frameAtaques = this.utilVistaServicio.openFrame(this.frameAtaques, display, window, this, ConstantesPantallas.PANTALLA_GESTION_ATAQUES);
			if (this.frameAtaques != null) {
				this.buttonSalir.getButtonPressListeners().add(this);
				this.listaAtaqueID.setListData(this.utilVistaServicio.recuperarLista(this.ataqueDao.recuperarTodo(new ListaAtaquesDTO())));
				this.listaAtaqueID.getListButtonSelectionListeners().add(this);
			}
		} else {
			Alert.alert("No se puede abrir la ventana si no hay lista de ataques dados de alta", window);
		}
	}

	@Override
	public void selectedIndexChanged(final ListButton listButton, final int previousSelectedIndex) {
		// TODO Auto-generated method stub

	}

	@Override
	public void selectedItemChanged(final ListButton listButton, final Object previousSelectedItem) {
		final ListaAtaquesDTO listaAtaquesDTO = (ListaAtaquesDTO) listButton.getSelectedItem();
		if (listaAtaquesDTO != null) {
			final PueblosDTO pueblosDTO = this.ataqueDao.consultar("FROM PueblosDTO WHERE gameID=? AND servidorID=?", listaAtaquesDTO.getGameIDPropio(), listaAtaquesDTO.getServidorDTO().getServidorID());
			this.usuario.setText(pueblosDTO.getJugadoresDTO().getNombre());
			this.poblado.setText(String.format("%s (%s|%s)", pueblosDTO.getName(), pueblosDTO.getX(), pueblosDTO.getY()));
			final List<AtaqueDTO> listaAtaques = this.ataqueDao.buscar("FROM AtaqueDTO WHERE listaAtaquesID=?", listaAtaquesDTO.getListaAtaquesID());
			final List<DatosAtaqueDTO> listaDato = new ArrayList<DatosAtaqueDTO>();
			for (final AtaqueDTO ataqueDTO : listaAtaques) {
				final PueblosDTO pueblosDTO2 = this.ataqueDao.consultar("FROM PueblosDTO WHERE gameID=? AND servidorID=?", ataqueDTO.getGameIDAtaque(), listaAtaquesDTO.getServidorDTO().getServidorID());
				final DatosAtaqueDTO datosAtaqueDTO = new DatosAtaqueDTO();
				datosAtaqueDTO.setDistancia(ataqueDTO.getDistanciaMax());
				if (ataqueDTO.getTiempoAtaque() != null) {
					datosAtaqueDTO.setFecha(new SimpleDateFormat("hh:mm:ss dd-MM-yyyy").format(ataqueDTO.getTiempoAtaque().getTime()));
				} else {
					datosAtaqueDTO.setFecha("-");
				}
				datosAtaqueDTO.setPuntos(pueblosDTO2.getPoints());
				datosAtaqueDTO.setX(pueblosDTO2.getX());
				datosAtaqueDTO.setY(pueblosDTO2.getY());
				listaDato.add(datosAtaqueDTO);
			}
			this.gestionTabla.setTableData(this.utilVistaServicio.recuperarLista(listaDato));
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
	 * Sets the gestion tabla.
	 * 
	 * @param gestionTabla
	 *            the new gestion tabla
	 */
	public void setGestionTabla(final TableView gestionTabla) {
		this.gestionTabla = gestionTabla;
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
