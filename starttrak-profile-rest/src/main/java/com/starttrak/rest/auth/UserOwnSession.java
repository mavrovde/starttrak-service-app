package com.starttrak.rest.auth;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author serg.mavrov@gmail.com
 */
public class UserOwnSession extends OwnSession {

    @XmlElement(name = "profilex_exists")
    private boolean profileExists;

    public UserOwnSession(String sessionId, boolean profileExists) {
        super(sessionId);
        this.profileExists = profileExists;
    }

    public boolean isProfileExists() {
        return profileExists;
    }

    public void setProfileExists(boolean profileExists) {
        this.profileExists = profileExists;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        UserOwnSession that = (UserOwnSession) o;

        return profileExists == that.profileExists;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (profileExists ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserOwnSession{" +
                "profileExists=" + profileExists +
                '}';
    }
}
