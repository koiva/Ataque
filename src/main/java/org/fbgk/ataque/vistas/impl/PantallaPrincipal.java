package org.fbgk.ataque.vistas.impl;

import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.collections.Map;
import org.apache.pivot.wtk.Action;
import org.apache.pivot.wtk.Application;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.Window;
import org.fbgk.ataque.main.Marshaller;
import org.fbgk.ataque.transformacion.TransformacionServicio;

public class PantallaPrincipal extends Window implements Application {

	private JugadoresServicio		jugadoresServicio;

	private TransformacionServicio	transformacionServicio;

	private void menusAyuda() {
		Action.getNamedActions().put("nuevoJugador", new Action() {
			@Override
			public void perform(final Component source) {
				PantallaPrincipal.this.jugadoresServicio.open(PantallaPrincipal.this.getDisplay());
			}
		});
		Action.getNamedActions().put("nuevaLista", new Action() {
			@Override
			public void perform(final Component source) {
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
		Action.getNamedActions().put("SobreAplicacion", new Action() {
			@Override
			public void perform(final Component source) {

			}
		});
		Action.getNamedActions().put("Salir", new Action() {
			@Override
			public void perform(final Component source) {

			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.pivot.wtk.Application#resume()
	 */
	public void resume() throws Exception {

	}

	public void setJugadoresServicio(final JugadoresServicio jugadoresServicio) {
		this.jugadoresServicio = jugadoresServicio;
	}

	public void setTransformacionServicio(final TransformacionServicio transformacionServicio) {
		this.transformacionServicio = transformacionServicio;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.pivot.wtk.Application#shutdown(boolean)
	 */
	public boolean shutdown(final boolean arg0) throws Exception {
		return false;
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
		final Window window = (Window) bxmlSerializer.readObject(this.getClass().getResource("/apache-pivot/view/AplicacionPrincipal.bxml"));
		display.add(window);
		this.jugadoresServicio = Marshaller.context.getBean(JugadoresServicio.class);
		this.transformacionServicio = Marshaller.context.getBean(TransformacionServicio.class);
		this.open(display);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.pivot.wtk.Application#suspend()
	 */
	public void suspend() throws Exception {

	}

}
