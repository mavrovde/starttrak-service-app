package com.starttrak.repo;

import com.starttrak.jpa.TitleEntity;

import javax.enterprise.context.RequestScoped;

/**
 * @author serg.mavrov@gmail.com
 */
@RequestScoped
public class TitleRepo extends AbstractRepository<TitleEntity> {

    @Override
    public Class<TitleEntity> getEntityClass() {
        return TitleEntity.class;
    }

}

