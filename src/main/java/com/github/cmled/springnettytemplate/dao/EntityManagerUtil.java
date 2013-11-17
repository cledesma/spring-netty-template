package com.github.cmled.springnettytemplate.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {

	private static EntityManager entityManager;

	public static EntityManager getEntityManager() {
		if (entityManager == null) {
			initEntityManager();
		}
		return entityManager;
	}

	/*
	 * Should be invoked on app load
	 */
	public static void initEntityManager() {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("persistence"); // looks for
															// persistence.xml
															// in the classpath
		entityManager = entityManagerFactory.createEntityManager();
	}
}
