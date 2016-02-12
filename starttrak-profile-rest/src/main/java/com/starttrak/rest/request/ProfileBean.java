package com.starttrak.rest.request;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author serg.mavrov@gmail.com
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
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

    @XmlElement(name = "phone_number", nillable = true)
    private String phone;

    @XmlElement(name = "country_id", nillable = true)
    private Long countryId;
    @XmlElement(name = "state_id", nillable = true)
    private Long regionId;

    @XmlElement(name = "company_name", nillable = true)
    private String companyLabel;

    @XmlElement(name = "position_id", nillable = true)
    private Long positionId;
    @XmlElement(name = "industry_id", nillable = true)
    private Long industryId;

    @XmlElement(name = "seniority_id", nillable = true)
    private Long seniorityId;

    @XmlElement(name = "size_id", nillable = true)
    private Long sizeId;

    public ProfileBean() {
    }

    public ProfileBean(String firstName, String lastName, String email, String phone, Long countryId,
                       Long regionId, String companyLabel, Long positionId, Long industryId,
                       Long seniorityId, Long sizeId) {
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

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getCompanyLabel() {
        return companyLabel;
    }

    public void setCompanyLabel(String companyLabel) {
        this.companyLabel = companyLabel;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public Long getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Long industryId) {
        this.industryId = industryId;
    }

    public Long getSeniorityId() {
        return seniorityId;
    }

    public void setSeniorityId(Long seniorityId) {
        this.seniorityId = seniorityId;
    }

    public Long getSizeId() {
        return sizeId;
    }

    public void setSizeId(Long sizeId) {
        this.sizeId = sizeId;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProfileBean that = (ProfileBean) o;

        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (countryId != null ? !countryId.equals(that.countryId) : that.countryId != null) return false;
        if (regionId != null ? !regionId.equals(that.regionId) : that.regionId != null) return false;
        if (companyLabel != null ? !companyLabel.equals(that.companyLabel) : that.companyLabel != null) return false;
        if (positionId != null ? !positionId.equals(that.positionId) : that.positionId != null) return false;
        if (industryId != null ? !industryId.equals(that.industryId) : that.industryId != null) return false;
        if (seniorityId != null ? !seniorityId.equals(that.seniorityId) : that.seniorityId != null) return false;
        return !(sizeId != null ? !sizeId.equals(that.sizeId) : that.sizeId != null);

    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (countryId != null ? countryId.hashCode() : 0);
        result = 31 * result + (regionId != null ? regionId.hashCode() : 0);
        result = 31 * result + (companyLabel != null ? companyLabel.hashCode() : 0);
        result = 31 * result + (positionId != null ? positionId.hashCode() : 0);
        result = 31 * result + (industryId != null ? industryId.hashCode() : 0);
        result = 31 * result + (seniorityId != null ? seniorityId.hashCode() : 0);
        result = 31 * result + (sizeId != null ? sizeId.hashCode() : 0);
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
