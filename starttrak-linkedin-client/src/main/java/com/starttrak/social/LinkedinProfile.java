package com.starttrak.social;

/**
 * @author serg.mavrov@gmail.com
 */
public class LinkedinProfile implements SocialNetworkProfile {

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String position;
    private String company;
    private String pictureUrl;

    public static SocialNetworkProfile createNew(String firstName, String lastName, String emailAddress,
                                                 String position, String company, String pictureUrl) {
        return new LinkedinProfile(firstName, lastName, emailAddress,
                position, company, pictureUrl);
    }

    private LinkedinProfile(String firstName, String lastName, String emailAddress,
                            String position, String company, String pictureUrl) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.position = position;
        this.company = company;
        this.pictureUrl = pictureUrl;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }


    @Override
    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LinkedinProfile that = (LinkedinProfile) o;

        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (emailAddress != null ? !emailAddress.equals(that.emailAddress) : that.emailAddress != null) return false;
        if (position != null ? !position.equals(that.position) : that.position != null) return false;
        if (company != null ? !company.equals(that.company) : that.company != null) return false;
        return !(pictureUrl != null ? !pictureUrl.equals(that.pictureUrl) : that.pictureUrl != null);

    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (emailAddress != null ? emailAddress.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (pictureUrl != null ? pictureUrl.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LinkedinProfile{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", position='" + position + '\'' +
                ", company='" + company + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                '}';
    }
}
