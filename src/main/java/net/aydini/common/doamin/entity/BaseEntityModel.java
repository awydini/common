package net.aydini.common.doamin.entity;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
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
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private AbstractUser createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by", nullable = true)
    @LastModifiedBy
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private AbstractUser updatedBy;
    
   
    

   
    
    public AbstractUser getCreatedBy()
    {
        return createdBy;
    }

    public void setCreatedBy(AbstractUser createdBy)
    {
        this.createdBy = createdBy;
    }

    public AbstractUser getUpdatedBy()
    {
        return updatedBy;
    }

    public void setUpdatedBy(AbstractUser updatedBy)
    {
        this.updatedBy = updatedBy;
    }


}