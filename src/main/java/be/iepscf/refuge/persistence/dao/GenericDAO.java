package be.iepscf.refuge.persistence.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO <E, ID extends Serializable> {

	E find(ID id); // SELECT ... WHERE id=id => E

	List<E> findAll(); // SELECT ...

	long save(E entity); // INSERT => last insert id

	long update(E entity); // UPDATE => affected rows

	long delete(E entity); // DELETE => affected rows

}
