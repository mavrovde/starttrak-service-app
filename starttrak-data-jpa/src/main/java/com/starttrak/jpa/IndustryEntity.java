package com.starttrak.jpa;

import javax.persistence.*;

/**
 * @author serg.mavrov@gmail.com
 */
@Entity
@Table(name = "industries", schema = "starttrak")
public class IndustryEntity extends AbstractEntity implements StandardEntity, Labeled {

    @TableGenerator(name = "entity_id_generator_industries",
            table = "entity_ids",
            schema = "starttrak",
            pkColumnName = "gen_name",
            pkColumnValue = "industry_entity",
            valueColumnName = "gen_value",
            initialValue = 0,
            allocationSize = 10
    )
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "entity_id_generator_industries")
    private Long id;

    @Basic
    @Column(name = "label")
    private String label;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IndustryEntity that = (IndustryEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return !(label != null ? !label.equals(that.label) : that.label != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (label != null ? label.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "IndustryEntity{" +
                "id=" + id +
                ", label='" + label + '\'' +
                '}';
    }

}
