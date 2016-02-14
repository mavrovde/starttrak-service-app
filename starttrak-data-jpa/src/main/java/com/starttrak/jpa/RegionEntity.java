package com.starttrak.jpa;

import javax.persistence.*;

/**
 * @author serg.mavrov@gmail.com
 */
@Entity
@Table(name = "regions", schema = "starttrak")
public class RegionEntity extends AbstractEntity implements StandardEntity, Labeled {

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
    private Long id;

    @Basic
    @Column(name = "label")
    private String label;

    @ManyToOne(optional = false)
    @JoinColumn(name = "country_id", nullable = false)
    private CountryEntity country;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (label != null ? !label.equals(that.label) : that.label != null) return false;
        return !(country != null ? !country.equals(that.country) : that.country != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (label != null ? label.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RegionEntity{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", country=" + country +
                '}';
    }

}
