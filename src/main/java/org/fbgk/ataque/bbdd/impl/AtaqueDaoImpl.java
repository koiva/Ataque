package org.fbgk.ataque.bbdd.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.fbgk.ataque.bbdd.AtaqueDao;
import org.fbgk.ataque.bbdd.RecuperarGameID;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class AtaqueDaoImpl.
 */
@Repository("AtaqueDao")
public class AtaqueDaoImpl extends HibernateDaoSupport implements AtaqueDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fbgk.ataque.bbdd.AtaqueDao#actualizar(java.util.List)
	 */
	@Transactional
	public <T> void actualizar(final List<T> dto) {
		for (final T nuevo : dto) {
			this.actualizar(nuevo);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fbgk.ataque.bbdd.AtaqueDao#actualizar(java.lang.Object)
	 */
	@Transactional
	public <T> void actualizar(final T dto) {
		this.getHibernateTemplate().saveOrUpdate(dto);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fbgk.ataque.bbdd.AtaqueDao#actualizaTodo(java.util.List)
	 */
	@Transactional
	public <T> void actualizaTodo(final Collection<T> data) {
		this.getHibernateTemplate().saveOrUpdateAll(data);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fbgk.ataque.bbdd.AtaqueDao#buscar(java.lang.Object)
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fbgk.ataque.bbdd.AtaqueDao#buscar(java.lang.Object)
	 */
	@Transactional(readOnly = true)
	public <T, K> List<K> buscar(final T dto) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fbgk.ataque.bbdd.AtaqueDao#consultar(java.lang.Object)
	 */
	@Transactional(readOnly=true)
	public <T, K> K consultar(final T dto) {
		this.getHibernateTemplate().
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fbgk.ataque.bbdd.AtaqueDao#eliminar(java.lang.Object)
	 */@Transactional
	public <T> void eliminar(final T dto) {
		this.getHibernateTemplate().delete(dto);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fbgk.ataque.bbdd.AtaqueDao#eliminarTodoServidor(java.util.List)
	 */
	@Transactional
	public <T> void eliminarTodoServidor(final List<T> data) {
		this.getHibernateTemplate().deleteAll(data);
	}

	/**
	 * Inits the.
	 * 
	 * @param sessionFactory
	 *            the session factory
	 */
	@Autowired
	public void init(final SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fbgk.ataque.bbdd.AtaqueDao#insertar(java.util.List)
	 */
	@Transactional
	public <T> List<T> insertar(final List<T> dto) {
		final List<T> respuesta = new ArrayList<T>();
		for (final T nuevo : dto) {
			respuesta.add(this.insertar(nuevo));
		}
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fbgk.ataque.bbdd.AtaqueDao#recuperarGameID(org.fbgk.ataque.bbdd.
	 * RecuperarGameID, int)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fbgk.ataque.bbdd.AtaqueDao#insertar(java.lang.Object)
	 */
	@Transactional
	@SuppressWarnings("unchecked")
	public <T> T insertar(final T dto) {
		return (T) this.getHibernateTemplate().save(dto);
	}

	/**
	 * Query game id.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param instance
	 *            the instance
	 * @return the query
	 */
	public <T extends RecuperarGameID> Query queryGameID(final T instance) {
		final Query query = this.getSession().createQuery("FROM :table t WHERE t.gameID = :gameID AND t.servidorID = :servidorID");
		query.setString("table", this.getSession().getEntityName(instance));
		query.setInteger("gameID", instance.getGameID());
		query.setInteger("servidorID", instance.getServidorDTO().getId());
		return query;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fbgk.ataque.bbdd.AtaqueDao#recuperarGameID(org.fbgk.ataque.bbdd.
	 * RecuperarGameID)
	 */
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public <T extends RecuperarGameID> T recuperarGameID(final T dto) {
		T t = null;
		final Query query = this.queryGameID(dto);
		if (query.uniqueResult() != null) {
			final List<T> lista = query.list();
			if (lista.size() == 1) {
				t = lista.get(0);
			}
		}
		return t;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fbgk.ataque.bbdd.AtaqueDao#recuperarTodaInformacion(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public <T extends RecuperarGameID> List<T> recuperarInformacionServer(final int identificador, final String name) {
		return this.getHibernateTemplate().find(String.format("FROM %s WHERE servidorID = ?", name), identificador);
	}

	/**
	 * Recuperar todo.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param dto
	 *            the dto
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> recuperarTodo(final T dto) {
		return (List<T>) this.getHibernateTemplate().loadAll(dto.getClass());
	}

}
