package org.fbgk.ataque.main;

import java.io.IOException;

import org.apache.pivot.wtk.DesktopApplicationContext;
import org.fbgk.ataque.vistas.impl.PantallaPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * The Class Marshaller.
 */
public class Marshaller {

	/** The logger. */
	static Logger						logger	= LoggerFactory.getLogger(Marshaller.class);

	/** The context. */
	public static ApplicationContext	context;

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void main(final String[] args) throws IOException {
		context = new ClassPathXmlApplicationContext("/spring/*.xml");
		DesktopApplicationContext.main(PantallaPrincipal.class, new String[] {});
	}
}
