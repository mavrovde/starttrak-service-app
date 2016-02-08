package com.starttrak.jpa;

import javax.persistence.*;

/**
 * @author serg.mavrov@gmail.com
 */
@Entity
@Table(name = "regions", schema = "starttrak")
public class RegionEntity extends AbstractEntity implements StandardEntity {

    @TableGenerator(name = "entity_id_generator",
            table = "entity_ids",
            schema = "starttrak",
            pkColumnName = "gen_name",
            pkColumnValue = "region_entity",
            valueColumnName = "gen_value",
            initialValue = 0,
            allocationSize = 10
    )
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "entity_id_generator")
    private long id;

    @Basic
    @Column(name = "label")
    private String label;

    @ManyToOne(optional = false)
    @JoinColumn(name = "country_id", nullable = false)
    private CountryEntity country;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public CountryEntity getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegionEntity that = (RegionEntity) o;

        if (id != that.id) return false;
        if (label != null ? !label.equals(that.label) : that.label != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (label != null ? label.hashCode() : 0);
        return result;
    }
}
