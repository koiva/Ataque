package org.fbgk.ataque.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.datetime.DateFormatter;

/**
 * The Class FileUtils.
 */
public class FileUtils {

	/**
	 * The Enum TipoArchivo.
	 */
	public enum TipoArchivo {

		/** The procesado. */
		PROCESADO,
		/** The temporal. */
		TEMPORAL;
	}

	/** The logger. */
	static Logger	logger	= LoggerFactory.getLogger(FileUtils.class);

	/**
	 * Copiar fichero. Copia un fichero desde un recurso a otro
	 * 
	 * @param source
	 *            the source
	 * @param target
	 *            the target
	 */
	public static void copiarFichero(final File source, final File target) {
		if (!target.exists() && source.exists()) {
			try {
				final FileInputStream fileInput = new FileInputStream(source);
				final FileOutputStream fileOutput = new FileOutputStream(target);
				final FileChannel src = fileInput.getChannel();
				final FileChannel dest = fileOutput.getChannel();
				dest.transferFrom(src, 0, src.size());
				fileInput.close();
				fileOutput.close();
			} catch (final FileNotFoundException e) {
				logger.error(e.getMessage());
			} catch (final IOException e) {
				logger.error(e.getMessage());
			}

		}
	}

	/**
	 * Descodificar linea.
	 * 
	 * @param linea
	 *            the linea
	 * @return the string
	 */
	public static String descodificarLinea(final String linea) {
		return descodificarLinea(linea, "UTF-8");
	}

	/**
	 * Descodificar linea.
	 * 
	 * @param linea
	 *            the linea
	 * @param codificacion
	 *            the codificacion
	 * @return the string
	 */
	public static String descodificarLinea(final String linea, final String codificacion) {
		String respuesta = linea;
		try {
			respuesta = URLDecoder.decode(linea, codificacion);
		} catch (final UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return respuesta;
	}

	/**
	 * Devolucion archivo.
	 * 
	 * @param tipo
	 *            the tipo
	 * @param game
	 *            the game
	 * @param server
	 *            the server
	 * @param nameFile
	 *            the name file
	 * @return the file
	 */
	private static File devolucionArchivo(final TipoArchivo tipo, final String game, final String server, final String nameFile) {
		final DateFormatter dateFormatter = new DateFormatter("yyyyMMdd");
		final String directorio = String.format("%s/%s/%s/%s/", tipo.toString().toLowerCase(), game, server, dateFormatter.print(new Date(), Locale.ENGLISH));
		final File respuesta = new File(directorio);
		respuesta.mkdirs();
		return new File(String.format("%s/%s", directorio, nameFile));
	}

	/**
	 * Escribir archivo.
	 * 
	 * @param inputStream
	 *            the input stream
	 * @param game
	 *            the game
	 * @param server
	 *            the server
	 * @param nameFile
	 *            the name file
	 * @return the file
	 */
	public static File escribirArchivo(final InputStream inputStream, final String game, final String server, final String nameFile) {
		final File respuesta = devolucionArchivo(TipoArchivo.TEMPORAL, game, server, nameFile);
		try {
			if (respuesta.exists()) {
				respuesta.delete();
			}
			final FileWriter fileWriter = new FileWriter(respuesta);
			final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;
			while ((line = reader.readLine()) != null) {
				fileWriter.write(String.format("%s\n", line));
			}
			reader.close();
			fileWriter.close();
		} catch (final IOException e) {
			logger.error(e.getMessage());
		}
		return respuesta;
	}

	/**
	 * Escribir archivo.
	 * 
	 * @param inputStream
	 *            the input stream
	 * @param game
	 *            the game
	 * @param server
	 *            the server
	 * @param nameFile
	 *            the name file
	 * @return the file
	 */
	public static List<String> leerArchivo(final String game, final String server, final String nameFile) {
		final List<String> respuesta = new ArrayList<String>();
		final File archivoProcesado = devolucionArchivo(TipoArchivo.PROCESADO, game, server, nameFile);
		if (!archivoProcesado.exists()) {
			final File fileTemporal = devolucionArchivo(TipoArchivo.TEMPORAL, game, server, nameFile);
			try {
				final BufferedReader bufferedReader = new BufferedReader(new FileReader(fileTemporal));
				while (bufferedReader.ready()) {
					respuesta.add(bufferedReader.readLine());
				}
				respuesta.add(bufferedReader.readLine());
				bufferedReader.close();
			} catch (final IOException e) {
				logger.error(e.getMessage());
			}
			copiarFichero(fileTemporal, archivoProcesado);
			fileTemporal.delete();
		}
		return respuesta;
	}

	/**
	 * Nombre archivo.
	 * 
	 * @param valor
	 *            the valor
	 * @return the string
	 */
	public static String nombreArchivo(final String valor) {
		final String nombres[] = valor.split("/");
		return nombres[nombres.length - 1];
	}

	/**
	 * Existe archivo procesado.
	 * 
	 * @param game
	 *            the game
	 * @param server
	 *            the server
	 * @param nameFile
	 *            the name file
	 * @return the boolean
	 */
	public final Boolean existeArchivoProcesado(final String game, final String server, final String nameFile) {
		return devolucionArchivo(TipoArchivo.PROCESADO, game, server, nameFile).exists();
	}
}
