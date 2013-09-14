package org.fbgk.ataque.vistas.impl;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.collections.Map;
import org.apache.pivot.wtk.Action;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.FlowPane;
import org.apache.pivot.wtk.Window;
import org.fbgk.ataque.actions.URLActionsServicio;
import org.fbgk.ataque.main.Marshaller;
import org.fbgk.ataque.transformacion.TransformacionServicio;
import org.fbgk.ataque.vistas.GestionAtaquesServicio;
import org.fbgk.ataque.vistas.ServidorServicio;
import org.fbgk.ataque.vistas.actions.ActivarRelojAction;
import org.fbgk.ataque.vistas.base.PantallaPrincipalBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class PantallaPrincipal.
 */
public class PantallaPrincipal extends PantallaPrincipalBase {

	@BXML
	private FlowPane	flowPane;

	/** The logger. */
	static Logger		logger	= LoggerFactory.getLogger(PantallaPrincipal.class);

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
				PantallaPrincipal.this.gestionAtaquesServicio.open(PantallaPrincipal.this.flowPane.getDisplay(), PantallaPrincipal.this.flowPane.getWindow());
			}
		});
		Action.getNamedActions().put("Opciones", new Action() {
			@Override
			public void perform(final Component source) {
			}
		});
		Action.getNamedActions().put("RestablecerBBDD", new Action() {
			@Override
			public void perform(final Component source) {
				PantallaPrincipal.this.transformacionServicio.actualizarBBDD();
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
	public void startup(final Display display, final Map<String, String> mapeo) throws Exception {
		// this.context = new ClassPathXmlApplicationContext("/spring/*.xml");
		this.menusAyuda();
		final BXMLSerializer bxmlSerializer = new BXMLSerializer();
		this.window = (Window) bxmlSerializer.readObject(this.getClass().getResource("/apache-pivot/view/AplicacionPrincipal.bxml"));
		bxmlSerializer.bind(this);
		display.add(this.window);
		this.jugadoresServicio = Marshaller.context.getBean(JugadoresServicioImpl.class);
		this.transformacionServicio = Marshaller.context.getBean(TransformacionServicio.class);
		this.urlActionsServicio = Marshaller.context.getBean(URLActionsServicio.class);
		this.servidorServicio = Marshaller.context.getBean(ServidorServicio.class);
		this.gestionAtaquesServicio = Marshaller.context.getBean(GestionAtaquesServicio.class);
		this.open(display);
	}
}
