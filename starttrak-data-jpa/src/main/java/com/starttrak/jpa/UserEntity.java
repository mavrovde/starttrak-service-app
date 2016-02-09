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
    private long id;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false, columnDefinition = "DATETIME")
    private Date created;

    @Basic
    @Column(name = "data")
    private String data;

    @Basic
    @Column(name = "own_session_id")
    private String ownSessionId;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != that.id) return false;
        if (created != null ? !created.equals(that.created) : that.created != null) return false;
        if (data != null ? !data.equals(that.data) : that.data != null) return false;
        if (ownSessionId != null ? !ownSessionId.equals(that.ownSessionId) : that.ownSessionId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (ownSessionId != null ? ownSessionId.hashCode() : 0);
        return result;
    }
}
