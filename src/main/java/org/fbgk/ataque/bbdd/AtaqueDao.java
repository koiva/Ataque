package org.fbgk.ataque.bbdd;

import java.util.Collection;
import java.util.List;

/**
 * The Interface AtaqueDao.
 */
public interface AtaqueDao {

	/**
	 * Actualizar.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param dto
	 *            the dto
	 */
	public <T> void actualizar(List<T> dto);

	/**
	 * Actualizar.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param dto
	 *            the dto
	 */
	public <T> void actualizar(T dto);

	/**
	 * Actualiza todo.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param data
	 *            the data
	 */
	public <T> void actualizaTodo(Collection<T> data);

	/**
	 * Buscar.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param <K>
	 *            the key type
	 * @param dto
	 *            the dto
	 * @return the list
	 */
	public <T, K> List<K> buscar(T dto);

	/**
	 * Consultar.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param <K>
	 *            the key type
	 * @param dto
	 *            the dto
	 * @return the k
	 */
	public <T, K> K consultar(T dto);

	/**
	 * Eliminar.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param dto
	 *            the dto
	 */
	public <T> void eliminar(T dto);

	/**
	 * Eliminar todo servidor.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param data
	 *            the data
	 */
	public <T> void eliminarTodoServidor(List<T> data);

	/**
	 * Insertar.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param dto
	 *            the dto
	 * @return the list
	 */
	public <T> List<T> insertar(List<T> dto);

	/**
	 * Insertar.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param dto
	 *            the dto
	 * @return the t
	 */
	public <T> T insertar(T dto);

	/**
	 * Recuperar game id.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param dto
	 *            the dto
	 * @return the t
	 */
	public <T extends RecuperarGameID> T recuperarGameID(T dto);

	/**
	 * Recuperar toda informacion.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param identificador
	 *            the identificador
	 * @param name
	 *            the name
	 * @return the list
	 */
	public <T extends RecuperarGameID> List<T> recuperarInformacionServer(int identificador, String name);

	/**
	 * Recuperar todo.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param dto
	 *            the dto
	 * @return the list
	 */
	public <T> List<T> recuperarTodo(final T dto);

}
