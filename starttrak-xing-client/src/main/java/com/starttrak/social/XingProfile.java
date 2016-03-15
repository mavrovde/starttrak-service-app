package com.starttrak.social;

import java.util.Optional;

public class XingProfile extends DefaultProfile {
            
    
    public static class Builder{
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
        
        public Builder firstName(String v){
        	this.firstName = v;
        	return this;
        }
        
        public Builder lastName(String v){
        	this.lastName = v;
        	return this;
        }
        
        public Builder emailAddress(String v){
        	this.emailAddress = v;
        	return this;
        }
        
        public Builder position(Optional<String> v){
        	this.position = v;
        	return this;
        }
        
        public Builder industry(Optional<String> v){
        	this.industry = v;
        	return this;
        }
        
        public Builder seniority(Optional<String> v){
        	this.seniority = v;
        	return this;
        }
        
        public Builder company(Optional<String> v){
        	this.company = v;
        	return this;
        }
        
        public Builder sizes(Optional<String> v){
        	this.sizes = v;
        	return this;
        }
        
        public Builder pictureUrl(Optional<String> v){
        	this.pictureUrl = v;
        	return this;
        }
        
        public Builder cityName(Optional<String> v){
        	this.cityName = v;
        	return this;
        }
        
        public Builder region(Optional<String> v){
        	this.region = v;
        	return this;
        }
        
        public Builder country(Optional<String> v){
        	this.country = v;
        	return this;
        }
        
        public XingProfile build(){
        	XingProfile p = new XingProfile();
            p.setFirstName(firstName);
            p.setLastName(lastName);
            p.setEmailAddress(emailAddress);
            p.setPosition(position);
            p.setIndustry(industry);
            p.setSeniority(seniority);
            p.setCompany(company);
            p.setSizes(sizes);
            p.setPictureUrl(pictureUrl);
            p.setCityName(cityName);
            p.setRegion(region);
            p.setCountry(country);
            return p;
        	        	
        }
    }
    

}
