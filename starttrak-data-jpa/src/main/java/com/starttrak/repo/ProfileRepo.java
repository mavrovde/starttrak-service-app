package com.starttrak.repo;

import com.starttrak.jpa.ProfileEntity;

import javax.enterprise.context.RequestScoped;
import java.util.Optional;

/**
 * @author serg.mavrov@gmail.com
 */
@RequestScoped
public class ProfileRepo extends AbstractRepository<ProfileEntity> {

    @Override
    public Class<ProfileEntity> getEntityClass() {
        return ProfileEntity.class;
    }

    public Optional<ProfileEntity> findByNetwork(String email) {
        return findBy(
                getBuilder().equal(getFrom(Operation.select).get("email"),
                        email)
        );
    }

}
