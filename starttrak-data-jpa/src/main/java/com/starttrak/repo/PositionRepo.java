package com.starttrak.repo;

import com.starttrak.jpa.PositionEntity;

import javax.enterprise.context.RequestScoped;

/**
 * @author serg.mavrov@gmail.com
 */
@RequestScoped
public class PositionRepo extends AbstractRepository<PositionEntity> {

    @Override
    public Class<PositionEntity> getEntityClass() {
        return PositionEntity.class;
    }

}
