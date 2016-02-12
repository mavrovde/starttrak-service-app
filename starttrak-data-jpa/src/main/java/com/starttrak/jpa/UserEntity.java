package com.starttrak.jpa;

import javax.persistence.*;
import java.util.Date;

/**
 * @author serg.mavrov@gmail.com
 */
@Entity
@Table(name = "users", schema = "starttrak")
public class UserEntity extends AbstractEntity implements StandardEntity {

    @TableGenerator(name = "entity_id_generator",
            table = "entity_ids",
            schema = "starttrak",
            pkColumnName = "gen_name",
            pkColumnValue = "user_entity",
            valueColumnName = "gen_value",
            initialValue = 0,
            allocationSize = 10
    )
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "entity_id_generator")
    private Long id;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false, columnDefinition = "DATETIME")
    private Date created;

    @Basic
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "data")
    private String data;

    @Basic
    @Column(name = "own_session_id")
    private String ownSessionId;

    @ManyToOne(optional = true, cascade = CascadeType.MERGE)
    @JoinColumn(name = "source_network_id", nullable = true)
    private NetworkEntity sourceNetwork;

    @Basic
    @Column(name = "password")
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


    public String getOwnSessionId() {
        return ownSessionId;
    }

    public void setOwnSessionId(String ownSessionId) {
        this.ownSessionId = ownSessionId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public NetworkEntity getSourceNetwork() {
        return sourceNetwork;
    }

    public void setSourceNetwork(NetworkEntity sourceNetwork) {
        this.sourceNetwork = sourceNetwork;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (created != null ? !created.equals(that.created) : that.created != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (data != null ? !data.equals(that.data) : that.data != null) return false;
        if (ownSessionId != null ? !ownSessionId.equals(that.ownSessionId) : that.ownSessionId != null) return false;
        if (sourceNetwork != null ? !sourceNetwork.equals(that.sourceNetwork) : that.sourceNetwork != null)
            return false;
        return !(password != null ? !password.equals(that.password) : that.password != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (ownSessionId != null ? ownSessionId.hashCode() : 0);
        result = 31 * result + (sourceNetwork != null ? sourceNetwork.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", created=" + created +
                ", email='" + email + '\'' +
                ", data='" + data + '\'' +
                ", ownSessionId='" + ownSessionId + '\'' +
                ", sourceNetwork=" + sourceNetwork +
                ", password='" + password + '\'' +
                '}';
    }
}
