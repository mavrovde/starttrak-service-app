package com.starttrak.repo;

import com.starttrak.jpa.RegionEntity;
import com.starttrak.jpa.UserEntity;

import javax.enterprise.context.RequestScoped;
import java.util.Optional;

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
