package ro.teamnet.zth.api.em;

import java.util.List;

/**
 * Created by Ionutz on 30.04.2015.
 */
public interface EntityManager {
    	<T> T findById(Class<T> entityClass, Long id);
    	<T> List<T> findAll(Class<T> entityClass);
    	<T> T insert(T entity);
    	<T> T update(T entity);
        void delete(Object entity);
}
