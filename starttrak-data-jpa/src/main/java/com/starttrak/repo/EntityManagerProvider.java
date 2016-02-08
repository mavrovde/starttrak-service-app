package com.starttrak.repo;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author serg.mavrov@gmail.com
 */
@ApplicationScoped
public class EntityManagerProvider {

    @PersistenceContext(unitName = "ST_PU")
    private EntityManager entityManager;

    @Produces
    @RequestScoped
    @Default
    public EntityManager produceEntityManager() {
        return entityManager;
    }

}