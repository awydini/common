package net.aydini.common.doamin.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;




/**
 * 
 * @author  <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 *
 *Dec 25, 2020
 */

@MappedSuperclass
public class Role extends BaseEntityModel implements GrantedAuthority
{

    /**
     * 
     */
    private static final long serialVersionUID = -6143329923730856053L;
    

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
    

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    

    @Override
    @Transient
    public String getAuthority()
    {
        return getName();
    }

    @Transient
    public String getTitle()
    {
        return getName();
    }

}