package net.aydini.common.doamin.entity;

import java.beans.Transient;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @Author <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 * 
 * Jul 9, 2020
 */

@MappedSuperclass
public abstract class AbstractEntityModel implements EntityModel<Long>{
    /**
     *
     */
    private static final long serialVersionUID = -570069186496403748L;
    @Id
    @GeneratedValue
    private Long id;

    public AbstractEntityModel() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Transient
    public boolean isNew() {
        return null == this.getId();
    }

    public int hashCode() {
        int result = 1;
        result = 31 * result + (this.id == null ? 0 : this.id.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            AbstractEntityModel other = (AbstractEntityModel)obj;
            if (this.id == null) {
                if (other.id != null) {
                    return false;
                }
            } else if (!this.id.equals(other.id)) {
                return false;
            }

            return true;
        }
    }
}