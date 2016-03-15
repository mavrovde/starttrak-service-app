package com.starttrak.social;

import java.util.Optional;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author serg.mavrov@gmail.com <br> 
 * Alexander Babin (alexander.babin@gmail.com)
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
    
    public DefaultProfile(){}

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

        return new EqualsBuilder()
                .append(firstName, that.getFirstName())
                .append(lastName, that.getLastName())
                .append(emailAddress, that.getEmailAddress())
                .append(position, that.getPosition())
                .append(industry, that.getIndustry())
                .append(seniority, that.getSeniority())
                .append(company, that.getCompany())
                .append(sizes, that.getSizes())
                .append(pictureUrl, that.getPictureUrl())
                .append(cityName, that.getCityName())
                .append(region, that.getRegion())
                .append(country, that.getCountry())
                .isEquals();
    }

    @Override
    public int hashCode() {
    	return new HashCodeBuilder()
		        .append(firstName)
		        .append(lastName)
		        .append(emailAddress)
		        .append(position)
		        .append(industry)
		        .append(seniority)
		        .append(company)
		        .append(sizes)
		        .append(pictureUrl)
		        .append(cityName)
		        .append(region)
		        .append(country)
		        .toHashCode();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
	        /*
        	.append(firstName)
	        .append(lastName)
	        .append(emailAddress)
	        .append(position)
	        .append(industry)
	        .append(seniority)
	        .append(company)
	        .append(sizes)
	        .append(pictureUrl)
	        .append(cityName)
	        .append(region)
	        .append(country)
	        .toString();
	        */
    }

    
    
}
