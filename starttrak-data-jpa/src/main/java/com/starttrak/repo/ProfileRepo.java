package com.starttrak.repo;

import com.starttrak.common.SocNetwork;
import com.starttrak.jpa.*;
import org.jboss.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.*;

/**
 * @author serg.mavrov@gmail.com
 */
@RequestScoped
public class ProfileRepo extends AbstractRepository<ProfileEntity> {

    private final static Logger logger = Logger.getLogger(ProfileRepo.class);

    @Override
    public Class<ProfileEntity> getEntityClass() {
        return ProfileEntity.class;
    }

    @Inject
    private NetworkRepo networkRepo;

    @Inject
    private UserRepo userRepo;

    @Inject
    private TitleRepo titleRepo;
    @Inject
    private CountryRepo countryRepo;
    @Inject
    private RegionRepo regionRepo;
    @Inject
    private SizeRepo sizeRepo;
    @Inject
    private SeniorityRepo seniorityRepo;
    @Inject
    private PositionRepo positionRepo;
    @Inject
    private IndustryRepo industryRepo;

    @Transactional(Transactional.TxType.REQUIRED)
    private ProfileEntity createByLabels(Long networkId, String email, String firstName, String lastName,
                                         Optional<String> position,
                                         Optional<String> company,
                                         Optional<String> pictureUrl,
                                         Optional<String> cityLabel,
                                         Optional<String> regionLabel,
                                         Optional<String> countryLabel,
                                         String appKey, UserEntity user) {
        logger.info("geo -> " + cityLabel + ", " + regionLabel + ", " + countryLabel);
        NetworkEntity network = networkRepo.find(networkId).orElseThrow(IllegalArgumentException::new);
        ProfileEntity newProfile = new ProfileEntity();
        newProfile.setEmail(email);
        newProfile.setFirstName(firstName);
        newProfile.setLastName(lastName);
        position.ifPresent(newProfile::setPositionLabel);
//        newProfile.setPositionLabel(position.get());
        company.ifPresent(newProfile::setCompanyLabel);
//        newProfile.setCompanyLabel(company.get());
        pictureUrl.ifPresent(newProfile::setPhotoUrl);
//        newProfile.setPhotoUrl(pictureUrl.get());
        //geo
        cityLabel.ifPresent(newProfile::setCityLabel);
        regionLabel.ifPresent(label ->{
            Optional<RegionEntity> region = regionRepo.findByLabel(label);
            region.ifPresent(r -> {
                newProfile.setRegion(r);
                newProfile.setRegionLabel(r.getLabel());
            });
        });
        countryLabel.ifPresent(label ->{
            Optional<CountryEntity> country = countryRepo.findByLabel(label);
            country.ifPresent(c -> {
                newProfile.setCountry(c);
                newProfile.setCountryLabel(c.getLabel());
            });
        });
        //-=-
        newProfile.setUser(user);
        newProfile.setNetwork(network);
        newProfile.setNetworkToken(appKey);
        newProfile.setLastLogin(new Date());
        return create(newProfile);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public ProfileEntity createByIds(Long networkId, String email, String firstName, String lastName,
                                     Optional<Long> positionId,
                                     Optional<String> company,
                                     Optional<String> pictureUrl,
                                     Optional<String> cityName,
                                     Optional<Long> regionId,
                                     Optional<Long> countryId,
                                     String appKey, UserEntity user) {
        NetworkEntity network = networkRepo.find(networkId).orElseThrow(IllegalArgumentException::new);
        ProfileEntity newProfile = new ProfileEntity();
        newProfile.setEmail(email);
        newProfile.setFirstName(firstName);
        newProfile.setLastName(lastName);

        positionId.ifPresent(id->{
                    PositionEntity position = positionRepo.find(id).
                            orElseThrow(IllegalArgumentException::new);
                    newProfile.setPosition(position);
                    newProfile.setPositionLabel(position.getLabel());
                }
        );

        company.ifPresent(newProfile::setCompanyLabel);
//        newProfile.setCompanyLabel(company.get());
        pictureUrl.ifPresent(newProfile::setPhotoUrl);
//        newProfile.setPhotoUrl(pictureUrl.get());
        //geo
        cityName.ifPresent(newProfile::setCityLabel);
        regionId.ifPresent(id -> {
            RegionEntity region = regionRepo.find(id).
                    orElseThrow(IllegalArgumentException::new);
            newProfile.setRegion(region);
            newProfile.setRegionLabel(region.getLabel());
        });
        countryId.ifPresent(id -> {
            CountryEntity country = countryRepo.find(id).
                    orElseThrow(IllegalArgumentException::new);
            newProfile.setCountry(country);
            newProfile.setCountryLabel(country.getLabel());
        });
        //-=-
        newProfile.setUser(user);
        newProfile.setNetwork(network);
        newProfile.setNetworkToken(appKey);
        newProfile.setLastLogin(new Date());
        return create(newProfile);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void update(String ownSessionId,
                       Long titleId,
                       String firstName, String lastName,
                       String phone,
                       Long positionId,
                       String companyLabel,
                       Long countryId, Long regionId, String cityLabel,
                       Long seniorityId,
                       Long sizeId,
                       Long industryId) {

        ProfileEntity profile = findByOwnSessionId(ownSessionId).orElseThrow(IllegalArgumentException::new);

        profile.setTitle(null);
        Optional.ofNullable(titleId).ifPresent(id ->
                        profile.setTitle(titleRepo.find(id).
                                orElseThrow(IllegalArgumentException::new))
        );

        profile.setFirstName(null);
        Optional.ofNullable(firstName).ifPresent(profile::setFirstName);

        profile.setLastName(null);
        Optional.ofNullable(lastName).ifPresent(profile::setLastName);

        profile.setPhone(null);
        Optional.ofNullable(phone).ifPresent(profile::setPhone);

        profile.setPosition(null);
        Optional.ofNullable(positionId).ifPresent(id ->
                        profile.setPosition(positionRepo.find(id).
                                orElseThrow(IllegalArgumentException::new))
        );

        profile.setCompanyLabel(null);
        Optional.ofNullable(companyLabel).ifPresent(profile::setCompanyLabel);

        profile.setCountry(null);
        Optional.ofNullable(countryId).ifPresent(id ->
                        profile.setCountry(countryRepo.find(id).
                                orElseThrow(IllegalArgumentException::new))
        );

        profile.setRegion(null);
        Optional.ofNullable(regionId).ifPresent(id ->
                        profile.setRegion(regionRepo.find(id).
                                orElseThrow(IllegalArgumentException::new))
        );

        profile.setCityLabel(null);
        Optional.ofNullable(cityLabel).ifPresent(profile::setCityLabel);

        profile.setSeniority(null);
        Optional.ofNullable(seniorityId).ifPresent(id ->
                        profile.setSeniority(seniorityRepo.find(id).
                                orElseThrow(IllegalArgumentException::new))
        );

        profile.setSizes(null);
        Optional.ofNullable(sizeId).ifPresent(id ->
                        profile.setSizes(sizeRepo.find(id).
                                orElseThrow(IllegalArgumentException::new))

        );

        profile.setIndustry(null);
        Optional.ofNullable(industryId).ifPresent(id ->
                        profile.setIndustry(industryRepo.find(id).
                                orElseThrow(IllegalArgumentException::new))
        );
        update(profile);
    }

    public Optional<ProfileEntity> findByEmailNetwork(SocNetwork network, String email) {
        return findBy(getBuilder().and(
                getBuilder().equal(getFrom(Operation.select).get("email"), email),
                getBuilder().equal(getFrom(Operation.select).get("network").get("id"),
                        (long) network.getCode())
        ));
    }

    public List<ProfileEntity> findAllByNetwork(Optional<Page> page, SocNetwork network) {
        return findAllBy(page, getBuilder().equal(getFrom(Operation.select).get("network").get("id"),
                (long) network.getCode()));
    }

    public Optional<ProfileEntity> findByOwnSessionId(String ownSessionId) {
        return findBy(getBuilder().equal(getFrom(Operation.select).get("networkToken"), ownSessionId));
    }

//    @Transactional(Transactional.TxType.REQUIRED)
//    public ProfileEntity createSimple(long networkId, String email, String firstName, String lastName,
//                                      String appKey, UserEntity user) {
//        return create(networkId, email, firstName, lastName, null, null, null,
//                null, null, null, appKey, user);
//    }

    @Transactional(Transactional.TxType.REQUIRED)
    public String updateSocialProfile(SocNetwork network, String email, String firstName, String lastName,
                                      Optional<String> position,
                                      Optional<String> company,
                                      Optional<String> pictureUrl,
                                      Optional<String> cityName,
                                      Optional<String> region,
                                      Optional<String> country,
                                      String appKey) {
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
                createByLabels((long) SocNetwork.STTR.getCode(), email, firstName, lastName,
                        position, company,
                        pictureUrl, cityName, region, country,
                        user.getOwnSessionId(), user);
            }
            // create linkedin profile
            createByLabels((long) network.getCode(), email, firstName, lastName, position, company,
                    pictureUrl, cityName, region, country,
                    appKey, user);
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
