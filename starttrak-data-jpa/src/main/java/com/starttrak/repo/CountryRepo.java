package com.starttrak.repo;

import com.starttrak.jpa.CountryEntity;

import javax.enterprise.context.RequestScoped;

/**
 * @author serg.mavrov@gmail.com
 */
@RequestScoped
public class CountryRepo extends AbstractRepository<CountryEntity> {

    @Override
    public Class<CountryEntity> getEntityClass() {
        return CountryEntity.class;
    }

}
