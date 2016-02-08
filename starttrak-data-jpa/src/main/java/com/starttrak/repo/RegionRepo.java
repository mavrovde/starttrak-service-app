package com.starttrak.repo;

import com.starttrak.jpa.RegionEntity;

import javax.enterprise.context.RequestScoped;

/**
 * @author serg.mavrov@gmail.com
 */
@RequestScoped
public class RegionRepo extends AbstractRepository<RegionEntity> {

    @Override
    public Class<RegionEntity> getEntityClass() {
        return RegionEntity.class;
    }

}
