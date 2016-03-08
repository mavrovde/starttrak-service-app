package com.starttrak.repo;

import java.util.Optional;

import javax.enterprise.context.RequestScoped;

import com.starttrak.jpa.CountryEntity;

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
