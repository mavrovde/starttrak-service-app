package com.starttrak.repo;

import java.util.Optional;

import javax.enterprise.context.RequestScoped;

import com.starttrak.jpa.RegionEntity;

/**
 * @author serg.mavrov@gmail.com
 */
@RequestScoped
public class RegionRepo extends AbstractRepository<RegionEntity> {

    @Override
    public Class<RegionEntity> getEntityClass() {
        return RegionEntity.class;
    }

    public Optional<RegionEntity> findByLabel(String label) {
        return findBy(getBuilder().equal(getFrom(Operation.select).get("label"), label.trim()));
    }

}
