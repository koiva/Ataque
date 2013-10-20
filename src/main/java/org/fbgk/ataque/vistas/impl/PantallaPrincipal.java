package org.fbgk.ataque.vistas.impl;

import java.io.IOException;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.collections.Map;
import org.apache.pivot.serialization.SerializationException;
import org.apache.pivot.wtk.Action;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.FlowPane;
import org.apache.pivot.wtk.Sheet;
import org.apache.pivot.wtk.Window;
import org.fbgk.ataque.actions.URLActionsServicio;
import org.fbgk.ataque.main.Marshaller;
import org.fbgk.ataque.transformacion.TransformacionServicio;
import org.fbgk.ataque.vistas.GestionAtaquesServicio;
import org.fbgk.ataque.vistas.GestionDistanciaMaxServicio;
import org.fbgk.ataque.vistas.GestionListaAtaquesServicio;
import org.fbgk.ataque.vistas.JugadoresServicio;
import org.fbgk.ataque.vistas.OpcionesServicio;
import org.fbgk.ataque.vistas.ServidorServicio;
import org.fbgk.ataque.vistas.actions.ActivarRelojAction;
import org.fbgk.ataque.vistas.base.PantallaPrincipalBase;
import org.fbgk.ataque.vistas.constantes.ConstantesPantallas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class PantallaPrincipal.
 */
public class PantallaPrincipal extends PantallaPrincipalBase {

	/** The logger. */
	static Logger		logger	= LoggerFactory.getLogger(PantallaPrincipal.class);

	@BXML
	private FlowPane	flowPane;

	private void cerrarSheet() {
		this.sheet.close();
	}

	private void esperePorFavor() throws IOException, SerializationException {
		final BXMLSerializer bxmlSerializer = new BXMLSerializer();
		this.sheet = (Sheet) bxmlSerializer.readObject(this.getClass().getResource(String.format("/apache-pivot/view/%s", ConstantesPantallas.PANTALLA_ESPERA)));
		this.sheet.open(this.getDisplay(), this.window);
	}

	/**
	 * Menus ayuda.
	 */
	private void menusAyuda() {
		Action.getNamedActions().put("gestionServidor", new Action() {
			@Override
			public void perform(final Component source) {
				PantallaPrincipal.this.servidorServicio.open(PantallaPrincipal.this.flowPane.getDisplay(), PantallaPrincipal.this.flowPane.getWindow());
			}
		});
		Action.getNamedActions().put("gestionJugador", new Action() {
			@Override
			public void perform(final Component source) {
				PantallaPrincipal.this.jugadoresServicio.open(PantallaPrincipal.this.flowPane.getDisplay(), PantallaPrincipal.this.flowPane.getWindow());
			}
		});
		Action.getNamedActions().put("gestionLista", new Action() {
			@Override
			public void perform(final Component source) {
				PantallaPrincipal.this.gestionListaAtaquesServicio.open(PantallaPrincipal.this.flowPane.getDisplay(), PantallaPrincipal.this.flowPane.getWindow());
			}
		});
		Action.getNamedActions().put("Opciones", new Action() {
			@Override
			public void perform(final Component source) {
				PantallaPrincipal.this.opcionesServicio.open(PantallaPrincipal.this.flowPane.getDisplay(), PantallaPrincipal.this.flowPane.getWindow());
			}
		});
		Action.getNamedActions().put("RestablecerBBDD", new Action() {
			@Override
			public void perform(final Component source) {
				try {
					PantallaPrincipal.this.esperePorFavor();
				} catch (final IOException e) {
					logger.error("Error al leer el archivo", e);
				} catch (final SerializationException e) {
					logger.error("Error serializando el archivo", e);
				}
				final Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {
						PantallaPrincipal.this.transformacionServicio.actualizarBBDD();
						PantallaPrincipal.this.cerrarSheet();
					}
				});
				thread.start();
			}
		});

		Action.getNamedActions().put("activarReloj", Marshaller.context.getBean(ActivarRelojAction.class));

		Action.getNamedActions().put("SobreAplicacion", new Action() {
			@Override
			public void perform(final Component source) {

			}
		});
		Action.getNamedActions().put("Salir", new Action() {
			@Override
			public void perform(final Component source) {
				try {
					logger.debug("Apagando aplicacion");
					PantallaPrincipal.this.shutdown(Boolean.FALSE);
				} catch (final Exception e) {
					logger.error("La aplicacion no ha podido ser cerrada", e);
				}
			}
		});
		Action.getNamedActions().put("mirarAtaques", new Action() {
			@Override
			public void perform(final Component source) {
				PantallaPrincipal.this.gestionAtaquesServicio.open(PantallaPrincipal.this.flowPane.getDisplay(), PantallaPrincipal.this.flowPane.getWindow());
			}
		});
		Action.getNamedActions().put("gestionBarbaros", new Action() {
			@Override
			public void perform(final Component source) {
				PantallaPrincipal.this.gestionDistanciaMaxServicio.open(PantallaPrincipal.this.flowPane.getDisplay(), PantallaPrincipal.this.flowPane.getWindow());
			}
		});

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.pivot.wtk.Application#startup(org.apache.pivot.wtk.Display,
	 * org.apache.pivot.collections.Map)
	 */
	@Override
	public void startup(final Display display, final Map<String, String> mapeo) throws Exception {
		// this.context = new ClassPathXmlApplicationContext("/spring/*.xml");
		this.menusAyuda();
		final BXMLSerializer bxmlSerializer = new BXMLSerializer();
		this.window = (Window) bxmlSerializer.readObject(this.getClass().getResource("/apache-pivot/view/AplicacionPrincipal.bxml"));
		bxmlSerializer.bind(this);
		display.add(this.window);
		this.jugadoresServicio = Marshaller.context.getBean(JugadoresServicio.class);
		this.transformacionServicio = Marshaller.context.getBean(TransformacionServicio.class);
		this.urlActionsServicio = Marshaller.context.getBean(URLActionsServicio.class);
		this.servidorServicio = Marshaller.context.getBean(ServidorServicio.class);
		this.gestionListaAtaquesServicio = Marshaller.context.getBean(GestionListaAtaquesServicio.class);
		this.gestionAtaquesServicio = Marshaller.context.getBean(GestionAtaquesServicio.class);
		this.gestionDistanciaMaxServicio = Marshaller.context.getBean(GestionDistanciaMaxServicio.class);
		this.opcionesServicio = Marshaller.context.getBean(OpcionesServicio.class);
		this.open(display);
	}
}
