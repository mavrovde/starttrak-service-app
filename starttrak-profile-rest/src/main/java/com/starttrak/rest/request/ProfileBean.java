package com.starttrak.rest.request;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author serg.mavrov@gmail.com
 */
public class ProfileBean {

    /*
Mandatory fields:
email

Optional fields:
first_name
last_name
phone_number
company_name
title_id (from dictionaries)
seniority_id
industry_id
country_id
state_id (only for USA)
     */

    @XmlElement(name = "first_name")
    private String firstName;
    @XmlElement(name = "last_name")
    private String lastName;

    private String email;
    @XmlElement(name = "phone_number")
    private String phone;

    @XmlElement(name = "country_id", nillable = true)
    private long countryId;
    @XmlElement(name = "state_id")
    private long regionId;

    @XmlElement(name = "company_name")
    private String companyLabel;

    @XmlElement(name = "position_id")
    private long positionId;
    @XmlElement(name = "industry_id")
    private long industryId;

    @XmlElement(name = "seniority_id")
    private long seniorityId;

    @XmlElement(name = "size_id")
    private long sizeId;


    public ProfileBean(String firstName, String lastName, String email, String phone, long countryId,
                       long regionId, String companyLabel, long positionId, long industryId,
                       long seniorityId, long sizeId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.countryId = countryId;
        this.regionId = regionId;
        this.companyLabel = companyLabel;
        this.positionId = positionId;
        this.industryId = industryId;
        this.seniorityId = seniorityId;
        this.sizeId = sizeId;
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

    public long getCountryId() {
        return countryId;
    }

    public void setCountryId(long countryId) {
        this.countryId = countryId;
    }

    public long getRegionId() {
        return regionId;
    }

    public void setRegionId(long regionId) {
        this.regionId = regionId;
    }

    public String getCompanyLabel() {
        return companyLabel;
    }

    public void setCompanyLabel(String companyLabel) {
        this.companyLabel = companyLabel;
    }

    public long getPositionId() {
        return positionId;
    }

    public void setPositionId(long positionId) {
        this.positionId = positionId;
    }

    public long getIndustryId() {
        return industryId;
    }

    public void setIndustryId(long industryId) {
        this.industryId = industryId;
    }

    public long getSeniorityId() {
        return seniorityId;
    }

    public void setSeniorityId(long seniorityId) {
        this.seniorityId = seniorityId;
    }

    public long getSizeId() {
        return sizeId;
    }

    public void setSizeId(long sizeId) {
        this.sizeId = sizeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProfileBean that = (ProfileBean) o;

        if (countryId != that.countryId) return false;
        if (regionId != that.regionId) return false;
        if (positionId != that.positionId) return false;
        if (industryId != that.industryId) return false;
        if (seniorityId != that.seniorityId) return false;
        if (sizeId != that.sizeId) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        return !(companyLabel != null ? !companyLabel.equals(that.companyLabel) : that.companyLabel != null);

    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (int) (countryId ^ (countryId >>> 32));
        result = 31 * result + (int) (regionId ^ (regionId >>> 32));
        result = 31 * result + (companyLabel != null ? companyLabel.hashCode() : 0);
        result = 31 * result + (int) (positionId ^ (positionId >>> 32));
        result = 31 * result + (int) (industryId ^ (industryId >>> 32));
        result = 31 * result + (int) (seniorityId ^ (seniorityId >>> 32));
        result = 31 * result + (int) (sizeId ^ (sizeId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "ProfileBean{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", countryId=" + countryId +
                ", regionId=" + regionId +
                ", companyLabel='" + companyLabel + '\'' +
                ", positionId=" + positionId +
                ", industryId=" + industryId +
                ", seniorityId=" + seniorityId +
                ", sizeId=" + sizeId +
                '}';
    }

}
