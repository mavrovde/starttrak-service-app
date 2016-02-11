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

    private final static long STRK_ID = 0;

    @Override
    public Class<ProfileEntity> getEntityClass() {
        return ProfileEntity.class;
    }

    @Inject
    private NetworkRepo networkRepo;

    @Inject
    private UserRepo userRepo;

    @Transactional(Transactional.TxType.REQUIRED)
    private ProfileEntity create(long networkId, String email, String name, String position, String company,
                                 String appKey, UserEntity user) {
        ProfileEntity newProfile = new ProfileEntity();
        newProfile.setEmail(email);
        newProfile.setName(name);
        newProfile.setPositionLabel(position);
        newProfile.setCompanyLabel(company);
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

    @Transactional(Transactional.TxType.REQUIRED)
    public String updateSocialProfile(long networkId, String email, String firstName, String lastName,
                                      String position, String company, String appKey) {
        Optional<ProfileEntity> linkedinProfile = findByEmailNetwork(networkId, email);
        UserEntity user; // we are trying to define current user
        if (!linkedinProfile.isPresent()) { //there is no linkedin profile
            Optional<ProfileEntity> otherProfile = findByEmail(email);
            if (!otherProfile.isPresent()) { //there is no any social profiles
                // create an user for starttrak
                user = userRepo.create(email, "empty_password", "registered by linkedin");
                // create the starttrak profile
                create(STRK_ID, email, firstName, lastName, position, company,
                        user.getOwnSessionId(), user);
            } else {
                // there was found at least one social profile, so take an user
                user = otherProfile.get().getUser();
            }
            // create linkedin profile
            create(networkId, email, firstName, lastName, position, company, appKey, user);
            // -=-=-=-
        } else { // we have already linkedin profile
            linkedinProfile.get().setNetworkToken(appKey);
            linkedinProfile.get().setLastLogin(new Date());
            update(linkedinProfile.get());
            user = linkedinProfile.get().getUser();
        }
        return user.getOwnSessionId();
    }

}
