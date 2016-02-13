package com.starttrak.rest.profile;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author serg.mavrov@gmail.com
 */
public class SearchRequest {

    /*
    {
search_text : “tesxt”,
filter :
{
company_size_id:<...>,
seniority_id:<...>,
industry_id:<...>,
country_id:<...>,
position_id:<...>,
}}
     */

    @XmlElement(name = "search_text")
    private String text;

    @XmlElement(name = "phone", nillable = true)
    private String phone;

    @XmlElement(name = "title_id", nillable = true)
    private Long titleId;

    @XmlElement(name = "country_id", nillable = true)
    private Long countryId;
    @XmlElement(name = "state_id", nillable = true)
    private Long regionId;

    @XmlElement(name = "city_name", nillable = true)
    private String cityLabel;

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getTitleId() {
        return titleId;
    }

    public void setTitleId(Long titleId) {
        this.titleId = titleId;
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

    public String getCityLabel() {
        return cityLabel;
    }

    public void setCityLabel(String cityLabel) {
        this.cityLabel = cityLabel;
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

        SearchRequest that = (SearchRequest) o;

        if (text != null ? !text.equals(that.text) : that.text != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (titleId != null ? !titleId.equals(that.titleId) : that.titleId != null) return false;
        if (countryId != null ? !countryId.equals(that.countryId) : that.countryId != null) return false;
        if (regionId != null ? !regionId.equals(that.regionId) : that.regionId != null) return false;
        if (cityLabel != null ? !cityLabel.equals(that.cityLabel) : that.cityLabel != null) return false;
        if (companyLabel != null ? !companyLabel.equals(that.companyLabel) : that.companyLabel != null) return false;
        if (positionId != null ? !positionId.equals(that.positionId) : that.positionId != null) return false;
        if (industryId != null ? !industryId.equals(that.industryId) : that.industryId != null) return false;
        if (seniorityId != null ? !seniorityId.equals(that.seniorityId) : that.seniorityId != null) return false;
        return !(sizeId != null ? !sizeId.equals(that.sizeId) : that.sizeId != null);

    }

    @Override
    public int hashCode() {
        int result = text != null ? text.hashCode() : 0;
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (titleId != null ? titleId.hashCode() : 0);
        result = 31 * result + (countryId != null ? countryId.hashCode() : 0);
        result = 31 * result + (regionId != null ? regionId.hashCode() : 0);
        result = 31 * result + (cityLabel != null ? cityLabel.hashCode() : 0);
        result = 31 * result + (companyLabel != null ? companyLabel.hashCode() : 0);
        result = 31 * result + (positionId != null ? positionId.hashCode() : 0);
        result = 31 * result + (industryId != null ? industryId.hashCode() : 0);
        result = 31 * result + (seniorityId != null ? seniorityId.hashCode() : 0);
        result = 31 * result + (sizeId != null ? sizeId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SearchRequest{" +
                "text='" + text + '\'' +
                ", phone='" + phone + '\'' +
                ", titleId=" + titleId +
                ", countryId=" + countryId +
                ", regionId=" + regionId +
                ", cityLabel='" + cityLabel + '\'' +
                ", companyLabel='" + companyLabel + '\'' +
                ", positionId=" + positionId +
                ", industryId=" + industryId +
                ", seniorityId=" + seniorityId +
                ", sizeId=" + sizeId +
                '}';
    }
}
