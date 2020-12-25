package net.aydini.common.doamin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

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
    
    @Column(name = "deleted_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedDate;
    
    
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    
    
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    
    
    @Column(name = "version", columnDefinition = " integer DEFAULT 0 ")
    @Version
    private int version;


    public int getVersion()
    {
        return this.version;
    }

    public void setVersion(int version)
    {
        this.version = version;
    }

    public Date getUpdatedDate()
    {
        return this.updatedDate;
    }

    public void setUpdatedDate(Date updatedDate)
    {
        this.updatedDate = updatedDate;
    }

    public Date getDeletedDate()
    {
        return this.deletedDate;
    }

    public void setDeletedDate(Date deletedDate)
    {
        this.deletedDate = deletedDate;
    }

    public Date getCreatedDate()
    {
        return this.createdDate;
    }

    public void setCreatedDate(Date createdDate)
    {
        this.createdDate = createdDate;
    }
    
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

    @PrePersist
    public void prePresist()
    {
        this.createdDate = new Date();
        this.updatedDate = new Date();
    }

    @PreUpdate
    public void preUpdate()
    {
        this.updatedDate = new Date();
    }
}