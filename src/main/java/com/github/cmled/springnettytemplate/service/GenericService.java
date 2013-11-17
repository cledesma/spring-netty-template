package com.github.cmled.springnettytemplate.service;

import java.util.List;

import com.github.cmled.springnettytemplate.dao.GenericDao;

public class GenericService<E> {

	private Class<E> entityClass;
	GenericDao<E> dao;

	public GenericService(Class<E> type) {
		this.entityClass = type;
		this.dao = new GenericDao<E>(entityClass);
	}

	public void create(E entity) {
		this.dao.persist(entity);
	}

	public E update(E entity) {
		return this.dao.merge(entity);
	}

	public void delete(E entity) {
		this.dao.remove(entity);
	}
	
	public void delete(long id){
		this.dao.remove(id);
	}

	public E findById(long id) {
		return this.dao.findById(id);
	}

	public List<E> findAll() {
		return this.dao.getAll();
	}

}
