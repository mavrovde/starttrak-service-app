package com.starttrak.repo;

import com.starttrak.jpa.SizeEntity;

import javax.enterprise.context.RequestScoped;

/**
 * @author serg.mavrov@gmail.com
 */
@RequestScoped
public class SizeRepo extends AbstractRepository<SizeEntity> {

    @Override
    public Class<SizeEntity> getEntityClass() {
        return SizeEntity.class;
    }

}
