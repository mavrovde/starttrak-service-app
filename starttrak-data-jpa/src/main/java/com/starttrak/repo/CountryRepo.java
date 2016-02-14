package com.starttrak.repo;

import com.starttrak.jpa.CountryEntity;
import com.starttrak.jpa.RegionEntity;

import javax.enterprise.context.RequestScoped;
import java.util.Optional;

/**
 * @author serg.mavrov@gmail.com
 */
@RequestScoped
public class CountryRepo extends AbstractRepository<CountryEntity> {

    @Override
    public Class<CountryEntity> getEntityClass() {
        return CountryEntity.class;
    }

    public Optional<CountryEntity> findByLabel(String label) {
        return findBy(getBuilder().equal(getFrom(Operation.select).get("label"), label.trim()));
    }

}
