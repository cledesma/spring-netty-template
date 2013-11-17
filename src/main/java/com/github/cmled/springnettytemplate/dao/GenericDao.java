package com.github.cmled.springnettytemplate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class GenericDao<E> {

	private Class<E> entityClass;

	private EntityManager entityManager;

	public GenericDao(Class<E> type) {
		this.entityClass = type;
		entityManager = EntityManagerUtil.getEntityManager();
	}

	/**
	 * Method for inserting data into database
	 * 
	 * @param entity
	 *            Name of the table where you want to persist
	 */
	public void persist(E entity) {
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
	}

	/**
	 * Method that performs deletion from database
	 * 
	 * @param entity
	 *            Name of the table where you want to delete
	 */
	public void remove(E entity) {
		entityManager.getTransaction().begin();
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
	}
	
	/**
	 * Delete record by id
	 * @param id
	 */
	public void remove(long id) {
		entityManager.getTransaction().begin();
		entityManager.remove(findById(id));
		entityManager.getTransaction().commit();
	}

	/**
	 * Method that retrieves record from db given the id(primary key)
	 * 
	 * @param id
	 *            Indicates the primary key of the record
	 * @return entity
	 */
	public E findById(long id) {
		return entityManager.find(entityClass, id);
	}

	/**
	 * Method that updates records in db
	 * 
	 * @param entity
	 *            Name of table to be updated
	 * @return entity
	 */
	public E merge(E entity) {
		entityManager.getTransaction().begin();
		E e = entityManager.merge(entity);
		entityManager.getTransaction().commit();
		return e;
	}

	/**
	 * Refresh record in db after performing persist or merge
	 * 
	 * @param entity
	 *            Name of table to refresh
	 */
	public void refresh(E entity) {
		entityManager.refresh(entity);
	}

	/**
	 * Method that retrieves all records and put it in a List
	 * 
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<E> getAll() {
		Query query = entityManager.createQuery("select e from "
				+ entityClass.getName() + " e");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<E> doCustomQuery(String q) {
		Query query = entityManager.createQuery(q);
		return query.getResultList();
	}

}