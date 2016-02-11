package com.starttrak.rest.auth;

/**
 * @author serg.mavrov@gmail.com
 */
public class OwnSession {

    private String sessionId;

    public OwnSession(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OwnSession that = (OwnSession) o;

        return !(sessionId != null ? !sessionId.equals(that.sessionId) : that.sessionId != null);

    }

    @Override
    public int hashCode() {
        return sessionId != null ? sessionId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "OwnSession{" +
                "sessionId='" + sessionId + '\'' +
                '}';
    }
}
