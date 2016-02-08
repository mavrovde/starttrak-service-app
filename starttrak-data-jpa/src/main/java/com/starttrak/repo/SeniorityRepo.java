package com.starttrak.repo;

import com.starttrak.jpa.SeniorityEntity;

import javax.enterprise.context.RequestScoped;

/**
 * @author serg.mavrov@gmail.com
 */
@RequestScoped
public class SeniorityRepo extends AbstractRepository<SeniorityEntity> {

    @Override
    public Class<SeniorityEntity> getEntityClass() {
        return SeniorityEntity.class;
    }

}
