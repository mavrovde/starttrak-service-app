package com.starttrak.social;

import java.util.Optional;

/**
 * @author serg.mavrov@gmail.com
 */
public abstract class DefaultProfile implements SocialNetworkProfile {

    private String firstName;
    private String lastName;
    private String emailAddress;
    private Optional<String> position;
    private Optional<String> industry;
    private Optional<String> seniority;
    private Optional<String> company;
    private Optional<String> sizes;
    private Optional<String> pictureUrl;
    private Optional<String> cityName;
    private Optional<String> region;
    private Optional<String> country;

    protected DefaultProfile(String firstName, String lastName,
                           String emailAddress,
                           Optional<String> position, Optional<String> industry,
                           Optional<String> seniority, Optional<String> company,
                           Optional<String> sizes, Optional<String> pictureUrl,
                           Optional<String> cityName, Optional<String> region, Optional<String> country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.position = position;
        this.seniority = seniority;
        this.industry = industry;
        this.company = company;
        this.sizes = sizes;
        this.pictureUrl = pictureUrl;
        this.cityName = cityName;
        this.region = region;
        this.country = country;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public Optional<String> getPosition() {
        return position;
    }

    public void setPosition(Optional<String> position) {
        this.position = position;
    }

    @Override
    public Optional<String> getIndustry() {
        return industry;
    }

    public void setIndustry(Optional<String> industry) {
        this.industry = industry;
    }

    public Optional<String> getSeniority() {
        return seniority;
    }

    public void setSeniority(Optional<String> seniority) {
        this.seniority = seniority;
    }

    @Override
    public Optional<String> getCompany() {
        return company;
    }

    public void setCompany(Optional<String> company) {
        this.company = company;
    }

    @Override
    public Optional<String> getSizes() {
        return sizes;
    }

    public void setSizes(Optional<String> sizes) {
        this.sizes = sizes;
    }

    @Override
    public Optional<String> getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(Optional<String> pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @Override
    public Optional<String> getCityName() {
        return cityName;
    }

    public void setCityName(Optional<String> cityName) {
        this.cityName = cityName;
    }

    @Override
    public Optional<String> getRegion() {
        return region;
    }

    public void setRegion(Optional<String> region) {
        this.region = region;
    }

    @Override
    public Optional<String> getCountry() {
        return country;
    }

    public void setCountry(Optional<String> country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DefaultProfile that = (DefaultProfile) o;

        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (emailAddress != null ? !emailAddress.equals(that.emailAddress) : that.emailAddress != null) return false;
        if (position != null ? !position.equals(that.position) : that.position != null) return false;
        if (industry != null ? !industry.equals(that.industry) : that.industry != null) return false;
        if (seniority != null ? !seniority.equals(that.seniority) : that.seniority != null) return false;
        if (company != null ? !company.equals(that.company) : that.company != null) return false;
        if (sizes != null ? !sizes.equals(that.sizes) : that.sizes != null) return false;
        if (pictureUrl != null ? !pictureUrl.equals(that.pictureUrl) : that.pictureUrl != null) return false;
        if (cityName != null ? !cityName.equals(that.cityName) : that.cityName != null) return false;
        if (region != null ? !region.equals(that.region) : that.region != null) return false;
        return !(country != null ? !country.equals(that.country) : that.country != null);

    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (emailAddress != null ? emailAddress.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (industry != null ? industry.hashCode() : 0);
        result = 31 * result + (seniority != null ? seniority.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (sizes != null ? sizes.hashCode() : 0);
        result = 31 * result + (pictureUrl != null ? pictureUrl.hashCode() : 0);
        result = 31 * result + (cityName != null ? cityName.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SocialNetwork{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", position=" + position +
                ", industry=" + industry +
                ", seniority=" + seniority +
                ", company=" + company +
                ", sizes=" + sizes +
                ", pictureUrl=" + pictureUrl +
                ", cityName=" + cityName +
                ", region=" + region +
                ", country=" + country +
                '}';
    }

}
