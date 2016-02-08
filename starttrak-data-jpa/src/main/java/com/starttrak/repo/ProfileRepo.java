package com.starttrak.repo;

import com.starttrak.jpa.ProfileEntity;

import javax.enterprise.context.RequestScoped;
import java.util.List;
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

    public Optional<ProfileEntity> findByEmail(String email) {
        List<ProfileEntity> allProfiles = findAllBy(Page.OPTIONAL_DEFAULT,
                getBuilder().equal(getFrom(Operation.select).get("email"), email)
        );
        return allProfiles.stream().findAny();
    }

    public Optional<ProfileEntity> findByEmailNetwork(long networkId, String email) {
        return findBy(getBuilder().and(
                getBuilder().equal(getFrom(Operation.select).get("email"), email),
                getBuilder().equal(getFrom(Operation.select).get("network").get("id"), networkId)
        ));
    }

}
