package org.fbgk.ataque.vistas.impl;

import java.io.IOException;

import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;
import org.apache.pivot.serialization.SerializationException;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.Frame;
import org.apache.pivot.wtk.Window;
import org.fbgk.ataque.vistas.UtilVistaServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class UtilVistaServicioImpl.
 */
public class UtilVistaServicioImpl implements UtilVistaServicio {

	/** The logger. */
	static Logger	logger	= LoggerFactory.getLogger(UtilVistaServicioImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fbgk.ataque.vistas.UtilVistaServicio#openFrame(org.apache.pivot.wtk
	 * .Frame, org.apache.pivot.wtk.Display, org.apache.pivot.wtk.Window,
	 * java.lang.Object, java.lang.String)
	 */
	public Frame openFrame(Frame frame, final Display display, final Window ownerArgument, final Object objeto, final String archivo) {
		if (frame == null || !frame.isOpen()) {
			final BXMLSerializer bxmlSerializer = new BXMLSerializer();
			bxmlSerializer.getNamespace().put("application", objeto);
			try {
				frame = (Frame) bxmlSerializer.readObject(objeto.getClass(), String.format("/apache-pivot/view/%s", archivo));
			} catch (final IOException e) {
				logger.error("Error al leer el archivo", e);
			} catch (final SerializationException e) {
				logger.error("Error deserializando el archivo", e);
			}
			frame.setName("Insertar o modificar los datos");
			// Seteamos los datos de la anotacion BXML
			bxmlSerializer.bind(objeto);
			frame.setLocation(40, 40);
			frame.open(display, ownerArgument);
		}
		return frame;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fbgk.ataque.vistas.UtilVistaServicio#recuperarLista(java.util.List)
	 */
	public <T> List<T> recuperarLista(final java.util.List<T> listaCambiar) {
		final List<T> list = new ArrayList<T>();
		for (final T dato : listaCambiar) {
			list.add(dato);
		}
		return list;
	}

}
