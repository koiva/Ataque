package org.fbgk.ataque.bbdd;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.fbgk.ataque.bbdd.interfaz.RecuperarGameID;
import org.fbgk.ataque.bbdd.interfaz.SetearSerializable;

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
	 * @param <K>
	 *            the key type
	 * @param SQL
	 *            the sql
	 * @return the list
	 */
	public <K> List<K> buscar(String SQL);

	/**
	 * Buscar.
	 * 
	 * @param <K>
	 *            the key type
	 * @param SQL
	 *            the sql
	 * @param constantes
	 *            the constantes
	 * @return the list
	 */
	public <K> List<K> buscar(String SQL, Object... constantes);

	/**
	 * Consultar.
	 * 
	 * @param <K>
	 *            the key type
	 * @param SQL
	 *            the sql
	 * @return the k
	 */
	public <K> K consultar(String SQL);

	/**
	 * Consultar.
	 * 
	 * @param <K>
	 *            the key type
	 * @param SQL
	 *            the sql
	 * @param constantes
	 *            the constantes
	 * @return the k
	 */
	public <K> K consultar(String SQL, Object... constantes);

	/**
	 * Consultar.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param dato
	 *            the dato
	 * @param id
	 *            the id
	 * @return the t
	 */
	public <T> T consultar(T dato, Serializable id);

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
	public <T extends SetearSerializable> List<T> insertar(List<T> dto);

	/**
	 * Insertar.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param dto
	 *            the dto
	 * @return the t
	 */
	public <T extends SetearSerializable> T insertar(T dto);

	/**
	 * Recuperar entidad.
	 * 
	 * @param object
	 *            the object
	 * @return the string
	 */
	public String recuperarEntidad(Object object);

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
