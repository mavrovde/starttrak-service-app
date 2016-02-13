package com.starttrak.jpa;

/**
 * @author serg.mavrov@gmail.com
 */
public class TitleEntity extends AbstractEntity implements StandardEntity {

    private Long id;
    private String label;

    public TitleEntity() {
    }

    public TitleEntity(Long id, String label) {
        this.id = id;
        this.label = label;
    }

    @Override
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

        TitleEntity that = (TitleEntity) o;

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
        return "TitleEntity{" +
                "id=" + id +
                ", label='" + label + '\'' +
                '}';
    }
}
