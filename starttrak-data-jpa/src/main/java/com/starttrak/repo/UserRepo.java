package com.starttrak.repo;

import com.starttrak.jpa.UserEntity;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

/**
 * @author serg.mavrov@gmail.com
 */
@RequestScoped
public class UserRepo extends AbstractRepository<UserEntity> {

    @Override
    public Class<UserEntity> getEntityClass() {
        return UserEntity.class;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public UserEntity create(String email, String password, String data) {
        UserEntity user = new UserEntity();
        user.setData(data);
        user.setEmail(email);
        user.setPassword(password);
        String ownSessionId = UUID.randomUUID().toString();
        user.setCreated(new Date());
        user.setOwnSessionId(ownSessionId);
        return create(user);
    }

    public Optional<UserEntity> findByOwnSessionId(String ownSessionId) {
        return findBy(
                getBuilder().equal(getFrom(Operation.select).get("ownSessionId"),
                        ownSessionId)
        );
    }

}
