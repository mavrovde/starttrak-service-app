package com.starttrak.repo;

import com.starttrak.common.SocNetwork;
import com.starttrak.jpa.NetworkEntity;
import com.starttrak.jpa.ProfileEntity;
import com.starttrak.jpa.UserEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.*;

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

    @Inject
    private UserRepo userRepo;

    @Transactional(Transactional.TxType.REQUIRED)
    private ProfileEntity create(Long networkId, String email, String firstName, String lastName,
                                 String position, String company, String appKey, UserEntity user) {
        ProfileEntity newProfile = new ProfileEntity();
        newProfile.setEmail(email);
        newProfile.setFirstName(firstName);
        newProfile.setLastName(lastName);
        newProfile.setPositionLabel(position);
        newProfile.setCompanyLabel(company);
        newProfile.setUser(user);
        NetworkEntity network = networkRepo.find(networkId).orElseThrow(IllegalStateException::new);
        newProfile.setNetwork(network);
        newProfile.setNetworkToken(appKey);
        newProfile.setLastLogin(new Date());
        return create(newProfile);
    }

    public Optional<ProfileEntity> findByEmailNetwork(SocNetwork network, String email) {
        return findBy(getBuilder().and(
                getBuilder().equal(getFrom(Operation.select).get("email"), email),
                getBuilder().equal(getFrom(Operation.select).get("network").get("id"),
                        (long) network.getCode())
        ));
    }

    public Optional<ProfileEntity> findByOwnSessionId(String ownSessionId) {
        return findBy(getBuilder().equal(getFrom(Operation.select).get("networkToken"), ownSessionId));
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public ProfileEntity createSimple(long networkId, String email, String firstName, String lastName,
                                      String appKey, UserEntity user) {
        return create(networkId, email, firstName, lastName, null, null, appKey, user);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public String updateSocialProfile(SocNetwork network, String email, String firstName, String lastName,
                                      String position, String company, String appKey) {
        Optional<ProfileEntity> linkedinProfile = findByEmailNetwork(network, email);
        UserEntity user; // we are trying to define current user
        if (!linkedinProfile.isPresent()) { //there is no linkedin profile
            Optional<UserEntity> checkUser = userRepo.findByEmail(email);
            if (checkUser.isPresent()) {//the user with the same email has been found
                // so take an user
                user = checkUser.get();
            } else {
                // create an user for starttrak
                user = userRepo.create((long) network.getCode(), email, "empty_password", "registered by social network");
                // create the starttrak profile
                create((long) SocNetwork.STTR.getCode(), email, firstName, lastName, position, company,
                        user.getOwnSessionId(), user);
            }
            // create linkedin profile
            create((long) network.getCode(), email, firstName, lastName, position, company, appKey, user);
            // -=-=-=-
        } else { // we have already linkedin profile
            linkedinProfile.get().setNetworkToken(appKey);
            linkedinProfile.get().setLastLogin(new Date());
            update(linkedinProfile.get());
            user = linkedinProfile.get().getUser();
        }
        return user.getOwnSessionId();
    }

    public List<ProfileEntity> findByConditions() {
        return new ArrayList<>(); //todo:: provide some real data
    }

}
