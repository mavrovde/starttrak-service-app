package com.starttrak.repo;

import com.starttrak.jpa.StandardEntity;

import javax.persistence.criteria.Predicate;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 * @author serg.mavrov@gmail.com
 */
public interface StandardRepository<ENTITY extends StandardEntity> {

    Class<ENTITY> getEntityClass();

    ENTITY create(ENTITY t);

    Optional<ENTITY> find(long id);

    List<ENTITY> findAllBy(Optional<Page> page);

    ENTITY update(ENTITY t);

    void delete(ENTITY t);

    void delete(long id);

}
