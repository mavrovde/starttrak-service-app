package com.starttrak.rest.profile;

/**
 * @author serg.mavrov@gmail.com
 */
public class SearchCondition {

    private String email;
    private String firstName;
    private String lastName;
    private long seniorityId;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public long getSeniorityId() {
        return seniorityId;
    }

    public void setSeniorityId(long seniorityId) {
        this.seniorityId = seniorityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchCondition that = (SearchCondition) o;

        if (seniorityId != that.seniorityId) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        return !(lastName != null ? !lastName.equals(that.lastName) : that.lastName != null);

    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (int) (seniorityId ^ (seniorityId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "SearchCondition{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", seniorityId=" + seniorityId +
                '}';
    }
}
