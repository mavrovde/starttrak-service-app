package com.starttrak.repo;

import java.util.List;
import java.util.Optional;

import com.starttrak.jpa.StandardEntity;

/**
 * @author serg.mavrov@gmail.com
 */
public interface StandardRepository<ENTITY extends StandardEntity> {

    Class<ENTITY> getEntityClass();

    ENTITY create(ENTITY t);

    Optional<ENTITY> find(Long id);

    List<ENTITY> findAllBy(Optional<Page> page);

    ENTITY update(ENTITY t);

    void delete(ENTITY t);

    void delete(Long id);

}
