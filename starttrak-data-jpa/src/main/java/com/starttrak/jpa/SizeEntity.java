package com.starttrak.jpa;

import javax.persistence.*;

/**
 * @author serg.mavrov@gmail.com
 */
@Entity
@Table(name = "sizes", schema = "starttrak")
public class SizeEntity extends AbstractEntity implements StandardEntity, Comparable<SizeEntity> {

    @TableGenerator(name = "entity_id_generator_sizes",
            table = "entity_ids",
            schema = "starttrak",
            pkColumnName = "gen_name",
            pkColumnValue = "site_entity",
            valueColumnName = "gen_value",
            initialValue = 0,
            allocationSize = 10
    )
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "entity_id_generator_sizes")
    private Long id;

    @Basic
    @Column(name = "label")
    private String label;


    @Basic
    @Column(name = "min_value")
    private int minValue;

    @Basic
    @Column(name = "max_value")
    private int maxValue;


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

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SizeEntity that = (SizeEntity) o;

        if (minValue != that.minValue) return false;
        if (maxValue != that.maxValue) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return !(label != null ? !label.equals(that.label) : that.label != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (label != null ? label.hashCode() : 0);
        result = 31 * result + minValue;
        result = 31 * result + maxValue;
        return result;
    }

    @Override
    public String toString() {
        return "SizeEntity{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", minValue=" + minValue +
                ", maxValue=" + maxValue +
                '}';
    }

    @Override
    public int compareTo(SizeEntity o) {
        return getMinValue()-o.getMinValue();
    }
}
