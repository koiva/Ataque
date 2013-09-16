package org.fbgk.ataque.vistas.actions;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.pivot.wtk.Action;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.Menu.Item;
import org.apache.pivot.wtk.content.MenuItemData;
import org.fbgk.ataque.actions.AtacadorAutomaticoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class ActivarRelojAction.
 */
public class ActivarRelojAction extends Action {

	/** The logger. */
	static Logger						logger	= LoggerFactory.getLogger(ActivarRelojAction.class);

	/** The atacador automatico servicio. */
	private AtacadorAutomaticoServicio	atacadorAutomaticoServicio;

	/** The timer. */
	private Timer						timer;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.pivot.wtk.Action#perform(org.apache.pivot.wtk.Component)
	 */
	@Override
	public void perform(final Component source) {
		final MenuItemData dataMenu = (MenuItemData) ((Item) source).getButtonData();
		if (this.timer == null) {
			logger.debug("Ejecutando los ataques automaticos");
			this.timer = new Timer("atacadorAutomatico");
			this.timer.scheduleAtFixedRate(new TimerTask() {
				@Override
				public void run() {
					try {
						ActivarRelojAction.this.atacadorAutomaticoServicio.atacarTodasListas();
					} catch (final Exception ex) {
						logger.error("Encontrado un error en el timer.", ex);
					}
				}
			}, 0, 300000);
			dataMenu.setText("Parar el atacador");
		} else {
			logger.debug("Parando los ataques automaticos");
			this.timer.cancel();
			this.timer = null;
			dataMenu.setText("Activar ataques automáticos");
		}
	}

	/**
	 * Sets the atacador automatico servicio.
	 * 
	 * @param atacadorAutomaticoServicio
	 *            the new atacador automatico servicio
	 */
	public void setAtacadorAutomaticoServicio(final AtacadorAutomaticoServicio atacadorAutomaticoServicio) {
		this.atacadorAutomaticoServicio = atacadorAutomaticoServicio;
	}

}
