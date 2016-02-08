package com.starttrak.repo;

import com.starttrak.jpa.IndustryEntity;

import javax.enterprise.context.RequestScoped;

/**
 * @author serg.mavrov@gmail.com
 */
@RequestScoped
public class IndustryRepo extends AbstractRepository<IndustryEntity> {

    @Override
    public Class<IndustryEntity> getEntityClass() {
        return IndustryEntity.class;
    }

}
