package net.aydini.common.doamin.entity;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import net.aydini.common.doamin.entity.security.SecUser;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;


/**
 * 
 * @author  <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 *
 *Dec 25, 2020
 */
@MappedSuperclass
public abstract class BaseEntityModel extends AbstractEntityModel
{
    /**
     * 
     */
    private static final long serialVersionUID = 2704095076651709280L;
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = true)
    @CreatedBy
    private SecUser createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by", nullable = true)
    @LastModifiedBy
    private SecUser updatedBy;
    
   
    

   
    
    public SecUser getCreatedBy()
    {
        return createdBy;
    }

    public void setCreatedBy(SecUser createdBy)
    {
        this.createdBy = createdBy;
    }

    public SecUser getUpdatedBy()
    {
        return updatedBy;
    }

    public void setUpdatedBy(SecUser updatedBy)
    {
        this.updatedBy = updatedBy;
    }


}