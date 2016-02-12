package com.starttrak.rest.request;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author serg.mavrov@gmail.com
 */
public class RegRequest {

    /*
{soc_network_type:0, email:””, password:””}
2. Facebook (OAuth2)
{soc_network_type:1, access_token:””}
3. LinkedIn (OAuth2)
{soc_network_type:2, access_token:””}
4. Xing (OAuth1)
{soc_network_type:3, oauth_token:””, oauth_token_secret:””}
     */

    @XmlElement(name = "soc_network_id")
    private int socNetworkId;

    private String email;
    private String password;

    @XmlElement(name = "access_token")
    private String accessToken;

    @XmlElement(name = "oauth_token")
    private String oauthToken;
    @XmlElement(name = "oauth_token_secret")
    private String oauthTokenSecret;

    public int getSocNetworkId() {
        return socNetworkId;
    }

    public void setSocNetworkId(int socNetworkId) {
        this.socNetworkId = socNetworkId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getOauthToken() {
        return oauthToken;
    }

    public void setOauthToken(String oauthToken) {
        this.oauthToken = oauthToken;
    }

    public String getOauthTokenSecret() {
        return oauthTokenSecret;
    }

    public void setOauthTokenSecret(String oauthTokenSecret) {
        this.oauthTokenSecret = oauthTokenSecret;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegRequest that = (RegRequest) o;

        if (socNetworkId != that.socNetworkId) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (accessToken != null ? !accessToken.equals(that.accessToken) : that.accessToken != null) return false;
        if (oauthToken != null ? !oauthToken.equals(that.oauthToken) : that.oauthToken != null) return false;
        return !(oauthTokenSecret != null ? !oauthTokenSecret.equals(that.oauthTokenSecret) : that.oauthTokenSecret != null);

    }

    @Override
    public int hashCode() {
        int result = socNetworkId;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (accessToken != null ? accessToken.hashCode() : 0);
        result = 31 * result + (oauthToken != null ? oauthToken.hashCode() : 0);
        result = 31 * result + (oauthTokenSecret != null ? oauthTokenSecret.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RegRequest{" +
                "socNetworkId=" + socNetworkId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", oauthToken='" + oauthToken + '\'' +
                ", oauthTokenSecret='" + oauthTokenSecret + '\'' +
                '}';
    }
}
