package be.iepscf.refuge.business.mao;

import java.io.Serializable;
import java.util.List;

public interface GenericMAO <E, ID extends Serializable> {

    E get(ID id);

    List<E> get();

    long save(E entity);

    long update(E entity);

    long delete(E entity);

}
