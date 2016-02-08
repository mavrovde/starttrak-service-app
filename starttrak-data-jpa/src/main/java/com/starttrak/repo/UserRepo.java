package com.starttrak.repo;

import com.starttrak.jpa.UserEntity;

import javax.enterprise.context.RequestScoped;
import java.util.Optional;

/**
 * @author serg.mavrov@gmail.com
 */
@RequestScoped
public class UserRepo extends AbstractRepository<UserEntity> {

    @Override
    public Class<UserEntity> getEntityClass() {
        return UserEntity.class;
    }

    public Optional<UserEntity> findByOwnSessionId(String ownSessionId) {
        return findBy(
                getBuilder().equal(getFrom(Operation.select).get("ownSessionId"),
                        ownSessionId)
        );
    }

}
