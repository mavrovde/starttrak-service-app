package com.starttrak.repo;

import com.starttrak.jpa.CompanyEntity;

import javax.enterprise.context.RequestScoped;

/**
 * @author serg.mavrov@gmail.com
 */
@RequestScoped
public class CompanyRepo extends AbstractRepository<CompanyEntity> {

    @Override
    public Class<CompanyEntity> getEntityClass() {
        return CompanyEntity.class;
    }

}
