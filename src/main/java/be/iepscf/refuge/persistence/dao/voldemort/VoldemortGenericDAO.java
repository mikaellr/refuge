package be.iepscf.refuge.persistence.dao.voldemort;

import be.iepscf.refuge.persistence.dao.GenericDAO;
import be.iepscf.refuge.persistence.dao.voldemort.util.EntityManagerHelper;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class VoldemortGenericDAO<E, ID extends Serializable> implements GenericDAO<E, ID> {

	private Class<E> entityClass;
	
	public VoldemortGenericDAO() {
		entityClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	protected Class<E> getEntityClass() {
		return entityClass;
	}
	
	protected String getEntityName() {
		return getEntityClass().getSimpleName();
	}
	
	protected String getEntityTableName() {
		Table a  = entityClass.getAnnotation(Table.class);
		if (a != null) {
			return a.name();
		} else {
			return getEntityName();
		}
	}
	
	/**
	 * @TODO IoC with Spring instead of instanciation ?
	 */
	protected EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}
	
	

	@Override
	public E find(ID id) {
		return (E) getEntityManager().find(entityClass, id);
	}

	/*@Override
	public E findById(ID id) {
		return find(id);
	}*/

	@Override
	public List<E> findAll() {
		 Query query = getEntityManager().createQuery(String.format("SELECT item FROM %s item", entityClass.getSimpleName()));
		 return (List<E>) query.getResultList();
	}

	@Override
	public long save(E entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Trying to persist null entity");
		}
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		try {
			entityTransaction.begin();
			entityManager.persist(entity);
			entityTransaction.commit();
		} catch (Exception e) {
			entityTransaction.rollback();
			throw e;
		}
		return 1;
	}

	@Override
	public long update(E entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Trying to persist null entity");
		}
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		try {
			entityTransaction.begin();
			entityManager.persist(entity);
			entityTransaction.commit();
		} catch (Exception e) {
			entityTransaction.rollback();
			throw e;
		}
		return 1;
	}








	@Override
	public long delete(E entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Trying to delete null entity");
		}
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		try {
			if (! entityTransaction.isActive()) {
				entityTransaction.begin();
			} else {
				// because not cool : java.lang.IllegalStateException: Transaction already active
				System.err.println("tx is already active !!!!!!!");
				//entityManager.flush();
			}
			entityManager.remove(entity);
			entityTransaction.commit();
		} catch (Exception e) {
			entityTransaction.rollback();
			throw e; 
		} finally {
			 if (entityTransaction.isActive()) {
				 System.err.println("tx still active in finally clause, for strange reason, rollbacking...");
				 entityTransaction.rollback();
			 }
		}
		return 1;
	}




	public E findBy(String attr, Object value) {
		try {
			String query = String.format("select item from %s item where item.%s=:param", getEntityName(), attr);
			return (E) getEntityManager()
					.createQuery(query)
					.setParameter("param", value)
					.getSingleResult();
		} catch (NoResultException e) {
		}
		return null;
	}


	
	/**
	 * There may be non standard SQL in here, but who really swaps rdbms anyway ?
	 */
	public void empty(boolean resetAutoCommit) {
		String sql = String.format("DELETE FROM `%s`", getEntityTableName());
		String sqlResetAutoCommit = String.format("ALTER TABLE `%s` AUTO_INCREMENT = 1", getEntityTableName());
		EntityManager em = getEntityManager();
		EntityTransaction tx = em.getTransaction();		
		tx.begin();
		int affectedRows = em.createNativeQuery(sql).executeUpdate();
		//System.out.println(String.format("emptied '%s' table, %d affected rows", getEntityName(), affectedRows));
		if (resetAutoCommit) {
			affectedRows = em.createNativeQuery(sqlResetAutoCommit).executeUpdate();
			//System.out.println(String.format("reset '%s' table auto commit, %d affected rows", getEntityName(), affectedRows));
		}
		tx.commit();
	}

	public void empty() {
		empty(false);
	}
	

	public void flush() {
		getEntityManager().flush();
	}

	public void clear() {
		getEntityManager().clear();
	}
	
	public void close() {
		getEntityManager().close();
	}
	
}
