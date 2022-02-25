package net.aydini.common.dao;

import net.aydini.common.doamin.entity.AbstractEntityModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 * <p>
 * 25.02.22
 */

@NoRepositoryBean
public interface BaseDao<T extends AbstractEntityModel> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {
}
