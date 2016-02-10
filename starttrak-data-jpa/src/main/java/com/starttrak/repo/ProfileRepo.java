package com.starttrak.repo;

import com.starttrak.jpa.NetworkEntity;
import com.starttrak.jpa.ProfileEntity;
import com.starttrak.jpa.UserEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Date;
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

    @Inject
    private NetworkRepo networkRepo;

    private ProfileEntity create(long networkId, String email, String name, String position, String company,
                                 String appKey, UserEntity user) {
        ProfileEntity newProfile = new ProfileEntity();
        newProfile.setEmail(email);
        newProfile.setName(name);
//                    position,
//                    company,
        newProfile.setUser(user);
        NetworkEntity network = networkRepo.find(networkId).orElseThrow(IllegalStateException::new);
        newProfile.setNetwork(network);
        newProfile.setNetworkToken(appKey);
        newProfile.setLastLogin(new Date());
        return create(newProfile);
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

    public Optional<ProfileEntity> findByOwnSessionId(String ownSessionId) {
        return findBy(getBuilder().equal(getFrom(Operation.select).get("networkToken"), ownSessionId));
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public ProfileEntity create(long networkId, String email, String firstName, String lastName,
                                String position, String company, String appKey, UserEntity user) {
        return create(networkId, email, firstName + " " + lastName, position, company, appKey, user);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public ProfileEntity create(long networkId, String email, String name, String phone, long positionId,
                                String companyLabel, long countryId, long regionId, long seniorityId, long sizesId,
                                String appKey, UserEntity user) {
        return create(networkId, email, name, null, null, appKey, user);
    }

}
