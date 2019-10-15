package be.iepscf.refuge.persistence.dao.voldemort.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * Helper class to instantiate and call EntityManagers singletons on a per-thread basis
 *
 * Used in OpenEntityManagerInViewFilter to close existing connection after a request on a servlet
 *
 *
 * inspiration : https://stackoverflow.com/questions/15071238/entitymanager-threadlocal-pattern-with-jpa-in-jse
 */
public class EntityManagerHelper {

	private static boolean debug = false;
	private static final String persistenceUnitName = "refuge";
	private static EntityManagerFactory entityManagerFactory;
	private static final ThreadLocal<EntityManager> threadLocalEntityManager = new ThreadLocal<EntityManager>();

	private EntityManagerHelper() {
	}

	private static void debug(String message) {
		if (debug) System.out.println(message);
	}

	public static EntityManagerFactory getEntityManagerFactory() {
		if (entityManagerFactory == null) {
			debug("creating static EntityManagerFactory");
			entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
		}
		return entityManagerFactory;
	}

	public static EntityManager getEntityManager() {
		EntityManager em = threadLocalEntityManager.get();
		if (em == null) {
			debug("creating static thread-local EntityManager");
			EntityManagerFactory emf = getEntityManagerFactory();
			if (emf != null) {
				em = emf.createEntityManager();
				threadLocalEntityManager.set(em);
			}
		}
		return em;
	}

	public static void closeEntityManager() {
		EntityManager em = threadLocalEntityManager.get();
		if (em != null && em.isOpen()) {
			debug("closing static thread-local EntityManager");
			em.close();
			threadLocalEntityManager.set(null);
		}
	}

	public static void close() {
		closeEntityManager();
	}

	public static void closeEntityManagerFactory() {
		if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
			debug("closing static EntityManagerFactory");
			entityManagerFactory.close();
		}
	}

	/*
	public static void beginTransaction() {
		getEntityManager().getTransaction().begin();
	}

	public static void rollback() {
		getEntityManager().getTransaction().rollback();
	}

	public static void commit() {
		getEntityManager().getTransaction().commit();
	}*/

}
