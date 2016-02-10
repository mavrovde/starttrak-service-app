package com.starttrak.rest.request;

/**
 * @author serg.mavrov@gmail.com
 */
public class ProfileCreateRequest {

    private String name;
    private String email;
    private String phone;

    private long countryId;
    private long regionId;

    private String companyLabel;
    private long positionId;
    private long industryId;

    private long seniorityId;
    private long sizesId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public long getSizesId() {
        return sizesId;
    }

    public void setSizesId(long sizesId) {
        this.sizesId = sizesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProfileCreateRequest that = (ProfileCreateRequest) o;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (companyLabel != null ? !companyLabel.equals(that.companyLabel) : that.companyLabel != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (companyLabel != null ? companyLabel.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProfileEntity{" +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", country=" + countryId +
                ", region=" + regionId +
                ", company=" + companyLabel +
                ", position=" + positionId +
                ", industry=" + industryId +
                ", seniority=" + seniorityId +
                ", sizes=" + sizesId +
                '}';
    }

}
