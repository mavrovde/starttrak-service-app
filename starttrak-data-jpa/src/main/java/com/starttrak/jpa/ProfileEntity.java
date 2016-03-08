package com.starttrak.jpa;

import javax.persistence.*;
import java.util.Date;

/**
 * @author serg.mavrov@gmail.com
 */
@Entity
@Table(name = "profiles", schema = "starttrak")
public class ProfileEntity extends AbstractEntity implements StandardEntity, Comparable<ProfileEntity> {

    @TableGenerator(name = "entity_id_generator_profiles",
            table = "entity_ids",
            schema = "starttrak",
            pkColumnName = "gen_name",
            pkColumnValue = "profile_entity",
            valueColumnName = "gen_value",
            initialValue = 0,
            allocationSize = 10
    )
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "entity_id_generator_profiles")
    private Long id;

    @ManyToOne(optional = true)
    @JoinColumn(name = "title_id", nullable = true)
    private TitleEntity title;

    @Basic
    @Column(name = "title_label", nullable = true)
    private String titleLabel;

    @Basic
    @Column(name = "first_name", nullable = true)
    private String firstName;

    @Basic
    @Column(name = "last_name", nullable = true)
    private String lastName;

    @Basic
    @Column(name = "email", nullable = false)
    private String email;

    @Basic
    @Column(name = "phone", nullable = true)
    private String phone;

    @ManyToOne(optional = true)
    @JoinColumn(name = "country_id", nullable = true)
    private CountryEntity country;

    @Basic
    @Column(name = "country_label", nullable = true)
    private String countryLabel;

    @ManyToOne(optional = true)
    @JoinColumn(name = "region_id", nullable = true)
    private RegionEntity region;

    @Basic
    @Column(name = "region_label", nullable = true)
    private String regionLabel;

    @Basic
    @Column(name = "city_label", nullable = true)
    private String cityLabel;

    @ManyToOne(optional = true)
    @JoinColumn(name = "company_id", nullable = true)
    private CompanyEntity company;

    @Basic
    @Column(name = "company_label", nullable = true)
    private String companyLabel;


    @ManyToOne(optional = true)
    @JoinColumn(name = "position_id", nullable = true)
    private PositionEntity position;

    @Basic
    @Column(name = "position_label", nullable = true)
    private String positionLabel;


    @ManyToOne(optional = true)
    @JoinColumn(name = "industry_id", nullable = true)
    private IndustryEntity industry;

    @Basic
    @Column(name = "industry_label", nullable = true)
    private String industryLabel;


    @ManyToOne(optional = true)
    @JoinColumn(name = "seniority_id", nullable = true)
    private SeniorityEntity seniority;

    @Basic
    @Column(name = "seniority_label", nullable = true)
    private String seniorityLabel;


    @ManyToOne(optional = true)
    @JoinColumn(name = "sizes_id", nullable = true)
    private SizeEntity sizes;

    @Basic
    @Column(name = "sizes_label", nullable = true)
    private String sizesLabel;

    @Basic
    @Column(name = "sizes_min_value", nullable = true)
    private int sizesMinValue;

    @Basic
    @Column(name = "sizes_max_value", nullable = true)
    private int sizesMaxValue;

    @Basic
    @Column(name = "photo_url", nullable = true)
    private String photoUrl;

    @ManyToOne(optional = true, cascade = CascadeType.MERGE)
    @JoinColumn(name = "network_id", nullable = true)
    private NetworkEntity network;

    @Basic
    @Column(name = "network_token", nullable = false)
    private String networkToken;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "last_login", nullable = false, columnDefinition = "DATETIME")
    private Date lastLogin;

    @ManyToOne(optional = true)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TitleEntity getTitle() {
        return title;
    }

    public void setTitle(TitleEntity title) {
        this.title = title;
    }

    public String getTitleLabel() {
        return titleLabel;
    }

    public void setTitleLabel(String titleLabel) {
        this.titleLabel = titleLabel;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getCountryLabel() {
        return countryLabel;
    }

    public void setCountryLabel(String countryLabel) {
        this.countryLabel = countryLabel;
    }


    public String getRegionLabel() {
        return regionLabel;
    }

    public void setRegionLabel(String regionLabel) {
        this.regionLabel = regionLabel;
    }


    public String getCompanyLabel() {
        return companyLabel;
    }

    public void setCompanyLabel(String companyLabel) {
        this.companyLabel = companyLabel;
    }


    public String getPositionLabel() {
        return positionLabel;
    }

    public void setPositionLabel(String positionLabel) {
        this.positionLabel = positionLabel;
    }


    public String getIndustryLabel() {
        return industryLabel;
    }

    public void setIndustryLabel(String industryLabel) {
        this.industryLabel = industryLabel;
    }


    public String getSeniorityLabel() {
        return seniorityLabel;
    }

    public void setSeniorityLabel(String seniorityLabel) {
        this.seniorityLabel = seniorityLabel;
    }


    public String getSizesLabel() {
        return sizesLabel;
    }

    public void setSizesLabel(String sizesLabel) {
        this.sizesLabel = sizesLabel;
    }


    public int getSizesMinValue() {
        return sizesMinValue;
    }

    public void setSizesMinValue(int sizesMinValue) {
        this.sizesMinValue = sizesMinValue;
    }


    public int getSizesMaxValue() {
        return sizesMaxValue;
    }

    public void setSizesMaxValue(int sizesMaxValue) {
        this.sizesMaxValue = sizesMaxValue;
    }


    public String getNetworkToken() {
        return networkToken;
    }

    public void setNetworkToken(String networkToken) {
        this.networkToken = networkToken;
    }

    public CountryEntity getCountry() {
        return country;
    }

    public void setCountry(CountryEntity country) {
        this.country = country;
    }

    public RegionEntity getRegion() {
        return region;
    }

    public void setRegion(RegionEntity region) {
        this.region = region;
    }

    public String getCityLabel() {
        return cityLabel;
    }

    public void setCityLabel(String cityLabel) {
        this.cityLabel = cityLabel;
    }

    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }

    public PositionEntity getPosition() {
        return position;
    }

    public void setPosition(PositionEntity position) {
        this.position = position;
    }

    public IndustryEntity getIndustry() {
        return industry;
    }

    public void setIndustry(IndustryEntity industry) {
        this.industry = industry;
    }

    public SeniorityEntity getSeniority() {
        return seniority;
    }

    public void setSeniority(SeniorityEntity seniority) {
        this.seniority = seniority;
    }

    public SizeEntity getSizes() {
        return sizes;
    }

    public void setSizes(SizeEntity sizes) {
        this.sizes = sizes;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public NetworkEntity getNetwork() {
        return network;
    }

    public void setNetwork(NetworkEntity network) {
        this.network = network;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProfileEntity that = (ProfileEntity) o;

        if (sizesMinValue != that.sizesMinValue) return false;
        if (sizesMaxValue != that.sizesMaxValue) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (titleLabel != null ? !titleLabel.equals(that.titleLabel) : that.titleLabel != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (countryLabel != null ? !countryLabel.equals(that.countryLabel) : that.countryLabel != null) return false;
        if (region != null ? !region.equals(that.region) : that.region != null) return false;
        if (regionLabel != null ? !regionLabel.equals(that.regionLabel) : that.regionLabel != null) return false;
        if (cityLabel != null ? !cityLabel.equals(that.cityLabel) : that.cityLabel != null) return false;
        if (company != null ? !company.equals(that.company) : that.company != null) return false;
        if (companyLabel != null ? !companyLabel.equals(that.companyLabel) : that.companyLabel != null) return false;
        if (position != null ? !position.equals(that.position) : that.position != null) return false;
        if (positionLabel != null ? !positionLabel.equals(that.positionLabel) : that.positionLabel != null)
            return false;
        if (industry != null ? !industry.equals(that.industry) : that.industry != null) return false;
        if (industryLabel != null ? !industryLabel.equals(that.industryLabel) : that.industryLabel != null)
            return false;
        if (seniority != null ? !seniority.equals(that.seniority) : that.seniority != null) return false;
        if (seniorityLabel != null ? !seniorityLabel.equals(that.seniorityLabel) : that.seniorityLabel != null)
            return false;
        if (sizes != null ? !sizes.equals(that.sizes) : that.sizes != null) return false;
        if (sizesLabel != null ? !sizesLabel.equals(that.sizesLabel) : that.sizesLabel != null) return false;
        if (photoUrl != null ? !photoUrl.equals(that.photoUrl) : that.photoUrl != null) return false;
        if (network != null ? !network.equals(that.network) : that.network != null) return false;
        if (networkToken != null ? !networkToken.equals(that.networkToken) : that.networkToken != null) return false;
        if (lastLogin != null ? !lastLogin.equals(that.lastLogin) : that.lastLogin != null) return false;
        return !(user != null ? !user.equals(that.user) : that.user != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (titleLabel != null ? titleLabel.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (countryLabel != null ? countryLabel.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (regionLabel != null ? regionLabel.hashCode() : 0);
        result = 31 * result + (cityLabel != null ? cityLabel.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (companyLabel != null ? companyLabel.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (positionLabel != null ? positionLabel.hashCode() : 0);
        result = 31 * result + (industry != null ? industry.hashCode() : 0);
        result = 31 * result + (industryLabel != null ? industryLabel.hashCode() : 0);
        result = 31 * result + (seniority != null ? seniority.hashCode() : 0);
        result = 31 * result + (seniorityLabel != null ? seniorityLabel.hashCode() : 0);
        result = 31 * result + (sizes != null ? sizes.hashCode() : 0);
        result = 31 * result + (sizesLabel != null ? sizesLabel.hashCode() : 0);
        result = 31 * result + sizesMinValue;
        result = 31 * result + sizesMaxValue;
        result = 31 * result + (photoUrl != null ? photoUrl.hashCode() : 0);
        result = 31 * result + (network != null ? network.hashCode() : 0);
        result = 31 * result + (networkToken != null ? networkToken.hashCode() : 0);
        result = 31 * result + (lastLogin != null ? lastLogin.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProfileEntity{" +
                "id=" + id +
                ", title=" + title +
                ", titleLabel='" + titleLabel + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", country=" + country +
                ", countryLabel='" + countryLabel + '\'' +
                ", region=" + region +
                ", regionLabel='" + regionLabel + '\'' +
                ", cityLabel='" + cityLabel + '\'' +
                ", company=" + company +
                ", companyLabel='" + companyLabel + '\'' +
                ", position=" + position +
                ", positionLabel='" + positionLabel + '\'' +
                ", industry=" + industry +
                ", industryLabel='" + industryLabel + '\'' +
                ", seniority=" + seniority +
                ", seniorityLabel='" + seniorityLabel + '\'' +
                ", sizes=" + sizes +
                ", sizesLabel='" + sizesLabel + '\'' +
                ", sizesMinValue=" + sizesMinValue +
                ", sizesMaxValue=" + sizesMaxValue +
                ", photoUrl='" + photoUrl + '\'' +
                ", network=" + network +
                ", networkToken='" + networkToken + '\'' +
                ", lastLogin=" + lastLogin +
                ", user=" + user +
                '}';
    }

    @Override
    public int compareTo(ProfileEntity o) {
        return (getFirstName() + getLastName()).compareTo((o.getFirstName()+o.getLastName()));
    }
}


