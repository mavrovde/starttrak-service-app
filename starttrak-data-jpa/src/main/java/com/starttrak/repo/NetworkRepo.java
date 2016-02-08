package com.starttrak.repo;

import com.starttrak.jpa.NetworkEntity;

import javax.enterprise.context.RequestScoped;

/**
 * @author serg.mavrov@gmail.com
 */
@RequestScoped
public class NetworkRepo extends AbstractRepository<NetworkEntity> {

    @Override
    public Class<NetworkEntity> getEntityClass() {
        return NetworkEntity.class;
    }

}
